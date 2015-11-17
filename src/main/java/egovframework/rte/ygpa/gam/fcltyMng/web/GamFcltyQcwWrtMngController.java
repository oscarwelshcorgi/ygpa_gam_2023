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
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcwWrtMngService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcwWrtMngVO;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamQcMngAtchFileMngVO;

/**
 *
 * @author 김종민
 * @since 2014. 11. 20.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 20.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamFcltyQcwWrtMngController {
	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="gamFcltyQcwWrtMngService")
    GamFcltyQcwWrtMngService gamFcltyQcwWrtMngService;

    @Resource(name="gamQcWrtFileIdGnrService")
    EgovTableIdGnrService gamQcWrtFileIdGnrService;

	/**
     * 시설물 점검관리 화면 호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/fcltyMng/gamFcltyQcwWrtMng.do")
    String indexFcltyQcwWrtMng(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fcltyMng/GamFcltyQcwWrtMng";
    }

	/**
	 * 점검관리목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/selectQcMngDtlsList.do")
	@ResponseBody Map<String, Object> selectQcMngDtlsList(GamFcltyQcwWrtMngVO searchVO) throws Exception {

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

		List resultList = gamFcltyQcwWrtMngService.selectQcMngDtlsList(searchVO);
		int totCnt = gamFcltyQcwWrtMngService.selectQcMngDtlsListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	return map;
    }

	/**
	 * 점검관리목록 엑셀다운로드
	 * @param map
	 * @return modelAndView
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/excelDownloadQcMngDtlsList.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView excelDownloadQcMngDtlsList(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltyQcwWrtMngVO searchVO= new GamFcltyQcwWrtMngVO();
		searchVO = mapper.convertValue(excelParam, GamFcltyQcwWrtMngVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltyQcwWrtMngService.selectQcMngDtlsList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);
	}

	/**
	 * 점검관리내역 상세
	 * @param fcltyManageVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/selectQcMngDtlsDetail.do")
    @ResponseBody Map<String, Object> selectQcMngDtlsDetail(GamFcltyQcwWrtMngVO searchVO) throws Exception {
    	Map map = new HashMap();
    	EgovMap detailData = null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
        	detailData = gamFcltyQcwWrtMngService.selectQcMngDtlsDetail(searchVO);
            map.put("resultCode", 0);
            map.put("detailData", detailData);
    	}
    	catch(Exception e) {
            map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
    	}
        return map;
	}

	/**
	 * 점검관리대상시설물 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/selectQcMngObjFcltsList.do")
	@ResponseBody Map<String, Object> selectQcMngObjFcltsList(GamFcltyQcwWrtMngVO searchVO) throws Exception {

		Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		List resultList = gamFcltyQcwWrtMngService.selectQcMngObjFcltsList(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", resultList);

    	return map;
    }

	/**
	 * 점검관리결과항목 목록조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/selectQcMngResultItemList.do")
	@ResponseBody Map<String, Object> selectQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception {

		Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	List resultList = null;

    	if(searchVO.getsFcltsJobSe().equals("M")) {
    		//업무구분이 기계시설일 경우
    		resultList = gamFcltyQcwWrtMngService.selectMechQcMngResultItemList(searchVO);
    	}
    	else {
	    	int resultCnt = gamFcltyQcwWrtMngService.selectQcMngResultItemListTotCnt(searchVO);

	    	if(resultCnt > 0) {
	    		//갯수가 0이상이면 해당 점검조건에 대한 리스트이므로 그대로 보여준다.
	    		resultList = gamFcltyQcwWrtMngService.selectQcMngResultItemList(searchVO);
	    	} else {
	    		//갯수가 0이면 해당 점검조건 중에 업무구분에 해당하는 리스트만 보여준다.
	    		searchVO.setsFcltsMngGroupNo(null);
	    		searchVO.setsQcMngSeq("");
	    		resultList = gamFcltyQcwWrtMngService.selectQcMngResultItemList(searchVO);
	    	}
    	}

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", resultList);
    	return map;
    }

	/**
	 * 점검관리 내역 삽입
	 * @param insertMap
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/insertQcMngDtls.do")
	@ResponseBody Map<String, Object> insertQcMngDtls(@RequestParam Map<String, Object> insertMap) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> detailForm = null;
		List<HashMap<String, String>> qcObjList=null;
		List<HashMap<String, String>> qcResultList=null;

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		detailForm = mapper.readValue((String)insertMap.get("detailForm"),
			 new TypeReference<HashMap<String, String>>(){});

		qcObjList = mapper.readValue((String)insertMap.get("qcObjList"),
				 new TypeReference<List<HashMap<String, String>>>(){});

		qcResultList = mapper.readValue((String)insertMap.get("qcResultList"),
				 new TypeReference<List<HashMap<String, String>>>(){});

		detailForm.put("regUsr",user.getId());
    	try {
    		gamFcltyQcwWrtMngService.insertQcMngDtls(detailForm, qcObjList, qcResultList);
    		map.put("resultCode", 0);			// return ok
    		map.put("qcMngSeq", detailForm.get("qcMngSeq"));
            map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}
      	return map;
	}

	/**
	 * 점검관리 내역 수정
	 * @param updateMap
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/updateQcMngDtls.do")
	@ResponseBody Map<String, Object> updateQcMngDtls(@RequestParam Map<String, Object> updateMap) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> detailForm = null;
		List<HashMap<String, String>> qcObjList=null;
		List<HashMap<String, String>> qcResultList=null;

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		detailForm = mapper.readValue((String)updateMap.get("detailForm"),
			 new TypeReference<HashMap<String, String>>(){});

		qcObjList = mapper.readValue((String)updateMap.get("qcObjList"),
				 new TypeReference<List<HashMap<String, String>>>(){});

		qcResultList = mapper.readValue((String)updateMap.get("qcResultList"),
				 new TypeReference<List<HashMap<String, String>>>(){});

		detailForm.put("updUsr",user.getId());
    	try {
    		gamFcltyQcwWrtMngService.updateQcMngDtls(detailForm, qcObjList, qcResultList);
    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}
      	return map;
	}

	/**
	 * 점검관리내역 삭제
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/deleteQcMngDtls.do")
    @ResponseBody Map<String, Object> deleteQcMngDtls(@RequestParam Map deleteMap) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		gamFcltyQcwWrtMngService.deleteQcMngDtls(deleteMap);

    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}
      	return map;
	}

	/**
	 * 점검관리결과항목 수정 팝업
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/popup/showQcMngResultItemPopup.do")
    String showQcMngResultItemPopup(@RequestParam Map qcResultList, ModelMap model) throws Exception {
		model.addAttribute("qcResultList", qcResultList);
    	return "/ygpa/gam/fcltyMng/GamPopupQcResultItemCd";
    }




	/**
	 * 건축시설물 점검표 인쇄
	 * @param map
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/fcltyMng/selectFcltyQcPrintA.do")
	public String printQcMngDtls(@RequestParam Map<String, Object> qcPrintOpt, ModelMap model) throws Exception {
    	String printPageName = null;

		ObjectMapper mapper = new ObjectMapper();
		GamFcltyQcwWrtMngVO searchVO = null;
		List qcResultItemList = null;
		int resultCnt = 0;

    	searchVO = mapper.convertValue(qcPrintOpt, GamFcltyQcwWrtMngVO.class);

    	searchVO.setsFcltsJobSe(searchVO.getFcltsJobSe());
    	searchVO.setsFcltsMngGroupNo(searchVO.getFcltsMngGroupNo());
    	searchVO.setsQcMngSeq(searchVO.getQcMngSeq());


    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return printPageName;
    	}

		EgovMap detailData = gamFcltyQcwWrtMngService.selectQcMngDtlsDetail(searchVO);

		resultCnt = gamFcltyQcwWrtMngService.selectQcMngResultItemListTotCnt(searchVO);
    	if(resultCnt > 0) {
    		qcResultItemList = gamFcltyQcwWrtMngService.selectQcMngResultItemList(searchVO);
    	} else {
    		searchVO.setsFcltsMngGroupNo(null);
    		searchVO.setsQcMngSeq("");
    		qcResultItemList = gamFcltyQcwWrtMngService.selectQcMngResultItemList(searchVO);
    	}

		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
    	model.addAttribute("resultList", qcResultItemList);
    	model.addAttribute("detailData", detailData);

		//hwp선택시 파일명
		if(qcPrintOpt.get("filename") != null){
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", qcPrintOpt.get("filename"));
    		}

    	return "/ygpa/gam/fcltyMng/GamFcltyQcPrintA";
	}



    /**
	 * 정보통신 시설물 점검표 인쇄
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/fcltyMng/selectFcltyQcPrintI.do")
	public String selectFcltyQcPrintI(@RequestParam Map<String, Object> qcPrintOpt, ModelMap model) throws Exception {
		String printPageName = null;

		ObjectMapper mapper = new ObjectMapper();
		GamFcltyQcwWrtMngVO searchVO = null;
		List qcResultItemList = null;
		int resultCnt = 0;

		searchVO = mapper.convertValue(qcPrintOpt, GamFcltyQcwWrtMngVO.class);

		searchVO.setsFcltsJobSe(searchVO.getFcltsJobSe());
		searchVO.setsFcltsMngGroupNo(searchVO.getFcltsMngGroupNo());
		searchVO.setsQcMngSeq(searchVO.getQcMngSeq());


		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if(!isAuthenticated) {
			model.addAttribute("resultCode", 1);
			model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
	    	return printPageName;
		}

		EgovMap detailData = gamFcltyQcwWrtMngService.selectQcMngDtlsDetail(searchVO);

		resultCnt = gamFcltyQcwWrtMngService.selectQcMngResultItemListTotCnt(searchVO);
		if(resultCnt > 0) {
			qcResultItemList = gamFcltyQcwWrtMngService.selectQcMngResultItemList(searchVO);
		} else {
			searchVO.setsFcltsMngGroupNo(null);
			searchVO.setsQcMngSeq("");
			qcResultItemList = gamFcltyQcwWrtMngService.selectQcMngResultItemList(searchVO);
		}

		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("resultList", qcResultItemList);
		model.addAttribute("detailData", detailData);

		//hwp선택시 파일명
		if(qcPrintOpt.get("filename") != null){
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", qcPrintOpt.get("filename"));
    		}
		return "/ygpa/gam/fcltyMng/GamFcltyQcPrintI";
	}



    /**
	 * 전기 시설물 점검표 인쇄
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/fcltyMng/selectFcltyQcPrintE.do")
	public String selectFcltyQcPrintE(@RequestParam Map<String, Object> qcPrintOpt, ModelMap model) throws Exception {
		String printPageName = null;

		ObjectMapper mapper = new ObjectMapper();
		GamFcltyQcwWrtMngVO searchVO = null;
		List qcResultItemList = null;
		int resultCnt = 0;

		searchVO = mapper.convertValue(qcPrintOpt, GamFcltyQcwWrtMngVO.class);

		searchVO.setsFcltsJobSe(searchVO.getFcltsJobSe());
		searchVO.setsFcltsMngGroupNo(searchVO.getFcltsMngGroupNo());
		searchVO.setsQcMngSeq(searchVO.getQcMngSeq());


		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if(!isAuthenticated) {
			model.addAttribute("resultCode", 1);
			model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
	    	return printPageName;
		}

		EgovMap detailData = gamFcltyQcwWrtMngService.selectQcMngDtlsDetail(searchVO);

		resultCnt = gamFcltyQcwWrtMngService.selectQcMngResultItemListTotCnt(searchVO);
		if(resultCnt > 0) {
			qcResultItemList = gamFcltyQcwWrtMngService.selectQcMngResultItemList(searchVO);
		} else {
			searchVO.setsFcltsMngGroupNo(null);
			searchVO.setsQcMngSeq("");
			qcResultItemList = gamFcltyQcwWrtMngService.selectQcMngResultItemList(searchVO);
		}

		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("resultList", qcResultItemList);
		model.addAttribute("detailData", detailData);

		//hwp선택시 파일명
		if(qcPrintOpt.get("filename") != null){
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", qcPrintOpt.get("filename"));
    		}
		return "/ygpa/gam/fcltyMng/GamFcltyQcPrintE";
	}



	/**
	 * 토목 시설물 점검표 인쇄
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/fcltyMng/selectFcltyQcPrintC.do")
	public String selectFcltyQcPrintC(@RequestParam Map<String, Object> qcPrintOpt, ModelMap model) throws Exception {
		String printPageName = null;

		ObjectMapper mapper = new ObjectMapper();
		GamFcltyQcwWrtMngVO searchVO = null;
		List qcResultItemList = null;
		int resultCnt = 0;

		searchVO = mapper.convertValue(qcPrintOpt, GamFcltyQcwWrtMngVO.class);

		searchVO.setsFcltsJobSe(searchVO.getFcltsJobSe());
		searchVO.setsFcltsMngGroupNo(searchVO.getFcltsMngGroupNo());
		searchVO.setsQcMngSeq(searchVO.getQcMngSeq());


		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if(!isAuthenticated) {
			model.addAttribute("resultCode", 1);
			model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
	    	return printPageName;
		}


		EgovMap detailMngGroup = gamFcltyQcwWrtMngService.selectFcltsMngGroupInfo(searchVO);

		EgovMap detailData = gamFcltyQcwWrtMngService.selectQcMngDtlsDetail(searchVO);

		resultCnt = gamFcltyQcwWrtMngService.selectQcMngResultItemListTotCnt(searchVO);
		if(resultCnt > 0) {
			qcResultItemList = gamFcltyQcwWrtMngService.selectQcMngResultItemList(searchVO);
		} else {
			searchVO.setsFcltsMngGroupNo(null);
			searchVO.setsQcMngSeq("");
			qcResultItemList = gamFcltyQcwWrtMngService.selectQcMngResultItemList(searchVO);
		}

		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("detailMngGroup", detailMngGroup);
		model.addAttribute("resultList", qcResultItemList);
		model.addAttribute("detailData", detailData);

		//hwp선택시 파일명
		if(qcPrintOpt.get("filename") != null){
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", qcPrintOpt.get("filename"));
    		}

		return "/ygpa/gam/fcltyMng/GamFcltyQcPrintC";
	}




    /**
	 * 항만 하역장비 시설물 점검표 인쇄
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/fcltyMng/selectFcltyQcPrintM1.do")
	public String selectFcltyQcPrintM1(@RequestParam Map<String, Object> qcPrintOpt, ModelMap model) throws Exception {
		String printPageName = null;

		ObjectMapper mapper = new ObjectMapper();
		GamFcltyQcwWrtMngVO searchVO = null;
		List qcResultItemList = null;

		searchVO = mapper.convertValue(qcPrintOpt, GamFcltyQcwWrtMngVO.class);

		searchVO.setsFcltsJobSe(searchVO.getFcltsJobSe());
		searchVO.setsFcltsMngGroupNo(searchVO.getFcltsMngGroupNo());
		searchVO.setsQcMngSeq(searchVO.getQcMngSeq());


		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if(!isAuthenticated) {
			model.addAttribute("resultCode", 1);
			model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
	    	return printPageName;
		}

		EgovMap detailData = gamFcltyQcwWrtMngService.selectQcMngDtlsDetail(searchVO);

		qcResultItemList = gamFcltyQcwWrtMngService.selectMechQcMngResultItemList(searchVO);

		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("resultList", qcResultItemList);
		model.addAttribute("detailData", detailData);

		//hwp선택시 파일명
		if(qcPrintOpt.get("filename") != null){
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", qcPrintOpt.get("filename"));
    		}

		return "/ygpa/gam/fcltyMng/GamFcltyQcPrintM1";
	}



    /**
	 * 기계설비 점검표 인쇄
	 * @param map
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/fcltyMng/selectFcltyQcPrintM2.do")
	public String selectFcltyQcPrintM2(@RequestParam Map<String, Object> qcPrintOpt, ModelMap model) throws Exception {
    	String printPageName = null;

		ObjectMapper mapper = new ObjectMapper();
		GamFcltyQcwWrtMngVO searchVO = null;

    	searchVO = mapper.convertValue(qcPrintOpt, GamFcltyQcwWrtMngVO.class);

    	searchVO.setsFcltsJobSe(searchVO.getFcltsJobSe());
    	searchVO.setsFcltsMngGroupNo(searchVO.getFcltsMngGroupNo());
    	searchVO.setsQcMngSeq(searchVO.getQcMngSeq());

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return printPageName;
    	}

		List mechQcMngResultItemList = gamFcltyQcwWrtMngService.selectMechQcMngResultItemList(searchVO);

		EgovMap detailData = gamFcltyQcwWrtMngService.selectQcMngDtlsDetail(searchVO);

		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
    	model.addAttribute("resultList", mechQcMngResultItemList);
    	model.addAttribute("detailData", detailData);

		//hwp선택시 파일명
		if(qcPrintOpt.get("filename") != null){
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", qcPrintOpt.get("filename"));
    		}

    	return "/ygpa/gam/fcltyMng/GamFcltyQcPrintM2";
	}


    // 파일 처리
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/fcltyMng/uploadQcWrtAttachFile.do", method=RequestMethod.POST)
    public @ResponseBody String uploadFile(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map map = new HashMap();
		String uploadPath = EgovProperties.getProperty("qcAttach.fileStorePath");
		try {
			List<GamFileServiceVo> list = GamFileUploadUtil.uploadFiles(request, uploadPath, gamQcWrtFileIdGnrService);

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

    @RequestMapping("/fcltyMng/getQcWrtAttachFile.do")
    public void getImage(final HttpServletRequest request, HttpServletResponse response) throws Exception {
		GamFileServiceVo gamFileServiceVo = new GamFileServiceVo();
		String uploadPath = EgovProperties.getProperty("qcAttach.fileStorePath");

		gamFileServiceVo.setPhyscalFileNm((String)request.getParameter("physicalFileNm"));

		GamFileUploadUtil.downloadImage(request, response, uploadPath, gamFileServiceVo);
    }

    @RequestMapping("/fcltyMng/downloadQcWrtAttachFile.do")
    public void getDownload(final HttpServletRequest request, HttpServletResponse response) throws Exception {
		GamFileServiceVo gamFileServiceVo = new GamFileServiceVo();
		String uploadPath = EgovProperties.getProperty("qcAttach.fileStorePath");

		gamFileServiceVo.setPhyscalFileNm((String)request.getParameter("physicalFileNm"));
		gamFileServiceVo.setLogicalFileNm((String)request.getParameter("logicalFileNm"));

		GamFileUploadUtil.downloadFile(request, response, uploadPath, gamFileServiceVo);
    }



	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/gamSelectFcltyQcwWrtMngQcMngAtchFileList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyQcwWrtMngQcMngAtchFileList(GamQcMngAtchFileMngVO searchVO) throws Exception {

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

		List resultList = gamFcltyQcwWrtMngService.selectFcltyQcwWrtMngQcMngAtchFileList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/fcltyMng/gamInsertFcltyQcwWrtMngQcMngAtchFile.do")
	@ResponseBody Map<String, Object> gamInsertFcltyQcwWrtMngQcMngAtchFile(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception {

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
			sNewSeq = gamFcltyQcwWrtMngService.selectFcltyQcwWrtMngQcMngAtchFileNewSeq(gamQcMngAtchFileMngVO);

			gamQcMngAtchFileMngVO.setAtchFileSeq(sNewSeq);
			gamQcMngAtchFileMngVO.setRegUsr((String)user.getId());
			gamFcltyQcwWrtMngService.insertFcltyQcwWrtMngQcMngAtchFile(gamQcMngAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("atchFileSeq", sNewSeq);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/fcltyMng/gamUpdateFcltyQcwWrtMngQcMngAtchFile.do")
	@ResponseBody Map<String, Object> gamUpdateFcltyQcwWrtMngQcMngAtchFile(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamQcMngAtchFileMngVO.setUpdUsr((String)user.getId());
			gamFcltyQcwWrtMngService.updateFcltyQcwWrtMngQcMngAtchFile(gamQcMngAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/fcltyMng/gamDeleteFcltyQcwWrtMngQcMngAtchFile.do")
	@ResponseBody Map<String, Object> gamDeleteFcltyQcwWrtMngQcMngAtchFile(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyQcwWrtMngService.deleteFcltyQcwWrtMngQcMngAtchFile(gamQcMngAtchFileMngVO);

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
	@RequestMapping(value="/fcltyMng/gamDeleteFcltyQcwWrtMngQcMngAtchFileMulti.do")
	@ResponseBody Map<String, Object> gamDeleteFcltyQcwWrtMngQcMngAtchFileMulti(@RequestParam Map deleteVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyQcwWrtMngService.deleteFcltyQcwWrtMngQcMngAtchFileMulti(deleteVO);

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
	@RequestMapping(value="/fcltyMng/gamSelectFcltyQcwWrtMngQcMngAtchFilePk.do")
	@ResponseBody Map<String, Object> gamSelectFcltyQcwWrtMngQcMngAtchFilePk(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltyQcwWrtMngService.selectFcltyQcwWrtMngQcMngAtchFilePk(gamQcMngAtchFileMngVO);

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
	@RequestMapping(value="/fcltyMng/gamSelectFcltyQcwWrtMngQcMngAtchFileNewSeq.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltyQcwWrtMngQcMngAtchFileNewSeq(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception {

		String sNewSeq;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sNewSeq = gamFcltyQcwWrtMngService.selectFcltyQcwWrtMngQcMngAtchFileNewSeq(gamQcMngAtchFileMngVO);

		map.put("resultCode", 0);
		map.put("sNewSeq", sNewSeq);

		return map;

	}

	
    /**
	 * 시설물 점검표 한글 문서 다운로드 - 김종민 추가 작업 2015.10.28
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/fcltyMng/downloadQcMngResultLIst.do")
	public String downloadQcMngResultLIst(@RequestParam Map<String, Object> qcPrintOpt, ModelMap model) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		GamFcltyQcwWrtMngVO searchVO = null;

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if(!isAuthenticated) {
			model.addAttribute("resultCode", 1);
			model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
	    	return "/ygpa/gam/fcltyMng/GamFcltyQcMngResultListReportHwp";
		}

		searchVO = mapper.convertValue(qcPrintOpt, GamFcltyQcwWrtMngVO.class);
		
		searchVO.setsFcltsMngGroupNo(searchVO.getFcltsMngGroupNo());
		searchVO.setsFcltsJobSe(searchVO.getFcltsJobSe());
		searchVO.setsQcMngSeq(searchVO.getQcMngSeq());
		
		String hwpML = gamFcltyQcwWrtMngService.selectQcMngResultListReportHWPML(searchVO);
		
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("hwpML", hwpML);

		//hwp선택시 파일명
		if(qcPrintOpt.get("filename") != null){
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", qcPrintOpt.get("filename"));
		}

		return "/ygpa/gam/fcltyMng/GamFcltyQcMngResultListReportHwp";
	}
	
	 /**
		 * 안전점검결과 한글 문서 다운로드 - 김종민 추가 작업 2015.10.28
		 * @param map
		 * @return
		 * @throws Exception
		 */
		@SuppressWarnings("rawtypes")
		@RequestMapping(value="/fcltyMng/downloadSafetyQcResult.do")
		public String downloadSafetyQcResult(@RequestParam Map<String, Object> qcPrintOpt, ModelMap model) throws Exception {
			ObjectMapper mapper = new ObjectMapper();
			GamFcltyQcwWrtMngVO searchVO = null;

			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

			if(!isAuthenticated) {
				model.addAttribute("resultCode", 1);
				model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
		    	return "/ygpa/gam/fcltyMng/GamFcltyQcMngResultListReportHwp";
			}

			searchVO = mapper.convertValue(qcPrintOpt, GamFcltyQcwWrtMngVO.class);
			
			searchVO.setsFcltsMngGroupNo(searchVO.getFcltsMngGroupNo());
			searchVO.setsFcltsJobSe(searchVO.getFcltsJobSe());
			searchVO.setsQcMngSeq(searchVO.getQcMngSeq());
			
			String hwpML = gamFcltyQcwWrtMngService.selectSafetyQcReportHWPML(searchVO);
			
			model.addAttribute("resultCode", 0);
			model.addAttribute("resultMsg", "");
			model.addAttribute("hwpML", hwpML);

			//hwp선택시 파일명
			if(qcPrintOpt.get("filename") != null){
				model.addAttribute("isHwp", true);
				model.addAttribute("filename", qcPrintOpt.get("filename"));
			}

			return "/ygpa/gam/fcltyMng/GamFcltyQcMngResultListReportHwp";
		}	
		
	    /**
	     * 선택된 안전점검결과 리스트 한글파일 문서 출력 - 김종민 추가 작업 2015.11.6
	     *
	     * @param searchVO
	     * @return xml string
	     * @throws Exception the exception
	     */
		@SuppressWarnings({ "rawtypes", "unchecked" })
	    @RequestMapping(value="/fcltyMng/downloadSelectedSafetyQcResult.do")
		public String downloadSelectedSafetyQcResult(@RequestParam Map<String, Object> reportOpt , ModelMap model) throws Exception {
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String,String>> reportList = null;
			
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

			if(!isAuthenticated) {
				model.addAttribute("resultCode", 1);
				model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
		    	return "/ygpa/gam/fcltyMng/GamFcltyQcMngResultListReportHwp";
			}

			reportList = mapper.readValue((String)reportOpt.get("downList"),
	    		    new TypeReference<List<HashMap<String,String>>>(){});

			String hwpML = gamFcltyQcwWrtMngService.selectSafetyQcReportListHWPML(reportList);
			
			model.addAttribute("resultCode", 0);
			model.addAttribute("resultMsg", "");
			model.addAttribute("hwpML", hwpML);

			//hwp선택시 파일명
			if(reportOpt.get("filename") != null){
				model.addAttribute("isHwp", true);
				model.addAttribute("filename", reportOpt.get("filename"));
			}

			return "/ygpa/gam/fcltyMng/GamFcltyQcMngResultListReportHwp";
		};

	    /**
	     * 선택된 안전점검결과 리스트 이미지 파일 개수 알아내기
	     *
	     * @param searchVO
	     * @return xml string
	     * @throws Exception the exception
	     */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@RequestMapping(value="/fcltyMng/selectFcltyQcMngReportListPictureCount.do")
		@ResponseBody Map<String, Object> selectFcltyQcMngReportListPictureCount(@RequestParam Map<String, Object> reportOpt) throws Exception {
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String,String>> reportList = null;
			Map<String, Object> map = new HashMap<String, Object>();

			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if (!isAuthenticated) {
				map.put("resultCode", 1);
				map.put("result", 0);
				map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
				return map;
			}

			reportList = mapper.readValue((String)reportOpt.get("downList"),
	    		    new TypeReference<List<HashMap<String,String>>>(){});

			try {
				int count = gamFcltyQcwWrtMngService.selectQcMngAtchPictureFileListTotCnt(reportList);

				map.put("resultCode", 0);
				map.put("result", count);
				map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
			} catch (Exception e) {
				map.put("result", 0);
				map.put("resultCode", 1);
				map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
			}

			return map;

		}

}