/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.web;

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
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcwWrtMngService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcwWrtMngVO;

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
    	List<?> atchFileList = null;
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	try {
        	detailData = gamFcltyQcwWrtMngService.selectQcMngDtlsDetail(searchVO);
        	atchFileList = gamFcltyQcwWrtMngService.selectQcMngAtchFileList(searchVO);
            map.put("resultCode", 0);
            map.put("detailData", detailData);
            map.put("atchFileList", atchFileList);
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
		List<HashMap<String, String>> atchFileList=null;

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

		atchFileList = mapper.readValue((String)insertMap.get("atchFileList"),
				 new TypeReference<List<HashMap<String, String>>>(){});
    	
		detailForm.put("regUsr",user.getId());
    	try {
    		gamFcltyQcwWrtMngService.insertQcMngDtls(detailForm, qcObjList, qcResultList, atchFileList);
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
		List<HashMap<String, String>> atchFileList=null;

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

		atchFileList = mapper.readValue((String)updateMap.get("atchFileList"),
				 new TypeReference<List<HashMap<String, String>>>(){});
    	
		detailForm.put("updUsr",user.getId());
    	try {
    		gamFcltyQcwWrtMngService.updateQcMngDtls(detailForm, qcObjList, qcResultList, atchFileList);
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
    	
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);		

    	    	
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
    	
    	
    	return "/ygpa/gam/fcltyMng/GamFcltyQcPrintA";
	}
}