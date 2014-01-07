package egovframework.rte.ygpa.gam.main.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author eunsungj
 *
 */
@Controller
public class GamMainController {

    @RequestMapping(value="/gamMain.do")
    String indexMain(ModelMap model) throws Exception {
    	return "/ygpa/gam/main/ygpaMapMain";
    }
}
