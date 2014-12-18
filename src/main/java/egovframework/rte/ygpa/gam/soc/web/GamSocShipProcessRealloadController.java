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
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;
import egovframework.rte.ygpa.gam.soc.service.GamSocShipProcessRealloadService;
import egovframework.rte.ygpa.gam.soc.service.GamSocShipProcessRealloadVO;


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
 *  2014. 10. 15.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamSocShipProcessRealloadController {
	
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
    
    @Resource(name = "gamSocShipProcessRealloadService")
    private GamSocShipProcessRealloadService gamSocShipProcessRealloadService;
    
    
    @SuppressWarnings("rawtypes")
    @RequestMapping(value="/soc/gamSocShipProcessRealload.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		List prtAtCdList = gamSocCmmUseService.selectSocPrtAtCodeDetail();
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/soc/GamSocShipProcessRealload";
    }
    
    
    /**
     * 총사업비상계처리내역목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSocShipProcessRealloadList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectSocShipProcessRealloadList(GamSocShipProcessRealloadVO searchVO) throws Exception {
		
		int totalCnt;
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
		

		//투자비보전 처리실적 리스트
    	List socShipProcessRealloadList = gamSocShipProcessRealloadService.selectSocShipProcessRealloadList(searchVO);
    	
    	//투자비보전 처리실적 리스트 총갯수
    	totalCnt = gamSocShipProcessRealloadService.selectSocShipProcessRealloadListTotCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", socShipProcessRealloadList);
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
    @RequestMapping(value="/soc/gamSocShipProcessRealloadDetail.do", method=RequestMethod.POST)
	public @ResponseBody Map selectSocShipProcessRealloadDetail(GamSocShipProcessRealloadVO searchVO) throws Exception {
		
		int totalCnt;
		long sumExmpAmnt;

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
		

		//투자비보전 처리실적 리스트
    	List socShipProcessRealloadDetail = gamSocShipProcessRealloadService.selectSocShipProcessRealloadDetail(searchVO);
    	
    	//투자비보전 처리실적 리스트 총갯수 및 금액합계
    	GamSocShipProcessRealloadVO socShipProcessRealloadDetailSum = gamSocShipProcessRealloadService.selectSocShipProcessRealloadDetailSum(searchVO);
    	
		totalCnt = socShipProcessRealloadDetailSum.getTotalCnt();
		sumExmpAmnt = socShipProcessRealloadDetailSum.getSumExmpAmnt();
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("sumExmpAmnt", sumExmpAmnt);
    	map.put("resultList", socShipProcessRealloadDetail);
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
    @RequestMapping(value="/soc/gamSelectSocShipProcessRealloadListPrint.do")
	public String selectSocShipProcessRealloadListPrint(@RequestParam Map socShipProcessRealloadOpt, ModelMap model) throws Exception {

		int totalCnt;
		String prtAtCode, prtKorNm, frDt, toDt;
		long sumExmpAmnt;
    	Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/soc/GamSocShipProcessRealloadPrint";
    	}

		ObjectMapper mapper = new ObjectMapper();
		GamSocShipProcessRealloadVO searchVO;

    	searchVO = mapper.convertValue(socShipProcessRealloadOpt, GamSocShipProcessRealloadVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		GamSocShipProcessRealloadVO totalSumCnt = gamSocShipProcessRealloadService.selectSocShipProcessRealloadListPrintTotCnt(searchVO);
    	List socShipProcessRealloadList = gamSocShipProcessRealloadService.selectSocShipProcessRealloadListPrint(searchVO);
    	
    	totalCnt = totalSumCnt.getTotalCnt();
    	sumExmpAmnt = totalSumCnt.getSumExmpAmnt();
    	prtAtCode = searchVO.getsPrtAtCode();
    	prtKorNm = searchVO.getsPrtKorNm();
    	frDt = searchVO.getsFrDt();
    	toDt = searchVO.getsToDt();

        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("sumExmpAmnt", sumExmpAmnt);
        model.addAttribute("prtAtCode", prtAtCode);
        model.addAttribute("prtKorNm", prtKorNm);
        model.addAttribute("frDt", frDt);
        model.addAttribute("toDt", toDt);
        model.addAttribute("resultList", socShipProcessRealloadList);

		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");

    	return "ygpa/gam/soc/GamSocShipProcessRealloadPrint";
    }

}
