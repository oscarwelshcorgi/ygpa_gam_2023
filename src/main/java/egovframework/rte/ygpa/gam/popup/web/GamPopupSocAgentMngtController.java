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
 * @author HNJ
 * @since 2014. 9. 30.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 30.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamPopupSocAgentMngtController {
	
	
	
	/**
     * 항만공사참여업체 추가편집 팝업
     *
     * 항만공사참여업체 팝업
     */
	@RequestMapping(value="/popup/showSocAgent.do")
    String showSocAgent(@RequestParam Map socAgentList, ModelMap model) throws Exception {

		model.addAttribute("fcltyinfo9List", socAgentList);
    	return "/ygpa/gam/popup/GamPopupSocAgentMngt";
    }

}
