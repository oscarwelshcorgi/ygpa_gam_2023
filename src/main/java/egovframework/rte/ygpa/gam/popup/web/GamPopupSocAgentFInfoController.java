/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.web;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.ygpa.gam.popup.service.GamPopupSocAgentFInfoService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupSocAgentFInfoVO;


/**
 * @Class Name : GamPopupSocAgentFInfoController.java
 * @Description : 허가원부선택정보
 * @Modification Information
 * 
 * @author HNJ
 * @since 2014. 9. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 25.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupSocAgentFInfoController {
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@Resource(name = "gamPopupSocAgentFInfoService")
    private GamPopupSocAgentFInfoService gamPopupSocAgentFInfoService;
	
	/**
     * 허가원부정보 팝업화면을 로딩한다.
     *
     * @param searchOpt
     * @param model the model
     * @return "/ygpa/gam/popup/GamPopupSocAgentFInfo"
     * @throws Exception the exception
     */
	@RequestMapping(value="/popup/showSocAgentFInfo.do")
    String showAgentFInfo(GamPopupSocAgentFInfoVO searchOpt, ModelMap model) throws Exception {

		model.addAttribute("searchOpt", searchOpt);
    	return "/ygpa/gam/popup/GamPopupSocAgentFInfo";
    }

}
