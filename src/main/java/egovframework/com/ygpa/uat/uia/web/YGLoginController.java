package egovframework.com.ygpa.uat.uia.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uat.uia.service.EgovLoginService;

@Controller
public class YGLoginController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(YGLoginController.class);

    /** EgovLoginService */
	@Resource(name = "loginService")
    private EgovLoginService loginService;
	
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    private String LOGINJSP = "ygpa/cmm/uat/uia/YGSLoginUsr";
    private String MAINURL = "/gamMain.do";
    
	/**
     * 로그인 화면
     * @param loginVO
     * @param request
     * @param response
     * @param model
     * @param commandMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/uat/uia/YGLoginUsr.do")
    public String loginUsrView(@ModelAttribute("loginVO") LoginVO loginVO,
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response,
            ModelMap model,
            Map commandMap) 
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("loginUsrView(LoginVO, HttpServletRequest, HttpServletResponse, ModelMap) - start"); //$NON-NLS-1$
            logger.debug("urlpath" + session.getAttribute("urlpath"));
        }
        
        
        model.addAttribute("pageurl", commandMap.get("pageurl"));
        //model.addAttribute(Common.PARAM_NEXTURL, commandMap.get(Common.PARAM_NEXTURL));
//        if ( isLoginIPCIS(session) ) return LOGINJSP_IPCIS;
//        else return LOGINJSP;   
        
        return LOGINJSP;
    }

    /**
	 * 일반(세션) 로그인을 처리한다
	 * @param vo - 아이디, 비밀번호가 담긴 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return result - 로그인결과(세션정보)
	 * @exception Exception
	 */
    @RequestMapping(value="/uat/uia/YGActionLogin.do")
    public String actionLogin(@ModelAttribute("loginVO") LoginVO loginVO, 
    		                   HttpServletRequest request,
    		                   ModelMap model)
            throws Exception {
    	

    	// 1. 일반 로그인 처리
        LoginVO resultVO = loginService.actionLogin(loginVO);
    
		
        if (resultVO != null && resultVO.getId() != null && !resultVO.getId().equals("")) {
        	
        	// 2-1. 로그인 정보를 세션에 저장
//        	request.getSession().setAttribute("loginVO", resultVO);
        	
            return "redirect:/j_spring_security_check?j_username=" + resultVO.getUserSe() + resultVO.getId() + "&j_password=" + resultVO.getUniqId();

//    		return "redirect:/uat/uia/actionMain.do";
      
        } else {
        	
        	model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return LOGINJSP;
        }
    }   
    
    /**
	 * 로그인 후 메인화면으로 들어간다
	 * @param 
	 * @return 로그인 페이지
	 * @exception Exception
	 */
    @RequestMapping(value="/uat/uia/YGActionMain.do")
	public String actionMain(ModelMap model) 
			throws Exception {
    	
    	// 1. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return LOGINJSP;
    	}
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	/*
    	// 2. 메뉴조회
		MenuManageVO menuManageVO = new MenuManageVO();
    	menuManageVO.setTmp_Id(user.getId());
    	menuManageVO.setTmp_UserSe(user.getUserSe());
    	menuManageVO.setTmp_Name(user.getName());
    	menuManageVO.setTmp_Email(user.getEmail());
    	menuManageVO.setTmp_OrgnztId(user.getOrgnztId());
    	menuManageVO.setTmp_UniqId(user.getUniqId());
    	List list_headmenu = menuManageService.selectMainMenuHead(menuManageVO);
		model.addAttribute("list_headmenu", list_headmenu);
    	*/
    	
		// 3. 메인 페이지 이동
		String main_page = Globals.MAIN_PAGE;
		main_page = MAINURL;
		
		logger.debug("Globals.MAIN_PAGE > " +  Globals.MAIN_PAGE);
		logger.debug("main_page > " +  main_page);
		logger.debug("main_page > " +  main_page);
		logger.debug("main_page > " +  main_page);
		logger.debug("main_page > " +  main_page);
		logger.debug("main_page > " +  main_page);
		logger.debug("main_page > " +  main_page);
		logger.debug("main_page > " +  main_page);
		logger.debug("main_page > " +  main_page);
		
		
		if (main_page.startsWith("/")) {
		    return "forward:" + main_page;
		} else {
		    return main_page;
		}
		
		/*
		if (main_page != null && !main_page.equals("")) {
			
			// 3-1. 설정된 메인화면이 있는 경우
			return main_page;
			
		} else {
			
			// 3-2. 설정된 메인화면이 없는 경우
			if (user.getUserSe().equals("USR")) {
	    		return "egovframework/com/EgovMainView";
	    	} else {
	    		return "egovframework/com/EgovMainViewG";
	    	}
		}
		*/
	}
    
    /**
	 * 로그아웃한다.
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/uat/uia/YGActionLogout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) 
			throws Exception {
    	
    	// 1. Security 연동
    	return "redirect:/j_spring_security_logout";
    	
    	
    }
    
}
