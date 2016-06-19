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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngtMainService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngtMainVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentRntfeeVO;

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
public class GamHtldRentMngtMainController {
	
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

    /** gamHtldRentMngtMainService */
    @Resource(name="gamHtldRentMngtMainService")
    private GamHtldRentMngtMainService gamHtldRentMngtMainService;
 
    /**
     * 배후단지 임대관리메인화면을 로딩한다.
     * @param windowId
     * @param model
     * @return /ygpa/gam/oper/htldnew/GamHtldRentMngtMain
     */
    @RequestMapping(value="/oper/htldnew/gamHtldRentMngtMain.do")
    public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/oper/htldnew/GamHtldRentMngtMain";
    }

    /**
     * 배후단지임대상세목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htldnew/selectHtldRentDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldRentDetailList(GamHtldRentMngtMainVO searchVO) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	List resultList = gamHtldRentMngtMainService.selectHtldRentDetailList(searchVO);
    	String cofixIntrrate = gamHtldRentMngtMainService.selectCofixIntrrate(searchVO);
    	
    	map.put("resultCode", 0);
    	map.put("resultList", resultList);
    	map.put("cofixIntrrate", cofixIntrrate);
    	
    	return map;
	}
	
	/**
     * 배후단지 임대료를 저장한다.
     * @param Map
     * @return Map
     * @throws Exception
     */
	@RequestMapping(value="/oper/htldnew/updateHtldRntfee.do")
	public @ResponseBody Map<String, Object> updateHtldRntfee(@RequestParam Map<String, Object> rentFeeData) throws Exception {
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
    	
    	List<GamHtldRentRntfeeVO> feeInsertList = null;
    	List<GamHtldRentRntfeeVO> feeUpdateList = null;
    	    	
    	if(rentFeeData.containsKey("feeInsertList")) {
    		feeInsertList = mapper.readValue((String)rentFeeData.get("feeInsertList"), TypeFactory.defaultInstance().constructCollectionType(List.class, GamHtldRentRntfeeVO.class));
    	}

    	if(rentFeeData.containsKey("feeUpdateList")) {
    		feeUpdateList = mapper.readValue((String)rentFeeData.get("feeUpdateList"), TypeFactory.defaultInstance().constructCollectionType(List.class, GamHtldRentRntfeeVO.class));
    	}
    	
    	try {
    		gamHtldRentMngtMainService.updateHtldRntfee(feeInsertList, feeUpdateList, loginVO.getId());
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));    		
    	} catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));    		
    	}
    	
    	return map;
	}

	
}