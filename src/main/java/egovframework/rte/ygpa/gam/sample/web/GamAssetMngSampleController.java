package egovframework.rte.ygpa.gam.sample.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdClService;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdService;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdService;
import egovframework.rte.ygpa.gam.asset.service.GisAssetsCdDefaultVO;
import egovframework.rte.ygpa.gam.sample.service.GamAssetCodeSampleService;
import egovframework.rte.ygpa.gam.sample.service.GamAssetSampleService;
import egovframework.rte.ygpa.gam.sample.service.GamGisCodeSampleService;

/**
 * @author eunsungj
 *
 */
@Controller
public class GamAssetMngSampleController {

    @Resource(name = "erpCmmnCdService")
    private ErpCmmnCdService erpCmmnCdService;

    @Resource(name = "erpCmmnCdClService")
    private ErpCmmnCdClService erpCmmnCdClService;

    @Resource(name = "erpAssetCdService")
    private ErpAssetCdService erpAssetCdService;

    @Resource(name = "gamGisCodeSampleService")
    private GamGisCodeSampleService gamGisCodeService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @RequestMapping(value="/sample/gamAssetMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

    	model.addAttribute("windowId", windowId);

    	ErpCmmnCdVO searchVO = new ErpCmmnCdVO();

    	searchVO.setBigClsCd("0401");	// 제코드구분
    	searchVO.setFirstIndex(0);
    	searchVO.setLastIndex(99);
    	List erpAssetClsList = erpCmmnCdService.selectErpCmmnCdList(searchVO);
    	model.addAttribute("erpAssetClsList", erpAssetClsList);

    	searchVO.setBigClsCd("0402");	// 취득구분
    	List erpPurchaseSeList = erpCmmnCdService.selectErpCmmnCdList(searchVO);
    	model.addAttribute("erpPurchaseSeList", erpPurchaseSeList);

    	searchVO.setBigClsCd("0409");	// 자산구분
    	List erpAssetSeList = erpCmmnCdService.selectErpCmmnCdList(searchVO);
    	model.addAttribute("erpAssetSeList", erpAssetSeList);

    	return "/ygpa/gam/sample/GamAssetMngt";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/sample/selectErpAssetCodeList.do", method=RequestMethod.POST)
    @ResponseBody Map selectErpAssetCodeList(ErpAssetCdDefaultVO searchVO) throws Exception {
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

    @RequestMapping(value="/sample/selectGisAssetCodeList.do")
    @ResponseBody Map selectGisAssetCodeList(GisAssetsCdDefaultVO searchVO) throws Exception {
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

		/** List Data */
    	int totCnt = gamGisCodeService.selectGisCodeListTotCnt(searchVO);

    	List gamAssetList = gamGisCodeService.selectGisCodeList(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", gamAssetList);
    	map.put("searchOption", searchVO);

    	return map;
    }

//    protected void initBinder(HttpServletRequest request,  ServletRequestDataBinder binder) throws Exception{
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
//        binder.registerCustomEditor(Date.class, editor);
//}
//
//@Override
//protected Object formBackingObject(HttpServletRequest request)
//                throws Exception {
//        SpringJsonForm bean = new SpringJsonForm();
//        bean.setBirthday(new Date());
//        bean.setPlaceofbirth("Sydney");
//        return bean;
//}
//
//public void onSubmitAction(Object command, BindException errors) {
//        SpringJsonForm bean = (SpringJsonForm) command;
//}

}
