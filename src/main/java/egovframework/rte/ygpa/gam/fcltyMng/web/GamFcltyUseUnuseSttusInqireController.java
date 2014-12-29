/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.web;

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
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltySpecInqireVO;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUseUnuseSttusInqireService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUseUnuseSttusInqireVO;

/**
 *
 * @author 정성현
 * @since 2014. 12. 11.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 11.	정성현	최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 *
 */
@Controller
public class GamFcltyUseUnuseSttusInqireController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;


	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="gamFcltyUseUnuseSttusInqireService")
    protected GamFcltyUseUnuseSttusInqireService gamFcltyUseUnuseSttusInqireService;


@RequestMapping(value="/fcltyMng/gamFcltyUseUnuseSttusInqire.do")
String indexFcltyUsageSttusInqire(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
	model.addAttribute("windowId", windowId);
	return "/ygpa/gam/fcltyMng/GamFcltyUseUnuseSttusInqire";

	}
/**
 * 시설물 사용/미사용 목록 조회
 * @param fcltyManageVO
 * @return map
 * @throws Exception
 */
@RequestMapping(value="/fcltyMng/selectFcltyUseUnuseSttusInqireList.do")
@ResponseBody Map<String, Object> selectFcltyUseUnuseSttusInqireList(GamFcltyUseUnuseSttusInqireVO searchVO) throws Exception {
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

	List resultList = gamFcltyUseUnuseSttusInqireService.selectFcltyUseUnuseSttusInqireList(searchVO);
	int totCnt = gamFcltyUseUnuseSttusInqireService.selectFcltyUseUnuseSttusInqireListTotCnt(searchVO);
	paginationInfo.setTotalRecordCount(totCnt);
    searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

	map.put("resultCode", 0);			// return ok
	map.put("totalCount", totCnt);
	map.put("resultList", resultList);
	map.put("searchOption", searchVO);

	return map;
	}



@RequestMapping(value="/fcltyMng/selectFcltyUseUnuseSttusInqireDetailList.do")
@ResponseBody Map<String, Object> selectFcltyUseUnuseSttusInqireDetail(GamFcltyUseUnuseSttusInqireVO searchVO) throws Exception {
	
	Map<String, Object> map = new HashMap<String, Object>();
	List result;
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	if(!isAuthenticated) {
        map.put("resultCode", 1);
		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	return map;
	}

	try {
		result = gamFcltyUseUnuseSttusInqireService.selectFcltyUseUnuseSttusInqireDetail(searchVO);
		
    }

	catch(Exception e) {
        map.put("resultCode", 1);
        map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
        return map;
	}

    map.put("resultCode", 0);
    map.put("result", result);

    return map;
}
}


