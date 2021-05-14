/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.web;

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
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtLgerHistService;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtLgerHistVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngChangeVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngFlawGrntyVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngFulfillCaryFwdVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngJoinContrVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngMoneyPymntVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngScsbidInfoVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngSubctrtVO;

/**
 *
 * @author HNJ
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltyCtrtLgerHistController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamFcltyCtrtLgerHistService")
	private GamFcltyCtrtLgerHistService gamFcltyCtrtLgerHistService;

	@RequestMapping(value="/ctrt/gamFcltyCtrtLgerHist.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/ctrt/GamFcltyCtrtLgerHist";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtLgerHistList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtLgerHistList(GamFcltyCtrtLgerHistVO searchVO) throws Exception {

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

		GamFcltyCtrtLgerHistVO resultSum = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistListSum(searchVO);
		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistList(searchVO);

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtLgerHist.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtLgerHist(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltyCtrtLgerHistVO searchVO= new GamFcltyCtrtLgerHistVO();
		searchVO = mapper.convertValue(excelParam, GamFcltyCtrtLgerHistVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtLgerHistJoinContrList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtLgerHistJoinContrList(GamFcltyCtrtMngJoinContrVO searchVO) throws Exception {

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

		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistJoinContrList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtLgerHistJoinContr.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtLgerHistJoinContr(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistJoinContrList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtLgerHistSubctrtList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtLgerHistSubctrtList(GamFcltyCtrtMngSubctrtVO searchVO) throws Exception {

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

		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistSubctrtList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtLgerHistSubctrt.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtLgerHistSubctrt(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistSubctrtList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtLgerHistChangeList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtLgerHistChangeList(GamFcltyCtrtMngChangeVO searchVO) throws Exception {

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

		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistChangeList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtLgerHistChange.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtLgerHistChange(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistChangeList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtLgerHistMoneyPymntList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtLgerHistMoneyPymntList(GamFcltyCtrtMngMoneyPymntVO searchVO) throws Exception {

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

		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistMoneyPymntList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtLgerHistMoneyPymnt.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtLgerHistMoneyPymnt(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistMoneyPymntList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtLgerHistFulfillCaryFwdList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtLgerHistFulfillCaryFwdList(GamFcltyCtrtMngFulfillCaryFwdVO searchVO) throws Exception {

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

		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistFulfillCaryFwdList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtLgerHistFulfillCaryFwd.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtLgerHistFulfillCaryFwd(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistFulfillCaryFwdList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtLgerHistScsbidInfoList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtLgerHistScsbidInfoList(GamFcltyCtrtMngScsbidInfoVO searchVO) throws Exception {

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

		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistScsbidInfoList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtLgerHistScsbidInfo.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtLgerHistScsbidInfo(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistScsbidInfoList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtLgerHistFlawGrntyList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtLgerHistFlawGrntyList(GamFcltyCtrtMngFlawGrntyVO searchVO) throws Exception {

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

		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistFlawGrntyList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtLgerHistFlawGrnty.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtLgerHistFlawGrnty(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		List resultList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistFlawGrntyList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}


	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtLgerHistEntrpsInfo.do")
	@ResponseBody Map<String, Object> gamSelectFcltyCtrtLgerHistEntrpsInfo(@RequestParam Map<String, Object> searchVO)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltyCtrtLgerHistService.selectEntrpsInfo(searchVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;
	}

}