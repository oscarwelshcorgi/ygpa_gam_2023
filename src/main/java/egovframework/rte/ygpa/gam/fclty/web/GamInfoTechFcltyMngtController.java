/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyManageVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyMngtService;

/**
 * 
 * @author kok
 * @since 2014. 2. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 3.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamInfoTechFcltyMngtController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@Resource(name = "gamFcltyMngtService")
	protected GamFcltyMngtService gamFcltyMngtService;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    
    private final static String prtFcltySe = "E";
    
	/**
     * 정보통신 시설시설 관리화면호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/fclty/gamInfoTechFcltyMngt.do")
    String indexInfoTechFcltyMngt(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fclty/GamInfoTechFcltyMngt";
    }
	
	
	/**
	 * 정보통신 시설시설 조회화면호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamInfoTechFcltyInqire.do")
	String indexInfoTechFcltyInqire(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
		return "/ygpa/gam/fclty/GamInfoTechFcltyInqire";
	}
	
	
	
	/**
	 * 정보통신 시설시설목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamInfoTechFcltyMngtList.do")
	@ResponseBody Map<String, Object> selectFcltyMngtList(GamFcltyManageVO searchVO)throws Exception {

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
		searchVO.setPrtFcltySe(prtFcltySe);
		List<ComDefaultVO> fcltyMngtList = gamFcltyMngtService.selectFcltyMngtList(searchVO);
        int totCnt = gamFcltyMngtService.selectFcltyMngtListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
		
		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", fcltyMngtList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 정보통신 시설 파일 목록
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamInfoTechFcltyPhotoList.do")
	@ResponseBody Map<String, Object> selectFcltyMngtPhotoList(GamFcltyManageVO searchVO)throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();

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
		searchVO.setPrtFcltySe(prtFcltySe);

		List<ComDefaultVO> fcltyMngtPhotoList = gamFcltyMngtService.selectFcltyMngtPhotoList(searchVO);
		int totCnt = gamFcltyMngtService.selectFcltyMngtPhotoListTotCnt(searchVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
		
		map.put("resultCode", 0);			// return ok
		map.put("totalCount", totCnt);
		map.put("resultList", fcltyMngtPhotoList);
		map.put("searchOption", searchVO);
		
		return map;
	}
	
	
	/**
	 * 정보통신 시설 시설관리 등록
	 * @param fcltyManageVO
	 * @param bindingResult
	 * @param cmd
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamInfoTechFcltyInsert.do")
	@ResponseBody Map<String, Object> insertFclty(@RequestParam Map<String, Object> fcltyMngtList) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	fcltyMngtList.put("USERID",user.getId());
    	fcltyMngtList.put("prtFcltySe",prtFcltySe);

    	try {
    		gamFcltyMngtService.insertFcltyManage(fcltyMngtList);

    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

      	return map;
    }

	
	/**
	 * 정보통신 시설 시설관리 상세
	 * @param fcltyManageVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamInfoTechFcltyView.do")
    @ResponseBody Map<String, Object> fcltyMngSelectView(@ModelAttribute("fcltyManageVO") GamFcltyManageVO fcltyManageVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	fcltyManageVO = gamFcltyMngtService.fcltyMngSelectView(fcltyManageVO);

        map.put("detail", fcltyManageVO);

        return map;
    }

	
	/**
	 * 정보통신 시설 시설관리 수정
	 * @param fcltyManageVO
	 * @param bindingResult
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping("/fclty/gamInfoTechFcltyUpdate.do")
    @ResponseBody Map<String, Object> updateFclty(@RequestParam Map<String, Object> fcltyMngtList)throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	fcltyMngtList.put("USERID", user.getId());
    	fcltyMngtList.put("prtFcltySe",prtFcltySe);
    	
    	try {
    		gamFcltyMngtService.updateFclty(fcltyMngtList);
    		map.put("resultCode", 0);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

    	return map;
    }
    
    
    /**
     * 정보통신 시설 시설관리 삭제
     * @param fcltyManageVO
     * @return map
     * @throws Exception
     */
    @RequestMapping("/fclty/gamInfoTechFcltyDelete.do")
    @ResponseBody Map<String, Object> deleteFclty(@ModelAttribute("fcltyManageVO") GamFcltyManageVO fcltyManageVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	fcltyManageVO.setPrtFcltySe(prtFcltySe);

    	try {
    		gamFcltyMngtService.deleteFcltyMngt(fcltyManageVO);

            map.put("resultCode", 0);
            map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

    	return map;
    }
}