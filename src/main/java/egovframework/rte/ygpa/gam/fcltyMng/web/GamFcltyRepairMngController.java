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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
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
	public @RequestMapping(value="/fcltyMng/gamFcltyRepairMng.do")
    String indexFcltyRepairMng(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fcltyMng/GamFcltyRepairMng_";
    }
	
	
	/**
	 * 하자보수내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/selectFcltyRepairMngList.do")
	public @ResponseBody Map selectFcltyRepairMngList(GamFcltyRepairMngVO searchVO)throws Exception {

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

		/** List Data */
		List fcltyRepairMngList = gamFcltyRepairMngService.selectFcltyRepairMngList(searchVO);

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/selectFcltyRepairMngDetail.do")
	public @ResponseBody Map selectFcltyRepairMngDetail(GamFcltyRepairMngVO searchVO)throws Exception {

		Map map = new HashMap();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/selectFlawRprObjFcltsF.do")
	public @ResponseBody Map selectFlawRprObjFcltsFList(GamFcltyRepairMngVO searchVO)throws Exception {

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

		/** List Data */
		List flawRprObjFcltsFList = gamFcltyRepairMngService.selectFlawRprObjFcltsFList(searchVO);

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/selectFlawExamUsrFList.do")
	public @ResponseBody Map selectFlawExamUsrFList(GamFcltyRepairMngVO searchVO)throws Exception {

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

		/** List Data */
		List flawExamUsrFList = gamFcltyRepairMngService.selectFlawExamUsrFList(searchVO);

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/selectFcltyRepairFileList.do")
	public @ResponseBody Map selectFcltyRepairFileList(GamFcltyRepairMngVO searchVO)throws Exception {

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

		/** List Data */
		List fcltyRepairFileList = gamFcltyRepairMngService.selectFcltyRepairFileList(searchVO);

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/insertFcltyRepairMng.do")
    public @ResponseBody Map insertFcltyRepairMng(@RequestParam Map fcltyRepairItem) throws Exception {

    	Map map = new HashMap();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/updateFcltyRepairMng.do")
    public @ResponseBody Map updateFcltyRepairMng(@RequestParam Map fcltyRepairItem) throws Exception {

    	Map map = new HashMap();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/deleteFcltyRepairMng.do")
    public @ResponseBody Map deleteFcltyRepairMng(@RequestParam Map fcltyRepairItem) throws Exception {

    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	

    	try {

    		// 하자보수내역 삭제
    		gamFcltyRepairMngService.deleteFcltyRepairMng(fcltyRepairItem);

    		map.put("resultCode", 0);
            map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
            
		} catch (Exception e) {
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/fcltyMng/mergeFlawRprObjFcltsF.do")
    public @ResponseBody Map mergeFlawRprObjFcltsF(@RequestParam Map mergeFlawRprObjFcltsFList)throws Exception {

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/fcltyMng/mergeFlawExamUsrF.do")
    public @ResponseBody Map mergeFlawExamUsrF(@RequestParam Map mntnRprObjFcltsFList)throws Exception {

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/mergeFcltyRepairFile.do")
	public @ResponseBody Map mergeFcltyRepairFile(@RequestParam Map fcltyRepairFileList) throws Exception {

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
	 * 시설물 하자보수관리 리스트를 엑셀로 다운로드한다.
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/fcltyMng/selectFcltyRepairMngListExcel.do", method=RequestMethod.POST)
    public @ResponseBody ModelAndView selectFcltyRepairMngListExcel(@RequestParam Map<String, Object> excelParam) throws Exception {
		Map map = new HashMap();
		List header;
		ObjectMapper mapper = new ObjectMapper();

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    		return new ModelAndView("gridExcelView", "gridResultMap", map);
    	}

    	// 환경설정
    	/** EgovPropertyService */
    	GamFcltyRepairMngVO searchVO= new GamFcltyRepairMngVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.
		// 조회 조건
		searchVO = mapper.convertValue(excelParam, GamFcltyRepairMngVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		
		//계약이력목록 
    	List fcltyRepairMngList = gamFcltyRepairMngService.selectFcltyRepairMngList(searchVO);
		

    	map.put("resultList", fcltyRepairMngList);
    	map.put("header", header);

    	return new ModelAndView("gridExcelView", "gridResultMap", map);
    }
    
    
    /**
     * 하자검사조서인쇄
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/fcltyMng/selectFcltyRepairCheckReportPrint.do")
	public String selectFcltyRepairCheckReportPrint(@RequestParam Map<String, Object> fcltyRepairCheckReportOpt, ModelMap model) throws Exception {

    	Map map = new HashMap();
    	EgovMap result = null;

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/fcltyMng/GamFcltyRepairCheckReportPrint";
    	}

		ObjectMapper mapper = new ObjectMapper();
		
		GamFcltyRepairMngVO searchVO;
    	searchVO = mapper.convertValue(fcltyRepairCheckReportOpt, GamFcltyRepairMngVO.class);

		
		//하자검사조서
    	result = gamFcltyRepairMngService.selectFcltyRepairCheckReport(searchVO);
    	
        model.addAttribute("result", result);
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");

    	return "ygpa/gam/fcltyMng/GamFcltyRepairCheckReportPrint";
    }
	
	
	/**
     * 하자만료검사조서인쇄
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/fcltyMng/selectFcltyRepairExpireCheckReportPrint.do")
	public String selectFcltyRepairExpireCheckReportPrint(@RequestParam Map<String, Object> fcltyRepairExpireCheckReportOpt, ModelMap model) throws Exception {

    	Map map = new HashMap();
    	EgovMap result = null;

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/fcltyMng/GamFcltyRepairExpireCheckReportPrint";
    	}

		ObjectMapper mapper = new ObjectMapper();
		
		GamFcltyRepairMngVO searchVO;
    	searchVO = mapper.convertValue(fcltyRepairExpireCheckReportOpt, GamFcltyRepairMngVO.class);

		
		//하자만료검사조서
    	result = gamFcltyRepairMngService.selectFcltyRepairCheckReport(searchVO);
    	
        model.addAttribute("result", result);
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");

    	return "ygpa/gam/fcltyMng/GamFcltyRepairExpireCheckReportPrint";
    }
	
	
	/**
     * 하자검사결과인쇄
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/fcltyMng/selectFcltyRepairCheckResultPrint.do")
	public String selectFcltyRepairCheckResultPrint(@RequestParam Map<String, Object> fcltyRepairCheckResultOpt, ModelMap model) throws Exception {

    	Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/fcltyMng/GamFcltyRepairCheckResultPrint";
    	}

		ObjectMapper mapper = new ObjectMapper();
		
		GamFcltyRepairMngVO searchVO;
    	searchVO = mapper.convertValue(fcltyRepairCheckResultOpt, GamFcltyRepairMngVO.class);
    	
    	searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		
		//계약이력목록
    	List fcltyRepairMngList = gamFcltyRepairMngService.selectFcltyRepairMngList(searchVO);
    	
        model.addAttribute("resultList", fcltyRepairMngList);
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");

    	return "ygpa/gam/fcltyMng/GamFcltyRepairCheckResultPrint";
    }
	

}