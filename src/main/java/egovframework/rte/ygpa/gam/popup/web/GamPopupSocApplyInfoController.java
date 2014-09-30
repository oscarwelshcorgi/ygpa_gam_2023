/**
 * 
 */
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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocApplyInfoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocApplyInfoVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 30.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 30.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupSocApplyInfoController {
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
    
    @Resource(name = "gamPopupSocApplyInfoService")
    private GamPopupSocApplyInfoService gamPopupSocApplyInfoService;
	
    @Resource(name="gamSocCmmUseService")
    private GamSocCmmUseService gamSocCmmUseService;
	
	/**
     * 요금정보 팝업화면을 로딩한다. 
     *
     * @param searchOpt
     * @param model the model
     * @return "/ygpa/gam/popup/GamPopupPayCd"
     * @throws Exception the exception  
     */
	@RequestMapping("/popup/showSocApplyInfo.do")
	String showApplyInfo(GamPopupSocApplyInfoVO searchOpt, ModelMap model) throws Exception {
		List prtAtCdList = gamSocCmmUseService.selectSocPrtAtCodeDetail();
    	
		model.addAttribute("searchOpt", searchOpt);
		model.addAttribute("prtAtCdList", prtAtCdList);

		return "/ygpa/gam/popup/GamPopupSocApplyInfo";  
    }
	
	
	/**
     * 요금정보목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/popup/selectSocApplyInfoList.do", method=RequestMethod.POST)
	@ResponseBody Map selectApplyInfoList(GamPopupSocApplyInfoVO searchVO) throws Exception {

		int totalCnt;
    	Map map = new HashMap();

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List resultList = gamPopupSocApplyInfoService.selectSocApplyInfoList(searchVO);
    	totalCnt = gamPopupSocApplyInfoService.selectSocApplyInfoListTotCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
		
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	
    	return map;
    }
}
