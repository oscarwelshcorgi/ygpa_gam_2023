/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.web;

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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentArrrgNticInfoVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentArrrgNticIssueService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticDefaultVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 9.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamHtldRentArrrgNticIssueController {
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

    @Resource(name="gamHtldRentArrrgNticIssueService")
    private GamHtldRentArrrgNticIssueService gamHtldRentArrrgNticIssueService;
    
    /**
     * 배후단지 연체 고지 화면을 로딩한다.
     * @param windowId
     * @param model
     * @return /ygpa/gam/oper/htldnew/GamHtldRentArrrgNticIssue.jsp
     */
    @RequestMapping(value="/oper/htldnew/gamHtldRentArrrgNticIssue.do")
    public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/oper/htldnew/GamHtldRentArrrgNticIssue";
    }
      
    /**
     * 연체고지정보를 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htldnew/selectHtldRentArrrgNticIssueInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectHtldRentNticIssueInfo(GamHtldRentNticDefaultVO searchVO) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	Map<?, ?> resultMaster = gamHtldRentArrrgNticIssueService.selectNticIssueMasterl(searchVO);
    	List<?> resultList = gamHtldRentArrrgNticIssueService.selectNticIssueDetailListl(searchVO);
    	Map<?, ?> arrrgDetail = gamHtldRentArrrgNticIssueService.selectNticArrrgDetaill(searchVO);
    	
    	map.put("resultCode", 0);
    	map.put("resultMaster", resultMaster);
    	map.put("resultList", resultList);
    	map.put("arrrgDetail", arrrgDetail);
    	
    	return map;
	}

	/**
     * 연체고지 실행
     * @param Map
     * @return Map
     * @throws Exception
     */
	@RequestMapping(value="/oper/htldnew/execArrrgNticIssue.do")
	public @ResponseBody Map<String, Object> execArrrgNticIssue(GamHtldRentArrrgNticInfoVO arrrgVO) throws Exception {
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
    		arrrgVO.setRegUsr(loginVO.getId());
    		arrrgVO.setUpdUsr(loginVO.getId());
    		arrrgVO.setUserName(loginVO.getName());
    		arrrgVO.setDeptCd(loginVO.getDeptCd());
    		arrrgVO.setEmplNo(loginVO.getId());
    		
    		gamHtldRentArrrgNticIssueService.execArrrgNticIssue(arrrgVO);
    		
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));    		
    	} catch(Exception e) {
    		map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));    		
    	}
    	
    	return map;
	}

}
