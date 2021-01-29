/**
 *
 */
package egovframework.rte.ygpa.gam.oper.htldnew.web;

import java.util.HashMap;
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
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldBizAssessService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldBizAssessVO;

/**
 *
 * @author Jongmin
 * @since 2016. 4. 26.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 4. 26.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupHtldBizAssessController {
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

    @Resource(name="gamPopupHtldBizAssessService")
    private GamPopupHtldBizAssessService gamPopupHtldBizAssessService;

    /**
     * 실적평가 화면을 로딩한다.
     * @param vo
     * @param model
     * @return /ygpa/gam/oper/htldnew/GamPopupHtldBizAssess
     */
    @RequestMapping(value="/popup/showHtldBizAssess.do")
    public String showHtldBizAssess(@RequestParam Map<?, ?> params, ModelMap model) {
    	model.addAttribute("params", params);
    	return "/ygpa/gam/oper/htldnew/GamPopupHtldBizAssess";
    }

    /**
     * 배후단지임대계약상세(실적평가)를 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/htldnew/selectHtldRentBizAssessDetail.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectHtldRentBizAssessDetail(GamPopupHtldBizAssessVO searchVO) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	Map<?, ?> resultDetail = gamPopupHtldBizAssessService.selectHtldRentBizAssessDetail(searchVO);

    	map.put("resultCode", 0);
    	map.put("resultDetail", resultDetail);

    	return map;
	}

	/**
     * 실적평가 등록
     * @param Map
     * @return Map
     * @throws Exception
     */
	@RequestMapping(value="/oper/htldnew/updateBizAssess.do")
	public @ResponseBody Map<String, Object> updateBizAssess(GamPopupHtldBizAssessVO updateVO) throws Exception {
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
    		gamPopupHtldBizAssessService.updateBizAssess(updateVO, loginVO.getId());
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
    	} catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
    	}

    	return map;
	}

	/**
     * 실적평가 삭제
     * @param Map
     * @return Map
     * @throws Exception
     */
	@RequestMapping(value="/oper/htldnew/deleteBizAssess.do")
	public @ResponseBody Map<String, Object> deleteBizAssess(GamPopupHtldBizAssessVO deleteVO) throws Exception {
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
    		gamPopupHtldBizAssessService.deleteBizAssess(deleteVO, loginVO.getId());
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
    	} catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
    	}

    	return map;
	}


}
