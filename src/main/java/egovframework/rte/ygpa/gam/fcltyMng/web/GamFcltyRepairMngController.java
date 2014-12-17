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
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairMngService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairMngVO;

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
public class GamFcltyRepairMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

//	@Resource(name = "gamFcltyRepairMngService")
//	protected GamFcltyRepairMngService gamFcltyRepairMngService;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name="gamFcltyRepairMngService")
    protected GamFcltyRepairMngService gamFcltyRepairMngService;


	/**
     * 하자보수내역 관리화면호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/fcltyMng/gamFcltyRepairMng.do")
    String indexFcltyRepairMng(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fcltyMng/GamFcltyRepairMng";
    }
	
	
	/**
	 * 하자보수내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/selectFcltyRepairMngList.do")
	@ResponseBody Map<String, Object> selectFcltyRepairMngList(GamFcltyRepairMngVO searchVO)throws Exception {

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
		List<?> fcltyRepairMngList = gamFcltyRepairMngService.selectFcltyRepairMngList(searchVO);

        int totCnt = gamFcltyRepairMngService.selectFcltyRepairMngListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", fcltyRepairMngList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 하자보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/selectFcltyRepairMngDetail.do")
	@ResponseBody Map<String, Object> selectFcltyRepairMngDetail(GamFcltyRepairMngVO searchVO)throws Exception {

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
    	result = gamFcltyRepairMngService.selectFcltyRepairMngDetail(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("result", result);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 하자보수 대상시설물 조회
	 * @param GamFcltyRepairMngVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/selectFlawRprObjFcltsF.do")
	@ResponseBody Map<String, Object> selectFlawRprObjFcltsFList(GamFcltyRepairMngVO searchVO)throws Exception {

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
		List<?> flawRprObjFcltsFList = gamFcltyRepairMngService.selectFlawRprObjFcltsFList(searchVO);

        int totCnt = gamFcltyRepairMngService.selectFlawRprObjFcltsFListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", flawRprObjFcltsFList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 하자보수 검사자 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/selectFlawExamUsrFList.do")
	@ResponseBody Map<String, Object> selectFlawExamUsrFList(GamFcltyRepairMngVO searchVO)throws Exception {

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
		List<?> flawExamUsrFList = gamFcltyRepairMngService.selectFlawExamUsrFList(searchVO);

        int totCnt = gamFcltyRepairMngService.selectFlawExamUsrFListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", flawExamUsrFList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 하자보수 첨부파일 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/selectFcltyRepairFileList.do")
	@ResponseBody Map<String, Object> selectFcltyRepairFileList(GamFcltyRepairMngVO searchVO)throws Exception {

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
		List<?> fcltyRepairFileList = gamFcltyRepairMngService.selectFcltyRepairFileList(searchVO);

        int totCnt = gamFcltyRepairMngService.selectFcltyRepairFileListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", fcltyRepairFileList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 하자보수내역 등록
	 * @param Map
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/insertFcltyRepairMng.do")
    @ResponseBody Map<String, Object> insertFcltyRepairMng(@RequestParam Map<String, Object> fcltyRepairItem) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	int flawRprSeq;
    	String fcltsMngGroupNo,fcltsJobSe;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	fcltyRepairItem.put("regUsr",user.getId());
    	
    	// 하자보수순번 생성 및 파라메타 전달
    	flawRprSeq = gamFcltyRepairMngService.selectNextMntnRprSeq(fcltyRepairItem);
    	fcltsMngGroupNo = (String) fcltyRepairItem.get("fcltsMngGroupNo");
    	fcltsJobSe = (String) fcltyRepairItem.get("fcltsJobSe");
    	
    	
    	fcltyRepairItem.put("flawRprSeq",flawRprSeq);

    	try {

    		// 하자보수내역 입력
    		gamFcltyRepairMngService.insertFcltyRepairMng(fcltyRepairItem);

    		map.put("resultCode", 0);			// return ok
    		map.put("fcltsMngGroupNo", fcltsMngGroupNo);
    		map.put("fcltsJobSe", fcltsJobSe);
    		map.put("flawRprSeq", flawRprSeq);
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
	 * 하자보수내역 수정
	 * @param Map
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/updateFcltyRepairMng.do")
    @ResponseBody Map<String, Object> updateFcltyRepairMng(@RequestParam Map<String, Object> fcltyRepairItem) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	int flawRprSeq;
    	String fcltsMngGroupNo,fcltsJobSe;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	fcltyRepairItem.put("updUsr",user.getId());
    	
    	// 하자보수순번 생성 및 파라메타 전달
    	flawRprSeq = Integer.parseInt((String) fcltyRepairItem.get("flawRprSeq"));
    	fcltsMngGroupNo = (String) fcltyRepairItem.get("fcltsMngGroupNo");
    	fcltsJobSe = (String) fcltyRepairItem.get("fcltsJobSe");

    	try {

    		// 하자보수내역 입력
    		gamFcltyRepairMngService.updateFcltyRepairMng(fcltyRepairItem);

    		map.put("resultCode", 0);			// return ok
    		map.put("fcltsMngGroupNo", fcltsMngGroupNo);
    		map.put("fcltsJobSe", fcltsJobSe);
    		map.put("flawRprSeq", flawRprSeq);
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
	 * 하자보수내역 삭제
	 * @param Map
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/deleteFcltyRepairMng.do")
    @ResponseBody Map<String, Object> deleteFcltyRepairMng(@RequestParam Map<String, Object> fcltyRepairItem) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	int flawRprSeq;
    	String fcltsMngGroupNo,fcltsJobSe;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	

    	try {
    		
    		// 하자보수첨부파일 삭제
    		gamFcltyRepairMngService.deleteFcltyRepairFile(fcltyRepairItem);
    		
    		// 하자보수검사자 삭제
    		gamFcltyRepairMngService.deleteFlawExamUsrF(fcltyRepairItem);
    		
    		// 하자보수대상시설물 삭제
    		gamFcltyRepairMngService.deleteFlawRprObjFcltsF(fcltyRepairItem);

    		// 하자보수내역 삭제
    		gamFcltyRepairMngService.deleteFcltyRepairMng(fcltyRepairItem);

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
	 * 하자보수 대상시설물 데이타 적용
	 * @param Map
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/fcltyMng/mergeFlawRprObjFcltsF.do")
    @ResponseBody Map<String, Object> mergeFlawRprObjFcltsF(@RequestParam Map mergeFlawRprObjFcltsFList)throws Exception {

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
    	
    	insertList = mapper.readValue((String)mergeFlawRprObjFcltsFList.get("insertList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		updateList = mapper.readValue((String)mergeFlawRprObjFcltsFList.get("updateList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		deleteList = mapper.readValue((String)mergeFlawRprObjFcltsFList.get("deleteList"),
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
    		
    		gamFcltyRepairMngService.mergeFlawRprObjFcltsF(mergeMap);
    		
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
	 * 하자보수 검사자 데이타 적용
	 * @param Map
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/fcltyMng/mergeFlawExamUsrF.do")
    @ResponseBody Map<String, Object> mergeFlawExamUsrF(@RequestParam Map mntnRprObjFcltsFList)throws Exception {

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
    		
    		gamFcltyRepairMngService.mergeFlawExamUsrF(mergeMap);
    		
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
	 * 하자보수 첨부파일 데이타 적용
	 * @param Map
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/mergeFcltyRepairFile.do")
	@ResponseBody Map<String, Object> mergeFcltyRepairFile(@RequestParam Map<String, Object> fcltyRepairFileList) throws Exception {

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

		insertList = mapper.readValue((String)fcltyRepairFileList.get("insertList"),
		    new TypeReference<List<HashMap<String,String>>>(){});

		updateList = mapper.readValue((String)fcltyRepairFileList.get("updateList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		deleteList = mapper.readValue((String)fcltyRepairFileList.get("deleteList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		userList = new ArrayList();
		userMap.put("id",  loginVO.getId());
		userList.add(userMap);

		Map<String,Object> mergeMap = new HashMap<String,Object>();

		insertList.addAll(updateList);

		mergeMap.put("CU", insertList);
		mergeMap.put("D", deleteList);
		mergeMap.put("USER", userList);

		gamFcltyRepairMngService.mergeFcltyRepairFile(mergeMap);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));

		return map;
	}
	
	
	/**
	 * 하자보수대상물목록 수정 팝업
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value="/popup/showFlawRprObjFcltsPopup.do")
    String showFlawRprObjFcltsPopup(@RequestParam Map flawRprObjFcltsList, ModelMap model) throws Exception {
		model.addAttribute("flawRprObjFcltsList", flawRprObjFcltsList);
    	return "/ygpa/gam/fcltyMng/GamPopupFlawRprObjFclts";
    }
	
	
	
	/**
     * 하자보수 검사자 추가편집 팝업
     *
     * 하자보수 검사자 팝업
     */
	@RequestMapping(value="/popup/selectFlawExamUsrFPopup.do")
    String selectFlawExamUsrFPopup(@RequestParam Map selectFlawExamUsrF, ModelMap model) throws Exception {

		model.addAttribute("selectFlawExamUsrF", selectFlawExamUsrF);
    	return "/ygpa/gam/fcltyMng/GamPopupFlawExamUsrF";
    }
	
	

	

}