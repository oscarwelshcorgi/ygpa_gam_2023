package egovframework.rte.ygpa.gam.cmmn.web;

import java.util.ArrayList;
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
import egovframework.com.sec.gmt.service.EgovGroupManageService;
import egovframework.com.sec.gmt.service.GroupManage;
import egovframework.com.sec.gmt.service.GroupManageVO;
import egovframework.com.sec.rmt.service.EgovRoleManageService;
import egovframework.com.sec.rmt.service.RoleManage;
import egovframework.com.sec.rmt.service.RoleManageVO;
import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import egovframework.com.sym.prm.service.EgovProgrmManageService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class GamRoleMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** EgovGroupManageService */
    @Resource(name = "egovRoleManageService")
    private EgovRoleManageService egovRoleManageService;


    /** Message ID Generation */
    @Resource(name="egovRoleIdGnrService")    
    private EgovIdGnrService egovRoleIdGnrService;

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
	@RequestMapping(value="/sec/rmt/gamRoleMng.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/cmmn/GamRoleMng";
    }
	
	/**
	 * 롤목록 리스트조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/sec/gmt/selectRoleList.do")
	@ResponseBody Map<String, Object> selectRoleListList(RoleManageVO searchVO)throws Exception {

		Map map = new HashMap();
		
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
		List groupList = egovRoleManageService.selectRoleList(searchVO);
        int totCnt = egovRoleManageService.selectRoleListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
		
		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", groupList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 롤리스트의 롤정보를 등록한다.
	 * @param groupManageVO
	 * @param bindingResult
	 * @param cmd
	 * @return Map
	 * @throws Exception
	 */
    @RequestMapping(value="/sec/gmt/gamRoleListInsert.do")
    @ResponseBody Map<String, Object> insertGroupList(@ModelAttribute("roleManage") RoleManageVO roleManage,
    		@ModelAttribute("roleManageVO") RoleManageVO roleManageVO, BindingResult bindingResult)
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
    	
	    beanValidator.validate(roleManage, bindingResult);
	    if (bindingResult.hasErrors()){
	        map.put("resultCode", 2);
			map.put("resultMsg", "입력 값에 오류가 있습니다.");
			map.put("resultObject", bindingResult.getAllErrors());
			return map;
		}
	    String roleTyp = roleManage.getRoleTyp();
    	if(roleTyp.equals("method")) 
    		roleTyp = "mtd";
    	else if(roleTyp.equals("pointcut"))
    		roleTyp = "pct";
    	else roleTyp = "web";
    	
    	roleManage.setRoleCode(roleTyp.concat("-").concat(egovRoleIdGnrService.getNextStringId()));
    	roleManageVO.setRoleCode(roleManage.getRoleCode());
    	
        resultMsg = egovMessageSource.getMessage("success.common.insert");
        map.put("roleManage", egovRoleManageService.insertRole(roleManage, roleManageVO));
        map.put("resultMsg", resultMsg);
      	return map;
    }
    
    
    /**
     * 롤목록을 수정 한다.
     * @param menuManageVO
     * @param bindingResult
     * @param model
     * @return Map
     * @throws Exception
     */
    @RequestMapping(value="/sec/gmt/gamRoleListUpdt.do")
    @ResponseBody Map<String, Object> gamRoleListUpdt(@ModelAttribute("roleManage") RoleManage roleManage,BindingResult bindingResult,ModelMap model) throws Exception {
    	
		Map<String, Object> map = new HashMap<String, Object>();
		String resultMsg = "";
    	// 0. Spring Security 사용자권한 처리
   	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

        beanValidator.validate(roleManage, bindingResult);
		if (bindingResult.hasErrors()){
	        map.put("resultCode", 2);
			map.put("resultMsg", "입력 값에 오류가 있습니다.");
			map.put("resultObject", bindingResult.getAllErrors());
			return map;
		}

		egovRoleManageService.updateRole(roleManage);
	    resultMsg = egovMessageSource.getMessage("success.common.update");

        map.put("resultCode", 0);
        map.put("resultMsg", resultMsg);
    	return map;
    }
    
    
    /**
     * 롤리스트의 메뉴정보를 삭제한다.
     * @param menuManageVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/sec/gmt/gamRoleListDelete.do")
    @ResponseBody Map<String, Object> deleteRoleList(String delList[], @ModelAttribute("roleManage") RoleManage roleManage)throws Exception {
        
    	Map<String, Object> map = new HashMap<String, Object>();

    	// 0. Spring Security 사용자권한 처리
   	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

//    	List list = (ArrayList)deleteList.get("delList");
    	for(int i=0; i<delList.length; i++) {
    		roleManage.setRoleCode(delList[i]);
    		egovRoleManageService.deleteRole(roleManage);
    	}

		map.put("resultCode", 0);
      	map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		return map;
    }

}