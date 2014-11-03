/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.web;

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
import org.springmodules.validation.commons.DefaultBeanValidator;


import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtSttusInqireService;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtSttusInqireVO;


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
		
		
		//login정보 
    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	List yearsList = this.getYears(); // 조회연도
    	
    	model.addAttribute("windowId", windowId);
    	model.addAttribute("yearsList", yearsList);

    	return "/ygpa/gam/ctrt/GamFcltyCtrtSttusInqire";
    }

	
	/**
     * 조회기간 연도를 가져온다
     *
     */
	public List getYears(){

		java.util.Calendar cal = java.util.Calendar.getInstance();
		int currentYear = cal.get(cal.YEAR);
		List result = new ArrayList();
   		
   		for (int i = 2000; i <= currentYear; i++) {
   			
   			result.add(String.valueOf(i));
   		}

   		return result;
   	}
	
	
	/**
     * 계약정보목록을 조회한다.
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

		
		//계약대장목록
    	List fcltyCtrtSttusInqireList = gamFcltyCtrtSttusInqireService.selectFcltyCtrtSttusInqireList(searchVO);
    	
    	//계약대장목록 총갯수 및 금액합계
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
	

}