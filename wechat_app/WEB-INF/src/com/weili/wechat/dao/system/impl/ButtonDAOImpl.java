/**
 * 
 */
package com.weili.wechat.dao.system.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;

import com.weili.wechat.common.HibernateDAO;
import com.weili.wechat.dao.system.ButtonDAO;
import com.weili.wechat.hibernate.SysButton;
import com.weili.wechat.hibernate.SysMenu;
import com.weili.wechat.vo.Button;
import com.weili.wechat.vo.Menu;

/**
 * @author ykliu
 *
 */
public class ButtonDAOImpl extends HibernateDAO implements ButtonDAO {

	private Log log = LogFactory.getLog(ButtonDAOImpl.class);
	
    private Class getRefClass() {
        return com.weili.wechat.hibernate.SysButton.class;
    }
    
    private Button po_vo(SysButton aSysButton){
    	if(aSysButton == null) return null;
    	Button aButton = new Button();
    	aButton.setNo(aSysButton.getNo());
    	aButton.setName(aSysButton.getName());
    	aButton.setOrder(aSysButton.getButtonOrder());
    	aButton.setURL(aSysButton.getUrl());
    	aButton.setNote(aSysButton.getNote());
    	if(aSysButton.getSysMenu() != null){
        	Menu aMenu = new Menu();
        	aMenu.setNo(aSysButton.getSysMenu().getNo());
        	aMenu.setName(aSysButton.getSysMenu().getName());
        	aButton.setMenu(aMenu);	
    	}
    	return aButton;
    }
    
    private SysButton vo_po(Button aButton){
    	if(aButton == null) return null;
    	SysButton aSysButton = new SysButton();
    	aSysButton.setNo(aButton.getNo());
    	aSysButton.setName(aButton.getName());
    	aSysButton.setButtonOrder(aButton.getOrder());
    	aSysButton.setUrl(aButton.getURL());
    	aSysButton.setNote(aButton.getNote());
    	SysMenu aSysMenu = new SysMenu();
    	aSysMenu.setNo(aButton.getMenu().getNo());
    	aSysMenu.setName(aButton.getMenu().getName());
    	aSysButton.setSysMenu(aSysMenu);
		return aSysButton;
	}
    
    private List<Button> po_vo_list(List<SysButton> poList){
    	List<Button> voList = new ArrayList<Button>();
        for(SysButton aSysButton:poList){
        	Button aButton = po_vo(aSysButton);
        	voList.add(aButton);
        }
        return voList;
    }
    
	/* (non-Javadoc)
	 * @see com.weili.wechat.common.DAO#delete(java.io.Serializable)
	 */
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		//角色按钮权限表 - SYS_CONF_ROLE_BUTTON
		if (this.CheckLinkTable("SysConfRoleButton", "id.sysButton.no", id.toString(),1)==1){
	    	throw new DataIntegrityViolationException("src.roleMessage1");
	    }
		
		SysButton aSysButton =(SysButton)super.findById(getRefClass(), id);
		this.deleteObject(aSysButton);
	}

	/* (non-Javadoc)
	 * @see com.weili.wechat.common.DAO#qryAll()
	 */
	public List qryAll() {
		// TODO Auto-generated method stub
		List list = this.find("from SysButton o order by o.sysMenu.id,o.buttonOrder ");
		return po_vo_list(list);
	}

	/* (non-Javadoc)
	 * @see com.weili.wechat.common.DAO#qryById(java.io.Serializable)
	 */
	public Object qryById(Serializable id) {
		// TODO Auto-generated method stub
		SysButton aSysButton=(SysButton)super.findById(getRefClass(), id);
		return po_vo(aSysButton);
	}

	public void save(Object obj) {
		// TODO Auto-generated method stub
		this.saveObject(vo_po((Button)obj));
	}

	public void update(Object obj) {
		// TODO Auto-generated method stub
		SysButton tmpSysButton = vo_po((Button)obj);
		SysButton aSysButton =(SysButton)super.findById(getRefClass(), tmpSysButton.getNo());
    	aSysButton.setName(tmpSysButton.getName());
    	aSysButton.setButtonOrder(tmpSysButton.getButtonOrder());
    	aSysButton.setUrl(tmpSysButton.getUrl());
    	aSysButton.setNote(tmpSysButton.getNote());
    	SysMenu aSysMenu = new SysMenu();
    	aSysMenu.setNo(tmpSysButton.getSysMenu().getNo());
    	aSysMenu.setName(tmpSysButton.getSysMenu().getName());
    	aSysButton.setSysMenu(aSysMenu);
		this.updateObject(aSysButton);
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

	public List qryButtonByMenu(String menuId) {
		// TODO Auto-generated method stub
		List list = this.find("from SysButton o where o.sysMenu.no=? order by o.no",menuId);
		return po_vo_list(list);
	}

	public List qryButtonByRole(Integer roleId) {
		// TODO Auto-generated method stub
		List list = this.find("from SysButton o join fetch o.sysConfRoleButtons rm where rm.id.sysRole.no=? order by o.no",roleId);
		return po_vo_list(list);
	}

}
