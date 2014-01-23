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
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentSttusInqireService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentSttusInqireVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentVO;

/**
 * @Class Name : GamAssetRentSttusInqireController.java
 * @Description : 자산임대현황조회
 * @Modification Information
 *
 * @author 정윤후
 * @since 2014-01-22
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamAssetRentSttusInqireController {
	
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
    
    @Resource(name = "gamAssetRentSttusInqireService")
    private GamAssetRentSttusInqireService gamAssetRentSttusInqireService;
	
    
    /**
     * 자산임대관리 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/GamAssetRentSttusInqire"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/asset/gamAssetRentSttusInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("COM998"); //항코드 (코드확인요망!!)
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("COM998"); //신청구분코드 (코드확인요망!!)
		List reqstCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		//codeVo.setCodeId("COM998"); //사용용도 코드 (코드확인요망!!) 자산임대테이블에 사용용도 컬럼 없음!!
		//List nticMthCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("reqstCdList", reqstCdList);
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/asset/GamAssetRentSttusInqire";
    }
	
	/**
     * 자산임대목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/selectAssetRentSttusInqireList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetRentList(GamAssetRentSttusInqireVO searchVO) throws Exception {

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
    	totalCnt = gamAssetRentSttusInqireService.selectAssetRentListTotCnt(searchVO);
    	List assetRentList = gamAssetRentSttusInqireService.selectAssetRentList(searchVO);
    	
    	//총면적, 총사용료
    	GamAssetRentSttusInqireVO resultSum = gamAssetRentSttusInqireService.selectAssetRentSum(searchVO);
    	
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
    @RequestMapping(value="/asset/selectAssetRentSttusInqireDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetRentDetailList(GamAssetRentSttusInqireVO searchVO) throws Exception {

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

		// 자산임대상세리스트 및 총건수
		totalCnt = gamAssetRentSttusInqireService.selectAssetRentDetailListTotCnt(searchVO);
		List resultList = gamAssetRentSttusInqireService.selectAssetRentDetailList(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
}
