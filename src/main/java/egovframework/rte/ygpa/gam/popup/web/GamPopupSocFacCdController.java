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
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocFacCdService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocFacCdVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 25.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupSocFacCdController {
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
    
    @Resource(name = "gamPopupSocFacCdService")
    private GamPopupSocFacCdService gamPopupSocFacCdService;
	
	
	/**
     * 요금정보 팝업화면을 로딩한다. 
     *
     * @param searchOpt
     * @param model the model
     * @return "/ygpa/gam/popup/GamPopupPayCd"
     * @throws Exception the exception  
     */
	@RequestMapping("/popup/showSocFacCd.do")
	String showFacCd(GamPopupSocFacCdVO searchOpt, ModelMap model) throws Exception {
		model.addAttribute("searchOpt", searchOpt);

		return "/ygpa/gam/popup/GamPopupSocFacCd";  
    }
	
	
	/**
     * 요금정보목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/popup/selectSocFacInfoList.do", method=RequestMethod.POST)
	@ResponseBody Map selectFacInfoList(GamPopupSocFacCdVO searchVO) throws Exception {

		int totalCnt;
    	Map map = new HashMap();

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List resultList = gamPopupSocFacCdService.selectSocFacCdList(searchVO);
    	totalCnt = gamPopupSocFacCdService.selectSocFacCdTotCnt(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
		
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	
    	return map;
    }
}
