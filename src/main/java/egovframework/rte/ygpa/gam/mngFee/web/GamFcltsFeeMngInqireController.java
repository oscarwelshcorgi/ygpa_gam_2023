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
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngInqireService;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngInqireUnpaidVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngInqireVo;


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
public class GamFcltsFeeMngInqireController {

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

	@Resource(name = "gamFcltsFeeMngInqireService")
	private GamFcltsFeeMngInqireService gamFcltsFeeMngInqireService;

	@RequestMapping(value="/mngFee/gamFcltsFeeMngInqire.do")
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

		return "/ygpa/gam/mngFee/GamFcltsFeeMngInqire";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectFcltsFeeMngInqire.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsFeeMngInqire(GamFcltsFeeMngInqireVo searchVO) throws Exception {

		int page, firstIndex;
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

		GamFcltsFeeMngInqireVo resultSum = gamFcltsFeeMngInqireService.selectFcltsFeeMngInqireListTotCnt(searchVO);
		List resultList = gamFcltsFeeMngInqireService.selectFcltsFeeMngInqireList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", resultSum.getDataCount());
		map.put("sumFee", resultSum.getSumFee());
		map.put("sumVat", resultSum.getSumVat());
		map.put("sumNticAmt", resultSum.getSumNticAmt());
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectFcltsFeeMngInqireChart.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsFeeMngInqireChart(GamFcltsFeeMngInqireVo gamFcltsFeeMngInqireVo) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamFcltsFeeMngInqireService.selectFcltsFeeMngInqireChartList(gamFcltsFeeMngInqireVo);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamExcelDownloadFcltsFeeMngInqire.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltsFeeMngInqire(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltsFeeMngInqireVo searchVO= new GamFcltsFeeMngInqireVo();
		searchVO = mapper.convertValue(excelParam, GamFcltsFeeMngInqireVo.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltsFeeMngInqireService.selectFcltsFeeMngInqireList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

    @SuppressWarnings("unchecked")
	@RequestMapping(value="/mngFee/gamUpdateFcltsFeeMngInqire.do")
	@ResponseBody Map<String, Object> gamUpdateFcltsFeeMngInqire(@RequestParam Map gamFcltsFeeMngInqireList)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		List<HashMap<String,String>> updateList=null;
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> updateData = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

    	updateList = mapper.readValue((String)gamFcltsFeeMngInqireList.get("updateList"),new TypeReference<List<HashMap<String,String>>>(){});

		try {
			for (int i=0; i<updateList.size(); i++) {
				updateData = (HashMap)updateList.get(i);
				gamFcltsFeeMngInqireService.updateFcltsFeeMngInqire(updateData);
			}

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

	@RequestMapping(value="/mngFee/gamSelectFcltsFeeMngInqireEntrpsNm.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsFeeMngInqireEntrpsNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sEntrpsNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sEntrpsNm = gamFcltsFeeMngInqireService.selectEntrpsNm(searchVO);

		map.put("resultCode", 0);
		map.put("sEntrpsNm", sEntrpsNm);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectFcltsFeeMngInqireUnpaid.do", method=RequestMethod.POST)
	public @ResponseBody Map gamSelectFcltsFeeMngInqireUnpaid(GamFcltsFeeMngInqireUnpaidVo searchVO) throws Exception {

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

		List resultList = gamFcltsFeeMngInqireService.selectFcltsFeeMngInqireUnpaidList(searchVO);

		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

}
