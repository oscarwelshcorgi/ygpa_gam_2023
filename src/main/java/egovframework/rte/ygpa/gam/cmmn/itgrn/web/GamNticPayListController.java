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

		model.addAttribute("windowId", windowId);

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

		//자료수와 고지금액합계,수납금액(수납구분이 2나 3)합계
		GamNticPayListVO resultSum = gamNticPayListService.selectNticPayListSum(searchVO);

//		System.out.print("test *************************** : " + nticPayList);

        paginationInfo.setTotalRecordCount(totCnt);

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", nticPayList);
    	map.put("searchOption", searchVO);

    	map.put("sumBillAmnt", resultSum.getSumBillAmnt());
    	map.put("sumRcvdAmnt", resultSum.getSumRcvdAmnt());
    	map.put("sumNotRcvdAmnt", resultSum.getSumNotRcvdAmnt());
    	map.put("dpTotCnt", resultSum.getDpTotCnt());

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

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    		return new ModelAndView("gridExcelView", "gridResultMap", map);
    	}

    	// 환경설정
    	/** EgovPropertyService */
		GamNticPayListVO searchVO= new GamNticPayListVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.
       // System.out.print("test *************************** : " + excelParam);
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

        //자료수와 미납고지금액합계
        GamNticPayListVO resultSum = gamNticPayListService.selectDelayNticPayListSum(searchVO);


        paginationInfo.setTotalRecordCount(totCnt);

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", nticPayList);
    	map.put("searchOption", searchVO);


    	map.put("sumDlyBillAmnt", resultSum.getSumDlyBillAmnt());
    	map.put("dpTotCnt", resultSum.getDpTotCnt());

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

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    		return new ModelAndView("gridExcelView", "gridResultMap", map);
    	}

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