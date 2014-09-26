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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocAgentFInfoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocAgentFInfoVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocEntrpsInfoVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseVO;


/**
 * @Class Name : GamPopupSocAgentFInfoController.java
 * @Description : 허가원부선택정보
 * @Modification Information
 * 
 * @author HNJ
 * @since 2014. 9. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 25.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupSocAgentFInfoController {
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@Resource(name = "gamSocCmmUseService")
    private GamSocCmmUseService gamSocCmmUseService;
	
	@Resource(name = "gamPopupSocAgentFInfoService")
    private GamPopupSocAgentFInfoService gamPopupSocAgentFInfoService;
	
	/**
     * 허가원부정보 팝업화면을 로딩한다.
     *
     * @param searchOpt
     * @param model the model
     * @return "/ygpa/gam/popup/GamPopupSocAgentFInfo"
     * @throws Exception the exception
     */
	@RequestMapping(value="/popup/showSocAgentFInfo.do")
	public String showAgentFInfo(GamPopupSocEntrpsInfoVO searchOpt, ModelMap model) throws Exception {
		
		String windowId;
		
		GamSocCmmUseVO codeVo = new GamSocCmmUseVO();
		codeVo.setCodeId("GAM019"); //항코드 
		
		List prtAtCdList = gamSocCmmUseService.selectSocPrtAtCodeDetail();
		
		windowId = searchOpt.getWindowId();

		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/popup/GamPopupSocAgentFInfo";
    }
	
	
	/**
     * 허가원부정보목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/popup/selectSocAgentFInfoList.do", method=RequestMethod.POST)
	@ResponseBody Map selectSocAgentFInfoList(GamPopupSocAgentFInfoVO searchVO) throws Exception {

		int totalCnt;
    	Map map = new HashMap();

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List resultList = gamPopupSocAgentFInfoService.selectSocAgentFInfoList(searchVO);
    	totalCnt = gamPopupSocAgentFInfoService.selectSocAgentFInfoListTotCnt(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	

}
