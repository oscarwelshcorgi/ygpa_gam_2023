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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.sec.ram.service.AuthorRoleManageVO;
import egovframework.com.sec.ram.service.EgovAuthorRoleManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class GamAuthorRoleMngController {

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
	@Resource(name = "egovAuthorRoleManageService")
    private EgovAuthorRoleManageService egovAuthorRoleManageService;
	
	@RequestMapping(value="/cmmn/gamAuthorRoleMng.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/cmmn/GamAuthorRoleMng";
    }
	
	
	/**
	 * 권한별 할당된 롤 목록 조회
	 * @param authorRoleManageVO AuthorRoleManageVO
	 * @return String
	 * @exception Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/cmmn/gamAuthorRoleList.do")
    @ResponseBody Map selectAuthorRoleList(@ModelAttribute("authorRoleManageVO") AuthorRoleManageVO authorRoleManageVO) throws Exception {

		Map map = new HashMap();
		
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(authorRoleManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(authorRoleManageVO.getPageUnit());
		paginationInfo.setPageSize(authorRoleManageVO.getPageSize());
		
		authorRoleManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		authorRoleManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		authorRoleManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		/** List Data */
		List authorRoleList = egovAuthorRoleManageService.selectAuthorRoleList(authorRoleManageVO); 
        int totCnt = egovAuthorRoleManageService.selectAuthorRoleListTotCnt(authorRoleManageVO);

        paginationInfo.setTotalRecordCount(totCnt);

        map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", authorRoleList);
    	map.put("searchOption", authorRoleManageVO);

    	return map;
	}
	
	
	/**
	 * 권한정보에 롤을 할당하여 데이터베이스에 등록
	 * @param authorCode String
	 * @param roleCodes String
	 * @param regYns String
	 * @param authorRoleManage AuthorRoleManage
	 * @return String
	 * @exception Exception
	 */
	/*
	@RequestMapping(value="/sec/ram/EgovAuthorRoleInsert.do")
	public String insertAuthorRole(@RequestParam("authorCode") String authorCode,@RequestParam("roleCodes") String roleCodes,
			                       @RequestParam("regYns") String regYns,@ModelAttribute("authorRoleManage") AuthorRoleManage authorRoleManage,
			                         SessionStatus status) throws Exception {

    	String [] strRoleCodes = roleCodes.split(";");
    	String [] strRegYns = regYns.split(";");
    	
    	authorRoleManage.setRoleCode(authorCode);
    	
    	for(int i=0; i<strRoleCodes.length;i++) {
    		authorRoleManage.setRoleCode(strRoleCodes[i]);
    		authorRoleManage.setRegYn(strRegYns[i]);
    		if(strRegYns[i].equals("Y")){
    			egovAuthorRoleManageService.deleteAuthorRole(authorRoleManage);//2011.09.07
    			egovAuthorRoleManageService.insertAuthorRole(authorRoleManage);
    		}else {
    			egovAuthorRoleManageService.deleteAuthorRole(authorRoleManage);
    		}
    	}

        status.setComplete();
        model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));		
		return "forward:/sec/ram/EgovAuthorRoleList.do";
	}*/
}