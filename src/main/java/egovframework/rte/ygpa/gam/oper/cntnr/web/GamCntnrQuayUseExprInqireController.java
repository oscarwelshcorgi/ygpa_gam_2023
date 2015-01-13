package egovframework.rte.ygpa.gam.oper.cntnr.web;

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
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayUseExprInqireService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayUseExprInqireVO;

/**
 * @Class Name : GamCntnrQuayUseExprInqireController.java
 * @Description : 컨테이너부두임대만기도래자료조회
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
public class GamCntnrQuayUseExprInqireController {

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

    @Resource(name = "gamCntnrQuayUseExprInqireService")
    private GamCntnrQuayUseExprInqireService gamCntnrQuayUseExprInqireService;


    /**
     * 컨테이너부두임대관리 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/cntnr/GamCntnrQuayUseExprInqire"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/cntnr/gamCntnrQuayUseExprInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//공시지가정보
		GamCntnrQuayUseExprInqireVO gvo = new GamCntnrQuayUseExprInqireVO();
//		List olnlpList = gamCntnrQuayUseExprInqireService.selectOlnlpInfo();

//		model.addAttribute("olnlpList", olnlpList);
		model.addAttribute("loginOrgnztId", loginVO.getOrgnztId());
		model.addAttribute("loginUserId", loginVO.getId());
		model.addAttribute("grUsagePdFromStr", EgovDateUtil.formatDate(EgovDateUtil.getToday(), "-"));
		model.addAttribute("grUsagePdToStr",   EgovDateUtil.formatDate(EgovDateUtil.addYearMonthDay(EgovDateUtil.getToday(),0,1,0), "-"));
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/oper/cntnr/GamCntnrQuayUseExprInqire";
    }

	/**
     * 컨테이너부두임대목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/cntnr/gamSelectCntnrQuayUseExprInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map selectCntnrQuayUseExprInqireList(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {

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

		//컨테이너부두임대목록
    	totalCnt = gamCntnrQuayUseExprInqireService.selectCntnrQuayUseExprInqireListTotCnt(searchVO);
    	List assetRentList = gamCntnrQuayUseExprInqireService.selectCntnrQuayUseExprInqireList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	//총면적, 총사용료
    	GamCntnrQuayUseExprInqireVO resultSum = gamCntnrQuayUseExprInqireService.selectCntnrQuayUseExprInqireSum(searchVO);

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
     * 컨테이너부두임대상세리스트를 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/cntnr/gamSelectCntnrQuayUseExprInqireDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectCntnrQuayUseExprInqireDetailList(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {

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

		// 컨테이너부두임대상세리스트 및 총건수
		totalCnt = gamCntnrQuayUseExprInqireService.selectCntnrQuayUseExprInqireDetailListTotCnt(searchVO);
		List resultList = gamCntnrQuayUseExprInqireService.selectCntnrQuayUseExprInqireDetailList(searchVO);

		paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

        map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
}
