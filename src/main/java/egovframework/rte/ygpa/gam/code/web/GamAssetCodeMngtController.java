/**
 *
 */
package egovframework.rte.ygpa.gam.code.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeMngtService;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeVO;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 4. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 4. 3.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamAssetCodeMngtController {

    @Resource(name = "gamGisAssetCodeMngtService")
    private GamGisAssetCodeMngtService gamGisAssetCodeMngtService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/**
	 * 화면 호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/code/assets/gamAssetCodeMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/code/GamAssetCodeMngt";
    }

    /**
     * 자산 코드 조회
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/code/assets/selectGisAssetCodeList.do")
    @ResponseBody Map selectAssetList(GamGisAssetCodeVO searchVO) throws Exception {
    	int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	totalCnt = gamGisAssetCodeMngtService.selectAssetCodeListTotCnt(searchVO);

    	List gamAssetList = gamGisAssetCodeMngtService.selectAssetCodeList(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", gamAssetList);
    	map.put("searchOption", searchVO);

    	return map;
    }

    /**
     * 자산 코드 조회
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/code/assets/selectGisAssetCodeByPk.do")
    @ResponseBody Map selectAssetCodeByPk(@RequestParam Map assetCode) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}


    	EgovMap gamAssetCode = gamGisAssetCodeMngtService.selectAssetCode(assetCode);

    	map.put("resultCode", 0);	// return ok
    	map.put("result", gamAssetCode);

    	return map;
    }

	@RequestMapping(value="/code/assets/insertGamGisAssetCode.do")
	@ResponseBody Map<String, Object> insertGamGisAssetCode(@RequestParam Map<String, Object> insertVO) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}


    	gamGisAssetCodeMngtService.insertAssetCode(insertVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));

    	return map;
	}

}
