/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageHistInqireService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageHistInqireVO;

/**
 * 
 * @author 김영길
 * @since 2014. 12. 11.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014.12.11	김영길		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamFcltyUsageHistInqireController {
	
	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name="gamFcltyUsageHistInqireService")
    protected GamFcltyUsageHistInqireService gamFcltyUsageHistInqireService;
    
    
	/**
	 * 시설물 사용이력 조회화면호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/gamFcltyUsageHistInqire.do")
	String indexFcltyUsageHistInqire(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
		return "/ygpa/gam/fcltyMng/GamFcltyUsageHistInqire";
	}
	
	/**
	 * 시설물 사용이력 목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fcltyMng/gamFcltyUsageHistInqireList.do")
	@ResponseBody Map<String, Object> selectFcltyUsageHistInqireList(GamFcltyUsageHistInqireVO searchVO) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
		List<?> usageHistInqireList = gamFcltyUsageHistInqireService.selectFcltyUsageHistInqireList(searchVO);
		GamFcltyUsageHistInqireVO resultSum = gamFcltyUsageHistInqireService.selectFcltyUsageHistInqireListTotCnt(searchVO);

        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);
		map.put("dataCount", resultSum.getDataCount());
		map.put("sumUsageAr", resultSum.getSumUsageAr());
		map.put("sumFee", resultSum.getSumFee());
    	map.put("resultList", usageHistInqireList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	/**
	 * 시설물 사용이력 목록 Excel
	 * @param excelParam
	 * @return ModelAndView
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/gamFcltyUsageHistInqireExcel.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadUsageHistInqire(@RequestParam Map<String, Object> excelParam) throws Exception {
		
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

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});
        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.
        
        GamFcltyUsageHistInqireVO searchVO= new GamFcltyUsageHistInqireVO();
		searchVO = mapper.convertValue(excelParam, GamFcltyUsageHistInqireVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);
		
    	List resultList = gamFcltyUsageHistInqireService.selectFcltyUsageHistInqireList(searchVO);
		
    	map.put("resultCode", 0);
    	map.put("resultList", resultList);
    	map.put("header", header);

    	return new ModelAndView("gridExcelView", "gridResultMap", map);
    }
}
