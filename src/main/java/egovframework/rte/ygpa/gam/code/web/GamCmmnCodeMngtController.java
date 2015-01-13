package egovframework.rte.ygpa.gam.code.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.ccm.cca.service.CmmnCode;
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.com.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;
import egovframework.com.sym.ccm.ccc.service.CmmnClCodeVO;
import egovframework.com.sym.ccm.ccc.service.EgovCcmCmmnClCodeManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *
 * @author Administrator
 * @since 2014. 1. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 1. 22.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamCmmnCodeMngtController {

	@Resource(name = "CmmnCodeManageService")
    private EgovCcmCmmnCodeManageService cmmnCodeManageService;

	@Resource(name = "CmmnClCodeManageService")
    private EgovCcmCmmnClCodeManageService cmmnClCodeManageService;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;



	/**
	 * 화면 호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/code/gamCmmnCodeMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, @ModelAttribute("cmmnCode") CmmnCode cmmnCode, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);

//    	CmmnClCodeVO searchVO;
//		searchVO = new CmmnClCodeVO();
//		searchVO.setRecordCountPerPage(999999);
//		searchVO.setFirstIndex(0);
//		searchVO.setSearchCondition("CodeList");
//        List CmmnCodeList = cmmnClCodeManageService.selectCmmnClCodeList(searchVO);
//        model.addAttribute("cmmnClCode", CmmnCodeList);

    	return "/ygpa/gam/code/GamCmmnCodeMngt";
    }

	/**
	 * 공통코드관리 목록을 조회한다.
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping(value="/code/gamCcmCmmnCodeList.do")
	@ResponseBody Map<String, Object> selectCmmnCodeList(@ModelAttribute("searchVO") CmmnCodeVO searchVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> CmmnCodeList = cmmnCodeManageService.selectCmmnCodeList(searchVO);
        int totCnt = cmmnCodeManageService.selectCmmnCodeListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", CmmnCodeList);
    	map.put("searchOption", searchVO);

    	return map;
	}


    /**
     * 공통코드관리 상세항목을 조회한다.
     * @param clCode
     * @return map
     * @throws Exception
     */
	@RequestMapping(value="/code/gamCcmCmmnCodeDetail.do")
 	@ResponseBody Map<String, Object> selectCmmnCodeDetail (@RequestParam("codeId") String codeId) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		CmmnCode cmmncode = new CmmnCode();
		cmmncode.setCodeId(codeId);

		CmmnCode vo = cmmnCodeManageService.selectCmmnCodeDetail(cmmncode);

		map.put("codeDetail", vo);
		return map;
	}


    /**
	 * 공통코드관리를 등록한다.
	 * @param loginVO
	 * @param cmmnCode
	 * @param bindingResult
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping(value="/code/gamCcmCmmnCodeRegist.do")
	@ResponseBody Map<String, Object> insertCmmnCode (@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("cmmnCode") CmmnCode cmmnCode, BindingResult bindingResult) throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

        beanValidator.validate(cmmnCode, bindingResult);
		if (bindingResult.hasErrors()){
			map.put("resultCode", 1);
			map.put("resultMsg", "입력값에 오류가 있습니다.");
            return map;
		}

		CmmnCodeVO comCode = new CmmnCodeVO();
		comCode.setSearchCondition("countCode");
		comCode.setCodeId(cmmnCode.getCodeId());

		int totCnt = cmmnCodeManageService.selectCmmnCodeListTotCnt(comCode);

		if(totCnt > 0){
			map.put("resultCode", 1);
			map.put("resultMsg", "등록된 코드가 존재합니다.");
		}else{


			try {
				cmmnCode.setFrstRegisterId(user.getId());
		    	cmmnCodeManageService.insertCmmnCode(cmmnCode);
		    	map.put("resultCode", 0);			// return ok
				map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
			} catch (Exception e) {
				// TODO: handle exception

				map.put("resultCode", 1);
				map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
			}

		}

        return map;
    }


    /**
     * 공통코드관리를 수정한다.
     * @param loginVO
     * @param cmmnCode
     * @param bindingResult
     * @param commandMap
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/code/gamCcmCmmnCodeModify.do")
	@ResponseBody Map<String, Object> updateCmmnCode (@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("cmmnCode") CmmnCode cmmnCode
			, BindingResult bindingResult, @ModelAttribute("cmd") String cmd) throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		if (cmd.equals("")) {
    		CmmnCode vo = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCode);
    		map.put("cmmnCode", vo);

    		map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
    		return map;
    	} else if (cmd.equals("modify")) {
            beanValidator.validate(cmmnCode, bindingResult);
    		if (bindingResult.hasErrors()){
        		CmmnCode vo = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCode);
        		map.put("cmmnCode", vo);

        		map.put("resultCode", 1);
        		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
        		return map;
    		}


    		try {
    			cmmnCode.setLastUpdusrId(user.getId());
    	    	cmmnCodeManageService.updateCmmnCode(cmmnCode);

    	    	map.put("resultCode", 0);
    	    	map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
			} catch (Exception e) {
				// TODO: handle exception

				map.put("resultCode", 1);
    	    	map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
			}

	        return map;
    	} else {
    		return map;
    	}
    }


    /**
     * 공통코드관리를 삭제한다.
     * @param cmmnCode
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/code/gamCcmCmmnCodeRemove.do")
	@ResponseBody Map<String, Object> deleteCmmnCode (@ModelAttribute("clCode") CmmnCode cmmnCode) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try{
    		cmmnCodeManageService.deleteCmmnCode(cmmnCode);
    		map.put("resultCode", 0);
          	map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
    	}catch(Exception e){
    		map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
    	}


    	return map;
	}
}