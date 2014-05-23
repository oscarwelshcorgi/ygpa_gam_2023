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

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtVO;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentFeeMngtVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeMngtVO;

/**
 * @Class Name : GamMarineCenterRentFeeMngtController.java
 * @Description : 마린센터임대료관리
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
public class GamMarineCenterRentFeeMngtController {
	
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
    
    @Resource(name = "gamMarineCenterRentFeeMngtService")
    private GamMarineCenterRentFeeMngtService gamMarineCenterRentFeeMngtService;
	
    @Resource(name = "gamNticRequestMngtService")
    private GamNticRequestMngtService gamNticRequestMngtService;
    /**
     * 마린센터임대료관리 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/GamMarineCenterRentFeeMngt"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/oper/center/gamMarineCenterRentFeeMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
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
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("reqstCdList", reqstCdList);
		model.addAttribute("nticMthCdList", nticMthCdList);
		model.addAttribute("usagePrposCdList", usagePrposCdList);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);
		model.addAttribute("fcltySeCdList", fcltySeCdList);
		model.addAttribute("rcivSeCdList", rcivSeCdList);
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/oper/center/GamMarineCenterRentFeeMngt";
    }
	
	/**
     * 마린센터임대료관리 목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/center/gamSelectMarineCenterRentFeeMngtList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectMarineCenterRentFeeMngtList(GamMarineCenterRentFeeMngtVO searchVO) throws Exception {

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
		
		//자산임대목록
    	totalCnt = gamMarineCenterRentFeeMngtService.selectMarineCenterRentFeeListTotCnt(searchVO);
    	List resultList = gamMarineCenterRentFeeMngtService.selectMarineCenterRentFeeList(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	//자료수, 사용료, 연체, 부가세, 고지액
    	GamMarineCenterRentFeeMngtVO resultSum = gamMarineCenterRentFeeMngtService.selectMarineCenterRentFeeSum(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	map.put("sumFee", resultSum.getSumFee());
    	map.put("sumArrrgAmt", resultSum.getSumArrrgAmt());
    	map.put("sumVat", resultSum.getSumVat());
    	map.put("sumNticAmt", resultSum.getSumNticAmt());
    	map.put("sumNhtIsueAmt", resultSum.getSumNhtIsueAmt());
    	
    	return map;
    }
	
	/**
     * 마린센터임대료관리정보를 수정한다.
     * @param gamMarineCenterRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/center/gamUpdateMarineCenterRentFee.do") 
    public @ResponseBody Map updateMarineCenterRentFee(
     	   @ModelAttribute("gamMarineCenterRentFeeMngtVO") GamMarineCenterRentFeeMngtVO gamMarineCenterRentFeeMngtVO, 
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
     	
         gamMarineCenterRentFeeMngtVO.setUpdUsr(loginVo.getId()); //수정자 (세션 로그인 아이디)
         
         gamMarineCenterRentFeeMngtService.updateMarineCenterRentFee(gamMarineCenterRentFeeMngtVO);
         
         resultCode = 0; 
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
         
     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);
         
 		return map;
     }

    /**
     * 고지의뢰를 한다. (다중처리)
     * @param gamMarineCenterRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    /*
    @RequestMapping(value="/oper/center/gamInsertMarineCenterRentFeeNtic.do") 
    @ResponseBody Map<String, Object> insertMarineCenterRentFeeNtic (
				  @RequestParam("nticCnts") String nticCnts
				 ,@RequestParam("prtAtCodes") String prtAtCodes
				 ,@RequestParam("mngYears") String mngYears
				 ,@RequestParam("mngNos") String mngNos
				 ,@RequestParam("mngCnts") String mngCnts
				 ,@RequestParam("nticnos") String nticnos
				 ,@RequestParam("accnutYears") String accnutYears
				 ,@ModelAttribute("gamMarineCenterRentFeeMngtVO") GamMarineCenterRentFeeMngtVO gamMarineCenterRentFeeMngtVO
    		     ) throws Exception {
 	
        Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        int anlrveLevCnt = 0;
        
        System.out.println("############ prtAtCodes => " + prtAtCodes);
        
        String [] arrNticCnts = nticCnts.split(";");
     	String [] arrPrtAtCodes = prtAtCodes.split(";");
     	String [] arrMngYears = mngYears.split(";");
     	String [] arrMngNos = mngNos.split(";");
     	String [] arrMngCnts = mngCnts.split(";");
     	String [] arrNticnos = nticnos.split(";");
     	String [] arrAccnutYears = accnutYears.split(";");
        
 		//세입징수에 등록하기전 validation 체크
     	for(int i=0; i<arrNticCnts.length;i++) {
     		gamMarineCenterRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamMarineCenterRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamMarineCenterRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamMarineCenterRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamMarineCenterRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamMarineCenterRentFeeMngtVO.setNticno(arrNticnos[i]);
     		gamMarineCenterRentFeeMngtVO.setAccnutYear(arrAccnutYears[i]);
     		
     		//세입징수에 기등록  여부
     		anlrveLevCnt = gamMarineCenterRentFeeMngtService.selectAnlrveLevCnt(gamMarineCenterRentFeeMngtVO);
     		
     		if( anlrveLevCnt > 0 ) {
     			map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject1")); //세입징수에 기존재하는 건이 있습니다. 확인후 다시 시도하십시오.
                 
         		return map;
     		}
     		
     		//징수의뢰 정보조회
            GamMarineCenterRentFeeMngtVO gamMarineCenterRentFeeMngtInfo = gamMarineCenterRentFeeMngtService.selectMarineCenterRentFeeInfo(gamMarineCenterRentFeeMngtVO);
            
            if( EgovStringUtil.isEmpty(gamMarineCenterRentFeeMngtInfo.getAccnutYear()) ) {
            	map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject2")); //회계년도가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.
                 
         		return map;
            }
            
            if( EgovStringUtil.isEmpty(gamMarineCenterRentFeeMngtInfo.getNticno()) ) {
            	map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject3")); //고지번호가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.
                 
         		return map;
            }
            
    	}
     	
     	//세입징수 등록
     	for(int i=0; i<arrNticCnts.length;i++) {
     		gamMarineCenterRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamMarineCenterRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamMarineCenterRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamMarineCenterRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamMarineCenterRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		
     		//징수의뢰 정보조회
            GamMarineCenterRentFeeMngtVO gamMarineCenterRentFeeMngtInfo = gamMarineCenterRentFeeMngtService.selectMarineCenterRentFeeInfo(gamMarineCenterRentFeeMngtVO);
            
            gamMarineCenterRentFeeMngtInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
            gamMarineCenterRentFeeMngtInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
            
            gamMarineCenterRentFeeMngtService.insertAnlrveLev(gamMarineCenterRentFeeMngtInfo);
 		}
 		
 		resultCode = 0; 
        resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
         
     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
         
 		return map;
     }
    */
    
    /**
     * 고지의뢰를 한다. (다중처리)
     * @param gamMarineCenterRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/center/gamInsertMarineCenterRentFeeNtic.do") 
    @ResponseBody Map<String, Object> insertMarineCenterRentFeeNtic (
				  @RequestParam("nticCnts") String nticCnts
				 ,@RequestParam("prtAtCodes") String prtAtCodes
				 ,@RequestParam("mngYears") String mngYears
				 ,@RequestParam("mngNos") String mngNos
				 ,@RequestParam("mngCnts") String mngCnts
				 ,@RequestParam("nticnos") String nticnos
				 ,@RequestParam("accnutYears") String accnutYears
				 ,@ModelAttribute("gamMarineCenterRentFeeMngtVO") GamMarineCenterRentFeeMngtVO gamMarineCenterRentFeeMngtVO
    		     ) throws Exception {
 	
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
       
        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        
        System.out.println("############ prtAtCodes => " + prtAtCodes);
        
        String [] arrNticCnts = nticCnts.split(";");
     	String [] arrPrtAtCodes = prtAtCodes.split(";");
     	String [] arrMngYears = mngYears.split(";");
     	String [] arrMngNos = mngNos.split(";");
     	String [] arrMngCnts = mngCnts.split(";");
     	String [] arrNticnos = nticnos.split(";");
     	String [] arrAccnutYears = accnutYears.split(";");
        
     	//세입징수 등록
     	for(int i=0; i<arrNticCnts.length;i++) {
     		gamMarineCenterRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamMarineCenterRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamMarineCenterRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamMarineCenterRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamMarineCenterRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamMarineCenterRentFeeMngtVO.setRegUsr(loginVO.getId()); 
     		gamMarineCenterRentFeeMngtVO.setUpdUsr(loginVO.getId()); 
            
     		System.out.println("############################# 고지의뢰 CALL!! START ");
            
     		paramMap.put("nticCnt", gamMarineCenterRentFeeMngtVO.getNticCnt());
     		paramMap.put("prtAtCode", gamMarineCenterRentFeeMngtVO.getPrtAtCode());
     		paramMap.put("mngYear", gamMarineCenterRentFeeMngtVO.getMngYear());
     		paramMap.put("mngNo", gamMarineCenterRentFeeMngtVO.getMngNo());
     		paramMap.put("mngCnt", gamMarineCenterRentFeeMngtVO.getMngCnt());
            System.out.println("##################################### paramMap => " + paramMap);
            
            //이곳에 고지의뢰 서비스콜!! 삽입할것!!
            //gamMarineCenterRentFeeMngtService.insertAnlrveLev(gamMarineCenterRentFeeMngtInfo);
 		}
 		
 		resultCode = 0; 
        resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
         
     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
         
 		return map;
     }
    
    /**
     * 고지취소를 한다. (다중처리)
     * @param gamMarineCenterRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    /*
    @RequestMapping(value="/oper/center/gamDeleteMarineCenterRentFeeNtic.do") 
    @ResponseBody Map<String, Object> deleteMarineCenterRentFeeNtic (
				  @RequestParam("nticCnts") String nticCnts
				 ,@RequestParam("prtAtCodes") String prtAtCodes
				 ,@RequestParam("mngYears") String mngYears
				 ,@RequestParam("mngNos") String mngNos
				 ,@RequestParam("mngCnts") String mngCnts
				 ,@RequestParam("nticnos") String nticnos
				 ,@RequestParam("accnutYears") String accnutYears
				 ,@ModelAttribute("gamMarineCenterRentFeeMngtVO") GamMarineCenterRentFeeMngtVO gamMarineCenterRentFeeMngtVO
    		     ) throws Exception {
 	
        Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        int anlrveLevCnt = 0;
         
        String [] arrNticCnts = nticCnts.split(";");
     	String [] arrPrtAtCodes = prtAtCodes.split(";");
     	String [] arrMngYears = mngYears.split(";");
     	String [] arrMngNos = mngNos.split(";");
     	String [] arrMngCnts = mngCnts.split(";");
     	String [] arrNticnos = nticnos.split(";");
     	String [] arrAccnutYears = accnutYears.split(";");
        
 		//징수의뢰 삭제전 세입징수의 등록건수 체크
     	for(int i=0; i<arrNticCnts.length;i++) {
     		gamMarineCenterRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamMarineCenterRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamMarineCenterRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamMarineCenterRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamMarineCenterRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamMarineCenterRentFeeMngtVO.setNticno(arrNticnos[i]);
     		gamMarineCenterRentFeeMngtVO.setAccnutYear(arrAccnutYears[i]);
     		
     		//세입징수에 기등록  여부
     		anlrveLevCnt = gamMarineCenterRentFeeMngtService.selectAnlrveLevCnt(gamMarineCenterRentFeeMngtVO);
     		
     		if( anlrveLevCnt > 0 ) {
     			map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject1")); //세입징수에 기존재하는 건이 있습니다. 확인후 다시 시도하십시오.
                 
         		return map;
     		}
    	}
     	
     	//징수의뢰 삭제
     	for(int i=0; i<arrNticCnts.length;i++) {
     		gamMarineCenterRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamMarineCenterRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamMarineCenterRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamMarineCenterRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamMarineCenterRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		
            gamMarineCenterRentFeeMngtService.deleteMarineCenterRentFee(gamMarineCenterRentFeeMngtVO);
 		}
 		
 		resultCode = 0; 
        resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
         
     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
         
 		return map;
     }
    */
    
    /**
     * 고지취소를 한다. (다중처리)
     * @param gamMarineCenterRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/center/gamDeleteMarineCenterRentFeeNtic.do") 
    @ResponseBody Map<String, Object> deleteMarineCenterRentFeeNtic (
				  @RequestParam("nticCnts") String nticCnts
				 ,@RequestParam("prtAtCodes") String prtAtCodes
				 ,@RequestParam("mngYears") String mngYears
				 ,@RequestParam("mngNos") String mngNos
				 ,@RequestParam("mngCnts") String mngCnts
				 ,@RequestParam("nticnos") String nticnos
				 ,@RequestParam("accnutYears") String accnutYears
				 ,@ModelAttribute("gamMarineCenterRentFeeMngtVO") GamMarineCenterRentFeeMngtVO gamMarineCenterRentFeeMngtVO
    		     ) throws Exception {
 	
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

    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        
        System.out.println("############ prtAtCodes => " + prtAtCodes);
        
        String [] arrNticCnts = nticCnts.split(";");
     	String [] arrPrtAtCodes = prtAtCodes.split(";");
     	String [] arrMngYears = mngYears.split(";");
     	String [] arrMngNos = mngNos.split(";");
     	String [] arrMngCnts = mngCnts.split(";");
     	String [] arrNticnos = nticnos.split(";");
     	String [] arrAccnutYears = accnutYears.split(";");
        
     	//세입징수 등록
     	for(int i=0; i<arrNticCnts.length;i++) {
     		gamMarineCenterRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamMarineCenterRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamMarineCenterRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamMarineCenterRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamMarineCenterRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamMarineCenterRentFeeMngtVO.setRegUsr(loginVO.getId()); 
     		gamMarineCenterRentFeeMngtVO.setUpdUsr(loginVO.getId()); 
            
     		System.out.println("############################# 고지취소 CALL!! START ");
            //gamMarineCenterRentFeeMngtService.insertAnlrveLev(gamMarineCenterRentFeeMngtInfo);
     		paramMap.put("nticCnt", gamMarineCenterRentFeeMngtVO.getNticCnt());
     		paramMap.put("prtAtCode", gamMarineCenterRentFeeMngtVO.getPrtAtCode());
     		paramMap.put("mngYear", gamMarineCenterRentFeeMngtVO.getMngYear());
     		paramMap.put("mngNo", gamMarineCenterRentFeeMngtVO.getMngNo());
     		paramMap.put("mngCnt", gamMarineCenterRentFeeMngtVO.getMngCnt());
            System.out.println("##################################### paramMap => " + paramMap);
            
            //이곳에 고지취소 서비스콜!! 삽입할것!!
            //gamMarineCenterRentFeeMngtService.insertAnlrveLev(gamMarineCenterRentFeeMngtInfo);
 		}
 		
 		resultCode = 0; 
        resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
         
     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
         
 		return map;
     }
    
    /**
     * 고지의뢰를 한다.(단일처리)
     * @param gamMarineCenterRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    /*
    @RequestMapping(value="/oper/center/gamInsertMarineCenterRentFeeNticSingle.do") 
    public @ResponseBody Map insertMarineCenterRentFeeNticSingle(
     	   @ModelAttribute("gamMarineCenterRentFeeMngtVO") GamMarineCenterRentFeeMngtVO gamMarineCenterRentFeeMngtVO, 
     	   BindingResult bindingResult)
            throws Exception {
 	
     	 Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
         int anlrveLevCnt = 0;
 
       //세입징수에 기등록  여부
  		anlrveLevCnt = gamMarineCenterRentFeeMngtService.selectAnlrveLevCnt(gamMarineCenterRentFeeMngtVO);
  		
  		if( anlrveLevCnt > 0 ) {
  			map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject1")); //세입징수에 기존재하는 건이 있습니다. 확인후 다시 시도하십시오.
              
      		return map;
  		}
  		
  		//징수의뢰 정보조회
        GamMarineCenterRentFeeMngtVO gamMarineCenterRentFeeMngtInfo = gamMarineCenterRentFeeMngtService.selectMarineCenterRentFeeInfo(gamMarineCenterRentFeeMngtVO);
        
        if( EgovStringUtil.isEmpty(gamMarineCenterRentFeeMngtInfo.getAccnutYear()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject2")); //회계년도가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.
             
     		return map;
        }
        
        if( EgovStringUtil.isEmpty(gamMarineCenterRentFeeMngtInfo.getNticno()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject3")); //고지번호가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.
             
     		return map;
        }
        
        gamMarineCenterRentFeeMngtInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
        gamMarineCenterRentFeeMngtInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
        
        gamMarineCenterRentFeeMngtService.insertAnlrveLev(gamMarineCenterRentFeeMngtInfo);
         
        resultCode = 0; 
 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
        
     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
         
 		return map;
     }
     */
    
    /**
     * 고지의뢰를 한다.(단일처리)
     * @param gamMarineCenterRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception  
     */
    @RequestMapping(value="/oper/center/gamInsertMarineCenterRentFeeNticSingle.do") 
    public @ResponseBody Map insertMarineCenterRentFeeNticSingle(
     	   @ModelAttribute("gamMarineCenterRentFeeMngtVO") GamMarineCenterRentFeeMngtVO gamMarineCenterRentFeeMngtVO, 
     	   BindingResult bindingResult)
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
        
    	LoginVO loginVo = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
        System.out.println("######################################### 고지의뢰(단일처리) START!! ");
        
        paramMap.put("nticCnt", gamMarineCenterRentFeeMngtVO.getNticCnt());
 		paramMap.put("prtAtCode", gamMarineCenterRentFeeMngtVO.getPrtAtCode());
 		paramMap.put("mngYear", gamMarineCenterRentFeeMngtVO.getMngYear());
 		paramMap.put("mngNo", gamMarineCenterRentFeeMngtVO.getMngNo());
 		paramMap.put("mngCnt", gamMarineCenterRentFeeMngtVO.getMngCnt());
 		
 		System.out.println("##################################### paramMap => " + paramMap);
        
 		gamMarineCenterRentFeeMngtVO.setRegUsr(loginVo.getId());
 		gamMarineCenterRentFeeMngtVO.setUpdUsr(loginVo.getId());
 		 //이곳에 고지의뢰 서비스콜!! 삽입할것!!
        //gamMarineCenterRentFeeMngtService.insertAnlrveLev(gamMarineCenterRentFeeMngtInfo);
 		
        resultCode = 0; 
 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
        
     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
         
 		return map;
     }
    
    /**
     * 고지취소를 한다.(단일처리)
     * @param gamMarineCenterRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/center/gamDeleteMarineCenterRentFeeNticSingle.do") 
    public @ResponseBody Map deleteMarineCenterRentFeeNticSingle(
     	   @ModelAttribute("gamMarineCenterRentFeeMngtVO") GamMarineCenterRentFeeMngtVO gamMarineCenterRentFeeMngtVO, 
     	   BindingResult bindingResult)
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
    	
        System.out.println("######################################### 고지취소(단일처리) START!! ");
        
        paramMap.put("nticCnt", gamMarineCenterRentFeeMngtVO.getNticCnt());
 		paramMap.put("prtAtCode", gamMarineCenterRentFeeMngtVO.getPrtAtCode());
 		paramMap.put("mngYear", gamMarineCenterRentFeeMngtVO.getMngYear());
 		paramMap.put("mngNo", gamMarineCenterRentFeeMngtVO.getMngNo());
 		paramMap.put("mngCnt", gamMarineCenterRentFeeMngtVO.getMngCnt());
 		
 		System.out.println("##################################### paramMap => " + paramMap);
        
 		 //이곳에 고지의뢰 서비스콜!! 삽입할것!!
        //gamMarineCenterRentFeeMngtService.insertAnlrveLev(gamMarineCenterRentFeeMngtInfo);
 		
        resultCode = 0; 
 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
        
     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
         
 		return map;
     }
    
    /**
     * 마린센터임대료관리정보를 등록한다.
     * @param gamMarineCenterRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/center/gamInsertMarineCenterRentFee.do") 
    public @ResponseBody Map insertMarineCenterRentFee(
     	   @ModelAttribute("gamMarineCenterRentFeeMngtVO") GamMarineCenterRentFeeMngtVO gamMarineCenterRentFeeMngtVO, 
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
     	
 		 LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
 		 gamMarineCenterRentFeeMngtVO.setRegUsr(loginVO.getId()); 
         gamMarineCenterRentFeeMngtVO.setUpdUsr(loginVO.getId()); 
         
         gamMarineCenterRentFeeMngtService.insertAssetRentLevReqest(gamMarineCenterRentFeeMngtVO);
         
         resultCode = 0; 
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
         
     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);
         
 		return map;
     }
    
    /**
     * 고지의뢰를 한다.(단일처리)
     * @param gamAssetRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/oper/center/insertMarineCenterRentFeeNticSingle.do")
    public @ResponseBody Map insertAssetRentFeeNticSingle(
     	   @ModelAttribute("gamAssetRentFeeMngtVO") GamAssetRentFeeMngtVO gamAssetRentFeeMngtVO,
     	   BindingResult bindingResult)
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

    		paramMap.put("updUsr", loginVo.getId());
	        paramMap.put("nticCnt", gamAssetRentFeeMngtVO.getNticCnt());
	 		paramMap.put("prtAtCode", gamAssetRentFeeMngtVO.getPrtAtCode());
	 		paramMap.put("mngYear", gamAssetRentFeeMngtVO.getMngYear());
	 		paramMap.put("mngNo", gamAssetRentFeeMngtVO.getMngNo());
	 		paramMap.put("mngCnt", gamAssetRentFeeMngtVO.getMngCnt());
	 		paramMap.put("chrgeKnd", gamAssetRentFeeMngtVO.getChrgeKnd());

	 		gamNticRequestMngtService.sendNticRequest(paramMap);

	        resultCode = 0;
	 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

	     	map.put("resultCode", resultCode);
	        map.put("resultMsg", resultMsg);
    	}
    	catch(Exception e) {
	        map.put("resultCode", -1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.nticIssue.msg"));
        	return map;
    	}

 		return map;
     }

    /**
     * 고지취소를 한다.(단일처리)
     * @param gamAssetRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/oper/center/cancelMarineCenterRentFeeNticSingle.do")
    public @ResponseBody Map deleteAssetRentFeeNticSingle(
     	   @ModelAttribute("gamAssetRentFeeMngtVO") GamAssetRentFeeMngtVO gamAssetRentFeeMngtVO,
     	   BindingResult bindingResult)
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

    		paramMap.put("updUsr", loginVo.getId());
	        paramMap.put("nticCnt", gamAssetRentFeeMngtVO.getNticCnt());
	 		paramMap.put("prtAtCode", gamAssetRentFeeMngtVO.getPrtAtCode());
	 		paramMap.put("mngYear", gamAssetRentFeeMngtVO.getMngYear());
	 		paramMap.put("mngNo", gamAssetRentFeeMngtVO.getMngNo());
	 		paramMap.put("mngCnt", gamAssetRentFeeMngtVO.getMngCnt());
	 		paramMap.put("chrgeKnd", gamAssetRentFeeMngtVO.getChrgeKnd());

	 		gamNticRequestMngtService.cancelNticRequest(paramMap);

	        resultCode = 0;
	 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
    	}
    	catch(Exception e) {
	        resultCode = -1;
	 		resultMsg  = egovMessageSource.getMessage("fail.cancelNticIssue.msg");
    	}

     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

 		return map;
     }
    
    /**
     * 고지서를 출력한다.
     * @param approvalOpt
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/oper/center/printMarineCenterRentFeePayNotice.do")
    String printAssetRentFeePayNotice(@RequestParam Map<String, Object> approvalOpt, ModelMap model) throws Exception {
    	model.addAttribute("searchOpt", approvalOpt);

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	}
    	else {
//    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    		List list = gamMarineCenterRentFeeMngtService.selectNpticPrintInfo(approvalOpt);

//    		model.addAttribute("emplyrNo", loginVo.getEmplNo());

    		model.addAttribute("resultCode", 0);
    		model.addAttribute("resultList", list);
    		model.addAttribute("resultMsg", "");
    	}

    	return "ygpa/gam/oper/center/GamPrtfcltyPrintNoticeIssue";
    	}

    
    
    
        @RequestMapping(value="/oper/center/printMarineCenterRentFeeTaxNotice.do")
        String printAssetRentFeeTaxNotice(@RequestParam Map<String, Object> approvalOpt, ModelMap model) throws Exception {
        	model.addAttribute("searchOpt", approvalOpt);

        	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        	if(!isAuthenticated) {
        		model.addAttribute("resultCode", 1);
        		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	}
        	else {
//        		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        		List list = gamMarineCenterRentFeeMngtService.selectTaxNtcPrintInfo(approvalOpt);

//        		model.addAttribute("emplyrNo", loginVo.getEmplNo());

        		model.addAttribute("resultCode", 0);
        		model.addAttribute("resultList", list);
        		model.addAttribute("resultMsg", "");
        	}

        	return "ygpa/gam/oper/center/GamPrtfcltyRentPrintTaxNoticeIssue";
        	}
        
        @RequestMapping(value="/oper/center/updateMarineCenterRentFeeMngtListDetail.do")
	    public @ResponseBody Map updateAssetRentFeeMngtListDetail(
	     	   @ModelAttribute("gamMarineCenterRentFeeMngtVO") GamMarineCenterRentFeeMngtVO gamMarineCenterRentFeeMngtVO,
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

	    	try {
	    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	    		gamMarineCenterRentFeeMngtVO.setUpdUsr(loginVo.getId()); //수정자 (세션 로그인 아이디)
	    		gamMarineCenterRentFeeMngtService.updateAssetRentFeeMngtListDetail(gamMarineCenterRentFeeMngtVO);
		         resultCode = 0;
		 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
	    	}
	    	catch(Exception e) {
		         resultCode = 0;
		 		 resultMsg  = egovMessageSource.getMessage("fail.common.update"); //정상적으로 처리되었습니다.
	    	}

	     	 map.put("resultCode", resultCode);
	         map.put("resultMsg", resultMsg);

	 		return map;
	     }
        
        /**
	     * 자산임대료고지관리 상세정보를 조회한다.
	     *
	     * @param searchVO
	     * @return map
	     * @throws Exception the exception
	     */
		@SuppressWarnings({ "rawtypes", "unchecked" })
	    @RequestMapping(value="/oper/center/gamSelectMarineCenterRentFeeMngtListDetail.do", method=RequestMethod.POST)
		public @ResponseBody Map gamSelectAssetRentFeeMngtListDetail(GamMarineCenterRentFeeMngtVO searchVO) throws Exception {

			int totalCnt, page, firstIndex;
	    	Map map = new HashMap();

	    	//searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
	    	//searchVO.setPageSize(propertiesService.getInt("pageSize"));

	    	PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
			paginationInfo.setPageSize(searchVO.getPageSize());

			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			//자산임대상세
	    	List resultList = gamMarineCenterRentFeeMngtService.selectAssetRentFeeDetailList(searchVO);
	    	Map master = gamMarineCenterRentFeeMngtService.selectAssetRentFeeDetailMstPk(searchVO);
	    	Map summary = gamMarineCenterRentFeeMngtService.selectAssetRentFeeDetailSumPk(searchVO);

	        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());


	    	map.put("resultCode", 0);	// return ok
	    	map.put("resultList", resultList);
	    	map.put("resultMaster", master);
	    	map.put("resultSummary", summary);
	    	map.put("searchOption", searchVO);

	    	return map;
	    }
        

    
    
    
}
