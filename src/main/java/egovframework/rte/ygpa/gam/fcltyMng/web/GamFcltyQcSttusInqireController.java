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
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcSttusInqireService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcSttusInqireVO;

/**
 * 
 * @author 김종민
 * @since 2014. 12. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 15.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamFcltyQcSttusInqireController {
	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="gamFcltyQcSttusInqireService")
    GamFcltyQcSttusInqireService gamFcltyQcSttusInqireService;    

    /**
     * 시설물 점검조회 화면 호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/fcltyMng/gamFcltyQcSttusInqire.do")
    String indexFcltyQcSttusInqire(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fcltyMng/GamFcltyQcSttusInqire";
    }
	

	/**
	 * 점검관리목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/selectQcSttusDtlsList.do")
	@ResponseBody Map<String, Object> selectQcSttusDtlsList(GamFcltyQcSttusInqireVO searchVO) throws Exception {

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

		List resultList = gamFcltyQcSttusInqireService.selectQcMngDtlsList(searchVO);
		int totCnt = gamFcltyQcSttusInqireService.selectQcMngDtlsListTotCnt(searchVO);
		
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
	@RequestMapping(value="/fcltyMng/excelDownloadQcSttusDtlsList.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView excelDownloadQcSttusDtlsList(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltyQcSttusInqireVO searchVO= new GamFcltyQcSttusInqireVO();
		searchVO = mapper.convertValue(excelParam, GamFcltyQcSttusInqireVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltyQcSttusInqireService.selectQcMngDtlsList(searchVO);

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
	@RequestMapping(value="/fcltyMng/selectQcSttusDtlsDetail.do")
    @ResponseBody Map<String, Object> selectQcSttusDtlsDetail(GamFcltyQcSttusInqireVO searchVO) throws Exception {
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
        	detailData = gamFcltyQcSttusInqireService.selectQcMngDtlsDetail(searchVO);
        	atchFileList = gamFcltyQcSttusInqireService.selectQcMngAtchFileList(searchVO);
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
	@RequestMapping(value="/fcltyMng/selectQcSttusObjFcltsList.do")
	@ResponseBody Map<String, Object> selectQcSttusObjFcltsList(GamFcltyQcSttusInqireVO searchVO) throws Exception {

		Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		List resultList = gamFcltyQcSttusInqireService.selectQcMngObjFcltsList(searchVO);
		
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
	@RequestMapping(value="/fcltyMng/selectQcSttusResultItemList.do")
	@ResponseBody Map<String, Object> selectQcSttusResultItemList(GamFcltyQcSttusInqireVO searchVO) throws Exception {

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
    		resultList = gamFcltyQcSttusInqireService.selectMechQcMngResultItemList(searchVO);
    	}
    	else {
	    	int resultCnt = gamFcltyQcSttusInqireService.selectQcMngResultItemListTotCnt(searchVO);
	    	
	    	if(resultCnt > 0) {
	    		//갯수가 0이상이면 해당 점검조건에 대한 리스트이므로 그대로 보여준다.
	    		resultList = gamFcltyQcSttusInqireService.selectQcMngResultItemList(searchVO);
	    	} else {
	    		//갯수가 0이면 해당 점검조건 중에 업무구분에 해당하는 리스트만 보여준다.
	    		searchVO.setsFcltsMngGroupNo(null);
	    		searchVO.setsQcMngSeq("");
	    		resultList = gamFcltyQcSttusInqireService.selectQcMngResultItemList(searchVO);
	    	}
    	}
    	
		map.put("resultCode", 0);			// return ok
    	map.put("resultList", resultList);
    	return map;
    }

	
	/**
	 * 건축시설물 점검표 인쇄
	 * @param map
	 * @return 
	 * @throws Exception
	 */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/fcltyMng/selectFcltyQcSttusPrintA.do")
	public String printQcMngDtls(@RequestParam Map<String, Object> qcPrintOpt, ModelMap model) throws Exception {
    	String printPageName = null;
    	
		ObjectMapper mapper = new ObjectMapper();
		GamFcltyQcSttusInqireVO searchVO = null;
		List qcResultItemList = null;
		int resultCnt = 0;
    	
    	searchVO = mapper.convertValue(qcPrintOpt, GamFcltyQcSttusInqireVO.class);
    	
    	searchVO.setsFcltsJobSe(searchVO.getFcltsJobSe());
    	searchVO.setsFcltsMngGroupNo(searchVO.getFcltsMngGroupNo());
    	searchVO.setsQcMngSeq(searchVO.getQcMngSeq());
    	
    	    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return printPageName;
    	}
    	
		EgovMap detailData = gamFcltyQcSttusInqireService.selectQcMngDtlsDetail(searchVO);
				
		resultCnt = gamFcltyQcSttusInqireService.selectQcMngResultItemListTotCnt(searchVO);
    	if(resultCnt > 0) {
    		qcResultItemList = gamFcltyQcSttusInqireService.selectQcMngResultItemList(searchVO);
    	} else {
    		searchVO.setsFcltsMngGroupNo(null);
    		searchVO.setsQcMngSeq("");
    		qcResultItemList = gamFcltyQcSttusInqireService.selectQcMngResultItemList(searchVO);
    	}    		
		
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
    	model.addAttribute("resultList", qcResultItemList);
    	model.addAttribute("detailData", detailData);
    	
    	
    	return "/ygpa/gam/fcltyMng/GamFcltyQcPrintA";
	}

    /**
	 * 정보통신 시설물 점검표 인쇄
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/fcltyMng/selectFcltyQcSttusPrintI.do")
	public String selectFcltyQcPrintI(@RequestParam Map<String, Object> qcPrintOpt, ModelMap model) throws Exception {
		String printPageName = null;
		
		ObjectMapper mapper = new ObjectMapper();
		GamFcltyQcSttusInqireVO searchVO = null;
		List qcResultItemList = null;
		int resultCnt = 0;
		
		searchVO = mapper.convertValue(qcPrintOpt, GamFcltyQcSttusInqireVO.class);
		
		searchVO.setsFcltsJobSe(searchVO.getFcltsJobSe());
		searchVO.setsFcltsMngGroupNo(searchVO.getFcltsMngGroupNo());
		searchVO.setsQcMngSeq(searchVO.getQcMngSeq());
		
		    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		if(!isAuthenticated) {
			model.addAttribute("resultCode", 1);
			model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
	    	return printPageName;
		}
		
		EgovMap detailData = gamFcltyQcSttusInqireService.selectQcMngDtlsDetail(searchVO);
				
		resultCnt = gamFcltyQcSttusInqireService.selectQcMngResultItemListTotCnt(searchVO);
		if(resultCnt > 0) {
			qcResultItemList = gamFcltyQcSttusInqireService.selectQcMngResultItemList(searchVO);
		} else {
			searchVO.setsFcltsMngGroupNo(null);
			searchVO.setsQcMngSeq("");
			qcResultItemList = gamFcltyQcSttusInqireService.selectQcMngResultItemList(searchVO);
		}    		
		
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("resultList", qcResultItemList);
		model.addAttribute("detailData", detailData);
		
		
		return "/ygpa/gam/fcltyMng/GamFcltyQcPrintI";
	}
    
    
    /**
	 * 전기 시설물 점검표 인쇄
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/fcltyMng/selectFcltyQcSttusPrintE.do")
	public String selectFcltyQcPrintE(@RequestParam Map<String, Object> qcPrintOpt, ModelMap model) throws Exception {
		String printPageName = null;
		
		ObjectMapper mapper = new ObjectMapper();
		GamFcltyQcSttusInqireVO searchVO = null;
		List qcResultItemList = null;
		int resultCnt = 0;
		
		searchVO = mapper.convertValue(qcPrintOpt, GamFcltyQcSttusInqireVO.class);
		
		searchVO.setsFcltsJobSe(searchVO.getFcltsJobSe());
		searchVO.setsFcltsMngGroupNo(searchVO.getFcltsMngGroupNo());
		searchVO.setsQcMngSeq(searchVO.getQcMngSeq());
		
		    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		if(!isAuthenticated) {
			model.addAttribute("resultCode", 1);
			model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
	    	return printPageName;
		}
		
		EgovMap detailData = gamFcltyQcSttusInqireService.selectQcMngDtlsDetail(searchVO);
				
		resultCnt = gamFcltyQcSttusInqireService.selectQcMngResultItemListTotCnt(searchVO);
		if(resultCnt > 0) {
			qcResultItemList = gamFcltyQcSttusInqireService.selectQcMngResultItemList(searchVO);
		} else {
			searchVO.setsFcltsMngGroupNo(null);
			searchVO.setsQcMngSeq("");
			qcResultItemList = gamFcltyQcSttusInqireService.selectQcMngResultItemList(searchVO);
		}    		
		
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("resultList", qcResultItemList);
		model.addAttribute("detailData", detailData);
		
		
		return "/ygpa/gam/fcltyMng/GamFcltyQcPrintE";
	}
    

    /**
	 * 토목 시설물 점검표 인쇄
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/fcltyMng/selectFcltyQcSttusPrintC.do")
	public String selectFcltyQcPrintC(@RequestParam Map<String, Object> qcPrintOpt, ModelMap model) throws Exception {
		String printPageName = null;
		
		ObjectMapper mapper = new ObjectMapper();
		GamFcltyQcSttusInqireVO searchVO = null;
		List qcResultItemList = null;
		int resultCnt = 0;
		
		searchVO = mapper.convertValue(qcPrintOpt, GamFcltyQcSttusInqireVO.class);
		
		searchVO.setsFcltsJobSe(searchVO.getFcltsJobSe());
		searchVO.setsFcltsMngGroupNo(searchVO.getFcltsMngGroupNo());
		searchVO.setsQcMngSeq(searchVO.getQcMngSeq());
		
		    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		if(!isAuthenticated) {
			model.addAttribute("resultCode", 1);
			model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
	    	return printPageName;
		}
		
		
		EgovMap detailMngGroup = gamFcltyQcSttusInqireService.selectFcltsMngGroupInfo(searchVO);
		
		EgovMap detailData = gamFcltyQcSttusInqireService.selectQcMngDtlsDetail(searchVO);
				
		resultCnt = gamFcltyQcSttusInqireService.selectQcMngResultItemListTotCnt(searchVO);
		if(resultCnt > 0) {
			qcResultItemList = gamFcltyQcSttusInqireService.selectQcMngResultItemList(searchVO);
		} else {
			searchVO.setsFcltsMngGroupNo(null);
			searchVO.setsQcMngSeq("");
			qcResultItemList = gamFcltyQcSttusInqireService.selectQcMngResultItemList(searchVO);
		}    		
		
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("detailMngGroup", detailMngGroup);
		model.addAttribute("resultList", qcResultItemList);
		model.addAttribute("detailData", detailData);
		
		
		return "/ygpa/gam/fcltyMng/GamFcltyQcPrintC";
	}

    /**
	 * 항만 하역장비 시설물 점검표 인쇄
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/fcltyMng/selectFcltyQcSttusPrintM1.do")
	public String selectFcltyQcPrintM1(@RequestParam Map<String, Object> qcPrintOpt, ModelMap model) throws Exception {
		String printPageName = null;
		
		ObjectMapper mapper = new ObjectMapper();
		GamFcltyQcSttusInqireVO searchVO = null;
		List qcResultItemList = null;
		
		searchVO = mapper.convertValue(qcPrintOpt, GamFcltyQcSttusInqireVO.class);
		
		searchVO.setsFcltsJobSe(searchVO.getFcltsJobSe());
		searchVO.setsFcltsMngGroupNo(searchVO.getFcltsMngGroupNo());
		searchVO.setsQcMngSeq(searchVO.getQcMngSeq());
		
		    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		if(!isAuthenticated) {
			model.addAttribute("resultCode", 1);
			model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
	    	return printPageName;
		}
		
		EgovMap detailData = gamFcltyQcSttusInqireService.selectQcMngDtlsDetail(searchVO);
		
		qcResultItemList = gamFcltyQcSttusInqireService.selectMechQcMngResultItemList(searchVO);
		
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("resultList", qcResultItemList);
		model.addAttribute("detailData", detailData);
		
		
		return "/ygpa/gam/fcltyMng/GamFcltyQcPrintM1";
	}
	
    /**
	 * 기계설비 점검표 인쇄
	 * @param map
	 * @return 
	 * @throws Exception
	 */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/fcltyMng/selectFcltyQcSttusPrintM2.do")
	public String selectFcltyQcPrintM2(@RequestParam Map<String, Object> qcPrintOpt, ModelMap model) throws Exception {
    	String printPageName = null;
    	
		ObjectMapper mapper = new ObjectMapper();
		GamFcltyQcSttusInqireVO searchVO = null;
    	
    	searchVO = mapper.convertValue(qcPrintOpt, GamFcltyQcSttusInqireVO.class);
    	
    	searchVO.setsFcltsJobSe(searchVO.getFcltsJobSe());
    	searchVO.setsFcltsMngGroupNo(searchVO.getFcltsMngGroupNo());
    	searchVO.setsQcMngSeq(searchVO.getQcMngSeq());
    	    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return printPageName;
    	}
    	
		List mechQcMngResultItemList = gamFcltyQcSttusInqireService.selectMechQcMngResultItemList(searchVO);
		
		EgovMap detailData = gamFcltyQcSttusInqireService.selectQcMngDtlsDetail(searchVO);
				
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
    	model.addAttribute("resultList", mechQcMngResultItemList);
    	model.addAttribute("detailData", detailData);
    	
    	
    	return "/ygpa/gam/fcltyMng/GamFcltyQcPrintM2";
	}
}