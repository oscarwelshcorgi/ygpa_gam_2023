/**
 *
 */
package egovframework.rte.ygpa.gam.code.web;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.code.service.GamOlnlpFVO;
import egovframework.rte.ygpa.gam.code.service.GamOlnlpInqireService;
import egovframework.rte.ygpa.gam.code.service.GisAssetsCodeVO;

/**
 *  공시지가 조회 컨트롤러
 * @author eunsungj
 * @since 2014. 3. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014.04.11. eunsungj	엑셀파일 등록 기능 추가
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamOlnlpInqireController {

	protected Log log = LogFactory.getLog(this.getClass());

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	@Resource(name = "gamOlnlpInqireService")
	protected GamOlnlpInqireService gamOlnlpInqireService;

	@Resource(name = "excelOlnlpService")
	private EgovExcelService excelOlnlpService;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	/**
	 * 공시지가 조회화면호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/code/gamOlnlpList.do")
	String indexConstOlnlpInqire(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
		return "/ygpa/gam/code/GamOlnlpList";
	}


	/**
	 * 공시지가 등록 현황 목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/gamOlnlpInqireList.do")
	@ResponseBody Map<String, Object> selectOlnlpInsertList(GisAssetsCodeVO searchVO)throws Exception {

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
		List OlnlpMngtList = gamOlnlpInqireService.selectOlnlpInsertList(searchVO);
		int totCnt = gamOlnlpInqireService.selectOlnlpInsertListTotCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
		map.put("totalCount", totCnt);
		map.put("resultList", OlnlpMngtList);
		map.put("searchOption", searchVO);

		return map;
	}


	/**
	 * 공시지가 목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/gamOlnlpInqireDetailList.do")
	@ResponseBody Map<String, Object> selectOlnlpInqireList(GamOlnlpFVO searchVO)throws Exception {

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
		List olnlpMngtList = gamOlnlpInqireService.selectOlnlpInqireList(searchVO);
        int totCnt = gamOlnlpInqireService.selectOlnlpInqireListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", olnlpMngtList);
    	map.put("searchOption", searchVO);

    	return map;
    }

}