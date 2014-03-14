package egovframework.rte.ygpa.gam.oper.gnrl.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtVO;

/**
 * @Class Name : GamPrtFcltyRentMngtController.java
 * @Description : 항만시설사용목록관리 (항만시설/일반부두/항만시설사용목록관리)
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
public class GamPrtFcltyRentMngtController {

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
    
    @Resource(name = "gamPrtFcltyRentMngtService")
    private GamPrtFcltyRentMngtService gamPrtFcltyRentMngtService;
	
    
    /**
     * 항만시설사용관리 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/gnrl/GamPrtFcltyRentMngt"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/oper/gnrl/gamPrtFcltyRentMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		//공시지가정보
		GamPrtFcltyRentMngtVO gvo = new GamPrtFcltyRentMngtVO();
		List olnlpList = gamPrtFcltyRentMngtService.selectOlnlpInfo();
		
		model.addAttribute("olnlpList", olnlpList);
		model.addAttribute("loginOrgnztId", loginVO.getOrgnztId());
		model.addAttribute("loginUserId", loginVO.getId());
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/oper/gnrl/GamPrtFcltyRentMngt";
    }
	
	/**
     * 항만시설사용목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrl/gamSelectPrtFcltyRentMngtList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectPrtFcltyRentMngtList(GamPrtFcltyRentMngtVO searchVO) throws Exception {

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
		
		//항만시설사용목록
    	totalCnt = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtListTotCnt(searchVO);
    	List assetRentList = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtList(searchVO);
    	
    	//총면적, 총사용료
    	GamPrtFcltyRentMngtVO resultSum = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtSum(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	map.put("sumGrAr", resultSum.getSumGrAr());
    	map.put("sumGrFee", resultSum.getSumGrFee());
    	
    	return map;
    }
	
	/**
     * 항만시설사용상세리스트를 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrl/gamSelectPrtFcltyRentMngtDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectPrtFcltyRentMngtDetailList(GamPrtFcltyRentMngtVO searchVO) throws Exception {

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

		// 항만시설사용상세리스트 및 총건수
		totalCnt = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtDetailListTotCnt(searchVO);
		List resultList = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtDetailList(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	/**
     * 항만시설사용 등록한다.
     * @param String
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
	@RequestMapping(value="/oper/gnrl/gamSavePrtFcltyRentMngt.do")
	@ResponseBody Map<String, Object> savePrtFcltyRentMngt(@RequestParam Map<String, Object> mergeAssetCodeList) throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		Map<String,Object> map = new HashMap<String,Object>();
		ObjectMapper mapper = new ObjectMapper();
 
    	List<HashMap<String,String>> insertList=null;
    	List<HashMap<String,String>> updateList=null;
    	List<HashMap<String,String>> deleteList=null;
    	HashMap<String,String> form=null;
    	
    	try {
    		insertList = mapper.readValue((String)mergeAssetCodeList.get("insertList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

    		updateList = mapper.readValue((String)mergeAssetCodeList.get("updateList"),
        		    new TypeReference<List<HashMap<String,String>>>(){});

    		deleteList = mapper.readValue((String)mergeAssetCodeList.get("deleteList"),
        		    new TypeReference<List<HashMap<String,String>>>(){});

    		form = mapper.readValue((String)mergeAssetCodeList.get("form"),
        		    new TypeReference<HashMap<String,String>>(){});

    		log.debug("###################################################### insertList : "+insertList);
    		log.debug("###################################################### updateList : "+updateList);
    		log.debug("###################################################### deleteList : "+deleteList);
    		log.debug("###################################################### form : "+form);

    		//자산임대저장
    		GamPrtFcltyRentMngtVO saveVO= new GamPrtFcltyRentMngtVO();
    		/*
    		   prtAtCode
    		   deptcd
    		   mngYear
    		   mngNo
    		   mngCnt
    		   entrpscd
    		   frstReqstDt
    		   
    		   prmisnYn//넣지마!!
    		   prmisnDt 넣지마
    		   
    		   grUsagePdFrom 넣지마
    		   grUsagePdTo  넣지마
    		   grAr 넣지마
    		   grFee 넣지마
    		   grRdcxptFee  넣지마
    		   
    		   payMth
    		   nticMth
    		   rm
    		   cmt
    		   
    		   
    		   
    		   
    		   MAX키값 가져오기
    		 */
    		

			saveVO.setPrtAtCode(form.get("prtAtCode"));
			saveVO.setDeptcd(form.get("deptcd"));     
			saveVO.setMngYear(form.get(" mngYear"));    
			saveVO.setMngNo(form.get("mngNo"));      
			saveVO.setMngCnt(form.get("mngCnt"));     
			saveVO.setEntrpscd(form.get("entrpscd"));   
			saveVO.setFrstReqstDt(form.get("frstReqstDt"));
			saveVO.setPayMth(form.get("payMth"));     
			saveVO.setNticMth(form.get("nticMth"));    
			saveVO.setRm(form.get("rm"));         
			saveVO.setCmt(form.get("cmt")); 
    		
    		saveVO.setReqstSeCd("1");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
    		saveVO.setRegUsr(loginVO.getId()); 
    		saveVO.setUpdUsr(loginVO.getId());
    		
    		//자산임대상세저장
    		for( int i = 0 ; i < insertList.size() ; i++ ) {
    			Map resultMap = insertList.get(i);
    		}
    		
    		for( int i = 0 ; i < updateList.size() ; i++ ) {
    			Map resultMap = updateList.get(i);
    		}
    		
    		for( int i = 0 ; i < deleteList.size() ; i++ ) {
    			Map resultMap = deleteList.get(i);
    			
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ insertList.get(i) String => " + insertList.get(i));
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ resultMap.get() => " + resultMap.get("gisAssetsPrtAtCode"));
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	log.debug("insert list : "+insertList.size());
    	log.debug("updateList list : "+updateList.size());
    	log.debug("deleteList list : "+deleteList.size());

		map.put("resultCode", 0);			// return ok
		map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));
		return map;
    }
	
	/**
     * 항만시설사용 최초신청을 등록한다.
     * @param String
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamInsertPrtFcltyRentMngtFirst.do") 
    public @ResponseBody Map insertPrtFcltyRentMngtFirst(
    	   @RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamPrtFcltyRentMngtVO") GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO, 
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
	        beanValidator.validate(gamPrtFcltyRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamPrtFcltyRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(gamPrtFcltyRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	if("insert".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamPrtFcltyRentMngtVO.setReqstSeCd("1");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamPrtFcltyRentMngtVO.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	gamPrtFcltyRentMngtVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	//gamPrtFcltyRentMngtVO.setDeptcd("A001");   //부서코드 (세션?) 
	    	
	        gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(gamPrtFcltyRentMngtVO);
	    	
	        resultCode = 0; // return ok
			resultMsg  = egovMessageSource.getMessage("success.common.insert");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.oper.gnrl.err.exceptional");
    	}
		
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    
    /**
     * 항만시설사용 연장신청을 등록한다.
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamInsertPrtFcltyRentMngtRenew.do") 
    public @ResponseBody Map insertPrtFcltyRentMngtRenew(
    	   @ModelAttribute("gamPrtFcltyRentMngtVO") GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO,
   	       BindingResult bindingResult)
           throws Exception {
    	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	GamPrtFcltyRentMngtVO resultVO = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtMaxNo(gamPrtFcltyRentMngtVO);
    	
    	if( gamPrtFcltyRentMngtVO.getMngCnt().equals(resultVO.getMaxMngCnt()) ) {
    		//키 같고 max관리번호가 같으면 연장신청 등록
        	
    		gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtRenew(gamPrtFcltyRentMngtVO);
    		
    		resultCode = 0; // return ok
    		resultMsg  = egovMessageSource.getMessage("success.common.insert");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.oper.gnrl.reject");
    	}
    	
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 항만시설사용 정보를 수정한다.
     * @param String
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamUpdatePrtFcltyRentMngt.do") 
    public @ResponseBody Map updatePrtFcltyRentMngtFirst(
    	   @RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamPrtFcltyRentMngtVO") GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	if("modify".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamPrtFcltyRentMngtVO.setReqstSeCd("3");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamPrtFcltyRentMngtVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	//gamPrtFcltyRentMngtVO.setDeptcd("A001");   //부서코드 (세션?) 
	    	
	        gamPrtFcltyRentMngtService.updatePrtFcltyRentMngt(gamPrtFcltyRentMngtVO);
	    	
	        resultCode = 0; // return ok
	        resultMsg  = egovMessageSource.getMessage("success.common.update");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.oper.gnrl.err.exceptional");
    	}
    	
    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);
        
		return map;
    }
	
    /**
     * 항만시설사용 정보를 삭제한다.
     * @param String
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamDeletePrtFcltyRentMngt.do") 
    public @ResponseBody Map deletePrtFcltyRentMngt(
    	   //@RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamPrtFcltyRentMngtDetailVO") GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg  = "";
        String deleteFlag = "";
        int resultCode = 1;
        
        int resultLevReqestCnt = -1;
        
        if( EgovStringUtil.isEmpty(gamPrtFcltyRentMngtVO.getPrmisnYn()) || gamPrtFcltyRentMngtVO.getPrmisnYn().equals("N") ) { //허가여부가 'N'이면 삭제가능	
        	deleteFlag = "Y";
        } else {
        	resultLevReqestCnt = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtLevReqestCnt(gamPrtFcltyRentMngtVO); //징수의뢰 정보 카운트
        	
        	if( gamPrtFcltyRentMngtVO.getPrmisnYn().equals("Y") && resultLevReqestCnt == 0 ) { //허가여부가 Y이고 징수의뢰테이블에 정보가 없으면 삭제가능
            	deleteFlag = "Y";
            }
        }
    	
    	if("Y".equals(deleteFlag)) {
	        gamPrtFcltyRentMngtService.deletePrtFcltyRentMngt(gamPrtFcltyRentMngtVO);
	    	
	        resultCode = 0; // return ok
	        resultMsg  = egovMessageSource.getMessage("success.common.delete");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.oper.gnrl.err.delete");
    	}
		
    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 항만시설사용 상세를 등록한다.
     * @param String
     * @param gamPrtFcltyRentMngtDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamInsertPrtFcltyRentMngtDetail.do") 
    public @ResponseBody Map insertPrtFcltyRentMngtDetail(
    	   @RequestParam("detailCmd") String detailCmd, 
    	   @ModelAttribute("gamPrtFcltyRentMngtDetailVO") GamPrtFcltyRentMngtDetailVO gamPrtFcltyRentMngtDetailVO, 
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
	        beanValidator.validate(gamPrtFcltyRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamPrtFcltyRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(gamPrtFcltyRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
        GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO = new GamPrtFcltyRentMngtVO();
        gamPrtFcltyRentMngtVO.setPrtAtCode(gamPrtFcltyRentMngtDetailVO.getDetailPrtAtCode());
        gamPrtFcltyRentMngtVO.setMngYear(gamPrtFcltyRentMngtDetailVO.getDetailMngYear());
        gamPrtFcltyRentMngtVO.setMngNo(gamPrtFcltyRentMngtDetailVO.getDetailMngNo());
        gamPrtFcltyRentMngtVO.setMngCnt(gamPrtFcltyRentMngtDetailVO.getDetailMngCnt());
        
        //임대정보 조회후 승낙여부 체크
        GamPrtFcltyRentMngtVO rentPrmisnInfo = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtPrmisnInfo(gamPrtFcltyRentMngtVO);
        
        
        
        log.debug("######################################## rentPrmisnInfo.getPrmisnYn() => " + rentPrmisnInfo.getPrmisnYn());
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 등록가능
        	if("insert".equals(detailCmd)) {
    	    	//확인후 변경혀라~~
    	    	gamPrtFcltyRentMngtDetailVO.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
    	    	gamPrtFcltyRentMngtDetailVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
    	    	
    	        gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtDetail(gamPrtFcltyRentMngtDetailVO);
    	    	
    	        resultCode = 0; // return ok
    			resultMsg  = egovMessageSource.getMessage("success.common.insert");
        	} else {
        		resultCode = 1; // return fail
        		resultMsg  = egovMessageSource.getMessage("gam.oper.gnrl.err.exceptional");
        	}
        } else {
        	resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.oper.gnrl.detailModify.reject");
        }
    	
		
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 항만시설사용 상세를 수정한다.
     * @param String
     * @param gamPrtFcltyRentMngtDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamUpdatePrtFcltyRentMngtDetail.do") 
    public @ResponseBody Map updatePrtFcltyRentMngtDetail(
    	   @RequestParam("detailCmd") String detailCmd, 
    	   @ModelAttribute("gamPrtFcltyRentMngtDetailVO") GamPrtFcltyRentMngtDetailVO gamPrtFcltyRentMngtDetailVO, 
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
    	log.debug("######################################## gamPrtFcltyRentMngtVO.getDetailPrtAtCode() => " + gamPrtFcltyRentMngtDetailVO.getDetailPrtAtCode());
    	
    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamPrtFcltyRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamPrtFcltyRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(gamPrtFcltyRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO = new GamPrtFcltyRentMngtVO();
        gamPrtFcltyRentMngtVO.setPrtAtCode(gamPrtFcltyRentMngtDetailVO.getDetailPrtAtCode());
        gamPrtFcltyRentMngtVO.setMngYear(gamPrtFcltyRentMngtDetailVO.getDetailMngYear());
        gamPrtFcltyRentMngtVO.setMngNo(gamPrtFcltyRentMngtDetailVO.getDetailMngNo());
        gamPrtFcltyRentMngtVO.setMngCnt(gamPrtFcltyRentMngtDetailVO.getDetailMngCnt());
        
        //임대정보 조회후 승낙여부 체크
        GamPrtFcltyRentMngtVO rentPrmisnInfo = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtPrmisnInfo(gamPrtFcltyRentMngtVO);
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 수정가능
	    	if("modify".equals(detailCmd)) {
		    	gamPrtFcltyRentMngtDetailVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
		    	
		        gamPrtFcltyRentMngtService.updatePrtFcltyRentMngtDetail(gamPrtFcltyRentMngtDetailVO);
		    	
		        resultCode = 0; // return ok
				resultMsg  = egovMessageSource.getMessage("success.common.update");
	    	} else {
	    		resultCode = 1; // return fail
	    		resultMsg  = egovMessageSource.getMessage("gam.oper.gnrl.err.exceptional");
	    	}
        } else {
        	resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.oper.gnrl.detailModify.reject");
        }
		
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 항만시설사용 상세를 삭제한다.
     * @param gamPrtFcltyRentMngtDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamDeletePrtFcltyRentMngtDetail.do") 
    public @ResponseBody Map deletePrtFcltyRentMngtDetail(
    	   @ModelAttribute("gamPrtFcltyRentMngtDetailVO") GamPrtFcltyRentMngtDetailVO gamPrtFcltyRentMngtDetailVO, 
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
	        beanValidator.validate(gamPrtFcltyRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamPrtFcltyRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(gamPrtFcltyRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	gamPrtFcltyRentMngtService.deletePrtFcltyRentMngtDetail2(gamPrtFcltyRentMngtDetailVO);
    	
        resultCode = 0; // return ok
		resultMsg  = egovMessageSource.getMessage("success.common.delete");
		
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 승낙 팝업화면을 로딩한다. 
     *
     * @param gamPrtFcltyRentMngtLevReqestVO
     * @param model the model
     * @return "/ygpa/gam/oper/gnrl/GamPopupPrtFcltyRentMngtPrmisn"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/oper/gnrl/popup/showPrtFcltyRentMngtPrmisn.do") 
    String showEntrpsInfo(GamPrtFcltyRentMngtLevReqestVO gamPrtFcltyRentMngtLevReqestVO, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM024"); //요금종류
		List chrgeKndCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		model.addAttribute("gamPrtFcltyRentMngtInfo", gamPrtFcltyRentMngtLevReqestVO);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);

    	return "/ygpa/gam/oper/gnrl/GamPopupPrtFcltyRentMngtPrmisn";
    }
    
    /**
     * 항만시설사용 승낙(허가)을 한다.
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamInsertPrtFcltyRentMngtPrmisn.do") 
    public @ResponseBody Map insertPrtFcltyRentMngtLevReqest(
    	   @ModelAttribute("gamPrtFcltyRentMngtVO") GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
        
        //승낙할 임대정보조회
        GamPrtFcltyRentMngtVO rentPrmisnInfo = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtPrmisnInfo(gamPrtFcltyRentMngtVO);
        
        //징수의뢰 테이블에 갯수 카운트 조회
        int levReqestCnt = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtLevReqestCnt(gamPrtFcltyRentMngtVO);
        
        if( "Y".equals(rentPrmisnInfo.getPrmisnYn()) ) { 
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.oper.gnrl.prmisn.reject2")); //이미 승낙된 상태입니다.
            
    		return map;
        }
        
        if( levReqestCnt > 0 ) { 
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.oper.gnrl.prmisn.reject3")); //징수의뢰에 정보가 존재하여 승낙을 진행할 수 없습니다.
            
    		return map;
        }
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getNticMth()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.oper.gnrl.prmisn.reject1")); //고지방법코드가 없습니다.
            
    		return map;
        }
        
        if( !"1".equals(rentPrmisnInfo.getNticMth()) && !"2".equals(rentPrmisnInfo.getNticMth()) && !"3".equals(rentPrmisnInfo.getNticMth()) && !"4".equals(rentPrmisnInfo.getNticMth()) && !"5".equals(rentPrmisnInfo.getNticMth())) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.oper.gnrl.prmisn.reject5")); // 고지방법코드가 올바르지 않습니다. ('1':일괄, '2':반기납, '3':3분납, '4':분기납, '5':월납)
            
    		return map;
        }
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getGrUsagePdFrom()) || EgovStringUtil.isEmpty(rentPrmisnInfo.getGrUsagePdTo()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.oper.gnrl.prmisn.reject4")); //총사용기간 일자가 없습니다.
            
    		return map;
        }
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getGrFee()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.oper.gnrl.prmisn.reject6")); //총사용료가 없습니다.
            
    		return map;
        }
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPayMth()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.oper.gnrl.prmisn.reject10")); //납부방법 코드가 없습니다.
            
    		return map;
        }
        
        if( !"Pre".equals( rentPrmisnInfo.getPayMth() ) && !"Aft".equals( rentPrmisnInfo.getPayMth() ) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.oper.gnrl.prmisn.reject9")); //납부방법 코드가 올바르지 않습니다.
            
    		return map;
        }
        
        GamPrtFcltyRentMngtLevReqestVO levReqestInfo = new GamPrtFcltyRentMngtLevReqestVO();
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
		levReqestInfo.setChrgeKnd( gamPrtFcltyRentMngtVO.getChrgeKnd() );
		levReqestInfo.setVatYn( gamPrtFcltyRentMngtVO.getVatYn() );
		levReqestInfo.setPayMth( rentPrmisnInfo.getPayMth() );
		
        levReqestInfo.setPrmisnYn("Y"); //허가여부
        levReqestInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
        levReqestInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
        
        //임대정보의 허가여부를 Y로 업데이트 및 징수의뢰 insert
        gamPrtFcltyRentMngtService.updatePrtFcltyRentMngtPrmisn(levReqestInfo);
        
        resultCode = 0; 
		resultMsg  = egovMessageSource.getMessage("gam.oper.gnrl.prmisn.exec"); //승낙이 정상적으로 처리되었습니다.
        
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 항만시설사용 승낙취소(허가취소)를 한다.
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamUpdatePrtFcltyRentMngtPrmisnCancel.do") 
    public @ResponseBody Map updatePrtFcltyRentMngtPrmisnCancel(
     	   @ModelAttribute("gamPrtFcltyRentMngtVO") GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO, 
     	   BindingResult bindingResult)
            throws Exception {
 	
     	Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
         
         //승낙할 임대정보조회
         GamPrtFcltyRentMngtVO rentPrmisnInfo = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtPrmisnInfo(gamPrtFcltyRentMngtVO);
         
         //징수의뢰 테이블에 갯수 카운트 조회
         int levReqestCnt = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtLevReqestCnt(gamPrtFcltyRentMngtVO);
         
         if( !"Y".equals(rentPrmisnInfo.getPrmisnYn()) ) { 
         	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.oper.gnrl.prmisn.reject7")); //승낙된 상태가 아닙니다.
             
     		return map;
         }
         
         if( levReqestCnt > 0 ) { 
         	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.oper.gnrl.prmisn.reject8")); //징수의뢰에 정보가 존재하여 승낙을 취소 할 수 없습니다.
             
     		return map;
         }
         
         GamPrtFcltyRentMngtLevReqestVO levReqestInfo = new GamPrtFcltyRentMngtLevReqestVO();
         levReqestInfo.setPrtAtCode( rentPrmisnInfo.getPrtAtCode() );
         levReqestInfo.setMngYear( rentPrmisnInfo.getMngYear() );
         levReqestInfo.setMngNo( rentPrmisnInfo.getMngNo() );
         levReqestInfo.setMngCnt( rentPrmisnInfo.getMngCnt() );
 		
         levReqestInfo.setPrmisnYn("N"); //허가여부
         levReqestInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
         levReqestInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
         
         //임대정보의 허가여부를 N으로 업데이트
         gamPrtFcltyRentMngtService.updatePrtFcltyRentMngtPrmisnCancel(levReqestInfo);
         
         resultCode = 0; 
 		 resultMsg  = egovMessageSource.getMessage("gam.oper.gnrl.prmisn.execCancel"); //승낙이 정상적으로 취소되었습니다.
         
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
    @RequestMapping(value="/oper/gnrl/gamSelectPrtFcltyRentMngtFileList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectPrtFcltyRentMngtFileList(GamPrtFcltyRentMngtVO searchVO) throws Exception {

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
		
		//파일목록
    	totalCnt = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtFileListTotCnt(searchVO);
    	List assetFileList = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtFileList(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("assetFileList", assetFileList);
    	
    	return map;
    }
}