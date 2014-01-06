package egovframework.com.ygpa.cmm.miplatform;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

@Controller
public class YGMiPlatformController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(YGMiPlatformController.class);

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
      
    private String LOGINURL = "/uat/uia/YGLoginUsr.do";

    
    /**
     * 마이플랫폼 실행 화면 이동
     * @param commandMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/form_miplatform.do")
    public ModelAndView formMiPlatformView(Map commandMap)
                throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("formMiPlatformView(Map) - start"); //$NON-NLS-1$
        }

            Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
            String returnurl = "";
            if(isAuthenticated){
                returnurl = "com/ygpa/cmm/form_miplatform";
            }else{
                returnurl = "redirect:" + LOGINURL;
        }
        ModelAndView returnModelAndView = new ModelAndView(returnurl).addAllObjects(commandMap);
        if (logger.isDebugEnabled()) {
            logger.debug("formMiPlatformView(Map) - end"); //$NON-NLS-1$
        }
        return returnModelAndView;
    }
    
    /**
     * 마이플랫폼 설치 파일 체크 화면 이동(초기 화면)
     * @param commandMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/check_miplatform.do")
    public ModelAndView checkMiPlatformView(Map commandMap)
                throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("checkMiPlatformView(Map) - start"); //$NON-NLS-1$
        }

        ModelAndView returnModelAndView = new ModelAndView("com/ygpa/cmm/check_miplatform").addAllObjects(commandMap);
        if (logger.isDebugEnabled()) {
            logger.debug("checkMiPlatformView(Map) - end"); //$NON-NLS-1$
        }
        return returnModelAndView;
    }
    
    /**
     * 마이플랫폼 메인 화면 호출
     * @param commandMap
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/main_miplatform.do")
    public ModelAndView mainMiPlatformView(Map commandMap, HttpServletRequest request)
                throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("mainMiPlatformView(Map, HttpServletRequest) - start"); //$NON-NLS-1$
        }
        
        // 0. MiPlatform ModelAndView 선언
        // MiPlatform Client 전송 Data
        // (PlatformData,VariableList,DatasetList,Dataset 선언
        ModelAndView mav = new ModelAndView("miplatformView");
        // 4. MiPlatform XML Form Response.
        mav.addObject("miplatformForm", new YGMiPlatformForm("/ygpa/main/YGSMain.xml"));
        
//        HttpSession session = request.getSession();
//        mav.addObject("IPLUSSES", session.getId());

        ModelAndView returnModelAndView = mav.addAllObjects(commandMap);

        if (logger.isDebugEnabled()) {
            logger.debug("mainMiPlatformView(Map, HttpServletRequest) - end"); //$NON-NLS-1$
        }
        return returnModelAndView;
    }
        
    /**
     * 마이플랫폼 메세지 화면 호출
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/message_miplatform.do")
    public ModelAndView messageMiPlatformView()
                throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("messageMiPlatformView() - start"); //$NON-NLS-1$
        }

        ModelAndView returnModelAndView = new ModelAndView("miplatformView").addObject("miplatformForm", new YGMiPlatformForm("/ipa/main/YGMsg.xml"));
        if (logger.isDebugEnabled()) {
            logger.debug("messageMiPlatformView() - end"); //$NON-NLS-1$
        }
        return returnModelAndView;
    }
    
    
}
