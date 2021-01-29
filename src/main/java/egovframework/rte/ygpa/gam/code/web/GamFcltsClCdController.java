/**
 * 
 */
package egovframework.rte.ygpa.gam.code.web;

import java.io.IOException;
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
import egovframework.rte.ygpa.gam.code.service.GamFcltsClCdService;
import egovframework.rte.ygpa.gam.code.service.GamFcltsClCdVO;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 17.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltsClCdController {
	
	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    
    @Resource(name = "gamFcltsClCdService")
	protected GamFcltsClCdService gamFcltsClCdService;
    
    
	@RequestMapping(value="/code/gamFcltsClCd.do")
    String indexFcltsClCd(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		
		List<?> mainFcltyClCdList = gamFcltsClCdService.selectMainFcltsClCdList();
		
    	model.addAttribute("windowId", windowId);
    	model.addAttribute("mainFcltyClCdList", mainFcltyClCdList);
    	return "/ygpa/gam/code/GamFcltsClCd";
    	
    }
	
	
	/**
	 * 시설물분류코드목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/selectFcltsClCdList.do")
	@ResponseBody Map<String, Object> selectFcltsClCdList(GamFcltsClCdVO searchVO)throws Exception {

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

		List<?> fcltyClCdList = gamFcltsClCdService.selectFcltsClCdList(searchVO);

        int totCnt = gamFcltsClCdService.selectFcltsClCdListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", fcltyClCdList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 시설물분류코드 상세
	 * @param fcltyManageVO 
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/selectFcltsClCdDetail.do")
    @ResponseBody Map<String, Object> selectFcltsClCdDetail(@RequestParam Map<String, Object> fcltsClCdVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	EgovMap result=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	result = gamFcltsClCdService.selectFcltsClCdDetail(fcltsClCdVO);

        map.put("resultCode", 0);
        map.put("result", result);

        return map;
    }
	
	
	
	/**
	 * 시설물분류 상위코드 리스트
	 * @param fcltyManageVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/selectFcltsClUpperCdList.do")
    @ResponseBody Map<String, Object> selectFcltsClUpperCdList(@RequestParam Map<String, Object> fcltsClCdVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	List<?> fcltsClUpperCdList = gamFcltsClCdService.selectFcltsClUpperCdList(fcltsClCdVO);

        map.put("resultCode", 0);
        map.put("fcltsClUpperCdList", fcltsClUpperCdList);

        return map;
    }
	
	
	/**
	 * 시설물분류 코드입력
	 * @param GamFcltsClCdVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/insertFcltsClCd.do")
    @ResponseBody Map<String, Object> insertFcltsClCd(GamFcltsClCdVO insertVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	String newFcltsClCd;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	insertVO.setRegUsr(user.getId());
    	
    	try {
    		
    		newFcltsClCd = gamFcltsClCdService.selectNewFcltsClCd(insertVO);
    		insertVO.setFcltsClCd(newFcltsClCd);
	    	gamFcltsClCdService.insertFcltsClCd(insertVO);
	    	
	    	insertVO.setLeafYn("N");
	    	gamFcltsClCdService.updateChnageParentLeafYn(insertVO);
	    	
	    	map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
	        map.put("resultCode", 0);
    	} catch(IOException e) {
    		
    	}catch (Exception e) {
			// TODO: handle exception
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

        return map;
    }
	
	
	/**
	 * 시설물분류 코드수정
	 * @param GamFcltsClCdVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/updateFcltsClCd.do")
    @ResponseBody Map<String, Object> updateFcltsClCd(GamFcltsClCdVO updateVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	String newFcltsClCd;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	updateVO.setUpdUsr(user.getId());
    	
    	try {
    		
    		newFcltsClCd = gamFcltsClCdService.selectNewFcltsClCd(updateVO);
    		updateVO.setFcltsClCd(newFcltsClCd);
	    	gamFcltsClCdService.updateFcltsClCd(updateVO);
	    	
	    	map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
	        map.put("resultCode", 0);
    	} catch(IOException e){
    		
    	} catch (Exception e) {
			// TODO: handle exception
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

        return map;
    }
	
	
	/**
	 * 시설물분류 코드삭제
	 * @param GamFcltsClCdVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/deleteFcltsClCd.do")
    @ResponseBody Map<String, Object> deleteFcltsClCd(GamFcltsClCdVO deleteVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	int codCnt;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	try {
	    	gamFcltsClCdService.deleteFcltsClCd(deleteVO);
	    	
	    	codCnt = gamFcltsClCdService.selectFcltsClParentCdListCnt(deleteVO);
	    	
	    	if(codCnt == 0){
	    		deleteVO.setLeafYn("Y");
	    		gamFcltsClCdService.updateChnageParentLeafYn(deleteVO);
	    	}

	    	map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
	        map.put("resultCode", 0);
    	} catch(IOException e){
    		
    	}catch (Exception e) {
			// TODO: handle exception
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

        return map;
    }
    

}
