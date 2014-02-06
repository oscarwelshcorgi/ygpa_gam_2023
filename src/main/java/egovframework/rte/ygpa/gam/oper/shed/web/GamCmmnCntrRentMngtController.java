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

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtVO;

/**
 * @Class Name : GamCmmnCntrRentMngtController.java
 * @Description : 공컨장치장임대목록관리 (공컨장치장/공컨장치장/공컨장치장임대목록관리)
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
public class GamCmmnCntrRentMngtController {

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
    
    @Resource(name = "gamCmmnCntrRentMngtService")
    private GamCmmnCntrRentMngtService gamCmmnCntrRentMngtService;
	
    
    /**
     * 공컨장치장사용목록관리 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/shed/GamCmmnCntrRentMngt"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/oper/shed/gamCmmnCntrRentMngt.do")
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
		
		codeVo.setCodeId("GAM043"); //납부 방법 코드
		List payMthCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM003"); //부두코드
		List quayCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
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
		model.addAttribute("payMthCdList", payMthCdList);
		model.addAttribute("quayCdList", quayCdList);
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/oper/shed/GamCmmnCntrRentMngt";
    }
	
	/**
     * 공컨장치장사용목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/shed/selectCmmnCntrRentMngtList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectCmmnCntrRentMngtList(GamCmmnCntrRentMngtVO searchVO) throws Exception {

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
		
		//공컨장치장사용목록
    	totalCnt = gamCmmnCntrRentMngtService.selectCmmnCntrRentMngtListTotCnt(searchVO);
    	List resultList = gamCmmnCntrRentMngtService.selectCmmnCntrRentMngtList(searchVO);
    	
    	//총면적, 총사용료
    	GamCmmnCntrRentMngtVO resultSum = gamCmmnCntrRentMngtService.selectCmmnCntrRentMngtSum(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	map.put("sumGrAr", resultSum.getSumGrAr());
    	map.put("sumGrFee", resultSum.getSumGrFee());
    	
    	return map;
    }
	
	/**
     * 공컨장치장사용목록상세리스트를 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/shed/selectCmmnCntrRentMngtDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectCmmnCntrRentMngtDetailList(GamCmmnCntrRentMngtVO searchVO) throws Exception {

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

		// 공컨장치장상세리스트 및 총건수
		totalCnt = gamCmmnCntrRentMngtService.selectCmmnCntrRentMngtDetailListTotCnt(searchVO);
		List resultList = gamCmmnCntrRentMngtService.selectCmmnCntrRentMngtDetailList(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	/**
     * 공컨장치장 최초신청을 등록한다.
     * @param String
     * @param gamCmmnCntrRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamInsertCmmnCntrRentMngtFirst.do") 
    public @ResponseBody Map insertCmmnCntrRentMngtFirst(
    	   @RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamCmmnCntrRentMngtVO") GamCmmnCntrRentMngtVO gamCmmnCntrRentMngtVO, 
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
	        beanValidator.validate(gamCmmnCntrRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamCmmnCntrRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamCmmnCntrRentMngtService.insertCmmnCntrRentMngtFirst(gamCmmnCntrRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	if("insert".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamCmmnCntrRentMngtVO.setReqstSeCd("1");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamCmmnCntrRentMngtVO.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	gamCmmnCntrRentMngtVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	//gamCmmnCntrRentMngtVO.setDeptcd("A001");   //부서코드 (세션?) 
	    	
	        gamCmmnCntrRentMngtService.insertCmmnCntrRentMngtFirst(gamCmmnCntrRentMngtVO);
	    	
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
     * 공컨장치장 연장신청을 등록한다.
     * @param gamCmmnCntrRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamInsertCmmnCntrRentMngtRenew.do") 
    public @ResponseBody Map insertCmmnCntrRentMngtRenew(
    	   @ModelAttribute("gamCmmnCntrRentMngtVO") GamCmmnCntrRentMngtVO gamCmmnCntrRentMngtVO,
   	       BindingResult bindingResult)
           throws Exception {
    	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	GamCmmnCntrRentMngtVO resultVO = gamCmmnCntrRentMngtService.selectCmmnCntrRentMngtMaxNo(gamCmmnCntrRentMngtVO);
    	
    	if( gamCmmnCntrRentMngtVO.getMngCnt().equals(resultVO.getMaxMngCnt()) ) {
    		//키 같고 max관리번호가 같으면 연장신청 등록
        	
    		gamCmmnCntrRentMngtService.insertCmmnCntrRentMngtRenew(gamCmmnCntrRentMngtVO);
        	
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
     * 공컨장치장 정보를 수정한다.
     * @param String
     * @param gamCmmnCntrRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamUpdateCmmnCntrRentMngt.do") 
    public @ResponseBody Map updateCmmnCntrRentMngtFirst(
    	   @RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamCmmnCntrRentMngtVO") GamCmmnCntrRentMngtVO gamCmmnCntrRentMngtVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	if("modify".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamCmmnCntrRentMngtVO.setReqstSeCd("3");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamCmmnCntrRentMngtVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	//gamCmmnCntrRentMngtVO.setDeptcd("A001");   //부서코드 (세션?) 
	    	
	        gamCmmnCntrRentMngtService.updateCmmnCntrRentMngt(gamCmmnCntrRentMngtVO);
	    	
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
     * 공컨장치장  정보를 삭제한다.
     * @param String
     * @param gamCmmnCntrRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamDeleteCmmnCntrRentMngt.do") 
    public @ResponseBody Map deleteCmmnCntrRentMngt(
    	   //@RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamCmmnCntrRentMngtDetailVO") GamCmmnCntrRentMngtVO gamCmmnCntrRentMngtVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg  = "";
        String deleteFlag = "";
        int resultCode = 1;
        
        int resultLevReqestCnt = -1;
        
        if( gamCmmnCntrRentMngtVO.getPrmisnYn().equals("N") ) { //허가여부가 'N'이면 삭제가능
        	deleteFlag = "Y";
        } else {
        	resultLevReqestCnt = gamCmmnCntrRentMngtService.selectCmmnCntrRentMngtLevReqestCnt(gamCmmnCntrRentMngtVO); //징수의뢰 정보 카운트
        	
        	if( gamCmmnCntrRentMngtVO.getPrmisnYn().equals("Y") && resultLevReqestCnt == 0 ) { //허가여부가 Y이고 징수의뢰테이블에 정보가 없으면 삭제가능
            	deleteFlag = "Y";
            }
        }
    	
    	if("Y".equals(deleteFlag)) {
	        gamCmmnCntrRentMngtService.deleteCmmnCntrRentMngt(gamCmmnCntrRentMngtVO);
	    	
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
     * 공컨장치장사용 상세를 등록한다.
     * @param String
     * @param gamCmmnCntrRentMngtDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamInsertCmmnCntrRentMngtDetail.do") 
    public @ResponseBody Map insertCmmnCntrRentMngtDetail(
    	   @RequestParam("detailCmd") String detailCmd, 
    	   @ModelAttribute("gamCmmnCntrRentMngtDetailVO") GamCmmnCntrRentMngtDetailVO gamCmmnCntrRentMngtDetailVO, 
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
	        beanValidator.validate(gamCmmnCntrRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamCmmnCntrRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamCmmnCntrRentMngtService.insertCmmnCntrRentMngtFirst(gamCmmnCntrRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
        GamCmmnCntrRentMngtVO gamCmmnCntrRentMngtVO = new GamCmmnCntrRentMngtVO();
        gamCmmnCntrRentMngtVO.setPrtAtCode(gamCmmnCntrRentMngtDetailVO.getDetailPrtAtCode());
        gamCmmnCntrRentMngtVO.setMngYear(gamCmmnCntrRentMngtDetailVO.getDetailMngYear());
        gamCmmnCntrRentMngtVO.setMngNo(gamCmmnCntrRentMngtDetailVO.getDetailMngNo());
        gamCmmnCntrRentMngtVO.setMngCnt(gamCmmnCntrRentMngtDetailVO.getDetailMngCnt());
        
        //임대정보 조회후 승낙여부 체크
        GamCmmnCntrRentMngtVO rentPrmisnInfo = gamCmmnCntrRentMngtService.selectCmmnCntrRentMngtPrmisnInfo(gamCmmnCntrRentMngtVO);
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 등록가능
        	if("insert".equals(detailCmd)) {
    	    	//확인후 변경혀라~~
    	    	gamCmmnCntrRentMngtDetailVO.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
    	    	gamCmmnCntrRentMngtDetailVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
    	    	
    	        gamCmmnCntrRentMngtService.insertCmmnCntrRentMngtDetail(gamCmmnCntrRentMngtDetailVO);
    	    	
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
     * 공컨장치장 상세를 수정한다.
     * @param String
     * @param gamCmmnCntrRentMngtDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamUpdateCmmnCntrRentMngtDetail.do") 
    public @ResponseBody Map updateCmmnCntrRentMngtDetail(
    	   @RequestParam("detailCmd") String detailCmd, 
    	   @ModelAttribute("gamCmmnCntrRentMngtDetailVO") GamCmmnCntrRentMngtDetailVO gamCmmnCntrRentMngtDetailVO, 
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
    	log.debug("######################################## gamCmmnCntrRentMngtVO.getDetailPrtAtCode() => " + gamCmmnCntrRentMngtDetailVO.getDetailPrtAtCode());
    	
    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamCmmnCntrRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamCmmnCntrRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamCmmnCntrRentMngtService.insertCmmnCntrRentMngtFirst(gamCmmnCntrRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	GamCmmnCntrRentMngtVO gamCmmnCntrRentMngtVO = new GamCmmnCntrRentMngtVO();
        gamCmmnCntrRentMngtVO.setPrtAtCode(gamCmmnCntrRentMngtDetailVO.getDetailPrtAtCode());
        gamCmmnCntrRentMngtVO.setMngYear(gamCmmnCntrRentMngtDetailVO.getDetailMngYear());
        gamCmmnCntrRentMngtVO.setMngNo(gamCmmnCntrRentMngtDetailVO.getDetailMngNo());
        gamCmmnCntrRentMngtVO.setMngCnt(gamCmmnCntrRentMngtDetailVO.getDetailMngCnt());
        
        //임대정보 조회후 승낙여부 체크
        GamCmmnCntrRentMngtVO rentPrmisnInfo = gamCmmnCntrRentMngtService.selectCmmnCntrRentMngtPrmisnInfo(gamCmmnCntrRentMngtVO);
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 수정가능
	    	if("modify".equals(detailCmd)) {
		    	gamCmmnCntrRentMngtDetailVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
		    	
		        gamCmmnCntrRentMngtService.updateCmmnCntrRentMngtDetail(gamCmmnCntrRentMngtDetailVO);
		    	
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
     * 공컨장치장 상세를 삭제한다.
     * @param gamCmmnCntrRentMngtDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamDeleteCmmnCntrRentMngtDetail.do") 
    public @ResponseBody Map deleteCmmnCntrRentMngtDetail(
    	   @ModelAttribute("gamCmmnCntrRentMngtDetailVO") GamCmmnCntrRentMngtDetailVO gamCmmnCntrRentMngtDetailVO, 
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
	        beanValidator.validate(gamCmmnCntrRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamCmmnCntrRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamCmmnCntrRentMngtService.insertCmmnCntrRentMngtFirst(gamCmmnCntrRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	gamCmmnCntrRentMngtService.deleteCmmnCntrRentMngtDetail2(gamCmmnCntrRentMngtDetailVO);
    	
        resultCode = 0; // return ok
		resultMsg  = egovMessageSource.getMessage("success.common.delete");
		
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 승낙 팝업화면을 로딩한다. 
     *
     * @param gamCmmnCntrRentMngtLevReqestVO
     * @param model the model
     * @return "/ygpa/gam/oper/shed/GamPopupCmmnCntrRentMngtPrmisn"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/oper/shed/popup/showCmmnCntrRentMngtPrmisn.do")
    String showEntrpsInfo(GamCmmnCntrRentMngtLevReqestVO gamPrtFcltyRentLevReqestVO, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM024"); //요금종류
		List chrgeKndCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		model.addAttribute("gamCmmnCntrRentMngtInfo", gamPrtFcltyRentLevReqestVO);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);

    	return "/ygpa/gam/oper/shed/GamPopupCmmnCntrRentMngtPrmisn";
    }
    
    /**
     * 공컨장치장 승낙(허가)을 한다.
     * @param gamCmmnCntrRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/popup/gamInsertCmmnCntrRentMngtPrmisn.do") 
    public @ResponseBody Map insertPrtFcltyRentLevReqest(
    	   @ModelAttribute("gamCmmnCntrRentMngtVO") GamCmmnCntrRentMngtVO gamCmmnCntrRentMngtVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
        
        //승낙할 임대정보조회
        GamCmmnCntrRentMngtVO rentPrmisnInfo = gamCmmnCntrRentMngtService.selectCmmnCntrRentMngtPrmisnInfo(gamCmmnCntrRentMngtVO);
        
        //징수의뢰 테이블에 갯수 카운트 조회
        int levReqestCnt = gamCmmnCntrRentMngtService.selectCmmnCntrRentMngtLevReqestCnt(gamCmmnCntrRentMngtVO);
        
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
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject7")); //납부방법 코드가 없습니다.
            
    		return map;
        }
        
        if( !"Pre".equals( rentPrmisnInfo.getPayMth() ) && !"Aft".equals( rentPrmisnInfo.getPayMth() ) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject9")); //납부방법 코드가 올바르지 않습니다.
            
    		return map;
        }
        
        GamCmmnCntrRentMngtLevReqestVO levReqestInfo = new GamCmmnCntrRentMngtLevReqestVO();
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
		levReqestInfo.setChrgeKnd( gamCmmnCntrRentMngtVO.getChrgeKnd() );
		levReqestInfo.setVatYn( gamCmmnCntrRentMngtVO.getVatYn() );
		levReqestInfo.setPayMth( rentPrmisnInfo.getPayMth() );
        
        levReqestInfo.setPrmisnYn("Y"); //허가여부
        levReqestInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
        levReqestInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
        
        //임대정보의 허가여부를 Y로 업데이트 및 징수의뢰 insert
        gamCmmnCntrRentMngtService.updateCmmnCntrRentMngtPrmisn(levReqestInfo);
        
        resultCode = 0; 
		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.exec"); //승낙이 정상적으로 처리되었습니다.
        
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 공컨장치장 승낙취소(허가취소)를 한다.
     * @param gamCmmnCntrRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamUpdateCmmnCntrRentMngtPrmisnCancel.do") 
    public @ResponseBody Map updateCmmnCntrRentMngtPrmisnCancel(
     	   @ModelAttribute("gamCmmnCntrRentMngtVO") GamCmmnCntrRentMngtVO gamCmmnCntrRentMngtVO, 
     	   BindingResult bindingResult)
            throws Exception {
 	
     	Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
         
         //승낙할 임대정보조회
         GamCmmnCntrRentMngtVO rentPrmisnInfo = gamCmmnCntrRentMngtService.selectCmmnCntrRentMngtPrmisnInfo(gamCmmnCntrRentMngtVO);
         
         //징수의뢰 테이블에 갯수 카운트 조회
         int levReqestCnt = gamCmmnCntrRentMngtService.selectCmmnCntrRentMngtLevReqestCnt(gamCmmnCntrRentMngtVO);
         
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
         
         GamCmmnCntrRentMngtLevReqestVO levReqestInfo = new GamCmmnCntrRentMngtLevReqestVO();
         levReqestInfo.setPrtAtCode( rentPrmisnInfo.getPrtAtCode() );
         levReqestInfo.setMngYear( rentPrmisnInfo.getMngYear() );
         levReqestInfo.setMngNo( rentPrmisnInfo.getMngNo() );
         levReqestInfo.setMngCnt( rentPrmisnInfo.getMngCnt() );
 		
         levReqestInfo.setPrmisnYn("N"); //허가여부
         levReqestInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
         levReqestInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
         
         //임대정보의 허가여부를 N으로 업데이트
         gamCmmnCntrRentMngtService.updateCmmnCntrRentMngtPrmisnCancel(levReqestInfo);
         
         resultCode = 0; 
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.execCancel"); //승낙이 정상적으로 취소되었습니다.
         
     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);
         
 		return map;
     }
}
