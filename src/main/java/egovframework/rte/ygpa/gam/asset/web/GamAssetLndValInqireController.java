package egovframework.rte.ygpa.gam.asset.web;

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

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.asset.service.GamAssetLndValInqireService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetLndValInqireVO;

/**
 * @Class Name : GamAssetSttusInqireController.java
 * @Description : 자산부지공시지가조회
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamAssetLndValInqireController {

//	protected Log log = LogFactory.getLog(this.getClass());

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

    @Resource(name = "gamAssetLndValInqireService")
    private GamAssetLndValInqireService gamAssetLndValInqireService;


    /**
     * 자산부지공시지가 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/GamAssetLndValInqire"
     * @throws Exception the exception
     */
	@RequestMapping(value="/asset/gamAssetLndValInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();

		codeVo.setCodeId("GAM019"); //항코드
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);

		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/asset/GamAssetLndValInqire";
    }

	/**
     * 자산부지공시지가 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/gamSelectAssetLndValInqireList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetLndValInqireList(GamAssetLndValInqireVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();
    	
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	//searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	//searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	totalCnt = gamAssetLndValInqireService.selectAssetLndValInqireListTotCnt(searchVO);
    	List assetRentList = gamAssetLndValInqireService.selectAssetLndValInqireList(searchVO);
    	GamAssetLndValInqireVO resultVO = gamAssetLndValInqireService.selectAssetLndValInqireSum(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	map.put("sumCnt", resultVO.getSumCnt());
    	map.put("sumArOlnlp", resultVO.getSumArOlnlp());
    	map.put("sumGisAssetsAcqPri", resultVO.getSumGisAssetsAcqPri());
    	
    	return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/selectAssetLndValInqireListExcel.do", method=RequestMethod.POST)
    @ResponseBody ModelAndView selectAssetLndValInqireListExcel(@RequestParam Map<String, Object> excelParam) throws Exception {
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

    	// 환경설정
    	/** EgovPropertyService */
		GamAssetLndValInqireVO searchVO= new GamAssetLndValInqireVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.

		// 조회 조건
        //System.out.print("test *************************** : " + excelParam);
		searchVO = mapper.convertValue(excelParam, GamAssetLndValInqireVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		/** List Data */
    	List assetRentList = gamAssetLndValInqireService.selectAssetLndValInqireList(searchVO);

    	map.put("resultList", assetRentList);
    	map.put("header", header);

    	return new ModelAndView("gridExcelView", "gridResultMap", map);
    }

}
