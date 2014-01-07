package egovframework.rte.ygpa.gam.port_mis.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GamInfoInqireController {

	@RequestMapping(value="/port_mis/gamInfo_Inqire.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/port_mis/GamInfo_Inqire";
    }
}
