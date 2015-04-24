package com.weili.wechat.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;



/**
 * jdbc连接处理类
 * @author zhangyf
 */


public class JdbcUtil {
   
	private DataSource dataSource = null;


	/**
	 * 获取数据库连接（从数据源获取一个连接）
	 * @return 返回一个数据库连接
	 */
    
	public Connection getConnection() throws SQLException{

		Connection conn = null;
		try {		
			if (dataSource == null) {
				dataSource = getDataSource();
			}
		    conn = dataSource.getConnection();
		} 
		catch (SQLException e) {
			System.out.println(e);
			throw new SQLException("获取数据库连接失败：" + e);			
		}		
		return conn;
	}
	
	/**
	 * 获取数据库连接（从数据源获取一个连接）
	 * @return 返回一个数据库连接
	 */
    
	public Connection getConnectionByJndi() throws SQLException{

		Connection conn = null;
		try {		
			if (dataSource == null) {
        		try {
					InitialContext ctx = new InitialContext();
					dataSource = (DataSource) ctx
							.lookup("java:comp/env/jdbc/tsp");
				} catch (Exception e) {
					e.printStackTrace();
				}    
			}
		    conn = dataSource.getConnection();

		} 
		catch (SQLException e) {
			System.out.println(e);
			throw new SQLException("获取数据库连接失败：" + e);			
		}		
		return conn;
	}
	
	/**
	 * 获取数据库连接
	 * @return 返回一个数据库连接
	 */
    
	public Connection getConnection(String url,String user,String passwd) throws Exception{

		Connection conn = null;
		try {		
			if (dataSource == null) {
				dataSource = getDataSource();
			}
			String driverName="oracle.jdbc.driver.OracleDriver";
			Driver driver = (Driver) Class.forName(driverName).newInstance();
		    conn = DriverManager.getConnection(url, user,passwd);
		} 
		catch (Exception e) {
			System.out.println(e);
			throw new Exception("获取数据库连接失败：" + e);			
		}		
		return conn;
	}
	
	/**
	 * 释放数据库连接
	 * @param arg0 要释放的数据库连接
	 */

	public void freeConnection(Connection arg0) {

		try {
			if (arg0 != null) {
				arg0.close();
			}
		} catch (SQLException e) {
			arg0 = null;
		}
	}

	/**
	 * 释放语句
	 * @param arg0 要释放的语句
	 */

	public void freeStatement(Statement arg0) {

		try {
			if (arg0 != null) {
				arg0.close();
			}
		} catch (SQLException e) {
			arg0 = null;
		}
	}

	/**
	 * 释放预备语句
	 * @param arg0 要释放的预备语句
	 */

	public void freePreparedStatement(PreparedStatement arg0) {

		try {
			if (arg0 != null) {
				arg0.close();
			}
		} catch (SQLException e) {
			arg0 = null;
		}
	}

	/**
	 * 释放结果集
	 * @param arg0 要释放的结果集
	 */

	public void freeResultSet(ResultSet arg0) {

		try {
			if (arg0 != null) {
				arg0.close();
			}
		} catch (SQLException e) {
			arg0 = null;
		}
	}



	public DataSource getDataSource() {
		return dataSource;
	}



	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	



}