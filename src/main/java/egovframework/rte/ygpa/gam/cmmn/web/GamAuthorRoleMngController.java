package egovframework.rte.ygpa.gam.cmmn.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.eclipse.jetty.util.log.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.AuthorRoleManage;
import egovframework.com.sec.ram.service.AuthorRoleManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.com.sec.ram.service.EgovAuthorRoleManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class GamAuthorRoleMngController {

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "egovAuthorRoleManageService")
    private EgovAuthorRoleManageService egovAuthorRoleManageService;

	@Resource(name = "egovAuthorManageService")
    private EgovAuthorManageService egovAuthorManageService;


	/**
	 * 화면 호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/cmmn/gamAuthorRoleMng.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	AuthorManageVO authorManageVO = new AuthorManageVO();
    	authorManageVO.setFirstIndex(0);
    	authorManageVO.setRecordCountPerPage(9999);
    	List<AuthorManageVO> AuthorList = egovAuthorManageService.selectAuthorList(authorManageVO);
    	model.addAttribute("windowId", windowId);
    	model.addAttribute("authorlist", AuthorList);
    	return "/ygpa/gam/cmmn/GamAuthorRoleMng";
    }


	/**
	 * 권한별 할당된 롤 목록 조회
	 * @param authorRoleManageVO AuthorRoleManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/cmmn/gamAuthorRoleList.do")
    @ResponseBody Map<String, Object> selectAuthorRoleList(@ModelAttribute("authorRoleManageVO") AuthorRoleManageVO authorRoleManageVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(authorRoleManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(authorRoleManageVO.getPageUnit());
		paginationInfo.setPageSize(authorRoleManageVO.getPageSize());

		authorRoleManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		authorRoleManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		authorRoleManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
		List<AuthorRoleManageVO> authorRoleList = egovAuthorRoleManageService.selectAuthorRoleList(authorRoleManageVO);
        int totCnt = egovAuthorRoleManageService.selectAuthorRoleListTotCnt(authorRoleManageVO);

        paginationInfo.setTotalRecordCount(totCnt);
        authorRoleManageVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

        map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", authorRoleList);
    	map.put("searchOption", authorRoleManageVO);

    	return map;
	}


	/**
	 * 권한정보에 롤을 할당하여 데이터베이스에 등록
	 * @param authorCode
	 * @param roleCodes
	 * @param regYns
	 * @param authorRoleManage
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/cmmn/gamAuthorRoleInsert.do")
	@ResponseBody Map<String, Object> insertAuthorRole(@RequestParam("authorCode") String authorCode,@RequestParam("roleCodes") String roleCodes,
			                       @RequestParam("regYns") String regYns,@ModelAttribute("authorRoleManage") AuthorRoleManage authorRoleManage) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	try{
    	String [] strRoleCodes = null;
    	String [] strRegYns = null;

    	if(roleCodes.split(";") != null) {
    		strRoleCodes = roleCodes.split(";");
     	}

    	if(regYns.split(";") != null) {
    		strRegYns = regYns.split(";");
     	}

    	authorRoleManage.setRoleCode(authorCode);

    	for(int i=0; i<strRoleCodes.length;i++) {
    		authorRoleManage.setRoleCode(strRoleCodes[i]);
    		authorRoleManage.setRegYn(strRegYns[i]);

    		if(strRegYns[i].equals("Y") && strRegYns[i] != null){
    			egovAuthorRoleManageService.deleteAuthorRole(authorRoleManage);//2011.09.07
    			egovAuthorRoleManageService.insertAuthorRole(authorRoleManage);
    		}else {
    			egovAuthorRoleManageService.deleteAuthorRole(authorRoleManage);
    		}
    	}

    	map.put("resultCode", 0);
        map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
    	}catch(NullPointerException npe){
    		Log.info(npe.getMessage());
    	}catch(Exception e){
    		Log.info(e.getMessage());
    	}
		return map;
	}
}