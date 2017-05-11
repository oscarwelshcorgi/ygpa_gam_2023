package egovframework.rte.ygpa.gam.oper.gnrltest.web;

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
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyMtRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyMtRentFeeSttusInqireVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUseSttusInqireVO;

/**
 * @Class Name : GamTestPrtFcltyMtRentFeeSttusInqireController.java
 * @Description : 항만시설월별사용료현황조회 (항만시설/일반부두/항만시설월별사용료현황조회)
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
public class GamTestPrtFcltyMtRentFeeSttusInqireController {
	
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
    
    @Resource(name = "gamTestPrtFcltyMtRentFeeSttusInqireService")
    private GamTestPrtFcltyMtRentFeeSttusInqireService gamTestPrtFcltyMtRentFeeSttusInqireService;
	
    
    /**
     * 항만시설월별사용료현황 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/gnrltest/GamTestPrtFcltyMtRentFeeSttusInqire"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/oper/gnrltest/gamTestPrtFcltyMtRentFeeSttusInqire.do") 
	String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM019"); //항코드 
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);
		model.addAttribute("yearsList", this.getYears());
		model.addAttribute("monthsList", this.getMonths());

    	return "/ygpa/gam/oper/gnrltest/GamTestPrtFcltyMtRentFeeSttusInqire"; 
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
     * 항만시설월별사용료현황 목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrltest/gamSelectPrtFcltyMtRentFeeSttusInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map selectPrtFcltyMtRentFeeSttusInqireList(GamTestPrtFcltyMtRentFeeSttusInqireVO searchVO) throws Exception {

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

		totalCnt = gamTestPrtFcltyMtRentFeeSttusInqireService.selectPrtFcltyMtRentFeeSttusInqireListTotCnt(searchVO);
    	List resultList = gamTestPrtFcltyMtRentFeeSttusInqireService.selectPrtFcltyMtRentFeeSttusInqireList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

        GamTestPrtFcltyMtRentFeeSttusInqireVO resultVO = gamTestPrtFcltyMtRentFeeSttusInqireService.selectPrtFcltyMtRentFeeSttusInqireSum(searchVO);
        
    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", resultList);
    	map.put("totalCount", totalCnt);
    	map.put("searchOption", searchVO);
    	map.put("totSumCnt", resultVO.getTotSumCnt());
    	map.put("totSumFee", resultVO.getTotSumFee());
    	
    	return map;
    }
	
	/**
     * 엑셀파일 다운로드
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrltest/selectPrtFcltyMtRentFeeSttusInqireListExcel.do", method=RequestMethod.POST)
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

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.

        GamTestPrtFcltyMtRentFeeSttusInqireVO searchVO= new GamTestPrtFcltyMtRentFeeSttusInqireVO();
		searchVO = mapper.convertValue(excelParam, GamTestPrtFcltyMtRentFeeSttusInqireVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		/** List Data */
    	List assetRentList = gamTestPrtFcltyMtRentFeeSttusInqireService.selectPrtFcltyMtRentFeeSttusInqireList(searchVO);
    	
    	map.put("resultCode", 0);
    	map.put("resultList", assetRentList);
    	map.put("header", header);

    	return new ModelAndView("gridExcelView", "gridResultMap", map);
    }
}
