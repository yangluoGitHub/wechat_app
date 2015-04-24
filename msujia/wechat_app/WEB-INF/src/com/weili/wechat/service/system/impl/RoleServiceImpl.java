package com.weili.wechat.service.system.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weili.wechat.common.RetInfo;
import com.weili.wechat.dao.system.ButtonDAO;
import com.weili.wechat.dao.system.MenuDAO;
import com.weili.wechat.dao.system.RoleDAO;
import com.weili.wechat.service.system.RoleService;
import com.weili.wechat.vo.Role;

public class RoleServiceImpl extends RetInfo implements RoleService {

	private RoleDAO roleDAO;
	private MenuDAO menuDAO;
	private ButtonDAO buttonDAO;
	
	private static Log log = LogFactory.getLog(RoleServiceImpl.class);
	
	public ButtonDAO getButtonDAO() {
		return buttonDAO;
	}

	public void setButtonDAO(ButtonDAO buttonDAO) {
		this.buttonDAO = buttonDAO;
	}

	public MenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	

	public int addRole(Role role) {
		this.setRetMsg("src.AddRoleSuc");
		initRet();
		
		if(!this.checkRole(role)){
			this.setRetCode(-1);
		}else{
			role.setNo(this.getRoleNo());
			this.getRoleDAO().save(role);
			setRetOK();
			log.debug("角色结束");
		}
		
		if(this.getRetCode() != 0){
			this.setRetMsg(this.getRetMsg());
			this.setRetCode(this.getRetCode());	
			log.debug("src.AddRoleExit");
			return this.getRetCode();
		}else{
			this.setRetMsg("src.AddRoleSuccess");
			this.setRetCode(0);	
			log.debug("src.AddRoleSuccessExit");
			return 0;
		}	
	}

	private int getRoleNo(){
		return this.getRoleDAO().getRoleNo();
	}
	
	public int delRole(Role role) {
		this.setRetMsg("src.DeleteRoleFailed");
		initRet();
		
		if(!this.checkIsExist(role.getNo())){
			log.debug("角色不存在");
			this.setRetCode(-1);
		}else{		
			log.debug("角色存在滴");
			this.getRoleDAO().delete(role.getNo());
			setRetOK();
			log.debug("角色结束");
		}
		
		if(this.getRetCode() != 0){
			this.setRetMsg(this.getRetMsg());
			this.setRetCode(this.getRetCode());	
			log.debug("src.DelRoleExit");
			return this.getRetCode();
		}else{
			this.setRetMsg("src.DeleteRoleSuccess");
			this.setRetCode(0);	
			log.debug("src.DelRoleSuccessExit");
			return 0;
		}
	}

	public int modRole(Role role) {
		this.setRetMsg("src.ModRoleFai");
		initRet();
		
		if(!this.checkIsExist(role.getNo())){
			this.setRetCode(-1);
		}else{
			if(!this.checkRole(role)){
				this.setRetCode(-1);
			}else{
				this.getRoleDAO().update(role);
				setRetOK();
				log.debug("角色结束");
			}
		}
		
		if(this.getRetCode() != 0){
			this.setRetMsg(this.getRetMsg());
			this.setRetCode(this.getRetCode());	
			log.debug("src.ModRoleExit");
			return this.getRetCode();
		}else{
			this.setRetMsg("src.ModRoleSuc");
			this.setRetCode(0);	
			log.debug("src.ModRoleSuccessExit");
			return 0;
		}

	}

	public List qryButton() {
		// TODO Auto-generated method stub
		return this.getButtonDAO().qryAll();
	}

	public List qryButtonByMenu(String menuId) {
		// TODO Auto-generated method stub
		return this.getButtonDAO().qryButtonByMenu(menuId);
	}

	public List qryButtonByRole(Integer roleId) {
		// TODO Auto-generated method stub
		return this.getButtonDAO().qryButtonByRole(roleId);
	}

	public List qryMenu() {
		// TODO Auto-generated method stub
		return this.getMenuDAO().qryAll();
	}

	public List qryMenuByRole(Integer roleId) {
		// TODO Auto-generated method stub
		return this.getMenuDAO().qryMenuByRole(roleId);
	}

	public List qryRole() {
		// TODO Auto-generated method stub
		return this.getRoleDAO().qryAll();
	}

	public Role qryRoleById(Integer roleId) {
		// TODO Auto-generated method stub
		return (Role)this.getRoleDAO().qryById(roleId);
	}
	
	public List qryRole(Integer orgGradeNo) {
		// TODO Auto-generated method stub
		return this.getRoleDAO().qryRoleByOryGrade(orgGradeNo);
	}
	
	public List qryRole1(Integer orgGradeNo) {
		// TODO Auto-generated method stub
		return this.getRoleDAO().qryRoleByOryGrade1(orgGradeNo);
	}
	/**
    * 校验角色的合法性
    * @param roleId,roleName
    * @return boolean
    * @roseuid 47CD0FE802FD
    */
	public boolean checkRole(String roleId,String roleName){
		//合法性标记flag
		boolean flag = false;
		List<Integer> list = this.getRoleDAO().getRoleNoByName(roleName);
		if(list==null||list.size() == 0){
			flag = true;
		}else{
			if(!roleId.equals("")){
				for(Integer roleNo:list){
					if(roleNo==Integer.parseInt(roleId)){
						flag = true;
						break;
					}
				}	
			}	
		}
		log.info("-----------------------------------------");
		log.info(flag);
		log.info("-----------------------------------------");
		return flag;
	}

	public List qryGradeNoByAthor(Integer orgGradeNo) {
//		return this.getOrgGradeDAO().qryOrgGradeByAuthor(orgGradeNo);
		return null;
	}
	
	public List qryRole2(String orgNo) {
//		Org org = (Org)this.getOrgDAO().qryById(orgNo);
//		return this.getRoleDAO().qryRoleByOryGrade1(org.getOrgGrade().getNo());
		return null;
	}
	
	
	public List qryRoleListByOrg(String orgNo) {
//		Org org = (Org)this.getOrgDAO().qryById(orgNo);
//		return this.getRoleDAO().qryRoleListByOryGrade(org.getOrgGrade().getNo());
		return null;
	}
	
	public boolean checkIsExist(Integer roleId) {
		// TODO Auto-generated method stub
		Role aRole = this.qryRoleById(roleId);
		if(aRole != null){
			this.setRetMsg("src.characterExist");
			log.debug("角色编号=["+roleId+"]已存在");
			return true;
		}else{
			this.setRetMsg("src.characterNotExist");
			log.debug("角色编号=["+roleId+"]不存在");
			return false;
		}
	}

	public boolean checkRole(Role role) {
		// TODO Auto-generated method stub
//		if(role == null || role.getName() == null || role.getName().equals("")){
//			this.setRetMsg("src.characterNameNotEmpty");
//			return false;
//		}
//		log.debug("checkRole no=["+role.getNo()+"] name=["+role.getName()+"]");
//		//合法性标记flag
//		boolean flag = false;
//		//List<Role> list = this.getRoleDAO().qryRoleByName(role.getName());
//		List<Role> list = this.getRoleDAO().qryRoleByName(role.getName(), role.getOrgType());
//		if(list.size() == 0){
//			flag = true;
//		}else{
//			if(role.getNo() != null){
//				for(Role aRole:list){
//					if(aRole.getNo().intValue() == role.getNo().intValue()) 
//						flag = true;
//				}	
//			}	
//		}
//		
//		if(flag){
//			this.setRetMsg("src.characterLegal");
//		}else{
//			this.setRetMsg("src.characterNameExist");
//		}
//		return flag;
		return true;
	}
	
	/**
	 * 是否有权力查看&&修改其他人的：验收任务单/任务单/日结单信息
	 * @param roleNo
	 * @return
	 */
	public boolean isAlmighty (Serializable roleId){
		boolean retVal = roleDAO.isAlmighty(roleId);
		return retVal;
	}
	
	public boolean checkIsExistByName(String roleName) {
		List list = this.roleDAO.qryRoleByName(roleName);
		if (list == null || list.size() == 0) {
			return false;
		}
		return true;
	}
	
}
