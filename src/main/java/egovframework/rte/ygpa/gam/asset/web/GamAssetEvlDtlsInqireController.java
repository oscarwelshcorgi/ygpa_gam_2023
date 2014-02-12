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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
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
		
		model.addAttribute("prtAtCdList", prtAtCdList);
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

    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
    	totalCnt = gamAssetEvlDtlsInqireService.selectAssetEvlDtlsInqireListTotCnt(searchVO);
    	List assetRentList = gamAssetEvlDtlsInqireService.selectAssetEvlDtlsInqireList(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	
    	return map;
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
		
		GamAssetEvlDtlsInqireVO result = gamAssetEvlDtlsInqireService.selectAssetEvlDtlsInqireErp(searchVO);
    	
		if( EgovStringUtil.isEmpty(result.getDeprctnYear()) ) {
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
