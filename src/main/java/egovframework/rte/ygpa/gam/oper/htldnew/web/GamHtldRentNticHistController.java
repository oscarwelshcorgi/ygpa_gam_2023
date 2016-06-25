/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.web;

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
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticDefaultVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticHistService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticHistVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticInfoVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticReportService;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 12.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamHtldRentNticHistController {
	
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

    @Resource(name="gamHtldRentNticHistService")
    private GamHtldRentNticHistService gamHtldRentNticHistService;

    @Resource(name="gamHtldRentNticReportService")
    private GamHtldRentNticReportService gamHtldRentNticReportService;

    /**
     * 배후단지 고지 이력 화면을 로딩한다.
     * @param windowId
     * @param model
     * @return /ygpa/gam/oper/htldnew/GamHtldRentNticHist.jsp
     */
    @RequestMapping(value="/oper/htldnew/gamHtldRentNticHist.do")
    public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/oper/htldnew/GamHtldRentNticHist";
    }

    /**
     * 고지이력목록을 조회한다.
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htldnew/selectHtldRentNticHistList.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectHtldRentNticHistList(GamHtldRentNticHistVO searchVO) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	Map<?, ?> entrpsInfo = gamHtldRentNticHistService.selectEntrpsInfo(searchVO);
    	List<?> resultList = gamHtldRentNticHistService.selectHtldRentNticHistList(searchVO);
    	
    	map.put("resultCode", 0);
    	map.put("entrpsInfo", entrpsInfo);
    	map.put("resultList", resultList);
    	
    	return map;
	}

    /**
     * 고지상세목록을 조회한다.
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htldnew/selectHistNticIssueList.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectHistNticIssueList(GamHtldRentNticHistVO searchVO) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	List<?> resultList = gamHtldRentNticHistService.selectHistNticIssueList(searchVO);
    	
    	map.put("resultCode", 0);
    	map.put("resultList", resultList);
    	
    	return map;
	}

    /**
     * 연체고지상세목록을 조회한다.
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htldnew/selectHistArrrgNticIssueList.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectHistArrrgNticIssueList(GamHtldRentNticHistVO searchVO) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	List<?> resultList = gamHtldRentNticHistService.selectHistArrrgNticIssueList(searchVO);
    	
    	map.put("resultCode", 0);
    	map.put("resultList", resultList);
    	
    	return map;
	}


    /**
     * 연체고지상세목록수와 원고지 출력여부를 조회한다.
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htldnew/selectHistNticIssueInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectHistNticIssueInfo(GamHtldRentNticHistVO searchVO) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	int arrrgCnt = gamHtldRentNticHistService.selectHistArrrgNticIssueListCnt(searchVO);
    	String billPrtYn = gamHtldRentNticHistService.selectNticIssueBillPrtYn(searchVO);
    	
    	map.put("resultCode", 0);
    	map.put("arrrgCnt", arrrgCnt);
    	map.put("billPrtYn", billPrtYn);
    	
    	return map;
	}

    /**
     * 원고지정보를 취소한다.
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htldnew/cancelSourceNticIssue.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> cancelSourceNticIssue(GamHtldRentNticHistVO searchVO) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	try {
    		searchVO.setUpdUsr(loginVO.getId());
    		gamHtldRentNticHistService.cancelSourceNticIssue(searchVO);
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("gam.asset.proc"));    		
    	} catch(Exception e) {
    		map.put("resultCode", 1);
    		map.put("resultMsg", e.getMessage());    		
    	}
    	
    	return map;
	}
	
    /**
     * 최신연체고지정보를 취소한다.
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htldnew/cancelArrrgNticIssue.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> cancelArrrgNticIssue(GamHtldRentNticHistVO searchVO) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	try {
    		searchVO.setUpdUsr(loginVO.getId());
    		gamHtldRentNticHistService.cancelArrrgNticIssue(searchVO);
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.unpaid"));    		
    	} catch(Exception e) {
    		map.put("resultCode", 1);
    		map.put("resultMsg", e.getMessage());    		
    	}
    	
    	return map;
	}

    /**
     * 지로 수납된 자료인지 조회
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htldnew/selectCheckOcrResult.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectCheckOcrResult(GamHtldRentNticDefaultVO searchVO) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	String ocrResult = gamHtldRentNticHistService.selectCheckOcrResult(searchVO);
    	
    	map.put("resultCode", 0);
    	map.put("ocrResult", ocrResult);
    	
    	return map;
	}

    /**
     * 고지서 출력 화면 로딩
     * @param searchVO
     * @return String
     * @throws Exception the exception
     */
    @RequestMapping(value="/oper/htldnew/printNticIssue.do")
    public String printNticIssue(GamHtldRentNticDefaultVO searchVO, ModelMap model) throws Exception {
    	model.addAttribute("searchVO", searchVO);
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    		return "ygpa/gam/oper/htldnew/GamHtldRentPrintNticIssue";
    	}
    	
    	Map<?, ?> master = ("00".equals(searchVO.getDlySerNo())) ? 
    			gamHtldRentNticReportService.selectNticPrintMaster(searchVO) : gamHtldRentNticReportService.selectArrrgNticPrintMaster(searchVO);
    			
    	List<?> detailList = gamHtldRentNticReportService.selectNticIssueList(searchVO);
    	
    	model.addAttribute("master", master);
    	model.addAttribute("detailList", detailList);
    	model.addAttribute("detailListCnt", detailList.size());
    	model.addAttribute("resultCode", 0);
    	model.addAttribute("resultMsg", "");
    	return "ygpa/gam/oper/htldnew/GamHtldRentPrintNticIssue";
    }
    
    /**
     * 인쇄 후 고지서 출력 처리
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htldnew/printProcessHtldNticIssue.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> printProcessHtldNticIssue(GamHtldRentNticInfoVO nticInfoVO) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	if("00".equals(nticInfoVO.getDlySerNo())) { //최초고지일 경우
    		int diffDays = gamHtldRentNticReportService.selectNticDaysDiff(nticInfoVO);
    		if(diffDays >= 0) { //고지일자가 오늘날짜를 포함한 이전의 날짜이면 즉시 출력상태를 바꾼다.
    			gamHtldRentNticReportService.updateImmediatelyPrintState(nticInfoVO);
    		} else { //미래의 날짜이면 예약출력상태로 해둔다. 스케줄링에 의해 출력상태 처리
    			gamHtldRentNticReportService.updateReservePrintState(nticInfoVO);
    		}
    	} else { //연체고지일 경우
    		gamHtldRentNticReportService.updateArrrgPrintState(nticInfoVO);
    	}
    	
    	map.put("resultCode", 0);
    	
    	return map;
	}
    
}
