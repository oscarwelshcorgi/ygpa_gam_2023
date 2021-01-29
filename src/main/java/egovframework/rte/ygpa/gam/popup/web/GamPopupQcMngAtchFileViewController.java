/**
 *
 */
package egovframework.rte.ygpa.gam.popup.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamQcMngAtchFileMngVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupQcMngAtchFileViewService;

/**
 *
 * @author ACEWOLF
 * @since 2015. 5. 8.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 5. 8.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupQcMngAtchFileViewController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamPopupQcMngAtchFileViewService")
	private GamPopupQcMngAtchFileViewService gamPopupQcMngAtchFileViewService;

	@RequestMapping(value="/popup/showQcMngAtchFileViewPopup.do")
	String showQcMngAtchFileViewPopup(GamQcMngAtchFileMngVO searchOpt, ModelMap model) throws Exception {

		model.addAttribute("searchOpt", searchOpt);

		return "/ygpa/gam/popup/GamPopupQcMngAtchFileView";

	}

	@RequestMapping(value="/popup/gamSelectQcMngAtchFileViewPk.do")
	@ResponseBody Map<String, Object> gamSelectQcMngAtchFileViewPk(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamPopupQcMngAtchFileViewService.selectQcMngAtchFileViewPk(gamQcMngAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@RequestMapping(value="/popup/gamUpdateQcMngAtchFileView.do")
	@ResponseBody Map<String, Object> gamUpdateQcMngAtchFileView(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamQcMngAtchFileMngVO.setUpdUsr((String)user.getId());

			gamPopupQcMngAtchFileViewService.updateQcMngAtchFileView(gamQcMngAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

}
