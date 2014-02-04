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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.com.uss.umt.service.UserManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltyManageVO;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltyMngtService;

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
public class GamCivilFcltyMngtController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@Resource(name = "gamCivilFcltyMngtService")
	protected GamCivilFcltyMngtService gamCivilFcltyMngtService;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	/**
     * 화면호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/fclty/gamCivilFcltyMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fclty/GamCivilFcltyMngt";
    }
	
	
	/**
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamCivilFcltyMngtList.do")
	@ResponseBody Map<String, Object> selectCivilFcltyMngtList(@ModelAttribute("searchVO") ComDefaultVO searchVO)throws Exception {

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
		List<ComDefaultVO> civilFcltyMngtList = gamCivilFcltyMngtService.selectCivilFcltyMngtList(searchVO);
        int totCnt = gamCivilFcltyMngtService.selectCivilFcltyMngtListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
		
		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", civilFcltyMngtList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 시설관리등록
	 * @param civilFcltyManageVO
	 * @param bindingResult
	 * @param cmd
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamCivilFcltyInsert.do")
    @ResponseBody Map<String, Object> insertCivilFclty(@ModelAttribute("civilFcltyManageVO") GamCivilFcltyManageVO civilFcltyManageVO,BindingResult bindingResult, @RequestParam("cmd") String cmd)
            throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	String resultMsg    = "";
    	
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	if("insert".equals(cmd)) {
		    beanValidator.validate(civilFcltyManageVO, bindingResult);
		    if (bindingResult.hasErrors()){
		        map.put("resultCode", 1);
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}

		   gamCivilFcltyMngtService.insertCivilFcltyManage(civilFcltyManageVO);

			map.put("resultCode", 0);			// return ok
    		resultMsg = egovMessageSource.getMessage("success.common.insert");
    	}

        map.put("resultMsg", resultMsg);
      	return map;
    }

	
	/**
	 * 시설관리 상세화면
	 * @param civilFcltyManageVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamCivilFcltyMngSelectView.do")
    @ResponseBody Map<String, Object> civilFcltyMngSelectView(@ModelAttribute("civilFcltyManageVO") GamCivilFcltyManageVO civilFcltyManageVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	civilFcltyManageVO = gamCivilFcltyMngtService.civilFcltyMngSelectView(civilFcltyManageVO);

        map.put("detail", civilFcltyManageVO);

        return map;
    }

	
	/**
     * 시설관리를 수정한다.
     * @param userManageVO
     * @param bindingResult
     * @return String
     * @throws Exception
     */
    @RequestMapping("/fclty/gamCivilFcltyUpdate.do")
    @ResponseBody Map<String, Object> updateCivilFclty(@ModelAttribute("civilFcltyManageVO") GamCivilFcltyManageVO civilFcltyManageVO,BindingResult bindingResult)throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

        beanValidator.validate(civilFcltyManageVO, bindingResult);
		if (bindingResult.hasErrors()){
	        map.put("resultCode", 1);
			map.put("resultMsg", "입력 값에 오류가 있습니다.");
			map.put("resultObject", bindingResult.getAllErrors());

		}else{
			
			gamCivilFcltyMngtService.updateCivilFclty(civilFcltyManageVO);
			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		}

        return map;
    }
    
    
    /**
     * 사용자정보삭제후 목록조회 화면으로 이동한다.
     * @param checkedId
     * @param userSearchVO
     * @return map
     * @throws Exception
     */
    @RequestMapping("/fclty/gamCivilFcltyDelete.do")
    @ResponseBody Map<String, Object> deleteCivilFclty(@ModelAttribute("civilFcltyManageVO") GamCivilFcltyManageVO civilFcltyManageVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	gamCivilFcltyMngtService.deleteCivilFclty(civilFcltyManageVO);

        map.put("resultCode", 0);
        map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
        return map;
    }
}