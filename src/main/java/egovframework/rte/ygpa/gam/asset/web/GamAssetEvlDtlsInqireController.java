package egovframework.rte.ygpa.gam.asset.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetEvlDtlsInqireService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetEvlDtlsInqireVO;

/**
 * @Class Name : GamAssetEvlDtlsInqireController.java
 * @Description : 자산가치평가내역조회
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
public class GamAssetEvlDtlsInqireController {

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

    @Resource(name = "gamAssetEvlDtlsInqireService")
    private GamAssetEvlDtlsInqireService gamAssetEvlDtlsInqireService;


    /**
     * 자산정보현황 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/GamAssetEvlDtlsInqire"
     * @throws Exception the exception
     */
	@RequestMapping(value="/asset/gamAssetEvlDtlsInqire.do")
	String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();

		codeVo.setCodeId("GAM019"); //항코드
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);

		int year = Integer.parseInt(EgovDateUtil.getToday().substring(0,4));
		List yearList = new ArrayList();
		Map yearMap = null;

		for( int i = year ; i >= year-10 ; i-- ) {
			yearMap = new HashMap();
			yearMap.put("code", i);
			yearMap.put("codeNm", i+"년");

			yearList.add(yearMap);
		}

		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("thisYear", year);
		model.addAttribute("yearList", yearList);
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/asset/GamAssetEvlDtlsInqire";
    }

	/**
     * 자산정보현황 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/gamSelectAssetEvlDtlsInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map selectAssetEvlDtlsInqireList(GamAssetEvlDtlsInqireVO searchVO) throws Exception {

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

    	totalCnt = gamAssetEvlDtlsInqireService.selectAssetEvlDtlsInqireListTotCnt(searchVO);
    	List assetRentList = gamAssetEvlDtlsInqireService.selectAssetEvlDtlsInqireList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	GamAssetEvlDtlsInqireVO resultSum = gamAssetEvlDtlsInqireService.selectAssetEvlDtlsInqireSum(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);

    	map.put("sumRevalAmt", resultSum.getSumRevalAmt());
    	map.put("sumThisTermIncreAmt", resultSum.getSumThisTermIncreAmt());
    	map.put("sumBsThisCurAmt", resultSum.getSumBsThisCurAmt());
    	map.put("sumBsPreDeprctnSum", resultSum.getSumBsPreDeprctnSum());
    	map.put("sumBsNoDeprctnBal", resultSum.getSumBsNoDeprctnBal());
    	map.put("sumPreEndAssetBuySum", resultSum.getSumPreEndAssetBuySum());
    	map.put("sumAssetBuyAmt", resultSum.getSumAssetBuyAmt());
    	map.put("sumGnrlDeprctnRate", resultSum.getSumGnrlDeprctnRate());
    	map.put("sumThisTermDeprctnAmt", resultSum.getSumThisTermDeprctnAmt());
    	map.put("sumCurAmt", resultSum.getSumCurAmt());
    	map.put("sumCnt", resultSum.getSumCnt());

    	return map;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/gamSelectAssetEvlDtlsInqireListExcel.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView selectAssetEvlDtlsInqireListExcel(@RequestParam Map<String, Object> excelParam) throws Exception {
		Map map = new HashMap();
		List header;
		ObjectMapper mapper = new ObjectMapper();
		
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    		return new ModelAndView("gridExcelView", "gridResultMap", map);
    	}

		GamAssetEvlDtlsInqireVO searchVO= new GamAssetEvlDtlsInqireVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.

		// 조회 조건
		searchVO = mapper.convertValue(excelParam, GamAssetEvlDtlsInqireVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		/** List Data */

    	List gamAssetList = gamAssetEvlDtlsInqireService.selectAssetEvlDtlsInqireList(searchVO);

    	map.put("resultList", gamAssetList);
    	map.put("header", header);

    	return new ModelAndView("gridExcelView", "gridResultMap", map);
    }

	/**
     * ERP감가상각내역을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/gamSelectAssetEvlDtlsInqireErp.do", method=RequestMethod.POST)
	@ResponseBody Map selectAssetEvlDtlsInqireErp( @RequestParam("erpAssetsSeCd") String erpAssetsSeCd
												  ,@RequestParam("erpAssetsNo") String erpAssetsNo
												  ,@RequestParam("erpAssetsNoSeq") String erpAssetsNoSeq
												  ,@ModelAttribute("gamAssetEvlDtlsInqireVO") GamAssetEvlDtlsInqireVO searchVO
		     								     ) throws Exception {
		Map map = new HashMap();
		
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		GamAssetEvlDtlsInqireVO result = gamAssetEvlDtlsInqireService.selectAssetEvlDtlsInqireErp(searchVO);

		if( result == null || EgovStringUtil.isEmpty(result.getDeprctnYear()) ) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("gam.asset.EvlDtlsInqire.nodata.reason")); //ERP에 해당 데이터가 존재하지 않습니다.
		} else {
			map.put("resultCode", 0);	// return ok
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select")); //정상적으로 조회되었습니다.
			map.put("assetCls", result.getAssetCls());
			map.put("assetNo", result.getAssetNo());
			map.put("assetNoSeq", result.getAssetNoSeq());
	    	map.put("thisTermIncreAmt", result.getThisTermIncreAmt());
	    	map.put("bsThisCurAmt", result.getBsThisCurAmt());
	    	map.put("bsPreDeprctnSum", result.getBsPreDeprctnSum());
	    	map.put("bsNoDeprctnBal", result.getBsNoDeprctnBal());
	    	map.put("preEndAssetBuySum", result.getPreEndAssetBuySum());
	    	map.put("assetBuyAmt", result.getAssetBuyAmt());
	    	map.put("thisTermDeprctnAmt", result.getThisTermDeprctnAmt());
	    	map.put("curAmt", result.getCurAmt());
		}

    	return map;
    }
}
