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
import egovframework.rte.psl.dataaccess.util.EgovMap;
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

	/**
	 * 계약정보 목록조회
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return map
	 * @exception Exception
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/selectFcltyCtrtMngList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtMngList(GamFcltyCtrtMngVO searchVO) throws Exception {
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

    	List fcltyCtrtMngList = gamFcltyCtrtMngService.selectFcltyCtrtMngList(searchVO);
    	
		GamFcltyCtrtMngVO fcltyCtrtMngSum = gamFcltyCtrtMngService.selectFcltyCtrtMngTotSum(searchVO);
    	
		totalCnt = fcltyCtrtMngSum.getTotalCnt();

		paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("sumPlanAmt", fcltyCtrtMngSum.getSumPlanAmt());
    	map.put("sumPrmtAmt", fcltyCtrtMngSum.getSumPrmtAmt());
    	map.put("sumScsbidAmt", fcltyCtrtMngSum.getSumScsbidAmt());
    	map.put("sumBaseAmt", fcltyCtrtMngSum.getSumBaseAmt());
    	map.put("resultList", fcltyCtrtMngList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	/**
	 * 계약정보 상세정보조회
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return map
	 * @exception Exception
	 */	
	@RequestMapping(value="/ctrt/selectFcltyCtrtInfoDetail.do")
	@ResponseBody Map<String, Object> selectFcltyCtrtInfoDetail( @ModelAttribute("gamFcltyCtrtMngVO") GamFcltyCtrtMngVO gamFcltyCtrtMngVO, BindingResult bindingResult)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		EgovMap result = null;
		
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
    	try {
    		result = gamFcltyCtrtMngService.selectFcltyCtrtInfoDetail(gamFcltyCtrtMngVO);
			map.put("resultCode", 0);
			map.put("resultVO", result);
    	} catch(Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
    	}
		
    	return map;
    }
    
	/**
	 * 계약공동도급 목록조회
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return map
	 * @exception Exception
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/selectFcltyCtrtJoinContrList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtJoinContrList(GamFcltyCtrtMngVO searchVO) throws Exception {
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
		
    	List ctrtJoinContrList = gamFcltyCtrtMngService.selectFcltyCtrtJoinContrList(searchVO);
    	
		totalCnt = gamFcltyCtrtMngService.selectFcltyCtrtJoinContrListTotCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", ctrtJoinContrList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	/**
	 * 계약하도급 목록조회
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return map
	 * @exception Exception
	 */		
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/selectFcltyCtrtSubCtrtList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtSubCtrtList(GamFcltyCtrtMngVO searchVO) throws Exception {
		
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
		
    	List ctrtSubCtrtList = gamFcltyCtrtMngService.selectFcltyCtrtSubCtrtList(searchVO);
    	
		totalCnt = gamFcltyCtrtMngService.selectFcltyCtrtSubCtrtListTotCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", ctrtSubCtrtList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	/**
	 * 계약변경 목록조회
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return map
	 * @exception Exception
	 */		
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/selectFcltyCtrtChangeList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtChangeList(GamFcltyCtrtMngVO searchVO) throws Exception {
		
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
		
    	List ctrtChangeList = gamFcltyCtrtMngService.selectFcltyCtrtChangeList(searchVO);
    	
		totalCnt = gamFcltyCtrtMngService.selectFcltyCtrtChangeListTotCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", ctrtChangeList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	/**
	 * 계약대금지급 목록조회
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return map
	 * @exception Exception
	 */		
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/selectFcltyCtrtMoneyPymntList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtMoneyPymntList(GamFcltyCtrtMngVO searchVO) throws Exception {
		
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
		
    	List ctrtMoneyPymntList = gamFcltyCtrtMngService.selectFcltyCtrtMoneyPymntList(searchVO);
    	
		totalCnt = gamFcltyCtrtMngService.selectFcltyCtrtMoneyPymntListTotCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", ctrtMoneyPymntList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	/**
	 * 계약이행이월 목록조회
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return map
	 * @exception Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/selectFcltyCtrtFulFillCaryFwdList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtFulFillCaryFwdList(GamFcltyCtrtMngVO searchVO) throws Exception {
		
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
		
    	List ctrtFulFillCaryFwdList = gamFcltyCtrtMngService.selectFcltyCtrtFulFillCaryFwdList(searchVO);
    	
		totalCnt = gamFcltyCtrtMngService.selectFcltyCtrtFulFillCaryFwdListTotCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", ctrtFulFillCaryFwdList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	/**
	 * 계약정보 삽입
	 * @param map - 삽입할 정보가 담긴 map
	 * @return map
	 * @exception Exception
	 */		
    @RequestMapping(value="/ctrt/insertFcltyCtrtInfo.do")
	@ResponseBody Map<String, Object> insertFcltyCtrtInfo(@RequestParam Map<String, Object> ctrtInfoData) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	ctrtInfoData.put("regUsr", user.getId());
    	
    	try {
    		gamFcltyCtrtMngService.insertFcltyCtrtInfoDetail(ctrtInfoData);
    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}
    	return map;
    }	

	/**
	 * 계약정보 수정
	 * @param map - 수정할 정보가 담긴 map
	 * @return map
	 * @exception Exception
	 */		
    @RequestMapping(value="/ctrt/updateFcltyCtrtInfo.do")
	@ResponseBody Map<String, Object> updateFcltyCtrtInfo(@RequestParam Map<String, Object> ctrtInfoData) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	ctrtInfoData.put("regUsr", user.getId());
    	
    	try {
    		gamFcltyCtrtMngService.updateFcltyCtrtInfoDetail(ctrtInfoData);
    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}
    	return map;
    }	

	/**
	 * 계약공동도급 병합저장
	 * @param map - 병합저장할 정보가 담긴 map
	 * @return map
	 * @exception Exception
	 */		
	@RequestMapping(value="/ctrt/mergeFcltyCtrtJoinContr.do")
	@ResponseBody Map<String, Object> mergeFcltyCtrtJoinContr(@RequestParam Map<String, Object> dataList) throws Exception {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String, String> userMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

    	List<HashMap<String,String>> insertList=null;
    	List<HashMap<String,String>> updateList=null;
    	List<HashMap<String,String>> deleteList=null;
    	List<Map<String,String>> userList=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
		insertList = mapper.readValue((String)dataList.get("insertList"),
		    new TypeReference<List<HashMap<String,String>>>(){});

		updateList = mapper.readValue((String)dataList.get("updateList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		deleteList = mapper.readValue((String)dataList.get("deleteList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});
		
		userList = new ArrayList<Map<String,String>>();
		userMap.put("id",  loginVO.getId());
		userList.add(userMap);

		Map<String,Object> mergeMap = new HashMap<String,Object>();
		insertList.addAll(updateList);
		mergeMap.put("CU", insertList);
		mergeMap.put("D", deleteList);
		mergeMap.put("USER", userList);
		
		try {
			gamFcltyCtrtMngService.mergeFcltyCtrtJoinContrDetail(mergeMap);
	        map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));
		} catch(Exception e) {
	        map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.merge"));
		}
		return map;
	}	
    
	/**
	 * 계약하도급 병합저장
	 * @param map - 병합저장할 정보가 담긴 map
	 * @return map
	 * @exception Exception
	 */		
	@RequestMapping(value="/ctrt/mergeFcltyCtrtSubCtrt.do")
	@ResponseBody Map<String, Object> mergeFcltyCtrtSubCtrt(@RequestParam Map<String, Object> dataList) throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String, String> userMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

    	List<HashMap<String,String>> insertList=null;
    	List<HashMap<String,String>> updateList=null;
    	List<HashMap<String,String>> deleteList=null;
    	List<Map<String,String>> userList=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
		insertList = mapper.readValue((String)dataList.get("insertList"),
		    new TypeReference<List<HashMap<String,String>>>(){});

		updateList = mapper.readValue((String)dataList.get("updateList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		deleteList = mapper.readValue((String)dataList.get("deleteList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});
		
		userList = new ArrayList<Map<String,String>>();
		userMap.put("id",  loginVO.getId());
		userList.add(userMap);

		Map<String,Object> mergeMap = new HashMap<String,Object>();
		insertList.addAll(updateList);
		mergeMap.put("CU", insertList);
		mergeMap.put("D", deleteList);
		mergeMap.put("USER", userList);

		try {
			gamFcltyCtrtMngService.mergeFcltyCtrtSubCtrtDetail(mergeMap);
	        map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));
		} catch(Exception e) {
	        map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.merge"));			
		}
		return map;
	}	

	/**
	 * 계약변경 병합저장
	 * @param map - 병합저장할 정보가 담긴 map
	 * @return map
	 * @exception Exception
	 */		
	@RequestMapping(value="/ctrt/mergeFcltyCtrtChange.do")
	@ResponseBody Map<String, Object> mergeFcltyCtrtChange(@RequestParam Map<String, Object> dataList) throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String, String> userMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

    	List<HashMap<String,String>> insertList=null;
    	List<HashMap<String,String>> updateList=null;
    	List<HashMap<String,String>> deleteList=null;
    	List<Map<String,String>> userList=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
		insertList = mapper.readValue((String)dataList.get("insertList"),
		    new TypeReference<List<HashMap<String,String>>>(){});

		updateList = mapper.readValue((String)dataList.get("updateList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		deleteList = mapper.readValue((String)dataList.get("deleteList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});
		
		userList = new ArrayList<Map<String,String>>();
		userMap.put("id",  loginVO.getId());
		userList.add(userMap);

		Map<String,Object> mergeMap = new HashMap<String,Object>();
		insertList.addAll(updateList);
		mergeMap.put("CU", insertList);
		mergeMap.put("D", deleteList);
		mergeMap.put("USER", userList);
		
		try {
			gamFcltyCtrtMngService.mergeFcltyCtrtChangeDetail(mergeMap);
	        map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));
		} catch(Exception e) {
	        map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.merge"));
		}
		
		return map;
	}	
	
	/**
	 * 계약대금지급 병합저장
	 * @param map - 병합저장할 정보가 담긴 map
	 * @return map
	 * @exception Exception
	 */		
	@RequestMapping(value="/ctrt/mergeFcltyCtrtMoneyPymnt.do")
	@ResponseBody Map<String, Object> mergeFcltyCtrtMoneyPymnt(@RequestParam Map<String, Object> dataList) throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String, String> userMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

    	List<HashMap<String,String>> insertList=null;
    	List<HashMap<String,String>> updateList=null;
    	List<HashMap<String,String>> deleteList=null;
    	List<Map<String,String>> userList=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
		insertList = mapper.readValue((String)dataList.get("insertList"),
		    new TypeReference<List<HashMap<String,String>>>(){});

		updateList = mapper.readValue((String)dataList.get("updateList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		deleteList = mapper.readValue((String)dataList.get("deleteList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});
		
		userList = new ArrayList<Map<String,String>>();
		userMap.put("id",  loginVO.getId());
		userList.add(userMap);

		Map<String,Object> mergeMap = new HashMap<String,Object>();
		insertList.addAll(updateList);
		mergeMap.put("CU", insertList);
		mergeMap.put("D", deleteList);
		mergeMap.put("USER", userList);

		try {
			gamFcltyCtrtMngService.mergeFcltyCtrtMoneyPymntDetail(mergeMap);
	        map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));
		} catch(Exception e) {
	        map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.merge"));			
		}
		return map;
	}	

	/**
	 * 계약이행이월 병합저장
	 * @param map - 병합저장할 정보가 담긴 map
	 * @return map
	 * @exception Exception
	 */		
	@RequestMapping(value="/ctrt/mergeFcltyCtrtFulFillCaryFwd.do")
	@ResponseBody Map<String, Object> mergeFcltyCtrtFulFillCaryFwd(@RequestParam Map<String, Object> dataList) throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String, String> userMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

    	List<HashMap<String,String>> insertList=null;
    	List<HashMap<String,String>> updateList=null;
    	List<HashMap<String,String>> deleteList=null;
    	List<Map<String,String>> userList=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
		insertList = mapper.readValue((String)dataList.get("insertList"),
		    new TypeReference<List<HashMap<String,String>>>(){});

		updateList = mapper.readValue((String)dataList.get("updateList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		deleteList = mapper.readValue((String)dataList.get("deleteList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});
		
		userList = new ArrayList<Map<String,String>>();
		userMap.put("id",  loginVO.getId());
		userList.add(userMap);

		Map<String,Object> mergeMap = new HashMap<String,Object>();
		insertList.addAll(updateList);
		mergeMap.put("CU", insertList);
		mergeMap.put("D", deleteList);
		mergeMap.put("USER", userList);

		try {
			gamFcltyCtrtMngService.mergeFcltyCtrtFulFillCaryFwdDetail(mergeMap);
	        map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));
		} catch(Exception e) {
	        map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.merge"));
		}
		return map;
	}	

	/**
	 * 계약정보(하위 포함) 삭제
	 * @param map - 삭제할 정보가 담긴 map
	 * @return map
	 * @exception Exception
	 */		
	@RequestMapping(value="/ctrt/deleteFcltyCtrtInfo.do")
	@ResponseBody Map<String, Object> deleteFcltyCtrtInfo(@RequestParam Map<String, Object> deleteMap) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	try {
    		gamFcltyCtrtMngService.deleteFcltyCtrtJoinContrList(deleteMap);
	    	gamFcltyCtrtMngService.deleteFcltyCtrtSubCtrtList(deleteMap);
	    	gamFcltyCtrtMngService.deleteFcltyCtrtChangeList(deleteMap);
	    	gamFcltyCtrtMngService.deleteFcltyCtrtMoneyPymntList(deleteMap);
	    	gamFcltyCtrtMngService.deleteFcltyCtrtFulFillCaryFwdList(deleteMap);
	    	gamFcltyCtrtMngService.deleteFcltyCtrtInfoDetail(deleteMap);
	    	
    		map.put("resultCode", 0);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
    	} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}
    	return map;
    }
	
	/**
	 * 계약공동도급 팝업 호출
	 * @param map - 계약공동도급 리스트
	 * @return 
	 * @exception Exception
	 */		
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/popup/showCtrtJoinContrMngt.do")
    String showCtrtJoinContrMngt(@RequestParam Map ctrtJoinContrList, ModelMap model) throws Exception {
		model.addAttribute("ctrtJoinContrList", ctrtJoinContrList);
    	return "/ygpa/gam/ctrt/GamPopupCtrtJoinContrMngt";
    }
	
	/**
	 * 계약하도급 팝업 호출
	 * @param map - 계약하도급 리스트
	 * @return 
	 * @exception Exception
	 */		
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/popup/showCtrtSubCtrtMngt.do")
    String showCtrtSubCtrtMngt(@RequestParam Map ctrtSubCtrtList, ModelMap model) throws Exception {
		model.addAttribute("ctrtSubCtrtList", ctrtSubCtrtList);
    	return "/ygpa/gam/ctrt/GamPopupCtrtSubCtrtMngt";
    }
	
	/**
	 * 계약변경 팝업 호출
	 * @param map - 계약변경 리스트
	 * @return 
	 * @exception Exception
	 */		
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/popup/showCtrtChangeMngt.do")
    String showCtrtChangeMngt(@RequestParam Map ctrtChangeList, ModelMap model) throws Exception {
		model.addAttribute("ctrtChangeList", ctrtChangeList);
    	return "/ygpa/gam/ctrt/GamPopupCtrtChangeMngt";
    }

	/**
	 * 계약대금지급 팝업 호출
	 * @param map - 계약대금지급 리스트
	 * @return 
	 * @exception Exception
	 */		
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/popup/showCtrtMoneyPymntMngt.do")
    String showCtrtMoneyPymntMngt(@RequestParam Map ctrtMoneyPymntList, ModelMap model) throws Exception {
		model.addAttribute("ctrtMoneyPymntList", ctrtMoneyPymntList);
    	return "/ygpa/gam/ctrt/GamPopupCtrtMoneyPymntMngt";
    }
	
	/**
	 * 계약이행이월 팝업 호출
	 * @param map - 계약이행이월 리스트
	 * @return 
	 * @exception Exception
	 */		
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/popup/showCtrtFulFillCaryFwdMngt.do")
    String showCtrtFulFillCaryFwdMngt(@RequestParam Map ctrtFulFillCaryFwdList, ModelMap model) throws Exception {
		model.addAttribute("ctrtFulFillCaryFwdList", ctrtFulFillCaryFwdList);
    	return "/ygpa/gam/ctrt/GamPopupCtrtFulFillCaryFwdMngt";
    }
}


