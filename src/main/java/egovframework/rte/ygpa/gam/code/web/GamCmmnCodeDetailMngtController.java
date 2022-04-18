package egovframework.rte.ygpa.gam.code.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
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
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.ccm.cca.service.CmmnCode;
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.com.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;
import egovframework.com.sym.ccm.ccc.service.CmmnClCodeVO;
import egovframework.com.sym.ccm.ccc.service.EgovCcmCmmnClCodeManageService;
import egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO;
import egovframework.com.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *
 * @author Administrator
 * @since 2014. 1. 23.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 1. 23.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamCmmnCodeDetailMngtController {

	@Resource(name = "CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;

	@Resource(name = "CmmnClCodeManageService")
    private EgovCcmCmmnClCodeManageService cmmnClCodeManageService;

	@Resource(name = "CmmnCodeManageService")
    private EgovCcmCmmnCodeManageService cmmnCodeManageService;

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
	@RequestMapping(value="/code/gamCmmnCodeDetailMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, @ModelAttribute("cmmnCode") CmmnCode cmmnCode, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);

    	CmmnClCodeVO searchClCodeVO;
		searchClCodeVO = new CmmnClCodeVO();
		searchClCodeVO.setRecordCountPerPage(999999);	
		searchClCodeVO.setFirstIndex(0);
		searchClCodeVO.setSearchCondition("CodeList");
        List CmmnClCodeList = (List)cmmnClCodeManageService.selectCmmnClCodeList(searchClCodeVO);
        model.addAttribute("cmmnClCodeList", CmmnClCodeList);

    	return "/ygpa/gam/code/GamCmmnCodeDetailMngt";
    }


	/**
	 * 공통상세코드 목록을 조회한다.
	 * @param loginVO
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping(value="/code/gamCcmCmmnDetailCodeList.do")
	@ResponseBody Map<String, Object> selectCmmnDetailCodeList (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") CmmnDetailCodeVO searchVO) throws Exception {

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

        List<?> CmmnCodeList = cmmnDetailCodeManageService.selectCmmnDetailCodeList(searchVO);
        int totCnt = cmmnDetailCodeManageService.selectCmmnDetailCodeListTotCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", CmmnCodeList);
    	map.put("searchOption", searchVO);

    	return map;
	}


    /**
     * 공통상세코드 상세항목을 조회한다.
     * @param loginVO
     * @param cmmnDetailCode
     * @return map
     * @throws Exception
     */
	@RequestMapping(value="/code/gamCcmCmmnDetailCodeDetail.do")
 	@ResponseBody Map<String, Object> selectCmmnDetailCodeDetail (@RequestParam("code") String code, @RequestParam("codeId") String codeId)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		CmmnDetailCode cmmnDetailCode = new CmmnDetailCode();
		cmmnDetailCode.setCode(code);
		cmmnDetailCode.setCodeId(codeId);

		CmmnDetailCode vo = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCode);

		map.put("codeDetail", vo);

		return map;
	}


	/**
	 * 공통상세코드를 등록한다.
	 * @param loginVO
	 * @param cmmnDetailCode
	 * @param cmmnCode
	 * @param bindingResult
	 * @param commandMap
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping(value="/code/gamCcmCmmnDetailCodeRegist.do")
	@ResponseBody Map<String, Object> insertCmmnDetailCode	(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("cmmnDetailCode") CmmnDetailCode cmmnDetailCode, @ModelAttribute("cmmnCode") CmmnCode cmmnCode
			, BindingResult bindingResult, @ModelAttribute("cmd") String cmd)	throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		CmmnDetailCode vo = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCode);

		if(vo != null){
			map.put("resultCode", 1);
			map.put("resultMsg", "이미 등록된 코드가 존재합니다.");
            return map;
    	}

		try {
			cmmnDetailCode.setFrstRegisterId(user.getId());
	    	cmmnDetailCodeManageService.insertCmmnDetailCode(cmmnDetailCode);

	    	map.put("resultCode", 0);			// return ok
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

    	return map;
    }


	/**
	 * 공통상세코드를 수정한다.
	 * @param loginVO
	 * @param cmmnDetailCode
	 * @param bindingResult
	 * @param commandMap
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping(value="/code/gamCcmCmmnDetailCodeModify.do")
	@ResponseBody Map<String, Object> updateCmmnDetailCode (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("cmmnDetailCode") CmmnDetailCode cmmnDetailCode, BindingResult bindingResult, @ModelAttribute("cmd") String cmd) throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	if(cmd.equals("modify") || cmd != null || cmd == null){

            beanValidator.validate(cmmnDetailCode, bindingResult);
    		if (bindingResult.hasErrors()){
    			map.put("resultCode", 1);
    			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
        		return map;
    		}

    		try {

    			cmmnDetailCode.setLastUpdusrId(user.getId());
    	    	cmmnDetailCodeManageService.updateCmmnDetailCode(cmmnDetailCode);
    	    	map.put("resultCode", 0);			// return ok
    			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));

			} catch(IOException e) {
				Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
			}catch (Exception e) {
				// TODO: handle exception
				map.put("resultCode", 1);
    			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
			}
    	}
    	return map;
    }



    @RequestMapping(value="/code/gamGetSubCode.do")
    @ResponseBody Map<String, Object> gamGetSubCode (@RequestParam("clCode") String clCode) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	CmmnCodeVO searchCodeVO;
        searchCodeVO = new CmmnCodeVO();
        searchCodeVO.setRecordCountPerPage(999999);
        searchCodeVO.setFirstIndex(0);
        searchCodeVO.setSearchCondition("clCode");
        searchCodeVO.setSearchKeyword(clCode);

        List CmmnCodeList = cmmnCodeManageService.selectCmmnCodeList(searchCodeVO);
        map.put("resultCode",0);
        map.put("cmmnCodeList", CmmnCodeList);

    	return map;
    }


	/**
	 * 공통상세코드를 삭제한다.
	 * @param loginVO
	 * @param cmmnDetailCode
	 * @param model
	 * @return "forward:/sym/ccm/cde/EgovCcmCmmnDetailCodeList.do"
	 * @throws Exception
	 */
    @RequestMapping(value="/code/gamCcmCmmnDetailCodeRemove.do")
	@ResponseBody Map<String, Object> deleteCmmnDetailCode (@ModelAttribute("codeId") String codeId, @ModelAttribute("code") String code) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	CmmnDetailCode cmmnDetailCode = new CmmnDetailCode();
    	cmmnDetailCode.setCode(code);
    	cmmnDetailCode.setCodeId(codeId);

    	try {
    		cmmnDetailCodeManageService.deleteCmmnDetailCode(cmmnDetailCode);

    		map.put("resultCode", 0);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch(IOException e){
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {

			// TODO: handle exception
			map.put("resultCode", 1);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

    	return map;
	}
}