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
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentSttusInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentSttusInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentSttusInqireService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentSttusInqireVO;

/**
 * @Class Name : GamCmmnCntrRentSttusInqireController.java
 * @Description : 공컨장치장임대현황조회
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamCmmnCntrRentSttusInqireController {

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

    @Resource(name = "gamCmmnCntrRentSttusInqireService")
    private GamCmmnCntrRentSttusInqireService gamCmmnCntrRentSttusInqireService;

    /**
     * 공컨장치장임대관리 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/shed/GamCmmnCntrRentSttusInqire"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/shed/gamCmmnCntrRentSttusInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//공시지가정보
//		List olnlpList = gamCmmnCntrRentSttusInqireService.selectOlnlpInfo();

		//코픽스 이자율
		List cofixList = gamCmmnCntrRentSttusInqireService.selectCofixInfo();

		//현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져와서 해당하는 코픽스 이자율 가져오기.
		GamCmmnCntrRentSttusInqireVO cofixVO = new GamCmmnCntrRentSttusInqireVO();
		GamCmmnCntrRentSttusInqireVO cofixResultVO = new GamCmmnCntrRentSttusInqireVO();

		cofixVO.setcYear(EgovDateUtil.getToday().substring(0,6));
		cofixVO = gamCmmnCntrRentSttusInqireService.selectCmmnCntrRentSttusInqireBeforeQuarterInfo(cofixVO);

		if( cofixVO != null ) {
			cofixResultVO = gamCmmnCntrRentSttusInqireService.selectCmmnCntrRentSttusInqireCofixInfo(cofixVO);

			if( cofixResultVO != null && cofixResultVO.getBlceStdrIntrrate() != null ) {
				model.addAttribute("blceStdrIntrrate", cofixResultVO.getBlceStdrIntrrate());
			}

			if( cofixResultVO != null && cofixResultVO.getBlceStdrIntrrateShow() != null ) {
				model.addAttribute("blceStdrIntrrateShow", cofixResultVO.getBlceStdrIntrrateShow());
			}
		}

//		model.addAttribute("olnlpList", olnlpList);
		model.addAttribute("cofixList", cofixList);
		model.addAttribute("loginOrgnztId", loginVO.getOrgnztId());
		model.addAttribute("loginUserId", loginVO.getId());
		model.addAttribute("currentDateStr", EgovDateUtil.formatDate(EgovDateUtil.getToday(), "-"));
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/oper/shed/GamCmmnCntrRentSttusInqire";
    }

	/**
     * 공컨장치장임대목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/shed/gamSelectCmmnCntrRentSttusInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map selectCmmnCntrRentSttusInqireList(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {

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
    	totalCnt = gamCmmnCntrRentSttusInqireService.selectCmmnCntrRentSttusInqireListTotCnt(searchVO);
    	List assetRentList = gamCmmnCntrRentSttusInqireService.selectCmmnCntrRentSttusInqireList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	//총면적, 총사용료
    	GamCmmnCntrRentSttusInqireVO resultSum = gamCmmnCntrRentSttusInqireService.selectCmmnCntrRentSttusInqireSum(searchVO);

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
    @RequestMapping(value="/oper/shed/gamSelectCmmnCntrRentSttusInqireDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectCmmnCntrRentSttusInqireDetailList(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {

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
		totalCnt = gamCmmnCntrRentSttusInqireService.selectCmmnCntrRentSttusInqireDetailListTotCnt(searchVO);
		List resultList = gamCmmnCntrRentSttusInqireService.selectCmmnCntrRentSttusInqireDetailList(searchVO);

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
    @RequestMapping(value="/oper/shed/gamSelectCmmnCntrRentSttusInqireFileList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectCmmnCntrRentSttusInqireFileList(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {

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
    	totalCnt = gamCmmnCntrRentSttusInqireService.selectCmmnCntrRentSttusInqireFileListTotCnt(searchVO);
    	List assetFileList = gamCmmnCntrRentSttusInqireService.selectCmmnCntrRentSttusInqireFileList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetFileList);

    	return map;
    }

}