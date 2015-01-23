package egovframework.rte.ygpa.eap.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.eap.service.EapGwCallInterfaceService;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamEApprovalRequestService;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeDefaultVO;

/**
 * @Class Name : EapGwCallInterfaceController.java
 * @Description : EapGwCallInterface Controller class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Controller
public class EapGwCallInterfaceController {

    @Resource(name = "eapGwCallInterfaceService")
    private EapGwCallInterfaceService eapGwCallInterfaceService;

    @Resource(name = "gamEApprovalRequestService")
    private GamEApprovalRequestService gamEApprovalRequestService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /**
     * 전자결재 목록 화면을 호출한다. (전자결재 테스트용)
     * @param searchVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/eap/showEapGwCallInterfaceView.do")
    public String addEapGwCallInterfaceView(
    		@RequestParam("window_id") String windowId, Model model)
            throws Exception {
    	model.addAttribute("windowId", windowId);
        return "ygpa/eap/EapGwCallInterfaceMngt";
    }

    /**
	 * 전자결재 신청 목록을 조회한다. (테스트용)
	 * @param searchVO - 조회할 정보가 담긴 EapGwCallInterfaceDefaultVO
	 * @return "/eapGwCallInterface/EapGwCallInterfaceList"
	 * @exception Exception
	 */
    @SuppressWarnings({ "rawtypes" })
    @RequestMapping(value="/eap/selectEapGwCallInterfaceList.do", method=RequestMethod.POST)
    @ResponseBody public Map<String, Object> selectEapGwCallInterfaceList(@RequestParam Map<String, Object> searchVO) throws Exception {
    	Map<String, Object>map = new HashMap<String, Object>();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}


    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(Integer.parseInt((String)searchVO.get("pageIndex")));
		paginationInfo.setRecordCountPerPage(Integer.parseInt((String)searchVO.get("pageUnit")));
		paginationInfo.setPageSize(Integer.parseInt((String)searchVO.get("recordCountPerPage")));


		searchVO.put("firstIndex", paginationInfo.getFirstRecordIndex());
		searchVO.put("lastIndex", paginationInfo.getLastRecordIndex());
		searchVO.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());

        List eapGwCallInterfaceList = eapGwCallInterfaceService.selectEapGwCallInterfaceList(searchVO);

        int totCnt = eapGwCallInterfaceService.selectEapGwCallInterfaceListTotCnt(searchVO);

        map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", eapGwCallInterfaceList);
    	map.put("searchOption", searchVO);

        return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/eap/updateGwCallInterface.do")
    @ResponseBody public Map updateEapGwCallInterface(
            @RequestParam Map gwCall)
            throws Exception {
    	Map map = new HashMap<String, Object>();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    	gwCall.put("updUsr", loginVo.getId());
    	gwCall.put("regUsr", loginVo.getId());

    	eapGwCallInterfaceService.eApprovalTest(gwCall);

        map.put("resultCode", "0");
        map.put("resultMsg", "Done.");
        return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping("/eap/deleteEapGwCallInterface.do")
    public Map deleteEapGwCallInterface(
            @ModelAttribute("gwCall") Map gwCall, SessionStatus status)
            throws Exception {
    	Map map = new HashMap<String, Object>();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	eapGwCallInterfaceService.deleteEapGwCallInterface(gwCall);
        status.setComplete();
        map.put("resultCode", "0");
        map.put("resultMsg", "Done.");
        return map;
    }

    @SuppressWarnings({ "rawtypes" })
    @RequestMapping("/eapGwCallInterface/selectEapGwCallInterface.do")
    public @ModelAttribute("eapGwCallInterfaceVO")
    @ResponseBody Map selectEapGwCallInterface(Map<String, Object> gwCall) throws Exception {
    	Map map = new HashMap<String, Object>();
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
        return eapGwCallInterfaceService.selectEapGwCallInterface(gwCall);
    }



}
