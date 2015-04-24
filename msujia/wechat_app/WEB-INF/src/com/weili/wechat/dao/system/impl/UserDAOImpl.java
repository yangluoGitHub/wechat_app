package com.weili.wechat.dao.system.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.ConvertUtils;
import com.weili.wechat.common.HibernateDAO;
import com.weili.wechat.dao.system.UserDAO;
import com.weili.wechat.hibernate.OpTable;
import com.weili.wechat.hibernate.SysRole;
import com.weili.wechat.vo.Role;
import com.weili.wechat.vo.User;

public class UserDAOImpl extends HibernateDAO implements UserDAO {

	private Log log = LogFactory.getLog(UserDAOImpl.class);

	private Class getReferenceClass() {
		return com.weili.wechat.hibernate.OpTable.class;
	}

	private User po_vo(OpTable aSysUser) {
		if (aSysUser == null)
			return null;
		User aUser = new User();
		aUser.setNo(aSysUser.getNo());
		aUser.setPasswd(aSysUser.getPasswd());
		aUser.setName(aSysUser.getName());
		aUser.setStatus(aSysUser.getStatus());
		aUser.setOnline_flag(aSysUser.getOnlineFlag());
		aUser.setTelephone(aSysUser.getPhone());
		aUser.setEmail(aSysUser.getEmail());
		aUser.setMobile(aSysUser.getMobile());
		aUser.setPhoto(aSysUser.getPhoto());
		aUser.setLoginIp(aSysUser.getLoginIp());
		aUser.setLoginTime(aSysUser.getLoginTime());
		aUser.setPasswdExpiration(aSysUser.getPasswdExpiration());
		aUser.setPasswdError(aSysUser.getPasswdError());
		aUser.setSignFlag(aSysUser.getSignFlag());
		if (aSysUser.getStoreId() != null) {
			aUser.setStoreId(aSysUser.getStoreId());
		}
		if (aSysUser.getSysRole() != null) {
			Role aRole = new Role();
			aRole.setNo(aSysUser.getSysRole().getNo());
			aRole.setName(aSysUser.getSysRole().getName());
			aRole.setCatalog(aSysUser.getSysRole().getCatalog());
			aUser.setRole(aRole);
		}

		return aUser;
	}

	private OpTable vo_po(User aUser) {
		if (aUser == null)
			return null;
		OpTable aSysUser = new OpTable();

		aSysUser.setNo(aUser.getNo());
		aSysUser.setPasswd(aUser.getPasswd());
		aSysUser.setName(aUser.getName());
		aSysUser.setStatus(aUser.getStatus());
		aSysUser.setOnlineFlag(aUser.getOnline_flag());
		aSysUser.setPhone(aUser.getTelephone());
		aSysUser.setEmail(aUser.getEmail());
		aSysUser.setMobile(aUser.getMobile());
		aSysUser.setPhoto(aUser.getPhoto());
		aSysUser.setLoginIp(aUser.getLoginIp());
		aSysUser.setLoginTime(aUser.getLoginTime());
		aSysUser.setPasswdExpiration(aUser.getPasswdExpiration());
		aSysUser.setPasswdError(aUser.getPasswdError());
		aSysUser.setSignFlag(aUser.getSignFlag());
		if (aUser.getStoreId() != null) {
			aSysUser.setStoreId(aUser.getStoreId());
		}
		if (aUser.getRole() != null) {
			SysRole aSysRole = new SysRole();
			aSysRole.setNo(aUser.getRole().getNo());
			aSysRole.setName(aUser.getRole().getName());
			aSysUser.setSysRole(aSysRole);
		}

		return aSysUser;
	}

	private List<User> po_vo_list(List<OpTable> poList) {
		List<User> voList = new ArrayList<User>();
		for (OpTable aSysUser : poList) {
			User aUser = po_vo(aSysUser);
			voList.add(aUser);
		}
		return voList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.weili.wechat.common.DAO#delete(java.io.Serializable)
	 */
	public void delete(Serializable id) {
		OpTable aSysUser = (OpTable) super.findById(getReferenceClass(), id);
		this.deleteObject(aSysUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.weili.wechat.common.DAO#qryAll()
	 */
	public List qryAll() {
		// TODO Auto-generated method stub
		List list = this.find("from OpTable o where o.sysRole.catalog!=0 order by o.orgTable.no,o.no asc");
		return po_vo_list(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.weili.wechat.common.DAO#qryById(java.io.Serializable)
	 */
	public Object qryById(Serializable id) {
		// TODO Auto-generated method stub
		OpTable aSysUser = (OpTable) this.findById(getReferenceClass(), id);
		if (aSysUser != null && aSysUser.getStatus() == 0) {
			return null;
		}
		return po_vo(aSysUser);
	}

	public void save(Object obj) {
		// TODO Auto-generated method stub
		this.saveObject(vo_po((User) obj));
	}

	public void saveUser(Object obj) {
		User user = (User) obj;
		Integer roleNo = user.getRole().getNo();
		SysRole sysRole = (SysRole) this.findById(SysRole.class, roleNo);
		OpTable opTable = vo_po(user);
		opTable.setSysRole(sysRole);

		this.saveObject(opTable);
	}

	public void modUser(User user) {
		// TODO Auto-generated method stub

	}

	public void update(Object obj) {
		// TODO Auto-generated method stub
		OpTable tmpSysUser = vo_po((User) obj);
		OpTable aSysUser = (OpTable) super.findById(getReferenceClass(), tmpSysUser.getNo());

		aSysUser.setSysRole(tmpSysUser.getSysRole());
		ConvertUtils.copy(aSysUser, tmpSysUser);
		this.updateObject(aSysUser);
	}

	public List qryUser(Integer roleId) {
		// TODO Auto-generated method stub
		String HQL = "from OpTable t where t.sysRole.no=?";
		List list = this.find(HQL, roleId);
		return po_vo_list(list);
	}

	public List qryUserByName(String roleName) {
		// TODO Auto-generated method stub
		log.info("from OpTable t where t.sysRole.name = '" + roleName + "' or t.sysRole.name like '" + roleName + "-%' or t.sysRole.name like '%-" + roleName
				+ "' or t.sysRole.name like '%-" + roleName + "-%' ");
		List list = this.find("from OpTable t where   t.sysRole.name = '" + roleName + "' or t.sysRole.name like '" + roleName
				+ "-%' or t.sysRole.name like '%-" + roleName + "' or t.sysRole.name like '%-" + roleName + "-%' order by  t.orgTable.no, t.no asc ");
		return po_vo_list(list);
	}

	// public List qryUser(String orgNo) {
	// String HQL="from OpTable t where t.orgTable.no= '" + orgNo +
	// "' and t.sysRole.catalog!=0 order by t.orgTable.no, t.no  asc";
	// List list = this.find(HQL);
	// return po_vo_list(list);
	// }

	// public List qryUser(String orgNo, Integer roleId) {
	// String HQL="from OpTable t where t.orgTable.no = '" + orgNo +
	// "' and t.sysRole.no = " + roleId;
	// List list = this.find(HQL);
	// return po_vo_list(list);
	// }

	// public List qryUserByName(String orgNo,String roleName) {
	// String HQL="from OpTable t where t.orgTable.no = '" + orgNo +
	// "' and (t.sysRole.name = '"+roleName+"' or t.sysRole.name like '"+roleName+"-%' or t.sysRole.name like '%-"+roleName+"' or t.sysRole.name like '%-"+roleName+"-%' ) order by  t.orgTable.no, t.no asc";
	// List list = this.find(HQL);
	// return po_vo_list(list);
	// }

	/**
	 * 判断表中是否有关联数据
	 * 
	 * @param string
	 *            表名,字段，值，类型
	 * @return 0:无关联，1：有关联
	 */
	public int CheckLinkTable(String table, String field, String value, int type) {
		String HQL;
		if (type == 0) {// String
			HQL = "select t." + field + " from " + table + " t where t." + field + "='" + value + "'";
		} else {// Int
			HQL = "select t." + field + " from " + table + " t where t." + field + "=" + value;
		}
		log.info("HQL=[" + HQL + "]");
		List list = this.find(HQL);
		log.debug("HQL=[" + HQL + "]");
		if (list != null && list.size() > 0) {
			log.debug("HQL=[" + HQL + "]");
			return 1;
		} else {
			return 0;
		}
	}

	// public List qryUser(String userName, String orgNo, Integer roleId) {
	// Object[] values = new Object[]{userName, orgNo};
	// String HQL ="";
	// if(roleId ==100 || roleId ==200) {
	// HQL="from OpTable u where u.name=? and u.sysRole.no = " + roleId +
	// " and u.orgTable.no=?";
	// } else {
	// HQL="from OpTable u where u.name=? and u.sysRole.no != " + roleId +
	// " and u.orgTable.no=?";
	// }
	// List list = this.find(HQL, values);
	// return po_vo_list(list);
	// }

	// public List qryUser(String opNo,String orgId,String roleName){
	// String hql = "from OpTable o where 1=1";
	// if(opNo!=null && !"".equals(opNo)){
	// hql += " and o.no like '%"+opNo+"%'";
	// }
	// if(orgId!=null && !"".equals(orgId)){
	// hql += " and o.orgTable.no='"+orgId+"'";
	// }
	// if(roleName!=null && !"".equals(roleName)){
	// hql +=
	// " and (o.sysRole.name = '"+roleName+"' or o.sysRole.name like '"+roleName+"-%' or o.sysRole.name like '%-"+roleName+"' or o.sysRole.name like '%-"+roleName+"-%')";
	// }
	// List list = this.find(hql);
	// return po_vo_list(list);
	// }

	// public List qryAllUserByRole(String orgNo) {
	// String HQL =
	// "from OpTable o where o.sysRole.catalog != 0 and o.orgTable.no = '" +
	// orgNo +"'";
	// List list = this.find(HQL);
	// return po_vo_list(list);
	// }

	public void updateByHql(String updateHql) {
		this.batchDeleteOrUpdate(updateHql);
	}

	public String qryNameById(Serializable id) {
		if (id == null) {
			return null;
		}
		String name = null;
		OpTable aSysUser = (OpTable) this.findById(getReferenceClass(), id);
		if (aSysUser != null) {
			name = aSysUser.getName();
		}
		return name;
	}
}
