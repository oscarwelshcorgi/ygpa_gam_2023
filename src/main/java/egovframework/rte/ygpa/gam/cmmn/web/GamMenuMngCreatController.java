package egovframework.rte.ygpa.gam.cmmn.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.mnu.mcm.service.EgovMenuCreateManageService;
import egovframework.com.sym.mnu.mcm.service.MenuCreatVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class GamMenuMngCreatController {

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMenuManageService */
	@Resource(name = "meunCreateManageService")
	private EgovMenuCreateManageService menuCreateManageService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/**
	 * 화면 호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/cmmn/gamMenuMngCreat.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/cmmn/GamMenuMngCreat";
    }


	/**
	 * 메뉴생성목록을 조회한다.
	 * @param searchVO
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/cmmn/gamMenuCreatManageSelect.do")
	@ResponseBody Map<String, Object> selectMenuCreatManagList(@ModelAttribute("searchVO") ComDefaultVO searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		String resultMsg = "";
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

		if (searchVO.getSearchKeyword() != null && !searchVO.getSearchKeyword().equals("")) {
			int IDcnt = menuCreateManageService.selectUsrByPk(searchVO);
			if (IDcnt == 0) {
				resultMsg = egovMessageSource.getMessage("info.nodata.msg");
			} else {
				/* AuthorCode 검색 */
				MenuCreatVO vo = new MenuCreatVO();
				vo = menuCreateManageService.selectAuthorByUsr(searchVO);
				searchVO.setSearchKeyword(vo.getAuthorCode());
			}
		}

		/** List Data */
		List list_menumanage = menuCreateManageService.selectMenuCreatManagList(searchVO);
		int totCnt = menuCreateManageService.selectMenuCreatManagTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", list_menumanage);
    	map.put("resultMsg", resultMsg);
    	map.put("searchOption", searchVO);

    	return map;
	}

    /**
     * 메뉴생성 팝업을 호출합니다.
     * @param searchVO
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/cmmn/popup/showMenuCreat.do", method=RequestMethod.POST)
    String showMenuCreat(String authorCode, ModelMap model) throws Exception {

    	model.addAttribute("authorCode", authorCode);

    	return "/ygpa/gam/cmmn/popup/GamPopupMenuCreat";
    }

    
	/**
	 * 메뉴생성 세부화면을 조회한다.
	 * @param menuCreatVO
	 * @param model
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmmn/gamMenuCreatSelect.do")
	@ResponseBody Map<String, Object> selectMenuCreatList(MenuCreatVO menuCreatVO) throws Exception {
    	
		Map<String, Object> map = new HashMap<String, Object>();
    	
		String resultMsg    = "";

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		resultMsg = egovMessageSource.getMessage("fail.common.login");
        	map.put("resultCode", 1);			// return error
        	map.put("resultMsg", resultMsg);	// return error message
        	return map;
    	}
		
    	List<?> list_menulist = menuCreateManageService.selectMenuCreatList(menuCreatVO);
		resultMsg = egovMessageSource.getMessage("success.common.select");
    	
		map.put("listMenulist", list_menulist);
    	map.put("resultMsg", resultMsg);
    	map.put("resultCode", 0);
    	return map;
	}
	
	
	/**
	 * 메뉴생성처리 및 메뉴생성내역을 등록한다.
	 * @param checkedAuthorForInsert String
	 * @param checkedMenuNoForInsert String
	 * @return 출력페이지정보 등록처리시 "forward:/sym/mnu/mcm/EgovMenuCreatSelect.do"
	 * @exception Exception
	 */
	@RequestMapping("/cmmn/gamMenuCreatInsert.do")
	@ResponseBody Map<String, Object> insertMenuCreatList(@RequestParam("checkedAuthorForInsert") String checkedAuthorForInsert,@RequestParam("checkedMenuNoForInsert") String checkedMenuNoForInsert,
			@ModelAttribute("menuCreatVO") MenuCreatVO menuCreatVO, ModelMap model) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String resultMsg = "";

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		resultMsg = egovMessageSource.getMessage("fail.common.login");
        	map.put("resultCode", 1);			// return error
        	map.put("resultMsg", resultMsg);	// return error message
        	return map;
    	}
    	String[] insertMenuNo = null; 
		if(checkedMenuNoForInsert != null) insertMenuNo = checkedMenuNoForInsert.split(",");
		
		if(insertMenuNo == null || (insertMenuNo.length == 0)){
			resultMsg = egovMessageSource.getMessage("fail.common.insert");
		}else{
			menuCreateManageService.insertMenuCreatList(checkedAuthorForInsert, checkedMenuNoForInsert);
			resultMsg = egovMessageSource.getMessage("success.common.insert");
			map.put("resultCode", 0);			// return ok
        	map.put("resultMsg", resultMsg);	// return ok message
		}
		return map;
	}
}