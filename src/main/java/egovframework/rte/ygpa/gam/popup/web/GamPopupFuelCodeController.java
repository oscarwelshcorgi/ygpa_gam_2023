/**
 *
 */
package egovframework.rte.ygpa.gam.popup.web;

import java.util.ArrayList;
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
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFuelCodeService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFuelCodeVO;

/**
 *
 * @author ACEWOLF
 * @since 2014. 11. 28.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 28.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupFuelCodeController {

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


	@Resource(name = "gamPopupFuelCodeService")
	private GamPopupFuelCodeService gamPopupFuelCodeService;


	/**
	 * 연료 코드 팝업 화면을 로딩한다.
	 *
	 * @param searchOpt
	 * @param model the model
	 * @return "/ygpa/gam/popup/GamPopupFuelCode"
	 * @throws Exception the exception
	 */
	@RequestMapping(value="/popup/showFuelCode.do")
	String selectFuelCodePopup(GamPopupFuelCodeVO searchOpt, ModelMap model) throws Exception {

		int year = Integer.parseInt(EgovDateUtil.getToday().substring(0,4));
		List yearList = new ArrayList();
		Map yearMap = null;

		for (int i = year ; i >= year-10 ; i--) {
			yearMap = new HashMap();
			yearMap.put("code", i);
			yearMap.put("codeNm", i+"년");
			yearList.add(yearMap);
		}

		model.addAttribute("yearsList", yearList);
		model.addAttribute("thisyear", year);
		model.addAttribute("searchOpt", searchOpt);

		return "/ygpa/gam/popup/GamPopupFuelCode";

    }

	/**
	 * 연료 코드 목록을 조회한다.
	 *
	 * @param searchVO
	 * @return map
	 * @throws Exception the exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/popup/selectFuelCodeList.do", method=RequestMethod.POST)
	@ResponseBody Map selectFuelCodeList(GamPopupFuelCodeVO searchVO) throws Exception {

		int totalCnt;
		Map map = new HashMap();

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List resultList = gamPopupFuelCodeService.selectFuelCodeList(searchVO);
		totalCnt = gamPopupFuelCodeService.selectFuelCodeListTotCnt(searchVO);

		paginationInfo.setTotalRecordCount(totalCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);	// return ok
		map.put("totalCount", totalCnt);
		map.put("resultList", resultList);
		map.put("searchOption", searchVO);

		return map;
	}

}
