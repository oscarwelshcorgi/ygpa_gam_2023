/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.web;

//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;

//import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
//import egovframework.com.cmm.LoginVO;
//import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
//import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
//import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintSttusInqireService;
//import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintSttusInqireVO;

/**
 *
 * @author HNJ
 * @since 2014. 12. 8.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 8.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltyMaintSttusInqireController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

//	@Resource(name = "gamFcltyMaintSttusInqireService")
//	protected GamFcltyMaintSttusInqireService gamFcltyMaintSttusInqireService;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
//    @Resource(name="gamFcltyMaintSttusInqireService")
//    protected GamFcltyMaintSttusInqireService gamFcltyMaintSttusInqireService;


	/**
     * 유지보수내역 관리화면호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/fcltyMng/gamFcltyMaintSttusInqire.do")
    String indexFcltyMaintSttusInqire(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fcltyMng/GamFcltyMaintSttusInqire";
    }
	
//	
//	/**
//	 * 유지보수내역 조회
//	 * @param searchVO
//	 * @return map
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/fcltyMng/selectFcltyMaintSttusInqireList.do")
//	@ResponseBody Map<String, Object> selectFcltyMaintSttusInqireList(GamFcltyMaintSttusInqireVO searchVO)throws Exception {
//
//		Map<String, Object> map = new HashMap<String, Object>();
//
//    	// 0. Spring Security 사용자권한 처리
//    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
//    	if(!isAuthenticated) {
//	        map.put("resultCode", 1);
//    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
//        	return map;
//    	}
//    	// 내역 조회
//    	/** pageing */
//    	PaginationInfo paginationInfo = new PaginationInfo();
//		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
//		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
//		paginationInfo.setPageSize(searchVO.getPageSize());
//
//		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
//		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
//		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
//
//		/** List Data */
//		List<?> fcltyMaintSttusInqireList = gamFcltyMaintSttusInqireService.selectFcltyMaintSttusInqireList(searchVO);
//
//        int totCnt = gamFcltyMaintSttusInqireService.selectFcltyMaintSttusInqireListTotCnt(searchVO);
//
//        paginationInfo.setTotalRecordCount(totCnt);
//        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
//
//		map.put("resultCode", 0);			// return ok
//    	map.put("totalCount", totCnt);
//    	map.put("resultList", fcltyMaintSttusInqireList);
//    	map.put("searchOption", searchVO);
//
//    	return map;
//    }
//	
//	
//	/**
//	 * 유지보수 대상시설물 조회
//	 * @param searchVO
//	 * @return map
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/fcltyMng/selectMntnRprObjFcltsFList.do")
//	@ResponseBody Map<String, Object> selectMntnRprObjFcltsFList(GamFcltyMaintSttusInqireVO searchVO)throws Exception {
//
//		Map<String, Object> map = new HashMap<String, Object>();
//
//    	// 0. Spring Security 사용자권한 처리
//    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
//    	if(!isAuthenticated) {
//	        map.put("resultCode", 1);
//    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
//        	return map;
//    	}
//    	// 내역 조회
//    	/** pageing */
//    	PaginationInfo paginationInfo = new PaginationInfo();
//		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
//		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
//		paginationInfo.setPageSize(searchVO.getPageSize());
//
//		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
//		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
//		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
//
//		/** List Data */
//		List<?> mntnRprObjFcltsFList = gamFcltyMaintSttusInqireService.selectMntnRprObjFcltsFList(searchVO);
//
//        int totCnt = gamFcltyMaintSttusInqireService.selectMntnRprObjFcltsFListTotCnt(searchVO);
//
//        paginationInfo.setTotalRecordCount(totCnt);
//        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
//
//		map.put("resultCode", 0);			// return ok
//    	map.put("totalCount", totCnt);
//    	map.put("resultList", mntnRprObjFcltsFList);
//    	map.put("searchOption", searchVO);
//
//    	return map;
//    }
//	
//	
//	/**
//	 * 유지보수 첨부파일 조회
//	 * @param searchVO
//	 * @return map
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/fcltyMng/selectFcltyMaintFileList.do")
//	@ResponseBody Map<String, Object> selectFcltyMaintFileList(GamFcltyMaintSttusInqireVO searchVO)throws Exception {
//
//		Map<String, Object> map = new HashMap<String, Object>();
//
//    	// 0. Spring Security 사용자권한 처리
//    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
//    	if(!isAuthenticated) {
//	        map.put("resultCode", 1);
//    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
//        	return map;
//    	}
//    	// 내역 조회
//    	/** pageing */
//    	PaginationInfo paginationInfo = new PaginationInfo();
//		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
//		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
//		paginationInfo.setPageSize(searchVO.getPageSize());
//
//		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
//		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
//		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
//
//		/** List Data */
//		List<?> fcltyMaintFileList = gamFcltyMaintSttusInqireService.selectFcltyMaintFileList(searchVO);
//
//        int totCnt = gamFcltyMaintSttusInqireService.selectFcltyMaintFileListTotCnt(searchVO);
//
//        paginationInfo.setTotalRecordCount(totCnt);
//        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
//
//		map.put("resultCode", 0);			// return ok
//    	map.put("totalCount", totCnt);
//    	map.put("resultList", fcltyMaintFileList);
//    	map.put("searchOption", searchVO);
//
//    	return map;
//    }
//	
	

}