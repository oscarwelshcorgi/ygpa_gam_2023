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


    /**
     * 화면 호출
     * @param searchOpt
     * @param model
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/popup/showAssetsCd.do")
    String showAssetsCd(GamPopupGisAssetsCdVO searchOpt, ModelMap model) throws Exception {
    	model.addAttribute("searchOpt", searchOpt);

    	return "/ygpa/gam/popup/GamPopupAssetsCd";
    }


    /**
     * 자산코드 목록 가져오기
     * @param searchVO
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/popup/selectAssetCodeList.do", method=RequestMethod.POST)
    @ResponseBody Map<String, Object> selectAssetCodeList(GamPopupGisAssetsCdVO searchVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> gamAssetList = gamPopupGisAssetsCdService.selectGisAssetsCdList(searchVO);
		int totalCnt = gamPopupGisAssetsCdService.selectGisAssetsCdListTotCnt(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", gamAssetList);
    	map.put("searchOption", searchVO);

    	return map;
    }

}
