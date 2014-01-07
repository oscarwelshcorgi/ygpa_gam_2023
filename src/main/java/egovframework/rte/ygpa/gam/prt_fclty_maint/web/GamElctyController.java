package egovframework.rte.ygpa.gam.prt_fclty_maint.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GamElctyController {

	@RequestMapping(value="/prt_fclty_maint/gamElcty.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/prt_fclty_maint/GamElcty";
    }
}
