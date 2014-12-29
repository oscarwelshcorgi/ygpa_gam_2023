/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.web;

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
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngNticService;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngNticUnpaidVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngNticVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngNticDetailVo;


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
public class GamFcltsFeeMngNticController {

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

	@Resource(name = "gamFcltsFeeMngNticService")
	private GamFcltsFeeMngNticService gamFcltsFeeMngNticService;

	@RequestMapping(value="/mngFee/gamFcltsFeeMngNtic.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		// login정보
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

		return "/ygpa/gam/mngFee/GamFcltsFeeMngNtic";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectFcltsFeeMngNtic.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsFeeMngNtic(GamFcltsFeeMngNticVo searchVO) throws Exception {

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

		GamFcltsFeeMngNticVo resultSum = gamFcltsFeeMngNticService.selectFcltsFeeMngNticListTotCnt(searchVO);
		List resultList = gamFcltsFeeMngNticService.selectFcltsFeeMngNticList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", resultSum.getDataCount());
		map.put("sumFee", resultSum.getSumFee());
		map.put("sumVat", resultSum.getSumVat());
		map.put("sumNticAmt", resultSum.getSumNticAmt());
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectFcltsFeeMngNticPk.do")
	@ResponseBody Map<String, Object> gamSelectFcltsFeeMngNticPk(GamFcltsFeeMngNticVo searchVO)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltsFeeMngNticService.selectFcltsFeeMngNticPk(searchVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamExcelDownloadFcltsFeeMngNtic.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltsFeeMngNtic(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltsFeeMngNticVo searchVO= new GamFcltsFeeMngNticVo();
		searchVO = mapper.convertValue(excelParam, GamFcltsFeeMngNticVo.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltsFeeMngNticService.selectFcltsFeeMngNticList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectFcltsFeeMngNticDetail.do", method=RequestMethod.POST)
	public @ResponseBody Map gamSelectFcltsMngFeeMngDetail(GamFcltsFeeMngNticDetailVo searchVO) throws Exception {

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

		List resultList = gamFcltsFeeMngNticService.selectFcltsFeeMngNticDetailList(searchVO);

		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectFcltsFeeMngNticMaxReqestSeq.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsFeeMngNticMaxReqestSeq(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) throws Exception {

		String sMaxReqestSeq;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sMaxReqestSeq = gamFcltsFeeMngNticService.selectFcltsFeeMngNticMaxReqestSeq(gamFcltsFeeMngNticVo);

		map.put("resultCode", 0);
		map.put("sMaxReqestSeq", sMaxReqestSeq);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectFcltsFeeMngNticEntrpsNm.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsFeeMngNticEntrpsNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sEntrpsNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sEntrpsNm = gamFcltsFeeMngNticService.selectEntrpsNm(searchVO);

		map.put("resultCode", 0);
		map.put("sEntrpsNm", sEntrpsNm);

		return map;

	}

	@RequestMapping(value="/mngFee/gamPrintPreviewFcltsFeeMngNticNoticeIssue.do")
	String gamPrintPreviewFcltsFeeMngNticNoticeIssue(@RequestParam Map<String, Object> approvalOpt, ModelMap model) throws Exception {

		model.addAttribute("searchOpt", approvalOpt);

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("resultCode", 1);
			model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
		} else {
    		List list = gamFcltsFeeMngNticService.selectFcltsFeeMngNticPrintNoticeIssueList(approvalOpt);

			model.addAttribute("resultCode", 0);
			model.addAttribute("resultList", list);
			model.addAttribute("resultMsg", "");
		}

		return "ygpa/gam/mngFee/GamFcltsFeeMngPrintNoticeIssue";

	}

	@RequestMapping(value="/mngFee/gamInsertFcltsFeeMngNtic.do")
	@ResponseBody Map<String, Object> gamInsertFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsFeeMngNticVo.setRegUsr((String)user.getId());
			gamFcltsFeeMngNticVo.setUpdUsr((String)user.getId());
			gamFcltsFeeMngNticVo.setDeptCd((String)user.getDeptCd());
			gamFcltsFeeMngNticService.insertFcltsFeeMngNtic(gamFcltsFeeMngNticVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamSaveFcltsFeeMngNticIssue.do")
	@ResponseBody Map<String, Object> gamSaveFcltsFeeMngNticIssue(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsFeeMngNticVo.setUpdUsr((String)user.getId());
			gamFcltsFeeMngNticVo.setDeptCd((String)user.getDeptCd());
			gamFcltsFeeMngNticVo.setDeptCd((String)user.getDeptCd());
			gamFcltsFeeMngNticService.updateFcltsFeeMngNtic(gamFcltsFeeMngNticVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamDeleteFcltsFeeMngNtic.do")
	@ResponseBody Map<String, Object> gamDeleteFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsFeeMngNticService.deleteFcltsFeeMngNtic(gamFcltsFeeMngNticVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamProcessFcltsFeeMngNticIssue.do")
	@ResponseBody Map<String, Object> gamProcessFcltsFeeMngNticIssue(@RequestParam Map<String, Object> processVo)	throws Exception {

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
			gamFcltsFeeMngNticService.processFcltsFeeMngNticIssue(processVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamCancelFcltsFeeMngNticIssue.do")
	@ResponseBody Map<String, Object> gamCancelFcltsFeeMngNticIssue(@RequestParam Map<String, Object> cancelVo)	throws Exception {

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
			gamFcltsFeeMngNticService.cancelFcltsFeeMngNticIssue(cancelVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamPrintFcltsFeeMngNticIssue.do")
	public @ResponseBody Map gamPrintFcltsFeeMngNticIssue(@RequestParam Map<String, Object> printVo, ModelMap model)	throws Exception {

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
    		gamFcltsFeeMngNticService.updateFcltsFeeMngNticIssuePrintYn(printVo);
			resultCode = 0;
			resultMsg = egovMessageSource.getMessage("success.common.update");
		} catch(Exception e) {
			resultCode = 1;
			resultMsg = egovMessageSource.getMessage("fail.common.update");
		}

		map.put("resultCode", resultCode);
		map.put("resultMsg", resultMsg);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectFcltsFeeMngNticUnpaidFMaxDlySerNo.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsFeeMngNticUnpaidFMaxDlySerNo(@RequestParam Map<String, Object> searchVo) throws Exception {

		String sMaxDlySerNo;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sMaxDlySerNo = gamFcltsFeeMngNticService.selectUnpaidFMaxDlySerNo(searchVo);

		map.put("resultCode", 0);
		map.put("sMaxDlySerNo", sMaxDlySerNo);

		return map;

	}

	@RequestMapping(value="/mngFee/gamCalcDlyBillAmnt.do")
	@ResponseBody Map<String, Object> gamCalcDlyBillAmnt(@RequestParam Map<String, Object> calcVo)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltsFeeMngNticService.calcDlyBillAmnt(calcVo);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;
	}

	@RequestMapping(value="/mngFee/gamProcessFcltsFeeMngNticIssueUnpaid.do")
	@ResponseBody Map<String, Object> gamProcessFcltsFeeMngNticIssueUnpaid(@RequestParam Map<String, Object> processVo)	throws Exception {

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
			processVo.put("emplNo", (String)user.getId());
			processVo.put("deptCd", (String)user.getDeptCd());
			processVo.put("userName", (String)user.getName());
			gamFcltsFeeMngNticService.processFcltsFeeMngNticIssueUnpaid(processVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamCancelFcltsFeeMngNticIssueUnpaid.do")
	@ResponseBody Map<String, Object> gamCancelFcltsFeeMngNticIssueUnpaid(@RequestParam Map<String, Object> cancelVo)	throws Exception {

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
			gamFcltsFeeMngNticService.cancelFcltsFeeMngNticIssueUnpaid(cancelVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectFcltsFeeMngNticUnpaid.do", method=RequestMethod.POST)
	public @ResponseBody Map gamSelectFcltsFeeMngNticUnpaid(GamFcltsFeeMngNticUnpaidVo searchVO) throws Exception {

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

		List resultList = gamFcltsFeeMngNticService.selectFcltsFeeMngNticUnpaidList(searchVO);

		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

}
