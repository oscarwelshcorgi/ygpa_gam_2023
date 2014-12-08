/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngService;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngVO;

/**
 *
 * @author 김종민
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltyCtrtMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="gamFcltyCtrtMngService")
    GamFcltyCtrtMngService gamFcltyCtrtMngService;
    
	@RequestMapping(value="/ctrt/gamFcltyCtrtMng.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/ctrt/GamFcltyCtrtMng";
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/selectFcltyCtrtMngList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtMngList(GamFcltyCtrtMngVO searchVO) throws Exception {
		int totalCnt;
		long sumPlanAmt, sumPrmtAmt, sumScsbidAmt, sumBaseAmt;
    	Map<String, Object> map = new HashMap<String, Object>();

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

    	List fcltyCtrtMngList = gamFcltyCtrtMngService.selectFcltyCtrtMngList(searchVO);
    	
		GamFcltyCtrtMngVO fcltyCtrtMngSum = gamFcltyCtrtMngService.selectFcltyCtrtMngSum(searchVO);
    	
		totalCnt = fcltyCtrtMngSum.getTotalCnt();
		sumPlanAmt = fcltyCtrtMngSum.getSumPlanAmt();
		sumPrmtAmt = fcltyCtrtMngSum.getSumPrmtAmt();
		sumScsbidAmt = fcltyCtrtMngSum.getSumScsbidAmt();
		sumBaseAmt = fcltyCtrtMngSum.getSumBaseAmt();
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("sumPlanAmt", sumPlanAmt);
    	map.put("sumPrmtAmt", sumPrmtAmt);
    	map.put("sumScsbidAmt", sumScsbidAmt);
    	map.put("sumBaseAmt", sumBaseAmt);
    	map.put("resultList", fcltyCtrtMngList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	@RequestMapping(value="/ctrt/selectFcltyCtrtInfoDetailInquire.do")
	@ResponseBody Map<String, Object> selectFcltyCtrtInfoDetailInquire( @ModelAttribute("gamFcltyCtrtMngVO") GamFcltyCtrtMngVO gamFcltyCtrtMngVO, BindingResult bindingResult)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
    	GamFcltyCtrtMngVO resultVO = gamFcltyCtrtMngService.selectFcltyCtrtInfoDetail(gamFcltyCtrtMngVO);
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
    @RequestMapping(value="/ctrt/selectFcltyCtrtJoinContrList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtJoinContrList(GamFcltyCtrtMngVO searchVO) throws Exception {
		int totalCnt;
    	Map<String, Object> map = new HashMap<String, Object>();

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
		
    	List ctrtJoinContrList = gamFcltyCtrtMngService.selectFcltyCtrtJoinContrList(searchVO);
    	
		totalCnt = gamFcltyCtrtMngService.selectFcltyCtrtJoinContrListCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", ctrtJoinContrList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/selectFcltyCtrtSubCtrtList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtSubCtrtList(GamFcltyCtrtMngVO searchVO) throws Exception {
		
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
		
    	List ctrtSubCtrtList = gamFcltyCtrtMngService.selectFcltyCtrtSubCtrtList(searchVO);
    	
		totalCnt = gamFcltyCtrtMngService.selectFcltyCtrtSubCtrtListCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", ctrtSubCtrtList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/selectFcltyCtrtChangeList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtChangeList(GamFcltyCtrtMngVO searchVO) throws Exception {
		
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
		
    	List ctrtChangeList = gamFcltyCtrtMngService.selectFcltyCtrtChangeList(searchVO);
    	
		totalCnt = gamFcltyCtrtMngService.selectFcltyCtrtChangeListCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", ctrtChangeList);
    	map.put("searchOption", searchVO);

    	return map;
    }


	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/selectFcltyCtrtMoneyPymntList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtMoneyPymntList(GamFcltyCtrtMngVO searchVO) throws Exception {
		
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
		
    	List ctrtMoneyPymntList = gamFcltyCtrtMngService.selectFcltyCtrtMoneyPymntList(searchVO);
    	
		totalCnt = gamFcltyCtrtMngService.selectFcltyCtrtMoneyPymntListCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", ctrtMoneyPymntList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/selectFcltyCtrtFulFillCaryFwdList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtFulFillCaryFwdList(GamFcltyCtrtMngVO searchVO) throws Exception {
		
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
		
    	List ctrtFulFillCaryFwdList = gamFcltyCtrtMngService.selectFcltyCtrtFulFillCaryFwdList(searchVO);
    	
		totalCnt = gamFcltyCtrtMngService.selectFcltyCtrtFulFillCaryFwdListCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", ctrtFulFillCaryFwdList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
    @RequestMapping(value="/ctrt/insertFcltyCtrtInfo.do")
	@ResponseBody Map insertFcltyCtrtInfo(@RequestParam Map ctrtInfoData) throws Exception {
    	Map map = new HashMap();

    	ObjectMapper mapper = new ObjectMapper();
    	Map<String, Object> ctrtInfo = new HashMap<String, Object>();
    	List<HashMap<String,String>> ctrtJoinContrList = null;
    	List<HashMap<String,String>> ctrtSubCtrtList = null;
    	List<HashMap<String,String>> ctrtChangeList = null;
    	List<HashMap<String,String>> ctrtMoneyPymntList = null;
    	List<HashMap<String,String>> ctrtFulFillCaryFwdList = null;
    	
    	Map<String, String> subData = null;
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
    	ctrtInfo = mapper.readValue((String)ctrtInfoData.get("ctrtInfo"),new TypeReference<HashMap<String,String>>(){});
    	ctrtJoinContrList = mapper.readValue((String)ctrtInfoData.get("ctrtJoinContrList"),new TypeReference<List<HashMap<String,String>>>(){});
    	ctrtSubCtrtList = mapper.readValue((String)ctrtInfoData.get("ctrtSubCtrtList"),new TypeReference<List<HashMap<String,String>>>(){});
    	ctrtChangeList = mapper.readValue((String)ctrtInfoData.get("ctrtChangeList"),new TypeReference<List<HashMap<String,String>>>(){});
    	ctrtMoneyPymntList = mapper.readValue((String)ctrtInfoData.get("ctrtMoneyPymntList"),new TypeReference<List<HashMap<String,String>>>(){});
    	ctrtFulFillCaryFwdList = mapper.readValue((String)ctrtInfoData.get("ctrtFulFillCaryFwdList"),new TypeReference<List<HashMap<String,String>>>(){});
    	
    	    	
    	try {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    		
    		ctrtInfo.put("regUsr", loginVo.getId());
    		gamFcltyCtrtMngService.insertFcltyCtrtInfoDetail(ctrtInfo);
	    	
	    	for(int i = 0, seq = 1; i < ctrtJoinContrList.size(); i++, seq++) {
	    		subData = ctrtJoinContrList.get(i);
	    		subData.put("ctrtNo", (String)ctrtInfo.get("ctrtNo"));
	    		subData.put("seq", String.valueOf(seq));
	    		subData.put("regUsr", loginVo.getId());
	    		subData.put("updUsr", loginVo.getId());
	    		gamFcltyCtrtMngService.insertFcltyCtrtJoinContrDetail(subData);
	    	}
	    	for(int i = 0, seq = 1; i < ctrtSubCtrtList.size(); i++, seq++) {
	    		subData = ctrtSubCtrtList.get(i);
	    		subData.put("ctrtNo", (String)ctrtInfo.get("ctrtNo"));
	    		subData.put("seq", String.valueOf(seq));
	    		subData.put("regUsr", loginVo.getId());
	    		subData.put("updUsr", loginVo.getId());
	    		gamFcltyCtrtMngService.insertFcltyCtrtSubCtrtDetail(subData);
	    	}
	    	for(int i = 0, seq = 1; i < ctrtChangeList.size(); i++, seq++) {
	    		subData = ctrtChangeList.get(i);
	    		subData.put("ctrtNo", (String)ctrtInfo.get("ctrtNo"));
	    		subData.put("seq", String.valueOf(seq));
	    		subData.put("regUsr", loginVo.getId());
	    		subData.put("updUsr", loginVo.getId());
	    		gamFcltyCtrtMngService.insertFcltyCtrtChangeDetail(subData);
	    	}
	    	for(int i = 0, seq = 1; i < ctrtMoneyPymntList.size(); i++, seq++) {
	    		subData = ctrtMoneyPymntList.get(i);
	    		subData.put("ctrtNo", (String)ctrtInfo.get("ctrtNo"));
	    		subData.put("seq", String.valueOf(seq));
	    		subData.put("regUsr", loginVo.getId());
	    		subData.put("updUsr", loginVo.getId());
	    		gamFcltyCtrtMngService.insertFcltyCtrtMoneyPymntDetail(subData);
	    	}
	    	for(int i = 0, seq = 1; i < ctrtFulFillCaryFwdList.size(); i++, seq++) {
	    		subData = ctrtFulFillCaryFwdList.get(i);
	    		subData.put("ctrtNo", (String)ctrtInfo.get("ctrtNo"));
	    		subData.put("seq", String.valueOf(seq));
	    		subData.put("regUsr", loginVo.getId());
	    		subData.put("updUsr", loginVo.getId());
	    		gamFcltyCtrtMngService.insertFcltyCtrtFulFillCaryFwdDetail(subData);
	    	}
	
    		map.put("resultCode", 0);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
    	} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

    	return map;
    }	

    @RequestMapping(value="/ctrt/updateFcltyCtrtInfo.do")
	@ResponseBody Map updateFcltyCtrtInfo(@RequestParam Map ctrtInfoData) throws Exception {
    	Map map = new HashMap();

    	ObjectMapper mapper = new ObjectMapper();
    	Map<String, Object> ctrtInfo = new HashMap<String, Object>();
    	List<HashMap<String,String>> ctrtJoinContrList = null;
    	List<HashMap<String,String>> ctrtSubCtrtList = null;
    	List<HashMap<String,String>> ctrtChangeList = null;
    	List<HashMap<String,String>> ctrtMoneyPymntList = null;
    	List<HashMap<String,String>> ctrtFulFillCaryFwdList = null;
    	
    	Map<String, String> subData = null;
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
    	ctrtInfo = mapper.readValue((String)ctrtInfoData.get("ctrtInfo"),new TypeReference<HashMap<String,String>>(){});
    	ctrtJoinContrList = mapper.readValue((String)ctrtInfoData.get("ctrtJoinContrList"),new TypeReference<List<HashMap<String,String>>>(){});
    	ctrtSubCtrtList = mapper.readValue((String)ctrtInfoData.get("ctrtSubCtrtList"),new TypeReference<List<HashMap<String,String>>>(){});
    	ctrtChangeList = mapper.readValue((String)ctrtInfoData.get("ctrtChangeList"),new TypeReference<List<HashMap<String,String>>>(){});
    	ctrtMoneyPymntList = mapper.readValue((String)ctrtInfoData.get("ctrtMoneyPymntList"),new TypeReference<List<HashMap<String,String>>>(){});
    	ctrtFulFillCaryFwdList = mapper.readValue((String)ctrtInfoData.get("ctrtFulFillCaryFwdList"),new TypeReference<List<HashMap<String,String>>>(){});
    	
    	try {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    		
    		ctrtInfo.put("updUsr", loginVo.getId());
    		gamFcltyCtrtMngService.updateFcltyCtrtInfoDetail(ctrtInfo);
	    	
    		gamFcltyCtrtMngService.deleteFcltyCtrtJoinContrAll(ctrtInfo);
	    	for(int i = 0, seq = 1; i < ctrtJoinContrList.size(); i++, seq++) {
	    		subData = ctrtJoinContrList.get(i);
	    		subData.put("ctrtNo", (String)ctrtInfo.get("ctrtNo"));
	    		subData.put("seq", String.valueOf(seq));
	    		subData.put("regUsr", loginVo.getId());
	    		subData.put("updUsr", loginVo.getId());
	    		gamFcltyCtrtMngService.insertFcltyCtrtJoinContrDetail(subData);
	    	}
	    	
	    	gamFcltyCtrtMngService.deleteFcltyCtrtSubCtrtAll(ctrtInfo);
	    	for(int i = 0, seq = 1; i < ctrtSubCtrtList.size(); i++, seq++) {
	    		subData = ctrtSubCtrtList.get(i);
	    		subData.put("ctrtNo", (String)ctrtInfo.get("ctrtNo"));
	    		subData.put("seq", String.valueOf(seq));
	    		subData.put("regUsr", loginVo.getId());
	    		subData.put("updUsr", loginVo.getId());
	    		gamFcltyCtrtMngService.insertFcltyCtrtSubCtrtDetail(subData);
	    	}
	    	
	    	gamFcltyCtrtMngService.deleteFcltyCtrtChangeAll(ctrtInfo);
	    	for(int i = 0, seq = 1; i < ctrtChangeList.size(); i++, seq++) {
	    		subData = ctrtChangeList.get(i);
	    		subData.put("ctrtNo", (String)ctrtInfo.get("ctrtNo"));
	    		subData.put("seq", String.valueOf(seq));
	    		subData.put("regUsr", loginVo.getId());
	    		subData.put("updUsr", loginVo.getId());
	    		gamFcltyCtrtMngService.insertFcltyCtrtChangeDetail(subData);
	    	}
	    	
	    	gamFcltyCtrtMngService.deleteFcltyCtrtMoneyPymntAll(ctrtInfo);
	    	for(int i = 0, seq = 1; i < ctrtMoneyPymntList.size(); i++, seq++) {
	    		subData = ctrtMoneyPymntList.get(i);
	    		subData.put("ctrtNo", (String)ctrtInfo.get("ctrtNo"));
	    		subData.put("seq", String.valueOf(seq));
	    		subData.put("regUsr", loginVo.getId());
	    		subData.put("updUsr", loginVo.getId());
	    		gamFcltyCtrtMngService.insertFcltyCtrtMoneyPymntDetail(subData);
	    	}
	    	
	    	gamFcltyCtrtMngService.deleteFcltyCtrtFulFillCaryFwdAll(ctrtInfo);
	    	for(int i = 0, seq = 1; i < ctrtFulFillCaryFwdList.size(); i++, seq++) {
	    		subData = ctrtFulFillCaryFwdList.get(i);
	    		subData.put("ctrtNo", (String)ctrtInfo.get("ctrtNo"));
	    		subData.put("seq", String.valueOf(seq));
	    		subData.put("regUsr", loginVo.getId());
	    		subData.put("updUsr", loginVo.getId());
	    		gamFcltyCtrtMngService.insertFcltyCtrtFulFillCaryFwdDetail(subData);
	    	}
	
    		map.put("resultCode", 0);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
    	} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

    	return map;
    }	

    @RequestMapping(value="/ctrt/deleteFcltyCtrtInfo.do")
	@ResponseBody Map deleteFcltyCtrtInfo(@RequestParam Map deleteMap) throws Exception {
    	Map map = new HashMap();

    	ObjectMapper mapper = new ObjectMapper();
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	try {
    		gamFcltyCtrtMngService.deleteFcltyCtrtJoinContrAll(deleteMap);
	    	gamFcltyCtrtMngService.deleteFcltyCtrtSubCtrtAll(deleteMap);
	    	gamFcltyCtrtMngService.deleteFcltyCtrtChangeAll(deleteMap);
	    	gamFcltyCtrtMngService.deleteFcltyCtrtMoneyPymntAll(deleteMap);
	    	gamFcltyCtrtMngService.deleteFcltyCtrtFulFillCaryFwdAll(deleteMap);
	    	gamFcltyCtrtMngService.deleteFcltyCtrtInfoDetail(deleteMap);
	    	
    		map.put("resultCode", 0);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
    	} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

    	return map;
    }	
}


