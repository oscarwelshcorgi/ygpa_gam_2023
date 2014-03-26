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
    
    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    
    
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
	 * 도면 정보 목록
	 * @param searchVO
	 * @param drwLstRegistYear
	 * @param drwLstSeq
	 * @param drwLstNm
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamDrwListMngtList.do")
	@ResponseBody Map<String, Object> selectDrwListMngtList(GamFcltyDrwInfoFVO searchVO,
			@RequestParam("searchDrwLstRegistYear") String drwLstRegistYear, @RequestParam("searchDrwLstSeq") String drwLstSeq, @RequestParam("searchDrwLstNm") String drwLstNm,
			@RequestParam("searchAuthnm") String authnm, @RequestParam("searchDeptCd") String deptCd)throws Exception {
		
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
		
		searchVO.setDrwLstRegistYear(drwLstRegistYear);
		searchVO.setDrwLstSeq(drwLstSeq);
		searchVO.setDrwLstNm(drwLstNm);
		searchVO.setAuthnm(authnm);
		searchVO.setDrwLstMngDeptCd(deptCd);
		
		/** List Data */
		List<GamFcltyDrwInfoFVO> drwMngtList = gamFcltyDrwMngtService.selectDrwListMngtList(searchVO);
		int totCnt = gamFcltyDrwMngtService.selectDrwListMngtListTotCnt(searchVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		map.put("resultCode", 0);			// return ok
		map.put("totalCount", totCnt);
		map.put("resultList", drwMngtList);
		map.put("searchOption", searchVO);
		
		return map;
	}
	
	
	/**
	 * 도면 파일 목록
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamDrwListPhotoList.do")
	@ResponseBody Map<String, Object> selectDrwListPhotoList(GamFcltyDrwDtaFVO searchVO)throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();

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
		
		List<ComDefaultVO> DrwListPhotoList = gamFcltyDrwMngtService.selectDrwListPhotoList(searchVO);
		int totCnt = gamFcltyDrwMngtService.selectDrwListPhotoListTotCnt(searchVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		map.put("resultCode", 0);			// return ok
		map.put("totalCount", totCnt);
		map.put("resultList", DrwListPhotoList);
		map.put("searchOption", searchVO);
		
		return map;
	}
	
	
	/**
	 * 도면 정보 저장
	 * @param drwListManageVO
	 * @param bindingResult
	 * @param cmd
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamDrwInfoListMngInsert.do")
	@ResponseBody Map<String, Object> insertDrwInfoListMng(@RequestParam Map<String, Object> drwListMngtList) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
    	try {
    		drwListMngtList.put("USERID",user.getId());
    		gamFcltyDrwMngtService.insertDrwListMng(drwListMngtList);

    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));	
		} catch (Exception e) {
			// TODO: handle exception
			map.put("resultCode", 1);			// return ok
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;
	}
	
	
	/**
	 * 도면 정보 수정
	 * @param drwListManageVO
	 * @param bindingResult
	 * @param cmd
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamDrwListMngUpdate.do")
	@ResponseBody Map<String, Object> updateDrwListMng(@RequestParam Map<String, Object> drwListMngtList)throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
    	try {
    		drwListMngtList.put("USERID",user.getId());
    		gamFcltyDrwMngtService.updateDrwListMng(drwListMngtList);
    		
    		map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			// TODO: handle exception
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}
		
		return map;
	}

	
	/**
	 * 도면 자료 삭제
	 * @param drwListManageVO
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping("/fclty/gamDrwListMngDelete.do")
    @ResponseBody Map<String, Object> deleteDrwListMng(@RequestParam("drwLstRegistYear") String drwLstRegistYear, @RequestParam String drwLstSeq) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	try {
    		
    		GamFcltyDrwDtaFVO vo = new GamFcltyDrwDtaFVO();
    		vo.setDrwLstRegistYear(drwLstRegistYear);
    		vo.setDrwLstSeq(drwLstSeq);
    		gamFcltyDrwMngtService.deleteDrwListMng(vo);

            map.put("resultCode", 0);
            map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));

		} catch (Exception e) {
			// TODO: handle exception
			map.put("resultCode", 1);			// return ok
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

        return map;
    }
}