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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticDefaultVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldRcivProcService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldRcivProcVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 15.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamPopupHtldRcivProcController {
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

    @Resource(name="gamPopupHtldRcivProcService")
    private GamPopupHtldRcivProcService gamPopupHtldRcivProcService;

    /**
     * 수납처리 화면을 로딩한다.
     * @param vo
     * @param model
     * @return /ygpa/gam/oper/htldnew/GamPopupHtldRcivProc
     * @throws Exception 
     */
    @RequestMapping(value="/popup/showHtldRcivProc.do")
    public String showHtldRcivProc(GamHtldRentNticDefaultVO searchVO, ModelMap model) throws Exception {
    	Map<?, ?> rcivInfo = gamPopupHtldRcivProcService.selectHtldNticDtlsRcivInfo(searchVO);
    	model.addAttribute("rcivInfo", rcivInfo);
    	return "/ygpa/gam/oper/htldnew/GamPopupHtldRcivProc";
    }

	/**
     * 수납정보 수정
     * @param 
     * @return Map
     * @throws Exception
     */
	@RequestMapping(value="/oper/htldnew/updateHtldRcivInfo.do")
	public @ResponseBody Map<String, Object> updateHtldRcivInfo(GamPopupHtldRcivProcVO vo) throws Exception {
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
    		vo.setUpdUsr(loginVO.getId());
    		gamPopupHtldRcivProcService.updateHtldRcivInfo(vo);
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));    		
    	} catch (IOException i) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
    	} catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));    		
    	}
    	
    	return map;
	}

}
