/**
 * 
 */
package com.disys.analyzer.config.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Sajid
 *
 */
public class AnalyserConnectionPool implements ConnectionPool
{
	private final String url;
	private final String user;
	private final String password;
	private final List<Connection> connectionPool;
	private final List<Connection> usedConnections = new ArrayList<>();
	private static int INITIAL_POOL_SIZE = 10;
	private static int MAX_POOL_SIZE = 50;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static AnalyserConnectionPool create(String url, String user, String password, Integer initPoolSize, Integer maxPoolSize) throws SQLException
	{
		System.out.println("Inside AnalyserConnectionPool --> create");
		
		INITIAL_POOL_SIZE = initPoolSize;
		List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
		MAX_POOL_SIZE = maxPoolSize;
		for (int i = 0; i < INITIAL_POOL_SIZE; i++)
		{
			pool.add(createConnection(url, user, password));
		}
		System.out.println("URL = "+url+", user = "+user+", initPoolSize = "+initPoolSize+", maxPoolSize = "+maxPoolSize);
		System.out.println("Pool Size = "+pool.size());
		System.out.println("Pool = "+pool.toString());
		return new AnalyserConnectionPool(url, user, password, pool);
	}
	
	private AnalyserConnectionPool(String url, String user, String password, List<Connection> connectionPool)
	{
		this.url = url;
		this.user = user;
		this.password = password;
		this.connectionPool = connectionPool;
	}
	
	@Override
	public Connection getConnection() throws SQLException
	{
		logger.debug("Inside AnalyserConnectionPool --> getConnection");
		System.out.println("Inside AnalyserConnectionPool --> getConnection");
		logger.debug("ConnectionPool Size = "+connectionPool.size());
		logger.debug("UsedConnections Size = "+usedConnections.size());
		System.out.println("ConnectionPool Size = "+connectionPool.size());
		System.out.println("UsedConnections Size = "+usedConnections.size());
		
		if (connectionPool.isEmpty())
		{
			logger.debug("ConnectionPool is EMPTY");
			System.out.println("ConnectionPool is EMPTY");
			
			logger.debug("usedConnections.size() = "+usedConnections.size());
			System.out.println("usedConnections.size() = "+usedConnections.size());
			if (usedConnections.size() < MAX_POOL_SIZE)
			{
				logger.debug("ConnectionPool is EMPTY so getting a NEW CONNETION");
				System.out.println("ConnectionPool is EMPTY so getting a NEW CONNETION");
				connectionPool.add(createConnection(url, user, password));
			}
			else
			{
				throw new RuntimeException("Maximum pool size reached, no available connections!");
			}
		}
		
		Connection connection = connectionPool.remove(connectionPool.size() - 1);
		usedConnections.add(connection);
		return connection;
	}
	
	@Override
	public boolean releaseConnection(Connection connection)
	{
		boolean removeUsedConnectionResult = false;
		
		logger.debug("Inside AnalyserConnectionPool --> releaseConnection : BEFORE RELEASE");
		System.out.println("Inside AnalyserConnectionPool --> releaseConnection : BEFORE RELEASE");
		logger.debug("ConnectionPool Size = "+connectionPool.size());
		logger.debug("UsedConnections Size = "+usedConnections.size());
		System.out.println("ConnectionPool Size = "+connectionPool.size());
		System.out.println("UsedConnections Size = "+usedConnections.size());
		
		//connectionPool.add(connection);
		removeUsedConnectionResult = usedConnections.remove(connection);
		if (removeUsedConnectionResult)
		{
			logger.debug("Inside AnalyserConnectionPool --> releaseConnection : CONNECTION SUCCESSFULLY RELEASED");
			System.out.println("Inside AnalyserConnectionPool --> releaseConnection : CONNECTION SUCCESSFULLY RELEASED");
			connectionPool.add(connection);
		}
		else
		{
			logger.error("Inside AnalyserConnectionPool --> releaseConnection : ERROR RELEASING CONNECTION");
			System.err.println("Inside AnalyserConnectionPool --> releaseConnection : ERROR RELEASING CONNECTION");
		}
		
		logger.debug("removeUsedConnectionResult = "+removeUsedConnectionResult);
		System.out.println("removeUsedConnectionResult = "+removeUsedConnectionResult);
		logger.debug("Inside AnalyserConnectionPool --> releaseConnection : AFTER RELEASE");
		System.out.println("Inside AnalyserConnectionPool --> releaseConnection : AFTER RELEASE");
		logger.debug("ConnectionPool Size = "+connectionPool.size());
		logger.debug("UsedConnections Size = "+usedConnections.size());
		System.out.println("ConnectionPool Size = "+connectionPool.size());
		System.out.println("UsedConnections Size = "+usedConnections.size());
		
		//return usedConnections.remove(connection);
		return removeUsedConnectionResult;
	}
	
	private static Connection createConnection(String url, String user, String password) throws SQLException
	{
		return DriverManager.getConnection(url, user, password);
	}
	
	@Override
	public int getSize()
	{
		logger.debug("Inside AnalyserConnectionPool --> getSize");
		System.out.println("Inside AnalyserConnectionPool --> getSize");
		logger.debug("ConnectionPool Size = "+connectionPool.size());
		logger.debug("UsedConnections Size = "+usedConnections.size());
		System.out.println("ConnectionPool Size = "+connectionPool.size());
		System.out.println("UsedConnections Size = "+usedConnections.size());
		
		return connectionPool.size() + usedConnections.size();
	}
	
	@Override
	public List<Connection> getConnectionPool()
	{
		return connectionPool;
	}
	
	@Override
	public String getUrl()
	{
		return url;
	}
	
	@Override
	public String getUser()
	{
		return user;
	}
	
	@Override
	public String getPassword()
	{
		return password;
	}
	
	@Override
	public void shutdown() throws SQLException
	{
		usedConnections.forEach(this::releaseConnection);
		for (Connection c : connectionPool)
		{
			c.close();
		}
		connectionPool.clear();
	}
	
}
