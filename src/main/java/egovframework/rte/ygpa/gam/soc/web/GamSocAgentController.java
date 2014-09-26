/**
 *
 */
package egovframework.rte.ygpa.gam.soc.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;


import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.soc.service.GamSocAgentService;
import egovframework.rte.ygpa.gam.soc.service.GamSocAgentVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseVO;



/**
 *
 * @author Lee
 * @since 2014. 9. 19.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 19.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamSocAgentController {

	protected Log log = LogFactory.getLog(this.getClass());

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "gamSocCmmUseService")
    private GamSocCmmUseService gamSocCmmUseService;
    
    @Resource(name = "gamSocAgentService")
    private GamSocAgentService gamSocAgentService;


    @RequestMapping(value="/soc/gamSocAgent.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

    	GamSocCmmUseVO codeVo = new GamSocCmmUseVO();
		
		codeVo.setCodeId("GAM019"); //항코드 
		
		List prtAtCdList = gamSocCmmUseService.selectSocPrtAtCodeDetail();
		List yearsList = this.getYears(); // 조회연도
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);
		model.addAttribute("yearsList", yearsList);

    	return "/ygpa/gam/soc/GamSocAgent";
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
     * 항만공사허가원부목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSelectSocAgentList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectSocAgentList(GamSocAgentVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
		long sumTotalAmnt, sumAccFee;
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
		
		//항만공사허가원부 검색업체 정보
		GamSocAgentVO socAgentInfo = gamSocAgentService.selectSocAgentInfo(searchVO);
		
		if(socAgentInfo != null){
			searchVO.setPrtAtCode(socAgentInfo.getPrtAtCode());
			searchVO.setAgentCode(socAgentInfo.getAgentCode());
			searchVO.setCmplYr(socAgentInfo.getCmplYr());
		}

		//항만공사허가원부목록
    	List socAgentList = gamSocAgentService.selectSocAgentList(searchVO);
    	
    	//항만공사허가원부목록 총갯수 및 금액합계
		GamSocAgentVO socAgentInfoSum = gamSocAgentService.selectSocAgentInfoSum(searchVO);
    	
		totalCnt = socAgentInfoSum.getTotalCnt();
    	sumTotalAmnt = socAgentInfoSum.getSumTotalAmnt();
    	sumAccFee = socAgentInfoSum.getSumAccFee();
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("sumTotalAmnt", sumTotalAmnt);
    	map.put("sumAccFee", sumAccFee);
    	map.put("resultList", socAgentList);
    	map.put("socAgentInfo", socAgentInfo);
    	map.put("searchOption", searchVO);

    	return map;
    }
}
