/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltySpecMngVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 6.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 6.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamCivilFcltySpecMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="gamCivilFcltySpecMngService")
    GamCivilFcltySpecMngService gamCivilFcltySpecMngService;
    
	/**
     * 토목시설 관리화면호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/fclty/gamCivilFcltySpecMng.do")
    String indexCivilFcltySpecMng(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fclty/GamCivilFcltySpecMng";
    }


	/**
	 * 토목시설관리목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/selectCivilFcltySpecMngList.do")
	@ResponseBody Map<String, Object> selectCivilFcltySpecMngList(GamCivilFcltySpecMngVO searchVO) throws Exception {

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

		List resultList = gamCivilFcltySpecMngService.selectCivilFcltySpecMngList(searchVO);
		int totCnt = gamCivilFcltySpecMngService.selectCivilFcltySpecMngListTotCnt(searchVO);
		
        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }


	/**
	 * 토목시설관리목록 엑셀다운로드
	 * @param map
	 * @return modelAndView
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/excelDownloadCivilFcltySpecMngList.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView excelDownloadCivilFcltySpecMngList(@RequestParam Map<String, Object> excelParam) throws Exception {

		Map map = new HashMap();
		List header;
		ObjectMapper mapper = new ObjectMapper();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return new ModelAndView("gridExcelView", "gridResultMap", map);
		}

		header = mapper.readValue((String)excelParam.get("header"),
								  new TypeReference<List<HashMap<String,String>>>(){});
		excelParam.remove("header");

		GamCivilFcltySpecMngVO searchVO= new GamCivilFcltySpecMngVO();
		searchVO = mapper.convertValue(excelParam, GamCivilFcltySpecMngVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamCivilFcltySpecMngService.selectCivilFcltySpecMngList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);
	}
	
	/**
	 * 토목 시설관리 상세
	 * @param fcltyManageVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/selectCivilFcltySpecMngDetail.do")
    @ResponseBody Map<String, Object> selectCivilFcltySpecMngDetail(@RequestParam Map searchVO) throws Exception {
    	Map map = new HashMap();
    	EgovMap result=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
        	result = gamCivilFcltySpecMngService.selectCivilFcltySpecMngDetail(searchVO);
            map.put("resultCode", 0);
            map.put("result", result);
    	} catch(IOException e){
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
    	}
    	catch(Exception e) {
            map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
    	}
        return map;		
	}
	

	/**
	 * 토목 시설정보 삽입
	 * @param map
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/insertCivilFcltySpecMngDetail.do")
    @ResponseBody Map<String, Object> insertCivilFcltySpecMngDetail(@RequestParam Map<String, Object> insertMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = null;
    	Map<String, String> detailForm = null;
    	List<HashMap<String, String>> insertAtchFileList = null;
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	mapper = new ObjectMapper();
    	
    	detailForm = mapper.readValue((String)insertMap.get("detailForm"),
    		    new TypeReference<HashMap<String,String>>(){});
    	
    	insertAtchFileList = mapper.readValue((String)insertMap.get("insertAtchFileList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});
    	
    	detailForm.put("regUsr", user.getId());
    	
    	try {
    		gamCivilFcltySpecMngService.insertCivilFcltySpecMngDetail(detailForm, insertAtchFileList);
    		
    		map.put("resultCode", 0);			// return ok
    		map.put("gisPrtFcltySeq", insertMap.get("gisPrtFcltySeq"));
    		map.put("fcltsMngNo", detailForm.get("fcltsMngNo"));
            map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}
      	return map;		
	}
	
	/**
	 * 토목 시설정보 수정
	 * @param map
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/updateCivilFcltySpecMngDetail.do")
    @ResponseBody Map<String, Object> updateCivilFcltySpecMngDetail(@RequestParam Map<String, Object> updateMap) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	ObjectMapper mapper = null;
    	Map<String, String> detailForm = null;
    	List<HashMap<String, String>> insertAtchFileList = null;
    	List<HashMap<String, String>> updateAtchFileList = null;
    	List<HashMap<String, String>> deleteAtchFileList = null;
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	mapper = new ObjectMapper();
    	
    	detailForm = mapper.readValue((String)updateMap.get("detailForm"),
    		    new TypeReference<HashMap<String,String>>(){});
    	
    	insertAtchFileList = mapper.readValue((String)updateMap.get("insertAtchFileList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});
    	updateAtchFileList = mapper.readValue((String)updateMap.get("updateAtchFileList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});
    	deleteAtchFileList = mapper.readValue((String)updateMap.get("deleteAtchFileList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});
    	
    	
    	List<Map<String,String>> userList = new ArrayList<Map<String,String>>();
    	Map<String, String> userMap = new HashMap<String, String>(); 
		userMap.put("id",  user.getId());
		userList.add(userMap);

		Map<String,Object> mergeMap = new HashMap<String,Object>();
		
		insertAtchFileList.addAll(updateAtchFileList);

		mergeMap.put("CU", insertAtchFileList);
		mergeMap.put("D", deleteAtchFileList);
		mergeMap.put("USER", userList);
		
		detailForm.put("updUsr",user.getId());
    	
    	try {
    		gamCivilFcltySpecMngService.updateCivilFcltySpecMngDetail(detailForm, mergeMap);
    		
    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}
      	return map;		
	}

	/**
	 * 토목 시설정보(하위 포함) 삭제
	 * @param map
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/deleteCivilFcltySpecMngDetail.do")
    @ResponseBody Map<String, Object> deleteCivilFcltySpecMngDetail(@RequestParam Map<String, Object> deleteMap) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		gamCivilFcltySpecMngService.deleteCivilFcltySpecMngDetail(deleteMap);
    		
    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}
      	return map;		
	}
	
	/**
	 * 토목 첨부파일 목록 조회
	 * @param map
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/selectCivilFcltySpecFileList.do")
    @ResponseBody Map selectCivilFcltySpecFileList(GamCivilFcltySpecMngVO searchVO) throws Exception {
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

		List resultList = gamCivilFcltySpecMngService.selectCivilFcltySpecFileList(searchVO);
		int totCnt = gamCivilFcltySpecMngService.selectCivilFcltySpecFileListTotCnt(searchVO);
		
        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	return map;
	}
	
}
