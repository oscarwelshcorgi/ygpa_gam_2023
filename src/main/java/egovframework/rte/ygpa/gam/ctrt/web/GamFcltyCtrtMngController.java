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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngService;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltyMngtService;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyManageVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyMngtService;

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
public class GamFcltyCtrtMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="gamFcltyCtrtMngService")
    GamFcltyCtrtMngService gamFcltyCtrtMngService;
    
	@RequestMapping(value="/ctrt/gamFcltyCtrtMng.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

    	model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/ctrt/GamFcltyCtrtMng";
    }

    @RequestMapping(value="/soc/gamSelectCtrtInfoDetailInquire.do")
	@ResponseBody Map selectSocCtrtInfoDetailInquire( @ModelAttribute("gamFcltyCtrtMngVO") GamFcltyCtrtMngVO gamFcltyCtrtMngVO, BindingResult bindingResult)
			throws Exception {
		Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
    	GamFcltyCtrtMngVO resultVO = gamFcltyCtrtMngService.selectFcltyCtrtInfoDetail(gamFcltyCtrtMngVO);
		
		if(resultVO == null) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		} else {
			map.put("resultCode", 0);
			map.put("resultVO", resultVO);
		}
		
    	return map;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/gamSelectFcltyCtrtJoinContrList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtJoinContrList(GamFcltyCtrtMngVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
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
		
    	List ctrtJoinContrList = gamFcltyCtrtMngService.selectFcltyCtrtJoinContrList(searchVO);
    	
		totalCnt = gamFcltyCtrtMngService.selectFcltyCtrtJoinContrListCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", ctrtJoinContrList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/gamSelectFcltyCtrtSubCtrtList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtSubCtrtList(GamFcltyCtrtMngVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
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
		
    	List ctrtSubCtrtList = gamFcltyCtrtMngService.selectFcltyCtrtSubCtrtList(searchVO);
    	
		totalCnt = gamFcltyCtrtMngService.selectFcltyCtrtSubCtrtListCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", ctrtSubCtrtList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/gamSelectFcltyCtrtChangeList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtChangeList(GamFcltyCtrtMngVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
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
		
    	List ctrtChangeList = gamFcltyCtrtMngService.selectFcltyCtrtChangeList(searchVO);
    	
		totalCnt = gamFcltyCtrtMngService.selectFcltyCtrtChangeListCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", ctrtChangeList);
    	map.put("searchOption", searchVO);

    	return map;
    }


	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/gamSelectFcltyCtrtMoneyPymntList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtMoneyPymntList(GamFcltyCtrtMngVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
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
		
    	List ctrtMoneyPymntList = gamFcltyCtrtMngService.selectFcltyCtrtMoneyPymntList(searchVO);
    	
		totalCnt = gamFcltyCtrtMngService.selectFcltyCtrtMoneyPymntListCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", ctrtMoneyPymntList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/ctrt/gamSelectFcltyCtrtFulFillCaryFwdList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyCtrtFulFillCaryFwdList(GamFcltyCtrtMngVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
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
		
    	List ctrtFulFillCaryFwdList = gamFcltyCtrtMngService.selectFcltyCtrtFulFillCaryFwdList(searchVO);
    	
		totalCnt = gamFcltyCtrtMngService.selectFcltyCtrtFulFillCaryFwdListCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", ctrtFulFillCaryFwdList);
    	map.put("searchOption", searchVO);

    	return map;
    }	
}
