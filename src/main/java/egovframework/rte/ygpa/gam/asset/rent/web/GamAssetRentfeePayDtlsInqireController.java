package egovframework.rte.ygpa.gam.asset.rent.web;

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
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentfeePayDtlsInqireService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentfeePayDtlsInqireVO;

/**
 * @Class Name : GamAssetRentfeePayDtlsInqireController.java
 * @Description : 사용료납부내역조회
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
public class GamAssetRentfeePayDtlsInqireController {
	
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
    
    @Resource(name = "gamAssetRentfeePayDtlsInqireService")
    private GamAssetRentfeePayDtlsInqireService gamAssetRentfeePayDtlsInqireService;
	
    
    /**
     * 사용료납부내역조회 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/gnrl/GamAssetRentfeePayDtlsInqire"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/asset/rent/gamAssetRentfeePayDtlsInqire.do") 
	String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM019"); //항코드 
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/asset/rent/GamAssetRentfeePayDtlsInqire"; 
    }
	
	/**
     * 사용료납부내역조회 목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/rent/gamSelectAssetRentfeePayDtlsInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map selectAssetRentfeePayDtlsInqireList(GamAssetRentfeePayDtlsInqireVO searchVO) throws Exception {

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
		
    	totalCnt = gamAssetRentfeePayDtlsInqireService.selectAssetRentfeePayDtlsInqireListTotCnt(searchVO);
    	List assetRentList = gamAssetRentfeePayDtlsInqireService.selectAssetRentfeePayDtlsInqireList(searchVO);
    	
    	//자료수 고지금액 수납금액합계
    	GamAssetRentfeePayDtlsInqireVO resultSum = gamAssetRentfeePayDtlsInqireService.selectAssetRentfeePayDtlsInqireSum(searchVO);
    	
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	
    	map.put("totSumCnt", resultSum.getTotSumCnt());
    	map.put("totSumNticAmt", resultSum.getTotSumNticAmt());
    	map.put("totSumRcvdAmt", resultSum.getTotSumRcvdAmt());
    	map.put("totSumNotRcvdAmt", resultSum.getTotSumNotRcvdAmt());
    	
    	return map;
    }
	
}
