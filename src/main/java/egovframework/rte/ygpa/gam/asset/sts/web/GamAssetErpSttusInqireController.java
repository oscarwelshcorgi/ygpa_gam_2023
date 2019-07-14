package egovframework.rte.ygpa.gam.asset.sts.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ygpa.gam.asset.sts.service.GamAssetErpSttusInqireService;
import egovframework.rte.ygpa.gam.asset.sts.service.GamSttusInqireDefaultVO;

/**
 * @Class Name : GamAssetSttusInqireController.java
 * @Description : 자산정보현황조회
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamAssetErpSttusInqireController {

	protected Log log = LogFactory.getLog(this.getClass());

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    @Resource(name = "gamAssetErpSttusInqireService")
    private GamAssetErpSttusInqireService gamAssetErpSttusInqireService;


    /**
     * 자산정보현황 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/GamAssetDisUseMngt"
     * @throws Exception the exception
     */
	@RequestMapping(value="/asset/gamAssetErpSttusInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
/*
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();

		codeVo.setCodeId("GAM019"); //항코드
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);*/

//		model.addAttribute("prtAtCdList", prtAtCdList);

		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/asset/sts/GamAssetErpSttusInqire";
    }

	/**
     * 자산정보현황 목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/gamAssetErpSttusInqireList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetErpSttusInqireList(GamSttusInqireDefaultVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}


    	List assetRentList = gamAssetErpSttusInqireService.selectAssetErpSttusInqireList(searchVO);


    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", assetRentList);


    	return map;
    }
}
