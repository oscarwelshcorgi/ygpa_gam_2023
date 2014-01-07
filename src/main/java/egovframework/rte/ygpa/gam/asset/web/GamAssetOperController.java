package egovframework.rte.ygpa.gam.asset.web;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.rte.ygpa.gam.asset.service.GamAssetService;

@Controller
public class GamAssetOperController {

	@Resource(name = "gamAssetService")
    private GamAssetService gamAssetService;
	
	@RequestMapping(value="/asset/gamAssetOper.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/asset/GamAssetOper";
    }
	
}
