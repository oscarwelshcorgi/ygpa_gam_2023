package egovframework.rte.ygpa.gam.asset.sts.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetUseSttusInqireService;

/**
 * @Class Name : GamGisAssetSttusInqireController.java
 * @Description : 자산GIS통계정보 사용현황 조회
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-11-24
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamGisAssetUseSttusInqireController {

	protected Log log = LogFactory.getLog(this.getClass());

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="gamGisAssetUseSttusInqireService")
    GamGisAssetUseSttusInqireService gamGisAssetUseSttusInqireService;

    /**
     * GIS 통계 시설물 분포 현황 조회 화면을 로딩한다.
     *
     * @param windowId	- 윈도우 아이디
     * @param model the model
     * @return "/ygpa/gam/asset/sts/GamGisAssetSttusInqire"
     * @throws Exception the exception
     */
	@RequestMapping(value="/asset/sts/gamGisAssetUseSttusInqire.do")
	public String gamGisAssetUseSttusInqire(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/asset/sts/GamGisAssetUseSttusInqire";
    }

	/**
     * 자산정보현황 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/sts/selectGisAssetUseSttusList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectGisAssetUseSttusList(@RequestParam Map searchVO) throws Exception {

		int totalCnt;
    	Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(Integer.parseInt((String) searchVO.get("pageIndex")));
		paginationInfo.setRecordCountPerPage(Integer.parseInt((String) searchVO.get("pageUnit")));

		searchVO.put("firstIndex", paginationInfo.getFirstRecordIndex());
		searchVO.put("lastIndex",paginationInfo.getLastRecordIndex());
		searchVO.put("recordCountPerPage",paginationInfo.getRecordCountPerPage());

    	totalCnt = gamGisAssetUseSttusInqireService.selectGisAssetUseSttusListTotCnt(searchVO);
    	List assetRentList = gamGisAssetUseSttusInqireService.selectGisAssetUseSttusList(searchVO);

    	Map resultSum = gamGisAssetUseSttusInqireService.selectGisAssetUseSttusListTotSum(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);

    	map.put("resultSum", resultSum);

    	return map;
    }

	@RequestMapping(value="/asset/sts/gamAssetUseSttusInfo.do")
	public String gamAssetCdInfo(@RequestParam Map searchVO, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	}
    	else {
    		String auth="";
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    		searchVO.put("authorities", EgovUserDetailsHelper.getAuthorities());

    		try {
				Map assetCodeInfo = gamGisAssetUseSttusInqireService.selectAssetUseSttusInfoByCode(searchVO);
				List usageList = gamGisAssetUseSttusInqireService.selectAssetUseSttusInfoListByCode(searchVO);

	    		model.addAttribute("assetCodeInfo", assetCodeInfo);
				model.addAttribute("referDate", (String)searchVO.get("searchDate"));
				model.addAttribute("usageList", usageList);

				model.addAttribute("resultCode", 0);
			}
			catch(Exception e) {
				model.addAttribute("resultCode", -1);
	    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.select"));
			}
    	}

    	return "ygpa/gam/asset/sts/GamGisAssetUseSttusPopupInfo";
    }

}
