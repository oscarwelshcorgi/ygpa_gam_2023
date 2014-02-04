package egovframework.rte.ygpa.gam.asset.web;

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

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentDetailVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentLevReqestVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupEntrpsInfoVO;

/**
 * @Class Name : GamAssetRentMngtController.java
 * @Description : 자산임대관리
 * @Modification Information
 *
 * @author heroin
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamAssetRentMngtController {
	
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
    
    @Resource(name = "gamAssetRentService")
    private GamAssetRentService gamAssetRentService;
	
    
    /**
     * 자산임대관리 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/GamAssetRentMngt"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/asset/gamAssetRentMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM019"); //항코드 
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM011"); //신청구분코드 
		List reqstCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM008"); //고지방법 코드
		List nticMthCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("COM077"); //GIS 코드  
		List gisCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM007"); //사용 용도 코드 
		List usagePrposCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM009"); //면제 구분  
		List exemptSeCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM017"); //면제 사유 코드
		List exemptRsnCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM015"); //포장 구분 
		List packSeCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM012"); //업체 구분
		List entrpsSeCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM023"); //사용료 계산 구분
		List feeCalcSeCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("COM998"); //감면 사용료 계산 구분 (확인할것!!)
		List rdcxptFeeCalcSeCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("reqstCdList", reqstCdList);
		model.addAttribute("nticMthCdList", nticMthCdList);
		model.addAttribute("gisCdList", gisCdList);
		model.addAttribute("usagePrposCdList", usagePrposCdList);
		model.addAttribute("exemptSeCdList", exemptSeCdList);
		model.addAttribute("exemptRsnCdList", exemptRsnCdList);
		model.addAttribute("packSeCdList", packSeCdList);
		model.addAttribute("entrpsSeCdList", entrpsSeCdList);
		model.addAttribute("feeCalcSeCdList", feeCalcSeCdList);
		model.addAttribute("rdcxptFeeCalcSeCdList", rdcxptFeeCalcSeCdList);
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/asset/GamAssetRentMngt";
    }
	
	/**
     * 자산임대목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/selectAssetRentList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetRentList(GamAssetRentVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		//자산임대목록
    	totalCnt = gamAssetRentService.selectAssetRentListTotCnt(searchVO);
    	List assetRentList = gamAssetRentService.selectAssetRentList(searchVO);
    	
    	//총면적, 총사용료
    	GamAssetRentVO resultSum = gamAssetRentService.selectAssetRentSum(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	map.put("sumGrAr", resultSum.getSumGrAr());
    	map.put("sumGrFee", resultSum.getSumGrFee());
    	
    	return map;
    }
	
	/**
     * 자산임대상세리스트를 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/selectAssetRentDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetRentDetailList(GamAssetRentVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();
    	
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 자산임대상세리스트 및 총건수
		totalCnt = gamAssetRentService.selectAssetRentDetailListTotCnt(searchVO);
		List resultList = gamAssetRentService.selectAssetRentDetailList(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	/**
     * 자산임대 최초신청을 등록한다.
     * @param String
     * @param gamAssetRentVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/gamInsertAssetRentFirst.do") 
    public @ResponseBody Map insertAssetRentFirst(
    	   @RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamAssetRentVO") GamAssetRentVO gamAssetRentVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
        /*
        String sLocationUrl = null;
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	*/

    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamAssetRentVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamAssetRentVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamAssetRentService.insertAssetRentFirst(gamAssetRentVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	if("insert".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamAssetRentVO.setReqstSeCd("1");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamAssetRentVO.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	gamAssetRentVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	//gamAssetRentVO.setDeptcd("A001");   //부서코드 (세션?) 
	    	
	        gamAssetRentService.insertAssetRentFirst(gamAssetRentVO);
	    	
	        resultCode = 0; // return ok
			resultMsg  = egovMessageSource.getMessage("success.common.insert");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
    	}
		
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    
    /**
     * 자산임대 연장신청을 등록한다.
     * @param gamAssetRentVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/gamInsertAssetRentRenew.do") 
    public @ResponseBody Map insertAssetRentRenew(
    	   @ModelAttribute("gamAssetRentVO") GamAssetRentVO gamAssetRentVO,
   	       BindingResult bindingResult)
           throws Exception {
    	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	GamAssetRentVO resultVO = gamAssetRentService.selectAssetRentMaxNo(gamAssetRentVO);
    	
    	if( gamAssetRentVO.getMngCnt().equals(resultVO.getMaxMngCnt()) ) {
    		//키 같고 max관리번호가 같으면 연장신청 등록
        	
    		gamAssetRentService.insertAssetRentRenew(gamAssetRentVO);
        	
    		resultCode = 0; // return ok
    		resultMsg  = egovMessageSource.getMessage("success.common.insert");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.reject");
    	}
    	
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 자산임대 정보를 수정한다.
     * @param String
     * @param gamAssetRentVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/gamUpdateAssetRent.do") 
    public @ResponseBody Map updateAssetRentFirst(
    	   @RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamAssetRentVO") GamAssetRentVO gamAssetRentVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	if("modify".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamAssetRentVO.setReqstSeCd("3");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamAssetRentVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	//gamAssetRentVO.setDeptcd("A001");   //부서코드 (세션?) 
	    	
	        gamAssetRentService.updateAssetRent(gamAssetRentVO);
	    	
	        resultCode = 0; // return ok
	        resultMsg  = egovMessageSource.getMessage("success.common.update");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
    	}
    	
    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);
        
		return map;
    }
	
    /**
     * 자산임대 정보를 삭제한다.
     * @param String
     * @param gamAssetRentVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/gamDeleteAssetRent.do") 
    public @ResponseBody Map deleteAssetRent(
    	   //@RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamAssetRentDetailVO") GamAssetRentVO gamAssetRentVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg  = "";
        String deleteFlag = "";
        int resultCode = 1;
        
        int resultLevReqestCnt = -1;
        
        if( gamAssetRentVO.getPrmisnYn().equals("N") ) { //허가여부가 'N'이면 삭제가능
        	deleteFlag = "Y";
        } else {
        	resultLevReqestCnt = gamAssetRentService.selectAssetRentLevReqestCnt(gamAssetRentVO); //징수의뢰 정보 카운트
        	
        	if( gamAssetRentVO.getPrmisnYn().equals("Y") && resultLevReqestCnt == 0 ) { //허가여부가 Y이고 징수의뢰테이블에 정보가 없으면 삭제가능
            	deleteFlag = "Y";
            }
        }
    	
    	if("Y".equals(deleteFlag)) {
	        gamAssetRentService.deleteAssetRent(gamAssetRentVO);
	    	
	        resultCode = 0; // return ok
	        resultMsg  = egovMessageSource.getMessage("success.common.delete");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.err.delete");
    	}
		
    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 자산임대 상세를 등록한다.
     * @param String
     * @param gamAssetRentDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/gamInsertAssetRentDetail.do") 
    public @ResponseBody Map insertAssetRentDetail(
    	   @RequestParam("detailCmd") String detailCmd, 
    	   @ModelAttribute("gamAssetRentDetailVO") GamAssetRentDetailVO gamAssetRentDetailVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
        /*
        String sLocationUrl = null;
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	*/

    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamAssetRentVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamAssetRentVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamAssetRentService.insertAssetRentFirst(gamAssetRentVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	if("insert".equals(detailCmd)) {
	    	//확인후 변경혀라~~
	    	gamAssetRentDetailVO.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	gamAssetRentDetailVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	
	        gamAssetRentService.insertAssetRentDetail(gamAssetRentDetailVO);
	    	
	        resultCode = 0; // return ok
			resultMsg  = egovMessageSource.getMessage("success.common.insert");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
    	}
		
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 자산임대 상세를 수정한다.
     * @param String
     * @param gamAssetRentDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/gamUpdateAssetRentDetail.do") 
    public @ResponseBody Map updateAssetRentDetail(
    	   @RequestParam("detailCmd") String detailCmd, 
    	   @ModelAttribute("gamAssetRentDetailVO") GamAssetRentDetailVO gamAssetRentDetailVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
        /*
        String sLocationUrl = null;
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	*/

    	log.debug("######################################## detailCmd => " + detailCmd);
    	log.debug("######################################## gamAssetRentVO.getDetailPrtAtCode() => " + gamAssetRentDetailVO.getDetailPrtAtCode());
    	
    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamAssetRentVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamAssetRentVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamAssetRentService.insertAssetRentFirst(gamAssetRentVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	if("modify".equals(detailCmd)) {
	    	//확인후 변경혀라~~
	    	gamAssetRentDetailVO.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	gamAssetRentDetailVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	
	        gamAssetRentService.updateAssetRentDetail(gamAssetRentDetailVO);
	    	
	        resultCode = 0; // return ok
			resultMsg  = egovMessageSource.getMessage("success.common.update");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
    	}
		
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 자산임대 상세를 삭제한다.
     * @param gamAssetRentDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/gamDeleteAssetRentDetail.do") 
    public @ResponseBody Map deleteAssetRentDetail(
    	   @ModelAttribute("gamAssetRentDetailVO") GamAssetRentDetailVO gamAssetRentDetailVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
        /*
        String sLocationUrl = null;
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	*/

    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamAssetRentVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamAssetRentVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamAssetRentService.insertAssetRentFirst(gamAssetRentVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	gamAssetRentService.deleteAssetRentDetail2(gamAssetRentDetailVO);
    	
        resultCode = 0; // return ok
		resultMsg  = egovMessageSource.getMessage("success.common.delete");
		
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 승낙 팝업화면을 로딩한다. 
     *
     * @param gamAssetRentLevReqestVO
     * @param model the model
     * @return "/ygpa/gam/asset/GamPopupAssetRentPrmisn"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/asset/popup/showAssetRentPrmisn.do")
    String showEntrpsInfo(GamAssetRentLevReqestVO gamAssetRentLevReqestVO, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM024"); //요금종류
		List chrgeKndCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		model.addAttribute("gamAssetRentInfo", gamAssetRentLevReqestVO);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);

    	return "/ygpa/gam/asset/GamPopupAssetRentPrmisn";
    }
    
    /**
     * 자산임대 승낙(허가)을 한다.
     * @param gamAssetRentVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/gamInsertAssetRentPrmisn.do") 
    public @ResponseBody Map insertAssetRentLevReqest(
    	   @ModelAttribute("gamAssetRentVO") GamAssetRentVO gamAssetRentVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
        
        //승낙할 임대정보조회
        GamAssetRentVO rentPrmisnInfo = gamAssetRentService.selectAssetRentPrmisnInfo(gamAssetRentVO);
        
        //징수의뢰 테이블에 갯수 카운트 조회
        int levReqestCnt = gamAssetRentService.selectAssetRentLevReqestCnt(gamAssetRentVO);
        
        if( "Y".equals(rentPrmisnInfo.getPrmisnYn()) ) { 
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject2")); //이미 승낙된 상태입니다.
            
    		return map;
        }
        
        if( levReqestCnt > 0 ) { 
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject3")); //징수의뢰에 정보가 존재하여 승낙을 진행할 수 없습니다.
            
    		return map;
        }
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getNticMth()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject1")); //고지방법코드가 없습니다.
            
    		return map;
        }
        
        if( !"1".equals(rentPrmisnInfo.getNticMth()) && !"2".equals(rentPrmisnInfo.getNticMth()) && !"3".equals(rentPrmisnInfo.getNticMth()) && !"4".equals(rentPrmisnInfo.getNticMth()) && !"5".equals(rentPrmisnInfo.getNticMth())) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject5")); // 고지방법코드가 올바르지 않습니다. ('1':일괄, '2':반기납, '3':3분납, '4':분기납, '5':월납)
            
    		return map;
        }
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getGrUsagePdFrom()) || EgovStringUtil.isEmpty(rentPrmisnInfo.getGrUsagePdTo()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject4")); //총사용기간 일자가 없습니다.
            
    		return map;
        }
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getGrFee()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject6")); //총사용료가 없습니다.
            
    		return map;
        }
        
        GamAssetRentLevReqestVO levReqestInfo = new GamAssetRentLevReqestVO();
        levReqestInfo.setPrtAtCode( rentPrmisnInfo.getPrtAtCode() );
        levReqestInfo.setMngYear( rentPrmisnInfo.getMngYear() );
        levReqestInfo.setMngNo( rentPrmisnInfo.getMngNo() );
        levReqestInfo.setMngCnt( rentPrmisnInfo.getMngCnt() );
        levReqestInfo.setEntrpscd( rentPrmisnInfo.getEntrpscd() );
        levReqestInfo.setEntrpsNm( rentPrmisnInfo.getEntrpsNm() );
        levReqestInfo.setRm( rentPrmisnInfo.getRm() );
        levReqestInfo.setNticMth( rentPrmisnInfo.getNticMth() );
        levReqestInfo.setGrFee( rentPrmisnInfo.getGrFee() );
        levReqestInfo.setGrUsagePdFrom( rentPrmisnInfo.getGrUsagePdFrom() ); //총사용기간 FROM
        levReqestInfo.setGrUsagePdTo( rentPrmisnInfo.getGrUsagePdTo() ); //총사용기간 TO
        levReqestInfo.setReqstSeCd( rentPrmisnInfo.getReqstSeCd() );
        
		levReqestInfo.setChrgeKnd( gamAssetRentVO.getChrgeKnd() );
		levReqestInfo.setVatYn( gamAssetRentVO.getVatYn() );
		
        levReqestInfo.setPrmisnYn("Y"); //허가여부
        levReqestInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
        levReqestInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
        
        //임대정보의 허가여부를 Y로 업데이트 및 징수의뢰 insert
        gamAssetRentService.updateAssetRentPrmisn(levReqestInfo);
        
        resultCode = 0; 
		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.exec"); //승낙이 정상적으로 처리되었습니다.
        
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 자산임대 승낙취소(허가취소)를 한다.
     * @param gamAssetRentVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/gamUpdateAssetRentPrmisnCancel.do") 
    public @ResponseBody Map updateAssetRentPrmisnCancel(
     	   @ModelAttribute("gamAssetRentVO") GamAssetRentVO gamAssetRentVO, 
     	   BindingResult bindingResult)
            throws Exception {
 	
     	Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
         
         //승낙할 임대정보조회
         GamAssetRentVO rentPrmisnInfo = gamAssetRentService.selectAssetRentPrmisnInfo(gamAssetRentVO);
         
         //징수의뢰 테이블에 갯수 카운트 조회
         int levReqestCnt = gamAssetRentService.selectAssetRentLevReqestCnt(gamAssetRentVO);
         
         if( !"Y".equals(rentPrmisnInfo.getPrmisnYn()) ) { 
         	map.put("resultCode", 1);
             map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject7")); //승낙된 상태가 아닙니다.
             
     		return map;
         }
         
         if( levReqestCnt > 0 ) { 
         	map.put("resultCode", 1);
             map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject8")); //징수의뢰에 정보가 존재하여 승낙을 취소 할 수 없습니다.
             
     		return map;
         }
         
         GamAssetRentLevReqestVO levReqestInfo = new GamAssetRentLevReqestVO();
         levReqestInfo.setPrtAtCode( rentPrmisnInfo.getPrtAtCode() );
         levReqestInfo.setMngYear( rentPrmisnInfo.getMngYear() );
         levReqestInfo.setMngNo( rentPrmisnInfo.getMngNo() );
         levReqestInfo.setMngCnt( rentPrmisnInfo.getMngCnt() );
 		
         levReqestInfo.setPrmisnYn("N"); //허가여부
         levReqestInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
         levReqestInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
         
         //임대정보의 허가여부를 N으로 업데이트
         gamAssetRentService.updateAssetRentPrmisnCancel(levReqestInfo);
         
         resultCode = 0; 
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.execCancel"); //승낙이 정상적으로 취소되었습니다.
         
     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);
         
 		return map;
     }
    
}
