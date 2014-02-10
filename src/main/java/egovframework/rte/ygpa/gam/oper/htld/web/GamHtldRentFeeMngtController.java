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
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtVO;

/**
 * @Class Name : GamHtldRentFeeMngtController.java
 * @Description : 배후단지임대료관리 (항만시설/일반부두/배후단지임대료관리)
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
public class GamHtldRentFeeMngtController {
	
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
    
    @Resource(name = "gamHtldRentFeeMngtService")
    private GamHtldRentFeeMngtService gamHtldRentFeeMngtService;
	
    
    /**
     * 배후단지임대료고지관리 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/htld/GamHtldRentFeeMngt"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/oper/htld/gamHtldRentFeeMngt.do")
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
    	
    	return "/ygpa/gam/oper/htld/GamHtldRentFeeMngt";
    }
	
	/**
     * 배후단지임대료고지관리 목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/gamSelectHtldRentFeeMngtList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldRentFeeMngtList(GamHtldRentFeeMngtVO searchVO) throws Exception {

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
    	totalCnt = gamHtldRentFeeMngtService.selectHtldRentFeeMngtListTotCnt(searchVO);
    	List resultList = gamHtldRentFeeMngtService.selectHtldRentFeeMngtList(searchVO);
    	
    	//자료수, 사용료, 연체, 부가세, 고지액
    	GamHtldRentFeeMngtVO resultSum = gamHtldRentFeeMngtService.selectHtldRentFeeMngtSum(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	map.put("sumFee", resultSum.getSumFee());
    	map.put("sumArrrgAmt", resultSum.getSumArrrgAmt());
    	map.put("sumVat", resultSum.getSumVat());
    	map.put("sumNticAmt", resultSum.getSumNticAmt());
    	
    	return map;
    }
	
	/**
     * 배후단지임대료고지관리정보를 수정한다.
     * @param gamHtldRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamUpdateHtldRentFeeMngt.do") 
    public @ResponseBody Map updateHtldRentFeeMngt(
     	   @ModelAttribute("gamHtldRentFeeMngtVO") GamHtldRentFeeMngtVO gamHtldRentFeeMngtVO, 
     	   BindingResult bindingResult)
            throws Exception {
 	
     	 Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
 
         gamHtldRentFeeMngtVO.setUpdUsr("admin1"); //수정자 (세션 로그인 아이디)
         
         gamHtldRentFeeMngtService.updateHtldRentFeeMngt(gamHtldRentFeeMngtVO);
         
         resultCode = 0; 
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
         
     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);
         
 		return map;
     }

    /**
     * 고지의뢰를 한다. (다중처리)
     * @param gamHtldRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamInsertHtldRentFeeMngtNtic.do") 
    @ResponseBody Map<String, Object> insertHtldRentFeeMngtNtic (
				  @RequestParam("nticCnts") String nticCnts
				 ,@RequestParam("prtAtCodes") String prtAtCodes
				 ,@RequestParam("mngYears") String mngYears
				 ,@RequestParam("mngNos") String mngNos
				 ,@RequestParam("mngCnts") String mngCnts
				 ,@RequestParam("nticnos") String nticnos
				 ,@RequestParam("accnutYears") String accnutYears
				 ,@ModelAttribute("gamHtldRentFeeMngtVO") GamHtldRentFeeMngtVO gamHtldRentFeeMngtVO
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
        
 		//세입징수에 등록하기전 validation 체크
     	for(int i=0; i<arrNticCnts.length;i++) {
     		gamHtldRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamHtldRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamHtldRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamHtldRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamHtldRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamHtldRentFeeMngtVO.setNticno(arrNticnos[i]);
     		gamHtldRentFeeMngtVO.setAccnutYear(arrAccnutYears[i]);
     		
     		//세입징수에 기등록  여부
     		anlrveLevCnt = gamHtldRentFeeMngtService.selectAnlrveLevCnt(gamHtldRentFeeMngtVO);
     		
     		if( anlrveLevCnt > 0 ) {
     			map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject1")); //세입징수에 기존재하는 건이 있습니다. 확인후 다시 시도하십시오.
                 
         		return map;
     		}
     		
     		//징수의뢰 정보조회
            GamHtldRentFeeMngtVO gamHtldRentFeeMngtInfo = gamHtldRentFeeMngtService.selectHtldRentFeeMngtInfo(gamHtldRentFeeMngtVO);
            
            if( EgovStringUtil.isEmpty(gamHtldRentFeeMngtInfo.getAccnutYear()) ) {
            	map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject2")); //회계년도가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.
                 
         		return map;
            }
            
            if( EgovStringUtil.isEmpty(gamHtldRentFeeMngtInfo.getNticno()) ) {
            	map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject3")); //고지번호가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.
                 
         		return map;
            }
            
    	}
     	
     	//세입징수 등록
     	for(int i=0; i<arrNticCnts.length;i++) {
     		gamHtldRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamHtldRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamHtldRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamHtldRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamHtldRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		
     		//징수의뢰 정보조회
            GamHtldRentFeeMngtVO gamHtldRentFeeMngtInfo = gamHtldRentFeeMngtService.selectHtldRentFeeMngtInfo(gamHtldRentFeeMngtVO);
            
            gamHtldRentFeeMngtInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
            gamHtldRentFeeMngtInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
            
            gamHtldRentFeeMngtService.insertAnlrveLev(gamHtldRentFeeMngtInfo);
 		}
 		
 		resultCode = 0; 
        resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
         
     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
         
 		return map;
     }
    
    /**
     * 고지취소를 한다. (다중처리)
     * @param gamHtldRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamDeleteHtldRentFeeMngtNtic.do") 
    @ResponseBody Map<String, Object> deleteHtldRentFeeMngtNtic (
				  @RequestParam("nticCnts") String nticCnts
				 ,@RequestParam("prtAtCodes") String prtAtCodes
				 ,@RequestParam("mngYears") String mngYears
				 ,@RequestParam("mngNos") String mngNos
				 ,@RequestParam("mngCnts") String mngCnts
				 ,@RequestParam("nticnos") String nticnos
				 ,@RequestParam("accnutYears") String accnutYears
				 ,@ModelAttribute("gamHtldRentFeeMngtVO") GamHtldRentFeeMngtVO gamHtldRentFeeMngtVO
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
     		gamHtldRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamHtldRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamHtldRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamHtldRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamHtldRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamHtldRentFeeMngtVO.setNticno(arrNticnos[i]);
     		gamHtldRentFeeMngtVO.setAccnutYear(arrAccnutYears[i]);
     		
     		//세입징수에 기등록  여부
     		anlrveLevCnt = gamHtldRentFeeMngtService.selectAnlrveLevCnt(gamHtldRentFeeMngtVO);
     		
     		if( anlrveLevCnt > 0 ) {
     			map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject1")); //세입징수에 기존재하는 건이 있습니다. 확인후 다시 시도하십시오.
                 
         		return map;
     		}
    	}
     	
     	//징수의뢰 삭제
     	for(int i=0; i<arrNticCnts.length;i++) {
     		gamHtldRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamHtldRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamHtldRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamHtldRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamHtldRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		
            gamHtldRentFeeMngtService.deleteHtldRentFeeMngt(gamHtldRentFeeMngtVO);
 		}
 		
 		resultCode = 0; 
        resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
         
     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
         
 		return map;
     }
    
    /**
     * 고지의뢰를 한다.(단일처리)
     * @param gamHtldRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamInsertHtldRentFeeMngtNticSingle.do") 
    public @ResponseBody Map insertHtldRentFeeMngtNticSingle(
     	   @ModelAttribute("gamHtldRentFeeMngtVO") GamHtldRentFeeMngtVO gamHtldRentFeeMngtVO, 
     	   BindingResult bindingResult)
            throws Exception {
 	
     	 Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
         int anlrveLevCnt = 0;
 
       //세입징수에 기등록  여부
  		anlrveLevCnt = gamHtldRentFeeMngtService.selectAnlrveLevCnt(gamHtldRentFeeMngtVO);
  		
  		if( anlrveLevCnt > 0 ) {
  			map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject1")); //세입징수에 기존재하는 건이 있습니다. 확인후 다시 시도하십시오.
              
      		return map;
  		}
  		
  		//징수의뢰 정보조회
        GamHtldRentFeeMngtVO gamHtldRentFeeMngtInfo = gamHtldRentFeeMngtService.selectHtldRentFeeMngtInfo(gamHtldRentFeeMngtVO);
        
        if( EgovStringUtil.isEmpty(gamHtldRentFeeMngtInfo.getAccnutYear()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject2")); //회계년도가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.
             
     		return map;
        }
        
        if( EgovStringUtil.isEmpty(gamHtldRentFeeMngtInfo.getNticno()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject3")); //고지번호가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.
             
     		return map;
        }
        
        gamHtldRentFeeMngtInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
        gamHtldRentFeeMngtInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
        
        gamHtldRentFeeMngtService.insertAnlrveLev(gamHtldRentFeeMngtInfo);
         
        resultCode = 0; 
 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
        
     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
         
 		return map;
     }
    
    /**
     * 고지취소를 한다.(단일처리)
     * @param gamHtldRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamDeleteHtldRentFeeMngtNticSingle.do") 
    public @ResponseBody Map deleteHtldRentFeeMngtNticSingle(
     	   @ModelAttribute("gamHtldRentFeeMngtVO") GamHtldRentFeeMngtVO gamHtldRentFeeMngtVO, 
     	   BindingResult bindingResult)
            throws Exception {
 	
     	 Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
         int anlrveLevCnt = 0;
 
       //세입징수에 기등록  여부
  		anlrveLevCnt = gamHtldRentFeeMngtService.selectAnlrveLevCnt(gamHtldRentFeeMngtVO);
  		
  		if( anlrveLevCnt > 0 ) {
  			map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject1")); //세입징수에 기존재하는 건이 있습니다. 확인후 다시 시도하십시오.
              
      		return map;
  		}
  		
  		gamHtldRentFeeMngtService.deleteHtldRentFeeMngt(gamHtldRentFeeMngtVO);
         
        resultCode = 0; 
 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
        
     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
         
 		return map;
     }
    
}
