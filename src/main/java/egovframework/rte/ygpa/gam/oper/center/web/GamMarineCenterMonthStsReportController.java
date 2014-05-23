package egovframework.rte.ygpa.gam.oper.center.web;

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
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterMonthStsReportService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterMonthStsReportVO;

/**
 * @Class Name : GamMarineCenterMonthStsReportController.java
 * @Description : 마린센터월별사용료현황조회 
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamMarineCenterMonthStsReportController {
	
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
    
    @Resource(name = "gamMarineCenterMonthStsReportService")
    private GamMarineCenterMonthStsReportService gamMarineCenterMonthStsReportService;
	
    
    /**
     * 마린센터월별사용료현황 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/gnrl/GamMarineCenterMonthStsReport"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/oper/center/gamMarineCenterMonthStsReport.do") 
	String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM019"); //항코드 
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);
		model.addAttribute("yearsList", this.getYears());
		model.addAttribute("monthsList", this.getMonths());
    	
    	return "/ygpa/gam/oper/center/GamMarineCenterMonthStsReport";
    }
	public List getYears(){
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int currentYear = cal.get(cal.YEAR);
		List result = new ArrayList();
   		for (int i = 2000; i <= currentYear; i++) {
   			result.add(String.valueOf(i));
   		}
   		return result;
   	}
	
	/**
     * 조회기간 월을 가져온다
     *
     */
	public List getMonths(){
		List result = new ArrayList();
   		for (int i=1; i<=12; i++) {
   			result.add(new Integer(i));
   		}
   		return result;
   	}

	
	/**
     * 마린센터월별사용료현황 목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/center/gamSelectMarineCenterMonthStsReportList.do", method=RequestMethod.POST)
	@ResponseBody Map selectMarineCenterMonthStsReportList(GamMarineCenterMonthStsReportVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
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
		
    	totalCnt = gamMarineCenterMonthStsReportService.selectMarineCenterMonthStsReportListTotCnt(searchVO);
    	List resultList = gamMarineCenterMonthStsReportService.selectMarineCenterMonthStsReportList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

        GamMarineCenterMonthStsReportVO resultVO = gamMarineCenterMonthStsReportService.selectMarineCenterMonthStsReportSum(searchVO);
        
    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", resultList);
    	map.put("totalCount", totalCnt);
    	map.put("searchOption", searchVO);
    	map.put("totSumCnt", resultVO.getTotSumCnt());
    	map.put("totSumFee", resultVO.getTotSumFee());
    	return map;
    }
	
}
