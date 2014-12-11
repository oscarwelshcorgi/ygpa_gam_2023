/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 31.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 31.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamPopupCtrtChangeMngtController {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/popup/showCtrtChangeMngt.do")
    String showCtrtChangeMngt(@RequestParam Map ctrtChangeList, ModelMap model) throws Exception {
		model.addAttribute("ctrtChangeList", ctrtChangeList);
    	return "/ygpa/gam/popup/GamPopupCtrtChangeMngt";
    }
}
