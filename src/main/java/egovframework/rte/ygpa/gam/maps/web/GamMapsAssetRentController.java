/**
 *
 */
package egovframework.rte.ygpa.gam.maps.web;

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
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeDefaultVO;
import egovframework.rte.ygpa.gam.maps.service.GamMapsAssetCodeMngtService;

/**
 * 자산임대 지도 관련 조회 컨트롤러
 * @author EUNSUNGJ
 * @since 2015. 01. 07.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 01. 07.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2015 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamMapsAssetRentController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "gamMapsAssetCodeMngtService")
	GamMapsAssetCodeMngtService gamMapsAssetCodeMngtService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/maps/selectMapsAssetRentList.do", method=RequestMethod.POST)
	@ResponseBody Map selectMapsAssetRentList(@RequestParam("assetRent") Map assetRent) throws Exception {
		Map item = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		item.put("resultCode", 1);
    		item.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	}
    	else {
			// 자산상세 정보
			List<Map<String, Object>> resultList=null;

			// 자산 임대 정보
			resultList = gamMapsAssetCodeMngtService.selectAssetRentInfoList(assetRent);

			if(resultList.size()==0) {
				item.put("resultCode", 2);
				item.put("resultMsg", egovMessageSource.getMessage("errors.maps.assets.notfound"));
				return item;
			}
			item.put("resultCode", 0);			// return ok
			item.put("resultList", resultList);
    	}

		return item;
	}

}
