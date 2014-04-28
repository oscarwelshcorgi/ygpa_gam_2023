package egovframework.rte.ygpa.gam.main.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ygpa.gam.main.service.GamMainMenuLoadService;

/**
 * @author eunsungj
 *
 */
@Controller
public class GamMainController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="gamMainMenuLoadService")
    GamMainMenuLoadService gamMainMenuLoadService;

    @RequestMapping(value="/main/gamMain.do")
    String indexMain(ModelMap model) throws Exception {
    	//
    	// 메뉴 로딩
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    		return "ygpa/gam/main/loginError";
    	}
    	else {
    		String auth="";
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();



    		model.addAttribute("mainMenu", gamMainMenuLoadService.selectMenuList(loginVo.getId()));
//    		model.addAttribute("authorities", EgovUserDetailsHelper.getAuthorities());
    	}

    	return "ygpa/gam/main/ygpaMapMain";
    }

    @RequestMapping(value="/index.do")
    String index(ModelMap model) throws Exception {
    	return "ygpa/gam/main/index";
    }

    @RequestMapping(value="/validator.do")
    String validator(ModelMap model) throws Exception {
    	return "validator";
    }

}
