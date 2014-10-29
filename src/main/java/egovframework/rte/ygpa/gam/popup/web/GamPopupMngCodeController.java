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
import egovframework.rte.ygpa.gam.popup.service.GamPopupEntrpsInfoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupMngCodeVO;

/**
 * @Class Name : GamPopupEntrpsInfoController.java
 * @Description : 업체정보
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamPopupMngCodeController {

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


    @Resource(name = "gamPopupEntrpsInfoService")
    private GamPopupEntrpsInfoService gamPopupEntrpsInfoService;


	/**
     * 관리비 시설코드 팝업화면을 로딩한다.
     *
     * @param searchOpt
     * @param model the model
     * @return "/ygpa/gam/popup/GamPopupEntrpsInfo"
     * @throws Exception the exception
     */
	@RequestMapping(value="/popup/showMngCode.do")
    String selectMngCodePopup(GamPopupMngCodeVO searchOpt, ModelMap model) throws Exception {

		model.addAttribute("searchOpt", searchOpt);
    	return "/ygpa/gam/popup/GamPopupMngCode";
    }

	/**
     * 관리비 시설코드 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/popup/selectMngCodeList.do", method=RequestMethod.POST)
	@ResponseBody Map selectMngCodeList(GamPopupMngCodeVO searchVO) throws Exception {

		int totalCnt;
    	Map map = new HashMap();

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
/*
 *
		List resultList = gamPopupEntrpsInfoService.selectEntrpsInfoList(searchVO);
    	totalCnt = gamPopupEntrpsInfoService.selectEntrpsInfoListTotCnt(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
 */
    	map.put("searchOption", searchVO);

    	return map;
    }
}