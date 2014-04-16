package egovframework.rte.ygpa.gam.cmmn.itgrn.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.cmmn.itgrn.service.GamNticPayListService_;
import egovframework.rte.ygpa.gam.cmmn.itgrn.service.GamNticPayListVO_;

@Controller
public class GamNticPayListController_ {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovProgrmManageService */
	@Resource(name = "gamNticPayListService_")
    private GamNticPayListService_ gamNticPayListService_;

	/** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;
    
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@RequestMapping(value="/cmmn/itgrn/gamNticPayList_.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		model.addAttribute("windowId", windowId);
		
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM024"); //요금종류
		List<?> chrgeKndCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		codeVo.setCodeId("GAM025"); //수납구분 
		List<?> rcivSeCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);
		model.addAttribute("rcivSeCdList", rcivSeCdList);
		
    	return "/ygpa/gam/cmmn/itgrn/GamNticPayList_";
    }


	/**
	 * 납부현황목록조회을 조회한다.
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping(value="/cmmn/itgrn/gamNticPayListSelect_.do")
    @ResponseBody Map<String, Object> selectNticPayList(GamNticPayListVO_ searchVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
		
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회
    	/** EgovPropertyService */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
		List<ComDefaultVO> nticPayList = gamNticPayListService_.selectNticPayList(searchVO);
        int totCnt = gamNticPayListService_.selectNticPayListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
		
		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", nticPayList);
    	map.put("searchOption", searchVO);

    	return map;
    }
    
    
	/**
	 * 납부현황목록조회 상세화면
	 * @param fcltyManageVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/cmmn/itgrn/gamNticPayListSelectView_.do")
    @ResponseBody Map<String, Object> gamNticPayListSelectView(@ModelAttribute("GamNticPayListVO") GamNticPayListVO_ nticPayListVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	nticPayListVO = gamNticPayListService_.gamNticPayListSelectView(nticPayListVO);

        map.put("detail", nticPayListVO);

        return map;
    }
}