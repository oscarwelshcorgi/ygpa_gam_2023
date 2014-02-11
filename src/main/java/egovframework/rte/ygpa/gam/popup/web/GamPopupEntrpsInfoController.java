package egovframework.rte.ygpa.gam.popup.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.popup.service.GamPopupEntrpsInfoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupEntrpsInfoVO;

/**
 * @Class Name : GamPopupEntrpsInfoController.java
 * @Description : 업체정보
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamPopupEntrpsInfoController {
	
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
    
    @Resource(name = "gamPopupEntrpsInfoService")
    private GamPopupEntrpsInfoService gamPopupEntrpsInfoService;
	
    
	/**
     * 업체정보 팝업화면을 로딩한다. 
     *
     * @param searchOpt
     * @param model the model
     * @return "/ygpa/gam/popup/GamPopupEntrpsInfo"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/popup/showEntrpsInfo.do")
    String showEntrpsInfo(GamPopupEntrpsInfoVO searchOpt, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("COM998"); //업체 유형 (코드확인요망!!)
		List entrpsTyCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("COM998"); //사업자 구분
		List bsnmSeCdList = cmmUseService.selectCmmCodeDetail(codeVo); 
		
		codeVo.setCodeId("COM998"); //업종
		List indutyCdList = cmmUseService.selectCmmCodeDetail(codeVo);  
		
		codeVo.setCodeId("COM998"); //업태
		List bizcndCdList = cmmUseService.selectCmmCodeDetail(codeVo); 
		
		
		model.addAttribute("searchOpt", searchOpt);
		
		model.addAttribute("entrpsTyCdList", entrpsTyCdList);
		model.addAttribute("bsnmSeCdList", bsnmSeCdList);
		model.addAttribute("indutyCdList", indutyCdList);
		model.addAttribute("bizcndCdList", bizcndCdList);

    	return "/ygpa/gam/popup/GamPopupEntrpsInfo";  
    }
	
	/**
     * 업체정보목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/popup/selectEntrpsInfoList.do", method=RequestMethod.POST)
	@ResponseBody Map selectEntrpsInfoList(GamPopupEntrpsInfoVO searchVO) throws Exception {

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
    	totalCnt = gamPopupEntrpsInfoService.selectEntrpsInfoListTotCnt(searchVO);
    	List resultList = gamPopupEntrpsInfoService.selectEntrpsInfoList(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	
    	return map;
    }

}
