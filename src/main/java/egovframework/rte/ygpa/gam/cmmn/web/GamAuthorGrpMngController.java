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

import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.com.sec.rgm.service.AuthorGroupVO;
import egovframework.com.sec.rgm.service.EgovAuthorGroupService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class GamAuthorGrpMngController {

	@Resource(name = "egovAuthorGroupService")
    private EgovAuthorGroupService egovAuthorGroupService;
	
	@Resource(name = "egovAuthorManageService")
    private EgovAuthorManageService egovAuthorManageService;
	
	@RequestMapping(value="/cmmn/gamAuthorGrpMng.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/cmmn/GamAuthorGrpMng";
    }
	
	
	/**
	 * 그룹별 할당된 권한 목록 조회
	 * @param authorGroupVO AuthorGroupVO
	 * @param authorManageVO AuthorManageVO
	 * @return String
	 * @exception Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/cmmn/gamAuthorGroupList.do")
    @ResponseBody Map selectAuthorGroupList(@ModelAttribute("authorGroupVO") AuthorGroupVO authorGroupVO,
			                            @ModelAttribute("authorManageVO") AuthorManageVO authorManageVO) throws Exception {

		Map map = new HashMap();
    	
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(authorGroupVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(authorGroupVO.getPageUnit());
		paginationInfo.setPageSize(authorGroupVO.getPageSize());
		
		authorGroupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		authorGroupVO.setLastIndex(paginationInfo.getLastRecordIndex());
		authorGroupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
		List authorGroupList = egovAuthorGroupService.selectAuthorGroupList(authorGroupVO);
		authorGroupList.add(egovAuthorManageService.selectAuthorAllList(authorManageVO));		// Need Check
        int totCnt = egovAuthorGroupService.selectAuthorGroupListTotCnt(authorGroupVO);
    	
        map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", authorGroupList);
    	map.put("searchOption", authorManageVO);

    	return map;
	}
}
