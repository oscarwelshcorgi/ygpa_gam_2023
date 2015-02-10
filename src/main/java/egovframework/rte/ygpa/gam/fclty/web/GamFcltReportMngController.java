/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltReportMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltReportMngVO;

/**
 * 
 * @author HNJ
 * @since 2015. 2. 6.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 6.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamFcltReportMngController {
	
	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name="gamFcltReportMngService")
    protected GamFcltReportMngService gamFcltReportMngService;
    
    
    
    /**
     * 시설물관리대장인쇄
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/fclty/selectFcltReportMngPrint.do")
	public String selectFcltReportMngPrint(@RequestParam Map<String, Object> fcltReportMngOpt, ModelMap model) throws Exception {

    	Map map = new HashMap();
    	EgovMap result = null;
    	
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "/ygpa/gam/fclty/GamFcltReportMngPrint";
    	}

		ObjectMapper mapper = new ObjectMapper();
		
		GamFcltReportMngVO searchVO;
    	searchVO = mapper.convertValue(fcltReportMngOpt, GamFcltReportMngVO.class);

		//시설물관리대장 기본현황/상세제원 인쇄
    	result = gamFcltReportMngService.selectFcltReportMng(searchVO);
    	
    	//안전점검 및 정밀안전진단계획리스트 인쇄
    	searchVO.setPlanHistSe("P");
    	List resultList = gamFcltReportMngService.selectFcleQcMngList(searchVO);
    	//안전점검 및 정밀안전진단계획총갯수
    	int resultListTotalCount = gamFcltReportMngService.selectFcleQcMngListTotalCount(searchVO);
    	
    	//보수.보강계획 리스트 인쇄
    	List mntnResultList = gamFcltReportMngService.selectFcleMntnRprMngList(searchVO);
    	//보수.보강계획 리스트 총갯수
    	int mntnResultListTotalCount = gamFcltReportMngService.selectFcleMntnRprMngListTotalCount(searchVO);
    	
    	
    	//안전점검 및 정밀안전진단계획리스트 인쇄
    	searchVO.setPlanHistSe("H");
    	List resultHistList = gamFcltReportMngService.selectFcleQcMngList(searchVO);
    	
    	//안전점검 및 정밀안전진단계획총갯수
    	int resultHistListTotalCount = gamFcltReportMngService.selectFcleQcMngListTotalCount(searchVO);
    	
    	//보수.보강계획 리스트 인쇄
    	List mntnResultHistList = gamFcltReportMngService.selectFcleMntnRprMngList(searchVO);
    	
    	//보수.보강계획 리스트 총갯수
    	int mntnResultHistListTotalCount = gamFcltReportMngService.selectFcleMntnRprMngListTotalCount(searchVO);
    	
		
    	
        model.addAttribute("result", result);
        
        model.addAttribute("resultList", resultList);
        model.addAttribute("resultListTotalCount", resultListTotalCount);
        model.addAttribute("mntnResultList", mntnResultList);
        model.addAttribute("mntnResultListTotalCount", mntnResultListTotalCount);
        
        model.addAttribute("resultHistList", resultHistList);
        model.addAttribute("resultHistListTotalCount", resultHistListTotalCount);
        model.addAttribute("mntnResultHistList", mntnResultHistList);
        model.addAttribute("mntnResultHistListTotalCount", mntnResultHistListTotalCount);
        
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");

    	return "ygpa/gam/fclty/GamFcltReportMngPrint";
    }

}
