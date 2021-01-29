/**
 *
 */
package egovframework.rte.ygpa.gam.system.mngt.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentDefaultVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService;

/**
 *
 * @author EUNSUNGJ
 * @since 2015. 6. 18.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 6. 18.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamSystemMngtController {
	@Resource(name="gamHtldRentMngtService")
	private GamHtldRentMngtService gamHtldRentMngtService;

	@RequestMapping(value="/system/mngt/gamSystemMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/system/mngt/GamSystemMngt";
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/system/mngt/createHtldRentMngtFirst.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldRentMngtList(GamHtldRentDefaultVO searchVO) throws Exception {
		Map map = new HashMap();
		try {
			gamHtldRentMngtService.createHtldRentMngtFirst();
	    	map.put("resultCode", 0);	// return ok
	    	map.put("resultMessage", "생성이 완료 된었습니다.");
		}
		catch(Exception e) {
	    	map.put("resultCode", -1);	// return error
	    	map.put("resultMessage", "생성을 실패 했습니다.");
		}

    	return map;
	}

}
