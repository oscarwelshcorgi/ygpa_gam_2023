/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupQcItemCdTreeService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupQcItemCdTreeVO;

/**
 * 
 * @author 김종민
 * @since 2014. 12. 31.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 31.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupQcItemCdTreeController {
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
	
	@Resource(name="gamPopupQcItemCdTreeService")
	GamPopupQcItemCdTreeService gamPopupQcItemCdTreeService;
	
	@RequestMapping(value="/popup/showQcItemCdTreePopup.do")
	String showQcItemCdPopup(GamPopupQcItemCdTreeVO searchOpt, ModelMap model) throws Exception {
		model.addAttribute("searchOpt", searchOpt);
		return "/ygpa/gam/popup/GamPopupQcItemCdTree";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/popup/selectQcItemCdTreeList.do" , method=RequestMethod.POST)
	@ResponseBody Map selectQcItemCdTreeList(GamPopupQcItemCdTreeVO gamQcItemCdTreeVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamPopupQcItemCdTreeService.selectQcItemCdTreeList(gamQcItemCdTreeVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;
	}
}
