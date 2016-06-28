/**
 *
 */
package egovframework.rte.ygpa.gam.oper.gnrl.web;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeSttusByFeeTpVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUsageConfmInqireService;

/**
 *
 * @author Administrator
 * @since 2016. 6. 28.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 28.		Administrator		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamprtFcltyUsageConfmInqireController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "gamPrtFcltyUsageConfmInqireService")
    private GamPrtFcltyUsageConfmInqireService gamPrtFcltyUsageConfmInqireService;

	@RequestMapping(value="/oper/gnrl/gamPrtFcltyUsageConfmInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/oper/gnrl/GamPrtFcltyUsageConfmInqire";
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrl/selectPrtFcltyUageConfmInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map selectPrtFcltyUageConfmInqireList() throws Exception {

		return null;
	}

}
