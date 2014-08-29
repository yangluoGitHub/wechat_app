package com.weili.wechat.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.weili.wechat.common.EncryptUtil;
import com.weili.wechat.common.RetInfo;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.dao.CommonData;
import com.weili.wechat.dao.system.RoleDAO;
import com.weili.wechat.dao.system.UserDAO;
import com.weili.wechat.hibernate.OpTable;
import com.weili.wechat.hibernate.PublicAccount;
import com.weili.wechat.hibernate.UserPubaccount;
import com.weili.wechat.hibernate.UserPubaccountId;
import com.weili.wechat.service.system.UserService;
import com.weili.wechat.vo.User;

public class UserServiceImpl extends RetInfo implements UserService {
	
	private static Log log = LogFactory.getLog(UserServiceImpl.class);
	
	//�û���ʼ����
	final private static String initPasswd = "abcd1234";
	
	private UserDAO userDAO;
	private RoleDAO roleDAO;
	private CommonData commonData;
	
	public CommonData getCommonData() {
		return commonData;
	}

	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public List qryUser(Map<String, Object> paramMap) {
		String roleNo = StringUtil.parseString(paramMap.get("roleNo"));
		String opNo = StringUtil.parseString(paramMap.get("opNo"));
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" select b.no, b.name, b.sysRole.no, b.sysRole.name, b.status from OpTable b ");
		if (!opNo.equals("") || !roleNo.equals("")) {
			hql.append("where ");
			if (!opNo.equals("")) {
				hql.append("b.no like ?");
				params.add("%" + opNo + "%");
				if(!roleNo.equals("")){
					hql.append(" and b.sysRole.no=? ");
					params.add(Integer.valueOf(roleNo));
				}
			}else{
				hql.append("b.sysRole.no=? ");
				params.add(Integer.valueOf(roleNo));
			}
		}
		hql.append(" order by b.no ");
		List userList = commonData.getAllResult(hql.toString(), params.toArray());
		return userList;
	}
	
	public int addUser(User user) {
		this.setRetMsg("src.AddStaffFai");
		initRet();
		if(!this.checkUser(user)){
			this.setRetCode(-1);
		}else if(this.checkIsExist(user.getNo())){
			this.setRetCode(-1);
		}else{
			user.setPasswd(EncryptUtil.MD5(initPasswd));
			this.getUserDAO().saveUser(user);
			setRetOK();
		}
		
		
		if(this.getRetCode() != 0){
			this.setRetMsg(this.getRetMsg());
			this.setRetCode(this.getRetCode());	
			log.debug("�����Աʧ�ܽ���");
			return this.getRetCode();
		}else{
			this.setRetMsg("src.AddStaffSuc");
			this.setRetCode(0);	
			log.debug("�����Ա�ɹ�����");
			return 0;
		}
	}

	public int delUser(User user) {
		this.setRetMsg("src.RemovePersonFai");
		
		initRet();
		
		if(!this.checkIsExist(user.getNo())){
			this.setRetCode(-1);
		}else{
			delUserPubAccountByUserID(user.getNo());
			this.getUserDAO().delete(user.getNo());
			setRetOK();
		}
		
		if(this.getRetCode() != 0){
			this.setRetMsg(this.getRetMsg());
			this.setRetCode(this.getRetCode());	
			log.debug("ɾ��ʧ�ܽ���");
			return this.getRetCode();
		}else{
			this.setRetMsg("src.RemovePersonSuc");
			this.setRetCode(0);	
			log.debug("ɾ����Ա�ɹ�����");
			return 0;
		}
	}
	
	/**
	 * �����û�IDɾ���û����ں�Ȩ��
	 * @param id
	 */
	private void delUserPubAccountByUserID(String id){
		String hql = "delete UserPubaccount u where u.opTable.no = ? ";
		commonData.batchDeleteOrUpdate(hql, id);
	}
	
	public int modUser(User user) {
		this.setRetMsg("src.ModStaffFai");
		
		initRet();
		
		if(!this.checkUser(user)){
			this.setRetCode(-1);
		}else if(!this.checkIsExist(user.getNo())){
			this.setRetCode(-1);
		}else{
			User tmpUser = this.qryUserById(user.getNo());
			user.setPasswd(tmpUser.getPasswd());
//			user.setPasswdExpiration(passwdExpiration);
			this.getUserDAO().update(user);
			setRetOK();
		}
		
		
		if(this.getRetCode() != 0){
			this.setRetMsg(this.getRetMsg());
			this.setRetCode(this.getRetCode());	
			log.debug("�޸�ʧ�ܽ���");
			return this.getRetCode();
		}else{
			this.setRetMsg("src.ModStaffSuc");
			this.setRetCode(0);	
			log.debug("�޸���Ա�ɹ�����");
			return 0;
		}
	}
//	
//
	public List qryOrgType() {
		return null;
////		return this.getOrgTypeDAO().qryAll();
	}
//
	public List qryRole() {
		List roleList = commonData.getAllResult("select o.no, o.name from SysRole o where o.catalog!=0 and o.catalog!=9 order by o.no");
		return roleList;
	}
	
	public List qryRole(Integer orgGradeNo) {
		return this.getRoleDAO().qryRoleByOryGrade(orgGradeNo);
	}

	public List qryUser() {
		return this.getUserDAO().qryAll();
	}

	public User qryUserById(String userId) {
		return (User)this.getUserDAO().qryById(userId);
	}
	
	@SuppressWarnings("unchecked")
	public List qryUser(HashMap orgMap, String orgId, String roleName) {
//		log.debug("��ѯ���� �������=["+orgId+"] ��ɫ����=["+roleName+"]");
//		List userList = null;
//		if(orgId.equals("") && roleName.equals("")){
//			userList =  this.qryUser();
//		}else if(!orgId.equals("") && !roleName.equals("")){
//			userList =  this.getUserDAO().qryUserByName(orgId, roleName);
//		}else if(!orgId.equals("")){
//			userList =  this.getUserDAO().qryUser(orgId);			
//		}else if(!roleName.equals("")){
//			userList =  this.getUserDAO().qryUserByName(roleName);
//		}
//		
//		Iterator it = orgMap.keySet().iterator();
//		while(it.hasNext()){
//			log.debug("��Ȩ�޵Ļ���=["+it.next()+"]");
//		}
//		List retList = new ArrayList();
//		for(int i=0;i<userList.size();i++){
//			User aUser = (User)userList.get(i);
//			if(orgMap.containsKey(aUser.getOrg().getNo()))
//				retList.add(aUser);
//		}
//		return retList;
		return null;
	}
	
	public List qryUser(HashMap orgMap, String opNo, String orgId, String roleName) {
//		List userList = this.getUserDAO().qryUser(opNo, orgId, roleName);
//		Iterator it = orgMap.keySet().iterator();
//		while(it.hasNext()){
//			log.debug("��Ȩ�޵Ļ���=["+it.next()+"]");
//		}
//		List retList = new ArrayList();
//		for(int i=0;i<userList.size();i++){
//			User aUser = (User)userList.get(i);
//			if(orgMap.containsKey(aUser.getOrg().getNo()))
//				retList.add(aUser);
//		}
//		return retList;
		return null;
	}
	
	//������Ȩ�ޣ����Բ鿴�����û�
	public List qryUser(String orgId, String roleName) {
//		List userList = null;
//		if(orgId.equals("") && roleName.equals("")){
//			userList =  this.qryUser();
//		}else if(!orgId.equals("") && !roleName.equals("")){
//			userList =  this.getUserDAO().qryUserByName(orgId, roleName);
//		}else if(!orgId.equals("")){
//			userList =  this.getUserDAO().qryUser(orgId);			
//		}else if(!roleName.equals("")){
//			userList =  this.getUserDAO().qryUserByName(roleName);
//		}
//		
//		return userList;
		return null;
	}

	public int resetUserPasswd(User user) {
		this.setRetMsg("src.ResetPswFai");
		
		this.resetUserPasswd(user.getNo(),user.getPasswdExpiration());
		if(this.getRetCode() != 0){
			this.setRetMsg(this.getRetMsg());
			this.setRetCode(this.getRetCode());	
			log.debug("��������ʧ�ܽ���");
			return this.getRetCode();
		}else{
			this.setRetMsg("src.ResetPswSuc");
			this.setRetCode(0);	
			log.debug("��������ɹ�����");
			return 0;
		}
	}

	public void resetUserPasswd(String userId,String passwdExpiration) {
		initRet();
		
		if(!this.checkIsExist(userId)){
			this.setRetCode(-1);
			return ;
		}else{
			User user = this.qryUserById(userId);
			user.setPasswd(EncryptUtil.MD5(initPasswd));
			user.setPasswdExpiration(passwdExpiration);
			this.getUserDAO().update(user);
		}
		
		setRetOK();
	}
	
	public int modUserPasswd(User user) {
		this.setRetMsg("src.ModPswFai");
		log.debug("================================"+user.getNo());
		this.modUserPasswd(user.getNo(), user.getPasswd(), user.getNewPasswd(),user.getPasswdExpiration());
		if(this.getRetCode() != 0){
			this.setRetMsg(this.getRetMsg());
			this.setRetCode(this.getRetCode());	
			log.debug("�޸�����ʧ�ܽ���");
			return this.getRetCode();
		}
		User tempUser = this.qryUserById(user.getNo());  
		
		if(tempUser.getOnline_flag().intValue() == -1){
			tempUser.setOnline_flag(0);
			this.modUser(tempUser);
			if(this.getRetCode() != 0){
				this.setRetMsg(this.getRetMsg());
				this.setRetCode(this.getRetCode());	
				return this.getRetCode();
			}
		}
		
		this.setRetMsg("src.ModPswSuc");
		this.setRetCode(0);	
		log.debug("�޸�����ɹ�����");
		return 0;
	}
	
	public void modUserPasswd(String userId, String oldPasswd, String passwd,String passwdExpiration) {
		initRet();
		
		if(!this.checkIsExist(userId)){
			this.setRetCode(-1);
			return ;
		}else{
			User user = this.qryUserById(userId);
			log.debug("��Ա���=["+user.getNo()+"] ����=["+user.getPasswd()+"] ��������=["+oldPasswd+"]");
			if(!EncryptUtil.MD5(oldPasswd).equals(user.getPasswd())){
				this.setRetMsg("src.oldPasswordError");
				this.setRetCode(-1);
				return ;
			}
			user.setPasswd(EncryptUtil.MD5(passwd));
			log.info("------------------------&&"+passwdExpiration);
            user.setPasswdExpiration(passwdExpiration);
			this.getUserDAO().update(user);
		}
		
		setRetOK();
		
	}
	
	public Boolean checkIsExist(String userId){
		User aUser = this.qryUserById(userId);
		if(aUser != null){
			this.setRetMsg("src.noExist");
			log.debug("��Ա���=["+userId+"]�Ѵ���");
			return true;
		}else{
			this.setRetMsg("src.noNotExist");
			log.debug("��Ա���=["+userId+"]������");
			return false;
		}
	}		

	public Boolean checkUser(User user) {
//		if(user == null || user.getNo() == null || user.getNo().equals("")){
//			this.setRetMsg("src.noNotEmpty");
//			return false;
//		}
//		if(user == null || user.getName() == null || user.getName().equals("")){
//			this.setRetMsg("src.staffNameNotNull");
//			return false;
//		}		
//		log.debug("checkUser no=["+user.getNo()+"] name=["+user.getName()+"]");
//		if(user.getOrg() == null || user.getOrg().getNo() == null || user.getOrg().getNo().equals("")){
//			this.setRetMsg("src.departmentBelongNotNull");
//			return false;
//		} else {
//			Org orgVO = (Org)this.getOrgDAO().qryById(user.getOrg().getNo());
//			if(orgVO == null) {
//				this.setRetMsg("����������!");
//				return false;
//			}
//		}
//		
//		if(user.getRole() == null || user.getRole().getNo() == null){
//			this.setRetMsg("src.characterNotEmpty");
//			return false;
//		} else {
//			Role role = (Role)this.getRoleDAO().qryById(user.getRole().getNo());
//			if(role == null) {
//				this.setRetMsg("��ɫ������!");
//				return false;
//			}
//		}
		
		return true;
	}
	
	/**
	 * �����û�ID����ѯ�û��� 
	 */
	public String qryUserNameById(String no){
		User user = (User)this.getUserDAO().qryById(no);
		if (user != null) {
			return user.getName();
		} else {
			return "";
		}
	}
	/**
	 * autocomplete�����������Դ
	 * @throws JSONException 
	 */
	public String qryIdNameById(String no) throws JSONException{
		//������DB2��sql���
//		String qrySql = "SELECT no,name FROM op_table where no like '%" + no + "%' or name like '%" + no + "%' and status!=0 order by no desc FETCH FIRST 10 ROWS ONLY ";
		//������ORACLE��sql���
		String qrySql = "SELECT no,name FROM (SELECT * FROM op_table d WHERE d.no LIKE '%"+ no +"%' OR d.name LIKE '%"+ no +"%' ORDER BY d.no) WHERE ROWNUM <=10 ";
		List resultList = commonData.findSQL(qrySql);
		JSONArray jsonArray = new JSONArray();
		for (Object obj : resultList) {
			Object[] idName = (Object[])obj;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", idName[0].toString());
			jsonObject.put("label", idName[1].toString());
			jsonArray.put(jsonObject);
		}
		return jsonArray.toString();
	}

	/**
	 * ��ѯ�û��й���Ȩ�޵Ĺ��ں�{(���ں�ԭʼID, ���ں�����)}
	 */
	public List qryUserPubAccount(String userId){
		String hql = "select u.publicAccount.id, u.publicAccount.wechatName from UserPubaccount u where u.opTable.no = ? order by u.publicAccount.id";
		return commonData.getAllResult(hql, userId);
	}
	
	
	@Override
	public List qrySwitchableUserPubAccount(String userId, String currPubAccount) {
		String hql = "select u.publicAccount.id, u.publicAccount.wechatName from UserPubaccount u where u.opTable.no = ? and u.publicAccount.id <> ? order by u.publicAccount.id";
		return commonData.getAllResult(hql, new Object[]{userId, currPubAccount});
	}
	
	public List qryUserPubAccountID(String userId){
		String hql = "select u.publicAccount.id from UserPubaccount u where u.opTable.no = ? order by u.publicAccount.id";
		List list = commonData.getAllResult(hql, userId);
		return list;
	}
	
	public List qryAllPubAccount(){
		String hql = "select u.id, u.wechatName, u.logoLocation from PublicAccount u order by u.id";
		List list = commonData.getAllResult(hql);
		return list;
	}
	
	public int addUserPubAccount(String userId, String[] PaIDs) {
		initRet();
		OpTable user = (OpTable) this.commonData.retrieveObject(OpTable.class, userId);
		if (user == null) {
			this.setRetMsg("�û�" + userId + "������");
			return -1;
		}
		for (int i = 0; i < PaIDs.length; i++) {
			PublicAccount pa = (PublicAccount) this.commonData.retrieveObject(PublicAccount.class, PaIDs[i]);
			if (pa == null) {
				this.setRetMsg("���ں�" + PaIDs[i] + "������");
				return -1;
			}
			try {
				UserPubaccountId upaid = new UserPubaccountId(user, pa);
				UserPubaccount UPa = new UserPubaccount(upaid);
				this.commonData.createObject(UPa);
			} catch (Exception e) {
				e.printStackTrace();
				this.setRetMsg("�ڲ�����");
				return -1;
			}
		}
		return 0;
	}
	
	public void delUserPubAccount(String userId) {
		String hql = "delete from UserPubaccount u where u.opTable.no = ?";
		commonData.batchDeleteOrUpdate(hql, userId);
	}
	
}
