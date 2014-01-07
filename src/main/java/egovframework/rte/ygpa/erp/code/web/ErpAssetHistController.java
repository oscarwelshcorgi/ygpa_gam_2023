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

import egovframework.rte.ygpa.erp.code.service.ErpAssetHistService;
import egovframework.rte.ygpa.erp.code.service.ErpAssetHistDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetHistVO;

/**
 * @Class Name : ErpAssetHistController.java
 * @Description : ErpAssetHist Controller class
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
@SessionAttributes(types=ErpAssetHistVO.class)
public class ErpAssetHistController {

    @Resource(name = "erpAssetHistService")
    private ErpAssetHistService erpAssetHistService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * ERP_ASSET_HIST 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 ErpAssetHistDefaultVO
	 * @return "/erpAssetHist/ErpAssetHistList"
	 * @exception Exception
	 */
    @RequestMapping(value="/erpAssetHist/ErpAssetHistList.do")
    public String selectErpAssetHistList(@ModelAttribute("searchVO") ErpAssetHistDefaultVO searchVO, 
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
		
        List erpAssetHistList = erpAssetHistService.selectErpAssetHistList(searchVO);
        model.addAttribute("resultList", erpAssetHistList);
        
        int totCnt = erpAssetHistService.selectErpAssetHistListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/erpAssetHist/ErpAssetHistList";
    } 
    
    @RequestMapping("/erpAssetHist/addErpAssetHistView.do")
    public String addErpAssetHistView(
            @ModelAttribute("searchVO") ErpAssetHistDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("erpAssetHistVO", new ErpAssetHistVO());
        return "/erpAssetHist/ErpAssetHistRegister";
    }
    
    @RequestMapping("/erpAssetHist/addErpAssetHist.do")
    public String addErpAssetHist(
            ErpAssetHistVO erpAssetHistVO,
            @ModelAttribute("searchVO") ErpAssetHistDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpAssetHistService.insertErpAssetHist(erpAssetHistVO);
        status.setComplete();
        return "forward:/erpAssetHist/ErpAssetHistList.do";
    }
    
    @RequestMapping("/erpAssetHist/updateErpAssetHistView.do")
    public String updateErpAssetHistView(
            @RequestParam("changeDate") java.lang.String changeDate ,
            @RequestParam("indexNo") java.lang.String indexNo ,
            @RequestParam("assetCls") java.lang.String assetCls ,
            @RequestParam("assetNo") java.math.BigDecimal assetNo ,
            @RequestParam("assetNoSeq") java.math.BigDecimal assetNoSeq ,
            @ModelAttribute("searchVO") ErpAssetHistDefaultVO searchVO, Model model)
            throws Exception {
        ErpAssetHistVO erpAssetHistVO = new ErpAssetHistVO();
        erpAssetHistVO.setChangeDate(changeDate);
        erpAssetHistVO.setIndexNo(indexNo);
        erpAssetHistVO.setAssetCls(assetCls);
        erpAssetHistVO.setAssetNo(assetNo);
        erpAssetHistVO.setAssetNoSeq(assetNoSeq);
        // 변수명은 CoC 에 따라 erpAssetHistVO
        model.addAttribute(selectErpAssetHist(erpAssetHistVO, searchVO));
        return "/erpAssetHist/ErpAssetHistRegister";
    }

    @RequestMapping("/erpAssetHist/selectErpAssetHist.do")
    public @ModelAttribute("erpAssetHistVO")
    ErpAssetHistVO selectErpAssetHist(
            ErpAssetHistVO erpAssetHistVO,
            @ModelAttribute("searchVO") ErpAssetHistDefaultVO searchVO) throws Exception {
        return erpAssetHistService.selectErpAssetHist(erpAssetHistVO);
    }

    @RequestMapping("/erpAssetHist/updateErpAssetHist.do")
    public String updateErpAssetHist(
            ErpAssetHistVO erpAssetHistVO,
            @ModelAttribute("searchVO") ErpAssetHistDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpAssetHistService.updateErpAssetHist(erpAssetHistVO);
        status.setComplete();
        return "forward:/erpAssetHist/ErpAssetHistList.do";
    }
    
    @RequestMapping("/erpAssetHist/deleteErpAssetHist.do")
    public String deleteErpAssetHist(
            ErpAssetHistVO erpAssetHistVO,
            @ModelAttribute("searchVO") ErpAssetHistDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpAssetHistService.deleteErpAssetHist(erpAssetHistVO);
        status.setComplete();
        return "forward:/erpAssetHist/ErpAssetHistList.do";
    }

}
