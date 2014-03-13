package egovframework.rte.ygpa.gam.sample.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.SimpleType;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.com.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;
import egovframework.com.sym.ccm.ccc.service.CmmnClCode;
import egovframework.com.sym.ccm.ccc.service.CmmnClCodeVO;
import egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO;
import egovframework.com.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.com.uat.uia.web.EgovLoginController;
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

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    protected static final Log LOG = LogFactory.getLog(GamAssetMngSampleController.class);

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
    @RequestMapping(value="/sample/selectErpAssetCodeList.do", method=RequestMethod.POST)
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


    @RequestMapping(value="/sample/mergeAssetCodeList.do")
	@ResponseBody Map<String, Object> mergeAssetCodeList (@RequestParam Map<String, Object> mergeAssetCodeList) throws Exception {

    	Map<String,Object> map = new HashMap<String,Object>();

    	List<HashMap<String,String>> insertList=null;
    	List<HashMap<String,String>> updateList=null;
    	List<HashMap<String,String>> deleteList=null;
    	HashMap<String,String> form=null;

    	ObjectMapper mapper = new ObjectMapper();

    	try {

    		LOG.debug("###################################################### mergeAssetCodeList 1");

    		//convert JSON string to Map
    		insertList = mapper.readValue((String)mergeAssetCodeList.get("insertList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

    		LOG.debug("###################################################### mergeAssetCodeList 2");

    		updateList = mapper.readValue((String)mergeAssetCodeList.get("updateList"),
        		    new TypeReference<List<HashMap<String,String>>>(){});

    		LOG.debug("###################################################### mergeAssetCodeList 3");

    		deleteList = mapper.readValue((String)mergeAssetCodeList.get("deleteList"),
        		    new TypeReference<List<HashMap<String,String>>>(){});

    		LOG.debug("###################################################### mergeAssetCodeList 4");

    		form = mapper.readValue((String)mergeAssetCodeList.get("form"),
        		    new TypeReference<HashMap<String,String>>(){});

    		LOG.debug("###################################################### insertList : "+insertList);
    		LOG.debug("###################################################### updateList : "+updateList);
    		LOG.debug("###################################################### deleteList : "+deleteList);

    		for( int i = 0 ; i < insertList.size() ; i++ ) {
    			LOG.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ insertList.get(i) String => " + insertList.get(i));

    			Map resultMap = insertList.get(i);

    			LOG.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ resultMap.get() => " + resultMap.get("gisAssetsPrtAtCode"));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	LOG.debug("insert list : "+insertList.size());
    	LOG.debug("updateList list : "+updateList.size());
    	LOG.debug("deleteList list : "+deleteList.size());

		map.put("resultCode", 0);			// return ok
		map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));
		return map;
    }

}
