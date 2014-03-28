package egovframework.rte.ygpa.gam.oper.center.web;

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
    	
    	return "/ygpa/gam/oper/center/GamMarineCenterMonthStsReport";
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

    	//searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	//searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
    	totalCnt = gamMarineCenterMonthStsReportService.selectMarineCenterMonthStsReportListTotCnt(searchVO);
    	List assetRentList = gamMarineCenterMonthStsReportService.selectMarineCenterMonthStsReportList(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	
    	return map;
    }
	
}
