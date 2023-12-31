package egovframework.rte.ygpa.gam.asset.web;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.asset.service.GamAssetSttusInqireVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetSttusInqireService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtVO;

/**
 * @Class Name : GamAssetSttusInqireController.java
 * @Description : 자산정보현황조회
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
public class GamAssetSttusInqireController {

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

    @Resource(name = "gamAssetSttusInqireService")
    private GamAssetSttusInqireService gamAssetSttusInqireService;


    /**
     * 자산정보현황 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/GamAssetDisUseMngt"
     * @throws Exception the exception
     */
	@RequestMapping(value="/asset/gamAssetSttusInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();

		codeVo.setCodeId("GAM019"); //항코드
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);

		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/asset/GamAssetSttusInqire";
    }

	/**
     * 자산정보현황 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/gamAssetSttusInqireList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetSttusInqireList(GamAssetSttusInqireVO searchVO) throws Exception {

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

    	totalCnt = gamAssetSttusInqireService.selectAssetDisUseListTotCnt(searchVO);
    	List assetRentList = gamAssetSttusInqireService.selectAssetSttusInqireList(searchVO);

    	GamAssetSttusInqireVO resultSum = gamAssetSttusInqireService.selectAssetSttusInqireSum(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);

    	map.put("sumCnt", resultSum.getSumCnt());
    	map.put("sumAr", resultSum.getSumAr());
    	map.put("sumFee", resultSum.getSumFee());
    	map.put("sumRdcxptFee", resultSum.getSumRdcxptFee());

    	return map;
    }

	/**
     * 자산정보현황 목록을 인쇄한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/gamAssetSttusInqireListPrint.do")
	public String selectAssetSttusInqireListPrint(@RequestParam Map<String, Object> assetSttusOpt, ModelMap model) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "ygpa/gam/asset/GamAssetSttusInqirePrint";
    	}

		ObjectMapper mapper = new ObjectMapper();
		GamAssetSttusInqireVO searchVO;

    	searchVO = mapper.convertValue(assetSttusOpt, GamAssetSttusInqireVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

    	totalCnt = gamAssetSttusInqireService.selectAssetDisUseListTotCnt(searchVO);
    	List assetRentList = gamAssetSttusInqireService.selectAssetSttusInqireList(searchVO);

    	GamAssetSttusInqireVO resultSum = gamAssetSttusInqireService.selectAssetSttusInqireSum(searchVO);

        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("resultList", assetRentList);

        model.addAttribute("sumCnt", resultSum.getSumCnt());
        model.addAttribute("sumAr", resultSum.getSumAr());
        model.addAttribute("sumFee", resultSum.getSumFee());
        model.addAttribute("sumRdcxptFee", resultSum.getSumRdcxptFee());

		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");

    	return "ygpa/gam/asset/GamAssetSttusInqirePrint";
    }
}
