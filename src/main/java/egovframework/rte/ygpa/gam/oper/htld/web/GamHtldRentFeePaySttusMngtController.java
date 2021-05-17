package egovframework.rte.ygpa.gam.oper.htld.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupGisAssetsCdVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtVO;
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
		int totalCnt;
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
    	List resultList = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtList(searchVO);
    	totalCnt = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtListTotCnt(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	//자료수, 사용료, 부가세, 고지액
    	GamHtldRentFeePaySttusMngtVO resultSum = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtSum(searchVO);

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
     * 지로 수납된 자료인지 체크 한다.
     * @param searchVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/oper/htld/htldCheckOcrResult.do")
    public @ResponseBody Map htldCheckOcrResult(GamPrtFcltyRentFeePaySttusMngtVO searchVO,ModelMap model)throws Exception {
    	Map map = new HashMap();
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	Map result = gamHtldRentFeePaySttusMngtService.selectHtldCheckOcrResult(searchVO);
		if(result!=null) {
			map.put("resultCode", 0);
			map.put("result", result);
		}
		else {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}
		return map;
     }

    /**
     * 수납처리 팝업을 호출 한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/oper/htld/htldShowFeePayPopup.do")
    public String htldShowFeePayPopup(GamPrtFcltyRentFeePaySttusMngtVO searchVO,ModelMap model)throws Exception {
		Map master = gamHtldRentFeePaySttusMngtService.selectHtldShowFeePayPopup(searchVO);
		model.addAttribute("feePayMaster", master);
    	return "/ygpa/gam/oper/htld/GamPopupHtldRentFeePay";
     }

    /**
     * 수납처리
     * @param gamAssetRentFeeMngtVO
     * @param vo
     * @return map
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/oper/gnrl/htldUpdateRevCollRcvdTp.do")
    public @ResponseBody Map htldUpdateRevCollRcvdTp(
     	   @ModelAttribute("gamPrtFcltyRentFeePaySttusMngtVO") GamPrtFcltyRentFeePaySttusMngtVO gamPrtFcltyRentFeePaySttusMngtVO, BindingResult bindingResult) throws Exception {
     	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    		gamPrtFcltyRentFeePaySttusMngtVO.setUpdUsr(loginVo.getId());
    		gamPrtFcltyRentFeePaySttusMngtVO.setDeptcd(loginVo.getDeptCd());
    		gamPrtFcltyRentFeePaySttusMngtVO.setRegUsr(loginVo.getName());
    		gamHtldRentFeePaySttusMngtService.htldUpdateRevCollRcvdTp(gamPrtFcltyRentFeePaySttusMngtVO);
	        resultCode = 0;
	 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
    	} catch (IOException i) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
    	} catch(Exception e) {
	        resultCode = 1;
	 		resultMsg  = egovMessageSource.getMessage("fail.reciveFee.msg");
    	}

     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

 		return map;
     }

	/**
     * 배후단지임대납부현황관리 상세정보 조회
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/selectHtldRentFeePaySttusMngtDetail.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldRentFeePaySttusMngtDetail(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	//배후단지임대료납부상세 원고지정보 가져오기
    	Map detailMaster = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtDetailMstPk(searchVO);
    	
    	//배후단지임대료납부상세 전체사용료 목록
    	List detailFeePayList = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtDetailList(searchVO);
    	
    	//배후단지임대료납부상세 총고지금액, 총납부금액, 관리비, 연체료, 과태료 정보
    	Map detailSummary = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtDetailSumPk(searchVO);
    	
    	//배후단지임대료납부상세 연체고지정보
    	Map detailArrrg = gamHtldRentFeePaySttusMngtService.selectNticArrrgDetail(searchVO);;

    	map.put("resultCode", 0);	// return ok
    	map.put("detailFeePayList", detailFeePayList);
    	map.put("detailMaster", detailMaster);
    	map.put("detailSummary", detailSummary);
    	map.put("detailArrrg", detailArrrg);
    	map.put("searchOption", searchVO);

    	return map;
    }

    /**
     * 연체고지 등록
     * @param htldRentArrrgMngtVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/oper/htld/insertHtldArrrgNticSingle.do")
    public @ResponseBody Map insertHtldArrrgNticSingle(
     	   @ModelAttribute("htldRentArrrgMngtVO") GamHtldRentArrrgMngtVO htldRentArrrgMngtVO, BindingResult bindingResult) throws Exception {
     	Map map = new HashMap();

     	String resultMsg = "";
        int resultCode = 1;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    		htldRentArrrgMngtVO.setRegUsr(loginVo.getId());
    		htldRentArrrgMngtVO.setUpdUsr(loginVo.getId());
    		htldRentArrrgMngtVO.setUserName(loginVo.getName());
    		htldRentArrrgMngtVO.setDeptCd(loginVo.getDeptCd());

    		gamHtldRentFeePaySttusMngtService.sendLevReqestUnpaidF(htldRentArrrgMngtVO);

	        resultCode = 0;
	 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

	     	map.put("resultCode", resultCode);
	        map.put("resultMsg", resultMsg);
    	} catch (IOException i) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
    	} catch(Exception e) {
	        map.put("resultCode", -1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.nticIssue.msg"));
        	return map;
    	}

 		return map;
     }
    
    /**
     * 연체현황관리 목록을 조회한다.
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/selectHtldRentFeePaySttusMngtDlyList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldRentFeePaySttusMngtDlyList(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
		//연체현황 리스트
    	List resultList = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtDlyList(searchVO);
    	
    	//연체현황 자료수
    	int totCnt = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtDlyListTotCnt(searchVO);
    	
    	//연체현황 고지합계
    	//Map summary = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtDlyListSum(searchVO);
    	
    	Map dlyInfo = null;
    	if(resultList.size() > 0) {
    		EgovMap lastRec = (EgovMap) resultList.get(resultList.size()-1);
    		searchVO.setDlySerNo((String)lastRec.get("dlySerNo"));
    		//연체현황 정보
    		dlyInfo = gamHtldRentFeePaySttusMngtService.selectHtldRentFeePaySttusMngtDlyInfo(searchVO);
    	}
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", resultList);
    	map.put("totCnt", totCnt);
    	//map.put("resultSummary", summary);
    	map.put("resultDlyInfo", dlyInfo);
 
    	return map;
    }
	
    /**
     * 배후단지 연체고지 취소 - 요금설정 및 되돌리는 방식이 틀리기에 일반부두와 약간 다름. 2015.12.14 추가 김종민
     * @param nticVo
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/cancelNticArrrgPk.do", method=RequestMethod.POST)
    @ResponseBody Map<String, Object> cancelNticArrrgPk(@RequestParam Map nticVo) throws Exception {
		Map map = new HashMap<String,Object>();

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

		gamHtldRentFeePaySttusMngtService.cancelUnpaidRequestPk(nticVo);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.unpaid"));

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

    		int result=gamNticRequestMngtService.updateRentFeePaySttusRefresh();

	        resultCode = 0;
	 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

	     	map.put("resultCode", resultCode);
	        map.put("resultMsg", resultMsg);
	        map.put("updateCount", result);
    	} catch (IOException i) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
    	} catch(Exception e) {
	        map.put("resultCode", -1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
        	return map;
    	}

 		return map;
     }

    @SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/oper/htld/showArrrgNticIssuePopup.do")
    public String showNticIssuePopup(
    		GamHtldRentFeePaySttusMngtVO gamHtldRentFeeMngtVO,
     	  ModelMap model)
            throws Exception {

		Map master = gamHtldRentFeePaySttusMngtService.selectArrrglevReqestPk(gamHtldRentFeeMngtVO);

		model.addAttribute("arrrglevReqest", master);

    	return "/ygpa/gam/oper/htld/GamPopupHtldRentArrrgNticIssue";
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
		searchVO.setRegUsr(loginVO.getId());
		searchVO.setDeptCd(loginVO.getDeptCd());

		Map<String,Object> mergeMap = new HashMap<String,Object>();

		List<?> gamNticArrrgList = gamHtldRentFeePaySttusMngtService.selectNticArrrgList(searchVO);

		for(int i=0; i<gamNticArrrgList.size(); i++) {
			Map<String, Object> nticArg = (Map<String, Object>)gamNticArrrgList.get(i);
			nticArg.put("arrrgRate", searchVO.getArrrgTariff());
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
     * 연체고지서를 출력한다.
     * @param approvalOpt
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/printHtldRentArrrgFeePayNotice.do")
    String printAssetRentArrrgFeePayNotice(@RequestParam Map<String, Object> approvalOpt, ModelMap model) throws Exception {    	    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	}
    	else {
    		List master = gamHtldRentFeePaySttusMngtService.selectNticPrintMaster(approvalOpt);
    		List detail = null, feeHist = null;
    		if(master.size() > 0) {
    			EgovMap masterRec = (EgovMap) master.get(0);
    			approvalOpt.put("mngYear", masterRec.get("mngYear"));
    			approvalOpt.put("mngNo", masterRec.get("mngNo"));
    			approvalOpt.put("mngCnt", masterRec.get("mngCnt"));
    			approvalOpt.put("nticCnt", masterRec.get("nticCnt"));
    			detail = gamHtldRentFeePaySttusMngtService.selectNticPrintDetail(approvalOpt);
    			feeHist = gamHtldRentFeePaySttusMngtService.selectNticPrintDetailHist(approvalOpt);
    		}
    		
    		model.addAttribute("searchOpt", approvalOpt);
    		model.addAttribute("resultCode", 0);
    		model.addAttribute("resultList", master);
    		model.addAttribute("detail", detail);
    		model.addAttribute("feeHist", feeHist);
    		model.addAttribute("resultMsg", "");
    	}

    	return "ygpa/gam/oper/htld/GamHtldPrintArrrgNoticeIssue";
    }
    
    /**
     * 연체고지서 인쇄
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/printHtldArrrgNoticeIssue.do")
    public @ResponseBody Map printHtldNoticeIssue(@RequestParam Map<String, Object> vo, ModelMap model)
            throws Exception {

     	 Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    		vo.put("updUsr", loginVo.getId());
    		gamHtldRentFeePaySttusMngtService.updateArrrgNticPrintState(vo);
	         resultCode = 0;
	 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
    	} catch (IOException i) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
    	}
    	catch(Exception e) {
	         resultCode = 1;
	 		 resultMsg  = egovMessageSource.getMessage("fail.common.update");
    	}

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }
    
}
