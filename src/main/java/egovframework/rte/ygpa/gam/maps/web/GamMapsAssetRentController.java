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

	@RequestMapping(value="/maps/assets/gamAssetRentInfo.do")
	public String gamAssetCdInfo(@RequestParam Map searchVO, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	}
    	else {
    		String auth="";
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    		String quayCd=(String)searchVO.get("QUAY_CD");

			for(int i=0; i<EgovUserDetailsHelper.getAuthorities().size(); i++) {
				String author = EgovUserDetailsHelper.getAuthorities().get(i);
				// 권한별 조회 메뉴 설정
				if(auth.length()!=0) auth+=",";
				if("role_admin".equalsIgnoreCase(author)) {
					auth="manager";
					break;
				}
				else
				if(quayCd=="P" && "role_asset_mngt".equalsIgnoreCase(author)) {
					auth="manager";
					break;
				}
				else
				if(quayCd=="G" && "role_pfuse_mngt".equalsIgnoreCase(author)) {
					auth="manager";
					break;
				}
				else
				if(quayCd=="H" && "role_htld_mngt".equalsIgnoreCase(author)) {
					auth="manager";
					break;
				}
				else
				if(quayCd=="E" && "role_cmmncntr_mngt".equalsIgnoreCase(author)) {
					auth="manager";
					break;
				}
				else
				if(quayCd=="C" && "role_cntrq_mngt".equalsIgnoreCase(author)) {
					auth="manager";
					break;
				}
				else
				if(quayCd=="M" && "role_mcentr_mngt".equalsIgnoreCase(author)) {
					auth="manager";
					break;
				}
			}

			try {
				Map assetsRent = gamMapsAssetCodeMngtService.selectMapsAssetsCodeUseInfo(searchVO);
				Map searchAssetsCd = new HashMap();
				searchAssetsCd.put("gisAssetsPrtAtCode", assetsRent.get("gisAssetsPrtAtCode"));
				searchAssetsCd.put("gisAssetsCd", assetsRent.get("gisAssetsCd"));
				searchAssetsCd.put("gisAssetsSubCd", assetsRent.get("gisAssetsSubCd"));

				Map assetCodeInfo = gamMapsAssetCodeMngtService.selectMapsAssetsCodeInfo(searchAssetsCd);

				model.addAttribute("assetCd", assetCodeInfo);
				model.addAttribute("assetRent", assetsRent);

				model.addAttribute("auth", auth);
				model.addAttribute("resultCode", 0);
			}
			catch(Exception e) {
				model.addAttribute("resultCode", -1);
	    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.select"));
			}
    	}

    	return "ygpa/gam/maps/GamAssetRentInfo";
    }



}
