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
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwDtaFVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwInfoFVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwMngtService;

/**
 * 
 * @author kok
 * @since 2014. 2. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 10.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltyDrwListMngtController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@Resource(name = "gamFcltyDrwMngtService")
	protected GamFcltyDrwMngtService gamFcltyDrwMngtService;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	/**
	 * 도면시설 관리화면호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamDrwListMngt.do")
	String indexDrwListMngt(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
		return "/ygpa/gam/fclty/GamDrwListMngt";
	}
	
	
	/**
	 * 도면시설관리목록 도면정보 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamFcltyDrwMngtInfoList.do")
	@ResponseBody Map<String, Object> selectFcltyDrwMngtInfoList(GamFcltyDrwDtaFVO searchVO)throws Exception {
		
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
		List<ComDefaultVO> fcltyDrwMngtList = gamFcltyDrwMngtService.selectFcltyDrwMngtInfoList(searchVO);
		int totCnt = gamFcltyDrwMngtService.selectFcltyDrwMngtInfoListTotCnt(searchVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		map.put("resultCode", 0);			// return ok
		map.put("totalCount", totCnt);
		map.put("resultList", fcltyDrwMngtList);
		map.put("searchOption", searchVO);
		
		return map;
	}
	
	
	/**
	 * 도면시설관리목록
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamFcltyDrwMngtList.do")
	@ResponseBody Map<String, Object> selectFcltyDrwMngtList(GamFcltyDrwDtaFVO searchVO, GamFcltyDrwInfoFVO fcltyDrwInfoFVO)throws Exception {

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
		
		/** infoDetail Data */
		fcltyDrwInfoFVO = gamFcltyDrwMngtService.fcltyDrwInfoListMngSelectView(fcltyDrwInfoFVO);
        map.put("detail", fcltyDrwInfoFVO);
		
		/** List Data */
		List<ComDefaultVO> fcltyDrwMngtList = gamFcltyDrwMngtService.selectFcltyDrwMngtList(searchVO);
        int totCnt = gamFcltyDrwMngtService.selectFcltyDrwMngtListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
		
		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", fcltyDrwMngtList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 시설관리 등록 시 시퀀스 값 가져오기
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamFcltyDrwGetInsertSeq.do")
	@ResponseBody Map<String, Object> insertFcltyGetSeq() throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		map.put("seq", gamFcltyDrwMngtService.insertFcltyGetSeq());
		return map;
	}

	
	/**
	 * 시설관리 등록 시 시퀀스 값 가져오기
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamFcltyDrwInfoGetInsertSeq.do")
	@ResponseBody Map<String, Object> insertFcltyInfoGetSeq() throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		map.put("seq", gamFcltyDrwMngtService.insertFcltyInfoGetSeq());
		return map;
	}
	
	
	/**
	 * 도면시설관리등록
	 * @param fcltyManageVO
	 * @param bindingResult
	 * @param cmd
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamFcltyDrwInfoListMngInsert.do")
	@ResponseBody Map<String, Object> insertFcltyDrwInfoListMng(@ModelAttribute("drwListManageVO") GamFcltyDrwInfoFVO drwListManageVO,BindingResult bindingResult, @RequestParam("cmd") String cmd)
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
			beanValidator.validate(drwListManageVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			
			gamFcltyDrwMngtService.insertFcltyDrwInfoListMng(drwListManageVO);
			
			map.put("resultCode", 0);			// return ok
			resultMsg = egovMessageSource.getMessage("success.common.insert");
		}
		
		map.put("resultMsg", resultMsg);
		return map;
	}
	
	
	/**
	 * 도면시설관리등록
	 * @param fcltyManageVO
	 * @param bindingResult
	 * @param cmd
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamFcltyDrwListMngInsert.do")
    @ResponseBody Map<String, Object> insertFcltyDrwListMng(@ModelAttribute("drwDtaListManageVO") GamFcltyDrwDtaFVO drwListManageVO,BindingResult bindingResult,
    		@RequestParam("dtaCmd") String cmd, @RequestParam("drwLstRegistYearSub") String drwLstRegistYearSub, @RequestParam("drwLstSeqSub") String drwLstSeqSub,
    		@RequestParam("regUsrSub") String regUsrSub)
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
		    beanValidator.validate(drwListManageVO, bindingResult);
		    if (bindingResult.hasErrors()){
		        map.put("resultCode", 1);
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}

		    
		    drwListManageVO.setDrwLstRegistYear(drwLstRegistYearSub);
		    drwListManageVO.setDrwLstSeq(drwLstSeqSub);
		    drwListManageVO.setRegUsr(regUsrSub);
		    gamFcltyDrwMngtService.insertFcltyDrwListMng(drwListManageVO);

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
	@RequestMapping(value="/fclty/gamFcltyDrwListMngSelectView.do")
    @ResponseBody Map<String, Object> fcltyDrwListMngSelectView(@ModelAttribute("drwListManageVO") GamFcltyDrwDtaFVO drwListManageVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	drwListManageVO = gamFcltyDrwMngtService.fcltyDrwListMngSelectView(drwListManageVO);

        map.put("detail", drwListManageVO);

        return map;
    }

	
	/**
     * 시설관리를 수정한다.
     * @param userManageVO
     * @param bindingResult
     * @return String
     * @throws Exception
     */
    @RequestMapping("/fclty/gamFcltyDrwListMngUpdate.do")
    @ResponseBody Map<String, Object> updateFcltyDrwListMng(@ModelAttribute("drwListManageVO") GamFcltyDrwDtaFVO drwListManageVO,BindingResult bindingResult)throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

        beanValidator.validate(drwListManageVO, bindingResult);
		if (bindingResult.hasErrors()){
	        map.put("resultCode", 1);
			map.put("resultMsg", "입력 값에 오류가 있습니다.");
			map.put("resultObject", bindingResult.getAllErrors());

		}else{
			
			gamFcltyDrwMngtService.updateFcltyDrwListMng(drwListManageVO);
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
    @RequestMapping("/fclty/gamFcltyDrwListMngDelete.do")
    @ResponseBody Map<String, Object> deleteFcltyDrwListMng(@ModelAttribute("drwListManageVO") GamFcltyDrwDtaFVO drwListManageVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	gamFcltyDrwMngtService.deleteFcltyDrwListMng(drwListManageVO);

        map.put("resultCode", 0);
        map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
        return map;
    }
}