package egovframework.rte.ygpa.gam.oper.gnrl.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdService;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeMngtVO;

/**
 * @Class Name : GamPrtFcltyRentFeeMngtController.java
 * @Description : 항만시설사용료관리 (항만시설/일반부두/항만시설사용료관리)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamPrtFcltyRentFeeMngtController {

    @Resource(name = "erpCmmnCdService")
    private ErpCmmnCdService erpCmmnCdService;
    
    /*
    @Resource(name = "erpCmmnCdClService")
    private ErpCmmnCdClService erpCmmnCdClService;
    
    @Resource(name = "erpAssetCdService")
    private ErpAssetCdService erpAssetCdService;
    */

    @Resource(name = "gamPrtFcltyRentFeeMngtService")
    private GamPrtFcltyRentFeeMngtService gamPrtFcltyRentFeeMngtService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * 
     */
    /**
	 * 항만시설사용현황조회 화면으로 이동한다.
	 * @param windowId
	 * @return 항만시설사용현황조회 화면
	 * @exception Exception
	 */
    @RequestMapping(value="/oper/gnrl/gamPrtFcltyRentFeeMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
    	model.addAttribute("windowId", windowId);

    	ErpCmmnCdVO searchVO = new ErpCmmnCdVO();

    	searchVO.setBigClsCd("0401");	// 제코드구분(자산)
    	searchVO.setFirstIndex(0);
    	searchVO.setLastIndex(99);
    	List erpAssetClsList = erpCmmnCdService.selectErpCmmnCdList(searchVO);
    	model.addAttribute("erpAssetClsList", erpAssetClsList);
    	
    	/*
    	searchVO.setBigClsCd("0402");	// 취득구분
    	List erpPurchaseSeList = erpCmmnCdService.selectErpCmmnCdList(searchVO);
    	model.addAttribute("erpPurchaseSeList", erpPurchaseSeList);
    	
    	searchVO.setBigClsCd("0409");	// 자산구분
    	List erpAssetSeList = erpCmmnCdService.selectErpCmmnCdList(searchVO);
    	model.addAttribute("erpAssetSeList", erpAssetSeList);
    	*/
    	
    	return "/ygpa/gam/oper/gnrl/GamPrtFcltyRentFeeMngt";
    }

    /**
	 * 항만시설사용현황을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용현황 목록
	 * @exception Exception
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrl/selectGamPrtFcltyRentFeeMngt.do", method=RequestMethod.POST)
    @ResponseBody Map selectGamPrtFcltyRentFeeMngtList(GamPrtFcltyRentFeeMngtVO searchVO) throws Exception {
    	
    	int totalCnt, page, firstIndex;
    	Map map = new HashMap();
    	
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	totalCnt = gamPrtFcltyRentFeeMngtService.selectGamPrtFcltyRentFeeMngtListTotCnt(searchVO);
    	
    	List gamAssetList = gamPrtFcltyRentFeeMngtService.selectGamPrtFcltyRentFeeMngtList(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", gamAssetList);
    	map.put("searchOption", searchVO);

    	return map;
    }

}
