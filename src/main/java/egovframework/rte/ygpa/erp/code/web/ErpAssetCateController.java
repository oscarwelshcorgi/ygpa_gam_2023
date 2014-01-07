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

import egovframework.rte.ygpa.erp.code.service.ErpAssetCateService;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCateDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCateVO;

/**
 * @Class Name : ErpAssetCateController.java
 * @Description : ErpAssetCate Controller class
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
@SessionAttributes(types=ErpAssetCateVO.class)
public class ErpAssetCateController {

    @Resource(name = "erpAssetCateService")
    private ErpAssetCateService erpAssetCateService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * ERP_ASSET_CATE 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 ErpAssetCateDefaultVO
	 * @return "/erpAssetCate/ErpAssetCateList"
	 * @exception Exception
	 */
    @RequestMapping(value="/erpAssetCate/ErpAssetCateList.do")
    public String selectErpAssetCateList(@ModelAttribute("searchVO") ErpAssetCateDefaultVO searchVO, 
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
		
        List erpAssetCateList = erpAssetCateService.selectErpAssetCateList(searchVO);
        model.addAttribute("resultList", erpAssetCateList);
        
        int totCnt = erpAssetCateService.selectErpAssetCateListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/erpAssetCate/ErpAssetCateList";
    } 
    
    @RequestMapping("/erpAssetCate/addErpAssetCateView.do")
    public String addErpAssetCateView(
            @ModelAttribute("searchVO") ErpAssetCateDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("erpAssetCateVO", new ErpAssetCateVO());
        return "/erpAssetCate/ErpAssetCateRegister";
    }
    
    @RequestMapping("/erpAssetCate/addErpAssetCate.do")
    public String addErpAssetCate(
            ErpAssetCateVO erpAssetCateVO,
            @ModelAttribute("searchVO") ErpAssetCateDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpAssetCateService.insertErpAssetCate(erpAssetCateVO);
        status.setComplete();
        return "forward:/erpAssetCate/ErpAssetCateList.do";
    }
    
    @RequestMapping("/erpAssetCate/updateErpAssetCateView.do")
    public String updateErpAssetCateView(
            @RequestParam("assetCateCls") java.lang.String assetCateCls ,
            @RequestParam("assetCateCd") java.lang.String assetCateCd ,
            @ModelAttribute("searchVO") ErpAssetCateDefaultVO searchVO, Model model)
            throws Exception {
        ErpAssetCateVO erpAssetCateVO = new ErpAssetCateVO();
        erpAssetCateVO.setAssetCateCls(assetCateCls);
        erpAssetCateVO.setAssetCateCd(assetCateCd);
        // 변수명은 CoC 에 따라 erpAssetCateVO
        model.addAttribute(selectErpAssetCate(erpAssetCateVO, searchVO));
        return "/erpAssetCate/ErpAssetCateRegister";
    }

    @RequestMapping("/erpAssetCate/selectErpAssetCate.do")
    public @ModelAttribute("erpAssetCateVO")
    ErpAssetCateVO selectErpAssetCate(
            ErpAssetCateVO erpAssetCateVO,
            @ModelAttribute("searchVO") ErpAssetCateDefaultVO searchVO) throws Exception {
        return erpAssetCateService.selectErpAssetCate(erpAssetCateVO);
    }

    @RequestMapping("/erpAssetCate/updateErpAssetCate.do")
    public String updateErpAssetCate(
            ErpAssetCateVO erpAssetCateVO,
            @ModelAttribute("searchVO") ErpAssetCateDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpAssetCateService.updateErpAssetCate(erpAssetCateVO);
        status.setComplete();
        return "forward:/erpAssetCate/ErpAssetCateList.do";
    }
    
    @RequestMapping("/erpAssetCate/deleteErpAssetCate.do")
    public String deleteErpAssetCate(
            ErpAssetCateVO erpAssetCateVO,
            @ModelAttribute("searchVO") ErpAssetCateDefaultVO searchVO, SessionStatus status)
            throws Exception {
        erpAssetCateService.deleteErpAssetCate(erpAssetCateVO);
        status.setComplete();
        return "forward:/erpAssetCate/ErpAssetCateList.do";
    }

}
