/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldAreaAssessService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldAreaAssessVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 29.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamPopupHtldAreaAssessController {
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

    @Resource(name="gamPopupHtldAreaAssessService")
    private GamPopupHtldAreaAssessService gamPopupHtldAreaAssessService;
    
    /**
     * 지적평가 화면을 로딩한다.
     * @param vo
     * @param model
     * @return /ygpa/gam/oper/htldnew/GamPopupHtldAreaAssess
     */
    @RequestMapping(value="/popup/showHtldAreaAssess.do")
    public String showHtldAreaAssess(@RequestParam Map<?, ?> params, ModelMap model) {
    	model.addAttribute("params", params);
    	return "/ygpa/gam/oper/htldnew/GamPopupHtldAreaAssess";
    }
    
    /**
     * 배후단지임대계약상세(지적평가)를 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htldnew/selectHtldAreaAssessDetail.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectHtldAreaAssessDetail(GamPopupHtldAreaAssessVO searchVO) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	Map<?, ?> resultDetail = gamPopupHtldAreaAssessService.selectHtldAreaAssessDetail(searchVO);
    	
    	map.put("resultCode", 0);
    	map.put("resultDetail", resultDetail);
    	
    	return map;
	}

	/**
     * 지적정산분 등록
     * @param Map
     * @return Map
     * @throws Exception
     */
	@RequestMapping(value="/oper/htldnew/insertAreaAssess.do")
	public @ResponseBody Map<String, Object> insertAreaAssess(GamPopupHtldAreaAssessVO insertVO) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
				
		//사용자 인증 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}    	
		
    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	try {
    		insertVO.setRegUsr(loginVO.getId());
    		gamPopupHtldAreaAssessService.insertAreaAssess(insertVO);
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));    		
    	} catch (IOException i) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
    	}
    	catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));    		
    	}
    	
    	return map;
	}

	/**
     * 지적정산분 수정
     * @param Map
     * @return Map
     * @throws Exception
     */
	@RequestMapping(value="/oper/htldnew/updateAreaAssess.do")
	public @ResponseBody Map<String, Object> updateAreaAssess(GamPopupHtldAreaAssessVO insertVO) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
				
		//사용자 인증 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}    	
		
    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	try {
    		insertVO.setUpdUsr(loginVO.getId());
    		gamPopupHtldAreaAssessService.updateAreaAssess(insertVO);
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));    		
    	} catch (IOException i) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
    	}
    	catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));    		
    	}
    	
    	return map;
	}

	/**
     * 지적정산분 수정
     * @param Map
     * @return Map
     * @throws Exception
     */
	@RequestMapping(value="/oper/htldnew/deleteAreaAssess.do")
	public @ResponseBody Map<String, Object> deleteAreaAssess(GamPopupHtldAreaAssessVO insertVO) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
				
		//사용자 인증 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}    	
		
    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	try {
    		insertVO.setUpdUsr(loginVO.getId());
    		gamPopupHtldAreaAssessService.deleteAreaAssess(insertVO);
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));    		
    	} catch (IOException i) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
    	} catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));    		
    	}
    	
    	return map;
	}
	
}
