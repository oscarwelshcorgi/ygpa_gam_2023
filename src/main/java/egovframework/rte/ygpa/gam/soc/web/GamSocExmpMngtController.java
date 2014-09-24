/**
 *
 */
package egovframework.rte.ygpa.gam.soc.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetEvlDtlsInqireVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocExmpMngtService;
import egovframework.rte.ygpa.gam.soc.service.GamSocExmpMngtVO;

/**
 *
 * @author Lee
 * @since 2014. 9. 19.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 19.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamSocExmpMngtController {

	protected Log log = LogFactory.getLog(this.getClass());

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    @Resource(name = "gamPrtFcltyRentMngtService")
    private GamPrtFcltyRentMngtService gamPrtFcltyRentMngtService;

    @Resource(name = "gamSocExmpMngtService")
    private GamSocExmpMngtService gamSocExmpMngtService;

    @RequestMapping(value="/soc/gamSocExmpMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//공시지가정보
		//GamPrtFcltyRentMngtVO gvo = new GamPrtFcltyRentMngtVO();
//		List olnlpList = gamPrtFcltyRentMngtService.selectOlnlpInfo();

		//코픽스 이자율
		List cofixList = gamPrtFcltyRentMngtService.selectCofixInfo();

		//현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져와서 해당하는 코픽스 이자율 가져오기.
		GamPrtFcltyRentMngtVO cofixVO = new GamPrtFcltyRentMngtVO();
		GamPrtFcltyRentMngtVO cofixResultVO = new GamPrtFcltyRentMngtVO();

		cofixVO.setcYear(EgovDateUtil.getToday().substring(0,6));
		cofixVO = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtBeforeQuarterInfo(cofixVO);

		if( cofixVO != null ) {
			cofixResultVO = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtCofixInfo(cofixVO);

			if( cofixResultVO == null ) {
				cofixResultVO = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtCofixInfoMax(cofixVO);
			}

			if( cofixResultVO != null && cofixResultVO.getBlceStdrIntrrate() != null ) {
				model.addAttribute("blceStdrIntrrate", cofixResultVO.getBlceStdrIntrrate());
			}

			if( cofixResultVO != null && cofixResultVO.getBlceStdrIntrrateShow() != null ) {
				model.addAttribute("blceStdrIntrrateShow", cofixResultVO.getBlceStdrIntrrateShow());
			}
		}

		model.addAttribute("cofixList", cofixList);
		model.addAttribute("loginOrgnztId", loginVO.getOrgnztId());
		model.addAttribute("loginUserId", loginVO.getId());
		model.addAttribute("currentDateStr", EgovDateUtil.formatDate(EgovDateUtil.getToday(), "-"));
		model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/soc/GamSocExmpMngt";
    }
    
    @RequestMapping(value="/soc/gamSelectSocExmpMngtDetailInquire.do")
	@ResponseBody Map selectSocExmpMngtDetailInquire(
			@ModelAttribute("gamSocExmpMngtVO") GamSocExmpMngtVO gamSocExmpMngtVO,
	     	BindingResult bindingResult)
	        throws Exception {
		Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		
		GamSocExmpMngtVO resultVO = gamSocExmpMngtService.selectSocExmpMngtDetailInquire(gamSocExmpMngtVO);

		map.put("resultCode", 0);
		if(resultVO == null) {
			map.put("resultCode", 1);
			map.put("resultMsg", "검색 조건에 맞는 데이터가 없습니다.");
		}
		map.put("resultVO", resultVO);
		
    	return map;
    }
}
