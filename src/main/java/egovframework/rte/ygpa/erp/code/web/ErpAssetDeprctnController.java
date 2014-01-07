package egovframework.rte.ygpa.erp.code.web;

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

import egovframework.rte.ygpa.erp.code.service.ErpAssetDeprctnService;
import egovframework.rte.ygpa.erp.code.service.ErpAssetDeprctnDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetDeprctnVO;

/**
 * @Class Name : ErpAssetDeprctnController.java
 * @Description : ErpAssetDeprctn Controller class
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
@SessionAttributes(types=ErpAssetDeprctnVO.class)
public class ErpAssetDeprctnController {

    @Resource(name = "erpAssetDeprctnService")
    private ErpAssetDeprctnService erpAssetDeprctnService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * ERP_ASSET_DEPRCTN 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 ErpAssetDeprctnDefaultVO
	 * @return "/erpAssetDeprctn/ErpAssetDeprctnList"
	 * @exception Exception
	 */
    @RequestMapping(value="/erpAssetDeprctn/ErpAssetDeprctnList.do")
    public String selectErpAssetDeprctnList(@ModelAttribute("searchVO") ErpAssetDeprctnDefaultVO searchVO, 
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
		
        List erpAssetDeprctnList = erpAssetDeprctnService.selectErpAssetDeprctnList(searchVO);
        model.addAttribute("resultList", erpAssetDeprctnList);
        
        int totCnt = erpAssetDeprctnService.selectErpAssetDeprctnListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/erpAssetDeprctn/ErpAssetDeprctnList";
    } 
    
    @RequestMapping("/erpAssetDeprctn/addErpAssetDeprctnView.do")
    public String addErpAssetDeprctnView(
            @ModelAttribute("searchVO") ErpAssetDeprctnDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("erpAssetDeprctnVO", new ErpAssetDeprctnVO());
        return "/erpAssetDeprctn/ErpAssetDeprctnRegister";
    }
    
    @RequestMapping("/erpAssetDeprctn/addErpAssetDeprctn.do")
    public String addErpAssetDeprctn(
            ErpAssetDeprctnVO erpAssetDeprctnVO,
            @ModelAttribute("searchVO") ErpAssetDeprctnDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpAssetDeprctnService.insertErpAssetDeprctn(erpAssetDeprctnVO);
        status.setComplete();
        return "forward:/erpAssetDeprctn/ErpAssetDeprctnList.do";
    }
    
    @RequestMapping("/erpAssetDeprctn/updateErpAssetDeprctnView.do")
    public String updateErpAssetDeprctnView(
            @RequestParam("deprctnYear") java.lang.String deprctnYear ,
            @RequestParam("assetCls") java.lang.String assetCls ,
            @RequestParam("assetNo") java.math.BigDecimal assetNo ,
            @RequestParam("assetNoSeq") java.math.BigDecimal assetNoSeq ,
            @ModelAttribute("searchVO") ErpAssetDeprctnDefaultVO searchVO, Model model)
            throws Exception {
        ErpAssetDeprctnVO erpAssetDeprctnVO = new ErpAssetDeprctnVO();
        erpAssetDeprctnVO.setDeprctnYear(deprctnYear);
        erpAssetDeprctnVO.setAssetCls(assetCls);
        erpAssetDeprctnVO.setAssetNo(assetNo);
        erpAssetDeprctnVO.setAssetNoSeq(assetNoSeq);
        // 변수명은 CoC 에 따라 erpAssetDeprctnVO
        model.addAttribute(selectErpAssetDeprctn(erpAssetDeprctnVO, searchVO));
        return "/erpAssetDeprctn/ErpAssetDeprctnRegister";
    }

    @RequestMapping("/erpAssetDeprctn/selectErpAssetDeprctn.do")
    public @ModelAttribute("erpAssetDeprctnVO")
    ErpAssetDeprctnVO selectErpAssetDeprctn(
            ErpAssetDeprctnVO erpAssetDeprctnVO,
            @ModelAttribute("searchVO") ErpAssetDeprctnDefaultVO searchVO) throws Exception {
        return erpAssetDeprctnService.selectErpAssetDeprctn(erpAssetDeprctnVO);
    }

    @RequestMapping("/erpAssetDeprctn/updateErpAssetDeprctn.do")
    public String updateErpAssetDeprctn(
            ErpAssetDeprctnVO erpAssetDeprctnVO,
            @ModelAttribute("searchVO") ErpAssetDeprctnDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpAssetDeprctnService.updateErpAssetDeprctn(erpAssetDeprctnVO);
        status.setComplete();
        return "forward:/erpAssetDeprctn/ErpAssetDeprctnList.do";
    }
    
    @RequestMapping("/erpAssetDeprctn/deleteErpAssetDeprctn.do")
    public String deleteErpAssetDeprctn(
            ErpAssetDeprctnVO erpAssetDeprctnVO,
            @ModelAttribute("searchVO") ErpAssetDeprctnDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpAssetDeprctnService.deleteErpAssetDeprctn(erpAssetDeprctnVO);
        status.setComplete();
        return "forward:/erpAssetDeprctn/ErpAssetDeprctnList.do";
    }

}
