package egovframework.rte.ygpa.gam.asset.rent.web;

import java.io.IOException;
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
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.logging.Logger;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupGisAssetsCdVO;

/**
 * @Class Name : GamAssetRentFeePayDtlsMngtController.java
 * @Description : 자산임대료납부관리
 * @Modification Information
 *
 * @author heroin
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamAssetRentFeePayDtlsMngtController {

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

    @Resource(name="gamNticRequestMngtService")
    private GamNticRequestMngtService gamNticRequestMngtService;

    @Resource(name = "gamAssetRentFeePayDtlsMngtService")
    private GamAssetRentFeePayDtlsMngtService gamAssetRentFeePayDtlsMngtService;


    /**
     * 항만시설납부현황관리 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/rent/GamAssetRentFeePayDtlsMngt"
     * @throws Exception the exception
     */
	@RequestMapping(value="/asset/rent/gamAssetRentFeePayDtlsMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

/*		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();

		codeVo.setCodeId("GAM019"); //항코드
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);

		codeVo.setCodeId("GAM011"); //신청구분코드
		List reqstCdList = cmmUseService.selectCmmCodeDetail(codeVo);

		codeVo.setCodeId("GAM008"); //고지방법 코드
		List nticMthCdList = cmmUseService.selectCmmCodeDetail(codeVo);

		codeVo.setCodeId("GAM007"); //사용 용도 코드
		List usagePrposCdList = cmmUseService.selectCmmCodeDetail(codeVo);

		codeVo.setCodeId("GAM024"); //요금종류
		List chrgeKndCdList = cmmUseService.selectCmmCodeDetail(codeVo);

		codeVo.setCodeId("GAM005"); //시설구분
		List fcltySeCdList = cmmUseService.selectCmmCodeDetail(codeVo);

		codeVo.setCodeId("GAM025"); //수납구분
		List rcivSeCdList = cmmUseService.selectCmmCodeDetail(codeVo);

		codeVo.setCodeId("GAM003"); //부두코드
		List quayCdList = cmmUseService.selectCmmCodeDetail(codeVo);

		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("reqstCdList", reqstCdList);
		model.addAttribute("nticMthCdList", nticMthCdList);
		model.addAttribute("usagePrposCdList", usagePrposCdList);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);
		model.addAttribute("fcltySeCdList", fcltySeCdList);
		model.addAttribute("rcivSeCdList", rcivSeCdList);
		model.addAttribute("quayCdList", quayCdList);*/
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/asset/rent/GamAssetRentFeePayDtlsMngt";
    }

	/**
     * 항만시설납부현황관리 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/rent/gamSelectAssetRentFeePayDtlsMngtList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetRentFeePayDtlsMngtList(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {

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

		//목록
    	totalCnt = gamAssetRentFeePayDtlsMngtService.selectAssetRentFeePayDtlsMngtListTotCnt(searchVO);
    	List resultList = gamAssetRentFeePayDtlsMngtService.selectAssetRentFeePayDtlsMngtList(searchVO);


    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	//자료수, 사용료, 부가세, 고지액
    	GamAssetRentFeePayDtlsMngtVO resultSum = gamAssetRentFeePayDtlsMngtService.selectAssetRentFeePayDtlsMngtSum(searchVO);
    	//GamAssetRentFeePayDtlsMngtVO resultSum = new GamAssetRentFeePayDtlsMngtVO();

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	map.put("sumCnt", resultSum.getSumCnt());
    	map.put("sumFee", resultSum.getSumFee());
    	map.put("sumNhtIsueAmt", resultSum.getSumNhtIsueAmt());
    	map.put("sumVat", resultSum.getSumVat());
    	map.put("sumNticAmt", resultSum.getSumNticAmt());
    	map.put("sumFeeA3", resultSum.getSumFeeA3());
    	map.put("sumFeeA4", resultSum.getSumFeeA4());
    	map.put("sumFeeD1", resultSum.getSumFeeD1());
    	map.put("sumFeeD2", resultSum.getSumFeeD2());
    	map.put("sumPayAmt", resultSum.getSumPayAmt());

    	return map;
    }

	/**
     * 항만시설납부현황관리 상세정보를 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/rent/selectAssetRentFeePayDtlsMngtDetail.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetRentFeePayDtlsMngtDetail(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {

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

    	List resultList = gamAssetRentFeePayDtlsMngtService.selectAssetRentFeePayDtlsMngtDetailList(searchVO);
    	Map master = gamAssetRentFeePayDtlsMngtService.selectAssetRentFeePayDtlsMngtDetailMstPk(searchVO);
    	Map summary = gamAssetRentFeePayDtlsMngtService.selectAssetRentFeePayDtlsMngtDetailSumPk(searchVO);
    	Map arrrgDetail = gamAssetRentFeePayDtlsMngtService.selectNticArrrgDetail(searchVO);;

        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());


    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", resultList);
    	map.put("resultMaster", master);
    	map.put("resultSummary", summary);
    	map.put("resultArrrg", arrrgDetail);
    	map.put("searchOption", searchVO);

    	return map;
    }

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/asset/rent/updateAssetRentFeePayDtlsMngtList.do")
    public @ResponseBody Map updateAssetRentFeePayDtlsMngtList()
            throws Exception {

     	Map map = new HashMap();
     	Map paramMap = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        int anlrveLevCnt = 0;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

//    		paramMap.put("updUsr", loginVo.getId());

    		int result=gamNticRequestMngtService.updateRentFeePaySttusRefresh();

	        resultCode = 0;
	 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

	     	map.put("resultCode", resultCode);
	        map.put("resultMsg", resultMsg);
	        map.put("updateCount", result);
    	} catch(IOException e) {
    		((Log) Logger.getLogger(EgovProperties.class)).debug("IGNORED: " + e.getMessage());
    	}
    	catch(Exception e) {
	        map.put("resultCode", -1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
        	return map;
    	}

 		return map;
     }

	/**
     *  연체 세입 조회
     * @param searchOpt
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/showNticArrrgPopup.do")
    String showAssetsCd(GamPopupGisAssetsCdVO searchOpt, ModelMap model) throws Exception {
    	model.addAttribute("searchOpt", searchOpt);

    	return "/ygpa/gam/asset/rent/GamNticArrrgPopup";
    }

    /**
     * 연체 세입 목록 가져오기
     * @param searchVO
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/selectNticArrrgList.do", method=RequestMethod.POST)
    @ResponseBody Map<String, Object> selectNticArrrgList(GamAssetRentArrrgMngtVO searchVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

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

		List<?> gamNticArrrgList = gamAssetRentFeePayDtlsMngtService.selectNticArrrgList(searchVO);
		int totalCnt = gamAssetRentFeePayDtlsMngtService.selectNticArrrgListTotCnt(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", gamNticArrrgList);
    	map.put("searchOption", searchVO);

    	return map;
    }

    /**
     *  연체 일괄 고지
     * @param dataList
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/insertNticArrrgList.do", method=RequestMethod.POST)
    @ResponseBody Map<String, Object> insertNticArrrgList(GamAssetRentArrrgMngtVO searchVO) throws Exception {


		Map map = new HashMap<String,Object>();

    	int resultCode = -1;
    	String resultMsg = "";

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		searchVO.setUpdUsr(loginVO.getId());

		Map<String,Object> mergeMap = new HashMap<String,Object>();

		List<?> gamNticArrrgList = gamAssetRentFeePayDtlsMngtService.selectNticArrrgList(searchVO);

		for(int i=0; i<gamNticArrrgList.size(); i++) {
			Map<String, Object> nticArg = (Map<String, Object>)gamNticArrrgList.get(i);
			nticArg.put("arrrgRate", searchVO.getArrrgRate());
		}

		gamNticRequestMngtService.sendMultiUnpaidRequest(gamNticArrrgList);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.unpaidlist"));

		return map;
    }



    /**
     * 연체 고지
     * @param nticVo
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/insertNticArrrg.do", method=RequestMethod.POST)
    @ResponseBody Map<String, Object> insertNticArrrg(@RequestParam Map nticVo) throws Exception {


		Map map = new HashMap<String,Object>();

    	int resultCode = -1;
    	String resultMsg = "";

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		nticVo.put("updUsr",loginVO.getId());
		nticVo.put("deptCd",loginVO.getDeptCd());
		nticVo.put("emplNo", loginVO.getEmplNo());
		nticVo.put("userName", loginVO.getName());

		gamNticRequestMngtService.sendUnpaidRequest(nticVo);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.unpaid"));

		return map;
    }

    @RequestMapping(value="/asset/rent/updateNticArrrg.do", method=RequestMethod.POST)
    @ResponseBody Map<String, Object> updateNticArrrg(@RequestParam Map nticVo) throws Exception {


		Map map = new HashMap<String,Object>();

    	int resultCode = -1;
    	String resultMsg = "";

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		nticVo.put("updUsr",loginVO.getId());
		nticVo.put("deptCd",loginVO.getDeptCd());
		nticVo.put("emplNo", loginVO.getEmplNo());
		nticVo.put("userName", loginVO.getName());

		gamNticRequestMngtService.sendUnpaidRequest(nticVo);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.unpaid"));

		return map;
    }

    /**
     * 연체 고지 취소
     * @param nticVo
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/cancelNticArrrg.do", method=RequestMethod.POST)
    @ResponseBody Map<String, Object> cancelNticArrrg(@RequestParam Map nticVo) throws Exception {


		Map map = new HashMap<String,Object>();

    	int resultCode = -1;
    	String resultMsg = "";

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		nticVo.put("updUsr",loginVO.getId());
		nticVo.put("deptCd",loginVO.getDeptCd());
		nticVo.put("emplNo", loginVO.getEmplNo());
		nticVo.put("userName", loginVO.getName());

		gamNticRequestMngtService.cancelUnpaidRequest(nticVo);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.unpaid"));

		return map;
    }

    /**
     * 단일 건에 대해서 고지를 취소 한다.
     * @param nticVo
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/cancelNticArrrgPk.do", method=RequestMethod.POST)
    @ResponseBody Map<String, Object> cancelNticArrrgPk(@RequestParam Map nticVo) throws Exception {


		Map map = new HashMap<String,Object>();

    	int resultCode = -1;
    	String resultMsg = "";

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		nticVo.put("updUsr",loginVO.getId());
		nticVo.put("deptCd",loginVO.getDeptCd());
		nticVo.put("emplNo", loginVO.getEmplNo());

		gamNticRequestMngtService.cancelUnpaidRequestPk(nticVo);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.unpaid"));

		return map;
    }

    /**
     * 항만시설연체현황관리 목록을 조회한다.
     *change**
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/rent/selectAssetRentFeePayDtlsMngtDlyList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetRentFeePayDtlsMngtDlyList(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {

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

    	List resultList = gamAssetRentFeePayDtlsMngtService.selectAssetRentFeePayDtlsMngtDlyList(searchVO);

    	int totCnt = gamAssetRentFeePayDtlsMngtService.selectAssetRentFeePayDtlsMngtDlyListTotCnt(searchVO);

    	Map dlyInfo = gamAssetRentFeePayDtlsMngtService.selectAssetRentFeePayDtlsMngtDlyInfo(searchVO);
    	Map summary = gamAssetRentFeePayDtlsMngtService.selectAssetRentFeePayDtlsMngtDlyListSum(searchVO);

        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());


    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", resultList);
    	map.put("resultDlyInfo", dlyInfo);
    	map.put("totCnt", totCnt);
    	map.put("resultSummary", summary);
    	map.put("searchOption", searchVO);

    	return map;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/rent/selectAssetRentFeePayDtlsMngtListExcel.do", method=RequestMethod.POST)
    @ResponseBody ModelAndView selectErpAssetCodeListExcel(@RequestParam Map<String, Object> excelParam) throws Exception {
		Map map = new HashMap();
		List header;
		ObjectMapper mapper = new ObjectMapper();

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    		return new ModelAndView("gridExcelView", "gridResultMap", map);
    	}


    	// 환경설정
    	/** EgovPropertyService */
    	GamAssetRentFeePayDtlsMngtVO searchVO= new GamAssetRentFeePayDtlsMngtVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.

		// 조회 조건
		searchVO = mapper.convertValue(excelParam, GamAssetRentFeePayDtlsMngtVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		/** List Data */
//    	int totCnt = erpAssetCdService.selectErpAssetCdListTotCnt(searchVO);

    	List gamAssetList = gamAssetRentFeePayDtlsMngtService.selectAssetRentFeePayDtlsMngtList(searchVO);

    	map.put("resultList", gamAssetList);
    	map.put("header", header);

    	return new ModelAndView("gridExcelView", "gridResultMap", map);
    }

	/**
     * 연체 고지서를 출력한다.
     * @param approvalOpt
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/printAssetRentFeePayNotice.do")
    String printAssetRentFeePayNotice(@RequestParam Map<String, Object> approvalOpt, ModelMap model) throws Exception {
    	String report = "ygpa/gam/asset/rent/GamAssetPrintNoticeIssue";
    	model.addAttribute("searchOpt", approvalOpt);

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	}
    	else {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    		Map nticItem = gamAssetRentFeePayDtlsMngtService.selectArrrgNpticPrintInfo(approvalOpt);

    		if("11076".equals(loginVo.getEmplNo()) || "14010".equals(loginVo.getEmplNo())) {
//    			log.debug("new paper selected");
    			report = "ygpa/gam/asset/rent/GamAssetPrintNoticeIssue2";	// 신규 고지서
    		}
//    		model.addAttribute("emplyrNo", loginVo.getEmplNo());

    		model.addAttribute("resultCode", 0);
    		model.addAttribute("result", nticItem);
    		model.addAttribute("resultMsg", "");
    	}

    	return report;
    	}

    /**
     * 연체료만가 분리 된 고지서를 출력한다.
     * @param approvalOpt
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/printAssetRentFeePayNotice2.do")
    String printAssetRentFeePayNotice2(@RequestParam Map<String, Object> approvalOpt, ModelMap model) throws Exception {
    	String report = "ygpa/gam/asset/rent/GamAssetPrintNoticeIssue";
    	model.addAttribute("searchOpt", approvalOpt);

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	}
    	else {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    		List resultList = gamAssetRentFeePayDtlsMngtService.selectArrrgNpticPrintInfo2(approvalOpt);

    		if("11076".equals(loginVo.getEmplNo()) || "14010".equals(loginVo.getEmplNo())) {
//    			log.debug("new paper selected");
    			report = "ygpa/gam/asset/rent/GamAssetPrintNoticeIssue2";	// 신규 고지서
    		}

//    		model.addAttribute("emplyrNo", loginVo.getEmplNo());

    		model.addAttribute("resultCode", 0);
    		model.addAttribute("result", resultList.get(0));
    		model.addAttribute("arrrgItem", resultList.get(1));
    		model.addAttribute("resultMsg", "");
    	}

    	return report;
    	}

    /**
     * 지로 수납 된 자료인지 체크 한다.
     * @param searchVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/asset/rent/checkOcrResult.do")
    public @ResponseBody Map checkOcrResult(
    		GamAssetRentFeePayDtlsMngtVO searchVO,
     	  ModelMap model)
            throws Exception {

    	Map map = new HashMap();
		Map result = gamAssetRentFeePayDtlsMngtService.selectCheckOcrResult(searchVO);

		if(result!=null) {
			map.put("resultCode", 0);
			map.put("result", result);
		}
		else {
			map.put("resultCode", -1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

     }
    /**
     * 수납처리 팝업을 호출 한다.
     * @param searchVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/asset/rent/showFeePayPopup.do")
    public String showFeePayPopup(
    		GamAssetRentFeePayDtlsMngtVO searchVO,
     	  ModelMap model)
            throws Exception {

		Map master = gamAssetRentFeePayDtlsMngtService.selectFeePayPopup(searchVO);

		model.addAttribute("feePayMaster", master);

    	return "/ygpa/gam/asset/rent/GamPopupAssetRentFeePay";
     }

    /**
     * 고지취소를 한다.(단일처리)
     * @param gamAssetRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/asset/rent/updateRevCollRcvdTp.do")
    public @ResponseBody Map updateRevCollRcvdTp(
     	   @ModelAttribute("GamAssetRentFeePayDtlsMngtVO") GamAssetRentFeePayDtlsMngtVO GamAssetRentFeePayDtlsMngtVO,
     	   BindingResult bindingResult)
            throws Exception {
     	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        int anlrveLevCnt = 0;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    		GamAssetRentFeePayDtlsMngtVO.setUpdUsr(loginVo.getId());
    		GamAssetRentFeePayDtlsMngtVO.setDeptcd(loginVo.getDeptCd());
    		GamAssetRentFeePayDtlsMngtVO.setRegUsr(loginVo.getName());

	 		gamAssetRentFeePayDtlsMngtService.updateRevCollRcvdTp(GamAssetRentFeePayDtlsMngtVO);

	        resultCode = 0;
	 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
    	} catch(IOException e){
    		((Log) Logger.getLogger(EgovProperties.class)).debug("IGNORED: " + e.getMessage());
    	} 
    	catch(Exception e) {
	        resultCode = -1;
	 		resultMsg  = egovMessageSource.getMessage("fail.reciveFee.msg");
    	}

     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

 		return map;
     }
}
