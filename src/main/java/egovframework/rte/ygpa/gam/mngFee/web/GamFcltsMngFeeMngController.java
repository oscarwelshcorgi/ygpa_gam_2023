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
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngDetailVo;


/**
 *
 * @author Lee
 * @since 2014. 10. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 22.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamFcltsMngFeeMngController {

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

	@Resource(name = "gamFcltsMngFeeMngService")
	private GamFcltsMngFeeMngService gamFcltsMngFeeMngService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamFcltsMngFeeMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

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

		return "/ygpa/gam/mngFee/GamFcltsMngFeeMng";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectFcltsMngFeeMng.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsMngFeeMng(GamFcltsMngFeeMngVo searchVO) throws Exception {

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

		GamFcltsMngFeeMngVo resultSum = gamFcltsMngFeeMngService.selectFcltsMngFeeMngListTotCnt(searchVO);
		List resultList = gamFcltsMngFeeMngService.selectFcltsMngFeeMngList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", resultSum.getDataCount());
		map.put("sumFcltyMngFee", resultSum.getSumFcltyMngFee());
		map.put("sumElctyFee", resultSum.getSumElctyFee());
		map.put("sumWaterFee", resultSum.getSumWaterFee());
		map.put("sumGasFee", resultSum.getSumGasFee());
		map.put("sumEnvFee", resultSum.getSumEnvFee());
		map.put("sumMngTotalFee", resultSum.getSumMngTotalFee());
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectFcltsMngFeeMngPk.do")
	@ResponseBody Map<String, Object> gamSelectFcltsMngFeeMngPk(GamFcltsMngFeeMngVo searchVO)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltsMngFeeMngService.selectFcltsMngFeeMngPk(searchVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (IOException i) {
			
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectFcltsMngFeeMngMonthCnt.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsMngFeeMngMonthCnt(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamFcltsMngFeeMngService.selectFcltsMngFeeMngMonthCntList(gamFcltsMngFeeMngVo);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamExcelDownloadFcltsMngFeeMng.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltsMngFeeMng(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltsMngFeeMngVo searchVO= new GamFcltsMngFeeMngVo();
		searchVO = mapper.convertValue(excelParam, GamFcltsMngFeeMngVo.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltsMngFeeMngService.selectFcltsMngFeeMngList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectFcltsMngFeeMngDetail.do", method=RequestMethod.POST)
	public @ResponseBody Map gamSelectFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo searchVO) throws Exception {

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

		List resultList = gamFcltsMngFeeMngService.selectFcltsMngFeeMngDetailList(searchVO);

		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectFcltsMngFeeMngDetailMaxMngSeq.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsMngFeeMngDetailMaxMngSeq(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception {

		String sMaxMngSeq;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sMaxMngSeq = gamFcltsMngFeeMngService.selectFcltsMngFeeMngDetailMaxMngSeq(gamFcltsMngFeeMngDetailVo);

		map.put("resultCode", 0);
		map.put("sMaxMngSeq", sMaxMngSeq);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamExcelDownloadFcltsMngFeeMngDetail.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltsMngFeeMngDetail(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltsMngFeeMngDetailVo searchVO= new GamFcltsMngFeeMngDetailVo();
		searchVO = mapper.convertValue(excelParam, GamFcltsMngFeeMngDetailVo.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltsMngFeeMngService.selectFcltsMngFeeMngDetailList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@RequestMapping(value="/mngFee/gamInsertFcltsMngFeeMng.do")
	@ResponseBody Map<String, Object> gamInsertFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsMngFeeMngVo.setMainRegUsr((String)user.getId());
			gamFcltsMngFeeMngVo.setMainUpdUsr((String)user.getId());
			gamFcltsMngFeeMngService.insertFcltsMngFeeMng(gamFcltsMngFeeMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (IOException i) {
			
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamUpdateFcltsMngFeeMng.do")
	@ResponseBody Map<String, Object> gamUpdateFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsMngFeeMngVo.setMainUpdUsr((String)user.getId());
			gamFcltsMngFeeMngService.updateFcltsMngFeeMng(gamFcltsMngFeeMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (IOException i) {
			 
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamDeleteFcltsMngFeeMng.do")
	@ResponseBody Map<String, Object> gamDeleteFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsMngFeeMngService.deleteFcltsMngFeeMng(gamFcltsMngFeeMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (IOException i) {
			
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamInsertFcltsMngFeeMngDetail.do")
	@ResponseBody Map<String, Object> gamInsertFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsMngFeeMngDetailVo.setRegUsr((String)user.getId());
			gamFcltsMngFeeMngDetailVo.setUpdUsr((String)user.getId());
			gamFcltsMngFeeMngDetailVo.setDeptCd((String)user.getDeptCd());
			gamFcltsMngFeeMngService.insertFcltsMngFeeMngDetail(gamFcltsMngFeeMngDetailVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (IOException i) {
			
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamUpdateFcltsMngFeeMngDetail.do")
	@ResponseBody Map<String, Object> gamUpdateFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsMngFeeMngDetailVo.setUpdUsr((String)user.getId());
			gamFcltsMngFeeMngDetailVo.setDeptCd((String)user.getDeptCd());
			gamFcltsMngFeeMngService.updateFcltsMngFeeMngDetail(gamFcltsMngFeeMngDetailVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (IOException i) {
			
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamDeleteFcltsMngFeeMngDetail.do")
	@ResponseBody Map<String, Object> gamDeleteFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsMngFeeMngService.deleteFcltsMngFeeMngDetail(gamFcltsMngFeeMngDetailVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (IOException i) {
			
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamProcessFcltsMngFeeMngNticIssue.do")
	@ResponseBody Map<String, Object> gamProcessFcltsMngFeeMngNticIssue(@RequestParam Map<String, Object> processVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			processVo.put("updUsr", (String)user.getId());
			processVo.put("deptCd", (String)user.getDeptCd());
			processVo.put("userName", (String)user.getName());
			gamFcltsMngFeeMngService.processFcltsMngFeeMngNticIssue(processVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (IOException i) {
			
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamCancelFcltsMngFeeMngNticIssue.do")
	@ResponseBody Map<String, Object> gamCancelFcltsMngFeeMngNticIssue(@RequestParam Map<String, Object> cancelVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			cancelVo.put("updUsr", (String)user.getId());
			cancelVo.put("deptCd", (String)user.getDeptCd());
			cancelVo.put("userName", (String)user.getName());
			gamFcltsMngFeeMngService.cancelFcltsMngFeeMngNticIssue(cancelVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (IOException i) {
			
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamPrintPreviewFcltsMngFeeMngNoticeIssue.do")
	String gamPrintPreviewFcltsMngFeeMngNoticeIssue(@RequestParam Map<String, Object> approvalOpt, ModelMap model) throws Exception {

		model.addAttribute("searchOpt", approvalOpt);

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("resultCode", 1);
			model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
		} else {
    		List list = gamFcltsMngFeeMngService.selectFcltsMngFeeMngPrintNoticeIssueList(approvalOpt);

			model.addAttribute("resultCode", 0);
			model.addAttribute("resultList", list);
			model.addAttribute("resultMsg", "");
		}

		return "ygpa/gam/mngFee/GamFcltsMngFeeMngPrintNoticeIssue";

	}

	@RequestMapping(value="/mngFee/gamPrintFcltsMngFeeMngNticIssue.do")
	public @ResponseBody Map gamPrintFcltsMngFeeMngNticIssue(@RequestParam Map<String, Object> printVo, ModelMap model)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();
		String resultMsg = "";
		int resultCode = 1;

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
    		printVo.put("nhtPrintYn", "Y");
			printVo.put("updUsr", (String)user.getId());
			printVo.put("deptCd", (String)user.getDeptCd());
			printVo.put("userName", (String)user.getName());
    		gamFcltsMngFeeMngService.updateFcltsMngFeeMngNticIssuePrintYn(printVo);
			resultCode = 0;
			resultMsg = egovMessageSource.getMessage("success.common.update");
		} catch (IOException i) {
			
		} catch(Exception e) {
			resultCode = 1;
			resultMsg = egovMessageSource.getMessage("fail.common.update");
		}

		map.put("resultCode", resultCode);
		map.put("resultMsg", resultMsg);

		return map;

	}

	@RequestMapping(value="/mngFee/gamCopyFcltsMngFeeMng.do")
	@ResponseBody Map<String, Object> gamCopyFcltsMngFeeMng(@RequestParam Map<String, Object> copyVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			copyVo.put("regUsr", (String)user.getId());
			copyVo.put("deptCd", (String)user.getDeptCd());

			gamFcltsMngFeeMngService.copyFcltsMngFeeMng(copyVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (IOException i) {
			
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectFcltsMngFeeMngChart.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsMngFeeMngChart(@RequestParam Map<String, Object> statVo) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamFcltsMngFeeMngService.selectFcltsMngFeeMngChartList(statVo);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamPrintPreviewFcltsMngFeeMngReport.do")
	public String gamPrintPreviewFcltsMngFeeMngReport(@RequestParam Map<String, Object> reportVO, ModelMap model) throws	Exception {

		model.addAttribute("searchOpt", reportVO);

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("resultCode", 1);
			model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
		} else {
    		Map result = gamFcltsMngFeeMngService.selectMngFeeLevRequestFByPk(reportVO);

			model.addAttribute("resultCode", 0);
			model.addAttribute("result", result);
			model.addAttribute("resultMsg", "");
		}

		return "ygpa/gam/mngFee/GamFcltsMngFeeMngPrintReport";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectFcltsMngFeeMngEntrpsNm.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsFeeMngEntrpsNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sEntrpsNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sEntrpsNm = gamFcltsMngFeeMngService.selectEntrpsNm(searchVO);

		map.put("resultCode", 0);
		map.put("sEntrpsNm", sEntrpsNm);

		return map;

	}

}
