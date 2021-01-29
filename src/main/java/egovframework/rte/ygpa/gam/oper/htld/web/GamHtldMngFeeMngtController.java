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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeDefaultVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtVO;

/**
 * @Class Name : GamHtldMngFeeMngtController.java
 * @Description : 배후단지관리비고지
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
public class GamHtldMngFeeMngtController {

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

    @Resource(name = "gamHtldMngFeeMngtService")
    private GamHtldMngFeeMngtService gamHtldMngFeeMngtService;

    @Resource(name = "gamNticRequestMngtService")
    private GamNticRequestMngtService gamNticRequestMngtService;

    /**
     * 배후단지관리비관리 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/htld/GamHtldMngFeeMngt"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htld/gamHtldMngFeeMngt.do")
	public String indexMain(@RequestParam("windowId") String windowId) throws Exception {
    	return "/ygpa/gam/oper/htld/GamHtldMngFeeMngt";
    }

	/**
     * 배후단지관리비관리 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/gamSelectHtldMngFeeMngtList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldMngFeeMngtList(GamHtldMngFeeDefaultVO searchVO) throws Exception {

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
    	totalCnt = gamHtldMngFeeMngtService.selectHtldMngFeeMngtListTotCnt(searchVO);
    	List resultList = gamHtldMngFeeMngtService.selectHtldMngFeeMngtList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	//자료수, 사용료, 연체, 부가세, 고지액
    	Map resultSum = gamHtldMngFeeMngtService.selectHtldMngFeeMngtSum(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	map.put("resultSum", resultSum);

    	return map;
    }

    @RequestMapping(value="/oper/htld/updateHtldMngFee.do")
    public @ResponseBody Map updateHtldMngFee(
     	   @ModelAttribute("gamHtldMngFeeMngtVO") GamHtldMngFeeMngtVO gamHtldMngFeeMngtVO,
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

     	gamHtldMngFeeMngtVO.setUpdUsr(loginVo.getId()); //수정자 (세션 로그인 아이디)

         gamHtldMngFeeMngtService.updateHtldMngFeeMngt(gamHtldMngFeeMngtVO);

         resultCode = 0;
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }

    @RequestMapping(value="/oper/htld/clearHtldMngFeeList.do")
    public @ResponseBody Map clearHtldMngFeeList(
    		GamHtldMngFeeDefaultVO gamHtldMngFeeMngtVO,
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

     	gamHtldMngFeeMngtVO.setUpdUsr(loginVo.getId()); //수정자 (세션 로그인 아이디)

         gamHtldMngFeeMngtService.clearHtldMngFeeList(gamHtldMngFeeMngtVO);

         resultCode = 0;
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/gamSelectHtldMngFeeMngtListExcel.do", method=RequestMethod.POST)
    @ResponseBody ModelAndView selectHtldMngFeeMngtListExcel(@RequestParam Map<String, Object> excelParam) throws Exception {
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
    	GamHtldMngFeeDefaultVO searchVO = new GamHtldMngFeeDefaultVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.
        fileName = (String)excelParam.get("fileName");
        excelParam.remove("fileName");	// 파라미터에서 헤더를 삭제 한다.
		// 조회 조건
		searchVO = mapper.convertValue(excelParam, GamHtldMngFeeDefaultVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		/** List Data */
//    	int totCnt = erpAssetCdService.selectErpAssetCdListTotCnt(searchVO);
    	List resultList = gamHtldMngFeeMngtService.selectHtldMngFeeMngtList(searchVO);

    	map.put("resultList", resultList);
    	map.put("header", header);
    	map.put("fileName", fileName);

    	return new ModelAndView("gridHtldFeeView", "gridResultMap", map);
    }

	/**
     * 배후단지관리비관리정보를 수정한다.
     * @param gamHtldMngFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamUpdateHtldMngFeeMngtFee.do")
    public @ResponseBody Map updateHtldMngFeeMngtFee(
     	   @ModelAttribute("gamHtldMngFeeMngtVO") GamHtldMngFeeMngtVO gamHtldMngFeeMngtVO,
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

     	gamHtldMngFeeMngtVO.setUpdUsr(loginVo.getId()); //수정자 (세션 로그인 아이디)

         gamHtldMngFeeMngtService.updateHtldMngFeeMngt(gamHtldMngFeeMngtVO);

         resultCode = 0;
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }

    /**
     * 고지의뢰를 한다. (다중처리)
     * @param gamHtldMngFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    /*
    @RequestMapping(value="/oper/htld/gamInsertHtldMngFeeMngtFeeNtic.do")
    @ResponseBody Map<String, Object> insertHtldMngFeeMngtFeeNtic (
				  @RequestParam("nticCnts") String nticCnts
				 ,@RequestParam("prtAtCodes") String prtAtCodes
				 ,@RequestParam("mngYears") String mngYears
				 ,@RequestParam("mngNos") String mngNos
				 ,@RequestParam("mngCnts") String mngCnts
				 ,@RequestParam("nticnos") String nticnos
				 ,@RequestParam("accnutYears") String accnutYears
				 ,@ModelAttribute("gamHtldMngFeeMngtVO") GamHtldMngFeeMngtVO gamHtldMngFeeMngtVO
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
     		gamHtldMngFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamHtldMngFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamHtldMngFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamHtldMngFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamHtldMngFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamHtldMngFeeMngtVO.setNticno(arrNticnos[i]);
     		gamHtldMngFeeMngtVO.setAccnutYear(arrAccnutYears[i]);

     		//세입징수에 기등록  여부
     		anlrveLevCnt = gamHtldMngFeeMngtService.selectAnlrveLevCnt(gamHtldMngFeeMngtVO);

     		if( anlrveLevCnt > 0 ) {
     			map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject1")); //세입징수에 기존재하는 건이 있습니다. 확인후 다시 시도하십시오.

         		return map;
     		}

     		//징수의뢰 정보조회
            GamHtldMngFeeMngtVO gamHtldMngFeeMngtInfo = gamHtldMngFeeMngtService.selectHtldMngFeeMngtFeeInfo(gamHtldMngFeeMngtVO);

            if( EgovStringUtil.isEmpty(gamHtldMngFeeMngtInfo.getAccnutYear()) ) {
            	map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject2")); //회계년도가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.

         		return map;
            }

            if( EgovStringUtil.isEmpty(gamHtldMngFeeMngtInfo.getNticno()) ) {
            	map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject3")); //고지번호가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.

         		return map;
            }

    	}

     	//세입징수 등록
     	for(int i=0; i<arrNticCnts.length;i++) {
     		gamHtldMngFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamHtldMngFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamHtldMngFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamHtldMngFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamHtldMngFeeMngtVO.setMngCnt(arrMngCnts[i]);

     		//징수의뢰 정보조회
            GamHtldMngFeeMngtVO gamHtldMngFeeMngtInfo = gamHtldMngFeeMngtService.selectHtldMngFeeMngtFeeInfo(gamHtldMngFeeMngtVO);

            gamHtldMngFeeMngtInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
            gamHtldMngFeeMngtInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)

            gamHtldMngFeeMngtService.insertAnlrveLev(gamHtldMngFeeMngtInfo);
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
     * @param gamHtldMngFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamInsertHtldMngFeeMngtFeeNtic.do")
    @ResponseBody Map<String, Object> insertHtldMngFeeMngtFeeNtic (
				  @RequestParam("nticCnts") String nticCnts
				 ,@RequestParam("prtAtCodes") String prtAtCodes
				 ,@RequestParam("mngYears") String mngYears
				 ,@RequestParam("mngNos") String mngNos
				 ,@RequestParam("mngCnts") String mngCnts
				 ,@RequestParam("nticnos") String nticnos
				 ,@RequestParam("accnutYears") String accnutYears
				 ,@ModelAttribute("gamHtldMngFeeMngtVO") GamHtldMngFeeMngtVO gamHtldMngFeeMngtVO
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
     		gamHtldMngFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamHtldMngFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamHtldMngFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamHtldMngFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamHtldMngFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamHtldMngFeeMngtVO.setRegUsr(loginVO.getId());
     		gamHtldMngFeeMngtVO.setUpdUsr(loginVO.getId());

     		System.out.println("############################# 고지의뢰 CALL!! START ");

     		paramMap.put("nticCnt", gamHtldMngFeeMngtVO.getNticCnt());
     		paramMap.put("prtAtCode", gamHtldMngFeeMngtVO.getPrtAtCode());
     		paramMap.put("mngYear", gamHtldMngFeeMngtVO.getMngYear());
     		paramMap.put("mngNo", gamHtldMngFeeMngtVO.getMngNo());
     		paramMap.put("mngCnt", gamHtldMngFeeMngtVO.getMngCnt());
            System.out.println("##################################### paramMap => " + paramMap);

            //이곳에 고지의뢰 서비스콜!! 삽입할것!!
            //gamHtldMngFeeMngtService.insertAnlrveLev(gamHtldMngFeeMngtInfo);
 		}

 		resultCode = 0;
        resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

 		return map;
     }

    /**
     * 고지취소를 한다. (다중처리)
     * @param gamHtldMngFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    /*
    @RequestMapping(value="/oper/htld/gamDeleteHtldMngFeeMngtFeeNtic.do")
    @ResponseBody Map<String, Object> deleteHtldMngFeeMngtFeeNtic (
				  @RequestParam("nticCnts") String nticCnts
				 ,@RequestParam("prtAtCodes") String prtAtCodes
				 ,@RequestParam("mngYears") String mngYears
				 ,@RequestParam("mngNos") String mngNos
				 ,@RequestParam("mngCnts") String mngCnts
				 ,@RequestParam("nticnos") String nticnos
				 ,@RequestParam("accnutYears") String accnutYears
				 ,@ModelAttribute("gamHtldMngFeeMngtVO") GamHtldMngFeeMngtVO gamHtldMngFeeMngtVO
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
     		gamHtldMngFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamHtldMngFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamHtldMngFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamHtldMngFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamHtldMngFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamHtldMngFeeMngtVO.setNticno(arrNticnos[i]);
     		gamHtldMngFeeMngtVO.setAccnutYear(arrAccnutYears[i]);

     		//세입징수에 기등록  여부
     		anlrveLevCnt = gamHtldMngFeeMngtService.selectAnlrveLevCnt(gamHtldMngFeeMngtVO);

     		if( anlrveLevCnt > 0 ) {
     			map.put("resultCode", 1);
                map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject1")); //세입징수에 기존재하는 건이 있습니다. 확인후 다시 시도하십시오.

         		return map;
     		}
    	}

     	//징수의뢰 삭제
     	for(int i=0; i<arrNticCnts.length;i++) {
     		gamHtldMngFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamHtldMngFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamHtldMngFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamHtldMngFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamHtldMngFeeMngtVO.setMngCnt(arrMngCnts[i]);

            gamHtldMngFeeMngtService.deleteHtldMngFeeMngtFee(gamHtldMngFeeMngtVO);
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
     * @param gamHtldMngFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamDeleteHtldMngFeeMngtFeeNtic.do")
    @ResponseBody Map<String, Object> deleteHtldMngFeeMngtFeeNtic (
				  @RequestParam("nticCnts") String nticCnts
				 ,@RequestParam("prtAtCodes") String prtAtCodes
				 ,@RequestParam("mngYears") String mngYears
				 ,@RequestParam("mngNos") String mngNos
				 ,@RequestParam("mngCnts") String mngCnts
				 ,@RequestParam("nticnos") String nticnos
				 ,@RequestParam("accnutYears") String accnutYears
				 ,@ModelAttribute("gamHtldMngFeeMngtVO") GamHtldMngFeeMngtVO gamHtldMngFeeMngtVO
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
     		gamHtldMngFeeMngtVO.setNticCnt(arrNticCnts[i]);
     		gamHtldMngFeeMngtVO.setPrtAtCode(arrPrtAtCodes[i]);
     		gamHtldMngFeeMngtVO.setMngYear(arrMngYears[i]);
     		gamHtldMngFeeMngtVO.setMngNo(arrMngNos[i]);
     		gamHtldMngFeeMngtVO.setMngCnt(arrMngCnts[i]);
     		gamHtldMngFeeMngtVO.setRegUsr(loginVO.getId());
     		gamHtldMngFeeMngtVO.setUpdUsr(loginVO.getId());

     		System.out.println("############################# 고지취소 CALL!! START ");
            //gamHtldMngFeeMngtService.insertAnlrveLev(gamHtldMngFeeMngtInfo);
     		paramMap.put("nticCnt", gamHtldMngFeeMngtVO.getNticCnt());
     		paramMap.put("prtAtCode", gamHtldMngFeeMngtVO.getPrtAtCode());
     		paramMap.put("mngYear", gamHtldMngFeeMngtVO.getMngYear());
     		paramMap.put("mngNo", gamHtldMngFeeMngtVO.getMngNo());
     		paramMap.put("mngCnt", gamHtldMngFeeMngtVO.getMngCnt());
            System.out.println("##################################### paramMap => " + paramMap);

            //이곳에 고지취소 서비스콜!! 삽입할것!!
            //gamHtldMngFeeMngtService.insertAnlrveLev(gamHtldMngFeeMngtInfo);
 		}

 		resultCode = 0;
        resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

 		return map;
     }

    /**
     * 고지의뢰를 한다.(단일처리)
     * @param gamHtldMngFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    /*
    @RequestMapping(value="/oper/htld/gamInsertHtldMngFeeMngtFeeNticSingle.do")
    public @ResponseBody Map insertHtldMngFeeMngtFeeNticSingle(
     	   @ModelAttribute("gamHtldMngFeeMngtVO") GamHtldMngFeeMngtVO gamHtldMngFeeMngtVO,
     	   BindingResult bindingResult)
            throws Exception {

     	 Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
         int anlrveLevCnt = 0;

       //세입징수에 기등록  여부
  		anlrveLevCnt = gamHtldMngFeeMngtService.selectAnlrveLevCnt(gamHtldMngFeeMngtVO);

  		if( anlrveLevCnt > 0 ) {
  			map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject1")); //세입징수에 기존재하는 건이 있습니다. 확인후 다시 시도하십시오.

      		return map;
  		}

  		//징수의뢰 정보조회
        GamHtldMngFeeMngtVO gamHtldMngFeeMngtInfo = gamHtldMngFeeMngtService.selectHtldMngFeeMngtFeeInfo(gamHtldMngFeeMngtVO);

        if( EgovStringUtil.isEmpty(gamHtldMngFeeMngtInfo.getAccnutYear()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject2")); //회계년도가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.

     		return map;
        }

        if( EgovStringUtil.isEmpty(gamHtldMngFeeMngtInfo.getNticno()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.fee.reject3")); //고지번호가 없는 데이터가 존재합니다. 확인후 다시 시도하십시오.

     		return map;
        }

        gamHtldMngFeeMngtInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
        gamHtldMngFeeMngtInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)

        gamHtldMngFeeMngtService.insertAnlrveLev(gamHtldMngFeeMngtInfo);

        resultCode = 0;
 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

 		return map;
     }
     */

    /**
     * 고지의뢰를 한다.(단일처리)
     * @param gamHtldMngFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamInsertHtldMngFeeMngtFeeNticSingle.do")
    public @ResponseBody Map insertHtldMngFeeMngtFeeNticSingle(
     	   @ModelAttribute("gamHtldMngFeeMngtVO") GamHtldMngFeeMngtVO gamHtldMngFeeMngtVO,
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

        paramMap.put("nticCnt", gamHtldMngFeeMngtVO.getNticCnt());
 		paramMap.put("prtAtCode", gamHtldMngFeeMngtVO.getPrtAtCode());
 		paramMap.put("mngYear", gamHtldMngFeeMngtVO.getMngYear());
 		paramMap.put("mngNo", gamHtldMngFeeMngtVO.getMngNo());
 		paramMap.put("mngCnt", gamHtldMngFeeMngtVO.getMngCnt());

 		System.out.println("##################################### paramMap => " + paramMap);

 		 //이곳에 고지의뢰 서비스콜!! 삽입할것!!
        //gamHtldMngFeeMngtService.insertAnlrveLev(gamHtldMngFeeMngtInfo);

        resultCode = 0;
 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

 		return map;
     }

    /**
     * 고지취소를 한다.(단일처리)
     * @param gamHtldMngFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamDeleteHtldMngFeeMngtFeeNticSingle.do")
    public @ResponseBody Map deleteHtldMngFeeMngtFeeNticSingle(
     	   @ModelAttribute("gamHtldMngFeeMngtVO") GamHtldMngFeeMngtVO gamHtldMngFeeMngtVO,
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

        paramMap.put("nticCnt", gamHtldMngFeeMngtVO.getNticCnt());
 		paramMap.put("prtAtCode", gamHtldMngFeeMngtVO.getPrtAtCode());
 		paramMap.put("mngYear", gamHtldMngFeeMngtVO.getMngYear());
 		paramMap.put("mngNo", gamHtldMngFeeMngtVO.getMngNo());
 		paramMap.put("mngCnt", gamHtldMngFeeMngtVO.getMngCnt());

 		System.out.println("##################################### paramMap => " + paramMap);

 		 //이곳에 고지의뢰 서비스콜!! 삽입할것!!
        //gamHtldMngFeeMngtService.insertAnlrveLev(gamHtldMngFeeMngtInfo);

        resultCode = 0;
 		resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

 		return map;
     }

    /**
     * 배후단지관리비관리정보를 등록한다.
     * @param gamHtldMngFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/htld/gamInsertHtldMngFeeMngtFee.do")
    public @ResponseBody Map insertHtldMngFeeMngtFee(
     	   @ModelAttribute("gamHtldMngFeeMngtVO") GamHtldMngFeeMngtVO gamHtldMngFeeMngtVO,
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

     	gamHtldMngFeeMngtVO.setRegUsr(loginVO.getId());
         gamHtldMngFeeMngtVO.setUpdUsr(loginVO.getId());

         gamHtldMngFeeMngtService.insertHtldMngFeeMngtLevReqest(gamHtldMngFeeMngtVO);

         resultCode = 0;
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }

    @SuppressWarnings("unchecked")
	@RequestMapping(value="/oper/htld/showMngNticIssuePopup.do")
    public String showNticIssuePopup(
     	   GamHtldMngFeeMngtVO gamHtldMngFeeMngtVO,
     	  ModelMap model)
            throws Exception {

		Map master = gamHtldMngFeeMngtService.selectAssetLevReqestNticPk(gamHtldMngFeeMngtVO);

		model.addAttribute("levReqestMaster", master);

    	return "/ygpa/gam/oper/htld/GamPopupHtldMngFeeNticIssue";
     }

    /**
     * 고지의뢰를 한다.(단일처리)
     * @param gamAssetMngFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/oper/htld/insertHtldMngFeeNticSingle.do")
    public @ResponseBody Map insertAssetMngFeeNticSingle(
     	   @ModelAttribute("gamHtldMngFeeMngtVO") GamHtldMngFeeMngtVO gamHtldMngFeeMngtVO,
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

    		Map nticParam = gamHtldMngFeeMngtService.selectNoticeRequest(gamHtldMngFeeMngtVO);

    		nticParam.put("updUsr", loginVo.getId());
    		nticParam.put("nhtPrintYn", "N");
    		nticParam.put("userName", loginVo.getName());
    		nticParam.put("deptCd", loginVo.getDeptCd());
    		nticParam.put("reimChrgeKnd", "DB");
    		nticParam.put("intrChrgeKnd", "A3");	// 이자 요금 코드
    		nticParam.put("vat", gamHtldMngFeeMngtVO.getVat());
    		nticParam.put("nticAmt", gamHtldMngFeeMngtVO.getNticAmt());

    		gamHtldMngFeeMngtService.sendLevReqestRevCollF(nticParam);

//	 		gamNticRequestMngtService.sendNticRequest(nticParam);

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
     * @param gamAssetMngFeeMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/oper/htld/cancelHtldMngFeeNticSingle.do")
    public @ResponseBody Map deleteAssetMngFeeNticSingle(
     	   @ModelAttribute("gamHtldMngFeeMngtVO") GamHtldMngFeeMngtVO gamHtldMngFeeMngtVO,
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
	        paramMap.put("nticCnt", gamHtldMngFeeMngtVO.getNticCnt());
	 		paramMap.put("prtAtCode", gamHtldMngFeeMngtVO.getPrtAtCode());
	 		paramMap.put("mngYear", gamHtldMngFeeMngtVO.getMngYear());
	 		paramMap.put("mngNo", gamHtldMngFeeMngtVO.getMngNo());
	 		paramMap.put("mngCnt", gamHtldMngFeeMngtVO.getMngCnt());
	 		paramMap.put("chrgeKnd", gamHtldMngFeeMngtVO.getChrgeKnd());
	 		paramMap.put("deptCd", loginVo.getDeptCd());

	 		gamHtldMngFeeMngtService.cancelNticRequest(paramMap);

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

    @RequestMapping(value="/oper/htld/printHtldMngNoticeIssue.do")
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
    		gamHtldMngFeeMngtService.updateNticPrintState(vo);
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
    @RequestMapping(value="/oper/htld/printHtldMngFeePayNotice.do")
    String printAssetMngFeePayNotice(@RequestParam Map<String, Object> approvalOpt, ModelMap model) throws Exception {
    	model.addAttribute("searchOpt", approvalOpt);

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	}
    	else {
//    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    		List master = gamHtldMngFeeMngtService.selectNticPrintMaster(approvalOpt);

    		List detail = gamHtldMngFeeMngtService.selectNticPrintDetail(approvalOpt);

//    		model.addAttribute("emplyrNo", loginVo.getEmplNo());

    		model.addAttribute("resultCode", 0);
    		model.addAttribute("resultList", master);
    		model.addAttribute("detail", detail);
    		model.addAttribute("resultMsg", "");
    	}

    	return "ygpa/gam/oper/htld/GamHtldPrintNoticeIssue";
    	}

    @RequestMapping(value="/oper/htld/printHtldMngFeeTaxNotice.do")
    String printAssetMngFeeTaxNotice(@RequestParam Map<String, Object> approvalOpt, ModelMap model) throws Exception {
    	model.addAttribute("searchOpt", approvalOpt);

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	}
    	else {
//    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    		List list = gamHtldMngFeeMngtService.selectTaxNtcPrintInfo(approvalOpt);

//    		model.addAttribute("emplyrNo", loginVo.getEmplNo());

    		model.addAttribute("resultCode", 0);
    		model.addAttribute("resultList", list);
    		model.addAttribute("resultMsg", "");
    	}

    	return "ygpa/gam/oper/htld/GamPrtfcltyRentPrintTaxNoticeIssue";
    	}

    @RequestMapping(value="/oper/htld/updateHtldMngFeeList.do")
    public @ResponseBody Map updateAssetMngFeeMngtListDetail(
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

        	List<GamHtldMngFeeMngtVO> createList=null, updateList=null;
        	if(nticList.containsKey("_cList")) {
    	    	createList = mapper.readValue((String)nticList.get("_cList"), TypeFactory.defaultInstance().constructCollectionType(List.class,
    	    			GamHtldMngFeeMngtVO.class));
    	    	for(int i=0; i<createList.size(); i++) {
    	    		GamHtldMngFeeMngtVO vo = createList.get(i);
    	    		vo.setRegUsr(loginVo.getId());
    	    	}
        	}
        	if(nticList.containsKey("_uList")) {
    	    	updateList = mapper.readValue((String)nticList.get("_uList"), TypeFactory.defaultInstance().constructCollectionType(List.class,
    	    			GamHtldMngFeeMngtVO.class));
    	    	for(int i=0; i<updateList.size(); i++) {
    	    		GamHtldMngFeeMngtVO vo = updateList.get(i);
    	    		vo.setUpdUsr(loginVo.getId());
    	    	}
        	}

        	gamHtldMngFeeMngtService.updateHtldMngFee(createList, updateList);

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
    @RequestMapping(value="/oper/htld/selectHtldMngFeeDetailByPk.do", method=RequestMethod.POST)
	public @ResponseBody Map gamSelectAssetMngFeeMngtListDetail(GamHtldMngFeeMngtVO searchVO) throws Exception {

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
    	Map master = gamHtldMngFeeMngtService.selectAssetMngFeeDetailMstPk(searchVO);
    	List resultList = gamHtldMngFeeMngtService.selectAssetMngFeeDetailList(searchVO);
    	Map summary = gamHtldMngFeeMngtService.selectAssetMngFeeDetailSumPk(searchVO);

        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());


    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", resultList);
    	map.put("resultMaster", master);
    	map.put("resultSummary", summary);
    	map.put("searchOption", searchVO);

    	return map;
    }

}
