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
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFcltsMngNoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFcltsMngNoVO;

/**
 *
 * @author 김종민
 * @since 2014. 12. 11.
 * @version 1.0
 * @see 시설물 관리 번호 조회 팝업
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 11.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupFcltsMngNoController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamPopupFcltsMngNoService")
	private GamPopupFcltsMngNoService gamPopupFcltsMngNoService;

	@RequestMapping(value="/popup/showFcltsMngNo.do")
	String showFcltsMngNo(GamPopupFcltsMngNoVO searchOpt, ModelMap model) throws Exception {

		model.addAttribute("searchOpt", searchOpt);

		return "/ygpa/gam/popup/GamPopupFcltsMngNo";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/popup/selectFcltsMngNoInfoList.do", method=RequestMethod.POST)
	@ResponseBody Map selectFcltsMngNoInfoList(GamPopupFcltsMngNoVO searchVO) throws Exception {

		int totalCount;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
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

		totalCount = gamPopupFcltsMngNoService.selectFcltsMngNoListTotCnt(searchVO);
		List resultList = gamPopupFcltsMngNoService.selectFcltsMngNoList(searchVO);

		paginationInfo.setTotalRecordCount(totalCount);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);
		map.put("totalCount", totalCount);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

}
