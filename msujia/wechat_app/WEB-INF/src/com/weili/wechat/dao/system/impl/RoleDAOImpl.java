package com.weili.wechat.dao.system.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;

import com.weili.wechat.common.HibernateDAO;
import com.weili.wechat.dao.system.RoleDAO;
import com.weili.wechat.hibernate.OpTable;
import com.weili.wechat.hibernate.SysButton;
import com.weili.wechat.hibernate.SysConfRoleButton;
import com.weili.wechat.hibernate.SysConfRoleButtonId;
import com.weili.wechat.hibernate.SysConfRoleMenu;
import com.weili.wechat.hibernate.SysConfRoleMenuId;
import com.weili.wechat.hibernate.SysMenu;
import com.weili.wechat.hibernate.SysRole;
import com.weili.wechat.vo.Button;
import com.weili.wechat.vo.Menu;
import com.weili.wechat.vo.Role;
/**
 * @author ykliu
 *
 */
public class RoleDAOImpl extends HibernateDAO implements RoleDAO {

	private Log log = LogFactory.getLog(RoleDAOImpl.class);
	
    private Class getRefClass() {
        return com.weili.wechat.hibernate.SysRole.class;
    }
    
    private Role po_vo(SysRole aSysRole){
    	if(aSysRole == null) return null;
    	Role aRole = new Role();
    	aRole.setNo(aSysRole.getNo());
    	aRole.setName(aSysRole.getName());
    	aRole.setCatalog(aSysRole.getCatalog());
    	aRole.setNote(aSysRole.getNote());
    	log.debug("po_vo转换start");
    	try{
    		log.debug("po_vo转换menu");
			Iterator iterator = aSysRole.getSysConfRoleMenus().iterator();
//			Iterator iterator = super.find("select t.id.sysMenu  from SysConfRoleMenu t where t.id.sysRole.no=? ", aSysRole.getNo()).iterator();
			while(iterator.hasNext()){
				Menu aMenu = new Menu();
				SysConfRoleMenu aSysConfRoleMenu = (SysConfRoleMenu)iterator.next();
				SysMenu aSysMenu = aSysConfRoleMenu.getId().getSysMenu();
				//SysMenu aSysMenu = (SysMenu)iterator.next();
				if(aSysMenu != null){
			    	aMenu.setNo(aSysMenu.getNo());
			    	aMenu.setName(aSysMenu.getName());
			    	aMenu.setLevel(aSysMenu.getMenuLevel());
			    	aMenu.setOrder(aSysMenu.getMenuOrder());
			    	aMenu.setURL(aSysMenu.getUrl());
			    	Menu aSuperMenu = new Menu();
			    	if (aSysMenu.getSysMenu()!=null){
				    	aSuperMenu.setNo(aSysMenu.getSysMenu().getNo());
				    	aSuperMenu.setName(aSysMenu.getSysMenu().getName());
			    	}
			    	aMenu.setSuperMenu(aSuperMenu);
				}
				aRole.getMenus().add(aMenu);
			}
			log.debug("po_vo转换button");
		    iterator = aSysRole.getSysConfRoleButtons().iterator();
//		    iterator = super.find("select t.id.sysButton  from SysConfRoleButton t where t.id.sysRole.no=? ", aSysRole.getNo()).iterator();
			while(iterator.hasNext()){
				Button aButton = new Button();
				SysConfRoleButton aSysConfRoleButton = (SysConfRoleButton)iterator.next();
				SysButton aSysButton = aSysConfRoleButton.getId().getSysButton();
//				SysButton aSysButton = (SysButton)iterator.next();
				if(aSysButton != null){
					aButton.setNo(aSysButton.getNo());
			    	aButton.setName(aSysButton.getName());
			    	aButton.setOrder(aSysButton.getButtonOrder());
			    	aButton.setURL(aSysButton.getUrl());
			    	aButton.setNote(aSysButton.getNote());
			    	Menu aMenu = new Menu();
			    	if (aSysButton.getSysMenu()!=null){
				    	aMenu.setNo(aSysButton.getSysMenu().getNo());
				    	aMenu.setName(aSysButton.getSysMenu().getName());
			    	}
			    	aButton.setMenu(aMenu);
				}
				aRole.getButtons().add(aButton);
			}
		}catch(org.hibernate.LazyInitializationException e){}
		log.debug("po_vo转换end");
    	return aRole;
    }
    
    private SysRole vo_po(Role aRole){
    	if(aRole == null) return null;
    	SysRole aSysRole = new SysRole();
    	aSysRole.setNo(aRole.getNo());
    	aSysRole.setName(aRole.getName());
    	aSysRole.setCatalog(aRole.getCatalog());
    	aSysRole.setNote(aRole.getNote());
    	log.debug("vo_po转换start");
    	try{
    		log.debug("vo_po转换menu-start");
    		Iterator iterator = aRole.getMenus().iterator();
			while(iterator.hasNext()){
				SysConfRoleMenu aSysConfRoleMenu = new SysConfRoleMenu();
	    		SysConfRoleMenuId aSysConfRoleMenuId = new SysConfRoleMenuId();
				SysMenu aSysMenu = new SysMenu();
				Menu aMenu = (Menu)iterator.next();
				if(aMenu != null){
			    	aSysMenu.setNo(aMenu.getNo());
//			    	aSysMenu.setName(aMenu.getName());
//			    	aSysMenu.setLevel(aMenu.getLevel());
//			    	aSysMenu.setOrder(aMenu.getOrder());
//			    	aSysMenu.setUrl(aMenu.getURL());
//			    	SysMenu aSuperSysMenu = new SysMenu();
//			    	aSuperSysMenu.setNo(aMenu.getSuperMenu().getNo());
//			    	aSuperSysMenu.setName(aMenu.getSuperMenu().getName());
//			    	aSysMenu.setSysMenu(aSuperSysMenu);
				}
				aSysConfRoleMenuId.setSysRole(aSysRole);
				aSysConfRoleMenuId.setSysMenu(aSysMenu);
				aSysConfRoleMenu.setId(aSysConfRoleMenuId);
				aSysRole.getSysConfRoleMenus().add(aSysConfRoleMenu);
			}
//			log.debug("**********************************");
//			aSysConfRoleMenu=(SysConfRoleMenu)aSysRole.getSysConfRoleMenus().iterator().next();
//			log.debug("**********************************="+aSysConfRoleMenu);
//			log.debug("**********************************="+aSysConfRoleMenu.getId().getSysRole().getNo());
//			log.debug("**********************************="+aSysConfRoleMenu.getId().getSysRole().getName());
//			log.debug("**********************************="+aSysConfRoleMenu.getId().getSysMenu().getNo());
//			log.debug("**********************************");
			log.debug("vo_po转换menu-end");
			log.debug("vo_po转换button-start");
    	    iterator = aRole.getButtons().iterator();
			while(iterator.hasNext()){
				SysConfRoleButton aSysConfRoleButton = new SysConfRoleButton();
	    		SysConfRoleButtonId aSysConfRoleButtonId = new SysConfRoleButtonId();
				SysButton aSysButton = new SysButton();
				Button aButton = (Button)iterator.next();
				if(aButton != null){
			    	aSysButton.setNo(aButton.getNo());
//			    	aSysButton.setName(aButton.getName());
//			    	aSysButton.setOrder(aButton.getOrder());
//			    	aSysButton.setUrl(aButton.getURL());
//			    	SysMenu aSuperSysMenu = new SysMenu();
//			    	aSuperSysMenu.setNo(aButton.getMenu().getNo());
//			    	aSuperSysMenu.setName(aButton.getMenu().getName());
//			    	aSysButton.setSysMenu(aSuperSysMenu);
				}
				aSysConfRoleButtonId.setSysRole(aSysRole);
				aSysConfRoleButtonId.setSysButton(aSysButton);
				aSysConfRoleButton.setId(aSysConfRoleButtonId);
				aSysRole.getSysConfRoleButtons().add(aSysConfRoleButton);
			}
			log.debug("vo_po转换button-end");
		}catch(org.hibernate.LazyInitializationException e){}
		log.debug("vo_po转换end");
		return aSysRole;
	}
    
    private List<Role> po_vo_list(List<SysRole> poList){
    	List<Role> voList = new ArrayList<Role>();
        for(SysRole aSysRole:poList){
        	Role aRole = po_vo(aSysRole);
        	voList.add(aRole);
        }
        return voList;
    }
    
	/* (non-Javadoc)
	 * @see com.weili.wechat.common.DAO#delete(java.io.Serializable)
	 */
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
			//人员表 – SYS_USER
			   if (this.CheckLinkTable("OpTable", "sysRole.no", id.toString(),1)==1){
		    	    throw new DataIntegrityViolationException("src.userMessage5");
		       } else  {
			        List<OpTable> roleList= super.find("from OpTable o where o.sysRole.note like '%"+id.toString()+"%' and o.sysRole.no > 90000");
			        for(OpTable obj:roleList ) {
			          String[] sp = obj.getSysRole().getNote().split("\\|");
			          for(int i=0;i<sp.length;i++)  {
			        	  if(sp[i].equals(id.toString())) {
			    	             throw new DataIntegrityViolationException("src.userMessage5");
			        	  }
			           }
		            }
		       }
		    
//		//审批类别表 - APPROVAL_CATALOG_TABLE
//		if (this.CheckLinkTable("ApprovalCatalogTable", "approvalRoler", id.toString(),1)==1){
//	    	throw new DataIntegrityViolationException("该角色下已有审批类别，请先删除该角色下的审批类别！");
//	    }	
//		//审批线索信息表 - APPROVAL_CLUE_TABLE
//		if (this.CheckLinkTable("ApprovalClueTable", "auditRoler", id.toString(),1)==1){
//	    	throw new DataIntegrityViolationException("该角色下已有审批线索信息，请先删除该角色下的审批线索信息！");
//	    }	
			   
		log.debug("删除角色菜单权限表 – SYS_CONF_ROLE_MENU");
		super.batchDeleteOrUpdate("delete from SysConfRoleMenu o where o.id.sysRole.no=?", id);
		log.debug("删除角色按钮权限表 – SYS_CONF_ROLE_BUTTON");
		super.batchDeleteOrUpdate("delete from SysConfRoleButton o where o.id.sysRole.no=?", id);
		
		SysRole aSysRole =(SysRole)super.findById(getRefClass(), id);
		this.deleteObject(aSysRole);
	}

	/* (non-Javadoc)
	 * @see com.weili.wechat.common.DAO#qryAll()
	 */
	public List qryAll() {
		// TODO Auto-generated method stub
		List list = this.find("from SysRole o where o.catalog!=0 and o.catalog!=9 order by o.catalog,o.no ");
		return po_vo_list(list);
	}

	/* (non-Javadoc)
	 * @see com.weili.wechat.common.DAO#qryById(java.io.Serializable)
	 */
	public Object qryById(Serializable id) {
		// TODO Auto-generated method stub
		SysRole aSysRole=(SysRole)super.findById(getRefClass(), id);
		return po_vo(aSysRole);
	}

	public void save(Object obj) {
		// TODO Auto-generated method stub
		log.debug("save角色表start");
		SysRole aSysRole = vo_po((Role)obj);
		this.saveObject(aSysRole);
		log.debug("save角色菜单权限表start");
		Iterator iterator = aSysRole.getSysConfRoleMenus().iterator();
		while(iterator.hasNext()){
			SysConfRoleMenu aSysConfRoleMenu = (SysConfRoleMenu)iterator.next();
			this.saveObject(aSysConfRoleMenu);
		}
		log.debug("save角色菜单权限表end");
		log.debug("save角色按钮权限表start");
	    iterator = aSysRole.getSysConfRoleButtons().iterator();
		while(iterator.hasNext()){
			SysConfRoleButton aSysConfRoleButton = (SysConfRoleButton)iterator.next();
			this.saveObject(aSysConfRoleButton);
		}
		log.debug("save角色按钮权限表end");
		
		log.debug("save角色表end");
	}

	@SuppressWarnings("unchecked")
	public void update(Object obj) {
		// TODO Auto-generated method stub
		this.getSession().clear();
		log.debug("update角色表start");
		Role role = (Role)obj;
		SysRole tempSysRole = vo_po(role);
		SysRole aSysRole =(SysRole)super.findById(getRefClass(), tempSysRole.getNo());
		aSysRole.setNo(tempSysRole.getNo());
    	aSysRole.setName(tempSysRole.getName());
    	aSysRole.setCatalog(tempSysRole.getCatalog());
    	aSysRole.setNote(tempSysRole.getNote());
    	aSysRole.setSysConfRoleButtons(tempSysRole.getSysConfRoleButtons());
    	aSysRole.setSysConfRoleMenus(tempSysRole.getSysConfRoleMenus());
		this.updateObject(aSysRole);
		
		log.debug("update角色菜单权限表start");
		log.debug("删除角色菜单权限表 – SYS_CONF_ROLE_MENU");
		super.batchDeleteOrUpdate("delete from SysConfRoleMenu o where o.id.sysRole.no=?", tempSysRole.getNo());
		Iterator iterator = aSysRole.getSysConfRoleMenus().iterator();
		while(iterator.hasNext()){
			SysConfRoleMenu aSysConfRoleMenu = (SysConfRoleMenu)iterator.next();
			this.saveObject(aSysConfRoleMenu);
		}
		log.debug("update角色菜单权限表end");
		
		log.debug("update角色按钮权限表start");
		log.debug("删除角色按钮权限表 – SYS_CONF_ROLE_BUTTON");
		super.batchDeleteOrUpdate("delete from SysConfRoleButton o where o.id.sysRole.no=?", tempSysRole.getNo());
	
		iterator = aSysRole.getSysConfRoleButtons().iterator();
		while(iterator.hasNext()){
			SysConfRoleButton aSysConfRoleButton = (SysConfRoleButton)iterator.next();
			this.saveObject(aSysConfRoleButton);
		}
		log.debug("update角色按钮权限表end");
		log.debug("update角色表end");
		
//		if(!role.getBeforeRoelName().equals(role.getName())) {
//			this.batchUpdateRoleName(role.getName(),role.getBeforeRoelName());
//		}

	}
	
	/**
	 * 判断表中是否有关联数据
	 * @param string  表名,字段，值，类型
	 * @return 0:无关联，1：有关联
	 */
	public int CheckLinkTable(String table,String field,String value,int type)
	{
		String HQL;
		if(type==0){//String
			HQL="select t."+field+" from "+ table +" t where t."+field+"='"+value+"'";
		}else{//Int
			HQL="select t."+field+" from "+ table +" t where t."+field+"="+value;
		}
		log.debug("HQL=["+HQL+"]");
		List list = this.find(HQL);
		log.debug("HQL=["+HQL+"]");
		if(list != null && list.size() > 0){
			log.debug("HQL=["+HQL+"]");
			return 1;
		}else{
			return 0;
		}
	}

	public List qryRoleByName(String name) {
		// TODO Auto-generated method stub
//		List list = super.find("from SysRole o where o.name like ? order by o.no ","%" + name + "%");
		//重名的不允许加入
		List list = super.find("from SysRole o where o.name = ? order by o.no ",name);
		return po_vo_list(list);
	}
	
	public List qryRoleByName(String name, Integer orgGradeNo) {
		// TODO Auto-generated method stub
//		List list = super.find("from SysRole o where o.name like ? order by o.no ","%" + name + "%");
		//重名的不允许加入
		List list = super.find("from SysRole o where o.name = ? and o.orgType = ? order by o.no ",
				new Object[] {name, orgGradeNo});
		return po_vo_list(list);
	}

	public int getRoleNo() {
		// TODO Auto-generated method stub
		List alist = super.find("select max(o.no)+1 from SysRole o where o.catalog!=9");
		return alist.get(0)==null?new Integer("0"):(Integer)alist.get(0);
	}

	public List getRoleNoByName(String name) {
		List list = super.find("select o.no from SysRole o where o.name = ? order by o.no ", name);
		return list;
	}
	
	public List qryRoleByOryGrade(Integer orgGradeNo) {
		// TODO Auto-generated method stub
		List list = this.find("from SysRole o where o.catalog!=0 and o.catalog!=9 and o.orgType > ? order by o.orgType,o.no ", orgGradeNo - 1);
		return po_vo_list(list);
	}
	
	public List qryRoleByOryGrade1(Integer orgGradeNo) {
		// TODO Auto-generated method stub
		List list = this.find("from SysRole o where o.catalog!=0 and o.catalog!=9 and o.orgType = ? order by o.orgType,o.no ", orgGradeNo);
		return po_vo_list(list);
	}
	
	public List qryRoleListByOryGrade(Integer orgGradeNo) {
		List list = this.find("select o.no, o.name from SysRole o where o.catalog!=0 and o.catalog!=9 and o.orgType > ? order by o.orgType,o.no ", orgGradeNo - 1);
		return list;
	}
	
	/**
	 * 是否有权力查看&&修改其他人的：验收任务单/任务单/日结单信息
	 * @param roleNo
	 * @return
	 */
	public boolean isAlmighty(Serializable roleId){
		boolean retVal = false;
		
		SysRole sysRole = (SysRole)this.findById(getRefClass(), roleId);
		int roleCatalog = sysRole.getCatalog();
		if (roleCatalog != 9) {    //非组合角色
			retVal = roleCatalog == 1 || roleCatalog == 2;    //1是系统管理员，2是一般管理员
		} else {    //组合角色，只要有其中一个角色满足条件
			String[] sp = sysRole.getNote().split("\\|");
			for(int i=0;i<sp.length;i++)  {
				String tmpRoleNo = sp[i];
				SysRole tmpSysRole = (SysRole)this.findById(getRefClass(), Integer.valueOf(tmpRoleNo));
				if (tmpSysRole != null) {
					int tmpRoleCatalog = tmpSysRole.getCatalog();
					if (tmpRoleCatalog == 1 || tmpRoleCatalog == 2) {
						retVal = true;
						break;
					}
				}
			}
		}
		
		return retVal;
	}
}
