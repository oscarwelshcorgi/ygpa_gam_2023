package egovframework.rte.ygpa.gam.asset.rent.web;

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
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentDetailVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentLevReqestVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO;

/**
 * @Class Name : GamAssetRentMngtController.java
 * @Description : 자산임대관리
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
    
    @Resource(name = "gamAssetRentMngtService")
    private GamAssetRentMngtService gamAssetRentMngtService;
	
    
    /**
     * 자산임대관리 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/rent/GamAssetRentMngt"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/asset/rent/gamAssetRentMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		//공시지가정보
		GamAssetRentMngtVO gvo = new GamAssetRentMngtVO();
		List olnlpList = gamAssetRentMngtService.selectOlnlpInfo();
		
		model.addAttribute("olnlpList", olnlpList);
		model.addAttribute("loginOrgnztId", loginVO.getOrgnztId());
		model.addAttribute("loginUserId", loginVO.getId());
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/asset/rent/GamAssetRentMngt";
    }
	
	/**
     * 자산임대목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/rent/gamSelectAssetRentList.do", method=RequestMethod.POST)
	@ResponseBody Map selectAssetRentList(GamAssetRentMngtVO searchVO) throws Exception {
		
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
    	totalCnt = gamAssetRentMngtService.selectAssetRentListTotCnt(searchVO);
    	List assetRentList = gamAssetRentMngtService.selectAssetRentList(searchVO);
    	
    	//총면적, 총사용료
    	GamAssetRentMngtVO resultSum = gamAssetRentMngtService.selectAssetRentSum(searchVO);
    	
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
    @RequestMapping(value="/asset/rent/gamSelectAssetRentDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetRentDetailList(GamAssetRentMngtVO searchVO) throws Exception {

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
		totalCnt = gamAssetRentMngtService.selectAssetRentDetailListTotCnt(searchVO);
		List resultList = gamAssetRentMngtService.selectAssetRentDetailList(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	/**
     * 자산임대 최초신청을 등록한다.
     * @param String
     * @param gamAssetRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamInsertAssetRentFirst.do") 
    public @ResponseBody Map insertAssetRentFirst(
    	   @RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamAssetRentMngtVO") GamAssetRentMngtVO gamAssetRentMngtVO, 
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
	        beanValidator.validate(gamAssetRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamAssetRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamAssetRentMngtService.insertAssetRentFirst(gamAssetRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	if("insert".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamAssetRentMngtVO.setReqstSeCd("1");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamAssetRentMngtVO.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	gamAssetRentMngtVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	//gamAssetRentMngtVO.setDeptcd("A001");   //부서코드 (세션?) 
	    	
	        gamAssetRentMngtService.insertAssetRentFirst(gamAssetRentMngtVO);
	    	
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
     * @param gamAssetRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamInsertAssetRentRenew.do") 
    public @ResponseBody Map insertAssetRentRenew(
    	   @ModelAttribute("gamAssetRentMngtVO") GamAssetRentMngtVO gamAssetRentMngtVO,
   	       BindingResult bindingResult)
           throws Exception {
    	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	GamAssetRentMngtVO resultVO = gamAssetRentMngtService.selectAssetRentMaxNo(gamAssetRentMngtVO);
    	
    	if( gamAssetRentMngtVO.getMngCnt().equals(resultVO.getMaxMngCnt()) ) {
    		//키 같고 max관리번호가 같으면 연장신청 등록
        	
    		gamAssetRentMngtService.insertAssetRentRenew(gamAssetRentMngtVO);
    		
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
     * @param gamAssetRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamUpdateAssetRent.do") 
    public @ResponseBody Map updateAssetRentFirst(
    	   @RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamAssetRentMngtVO") GamAssetRentMngtVO gamAssetRentMngtVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	if("modify".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamAssetRentMngtVO.setReqstSeCd("3");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamAssetRentMngtVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	//gamAssetRentMngtVO.setDeptcd("A001");   //부서코드 (세션?) 
	    	
	        gamAssetRentMngtService.updateAssetRent(gamAssetRentMngtVO);
	    	
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
     * @param gamAssetRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamDeleteAssetRent.do") 
    public @ResponseBody Map deleteAssetRent(
    	   //@RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamAssetRentDetailVO") GamAssetRentMngtVO gamAssetRentMngtVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg  = "";
        String deleteFlag = "";
        int resultCode = 1;
        
        int resultLevReqestCnt = -1;
        
        if( EgovStringUtil.isEmpty(gamAssetRentMngtVO.getPrmisnYn()) || gamAssetRentMngtVO.getPrmisnYn().equals("N") ) { //허가여부가 'N'이면 삭제가능
        	deleteFlag = "Y";
        } else {
        	resultLevReqestCnt = gamAssetRentMngtService.selectAssetRentLevReqestCnt(gamAssetRentMngtVO); //징수의뢰 정보 카운트
        	
        	if( gamAssetRentMngtVO.getPrmisnYn().equals("Y") && resultLevReqestCnt == 0 ) { //허가여부가 Y이고 징수의뢰테이블에 정보가 없으면 삭제가능
            	deleteFlag = "Y";
            }
        }
    	
    	if("Y".equals(deleteFlag)) {
	        gamAssetRentMngtService.deleteAssetRent(gamAssetRentMngtVO);
	    	
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
    @RequestMapping(value="/asset/rent/gamInsertAssetRentDetail.do") 
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
	        beanValidator.validate(gamAssetRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamAssetRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamAssetRentMngtService.insertAssetRentFirst(gamAssetRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
        GamAssetRentMngtVO gamAssetRentMngtVO = new GamAssetRentMngtVO();
        gamAssetRentMngtVO.setPrtAtCode(gamAssetRentDetailVO.getDetailPrtAtCode());
        gamAssetRentMngtVO.setMngYear(gamAssetRentDetailVO.getDetailMngYear());
        gamAssetRentMngtVO.setMngNo(gamAssetRentDetailVO.getDetailMngNo());
        gamAssetRentMngtVO.setMngCnt(gamAssetRentDetailVO.getDetailMngCnt());
        
        //임대정보 조회후 승낙여부 체크
        GamAssetRentMngtVO rentPrmisnInfo = gamAssetRentMngtService.selectAssetRentPrmisnInfo(gamAssetRentMngtVO);
        
        
        
        log.debug("######################################## rentPrmisnInfo.getPrmisnYn() => " + rentPrmisnInfo.getPrmisnYn());
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 등록가능
        	if("insert".equals(detailCmd)) {
    	    	//확인후 변경혀라~~
    	    	gamAssetRentDetailVO.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
    	    	gamAssetRentDetailVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
    	    	
    	        gamAssetRentMngtService.insertAssetRentDetail(gamAssetRentDetailVO);
    	    	
    	        resultCode = 0; // return ok
    			resultMsg  = egovMessageSource.getMessage("success.common.insert");
        	} else {
        		resultCode = 1; // return fail
        		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
        	}
        } else {
        	resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.detailModify.reject");
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
    @RequestMapping(value="/asset/rent/gamUpdateAssetRentDetail.do") 
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
    	log.debug("######################################## gamAssetRentMngtVO.getDetailPrtAtCode() => " + gamAssetRentDetailVO.getDetailPrtAtCode());
    	
    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamAssetRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamAssetRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamAssetRentMngtService.insertAssetRentFirst(gamAssetRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	GamAssetRentMngtVO gamAssetRentMngtVO = new GamAssetRentMngtVO();
        gamAssetRentMngtVO.setPrtAtCode(gamAssetRentDetailVO.getDetailPrtAtCode());
        gamAssetRentMngtVO.setMngYear(gamAssetRentDetailVO.getDetailMngYear());
        gamAssetRentMngtVO.setMngNo(gamAssetRentDetailVO.getDetailMngNo());
        gamAssetRentMngtVO.setMngCnt(gamAssetRentDetailVO.getDetailMngCnt());
        
        //임대정보 조회후 승낙여부 체크
        GamAssetRentMngtVO rentPrmisnInfo = gamAssetRentMngtService.selectAssetRentPrmisnInfo(gamAssetRentMngtVO);
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 수정가능
	    	if("modify".equals(detailCmd)) {
		    	gamAssetRentDetailVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
		    	
		        gamAssetRentMngtService.updateAssetRentDetail(gamAssetRentDetailVO);
		    	
		        resultCode = 0; // return ok
				resultMsg  = egovMessageSource.getMessage("success.common.update");
	    	} else {
	    		resultCode = 1; // return fail
	    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
	    	}
        } else {
        	resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.detailModify.reject");
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
    @RequestMapping(value="/asset/rent/gamDeleteAssetRentDetail.do") 
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
	        beanValidator.validate(gamAssetRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamAssetRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamAssetRentMngtService.insertAssetRentFirst(gamAssetRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	gamAssetRentMngtService.deleteAssetRentDetail2(gamAssetRentDetailVO);
    	
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
     * @return "/ygpa/gam/asset/rent/GamPopupAssetRentPrmisn"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/asset/rent/popup/showAssetRentPrmisn.do") 
    String showEntrpsInfo(GamAssetRentLevReqestVO gamAssetRentLevReqestVO, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM024"); //요금종류
		List chrgeKndCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		model.addAttribute("gamAssetRentInfo", gamAssetRentLevReqestVO);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);

    	return "/ygpa/gam/asset/rent/GamPopupAssetRentPrmisn";
    }
    
    /**
     * 자산임대 승낙(허가)을 한다.
     * @param gamAssetRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamInsertAssetRentPrmisn.do") 
    public @ResponseBody Map insertAssetRentLevReqest(
    	   @ModelAttribute("gamAssetRentMngtVO") GamAssetRentMngtVO gamAssetRentMngtVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
        
        //승낙할 임대정보조회
        GamAssetRentMngtVO rentPrmisnInfo = gamAssetRentMngtService.selectAssetRentPrmisnInfo(gamAssetRentMngtVO);
        
        //징수의뢰 테이블에 갯수 카운트 조회
        int levReqestCnt = gamAssetRentMngtService.selectAssetRentLevReqestCnt(gamAssetRentMngtVO);
        
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
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPayMth()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject10")); //납부방법 코드가 없습니다.
            
    		return map;
        }
        
        if( !"Pre".equals( rentPrmisnInfo.getPayMth() ) && !"Aft".equals( rentPrmisnInfo.getPayMth() ) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject9")); //납부방법 코드가 올바르지 않습니다.
            
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
		levReqestInfo.setChrgeKnd( gamAssetRentMngtVO.getChrgeKnd() );
		levReqestInfo.setVatYn( gamAssetRentMngtVO.getVatYn() );
		levReqestInfo.setPayMth( rentPrmisnInfo.getPayMth() );
		
        levReqestInfo.setPrmisnYn("Y"); //허가여부
        levReqestInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
        levReqestInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
        
        //임대정보의 허가여부를 Y로 업데이트 및 징수의뢰 insert
        gamAssetRentMngtService.updateAssetRentPrmisn(levReqestInfo);
        
        resultCode = 0; 
		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.exec"); //승낙이 정상적으로 처리되었습니다.
        
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 자산임대 승낙취소(허가취소)를 한다.
     * @param gamAssetRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamUpdateAssetRentPrmisnCancel.do") 
    public @ResponseBody Map updateAssetRentPrmisnCancel(
     	   @ModelAttribute("gamAssetRentMngtVO") GamAssetRentMngtVO gamAssetRentMngtVO, 
     	   BindingResult bindingResult)
            throws Exception {
 	
     	Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
         
         //승낙할 임대정보조회
         GamAssetRentMngtVO rentPrmisnInfo = gamAssetRentMngtService.selectAssetRentPrmisnInfo(gamAssetRentMngtVO);
         
         //징수의뢰 테이블에 갯수 카운트 조회
         int levReqestCnt = gamAssetRentMngtService.selectAssetRentLevReqestCnt(gamAssetRentMngtVO);
         
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
         gamAssetRentMngtService.updateAssetRentPrmisnCancel(levReqestInfo);
         
         resultCode = 0; 
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.execCancel"); //승낙이 정상적으로 취소되었습니다.
         
     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);
         
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
    @RequestMapping(value="/asset/rent/gamSelectAssetRentFileList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetRentFileList(GamAssetRentMngtVO searchVO) throws Exception {

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
    	totalCnt = gamAssetRentMngtService.selectAssetRentFileListTotCnt(searchVO);
    	List assetFileList = gamAssetRentMngtService.selectAssetRentFileList(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("assetFileList", assetFileList);
    	
    	return map;
    }
    
}
