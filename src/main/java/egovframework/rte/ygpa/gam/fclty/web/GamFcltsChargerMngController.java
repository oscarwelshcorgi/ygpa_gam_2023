/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsChargerMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsChargerMngVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 3. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 5.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltsChargerMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamFcltsChargerMngService")
	private GamFcltsChargerMngService gamFcltsChargerMngService;

	@RequestMapping(value="/fclty/gamFcltsChargerMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/fclty/GamFcltsChargerMng";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectFcltsChargerMngList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsChargerMngList(GamFcltsChargerMngVO searchVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		GamFcltsChargerMngVO resultSum = gamFcltsChargerMngService.selectFcltsChargerMngListSum(searchVO);
		List resultList = gamFcltsChargerMngService.selectFcltsChargerMngList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", resultSum.getTotalCount());
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/fclty/gamInsertFcltsChargerMng.do")
	@ResponseBody Map<String, Object> gamInsertFcltsChargerMng(GamFcltsChargerMngVO gamFcltsChargerMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsChargerMngVO.setRegUsr((String)user.getId());
			gamFcltsChargerMngService.insertFcltsChargerMng(gamFcltsChargerMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			//e.printStackTrace();
			Logger log = Logger.getLogger(this.getClass());
			log.error("--error--");

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamUpdateFcltsChargerMng.do")
	@ResponseBody Map<String, Object> gamUpdateFcltsChargerMng(GamFcltsChargerMngVO gamFcltsChargerMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsChargerMngVO.setUpdUsr((String)user.getId());
			gamFcltsChargerMngService.updateFcltsChargerMng(gamFcltsChargerMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			//e.printStackTrace();
			Logger log = Logger.getLogger(this.getClass());
			log.error("--error--");

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamDeleteFcltsChargerMng.do")
	@ResponseBody Map<String, Object> gamDeleteFcltsChargerMng(GamFcltsChargerMngVO gamFcltsChargerMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsChargerMngService.deleteFcltsChargerMng(gamFcltsChargerMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			//e.printStackTrace();
			Logger log = Logger.getLogger(this.getClass());
			log.error("--error--");

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectFcltsChargerMngPk.do")
	@ResponseBody Map<String, Object> gamSelectFcltsChargerMngPk(GamFcltsChargerMngVO gamFcltsChargerMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltsChargerMngService.selectFcltsChargerMngPk(gamFcltsChargerMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			//e.printStackTrace();
			Logger log = Logger.getLogger(this.getClass());
			log.error("--error--");

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

}
