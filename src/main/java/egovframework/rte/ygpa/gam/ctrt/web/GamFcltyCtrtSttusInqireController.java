/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.web;

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
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtSttusInqireVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtSttusInqireService;



/**
 *
 * @author kok
 * @since 2014. 2. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 3.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltyCtrtSttusInqireController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name="gamFcltyCtrtSttusInqireService")
    GamFcltyCtrtSttusInqireService gamFcltyCtrtSttusInqireService;


	@RequestMapping(value="/ctrt/gamFcltyCtrtSttusInqire.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/ctrt/GamFcltyCtrtSttusInqire";
    }

	
	
	/**
     * 계약이력목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/gamSelectFcltyCtrtSttusInqireList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtSttusInqireList(GamFcltyCtrtSttusInqireVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
		long sumPrevCtrtAmt, sumCurrCtrtAmt;
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
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

		
		//계약이력목록
    	List fcltyCtrtSttusInqireList = gamFcltyCtrtSttusInqireService.selectFcltyCtrtSttusInqireList(searchVO);
    	
    	//계약이력목록 총갯수 및 금액합계
		GamFcltyCtrtSttusInqireVO fcltyCtrtSttusInqireInfoSum = gamFcltyCtrtSttusInqireService.selectFcltyCtrtSttusInqireInfoSum(searchVO);
    	
		totalCnt = fcltyCtrtSttusInqireInfoSum.getTotalCnt();
		sumPrevCtrtAmt = fcltyCtrtSttusInqireInfoSum.getSumPrevCtrtAmt();
		sumCurrCtrtAmt = fcltyCtrtSttusInqireInfoSum.getSumCurrCtrtAmt();
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("sumPrevCtrtAmt", sumPrevCtrtAmt);
    	map.put("sumCurrCtrtAmt", sumCurrCtrtAmt);
    	map.put("resultList", fcltyCtrtSttusInqireList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
     * 시설물 계약이력을 인쇄한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/gamSelectFcltyCtrtSttusInqirePrint.do")
	public String selectFcltyCtrtSttusInqirePrint(@RequestParam Map<String, Object> fcltyCtrtSttusInqireOpt, ModelMap model) throws Exception {

		int joinTotalCnt, changeTotalCnt, moneyTotalCnt, page, firstIndex;
		String sPrevCtrtYr, sCtrtYr;
    	Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/ctrt/GamFcltyCtrtSttusInqirePrint";
    	}

		ObjectMapper mapper = new ObjectMapper();
		
		GamFcltyCtrtSttusInqireVO searchVO;
    	searchVO = mapper.convertValue(fcltyCtrtSttusInqireOpt, GamFcltyCtrtSttusInqireVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);
		
		//계약이력목록
    	List fcltyCtrtSttusInqireList = gamFcltyCtrtSttusInqireService.selectFcltyCtrtSttusInqireList(searchVO);
    	
    	sPrevCtrtYr = searchVO.getsPrevCtrtYr();
    	sCtrtYr = searchVO.getsCtrtYr();

        model.addAttribute("fcltyCtrtSttusInqireList", fcltyCtrtSttusInqireList);
        model.addAttribute("sPrevCtrtYr", sPrevCtrtYr);
        model.addAttribute("sCtrtYr", sCtrtYr);
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");

    	return "ygpa/gam/ctrt/GamFcltyCtrtSttusInqirePrint";
    }
	
	
	
	/**
	 * 시설물 계약이력을 엑셀로 다운로드한다.
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/gamSelectFcltyCtrtSttusInqireExcel.do", method=RequestMethod.POST)
    @ResponseBody ModelAndView selectFcltyCtrtSttusInqireExcel(@RequestParam Map<String, Object> excelParam) throws Exception {
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
    	GamFcltyCtrtSttusInqireVO searchVO= new GamFcltyCtrtSttusInqireVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.
       // System.out.print("test *************************** : " + excelParam);
		// 조회 조건
		searchVO = mapper.convertValue(excelParam, GamFcltyCtrtSttusInqireVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		
		//계약이력목록
    	List fcltyCtrtSttusInqireList = gamFcltyCtrtSttusInqireService.selectFcltyCtrtSttusInqireList(searchVO);
		

    	map.put("resultList", fcltyCtrtSttusInqireList);
    	map.put("header", header);

    	return new ModelAndView("gridExcelView", "gridResultMap", map);
    }
	

}