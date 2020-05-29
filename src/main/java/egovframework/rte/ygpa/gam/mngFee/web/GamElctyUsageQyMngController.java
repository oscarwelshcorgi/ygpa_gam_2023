/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.web;

import java.util.ArrayList;
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
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyUsageQyMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyUsageQyMngVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 7.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamElctyUsageQyMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamElctyUsageQyMngService")
	private GamElctyUsageQyMngService gamElctyUsageQyMngService;

	@RequestMapping(value="/mngFee/gamElctyUsageQyMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		int year = Integer.parseInt(EgovDateUtil.getToday().substring(0,4));
		List yearList = new ArrayList();
		Map yearMap = null;
		List mngFeeFcltyCdList = gamElctyUsageQyMngService.selectMngFeeFcltyCdList();

		for ( int i = year ; i >= year-20 ; i-- ) {
			yearMap = new HashMap();
			yearMap.put("code", i);
			yearMap.put("codeNm", i+"년");
			yearList.add(yearMap);
		}

		model.addAttribute("mngFeeFcltyCdList", mngFeeFcltyCdList);
		model.addAttribute("yearsList", yearList);
		model.addAttribute("thisyear", year);
		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/mngFee/GamElctyUsageQyMng";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectElctyUsageQyMngList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectElctyUsageQyMngList(GamElctyUsageQyMngVO searchVO) throws Exception {

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

		GamElctyUsageQyMngVO resultSum = gamElctyUsageQyMngService.selectElctyUsageQyMngListSum(searchVO);
		List resultList = gamElctyUsageQyMngService.selectElctyUsageQyMngList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", resultSum.getTotalCount());
		map.put("sumUsageQy", resultSum.getSumUsageQy());
		map.put("sumUsageAmt", resultSum.getSumUsageAmt());
		map.put("sumPeekQy", resultSum.getSumPeekQy());
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamInsertElctyUsageQyMng.do")
	@ResponseBody Map<String, Object> gamInsertElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElctyUsageQyMngVO.setRegUsr((String)user.getId());
			gamElctyUsageQyMngService.insertElctyUsageQyMng(gamElctyUsageQyMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			//e.printStackTrace();
			//2020.04.24 보안검사 후속조치
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamUpdateElctyUsageQyMng.do")
	@ResponseBody Map<String, Object> gamUpdateElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElctyUsageQyMngVO.setUpdUsr((String)user.getId());
			gamElctyUsageQyMngService.updateElctyUsageQyMng(gamElctyUsageQyMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			//e.printStackTrace();
			//2020.04.24 보안검사 후속조치
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamDeleteElctyUsageQyMng.do")
	@ResponseBody Map<String, Object> gamDeleteElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElctyUsageQyMngService.deleteElctyUsageQyMng(gamElctyUsageQyMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			//e.printStackTrace();
			//2020.04.24 보안검사 후속조치
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectElctyUsageQyMngPk.do")
	@ResponseBody Map<String, Object> gamSelectElctyUsageQyMngPk(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamElctyUsageQyMngService.selectElctyUsageQyMngPk(gamElctyUsageQyMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {
			//e.printStackTrace();
			//2020.04.24 보안검사 후속조치
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectElctyUsageQyMngChart.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectElctyUsageQyMngChart(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamElctyUsageQyMngService.selectElctyUsageQyMngChartList(gamElctyUsageQyMngVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectElctyUsageQyMngMonthChart.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectElctyUsageQyMngMonthChart(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamElctyUsageQyMngService.selectElctyUsageQyMngMonthChartList(gamElctyUsageQyMngVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}
	@RequestMapping(value="/mngFee/gamSelectElctyUsageQyMngMtAmtChart.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectElctyUsageQyMngMtAmtChart(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamElctyUsageQyMngService.selectElctyUsageQyMngMtAmtChart(gamElctyUsageQyMngVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectElctyUsageQyMngYearCnt.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectElctyUsageQyMngYearCnt(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamElctyUsageQyMngService.selectElctyUsageQyMngYearCntList(gamElctyUsageQyMngVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamExcelDownloadElctyUsageQyMng.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadElctyUsageQyMng(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamElctyUsageQyMngVO searchVO= new GamElctyUsageQyMngVO();
		searchVO = mapper.convertValue(excelParam, GamElctyUsageQyMngVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamElctyUsageQyMngService.selectElctyUsageQyMngList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@RequestMapping(value="/mngFee/gamCopyElctyUsageQyMng.do")
	@ResponseBody Map<String, Object> gamCopyElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElctyUsageQyMngVO.setRegUsr((String)user.getId());
			gamElctyUsageQyMngService.copyElctyUsageQyMng(gamElctyUsageQyMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectElctyUsageQyMngPkCnt.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectElctyUsageQyMngPkCnt(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		int resultCnt = gamElctyUsageQyMngService.selectElctyUsageQyMngPkCnt(gamElctyUsageQyMngVO);

		if(resultCnt > 0){

			map.put("resultCode", 1);
			map.put("resultMsg","이미 등록된 자료가 있습니다.");
		}else{
			map.put("resultCode", 0);
		}


		return map;

	}

}
