package egovframework.rte.ygpa.gam.port_mis.web;

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
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentSttusInqireVO;
import egovframework.rte.ygpa.gam.port_mis.service.GamFcltyUseSttusInqireService;
import egovframework.rte.ygpa.gam.port_mis.service.GamFcltyUseSttusInqireVO;

/**
 * @Class Name : GamFcltyUseSttusInqireController.java
 * @Description : 항만시설사용현황조회(포트미스정보)
 * @Modification Information
 *   수정일          수정자                   수정내용 
  *  -------    --------    ---------------------------
  *  2014.01.14  domh          최초 생성
  *  2014.04.14  lsl          선석별 사용현황 조회처리 -- 기존 파일은 _처리 백업
  *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamFcltyUseSttusInqireController {
	
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
    
    @Resource(name = "gamFcltyUseSttusInqireService")
    private GamFcltyUseSttusInqireService gamFcltyUseSttusInqireService;
	
    
    /**
     * 항만시설납부현황관리 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/port_mis/GamFcltyUseSttusInqire"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/port_mis/gamFcltyUseSttusInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM019"); //항코드 
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/port_mis/GamFcltyUseSttusInqire";
    }
	
	/**
     * 항만시설납부현황관리 목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/port_mis/gamSelectFcltyUseSttusInqireList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectFcltyUseSttusInqireList(GamFcltyUseSttusInqireVO searchVO) throws Exception {

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
		
		//목록
    	totalCnt = gamFcltyUseSttusInqireService.selectFcltyUseSttusInqireListTotCnt(searchVO);
    	List resultList = gamFcltyUseSttusInqireService.selectFcltyUseSttusInqireList(searchVO);
    	
    	//면제금액합계, 할인금액합계, 고지금액합계(Map)list.get(i)
    	GamFcltyUseSttusInqireVO resultSum = gamFcltyUseSttusInqireService.selectFcltyUseSttusInqireSum(searchVO);

    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	
    	map.put("sumBillAmnt", resultSum.getSumBillAmnt());
    	map.put("sumDcAmnt", resultSum.getSumDcAmnt());
    	map.put("sumExmpAmnt", resultSum.getSumExmpAmnt());
    	map.put("dpTotCnt", resultSum.getDpTotCnt());


    	return map;
    }
	
}
