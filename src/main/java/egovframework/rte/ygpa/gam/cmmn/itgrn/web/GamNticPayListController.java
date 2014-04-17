package egovframework.rte.ygpa.gam.cmmn.itgrn.web;

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

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.cmmn.itgrn.service.GamNticPayListService;
import egovframework.rte.ygpa.gam.cmmn.itgrn.service.GamNticPayListVO;


@Controller
public class GamNticPayListController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovProgrmManageService */
	@Resource(name = "gamNticPayListService")
    private GamNticPayListService gamNticPayListService;

	/** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;
    
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    
	@RequestMapping(value="/cmmn/itgrn/gamNticPayList.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM019"); //항코드 
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM024"); //요금종류
		List<?> chrgeKndCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM025"); //수납구분 
		List<?> rcivSeCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		model.addAttribute("windowId", windowId);
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);
		model.addAttribute("rcivSeCdList", rcivSeCdList);
		
    	return "/ygpa/gam/cmmn/itgrn/GamNticPayList";
    }


	/**
	 * 세입목록을 조회한다.
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping(value="/cmmn/itgrn/gamNticPayListSelect.do", method=RequestMethod.POST)
    @ResponseBody Map<String, Object> selectNticPayList(GamNticPayListVO searchVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
		
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회
    	/** EgovPropertyService */
//    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
//    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
		int totCnt = gamNticPayListService.selectNticPayListTotCnt(searchVO);
		List nticPayList = gamNticPayListService.selectNticPayList(searchVO);
		
//		System.out.print("test *************************** : " + nticPayList);
        
        paginationInfo.setTotalRecordCount(totCnt);
		
		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", nticPayList);
    	map.put("searchOption", searchVO);

    	return map;
    }
    
    /**
	 * 세입목록을 엑셀로 다운로드한다.
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/cmmn/itgrn/gamNticPayListSelectExcel.do", method=RequestMethod.POST)
    @ResponseBody ModelAndView selectNticPayListListExcel(@RequestParam Map<String, Object> excelParam) throws Exception {
		Map map = new HashMap();
		List header;
		ObjectMapper mapper = new ObjectMapper();

    	// 환경설정
    	/** EgovPropertyService */
		GamNticPayListVO searchVO= new GamNticPayListVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.

		// 조회 조건
		searchVO = mapper.convertValue(excelParam, GamNticPayListVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		/** List Data */
		int totCnt = gamNticPayListService.selectNticPayListTotCnt(searchVO);
		List nticPayList = gamNticPayListService.selectNticPayList(searchVO);

    	map.put("resultList", nticPayList);
    	map.put("header", header);

    	return new ModelAndView("gridExcelView", "gridResultMap", map);
    }
    
    
    /**
	 * 연체세입목록을 조회한다.
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping(value="/cmmn/itgrn/gamDelayNticPayListSelect.do", method=RequestMethod.POST)
    @ResponseBody Map<String, Object> selectDelayNticPayList(GamNticPayListVO searchVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
		
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회
    	/** EgovPropertyService */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
		List nticPayList = gamNticPayListService.selectDelayNticPayList(searchVO);
        int totCnt = gamNticPayListService.selectDelayNticPayListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
		
		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", nticPayList);
    	map.put("searchOption", searchVO);

    	return map;
    }
    
    
    /**
	 * 연체세입목록을 엑셀로 다운로드한다.
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/cmmn/itgrn/gamDelayNticPayListSelectExcel.do", method=RequestMethod.POST)
    @ResponseBody ModelAndView selectDelayNticPayListListExcel(@RequestParam Map<String, Object> excelParam) throws Exception {
		Map map = new HashMap();
		List header;
		ObjectMapper mapper = new ObjectMapper();

    	// 환경설정
    	/** EgovPropertyService */
		GamNticPayListVO searchVO= new GamNticPayListVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.

		// 조회 조건
		searchVO = mapper.convertValue(excelParam, GamNticPayListVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		/** List Data */
		int totCnt = gamNticPayListService.selectDelayNticPayListTotCnt(searchVO);
		List nticPayList = gamNticPayListService.selectDelayNticPayList(searchVO);

    	map.put("resultList", nticPayList);
    	map.put("header", header);

    	return new ModelAndView("gridExcelView", "gridResultMap", map);
    }
}