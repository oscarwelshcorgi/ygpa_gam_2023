/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.web;

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
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintMngService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintMngVO;

/**
 *
 * @author HNJ
 * @since 2014. 11. 20.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 20.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltyMaintMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name="gamFcltyMaintMngService")
    protected GamFcltyMaintMngService gamFcltyMaintMngService;


	/**
     * 유지보수내역 관리화면호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/fcltyMng/gamFcltyMaintMng.do")
    String indexFcltyMaintMng(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fcltyMng/GamFcltyMaintMng";
    }
	
	
	/**
	 * 유지보수내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/selectFcltyMaintMngList.do")
	@ResponseBody Map<String, Object> selectFcltyMaintMngList(GamFcltyMaintMngVO searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

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

		/** List Data */
		List<?> fcltyMaintMngList = gamFcltyMaintMngService.selectFcltyMaintMngList(searchVO);

        int totCnt = gamFcltyMaintMngService.selectFcltyMaintMngListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", fcltyMaintMngList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	
	/**
	 * 유지보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/selectFcltyMaintMngDetail.do")
	@ResponseBody Map<String, Object> selectFcltyMaintMngDetail(GamFcltyMaintMngVO searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		EgovMap result = null;

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회
    	result = gamFcltyMaintMngService.selectFcltyMaintMngDetail(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("result", result);
    	map.put("searchOption", searchVO);

    	return map;
    }
	

	
	/**
	 * 유지보수 대상시설물 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/selectMntnRprObjFcltsFList.do")
	@ResponseBody Map<String, Object> selectMntnRprObjFcltsFList(GamFcltyMaintMngVO searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

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

		/** List Data */
		List<?> mntnRprObjFcltsFList = gamFcltyMaintMngService.selectMntnRprObjFcltsFList(searchVO);

        int totCnt = gamFcltyMaintMngService.selectMntnRprObjFcltsFListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", mntnRprObjFcltsFList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 유지보수 첨부파일 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/selectFcltyMaintFileList.do")
	@ResponseBody Map<String, Object> selectFcltyMaintFileList(GamFcltyMaintMngVO searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

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

		/** List Data */
		List<?> fcltyMaintFileList = gamFcltyMaintMngService.selectFcltyMaintFileList(searchVO);

        int totCnt = gamFcltyMaintMngService.selectFcltyMaintFileListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", fcltyMaintFileList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 유지보수내역 등록
	 * @param Map
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/insertFcltyMaintMng.do")
    @ResponseBody Map<String, Object> insertFcltyMaintMng(@RequestParam Map<String, Object> fcltyMaintItem) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	int mntnRprSeq;
    	String fcltsMngGroupNo,fcltsJobSe;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	fcltyMaintItem.put("regUsr",user.getId());
    	
    	// 유지보수순번 생성 및 파라메타 전달
    	mntnRprSeq = gamFcltyMaintMngService.selectNextMntnRprSeq(fcltyMaintItem);
    	fcltsMngGroupNo = (String) fcltyMaintItem.get("fcltsMngGroupNo");
    	fcltsJobSe = (String) fcltyMaintItem.get("fcltsJobSe");
    	
    	
    	fcltyMaintItem.put("mntnRprSeq",mntnRprSeq);

    	try {

    		// 유지보수내역 입력
    		gamFcltyMaintMngService.insertFcltyMaintMng(fcltyMaintItem);

    		map.put("resultCode", 0);			// return ok
    		map.put("fcltsMngGroupNo", fcltsMngGroupNo);
    		map.put("fcltsJobSe", fcltsJobSe);
    		map.put("mntnRprSeq", mntnRprSeq);
            map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

      	return map;
    }
	
	
	/**
	 * 유지보수내역 수정
	 * @param Map
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/updateFcltyMaintMng.do")
    @ResponseBody Map<String, Object> updateFcltyMaintMng(@RequestParam Map<String, Object> fcltyMaintItem) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	int mntnRprSeq;
    	String fcltsMngGroupNo,fcltsJobSe;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	fcltyMaintItem.put("updUsr",user.getId());
    	
    	// 유지보수순번 생성 및 파라메타 전달
    	mntnRprSeq = Integer.parseInt((String) fcltyMaintItem.get("mntnRprSeq"));
    	fcltsMngGroupNo = (String) fcltyMaintItem.get("fcltsMngGroupNo");
    	fcltsJobSe = (String) fcltyMaintItem.get("fcltsJobSe");

    	try {

    		// 유지보수내역 입력
    		gamFcltyMaintMngService.updateFcltyMaintMng(fcltyMaintItem);

    		map.put("resultCode", 0);			// return ok
    		map.put("fcltsMngGroupNo", fcltsMngGroupNo);
    		map.put("fcltsJobSe", fcltsJobSe);
    		map.put("mntnRprSeq", mntnRprSeq);
            map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

      	return map;
    }
	
	
	/**
	 * 유지보수내역 삭제
	 * @param Map
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/deleteFcltyMaintMng.do")
    @ResponseBody Map<String, Object> deleteFcltyMaintMng(@RequestParam Map<String, Object> fcltyMaintItem) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	int mntnRprSeq;
    	String fcltsMngGroupNo,fcltsJobSe;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	

    	try {
    		
    		// 유지보수첨부파일 삭제
    		gamFcltyMaintMngService.deleteFcltyMaintFile(fcltyMaintItem);
    		
    		// 유지보수대상시설물 삭제
    		gamFcltyMaintMngService.deleteMntnRprObjFcltsF(fcltyMaintItem);

    		// 유지보수내역 삭제
    		gamFcltyMaintMngService.deleteFcltyMaintMng(fcltyMaintItem);

    		map.put("resultCode", 0);
            map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

      	return map;
    }
	
	
	/**
	 * 유지보수 대상시설물 데이타 적용
	 * @param Map
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/fcltyMng/mergeMntnRprObjFcltsF.do")
    @ResponseBody Map<String, Object> mergeMntnRprObjFcltsF(@RequestParam Map mntnRprObjFcltsFList)throws Exception {

    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String, String> userMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		
		int resultCode;
		String resultMsg;

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
    	
    	insertList = mapper.readValue((String)mntnRprObjFcltsFList.get("insertList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		updateList = mapper.readValue((String)mntnRprObjFcltsFList.get("updateList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		deleteList = mapper.readValue((String)mntnRprObjFcltsFList.get("deleteList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		userList = new ArrayList();
		userMap.put("id",  loginVO.getId());
		userList.add(userMap);

		Map<String,Object> mergeMap = new HashMap<String,Object>();

		insertList.addAll(updateList);

		mergeMap.put("CU", insertList);
		mergeMap.put("D", deleteList);
		mergeMap.put("USER", userList);

    	try {
    		
    		gamFcltyMaintMngService.mergeMntnRprObjFcltsF(mergeMap);
    		
    		map.put("resultCode", 0);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

    	return map;
    }
	
	/**
	 * 유지보수 첨부파일 데이타 적용
	 * @param Map
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/mergeFcltyMaintFile.do")
	@ResponseBody Map<String, Object> mergeFcltyMaintFile(@RequestParam Map<String, Object> fcltyMaintFileList) throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String, String> userMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

    	List<HashMap<String,String>> insertList=null;
    	List<HashMap<String,String>> updateList=null;
    	List<HashMap<String,String>> deleteList=null;
    	List<Map<String,String>> userList=null;

    	int resultCode = -1;
    	String resultMsg = "";

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		insertList = mapper.readValue((String)fcltyMaintFileList.get("insertList"),
		    new TypeReference<List<HashMap<String,String>>>(){});

		updateList = mapper.readValue((String)fcltyMaintFileList.get("updateList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		deleteList = mapper.readValue((String)fcltyMaintFileList.get("deleteList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		userList = new ArrayList();
		userMap.put("id",  loginVO.getId());
		userList.add(userMap);

		Map<String,Object> mergeMap = new HashMap<String,Object>();

		insertList.addAll(updateList);

		mergeMap.put("CU", insertList);
		mergeMap.put("D", deleteList);
		mergeMap.put("USER", userList);

		gamFcltyMaintMngService.mergeFcltyMaintFile(mergeMap);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));

		return map;
	}
	
	
	
	
	/**
     * 유지보수 대상시설물 추가편집 팝업
     *
     * 유지보수 대상시설물 팝업
     */
	@RequestMapping(value="/popup/selectMntnRprObjFcltsFPopup.do")
    String selectMntnRprObjFcltsFPopup(@RequestParam Map selectMntnRprObjFcltsF, ModelMap model) throws Exception {

		model.addAttribute("selectMntnRprObjFcltsF", selectMntnRprObjFcltsF);
    	return "/ygpa/gam/fcltyMng/GamPopupMntnRprObjFcltsF";
    }
	
	

	

}