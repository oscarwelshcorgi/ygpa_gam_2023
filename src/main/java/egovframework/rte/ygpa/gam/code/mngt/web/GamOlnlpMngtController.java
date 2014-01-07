package egovframework.rte.ygpa.gam.code.mngt.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GamOlnlpMngtController {

	@RequestMapping(value="/code/mngt/gamOlnlpMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/code/mngt/GamOlnlpMngt";
    }
}
