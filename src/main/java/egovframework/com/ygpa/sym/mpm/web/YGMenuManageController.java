package egovframework.com.ygpa.sym.mpm.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import egovframework.com.ygpa.sym.mpm.service.YGMenuManageService;
import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class YGMenuManageController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(YGMenuManageController.class);

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "ygMenuManageService")
    private YGMenuManageService ygMenuManageService;

    /**
     * 상단 메뉴 목록 조회
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/ygpa/sym/mpm/YGUserMenuSelect.do")
    public ModelAndView selectUserMenuList(HttpServletRequest request)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("selectUserMenuList() - start"); //$NON-NLS-1$
        } 
        
        if (logger.isDebugEnabled()) {
            HttpSession session = request.getSession();
            
            logger.debug("session.getId()="  + session.getId()); //$NON-NLS-1$
        }  
        
        
        ModelAndView mav = new ModelAndView("miplatformView");
//        YGMiPlatformData iSMiPlatformData = new YGMiPlatformData();
//        
//        try
//        {
//            MenuManageVO menuManageVO = new MenuManageVO();
//            LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//            menuManageVO.setTmpUniqId(user.getUniqId());
//            
//            List list_mainmenuhead = ygMenuManageService.selectMainMenuHead(menuManageVO);
//            if (logger.isDebugEnabled()) {
//                logger.debug("selectMenuManageList() - list_mainmenuhead.size()="  + list_mainmenuhead.size()); 
//            }        
//            iSMiPlatformData.addDataset("ds_output", list_mainmenuhead);
//            iSMiPlatformData.setMiResultMessage("0", "success");
//
//            iSMiPlatformData.addDataset("ds_user", user);
//        } catch ( Exception e ) {
//            logger.error("selectSampleList4Mi(SampleDefaultVO)", e);
//            iSMiPlatformData.setMiResultMessage("-1", e.toString());               
//        }
//        
//        mav.addObject("miplatformData", iSMiPlatformData);
//        if (logger.isDebugEnabled()) {
//            logger.debug("selectMiplatformList(HttpServletRequest, HttpServletResponse, ModelMap) - end"); 
//        }
        return mav;
    }
}
