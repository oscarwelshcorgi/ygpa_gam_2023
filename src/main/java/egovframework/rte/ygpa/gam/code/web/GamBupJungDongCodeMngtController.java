/**
 *
 */
package egovframework.rte.ygpa.gam.code.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeDefaultVO;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeMngtService;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeVO;

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
public class GamBupJungDongCodeMngtController {

	@Resource(name = "gamBupJungDongCodeMngtService")
	GamBupJungDongCodeMngtService gamBupJungDongCodeMngtService;

	/**
	 * 법정동 코드 조회 화면 호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/code/gamBupJungDongCodeMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/code/GamBupJungDongMngt";
    }

    @RequestMapping(value="/code/selectBupJungDongCodeList.do")
	@ResponseBody Map<String, Object> selectBupJungDongCodeList (GamBupJungDongCodeDefaultVO searchVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

        List<?> bupjungdongCodeList = gamBupJungDongCodeMngtService.selectBupJungDongCodeList(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", bupjungdongCodeList.size());
    	map.put("resultList", bupjungdongCodeList);
    	map.put("searchOption", searchVO);

    	return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/cmmn/selectBupJungDongCodeNm.do", method=RequestMethod.POST)
	@ResponseBody Map selectBupJungDongCodeList(@RequestParam("cmd") String code) throws Exception {
		// 자산코드
		Map optMap = new HashMap();
		GamBupJungDongCodeDefaultVO searchVO = new GamBupJungDongCodeDefaultVO();
		searchVO.setSearchBupjungdongCd(code);
		List<Map<String, Object>> bupjungdongCodeList = gamBupJungDongCodeMngtService.selectBupJungDongCodeList(searchVO);
		List list = new ArrayList();
		Map item = new HashMap();
		if(bupjungdongCodeList.size()==0) {
			item.put("name", "-");
		}
		else {
			item.put("name", bupjungdongCodeList.get(0).get("bupjungdongNm"));
		}
		list.add(item);
		optMap.put("resultCode", 0);			// return ok
		optMap.put("resultList", list);

		return optMap;
	}

}
