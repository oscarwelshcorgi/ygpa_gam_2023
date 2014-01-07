package egovframework.rte.ygpa.gam.cmmn.itgrn.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GamDtaLnkPortMisController {

	@RequestMapping(value="/cmmn/itgrn/gamDtaLnkPortMis.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/cmmn/itgrn/GamDtaLnkPortMis";
    }
}
