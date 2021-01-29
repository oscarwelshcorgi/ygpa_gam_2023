/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.web;

import java.io.IOException;
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
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtSttusInqireVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtSttusInqireService;

/**
 *
 * @author kok
 * @since 2014. 2. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 3.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltyCtrtSttusInqireController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name="gamFcltyCtrtSttusInqireService")
	GamFcltyCtrtSttusInqireService gamFcltyCtrtSttusInqireService;

	@RequestMapping(value="/ctrt/gamFcltyCtrtSttusInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/ctrt/GamFcltyCtrtSttusInqire";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtSttusInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyCtrtSttusInqireList(GamFcltyCtrtSttusInqireVO searchVO) throws Exception {

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

		GamFcltyCtrtSttusInqireVO resultSum = gamFcltyCtrtSttusInqireService.selectFcltyCtrtSttusInqireListSum(searchVO);
		List resultList = gamFcltyCtrtSttusInqireService.selectFcltyCtrtSttusInqireList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", resultSum.getTotalCount());
		map.put("sumPrevCtrtAmt", resultSum.getSumPrevCtrtAmt());
		map.put("sumCurrCtrtAmt", resultSum.getSumCurrCtrtAmt());
		map.put("resultList", resultList);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamExcelDownloadFcltyCtrtSttusInqire.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltyCtrtSttusInqire(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltyCtrtSttusInqireVO searchVO= new GamFcltyCtrtSttusInqireVO();
		searchVO = mapper.convertValue(excelParam, GamFcltyCtrtSttusInqireVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltyCtrtSttusInqireService.selectFcltyCtrtSttusInqireList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtSttusInqirePrint.do")
	public String gamPrintFcltyCtrtSttusInqire(@RequestParam Map<String, Object> printParam, ModelMap model) throws Exception {

		String sPrevCtrtYr, sCtrtYr;
		ObjectMapper mapper = new ObjectMapper();
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return "/ygpa/gam/ctrt/GamFcltyCtrtSttusInqirePrint";
		}

		GamFcltyCtrtSttusInqireVO searchVO;
		searchVO = mapper.convertValue(printParam, GamFcltyCtrtSttusInqireVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltyCtrtSttusInqireService.selectFcltyCtrtSttusInqireList(searchVO);

		sPrevCtrtYr = searchVO.getsPrevCtrtYr();
		sCtrtYr = searchVO.getsCtrtYr();

		model.addAttribute("sPrevCtrtYr", sPrevCtrtYr);
		model.addAttribute("sCtrtYr", sCtrtYr);
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultList", resultList);

		return "ygpa/gam/ctrt/GamFcltyCtrtSttusInqirePrint";

	}

	@RequestMapping(value="/ctrt/gamSelectFcltyCtrtSttusInqireEntrpsInfo.do")
	@ResponseBody Map<String, Object> gamSelectFcltyCtrtSttusInqireEntrpsInfo(@RequestParam Map<String, Object> searchVO)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltyCtrtSttusInqireService.selectEntrpsInfo(searchVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch(IOException e){
			
		}catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;
	}

}