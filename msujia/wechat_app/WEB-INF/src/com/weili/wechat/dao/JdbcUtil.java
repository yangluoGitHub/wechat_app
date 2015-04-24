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
 * jdbc���Ӵ�����
 * @author zhangyf
 */


public class JdbcUtil {
   
	private DataSource dataSource = null;


	/**
	 * ��ȡ���ݿ����ӣ�������Դ��ȡһ�����ӣ�
	 * @return ����һ�����ݿ�����
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
			throw new SQLException("��ȡ���ݿ�����ʧ�ܣ�" + e);			
		}		
		return conn;
	}
	
	/**
	 * ��ȡ���ݿ����ӣ�������Դ��ȡһ�����ӣ�
	 * @return ����һ�����ݿ�����
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
			throw new SQLException("��ȡ���ݿ�����ʧ�ܣ�" + e);			
		}		
		return conn;
	}
	
	/**
	 * ��ȡ���ݿ�����
	 * @return ����һ�����ݿ�����
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
			throw new Exception("��ȡ���ݿ�����ʧ�ܣ�" + e);			
		}		
		return conn;
	}
	
	/**
	 * �ͷ����ݿ�����
	 * @param arg0 Ҫ�ͷŵ����ݿ�����
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
	 * �ͷ����
	 * @param arg0 Ҫ�ͷŵ����
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
	 * �ͷ�Ԥ�����
	 * @param arg0 Ҫ�ͷŵ�Ԥ�����
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
	 * �ͷŽ����
	 * @param arg0 Ҫ�ͷŵĽ����
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