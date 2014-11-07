package egovframework.rte.ygpa.gam.asset.rent.web;

import java.util.ArrayList;
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
import egovframework.com.sec.ram.service.AuthorRoleManage;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService;
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

    @Resource(name="gamAssetRentFeeMngtService")
    private GamAssetRentFeeMngtService gamAssetRentFeeMngtService;

    /**
     * 자산임대료납부관리 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/rent/GamAssetRentFeePayDtlsMngt"
     * @throws Exception the exception
     */
	@RequestMapping(value="/asset/rent/gamAssetRentFeePayDtlsMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/asset/rent/GamAssetRentFeePayDtlsMngt";
    }

	/**
     * 자산임대료납부관리 목록을 조회한다.
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

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	//searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	//searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	totalCnt = gamAssetRentFeePayDtlsMngtService.selectAssetRentFeePayDtlsListTotCnt(searchVO);
    	List resultList = gamAssetRentFeePayDtlsMngtService.selectAssetRentFeePayDtlsList(searchVO);

    	//고지금액합계, 수납금액합계
    	GamAssetRentFeePayDtlsMngtVO resultSum = gamAssetRentFeePayDtlsMngtService.selectAssetRentFeePayDtlsMngtSum(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

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
     * 자산임대료납부관리 상세정보를 조회한다.
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

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	//searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	//searchVO.setPageSize(propertiesService.getInt("pageSize"));

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

	    /**
	     * 수납확인을 하거나 취소 한다.
	     * @param nticCnt
	     * @param prtAtCode
	     * @param mngYear
	     * @param mngNo
	     * @param mngCnt
	     * @param accnutYear
	     * @param nticno
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value="/asset/rent/updateNticPayment.do")
	    @ResponseBody Map<String, Object> updateNticPayment (
					  @RequestParam("nticCnt") String nticCnt
					 ,@RequestParam("prtAtCode") String prtAtCode
					 ,@RequestParam("mngYear") String mngYear
					 ,@RequestParam("mngNo") String mngNo
					 ,@RequestParam("mngCnt") String mngCnt
					 ,@RequestParam("accnutYear") String accnutYear
					 ,@RequestParam("nticno") String nticno
					 ,@RequestParam("option") String option
	    		     ) throws Exception {

	    	GamAssetRentFeeMngtVO gamAssetRentFeeMngtVO = new GamAssetRentFeeMngtVO();
	        Map map = new HashMap();
	        Map paramMap = new HashMap();
	        String resultMsg = "";
	        int resultCode = 1;
	        int anlrveLevCnt = 0;

	     // 0. Spring Security 사용자권한 처리
	    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	if(!isAuthenticated) {
		        map.put("resultCode", 1);
	    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
	        	return map;
	    	}

	        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

	        if("ok".equals(option)) {
	        	//세입징수 취소
	     		gamAssetRentFeeMngtVO.setNticCnt(nticCnt);
	     		gamAssetRentFeeMngtVO.setPrtAtCode(prtAtCode);
	     		gamAssetRentFeeMngtVO.setMngYear(mngYear);
	     		gamAssetRentFeeMngtVO.setMngNo(mngNo);
	     		gamAssetRentFeeMngtVO.setMngCnt(mngCnt);
	     		gamAssetRentFeeMngtVO.setRegUsr(loginVO.getId());
	     		gamAssetRentFeeMngtVO.setUpdUsr(loginVO.getId());
	     		gamAssetRentFeeMngtVO.setRcivSe("3");

	     		paramMap.put("nticCnt", nticCnt);
	     		paramMap.put("prtAtCode", prtAtCode);
	     		paramMap.put("mngYear", mngYear);
	     		paramMap.put("mngNo", mngNo);
	     		paramMap.put("mngCnt", mngCnt);

	     		gamNticRequestMngtService.cancelNticRequest(paramMap);

		 		gamAssetRentFeeMngtService.updateAssetRentFee(gamAssetRentFeeMngtVO);
	 		}
	        else {
	     		gamAssetRentFeeMngtVO.setNticCnt(nticCnt);
	     		gamAssetRentFeeMngtVO.setPrtAtCode(prtAtCode);
	     		gamAssetRentFeeMngtVO.setMngYear(mngYear);
	     		gamAssetRentFeeMngtVO.setMngNo(mngNo);
	     		gamAssetRentFeeMngtVO.setMngCnt(mngCnt);
	     		gamAssetRentFeeMngtVO.setRegUsr(loginVO.getId());
	     		gamAssetRentFeeMngtVO.setUpdUsr(loginVO.getId());
	     		gamAssetRentFeeMngtVO.setRcivSe("0");

	     		paramMap.put("nticCnt", nticCnt);
	     		paramMap.put("prtAtCode", prtAtCode);
	     		paramMap.put("mngYear", mngYear);
	     		paramMap.put("mngNo", mngNo);
	     		paramMap.put("mngCnt", mngCnt);

	     		gamNticRequestMngtService.sendNticRequest(paramMap);	//  다시 고지한다.

		 		gamAssetRentFeeMngtService.updateAssetRentFee(gamAssetRentFeeMngtVO);
	        }

	 		resultCode = 0;
	        resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

	     	map.put("resultCode", resultCode);
	        map.put("resultMsg", resultMsg);

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

//	    		paramMap.put("updUsr", loginVo.getId());

	    		int result=gamNticRequestMngtService.updateRentFeePaySttusRefresh();

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


	    @RequestMapping(value="/asset/rent/showNticPaymentPopup.do")
	    String showNticPaymentPopup(ModelMap model) throws Exception {
	    	return "/ygpa/gam/asset/rent/GamNticPaymentPopup";
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

    	// 0. Spring Security 사용자권한 처리
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

//		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		searchVO.setUpdUsr("TEST1");	// 감리대비

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
		nticVo.put("regUsr",loginVO.getId());

		gamNticRequestMngtService.sendUnpaidRequest(nticVo);

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
    	Map summary = gamAssetRentFeePayDtlsMngtService.selectAssetRentFeePayDtlsMngtDlyListSum(searchVO);

        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());


    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", resultList);
    	map.put("totCnt", totCnt);
    	map.put("resultSummary", summary);
    	map.put("searchOption", searchVO);

    	return map;
    }
    }
