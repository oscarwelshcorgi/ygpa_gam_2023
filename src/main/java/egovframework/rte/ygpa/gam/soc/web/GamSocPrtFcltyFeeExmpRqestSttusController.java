/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;
import egovframework.rte.ygpa.gam.soc.service.GamSocPrtFcltyFeeExmpRqestSttusService;
import egovframework.rte.ygpa.gam.soc.service.GamSocPrtFcltyFeeExmpRqestSttusVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 13.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 13.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamSocPrtFcltyFeeExmpRqestSttusController {
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

    /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;
    
    @Resource(name = "gamSocCmmUseService")
    private GamSocCmmUseService gamSocCmmUseService;
    
    @Resource(name = "gamSocPrtFcltyFeeExmpRqestSttusService")
    private GamSocPrtFcltyFeeExmpRqestSttusService gamSocPrtFcltyFeeExmpRqestSttusService;
    
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/soc/gamSocPrtFcltyFeeExmpRqestSttus.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		List prtAtCdList = gamSocCmmUseService.selectSocPrtAtCodeDetail();
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/soc/GamSocPrtFcltyFeeExmpRqestSttus";
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSelectSocPrtFcltyFeeExmpRqestSttusList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectSocPrtFcltyFeeExmpRqestSttusList(GamSocPrtFcltyFeeExmpRqestSttusVO searchVO) throws Exception {
		
		int totalCnt;
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
		
    	List socPrtFcltyFeeExmpRqestSttusList = gamSocPrtFcltyFeeExmpRqestSttusService.selectSocPrtFcltyFeeExmpRqestSttusList(searchVO);
    	GamSocPrtFcltyFeeExmpRqestSttusVO resultVO = gamSocPrtFcltyFeeExmpRqestSttusService.selectSocPrtFcltyFeeExmpRqestSttusListTotSum(searchVO);
    	
		totalCnt = (resultVO != null) ? resultVO.getTotCnt() : 0;
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
        map.put("sumExmpAmnt", (resultVO != null) ? resultVO.getSumExmpAmnt() : 0);
        map.put("sumExmpAcc", (resultVO != null) ? resultVO.getSumExmpAcc() : 0);    	
    	map.put("resultList", socPrtFcltyFeeExmpRqestSttusList);
    	map.put("searchOption", searchVO);

    	return map;
    }       

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSelectSocPrtFcltyFeeExmpRqestSttusListPrint.do")
	public String selectSocPrtFcltyFeeExmpRqestSttusList(@RequestParam Map<String, Object> socPrtFcltyFeeExmpRqestSttusOpt, ModelMap model) throws Exception {
		
		int totalCnt;
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    		return "/ygpa/gam/soc/GamSocPrtFcltyFeeExmpRqestSttusPrint";
    	}

		ObjectMapper mapper = new ObjectMapper();
		GamSocPrtFcltyFeeExmpRqestSttusVO searchVO;
		
		searchVO = mapper.convertValue(socPrtFcltyFeeExmpRqestSttusOpt, GamSocPrtFcltyFeeExmpRqestSttusVO.class);
		
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);
		
    	List socPrtFcltyFeeExmpRqestSttusList = gamSocPrtFcltyFeeExmpRqestSttusService.selectSocPrtFcltyFeeExmpRqestSttusList(searchVO);
    	GamSocPrtFcltyFeeExmpRqestSttusVO resultVO = gamSocPrtFcltyFeeExmpRqestSttusService.selectSocPrtFcltyFeeExmpRqestSttusListTotSum(searchVO);
    	
		totalCnt = (resultVO != null) ? resultVO.getTotCnt() : 0;
    	 
		model.addAttribute("resultCode", 0);	// return ok
		model.addAttribute("totalCount", totalCnt);
		model.addAttribute("sumExmpAmnt", (resultVO != null) ? resultVO.getSumExmpAmnt() : 0);
		model.addAttribute("sumExmpAcc", (resultVO != null) ? resultVO.getSumExmpAcc() : 0);    	
		model.addAttribute("resultList", socPrtFcltyFeeExmpRqestSttusList);

    	return "/ygpa/gam/soc/GamSocPrtFcltyFeeExmpRqestSttusPrint";
    }       
	
}
