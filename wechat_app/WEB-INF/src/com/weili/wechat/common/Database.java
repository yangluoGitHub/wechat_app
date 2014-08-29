package com.weili.wechat.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ����������ȡ���ͷ����ݿ�����
 * 
 * @author shp
 * @since 2006.2.23
 */

public class Database {

	private static final Log log = LogFactory.getLog(Database.class);

	private static DataSource jgpjDataSource = null;
	private static DataSource dmDataSource = null;

	/**
	 * ��ȡϵͳ���ݿ����ӷ������ϲ���
	 * 
	 * @return ����һ�����ݿ�����
	 */

	public static Connection getConnection() {

		Connection conn = null;
		conn = getConnectionPool();
		return conn;
	}

	/**
	 * ��ȡ���ݲֿ����ӷ������ϲ���
	 * 
	 * @return ����һ�����ݿ�����
	 */

	public static Connection getDMConnection(HttpServletRequest req) {

		Connection conn = null;
		if (conn == null)
			conn = getDMConnectionLink(req);
		return conn;
	}

	/**
	 * ��ȡϵͳ���ݿ����ӷ�����������Դ��ȡһ�����ӣ�
	 * 
	 * @return ����һ�����ݿ�����
	 */

	public static Connection getConnectionPool() {

		Connection conn = null;
		try {
			if (jgpjDataSource == null) {
				jgpjDataSource = getDataSource("java:comp/env/jdbc/zjconsole");
			}
			conn = jgpjDataSource.getConnection();
		} catch (SQLException e) {
			log.error("Database::getConnection() run error��" + e);
		}

		return conn;
	}

	/**
	 * ��ȡ���ݲֿ����ӷ�����������Դ��ȡһ�����ӣ�
	 * 
	 * @return ����һ�����ݿ�����
	 */

	public static Connection getDMConnectionPool() {

		Connection conn = null;
		try {
			if (dmDataSource == null) {
				dmDataSource = getDataSource("java:comp/env/jdbc/atmp");
			}
			conn = dmDataSource.getConnection();
		} catch (SQLException e) {
			log.error("Database::getDMConnection() run error��" + e);
		}

		return conn;
	}

	/**
	 * ��ȡϵͳ���ݿ����ӷ�������ȡһ���������ӣ�
	 * 
	 * @return ����һ�����ݿ�����
	 */

	public static Connection getConnectionLink1() {
		Connection conn = null;
		try {
			String driver = CfgProperty.getProperty("db.driverClassName");
			String url = CfgProperty.getProperty("db.url");
			String username = CfgProperty.getProperty("db.username");
			String password = CfgProperty.getProperty("db.password");
			
			if (driver == null || driver.equals(""))
				Class.forName("oracle.jdbc.driver.OracleDriver"); // remote db
			else
				Class.forName(driver);
			if (url == null || url.equals("") || username == null || username.equals("") || password == null || password.equals(""))
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:tsp", "tsp", "tsp");
			else
				conn = DriverManager.getConnection(url, username, password);
			// conn =
			// DriverManager.getConnection("jdbc:oracle:thin:@10.2.7.83:1521:atmp","atmp","atmp");
			// conn =
			// DriverManager.getConnection("jdbc:db2://10.1.2.53:6789/jgpj","PJSYS","PJSYS");
			// Class.forName("COM.ibm.db2.jdbc.app.DB2Driver"); //local db
			// conn =
			// DriverManager.getConnection("jdbc:db2:jgpj","pjsys","PJSYS");

		} catch (ClassNotFoundException e) {
			log.error("Database::getConnectionLink() run error:" + e);
		} catch (SQLException e) {
			log.error("Database::getConnectionLink() run error:" + e);
		}
		return conn;
	}

	/**
	 * proxool��ʽ��������
	 */
	public static Connection getConnectionLink() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("proxool.weiliConn");
			if (conn == null) {
				log.error(" Get connection fail ");
			}
		} catch (SQLException e) {
			log.error("Database::getConnectionLink() run error:" + e);
		}
		return conn;
	}

	/**
	 * ��ȡ���ݲֿ����ӷ�������ȡһ���������ӣ�
	 * 
	 * @return ����һ�����ݿ�����
	 */

	public static Connection getDMConnectionLink(HttpServletRequest req) {

		Connection conn = null;
		try {
			String driverName = req.getSession().getServletContext().getInitParameter("driverName");
			String dwConn = req.getSession().getServletContext().getInitParameter("dwConn");
			String dwUser = req.getSession().getServletContext().getInitParameter("dwUser");
			String dwPasswd = req.getSession().getServletContext().getInitParameter("dwPasswd");

			Class.forName(driverName);
			conn = DriverManager.getConnection(dwConn, dwUser, dwPasswd); // ����
			// Class.forName("COM.ibm.db2.jdbc.app.DB2Driver");
			// conn =
			// DriverManager.getConnection("jdbc:db2:yjhdb","db2admin","db2admin");
		} catch (ClassNotFoundException e) {
			log.error("Database::getDMConnectionLink() run error��" + e);
		} catch (SQLException e) {
			log.error("Database::getDMConnectionLink() run error:" + e);
		}
		return conn;
	}

	/**
	 * �ͷ����ݿ����ӷ���
	 * 
	 * @param -
	 *            Ҫ�ͷŵ����ݿ�����
	 */

	public static void freeConnection(Connection conn) {

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			conn = null;
			log.error("Database::freeConnection(Connection) run error:" + e);
		}
	}

	/**
	 * �ͷ���䷽��
	 * 
	 * @param -
	 *            Ҫ�ͷŵ����
	 */

	public static void freeStatement(Statement st) {

		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			st = null;
			log.error("Database::freeStatement(Statement) run error:" + e);
		}
	}

	/**
	 * �ͷ�Ԥ����䷽��
	 * 
	 * @param -
	 *            Ҫ�ͷŵ�Ԥ�����
	 */

	public static void freePreparedStatement(PreparedStatement ps) {

		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			ps = null;
			log.error("Database::freePreparedStatement(PreparedStatement) run error:" + e);
		}
	}

	/**
	 * �ͷŽ��������
	 * 
	 * @param -
	 *            Ҫ�ͷŵĽ����
	 */

	public static void freeResultSet(ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			rs = null;
			log.error("Database::freeResultSet(ResultSet) run error:" + e);
		}
	}

	/**
	 * ��ȡ����Դ����
	 * 
	 * @param jndiName -
	 * @return ����һ������Դ
	 */

	public static DataSource getDataSource(String jndiName) {

		DataSource ds = null;

		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(jndiName);
		} catch (Exception e) {
			log.error("Database::getDataSource() run error:" + e);
		}
		return ds;
	}
	
//	public static void  main(String[] args){
//		Connection conn = Database.getConnectionLink1();
//		System.out.println(conn==null);
//		
//	}

}