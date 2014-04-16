/**
 *
 */
package egovframework.rte.ygpa.gam.maps.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.maps.service.GamMapsFcltyMngtService;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 4. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 4. 15.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamMapsFcltyMngtController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	@Resource(name = "gamMapsFcltyMngtService")
	protected GamMapsFcltyMngtService gamMapsFcltyMngtService;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	/**
	 * 시설 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/maps/fclty/gamFcltyList.do")
	@ResponseBody Map<String, Object> gamFcltyList(@RequestParam Map searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		/** List Data */
		List fcltyList = gamMapsFcltyMngtService.selectMapsFcltyMngtList(searchVO);


		map.put("resultCode", 0);			// return ok
    	map.put("resultList", fcltyList);
    	map.put("searchOption", searchVO);

    	return map;
    }


	@RequestMapping(value="/maps/fclty/gamFcltyInfo.do")
	@ResponseBody Map<String, Object> gamFcltyInfo(@RequestParam Map searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		/** List Data */
		Map fcltyList = gamMapsFcltyMngtService.selectMapsFcltyMngtInfo(searchVO);


		map.put("resultCode", 0);			// return ok
    	map.put("result", fcltyList);
    	map.put("searchOption", searchVO);

    	return map;
    }

}