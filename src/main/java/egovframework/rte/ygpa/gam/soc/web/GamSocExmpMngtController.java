/**
 *
 */
package egovframework.rte.ygpa.gam.soc.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetEvlDtlsInqireVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;
import egovframework.rte.ygpa.gam.soc.service.GamSocExmpMngtService;
import egovframework.rte.ygpa.gam.soc.service.GamSocExmpMngtVO;

/**
 *
 * @author Lee
 * @since 2014. 9. 19.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 19.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamSocExmpMngtController {

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

    @Resource(name="gamPrtFcltyRentMngtService")
    private GamPrtFcltyRentMngtService gamPrtFcltyRentMngtService;

    @Resource(name="gamSocExmpMngtService")
    private GamSocExmpMngtService gamSocExmpMngtService;

    @Resource(name="gamSocCmmUseService")
    private GamSocCmmUseService gamSocCmmUseService;
    
    @RequestMapping(value="/soc/gamSocExmpMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		//청코드 리스트 읽기
		List prtAtCdList = gamSocCmmUseService.selectSocPrtAtCodeDetail();
		
		List yearsList = this.getYears();
		
		model.addAttribute("loginOrgnztId", loginVO.getOrgnztId());
		model.addAttribute("loginUserId", loginVO.getId());
		model.addAttribute("currentDateStr", EgovDateUtil.formatDate(EgovDateUtil.getToday(), "-"));
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);
		model.addAttribute("yearsList", yearsList);

		return "/ygpa/gam/soc/GamSocExmpMngt";
    }
    
	public List getYears(){

		java.util.Calendar cal = java.util.Calendar.getInstance();
		int currentYear = cal.get(cal.YEAR);
		List result = new ArrayList();
   		
   		for (int i = 2000; i <= currentYear; i++) {
   			
   			result.add(String.valueOf(i));
   		}

   		return result;
   	}
    
	public String getInoutName(String feeTp, String inOut) {
		String result = null;
		
		String[] RCodeName = {"외항", "내항"};
		String[] LCodeName = {"외항입항", "외항출항", "내항입항", "내항출항", "항내운입", "항내운출"};
		
		int index = 0;
		if((inOut != null) && (inOut.length() == 1)) 
			index = Integer.parseInt(inOut);
		
		if(index > 0) { 
			if(feeTp.startsWith("L")) {
				result = LCodeName[index - 1];
			} else if(feeTp.startsWith("R")) {
				result = RCodeName[index - 1];
			} else {
				result = "";
			}
		}
		return result;
	}
	
    @RequestMapping(value="/soc/gamSelectSocExmpMngtDetailInquire.do")
	@ResponseBody Map selectSocExmpMngtDetailInquire(
			@ModelAttribute("gamSocExmpMngtVO") GamSocExmpMngtVO gamSocExmpMngtVO,
	     	BindingResult bindingResult)
	        throws Exception {
		Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
		GamSocExmpMngtVO resultVO = gamSocExmpMngtService.selectSocExmpMngtDetailInquire(gamSocExmpMngtVO);
		resultVO.setInOutName(getInoutName(resultVO.getFeeTp(), resultVO.getInOut()));
		
		map.put("resultCode", 0);
		if(resultVO == null) {
			map.put("resultCode", 1);
			map.put("resultMsg", "검색 조건에 맞는 데이터가 없습니다.");
		}
		map.put("resultVO", resultVO);
		
    	return map;
    }

    @RequestMapping(value="/soc/selectSocExmpMngtGetNextSocNo.do")
	@ResponseBody Map selectSocExmpMngtGetNextSocNo(
			@ModelAttribute("gamSocExmpMngtVO") GamSocExmpMngtVO gamSocExmpMngtVO,
	     	BindingResult bindingResult)
	        throws Exception {
		Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
		String nextSocNo = gamSocExmpMngtService.selectSocExmpMngtGetNextSocNo(gamSocExmpMngtVO);
		
		map.put("resultCode", 0);
		map.put("nextSocNo", nextSocNo);
		
    	return map;
    }
    
    @RequestMapping(value="/soc/gamInsertSocExmpMngtDetail.do")
	@ResponseBody Map insertSocExmpMngtDetail(
			@ModelAttribute("gamSocExmpMngtVO") GamSocExmpMngtVO gamSocExmpMngtVO,
	     	BindingResult bindingResult)
	        throws Exception {
		Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
		gamSocExmpMngtService.insertSocExmpMngtDetail(gamSocExmpMngtVO);
		
		map.put("resultCode", 0);
		
    	return map;
    }
    
    @RequestMapping(value="/soc/gamUpdateSocExmpMngtDetail.do")
	@ResponseBody Map updateSocExmpMngtDetail(
			@ModelAttribute("gamSocExmpMngtVO") GamSocExmpMngtVO gamSocExmpMngtVO,
	     	BindingResult bindingResult)
	        throws Exception {
		Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
		gamSocExmpMngtService.updateSocExmpMngtDetail(gamSocExmpMngtVO);
		
		map.put("resultCode", 0);
		
    	return map;
    }
    
    @RequestMapping(value="/soc/gamDeleteSocExmpMngtDetail.do")
	@ResponseBody Map deleteSocExmpMngtDetail(
			@ModelAttribute("gamSocExmpMngtVO") GamSocExmpMngtVO gamSocExmpMngtVO,
	     	BindingResult bindingResult)
	        throws Exception {
		Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
		gamSocExmpMngtService.deleteSocExmpMngtDetail(gamSocExmpMngtVO);
		
		map.put("resultCode", 0);
		
    	return map;
    }
    
}
