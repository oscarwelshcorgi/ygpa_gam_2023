package egovframework.rte.ygpa.gam.oper.gnrl.web;

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

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseSttusInqireService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseSttusInqireVO;

/**
 * @Class Name : GamPrtFcltyUseSttusInqireController.java
 * @Description : 항만시설사용현황조회 (항만시설/일반부두/항만시설사용현황조회)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamPrtFcltyUseSttusInqireController {
	
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
	
    @Resource(name = "gamPrtFcltyUseSttusInqireService")
    private GamPrtFcltyUseSttusInqireService gamPrtFcltyUseSttusInqireService;
    
    /**
	 * 항만시설사용현황조회 화면으로 이동한다.
	 * 
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/GamPrtFcltyUseSttusInqireMngt"
     * @throws Exception the exception  
	 */
	@RequestMapping(value="/oper/gnrl/gamPrtFcltyUseSttusInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		codeVo.setCodeId("COM998"); //항코드 (코드확인요망!!)
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("COM998"); //신청구분코드 (코드확인요망!!)
		List reqstCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		//codeVo.setCodeId("COM998"); //사용용도 코드 (코드확인요망!!) 자산임대테이블에 사용용도 컬럼 없음!!
		//List nticMthCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("COM999"); //고지방법 코드 (코드확인요망!!)
		List nticMthCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("COM998"); //GIS 코드   (코드확인요망!!)
		List gisCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("COM998"); //사용 용도 코드 
		List usagePrposCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("COM998"); //면제 구분  
		List exemptSeCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("COM998"); //면제 사유 코드
		List exemptRsnCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("COM998"); //포장 구분 
		List packSeCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("COM998"); //업체 구분
		List entrpsSeCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("COM998"); //사용료 계산 구분
		List feeCalcSeCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("COM998"); //감면 사용료 계산 구분
		List rdcxptFeeCalcSeCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("reqstCdList", reqstCdList);
		model.addAttribute("nticMthCdList", nticMthCdList);
		model.addAttribute("gisCdList", gisCdList);
		model.addAttribute("usagePrposCdList", usagePrposCdList);
		model.addAttribute("exemptSeCdList", exemptSeCdList);
		model.addAttribute("exemptRsnCdList", exemptRsnCdList);
		model.addAttribute("packSeCdList", packSeCdList);
		model.addAttribute("entrpsSeCdList", entrpsSeCdList);
		model.addAttribute("feeCalcSeCdList", feeCalcSeCdList);
		model.addAttribute("rdcxptFeeCalcSeCdList", rdcxptFeeCalcSeCdList);
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/oper/gnrl/GamPrtFcltyUseSttusInqire";
    }

	/**
     * 항만시설사용현황을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrl/selectPrtFcltyUseSttusInqireList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectPrtFcltyUseSttusInqireList(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {

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
		
		//항만시설사용현황
    	totalCnt = gamPrtFcltyUseSttusInqireService.selectPrtFcltyUseSttusInqireListTotCnt(searchVO);
    	List resultList = gamPrtFcltyUseSttusInqireService.selectPrtFcltyUseSttusInqireList(searchVO);
    	
    	//총면적, 총사용료
    	GamPrtFcltyUseSttusInqireVO resultSum = gamPrtFcltyUseSttusInqireService.selectPrtFcltyUseSttusInqireSum(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	map.put("sumGrAr", resultSum.getSumGrAr());
    	map.put("sumGrFee", resultSum.getSumGrFee());
    	
    	return map;
    }
	
	/**
     * 항만시설사용현황 상세리스트를 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrl/selectPrtFcltyUseSttusInqireDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectPrtFcltyUseSttusInqireDetailList(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {

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

		// 항만시설사용현황 상세리스트 및 총건수
		totalCnt = gamPrtFcltyUseSttusInqireService.selectPrtFcltyUseSttusInqireDetailListTotCnt(searchVO);
		List resultList = gamPrtFcltyUseSttusInqireService.selectPrtFcltyUseSttusInqireDetailList(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }

}
