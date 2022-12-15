/**
 * 
 */
package com.disys.analyzer.config.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Sajid
 *
 */
public interface ConnectionPool
{
	Connection getConnection() throws SQLException;
	
	boolean releaseConnection(Connection connection);
	
	List<Connection> getConnectionPool();
	
	int getSize();
	
	String getUrl();
	
	String getUser();
	
	String getPassword();
	
	void shutdown() throws SQLException;
}
