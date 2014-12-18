/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.web;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocFrghtProcessSetoffLgerService;
import egovframework.rte.ygpa.gam.soc.service.GamSocFrghtProcessSetoffLgerVO;


/**
 * 
 * @author HNJ
 * @since 2014. 10. 16.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 16.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamSocFrghtProcessSetoffLgerController {
	
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
    
    @Resource(name = "gamSocCmmUseService")
    private GamSocCmmUseService gamSocCmmUseService;
    
    @Resource(name = "gamSocFrghtProcessSetoffLgerService")
    private GamSocFrghtProcessSetoffLgerService gamSocFrghtProcessSetoffLgerService;

    
    @SuppressWarnings("rawtypes")
    @RequestMapping(value="/soc/gamSocFrghtProcessSetoffLger.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

    	GamSocCmmUseVO codeVo = new GamSocCmmUseVO();
		
		codeVo.setCodeId("GAM019"); //항코드 
		
		List prtAtCdList = gamSocCmmUseService.selectSocPrtAtCodeDetail();
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/soc/GamSocFrghtProcessSetoffLger";
    }
    
    
    /**
     * 투자비보전(화물)상계처리대장 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSocFrghtProcessSetoffLgerList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectSocFrghtProcessSetoffLgerList(GamSocFrghtProcessSetoffLgerVO searchVO) throws Exception {
		
		int totalCnt;
		long sumExmpAmnt,sumExmpAmntPa,sumAmnt;
		
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		

		//투자비보전(화물)상계처리대장 리스트
    	List socFrghtProcessSetoffLgerList = gamSocFrghtProcessSetoffLgerService.selectSocFrghtProcessSetoffLgerList(searchVO);
    	
    	//투자비보전(화물)상계처리대장 리스트 총갯수 및 금액합계
    	GamSocFrghtProcessSetoffLgerVO socFrghtProcessSetoffLgerDetailSum = gamSocFrghtProcessSetoffLgerService.selectSocFrghtProcessSetoffLgerListSum(searchVO);
    	
		totalCnt = socFrghtProcessSetoffLgerDetailSum.getTotalCnt();
		sumExmpAmnt = socFrghtProcessSetoffLgerDetailSum.getSumExmpAmnt();
		sumExmpAmntPa = socFrghtProcessSetoffLgerDetailSum.getSumExmpAmntPa();
		sumAmnt = socFrghtProcessSetoffLgerDetailSum.getSumAmnt();
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("sumExmpAmnt", sumExmpAmnt);
    	map.put("sumExmpAmntPa", sumExmpAmntPa);
    	map.put("sumAmnt", sumAmnt);
    	map.put("resultList", socFrghtProcessSetoffLgerList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
     * 투자비보전(화물)상계처리대장 목록을 인쇄한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSelectSocFrghtProcessSetoffLgerListPrint.do")
	public String selectSocFrghtProcessSetoffLgerListPrint(@RequestParam Map<String, Object> socFrghtProcessSetoffLgerOpt, ModelMap model) throws Exception {

		int totalCnt;
		String ioDt, ioDt2, dtFr, dtTo, exmpAcc;

    	Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/soc/GamSocFrghtProcessSetoffLgerPrint";
    	}

		ObjectMapper mapper = new ObjectMapper();
		GamSocFrghtProcessSetoffLgerVO searchVO;

    	searchVO = mapper.convertValue(socFrghtProcessSetoffLgerOpt, GamSocFrghtProcessSetoffLgerVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		GamSocFrghtProcessSetoffLgerVO totalSumCnt = gamSocFrghtProcessSetoffLgerService.selectSocFrghtProcessSetoffLgerListSum(searchVO);
    	List socFrghtProcessSetoffLgerList = gamSocFrghtProcessSetoffLgerService.selectSocFrghtProcessSetoffLgerList(searchVO);
    	
    	totalCnt = totalSumCnt.getTotalCnt();
    	dtFr = searchVO.getsDtFr();
    	dtTo = searchVO.getsDtTo();
    	ioDt = searchVO.getsIoDt();
    	ioDt2 = searchVO.getsIoDt2();
    	exmpAcc = searchVO.getsExmpAcc();

        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("dtFr", dtFr);
        model.addAttribute("dtTo", dtTo);
        model.addAttribute("ioDt", ioDt);
        model.addAttribute("ioDt2", ioDt2);
        model.addAttribute("exmpAcc", exmpAcc);
       
        model.addAttribute("resultList", socFrghtProcessSetoffLgerList);

		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");

    	return "ygpa/gam/soc/GamSocFrghtProcessSetoffLgerPrint";
    }
    
    	
	

}
