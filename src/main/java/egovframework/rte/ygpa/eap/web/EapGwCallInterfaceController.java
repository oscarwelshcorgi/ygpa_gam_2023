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

import egovframework.rte.cmmn.AjaxXmlView;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.eap.service.EapGwCallInterfaceService;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
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

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

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
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/eap/selectEapGwCallInterfaceList.do", method=RequestMethod.POST)
    @ResponseBody public Map<String, Object> selectEapGwCallInterfaceList(@RequestParam Map<String, Object> searchVO) throws Exception {
    	Map<String, Object>map = new HashMap<String, Object>();

//    	PaginationInfo paginationInfo = new PaginationInfo();
//		paginationInfo.setCurrentPageNo((Integer)searchVO.get("pageIndex"));
//		paginationInfo.setRecordCountPerPage((Integer)searchVO.get("pageUnit"));
//		paginationInfo.setPageSize((Integer)searchVO.get("PageSize"));


//		searchVO.put("firstIndex", paginationInfo.getFirstRecordIndex());
//		searchVO.put("lastIndex", paginationInfo.getLastRecordIndex());
//		searchVO.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());

        List eapGwCallInterfaceList = eapGwCallInterfaceService.selectEapGwCallInterfaceList(searchVO);
        map.put("resultList", eapGwCallInterfaceList);

        int totCnt = eapGwCallInterfaceService.selectEapGwCallInterfaceListTotCnt(searchVO);
//		paginationInfo.setTotalRecordCount(totCnt);
//        map.put("paginationInfo", paginationInfo);

        return map;
    }

    @RequestMapping("/eap/updateEapGwCallInterface.do")
    @ResponseBody public Map updateEapGwCallInterface(
            @ModelAttribute("gwCallInterfaceVo") Map gwCall,
            BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {
    	Map map = new HashMap<String, Object>();

    	eapGwCallInterfaceService.updateEapGwCallInterface(gwCall);

        status.setComplete();
        map.put("resultCode", "0");
        map.put("resultMsg", "Done.");
        return map;
    }

    @RequestMapping("/code/mngt/deleteEapGwCallInterface.do")
    public Map deleteEapGwCallInterface(
            @ModelAttribute("gwCall") Map gwCall, SessionStatus status)
            throws Exception {
    	Map map = new HashMap<String, Object>();

    	eapGwCallInterfaceService.deleteEapGwCallInterface(gwCall);
        status.setComplete();
        map.put("resultCode", "0");
        map.put("resultMsg", "Done.");
        return map;
    }

    @RequestMapping("/eapGwCallInterface/selectEapGwCallInterface.do")
    public @ModelAttribute("eapGwCallInterfaceVO")
    @ResponseBody Map selectEapGwCallInterface(Map<String, Object> gwCall) throws Exception {
        return eapGwCallInterfaceService.selectEapGwCallInterface(gwCall);
    }

}
