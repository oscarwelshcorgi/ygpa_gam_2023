/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtLgerHistService;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtLgerHistVO;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltyMngtService;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyManageVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyMngtService;
import egovframework.rte.ygpa.gam.soc.service.GamSocAgentService;
import egovframework.rte.ygpa.gam.soc.service.GamSocTotalBsnsSetoffDtlsVO;

/**
 *
 * @author HNJ
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltyCtrtLgerHistController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "gamFcltyCtrtLgerHistService")
    private GamFcltyCtrtLgerHistService gamFcltyCtrtLgerHistService;


	@RequestMapping(value="/ctrt/gamFcltyCtrtLgerHist.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

    	model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/ctrt/GamFcltyCtrtLgerHist";
    }

	/**
     * 계약정보목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/gamSelectFcltyCtrtLgerHistList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtLgerHistList(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
		long sumPlanAmt, sumPrmtAmt, sumScsbidAmt, sumBaseAmt;
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

		
		//계약대장목록
    	List fcltyCtrtLgerHistList = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistList(searchVO);
    	
    	//계약대장목록 총갯수 및 금액합계
		GamFcltyCtrtLgerHistVO fcltyCtrtLgerHistInfoSum = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistInfoSum(searchVO);
    	
		totalCnt = fcltyCtrtLgerHistInfoSum.getTotalCnt();
		sumPlanAmt = fcltyCtrtLgerHistInfoSum.getSumPlanAmt();
		sumPrmtAmt = fcltyCtrtLgerHistInfoSum.getSumPrmtAmt();
		sumScsbidAmt = fcltyCtrtLgerHistInfoSum.getSumScsbidAmt();
		sumBaseAmt = fcltyCtrtLgerHistInfoSum.getSumBaseAmt();
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("sumPlanAmt", sumPlanAmt);
    	map.put("sumPrmtAmt", sumPrmtAmt);
    	map.put("sumScsbidAmt", sumScsbidAmt);
    	map.put("sumBaseAmt", sumBaseAmt);
    	map.put("resultList", fcltyCtrtLgerHistList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
     * 계약공동도급목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/gamSelectFcltyCtrtJoinContrFList.do")
	public @ResponseBody Map selectFcltyCtrtJoinContrFList(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		
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

		
		//계약공동도급목록
    	List fcltyCtrtJoinContrF = gamFcltyCtrtLgerHistService.selectFcltyCtrtJoinContrFList(searchVO);
    	
    	//계약공동도급목록 총갯수
		totalCnt = gamFcltyCtrtLgerHistService.selectFcltyCtrtJoinContrFTotalCnt(searchVO);
 
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", fcltyCtrtJoinContrF);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
     * 계약공동도급상세내역을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/gamSelectFcltyCtrtJoinContrFDetail.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtJoinContrFDetail(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
		//계약대장목록
    	GamFcltyCtrtLgerHistVO fcltyCtrtJoinContrFDetail = gamFcltyCtrtLgerHistService.selectFcltyCtrtJoinContrFDetail(searchVO);
        
    	map.put("resultCode", 0);	// return ok
    	map.put("resultDetail", fcltyCtrtJoinContrFDetail);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
     * 계약변경목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/gamSelectFcltyCtrtChangeFList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtChangeFList(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
		long sumChangeCtrtAmt, sumLastCtrtAmt;
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

		
		//계약변경목록
    	List fcltyCtrtChangeFList = gamFcltyCtrtLgerHistService.selectFcltyCtrtChangeFList(searchVO);
    	
    	//계약변경목록 총갯수 및 금액합계
		GamFcltyCtrtLgerHistVO fcltyCtrtChangeFListSum = gamFcltyCtrtLgerHistService.selectFcltyCtrtChangeFListSum(searchVO);
    	
		totalCnt = fcltyCtrtChangeFListSum.getTotalCnt();
		sumChangeCtrtAmt = fcltyCtrtChangeFListSum.getSumChangeCtrtAmt();
		sumLastCtrtAmt = fcltyCtrtChangeFListSum.getSumLastCtrtAmt();
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("sumChangeCtrtAmt", sumChangeCtrtAmt);
    	map.put("sumLastCtrtAmt", sumLastCtrtAmt);
    	map.put("resultList", fcltyCtrtChangeFList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	
	/**
     * 계약대금지급목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/gamSelectFcltyCtrtMoneyPymntFList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtMoneyPymntFList(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
		long sumThisTimeEstbAmt, sumDepositExcclcAmt, sumPymntAmt, sumPymntAggrAmt;
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

		
		//계약대금지급목록
    	List fcltyCtrtMoneyPymntFList = gamFcltyCtrtLgerHistService.selectFcltyCtrtMoneyPymntFList(searchVO);
    	
    	//계약대금지급목록 총갯수 및 금액합계
		GamFcltyCtrtLgerHistVO fcltyCtrtMoneyPymntFListSum = gamFcltyCtrtLgerHistService.selectFcltyCtrtMoneyPymntFListSum(searchVO);
    	
		totalCnt = fcltyCtrtMoneyPymntFListSum.getTotalCnt();
		sumThisTimeEstbAmt = fcltyCtrtMoneyPymntFListSum.getSumThisTimeEstbAmt();
		sumDepositExcclcAmt = fcltyCtrtMoneyPymntFListSum.getSumDepositExcclcAmt();
		sumPymntAmt = fcltyCtrtMoneyPymntFListSum.getSumPymntAmt();
		sumPymntAggrAmt = fcltyCtrtMoneyPymntFListSum.getSumPymntAggrAmt();
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("sumThisTimeEstbAmt", sumThisTimeEstbAmt);
    	map.put("sumDepositExcclcAmt", sumDepositExcclcAmt);
    	map.put("sumPymntAmt", sumPymntAmt);
    	map.put("sumPymntAggrAmt", sumPymntAggrAmt);
    	map.put("resultList", fcltyCtrtMoneyPymntFList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
     * 계약이행이월목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/gamSelectFcltyCtrtFulfillCaryFwdFList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtFulfillCaryFwdFList(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
		long sumFulfillAmt, sumCaryFwdAmt;
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

		
		//계약이행이월목록
    	List fcltyCtrtFulfillCaryFwdFList = gamFcltyCtrtLgerHistService.selectFcltyCtrtFulfillCaryFwdFList(searchVO);
    	
    	//계약이행이월목록 총갯수 및 금액합계
		GamFcltyCtrtLgerHistVO fcltyCtrtFulfillCaryFwdFListSum = gamFcltyCtrtLgerHistService.selectFcltyCtrtFulfillCaryFwdFListSum(searchVO);
    	
		totalCnt = fcltyCtrtFulfillCaryFwdFListSum.getTotalCnt();
		sumFulfillAmt = fcltyCtrtFulfillCaryFwdFListSum.getSumThisTimeEstbAmt();
		sumCaryFwdAmt = fcltyCtrtFulfillCaryFwdFListSum.getSumDepositExcclcAmt();

    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("sumFulfillAmt", sumFulfillAmt);
    	map.put("sumCaryFwdAmt", sumCaryFwdAmt);
    	map.put("resultList", fcltyCtrtFulfillCaryFwdFList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	
	/**
     * 시설물 계약대장을 인쇄한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/gamSelectFcltyCtrtLgerHistPrint.do")
	public String selectFcltyCtrtLgerHistDetailPrint(@RequestParam Map<String, Object> fcltyCtrtLgerHistOpt, ModelMap model) throws Exception {

		int joinTotalCnt, changeTotalCnt, moneyTotalCnt, page, firstIndex;
    	Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/ctrt/GamFcltyCtrtLgerHistPrint";
    	}

		ObjectMapper mapper = new ObjectMapper();
		
		GamFcltyCtrtLgerHistVO searchVO;
    	searchVO = mapper.convertValue(fcltyCtrtLgerHistOpt, GamFcltyCtrtLgerHistVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);
		
		//계약대장상세내역
    	GamFcltyCtrtLgerHistVO fcltyCtrtLgerHistDetail = gamFcltyCtrtLgerHistService.selectFcltyCtrtLgerHistDetail(searchVO);

    	//계약공동도급목록
    	List fcltyCtrtJoinContrF = gamFcltyCtrtLgerHistService.selectFcltyCtrtJoinContrFList(searchVO);
    	//계약공동도급목록 총갯수
		//joinTotalCnt = gamFcltyCtrtLgerHistService.selectFcltyCtrtJoinContrFTotalCnt(searchVO);
		
		
		//계약변경목록
    	List fcltyCtrtChangeFList = gamFcltyCtrtLgerHistService.selectFcltyCtrtChangeFList(searchVO);
    	//계약변경목록 총갯수 및 금액합계
		//GamFcltyCtrtLgerHistVO fcltyCtrtChangeFListSum = gamFcltyCtrtLgerHistService.selectFcltyCtrtChangeFListSum(searchVO);
		//changeTotalCnt = fcltyCtrtChangeFListSum.getTotalCnt();
		
		
		//계약대금지급목록
    	List fcltyCtrtMoneyPymntFList = gamFcltyCtrtLgerHistService.selectFcltyCtrtMoneyPymntFList(searchVO);
    	//계약대금지급목록 총갯수 및 금액합계
		//GamFcltyCtrtLgerHistVO fcltyCtrtMoneyPymntFListSum = gamFcltyCtrtLgerHistService.selectFcltyCtrtMoneyPymntFListSum(searchVO);
		//moneyTotalCnt = fcltyCtrtMoneyPymntFListSum.getTotalCnt();
    	
    	
    	//계약이행이월목록
    	List fcltyCtrtFulfillCaryFwdFList = gamFcltyCtrtLgerHistService.selectFcltyCtrtFulfillCaryFwdFList(searchVO);
    	

        model.addAttribute("fcltyCtrtLgerHistDetail", fcltyCtrtLgerHistDetail);
        model.addAttribute("fcltyCtrtJoinContrFList", fcltyCtrtJoinContrF);
        model.addAttribute("fcltyCtrtChangeFList", fcltyCtrtChangeFList);
        model.addAttribute("fcltyCtrtMoneyPymntFList", fcltyCtrtMoneyPymntFList);
        model.addAttribute("fcltyCtrtFulfillCaryFwdFList", fcltyCtrtFulfillCaryFwdFList);
      
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");

    	return "ygpa/gam/ctrt/GamFcltyCtrtLgerHistPrint";
    }

}