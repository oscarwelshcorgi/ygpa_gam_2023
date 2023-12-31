package egovframework.rte.ygpa.gam.asset.web;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.sun.istack.internal.logging.Logger;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.asset.service.GamAssetDisUseMngtService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetDisUseMngtVO;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeDefaultVO;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeVO;

/**
 * @Class Name : GamAssetDisUseMngtController.java
 * @Description : 자산폐기등록
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamAssetDisUseMngtController {

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

    /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    @Resource(name = "gamAssetDisUseMngtService")
    private GamAssetDisUseMngtService gamAssetDisUseMngtService;


    /**
     * 자산폐기등록 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/GamAssetDisUseMngt"
     * @throws Exception the exception
     */
	@RequestMapping(value="/asset/gamAssetDisUseMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();

		codeVo.setCodeId("GAM019"); //항코드
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);

		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/asset/GamAssetDisUseMngt";
    }

	/**
     * GIS자산코드 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/gamSelectAssetDisUseList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetDisUseList(GamGisAssetCodeDefaultVO searchVO) throws Exception {

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
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	totalCnt = gamAssetDisUseMngtService.selectAssetDisUseListTotCnt(searchVO);
    	List assetRentList = gamAssetDisUseMngtService.selectAssetDisUseList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	/**
     * 자산폐기 팝업화면을 로딩한다.
     *
     * @param gamAssetDisUseMngtVO
     * @param model the model
     * @return "/ygpa/gam/asset/GamPopupAssetRentPrmisn"
     * @throws Exception the exception
     */
	@RequestMapping(value="/asset/popup/showAssetDisUse.do")
    String showAssetDisUse(GamAssetDisUseMngtVO gamAssetDisUseMngtVO, ModelMap model) throws Exception {

		model.addAttribute("gamAssetDisUseMngtVO", gamAssetDisUseMngtVO);

    	return "/ygpa/gam/asset/GamPopupAssetDisUse";
    }

    /**
     * ERP 자산 폐기 정보를 수정한다.
     * @param String
     * @param GamAssetDisUseMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/gamUpdateAssetDisUse.do")
    public @ResponseBody Map updateAssetDisUse(
    	   @ModelAttribute("gamAssetDisUseMngtVO") GamGisAssetCodeVO gamAssetDisUseMngtVO,
    	   BindingResult bindingResult)
           throws Exception {

    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;

        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
			LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

	        gamAssetDisUseMngtVO.setUpdUsr(loginVO.getId());

	        gamAssetDisUseMngtService.updateAssetDisUse(gamAssetDisUseMngtVO);
    	} catch(IOException e){
    		((Log) Logger.getLogger(EgovProperties.class)).debug("IGNORED: " + e.getMessage());
    	}
    	catch(Exception e) {
        	map.put("resultCode", -1);	// return ok
        	map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));

        	return map;
    	}

        resultCode = 0; // return ok
        resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.

    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);

		return map;
    }

}
