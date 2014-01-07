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

import egovframework.rte.ygpa.erp.code.service.ErpAssetCdService;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;

/**
 * @Class Name : ErpAssetCdController.java
 * @Description : ErpAssetCd Controller class
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
@SessionAttributes(types=ErpAssetCdVO.class)
public class ErpAssetCdController {

    @Resource(name = "erpAssetCdService")
    private ErpAssetCdService erpAssetCdService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * ERP_ASSET_CD 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 ErpAssetCdDefaultVO
	 * @return "/erpAssetCd/ErpAssetCdList"
	 * @exception Exception
	 */
    @RequestMapping(value="/erpAssetCd/ErpAssetCdList.do")
    public String selectErpAssetCdList(@ModelAttribute("searchVO") ErpAssetCdDefaultVO searchVO, 
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
		
        List erpAssetCdList = erpAssetCdService.selectErpAssetCdList(searchVO);
        model.addAttribute("resultList", erpAssetCdList);
        
        int totCnt = erpAssetCdService.selectErpAssetCdListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/erpAssetCd/ErpAssetCdList";
    } 
    
    @RequestMapping("/erpAssetCd/addErpAssetCdView.do")
    public String addErpAssetCdView(
            @ModelAttribute("searchVO") ErpAssetCdDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("erpAssetCdVO", new ErpAssetCdVO());
        return "/erpAssetCd/ErpAssetCdRegister";
    }
    
    @RequestMapping("/erpAssetCd/addErpAssetCd.do")
    public String addErpAssetCd(
            ErpAssetCdVO erpAssetCdVO,
            @ModelAttribute("searchVO") ErpAssetCdDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpAssetCdService.insertErpAssetCd(erpAssetCdVO);
        status.setComplete();
        return "forward:/erpAssetCd/ErpAssetCdList.do";
    }
    
    @RequestMapping("/erpAssetCd/updateErpAssetCdView.do")
    public String updateErpAssetCdView(
            @RequestParam("assetCls") java.lang.String assetCls ,
            @RequestParam("assetNo") java.math.BigDecimal assetNo ,
            @RequestParam("assetNoSeq") java.math.BigDecimal assetNoSeq ,
            @ModelAttribute("searchVO") ErpAssetCdDefaultVO searchVO, Model model)
            throws Exception {
        ErpAssetCdVO erpAssetCdVO = new ErpAssetCdVO();
        erpAssetCdVO.setAssetCls(assetCls);
        erpAssetCdVO.setAssetNo(assetNo);
        erpAssetCdVO.setAssetNoSeq(assetNoSeq);
        // 변수명은 CoC 에 따라 erpAssetCdVO
        model.addAttribute(selectErpAssetCd(erpAssetCdVO, searchVO));
        return "/erpAssetCd/ErpAssetCdRegister";
    }

    @RequestMapping("/erpAssetCd/selectErpAssetCd.do")
    public @ModelAttribute("erpAssetCdVO")
    ErpAssetCdVO selectErpAssetCd(
            ErpAssetCdVO erpAssetCdVO,
            @ModelAttribute("searchVO") ErpAssetCdDefaultVO searchVO) throws Exception {
        return erpAssetCdService.selectErpAssetCd(erpAssetCdVO);
    }

    @RequestMapping("/erpAssetCd/updateErpAssetCd.do")
    public String updateErpAssetCd(
            ErpAssetCdVO erpAssetCdVO,
            @ModelAttribute("searchVO") ErpAssetCdDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpAssetCdService.updateErpAssetCd(erpAssetCdVO);
        status.setComplete();
        return "forward:/erpAssetCd/ErpAssetCdList.do";
    }
    
    @RequestMapping("/erpAssetCd/deleteErpAssetCd.do")
    public String deleteErpAssetCd(
            ErpAssetCdVO erpAssetCdVO,
            @ModelAttribute("searchVO") ErpAssetCdDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpAssetCdService.deleteErpAssetCd(erpAssetCdVO);
        status.setComplete();
        return "forward:/erpAssetCd/ErpAssetCdList.do";
    }

}
