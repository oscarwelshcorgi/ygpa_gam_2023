package egovframework.rte.ygpa.gam.oper.gnrltest.web;

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
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetPopupInqireService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetPopupInqireVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyPopupInqireService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyPopupInqireVO;

/**
 * @Class Name : GamAssetSttusInqireController.java
 * @Description : 자산정보현황알림
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamTestPrtFcltyPopupInqireController {

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

    @Resource(name = "gamTestPrtFcltyPopupInqireService")
    private GamTestPrtFcltyPopupInqireService gamTestPrtFcltyPopupInqireService;


    /**
     * 자산정보현황알림 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/rent/GamAssetPopupInqire"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/gnrltest/gamTestPrtFcltyPopupInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		GamTestPrtFcltyPopupInqireVO vo = new GamTestPrtFcltyPopupInqireVO();
		vo.setsDeptcd(user.getOrgnztId());

		GamTestPrtFcltyPopupInqireVO resultVo = gamTestPrtFcltyPopupInqireService.selectPrtFcltyPopupInqire(vo);

		model.addAttribute("resultVo", resultVo);

		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/oper/gnrltest/GamTestPrtFcltyPopupInqire";
    }

}
