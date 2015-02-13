/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngChangeVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngFlawGrntyVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngFulfillCaryFwdVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngJoinContrVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngMoneyPymntVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngScsbidInfoVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngService;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngSubctrtVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngVO;

/**
 *
 * @author 김종민
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltyCtrtMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamFcltyCtrtMngService")
	private GamFcltyCtrtMngService gamFcltyCtrtMngService;

	@RequestMapping(value="/ctrt/gamFcltyCtrtMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/ctrt/GamFcltyCtrtMng";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngList(GamFcltyCtrtMngVO searchVO) throws Exception {

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

		GamFcltyCtrtMngVO resultSum = gamFcltyCtrtMngService.selectFcltyCtrtMngListSum(searchVO);
		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", resultSum.getTotalCount());
		map.put("sumPlanAmt", resultSum.getSumPlanAmt());
		map.put("sumPrmtAmt", resultSum.getSumPrmtAmt());
		map.put("sumScsbidAmt", resultSum.getSumScsbidAmt());
		map.put("sumBaseAmt", resultSum.getSumBaseAmt());
		map.put("sumCtrtAmt", resultSum.getSumCtrtAmt());
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/ctrt/gamInsertFcltyCtrtMng.do")
	@ResponseBody Map<String, Object> gamInsertFcltyCtrtMng(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngVO.setRegUsr((String)user.getId());
			gamFcltyCtrtMngService.insertFcltyCtrtMng(gamFcltyCtrtMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamUpdateFcltyCtrtMng.do")
	@ResponseBody Map<String, Object> gamUpdateFcltyCtrtMng(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngVO.setUpdUsr((String)user.getId());
			gamFcltyCtrtMngService.updateFcltyCtrtMng(gamFcltyCtrtMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamDeleteFcltyCtrtMng.do")
	@ResponseBody Map<String, Object> gamDeleteFcltyCtrtMng(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngService.deleteFcltyCtrtMng(gamFcltyCtrtMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtMng.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtMng(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltyCtrtMngVO searchVO= new GamFcltyCtrtMngVO();
		searchVO = mapper.convertValue(excelParam, GamFcltyCtrtMngVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngPk.do")
	@ResponseBody Map<String, Object> gamSelectFcltyCtrtMngPk(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltyCtrtMngService.selectFcltyCtrtMngPk(gamFcltyCtrtMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngNewCtrtNo.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngNewCtrtNo(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) throws Exception {

		String sNewCtrtNo;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sNewCtrtNo = gamFcltyCtrtMngService.selectFcltyCtrtMngNewCtrtNo(gamFcltyCtrtMngVO);

		map.put("resultCode", 0);
		map.put("sNewCtrtNo", sNewCtrtNo);

		return map;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngJoinContrList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngJoinContrList(GamFcltyCtrtMngJoinContrVO searchVO) throws Exception {

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

		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngJoinContrList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@RequestMapping(value="/ctrt/gamInsertFcltyCtrtMngJoinContr.do")
	@ResponseBody Map<String, Object> gamInsertFcltyCtrtMngJoinContr(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngJoinContrVO.setRegUsr((String)user.getId());
			gamFcltyCtrtMngService.insertFcltyCtrtMngJoinContr(gamFcltyCtrtMngJoinContrVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamUpdateFcltyCtrtMngJoinContr.do")
	@ResponseBody Map<String, Object> gamUpdateFcltyCtrtMngJoinContr(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngJoinContrVO.setUpdUsr((String)user.getId());
			gamFcltyCtrtMngService.updateFcltyCtrtMngJoinContr(gamFcltyCtrtMngJoinContrVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamDeleteFcltyCtrtMngJoinContr.do")
	@ResponseBody Map<String, Object> gamDeleteFcltyCtrtMngJoinContr(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngService.deleteFcltyCtrtMngJoinContr(gamFcltyCtrtMngJoinContrVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtMngJoinContr.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtMngJoinContr(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltyCtrtMngJoinContrVO searchVO= new GamFcltyCtrtMngJoinContrVO();
		searchVO = mapper.convertValue(excelParam, GamFcltyCtrtMngJoinContrVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngJoinContrList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngJoinContrPk.do")
	@ResponseBody Map<String, Object> gamSelectFcltyCtrtMngJoinContrPk(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltyCtrtMngService.selectFcltyCtrtMngJoinContrPk(gamFcltyCtrtMngJoinContrVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngJoinContrMaxSeq.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngJoinContrMaxSeq(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) throws Exception {

		String sMaxSeq;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sMaxSeq = gamFcltyCtrtMngService.selectFcltyCtrtMngJoinContrMaxSeq(gamFcltyCtrtMngJoinContrVO);

		map.put("resultCode", 0);
		map.put("sMaxSeq", sMaxSeq);

		return map;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngSubctrtList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngSubctrtList(GamFcltyCtrtMngSubctrtVO searchVO) throws Exception {

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

		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngSubctrtList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@RequestMapping(value="/ctrt/gamInsertFcltyCtrtMngSubctrt.do")
	@ResponseBody Map<String, Object> gamInsertFcltyCtrtMngSubctrt(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngSubctrtVO.setRegUsr((String)user.getId());
			gamFcltyCtrtMngService.insertFcltyCtrtMngSubctrt(gamFcltyCtrtMngSubctrtVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamUpdateFcltyCtrtMngSubctrt.do")
	@ResponseBody Map<String, Object> gamUpdateFcltyCtrtMngSubctrt(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngSubctrtVO.setUpdUsr((String)user.getId());
			gamFcltyCtrtMngService.updateFcltyCtrtMngSubctrt(gamFcltyCtrtMngSubctrtVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamDeleteFcltyCtrtMngSubctrt.do")
	@ResponseBody Map<String, Object> gamDeleteFcltyCtrtMngSubctrt(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngService.deleteFcltyCtrtMngSubctrt(gamFcltyCtrtMngSubctrtVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtMngSubctrt.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtMngSubctrt(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltyCtrtMngSubctrtVO searchVO= new GamFcltyCtrtMngSubctrtVO();
		searchVO = mapper.convertValue(excelParam, GamFcltyCtrtMngSubctrtVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngSubctrtList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngSubctrtPk.do")
	@ResponseBody Map<String, Object> gamSelectFcltyCtrtMngSubctrtPk(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltyCtrtMngService.selectFcltyCtrtMngSubctrtPk(gamFcltyCtrtMngSubctrtVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngSubctrtMaxSeq.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngSubctrtMaxSeq(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) throws Exception {

		String sMaxSeq;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sMaxSeq = gamFcltyCtrtMngService.selectFcltyCtrtMngSubctrtMaxSeq(gamFcltyCtrtMngSubctrtVO);

		map.put("resultCode", 0);
		map.put("sMaxSeq", sMaxSeq);

		return map;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngChangeList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngChangeList(GamFcltyCtrtMngChangeVO searchVO) throws Exception {

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

		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngChangeList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@RequestMapping(value="/ctrt/gamInsertFcltyCtrtMngChange.do")
	@ResponseBody Map<String, Object> gamInsertFcltyCtrtMngChange(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngChangeVO.setRegUsr((String)user.getId());
			gamFcltyCtrtMngService.insertFcltyCtrtMngChange(gamFcltyCtrtMngChangeVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamUpdateFcltyCtrtMngChange.do")
	@ResponseBody Map<String, Object> gamUpdateFcltyCtrtMngChange(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngChangeVO.setUpdUsr((String)user.getId());
			gamFcltyCtrtMngService.updateFcltyCtrtMngChange(gamFcltyCtrtMngChangeVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamDeleteFcltyCtrtMngChange.do")
	@ResponseBody Map<String, Object> gamDeleteFcltyCtrtMngChange(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngService.deleteFcltyCtrtMngChange(gamFcltyCtrtMngChangeVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtMngChange.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtMngChange(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltyCtrtMngChangeVO searchVO= new GamFcltyCtrtMngChangeVO();
		searchVO = mapper.convertValue(excelParam, GamFcltyCtrtMngChangeVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngChangeList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngChangePk.do")
	@ResponseBody Map<String, Object> gamSelectFcltyCtrtMngChangePk(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltyCtrtMngService.selectFcltyCtrtMngChangePk(gamFcltyCtrtMngChangeVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngChangeMaxSeq.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngChangeMaxSeq(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) throws Exception {

		String sMaxSeq;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sMaxSeq = gamFcltyCtrtMngService.selectFcltyCtrtMngChangeMaxSeq(gamFcltyCtrtMngChangeVO);

		map.put("resultCode", 0);
		map.put("sMaxSeq", sMaxSeq);

		return map;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngMoneyPymntList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngMoneyPymntList(GamFcltyCtrtMngMoneyPymntVO searchVO) throws Exception {

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

		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngMoneyPymntList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@RequestMapping(value="/ctrt/gamInsertFcltyCtrtMngMoneyPymnt.do")
	@ResponseBody Map<String, Object> gamInsertFcltyCtrtMngMoneyPymnt(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngMoneyPymntVO.setRegUsr((String)user.getId());
			gamFcltyCtrtMngService.insertFcltyCtrtMngMoneyPymnt(gamFcltyCtrtMngMoneyPymntVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamUpdateFcltyCtrtMngMoneyPymnt.do")
	@ResponseBody Map<String, Object> gamUpdateFcltyCtrtMngMoneyPymnt(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngMoneyPymntVO.setUpdUsr((String)user.getId());
			gamFcltyCtrtMngService.updateFcltyCtrtMngMoneyPymnt(gamFcltyCtrtMngMoneyPymntVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamDeleteFcltyCtrtMngMoneyPymnt.do")
	@ResponseBody Map<String, Object> gamDeleteFcltyCtrtMngMoneyPymnt(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngService.deleteFcltyCtrtMngMoneyPymnt(gamFcltyCtrtMngMoneyPymntVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtMngMoneyPymnt.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtMngMoneyPymnt(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltyCtrtMngMoneyPymntVO searchVO= new GamFcltyCtrtMngMoneyPymntVO();
		searchVO = mapper.convertValue(excelParam, GamFcltyCtrtMngMoneyPymntVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngMoneyPymntList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngMoneyPymntPk.do")
	@ResponseBody Map<String, Object> gamSelectFcltyCtrtMngMoneyPymntPk(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltyCtrtMngService.selectFcltyCtrtMngMoneyPymntPk(gamFcltyCtrtMngMoneyPymntVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngMoneyPymntMaxSeq.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngMoneyPymntMaxSeq(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) throws Exception {

		String sMaxSeq;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sMaxSeq = gamFcltyCtrtMngService.selectFcltyCtrtMngMoneyPymntMaxSeq(gamFcltyCtrtMngMoneyPymntVO);

		map.put("resultCode", 0);
		map.put("sMaxSeq", sMaxSeq);

		return map;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngFulfillCaryFwdList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngFulfillCaryFwdList(GamFcltyCtrtMngFulfillCaryFwdVO searchVO) throws Exception {

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

		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngFulfillCaryFwdList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@RequestMapping(value="/ctrt/gamInsertFcltyCtrtMngFulfillCaryFwd.do")
	@ResponseBody Map<String, Object> gamInsertFcltyCtrtMngFulfillCaryFwd(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngFulfillCaryFwdVO.setRegUsr((String)user.getId());
			gamFcltyCtrtMngService.insertFcltyCtrtMngFulfillCaryFwd(gamFcltyCtrtMngFulfillCaryFwdVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamUpdateFcltyCtrtMngFulfillCaryFwd.do")
	@ResponseBody Map<String, Object> gamUpdateFcltyCtrtMngFulfillCaryFwd(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngFulfillCaryFwdVO.setUpdUsr((String)user.getId());
			gamFcltyCtrtMngService.updateFcltyCtrtMngFulfillCaryFwd(gamFcltyCtrtMngFulfillCaryFwdVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamDeleteFcltyCtrtMngFulfillCaryFwd.do")
	@ResponseBody Map<String, Object> gamDeleteFcltyCtrtMngFulfillCaryFwd(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngService.deleteFcltyCtrtMngFulfillCaryFwd(gamFcltyCtrtMngFulfillCaryFwdVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtMngFulfillCaryFwd.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtMngFulfillCaryFwd(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltyCtrtMngFulfillCaryFwdVO searchVO= new GamFcltyCtrtMngFulfillCaryFwdVO();
		searchVO = mapper.convertValue(excelParam, GamFcltyCtrtMngFulfillCaryFwdVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngFulfillCaryFwdList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngFulfillCaryFwdPk.do")
	@ResponseBody Map<String, Object> gamSelectFcltyCtrtMngFulfillCaryFwdPk(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltyCtrtMngService.selectFcltyCtrtMngFulfillCaryFwdPk(gamFcltyCtrtMngFulfillCaryFwdVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngFulfillCaryFwdMaxSeq.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngFulfillCaryFwdMaxSeq(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) throws Exception {

		String sMaxSeq;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sMaxSeq = gamFcltyCtrtMngService.selectFcltyCtrtMngFulfillCaryFwdMaxSeq(gamFcltyCtrtMngFulfillCaryFwdVO);

		map.put("resultCode", 0);
		map.put("sMaxSeq", sMaxSeq);

		return map;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngScsbidInfoList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngScsbidInfoList(GamFcltyCtrtMngScsbidInfoVO searchVO) throws Exception {

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

		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngScsbidInfoList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@RequestMapping(value="/ctrt/gamInsertFcltyCtrtMngScsbidInfo.do")
	@ResponseBody Map<String, Object> gamInsertFcltyCtrtMngScsbidInfo(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngScsbidInfoVO.setRegUsr((String)user.getId());
			gamFcltyCtrtMngService.insertFcltyCtrtMngScsbidInfo(gamFcltyCtrtMngScsbidInfoVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamUpdateFcltyCtrtMngScsbidInfo.do")
	@ResponseBody Map<String, Object> gamUpdateFcltyCtrtMngScsbidInfo(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngScsbidInfoVO.setUpdUsr((String)user.getId());
			gamFcltyCtrtMngService.updateFcltyCtrtMngScsbidInfo(gamFcltyCtrtMngScsbidInfoVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamDeleteFcltyCtrtMngScsbidInfo.do")
	@ResponseBody Map<String, Object> gamDeleteFcltyCtrtMngScsbidInfo(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngService.deleteFcltyCtrtMngScsbidInfo(gamFcltyCtrtMngScsbidInfoVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtMngScsbidInfo.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtMngScsbidInfo(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltyCtrtMngScsbidInfoVO searchVO= new GamFcltyCtrtMngScsbidInfoVO();
		searchVO = mapper.convertValue(excelParam, GamFcltyCtrtMngScsbidInfoVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngScsbidInfoList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngScsbidInfoPk.do")
	@ResponseBody Map<String, Object> gamSelectFcltyCtrtMngScsbidInfoPk(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltyCtrtMngService.selectFcltyCtrtMngScsbidInfoPk(gamFcltyCtrtMngScsbidInfoVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngScsbidInfoMaxSeq.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngScsbidInfoMaxSeq(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) throws Exception {

		String sMaxSeq;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sMaxSeq = gamFcltyCtrtMngService.selectFcltyCtrtMngScsbidInfoMaxSeq(gamFcltyCtrtMngScsbidInfoVO);

		map.put("resultCode", 0);
		map.put("sMaxSeq", sMaxSeq);

		return map;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngFlawGrntyList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngFlawGrntyList(GamFcltyCtrtMngFlawGrntyVO searchVO) throws Exception {

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

		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngFlawGrntyList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@RequestMapping(value="/ctrt/gamInsertFcltyCtrtMngFlawGrnty.do")
	@ResponseBody Map<String, Object> gamInsertFcltyCtrtMngFlawGrnty(GamFcltyCtrtMngFlawGrntyVO gamFcltyCtrtMngFlawGrntyVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngFlawGrntyVO.setRegUsr((String)user.getId());
			gamFcltyCtrtMngService.insertFcltyCtrtMngFlawGrnty(gamFcltyCtrtMngFlawGrntyVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamUpdateFcltyCtrtMngFlawGrnty.do")
	@ResponseBody Map<String, Object> gamUpdateFcltyCtrtMngFlawGrnty(GamFcltyCtrtMngFlawGrntyVO gamFcltyCtrtMngFlawGrntyVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngFlawGrntyVO.setUpdUsr((String)user.getId());
			gamFcltyCtrtMngService.updateFcltyCtrtMngFlawGrnty(gamFcltyCtrtMngFlawGrntyVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/ctrt/gamDeleteFcltyCtrtMngFlawGrnty.do")
	@ResponseBody Map<String, Object> gamDeleteFcltyCtrtMngFlawGrnty(GamFcltyCtrtMngFlawGrntyVO gamFcltyCtrtMngFlawGrntyVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyCtrtMngService.deleteFcltyCtrtMngFlawGrnty(gamFcltyCtrtMngFlawGrntyVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtMngFlawGrnty.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtMngFlawGrnty(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltyCtrtMngFlawGrntyVO searchVO= new GamFcltyCtrtMngFlawGrntyVO();
		searchVO = mapper.convertValue(excelParam, GamFcltyCtrtMngFlawGrntyVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltyCtrtMngService.selectFcltyCtrtMngFlawGrntyList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngFlawGrntyPk.do")
	@ResponseBody Map<String, Object> gamSelectFcltyCtrtMngFlawGrntyPk(GamFcltyCtrtMngFlawGrntyVO gamFcltyCtrtMngFlawGrntyVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltyCtrtMngService.selectFcltyCtrtMngFlawGrntyPk(gamFcltyCtrtMngFlawGrntyVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngFlawGrntyMaxSeq.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtMngFlawGrntyMaxSeq(GamFcltyCtrtMngFlawGrntyVO gamFcltyCtrtMngFlawGrntyVO) throws Exception {

		String sMaxSeq;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sMaxSeq = gamFcltyCtrtMngService.selectFcltyCtrtMngFlawGrntyMaxSeq(gamFcltyCtrtMngFlawGrntyVO);

		map.put("resultCode", 0);
		map.put("sMaxSeq", sMaxSeq);

		return map;

	}

	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtMngEntrpsInfo.do")
	@ResponseBody Map<String, Object> gamSelectFcltyCtrtMngEntrpsInfo(@RequestParam Map<String, Object> searchVO)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltyCtrtMngService.selectEntrpsInfo(searchVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;
	}

}
