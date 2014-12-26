/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.web;

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
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fclty.service.GamConsFcltySpecInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamConsFcltySpecInqireVO;

/**
 * 
 * @author LFIT
 * @since 2014. 12. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 5.		김영길		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamConsFcltySpecInqireController {
	
	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@Resource(name = "gamConsFcltySpecInqireService")
	protected GamConsFcltySpecInqireService gamConsFcltySpecInqireService;
	
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    private final static String prtFcltySe = "A";
    
    
    /**
	 * 건축시설 조회화면호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamConstFcltySpecInqire.do")
	String indexConstFcltySpecInqire(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
		return "/ygpa/gam/fclty/GamConstFcltySpecInqire";
	}

	/**
	 * 건축시설목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamConstFcltySpecInqireList.do")
	@ResponseBody Map<String, Object> selectFcltySpecInqireList(GamConsFcltySpecInqireVO searchVO)throws Exception {

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
		searchVO.setPrtFcltySe(prtFcltySe);
		List fcltyInqireList = gamConsFcltySpecInqireService.selectFcltySpecInqireList(searchVO);

        int totCnt = gamConsFcltySpecInqireService.selectFcltySpecInqireListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", fcltyInqireList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	/**
	 * 건축 시설관리 상세
	 * @param fcltyManageVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamConstFcltySpecInqireDetail.do")
    @ResponseBody Map<String, Object> fcltyInqireSelectView(@RequestParam Map fcltyManageVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	String fcltsMngNo;
    	EgovMap result=null;
    	EgovMap specResult=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		
        	result = gamConsFcltySpecInqireService.fcltInqireSelectView(fcltyManageVO);
        	
        	System.out.print("test : " + result);
        	fcltsMngNo = (String) result.get("fcltsMngNo");
        	fcltyManageVO.put("fcltsMngNo", fcltsMngNo);
        	
        	specResult = gamConsFcltySpecInqireService.fcltySpecInqireSelectView(fcltyManageVO);
    	}
    	catch(Exception e) {
            map.put("resultCode", 2);
            map.put("resultMsg", e.getMessage());
            return map;
    	}

        map.put("resultCode", 0);
        map.put("result", result);
        map.put("specResult", specResult);
        
        return map;
    }
	
	/**
	 * 건축시설 파일 목록
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamConstFcltySpecInqireFileList.do")
	@ResponseBody Map<String, Object> selectFcltySpecInqireFileList(GamConsFcltySpecInqireVO searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

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
		searchVO.setPrtFcltySe(prtFcltySe);

		List<?> fcltyMngtFileList = gamConsFcltySpecInqireService.selectFcltySpecInqireFileList(searchVO);
		int totCnt = gamConsFcltySpecInqireService.selectFcltySpecInqireFileListTotCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
		map.put("totalCount", totCnt);
		map.put("resultList", fcltyMngtFileList);
		map.put("searchOption", searchVO);

		return map;
	}	

}
