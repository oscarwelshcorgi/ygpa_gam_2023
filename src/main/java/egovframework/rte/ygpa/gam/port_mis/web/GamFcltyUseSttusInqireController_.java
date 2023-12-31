package egovframework.rte.ygpa.gam.port_mis.web;

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
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.port_mis.service.GamFcltyUseSttusInqireService_;
import egovframework.rte.ygpa.gam.port_mis.service.GamFcltyUseSttusInqireVO_;

/**
 * @Class Name : GamFcltyUseSttusInqireController.java
 * @Description : 항만시설사용현황조회(포트미스정보)
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
public class GamFcltyUseSttusInqireController_ {
	
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
    
    @Resource(name = "gamFcltyUseSttusInqireService_")
    private GamFcltyUseSttusInqireService_ gamFcltyUseSttusInqireService_;
	
    
    /**
     * 항만시설납부현황관리 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/port_mis/GamFcltyUseSttusInqire"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/port_mis/gamFcltyUseSttusInqire_.do")
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
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("reqstCdList", reqstCdList);
		model.addAttribute("nticMthCdList", nticMthCdList);
		model.addAttribute("usagePrposCdList", usagePrposCdList);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);
		model.addAttribute("fcltySeCdList", fcltySeCdList);
		model.addAttribute("rcivSeCdList", rcivSeCdList);
		model.addAttribute("quayCdList", quayCdList);
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/port_mis/GamFcltyUseSttusInqire_";
    }
	
	/**
     * 항만시설납부현황관리 목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/port_mis/gamSelectFcltyUseSttusInqireList_.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyUseSttusInqireList(GamFcltyUseSttusInqireVO_ searchVO) throws Exception {

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
		
		//목록
    	totalCnt = gamFcltyUseSttusInqireService_.selectFcltyUseSttusInqireListTotCnt(searchVO);
    	List resultList = gamFcltyUseSttusInqireService_.selectFcltyUseSttusInqireList(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	//자료수, 사용료, 부가세, 고지액
    	//GamFcltyUseSttusInqireVO resultSum = gamFcltyUseSttusInqireService.selectFcltyUseSttusInqireSum(searchVO);
    	GamFcltyUseSttusInqireVO_ resultSum = new GamFcltyUseSttusInqireVO_();
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	/*
    	map.put("sumFee", resultSum.getSumFee());
    	map.put("sumVat", resultSum.getSumVat());
    	map.put("sumNticAmt", resultSum.getSumNticAmt());
    	*/
    	map.put("sumFee", "");
    	map.put("sumVat", "");
    	map.put("sumNticAmt", "");
    	return map;
    }
	
}
