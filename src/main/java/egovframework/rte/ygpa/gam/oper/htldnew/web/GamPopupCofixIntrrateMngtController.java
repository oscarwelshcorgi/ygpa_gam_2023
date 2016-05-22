/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.web;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 
 * @author Jongmin
 * @since 2016. 4. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 4. 25.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamPopupCofixIntrrateMngtController {
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

    /**
     * Cofix 이자율관리 팝업화면을 로딩한다.
     * @param vo
     * @param model
     * @return /ygpa/gam/oper/htldnew/GamPopupIntrrateMngt
     */
    @RequestMapping(value="/popup/showCofixIntrrateMngt.do")
    public String showCofixIntrrateMngt(ComDefaultVO searchOpt, ModelMap model) {
    	model.addAttribute("searchOpt", searchOpt);
    	return "/ygpa/gam/oper/htldnew/GamPopupCofixIntrrateMngt";
    }
}
