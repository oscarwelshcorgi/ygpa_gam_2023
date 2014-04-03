package egovframework.rte.ygpa.gam.cmmn.web;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import egovframework.com.sym.prm.service.EgovProgrmManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class GamMenuMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** EgovMenuManageService */
	@Resource(name = "meunManageService")
    private EgovMenuManageService menuManageService;
	
	/** EgovMenuManageService */
	@Resource(name = "progrmManageService")
	private EgovProgrmManageService progrmManageService;
	
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
	@RequestMapping(value="/cmmn/gamMenuMng.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/cmmn/GamMenuMng";
    }
	
	/**
	 * 메뉴목록 리스트조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/cmmn/gamMenuManageSelect.do")
	@ResponseBody Map<String, Object> selectMenuManageList(@ModelAttribute("searchVO") ComDefaultVO searchVO)throws Exception {

		Map map = new HashMap();
		
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
		List list_menumanage = menuManageService.selectMenuManageList(searchVO);
        int totCnt = menuManageService.selectMenuManageListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
		
		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", list_menumanage);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 메뉴리스트의 메뉴정보를 등록한다.
	 * @param menuManageVO
	 * @param bindingResult
	 * @param cmd
	 * @return Map
	 * @throws Exception
	 */
    @RequestMapping(value="/cmmn/gamMenuListInsert.do")
    @ResponseBody Map<String, Object> insertMenuList(@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,BindingResult bindingResult, @RequestParam("cmd") String cmd)
            throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	String resultMsg    = "";
    	
	    if(menuManageService.selectMenuNoByPk(menuManageVO) == 0){
			ComDefaultVO searchVO = new ComDefaultVO();
			searchVO.setSearchKeyword(menuManageVO.getProgrmFileNm());
			if(progrmManageService.selectProgrmNMTotCnt(searchVO)==0){
				map.put("resultCode", 1);
	    		resultMsg = egovMessageSource.getMessage("fail.common.insert");
			}else{
				
				try {
					menuManageService.insertMenuManage(menuManageVO);
		        	map.put("resultCode", 0);			// return ok
		    		resultMsg = egovMessageSource.getMessage("success.common.insert");	
				} catch (Exception e) {
					// TODO: handle exception
					map.put("resultCode", 1);			// return ok
		    		resultMsg = egovMessageSource.getMessage("fail.common.insert");
				}
			}
		}else{
    		resultMsg = egovMessageSource.getMessage("common.isExist.msg");
		}

        map.put("resultMsg", resultMsg);
      	return map;
    }
    
    
    /**
     * 프로그램목록을 수정 한다.
     * @param menuManageVO
     * @param bindingResult
     * @param model
     * @return Map
     * @throws Exception
     */
    @RequestMapping(value="/cmmn/gamMenuListUpdt.do")
    @ResponseBody Map<String, Object> updateMenuManage(@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,BindingResult bindingResult,ModelMap model) throws Exception {
    	
		Map<String, Object> map = new HashMap<String, Object>();
		String resultMsg = "";

		ComDefaultVO searchVO = new ComDefaultVO();
		searchVO.setSearchKeyword(menuManageVO.getProgrmFileNm());

		if(progrmManageService.selectProgrmNMTotCnt(searchVO)==0){
			map.put("resultCode", 1);
    		resultMsg = egovMessageSource.getMessage("fail.common.update");
		}else{
			try {
				menuManageService.updateMenuManage(menuManageVO);
				map.put("resultCode", 0);
		    	resultMsg = egovMessageSource.getMessage("success.common.update");	
			} catch (Exception e) {
				// TODO: handle exception
				map.put("resultCode", 1);
		    	resultMsg = egovMessageSource.getMessage("fail.common.update");
			}
		}

        map.put("resultMsg", resultMsg);
    	return map;
    }
    
    
    /**
     * 메뉴리스트의 메뉴정보를 삭제한다.
     * @param menuManageVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/cmmn/gamMenuListDelete.do")
    @ResponseBody Map<String, Object> deleteMenuList(@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,BindingResult bindingResult)throws Exception {
        
    	Map<String, Object> map = new HashMap<String, Object>();

        try {
        	menuManageService.deleteMenuManage(menuManageVO);

    		map.put("resultCode", 0);
          	map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));	
		} catch (Exception e) {
			// TODO: handle exception
			map.put("resultCode", 1);
          	map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}
		
		return map;
    }
    

	/**
	 * 프로그램검색 리스트 팝업 호출
	 * @param searchVO
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/cmmn/popup/gamPopupProgramView.do", method=RequestMethod.POST)
	public String popupGrouphView(ModelMap model, @RequestParam("progrmFileNm") String progrmFileNm) throws Exception {
		
		model.addAttribute("searchKeyword", progrmFileNm);
		return "/ygpa/gam/cmmn/popup/GamPopupProgramList";
	}
	
	
	
    /**
     * 프로그램파일명을 조회한다.
     * @param searchVO
     * @return map
     * @throws Exception
     */
	@RequestMapping(value="/cmmn/popup/gamPopupProgramList.do", method=RequestMethod.POST)
	@ResponseBody Map<String, Object> selectProgrmListSearch(@ModelAttribute("searchVO") ComDefaultVO searchVO)throws Exception { 

		Map<String, Object> map = new HashMap<String, Object>();
    	
    	// 내역 조회
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
		
        int totCnt = progrmManageService.selectProgrmListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);

    	map.put("totalCount", totCnt);
    	map.put("searchOption", searchVO);
        map.put("resultList", progrmManageService.selectProgrmList(searchVO));
      	return map;
    }
}