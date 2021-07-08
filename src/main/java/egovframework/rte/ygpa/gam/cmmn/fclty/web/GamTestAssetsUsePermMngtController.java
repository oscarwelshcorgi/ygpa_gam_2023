/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamTestAssetsUsePermMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtLevReqestVO;

/**
 * 사용 승낙 처리 모듈
 * @author EUNSUNGJ
 * @since 2014. 3. 14.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 14.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamTestAssetsUsePermMngtController {

    @Resource(name = "gamTestAssetsUsePermMngtService")
    private GamTestAssetsUsePermMngtService gamTestAssetsUsePermMngtService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/cmmn/fclty/gamTestInsertLevreqestAdit.do")
	@ResponseBody Map<String, Object> gamInsertLevreqestAdit(@RequestParam Map<String, Object> levreqestAdit, ModelMap model) throws Exception {
		Map map = new HashMap();

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    		return map;
    	}

    	try {
    		gamTestAssetsUsePermMngtService.insertBillCreateAdit(levreqestAdit);
    	} catch(IOException e){
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
    	}
    	catch(Exception e) {
            map.put("resultCode", -1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));

    	}

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));

    	return map;
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/cmmn/fclty/gamTestDeleteLevreqestAdit.do")
	@ResponseBody Map<String, Object> gamDeleteLevreqestAdit(@RequestParam Map<String, Object> levReqest, ModelMap model) throws Exception {
		Map map = new HashMap();

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    		return map;
    	}

    	try {
    		gamTestAssetsUsePermMngtService.deleteBillAdit(levReqest);
    	} catch(IOException e){
    		map.put("resultCode", -1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete.IOExeption"));
    	}
    	catch(Exception e) {
            map.put("resultCode", -1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));

    	}

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));

    	return map;
    }

}
