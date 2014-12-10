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
 * @author 김종민
 * @since 2014. 12. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 10.		김종민		최초 생성
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
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/code/GamFcltsMngGroup";
    }
	
	/**
	 * 시설물관리그룹 목록 조회
	 * @param vo
	 * @return map
	 * @throws Exception
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/code/selectFcltsMngGroupList.do")
	@ResponseBody Map<String, Object> selectFcltsMngGroupList(GamFcltsMngGroupVO searchVO) throws Exception {

		Map map = new HashMap();

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

		List resultList = gamFcltsMngGroupService.selectFcltsMngGroupList(searchVO);
		int totCnt = gamFcltsMngGroupService.selectFcltsMngGroupListTotCnt(searchVO);
		
        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }


	/**
	 * 시설물 관리그룹 데이터 조회
	 * @param map
	 * @return map
	 * @throws Exception
	 */		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/code/selectFcltsMngGroupDetail.do")
    @ResponseBody Map<String, Object> selectFcltsMngGroupDetail(@RequestParam Map searchVO) throws Exception {
    	Map map = new HashMap();
    	EgovMap result=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
        	result = gamFcltsMngGroupService.selectFcltsMngGroupDetail(searchVO);
            map.put("resultCode", 0);
            map.put("result", result);
    	}
    	catch(Exception e) {
            map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
    	}
        return map;		
	}
	
	/**
	 * 시설물관리그룹 데이터를 삽입한다.
	 * @param map
	 * @return 
	 * @throws Exception
	 */		
	@RequestMapping(value="/code/insertFcltsMngGroupDetail.do")
    @ResponseBody Map<String, Object> insertFcltsMngGroupDetail(@RequestParam Map<String, Object> insertMap) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	insertMap.put("regUsr", user.getId());
    	
    	try {
    		gamFcltsMngGroupService.insertFcltsMngGroupDetail(insertMap);
    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}
      	return map;		
	}
	
	/**
	 * 시설물관리그룹 데이터를 수정한다.
	 * @param map
	 * @return map
	 * @throws Exception
	 */		
	@RequestMapping(value="/code/updateFcltsMngGroupDetail.do")
    @ResponseBody Map<String, Object> updateFcltsMngGroupDetail(@RequestParam Map<String, Object> updateMap) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	updateMap.put("updUsr", user.getId());
    	
    	try {
    		gamFcltsMngGroupService.updateFcltsMngGroupDetail(updateMap);
    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}
      	return map;		
	}

	/**
	 * 시설물관리그룹 데이터를 삭제한다.
	 * @param map
	 * @return map
	 * @throws Exception
	 */		
	@RequestMapping(value="/code/deleteFcltsMngGroupDetail.do")
    @ResponseBody Map<String, Object> deleteFcltsMngGroupDetail(@RequestParam Map<String, Object> deleteMap) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		gamFcltsMngGroupService.deleteFcltsMngGroupDetail(deleteMap);
    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}
      	return map;		
	}
	
}
