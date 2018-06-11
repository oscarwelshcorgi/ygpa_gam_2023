/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.web;

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
import egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMntnRprDtlsVO;
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

    @Resource(name="gamMaintFileIdGnrService")
    EgovTableIdGnrService gamMaintFileIdGnrService;


	/**
     * 유지보수내역 관리화면호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/fcltyMng/gamFcltyMaintMng.do")
    public String indexFcltyMaintMng(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fcltyMng/GamFcltyMaintMng";
    }


	/**
	 * 유지보수내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/selectFcltyMaintMngList.do")
	public @ResponseBody Map selectFcltyMaintMngList(GamFcltyMaintMngVO searchVO)throws Exception {

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
		
		if(searchVO.getsFcltsJobSe().endsWith("*"))
		{
			searchVO.setsFcltsJobSe("");
		}

		/** List Data */
		List<?> fcltyMaintMngList = gamFcltyMaintMngService.selectFcltyMaintMngList(searchVO);

		GamFcltyMaintMngVO resultSum = gamFcltyMaintMngService.selectFcltyMaintMngListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(resultSum.getTotCnt());
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", resultSum.getTotCnt());
    	map.put("sumMntnRprCnstAmt", resultSum.getSumMntnRprCnstAmt());
    	map.put("sumMntnRprBdgt", resultSum.getSumMntnRprBdgt());
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/selectFcltyMaintMngDetail.do")
	public @ResponseBody Map selectFcltyMaintMngDetail(GamFcltyMaintMngVO searchVO)throws Exception {

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/selectMntnRprObjFcltsFList.do")
	public @ResponseBody Map selectMntnRprObjFcltsFList(GamFcltyMaintMngVO searchVO)throws Exception {

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
		List mntnRprObjFcltsFList = gamFcltyMaintMngService.selectMntnRprObjFcltsFList(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", mntnRprObjFcltsFList);

    	return map;
    }


/*	*//**
	 * 유지보수 첨부파일 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 *//*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/selectFcltyMaintFileList.do")
	public @ResponseBody Map selectFcltyMaintFileList(GamFcltyMaintMngVO searchVO)throws Exception {

		Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회

		*//** List Data *//*
		List fcltyMaintFileList = gamFcltyMaintMngService.selectFcltyMaintFileList(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", fcltyMaintFileList);

    	return map;
    }*/


	/**
	 * 유지보수내역 등록
	 * @param Map
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/insertFcltyMaintMng.do")
    public @ResponseBody Map insertFcltyMaintMng(@RequestParam Map fcltyMaintItem) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	Map map = new HashMap();
    	ObjectMapper mapper = new ObjectMapper();
    	List<HashMap<String,String>> insertObjList=null;
    	List<HashMap<String,String>> insertFileList=null;
    	Map insertMntnData = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	insertMntnData = mapper.readValue((String)fcltyMaintItem.get("saveFcltyMaintMngVO"),
    		    new TypeReference<HashMap<String,String>>(){});

    	insertObjList = mapper.readValue((String)fcltyMaintItem.get("insertMntnObjList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

    	insertFileList = mapper.readValue((String)fcltyMaintItem.get("insertMntnFileList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

    	insertMntnData.put("regUsr",user.getId());

    	try {
    		// 유지보수내역 저장
    		gamFcltyMaintMngService.insertFcltyMaintMng(insertMntnData, insertObjList, insertFileList);

    		map.put("resultCode", 0);			// return ok
    		map.put("mntnRprSeq", insertMntnData.get("mntnRprSeq"));
            map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));

		} catch (Exception e) {
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/updateFcltyMaintMng.do")
    public @ResponseBody Map updateFcltyMaintMng(@RequestParam Map fcltyMaintItem) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	Map map = new HashMap();
    	ObjectMapper mapper = new ObjectMapper();
    	List<HashMap<String,String>> insertObjList=null;
    	List<HashMap<String,String>> insertFileList=null;
    	Map updateMntnData = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	updateMntnData = mapper.readValue((String)fcltyMaintItem.get("saveFcltyMaintMngVO"),
    		    new TypeReference<HashMap<String,String>>(){});

    	insertObjList = mapper.readValue((String)fcltyMaintItem.get("insertMntnObjList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

    	insertFileList = mapper.readValue((String)fcltyMaintItem.get("insertMntnFileList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

    	fcltyMaintItem.put("regUsr",user.getId());


    	try {

    		// 유지보수내역 입력
    		gamFcltyMaintMngService.updateFcltyMaintMng(updateMntnData, insertObjList, insertFileList);

    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));

		} catch (Exception e) {
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/deleteFcltyMaintMng.do")
    public @ResponseBody Map deleteFcltyMaintMng(@RequestParam Map fcltyMaintItem) throws Exception {

    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}


    	try {

    		// 유지보수내역 삭제
    		gamFcltyMaintMngService.deleteFcltyMaintMng(fcltyMaintItem);

    		map.put("resultCode", 0);
            map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));

		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

      	return map;
    }


	/**
	 * 시설물 유지보수관리 리스트를 엑셀로 다운로드한다.
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/fcltyMng/selectFcltyMaintMngListExcel.do", method=RequestMethod.POST)
    public @ResponseBody ModelAndView selectFcltyMaintMngListExcel(@RequestParam Map<String, Object> excelParam) throws Exception {
		Map map = new HashMap();
		List header;
		ObjectMapper mapper = new ObjectMapper();

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    		return new ModelAndView("gridFcltyMaintView", "gridResultMap", map);
    	}

    	// 환경설정
    	/** EgovPropertyService */
    	GamFcltyMaintMngVO searchVO= new GamFcltyMaintMngVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.
		// 조회 조건
		searchVO = mapper.convertValue(excelParam, GamFcltyMaintMngVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);


		//계약이력목록
    	List fcltyMaintMngList = gamFcltyMaintMngService.selectFcltyMaintMngList(searchVO);


    	map.put("resultList", fcltyMaintMngList);
    	map.put("header", header);
    	return new ModelAndView("gridFcltyMaintView", "gridResultMap", map);
    }


/* 유지보수 */


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMaintMngAtchFileDirList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMaintMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamFcltyMaintMngService.selectMaintMngAtchFileDirList(gamAtchFileDirMngVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectMaintMngAtchFileDirPk.do")
	@ResponseBody Map<String, Object> gamSelectMaintMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltyMaintMngService.selectMaintMngAtchFileDirPk(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamInsertMaintMngAtchFileDir.do")
	@ResponseBody Map<String, Object> gamInsertMaintMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();
		String sNewNo;

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			sNewNo = gamFcltyMaintMngService.selectMaintMngAtchFileDirNewNo(gamAtchFileDirMngVO);

			gamAtchFileDirMngVO.setDirNo(sNewNo);
			gamAtchFileDirMngVO.setRegUsr((String)user.getId());
			gamFcltyMaintMngService.insertMaintMngAtchFileDir(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamUpdateMaintMngAtchFileDir.do")
	@ResponseBody Map<String, Object> gamUpdateMaintMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamAtchFileDirMngVO.setUpdUsr((String)user.getId());
			gamFcltyMaintMngService.updateMaintMngAtchFileDir(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamDeleteMaintMngAtchFileDir.do")
	@ResponseBody Map<String, Object> gamDeleteMaintMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyMaintMngService.deleteMaintMngAtchFileDir(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectMaintMngAtchFileDirNewNo.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMaintMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		String sNewNo;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sNewNo = gamFcltyMaintMngService.selectMaintMngAtchFileDirNewNo(gamAtchFileDirMngVO);

		map.put("resultCode", 0);
		map.put("sNewNo", sNewNo);

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectMaintMngAtchFileDirLowerDataCnt.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMaintMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamFcltyMaintMngService.selectMaintMngAtchFileDirLowerDataCnt(gamAtchFileDirMngVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMaintMngFcltsAtchFileList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMaintMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
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

		List resultList = gamFcltyMaintMngService.selectMaintMngFcltsAtchFileList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/fclty/gamInsertMaintMngFcltsAtchFile.do")
	@ResponseBody Map<String, Object> gamInsertMaintMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();
		String sNewNo;

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			sNewNo = gamFcltyMaintMngService.selectMaintMngFcltsAtchFileNewNo(gamFcltsAtchFileMngVO);

			gamFcltsAtchFileMngVO.setAtchFileNo(sNewNo);
			gamFcltsAtchFileMngVO.setRegUsr((String)user.getId());
			gamFcltyMaintMngService.insertMaintMngFcltsAtchFile(gamFcltsAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("atchFileNo", sNewNo);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamUpdateMaintMngFcltsAtchFile.do")
	@ResponseBody Map<String, Object> gamUpdateMaintMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsAtchFileMngVO.setUpdUsr((String)user.getId());
			gamFcltyMaintMngService.updateMaintMngFcltsAtchFile(gamFcltsAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamDeleteMaintMngFcltsAtchFile.do")
	@ResponseBody Map<String, Object> gamDeleteMaintMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyMaintMngService.deleteMaintMngFcltsAtchFile(gamFcltsAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamDeleteMaintMngFcltsAtchFileMulti.do")
	@ResponseBody Map<String, Object> gamDeleteMaintMngFcltsAtchFileMulti(@RequestParam Map deleteVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyMaintMngService.deleteMaintMngFcltsAtchFileMulti(deleteVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectMaintMngFcltsAtchFilePk.do")
	@ResponseBody Map<String, Object> gamSelectMaintMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltyMaintMngService.selectMaintMngFcltsAtchFilePk(gamFcltsAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMaintMngFcltsAtchFileNewNo.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMaintMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		String sNewNo;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sNewNo = gamFcltyMaintMngService.selectMaintMngFcltsAtchFileNewNo(gamFcltsAtchFileMngVO);

		map.put("resultCode", 0);
		map.put("sNewNo", sNewNo);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMaintMngMntnRprDtlsList.do", method=RequestMethod.POST)
	@ResponseBody Map selectMaintMngMntnRprDtlsList(GamMntnRprDtlsVO searchVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
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

		List resultList = gamFcltyMaintMngService.selectMaintMngMntnRprDtlsList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}








/*
	// 파일 처리
    @RequestMapping(value="/fcltyMng/uploadMaintAttachFile.do", method=RequestMethod.POST)
    public @ResponseBody String uploadFile(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map map = new HashMap();
		String uploadPath = EgovProperties.getProperty("maintAttach.fileStorePath");
		try {
			List<GamFileServiceVo> list = GamFileUploadUtil.uploadFiles(request, uploadPath, gamMaintFileIdGnrService);

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

    @RequestMapping("/fcltyMng/getMaintAttachFile.do")
    public void getImage(final HttpServletRequest request, HttpServletResponse response) throws Exception {
		GamFileServiceVo gamFileServiceVo = new GamFileServiceVo();
		String uploadPath = EgovProperties.getProperty("maintAttach.fileStorePath");

		gamFileServiceVo.setPhyscalFileNm((String)request.getParameter("physicalFileNm"));

		GamFileUploadUtil.downloadImage(request, response, uploadPath, gamFileServiceVo);
    }

    @RequestMapping("/fcltyMng/downloadMaintAttachFile.do")
    public void getDownload(final HttpServletRequest request, HttpServletResponse response) throws Exception {
		GamFileServiceVo gamFileServiceVo = new GamFileServiceVo();
		String uploadPath = EgovProperties.getProperty("maintAttach.fileStorePath");

		gamFileServiceVo.setPhyscalFileNm((String)request.getParameter("physicalFileNm"));
		gamFileServiceVo.setLogicalFileNm((String)request.getParameter("logicalFileNm"));

		GamFileUploadUtil.downloadFile(request, response, uploadPath, gamFileServiceVo);
    }
*/


}