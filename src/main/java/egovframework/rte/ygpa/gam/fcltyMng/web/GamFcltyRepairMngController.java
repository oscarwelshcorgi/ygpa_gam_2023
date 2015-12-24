/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.cmmn.service.GamFileServiceVo;
import egovframework.rte.ygpa.gam.cmmn.service.GamFileUploadUtil;
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

    @Resource(name="gamRepairFileIdGnrService")
    EgovTableIdGnrService gamRepairFileIdGnrService;

    @Resource(name="basicService")
    EgovTableIdGnrService basicService;
     
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
    	return "/ygpa/gam/fcltyMng/GamFcltyRepairMng";
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

		GamFcltyRepairMngVO resultSum = gamFcltyRepairMngService.selectFcltyRepairMngListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(resultSum.getTotCnt());
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", resultSum.getTotCnt());
    	map.put("sumFlawRprAmt", resultSum.getSumFlawRprAmt());
    	map.put("resultList", fcltyRepairMngList);

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

		/** List Data */
		List fcltyRepairFileList = gamFcltyRepairMngService.selectFcltyRepairFileList(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", fcltyRepairFileList);

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

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map map = new HashMap();
    	ObjectMapper mapper = new ObjectMapper();
    	List<HashMap<String,String>> insertObjList=null;
    	//List<HashMap<String,String>> insertFileList=null;
    	Map insertRprData = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	insertRprData = mapper.readValue((String)fcltyRepairItem.get("fcltyRepairMngListVO"),
    		    new TypeReference<HashMap<String,String>>(){});

    	insertObjList = mapper.readValue((String)fcltyRepairItem.get("insertObjList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

    	/*insertFileList = mapper.readValue((String)fcltyRepairItem.get("insertRepairFileList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});*/

    	insertRprData.put("regUsr",user.getId());

    	try {

    		// 하자보수내역 입력
    		gamFcltyRepairMngService.insertFcltyRepairMng(insertRprData, insertObjList);

    		map.put("resultCode", 0);			// return ok
    		map.put("flawRprSeq", insertRprData.get("flawRprSeq"));
            map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));

		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

      	return map;
    }

	@RequestMapping(value="/fcltyMng/insertFcltyRepairMngAtchFile.do")
	@ResponseBody Map<String, Object> insertFcltyRepairMngAtchFile(GamFcltyRepairMngVO gamFcltyRepairMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();
		String sNewSeq;

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			/*sNewSeq = basicService.getNextStringId();
			gamFcltyRepairMngVO.setAtchFileSeq(sNewSeq.replace("*", ""));
			sNewSeq = gamFcltyRepairMngVO.getAtchFileSeq();*/
			
			sNewSeq = gamFcltyRepairMngService.selectFcltyRepairMngAtchFileNewSeq(gamFcltyRepairMngVO);
			gamFcltyRepairMngVO.setAtchFileSeq(sNewSeq);
			
			gamFcltyRepairMngVO.setRegUsr((String)user.getId());
			gamFcltyRepairMngService.insertFcltyRepairMngAtchFile(gamFcltyRepairMngVO);
			
			map.put("atchFileSeq", sNewSeq);
			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}
		return map;
	}
	
	@RequestMapping(value="/fcltyMng/selectFcltyRepairMngAtchFileSeq.do")
	@ResponseBody Map<String, Object> selectFcltyRepairMngAtchFileSeq(GamFcltyRepairMngVO gamFcltyRepairMngVO) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();
		String sNewSeq;

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		
		try {
			sNewSeq = gamFcltyRepairMngService.selectFcltyRepairMngAtchFileNewSeq(gamFcltyRepairMngVO);
			map.put("atchFileSeq", sNewSeq);
			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/deleteFcltyRepairMngAtchFile.do")
	@ResponseBody Map<String, Object> deleteFcltyRepairMngAtchFile(@RequestParam Map deleteVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyRepairMngService.deleteFcltyRepairMngAtchFile(deleteVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
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
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map map = new HashMap();
    	ObjectMapper mapper = new ObjectMapper();
    	List<HashMap<String,String>> insertObjList=null;
    	List<HashMap<String,String>> updateObjList=null;
    	List<HashMap<String,String>> deleteObjList=null;
    	List<HashMap<String,String>> insertFileList=null;
    	List<HashMap<String,String>> deleteFileList=null;

    	List<Map<String,String>> userList=null;
    	Map insertRprData = new HashMap();

    	Map<String, String> userMap = new HashMap<String, String>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	insertRprData = mapper.readValue((String)fcltyRepairItem.get("fcltyRepairMngListVO"),
    		    new TypeReference<HashMap<String,String>>(){});
  //  	insertRprData.put("atchFileSeq", fcltyRepairItem.get("atchFileSeq"));


    	insertObjList = mapper.readValue((String)fcltyRepairItem.get("insertObjList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});
    	updateObjList = mapper.readValue((String)fcltyRepairItem.get("updateObjList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});
    	deleteObjList = mapper.readValue((String)fcltyRepairItem.get("deleteObjList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

    	/*insertFileList = mapper.readValue((String)fcltyRepairItem.get("insertRepairFileList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

    	deleteFileList = mapper.readValue((String)fcltyRepairItem.get("deleteRepairFileList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});*/

    	userList = new ArrayList();
		userMap.put("id",  user.getId());
		userList.add(userMap);

		Map<String,Object> mergeMap = new HashMap<String,Object>();

		insertObjList.addAll(updateObjList);

		mergeMap.put("CU", insertObjList);
		mergeMap.put("D", deleteObjList);
		mergeMap.put("USER", userList);

    	insertRprData.put("regUsr",user.getId());

   		/*for( int i = 0 ; i < deleteFileList.size() ; i++ ) {


			Map resultMap = deleteFileList.get(i);

			GamFcltyRepairMngVO deleteFileVO = new GamFcltyRepairMngVO();
			deleteFileVO.setFcltsMngGroupNo(resultMap.get("fcltsMngGroupNo").toString());
			deleteFileVO.setFcltsJobSe(resultMap.get("fcltsJobSe").toString());
			deleteFileVO.setFlawRprSeq(resultMap.get("flawRprSeq").toString());
			deleteFileVO.setAtchFileSeq(resultMap.get("atchFileSeq").toString());


			gamFcltyRepairMngService.deleteFcltyRepairMngList(deleteFileVO);
		}*/

    	try {

    		// 하자보수내역 입력
    		gamFcltyRepairMngService.updateFcltyRepairMng(insertRprData, mergeMap);

    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));

		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
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
	public String selectFcltyRepairCheckReportPrint(@RequestParam Map<String, Object> fcltyRepairCheckReportOpt ,GamFcltyRepairMngVO searchVO, ModelMap model) throws Exception {

		//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	Map map = new HashMap();
    	EgovMap result = null;
    	EgovMap charger= null;

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/fcltyMng/GamFcltyRepairCheckReportPrint";
    	}
    	ObjectMapper mapper = new ObjectMapper();

    	//하자검사자직인
    	charger=gamFcltyRepairMngService.selectFcltyRepairCheckReportCharger(searchVO);
    	//하자검사조서
    	result = gamFcltyRepairMngService.selectFcltyRepairCheckReport(searchVO);
    	//첨부파일이미지
    	List resultList = gamFcltyRepairMngService.selectFcltyRepairFileList(searchVO);

        model.addAttribute("result", result);
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("charger",charger);
		model.addAttribute("resultList",resultList);
		if(fcltyRepairCheckReportOpt.get("filename") != null){
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", fcltyRepairCheckReportOpt.get("filename"));
    		}

    	return "ygpa/gam/fcltyMng/GamFcltyRepairCheckReportPrint";
    }

    /**
     * 선택된 하자검사조서 리스트 한글파일 문서 출력
     *
     * @param searchVO
     * @return xml string
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/fcltyMng/selectFcltyRepairCheckReportListHwp.do")
	public String selectFcltyRepairCheckSelectedReportListHwp(@RequestParam Map<String, Object> fcltyRepairCheckReportOpt , ModelMap model) throws Exception {

    	Map map = new HashMap();
    	List<HashMap<String,String>> reportList = null;
		ObjectMapper mapper = new ObjectMapper();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "ygpa/gam/fcltyMng/GamFcltyRepairCheckReportHwp";
    	}
 
		reportList = mapper.readValue((String)fcltyRepairCheckReportOpt.get("downList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});
   	
    	String hwpML = gamFcltyRepairMngService.selectFcltyRepairCheckReportListHWPML(reportList);
    	
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("hwpML", hwpML);
    	
		if(fcltyRepairCheckReportOpt.get("filename") != null) {
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", fcltyRepairCheckReportOpt.get("filename"));
    	}
	
		return "ygpa/gam/fcltyMng/GamFcltyRepairCheckReportHwp";
    }
	
    /**
     * 하자검사조서 한글파일 문서 출력
     *
     * @param searchVO
     * @return xml string
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/fcltyMng/selectFcltyRepairCheckReportHwp.do")
	public String selectFcltyRepairCheckReportHwp(@RequestParam Map<String, Object> fcltyRepairCheckReportOpt ,GamFcltyRepairMngVO searchVO, ModelMap model) throws Exception {

    	Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/fcltyMng/GamFcltyRepairCheckReportHwp";
    	}
    	
    	String hwpML = gamFcltyRepairMngService.selectFcltyRepairCheckReportHWPML(searchVO);
    	
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("hwpML", hwpML);
	
		if(fcltyRepairCheckReportOpt.get("filename") != null) {
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", fcltyRepairCheckReportOpt.get("filename"));
    	}
    	
		return "ygpa/gam/fcltyMng/GamFcltyRepairCheckReportHwp";
    }
	
/*
	@RequestMapping(value="/fcltyMng/selectFcltyRepairCheckReportHwp2.do")
	String selectFcltyRepairCheckReportHwp2(@RequestParam Map<String, Object> fcltyRepairCheckReportOpt ,GamFcltyRepairMngVO searchVO, ModelMap model) throws Exception {
		List varItems = new ArrayList<Map>();

		//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	Map map = new HashMap();
    	EgovMap result = null;
    	EgovMap charger= null;

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    		return "/ygpa/gam/cmmn/GamHwpOcxPreview";
    	}

    	ObjectMapper mapper = new ObjectMapper();

    	//하자검사자 직인
    	charger=gamFcltyRepairMngService.selectFcltyRepairCheckReportCharger(searchVO);
    	//하자검사조서
    	result = gamFcltyRepairMngService.selectFcltyRepairCheckReport(searchVO);
    	//첨부파일이미지
    	List resultList = gamFcltyRepairMngService.selectFcltyRepairFileList(searchVO);



        model.addAttribute("result", result);
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("charger",charger);
		model.addAttribute("resultList",resultList);
		if(fcltyRepairCheckReportOpt.get("filename") != null){
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", fcltyRepairCheckReportOpt.get("filename"));
    		}

		model.addAttribute("hwpTemplateName", "fclty_rpr_templ.hwp");	// 파일 명을 지정 한다. (/tmpl 기준)

		// 변수 전달
		map.put("name", "title");
		map.put("value", "제목을 서버에서 전송 합니다.");
		map.put("type", "TEXT");
		varItems.add(map);
		map = new HashMap();
		map.put("name", "name");
		map.put("value", "테스트");
		map.put("type", "TEXT");
		varItems.add(map);
		map = new HashMap();
		map.put("name", "date");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD");
		map.put("value", format.format(new Date()));
		map.put("type", "TEXT");
		varItems.add(map);

		model.addAttribute("varItems", varItems);

		return "/ygpa/gam/cmmn/GamHwpOcxPreview";

	}
*/

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
    	EgovMap charger= null;
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
    	//하자검사자 직인
    	charger=gamFcltyRepairMngService.selectFcltyRepairCheckReportCharger(searchVO);

    	model.addAttribute("result", result);
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("charger",charger);
		if(fcltyRepairExpireCheckReportOpt.get("filename") != null){
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", fcltyRepairExpireCheckReportOpt.get("filename"));
    		}

    	return "ygpa/gam/fcltyMng/GamFcltyRepairExpireCheckReportPrint";
    }

	/**
     * 하자만료검사조서 한글파일 출력
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/fcltyMng/selectFcltyRepairExpireCheckReportHwp.do")
	public String selectFcltyRepairExpireCheckReportHwp(@RequestParam Map<String, Object> fcltyRepairExpireCheckReportOpt, ModelMap model) throws Exception {

    	Map map = new HashMap();
    	EgovMap result = null;
    	EgovMap charger= null;
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/fcltyMng/GamFcltyRepairExpireCheckReportHwp";
    	}

		ObjectMapper mapper = new ObjectMapper();

		GamFcltyRepairMngVO searchVO;
    	searchVO = mapper.convertValue(fcltyRepairExpireCheckReportOpt, GamFcltyRepairMngVO.class);


		//하자만료검사조서
    	result = gamFcltyRepairMngService.selectFcltyRepairCheckReport(searchVO);
    	//하자검사자 직인
    	charger=gamFcltyRepairMngService.selectFcltyRepairCheckReportCharger(searchVO);

    	model.addAttribute("result", result);
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("charger",charger);
		if(fcltyRepairExpireCheckReportOpt.get("filename") != null){
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", fcltyRepairExpireCheckReportOpt.get("filename"));
    		}

    	return "ygpa/gam/fcltyMng/GamFcltyRepairExpireCheckReportHwp";
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

		//hwp선택시 파일명
		if(fcltyRepairCheckResultOpt.get("filename") != null){
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", fcltyRepairCheckResultOpt.get("filename"));
    		}

    	return "ygpa/gam/fcltyMng/GamFcltyRepairCheckResultPrint";
    }


	/**
     * 하자검사관리대장인쇄
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/fcltyMng/selectFcltyRepairCheckMngPrint.do")
	public String selectFcltyRepairCheckMngPrint(@RequestParam Map<String, Object> fcltyRepairCheckMngOpt, ModelMap model) throws Exception {

    	Map map = new HashMap();
    	EgovMap result = null;
    	int totCnt;

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/fcltyMng/GamFcltyRepairCheckMngPrint";
    	}

		ObjectMapper mapper = new ObjectMapper();

		GamFcltyRepairMngVO searchVO;
    	searchVO = mapper.convertValue(fcltyRepairCheckMngOpt, GamFcltyRepairMngVO.class);

		//하자검사관리대장인쇄
    	result = gamFcltyRepairMngService.selectFcltyRepairCheckMng(searchVO);

		String ctrtNo = (String) result.get("ctrtNo");

		//하자보증내용
    	List fcltyRepairMngListPerCtrt = gamFcltyRepairMngService.selectFcltyRepairMngListPerCtrt(ctrtNo);

    	totCnt =  gamFcltyRepairMngService.selectFcltyRepairMngListPerCtrtTotalCnt(ctrtNo);

        model.addAttribute("result", result);
        model.addAttribute("resultList", fcltyRepairMngListPerCtrt);
        model.addAttribute("totCnt", totCnt);
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");

		//hwp선택시 파일명
		if(fcltyRepairCheckMngOpt.get("filename") != null){
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", fcltyRepairCheckMngOpt.get("filename"));
    		}
    	return "ygpa/gam/fcltyMng/GamFcltyRepairCheckMngPrint";
    }
	/**
     * 하자검사관리대장 한글파일 문서
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/fcltyMng/selectFcltyRepairCheckMngHwp.do")
	public String selectFcltyRepairCheckMngHwp(@RequestParam Map<String, Object> fcltyRepairCheckMngOpt, ModelMap model) throws Exception {

    	Map map = new HashMap();
    	EgovMap result = null;
    	int totCnt;

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/fcltyMng/GamFcltyRepairCheckMngHwp";
    	}

		ObjectMapper mapper = new ObjectMapper();

		GamFcltyRepairMngVO searchVO;
    	searchVO = mapper.convertValue(fcltyRepairCheckMngOpt, GamFcltyRepairMngVO.class);

		//하자검사관리대장인쇄
    	result = gamFcltyRepairMngService.selectFcltyRepairCheckMng(searchVO);

		String ctrtNo = (String) result.get("ctrtNo");

		//하자보증내용
    	List fcltyRepairMngListPerCtrt = gamFcltyRepairMngService.selectFcltyRepairMngListPerCtrt(ctrtNo);

    	totCnt =  gamFcltyRepairMngService.selectFcltyRepairMngListPerCtrtTotalCnt(ctrtNo);

        model.addAttribute("result", result);
        model.addAttribute("resultList", fcltyRepairMngListPerCtrt);
        model.addAttribute("totCnt", totCnt);
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");

		//hwp선택시 파일명
		if(fcltyRepairCheckMngOpt.get("filename") != null){
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", fcltyRepairCheckMngOpt.get("filename"));
    		}
    	return "ygpa/gam/fcltyMng/GamFcltyRepairCheckMngHwp";
    }
	// 파일 처리
    @RequestMapping(value="/fcltyMng/uploadRepairAttachFile.do", method=RequestMethod.POST)
    public @ResponseBody String uploadFile(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map map = new HashMap();
		String uploadPath = EgovProperties.getProperty("repairAttach.fileStorePath");
		try {
			List<GamFileServiceVo> list = GamFileUploadUtil.uploadFiles(request, uploadPath, gamRepairFileIdGnrService);

			map.put("resultCode", "0");
			map.put("result", list);
		}
		catch(Exception e) {
			map.put("resultCode", "-1");
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.upload"));
		}

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		response.setContentType("text/html; charset=utf-8");

		return json;	// ie 문제 때문에 스트링으로 출력한다.

	}

    @RequestMapping("/fcltyMng/fdown/getRepairAttachFile.do")
    public void getImage(final HttpServletRequest request, HttpServletResponse response) throws Exception {
		GamFileServiceVo gamFileServiceVo = new GamFileServiceVo();
		String uploadPath = EgovProperties.getProperty("repairAttach.fileStorePath");

		gamFileServiceVo.setPhyscalFileNm((String)request.getParameter("physicalFileNm"));

		GamFileUploadUtil.downloadImage(request, response, uploadPath, gamFileServiceVo);
    }

    @RequestMapping("/fcltyMng/downloadRepairAttachFile.do")
    public void getDownload(final HttpServletRequest request, HttpServletResponse response) throws Exception {
		GamFileServiceVo gamFileServiceVo = new GamFileServiceVo();
		String uploadPath = EgovProperties.getProperty("repairAttach.fileStorePath");

		gamFileServiceVo.setPhyscalFileNm((String)request.getParameter("physicalFileNm"));
		gamFileServiceVo.setLogicalFileNm((String)request.getParameter("logicalFileNm"));

		GamFileUploadUtil.downloadFile(request, response, uploadPath, gamFileServiceVo);
    }

}