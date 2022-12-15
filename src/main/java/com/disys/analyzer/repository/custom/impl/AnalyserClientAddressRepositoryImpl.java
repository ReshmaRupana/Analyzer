/**
 * 
 */
package com.disys.analyzer.repository.custom.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.AnalyserClientAddress;
import com.disys.analyzer.repository.custom.AnalyserClientAddressRepositoryCustom;

/**
 * @author Sajid
 * 
 */
public class AnalyserClientAddressRepositoryImpl implements AnalyserClientAddressRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.disys.analyzer.repository.custom.AnalyserClientAddressRepositoryCustom#
	 * getAnalyserClientAddressInfo(java.lang.String)
	 */
	@Override
	public AnalyserClientAddress getAnalyserClientAddressInfo(String analyserAddressId) {
		Session session = entityManager.unwrap(Session.class);
		AnalyserClientAddressWork work = new AnalyserClientAddressWork(analyserAddressId);
		session.doWork(work);
		AnalyserClientAddress address = work.getAddress();
		return address;
	}

	private static class AnalyserClientAddressWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private AnalyserClientAddress address;
		String analyserAddressId;

		String action;
		String result;
		Integer actionType;

		ResultSet rs = null;
		CallableStatement callStmt = null;

		/**
		 * @param analyserAddressId
		 */
		public AnalyserClientAddressWork(String analyserAddressId) {
			super();
			this.action = "Info";
			this.analyserAddressId = analyserAddressId;
		}

		public AnalyserClientAddressWork(String action, AnalyserClientAddress clientAddress, Integer actionType) {
			super();
			this.action = action;
			this.address = clientAddress;
			this.actionType = actionType;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			if (action.equals("Add")) {
				String query = "";

				/*
				 * @intActionType int,
				 * 
				 * @varLoggedUserID varchar(50),
				 * 
				 * @intAddressID int = null,
				 * 
				 * @intClientId int,
				 * 
				 * @varContactName varchar(100),
				 * 
				 * @varAddress1 VARCHAR(250),
				 * 
				 * @varAddress2 VARCHAR(250) = null,
				 * 
				 * @varCity VARCHAR(50),
				 * 
				 * @varState VARCHAR(25),
				 * 
				 * @varZip VARCHAR(15),
				 * 
				 * @varTel VARCHAR(50),
				 * 
				 * @varFax VARCHAR(50) = null,
				 * 
				 * @varEmail VARCHAR(50),
				 * 
				 * @varCountry VARCHAR(50),
				 * 
				 * @intRecordStatus int,
				 * 
				 * @varDistributionMethod Varchar(50),
				 * 
				 * @varSpecialNotes Varchar(1000),
				 * 
				 * @varDysBillType Varchar(50)= '',
				 * 
				 * @varDysDeliveryType Varchar(50)= '',
				 * 
				 * @varDysPracticeArea Varchar(50)= ''
				 */

				String userId = FacesUtils.getCurrentUserId();
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spAddUpdateAnalyserAddress("+actionType+", '" + userId + "',"
						+ address.getAddressId() + "," + address.getClientId() + ",'" + address.getContactName() + "','"
						+ address.getAddress1() + "','" + address.getAddress2() + "','" + address.getCity() + "','"
						+ address.getState() + "','" + address.getZipCode() + "','" + address.getTelephone() + "','"
						+ address.getFax() + "','" + address.getEmail() + "','" + address.getCountry() + "',1,'"
						+ address.getDistributionMethod() + "','" + address.getSpecialNotes() + "','','','')}";

				System.out.println("query " + query);
				logger.debug("spAddUpdateAnalyserAddress query is : " + query);

				callStmt = connection.prepareCall(query);
				rs = callStmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						result = "" + rs.getString(1);
					}
					// con.close();
					callStmt.close();
					rs.close();
					// con = null;
					callStmt = null;
					rs = null;
				} else {
					result = "-1";
					logger.debug("spAddUpdateAnalyserAddress could not be added");
				}
			} else {
				address = new AnalyserClientAddress();
				String query = "select DysBillType,DysDeliveryType,DysPracticeArea,  DistributionMethod, SpecialNotes, Address_Id, Analyser_ClientAddress.Country, Analyser_ClientAddress.Client_Id,Analyser_ClientAddress.Org_ID,Contact_Name,Address1,Address2,City, State, ltrim(rtrim(Zipcode)) as Zipcode, Telephone, ltrim(rtrim(Fax)) as Fax, ltrim(rtrim(Email)) as Email,Analyser_ClientAddress.Updated_By,Analyser_ClientAddress.Status,"
						+ "cast(client.Client_Id as varchar) + " + "'~'"
						+ " + Company  as ClientCompany from Analyser_ClientAddress,(select Client_Id,Company from Analyser_Client) client where Analyser_ClientAddress.Address_Id = "
						+ analyserAddressId + " and Analyser_ClientAddress.Client_Id = client.Client_Id";

				System.out.println("query " + query);

				logger.debug("Query in AnalyserClientAddressWork " + query);
				callStmt = connection.prepareCall(query);
				rs = callStmt.executeQuery();

				try {

					if (rs != null) {
						if (rs.next()) {
							address.setAddressId(rs.getInt("Address_Id"));
							address.setClientId(rs.getInt("Client_Id"));
							address.setOrgId(rs.getInt("Org_Id"));
							address.setContactName(rs.getString("Contact_Name"));
							address.setAddress1(rs.getString("Address1"));
							address.setAddress2(rs.getString("Address2"));
							address.setCity(rs.getString("City"));
							address.setState(rs.getString("State"));
							address.setZipCode(rs.getString("Zipcode"));
							address.setTelephone(rs.getString("Telephone"));
							address.setFax(rs.getString("Fax"));
							address.setEmail(rs.getString("Email"));
							address.setUpdatedBy(rs.getString("Updated_By"));
							address.setCountry(rs.getString("Country"));
							address.setDistributionMethod(rs.getString("DistributionMethod"));
							address.setSpecialNotes(rs.getString("SpecialNotes"));
							address.setDysBillType(rs.getString("DysBillType"));
							address.setDysDeliveryType(rs.getString("DysDeliveryType"));
							address.setDysPracticeArea(rs.getString("DysPracticeArea"));
						}
					} else {
						address = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs != null)
						rs.close();
					if (callStmt != null)
						callStmt.close();
				}
			}
		}

		/**
		 * @return the address
		 */
		public AnalyserClientAddress getAddress() {
			return address;
		}

		/**
		 * @return the result
		 */
		public String getResult() {
			return result;
		}

	}

	@Override
	public String saveAnalyserClientCompanyAddress(Integer actionType,AnalyserClientAddress clientAddress) {
		Session session = entityManager.unwrap(Session.class);
		String action = "Add";
		AnalyserClientAddressWork work = new AnalyserClientAddressWork(action, clientAddress,actionType);
		session.doWork(work);
		String res = work.getResult();
		return res;

	}

}
