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
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.popup.service.GamPopupQcItemCdService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupQcItemCdVo;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 23.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 23.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupQcItemCdController {

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


	@Resource(name = "gamPopupQcItemCdService")
	private GamPopupQcItemCdService gamPopupQcItemCdService;

	@RequestMapping(value="/popup/showQcItemCdPopup.do")
	String showQcItemCdPopup(GamPopupQcItemCdVo searchOpt, ModelMap model) throws Exception {

		model.addAttribute("searchOpt", searchOpt);
		return "/ygpa/gam/popup/GamPopupQcItemCd";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/popup/gamSelectQcItemCdList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectQcItemCdList(GamPopupQcItemCdVo searchVO) throws Exception {

		int totalCnt;
		Map map = new HashMap();

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		totalCnt = gamPopupQcItemCdService.selectQcItemCdListTotCnt(searchVO);
		List resultList = gamPopupQcItemCdService.selectQcItemCdList(searchVO);

		paginationInfo.setTotalRecordCount(totalCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);
		map.put("totalCount", totalCnt);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;

	}

}