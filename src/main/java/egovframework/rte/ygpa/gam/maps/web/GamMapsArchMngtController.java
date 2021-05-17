/**
 *
 */
package egovframework.rte.ygpa.gam.maps.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeDefaultVO;
import egovframework.rte.ygpa.gam.maps.service.GamMapsArchFcltyInqireService;
import egovframework.rte.ygpa.gam.maps.service.GamMapsAssetCodeMngtService;
import egovframework.rte.ygpa.gam.maps.service.GamMapsFcltyCdMngtService;

/**
 * 건축시설 지도 조회 컨트롤러
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
public class GamMapsArchMngtController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "gamMapsArchFcltyInqireService")
	GamMapsArchFcltyInqireService gamMapsArchFcltyInqireService;

	@RequestMapping(value="/maps/fclty/gamArchFcltyInfo.do")
	public String gamArchFcltyInfo(@RequestParam Map searchVO, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	}
    	else {
    		String auth="";
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

			try {
				Map fcltyCdInfo = gamMapsArchFcltyInqireService.selectFcltySpecInfo(searchVO);
				List fileList = gamMapsArchFcltyInqireService.selectFcltyFileList(searchVO);
				List<String> authorities = EgovUserDetailsHelper.getAuthorities();
				String hasIndoor="";

				for(int i=0; i<authorities.size(); i++) {
					String author = EgovUserDetailsHelper.getAuthorities().get(i);
					// 권한별 조회 메뉴 설정
					if(auth.length()!=0) auth+=",";
					if("role_admin".equalsIgnoreCase(author)) {
						auth="manager";
						break;
					}
					else {
						if("A".equals(loginVo.getMngFcltyCd())) {
							auth="manager";
						}
					}
				}

				if(fcltyCdInfo!=null) {
					if("622".equals(fcltyCdInfo.get("gisAssetsPrtAtCode"))
							&& "AHG".equals(fcltyCdInfo.get("gisAssetsCd"))
							&& "01".equals(fcltyCdInfo.get("gisAssetsSubCd"))
							&& "AE".equals(fcltyCdInfo.get("gisPrtFcltyCd"))
							&& "0001".equals(fcltyCdInfo.get("gisPrtFcltySeq")) ) {
						// 황금물류 센터
						hasIndoor="/gis/innroom/gamGoldenLogisCenter.do";
					}
					if("622".equals(fcltyCdInfo.get("gisAssetsPrtAtCode"))
							&& "AHE".equals(fcltyCdInfo.get("gisAssetsCd"))
							&& "04".equals(fcltyCdInfo.get("gisAssetsSubCd"))
							&& "AE".equals(fcltyCdInfo.get("gisPrtFcltyCd"))
							&& "0001".equals(fcltyCdInfo.get("gisPrtFcltySeq")) ) {
						// 국제물류 센터
						hasIndoor="/gis/innroom/gamNationalLogisCenter.do";
					}
					fcltyCdInfo.put("hasIndoor", hasIndoor);
				}

				model.addAttribute("fcltyCd", fcltyCdInfo);
				model.addAttribute("fileList", fileList);
				model.addAttribute("auth", auth);
				model.addAttribute("resultCode", 0);
			} catch(IOException e) {
				Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
			}
			catch(Exception e) {
				model.addAttribute("resultCode", -1);
	    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.select"));
			}
    	}

    	return "ygpa/gam/maps/GamArchFcltyCdInfo";
    }

}
