package egovframework.rte.ygpa.gam.main.web;

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
public class GamMainController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="gamMainMenuLoadService")
    GamMainMenuLoadService gamMainMenuLoadService;

    protected static final Log LOG = LogFactory.getLog(GamMainController.class);

    private Map findUpperMenu(List mainMenu, Map item) {
    	Map findMenu=null;
		for(int j=0; j<mainMenu.size(); j++) {
			findMenu = (Map)mainMenu.get(j);
			if(item.get("upperMenuId").equals(findMenu.get("menuNo"))) {
				return findMenu;
			}
			else if(findMenu.containsKey("submenu")) {
				findMenu=findUpperMenu((List)findMenu.get("submenu"), item);
				if(findMenu!=null) return findMenu;
			}
		}
		return null;
    }

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

    		List list = gamMainMenuLoadService.selectMenuList(loginVo.getId());
    		List<Map<String, Object>> mainMenu = new ArrayList();
    		Map menu = (Map)list.get(0);
    		Map findMenu;

    		mainMenu.add(menu);
    		for(int i=1; i<list.size(); i++) {
    			menu = (Map)list.get(i);
    			findMenu = findUpperMenu(mainMenu, menu);
    			if(findMenu==null) {
    				LOG.warn("upper menu not found :"+ (String)menu.get("menuNm"));
    				mainMenu.add(menu);
    			}
    			else {
    				List submenu=null;
    				if(findMenu.containsKey("submenu")) submenu=(List)findMenu.get("submenu");
    				else {
    					submenu=new ArrayList();
    					findMenu.put("submenu", submenu);
    				}
    				submenu.add(menu);
    			}
    		}

    		model.addAttribute("mainMenu", (List)((Map)mainMenu.get(0)).get("submenu"));
    		model.addAttribute("frmwrkMenu", (List)((Map)mainMenu.get(1)).get("submenu"));
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
