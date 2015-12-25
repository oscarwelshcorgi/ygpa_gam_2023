package egovframework.rte.ygpa.gam.oper.htld.web;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtVO;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeMngtVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeDefaultVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtVO;

/**
 * @Class Name : GamHtldRentFeeMngtController.java
 * @Description : 배후단지임대료관리
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


    @Resource(name = "gamNticRequestMngtService")
    private GamNticRequestMngtService gamNticRequestMngtService;

    /**
     * 배후단지임대료관리 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/htld/GamHtldRentFeeMngt"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htld/gamHtldRentFeeMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
/*
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
		model.addAttribute("rcivSeCdList", rcivSeCdList);*/
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/oper/htld/GamHtldRentFeeMngt";
    }

	/**
     * 배후단지임대료관리 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/gamSelectHtldRentFeeMngtList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldRentFeeMngtList(GamHtldRentFeeDefaultVO searchVO) throws Exception {

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
    	totalCnt = gamHtldRentFeeMngtService.selectHtldRentFeeMngtListTotCnt(searchVO);
    	List resultList = gamHtldRentFeeMngtService.selectHtldRentFeeMngtList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	//자료수, 사용료, 연체, 부가세, 고지액
    	Map resultSum = gamHtldRentFeeMngtService.selectHtldRentFeeMngtSum(searchVO);

    	Map resultCofix = gamHtldRentFeeMngtService.selectHtldCofixPk(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	map.put("resultSum", resultSum);
    	map.put("cofixIntr", resultCofix.get("intrRate"));

    	return map;
    }

    @RequestMapping(value="/oper/htld/updateHtldRentFee.do")
    public @ResponseBody Map updateHtldRentFee(
     	   @ModelAttribute("gamHtldRentFeeMngtVO") GamHtldRentFeeMngtVO gamHtldRentFeeMngtVO,
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

     	gamHtldRentFeeMngtVO.setUpdUsr(loginVo.getId()); //수정자 (세션 로그인 아이디)

         gamHtldRentFeeMngtService.updateHtldRentFeeMngt(gamHtldRentFeeMngtVO);

         resultCode = 0;
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }

    @RequestMapping(value="/oper/htld/clearHtldRentFeeList.do")
    public @ResponseBody Map clearHtldRentFeeList(
    		GamHtldRentFeeDefaultVO gamHtldRentFeeMngtVO,
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

     	gamHtldRentFeeMngtVO.setUpdUsr(loginVo.getId()); //수정자 (세션 로그인 아이디)

         gamHtldRentFeeMngtService.clearHtldRentFeeList(gamHtldRentFeeMngtVO);

         resultCode = 0;
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/gamSelectHtldRentFeeMngtListExcel.do", method=RequestMethod.POST)
    @ResponseBody ModelAndView selectHtldRentFeeMngtListExcel(@RequestParam Map<String, Object> excelParam) throws Exception {
		Map map = new HashMap();
		List header;
		String fileName="";
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
    	GamHtldRentFeeDefaultVO searchVO = new GamHtldRentFeeDefaultVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.
        fileName = (String)excelParam.get("fileName");
        excelParam.remove("fileName");	// 파라미터에서 헤더를 삭제 한다.
		// 조회 조건
		searchVO = mapper.convertValue(excelParam, GamHtldRentFeeDefaultVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		/** List Data */
//    	int totCnt = erpAssetCdService.selectErpAssetCdListTotCnt(searchVO);
    	List resultList = gamHtldRentFeeMngtService.selectHtldRentFeeMngtList(searchVO);

    	map.put("resultList", resultList);
    	map.put("header", header);
    	map.put("fileName", fileName);

    	return new ModelAndView("gridHtldFeeView", "gridResultMap", map);
    }

	/**
     * 배후단지임대료관리정보를 수정한다.
     * @param gamHtldRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamUpdateHtldRentFeeMngtFee.do")
    public @ResponseBody Map updateHtldRentFeeMngtFee(
     	   @ModelAttribute("gamHtldRentFeeMngtVO") GamHtldRentFeeMngtVO gamHtldRentFeeMngtVO,
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

     	gamHtldRentFeeMngtVO.setUpdUsr(loginVo.getId()); //수정자 (세션 로그인 아이디)

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
    /*
    @RequestMapping(value="/oper/htld/gamInsertHtldRentFeeMngtFeeNtic.do")
    @ResponseBody Map<String, Object> insertHtldRentFeeMngtFeeNtic (
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
            GamHtldRentFeeMngtVO gamHtldRentFeeMngtInfo = gamHtldRentFeeMngtService.selectHtldRentFeeMngtFeeInfo(gamHtldRentFeeMngtVO);

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
            GamHtldRentFeeMngtVO gamHtldRentFeeMngtInfo = gamHtldRentFeeMngtService.selectHtldRentFeeMngtFeeInfo(gamHtldRentFeeMngtVO);

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
    */

    /**
     * 고지의뢰를 한다. (다중처리)
     * @param gamHtldRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamInsertHtldRentFeeMngtFeeNtic.do")
    @ResponseBody Map<String, Object> insertHtldRentFeeMngtFeeNtic (
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
     		gamHtldRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamHtldRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamHtldRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamHtldRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamHtldRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamHtldRentFeeMngtVO.setRegUsr(loginVO.getId());
     		gamHtldRentFeeMngtVO.setUpdUsr(loginVO.getId());

     		System.out.println("############################# 고지의뢰 CALL!! START ");

     		paramMap.put("nticCnt", gamHtldRentFeeMngtVO.getNticCnt());
     		paramMap.put("prtAtCode", gamHtldRentFeeMngtVO.getPrtAtCode());
     		paramMap.put("mngYear", gamHtldRentFeeMngtVO.getMngYear());
     		paramMap.put("mngNo", gamHtldRentFeeMngtVO.getMngNo());
     		paramMap.put("mngCnt", gamHtldRentFeeMngtVO.getMngCnt());
            System.out.println("##################################### paramMap => " + paramMap);

            //이곳에 고지의뢰 서비스콜!! 삽입할것!!
            //gamHtldRentFeeMngtService.insertAnlrveLev(gamHtldRentFeeMngtInfo);
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
    /*
    @RequestMapping(value="/oper/htld/gamDeleteHtldRentFeeMngtFeeNtic.do")
    @ResponseBody Map<String, Object> deleteHtldRentFeeMngtFeeNtic (
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

            gamHtldRentFeeMngtService.deleteHtldRentFeeMngtFee(gamHtldRentFeeMngtVO);
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
     * @param gamHtldRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamDeleteHtldRentFeeMngtFeeNtic.do")
    @ResponseBody Map<String, Object> deleteHtldRentFeeMngtFeeNtic (
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
     		gamHtldRentFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamHtldRentFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamHtldRentFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamHtldRentFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamHtldRentFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamHtldRentFeeMngtVO.setRegUsr(loginVO.getId());
     		gamHtldRentFeeMngtVO.setUpdUsr(loginVO.getId());

     		System.out.println("############################# 고지취소 CALL!! START ");
            //gamHtldRentFeeMngtService.insertAnlrveLev(gamHtldRentFeeMngtInfo);
     		paramMap.put("nticCnt", gamHtldRentFeeMngtVO.getNticCnt());
     		paramMap.put("prtAtCode", gamHtldRentFeeMngtVO.getPrtAtCode());
     		paramMap.put("mngYear", gamHtldRentFeeMngtVO.getMngYear());
     		paramMap.put("mngNo", gamHtldRentFeeMngtVO.getMngNo());
     		paramMap.put("mngCnt", gamHtldRentFeeMngtVO.getMngCnt());
            System.out.println("##################################### paramMap => " + paramMap);

            //이곳에 고지취소 서비스콜!! 삽입할것!!
            //gamHtldRentFeeMngtService.insertAnlrveLev(gamHtldRentFeeMngtInfo);
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
    /*
    @RequestMapping(value="/oper/htld/gamInsertHtldRentFeeMngtFeeNticSingle.do")
    public @ResponseBody Map insertHtldRentFeeMngtFeeNticSingle(
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
        GamHtldRentFeeMngtVO gamHtldRentFeeMngtInfo = gamHtldRentFeeMngtService.selectHtldRentFeeMngtFeeInfo(gamHtldRentFeeMngtVO);

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
     */

    /**
     * 고지의뢰를 한다.(단일처리)
     * @param gamHtldRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamInsertHtldRentFeeMngtFeeNticSingle.do")
    public @ResponseBody Map insertHtldRentFeeMngtFeeNticSingle(
     	   @ModelAttribute("gamHtldRentFeeMngtVO") GamHtldRentFeeMngtVO gamHtldRentFeeMngtVO,
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

        paramMap.put("nticCnt", gamHtldRentFeeMngtVO.getNticCnt());
 		paramMap.put("prtAtCode", gamHtldRentFeeMngtVO.getPrtAtCode());
 		paramMap.put("mngYear", gamHtldRentFeeMngtVO.getMngYear());
 		paramMap.put("mngNo", gamHtldRentFeeMngtVO.getMngNo());
 		paramMap.put("mngCnt", gamHtldRentFeeMngtVO.getMngCnt());

 		System.out.println("##################################### paramMap => " + paramMap);

 		 //이곳에 고지의뢰 서비스콜!! 삽입할것!!
        //gamHtldRentFeeMngtService.insertAnlrveLev(gamHtldRentFeeMngtInfo);

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
    @RequestMapping(value="/oper/htld/gamDeleteHtldRentFeeMngtFeeNticSingle.do")
    public @ResponseBody Map deleteHtldRentFeeMngtFeeNticSingle(
     	   @ModelAttribute("gamHtldRentFeeMngtVO") GamHtldRentFeeMngtVO gamHtldRentFeeMngtVO,
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

        paramMap.put("nticCnt", gamHtldRentFeeMngtVO.getNticCnt());
 		paramMap.put("prtAtCode", gamHtldRentFeeMngtVO.getPrtAtCode());
 		paramMap.put("mngYear", gamHtldRentFeeMngtVO.getMngYear());
 		paramMap.put("mngNo", gamHtldRentFeeMngtVO.getMngNo());
 		paramMap.put("mngCnt", gamHtldRentFeeMngtVO.getMngCnt());

 		System.out.println("##################################### paramMap => " + paramMap);

 		 //이곳에 고지의뢰 서비스콜!! 삽입할것!!
        //gamHtldRentFeeMngtService.insertAnlrveLev(gamHtldRentFeeMngtInfo);

        resultCode = 0;
 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

 		return map;
     }

    /**
     * 배후단지임대료관리정보를 등록한다.
     * @param gamHtldRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamInsertHtldRentFeeMngtFee.do")
    public @ResponseBody Map insertHtldRentFeeMngtFee(
     	   @ModelAttribute("gamHtldRentFeeMngtVO") GamHtldRentFeeMngtVO gamHtldRentFeeMngtVO,
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

     	gamHtldRentFeeMngtVO.setRegUsr(loginVO.getId());
         gamHtldRentFeeMngtVO.setUpdUsr(loginVO.getId());

         gamHtldRentFeeMngtService.insertHtldRentFeeMngtLevReqest(gamHtldRentFeeMngtVO);

         resultCode = 0;
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }

    @SuppressWarnings("unchecked")
	@RequestMapping(value="/oper/htld/showNticIssuePopup.do")
    public String showNticIssuePopup(
     	   GamHtldRentFeeMngtVO gamHtldRentFeeMngtVO,
     	  ModelMap model)
            throws Exception {

		Map master = gamHtldRentFeeMngtService.selectAssetLevReqestNticPk(gamHtldRentFeeMngtVO);

		model.addAttribute("levReqestMaster", master);

    	return "/ygpa/gam/oper/htld/GamPopupHtldRentFeeNticIssue";
     }

    /**
     * 고지의뢰를 한다.(단일처리) 고지합업 후 실행 --- 서비스 이전 및 버그 수정 작업 2015.12.10
     * @param gamAssetRentFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/oper/htld/insertHtldFeeNticSingle.do")
    public @ResponseBody Map insertAssetRentFeeNticSingle(
     	   @ModelAttribute("gamHtldRentFeeMngtVO") GamHtldRentFeeMngtVO gamHtldRentFeeMngtVO,
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
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    		gamHtldRentFeeMngtService.insertAssetRentFeeNticSingle(loginVo, gamHtldRentFeeMngtVO);
    		
	     	map.put("resultCode", 0);
	        map.put("resultMsg", egovMessageSource.getMessage("gam.asset.proc"));
    	}
    	catch(Exception e) {
	        map.put("resultCode", 1);
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
	@RequestMapping(value="/oper/htld/cancelHtldRentFeeNticSingle.do")
    public @ResponseBody Map deleteAssetRentFeeNticSingle(
     	   @ModelAttribute("gamHtldRentFeeMngtVO") GamHtldRentFeeMngtVO gamHtldRentFeeMngtVO,
     	   BindingResult bindingResult)
            throws Exception {
     	Map map = new HashMap();
     	Map paramMap = new EgovMap();
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
	        paramMap.put("nticCnt", gamHtldRentFeeMngtVO.getNticCnt());
	 		paramMap.put("prtAtCode", gamHtldRentFeeMngtVO.getPrtAtCode());
	 		paramMap.put("mngYear", gamHtldRentFeeMngtVO.getMngYear());
	 		paramMap.put("mngNo", gamHtldRentFeeMngtVO.getMngNo());
	 		paramMap.put("mngCnt", gamHtldRentFeeMngtVO.getMngCnt());
	 		paramMap.put("chrgeKnd", gamHtldRentFeeMngtVO.getChrgeKnd());
	 		paramMap.put("deptCd", loginVo.getDeptCd());

	 		gamHtldRentFeeMngtService.cancelNticRequest(paramMap);

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

    @RequestMapping(value="/oper/htld/printHtldNoticeIssue.do")
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
    		vo.put("nhtPrintYn", "Y");
    		gamHtldRentFeeMngtService.updateNticPrintState(vo);
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
     * 고지서를 출력한다.
     * @param approvalOpt
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/printHtldRentFeePayNotice.do")
    String printAssetRentFeePayNotice(@RequestParam Map<String, Object> approvalOpt, ModelMap model) throws Exception {
    	model.addAttribute("searchOpt", approvalOpt);

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    		return "ygpa/gam/oper/htld/GamHtldPrintNoticeIssue";
    	}
    	
    	List master = gamHtldRentFeeMngtService.selectNticPrintMaster(approvalOpt);

    	List detail = gamHtldRentFeeMngtService.selectNticPrintDetail(approvalOpt);

    	model.addAttribute("resultCode", 0);
    	model.addAttribute("resultList", master);
    	model.addAttribute("detail", detail);
    	model.addAttribute("resultMsg", "");
    	return "ygpa/gam/oper/htld/GamHtldPrintNoticeIssue";
    	}

    @RequestMapping(value="/oper/htld/printHtldRentFeeTaxNotice.do")
    String printAssetRentFeeTaxNotice(@RequestParam Map<String, Object> approvalOpt, ModelMap model) throws Exception {
    	model.addAttribute("searchOpt", approvalOpt);

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	}
    	else {
//    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    		List list = gamHtldRentFeeMngtService.selectTaxNtcPrintInfo(approvalOpt);

//    		model.addAttribute("emplyrNo", loginVo.getEmplNo());

    		model.addAttribute("resultCode", 0);
    		model.addAttribute("resultList", list);
    		model.addAttribute("resultMsg", "");
    	}

    	return "ygpa/gam/oper/htld/GamPrtfcltyRentPrintTaxNoticeIssue";
    	}

    @RequestMapping(value="/oper/htld/updateHtldRentFeeList.do")
    public @ResponseBody Map updateAssetRentFeeMngtListDetail(
    		@RequestParam Map<String, Object> nticList)
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

    	try {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        	List<GamHtldRentFeeMngtVO> createList=null, updateList=null;
        	if(nticList.containsKey("_cList")) {
    	    	createList = mapper.readValue((String)nticList.get("_cList"), TypeFactory.defaultInstance().constructCollectionType(List.class,
    	    			GamHtldRentFeeMngtVO.class));
    	    	for(int i=0; i<createList.size(); i++) {
    	    		GamHtldRentFeeMngtVO vo = createList.get(i);
    	    		vo.setRegUsr(loginVo.getId());
    	    	}
        	}
        	if(nticList.containsKey("_uList")) {
    	    	updateList = mapper.readValue((String)nticList.get("_uList"), TypeFactory.defaultInstance().constructCollectionType(List.class,
    	    			GamHtldRentFeeMngtVO.class));
    	    	for(int i=0; i<updateList.size(); i++) {
    	    		GamHtldRentFeeMngtVO vo = updateList.get(i);
    	    		vo.setUpdUsr(loginVo.getId());
    	    	}
        	}

        	gamHtldRentFeeMngtService.updateHtldRentFee(createList, updateList);

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
    @RequestMapping(value="/oper/htld/selectHtldRentFeeDetailByPk.do", method=RequestMethod.POST)
	public @ResponseBody Map gamSelectAssetRentFeeMngtListDetail(GamHtldRentFeeMngtVO searchVO) throws Exception {

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
    	Map master = gamHtldRentFeeMngtService.selectAssetRentFeeDetailMstPk(searchVO);
    	List resultList = gamHtldRentFeeMngtService.selectAssetRentFeeDetailList(searchVO);
    	Map summary = gamHtldRentFeeMngtService.selectAssetRentFeeDetailSumPk(searchVO);

        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());


    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", resultList);
    	map.put("resultMaster", master);
    	map.put("resultSummary", summary);
    	map.put("searchOption", searchVO);

    	return map;
    }


	@RequestMapping(value="/oper/htld/gamHtldRentHwpPreview.do")
	String gamHtldRentHwpPreview(@RequestParam Map<String, Object> approvalOpt, ModelMap model) throws Exception {
		List varItems = new ArrayList<Map>();

		model.addAttribute("searchOpt", approvalOpt);

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("resultCode", 1);
			model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
		} else {

			model.addAttribute("resultCode", 0);
			model.addAttribute("resultList", approvalOpt);
			model.addAttribute("resultMsg", "");
			model.addAttribute("hwpTemplateName", "TEMPLATE_TEST.hwp");	// 파일 명을 지정 한다. (/tmpl 기준)
			Map map = new HashMap();
			map.put("name", "title");
			map.put("value", "제목을 서버에서 전송 합니다.");
			map.put("type", "TEXT");
			varItems.add(map);
			map = new HashMap();
			map.put("name", "name");
			map.put("value", "장은성");
			map.put("type", "TEXT");
			varItems.add(map);
			map = new HashMap();
			map.put("name", "date");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD");
			map.put("value", format.format(new Date()));
			map.put("type", "TEXT");
			varItems.add(map);

			model.addAttribute("varItems", varItems);
		}

		return "/ygpa/gam/oper/htld/GamHtldHwpPreview";

	}

}
