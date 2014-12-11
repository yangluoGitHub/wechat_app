package com.weili.mweb.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.weili.wechat.vo.MOpTableVO;
import com.weili.wechatCom.pojo.Knowledge;

/**
 * Mysql���ݿ������
 * 
 * @author liufeng
 * @date 2013-12-01
 */
public class MySQLUtil1 {
	/**
	 * ��ȡMysql���ݿ�����
	 * 
	 * @return Connection
	 */
	private Connection getConn() {
		String url = "jdbc:mysql://localhost:3306/wechat";
		String username = "wechat";
		String password = "wechat";
		Connection conn = null;
		try {
			// ����MySQL����
			Class.forName("com.mysql.jdbc.Driver");
			// ��ȡ���ݿ�����
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * �ͷ�JDBC��Դ
	 * 
	 * @param conn ���ݿ�����
	 * @param ps
	 * @param rs ��¼��
	 */
	private void releaseResources(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (null != rs)
				rs.close();
			if (null != ps)
				ps.close();
			if (null != conn)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	/**
	 * �ж����ݿ��Ƿ��Ѿ����д�openId
	 * 
	 * @param openId �û���OpenID
	 * @return chatCategory
	 */
	public static List<MOpTableVO> getUserByOpenId(String openId) {
		List<MOpTableVO> list = new ArrayList<MOpTableVO>();
		String sql = "select * from m_op_table where openid=?";

		MySQLUtil1 MySQLUtil1 = new MySQLUtil1();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = MySQLUtil1.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			rs = ps.executeQuery();
			while (rs.next()) {
				MOpTableVO vo = new MOpTableVO();
				vo.setNo(rs.getString("no"));
				vo.setName(rs.getString("name"));
				vo.setMobile(rs.getString("mobile"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setOpenId(rs.getString("openid"));
				list.add(vo);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			MySQLUtil1.releaseResources(conn, ps, rs);
		}
		return list;
	}

	

	/**
	 * ���������¼
	 * 
	 * @param openId �û���OpenID
	 * @param createTime ��Ϣ����ʱ��
	 * @param reqMsg �û����е���Ϣ
	 * @param respMsg �����˺Żظ�����Ϣ
	 * @param chatCategory �������
	 */
	public static void saveChatLog(String openId, String createTime, String reqMsg, String respMsg, int chatCategory) {
		String sql = "insert into chat_log(open_id, create_time, req_msg, resp_msg, chat_category) values(?, ?, ?, ?, ?)";

		MySQLUtil1 MySQLUtil1 = new MySQLUtil1();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = MySQLUtil1.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			ps.setString(2, createTime);
			ps.setString(3, reqMsg);
			ps.setString(4, respMsg);
			ps.setInt(5, chatCategory);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			MySQLUtil1.releaseResources(conn, ps, rs);
		}
	}
}
