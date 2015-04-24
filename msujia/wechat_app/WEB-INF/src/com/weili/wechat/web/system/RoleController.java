

package com.weili.wechat.web.system;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.weili.wechat.common.GetResource;
import com.weili.wechat.common.OperateAspect;
import com.weili.wechat.common.Resource;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.common.UserSession;
import com.weili.wechat.service.system.RoleService;
import com.weili.wechat.vo.Button;
import com.weili.wechat.vo.Menu;
import com.weili.wechat.vo.Role;


/**
 * 处理与角色相关操作的请求
 * @author hsxu
 *
 */

public class RoleController extends MultiActionController {
	
	private RoleService roleService;


	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	private static Log log = LogFactory.getLog(RoleController.class);
    
	
	/**
	 * 查询角色
	 */
	
	public ModelAndView qry(HttpServletRequest request,HttpServletResponse response){
		UserSession userSession=(UserSession)request.getSession().getAttribute("userSession");
		try{				
			ModelAndView mv = new ModelAndView("system/role_qry");	
			List roleList = null;
			roleList = this.getRoleService().qryRole();
			mv.addObject("roleList", roleList);
			
	        return mv;
		} 
		catch (Exception e) {
			e.printStackTrace();
			log.error("查询角色异常：" + e.getMessage());
			return new ModelAndView("info","message",e);
		}
	}
	
	/**
	 * 进入添加页面
	 */
	
	public ModelAndView addPage(HttpServletRequest request,HttpServletResponse response){
		Resource resource = (Resource)GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());
		UserSession userSession=(UserSession)request.getSession().getAttribute("userSession");
		try{	
			
			List menuList = this.getRoleService().qryMenu();
			List buttonList = this.getRoleService().qryButton();
			
			log.debug("menuList.size()=================[ "+menuList.size()+" ] buttonList.size()=================[ "+buttonList.size()+" ]");
			
//			List sysOrgGradeList = this.getRoleService().qryGradeNoByAthor(userSession.getOrgGrade());
			
			ModelAndView mv = new ModelAndView("system/role_add")
			.addObject("menuList", menuList)
			.addObject("buttonList", buttonList);
//			.addObject("sysOrgGradeList", sysOrgGradeList);
			
			return mv;
		} 
		catch (Exception e) {
			e.printStackTrace();
			log.error("进入添加页面异常：" + e.getMessage());
			return new ModelAndView("info","message",e.getMessage());
		}
	}
	
	/**
	 * 添加角色
	 */
	
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response){
		Resource resource = (Resource)GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());
		try {			
			
			String roleName = StringUtil.parseString(request.getParameter("roleName"));
			String note = StringUtil.parseString(request.getParameter("note"));
			Integer roleCatalog = StringUtil.parseInteger2(request.getParameter("roleCatalog"));
//			String orgType = StringUtil.parseString(request.getParameter("orgType"));
			//获取菜单功能权限
			
			String menu[]= request.getParameterValues("menu");
			Set<Menu> menus = new HashSet<Menu>(0);
			if(menu!=null){
				for(int i=0;i<menu.length;i++){
					Menu m = new Menu();
					log.debug("菜单编号=["+menu[i]+"]");
					m.setNo(menu[i]);
					m.setName(request.getParameter("id"+menu[i]));
					menus.add(m);
				}
			}
			
			
			//获取按钮功能权限
			
			String button[]= request.getParameterValues("button");
			Set<Button> buttons = new HashSet<Button>(0);
			if(button!=null){
				for(int i=0;i<button.length;i++){
					Button b = new Button();
					log.debug("按钮编号=["+button[i]+"]");
					b.setNo(Integer.valueOf(button[i]));
					b.setName(request.getParameter("id"+button[i]));
					buttons.add(b);
				}
			}
			
			//添加角色
			Role role = new Role();
		    role.setName(roleName);
		    role.setCatalog(roleCatalog);
		    role.setMenus(menus);
		    role.setButtons(buttons);
//		    role.setOrgType(Integer.parseInt(orgType));
		    role.setNote(note);

			int retVal = -1;
		    if((retVal = this.getRoleService().addRole(role))!=0){
				if(retVal == OperateAspect.AUDIT_FLAG)
					return new ModelAndView("info","message",resource.srcStr(resource.srcStr("Login.wait_preview")));
				else
					return new ModelAndView("info","message",resource.srcStr(this.getRoleService().getRetMsg()));
			}
		    return new ModelAndView("pageinfo_pagedecrease", "message", "添加角色成功！")
			.addObject("url", "role.do?action=qry");
		} 
		catch (Exception e) {
			e.printStackTrace();
			log.error("添加异常：" + e.getMessage());
			return new ModelAndView("info","message",e.getMessage());
		}
	}
	
	/**
	 * 进入修改页面
	 */
	
	public ModelAndView modPage(HttpServletRequest request,HttpServletResponse response){
		Resource resource = (Resource)GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());		
		UserSession userSession=(UserSession)request.getSession().getAttribute("userSession");
		try{
			String roleNo = request.getParameter("roleNo");
			
			Role role = (Role)this.getRoleService().qryRoleById(Integer.valueOf(roleNo));
			if(role == null) return  new ModelAndView("info","message", "该角色已经不存在");
			List menuList = this.getRoleService().qryMenu();
			List buttonList = this.getRoleService().qryButton();
			List confmenuList = this.getRoleService().qryMenuByRole(Integer.valueOf(roleNo));
			List confbuttonList = this.getRoleService().qryButtonByRole(Integer.valueOf(roleNo));
			log.debug("menuList.size()========================="+menuList.size());
			log.debug("buttonList.size()========================="+buttonList.size());
			log.debug("confmenuList.size()========================="+confmenuList.size());
			log.debug("confbuttonList.size()========================="+confbuttonList.size());
			
			
			
			//		用列表存储被选择的菜单			
			StringBuffer confMenus = new StringBuffer();
			String f;
			for(Object o:confmenuList){
				Menu m = (Menu)o;
				f = StringUtil.parseString(m.getNo().toString());
				if(f.length() > 0){
					confMenus.append(f).append(",");
				}
			}			
			String menuchecked = confMenus.toString();
			List<String> menucheckedList = new ArrayList<String>(0);
			for(Object o:menuList){
				Menu m = (Menu)o;
				String t = menuchecked.indexOf(m.getNo()+",")>=0 ? "checked" : "";
				log.debug("t============================="+t);
				menucheckedList.add(t);
			}
			log.debug("menucheckedList============================="+menucheckedList);
			log.debug("confMenus============================="+confMenus);
			
			//		用列表存储被选择的按钮
			StringBuffer confButtons = new StringBuffer();
			confButtons.append(",");//唯一性 ykliu add
			String s;
			for(Object o:confbuttonList){
				Button b = (Button)o;
				s = StringUtil.parseString(b.getNo().toString());
				if(s.length() > 0){
					confButtons.append(s).append(",");
				}
			}			
			String buttonchecked = confButtons.toString();
			log.debug("buttonchecked="+buttonchecked);
			List<String> buttoncheckedList = new ArrayList<String>(0);
			for(Object o:buttonList){
				Button b = (Button)o;
				log.debug("b.getNo()="+b.getNo());
//				String c = buttonchecked.indexOf(b.getNo()+",")>=0 ? "checked" : "";
				String c = buttonchecked.indexOf(","+b.getNo()+",")>=0 ? "checked" : "";
				log.debug("c============================="+c);
				buttoncheckedList.add(c);
			}
			log.debug("buttoncheckedList============================="+buttoncheckedList);
			log.debug("confButtons============================="+confButtons);
			
//			List sysOrgGradeList = this.getRoleService().qryGradeNoByAthor(userSession.getOrgGrade());
			
			ModelAndView mv = new ModelAndView("system/role_mod")
			.addObject("role",role)
			.addObject("menucheckedList", menucheckedList)
			.addObject("buttoncheckedList", buttoncheckedList)
			.addObject("menuList", menuList)
			.addObject("buttonList", buttonList);
//			.addObject("sysOrgGradeList", sysOrgGradeList);
			
			return mv;
		} 
		catch (Exception e) {
			e.printStackTrace();
			log.error("进入修改页面异常：" + e.getMessage());
			return new ModelAndView("info","message",e.getMessage());
		}
	}
	
	/**
	 * 修改角色
	 */
	
	public ModelAndView mod(HttpServletRequest request,HttpServletResponse response){
		Resource resource = (Resource)GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());		
		try {			
			Integer roleNo = Integer.valueOf(request.getParameter("roleNo"));
			String roleName = StringUtil.parseString(request.getParameter("roleName"));
			String beforeRoleName = StringUtil.parseString(request.getParameter("beforeRoleName"));
			String note = StringUtil.parseString(request.getParameter("note"));
			Integer roleCatalog = StringUtil.parseInteger2(request.getParameter("roleCatalog"));
//			String orgType = StringUtil.parseString(request.getParameter("orgType"));
			//获取菜单功能权限
			
			String menu[]= request.getParameterValues("menu");
//			log.debug("menu.length======================================"+menu.length);
			Set<Menu> menus = new HashSet<Menu>(0);
			
			if(menu!=null){
				for(int i=0;i<menu.length;i++){
					Menu m = new Menu();
					m.setNo(menu[i]);
					m.setName(request.getParameter("id"+menu[i]));
					menus.add(m);
				}
				//log.debug("menus.size()=================="+menus.size());
			}
			
			//获取按钮功能权限
			
			String button[]= request.getParameterValues("button");
//			log.debug("button.length======================================"+button.length);
			Set<Button> buttons = new HashSet<Button>(0);
			if(button!=null){
				for(int i=0;i<button.length;i++){
					Button b = new Button();
					b.setNo(Integer.valueOf(button[i]));
					b.setName(request.getParameter("id"+button[i]));
					buttons.add(b);
				}
				//log.debug("buttons.size()=================="+buttons.size());
			}
			
			//修改角色
			Role role = this.getRoleService().qryRoleById(roleNo);
			if(role == null) {
				return new ModelAndView("info","message", "该角色已经不存在!");
			}
		    role.setName(roleName);
		    role.setCatalog(roleCatalog);
		    role.setMenus(menus);		
		    role.setButtons(buttons);
//		    role.setOrgType(Integer.valueOf(orgType));
		    role.setBeforeRoelName(beforeRoleName);
		    role.setNote(note);
		    int retVal = -1;
		    if((retVal = this.getRoleService().modRole(role))!=0){
				if(retVal == OperateAspect.AUDIT_FLAG)
					return new ModelAndView("info","message",resource.srcStr(resource.srcStr("Login.wait_preview")));
				else
					return new ModelAndView("info","message",resource.srcStr(this.getRoleService().getRetMsg()));
		    }
		    
		    return new ModelAndView("pageinfo_pagedecrease", "message", "修改角色成功！")
			.addObject("url", "role.do?action=qry");
			
		} 
		catch (Exception e) {
			log.error("修改异常：" + e.getMessage());
			return new ModelAndView("info","message",e.getMessage());
		}
	}
	
	
	/**
	 * 删除角色
	 */
	
	public ModelAndView del(HttpServletRequest request,HttpServletResponse response) {
		Resource resource = (Resource)GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());		
		try {	
			String roleNo = StringUtil.parseString(request.getParameter("roleNo"));
			Role aRole = (Role)this.getRoleService().qryRoleById(Integer.valueOf(roleNo));
			if(aRole == null) return  new ModelAndView("info","message", "该角色已经不存在");
			List confButtonList = this.getRoleService().qryButtonByRole(Integer.valueOf(roleNo));
			List confMenuList = this.getRoleService().qryMenuByRole(Integer.valueOf(roleNo));
			
			//角色菜单
			Set<Menu> menus = new HashSet<Menu>(0);
			for(Object obj: confMenuList) {
				Menu menu = (Menu) obj;
				menus.add(menu);
			}
			//角色功能按钮
			Set<Button> buttons = new HashSet<Button>(0);
			for(Object obj: confButtonList) {
				Button button = (Button) obj;
				buttons.add(button);
			}
			aRole.setButtons(buttons);
			aRole.setMenus(menus);
			
			int retVal = -1;
			if((retVal = this.getRoleService().delRole(aRole)) !=0){
				if(retVal == OperateAspect.AUDIT_FLAG)
					return new ModelAndView("info","message",resource.srcStr(resource.srcStr("Login.wait_preview")));
				else
					return new ModelAndView("info","message",resource.srcStr(this.getRoleService().getRetMsg()));
			}
			 return new ModelAndView("pageinfo_pagedecrease", "message", "删除角色成功！")
				.addObject("url", "role.do?action=qry");
		} 
		catch (Exception e) {
			log.error("删除异常：" + resource.srcStr(e.getMessage()));
			return new ModelAndView("info","message",resource.srcStr(e.getMessage()));
		}
	}
	
	/**
	 * 进入显示角色详细页面
	 */
	
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response) {
		Resource resource = (Resource)GetResource.getOneResource(request.getSession().getServletContext(), request.getSession().getAttribute("locale").toString());				
		try{
			String roleNo = request.getParameter("roleNo");
			
			Role role = (Role)this.getRoleService().qryRoleById(Integer.valueOf(roleNo));
			List menuList = this.getRoleService().qryMenu();
			List buttonList = this.getRoleService().qryButton();
			List confmenuList = this.getRoleService().qryMenuByRole(Integer.valueOf(roleNo));
			List confbuttonList = this.getRoleService().qryButtonByRole(Integer.valueOf(roleNo));

//			用列表存储被选择的菜单			
			StringBuffer confMenus = new StringBuffer();
			String f;
			for(Object o:confmenuList){
				Menu m = (Menu)o;
				f = StringUtil.parseString(m.getNo().toString());
				if(f.length() > 0){
					confMenus.append(f).append(",");
				}
			}			
			String menuchecked = confMenus.toString();
			List<String> menucheckedList = new ArrayList<String>(0);
			for(Object o:menuList){
				Menu m = (Menu)o;
				String t = menuchecked.indexOf(m.getNo()+",")>=0 ? "checked" : "";
				//log.debug("t============================="+t);
				menucheckedList.add(t);
			}
			log.debug("menucheckedList============================="+menucheckedList);
			log.debug("confMenus============================="+confMenus);
			
			//		用列表存储被选择的按钮
			StringBuffer confButtons = new StringBuffer();
			confButtons.append(",");//唯一性 ykliu add
			String s;
			for(Object o:confbuttonList){
				Button b = (Button)o;
				s = StringUtil.parseString(b.getNo().toString());
				if(s.length() > 0){
					confButtons.append(s).append(",");
				}
			}			
			String buttonchecked = confButtons.toString();
			List<String> buttoncheckedList = new ArrayList<String>();
			for(Object o:buttonList){
				Button b = (Button)o;
//				String c = buttonchecked.indexOf(b.getNo()+",")>=0 ? "checked" : "";
				String c = buttonchecked.indexOf(","+b.getNo()+",")>=0 ? "checked" : "";
				//log.debug("c============================="+c);
				buttoncheckedList.add(c);
			}
			log.debug("buttoncheckedList============================="+buttoncheckedList);
			log.debug("confButtons============================="+confButtons);
			
			ModelAndView mv = new ModelAndView("system/role_detail")
			.addObject("role",role)
			.addObject("menucheckedList", menucheckedList)
			.addObject("buttoncheckedList", buttoncheckedList)
			.addObject("menuList", menuList)
			.addObject("buttonList", buttonList);
			return mv;	
		} 
		catch (Exception e) {
			e.printStackTrace();
			log.error("详细页面异常：" + e.getMessage());
			return new ModelAndView("info","message",e.getMessage());
		}
	}
	
}