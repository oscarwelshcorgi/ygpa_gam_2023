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

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeDefaultVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderMngGroupVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderSttusInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderSttusInqireVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 17.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFenderSttusInqireController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamFenderSttusInqireService")
	private GamFenderSttusInqireService gamFenderSttusInqireService;

	@RequestMapping(value="/fclty/gamFenderSttusInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//List fcltsClCdList = gamCvlEngFcltySpecMngService.selectFcltsClCdList();

		//model.addAttribute("fcltsClCdList", fcltsClCdList);
		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/fclty/GamFenderSttusInqire";

	}


	/**
	 * 방충재 시설 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamFenderMngGroupList.do")
	public @ResponseBody Map gamFenderMngGroupList(GamFenderMngGroupVO searchVO)throws Exception {

		Map map = new HashMap();

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
		List gamFenderMngGroupList = gamFenderSttusInqireService.selectFenderMngGroupList(searchVO);

        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", gamFenderMngGroupList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	@RequestMapping(value="/fclty/gamFenderSttusInqireList.do")
	@ResponseBody Map<String, Object> gamFenderSttusInqireList(GamFenderSttusInqireVO searchVO) throws Exception {

		Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회

		/** List Data */
		List mntnRprObjFcltsFList = gamFenderSttusInqireService.selectFenderSttusInqireList(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", mntnRprObjFcltsFList);

    	return map;

	}



    @RequestMapping(value="/fclty/fenderSttusInqirePrint.do")
    public String gamFenderSttusInqirePrint(@RequestParam Map<String, Object> searchOpt, ModelMap model) throws Exception {
    	Map map = new HashMap();

    	GamFenderMngGroupVO fenderMng;
    	GamFenderSttusInqireVO searchVO = new GamFenderSttusInqireVO();
		ObjectMapper mapper = new ObjectMapper();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "ygpa/gam/code/GamAssetCodeListPrint";
    	}

    	fenderMng = mapper.convertValue(searchOpt, GamFenderMngGroupVO.class);
    	searchVO.setsFcltsMngGroupNo(fenderMng.getFcltsMngGroupNo());

		List gamFenderMngGroupList = gamFenderSttusInqireService.selectFenderSttusInqireList(searchVO);

    	model.addAttribute("resultCode", 0);	// return ok
    	model.addAttribute("fenderMng", fenderMng);
    	model.addAttribute("gamFenderMngGroupList", gamFenderMngGroupList);

    	return "ygpa/gam/fclty/GamFenderSttusInqirePrint";
    }

}

