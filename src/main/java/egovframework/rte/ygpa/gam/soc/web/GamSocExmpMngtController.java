/**
 *
 */
package egovframework.rte.ygpa.gam.soc.web;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
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
    
    @SuppressWarnings("rawtypes")
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
    
    @RequestMapping(value="/soc/selectSocExmpMngtDetail.do")
	@ResponseBody Map<String, Object> selectSocExmpMngtDetail(@ModelAttribute("gamSocExmpMngtVO") GamSocExmpMngtVO gamSocExmpMngtVO, BindingResult bindingResult) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		EgovMap result = null;
		
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
    	try {
    		result = gamSocExmpMngtService.selectSocExmpMngtDetail(gamSocExmpMngtVO);
			map.put("resultCode", 0);
			map.put("resultVO", result);
    	} catch(NullPointerException n){
    		map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select.NullpointException"));
    	} catch(Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
    	}
    	
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
	@ResponseBody Map<String, Object> insertSocExmpMngtDetail(@RequestParam Map<String, Object> insertMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
    	try {
    		gamSocExmpMngtService.insertSocExmpMngtDetail(insertMap);
    		map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
    	} catch(NullPointerException n){
    		map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert.NullpointException"));
    	}
    	catch(Exception e) {
    		map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
    	}
		
    	return map;
    }
    
    @RequestMapping(value="/soc/updateSocExmpMngtDetail.do")
	@ResponseBody Map<String, Object> updateSocExmpMngtDetail(@RequestParam Map<String, Object> updateMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		gamSocExmpMngtService.updateSocExmpMngtDetail(updateMap);
    		map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
    	} catch(NullPointerException n){
    		map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update.NullpointException"));
    	}
		catch(Exception e) {
    		map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}
    	return map;
    }
    
    @RequestMapping(value="/soc/deleteSocExmpMngtDetail.do")
	@ResponseBody Map<String, Object> deleteSocExmpMngtDetail(@RequestParam Map<String, Object> deleteMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
    	try {
    		gamSocExmpMngtService.deleteSocExmpMngtDetail(deleteMap);
    		map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
    	} catch(NullPointerException n){
    		map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete.NullpointException"));
    	}
		catch(Exception e) {
    		map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}
    	
    	return map;
    }
    
}
