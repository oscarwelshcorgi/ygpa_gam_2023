/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamGisPrtFcltyCdMngtService;
import egovframework.rte.ygpa.gam.fclty.service.GamInfoTechFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamInfoTechFcltySpecMngVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 17.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamInfoTechFcltySpecMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="gamInfoTechFcltySpecMngService")
    GamInfoTechFcltySpecMngService gamInfoTechFcltySpecMngService;
    
    @Resource(name="gamGisPrtFcltyCdMngtService")
    GamGisPrtFcltyCdMngtService gamGisPrtFcltyCdMngtService;
    
    private final static String prtFcltySe = "I";    

    /**
     * 정보통신시설 관리화면호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/fclty/gamInfoTechFcltySpecMng.do")
    String indexInfoTechFcltySpecMng(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fclty/GamInfoTechFcltySpecMng";
    }


	/**
	 * 정보통신시설관리목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/selectInfoTechFcltySpecMngList.do")
	@ResponseBody Map selectInfoTechFcltySpecMngList(GamInfoTechFcltySpecMngVO searchVO) throws Exception {

		Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List resultList = gamInfoTechFcltySpecMngService.selectInfoTechFcltySpecMngList(searchVO);
		int totCnt = gamInfoTechFcltySpecMngService.selectInfoTechFcltySpecMngListTotCnt(searchVO);
		
        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	return map;
    }


	/**
	 * 정보통신 시설관리 상세
	 * @param fcltyManageVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/selectInfoTechFcltySpecMngDetail.do")
    @ResponseBody Map selectInfoTechFcltySpecMngDetail(@RequestParam Map searchVO) throws Exception {
    	Map map = new HashMap();
    	EgovMap result=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
        	result = gamInfoTechFcltySpecMngService.selectInfoTechFcltySpecMngDetail(searchVO);
            map.put("resultCode", 0);
            map.put("result", result);
    	}
    	catch(Exception e) {
            map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
    	}
        return map;		
	}
	
	/**
	 * 정보통신 시설정보 삽입
	 * @param map
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/insertInfoTechFcltySpecMngDetail.do")
    @ResponseBody Map<String, Object> insertInfoTechFcltySpecMngDetail(@RequestParam Map<String, Object> insertMap) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	insertMap.put("regUsr", user.getId());
    	
    	try {
    		insertMap.put("prtFcltySe", prtFcltySe);
    		insertMap.put("gisPrtFcltySeq", gamGisPrtFcltyCdMngtService.selectNextFcltySeq(insertMap));
    		gamGisPrtFcltyCdMngtService.insertGisPrtFclty(insertMap);
    		gamInfoTechFcltySpecMngService.insertInfoTechFcltySpecMngDetail(insertMap);
    		
    		map.put("resultCode", 0);			// return ok
    		map.put("gisPrtFcltySeq", insertMap.get("gisPrtFcltySeq"));
            map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}
      	return map;		
	}
	
	/**
	 * 정보통신 시설정보 수정
	 * @param map
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/updateInfoTechFcltySpecMngDetail.do")
    @ResponseBody Map<String, Object> updateInfoTechFcltySpecMngDetail(@RequestParam Map<String, Object> updateMap) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	updateMap.put("updUsr", user.getId());
    	
    	try {
    		updateMap.put("prtFcltySe", prtFcltySe);
    		gamGisPrtFcltyCdMngtService.updateGisPrtFclty(updateMap);
    		gamInfoTechFcltySpecMngService.updateInfoTechFcltySpecMngDetail(updateMap);
    		
    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}
      	return map;		
	}

	/**
	 * 정보통신 시설정보(하위 포함) 삭제
	 * @param map
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/deleteInfoTechFcltySpecMngDetail.do")
    @ResponseBody Map<String, Object> deleteInfoTechFcltySpecMngDetail(@RequestParam Map<String, Object> deleteMap) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		gamInfoTechFcltySpecMngService.deleteInfoTechFcltySpecFileList(deleteMap);
    		gamInfoTechFcltySpecMngService.deleteInfoTechFcltySpecMngDetail(deleteMap);
    		gamGisPrtFcltyCdMngtService.deleteGisPrtFclty(deleteMap);
    		
    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}
      	return map;		
	}
	
	/**
	 * 정보통신 첨부파일 목록 조회
	 * @param map
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/selectInfoTechFcltySpecFileList.do")
    @ResponseBody Map selectInfoTechFcltySpecFileList(GamInfoTechFcltySpecMngVO searchVO) throws Exception {
		Map map = new HashMap();
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List resultList = gamInfoTechFcltySpecMngService.selectInfoTechFcltySpecFileList(searchVO);
		int totCnt = gamInfoTechFcltySpecMngService.selectInfoTechFcltySpecFileListTotCnt(searchVO);
		
        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	return map;
	}
	
	/**
	 * 정보통신 첨부파일 병합저장
	 * @param map
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/mergeInfoTechFcltySpecAtchFile.do")
	@ResponseBody Map<String, Object> mergeInfoTechFcltySpecAtchFile(@RequestParam Map<String, Object> dataList) throws Exception {

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
			gamInfoTechFcltySpecMngService.mergeFcltyFileMngt(mergeMap);
	        map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));
		} catch(Exception e) {
	        map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.merge"));
		}
		return map;
	}
	
}
