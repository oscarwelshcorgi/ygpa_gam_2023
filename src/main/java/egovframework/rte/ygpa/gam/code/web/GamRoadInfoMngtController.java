/**
 *
 */
package egovframework.rte.ygpa.gam.code.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ygpa.gam.code.service.GamRoadInfoMngtService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupRoadInfoMngtVO;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 12.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamRoadInfoMngtController {

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
	@Resource(name = "gamRoadInfoMngtService")
	GamRoadInfoMngtService gamRoadInfoMngtService;
	
	/**
	 * 법정동 코드 조회 화면 호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
/*	@RequestMapping(value="/code/gamRoadInfoMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/code/GamRoadInfoMngt";
    }
*/	
    @RequestMapping(value="/popup/showRoadInfo.do")
    String showRoadInfo(GamPopupRoadInfoMngtVO searchOpt, ModelMap model) throws Exception {
    	model.addAttribute("searchOpt", searchOpt);

    	return "/ygpa/gam/popup/GamPopupRoadInfo";
    }


    @RequestMapping(value="/code/selectRoadInfoList.do")
	@ResponseBody Map<String, Object> selectRoadInfoList (GamPopupRoadInfoMngtVO searchVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	List<?> roadInfoList = gamRoadInfoMngtService.selectRoadInfoList(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", roadInfoList.size());
    	map.put("resultList", roadInfoList);
    	map.put("searchOption", searchVO);

    	return map;
	}


}
