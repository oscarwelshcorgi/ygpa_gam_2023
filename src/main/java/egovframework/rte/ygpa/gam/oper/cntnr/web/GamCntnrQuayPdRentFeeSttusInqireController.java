package egovframework.rte.ygpa.gam.oper.cntnr.web;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayPdRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayPdRentFeeSttusInqireVO;

/**
 * @Class Name : GamCntnrQuayPdRentFeeSttusInqireController.java
 * @Description : 컨테이너부두임대기간별사용료현황조회
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamCntnrQuayPdRentFeeSttusInqireController {
	
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
    
    @Resource(name = "gamCntnrQuayPdRentFeeSttusInqireService")
    private GamCntnrQuayPdRentFeeSttusInqireService gamCntnrQuayPdRentFeeSttusInqireService;
	
    
    /**
     * 컨테이너부두임대기간별사용료현황 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/cntnr/GamCntnrQuayPdRentFeeSttusInqire"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/oper/cntnr/gamCntnrQuayPdRentFeeSttusInqire.do") 
	String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM019"); //항코드 
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		List yearsList = this.getYears(); // 조회연도
		List monthsList = this.getMonths(); // 조회월


		model.addAttribute("yearsList", yearsList);
		model.addAttribute("monthsList", monthsList);
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/oper/cntnr/GamCntnrQuayPdRentFeeSttusInqire"; 
    }
	
	/**
     * 조회기간 연도를 가져온다
     *
     */
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
   			
   			result.add(String.valueOf(i));
   		}

   		return result;
   	}
	
	/**
     * 컨테이너부두임대월별사용료현황 목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/cntnr/gamSelectCntnrQuayPdRentFeeSttusInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map selectCntnrQuayPdRentFeeSttusInqireList(GamCntnrQuayPdRentFeeSttusInqireVO searchVO) throws Exception {

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
		
		
		//목록
		totalCnt = gamCntnrQuayPdRentFeeSttusInqireService.selectCntnrQuayPdRentFeeSttusInqireListTotCnt(searchVO);
    	List resultList = gamCntnrQuayPdRentFeeSttusInqireService.selectCntnrQuayPdRentFeeSttusInqireList(searchVO);
    	
    	
    	//사용료합계, 감면사용료합계 
    	GamCntnrQuayPdRentFeeSttusInqireVO resultSum = gamCntnrQuayPdRentFeeSttusInqireService.selectCntnrQuayPdRentFeeSttusInqireSum(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	
        map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	
    	map.put("sumTotalFeeSum", resultSum.getSumTotalFeeSum());
    	map.put("sumTotalRdcxptFeeSumSum", resultSum.getSumTotalRdcxptFeeSumSum());
    	map.put("dpTotCnt", resultSum.getDpTotCnt());
    	
    	return map;
    }
	
}
