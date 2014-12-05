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
				
		model.addAttribute("loginOrgnztId", loginVO.getOrgnztId());
		model.addAttribute("loginUserId", loginVO.getId());
		model.addAttribute("currentDateStr", EgovDateUtil.formatDate(EgovDateUtil.getToday(), "-"));
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/soc/GamSocExmpMngt";
    }
    
    @RequestMapping(value="/soc/gamSelectSocExmpMngtDetailInquire.do")
	@ResponseBody Map<String, Object> selectSocExmpMngtDetailInquire(@ModelAttribute("gamSocExmpMngtVO") GamSocExmpMngtVO gamSocExmpMngtVO, BindingResult bindingResult) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
		GamSocExmpMngtVO resultVO = gamSocExmpMngtService.selectSocExmpMngtDetailInquire(gamSocExmpMngtVO);
		
		if(resultVO == null) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		} else {
			map.put("resultCode", 0);
		}
		map.put("resultVO", resultVO);
		
    	return map;
    }

    @RequestMapping(value="/soc/selectSocExmpMngtGetNextSocNo.do")
	@ResponseBody Map<String, Object> selectSocExmpMngtGetNextSocNo(@ModelAttribute("gamSocExmpMngtVO") GamSocExmpMngtVO gamSocExmpMngtVO, BindingResult bindingResult) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

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
    
    @RequestMapping(value="/soc/insertSocExmpMngtDetail.do")
	@ResponseBody Map<String, Object> insertSocExmpMngtDetail(@ModelAttribute("gamSocExmpMngtVO") GamSocExmpMngtVO gamSocExmpMngtVO, BindingResult bindingResult) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
    	try {
    		gamSocExmpMngtService.insertSocExmpMngtDetail(gamSocExmpMngtVO);
    		map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
    	}
    	catch(Exception e) {
    		map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
    	}
		
    	return map;
    }
    
    @RequestMapping(value="/soc/updateSocExmpMngtDetail.do")
	@ResponseBody Map<String, Object> updateSocExmpMngtDetail(@ModelAttribute("gamSocExmpMngtVO") GamSocExmpMngtVO gamSocExmpMngtVO, BindingResult bindingResult) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		gamSocExmpMngtService.updateSocExmpMngtDetail(gamSocExmpMngtVO);
    		map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
    	}
		catch(Exception e) {
    		map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}
    	return map;
    }
    
    @RequestMapping(value="/soc/deleteSocExmpMngtDetail.do")
	@ResponseBody Map<String, Object> deleteSocExmpMngtDetail(@ModelAttribute("gamSocExmpMngtVO") GamSocExmpMngtVO gamSocExmpMngtVO, BindingResult bindingResult) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
    	try {
    		gamSocExmpMngtService.deleteSocExmpMngtDetail(gamSocExmpMngtVO);
    		map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
    	}
		catch(Exception e) {
    		map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}
    	
    	return map;
    }
    
}
