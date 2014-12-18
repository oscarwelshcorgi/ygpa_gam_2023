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
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocShipProcessSetoffLgerVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocShipProcessSetoffLgerService;



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

    @SuppressWarnings("rawtypes")
    @RequestMapping(value="/soc/gamSocShipProcessSetoffLger.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

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
		
		int totalCnt;
		long sumR1Fare,sumR2Fare,sumR3Fare,sumR6Fare,sumRFare;
		long sumR1FarePa,sumR2FarePa,sumR3FarePa,sumR6FarePa,sumRFarePa;
		long sumR1All,sumR2All,sumR3All,sumR6All,sumRAll;
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
    	
    	//투자비보전(선석)상계처리대장 상세내역 리스트 총갯수 및 금액합계
    	GamSocShipProcessSetoffLgerVO socShipProcessSetoffLgerListSum = gamSocShipProcessSetoffLgerService.selectSocShipProcessSetoffLgerListSum(searchVO);
    	
		totalCnt = socShipProcessSetoffLgerListSum.getTotalCnt();
		sumR1Fare = socShipProcessSetoffLgerListSum.getSumR1Fare();
		sumR2Fare = socShipProcessSetoffLgerListSum.getSumR2Fare();
		sumR3Fare = socShipProcessSetoffLgerListSum.getSumR3Fare();
		sumR6Fare = socShipProcessSetoffLgerListSum.getSumR6Fare();
		sumRFare = socShipProcessSetoffLgerListSum.getSumRFare();
		
		sumR1FarePa = socShipProcessSetoffLgerListSum.getSumR1FarePa();
		sumR2FarePa = socShipProcessSetoffLgerListSum.getSumR2FarePa();
		sumR3FarePa = socShipProcessSetoffLgerListSum.getSumR3FarePa();
		sumR6FarePa = socShipProcessSetoffLgerListSum.getSumR6FarePa();
		sumRFarePa = socShipProcessSetoffLgerListSum.getSumRFarePa();
		
		sumR1All = socShipProcessSetoffLgerListSum.getSumR1All();
		sumR2All = socShipProcessSetoffLgerListSum.getSumR2All();
		sumR3All = socShipProcessSetoffLgerListSum.getSumR3All();
		sumR6All = socShipProcessSetoffLgerListSum.getSumR6All();
		sumRAll = socShipProcessSetoffLgerListSum.getSumRAll();
    	
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

    	map.put("resultList", socShipProcessSetoffLgerList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	
	/**
     * 투자비보전(선석)상계처리대장 목록을 인쇄한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSelectSocShipProcessSetoffLgerListPrint.do")
	public String selectSocShipProcessSetoffLgerListPrint(@RequestParam Map<String, Object> socShipProcessSetoffLgerOpt, ModelMap model) throws Exception {

		int totalCnt;
		String ioDt, ioDt2, dtFr, dtTo, exmpAcc;

    	Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/soc/GamSocShipProcessSetoffLgerPrint";
    	}

		ObjectMapper mapper = new ObjectMapper();
		GamSocShipProcessSetoffLgerVO searchVO;

    	searchVO = mapper.convertValue(socShipProcessSetoffLgerOpt, GamSocShipProcessSetoffLgerVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		GamSocShipProcessSetoffLgerVO totalSumCnt = gamSocShipProcessSetoffLgerService.selectSocShipProcessSetoffLgerListSum(searchVO);
    	List socShipProcessSetoffLgerList = gamSocShipProcessSetoffLgerService.selectSocShipProcessSetoffLgerList(searchVO);
    	
    	totalCnt = totalSumCnt.getTotalCnt();
    	dtFr = searchVO.getsDtFr();
    	dtTo = searchVO.getsDtTo();
    	ioDt = searchVO.getsIoDt();
    	ioDt2 = searchVO.getsIoDt2();
    	exmpAcc = searchVO.getsExmpAcc();

        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("dtFr", dtFr);
        model.addAttribute("dtTo", dtTo);
        model.addAttribute("ioDt", ioDt);
        model.addAttribute("ioDt2", ioDt2);
        model.addAttribute("exmpAcc", exmpAcc);
       
        model.addAttribute("resultList", socShipProcessSetoffLgerList);

		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");

    	return "ygpa/gam/soc/GamSocShipProcessSetoffLgerPrint";
    }
    
    	
	

}
