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
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocEntrpsInfoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocEntrpsInfoVO;

/**
 * @Class Name : GamPopupSocEntrpsInfoController.java
 * @Description : 업체정보
 * @Modification Information
 * 
 * @author HNJ
 * @since 2014. 9. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 24.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamPopupSocEntrpsInfoController {
	
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
    
    /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;
	
	@Resource(name = "gamPopupSocEntrpsInfoService")
    private GamPopupSocEntrpsInfoService gamPopupSocEntrpsInfoService;
	
	/**
     * 업체정보 팝업화면을 로딩한다.
     *
     * @param searchOpt
     * @param model the model
     * @return "/ygpa/gam/popup/GamPopupSocEntrpsInfo"
     * @throws Exception the exception
     */
	@RequestMapping(value="/popup/showSocEntrpsInfo.do")
    String showEntrpsInfo(GamPopupSocEntrpsInfoVO searchOpt, ModelMap model) throws Exception {

		model.addAttribute("searchOpt", searchOpt);
    	return "/ygpa/gam/popup/GamPopupSocEntrpsInfo";
    }

	/**
     * 업체정보목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/popup/selectSocEntrpsInfoList.do", method=RequestMethod.POST)
	@ResponseBody Map selectSocEntrpsInfoList(GamPopupSocEntrpsInfoVO searchVO) throws Exception {

		int totalCnt;
    	Map map = new HashMap();

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List resultList = gamPopupSocEntrpsInfoService.selectSocEntrpsInfoList(searchVO);
    	totalCnt = gamPopupSocEntrpsInfoService.selectSocEntrpsInfoListTotCnt(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
}
