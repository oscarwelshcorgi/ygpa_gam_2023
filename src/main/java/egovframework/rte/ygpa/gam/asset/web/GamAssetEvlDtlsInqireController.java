package egovframework.rte.ygpa.gam.asset.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.cmmn.AjaxXmlView;

@Controller
public class GamAssetEvlDtlsInqireController {

	@RequestMapping(value="/asset/gamAssetEvlDtlsInqire.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/asset/GamAssetEvlDtlsInqire";
    }
	
	// 조회
	    @RequestMapping(value="/asset/inqry/selectAssetEvlDtlsInqryList.do")
	    ModelAndView selectAssetList(@RequestParam Map<String,Object> searchOpt) throws Exception {
	    	int totalCnt, page, firstIndex;
	//
//	    	searchOpt.put("prtAtCode", prtAtCode);
	    	page = Integer.parseInt((String)searchOpt.get("page"));
	    	if(page<0) page=0;
	    	totalCnt = Integer.parseInt((String)searchOpt.get("recordCountPerPage"));
	    	if(totalCnt<5) totalCnt=15;
	    	firstIndex = (page-1)*totalCnt;
//	    	if(searchOpt.containsKey("recordCountPerPage"))searchOpt.
//	    	else 
	    		searchOpt.put("recordCountPerPage", totalCnt);
	    	searchOpt.put("firstIndex", firstIndex);
	    	
//	    	List gamAssetList = gamAssetCodeService.selectAssetCodeList(searchOpt);
//
//	        int totCnt = gamAssetCodeService.selectAssetCodeListTotCnt(searchOpt);
	    	
	    	// sample
	        List<Map> assetEvlDtlsList = new ArrayList<Map>();

	        int totCnt = 80;

	    	ModelAndView model = new ModelAndView(new AjaxXmlView());

	    	model.addObject("searchOpt", searchOpt);
//	    	model.addObject("firstIndex", 0);
	    	model.addObject("page", page);
	    	model.addObject("lastIndex", 15);
	    	// sample
	    	for(int i=0; i<80; i++) {
	    		Map <String,String>result = new HashMap<String, String>();
	    		result.put("PRT_AT_CODE", "622");
	    		result.put("ASSETS_CODE", "LNM-"+String.format("%03d", i));
	    		result.put("ASSETS_NM", "테스트 시설 "+String.format("%03d", i));
	    		result.put("LOCPLC", "TEST");
	    		result.put("LNM", String.format("%3d", i));
	    		result.put("LNM_SUB", "");
	    		result.put("AR", String.format("%d", (int)Math.random()*1000));
	    		result.put("VAL_AMOUNT", "1000000");
	    		result.put("VAL_AGENT", "TEST");
	    		result.put("VAL_DT", "2010-08-23");
	    		result.put("ASSETS_SE_CD", "LAND");
	    		result.put("PRPRTY_CD", "FCLTY");
	    		result.put("INVSTMNT_MTHD", "METHOD");
	    		assetEvlDtlsList.add(result);
	    	}
	    	model.addObject("resultList", assetEvlDtlsList);
	        model.addObject("totalCount", totCnt);
	    	
	    	return model;
	    }
}
