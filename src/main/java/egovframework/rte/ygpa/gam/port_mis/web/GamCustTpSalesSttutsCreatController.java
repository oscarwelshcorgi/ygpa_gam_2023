package egovframework.rte.ygpa.gam.port_mis.web;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
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
import egovframework.rte.ygpa.gam.port_mis.service.GamCustTpSalesSttutsCreatService;
import egovframework.rte.ygpa.gam.port_mis.service.GamCustTpSalesSttutsCreatVO;

/**
 * @Class Name : GamCustTpSalesSttutsCreatController.java
 * @Description : 고객군별통계(포트미스정보) DAO Class
 * @Modification Information
 *
 * @author lsl
 * @since 2014-04-9
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamCustTpSalesSttutsCreatController {
	
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
    
    @Resource(name = "gamCustTpSalesSttutsCreatService")
    private GamCustTpSalesSttutsCreatService gamCustTpSalesSttutsCreatService;
	
    
    /**
     * 고객군별통계관리 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/port_mis/GamCustTpSalesSttutsCreat"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/port_mis/gamCustTpSalesSttutsCreat.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM019"); //항코드 
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		
		
		codeVo.setCodeId("GAM011"); //신청구분코드 
		List reqstCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM008"); //고지방법 코드
		List nticMthCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM007"); //사용 용도 코드 
		List usagePrposCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM024"); //요금종류
		List chrgeKndCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM005"); //시설구분
		List fcltySeCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM025"); //수납구분 
		List rcivSeCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM003"); //부두코드 
		List quayCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		List yearsList = this.getYears(); // 조회연도
		List monthsList = this.getMonths(); // 조회월
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("reqstCdList", reqstCdList);
		model.addAttribute("nticMthCdList", nticMthCdList);
		model.addAttribute("usagePrposCdList", usagePrposCdList);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);
		model.addAttribute("fcltySeCdList", fcltySeCdList);
		model.addAttribute("rcivSeCdList", rcivSeCdList);
		model.addAttribute("quayCdList", quayCdList);
		model.addAttribute("windowId", windowId);
		
		model.addAttribute("yearsList", yearsList);
		model.addAttribute("monthsList", monthsList);
    	
    	return "/ygpa/gam/port_mis/GamCustTpSalesSttutsCreat";
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
     * 매출액통계생성 목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/port_mis/selectgamCustTpSalesSttutsCreatList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectGamCustTpSalesSttutsCreatList(GamCustTpSalesSttutsCreatVO searchVO, HttpServletRequest request) throws Exception {

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
		

		String grStartMn = ServletRequestUtils.getStringParameter(request, "grStartMn", "");
		if(grStartMn != null){
			Integer monFrom = Integer.parseInt(grStartMn);
			if(monFrom < 10){
				grStartMn = "0" + String.valueOf(monFrom);
			}else{
				grStartMn = String.valueOf(monFrom);
			}
			
			searchVO.setGrStartMn(grStartMn);
		}
		
		//목록
    	totalCnt = gamCustTpSalesSttutsCreatService.selectPortMisCostvalStatsListTotCnt(searchVO);
    	List resultList = gamCustTpSalesSttutsCreatService.selectPortMisCostvalStatsList(searchVO);

    	
//    	System.out.print("test : ##################" + resultList);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	//자료수, 사용료, 부가세, 고지액
    	//GamCustTpSalesSttutsCreatVO resultSum = gamCustTpSalesSttutsCreatService.selectFcltyUseSttusInqireSum(searchVO);
        GamCustTpSalesSttutsCreatVO resultSum = new GamCustTpSalesSttutsCreatVO();
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
  
    	return map;
    }
	
	
	/**
     * 업체별매출액통계 목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/port_mis/selectErpStatisticsList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectErpStatisticsList(GamCustTpSalesSttutsCreatVO searchVO, HttpServletRequest request) throws Exception {

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
		
		String eGrStartMn = ServletRequestUtils.getStringParameter(request, "eGrStartMn", "");
		if(eGrStartMn != null && !eGrStartMn.equals("")){
			Integer monFrom = Integer.parseInt(eGrStartMn);
			if(monFrom < 10){
				eGrStartMn = "0" + String.valueOf(monFrom);
			}else{
				eGrStartMn = String.valueOf(monFrom);
			}
			
			searchVO.seteGrStartMn(eGrStartMn);
		}
		String eGrEndMn = ServletRequestUtils.getStringParameter(request, "eGrEndMn", "");

		if(eGrEndMn != null && !eGrEndMn.equals("")){
			Integer monTo = Integer.parseInt(eGrEndMn);
			if(monTo < 10){
				eGrEndMn = "0" + String.valueOf(monTo);
			}else{
				eGrEndMn = String.valueOf(monTo);
			}
			
			searchVO.seteGrEndMn(eGrEndMn);
		}
		
		//목록
    	totalCnt = gamCustTpSalesSttutsCreatService.selectCustAgentSalesSttutsListTotCnt(searchVO);
    	List resultList = gamCustTpSalesSttutsCreatService.selectCustAgentSalesSttutsList(searchVO);

    	
//    	System.out.print("test : ##################" + resultList);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	//자료수, 사용료, 부가세, 고지액
    	//GamCustTpSalesSttutsCreatVO resultSum = gamCustTpSalesSttutsCreatService.selectFcltyUseSttusInqireSum(searchVO);
        GamCustTpSalesSttutsCreatVO resultSum = new GamCustTpSalesSttutsCreatVO();
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
  
    	return map;
    }
	
	
	/**
     * 선사별매출액통계 목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/port_mis/selectShipErpStatisticsList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectShipErpStatisticsList(GamCustTpSalesSttutsCreatVO searchVO, HttpServletRequest request) throws Exception {

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
		
		String sEgrStartMn = ServletRequestUtils.getStringParameter(request, "sEgrStartMn", "");
		if(sEgrStartMn != null && !sEgrStartMn.equals("")){
			Integer monFrom = Integer.parseInt(sEgrStartMn);
			if(monFrom < 10){
				sEgrStartMn = "0" + String.valueOf(monFrom);
			}else{
				sEgrStartMn = String.valueOf(monFrom);
			}
			
			searchVO.setsEgrStartMn(sEgrStartMn);
		}
		String sEgrEndMn = ServletRequestUtils.getStringParameter(request, "sEgrEndMn");

		if(sEgrEndMn != null && !sEgrEndMn.equals("")){
			Integer monTo = Integer.parseInt(sEgrEndMn);
			if(monTo < 10){
				sEgrEndMn = "0" + String.valueOf(monTo);
			}else{
				sEgrEndMn = String.valueOf(monTo);
			}
			
			searchVO.setsEgrEndMn(sEgrEndMn);
		}
		
		
		//목록
    	totalCnt = gamCustTpSalesSttutsCreatService.selectCustTpSalesSttutsListTotCnt(searchVO);
    	List resultList = gamCustTpSalesSttutsCreatService.selectCustTpSalesSttutsList(searchVO);

    	
    	System.out.print("test : ##################" + resultList);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	//자료수, 사용료, 부가세, 고지액
    	//GamCustTpSalesSttutsCreatVO resultSum = gamCustTpSalesSttutsCreatService.selectFcltyUseSttusInqireSum(searchVO);
        GamCustTpSalesSttutsCreatVO resultSum = new GamCustTpSalesSttutsCreatVO();
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
  
    	return map;
    }
	
}
