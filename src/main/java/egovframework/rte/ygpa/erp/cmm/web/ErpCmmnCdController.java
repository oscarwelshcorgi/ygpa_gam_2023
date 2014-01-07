package egovframework.rte.ygpa.erp.cmm.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdService;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdVO;

/**
 * @Class Name : ErpCmmnCdController.java
 * @Description : ErpCmmnCd Controller class
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
@SessionAttributes(types=ErpCmmnCdVO.class)
public class ErpCmmnCdController {

    @Resource(name = "erpCmmnCdService")
    private ErpCmmnCdService erpCmmnCdService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * ERP_CMMN_CD 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 ErpCmmnCdDefaultVO
	 * @return "/erpCmmnCd/ErpCmmnCdList"
	 * @exception Exception
	 */
    @RequestMapping(value="/erpCmmnCd/ErpCmmnCdList.do")
    public String selectErpCmmnCdList(@ModelAttribute("searchVO") ErpCmmnCdDefaultVO searchVO, 
    		ModelMap model)
            throws Exception {
    	
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
		
        List erpCmmnCdList = erpCmmnCdService.selectErpCmmnCdList(searchVO);
        model.addAttribute("resultList", erpCmmnCdList);
        
        int totCnt = erpCmmnCdService.selectErpCmmnCdListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/erpCmmnCd/ErpCmmnCdList";
    } 
    
    @RequestMapping("/erpCmmnCd/addErpCmmnCdView.do")
    public String addErpCmmnCdView(
            @ModelAttribute("searchVO") ErpCmmnCdDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("erpCmmnCdVO", new ErpCmmnCdVO());
        return "/erpCmmnCd/ErpCmmnCdRegister";
    }
    
    @RequestMapping("/erpCmmnCd/addErpCmmnCd.do")
    public String addErpCmmnCd(
            ErpCmmnCdVO erpCmmnCdVO,
            @ModelAttribute("searchVO") ErpCmmnCdDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpCmmnCdService.insertErpCmmnCd(erpCmmnCdVO);
        status.setComplete();
        return "forward:/erpCmmnCd/ErpCmmnCdList.do";
    }
    
    @RequestMapping("/erpCmmnCd/updateErpCmmnCdView.do")
    public String updateErpCmmnCdView(
            @RequestParam("smCls") java.lang.String smCls ,
            @RequestParam("bigClsCd") java.lang.String bigClsCd ,
            @ModelAttribute("searchVO") ErpCmmnCdDefaultVO searchVO, Model model)
            throws Exception {
        ErpCmmnCdVO erpCmmnCdVO = new ErpCmmnCdVO();
        erpCmmnCdVO.setSmCls(smCls);
        erpCmmnCdVO.setBigClsCd(bigClsCd);
        // 변수명은 CoC 에 따라 erpCmmnCdVO
        model.addAttribute(selectErpCmmnCd(erpCmmnCdVO, searchVO));
        return "/erpCmmnCd/ErpCmmnCdRegister";
    }

    @RequestMapping("/erpCmmnCd/selectErpCmmnCd.do")
    public @ModelAttribute("erpCmmnCdVO")
    ErpCmmnCdVO selectErpCmmnCd(
            ErpCmmnCdVO erpCmmnCdVO,
            @ModelAttribute("searchVO") ErpCmmnCdDefaultVO searchVO) throws Exception {
        return erpCmmnCdService.selectErpCmmnCd(erpCmmnCdVO);
    }

    @RequestMapping("/erpCmmnCd/updateErpCmmnCd.do")
    public String updateErpCmmnCd(
            ErpCmmnCdVO erpCmmnCdVO,
            @ModelAttribute("searchVO") ErpCmmnCdDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpCmmnCdService.updateErpCmmnCd(erpCmmnCdVO);
        status.setComplete();
        return "forward:/erpCmmnCd/ErpCmmnCdList.do";
    }
    
    @RequestMapping("/erpCmmnCd/deleteErpCmmnCd.do")
    public String deleteErpCmmnCd(
            ErpCmmnCdVO erpCmmnCdVO,
            @ModelAttribute("searchVO") ErpCmmnCdDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpCmmnCdService.deleteErpCmmnCd(erpCmmnCdVO);
        status.setComplete();
        return "forward:/erpCmmnCd/ErpCmmnCdList.do";
    }

}
