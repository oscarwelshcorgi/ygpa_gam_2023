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
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngDefaultVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticInfoVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticIssueService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentRntfeeVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 2.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 2.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamHtldRentNticIssueController {
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
    
    @Resource(name="gamHtldRentNticIssueService")
    private GamHtldRentNticIssueService gamHtldRentNticIssueService;
    
    /**
     * 배후단지 고지 화면을 로딩한다.
     * @param windowId
     * @param model
     * @return /ygpa/gam/oper/htldnew/GamHtldRentNticIssue.jsp
     */
    @RequestMapping(value="/oper/htldnew/gamHtldRentNticIssue.do")
    public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/oper/htldnew/GamHtldRentNticIssue";
    }
   
    /**
     * 고지정보를 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htldnew/selectHtldRentNticIssueInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectHtldRentNticIssueInfo(GamHtldRentMngDefaultVO searchVO) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	Map<?, ?> resultMaster = gamHtldRentNticIssueService.selectNticIssueMasterl(searchVO);
    	List<?> resultList = gamHtldRentNticIssueService.selectNticIssueDetailListl(searchVO);
    	
    	map.put("resultCode", 0);
    	map.put("resultMaster", resultMaster);
    	map.put("resultList", resultList);
    	
    	return map;
	}
	
	/**
	 * 고지 실행
	 * @param Map
	 * @return Map
	 * @throws Exception
	 */
	@RequestMapping(value="/oper/htldnew/execNticIssue.do")
	public @ResponseBody Map<String, Object> execNticIssue(@RequestParam Map<String, Object> nticData) throws Exception {
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
    	
    	GamHtldRentNticInfoVO nticInfo = null;
    	List<GamHtldRentRntfeeVO> rntfeeList = null;
    	
    	//데이터 변환
    	if(nticData.containsKey("nticInfo")) {
    		nticInfo = mapper.readValue((String)nticData.get("nticInfo"), GamHtldRentNticInfoVO.class);
    	}
    	
    	if(nticData.containsKey("rntfeeList")) {
    		rntfeeList = mapper.readValue((String)nticData.get("rntfeeList"), TypeFactory.defaultInstance().constructCollectionType(List.class, GamHtldRentRntfeeVO.class));
    	}
    	
    	try {
    		
    		nticData = null;
    		
        	if(nticData != null){
        		gamHtldRentNticIssueService.execNticIssue(nticInfo, rntfeeList, loginVO);
        	}
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("gam.asset.proc")); 		
    	} catch (IOException i) {
    		map.put("resultCode", 1);
     		map.put("resultMsg", egovMessageSource.getMessage("fail.nticIssue.msg"));
    	} catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.nticIssue.msg"));    		
    	}
    	return map;
	}
}
