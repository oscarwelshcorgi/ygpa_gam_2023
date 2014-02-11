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

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.asset.service.GamAssetDisUseMngtService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetDisUseMngtVO;

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
	public @ResponseBody Map selectAssetDisUseList(GamAssetDisUseMngtVO searchVO) throws Exception {

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
		
    	totalCnt = gamAssetDisUseMngtService.selectAssetDisUseListTotCnt(searchVO);
    	List assetRentList = gamAssetDisUseMngtService.selectAssetDisUseList(searchVO);
    	
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
    	   @ModelAttribute("gamAssetDisUseMngtVO") GamAssetDisUseMngtVO gamAssetDisUseMngtVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
        gamAssetDisUseMngtVO.setErpAssetsDisuseRegistYn("Y");
        gamAssetDisUseMngtVO.setUpdUsr("admin1"); // 추후세션 처리
        
        gamAssetDisUseMngtService.updateAssetDisUse(gamAssetDisUseMngtVO);
    	
        resultCode = 0; // return ok
        resultMsg  = egovMessageSource.getMessage("gam.asset.proc"); //정상적으로 처리되었습니다.
        
    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);
        
		return map;
    }
    
}
