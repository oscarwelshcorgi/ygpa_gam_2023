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


import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairMngVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupRepairMngFileViewService;

/**
 *
 * @author Administrator
 * @since 2015. 2. 26.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 26.		Administrator		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupRepairMngFileViewController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamPopupRepairMngFileViewService")
	private GamPopupRepairMngFileViewService gamPopupRepairMngFileViewService;

	@RequestMapping(value="/popup/showRepairMngFileViewPopup.do")
	String showRepairMngFileViewPopup(GamFcltyRepairMngVO searchOpt, ModelMap model) throws Exception {

		model.addAttribute("searchOpt", searchOpt);

		return "/ygpa/gam/popup/GamPopupRepairMngFileView";

	}

	@RequestMapping(value="/popup/gamSelectRepairMngFileViewPk.do")
	@ResponseBody Map<String, Object> gamSelectRepairMngFileViewPk(GamFcltyRepairMngVO gamFcltyRepairMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamPopupRepairMngFileViewService.selectRepairMngFileViewPk(gamFcltyRepairMngVO);

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

	@RequestMapping(value="/popup/gamUpdateRepairMngFileView.do")
	@ResponseBody Map<String, Object> gamUpdateRepairMngFileView(GamFcltyRepairMngVO gamFcltyRepairMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltyRepairMngVO.setUpdUsr((String)user.getId());

			gamPopupRepairMngFileViewService.updateRepairMngFileView(gamFcltyRepairMngVO);
			//하자내용과 비고 자동입력부분 일단 제외시키기로 했음 -- 김종민 차장 수정 2015년 10월 16일
			//gamPopupRepairMngFileViewService.updateFcltyRepairMngContentsRm(gamFcltyRepairMngVO);

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

}
