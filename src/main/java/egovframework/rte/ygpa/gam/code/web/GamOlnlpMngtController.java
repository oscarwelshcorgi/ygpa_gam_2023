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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.code.service.GamOlnlpFVO;
import egovframework.rte.ygpa.gam.code.service.GamOlnlpMngtService;
import egovframework.rte.ygpa.gam.code.service.GisAssetsCodeVO;

/**
 * 
 * @author kok
 * @since 2014. 3. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 7.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamOlnlpMngtController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@Resource(name = "gamOlnlpMngtService")
	protected GamOlnlpMngtService gamOlnlpMngtService;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    
	/**
     * 공시지가 관리화면호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/code/gamOlnlpMngt.do")
    String indexConstOlnlpMngt(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/code/GamOlnlpMngt";
    }
	
	
	/**
	 * 공시지가 조회화면호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/code/gamOlnlpList.do")
	String indexConstOlnlpInqire(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
		return "/ygpa/gam/code/GamOlnlpList";
	}
	
	
	/**
	 * 공시지가 등록 현황 목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/gamOlnlpInsertList.do")
	@ResponseBody Map<String, Object> selectOlnlpInsertList(GisAssetsCodeVO searchVO)throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		// 내역 조회
		/** EgovPropertyService */
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
		
		/** List Data */
		List<GisAssetsCodeVO> OlnlpMngtList = gamOlnlpMngtService.selectOlnlpInsertList(searchVO);
		int totCnt = gamOlnlpMngtService.selectOlnlpInsertListTotCnt(searchVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		map.put("resultCode", 0);			// return ok
		map.put("totalCount", totCnt);
		map.put("resultList", OlnlpMngtList);
		map.put("searchOption", searchVO);
		
		return map;
	}
	
	
	/**
	 * 공시지가 목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/gamOlnlpMngtList.do")
	@ResponseBody Map<String, Object> selectOlnlpMngtList(GamOlnlpFVO searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회
    	/** EgovPropertyService */
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

		System.out.println("getGisAssetsCd : " + searchVO.getGisAssetsCd());
		System.out.println("getGisAssetsPrtAtCode : " + searchVO.getGisAssetsPrtAtCode());
		System.out.println("getGisAssetsSubCd : " + searchVO.getGisAssetsSubCd());
		
		/** List Data */
		List<GamOlnlpFVO> OlnlpMngtList = gamOlnlpMngtService.selectOlnlpMngtList(searchVO);
        int totCnt = gamOlnlpMngtService.selectOlnlpMngtListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
		
		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", OlnlpMngtList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 공시지가 목록 관리 수정
	 * @param olnlpManageVO
	 * @param bindingResult
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping("/code/updateOlnlpMngt.do")
    @ResponseBody Map<String, Object> updateOlnlpMngt(GamOlnlpFVO olnlpVO,BindingResult bindingResult)throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

        beanValidator.validate(olnlpVO, bindingResult);
		if (bindingResult.hasErrors()){
	        map.put("resultCode", 1);
			map.put("resultMsg", "입력 값에 오류가 있습니다.");
			map.put("resultObject", bindingResult.getAllErrors());

		}else{

			olnlpVO.setUpdUsr(user.getId());
			gamOlnlpMngtService.updateOlnlpMngt(olnlpVO);
			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		}

        return map;
    }
    
    
    /**
     * 건축 시설관리 삭제
     * @param olnlpManageVO
     * @return map
     * @throws Exception
     */
    @RequestMapping("/code/gamOlnlpDelete.do")
    @ResponseBody Map<String, Object> deleteOlnlpMngt(GamOlnlpFVO olnlpVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	gamOlnlpMngtService.deleteOlnlpMngt(olnlpVO);

        map.put("resultCode", 0);
        map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
        return map;
    }
}