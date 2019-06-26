/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderDurabilityService;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionPrintVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderDurabilityVO;

/**
*
* @author LFIT
* @since 2019. 6. 19.
* @version 1.0
* @see
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일 		 수정자		 수정내용
*  -------		--------	---------------------------
*  2019.6.19.		LFIT		최초 생성
*
* </pre>
*/

@Controller
public class GamFenderDurabilityController {

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamFenderDurabilityService")
	private GamFenderDurabilityService gamFenderDurabilityService;

	@RequestMapping(value="/fclty/gamFenderDurability.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/fclty/GamFenderDurability";

	}


	@RequestMapping(value="/fclty/gamFenderDurabilityList.do")
	@ResponseBody Map<String, Object> gamFenderDurabilityList(GamFenderDurabilityVO searchVO) throws Exception {

		Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회

		/** List Data */
		List mntnRprObjFcltsFList = gamFenderDurabilityService.selectFenderDurabilityList(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", mntnRprObjFcltsFList);

    	return map;

	}

	
	/**
	 * 방충재 정기점검 출력
	 * @param searchOpt
	 * @return String
	 * @throws Exception
	 */	

/*    @RequestMapping(value="/fclty/fenderInspectionPrint.do")
    public String gamFenderInspectionPrint(GamFenderInspectionPrintVO printVo, ModelMap model) throws Exception {
    	Map map = new HashMap();

		ObjectMapper mapper = new ObjectMapper();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "ygpa/gam/code/GamAssetCodeListPrint";
    	}
//    	fenderMng = mapper.convertValue(searchOpt, GamFenderMngGroupVO.class);
//    	searchVO.setsFcltsMngGroupNo(fenderMng.getFcltsMngGroupNo());
    	
    	String fcltsGbn = printVo.getFcltsGbn();
    	
    	if(fcltsGbn=="1") {
    		printVo.setFcltsGbnNm("1종");
    	}else if(fcltsGbn=="2") {
    		printVo.setFcltsGbnNm("2종");
    	}else if(fcltsGbn=="3") {
    		printVo.setFcltsGbnNm("1종/2종");
    	}else if(fcltsGbn=="9") {
    		printVo.setFcltsGbnNm("기타");
    	}else {
    		printVo.setFcltsGbnNm("기타");
    	}
    	
    	model.addAttribute("resultCode", 0);	// return ok
    	model.addAttribute("printVo", printVo);

    	return "ygpa/gam/fclty/GamFenderInspectionPrint";
    }*/
    
	
	
	
	
}

