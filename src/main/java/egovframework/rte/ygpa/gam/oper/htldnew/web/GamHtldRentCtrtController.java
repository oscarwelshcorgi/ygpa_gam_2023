/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentCtrtDetailVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentCtrtService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentCtrtVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngDefaultVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 4. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 4. 25.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamHtldRentCtrtController {
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

    /** gamHtldRentCtrtService */
    @Resource(name="gamHtldRentCtrtService")
    private GamHtldRentCtrtService gamHtldRentCtrtService;
    
    /**
     * 배후단지 임대계약 처리 화면을 로딩한다.
     * @param windowId
     * @param model
     * @return /ygpa/gam/oper/htldnew/GamHtldRentCtrt
     */
    @RequestMapping(value="/oper/htldnew/gamHtldRentCtrt.do")
    public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/oper/htldnew/GamHtldRentCtrt";
    }
    
    /**
     * 배후단지임대계약을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htldnew/selectHtldRentCtrt.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectHtldRentCtrt(GamHtldRentCtrtVO searchVO) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	Map<?, ?> resultMaster = gamHtldRentCtrtService.selectHtldRentCtrt(searchVO);
    	List<?> resultList = gamHtldRentCtrtService.selectHtldRentCtrtDetailList(searchVO);
    	
    	map.put("resultCode", 0);
    	map.put("resultMaster", resultMaster);
    	map.put("resultList", resultList);
    	
    	return map;
	}
    
	/**
     * 배후단지 임대계약 등록
     * @param Map
     * @return Map
     * @throws Exception
     */
	@RequestMapping(value="/oper/htldnew/insertHtldRentCtrt.do")
	public @ResponseBody Map<String, Object> insertHtldRentCtrt(@RequestParam Map<String, Object> contractData) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		ObjectMapper mapper = new ObjectMapper();
		
		//사용자 인증 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}    	
		
    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	GamHtldRentCtrtVO rentData = null;
    	List<GamHtldRentCtrtDetailVO> rentDetailList = null;
    	
    	//데이터 변환
    	if(contractData.containsKey("rentData")) {
    		rentData = mapper.readValue((String)contractData.get("rentData"), GamHtldRentCtrtVO.class);
    	}
    	
    	if(contractData.containsKey("rentDetailList")) {
    		rentDetailList = mapper.readValue((String)contractData.get("rentDetailList"), TypeFactory.defaultInstance().constructCollectionType(List.class, GamHtldRentCtrtDetailVO.class));
    	}
    	
    	try {
    		gamHtldRentCtrtService.insertHtldRentCtrt(rentData, rentDetailList, loginVO.getId());
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));    		
    	} catch (IOException i) {
    		map.put("resultCode", 1);
     		map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));   
    	}
    	catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));    		
    	}
    	
    	return map;
	}

	/**
     * 배후단지 임대계약 수정
     * @param Map
     * @return Map
     * @throws Exception
     */
	@RequestMapping(value="/oper/htldnew/updateHtldRentCtrt.do")
	public @ResponseBody Map<String, Object> updateHtldRentCtrt(@RequestParam Map<String, String> contractData) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		ObjectMapper mapper = new ObjectMapper();
				
		//사용자 인증 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}    	
		
    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	GamHtldRentCtrtVO rentData = null;
    	List<GamHtldRentCtrtDetailVO> rentDetailList = null;
    	
    	//데이터 변환
    	if(contractData.containsKey("rentData")) {
    		rentData = mapper.readValue((String)contractData.get("rentData"), GamHtldRentCtrtVO.class);
    	}
    	
    	if(contractData.containsKey("rentDetailList")) {
    		rentDetailList = mapper.readValue((String)contractData.get("rentDetailList"), TypeFactory.defaultInstance().constructCollectionType(List.class, GamHtldRentCtrtDetailVO.class));
    	}
 	
    	try {
    		gamHtldRentCtrtService.updateHtldRentCtrt(rentData, rentDetailList, loginVO.getId());
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));    		
    	} catch (IOException i) {
    		map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));  
    	}
    	catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));    		
    	}
    	
    	return map;
	}
	

	/**
     * 배후단지 임대계약 해지
     * @param Map
     * @return Map
     * @throws Exception
     */
	@RequestMapping(value="/oper/htldnew/terminateHtldRentCtrt.do")
	public @ResponseBody Map<String, Object> terminateHtldRentCtrt(GamHtldRentCtrtVO terminateRentData) throws Exception {
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
    		gamHtldRentCtrtService.terminateHtldRentCtrt(terminateRentData, loginVO.getId());
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.termnate"));    		
    	} catch (IOException i) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
    	}
    	catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.termnate"));    		
    	}
    	
    	return map;
	}

	/**
     * 배후단지 임대계약 해지
     * @param Map
     * @return Map
     * @throws Exception
     */
	@RequestMapping(value="/oper/htldnew/deleteHtldCtrt.do")
	public @ResponseBody Map<String, Object> deleteHtldCtrt(GamHtldRentMngDefaultVO vo) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
				
		//사용자 인증 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}    	
    	
    	try {
    		gamHtldRentCtrtService.deleteHtldCtrt(vo);
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));    		
    	} catch (IOException i) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
    	}
    	catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", e.getMessage());    		
    	}
    	
    	return map;
	}

}
