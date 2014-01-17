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

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupGisAssetsCdService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupGisAssetsCdVO;

/**
 * @author eunsungj
 *
 */
@Controller
public class GamPopupAssetsCdController {

    @Resource(name = "gamPopupGisAssetsCdService")
	private GamPopupGisAssetsCdService gamPopupGisAssetsCdService;


    @RequestMapping(value="/popup/showAssetsCd.do")
    String showAssetsCd(GamPopupGisAssetsCdVO searchOpt, ModelMap model) throws Exception {
    	model.addAttribute("searchOpt", searchOpt);

    	return "/ygpa/gam/popup/GamPopupAssetsCd";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/popup/selectAssetCodeList.do", method=RequestMethod.POST)
    @ResponseBody Map selectAssetCodeList(GamPopupGisAssetsCdVO searchVO) throws Exception {
    	int totalCnt;
    	Map map = new HashMap();

    	searchVO.setPageUnit(10);
    	searchVO.setPageSize(10);

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	totalCnt = gamPopupGisAssetsCdService.selectGisAssetsCdListTotCnt(searchVO);

    	List gamAssetList = gamPopupGisAssetsCdService.selectGisAssetsCdList(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", gamAssetList);
    	map.put("searchOption", searchVO);

    	return map;
    }

}
