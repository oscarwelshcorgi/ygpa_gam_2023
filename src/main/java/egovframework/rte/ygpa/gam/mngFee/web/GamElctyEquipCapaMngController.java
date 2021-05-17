/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyEquipCapaMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyEquipCapaMngVo;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 31.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 31.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamElctyEquipCapaMngController {

	protected Log log = LogFactory.getLog(this.getClass());

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name="gamElctyEquipCapaMngService")
	GamElctyEquipCapaMngService gamElctyEquipCapaMngService;

	@RequestMapping(value="/mngFee/gamElctyEquipCapaMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		///login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		int year = Integer.parseInt(EgovDateUtil.getToday().substring(0,4));
		List yearList = new ArrayList();
		Map yearMap = null;

		for ( int i = year ; i >= year-10 ; i-- ) {
			yearMap = new HashMap();
			yearMap.put("code", i);
			yearMap.put("codeNm", i+"년");
			yearList.add(yearMap);
		}

		model.addAttribute("yearsList", yearList);
		model.addAttribute("thisyear", year);
		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/mngFee/GamElctyEquipCapaMng";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectElctyEquipCapaMng.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectElctyEquipCapaMng(GamElctyEquipCapaMngVo searchVO) throws Exception {

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

		GamElctyEquipCapaMngVo resultSum = gamElctyEquipCapaMngService.selectElctyEquipCapaMngListTotCnt(searchVO);
		List resultList = gamElctyEquipCapaMngService.selectElctyEquipCapaMngList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", resultSum.getDataCount());
		map.put("sumEquipCapa", resultSum.getSumEquipCapa());
		map.put("sumCtrtCapa", resultSum.getSumCtrtCapa());
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectElctyEquipCapaMngPk.do")
	@ResponseBody Map<String, Object> gamSelectElctyEquipCapaMngPk(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamElctyEquipCapaMngService.selectElctyEquipCapaMngPk(gamElctyEquipCapaMngVo);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (IOException i) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;
	}

	@RequestMapping(value="/mngFee/gamSelectElctyEquipCapaMngChart.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectElctyEquipCapaMngChart(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamElctyEquipCapaMngService.selectElctyEquipCapaMngChartList(gamElctyEquipCapaMngVo);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectElctyEquipCapaMngPrevCapa.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectElctyEquipCapaMngPrevCapa(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception {

		String sPrevMtUsageQy;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamElctyEquipCapaMngService.selectElctyEquipCapaMngPrevYearCapa(gamElctyEquipCapaMngVo);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (IOException i) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectElctyEquipCapaMngYearCnt.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectElctyEquipCapaMngYearCnt(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamElctyEquipCapaMngService.selectElctyEquipCapaMngYearCntList(gamElctyEquipCapaMngVo);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/code/gamSelectElctyEquipCapaMngNewMngSeq.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectElctyEquipCapaMngNewMngSeq(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception {

		String sNewMngSeq;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sNewMngSeq = gamElctyEquipCapaMngService.selectElctyEquipCapaMngNewMngSeq(gamElctyEquipCapaMngVo);

		map.put("resultCode", 0);
		map.put("sNewMngSeq", sNewMngSeq);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamExcelDownloadElctyEquipCapaMng.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadElctyEquipCapaMng(@RequestParam Map<String, Object> excelParam) throws Exception {

		Map map = new HashMap();
		List header;
		ObjectMapper mapper = new ObjectMapper();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return new ModelAndView("gridExcelView", "gridResultMap", map);
		}

		header = mapper.readValue((String)excelParam.get("header"),
								  new TypeReference<List<HashMap<String,String>>>(){});
		excelParam.remove("header");

		GamElctyEquipCapaMngVo searchVO= new GamElctyEquipCapaMngVo();
		searchVO = mapper.convertValue(excelParam, GamElctyEquipCapaMngVo.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamElctyEquipCapaMngService.selectElctyEquipCapaMngList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@RequestMapping(value="/mngFee/gamInsertElctyEquipCapaMng.do")
	@ResponseBody Map<String, Object> gamInsertElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElctyEquipCapaMngVo.setRegUsr((String)user.getId());
			gamElctyEquipCapaMngService.insertElctyEquipCapaMng(gamElctyEquipCapaMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (IOException i) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamUpdateElctyEquipCapaMng.do")
	@ResponseBody Map<String, Object> gamUpdateElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElctyEquipCapaMngVo.setUpdUsr((String)user.getId());
			gamElctyEquipCapaMngService.updateElctyEquipCapaMng(gamElctyEquipCapaMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (IOException i) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamDeleteElctyEquipCapaMng.do")
	@ResponseBody Map<String, Object> gamDeleteElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElctyEquipCapaMngService.deleteElctyEquipCapaMng(gamElctyEquipCapaMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (IOException i) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamCopyElctyEquipCapaMng.do")
	@ResponseBody Map<String, Object> gamCopyElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElctyEquipCapaMngVo.setRegUsr((String)user.getId());
			gamElctyEquipCapaMngService.copyElctyEquipCapaMng(gamElctyEquipCapaMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (IOException i) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

}
