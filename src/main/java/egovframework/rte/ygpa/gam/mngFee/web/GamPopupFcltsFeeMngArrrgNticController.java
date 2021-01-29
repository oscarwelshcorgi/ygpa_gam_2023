/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.mngFee.service.GamPopupFcltsFeeMngArrrgNticService;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 25.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupFcltsFeeMngArrrgNticController {

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


	@Resource(name = "gamPopupFcltsFeeMngArrrgNticService")
	private GamPopupFcltsFeeMngArrrgNticService gamPopupFcltsFeeMngArrrgNticService;

	@RequestMapping(value="/mngFee/showFcltsFeeMngArrrgNticPopup.do")
	String showFcltsFeeMngArrrgNticPopup(Map<String, Object> searchOpt, ModelMap model) throws Exception {

		model.addAttribute("searchOpt", searchOpt);
		return "/ygpa/gam/mngFee/GamPopupFcltsFeeMngArrrgNtic";

	}

	@RequestMapping(value="/mngFee/gamPopupCalcDlyBillAmnt.do")
	@ResponseBody Map<String, Object> gamPopupCalcDlyBillAmnt(@RequestParam Map<String, Object> calcVo)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamPopupFcltsFeeMngArrrgNticService.calcDlyBillAmnt(calcVo);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (IOException i) {
			
		} catch (Exception e) {

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;
	}

}
