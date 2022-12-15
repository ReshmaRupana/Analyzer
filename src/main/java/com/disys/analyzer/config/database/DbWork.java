/**
 * 
 */
package com.disys.analyzer.config.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sajid
 */
public class DbWork {
	private static Logger logger = LoggerFactory.getLogger(DbWork.class);
	protected Connection connection = null;
	protected boolean closeConnectionAfterQuery = true;

	// protected String WILD_CARD = "like %";
	protected String WILD_CARD = "";

	public List<String> columnList = new ArrayList<String>();
	int tempColumn = 0;

	public DbWork() {
	}

	/*public DbWork(String connPoolName) throws Exception {
		try {
			Context naming = new InitialContext();
			Object pool = naming.lookup(connPoolName);
			DataSource dataSource = (DataSource) pool;
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			String sz = "An error occured while connection to the database for pool "
					+ connPoolName
					+ ".  Error type :  "
					+ e.getMessage()
					+ ".  Error Code : " + e.getMessage();
			String s1 = e.getMessage();
			System.out.println("Error message : "+s1);
			logger.debug("Error message in Db Work " + s1);
			Exception err = new Exception(sz);
			throw err;
		} catch (Exception e) {
			String sz = "An error occured while connection to the database.  "
					+ "Error type :  " + e.getMessage();
			logger.debug("Error message in Db Work " + sz);
			Exception err = new Exception(sz);
			throw err;
		}
	}*/

	private Connection getConnection() {
		// DBConnection disysDB = new DBConnection();
		connection = DBConnection.getConnection();// to get the connection from the real data
		return connection;
	}

	public Connection getConnectionFromCommission() {
		connection = DBConnection.getConnectionFromCommission();
		return connection;
	}

	public Integer saveCommissionProcess(String queryString) {

		Integer number;
		
		Statement stmt = null;
		String sql = "";
		try {
			stmt = getConnectionFromCommission().createStatement();
			sql = queryString;
			synchronized (sql) {
				number = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			}
			return number;
		}

		catch (Exception e) {
			String sz = "An error occured while attempting to execute a query. SQL = "
					+ sql + "  .  Error type :  " + e.getMessage();
			Exception err = new Exception(sz);
			System.err.println("Exception is : "+err.getMessage());
			return -1;
			//throw err;

		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (closeConnectionAfterQuery)
				this.closeCommissionConnection(connection);
		}
	}

	public List<Map<String, Object>> getResultFromCommission(
			String queryString) throws Exception {

		Statement stmt = null;
		String sql = "";
		try {
			stmt = getConnectionFromCommission().createStatement();
			sql = queryString;
			ResultSet rs = stmt.executeQuery(sql);

			List<Map<String, Object>> alist = getRSRowInfo(rs);

			return alist;
		}

		catch (Exception e)

		{
			String sz = "An error occured while attempting to execute a query. SQL = "
					+ sql + "  .  Error type :  " + e.getMessage();
			Exception err = new Exception(sz);
			throw err;

		}

		finally {

			if (stmt != null)
				stmt.close();
			if (closeConnectionAfterQuery)
				this.closeCommissionConnection(connection);
		}
	}

	public List<Map<String, Object>> getResult(String queryString,
			Object[] userParam, String SQLTYPE) throws Exception {
		return getResult(queryString, userParam, SQLTYPE, false);
	}

	public List<Map<String, Object>> getResult(String queryString,
			Object[] userParam, String SQLTYPE, boolean accounting)
			throws Exception {
		System.out.println("Main query -- " + queryString);
		String SQL = getSQLString(queryString, userParam);
		System.out.println("After query -- " + SQL);
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			if (SQLTYPE.trim().equals("SP")) {

				System.out.println("Inside General Connection");
				pstmt = getConnection().prepareStatement(SQL);
				System.out
						.println("Inside General Connection: statement prepared!");

				System.out.println("Sql in DBWork is :" + SQL);
				pstmt.execute();
				// rs = stmt.executeQuery();
				rs = pstmt.getResultSet();
			}
			List<Map<String, Object>> alist = getRSRowInfo(rs);
			return alist;
		} catch (SQLException e) {

			String sz = "An error occured.  Error type :  " + e.getMessage()
					+ ".  Error Code : " + e.getErrorCode();
			Exception err = new Exception(sz);
			throw err;
		} catch (Exception e) {
			// System.out.println("error -- " + e);
			String sz = "An error occured. Error Text : " + e.getMessage();
			System.out.println(e.toString());
			Exception err = new Exception(sz);
			throw err;

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (pstmt != null)
				pstmt.close();
			if (closeConnectionAfterQuery)
				this.closeConnection(connection);
		}
	}

	public List<Map<String, Object>> getResultFromCommission(
			String queryString, Object[] userParam, String SQLTYPE,
			boolean accounting) throws Exception {
		System.out.println("Main query -- " + queryString);
		String SQL = getSQLString(queryString, userParam);
		System.out.println("After query -- " + SQL);
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			if (SQLTYPE.trim().equals("SP")) {

				System.out.println("Inside Commission Connection");
				pstmt = getConnectionFromCommission().prepareStatement(SQL);
				System.out
						.println("Inside Commission Connection: statement prepared!");

				System.out.println("Sql in DBWork is :" + SQL);
				pstmt.execute();
				// rs = stmt.executeQuery();
				rs = pstmt.getResultSet();
			}
			List<Map<String, Object>> alist = getRSRowInfo(rs);
			return alist;
		} catch (SQLException e) {

			String sz = "An error occured.  Error type :  " + e.getMessage()
					+ ".  Error Code : " + e.getErrorCode();
			Exception err = new Exception(sz);
			throw err;
		} catch (Exception e) {
			// System.out.println("error -- " + e);
			String sz = "An error occured. Error Text : " + e.getMessage();
			System.out.println(e.toString());
			Exception err = new Exception(sz);
			throw err;

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (pstmt != null)
				pstmt.close();
			if (closeConnectionAfterQuery)
				this.closeCommissionConnection(connection);
		}
	}

	@SuppressWarnings("null")
	public long executeSQL(String queryString, Object[] userParam,
			boolean commit) throws Exception

	{

		Statement stmt = null;
		Connection conn = null;
		String SQL = "";
		try {

			stmt = getConnection().createStatement();
			SQL = getSQLString(queryString, userParam);
			long count = (long) stmt.executeUpdate(SQL);
			if (commit)
				conn.commit();
			return count;

		}

		catch (Exception e)

		{
			if (conn != null)
				conn.rollback();
			String sz = "An error occured while attempting to execute a query. SQL = "
					+ SQL + "  .  Error type :  " + e.getMessage();
			Exception err = new Exception(sz);
			throw err;

		}

		finally {

			if (stmt != null)
				stmt.close();
			if (closeConnectionAfterQuery)
				this.closeConnection(conn);
		}

	}

	private List<Map<String, Object>> getRSRowInfo(ResultSet rs)
			throws Exception {
		ResultSetMetaData rsMeta;
		int iCount = 0;
		List<Map<String, Object>> aList = new ArrayList<Map<String, Object>>();
		//to get values in the same order as they were taken from the database.
		Map<String, Object> myMap;
		//HashMap<String, Object> tmap;
		try {
			int tempColumn = 0;
			rsMeta = rs.getMetaData();
			int iRowCount = rsMeta.getColumnCount();
			while (rs.next()) {
				//tmap = new HashMap<String, Object>();
				myMap = new LinkedHashMap<String, Object>();
				for (int i = 0; i < iRowCount; i++) {
					//int k = i + 1;
					String columnName = rsMeta.getColumnLabel(i + 1);
					if (tempColumn == 0) {
						columnList.add(columnName.toUpperCase());
					}
					/*tmap.put(columnName.toUpperCase(),
							getRSColumnValue(rs, rsMeta, i + 1));*/
					
					myMap.put(columnName.toUpperCase(),
							getRSColumnValue(rs, rsMeta, i + 1));

				}
				tempColumn = 1;
				iCount++;

				aList.add(iCount - 1, myMap);
			}
			return aList;
		} catch (SQLException e) {
			String sz = "An error occured while attempting to parse resultset column information from the database.  Error type :  "
					+ e.getSQLState() + ".  Error Code : " + e.getErrorCode();
			Exception err = new Exception(sz);
			throw err;
		}
	}

	private Object getRSColumnValue(ResultSet rs, ResultSetMetaData rsMeta,
			int pos) throws Exception {
		try {
			int iType = (int) rsMeta.getColumnType(pos);
			switch (iType) {
			case Types.BIGINT:
				return new Long(rs.getLong(pos));
			case Types.NUMERIC: {
				java.math.BigDecimal bigDecimal = rs.getBigDecimal(pos);
				if (bigDecimal == null)
					return null;
				else if (bigDecimal.scale() == 0)
					return new Long(bigDecimal.longValue());
				else
					return new Double(bigDecimal.doubleValue());
			}

			case Types.CHAR: {
				String s = rs.getString(pos);
				// if (rs.wasNull()) return "";
				return s;
			}
			case Types.TIMESTAMP: {
				java.sql.Timestamp d = rs.getTimestamp(pos);
				// if (rs.wasNull()) return "";
				return d;
			}
			case Types.DATE: {
				java.sql.Date d = rs.getDate(pos);
				// if (rs.wasNull()) return "";
				return d;
			}
			case Types.DOUBLE:
				return new Double(rs.getDouble(pos));
			case Types.FLOAT:
				return new Float(rs.getFloat(pos));
			case Types.INTEGER:
				return new Integer(rs.getInt(pos));
			case Types.LONGVARCHAR:
				return rs.getString(pos);
			case Types.VARBINARY:
				return rs.getBytes(pos);
			case Types.VARCHAR: {
				String s = rs.getString(pos);
				// if (rs.wasNull()) return "";
				return s;
			}
			default:
				return rs.getObject(pos);
			}
		} catch (SQLException e) {
			String sz = "An error occured while attempting to parse resultset column information from the database.  Error type :  "
					+ e.getSQLState() + ".  Error Code : " + e.getErrorCode();
			Exception err = new Exception(sz);
			throw err;
		}
	}

	private String getSQLString(String queryString, Object[] userParam)
			throws Exception {
		if (userParam == null)
			return queryString;

		for (int i = 0; i < userParam.length; i++) {
			String paramVal = (String) userParam[i];
			String locString = "[" + i + "]";
			int loc = queryString.indexOf(locString);

			String head = queryString.substring(0, loc);
			String tail = queryString.substring(loc + locString.length(),
					queryString.length());
			queryString = head + paramVal.toString() + tail;
			/*  if ((paramVal == null) || (paramVal.trim().equals("") ))
			  {
			       head = head.trim();
			       head = head.substring(0,head.length()-1);
			       queryString = head + WILD_CARD + tail;
			  }
			  else
			  {
			      queryString = head + paramVal.toString() + tail;
			  }*/
		}

		return queryString;
	}

	public void closeConnection(Connection connection) {
		try {
			if (connection == null)
				return;
			if (connection.isClosed())
				return;
			DBConnection.closeConnection(connection);
			//connection.close();
		} catch (Exception e) {
		}
	}
	
	public void closeCommissionConnection(Connection connection) {
		try {
			if (connection == null)
				return;
			if (connection.isClosed())
				return;
			DBConnection.closeConnectionCommission(connection);
			//connection.close();
		} catch (Exception e) {
		}
	}
}
