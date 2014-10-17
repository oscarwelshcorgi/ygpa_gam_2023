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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocShipProcessSetoffLgerService;
import egovframework.rte.ygpa.gam.soc.service.GamSocShipProcessSetoffLgerVO;



/**
 * 
 * @author HNJ
 * @since 2014. 10. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 17.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamSocShipProcessSetoffLgerController {
	
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
    
    @Resource(name = "gamSocShipProcessSetoffLgerService")
    private GamSocShipProcessSetoffLgerService gamSocShipProcessSetoffLgerService;


    @RequestMapping(value="/soc/gamSocShipProcessSetoffLger.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
    	//login정보 
    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    	GamSocCmmUseVO codeVo = new GamSocCmmUseVO();
		
		codeVo.setCodeId("GAM019"); //항코드 
		
		List prtAtCdList = gamSocCmmUseService.selectSocPrtAtCodeDetail();
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/soc/GamSocShipProcessSetoffLger";
    }
    
    
    /**
     * 투자비보전(선석)상계처리대장 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSocShipProcessSetoffLgerList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectSocShipProcessSetoffLgerList(GamSocShipProcessSetoffLgerVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
		long sumExmpAmnt;
		String feeTp, feeTpNm;
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
		

		//투자비보전(선석)상계처리대장 리스트
    	List socShipProcessSetoffLgerList = gamSocShipProcessSetoffLgerService.selectSocShipProcessSetoffLgerList(searchVO);
    	
    	//투자비보전(선석)상계처리대장 리스트 총갯수
    	totalCnt = gamSocShipProcessSetoffLgerService.selectSocShipProcessSetoffLgerListTotCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", socShipProcessSetoffLgerList);
    	map.put("searchOption", searchVO);

    	return map;
    }
    
    	
	/**
     * 투자비보전(선석)상계처리대장 상세목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSocShipProcessSetoffLgerDetail.do", method=RequestMethod.POST)
	public @ResponseBody Map selectSocShipProcessSetoffLgerDetail(GamSocShipProcessSetoffLgerVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
		long sumR1Fare,sumR2Fare,sumR3Fare,sumR6Fare,sumRFare;
		long sumR1FarePa,sumR2FarePa,sumR3FarePa,sumR6FarePa,sumRFarePa;
		long sumR1All,sumR2All,sumR3All,sumR6All,sumRAll;
		
		String feeTp, feeTpNm;
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
		

		//투자비보전(선석)상계처리대장 상세내역 리스트
    	List socShipProcessSetoffLgerDetail = gamSocShipProcessSetoffLgerService.selectSocShipProcessSetoffLgerDetail(searchVO);
    	
    	//투자비보전(선석)상계처리대장 상세내역 리스트 총갯수 및 금액합계
    	GamSocShipProcessSetoffLgerVO socShipProcessSetoffLgerDetailSum = gamSocShipProcessSetoffLgerService.selectSocShipProcessSetoffLgerDetailSum(searchVO);
    	
		totalCnt = socShipProcessSetoffLgerDetailSum.getTotalCnt();
		sumR1Fare = socShipProcessSetoffLgerDetailSum.getSumR1Fare();
		sumR2Fare = socShipProcessSetoffLgerDetailSum.getSumR2Fare();
		sumR3Fare = socShipProcessSetoffLgerDetailSum.getSumR3Fare();
		sumR6Fare = socShipProcessSetoffLgerDetailSum.getSumR6Fare();
		sumRFare = socShipProcessSetoffLgerDetailSum.getSumRFare();
		
		sumR1FarePa = socShipProcessSetoffLgerDetailSum.getSumR1FarePa();
		sumR2FarePa = socShipProcessSetoffLgerDetailSum.getSumR2FarePa();
		sumR3FarePa = socShipProcessSetoffLgerDetailSum.getSumR3FarePa();
		sumR6FarePa = socShipProcessSetoffLgerDetailSum.getSumR6FarePa();
		sumRFarePa = socShipProcessSetoffLgerDetailSum.getSumRFarePa();
		
		sumR1All = socShipProcessSetoffLgerDetailSum.getSumR1All();
		sumR2All = socShipProcessSetoffLgerDetailSum.getSumR2All();
		sumR3All = socShipProcessSetoffLgerDetailSum.getSumR3All();
		sumR6All = socShipProcessSetoffLgerDetailSum.getSumR6All();
		sumRAll = socShipProcessSetoffLgerDetailSum.getSumRAll();
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("sumR1Fare", sumR1Fare);
    	map.put("sumR2Fare", sumR2Fare);
    	map.put("sumR3Fare", sumR3Fare);
    	map.put("sumR6Fare", sumR6Fare);
    	map.put("sumRFare", sumRFare);
    	
    	map.put("sumR1FarePa", sumR1FarePa);
    	map.put("sumR2FarePa", sumR2FarePa);
    	map.put("sumR3FarePa", sumR3FarePa);
    	map.put("sumR6FarePa", sumR6FarePa);
    	map.put("sumRFarePa", sumRFarePa);
    	
    	map.put("sumR1All", sumR1All);
    	map.put("sumR2All", sumR2All);
    	map.put("sumR3All", sumR3All);
    	map.put("sumR6All", sumR6All);
    	map.put("sumRAll", sumRAll);

    	map.put("resultList", socShipProcessSetoffLgerDetail);
    	map.put("searchOption", searchVO);

    	return map;
    }

}
