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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.code.service.GamFcltsMngGroupService;
import egovframework.rte.ygpa.gam.code.service.GamFcltsMngGroupVO;

/**
 * 
 * @author HNJ
 * @since 2014. 12. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 5.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltsMngGroupController {
	
	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    
    @Resource(name = "gamFcltsMngGroupService")
	protected GamFcltsMngGroupService gamFcltsMngGroupService;
    
    
	@RequestMapping(value="/code/gamFcltsMngGroup.do")
    String indexFcltsMngGroup(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		
		List<?> mainFcltsMngGroupList = gamFcltsMngGroupService.selectMainFcltsMngGroupList();
		
    	model.addAttribute("windowId", windowId);
    	model.addAttribute("mainFcltsMngGroupList", mainFcltsMngGroupList);
    	return "/ygpa/gam/code/GamFcltsMngGroup";
    	
    }
	
	
	/**
	 * 시설물그룹관리목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/selectFcltsMngGroupList.do")
	@ResponseBody Map<String, Object> selectFcltsMngGroupList(GamFcltsMngGroupVO searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */

		List<?> fcltyClCdList = gamFcltsMngGroupService.selectFcltsMngGroupList(searchVO);

        int totCnt = gamFcltsMngGroupService.selectFcltsMngGroupListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", fcltyClCdList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 시설물그룹관리 상세
	 * @param fcltyManageVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/selectFcltsMngGroupDetail.do")
    @ResponseBody Map<String, Object> selectFcltsMngGroupDetail(@RequestParam Map<String, Object> fcltsClCdVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	EgovMap result=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	result = gamFcltsMngGroupService.selectFcltsMngGroupDetail(fcltsClCdVO);
    	
    	fcltsClCdVO.put("mainFcltsDiv", result.get("mainFcltsDiv"));
    	fcltsClCdVO.put("depthSort", result.get("depthSort"));

        map.put("resultCode", 0);
        map.put("result", result);

        return map;
    }
	
	
	
	/**
	 * 시설물분류 코드입력
	 * @param GamFcltsMngGroupVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/insertFcltsMngGroup.do")
    @ResponseBody Map<String, Object> insertFcltsMngGroup(GamFcltsMngGroupVO insertVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	String newFcltsMngGroup;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	insertVO.setRegUsr(user.getId());
    	
    	try {
    		
    		newFcltsMngGroup = gamFcltsMngGroupService.selectNewFcltsMngGroup(insertVO);
    		insertVO.setFcltsMngGroup(newFcltsMngGroup);
	    	gamFcltsMngGroupService.insertFcltsMngGroup(insertVO);
	    	
	    	insertVO.setLeafYn("N");
	    	gamFcltsMngGroupService.updateChnageParentLeafYn(insertVO);
	    	
	    	map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
	        map.put("resultCode", 0);
    	} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

        return map;
    }
	
	
	/**
	 * 시설물분류 코드수정
	 * @param GamFcltsMngGroupVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/updateFcltsMngGroup.do")
    @ResponseBody Map<String, Object> updateFcltsMngGroup(GamFcltsMngGroupVO updateVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	String newFcltsMngGroup;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	updateVO.setUpdUsr(user.getId());
    	
    	try {
    		
    		newFcltsMngGroup = gamFcltsMngGroupService.selectNewFcltsMngGroup(updateVO);
    		updateVO.setFcltsMngGroup(newFcltsMngGroup);
	    	gamFcltsMngGroupService.updateFcltsMngGroup(updateVO);
	    	
	    	map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
	        map.put("resultCode", 0);
    	} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

        return map;
    }
	
	
	/**
	 * 시설물분류 코드삭제
	 * @param GamFcltsMngGroupVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/deleteFcltsMngGroup.do")
    @ResponseBody Map<String, Object> deleteFcltsMngGroup(GamFcltsMngGroupVO deleteVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	int codCnt;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	try {
	    	gamFcltsMngGroupService.deleteFcltsMngGroup(deleteVO);
	    	
	    	codCnt = gamFcltsMngGroupService.selectFcltsClParentCdListCnt(deleteVO);
	    	
	    	if(codCnt == 0){
	    		deleteVO.setLeafYn("Y");
	    		gamFcltsMngGroupService.updateChnageParentLeafYn(deleteVO);
	    	}

	    	map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
	        map.put("resultCode", 0);
    	} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

        return map;
    }
    

}
