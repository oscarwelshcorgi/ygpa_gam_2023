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
public class GamHtldRentAssessMngtController {

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
	@RequestMapping(value="/oper/htld/gamHtldRentAssessMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/oper/htld/GamHtldRentAssessMngt";
    }

	/**
     * 배후단지임대평가목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/selectHtldRentAssessList.do", method=RequestMethod.POST)
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
    @RequestMapping(value="/oper/htld/selectHtldRentAssessDetailPk.do", method=RequestMethod.POST)
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
    	List resultAttachList = gamHtldRentMngtService.selectHtldRentMngtFileList(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("result", assetRentDetail);
    	map.put("resultDetailList", resultList);
    	map.put("resultAttachList", resultAttachList);

    	return map;
    }

    /**
     * 배후단지 임대 정보를 등록 한다.
     * @param assetRent
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/oper/htld/insertHtldRentAssess.do")
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
     * 배후단지임대 정보를 수정한다.
     * @param String
     * @param gamHtldRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/updateHtldRentAssess.do")
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

        gamHtldRentMngtService.updateHtldRentMngt(gamHtldRentMngtVO, createList, updateList, deleteList);

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
    @RequestMapping(value="/oper/htld/deleteHtldRentAssess.do")
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
    		resultMsg  = egovMessageSource.getMessage("fail.common.msg");
        }

    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);

		return map;
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
    @RequestMapping(value="/oper/htld/updateHtldRentAssessCmt.do")
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
	 * 실적평가 목록을 조회 한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/selectHtldAssessMngtList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldAssessList(GamHtldAssessVO searchVO) throws Exception {

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
    	//Map totalSum = gamHtldRentMngtService.selectHtldAssessSum(searchVO);
    	List assessList = gamHtldRentMngtService.selectHtldAssessList(searchVO);

//    	paginationInfo.setTotalRecordCount(totalCnt);
//        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	map.put("resultCode", 0);	// return ok
    	//map.put("totalSum", totalSum);
    	map.put("resultList", assessList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	/**
	 * 계약에 대한 고지/납부 목록을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/selectHtldAssessNticList.do", method=RequestMethod.POST)
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