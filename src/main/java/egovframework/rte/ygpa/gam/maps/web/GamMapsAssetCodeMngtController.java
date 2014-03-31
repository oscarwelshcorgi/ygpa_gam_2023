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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeDefaultVO;
import egovframework.rte.ygpa.gam.maps.service.GamMapsAssetCodeMngtService;

/**
 *
 * @author kok
 * @since 2014. 2. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 3.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamMapsAssetCodeMngtController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "gamMapsAssetCodeMngtService")
	GamMapsAssetCodeMngtService gamMapsAssetCodeMngtService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/maps/selectMapsAssetCodeInfo.do", method=RequestMethod.POST)
	@ResponseBody Map selectBupJungDongCodeList(@RequestParam("assetCode") Map searchVO) throws Exception {
		Map item = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		item.put("resultCode", 1);
    		item.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	}
    	else {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    		searchVO.put("authorities", EgovUserDetailsHelper.getAuthorities());

			String auth="";
			for(int i=0; i<EgovUserDetailsHelper.getAuthorities().size(); i++) {
				String author = EgovUserDetailsHelper.getAuthorities().get(i);
				// 권한별 조회 메뉴 설정
				if(auth.length()!=0) auth+=",";
				if("role_asset_mngt".equalsIgnoreCase(author)) {
					auth+="roleAssetMngt";
					searchVO.put("roleAssetMngt", true);
				}
				if("role_fclty_mngt".equalsIgnoreCase(author)) {
					auth+="roleFcltyMngt";
					searchVO.put("roleFcltyMngt", true);
				}
				if("role_pfuse_mngt".equalsIgnoreCase(author)) {
					auth+="rolePfuseMngt";
					searchVO.put("rolePfuseMngt", true);
				}
				if("role_cntrq_mngt".equalsIgnoreCase(author)) {
					auth+="roleCntrqMngt";
					searchVO.put("roleCntrqMngt", true);
				}
			}

			// 자산코드 정보
			List<Map<String, Object>> resultList = gamMapsAssetCodeMngtService.selectAssetCodeInfoList(searchVO);

			if(resultList.size()==0) {
				item.put("resultCode", 2);
				item.put("resultMsg", egovMessageSource.getMessage("errors.maps.assets.notfound"));
				return item;
			}
			item.put("resultCode", 0);			// return ok
			item.put("auth", auth);
			item.put("resultList", resultList);

			// 자산 임대 정보
			resultList = gamMapsAssetCodeMngtService.selectAssetRentInfoList(searchVO);

			if(resultList.size()>0) {
				item.put("rentList", resultList);
			}
    	}

		return item;
	}

}
