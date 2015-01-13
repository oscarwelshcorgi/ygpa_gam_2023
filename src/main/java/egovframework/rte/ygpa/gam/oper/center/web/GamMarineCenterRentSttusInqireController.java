package egovframework.rte.ygpa.gam.oper.center.web;

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
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentSttusInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentSttusInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentSttusInqireService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentSttusInqireVO;

/**
 * @Class Name : GamMarineCenterRentSttusInqireController.java
 * @Description : 마린센터임대현황조회
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
public class GamMarineCenterRentSttusInqireController {

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

    @Resource(name = "gamMarineCenterRentSttusInqireService")
    private GamMarineCenterRentSttusInqireService gamMarineCenterRentSttusInqireService;


    /**
     * 마린센터임대관리 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/center/GamMarineCenterRentSttusInqire"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/center/gamMarineCenterRentSttusInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//공시지가정보
//		List olnlpList = gamMarineCenterRentSttusInqireService.selectOlnlpInfo();

		//코픽스 이자율
		List cofixList = gamMarineCenterRentSttusInqireService.selectCofixInfo();

		//현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져와서 해당하는 코픽스 이자율 가져오기.
		GamMarineCenterRentSttusInqireVO cofixVO = new GamMarineCenterRentSttusInqireVO();
		GamMarineCenterRentSttusInqireVO cofixResultVO = new GamMarineCenterRentSttusInqireVO();

		cofixVO.setcYear(EgovDateUtil.getToday().substring(0,6));
		cofixVO = gamMarineCenterRentSttusInqireService.selectMarineCenterRentSttusInqireBeforeQuarterInfo(cofixVO);

		if( cofixVO != null ) {
			cofixResultVO = gamMarineCenterRentSttusInqireService.selectMarineCenterRentSttusInqireCofixInfo(cofixVO);

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

    	return "/ygpa/gam/oper/center/GamMarineCenterRentSttusInqire";
    }

	/**
     * 마린센터임대목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/center/gamSelectMarineCenterRentSttusInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map selectMarineCenterRentSttusInqireList(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {

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

		//마린센터임대목록
    	totalCnt = gamMarineCenterRentSttusInqireService.selectMarineCenterRentSttusInqireListTotCnt(searchVO);
    	List assetRentList = gamMarineCenterRentSttusInqireService.selectMarineCenterRentSttusInqireList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	//총면적, 총사용료
    	GamMarineCenterRentSttusInqireVO resultSum = gamMarineCenterRentSttusInqireService.selectMarineCenterRentSttusInqireSum(searchVO);

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
     * 마린센터임대상세리스트를 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/center/gamSelectMarineCenterRentSttusInqireDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectMarineCenterRentSttusInqireDetailList(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {

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

		// 마린센터임대상세리스트 및 총건수
		totalCnt = gamMarineCenterRentSttusInqireService.selectMarineCenterRentSttusInqireDetailListTotCnt(searchVO);
		List resultList = gamMarineCenterRentSttusInqireService.selectMarineCenterRentSttusInqireDetailList(searchVO);

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
    @RequestMapping(value="/oper/center/gamSelectMarineCenterRentSttusInqireFileList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectMarineCenterRentSttusInqireFileList(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {

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

		//마린센터임대목록
    	totalCnt = gamMarineCenterRentSttusInqireService.selectMarineCenterRentSttusInqireFileListTotCnt(searchVO);
    	List assetFileList = gamMarineCenterRentSttusInqireService.selectMarineCenterRentSttusInqireFileList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetFileList);

    	return map;
    }

}
