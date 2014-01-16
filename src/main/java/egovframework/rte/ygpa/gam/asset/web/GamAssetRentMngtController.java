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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.sym.prm.service.ProgrmManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentDetailVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentVO;

/**
 * @Class Name : GamAssetRentMngtController.java
 * @Description : 자산임대관리
 * @Modification Information
 *
 * @author dev
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamAssetRentMngtController {
	
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
    
    @Resource(name = "gamAssetRentService")
    private GamAssetRentService gamAssetRentService;
	
    
    /**
     * 자산임대관리 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/GamAssetRentMngt"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/asset/gamAssetRentMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/asset/GamAssetRentMngt";
    }
	
	
	/**
     * 자산임대목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/selectAssetRentList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetRentList(GamAssetRentVO searchVO) throws Exception {

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
		
		//자산임대목록
    	totalCnt = gamAssetRentService.selectAssetRentListTotCnt(searchVO);
    	List assetRentList = gamAssetRentService.selectAssetRentList(searchVO);
    	
    	//총면적, 총사용료
    	GamAssetRentVO resultSum = gamAssetRentService.selectAssetRentSum(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	map.put("sumGrAr", resultSum.getSumGrAr());
    	map.put("sumGrFee", resultSum.getSumGrFee());
    	
    	return map;
    }
	
	
	/**
     * 자산임대상세리스트를 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/selectAssetRentDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetRentDetailList(GamAssetRentDetailVO searchVO) throws Exception {

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

		
		System.out.println("############################################ searchVO.getDetailMngNo() => " + searchVO.getDetailMngNo());
		System.out.println("############################################ searchVO.getDetailMngYear() => " + searchVO.getDetailMngYear());
		
		// 자산임대상세리스트 및 총건수
    	totalCnt = gamAssetRentService.selectAssetRentDetailListTotCnt(searchVO);
    	List resultList = gamAssetRentService.selectAssetRentDetailList(searchVO);
    	
    	System.out.println("############################################ selectAssetRentDetailList : resultList => " + resultList);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
     * 자산임대 최초 신청을 등록한다.
     * @param commandMap
     * @param gamAssetRentVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/asset/gamInsertAssetRentFirst.do") 
    public @ResponseBody Map insertAssetRentFirst(
    		@RequestParam("cmd") String cmd,
    		@ModelAttribute("gamAssetRentVO") GamAssetRentVO gamAssetRentVO,
			BindingResult bindingResult)
            throws Exception {
    	
    	Map map = new HashMap();
        String resultMsg = "";
        
        /*
        String sLocationUrl = null;
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	*/

    	System.out.println("######################################## cmd => " + cmd);
    	
    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamAssetRentVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamAssetRentVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamAssetRentService.insertAssetRentFirst(gamAssetRentVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	//max관리번호 조회
//    	selectAssetRentMaxNo
    	
    	GamAssetRentVO resultMaxNo = gamAssetRentService.selectAssetRentMaxNo(gamAssetRentVO);
    	
    	System.out.println("######################################## gamAssetRentVO.getrMngNo() => " + gamAssetRentVO.getrMngNo());
    	System.out.println("######################################## resultMaxNo.getrMngNo() => " + resultMaxNo.getrMngNo());    	
    	
    	if( gamAssetRentVO.getrMngNo().equals(resultMaxNo.getrMngNo()) ) {
    		//키 같고 max관리번호가 같으면 연장신청 등록
        	
        	gamAssetRentService.insertAssetRentFirst(gamAssetRentVO);
        	
    		resultMsg = egovMessageSource.getMessage("success.common.insert");
    	} else {
    		resultMsg = egovMessageSource.getMessage("에러");
    	}
    	
    	
    	

    	
        map.put("resultCode", 0);			// return ok
        map.put("resultMsg", resultMsg);
        map.put("testMsg", "testMsg!!");
        
		return map;
    }
    
    
    /**
     * 자산임대 연장신청을 등록한다.
     * @param commandMap
     * @param gamAssetRentVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/asset/gamInsertAssetRentRenew.do") 
    public @ResponseBody Map insertAssetRentRenew(
    		@ModelAttribute("gamAssetRentVO") GamAssetRentVO gamAssetRentVO, BindingResult bindingResult)
            throws Exception {
    	
    	Map map = new HashMap();
        String resultMsg = "";
        
        /*
        String sLocationUrl = null;
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	*/

    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamAssetRentVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamAssetRentVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamAssetRentService.insertAssetRentFirst(gamAssetRentVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
        
        
        
        
    	//gamAssetRentService.insertAssetRentFirst(gamAssetRentVO);
    	
		resultMsg = egovMessageSource.getMessage("success.common.insert");
    	
		System.out.println("######################################## gamAssetRentVO.getrPrtAtCode() => " + gamAssetRentVO.getrPrtAtCode());
		System.out.println("######################################## resultMsg => " + resultMsg);
		
        map.put("resultCode", 0);			// return ok
        map.put("resultMsg", resultMsg);
        map.put("testMsg", "testMsg!!");
        
		return map;
    }
	
}
