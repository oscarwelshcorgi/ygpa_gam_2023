package egovframework.rte.ygpa.gam.asset.web;

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
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.asset.service.GamAssetTypeValueStsService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetTypeValueStsVO;

/**
 * @Class Name : GamAssetTypeValueStsController.java
 * @Description : 자산종류별자산가치통계조회 
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
public class GamAssetTypeValueStsController {
	
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
    
    @Resource(name = "gamAssetTypeValueStsService")
    private GamAssetTypeValueStsService gamAssetTypeValueStsService;
	
    
    /**
     * 자산종류별자산가치통계조회 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/GamAssetTypeValueSts"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/asset/gamAssetTypeValueSts.do") 
	String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM019"); //항코드 
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		int year = Integer.parseInt(EgovDateUtil.getToday().substring(0,4));
		List yearList = new ArrayList();
		Map yearMap = null;
		
		for( int i = year ; i >= year-10 ; i-- ) {
			yearMap = new HashMap();
			yearMap.put("code", i);
			yearMap.put("codeNm", i+"년");
			
			yearList.add(yearMap);
		}
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("thisYear", year);
		model.addAttribute("yearList", yearList);
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/asset/GamAssetTypeValueSts";
    }
	
	/**
     * 자산종류별자산가치통계조회 목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/gamSelectAssetTypeValueStsList.do", method=RequestMethod.POST)
	@ResponseBody Map selectAssetTypeValueStsList(GamAssetTypeValueStsVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();
    	
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	//searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	//searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
    	totalCnt = gamAssetTypeValueStsService.selectAssetTypeValueStsListTotCnt(searchVO);
    	List assetRentList = gamAssetTypeValueStsService.selectAssetTypeValueStsList(searchVO);
    	
    	GamAssetTypeValueStsVO resultSum = gamAssetTypeValueStsService.selectAssetTypeValueStsSum(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	map.put("totalCount", totalCnt);
    	
    	map.put("sumRevalAmt", resultSum.getSumRevalAmt());
    	map.put("sumThisTermIncreAmt", resultSum.getSumThisTermIncreAmt());
    	map.put("sumBsThisCurAmt", resultSum.getSumBsThisCurAmt());
    	map.put("sumBsPreDeprctnSum", resultSum.getSumBsPreDeprctnSum());
    	map.put("sumBsNoDeprctnBal", resultSum.getSumBsNoDeprctnBal());
    	map.put("sumPreEndAssetBuySum", resultSum.getSumPreEndAssetBuySum());
    	map.put("sumAssetBuyAmt", resultSum.getSumAssetBuyAmt());
    	map.put("sumThisTermDeprctnAmt", resultSum.getSumThisTermDeprctnAmt());
    	map.put("sumCurAmt", resultSum.getSumCurAmt());
    	
    	return map;
    }
	
}
