package egovframework.rte.ygpa.gam.oper.htld.web;

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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldAssessVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentAttachFileVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentDefaultVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO;

/**
 * @Class Name : GamHtldRentMngtController.java
 * @Description : 배후단지임대목록관리
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamHtldRentMngtController {

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

    @Resource(name = "gamHtldRentMngtService")
    private GamHtldRentMngtService gamHtldRentMngtService;

    @Resource(name = "gamAssetsUsePermMngtService")
    private GamAssetsUsePermMngtService gamAssetsUsePermMngtService;

    /**
     * 배후단지임대관리 화면을 로딩한다.
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/htld/GamHtldRentMngt"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htld/gamHtldRentMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
		model.addAttribute("cofixList", gamHtldRentMngtService.selectCofixInfo());
    	return "/ygpa/gam/oper/htld/GamHtldRentMngt";
    }

	/**
     * 배후단지임대목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/selectHtldRentList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldRentMngtList(GamHtldRentDefaultVO searchVO) throws Exception {

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

		//배후단지임대목록
    	totalCnt = gamHtldRentMngtService.selectHtldRentMngtListTotCnt(searchVO);
    	List assetRentList = gamHtldRentMngtService.selectHtldRentMngtList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	//총면적, 총사용료
    	Map resultSum = gamHtldRentMngtService.selectHtldRentMngtSum(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	map.put("sumGrAr", resultSum.get("sumGrAr"));
    	map.put("sumGrFee", resultSum.get("sumGrFee"));
    	map.put("sumGrRdcxptFee", resultSum.get("sumGrRdcxptFee"));

    	return map;
    }

	/**
	 * 배후단지 임대 정보를 로딩한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/selectHtldRentDetailPk.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldRentMngtDetailPk(GamHtldRentMngtVO searchVO) throws Exception {

    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		//배후단지임대상세
    	GamHtldRentMngtVO assetRentDetail = gamHtldRentMngtService.selectHtldRentMngtDetailPk(searchVO);

    	// 시설 상세 목록 조회
		List resultList = gamHtldRentMngtService.selectHtldRentMngtDetailList(searchVO);

		// 파일 목록 조회
    	//List resultAttachList = gamHtldRentMngtService.selectHtldRentMngtFileList(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("result", assetRentDetail);
    	map.put("resultDetailList", resultList);
//    	map.put("resultAttachList", resultAttachList);

    	return map;
    }

    /**
     * 배후단지 임대 정보를 등록 한다.
     * @param assetRent
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/oper/htld/insertHtldRent.do")
    public @ResponseBody Map insertHtldRentMngt(
    		@RequestParam Map<String, Object> assetRent)
           throws Exception {

    	GamHtldRentMngtVO gamHtldRentMngtVO = null;
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        ObjectMapper mapper = new ObjectMapper();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO loginVo = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	gamHtldRentMngtVO = mapper.readValue((String)assetRent.get("assetRentVo"), GamHtldRentMngtVO.class);

    	gamHtldRentMngtVO.setUpdUsr(loginVo.getId());
    	gamHtldRentMngtVO.setDeptcd(loginVo.getDeptCd());

    	List<GamHtldRentMngtDetailVO> createList;

    	createList = mapper.readValue((String)assetRent.get("_cList"), TypeFactory.defaultInstance().constructCollectionType(List.class,
    			GamHtldRentMngtDetailVO.class));

    	GamHtldRentMngtVO newRentVo = gamHtldRentMngtService.insertHtldRentMngt(gamHtldRentMngtVO, createList);

        resultCode = 0; // return ok
        resultMsg  = egovMessageSource.getMessage("success.common.update");

    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);
    	map.put("newRentVo", newRentVo);

		return map;
    }


    /**
     * 배후단지임대 연장신청을 등록한다.
     * @param gamHtldRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/insertHtldRentExtend.do")
    public @ResponseBody Map insertHtldRentExtend(
    	   @ModelAttribute("gamHtldRentMngtVO") GamHtldRentMngtVO gamHtldRentMngtVO,
   	       BindingResult bindingResult)
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

    	LoginVO loginVo = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	gamHtldRentMngtVO.setPrtAtCode("622");	// 광양항 기본 코드 삽입

    	beanValidator.validate(gamHtldRentMngtVO, bindingResult);

		if(bindingResult.hasErrors()){
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
    		map.put("errors", bindingResult.getAllErrors());
        	return map;
		}

        try {
    		gamHtldRentMngtVO.setRegUsr(loginVo.getId());
    		gamHtldRentMngtVO.setDeptcd(loginVo.getDeptCd());
    		gamHtldRentMngtVO  = gamHtldRentMngtService.insertHtldRentMngtExtend(gamHtldRentMngtVO);

    		resultCode = 0; // return ok
    		resultMsg  = egovMessageSource.getMessage("success.common.insert");
        }
        catch(EgovBizException e) {
    		resultCode = 1;
    		resultMsg  = e.getMessage();
        }
        catch(Exception e) {
    		resultCode = 1;
    		resultMsg  = egovMessageSource.getMessage("fail.common.msg");
        }

    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        map.put("rentVo", gamHtldRentMngtVO);

		return map;
    }

    /**
     * 배후단지임대 정보를 수정한다.
     * @param String
     * @param gamHtldRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/updateHtldRent.do")
    public @ResponseBody Map updateHtldRentMngt(
    		@RequestParam Map<String, Object> assetRent)
           throws Exception {

    	GamHtldRentMngtVO gamHtldRentMngtVO;
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        ObjectMapper mapper = new ObjectMapper();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO loginVo = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	gamHtldRentMngtVO = mapper.readValue((String)assetRent.get("assetRentVo"), GamHtldRentMngtVO.class);

    	gamHtldRentMngtVO.setPrtAtCode("622");	// 광양항 기본 코드 삽입

    	gamHtldRentMngtVO.setUpdUsr(loginVo.getId());
    	gamHtldRentMngtVO.setDeptcd(loginVo.getDeptCd());
    	List<GamHtldRentMngtDetailVO> createList=null, updateList=null, deleteList=null;
    	if(assetRent.containsKey("_cList"))
	    	createList = mapper.readValue((String)assetRent.get("_cList"), TypeFactory.defaultInstance().constructCollectionType(List.class,
	    			GamHtldRentMngtDetailVO.class));
    	if(assetRent.containsKey("_uList"))
	    	updateList = mapper.readValue((String)assetRent.get("_uList"), TypeFactory.defaultInstance().constructCollectionType(List.class,
	    			GamHtldRentMngtDetailVO.class));
    	if(assetRent.containsKey("_dList"))
	    	deleteList = mapper.readValue((String)assetRent.get("_dList"), TypeFactory.defaultInstance().constructCollectionType(List.class,
	    			GamHtldRentMngtDetailVO.class));

    	try {
    		gamHtldRentMngtService.updateHtldRentMngt(gamHtldRentMngtVO, createList, updateList, deleteList);
    	}
    	catch(Exception e) {
        	map.put("resultCode", -1);
        	map.put("resultMsg", e.getMessage());
        	return map;
    	}

        resultCode = 0; // return ok
        resultMsg  = egovMessageSource.getMessage("success.common.update");

    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);

		return map;
    }

    /**
     * 배후단지임대 정보를 삭제한다.
     * @param String
     * @param gamHtldRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/deleteHtldRent.do")
    public @ResponseBody Map deleteHtldRentMngt(
    	   //@RequestParam("cmd") String cmd,
    	   @ModelAttribute("gamHtldRentMngtVO") GamHtldRentMngtVO gamHtldRentMngtVO,
    	   BindingResult bindingResult)
           throws Exception {

    	Map map = new HashMap();
        String resultMsg  = "";
        int resultCode = 0;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

        try {
	        gamHtldRentMngtService.deleteHtldRentMngt(gamHtldRentMngtVO);
	        resultMsg = egovMessageSource.getMessage("success.common.delete");
        } catch(Exception e) {
    		resultCode = 1;
    		resultMsg  = e.getMessage();
        }

    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);

		return map;
    }

    /**
     * 배후단지임대 계약을 해지한다.. 2015.11.10 김종민 추가작업.
     * @param String
     * @param gamHtldRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/terminateHtldRent.do")
    public @ResponseBody Map terminateHtldRentMngt(
    	   @ModelAttribute("gamHtldRentMngtVO") GamHtldRentMngtVO gamHtldRentMngtVO,
    	   BindingResult bindingResult)
           throws Exception {

    	Map map = new HashMap();
        String resultMsg  = "";
        int resultCode = 0;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

        try {
        	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
        	gamHtldRentMngtVO.setTermnYn("Y");
        	gamHtldRentMngtVO.setTermnUsr(user.getId());
        	gamHtldRentMngtVO.setTermnKnd("0"); //해지변경 사유 : 0 중도 해지...
        	gamHtldRentMngtVO.setUpdUsr(user.getId());
        	gamHtldRentMngtService.terminateHtldRentMngt(gamHtldRentMngtVO);
	        resultMsg = egovMessageSource.getMessage("success.common.update");
        } catch(Exception e) {
    		resultCode = 1;
    		resultMsg  = e.getMessage();
        }

    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);

		return map;
    }
    
    /**
     * 승낙 팝업화면을 로딩한다.
     *
     * @param gamHtldRentMngtLevReqestVO
     * @param model the model
     * @return "/ygpa/gam/oper/htld/GamPopupHtldRentMngtPrmisn"
     * @throws Exception the exception
     */
    @SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/oper/htld/popup/showHtldRentPrmisn.do")
    String showEntrpsInfo(GamHtldRentMngtVO gamHtldRentMngtVO, ModelMap model) throws Exception {

		List chrgeKndCdList = gamHtldRentMngtService.selectChargeKndList(gamHtldRentMngtVO);

		model.addAttribute("gamHtldRentMngtVO", gamHtldRentMngtVO);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);

    	return "/ygpa/gam/oper/htld/GamPopupHtldRentMngtPrmisn";
    }

	/**
     * 코멘트를 저장한다.
     * @param String
     * @param gamHtldRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/updateHtldRentComment.do")
    public @ResponseBody Map updateHtldRentMngtComment(
    	   @ModelAttribute("gamHtldRentMngtVO") GamHtldRentMngtVO gamHtldRentMngtVO,
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

    	if( gamHtldRentMngtVO.getMngYear() == null || "".equals(gamHtldRentMngtVO.getMngYear()) ) {
        	updateFlag = "N";
        } else {
        	updateFlag = "Y";
        }

    	if("Y".equals(updateFlag)) {
    		gamHtldRentMngtVO.setUpdUsr(loginVo.getId());
	        gamHtldRentMngtService.updateHtldRentMngtComment(gamHtldRentMngtVO);

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

    /**
	 * 추가고지 팝업화면을 로딩한다.
	 * @param GamHtldRentMngtVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/oper/htld/popupLevReqestAdit.do")
  String popupLevReqestAdit(GamHtldRentMngtVO gamHtldRentMngtVO, ModelMap model) throws Exception {

		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();

		codeVo.setCodeId("GAM053"); //요금종류 배후단지
		List chrgeKndCdList = cmmUseService.selectCmmCodeDetail(codeVo);

		model.addAttribute("gamPrtFcltyRentMngtInfo", gamHtldRentMngtVO);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);

  	return "ygpa/gam/oper/htld/GamPopupHtldRentMngtLevReqestAdit";
  }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrl/selectHtldRentListExcel.do", method=RequestMethod.POST)
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
    	GamHtldRentDefaultVO searchVO= new GamHtldRentDefaultVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.

		// 조회 조건
		searchVO = mapper.convertValue(excelParam, GamHtldRentDefaultVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		/** List Data */
//    	int totCnt = erpAssetCdService.selectErpAssetCdListTotCnt(searchVO);

    	List gamAssetList =  gamHtldRentMngtService.selectHtldRentMngtList(searchVO);

    	map.put("resultList", gamAssetList);
    	map.put("header", header);

    	return new ModelAndView("gridExcelView", "gridResultMap", map);
    }


	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/selectHtldAssessSeCodeList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldAssessSeCodeList(GamHtldRentDefaultVO searchVO) throws Exception {

    	Map map = new HashMap();

    	List assessSeList = gamHtldRentMngtService.selectHtldAssessSeCodeList(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", assessSeList);
    	return map;
	}
	/**
	 * 실적평가 목록을 조회 한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/selectHtldAssessList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldAssessList(GamHtldRentDefaultVO searchVO) throws Exception {

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

		//배후단지 평가 목록
    	Map totalSum = gamHtldRentMngtService.selectHtldAssessSum(searchVO);
    	List assessList = gamHtldRentMngtService.selectHtldAssessList(searchVO);

//    	paginationInfo.setTotalRecordCount(totalCnt);
//        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	map.put("resultCode", 0);	// return ok
    	map.put("totalSum", totalSum);
    	map.put("resultList", assessList);
    	map.put("searchOption", searchVO);

    	return map;
    }


    /**
     * 평가 항목을 저장한다.
     * @param assessList
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/updateHtldAssessList.do")
    public @ResponseBody Map updateHtldAssessList(
    		@RequestParam Map<String, Object> assessList)
           throws Exception {

    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        ObjectMapper mapper = new ObjectMapper();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO loginVo = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	List<GamHtldAssessVO> createList=null, updateList=null, deleteList=null;
    	if(assessList.containsKey("_cList"))
	    	createList = mapper.readValue((String)assessList.get("_cList"), TypeFactory.defaultInstance().constructCollectionType(List.class,
	    			GamHtldAssessVO.class));
    	if(assessList.containsKey("_uList"))
	    	updateList = mapper.readValue((String)assessList.get("_uList"), TypeFactory.defaultInstance().constructCollectionType(List.class,
	    			GamHtldAssessVO.class));
    	if(assessList.containsKey("_dList"))
	    	deleteList = mapper.readValue((String)assessList.get("_dList"), TypeFactory.defaultInstance().constructCollectionType(List.class,
	    			GamHtldAssessVO.class));

    	try {
    		if(createList!=null || updateList!=null || deleteList!=null) {
    			GamHtldAssessVO modifyVo=null;
    			if(createList!=null && createList.size()!=0) {
    				modifyVo=createList.get(0);
    			}
    			if(updateList!=null && updateList.size()!=0) {
    				modifyVo=updateList.get(0);
    			}
    			if(deleteList!=null && deleteList.size()!=0) {
    				modifyVo=deleteList.get(0);
    			}
        		gamHtldRentMngtService.updateHtldAssessList(createList, updateList, deleteList);
        		/**
        		 * 평가 금액 정보를 반영해서 사용료를 생성한다.
        		 */
        		if(modifyVo!=null) {
//            		gamHtldRentMngtService.applyHtldAssessList(modifyVo);
        		}
    		}
    	}
    	catch(Exception e) {
        	map.put("resultCode", -1);
        	map.put("resultMsg", e.getMessage());
        	return map;
    	}

        resultCode = 0; // return ok
        resultMsg  = egovMessageSource.getMessage("success.common.update");

    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);

		return map;
    }

	/**
	 * 계약에 대한 고지/납부 목록을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/selectHtldNticList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldNticList(GamHtldRentDefaultVO searchVO) throws Exception {

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

		//배후단지임대목록
    	Map totalSum = gamHtldRentMngtService.selectHtldNticRcivSum(searchVO);
    	List nticList = gamHtldRentMngtService.selectHtldNticRcivList(searchVO);

//    	paginationInfo.setTotalRecordCount(totalCnt);
//        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	map.put("resultCode", 0);	// return ok
    	map.put("totalSum", totalSum);
    	map.put("resultList", nticList);
    	map.put("searchOption", searchVO);

    	return map;
    }


}