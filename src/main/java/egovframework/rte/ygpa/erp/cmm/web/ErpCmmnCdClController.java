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

import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdClService;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdClDefaultVO;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdClVO;

/**
 * @Class Name : ErpCmmnCdClController.java
 * @Description : ErpCmmnCdCl Controller class
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
@SessionAttributes(types=ErpCmmnCdClVO.class)
public class ErpCmmnCdClController {

    @Resource(name = "erpCmmnCdClService")
    private ErpCmmnCdClService erpCmmnCdClService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * ERP_CMMN_CD_CL 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 ErpCmmnCdClDefaultVO
	 * @return "/erpCmmnCdCl/ErpCmmnCdClList"
	 * @exception Exception
	 */
    @RequestMapping(value="/erpCmmnCdCl/ErpCmmnCdClList.do")
    public String selectErpCmmnCdClList(@ModelAttribute("searchVO") ErpCmmnCdClDefaultVO searchVO, 
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
		
        List erpCmmnCdClList = erpCmmnCdClService.selectErpCmmnCdClList(searchVO);
        model.addAttribute("resultList", erpCmmnCdClList);
        
        int totCnt = erpCmmnCdClService.selectErpCmmnCdClListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/erpCmmnCdCl/ErpCmmnCdClList";
    } 
    
    @RequestMapping("/erpCmmnCdCl/addErpCmmnCdClView.do")
    public String addErpCmmnCdClView(
            @ModelAttribute("searchVO") ErpCmmnCdClDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("erpCmmnCdClVO", new ErpCmmnCdClVO());
        return "/erpCmmnCdCl/ErpCmmnCdClRegister";
    }
    
    @RequestMapping("/erpCmmnCdCl/addErpCmmnCdCl.do")
    public String addErpCmmnCdCl(
            ErpCmmnCdClVO erpCmmnCdClVO,
            @ModelAttribute("searchVO") ErpCmmnCdClDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpCmmnCdClService.insertErpCmmnCdCl(erpCmmnCdClVO);
        status.setComplete();
        return "forward:/erpCmmnCdCl/ErpCmmnCdClList.do";
    }
    
    @RequestMapping("/erpCmmnCdCl/updateErpCmmnCdClView.do")
    public String updateErpCmmnCdClView(
            @RequestParam("bigClsCd") java.lang.String bigClsCd ,
            @ModelAttribute("searchVO") ErpCmmnCdClDefaultVO searchVO, Model model)
            throws Exception {
        ErpCmmnCdClVO erpCmmnCdClVO = new ErpCmmnCdClVO();
        erpCmmnCdClVO.setBigClsCd(bigClsCd);
        // 변수명은 CoC 에 따라 erpCmmnCdClVO
        model.addAttribute(selectErpCmmnCdCl(erpCmmnCdClVO, searchVO));
        return "/erpCmmnCdCl/ErpCmmnCdClRegister";
    }

    @RequestMapping("/erpCmmnCdCl/selectErpCmmnCdCl.do")
    public @ModelAttribute("erpCmmnCdClVO")
    ErpCmmnCdClVO selectErpCmmnCdCl(
            ErpCmmnCdClVO erpCmmnCdClVO,
            @ModelAttribute("searchVO") ErpCmmnCdClDefaultVO searchVO) throws Exception {
        return erpCmmnCdClService.selectErpCmmnCdCl(erpCmmnCdClVO);
    }

    @RequestMapping("/erpCmmnCdCl/updateErpCmmnCdCl.do")
    public String updateErpCmmnCdCl(
            ErpCmmnCdClVO erpCmmnCdClVO,
            @ModelAttribute("searchVO") ErpCmmnCdClDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpCmmnCdClService.updateErpCmmnCdCl(erpCmmnCdClVO);
        status.setComplete();
        return "forward:/erpCmmnCdCl/ErpCmmnCdClList.do";
    }
    
    @RequestMapping("/erpCmmnCdCl/deleteErpCmmnCdCl.do")
    public String deleteErpCmmnCdCl(
            ErpCmmnCdClVO erpCmmnCdClVO,
            @ModelAttribute("searchVO") ErpCmmnCdClDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpCmmnCdClService.deleteErpCmmnCdCl(erpCmmnCdClVO);
        status.setComplete();
        return "forward:/erpCmmnCdCl/ErpCmmnCdClList.do";
    }

}
