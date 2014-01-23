/**
 * 
 */
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
import egovframework.com.sym.ccm.ccc.service.CmmnClCode;
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
 *  2014. 1. 22.		권옥경		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamCmmnCodeClMngtController {

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
	@RequestMapping(value="/code/gamCmmnCodeClMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/code/GamCmmnCodeClMngt";
    }
	
	
	/**
	 * 공통분류코드 목록을 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model
     * @return map;
     * @throws Exception
     */
    @RequestMapping(value="/code/gamCmmnClCodeList.do")
	@ResponseBody Map<String, Object> selectCmmnClCodeList (@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("searchVO") CmmnClCodeVO searchVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List<?> CmmnCodeList = cmmnClCodeManageService.selectCmmnClCodeList(searchVO);
        int totCnt = cmmnClCodeManageService.selectCmmnClCodeListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", CmmnCodeList);
    	map.put("searchOption", searchVO);

    	return map;
	}

    
    /**
	 * 공통분류코드 상세항목을 조회한다.
	 * @param loginVO
	 * @param cmmnClCode
	 * @param model
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/gamCmmnClCodeDetail.do")
 	@ResponseBody Map<String, Object> selectCmmnClCodeDetail (@RequestParam("clCode") String clCode) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		CmmnClCode cmmnclcode = new CmmnClCode();
		cmmnclcode.setClCode(clCode);

		CmmnClCode vo = cmmnClCodeManageService.selectCmmnClCodeDetail(cmmnclcode);

		map.put("codeDetail", vo);
		return map;
	}


    /**
     * 공통분류코드를 등록한다.
     * @param loginVO
     * @param cmmnClCode
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/code/gamCmmnClCodeRegist.do")
	@ResponseBody Map<String, Object> insertCmmnClCode (@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("clCode") CmmnClCode cmmnClCode, BindingResult bindingResult) throws Exception {
    	
    	Map<String, Object> map = new HashMap<String, Object>();

    	if(cmmnClCode.getClCode() == null || cmmnClCode.getClCode().equals("")) {
    		map.put("resultCode", 1);			// return ok
			map.put("resultMsg", "분류코드가 존재하지 않습니다.");
    		return map;
    	}

        beanValidator.validate(cmmnClCode, bindingResult);
		if (bindingResult.hasErrors()){
			map.put("resultCode", 1);			// return ok
			map.put("resultMsg", "입력값을 확인하세요.");
			return map;
		}
		
		if(cmmnClCode.getClCode() != null){
			CmmnClCode vo = cmmnClCodeManageService.selectCmmnClCodeDetail(cmmnClCode);
			if(vo != null){
				map.put("resultCode", 1);			// return ok
				map.put("resultMsg", "이미 등록된 분류코드가 존재합니다.");
				return map;
			}
		}
		
		cmmnClCode.setFrstRegisterId(loginVO.getUniqId());
		cmmnClCodeManageService.insertCmmnClCode(cmmnClCode);
		map.put("resultCode", 0);			// return ok
		map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		return map;
    }
    
    
	/**
	 * 공통분류코드를 수정한다.
	 * @param loginVO
	 * @param cmmnClCode
	 * @param bindingResult
	 * @return map;
	 * @throws Exception
	 */
    @RequestMapping(value="/code/gamCmmnClCodeModify.do")
	@ResponseBody Map<String, Object> updateCmmnClCode (@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("clCode") CmmnClCode cmmnClCode
			, BindingResult bindingResult, @ModelAttribute("cmd") String cmd) throws Exception {
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
		if (cmd.equals("")) {
    		CmmnClCode vo = cmmnClCodeManageService.selectCmmnClCodeDetail(cmmnClCode);
    		map.put("cmmnClCode", vo);

    		map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
    		return map;
    	} else if (cmd.equals("modify")) {
            beanValidator.validate(cmmnClCode, bindingResult);

    		if (bindingResult.hasErrors()){
        		CmmnClCode vo = cmmnClCodeManageService.selectCmmnClCodeDetail(cmmnClCode);
        		map.put("cmmnClCode", vo);

        		map.put("resultCode", 1);
        		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
        		return map;
    		}
    		cmmnClCode.setLastUpdusrId(loginVO.getUniqId());
	    	cmmnClCodeManageService.updateCmmnClCode(cmmnClCode);

	    	map.put("resultCode", 0);
	    	map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));

	    	return map;
    	} else {
    		return map;
    	}
    }
    
    /**
	 * 공통분류코드를 삭제한다.
	 * @param loginVO
	 * @param cmmnClCode
	 * @param model
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping(value="/code/gamCmmnClCodeRemove.do")
	@ResponseBody Map<String, Object> deleteCmmnClCode (@ModelAttribute("loginVO") LoginVO loginVO,  @ModelAttribute("clCode") CmmnClCode cmmnClCode) throws Exception {
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	cmmnClCodeManageService.deleteCmmnClCode(cmmnClCode);
        
    	map.put("resultCode", 0);
      	map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
    	return map;
	}
}