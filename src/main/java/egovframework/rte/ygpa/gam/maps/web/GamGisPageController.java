package egovframework.rte.ygpa.gam.maps.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ygpa.gam.main.service.GamMainMenuLoadService;
import egovframework.rte.ygpa.gam.sample.web.GamAssetMngSampleController;

/**
 * @author eunsungj
 *
 */
@Controller
public class GamGisPageController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    protected static final Log LOG = LogFactory.getLog(GamGisPageController.class);

    @RequestMapping(value="/gis/gamBuildInfoMap.do")
    String indexMain(ModelMap model) throws Exception {
    	//
    	// 메뉴 로딩
    	LOG.debug("##### gamMain access auth : "+EgovUserDetailsHelper.class.toString());

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    		return "ygpa/gam/main/loginError";
    	}
    	else {
    	}

    	return "ygpa/gam/main/gisBuildInfoPage";
    }

    @RequestMapping(value="/gis/innroom/gamMarinCenterInfo.do")
    String gamMarinCenterInfo(ModelMap model) throws Exception {
    	//
    	// 메뉴 로딩
    	LOG.debug("##### gamMain access auth : "+EgovUserDetailsHelper.class.toString());

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    		return "ygpa/gam/main/loginError";
    	}
    	else {
    	}

    	return "ygpa/gam/indoor/GamMarinCenterMngt";
    }

    @RequestMapping(value="/gis/innroom/gamNationalLogisCenter.do")
    String gamNationalLogisCenter(ModelMap model) throws Exception {
    	//
    	// 메뉴 로딩
    	LOG.debug("##### gamMain access auth : "+EgovUserDetailsHelper.class.toString());

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    		return "ygpa/gam/main/loginError";
    	}
    	else {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    		model.addAttribute("updUsr", loginVo.getId());
    	}

    	return "ygpa/gam/indoor/GamNationalLogisCenter";
    }

    @RequestMapping(value="/gis/innroom/gamGoldenLogisCenter.do")
    String gamGoldenLogisCenter(ModelMap model) throws Exception {
    	//
    	// 메뉴 로딩
    	LOG.debug("##### gamMain access auth : "+EgovUserDetailsHelper.class.toString());

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    		return "ygpa/gam/main/loginError";
    	}
    	else {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    		model.addAttribute("updUsr", loginVo.getId());
    	}

    	return "ygpa/gam/indoor/GamGoldenLogisCenter";
    }


}
