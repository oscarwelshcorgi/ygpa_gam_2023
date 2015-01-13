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
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterFeeExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterFeeExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterFeeExprInqireService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterFeeExprInqireVO;

/**
 * @Class Name : GamMarineCenterFeeExprInqireController.java
 * @Description : 마린센터임대고지도래자료조회
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
public class GamMarineCenterFeeExprInqireController {

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

    @Resource(name = "gamMarineCenterFeeExprInqireService")
    private GamMarineCenterFeeExprInqireService gamMarineCenterFeeExprInqireService;


    /**
     * 마린센터임대고지도래 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/center/GamMarineCenterFeeExprInqire"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/center/gamMarineCenterFeeExprInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//공시지가정보
		GamMarineCenterFeeExprInqireVO gvo = new GamMarineCenterFeeExprInqireVO();
		List olnlpList = gamMarineCenterFeeExprInqireService.selectOlnlpInfo();

		model.addAttribute("olnlpList", olnlpList);
		model.addAttribute("loginOrgnztId", loginVO.getOrgnztId());
		model.addAttribute("loginUserId", loginVO.getId());
		model.addAttribute("grUsagePdFromStr", EgovDateUtil.formatDate(EgovDateUtil.getToday(), "-"));
		model.addAttribute("grUsagePdToStr",   EgovDateUtil.formatDate(EgovDateUtil.addYearMonthDay(EgovDateUtil.getToday(),0,1,0), "-"));
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/oper/center/GamMarineCenterFeeExprInqire";
    }

	/**
     * 마린센터임대고지도래목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/center/gamSelectMarineCenterFeeExprInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map selectMarineCenterFeeExprInqireList(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {

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
    	totalCnt = gamMarineCenterFeeExprInqireService.selectMarineCenterFeeExprInqireListTotCnt(searchVO);
    	List assetRentList = gamMarineCenterFeeExprInqireService.selectMarineCenterFeeExprInqireList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	//총면적, 총사용료
    	GamMarineCenterFeeExprInqireVO resultSum = gamMarineCenterFeeExprInqireService.selectMarineCenterFeeExprInqireSum(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	//map.put("sumGrAr", resultSum.getSumGrAr());
    	//map.put("sumGrFee", resultSum.getSumGrFee());
    	map.put("totalNticAmt", resultSum.getSumNticAmt());

    	return map;
    }

	/**
     * 마린센터임대고지도래상세리스트를 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/center/gamSelectMarineCenterFeeExprInqireDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectMarineCenterFeeExprInqireDetailList(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {

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
		totalCnt = gamMarineCenterFeeExprInqireService.selectMarineCenterFeeExprInqireDetailListTotCnt(searchVO);
		List resultList = gamMarineCenterFeeExprInqireService.selectMarineCenterFeeExprInqireDetailList(searchVO);

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
    @RequestMapping(value="/oper/center/gamSelectMarineCenterFeeExprInqireFileList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectMarineCenterFeeExprInqireFileList(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {

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
    	totalCnt = gamMarineCenterFeeExprInqireService.selectMarineCenterFeeExprInqireFileListTotCnt(searchVO);
    	List assetFileList = gamMarineCenterFeeExprInqireService.selectMarineCenterFeeExprInqireFileList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("assetFileList", assetFileList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	/**
     * 코멘트를 저장한다.
     * @param String
     * @param gamMarineCenterFeeExprInqireVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/center/gamUpdateMarineCenterFeeExprInqireComment.do")
    public @ResponseBody Map updateMarineCenterFeeExprInqireComment(
    	   @ModelAttribute("gamMarineCenterFeeExprInqireVO") GamMarineCenterFeeExprInqireVO gamMarineCenterFeeExprInqireVO,
    	   BindingResult bindingResult)
           throws Exception {

    	Map map = new HashMap();
        String resultMsg  = "";
        String updateFlag = "";
        int resultCode = 1;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO loginVo = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        /*
        int resultLevReqestCnt = -1;

        if( EgovStringUtil.isEmpty(gamMarineCenterFeeExprInqireVO.getPrmisnYn()) || gamMarineCenterFeeExprInqireVO.getPrmisnYn().equals("N") ) { //허가여부가 'N'이면 삭제가능
        	deleteFlag = "Y";
        } else {
        	resultLevReqestCnt = gamMarineCenterFeeExprInqireService.selectMarineCenterFeeExprInqireLevReqestCnt(gamMarineCenterFeeExprInqireVO); //징수의뢰 정보 카운트

        	if( gamMarineCenterFeeExprInqireVO.getPrmisnYn().equals("Y") && resultLevReqestCnt == 0 ) { //허가여부가 Y이고 징수의뢰테이블에 정보가 없으면 삭제가능
            	deleteFlag = "Y";
            }
        }
    	*/
        if( gamMarineCenterFeeExprInqireVO.getMngYear() == null || "".equals(gamMarineCenterFeeExprInqireVO.getMngYear()) ) {
        	updateFlag = "N";
        } else {
        	updateFlag = "Y";
        }

    	if("Y".equals(updateFlag)) {
    		gamMarineCenterFeeExprInqireVO.setUpdUsr(loginVo.getId());
	        gamMarineCenterFeeExprInqireService.updateMarineCenterFeeExprInqireComment(gamMarineCenterFeeExprInqireVO);

	        resultCode = 0; // return ok
	        resultMsg  = egovMessageSource.getMessage("success.common.insert");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = "신청 저장후 코멘트 저장이 가능합니다.";
    	}

    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);

		return map;
    }

}
