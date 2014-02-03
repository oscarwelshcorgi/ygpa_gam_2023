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

import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.com.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;
import egovframework.com.sym.ccm.ccc.service.CmmnClCodeVO;
import egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO;
import egovframework.com.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.rte.cmmn.AjaxXmlView;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdClService;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdService;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdService;
import egovframework.rte.ygpa.gam.sample.service.GamAssetSampleService;

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

    @Resource(name = "gamAssetSampleService")
    private GamAssetSampleService gamAssetService;

	@Resource(name = "CmmnDetailCodeManageService")
    private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

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

    	// 코드 조회
        CmmnDetailCodeVO searchCodeVO;
        searchCodeVO = new CmmnDetailCodeVO();
        searchCodeVO.setRecordCountPerPage(999999);
        searchCodeVO.setFirstIndex(0);
        searchCodeVO.setSearchCondition("1");

        searchCodeVO.setSearchKeyword("GAM019");
        List codeList = cmmnDetailCodeManageService.selectCmmnDetailCodeList(searchCodeVO);
    	model.addAttribute("prtAtCodeList", codeList);

        searchCodeVO.setSearchKeyword("GAM001");
        codeList = cmmnDetailCodeManageService.selectCmmnDetailCodeList(searchCodeVO);
    	model.addAttribute("assetsPrprtySeCdList", codeList);

        searchCodeVO.setSearchKeyword("GAM002");
        codeList = cmmnDetailCodeManageService.selectCmmnDetailCodeList(searchCodeVO);
    	model.addAttribute("assetsLocCdList", codeList);

        searchCodeVO.setSearchKeyword("GAM003");
        codeList = cmmnDetailCodeManageService.selectCmmnDetailCodeList(searchCodeVO);
    	model.addAttribute("assetsQuayCdList", codeList);

        searchCodeVO.setSearchKeyword("GAM019");
        codeList = cmmnDetailCodeManageService.selectCmmnDetailCodeList(searchCodeVO);
    	model.addAttribute("prtAtCodeList", codeList);

        searchCodeVO.setSearchKeyword("GAM005");
        codeList = cmmnDetailCodeManageService.selectCmmnDetailCodeList(searchCodeVO);
    	model.addAttribute("assetsSeCdList", codeList);

        searchCodeVO.setSearchKeyword("GAM013");
        codeList = cmmnDetailCodeManageService.selectCmmnDetailCodeList(searchCodeVO);
    	model.addAttribute("assetsInvstmntMthdList", codeList);

    	return "/ygpa/gam/sample/GamAssetMngt";
    }

/*    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/frgt/traffic/selectCityDoBizTypeDistList.do")
    ModelAndView selectCityDoBizTypeDistList(@RequestParam Map<String,Object> searchOpt) throws Exception {
    	int totalCnt, page, firstIndex;
    	ModelAndView model = new ModelAndView(new AjaxXmlView());

    	if (!searchOpt.containsKey("cmptpCd") || searchOpt.get("cmptpCd")==null  || searchOpt.get("cmptpCd")=="" ) {
    		model.addObject("resultCode", "-1");
    		model.addObject("resultMsg", "Occur Error!! errorno : -1");
    		return model;
    	}

    	List resultList = cmptLogisStatusService.selectCityDoBizTypeDistList(searchOpt);

    	model.addObject("searchOpt", searchOpt);
		model.addObject("resultCode", "0");
    	model.addObject("resultList", resultList);

        model.addObject("totalCount", resultList.size());

    	return model;
    }
    */

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/selectErpAssetCodeList.do", method=RequestMethod.POST)
    @ResponseBody Map selectErpAssetCodeList(ErpAssetCdDefaultVO searchVO) throws Exception {
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

    	totalCnt = erpAssetCdService.selectErpAssetCdListTotCnt(searchVO);

    	List gamAssetList = erpAssetCdService.selectErpAssetCdList(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", gamAssetList);
    	map.put("searchOption", searchVO);

    	return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/selectJsonTest.do", method=RequestMethod.GET)
    public @ResponseBody ErpAssetCdDefaultVO selectJsonTest(ErpAssetCdDefaultVO searchOpt) throws Exception {
//    	ErpAssetCdDefaultVO searchOpt = new ErpAssetCdDefaultVO();
    	searchOpt.setRecordCountPerPage(15);
    	searchOpt.setLastIndex(9999);
    	searchOpt.setFirstIndex(0);

    	return searchOpt;
    }

    @RequestMapping(value="/asset/selectAssetList.do")
    ModelAndView selectAssetList(@RequestParam("prtAtCode") String prtAtCode) throws Exception {
    	Map<String, Object> searchOpt = new HashMap();
//    	AjaxXmlBuilder ajaxXmlBuilder = new AjaxXmlBuilder();

    	searchOpt.put("prtAtCode", prtAtCode);
    	searchOpt.put("recordCountPerPage", 15);
    	searchOpt.put("firstIndex", 0);


    	List gamAssetList = gamAssetService.selectGamAssetUseList(searchOpt);
    	ModelAndView model = new ModelAndView(new AjaxXmlView());

    	model.addObject("prtAtCode", prtAtCode);
    	model.addObject("firstIndex", 0);
    	model.addObject("lastIndex", 15);
    	model.addObject("resultList", gamAssetList);
        int totCnt = gamAssetService.selectGamAssetUseListTotCnt(searchOpt);
        model.addObject("totalCount", totCnt);

//    	for (Iterator iter = (Iterator) gamAssetList.iterator(); iter.hasNext();) {
//    		EgovMap berthInfo = (EgovMap) iter.next();
//    		ajaxXmlBuilder.add;
//    		ajaxXmlBuilder.addItem((String)berthInfo.get("prtFcltyNm"), (String)berthInfo.get("prtFcltySubCd"));
//    	}
//        model.addObject("ajaxXml", ajaxXmlBuilder.toString());
    	return model;
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
