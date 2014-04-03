package egovframework.rte.ygpa.gam.popup.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdClService;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdService;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupGisAssetsCdService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupGisAssetsCdVO;

/**
 * Erp 팝업
 * @author eunsungj
 *
 */
@Controller
public class GamPopupErpAssetCdController {

    @Resource(name = "erpCmmnCdService")
    private ErpCmmnCdService erpCmmnCdService;

    @Resource(name = "erpCmmnCdClService")
    private ErpCmmnCdClService erpCmmnCdClService;

    @Resource(name = "erpAssetCdService")
    private ErpAssetCdService erpAssetCdService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /**
     * 화면 호출
     * @param searchOpt
     * @param model
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/popup/showErpAssetCd.do")
    String showAssetsCd(GamPopupGisAssetsCdVO searchOpt, ModelMap model) throws Exception {
    	model.addAttribute("searchOpt", searchOpt);

    	ErpCmmnCdVO searchVO = new ErpCmmnCdVO();

    	searchVO.setBigClsCd("0401");	// 제코드구분
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
    	return "ygpa/gam/code/popup/GamPopupErpAssetCd";
    }


    /**
     * ERP 자산코드 목록 가져오기
     * @param searchVO
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/popup/selectErpAssetCodeList.do", method=RequestMethod.POST)
    @ResponseBody Map<String, Object> selectAssetCodeList(ErpAssetCdDefaultVO searchVO) throws Exception {
		Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	// 환경설정
    	/** EgovPropertyService */

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
    	int totCnt = erpAssetCdService.selectErpAssetCdListTotCnt(searchVO);

    	List gamAssetList = erpAssetCdService.selectErpAssetCdList(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", gamAssetList);
    	map.put("searchOption", searchVO);

    	return map;
    }

}
