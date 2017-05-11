/**
 *
 */
package egovframework.rte.ygpa.gam.oper.gnrltest.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.code.service.GamQcItemCdMngVo;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentFeeSttusByFeeTpVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUsageConfmInqireService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUsageConfmInqireVO;

/**
 *
 * @author Administrator
 * @since 2016. 6. 28.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 28.		Administrator		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamTestPrtFcltyUsageConfmInqireController {

	protected Log log = LogFactory.getLog(this.getClass());

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "gamTestPrtFcltyUsageConfmInqireService")
    private GamTestPrtFcltyUsageConfmInqireService gamTestPrtFcltyUsageConfmInqireService;

	@RequestMapping(value="/oper/gnrltest/gamTestPrtFcltyUsageConfmInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/oper/gnrltest/GamTestPrtFcltyUsageConfmInqire";
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrltest/selectPrtFcltyUageConfmInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map selectPrtFcltyUageConfmInqireList(GamTestPrtFcltyUsageConfmInqireVO searchVO) throws Exception {
		int totalCnt, totSumFee, page, firstIndex;

		Map totSum;
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

		totalCnt = gamTestPrtFcltyUsageConfmInqireService.selectPrtFcltyUsageConfmInqireListTotCnt(searchVO);
    	List resultList = gamTestPrtFcltyUsageConfmInqireService.selectPrtFcltyUsageConfmInqireList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
    	searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	totSum = gamTestPrtFcltyUsageConfmInqireService.selectPrtFcltyUsageConfmInqireSum(searchVO);


    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", resultList);
    	map.put("totSumFee", totSum);
    	map.put("totalCount", totalCnt);
    	map.put("searchOption", searchVO);

    	return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/oper/gnrltest/downloadPrtFcltyUageConfmInqireXlsList.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView downloadPrtFcltyUageConfmInqireXlsList(@RequestParam Map<String, Object> excelParam) throws Exception {

		Map map = new HashMap();
		List header;
		ObjectMapper mapper = new ObjectMapper();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return new ModelAndView("gridExcelView", "gridResultMap", map);
		}

		header = mapper.readValue((String)excelParam.get("header"),
								  new TypeReference<List<HashMap<String,String>>>(){});
		excelParam.remove("header");

		GamTestPrtFcltyUsageConfmInqireVO searchVO= new GamTestPrtFcltyUsageConfmInqireVO();
		searchVO = mapper.convertValue(excelParam, GamTestPrtFcltyUsageConfmInqireVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

    	List resultList = gamTestPrtFcltyUsageConfmInqireService.selectPrtFcltyUsageConfmInqireList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

}
