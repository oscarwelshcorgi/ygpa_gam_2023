/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.web;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.soc.service.GamSocAgentVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocTotalBsnsSetoffDtlsService;
import egovframework.rte.ygpa.gam.soc.service.GamSocTotalBsnsSetoffDtlsVO;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 13.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 13.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamSocTotalBsnsSetoffDtlsController {
	
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
    
    @Resource(name = "gamSocCmmUseService")
    private GamSocCmmUseService gamSocCmmUseService;
    
    @Resource(name = "gamSocTotalBsnsSetoffDtlsService")
    private GamSocTotalBsnsSetoffDtlsService gamSocTotalBsnsSetoffDtlsService;
    
    
    @RequestMapping(value="/soc/gamSocTotalBsnsSetoffDtls.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
    	//login정보 
    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		List prtAtCdList = gamSocCmmUseService.selectSocPrtAtCodeDetail();
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/soc/GamSocTotalBsnsSetoffDtls";
    }
    
    
    
    /**
     * 총사업비상계처리내역목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSelectSocTotalBsnsSetoffDtlsList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectSocTotalBsnsSetoffDtlsList(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
		long sumTotalAmnt, sumAccFee;
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
		
		//총사업비상계처리내역목록총걧수
		totalCnt = gamSocTotalBsnsSetoffDtlsService.selectSocTotalBsnsSetoffDtlsListTotCnt(searchVO);
		
		//총사업비상계처리내역목록
    	List socTotalBsnsSetoffDtlsList = gamSocTotalBsnsSetoffDtlsService.selectSocTotalBsnsSetoffDtlsList(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", socTotalBsnsSetoffDtlsList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	
	/**
     * 총사업비상계처리내역상세목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSelectSocTotalBsnsSetoffDtlsDetail.do", method=RequestMethod.POST)
	public @ResponseBody Map selectSocTotalBsnsSetoffDtlsDetail(GamSocTotalBsnsSetoffDtlsVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
		long sumPrtTotalAmnt, sumAppTotalAmnt;
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
		

		//총사업비상계처리내역상세목록
    	List socTotalBsnsSetoffDtlsDetail = gamSocTotalBsnsSetoffDtlsService.selectSocTotalBsnsSetoffDtlsDetail(searchVO);
    	
    	//총사업비상계처리내역상세목록 총갯수 및 금액합계
    	GamSocTotalBsnsSetoffDtlsVO socTotalBsnsSetoffDtlsDetailSum = gamSocTotalBsnsSetoffDtlsService.selectSocTotalBsnsSetoffDtlsDetailSum(searchVO);
    	
		totalCnt = socTotalBsnsSetoffDtlsDetailSum.getTotalCnt();
		sumPrtTotalAmnt = socTotalBsnsSetoffDtlsDetailSum.getSumPrtTotalAmnt();
		sumAppTotalAmnt = socTotalBsnsSetoffDtlsDetailSum.getSumAppTotalAmnt();
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("sumPrtTotalAmnt", sumPrtTotalAmnt);
    	map.put("sumAppTotalAmnt", sumAppTotalAmnt);
    	map.put("resultList", socTotalBsnsSetoffDtlsDetail);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	/**
     * 총사업비상계처리내역 목록을 인쇄한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSelectSocTotalBsnsSetoffDtlsListPrint.do")
	public String selectSocTotalBsnsSetoffDtlsListPrint(@RequestParam Map<String, Object> socTotalBsnsSetoffDtlsOpt, ModelMap model) throws Exception {

		int totalCnt, page, firstIndex;
		String frDt,toDt;
    	Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/soc/GamSocTotalBsnsSetoffDtlsPrint";
    	}

		ObjectMapper mapper = new ObjectMapper();
		GamSocTotalBsnsSetoffDtlsVO searchVO;

    	searchVO = mapper.convertValue(socTotalBsnsSetoffDtlsOpt, GamSocTotalBsnsSetoffDtlsVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

    	totalCnt = gamSocTotalBsnsSetoffDtlsService.selectSocTotalBsnsSetoffDtlsListPrintTotCnt(searchVO);
    	List socTotalBsnsSetoffDtlsList = gamSocTotalBsnsSetoffDtlsService.selectSocTotalBsnsSetoffDtlsListPrint(searchVO);
    	
    	frDt = searchVO.getsFrDt();
    	toDt = searchVO.getsToDt();

        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("resultList", socTotalBsnsSetoffDtlsList);
        
        model.addAttribute("frDt", frDt);
        model.addAttribute("toDt", toDt);

		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");

    	return "ygpa/gam/soc/GamSocTotalBsnsSetoffDtlsPrint";
    }

}
