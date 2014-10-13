/**
 *
 */
package egovframework.rte.ygpa.gam.soc.web;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.soc.service.GamSocApplyService;
import egovframework.rte.ygpa.gam.soc.service.GamSocApplyVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseVO;

/**
 *
 * @author 김종민
 * @since 2014. 10. 06.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 06.	김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamSocApplyController {

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
    
    @Resource(name = "gamSocApplyService")
    private GamSocApplyService gamSocApplyService;
    
    @RequestMapping(value="/soc/gamSocApply.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		List prtAtCdList = gamSocCmmUseService.selectSocPrtAtCodeDetail();
		List yearsList = this.getYears(); // 조회연도
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);
		model.addAttribute("yearsList", yearsList);

    	return "/ygpa/gam/soc/GamSocApply";
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
	
    @RequestMapping(value="/soc/gamSelectApplyDetailInquire.do")
	@ResponseBody Map selectSocApplyDetailInquire(
			@ModelAttribute("gamSocApplyVO") GamSocApplyVO gamSocApplyVO,
	     	BindingResult bindingResult)
	        throws Exception {
		Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
		GamSocApplyVO resultVO = gamSocApplyService.selectSocApplyDetailInquire(gamSocApplyVO);
		
		if(resultVO == null) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		} else {
			map.put("resultCode", 0);
			map.put("resultVO", resultVO);
		}
		
    	return map;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSelectSocApplyList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectSocApplyList(GamSocApplyVO searchVO) throws Exception {
		
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
		
    	List socApplyList = gamSocApplyService.selectSocApplyList(searchVO);
    	
		totalCnt = gamSocApplyService.selectSocApplyListTotCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", socApplyList);
    	map.put("searchOption", searchVO);

    	return map;
    }

    @RequestMapping(value="/soc/gamInsertSocApplyDetail.do")
	@ResponseBody Map insertSocApplyDetail(@RequestParam Map socApplyData) throws Exception {
    	Map map = new HashMap();

    	ObjectMapper mapper = new ObjectMapper();
    	Map<String, Object> applyData = new HashMap<String, Object>();
    	List<HashMap<String,String>> applyFacilList = null;
    	List<HashMap<String,String>> applyFeeList = null;
    	
    	Map<String, String> subData = null;
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
    	applyFacilList = mapper.readValue((String)socApplyData.get("applyFacilList"),new TypeReference<List<HashMap<String,String>>>(){});
    	applyFeeList = mapper.readValue((String)socApplyData.get("applyFeeList"),new TypeReference<List<HashMap<String,String>>>(){});
    	applyData = mapper.readValue((String)socApplyData.get("applyData"),new TypeReference<HashMap<String,String>>(){});
    	
    	applyData.put("updtUid", "");
    	
    	try {
	    	gamSocApplyService.insertSocApplyDetail(applyData);
	    	
	    	for(int i = 0; i < applyFacilList.size(); i++) {
	    		subData = applyFacilList.get(i);
	    		subData.put("prtAtCode", (String)applyData.get("prtAtCode"));
	    		subData.put("cmplYr", (String)applyData.get("cmplYr"));
	    		subData.put("constNo", (String)applyData.get("constNo"));
	    		subData.put("appPrtAtCode", (String)applyData.get("appPrtAtCode"));
	    		subData.put("appAgentCode", (String)applyData.get("appAgentCode"));
	    		subData.put("useNo", (String)applyData.get("useNo"));
	    		
	    		gamSocApplyService.insertSocApplyFacilInfo(subData);
	    	}
	
	    	for(int i = 0; i < applyFeeList.size(); i++) {
	    		subData = applyFeeList.get(i);
	    		subData.put("prtAtCode", (String)applyData.get("prtAtCode"));
	    		subData.put("cmplYr", (String)applyData.get("cmplYr"));
	    		subData.put("constNo", (String)applyData.get("constNo"));
	    		subData.put("appPrtAtCode", (String)applyData.get("appPrtAtCode"));
	    		subData.put("appAgentCode", (String)applyData.get("appAgentCode"));
	    		subData.put("useNo", (String)applyData.get("useNo"));
	    		
	    		gamSocApplyService.insertSocApplyFeeInfo(subData);
	    	}
    		map.put("resultCode", 0);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
    	} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

    	return map;
    }
    
    @RequestMapping(value="/soc/gamUpdateSocApplyDetail.do")
	@ResponseBody Map updateSocApplyDetail(@RequestParam Map socApplyData) throws Exception {
    	Map map = new HashMap();

    	ObjectMapper mapper = new ObjectMapper();
    	Map<String, Object> applyData = new HashMap<String, Object>();
    	List<HashMap<String,String>> applyFacilList = null;
    	List<HashMap<String,String>> applyFeeList = null;
    	
    	Map<String, String> subData = null;
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
    	applyFacilList = mapper.readValue((String)socApplyData.get("applyFacilList"),new TypeReference<List<HashMap<String,String>>>(){});
    	applyFeeList = mapper.readValue((String)socApplyData.get("applyFeeList"),new TypeReference<List<HashMap<String,String>>>(){});
    	applyData = mapper.readValue((String)socApplyData.get("applyData"),new TypeReference<HashMap<String,String>>(){});

    	applyData.put("updtUid", "");
    	
    	try {
	    	gamSocApplyService.updateSocApplyDetail(applyData);
	    	
	    	gamSocApplyService.deleteSocApplyFacilList(applyData);
	    	for(int i = 0; i < applyFacilList.size(); i++) {
	    		subData = applyFacilList.get(i);
	    		subData.put("prtAtCode", (String)applyData.get("prtAtCode"));
	    		subData.put("cmplYr", (String)applyData.get("cmplYr"));
	    		subData.put("constNo", (String)applyData.get("constNo"));
	    		subData.put("appPrtAtCode", (String)applyData.get("appPrtAtCode"));
	    		subData.put("appAgentCode", (String)applyData.get("appAgentCode"));
	    		subData.put("useNo", (String)applyData.get("useNo"));
	    		
	    		gamSocApplyService.insertSocApplyFacilInfo(subData);
	    	}
	    	
	    	gamSocApplyService.deleteSocApplyFeeList(applyData);
	    	for(int i = 0; i < applyFeeList.size(); i++) {
	    		subData = applyFeeList.get(i);
	    		subData.put("prtAtCode", (String)applyData.get("prtAtCode"));
	    		subData.put("cmplYr", (String)applyData.get("cmplYr"));
	    		subData.put("constNo", (String)applyData.get("constNo"));
	    		subData.put("appPrtAtCode", (String)applyData.get("appPrtAtCode"));
	    		subData.put("appAgentCode", (String)applyData.get("appAgentCode"));
	    		subData.put("useNo", (String)applyData.get("useNo"));
	    		
	    		gamSocApplyService.insertSocApplyFeeInfo(subData);
	    	}
    		map.put("resultCode", 0);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
    	} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}
				
    	return map;
    }
    
    @RequestMapping(value="/soc/gamDeleteSocApplyDetail.do")
	@ResponseBody Map deleteSocApplyDetail(@RequestParam Map socApplyData) throws Exception {
		Map map = new HashMap();

    	ObjectMapper mapper = new ObjectMapper();
    	Map<String, Object> applyData = new HashMap<String, Object>();
    	List<HashMap<String,String>> applyFacilList = null;
    	List<HashMap<String,String>> applyFeeList = null;
    	
    	Map<String, String> subData = null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	applyFacilList = mapper.readValue((String)socApplyData.get("applyFacilList"),new TypeReference<List<HashMap<String,String>>>(){});
    	applyFeeList = mapper.readValue((String)socApplyData.get("applyFeeList"),new TypeReference<List<HashMap<String,String>>>(){});
    	applyData = mapper.readValue((String)socApplyData.get("applyData"),new TypeReference<HashMap<String,String>>(){});
    	
    	try {
	    	gamSocApplyService.deleteSocApplyFacilList(applyData);	    	
	    	gamSocApplyService.deleteSocApplyFeeList(applyData);
	    	gamSocApplyService.deleteSocApplyDetail(applyData);
	    	
    		map.put("resultCode", 0);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
    	} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

    	return map;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSelectSocApplyFacilList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectSocApplyFacilList(GamSocApplyVO searchVO) throws Exception {
		
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
		
    	List socApplyFacilList = gamSocApplyService.selectSocApplyFacilList(searchVO);
    	
		totalCnt = gamSocApplyService.selectSocApplyFacilListTotCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", socApplyFacilList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSelectSocApplyFeeList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectSocApplyFeeList(GamSocApplyVO searchVO) throws Exception {
		
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
		
    	List socApplyFeeList = gamSocApplyService.selectSocApplyFeeList(searchVO);
    	
		totalCnt = gamSocApplyService.selectSocApplyFeeListTotCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", socApplyFeeList);
    	map.put("searchOption", searchVO);

    	return map;
    }
}
