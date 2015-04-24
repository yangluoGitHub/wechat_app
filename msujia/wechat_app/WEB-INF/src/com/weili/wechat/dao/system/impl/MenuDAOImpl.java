/**
 * 
 */
package com.weili.wechat.dao.system.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;

import com.weili.wechat.common.HibernateDAO;
import com.weili.wechat.dao.system.MenuDAO;
import com.weili.wechat.hibernate.SysMenu;
import com.weili.wechat.vo.Menu;

/**
 * @author ykliu
 *
 */
public class MenuDAOImpl extends HibernateDAO implements MenuDAO {

	private Log log = LogFactory.getLog(MenuDAOImpl.class);
	
    private Class getRefClass() {
        return com.weili.wechat.hibernate.SysMenu.class;
    }
    
    private Menu po_vo(SysMenu aSysMenu){
    	if(aSysMenu == null) return null;
    	Menu aMenu = new Menu();
    	aMenu.setNo(aSysMenu.getNo());
    	aMenu.setName(aSysMenu.getName());
    	aMenu.setLevel(aSysMenu.getMenuLevel());
    	aMenu.setOrder(aSysMenu.getMenuOrder());
    	aMenu.setURL(aSysMenu.getUrl());
    	
    	if(aSysMenu.getSysMenu()!=null){
        	Menu aSuperMenu = new Menu();
        	aSuperMenu.setNo(aSysMenu.getSysMenu().getNo());
        	aSuperMenu.setName(aSysMenu.getSysMenu().getName());
        	aMenu.setSuperMenu(aSuperMenu);
    	}
    	
//    	try{
//    		Iterator iterator = aSysMenu.getSysMenus().iterator();
//    		while (iterator.hasNext()) {
//    			aSysMenu = ((SysMenu)iterator.next());
//    			//log.debug(getSpace(aSysMenu.getLevel())+aSysMenu.getName());
//    			aMenu.getSubMenu().add(po_vo(aSysMenu)); 
//    		}
//    	}catch(org.hibernate.LazyInitializationException e){}
		
    	return aMenu;
    }
    
    private SysMenu vo_po(Menu aMenu){
    	if(aMenu == null) return null;
    	SysMenu aSysMenu = new SysMenu();
    	aSysMenu.setNo(aMenu.getNo());
    	aSysMenu.setName(aMenu.getName());
    	aSysMenu.setMenuLevel(aMenu.getLevel());
    	aSysMenu.setNote(aMenu.getNote());
    	aSysMenu.setMenuOrder(aMenu.getOrder());
    	aSysMenu.setUrl(aMenu.getURL());
    	SysMenu aSysSuperMenu = new SysMenu();
    	aSysSuperMenu.setNo(aMenu.getSuperMenu().getNo());
    	aSysSuperMenu.setName(aMenu.getSuperMenu().getName());
    	aSysMenu.setSysMenu(aSysSuperMenu);
    	
		return aSysMenu;
	}
    
    private List<Menu> po_vo_list(List<SysMenu> poList){
    	List<Menu> voList = new ArrayList<Menu>();
        for(SysMenu aSysMenu:poList){
        	Menu aMenu = po_vo(aSysMenu);
        	voList.add(aMenu);
        }
        return voList;
    }
    
	/* (non-Javadoc)
	 * @see com.weili.wechat.common.DAO#delete(java.io.Serializable)
	 */
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		//按钮表 – SYS_BUTTON
		if (this.CheckLinkTable("SysButton", "sysMenu.no", id.toString(),1)==1){
	    	throw new DataIntegrityViolationException("src.menuMessage1");
	    }
		//角色菜单权限表 – SYS_CONF_ROLE_MENU
		if (this.CheckLinkTable("SysConfRoleMenu", "id.sysMenu.no", id.toString(),1)==1){
	    	throw new DataIntegrityViolationException("src.menuMessage2");
	    }
		
		SysMenu aSysMenu =(SysMenu)super.findById(getRefClass(), id);
		this.deleteObject(aSysMenu);
	}

	/* (non-Javadoc)
	 * @see com.weili.wechat.common.DAO#qryAll()
	 */
	public List qryAll() {
		// TODO Auto-generated method stub
		List list = this.find("from SysMenu s inner join fetch  s.sysMenu f order by f.menuOrder , s.menuOrder , s.no");
		return po_vo_list(list);
	}

	/* (non-Javadoc)
	 * @see com.weili.wechat.common.DAO#qryById(java.io.Serializable)
	 */
	public Object qryById(Serializable id) {
		// TODO Auto-generated method stub
		SysMenu aSysMenu=(SysMenu)super.findById(getRefClass(), id);
		return po_vo(aSysMenu);
	}

	public void save(Object obj) {
		// TODO Auto-generated method stub
		this.saveObject(vo_po((Menu)obj));
	}

	public void update(Object obj) {
		// TODO Auto-generated method stub
		SysMenu tmpSysMenu = vo_po((Menu)obj);
		SysMenu aSysMenu =(SysMenu)super.findById(getRefClass(), tmpSysMenu.getNo());
		aSysMenu.setName(tmpSysMenu.getName());
		aSysMenu.setMenuLevel(tmpSysMenu.getMenuLevel());
    	aSysMenu.setNote(tmpSysMenu.getNote());
    	aSysMenu.setMenuOrder(tmpSysMenu.getMenuOrder());
    	aSysMenu.setUrl(tmpSysMenu.getUrl());
    	SysMenu aSysSuperMenu = new SysMenu();
    	aSysSuperMenu.setNo(tmpSysMenu.getSysMenu().getNo());
    	aSysSuperMenu.setName(tmpSysMenu.getSysMenu().getName());
    	aSysMenu.setSysMenu(aSysSuperMenu);
		this.updateObject(aSysMenu);
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

	public List qryMenuByRole(Integer roleId) {
		// TODO Auto-generated method stub
		List list = null;
		if(roleId.intValue() == 10001){
			list = this.find("from SysMenu s inner join fetch  s.sysMenu f order by s.menuOrder");
		}else{
			list = this.find("from SysMenu o join fetch  o.sysConfRoleMenus rm  where rm.id.sysRole.no =? order by o.menuOrder",roleId);
		}
		return po_vo_list(list);
	}

	public Menu qryMenuTree(String menuId) {
		// TODO Auto-generated method stub
		SysMenu aSysMenu = (SysMenu)this.find("from SysMenu m left join fetch m.sysMenus order by m.menuOrder").get(0);
		log.debug(getSpace(aSysMenu.getMenuLevel())+aSysMenu.getName());
		Menu aMenu = po_vo(aSysMenu);
		log.debug("--------------");
		traverseTree(aMenu);
		return null;
	}
	
	public void traverseTree(Menu aMenu) { 
		Iterator iterator = aMenu.getSubMenu().iterator();
		while (iterator.hasNext()) {
			aMenu = ((Menu)iterator.next());
			log.debug(getSpace(aMenu.getLevel())+aMenu.getName());
			traverseTree(aMenu); 
		}
	} 
	
	private String getSpace(int menuLevel){
		String rtn;
		switch(menuLevel){
	 	case 1:
	 		rtn = "  ";
	 		break;
	 	case 2:
	 		rtn = "　　  ";
	 		break;
	 	case 3:
	 		rtn = "　　　　  ";
	 		break;
	 	case 4:
	 		rtn = "　　　　　　  ";
	 		break;
	 	default :
	 		rtn = "";
	 	}
		return rtn;
	}

}
