package egovframework.rte.ygpa.gam.cmmn.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
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
	
	@RequestMapping(value="/cmmn/gamMenuMngCreat.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/cmmn/GamMenuMngCreat";
    }
	
	
	/**
	 * *메뉴생성목록을 조회한다.
	 * @param searchVO ComDefaultVO
	 * @return 출력페이지정보 "/ygpa/gam/cmmn/GamMenuMngCreat"
	 * @exception Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cmmn/gamMenuCreatManageSelect.do")
	@ResponseBody Map<String, Object> selectMenuCreatManagList(@ModelAttribute("searchVO") ComDefaultVO searchVO)throws Exception {
		
		Map map = new HashMap();
		
		String resultMsg = "";
		// 0. Spring Security 사용자권한 처리
		/*Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}*/
		
		// 내역 조회
		/** EgovPropertyService.sample */
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
		
		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", list_menumanage);
    	map.put("resultMsg", resultMsg);
    	map.put("searchOption", searchVO);

    	return map;
	}
}