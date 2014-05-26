package egovframework.rte.ygpa.gam.asset.rent.web;

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
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamRecvTpRecvStsInqireService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamRecvTpRecvStsInqireVO;

/**
 * @Class Name : GamRecvTpRecvStsInqireController.java
 * @Description : 수입종류별수입현황조회
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamRecvTpRecvStsInqireController {
	
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
    
    @Resource(name = "gamRecvTpRecvStsInqireService")
    private GamRecvTpRecvStsInqireService gamRecvTpRecvStsInqireService;
	
    
    /**
     * 수입종류별수입현황 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/GamRecvTpRecvStsInqire"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/asset/rent/gamRecvTpRecvStsInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/asset/rent/GamRecvTpRecvStsInqire"; 
    }
	
	/**
     * 수입종류별수입현황 목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/rent/gamSelectRecvTpRecvStsInqireList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectRecvTpRecvStsInqireList(GamRecvTpRecvStsInqireVO searchVO) throws Exception {

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
		
    	totalCnt = gamRecvTpRecvStsInqireService.selectCmpyRecvStsInqireListTotCnt(searchVO);
    	List assetRentList = gamRecvTpRecvStsInqireService.selectRecvTpRecvStsInqireList(searchVO);
    	
    	GamRecvTpRecvStsInqireVO resultSum = gamRecvTpRecvStsInqireService.selectCmpyRecvStsInqireSum(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	
    	map.put("totSumCnt", resultSum.getTotSumCnt());
    	map.put("totSumNticAmt", resultSum.getTotSumNticAmt());
    	map.put("totSumRcvdAmt", resultSum.getTotSumRcvdAmt());
    	map.put("totSumNotRcvdAmt", resultSum.getTotSumNotRcvdAmt());
    	return map;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/rent/gamSelectRecvTpRecvStsInqireListExcel.do", method=RequestMethod.POST)
	public @ResponseBody ModelAndView selectRecvTpRecvStsInqireListExcel(@RequestParam Map<String, Object> excelParam) throws Exception {
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

		GamRecvTpRecvStsInqireVO searchVO= new GamRecvTpRecvStsInqireVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.

		// 조회 조건
		searchVO = mapper.convertValue(excelParam, GamRecvTpRecvStsInqireVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		/** List Data */
    	List assetRentList = gamRecvTpRecvStsInqireService.selectRecvTpRecvStsInqireList(searchVO);

    	map.put("resultList", assetRentList);
    	map.put("header", header);

    	return new ModelAndView("gridExcelView", "gridResultMap", map);
    }
}
