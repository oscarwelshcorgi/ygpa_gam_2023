package egovframework.rte.ygpa.gam.oper.shed.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrUseExprInqireService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrUseExprInqireVO;

/**
 * @Class Name : GamCmmnCntrUseExprInqireController.java
 * @Description : 공컨장치장임대만기도래자료조회
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamCmmnCntrUseExprInqireController {

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

    @Resource(name = "gamCmmnCntrUseExprInqireService")
    private GamCmmnCntrUseExprInqireService gamCmmnCntrUseExprInqireService;


    /**
     * 공컨장치장임대관리 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/shed/GamCmmnCntrUseExprInqire"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/shed/gamCmmnCntrUseExprInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//공시지가정보
		GamCmmnCntrUseExprInqireVO gvo = new GamCmmnCntrUseExprInqireVO();
//		List olnlpList = gamCmmnCntrUseExprInqireService.selectOlnlpInfo();

//		model.addAttribute("olnlpList", olnlpList);
		model.addAttribute("loginOrgnztId", loginVO.getOrgnztId());
		model.addAttribute("loginUserId", loginVO.getId());
		model.addAttribute("grUsagePdFromStr", EgovDateUtil.formatDate(EgovDateUtil.getToday(), "-"));
		model.addAttribute("grUsagePdToStr",   EgovDateUtil.formatDate(EgovDateUtil.addYearMonthDay(EgovDateUtil.getToday(),0,1,0), "-"));
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/oper/shed/GamCmmnCntrUseExprInqire";
    }

	/**
     * 공컨장치장임대목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/shed/gamSelectCmmnCntrUseExprInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map selectCmmnCntrUseExprInqireList(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
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

		//공컨장치장임대목록
    	totalCnt = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireListTotCnt(searchVO);
    	List assetRentList = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	//총면적, 총사용료
    	GamCmmnCntrUseExprInqireVO resultSum = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireSum(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	map.put("sumGrAr", resultSum.getSumGrAr());
    	map.put("sumGrFee", resultSum.getSumGrFee());
    	map.put("sumGrRdcxptFee", resultSum.getSumGrRdcxptFee());

    	return map;
    }

	/**
     * 공컨장치장임대상세리스트를 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/shed/gamSelectCmmnCntrUseExprInqireDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectCmmnCntrUseExprInqireDetailList(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
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

		// 공컨장치장임대상세리스트 및 총건수
		totalCnt = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireDetailListTotCnt(searchVO);
		List resultList = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireDetailList(searchVO);

		paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }

    /**
     * 파일목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/shed/gamSelectCmmnCntrUseExprInqireFileList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectCmmnCntrUseExprInqireFileList(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
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

		//공컨장치장임대목록
    	totalCnt = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireFileListTotCnt(searchVO);
    	List assetFileList = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireFileList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("assetFileList", assetFileList);
    	map.put("searchOption", searchVO);

    	return map;
    }

}
