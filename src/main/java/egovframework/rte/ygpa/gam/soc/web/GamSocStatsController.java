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
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;
import egovframework.rte.ygpa.gam.soc.service.GamSocStatsService;
import egovframework.rte.ygpa.gam.soc.service.GamSocStatsVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 17.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamSocStatsController {

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
    
    @Resource(name = "gamSocCmmUseService")
    private GamSocCmmUseService gamSocCmmUseService;

    @Resource(name = "gamSocStatsService")
    private GamSocStatsService gamSocStatsService;
    
    @RequestMapping(value="/soc/gamSocStats.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		List prtAtCdList = gamSocCmmUseService.selectSocPrtAtCodeDetail();
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/soc/GamSocStats";
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSelectSocStatsList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectSocStatsList(GamSocStatsVO searchVO) throws Exception {
		
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
		
		List socStatsList = null;
		GamSocStatsVO resultVO = null;
		
		if(searchVO.getsSearchTarget().equals("1")) {
			//업체별 투자비 보전 집계 조회
			socStatsList = gamSocStatsService.selectSocEntprStatsList(searchVO);
			resultVO = gamSocStatsService.selectSocEntprStatsListTotSum(searchVO);
		} else if(searchVO.getsSearchTarget().equals("2")) {
			//웗별 투자비 보전 집계 조회
			socStatsList = gamSocStatsService.selectSocMonthStatsList(searchVO);
			resultVO = gamSocStatsService.selectSocMonthStatsListTotSum(searchVO);
		} else {
			if(searchVO.getsBasis().equals("1")) {
				//업체기준 월별 투자비 보전 집계 내역
				socStatsList = gamSocStatsService.selectSocEntprBasisMonthStatsList(searchVO);
				resultVO = gamSocStatsService.selectSocEntprBasisMonthStatsListTotSum(searchVO);				
			}
			else {
				//월기준 업체별 투자비 보전 집계 내역
				socStatsList = gamSocStatsService.selectSocMonthBasisEntprStatsList(searchVO);
				resultVO = gamSocStatsService.selectSocMonthBasisEntprStatsListTotSum(searchVO);
			}
		}
		
    	totalCnt = (resultVO != null) ? resultVO.getTotCnt() : 0;

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", socStatsList);
    	map.put("searchOption", searchVO);
    	map.put("totExmpAmntSum", (resultVO != null) ? resultVO.getTotExmpAmntSum() : "0");
    	map.put("totExmpAmntPaSum", (resultVO != null) ? resultVO.getTotExmpAmntPaSum() : "0");
    	map.put("totExmpAmntTotSum", (resultVO != null) ? resultVO.getTotExmpAmntTotSum() : "0");
    	
    	return map;
    }    

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSelectSocStatsListPrint.do")
	public String selectSocStatsListPrint(@RequestParam Map<String, Object> socStatsOpt, ModelMap model) throws Exception {
		int totalCnt, page, firstIndex;
    	Map map = new HashMap();
    	String printPageName = null;
   
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/soc/GamSocApplyDtlsPrint";
    	}

		ObjectMapper mapper = new ObjectMapper();
		GamSocStatsVO searchVO;
		
		searchVO = mapper.convertValue(socStatsOpt, GamSocStatsVO.class);
		
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List socStatsList = null;
		GamSocStatsVO resultVO = null;
		
		if(searchVO.getsSearchTarget().equals("1")) {
			//업체별 투자비 보전 집계 조회
			socStatsList = gamSocStatsService.selectSocEntprStatsList(searchVO);
			resultVO = gamSocStatsService.selectSocEntprStatsListTotSum(searchVO);
			printPageName = "/ygpa/gam/soc/GamSocStatsPrint1";
		} else if(searchVO.getsSearchTarget().equals("2")) {
			//웗별 투자비 보전 집계 조회
			socStatsList = gamSocStatsService.selectSocMonthStatsList(searchVO);
			resultVO = gamSocStatsService.selectSocMonthStatsListTotSum(searchVO);
			printPageName = "/ygpa/gam/soc/GamSocStatsPrint2";
		} else {
			if(searchVO.getsBasis().equals("1")) {
				//업체기준 월별 투자비 보전 집계 내역
				socStatsList = gamSocStatsService.selectSocEntprBasisMonthStatsList(searchVO);
				resultVO = gamSocStatsService.selectSocEntprBasisMonthStatsListTotSum(searchVO);
				printPageName = "/ygpa/gam/soc/GamSocStatsPrint3";
			}
			else {
				//월기준 업체별 투자비 보전 집계 내역
				socStatsList = gamSocStatsService.selectSocMonthBasisEntprStatsList(searchVO);
				resultVO = gamSocStatsService.selectSocMonthBasisEntprStatsListTotSum(searchVO);
				printPageName = "/ygpa/gam/soc/GamSocStatsPrint4";
			}
		}
    	
    	model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
    	model.addAttribute("resultList", socStatsList);
    	model.addAttribute("totalCount", (resultVO != null) ? resultVO.getTotCnt() : 0);
    	
    	model.addAttribute("searchFr", searchVO.getsSearchFr());
    	model.addAttribute("searchTo", searchVO.getsSearchTo());
    	model.addAttribute("searchAgentCode", searchVO.getsExmpAgentCode());
    	    	
    	model.addAttribute("totalCount", (resultVO != null) ? resultVO.getTotCnt() : "0");
    	model.addAttribute("totExmpAmntSum", (resultVO != null) ? resultVO.getTotExmpAmntSum() : "0");
    	model.addAttribute("totExmpAmntPaSum", (resultVO != null) ? resultVO.getTotExmpAmntPaSum() : "0");
    	model.addAttribute("totExmpAmntTotSum", (resultVO != null) ? resultVO.getTotExmpAmntTotSum() : "0");
		
		return printPageName;
	}
	
}
