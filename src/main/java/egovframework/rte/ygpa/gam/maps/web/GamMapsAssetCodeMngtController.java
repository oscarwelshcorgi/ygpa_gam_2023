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
import egovframework.rte.ygpa.gam.maps.service.GamMapsAssetCodeMngtService;

/**
 * 자산코드 지도 조회 컨트롤러
 * @author EUNSUNGJ
 * @since 2014. 4. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 4. 17.		EUNSUNGJ		최초 생성
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

/*
	@RequestMapping(value="/maps/assets/gamAssetCdInfo.do")
	@ResponseBody Map<String, Object> gamAssetCdInfo(@RequestParam Map searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		*//** List Data *//*
		Map assetCodeInfo = gamMapsAssetCodeMngtService.selectMapsAssetsCodeInfo(searchVO);


		map.put("resultCode", 0);			// return ok
		map.put("result", assetCodeInfo);
		map.put("searchOption", searchVO);

		return map;
	}
	*/

	@RequestMapping(value="/maps/assets/gamAssetCdInfo.do")
	public String gamAssetCdInfo(@RequestParam Map searchVO, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	}
    	else {
    		String auth="";
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    		searchVO.put("authorities", EgovUserDetailsHelper.getAuthorities());

			for(int i=0; i<EgovUserDetailsHelper.getAuthorities().size(); i++) {
				String author = EgovUserDetailsHelper.getAuthorities().get(i);
				// 권한별 조회 메뉴 설정
				if(auth.length()!=0) auth+=",";
				if("role_admin".equalsIgnoreCase(author)) {
					auth+="roleAdmin";
					auth+=",roleAssetMngt";
					auth+=",roleFcltyMngt";
					auth+=",rolePfuseMngt";
					auth+=",roleCntrqMngt";
					auth+=",roleHtldMngt";
					break;
				}
				else if("role_asset_mngt".equalsIgnoreCase(author)) {
					auth+="roleAssetMngt";
				}
				else if("role_fclty_mngt".equalsIgnoreCase(author)) {
					auth+="roleFcltyMngt";
				}
				else if("role_pfuse_mngt".equalsIgnoreCase(author)) {
					auth+="rolePfuseMngt";
				}
				else if("role_cntrq_mngt".equalsIgnoreCase(author)) {
					auth+="roleCntrqMngt";
				}
				else if("ROLE_HTLD_MNGT".equalsIgnoreCase(author)) {
					auth+="roleHtldMngt";
				}
			}

			try {
				Map assetCodeInfo = gamMapsAssetCodeMngtService.selectMapsAssetsCodeInfo(searchVO);
				String gisAssetsRm = "";
				
				if(assetCodeInfo != null) {
					gisAssetsRm = assetCodeInfo.get("gisAssetsRm")+"";
				}

				if(gisAssetsRm.contains("/")) {
					String[] wharfInfo = gisAssetsRm.split("/");
					for(int i=0; i<3; i++) {
						model.addAttribute("wharfInfo"+i, wharfInfo[i]);
					}
				}

				model.addAttribute("assetCd", assetCodeInfo);

				model.addAttribute("auth", auth);
				if(assetCodeInfo==null) {
					String bjdCode=(String)searchVO.get("bjdCode");
					model.addAttribute("bjdCode", bjdCode);
					String addr = gamMapsAssetCodeMngtService.selectMapsBjdCodeInfo(bjdCode);
					String lnm="", lnmSub="";
					if(bjdCode.length()>=14) {
						lnm=bjdCode.substring(11, 15);
					}
					if(bjdCode.length()>=18) {
						lnmSub=bjdCode.substring(15, 19);
					}
					model.addAttribute("addr", addr);
					model.addAttribute("lnm", lnm);
					model.addAttribute("lnmSub", lnmSub);
				}
				else {
					if(auth.indexOf("roleAssetMngt")>=0 || auth.indexOf("rolePfuseMngt")>=0) {
						List<Map> assetsRentList = (List<Map>) gamMapsAssetCodeMngtService.selectMapsAssetsRentInfo(searchVO);

						if(assetsRentList.size()>0) {
							Map assetsRentInfo = assetsRentList.get(0);

							model.addAttribute("prtAtCode", assetsRentInfo.get("prtAtCode"));
							model.addAttribute("mngYear", assetsRentInfo.get("mngYear"));
							model.addAttribute("mngNo", assetsRentInfo.get("mngNo"));
							model.addAttribute("mngCnt", assetsRentInfo.get("mngCnt"));
						}

						model.addAttribute("assetRent", assetsRentList);
					}
					if(auth.indexOf("roleAssetMngt")>=0 || auth.indexOf("rolePfuseMngt")>=0) {
						model.addAttribute("assetRentSummary", gamMapsAssetCodeMngtService.selectMapsAssetsCodeUseSummary(searchVO));
					}
					if(auth.indexOf("roleHtldMngt")>=0) {
						Map map = gamMapsAssetCodeMngtService.selectMapsHtldRentInfo(searchVO);
						if(map!=null) model.addAttribute("assetHtldInfo", map);
					}
				}
				model.addAttribute("resultCode", 0);
			}
			catch(Exception e) {
				model.addAttribute("resultCode", -1);
	    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.select"));
			}
    	}

    	return "ygpa/gam/maps/GamAssetCdInfo";
    }


	@RequestMapping(value="/maps/assets/gamPrtFcltyCdInfo.do")
	public String gamPrtFcltyCdInfo(@RequestParam Map searchVO, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	}
    	else {
    		String auth="";
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    		searchVO.put("authorities", EgovUserDetailsHelper.getAuthorities());

			for(int i=0; i<EgovUserDetailsHelper.getAuthorities().size(); i++) {
				String author = EgovUserDetailsHelper.getAuthorities().get(i);
				// 권한별 조회 메뉴 설정
				if(auth.length()!=0) auth+=",";
				if("role_admin".equalsIgnoreCase(author)) {
					auth+="roleAdmin";
					break;
				}
				if("role_asset_mngt".equalsIgnoreCase(author)) {
					auth+="roleAssetMngt";
				}
				if("role_fclty_mngt".equalsIgnoreCase(author)) {
					auth+="roleFcltyMngt";
				}
				if("role_pfuse_mngt".equalsIgnoreCase(author)) {
					auth+="rolePfuseMngt";
				}
				if("role_cntrq_mngt".equalsIgnoreCase(author)) {
					auth+="roleCntrqMngt";
				}
			}

			try {
				Map assetCodeInfo = gamMapsAssetCodeMngtService.selectMapsAssetsCodeInfo(searchVO);

				model.addAttribute("assetCd", assetCodeInfo);
				if(auth.indexOf("roleAssetMngt")>0 || auth.indexOf("rolePfuseMngt")>0) {
					model.addAttribute("assetRent", gamMapsAssetCodeMngtService.selectMapsAssetsRentInfo(searchVO));
					model.addAttribute("assetRentSummary", gamMapsAssetCodeMngtService.selectMapsAssetsCodeUseSummary(searchVO));
				}
				model.addAttribute("auth", auth);
				if(assetCodeInfo==null) {
					String bjdCode=(String)searchVO.get("bjdCode");
					model.addAttribute("bjdCode", bjdCode);
					String addr = gamMapsAssetCodeMngtService.selectMapsBjdCodeInfo(bjdCode);
					String lnm="", lnmSub="";
					if(bjdCode.length()>=14) {
						lnm=bjdCode.substring(11, 15);
					}
					if(bjdCode.length()>=18) {
						lnmSub=bjdCode.substring(15, 19);
					}
					model.addAttribute("addr", addr);
					model.addAttribute("lnm", lnm);
					model.addAttribute("lnmSub", lnmSub);
				}
				model.addAttribute("resultCode", 0);
			}
			catch(Exception e) {
				model.addAttribute("resultCode", -1);
	    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.select"));
			}
    	}

    	return "ygpa/gam/maps/GamAssetCdInfo";
    }

}
