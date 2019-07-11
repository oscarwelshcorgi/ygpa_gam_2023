package egovframework.rte.ygpa.gam.fclty.web;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderPopupInqireService;

/**
 * @Class Name : GamTrainPortPopupInqireController.java
 * @Description : 철송장 정보현황알림
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 * 수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.06.17  sj          최초 생성
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamFenderPopupInqireController {
	
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
    
    @Resource(name = "gamFenderPopupInqireService")
    private GamFenderPopupInqireService gamFenderPopupInqireService;
	
    
    /**
     * 방충재 정보현황알림 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gamer/htld/gamHtldPopupInqire"; 
     * @throws Exception the exception  
     */
	@RequestMapping(value="/fclty/gamFenderPopupInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		Map resultMap = gamFenderPopupInqireService.selectFenderPopupInqire();
    	
		model.addAttribute("durabilityCount", resultMap.get("durabilityCount"));
		model.addAttribute("badCount", resultMap.get("badCount"));
		
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/fclty/GamFenderPopupInqire"; 
    }
	
}
