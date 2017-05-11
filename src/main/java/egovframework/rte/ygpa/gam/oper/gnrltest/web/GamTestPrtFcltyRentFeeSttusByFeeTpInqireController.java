package egovframework.rte.ygpa.gam.oper.gnrltest.web;

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
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentFeeSttusByFeeTpService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentFeeSttusByFeeTpVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtVO;

/**
 * @Class Name : GamTestPrtFcltyRentFeeSttusByFeeTpInqireController.java
 * @Description : 항만시설사용료별고지현황조회 (항만시설/일반부두/항만시설사용료별고지현황조회)
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-11-12
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamTestPrtFcltyRentFeeSttusByFeeTpInqireController {

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

    /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    @Resource(name = "gamTestPrtFcltyRentFeeSttusByFeeTpService")
    private GamTestPrtFcltyRentFeeSttusByFeeTpService gamTestPrtFcltyRentFeeSttusByFeeTpService;


    /**
     * 항만시설요금종류별고지현황 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/gnrltest/GamTestPrtFcltyRentFeeSttusByFeeTpInqire"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/gnrltest/GamTestPrtFcltyRentFeeSttusByFeeTpInqire.do")
	String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		model.addAttribute("windowId", windowId);
		model.addAttribute("yearsList", gamTestPrtFcltyRentFeeSttusByFeeTpService.getYears());
		model.addAttribute("monthsList", gamTestPrtFcltyRentFeeSttusByFeeTpService.getMonths());

    	return "/ygpa/gam/oper/gnrltest/GamTestPrtFcltyRentFeeSttusByFeeTpInqire";
    }

	/**
     * 항만시설요금종류별고지현황 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrltest/selectPrtFcltyMtRentFeeSttusInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map selectPrtFcltyMtRentFeeSttusInqireList(GamTestPrtFcltyRentFeeSttusByFeeTpVO searchVO) throws Exception {

		int totalCnt, totSumFee, page, firstIndex;
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
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

		totalCnt = gamTestPrtFcltyRentFeeSttusByFeeTpService.selectPrtFcltyRentFeeSttusByFeeTpListTotCnt(searchVO);
    	List resultList = gamTestPrtFcltyRentFeeSttusByFeeTpService.selectPrtFcltyRentFeeSttusByFeeTpList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
    	searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	totSumFee = gamTestPrtFcltyRentFeeSttusByFeeTpService.selectPrtFcltyRentFeeSttusByFeeTpSum(searchVO);


    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", resultList);
    	map.put("totSumFee", totSumFee);
    	map.put("totalCount", totalCnt);
    	map.put("searchOption", searchVO);

    	return map;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrltest/selectPrtFcltyRentFeeSttusInqireListExcel.do", method=RequestMethod.POST)
    @ResponseBody ModelAndView selectPrtFcltyMtRentFeeSttusInqireListExcel(@RequestParam Map<String, Object> excelParam) throws Exception {
		Map map = new HashMap();
		List header;
		ObjectMapper mapper = new ObjectMapper();

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    		return new ModelAndView("gridExcelView", "gridResultMap", map);
    	}


    	// 환경설정
    	/** EgovPropertyService */
    	GamTestPrtFcltyRentFeeSttusByFeeTpVO searchVO= new GamTestPrtFcltyRentFeeSttusByFeeTpVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.

		// 조회 조건
		searchVO = mapper.convertValue(excelParam, GamTestPrtFcltyRentFeeSttusByFeeTpVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		/** List Data */
		List resultList = gamTestPrtFcltyRentFeeSttusByFeeTpService.selectPrtFcltyRentFeeSttusByFeeTpList(searchVO);

    	map.put("resultList", resultList);
    	map.put("header", header);

    	return new ModelAndView("gridExcelView", "gridResultMap", map);
    }
	
}
