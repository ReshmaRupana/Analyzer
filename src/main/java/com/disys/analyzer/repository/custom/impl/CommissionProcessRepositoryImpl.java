/**
 * 
 */
package com.disys.analyzer.repository.custom.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.config.database.DbWork;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.CommissionProcess;
import com.disys.analyzer.repository.custom.CommissionProcessRepositoryCustom;

/**
 * @author Sajid
 * 
 *
 */
@Repository
@Transactional(readOnly = true)
public class CommissionProcessRepositoryImpl implements
		CommissionProcessRepositoryCustom {

	public Logger logger = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	EntityManager entityManager;

	DbWork dataBaseObject = new DbWork();

	@Override
	public List<Map<String, Object>> findAllOrderByAccountingPeriod()
			throws Exception {
		String queryString = "Select * from CommissionProcess order by CommissionProcessId desc;";
		logger.debug("Query in CommissionProcessRepositoryImpl is  : "
				+ queryString);
		return dataBaseObject.getResultFromCommission(queryString);
	}

	@Override
	public List<Map<String, Object>> findAccountingPeriod()
			throws Exception {
		String queryString = "Select top 1 accountingPeriod, fiscalYear from CommissionProcess where CommissionMode = 'FINAL' order by fiscalYear desc,accountingPeriod desc;";
		logger.debug("Query in CommissionProcessRepositoryImpl is  : "
				+ queryString);
		return dataBaseObject.getResultFromCommission(queryString);
	}

	@Override
	public Integer saveCommissionProcess(CommissionProcess commissionProcess) {
		String queryString = "INSERT INTO CommissionProcess(AccountingPeriod,FiscalYear,CommissionMode,ExecutedBy,ExecutionDate,Progress) VALUES (";
		queryString += commissionProcess.getAccountingPeriod() + ","
				+ commissionProcess.getFiscalYear() + ",'"
				+ commissionProcess.getCommissionMode() + "','"
				+ commissionProcess.getExecutedBy() + "','"
				+ commissionProcess.getExecutionDate() + "',"
				+ commissionProcess.getCompletionPercentage();
		queryString += ")";
		try {
			logger.debug("Query in CommissionProcessRepositoryImpl is  : "
					+ queryString);
			Integer res = dataBaseObject.saveCommissionProcess(queryString);
			logger.debug("CommissionProcess created is  : " + res);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Integer updateCommissionProcess(CommissionProcess commissionProcess) {
		String queryString = "UPDATE CommissionProcess set ExecutedBy = '";
		queryString += commissionProcess.getExecutedBy()
				+ "',CompletionDate = null" + ",ExecutionDate='"
				+ commissionProcess.getExecutionDate() + "',Progress = "
				+ commissionProcess.getCompletionPercentage()
				+ " where CommissionProcessId = "
				+ commissionProcess.getCommissionProcessId();

		try {
			logger.debug("Query in CommissionProcessRepositoryImpl is  : "
					+ queryString);
			Integer res = dataBaseObject.saveCommissionProcess(queryString);
			logger.debug("CommissionProcess updated is  : " + res);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	@Transactional
    public String executeCommissionProcess(CommissionProcess commissionProcess) {
		String response = "";
		Session session = entityManager.unwrap(Session.class);

		ExecuteCommissionProcessWork work = new ExecuteCommissionProcessWork(
				commissionProcess);
		session.doWork(work);
		response = work.getStatus();
		return response;

	}

	private static class ExecuteCommissionProcessWork implements Work {

		private Logger logger = LoggerFactory.getLogger(getClass());

		String status;

		CommissionProcess commissionProcess;

		/**
		 * @param analyser
		 * @param userId
		 * @param actionType
		 */
		public ExecuteCommissionProcessWork(CommissionProcess commissionProcess) {
			super();
			this.commissionProcess = commissionProcess;
		}

		@Override
		public void execute(Connection connection) throws SQLException {
			status = "Process started successfully!";
			
			CallableStatement callStmt = null;
			ResultSet rs = null;
			DbWork worker = new DbWork();
			try {
				/*
				@varAccountingPeriod INT,	--MONTH
				@varFiscalYear INT,
				@varUserId	VARCHAR(100),
				@varCommissionMode	VARCHAR(50)*/

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".ExecuteCommissionProcess("
						+ commissionProcess.getAccountingPeriod() + ","
						+ commissionProcess.getFiscalYear() + ",'"
						+ commissionProcess.getExecutedBy() + "','"
						+ commissionProcess.getCommissionMode() + "'";
				query += ")}";

				System.out
						.println("Executing Query in ExecuteCommissionProcessWork "
								+ query);

				logger.debug("Executing Query in ExecuteCommissionProcessWork : "
						+ query);

				
				connection = worker.getConnectionFromCommission();
				callStmt = connection.prepareCall(query);

				boolean gotResults = callStmt.execute();
				if (!gotResults) {
					System.out.println("No results returned");
				} else {
					rs = callStmt.getResultSet();
				}
			} catch (Exception ex) {
				status = "1";
				System.out
						.println("Exception in ExecuteCommissionProcessWork");
				logger.debug("Exception in ExecuteCommissionProcessWork");
				logger.debug("Exception " + ex.getMessage());
				ex.printStackTrace();
			} finally {
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (callStmt != null)
					try {
						callStmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if(connection != null){
					try{
						worker.closeCommissionConnection(connection);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}


		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}

	}

	@Override
	public String finalizeCommissionProcess(Integer accountingPeriod,
			Integer fiscalYear) {
		String response = "";
		Session session = entityManager.unwrap(Session.class);

		FinalizeCommissionProcessWork work = new FinalizeCommissionProcessWork(
				accountingPeriod, fiscalYear);
		session.doWork(work);
		response = work.getStatus();
		return response;
	}

	private static class FinalizeCommissionProcessWork implements Work {

		private Logger logger = LoggerFactory.getLogger(getClass());

		String status;

		Integer accountingPeriod;
		Integer fiscalYear;

		/**
		 * @param analyser
		 * @param userId
		 * @param actionType
		 */
		public FinalizeCommissionProcessWork(Integer accountingPeriod,
				Integer fiscalYear) {
			super();
			this.accountingPeriod = accountingPeriod;
			this.fiscalYear = fiscalYear;
		}

		@Override
		public void execute(Connection connection) throws SQLException {
			status = "Process finalized successfully!";

			CallableStatement callStmt = null;
			ResultSet rs = null;
			DbWork worker = new DbWork();
			try {

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".FinalizeDraftCommissionProcess(" + accountingPeriod
						+ "," + fiscalYear;
				query += ")}";

				System.out
						.println("Executing Query in FinalizeDraftCommissionProcess "
								+ query);

				logger.debug("Executing Query in FinalizeDraftCommissionProcess : "
						+ query);

				connection = worker.getConnectionFromCommission();
				callStmt = connection.prepareCall(query);

				callStmt.executeQuery();
			} catch (Exception ex) {
				status = "1";
				System.out
						.println("Exception in FinalizeDraftCommissionProcess");
				logger.debug("Exception in FinalizeDraftCommissionProcess");
				logger.debug("Exception " + ex.getMessage());
				ex.printStackTrace();
			} finally {
				if (rs != null)
					rs.close();
				if (callStmt != null)
					callStmt.close();
				if(connection != null){
					try{
						worker.closeCommissionConnection(connection);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}

		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}

	}

}
