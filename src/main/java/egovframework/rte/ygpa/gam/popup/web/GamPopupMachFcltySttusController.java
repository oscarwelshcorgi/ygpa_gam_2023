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
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySttusMngVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupMachFcltySttusService;

/**
 *
 * @author ACEWOLF
 * @since 2015. 3. 21.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 21.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupMachFcltySttusController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamPopupMachFcltySttusService")
	private GamPopupMachFcltySttusService gamPopupMachFcltySttusService;

	@RequestMapping(value="/popup/showMachFcltySttusPopup.do")
	String showMachFcltySttusPopup(GamMachFcltySttusMngVO searchOpt, ModelMap model) throws Exception {

		model.addAttribute("searchOpt", searchOpt);

		return "/ygpa/gam/popup/GamPopupMachFcltySttus";

	}

	@RequestMapping(value="/popup/gamSelectMachFcltySttusPk.do")
	@ResponseBody Map<String, Object> gamSelectMachFcltySttusPk(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamPopupMachFcltySttusService.selectMachFcltySttusPk(gamMachFcltySttusMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {
			//e.printStackTrace();
			//2020.04.24 보안검사 후속조치
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@RequestMapping(value="/popup/gamInsertMachFcltySttus.do")
	@ResponseBody Map<String, Object> gamInsertMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamMachFcltySttusMngVO.setRegUsr((String)user.getId());
			gamPopupMachFcltySttusService.insertMachFcltySttus(gamMachFcltySttusMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			//e.printStackTrace();
			//2020.04.24 보안검사 후속조치
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/popup/gamUpdateMachFcltySttus.do")
	@ResponseBody Map<String, Object> gamUpdateMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamMachFcltySttusMngVO.setUpdUsr((String)user.getId());
			gamPopupMachFcltySttusService.updateMachFcltySttus(gamMachFcltySttusMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			//e.printStackTrace();
			//2020.04.24 보안검사 후속조치
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/popup/gamDeleteMachFcltySttus.do")
	@ResponseBody Map<String, Object> gamDeleteMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamPopupMachFcltySttusService.deleteMachFcltySttus(gamMachFcltySttusMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			//e.printStackTrace();
			//2020.04.24 보안검사 후속조치
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

}
