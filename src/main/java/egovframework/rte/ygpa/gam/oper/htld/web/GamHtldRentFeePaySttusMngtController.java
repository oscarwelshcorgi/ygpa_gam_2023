package egovframework.rte.ygpa.gam.oper.htld.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupGisAssetsCdVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeePaySttusMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeePaySttusMngtVO;

/**
 * @Class Name : GamHtldRentFeePaySttusMngtController.java
 * @Description : 배후단지납부현황관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamHtldRentFeePaySttusMngtController {

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

    @Resource(name = "gamHtldRentFeePaySttusMngtService")
    private GamHtldRentFeePaySttusMngtService gamHtldRentFeePaySttusMngtService;


    /**
     * 배후단지임대납부현황관리 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/htld/GamHtldRentFeePaySttusMngt"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htld/gamHtldRentFeePaySttusMngt.do")
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

    	return "/ygpa/gam/oper/htld/GamHtldRentFeePaySttusMngt";
    }

	/**
     * 배후단지임대납부현황관리 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/gamSelectHtldRentFeePaySttusMngtList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldRentFeePaySttusMngtList(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {

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
    	totalCnt = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtListTotCnt(searchVO);
    	List resultList = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	//자료수, 사용료, 부가세, 고지액
    	GamHtldRentFeePaySttusMngtVO resultSum = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtSum(searchVO);
    	//GamHtldRentFeePaySttusMngtVO resultSum = new GamHtldRentFeePaySttusMngtVO();

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

    	/*
    	map.put("sumFee", "");
    	map.put("sumVat", "");
    	map.put("sumNticAmt", "");
    	*/
    	return map;
    }


	/**
     * 배후단지임대납부현황관리 상세정보를 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/selectHtldRentFeePaySttusMngtDetail.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldRentFeePaySttusMngtDetail(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {

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

    	List resultList = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtDetailList(searchVO);
    	Map master = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtDetailMstPk(searchVO);
    	Map summary = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtDetailSumPk(searchVO);
    	Map arrrgDetail = gamHtldRentFeePaySttusMngtService.selectNticArrrgDetail(searchVO);;

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
	@RequestMapping(value="/oper/htld/updateHtldRentFeePaySttusMngtList.do")
    public @ResponseBody Map updateHtldRentFeePaySttusMngtList()
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

    		int result=gamHtldRentFeePaySttusMngtService.updateHtldRentFeePaySttusMngtList();

	        resultCode = 0;
	 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

	     	map.put("resultCode", resultCode);
	        map.put("resultMsg", resultMsg);
	        map.put("updateCount", result);
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
    @RequestMapping(value="/oper/htld/showNticArrrgPopup.do")
    String showAssetsCd(GamPopupGisAssetsCdVO searchOpt, ModelMap model) throws Exception {
    	model.addAttribute("searchOpt", searchOpt);

    	return "/ygpa/gam/oper/htld/GamNticArrrgPopup";
    }

    /**
     * 연체 세입 목록 가져오기
     * @param searchVO
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/selectNticArrrgList.do", method=RequestMethod.POST)
    @ResponseBody Map<String, Object> selectNticArrrgList(GamHtldRentArrrgMngtVO searchVO) throws Exception {

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

		List<?> gamNticArrrgList = gamHtldRentFeePaySttusMngtService.selectNticArrrgList(searchVO);
		int totalCnt = gamHtldRentFeePaySttusMngtService.selectNticArrrgListTotCnt(searchVO);

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
    @RequestMapping(value="/oper/htld/insertNticArrrgList.do", method=RequestMethod.POST)
    @ResponseBody Map<String, Object> insertNticArrrgList(GamHtldRentArrrgMngtVO searchVO) throws Exception {


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

		List<?> gamNticArrrgList = gamHtldRentFeePaySttusMngtService.selectNticArrrgList(searchVO);

		for(int i=0; i<gamNticArrrgList.size(); i++) {
			Map<String, Object> nticArg = (Map<String, Object>)gamNticArrrgList.get(i);
			nticArg.put("arrrgRate", searchVO.getArrrgRate());
		}

		gamNticRequestMngtService.sendMultiUnpaidRequest(gamNticArrrgList);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.unpaidlist"));

		return map;
    }



    @RequestMapping(value="/oper/htld/insertNticArrrg.do", method=RequestMethod.POST)
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

		gamNticRequestMngtService.sendUnpaidRequest(nticVo);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.unpaid"));

		return map;
    }


    /**
     * 배후단지연체현황관리 목록을 조회한다.
     *change**
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrl/selectHtldRentFeePaySttusMngtDlyList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldRentFeePaySttusMngtDlyList(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {

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

    	List resultList = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtDlyList(searchVO);

    	int totCnt = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtDlyListTotCnt(searchVO);
    	Map summary = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtDlyListSum(searchVO);

        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());


    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", resultList);
    	map.put("totCnt", totCnt);
    	map.put("resultSummary", summary);
    	map.put("searchOption", searchVO);

    	return map;
    }

}
