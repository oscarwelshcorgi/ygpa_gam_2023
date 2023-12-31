package egovframework.rte.ygpa.gam.asset.sts.web;

import java.io.IOException;
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

import com.sun.istack.internal.logging.Logger;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.asset.service.GamAssetSttusInqireVO;
import egovframework.rte.ygpa.gam.asset.sts.service.GamGisAssetSttusInqireService;
import egovframework.rte.ygpa.gam.maps.service.GamMapsAssetCodeMngtService;

/**
 * @Class Name : GamGisAssetSttusInqireController.java
 * @Description : 자산GIS통계정보 조회 컨트롤러
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
public class GamGisAssetSttusInqireController {

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

    @Resource(name="gamGisAssetSttusInqireService")
    GamGisAssetSttusInqireService gamGisAssetSttusInqireService;

	@Resource(name = "gamMapsAssetCodeMngtService")
	GamMapsAssetCodeMngtService gamMapsAssetCodeMngtService;

    /**
     * 자산GIS통계 화면을 로딩한다.
     *
     * @param windowId	- 윈도우 아이디
     * @param model the model
     * @return "/ygpa/gam/asset/sts/GamGisAssetSttusInqire"
     * @throws Exception the exception
     */
	@RequestMapping(value="/asset/sts/gamGisAssetSttusInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/asset/sts/GamGisAssetSttusInqire";
    }

	@RequestMapping(value="/asset/sts/gamGisAssetSttusInqire1.do")
	public String indexMain1(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/asset/sts/GamGisAssetSttusInqire1";
    }

	@RequestMapping(value="/asset/sts/gamGisAssetSttusInqire2.do")
	public String indexMain2(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/asset/sts/GamGisAssetSttusInqire2";
    }

	@RequestMapping(value="/asset/sts/gamGisAssetSttusInqire3.do")
	public String indexMain3(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/asset/sts/GamGisAssetSttusInqire3";
    }

	/**
     * 자산정보현황 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/sts/selectGisAssetSttusList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetSttusInqireList(@RequestParam Map searchVO) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	//searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	//searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(Integer.parseInt((String) searchVO.get("pageIndex")));
		paginationInfo.setRecordCountPerPage(Integer.parseInt((String) searchVO.get("pageUnit")));

		searchVO.put("firstIndex", paginationInfo.getFirstRecordIndex());
		searchVO.put("lastIndex",paginationInfo.getLastRecordIndex());
		searchVO.put("recordCountPerPage",paginationInfo.getRecordCountPerPage());

    	totalCnt = gamGisAssetSttusInqireService.selectGisAssetSttusListTotCnt(searchVO);
    	List assetRentList = gamGisAssetSttusInqireService.selectGisAssetSttusList(searchVO);

    	Map resultSum = gamGisAssetSttusInqireService.selectGisAssetSttusListTotSum(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);

    	map.put("resultSum", resultSum);

    	return map;
    }

	@RequestMapping(value="/asset/sts/gamAssetSttusInfo.do")
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
				Map assetCodeInfo = gamGisAssetSttusInqireService.selectAssetSttusInfoByCode(searchVO);
				List deprctnList = gamGisAssetSttusInqireService.selectAssetSttusDeprctnListByCode(searchVO);

				model.addAttribute("assetCd", assetCodeInfo);
	    		model.addAttribute("assetCodeInfo", assetCodeInfo);
				model.addAttribute("deprctnList", deprctnList);

				model.addAttribute("resultCode", 0);
			} catch(IOException e){
				((Log) Logger.getLogger(EgovProperties.class)).debug("IGNORED: " + e.getMessage());
			}
			catch(Exception e) {
				model.addAttribute("resultCode", -1);
	    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.select"));
			}
    	}

    	return "ygpa/gam/asset/sts/GamGisAssetSttusPopupInfo";
    }

	/**
     * 자산임대현황통계 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/sts/selectAssetRentSttusList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetRentSttusList(@RequestParam Map searchVO) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	//searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	//searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(Integer.parseInt((String) searchVO.get("pageIndex")));
		paginationInfo.setRecordCountPerPage(Integer.parseInt((String) searchVO.get("pageUnit")));

		searchVO.put("firstIndex", paginationInfo.getFirstRecordIndex());
		searchVO.put("lastIndex",paginationInfo.getLastRecordIndex());
		searchVO.put("recordCountPerPage",paginationInfo.getRecordCountPerPage());

    	totalCnt = gamGisAssetSttusInqireService.selectGisAssetRentSttusListTotCnt(searchVO);
    	List assetRentList = gamGisAssetSttusInqireService.selectGisAssetRentSttusList(searchVO);

    	Map resultSum = gamGisAssetSttusInqireService.selectGisAssetRentSttusListTotSum(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);

    	map.put("resultSum", resultSum);

    	return map;
    }

	/**
	 * 시설 사용 현황 팝업
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/asset/sts/gamAssetRentSttusInfo.do")
	public String gamAssetRentSttusInfo(@RequestParam Map searchVO, ModelMap model) throws Exception {

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
				Map assetCodeInfo = gamGisAssetSttusInqireService.selectAssetSttusInfoByCode(searchVO);
				Map rentSttus = gamGisAssetSttusInqireService.selectAssetRentSttusInfoByCode(searchVO);
				List rentList = gamGisAssetSttusInqireService.selectAssetRentSttusListByCode(searchVO);

				model.addAttribute("assetCd", assetCodeInfo);
				model.addAttribute("rentSttus", rentSttus);
				model.addAttribute("rentList", rentList);
				model.addAttribute("referDate", (String)searchVO.get("referDate"));

				model.addAttribute("resultCode", 0);
			} catch(IOException e){
				((Log) Logger.getLogger(EgovProperties.class)).debug("IGNORED: " + e.getMessage());
			}
			catch(Exception e) {
				model.addAttribute("resultCode", -1);
	    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.select"));
			}
    	}

    	return "ygpa/gam/asset/sts/GamGisAssetRentSttusPopupInfo";
    }

	/**
     * 자산사용료통계 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/sts/selectAssetRentFeeSttusList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetRentFeeSttusList(@RequestParam Map searchVO) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	//searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	//searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(Integer.parseInt((String) searchVO.get("pageIndex")));
		paginationInfo.setRecordCountPerPage(Integer.parseInt((String) searchVO.get("pageUnit")));

		searchVO.put("firstIndex", paginationInfo.getFirstRecordIndex());
		searchVO.put("lastIndex",paginationInfo.getLastRecordIndex());
		searchVO.put("recordCountPerPage",paginationInfo.getRecordCountPerPage());

    	totalCnt = gamGisAssetSttusInqireService.selectGisAssetRentFeeSttusListTotCnt(searchVO);
    	List assetRentList = gamGisAssetSttusInqireService.selectGisAssetRentFeeSttusList(searchVO);

    	Map resultSum = gamGisAssetSttusInqireService.selectGisAssetRentFeeSttusListTotSum(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);

    	map.put("resultSum", resultSum);

    	return map;
    }

	/**
	 * 시설 사용료 현황 팝업
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/asset/sts/gamAssetRentFeeSttusInfo.do")
	public String gamAssetRentFeeSttusInfo(@RequestParam Map searchVO, ModelMap model) throws Exception {

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
				Map assetCodeInfo = gamGisAssetSttusInqireService.selectAssetSttusInfoByCode(searchVO);
				Map rentSttus = gamGisAssetSttusInqireService.selectAssetRentFeeSttusInfoByCode(searchVO);
				List rentList = gamGisAssetSttusInqireService.selectAssetRentFeeSttusListByCode(searchVO);

				model.addAttribute("assetCd", assetCodeInfo);
				model.addAttribute("rentSttus", rentSttus);
				model.addAttribute("rentList", rentList);
				model.addAttribute("sGrUsagePdFrom", (String)searchVO.get("sGrUsagePdFrom"));
				model.addAttribute("sGrUsagePdTo", (String)searchVO.get("sGrUsagePdTo"));

				model.addAttribute("resultCode", 0);
			} catch(IOException e){
				((Log) Logger.getLogger(EgovProperties.class)).debug("IGNORED: " + e.getMessage());
			}
			catch(Exception e) {
				model.addAttribute("resultCode", -1);
	    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.select"));
			}
    	}

    	return "ygpa/gam/asset/sts/GamGisAssetRentFeeSttusPopupInfo";
    }


}
