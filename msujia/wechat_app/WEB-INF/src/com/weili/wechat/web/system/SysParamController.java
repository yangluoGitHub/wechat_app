package com.weili.wechat.web.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.weili.wechat.common.GetResource;
import com.weili.wechat.common.Resource;
import com.weili.wechat.common.StringUtil;
import com.weili.wechat.hibernate.SysParam;
import com.weili.wechat.service.system.SysParamService;
import com.weili.wechat.vo.SysParamVO;

/**
 * 处理与系统参数操作相关的请求
 * 
 * @author hsxu
 * 
 */
public class SysParamController extends MultiActionController {
	private static Log log = LogFactory.getLog(SysParamController.class);

	private SysParamService sysParamService;

	public SysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(SysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	/**
	 * 获取参数（暂时未用到）
	 */
	@SuppressWarnings("rawtypes")
	public ModelAndView getParam(HttpServletRequest request, HttpServletResponse response) {

		Resource resource = (Resource) GetResource.getOneResource(request.getSession().getServletContext(),
				request.getSession().getAttribute("locale").toString());
		try {
			String catalogStr = StringUtil.parseString(request.getParameter("catalog"));
			if (catalogStr.length() == 0) {
				return new ModelAndView("info").addObject("message", resource.srcStr("src.parameterValueEmpty"));
			}

			int catalog = Integer.parseInt(catalogStr);

			ModelAndView mv = null;

			switch (catalog) {
			case 1:
				mv = new ModelAndView("system/param_sys_run");
				break;
			case 2:
				mv = new ModelAndView("system/param_scheduling");
				break;
			case 3:
				mv = new ModelAndView("system/param_status_color");
				break;
			default:
				mv = new ModelAndView("info");
				break;
			}

			HashMap<String, String> map = this.getSysParamService().qrySysParamNVMapByCatalog(catalog);

			mv.addAllObjects(map);
			return mv;
		} catch (Exception e) {
			log.error("获取参数异常：" + e.getMessage());
			return new ModelAndView("info").addObject("message", e.getMessage());
		}
	}

	/**
	 * 设置参数（暂时未用到）
	 */
	@SuppressWarnings("rawtypes")
	public ModelAndView setParam(HttpServletRequest request, HttpServletResponse response) {

		Resource resource = (Resource) GetResource.getOneResource(request.getSession().getServletContext(),
				request.getSession().getAttribute("locale").toString());
		ModelAndView mv = new ModelAndView("system/param_set_result");

		try {
			String catalogStr = StringUtil.parseString(request.getParameter("catalog"));
			String paramName = StringUtil.parseString(request.getParameter("paramName"));
			String paramValue = StringUtil.parseString(request.getParameter("paramValue"));

			if (catalogStr.length() == 0) {
				return mv.addObject("result", resource.srcStr("src.parameterValueEmpty"));
			}

			int catalog = Integer.parseInt(catalogStr);

			SysParam sysParam = (SysParam) this.getSysParamService().qrySysParamValueByCatalogAndName(catalog, paramName);
			if (sysParam != null) {
				SysParamVO sysParamVO = new SysParamVO();
				sysParamVO.setLogicId(sysParam.getLogicId());
				sysParamVO.setParamValue(paramValue);
				sysParamVO.setParamName(sysParam.getParamName());
				sysParamVO.setCatalog(sysParam.getSysParamCatalog().getCatalog());
				sysParamVO.setStatement(sysParam.getStatement());
				sysParamVO.setDescription(sysParam.getDescription());
				
				this.getSysParamService().modSysParam(sysParamVO);
			} else {
				SysParamVO sysParamVO = new SysParamVO();
				sysParamVO.setLogicId(UUID.randomUUID().toString());
				sysParamVO.setCatalog(catalog);
				sysParamVO.setParamName(paramName);
				sysParamVO.setParamValue(paramValue);
//				this.getSysParamService().addSysParam(sysParamVO);
			}

			return mv.addObject("result", resource.srcStr("src.SetSuc"));
		} catch (Exception e) {
			log.error("设置异常：" + e.getMessage());
			return mv.addObject("result", resource.srcStr("src.Failed") + "：" + e.getMessage());
		}
	}

	/**
	 * 系统参数查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView qrySysparam(HttpServletRequest request, HttpServletResponse response) {
		try {
			Integer catalog = StringUtil.parseInteger2(request.getParameter("catalog"));
			String paramName = StringUtil.parseString(request.getParameter("paramName"));
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("catalog", catalog);
			params.put("paramName", paramName);
			List resultList = this.sysParamService.qrySysParam(params);
			
			List sysParamCatalogList = sysParamService.qrySysParamCatalog();

			return new ModelAndView("system/param_sys_qry").addObject("catalog", catalog)
					.addObject("paramName", paramName).addObject("resultList", resultList)
					.addObject("sysParamCatalogList", sysParamCatalogList);
		} catch (Exception e) {
			log.error("查询系统参数异常：" + e.getMessage(), e);
			return new ModelAndView("info", "message", "查询系统参数异常："+e.getMessage());
		}
	}

	/**
	 * 转向添加系统参数页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView intoAddSysPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			List sysParamCatalogList = sysParamService.qrySysParamCatalog();
			
			return new ModelAndView("system/param_sys_add").addObject("sysParamCatalogList", sysParamCatalogList);
		} catch (Exception e) {
			log.error("转向添加页面异常：" + e.getMessage(), e);
			return new ModelAndView("info", "message", "转向添加系统参数页面异常："+e.getMessage());
		}
	}

	/**
	 * 添加系统参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
//	public ModelAndView addSysparam(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			Integer catalog = StringUtil.parseInteger2(request.getParameter("catalog"));
//			String paramName = StringUtil.parseString(request.getParameter("paramName"));
//			String paramValue = StringUtil.parseString(request.getParameter("paramValue"));
//			String statement = StringUtil.parseString(request.getParameter("statement"));
//			String description = StringUtil.parseString(request.getParameter("description"));
//			String logicId = sysParamService.getLogicId(catalog);
//
//			SysParamVO sysParamVO = new SysParamVO();
//			sysParamVO.setLogicId(logicId);
//			sysParamVO.setCatalog(catalog);
//			sysParamVO.setParamName(paramName);
//			sysParamVO.setParamValue(paramValue);
//			sysParamVO.setStatement(statement);
//			sysParamVO.setDescription(description);
//
//			sysParamService.addSysParam(sysParamVO);
//
//			return new ModelAndView("pageinfo_pagedecrease", "message", "添加系统参数成功！").addObject("url", "param.do?action=qrySysparam");
//		} catch (Exception e) {
//			log.error("添加系统参数异常：", e);
//			return new ModelAndView("info", "message", "添加系统参数异常："+e.getMessage());
//		}
//	}

	/**
	 * 转向修改系统参数页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView intoModSysPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			String logicId = StringUtil.parseString(request.getParameter("logicId"));
			
			SysParam sysParam = sysParamService.qrySysParamByNo(logicId);
			
			if (sysParam == null) {
				return new ModelAndView("info", "message", "系统参数已经不存在");
			}
//			List sysParamCatalogList = sysParamService.qrySysParamCatalog();
			
			return new ModelAndView("system/param_sys_mod").addObject("sysParam", sysParam);
		} catch (Exception e) {
			log.error("转向修改系统参数页面异常：", e);
			return new ModelAndView("info", "message", "转向修改系统参数页面异常: "+e.getMessage());
		}
	}

	/**
	 * 修改系统参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView modSysparam(HttpServletRequest request, HttpServletResponse response) {
		try {
			String logicId = StringUtil.parseString(request.getParameter("logicId"));
			Integer catalog = StringUtil.parseInteger2(request.getParameter("catalog"));
			String paramName = StringUtil.parseString(request.getParameter("paramName"));
			String paramValue = StringUtil.parseString(request.getParameter("paramValue"));
			String statement = StringUtil.parseString(request.getParameter("statement"));
			String description = StringUtil.parseString(request.getParameter("description"));

			SysParamVO sysParamVO = new SysParamVO();
			sysParamVO.setLogicId(logicId);
			sysParamVO.setCatalog(catalog);
			sysParamVO.setParamName(paramName);
			sysParamVO.setParamValue(paramValue);
			sysParamVO.setStatement(statement);
			sysParamVO.setDescription(description);

			sysParamService.modSysParam(sysParamVO);

			return new ModelAndView("pageinfo_pagedecrease", "message", "修改系统参数成功！").addObject("url", "param.do?action=qrySysparam");
		} catch (Exception e) {
			log.error("修改系统参数异常：", e);
			return new ModelAndView("info", "message", "修改系统参数异常："+e.getMessage());
		}
	}

	/**
	 * 删除系统参数信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
//	public ModelAndView delSysparam(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			String logicId = StringUtil.parseString(request.getParameter("logicId"));
//			SysParamVO sysParamVO = sysParamService.po_vo(sysParamService.qrySysParamByNo(logicId));
//			if (sysParamVO == null) {
//				return new ModelAndView("info", "message", "系统参数已经不存在");
//			}
//			
//			sysParamService.delSysParam(sysParamVO);
//
//			return new ModelAndView("pageinfo_pagedecrease", "message", "删除系统参数成功！").addObject("url", "param.do?action=qrySysparam");
//		} catch (Exception e) {
//			log.error("删除系统参数异常：", e);
//			return new ModelAndView("info", "message", "删除系统参数异常："+e.getMessage());
//		}
//	}

}