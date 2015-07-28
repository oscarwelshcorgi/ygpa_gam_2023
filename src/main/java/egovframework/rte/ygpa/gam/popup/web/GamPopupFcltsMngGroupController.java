/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFcltsMngGroupService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFcltsMngGroupVO;

/**
 * 
 * @author 김종민
 * @since 2014. 11. 14.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 14.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamPopupFcltsMngGroupController {
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
	
	@Resource(name = "gamPopupFcltsMngGroupService")
    private GamPopupFcltsMngGroupService gamPopupFcltsMngGroupService;
	
	@RequestMapping(value="/popup/showFcltsMngGroup.do")
    String showFcltsMngGroup(GamPopupFcltsMngGroupVO searchOpt, ModelMap model) throws Exception {
		model.addAttribute("searchOpt", searchOpt);
    	return "/ygpa/gam/popup/GamPopupFcltsMngGroup";
    }

	@RequestMapping(value="/popup/showCvlFcltsMngGroup.do")
    String showCvlFcltsMngGroup(GamPopupFcltsMngGroupVO searchOpt, ModelMap model) throws Exception {
		model.addAttribute("searchOpt", searchOpt);
    	return "/ygpa/gam/popup/GamPopupCvlFcltsMngGroup";
    }
	
	@RequestMapping(value="/popup/showArchFcltsMngGroup.do")
    String showArchFcltsMngGroup(GamPopupFcltsMngGroupVO searchOpt, ModelMap model) throws Exception {
		model.addAttribute("searchOpt", searchOpt);
    	return "/ygpa/gam/popup/GamPopupArchFcltsMngGroup";
    }
	
	@RequestMapping(value="/popup/showElectyFcltsMngGroup.do")
    String showElectyFcltsMngGroup(GamPopupFcltsMngGroupVO searchOpt, ModelMap model) throws Exception {
		model.addAttribute("searchOpt", searchOpt);
    	return "/ygpa/gam/popup/GamPopupElectyFcltsMngGroup";
    }
	
	@RequestMapping(value="/popup/showInfoCommFcltsMngGroup.do")
    String showInfoCommFcltsMngGroup(GamPopupFcltsMngGroupVO searchOpt, ModelMap model) throws Exception {
		model.addAttribute("searchOpt", searchOpt);
    	return "/ygpa/gam/popup/GamPopupInfoCommFcltsMngGroup";
    }
	
	@RequestMapping(value="/popup/showMachFcltsMngGroup.do")
    String showMachFcltsMngGroup(GamPopupFcltsMngGroupVO searchOpt, ModelMap model) throws Exception {
		model.addAttribute("searchOpt", searchOpt);
    	return "/ygpa/gam/popup/GamPopupMachFcltsMngGroup";
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/popup/selectFcltsMngGroupInfoList.do", method=RequestMethod.POST)
	@ResponseBody Map selectFcltsMngGroupInfoList(GamPopupFcltsMngGroupVO searchVO) throws Exception {
		int totalCnt;
    	Map map = new HashMap();

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List resultList = gamPopupFcltsMngGroupService.selectFcltsMngGroupList(searchVO);
    	totalCnt = gamPopupFcltsMngGroupService.selectFcltsMngGroupListTotCnt(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
		
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/popup/selectCvlFcltsMngGroupInfoList.do", method=RequestMethod.POST)
	@ResponseBody Map selectCvlFcltsMngGroupInfoList(GamPopupFcltsMngGroupVO searchVO) throws Exception {
		int totalCnt;
    	Map map = new HashMap();

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		searchVO.setsFcltsMngGroup("C");
		
		List resultList = gamPopupFcltsMngGroupService.selectFcltsMngGroupList(searchVO);
    	totalCnt = gamPopupFcltsMngGroupService.selectFcltsMngGroupListTotCnt(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
		
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/popup/selectArchFcltsMngGroupInfoList.do", method=RequestMethod.POST)
	@ResponseBody Map selectArchFcltsMngGroupInfoList(GamPopupFcltsMngGroupVO searchVO) throws Exception {
		int totalCnt;
		Map map = new HashMap();
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		searchVO.setsFcltsMngGroup("A");
		
		List resultList = gamPopupFcltsMngGroupService.selectFcltsMngGroupList(searchVO);
		totalCnt = gamPopupFcltsMngGroupService.selectFcltsMngGroupListTotCnt(searchVO);
		
		paginationInfo.setTotalRecordCount(totalCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
		
		map.put("resultCode", 0);	// return ok
		map.put("totalCount", totalCnt);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);
		
		return map;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/popup/selectElectyFcltsMngGroupInfoList.do", method=RequestMethod.POST)
	@ResponseBody Map selectElectyFcltsMngGroupInfoList(GamPopupFcltsMngGroupVO searchVO) throws Exception {
		int totalCnt;
    	Map map = new HashMap();

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		searchVO.setsFcltsMngGroup("E");
		
		List resultList = gamPopupFcltsMngGroupService.selectFcltsMngGroupList(searchVO);
    	totalCnt = gamPopupFcltsMngGroupService.selectFcltsMngGroupListTotCnt(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
		
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/popup/selectInfoCommFcltsMngGroupInfoList.do", method=RequestMethod.POST)
	@ResponseBody Map selectInfoCommFcltsMngGroupInfoList(GamPopupFcltsMngGroupVO searchVO) throws Exception {
		int totalCnt;
    	Map map = new HashMap();

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		searchVO.setsFcltsMngGroup("I");
		
		List resultList = gamPopupFcltsMngGroupService.selectFcltsMngGroupList(searchVO);
    	totalCnt = gamPopupFcltsMngGroupService.selectFcltsMngGroupListTotCnt(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
		
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/popup/selectMachFcltsMngGroupInfoList.do", method=RequestMethod.POST)
	@ResponseBody Map selectMachFcltsMngGroupInfoList(GamPopupFcltsMngGroupVO searchVO) throws Exception {
		int totalCnt;
    	Map map = new HashMap();

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		searchVO.setsFcltsMngGroup("M");

		List resultList = gamPopupFcltsMngGroupService.selectFcltsMngGroupList(searchVO);
    	totalCnt = gamPopupFcltsMngGroupService.selectFcltsMngGroupListTotCnt(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
		
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
}
