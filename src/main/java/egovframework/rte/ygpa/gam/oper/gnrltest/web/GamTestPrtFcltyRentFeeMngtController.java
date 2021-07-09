package egovframework.rte.ygpa.gam.oper.gnrltest.web;

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
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtVO;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamTestNticRequestMngtService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentFeeMngtVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentFeePaySttusMngtVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtService;

/**
 * @Class Name : GamTestPrtFcltyRentFeeMngtController.java
 * @Description : 항만시설사용료관리 (항만시설/일반부두/항만시설사용료관리)
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
public class GamTestPrtFcltyRentFeeMngtController {

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

    @Resource(name = "gamTestPrtFcltyRentMngtService")
    private GamTestPrtFcltyRentMngtService gamTestPrtFcltyRentMngtService;

    @Resource(name = "gamTestPrtFcltyRentFeeMngtService")
    private GamTestPrtFcltyRentFeeMngtService gamTestPrtFcltyRentFeeMngtService;

    @Resource(name = "gamTestNticRequestMngtService")
    private GamTestNticRequestMngtService gamTestNticRequestMngtService;


    /**
     * 항만시설사용료관리 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/gnrltest/GamTestPrtFcltyRentFeeMngt"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/gnrltest/gamTestPrtFcltyRentFeeMngt.do")
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

		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("reqstCdList", reqstCdList);
		model.addAttribute("nticMthCdList", nticMthCdList);
		model.addAttribute("usagePrposCdList", usagePrposCdList);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);
		model.addAttribute("fcltySeCdList", fcltySeCdList);
		model.addAttribute("rcivSeCdList", rcivSeCdList);*/
		model.addAttribute("windowId", windowId);
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("loginId", loginVO.getId());
		List cofixList = gamTestPrtFcltyRentMngtService.selectCofixInfo();
		model.addAttribute("cofixList", cofixList);

    	return "/ygpa/gam/oper/gnrltest/GamTestPrtFcltyRentFeeMngt";
    }

	/**
     * 항만시설사용료관리 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrltest/gamSelectPrtFcltyRentFeeMngtList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectPrtFcltyRentFeeMngtList(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception {

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
    	totalCnt = gamTestPrtFcltyRentFeeMngtService.selectPrtFcltyRentFeeMngtListTotCnt(searchVO);
    	List resultList = gamTestPrtFcltyRentFeeMngtService.selectPrtFcltyRentFeeMngtList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	//자료수, 사용료, 연체, 부가세, 고지액
    	GamTestPrtFcltyRentFeeMngtVO resultSum = gamTestPrtFcltyRentFeeMngtService.selectPrtFcltyRentFeeMngtSum(searchVO);

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
     * 항만시설사용료관리정보를 수정한다.
     * @param gamTestPrtFcltyRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamUpdatePrtFcltyRentFeeMngtFee.do")
    public @ResponseBody Map updatePrtFcltyRentFeeMngtFee(
     	   @ModelAttribute("gamTestPrtFcltyRentFeeMngtVO") GamTestPrtFcltyRentFeeMngtVO gamTestPrtFcltyRentFeeMngtVO,
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

     	gamTestPrtFcltyRentFeeMngtVO.setUpdUsr(loginVo.getId()); //수정자 (세션 로그인 아이디)

         gamTestPrtFcltyRentFeeMngtService.updatePrtFcltyRentFeeMngt(gamTestPrtFcltyRentFeeMngtVO);

         resultCode = 0;
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }

    /**
     * 고지의뢰를 한다. (다중처리)
     * @param gamTestPrtFcltyRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    /*
    @RequestMapping(value="/oper/gnrltest/gamInsertPrtFcltyRentFeeMngtFeeNtic.do")
    @ResponseBody Map<String, Object> insertPrtFcltyRentFeeMngtFeeNtic (
				  @RequestParam("nticCnts") String nticCnts
				 ,@RequestParam("prtAtCodes") String prtAtCodes
				 ,@RequestParam("mngYears") String mngYears
				 ,@RequestParam("mngNos") String mngNos
				 ,@RequestParam("mngCnts") String mngCnts
				 ,@RequestParam("nticnos") String nticnos
				 ,@RequestParam("accnutYears") String accnutYears
				 ,@ModelAttribute("gamTestPrtFcltyRentFeeMngtVO") GamTestPrtFcltyRentFeeMngtVO gamTestPrtFcltyRentFeeMngtVO
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
     		gamTestPrtFcltyRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setNticno(arrNticnos[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setAccnutYear(arrAccnutYears[i]);

     		//세입징수에 기등록  여부
     		anlrveLevCnt = gamTestPrtFcltyRentFeeMngtService.selectAnlrveLevCnt(gamTestPrtFcltyRentFeeMngtVO);

     		if( anlrveLevCnt > 0 ) {
     			map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject1")); //세입징수에 기존재하는 건이 있습니다. 확인후 다시 시도하십시오.

         		return map;
     		}

     		//징수의뢰 정보조회
            GamTestPrtFcltyRentFeeMngtVO gamTestPrtFcltyRentFeeMngtInfo = gamTestPrtFcltyRentFeeMngtService.selectPrtFcltyRentFeeMngtFeeInfo(gamTestPrtFcltyRentFeeMngtVO);

            if( EgovStringUtil.isEmpty(gamTestPrtFcltyRentFeeMngtInfo.getAccnutYear()) ) {
            	map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject2")); //회계년도가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.

         		return map;
            }

            if( EgovStringUtil.isEmpty(gamTestPrtFcltyRentFeeMngtInfo.getNticno()) ) {
            	map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject3")); //고지번호가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.

         		return map;
            }

    	}

     	//세입징수 등록
     	for(int i=0; i<arrNticCnts.length;i++) {
     		gamTestPrtFcltyRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngCnt(arrMngCnts[i]);

     		//징수의뢰 정보조회
            GamTestPrtFcltyRentFeeMngtVO gamTestPrtFcltyRentFeeMngtInfo = gamTestPrtFcltyRentFeeMngtService.selectPrtFcltyRentFeeMngtFeeInfo(gamTestPrtFcltyRentFeeMngtVO);

            gamTestPrtFcltyRentFeeMngtInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
            gamTestPrtFcltyRentFeeMngtInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)

            gamTestPrtFcltyRentFeeMngtService.insertAnlrveLev(gamTestPrtFcltyRentFeeMngtInfo);
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
     * @param gamTestPrtFcltyRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamInsertPrtFcltyRentFeeMngtFeeNtic.do")
    @ResponseBody Map<String, Object> insertPrtFcltyRentFeeMngtFeeNtic (
				  @RequestParam("nticCnts") String nticCnts
				 ,@RequestParam("prtAtCodes") String prtAtCodes
				 ,@RequestParam("mngYears") String mngYears
				 ,@RequestParam("mngNos") String mngNos
				 ,@RequestParam("mngCnts") String mngCnts
				 ,@RequestParam("nticnos") String nticnos
				 ,@RequestParam("accnutYears") String accnutYears
				 ,@ModelAttribute("gamTestPrtFcltyRentFeeMngtVO") GamTestPrtFcltyRentFeeMngtVO gamTestPrtFcltyRentFeeMngtVO
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

        try{
            String [] arrNticCnts = null;
            if(nticCnts != null) arrNticCnts = nticCnts.split(";");
         	String [] arrPrtAtCodes = null;
         	if(prtAtCodes != null) arrPrtAtCodes = prtAtCodes.split(";");
         	String [] arrMngYears = null;
         	if(mngYears != null) arrMngYears = mngYears.split(";");
         	String [] arrMngNos = null;
         	if(mngNos != null) arrMngNos = mngNos.split(";");
         	String [] arrMngCnts = null;
         	if(mngCnts != null) arrMngCnts = mngCnts.split(";");
         	String [] arrNticnos = null;
         	if(nticnos != null) arrNticnos = nticnos.split(";");
         	String [] arrAccnutYears = null;
         	if(accnutYears != null) arrAccnutYears = accnutYears.split(";");

     	//세입징수 등록
     	for(int i=0; i<arrNticCnts.length;i++) {
     		gamTestPrtFcltyRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setRegUsr(loginVO.getId());
     		gamTestPrtFcltyRentFeeMngtVO.setUpdUsr(loginVO.getId());

     		//System.out.println("############################# 고지의뢰 CALL!! START ");

     		paramMap.put("nticCnt", gamTestPrtFcltyRentFeeMngtVO.getNticCnt());
     		paramMap.put("prtAtCode", gamTestPrtFcltyRentFeeMngtVO.getPrtAtCode());
     		paramMap.put("mngYear", gamTestPrtFcltyRentFeeMngtVO.getMngYear());
     		paramMap.put("mngNo", gamTestPrtFcltyRentFeeMngtVO.getMngNo());
     		paramMap.put("mngCnt", gamTestPrtFcltyRentFeeMngtVO.getMngCnt());
            //System.out.println("##################################### paramMap => " + paramMap);

            //이곳에 고지의뢰 서비스콜!! 삽입할것!!
            //gamTestPrtFcltyRentFeeMngtService.insertAnlrveLev(gamTestPrtFcltyRentFeeMngtInfo);
 		}

 		resultCode = 0;
        resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        }catch(NullPointerException npe){
        	log.info(npe.getMessage());
        }catch(Exception e){
        	log.info(e.getMessage());
        }
 		return map;
     }

    /**
     * 고지취소를 한다. (다중처리)
     * @param gamTestPrtFcltyRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    /*
    @RequestMapping(value="/oper/gnrltest/gamDeletePrtFcltyRentFeeMngtFeeNtic.do")
    @ResponseBody Map<String, Object> deletePrtFcltyRentFeeMngtFeeNtic (
				  @RequestParam("nticCnts") String nticCnts
				 ,@RequestParam("prtAtCodes") String prtAtCodes
				 ,@RequestParam("mngYears") String mngYears
				 ,@RequestParam("mngNos") String mngNos
				 ,@RequestParam("mngCnts") String mngCnts
				 ,@RequestParam("nticnos") String nticnos
				 ,@RequestParam("accnutYears") String accnutYears
				 ,@ModelAttribute("gamTestPrtFcltyRentFeeMngtVO") GamTestPrtFcltyRentFeeMngtVO gamTestPrtFcltyRentFeeMngtVO
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
     		gamTestPrtFcltyRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setNticno(arrNticnos[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setAccnutYear(arrAccnutYears[i]);

     		//세입징수에 기등록  여부
     		anlrveLevCnt = gamTestPrtFcltyRentFeeMngtService.selectAnlrveLevCnt(gamTestPrtFcltyRentFeeMngtVO);

     		if( anlrveLevCnt > 0 ) {
     			map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject1")); //세입징수에 기존재하는 건이 있습니다. 확인후 다시 시도하십시오.

         		return map;
     		}
    	}

     	//징수의뢰 삭제
     	for(int i=0; i<arrNticCnts.length;i++) {
     		gamTestPrtFcltyRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngCnt(arrMngCnts[i]);

            gamTestPrtFcltyRentFeeMngtService.deletePrtFcltyRentFeeMngtFee(gamTestPrtFcltyRentFeeMngtVO);
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
     * @param gamTestPrtFcltyRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamDeletePrtFcltyRentFeeMngtFeeNtic.do")
    @ResponseBody Map<String, Object> deletePrtFcltyRentFeeMngtFeeNtic (
				  @RequestParam("nticCnts") String nticCnts
				 ,@RequestParam("prtAtCodes") String prtAtCodes
				 ,@RequestParam("mngYears") String mngYears
				 ,@RequestParam("mngNos") String mngNos
				 ,@RequestParam("mngCnts") String mngCnts
				 ,@RequestParam("nticnos") String nticnos
				 ,@RequestParam("accnutYears") String accnutYears
				 ,@ModelAttribute("gamTestPrtFcltyRentFeeMngtVO") GamTestPrtFcltyRentFeeMngtVO gamTestPrtFcltyRentFeeMngtVO
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

        try{
            String [] arrNticCnts = null;
            if(nticCnts != null) arrNticCnts = nticCnts.split(";");
         	String [] arrPrtAtCodes = null;
         	if(prtAtCodes != null) arrPrtAtCodes = prtAtCodes.split(";");
         	String [] arrMngYears = null;
         	if(mngYears != null) arrMngYears = mngYears.split(";");
         	String [] arrMngNos = null;
         	if(mngNos != null) arrMngNos = mngNos.split(";");
         	String [] arrMngCnts = null;
         	if(mngCnts != null) arrMngCnts = mngCnts.split(";");
         	String [] arrNticnos = null;
         	if(nticnos != null) arrNticnos = nticnos.split(";");
         	String [] arrAccnutYears = null;
         	if(accnutYears != null) arrAccnutYears = accnutYears.split(";");

     	//세입징수 등록
     	for(int i=0; i<arrNticCnts.length;i++) {
     		gamTestPrtFcltyRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamTestPrtFcltyRentFeeMngtVO.setRegUsr(loginVO.getId());
     		gamTestPrtFcltyRentFeeMngtVO.setUpdUsr(loginVO.getId());

     		//System.out.println("############################# 고지취소 CALL!! START ");
            //gamTestPrtFcltyRentFeeMngtService.insertAnlrveLev(gamTestPrtFcltyRentFeeMngtInfo);
     		paramMap.put("nticCnt", gamTestPrtFcltyRentFeeMngtVO.getNticCnt());
     		paramMap.put("prtAtCode", gamTestPrtFcltyRentFeeMngtVO.getPrtAtCode());
     		paramMap.put("mngYear", gamTestPrtFcltyRentFeeMngtVO.getMngYear());
     		paramMap.put("mngNo", gamTestPrtFcltyRentFeeMngtVO.getMngNo());
     		paramMap.put("mngCnt", gamTestPrtFcltyRentFeeMngtVO.getMngCnt());
            //System.out.println("##################################### paramMap => " + paramMap);

            //이곳에 고지취소 서비스콜!! 삽입할것!!
            //gamTestPrtFcltyRentFeeMngtService.insertAnlrveLev(gamTestPrtFcltyRentFeeMngtInfo);
 		}

 		resultCode = 0;
        resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        }catch(NullPointerException npe){
        	log.info(npe.getMessage());
        }catch(Exception e){
        	log.info(e.getMessage());
        }
 		return map;
     }

    /**
     * 고지의뢰를 한다.(단일처리)
     * @param gamTestPrtFcltyRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    /*
    @RequestMapping(value="/oper/gnrltest/gamInsertPrtFcltyRentFeeMngtFeeNticSingle.do")
    public @ResponseBody Map insertPrtFcltyRentFeeMngtFeeNticSingle(
     	   @ModelAttribute("gamTestPrtFcltyRentFeeMngtVO") GamTestPrtFcltyRentFeeMngtVO gamTestPrtFcltyRentFeeMngtVO,
     	   BindingResult bindingResult)
            throws Exception {

     	 Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
         int anlrveLevCnt = 0;

       //세입징수에 기등록  여부
  		anlrveLevCnt = gamTestPrtFcltyRentFeeMngtService.selectAnlrveLevCnt(gamTestPrtFcltyRentFeeMngtVO);

  		if( anlrveLevCnt > 0 ) {
  			map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject1")); //세입징수에 기존재하는 건이 있습니다. 확인후 다시 시도하십시오.

      		return map;
  		}

  		//징수의뢰 정보조회
        GamTestPrtFcltyRentFeeMngtVO gamTestPrtFcltyRentFeeMngtInfo = gamTestPrtFcltyRentFeeMngtService.selectPrtFcltyRentFeeMngtFeeInfo(gamTestPrtFcltyRentFeeMngtVO);

        if( EgovStringUtil.isEmpty(gamTestPrtFcltyRentFeeMngtInfo.getAccnutYear()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject2")); //회계년도가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.

     		return map;
        }

        if( EgovStringUtil.isEmpty(gamTestPrtFcltyRentFeeMngtInfo.getNticno()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject3")); //고지번호가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.

     		return map;
        }

        gamTestPrtFcltyRentFeeMngtInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
        gamTestPrtFcltyRentFeeMngtInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)

        gamTestPrtFcltyRentFeeMngtService.insertAnlrveLev(gamTestPrtFcltyRentFeeMngtInfo);

        resultCode = 0;
 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

 		return map;
     }
     */

    /**
     * 고지의뢰를 한다.(단일처리)
     * @param gamTestPrtFcltyRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamInsertPrtFcltyRentFeeMngtFeeNticSingle.do")
    public @ResponseBody Map insertPrtFcltyRentFeeMngtFeeNticSingle(
     	   @ModelAttribute("gamTestPrtFcltyRentFeeMngtVO") GamTestPrtFcltyRentFeeMngtVO gamTestPrtFcltyRentFeeMngtVO,
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

        paramMap.put("nticCnt", gamTestPrtFcltyRentFeeMngtVO.getNticCnt());
 		paramMap.put("prtAtCode", gamTestPrtFcltyRentFeeMngtVO.getPrtAtCode());
 		paramMap.put("mngYear", gamTestPrtFcltyRentFeeMngtVO.getMngYear());
 		paramMap.put("mngNo", gamTestPrtFcltyRentFeeMngtVO.getMngNo());
 		paramMap.put("mngCnt", gamTestPrtFcltyRentFeeMngtVO.getMngCnt());

 		System.out.println("##################################### paramMap => " + paramMap);

 		 //이곳에 고지의뢰 서비스콜!! 삽입할것!!
//        gamTestPrtFcltyRentFeeMngtService.insertAnlrveLev(gamTestPrtFcltyRentFeeMngtInfo);

        resultCode = 0;
 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

 		return map;
     }

    /**
     * 고지취소를 한다.(단일처리)
     * @param gamTestPrtFcltyRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamDeletePrtFcltyRentFeeMngtFeeNticSingle.do")
    public @ResponseBody Map deletePrtFcltyRentFeeMngtFeeNticSingle(
     	   @ModelAttribute("gamTestPrtFcltyRentFeeMngtVO") GamTestPrtFcltyRentFeeMngtVO gamTestPrtFcltyRentFeeMngtVO,
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

        paramMap.put("nticCnt", gamTestPrtFcltyRentFeeMngtVO.getNticCnt());
 		paramMap.put("prtAtCode", gamTestPrtFcltyRentFeeMngtVO.getPrtAtCode());
 		paramMap.put("mngYear", gamTestPrtFcltyRentFeeMngtVO.getMngYear());
 		paramMap.put("mngNo", gamTestPrtFcltyRentFeeMngtVO.getMngNo());
 		paramMap.put("mngCnt", gamTestPrtFcltyRentFeeMngtVO.getMngCnt());

 		System.out.println("##################################### paramMap => " + paramMap);

 		 //이곳에 고지의뢰 서비스콜!! 삽입할것!!
        //gamTestPrtFcltyRentFeeMngtService.insertAnlrveLev(gamTestPrtFcltyRentFeeMngtInfo);

        resultCode = 0;
 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

 		return map;
     }

    /**
     * 항만시설사용료관리정보를 등록한다.
     * @param gamTestPrtFcltyRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamInsertPrtFcltyRentFeeMngtFee.do")
    public @ResponseBody Map insertPrtFcltyRentFeeMngtFee(
     	   @ModelAttribute("gamTestPrtFcltyRentFeeMngtVO") GamTestPrtFcltyRentFeeMngtVO gamTestPrtFcltyRentFeeMngtVO,
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
 		 gamTestPrtFcltyRentFeeMngtVO.setRegUsr(loginVO.getId());
         gamTestPrtFcltyRentFeeMngtVO.setUpdUsr(loginVO.getId());

         gamTestPrtFcltyRentFeeMngtService.insertPrtFcltyRentFeeMngtLevReqest(gamTestPrtFcltyRentFeeMngtVO);

         resultCode = 0;
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }

    /**
     * 투자비보전 상계처리를 한다. 2015.12.17 김종민
     * @param gamTestPrtFcltyRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/oper/gnrltest/updatePrtFcltyRentFeeSaveSingle.do")
    public @ResponseBody Map updatePrtFcltyRentFeeSaveSingle(
     	   @ModelAttribute("gamTestPrtFcltyRentFeeMngtVO") GamTestPrtFcltyRentFeeMngtVO gamTestPrtFcltyRentFeeMngtVO,
     	   BindingResult bindingResult)
            throws Exception {
     	Map map = new HashMap();

     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
 	        map.put("resultCode", 1);
     		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
         	return map;
     	}

    	try {
    		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
 		 	gamTestPrtFcltyRentFeeMngtVO.setRegUsr(loginVO.getId());
 		 	gamTestPrtFcltyRentFeeMngtVO.setUpdUsr(loginVO.getId());

         	gamTestPrtFcltyRentFeeMngtService.updatePrtFcltyRentFeeSaveSingle(gamTestPrtFcltyRentFeeMngtVO);

     	 	map.put("resultCode", 0);
     	 	map.put("resultMsg", egovMessageSource.getMessage("gam.asset.proc"));
    	} catch (IOException i) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
    	}
    	catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
    	}
 		return map;
     }

    /**
     * 투자비보전 상계취소를 한다. 2015.12.17 김종민
     * @param gamTestPrtFcltyRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/oper/gnrltest/cancelPrtFcltyRentFeeSaveSingle.do")
    public @ResponseBody Map cancelPrtFcltyRentFeeSaveSingle(
     	   @ModelAttribute("gamTestPrtFcltyRentFeeMngtVO") GamTestPrtFcltyRentFeeMngtVO gamTestPrtFcltyRentFeeMngtVO,
     	   BindingResult bindingResult)
            throws Exception {
     	Map map = new HashMap();

     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
 	        map.put("resultCode", 1);
     		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
         	return map;
     	}

    	try {
    		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
 		 	gamTestPrtFcltyRentFeeMngtVO.setRegUsr(loginVO.getId());
 		 	gamTestPrtFcltyRentFeeMngtVO.setUpdUsr(loginVO.getId());

         	gamTestPrtFcltyRentFeeMngtService.cancelPrtFcltyRentFeeSaveSingle(gamTestPrtFcltyRentFeeMngtVO);

     	 	map.put("resultCode", 0);
     	 	map.put("resultMsg", egovMessageSource.getMessage("gam.asset.proc"));
    	} catch (IOException i) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
    	} catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
    	}
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
	@RequestMapping(value="/oper/gnrltest/insertPrtFcltyRentFeeNticSingle.do")
    public @ResponseBody Map insertAssetRentFeeNticSingle(
     	   @ModelAttribute("gamAssetRentFeeMngtVO") GamTestPrtFcltyRentFeeMngtVO gamAssetRentFeeMngtVO,
     	   @ModelAttribute("modifyFee") String modifyFee,
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

    		if("modified".equals(modifyFee)) {
    			gamAssetRentFeeMngtVO.setUpdUsr(loginVo.getId());
        		gamTestPrtFcltyRentFeeMngtService.updatePrtFcltyRentFee(gamAssetRentFeeMngtVO);	// 사용료를 변경한다.
    		}

    		paramMap.put("updUsr", loginVo.getId());
	        paramMap.put("nticCnt", gamAssetRentFeeMngtVO.getNticCnt());
	 		paramMap.put("prtAtCode", gamAssetRentFeeMngtVO.getPrtAtCode());
	 		paramMap.put("mngYear", gamAssetRentFeeMngtVO.getMngYear());
	 		paramMap.put("mngNo", gamAssetRentFeeMngtVO.getMngNo());
	 		paramMap.put("mngCnt", gamAssetRentFeeMngtVO.getMngCnt());
	 		paramMap.put("chrgeKnd", gamAssetRentFeeMngtVO.getChrgeKnd());
	 		paramMap.put("reimChrgeKnd", "DB");
	 		paramMap.put("payTmlmt", gamAssetRentFeeMngtVO.getPayTmlmt());
	 		paramMap.put("frstPayTmlmt", gamAssetRentFeeMngtVO.getPayTmlmt());
	 		paramMap.put("deptCd", loginVo.getDeptCd());
	 		paramMap.put("nhtPrintYn", "N"); //고지서 출력 여부 기본 N 설정
	 		paramMap.put("userName", loginVo.getName());
	 		//paramMap.put("chrgeKnd", gamAssetRentFeeMngtVO.getNticAmt());
	 		paramMap.put("fee", gamAssetRentFeeMngtVO.getIntrAmnt());
	 		paramMap.put("intrAmnt", gamAssetRentFeeMngtVO.getIntrAmnt());
	 		paramMap.put("vat", gamAssetRentFeeMngtVO.getVat());
	 		gamTestNticRequestMngtService.sendNticRequest(paramMap);

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
     * 고지취소를 한다.(단일처리)
     * @param gamAssetRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/oper/gnrltest/cancelPrtFcltyRentFeeNticSingle.do")
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
	 		paramMap.put("deptCd", loginVo.getDeptCd());

	 		gamTestNticRequestMngtService.cancelNticRequest(paramMap);

	        resultCode = 0;
	 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
    	} catch (IOException i) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
    	} catch(Exception e) {
	        resultCode = -1;
	 		resultMsg  = egovMessageSource.getMessage("fail.cancelNticIssue.msg");
    	}

     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

 		return map;
     }

        @RequestMapping(value="/oper/gnrltest/printPrtFcltyRentFeeTaxNotice.do")
        String printAssetRentFeeTaxNotice(@RequestParam Map<String, Object> approvalOpt, ModelMap model) throws Exception {
        	model.addAttribute("searchOpt", approvalOpt);

        	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        	if(!isAuthenticated) {
        		model.addAttribute("resultCode", 1);
        		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	}
        	else {
//        		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        		List list = gamTestPrtFcltyRentFeeMngtService.selectTaxNtcPrintInfo(approvalOpt);

//        		model.addAttribute("emplyrNo", loginVo.getEmplNo());

        		model.addAttribute("resultCode", 0);
        		model.addAttribute("resultList", list);
        		model.addAttribute("resultMsg", "");
        	}

        	return "ygpa/gam/oper/gnrltest/GamTestPrtFcltyRentPrintTaxNoticeIssue";
        	}

        @RequestMapping(value="/oper/gnrltest/updatePrtFcltyRentFeeMngtListDetail.do")
	    public @ResponseBody Map updateAssetRentFeeMngtListDetail(
	     	   @ModelAttribute("gamTestPrtFcltyRentFeeMngtVO") GamTestPrtFcltyRentFeeMngtVO gamTestPrtFcltyRentFeeMngtVO,
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
	    		gamTestPrtFcltyRentFeeMngtVO.setUpdUsr(loginVo.getId()); //수정자 (세션 로그인 아이디)
	    		gamTestPrtFcltyRentFeeMngtService.updateAssetRentFeeMngtListDetail(gamTestPrtFcltyRentFeeMngtVO);
		         resultCode = 0;
		 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
	    	} catch (IOException i) {
	    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
	    	} catch(Exception e) {
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
	    @RequestMapping(value="/oper/gnrltest/gamSelectPrtFcltyRentFeeMngtListDetail.do", method=RequestMethod.POST)
		public @ResponseBody Map gamSelectAssetRentFeeMngtListDetail(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception {

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

			//자산임대상세
	    	List resultList = gamTestPrtFcltyRentFeeMngtService.selectAssetRentFeeDetailList(searchVO);
	    	Map master = gamTestPrtFcltyRentFeeMngtService.selectAssetRentFeeDetailMstPk(searchVO);
	    	Map summary = gamTestPrtFcltyRentFeeMngtService.selectAssetRentFeeDetailSumPk(searchVO);

	        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());


	    	map.put("resultCode", 0);	// return ok
	    	map.put("resultList", resultList);
	    	map.put("resultMaster", master);
	    	map.put("resultSummary", summary);
	    	map.put("searchOption", searchVO);

	    	return map;
	    }


		@SuppressWarnings({ "rawtypes", "unchecked" })
	    @RequestMapping(value="/oper/gnrltest/selectPrtFcltyRentFeeMngtListExcel.do", method=RequestMethod.POST)
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
	    	GamTestPrtFcltyRentFeeMngtVO searchVO= new GamTestPrtFcltyRentFeeMngtVO();

	        header = mapper.readValue((String)excelParam.get("header"),
				    new TypeReference<List<HashMap<String,String>>>(){});

	        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.

			// 조회 조건
			searchVO = mapper.convertValue(excelParam, GamTestPrtFcltyRentFeeMngtVO.class);

			searchVO.setFirstIndex(0);
			searchVO.setLastIndex(9999);
			searchVO.setRecordCountPerPage(9999);

			/** List Data */
//	    	int totCnt = erpAssetCdService.selectErpAssetCdListTotCnt(searchVO);

	    	List gamAssetList = gamTestPrtFcltyRentFeeMngtService.selectPrtFcltyRentFeeMngtList(searchVO);

	    	map.put("resultList", gamAssetList);
	    	map.put("header", header);

	    	return new ModelAndView("gridExcelView", "gridResultMap", map);
	    }

		/**
	     * 고지서를 출력한다.
	     * @param approvalOpt
	     * @param model
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value="/oper/gnrltest/printRentFeeNotice.do")
	    String printRentFeeNotice(GamTestPrtFcltyRentFeeMngtVO approvalOpt, ModelMap model) throws Exception {
	    	String report = "ygpa/gam/oper/gnrltest/GamTestPrtfcltyPrintNoticeIssue"; //"ygpa/gam/oper/gnrltest/GamTestPrtFcltyPrintNoticeIssue";
	
	    	model.addAttribute("searchOpt", approvalOpt);

	    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	if(!isAuthenticated) {
	    		model.addAttribute("resultCode", 1);
	    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
	    	}
	    	else {
	    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

	    		//Map printInfo = gamTestPrtFcltyRentFeeMngtService.selectNpticPrintInfo(approvalOpt);
	    		List printList = gamTestPrtFcltyRentFeeMngtService.selectNpticPrintInfo(approvalOpt);
	    		Map printInfo = null;
	    		int etcCnt = 0;
	    		if(printList.size() > 0) {
	    			printInfo = (Map) printList.get(0);
	    			etcCnt = printList.size() - 1;
	    		}
	    		if("ORGNZT_0000000000007".equals(loginVo.getOrgnztId()) || "14010".equals(loginVo.getEmplNo())) {
//	    			log.debug("new paper selected");
	    			report = "ygpa/gam/oper/gnrltest/GamTestPrtfcltyPrintNoticeIssue2";	// 신규 고지서
	    		}
//	    		model.addAttribute("emplyrNo", loginVo.getEmplNo());

	    		model.addAttribute("resultCode", 0);
	    		model.addAttribute("result", printInfo);
	    		model.addAttribute("etcCnt", etcCnt);
	    		model.addAttribute("resultMsg", "");
	    	}

	    	return report;
    	}


	    /**
	     * 인쇄 상태를 업데이트 한다.
	     * @param gamAssetRentFeeMngtVO
	     * @param bindingResult
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value="/oper/gnrltest/printRentFeeNoticeIssue.do")
	    public @ResponseBody Map printRentFeeNoticeIssue(GamTestPrtFcltyRentFeeMngtVO vo)
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
	    		vo.setUpdUsr(loginVo.getId());
	    		vo.setNhtPrintYn("Y");
	    		map.put("prtAtCode", vo.getPrtAtCode());
	    		map.put("mngYear", vo.getMngYear());
	    		map.put("mngNo", vo.getMngNo());
	    		map.put("mngCnt", vo.getMngCnt());
	    		map.put("nticCnt", vo.getNticCnt());
	    		map.put("chrgeKnd", vo.getChrgeKnd());
	    		map.put("nhtPrintYn", vo.getNhtPrintYn());

	    		gamTestNticRequestMngtService.updateNticPrintState(map);
		         resultCode = 0;
		 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
	    	} catch (IOException i) {
	    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
	    	} catch(Exception e) {
		         resultCode = 0;
		 		 resultMsg  = egovMessageSource.getMessage("fail.common.update"); // 오류 발생
	    	}

	     	 map.put("resultCode", resultCode);
	         map.put("resultMsg", resultMsg);

	 		return map;
	     }

	    /**
	     * 분리 고지된 고지서의 인쇄 상태를 업데이트 한다.
	     * @param gamAssetRentFeeMngtVO
	     * @param bindingResult
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value="/oper/gnrltest/printRentFeeSepNoticeIssue.do")
	    public @ResponseBody Map printRentFeeSepNoticeIssue(GamTestPrtFcltyRentFeeMngtVO vo)
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
	    		vo.setUpdUsr(loginVo.getId());
	    		vo.setNhtPrintYn("Y");
	    		map.put("prtAtCode", vo.getPrtAtCode());
	    		map.put("mngYear", vo.getMngYear());
	    		map.put("mngNo", vo.getMngNo());
	    		map.put("mngCnt", vo.getMngCnt());
	    		map.put("nticCnt", vo.getNticCnt());
	    		map.put("chrgeKnd", vo.getChrgeKnd());
	    		map.put("nhtPrintYn", vo.getNhtPrintYn());

	    		gamTestNticRequestMngtService.updateNticPrintState2(map);
		         resultCode = 0;
		 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
	    	} catch (IOException i) {
	    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
	    	} catch(Exception e) {
		         resultCode = 0;
		 		 resultMsg  = egovMessageSource.getMessage("fail.common.update"); // 오류 발생
	    	}

	     	 map.put("resultCode", resultCode);
	         map.put("resultMsg", resultMsg);

	 		return map;
	     }
}
