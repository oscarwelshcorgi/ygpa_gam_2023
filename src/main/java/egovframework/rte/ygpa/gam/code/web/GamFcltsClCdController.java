/**
 * 
 */
package egovframework.rte.ygpa.gam.code.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.code.service.GamFcltsClCdService;
import egovframework.rte.ygpa.gam.code.service.GamFcltsClCdVO;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 17.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltsClCdController {
	
	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    
    @Resource(name = "gamFcltsClCdService")
	protected GamFcltsClCdService gamFcltsClCdService;
    
    
	@RequestMapping(value="/code/gamFcltsClCd.do")
    String indexFcltsClCd(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		
		List<?> mainFcltyClCdList = gamFcltsClCdService.selectMainFcltsClCdList();
		
    	model.addAttribute("windowId", windowId);
    	model.addAttribute("mainFcltyClCdList", mainFcltyClCdList);
    	return "/ygpa/gam/code/GamFcltsClCd";
    	
    }
	
	
	/**
	 * 시설물분류코드목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/selectFcltsClCdList.do")
	@ResponseBody Map<String, Object> selectFcltsClCdList(GamFcltsClCdVO searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

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

		/** List Data */

		List<?> fcltyClCdList = gamFcltsClCdService.selectFcltsClCdList(searchVO);

        int totCnt = gamFcltsClCdService.selectFcltsClCdListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", fcltyClCdList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 시설물분류코드 상세
	 * @param fcltyManageVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/selectFcltsClCdDetail.do")
    @ResponseBody Map<String, Object> selectFcltsClCdDetail(@RequestParam Map fcltsClCdVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	EgovMap result=null;
    	ModelMap model = null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	result = gamFcltsClCdService.selectFcltsClCdDetail(fcltsClCdVO);
    	
    	fcltsClCdVO.put("mainFcltsDiv", result.get("mainFcltsDiv"));
    	fcltsClCdVO.put("depthSort", result.get("depthSort"));
    	
    	List<?> fcltsClUpperCdList = gamFcltsClCdService.selectFcltsClUpperCdList(fcltsClCdVO);

        map.put("resultCode", 0);
        map.put("result", result);
        map.put("fcltsClUpperCdList", fcltsClUpperCdList);

        return map;
    }
	
	
	
	/**
	 * 시설물분류 상위코드 리스트
	 * @param fcltyManageVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/selectFcltsClUpperCdList.do")
    @ResponseBody Map<String, Object> selectFcltsClUpperCdList(@RequestParam Map fcltsClCdVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	EgovMap result=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	List<?> fcltsClUpperCdList = gamFcltsClCdService.selectFcltsClUpperCdList(fcltsClCdVO);

        map.put("resultCode", 0);
        map.put("fcltsClUpperCdList", fcltsClUpperCdList);

        return map;
    }
	
	
    
    

}
