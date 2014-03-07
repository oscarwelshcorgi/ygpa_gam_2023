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
public class GamFcltyMngtController {

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
    
	
	/**
	 * 기계시설 관리화면호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamMechFcltyMngt.do")
	String indexMechFcltyMngt(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
		return "/ygpa/gam/fclty/GamMechFcltyMngt";
	}
	
	
	/**
	 * 기계시설 조회화면호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamMechFcltyInqire.do")
	String indexMechFcltyInqire(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
		return "/ygpa/gam/fclty/GamMechFcltyInqire";
	}
	
	
	/**
	 * 정보통신시설 관리화면호출
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
	 * 정보통신시설 조회화면호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamInfoTechInqire.do")
	String indexInfoTechInqire(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
		return "/ygpa/gam/fclty/GamInfoTechInqire";
	}
	
	
	/**
	 * 토목시설 관리화면호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamCivilFcltyMngt.do")
	String indexCivilFcltyMngt(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
		return "/ygpa/gam/fclty/GamCivilFcltyMngt";
	}
	
	
	/**
	 * 토목시설 조회화면호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamCivilFcltyInqire.do")
	String indexCivilFcltyInqire(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
		return "/ygpa/gam/fclty/GamCivilFcltyInqire";
	}

	
	/**
	 * 시설관리목록
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamFcltyMngtList.do")
	@ResponseBody Map<String, Object> selectFcltyMngtList(GamFcltyManageVO searchVO, @RequestParam("uniqFcltyCd") String uniqFcltyCd)throws Exception {

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
		searchVO.setSearchCondition(uniqFcltyCd);

		/** List Data */
		List<ComDefaultVO> fcltyMngtList = gamFcltyMngtService.selectFcltyMngtList(searchVO);
        int totCnt = gamFcltyMngtService.selectFcltyMngtListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
		
		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", fcltyMngtList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 시설관리 등록 시 시퀀스 값 가져오기
	 * @param fcltyManageVO
	 * @param bindingResult
	 * @param cmd
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamFcltyGetInsertSeq.do")
	@ResponseBody Map<String, Object> insertFcltyGetSeq() throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		map.put("seq", gamFcltyMngtService.insertFcltyGetSeq());
		return map;
	}
	
	
	/**
	 * 시설관리등록
	 * @param fcltyManageVO
	 * @param bindingResult
	 * @param cmd
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamFcltyInsert.do")
    @ResponseBody Map<String, Object> insertFclty(@ModelAttribute("fcltyManageVO") GamFcltyManageVO fcltyManageVO,BindingResult bindingResult, @RequestParam("cmd") String cmd)
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
		    beanValidator.validate(fcltyManageVO, bindingResult);
		    if (bindingResult.hasErrors()){
		        map.put("resultCode", 1);
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}

		   gamFcltyMngtService.insertFcltyManage(fcltyManageVO);

			map.put("resultCode", 0);			// return ok
    		resultMsg = egovMessageSource.getMessage("success.common.insert");
    	}

        map.put("resultMsg", resultMsg);
      	return map;
    }

	
	/**
	 * 시설관리 상세화면
	 * @param fcltyManageVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamFcltyMngSelectView.do")
    @ResponseBody Map<String, Object> fcltyMngSelectView(@ModelAttribute("fcltyManageVO") GamFcltyManageVO fcltyManageVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	fcltyManageVO = gamFcltyMngtService.fcltyMngSelectView(fcltyManageVO);

        map.put("detail", fcltyManageVO);

        return map;
    }

	
	/**
     * 시설관리를 수정한다.
     * @param userManageVO
     * @param bindingResult
     * @return String
     * @throws Exception
     */
    @RequestMapping("/fclty/gamFcltyUpdate.do")
    @ResponseBody Map<String, Object> updateFclty(@ModelAttribute("fcltyManageVO") GamFcltyManageVO fcltyManageVO,BindingResult bindingResult)throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

        beanValidator.validate(fcltyManageVO, bindingResult);
		if (bindingResult.hasErrors()){
	        map.put("resultCode", 1);
			map.put("resultMsg", "입력 값에 오류가 있습니다.");
			map.put("resultObject", bindingResult.getAllErrors());

		}else{
			
			gamFcltyMngtService.updateFclty(fcltyManageVO);
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
    @RequestMapping("/fclty/gamFcltyDelete.do")
    @ResponseBody Map<String, Object> deleteFclty(@ModelAttribute("fcltyManageVO") GamFcltyManageVO fcltyManageVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	gamFcltyMngtService.deleteFclty(fcltyManageVO);

        map.put("resultCode", 0);
        map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
        return map;
    }
}