package egovframework.rte.ygpa.gam.oper.center.web;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetPopupInqireService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetPopupInqireVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterPopupInqireService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayPopupInqireService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyPopupInqireService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldPopupInqireService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrPopupInqireService;
import egovframework.rte.ygpa.gam.oper.shed.service.impl.GamCmmnCntrPopupInqireServiceImpl;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortPopupInqireService;

/**
 * @Class Name : GamMarineCenterPopupInqireController.java
 * @Description : 마린센터  정보현황알림
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 * 수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.06.17  sj          최초 생성
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamMarineCenterPopupInqireController {
	
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
    
    @Resource(name = "gamMarineCenterPopupInqireService")
    private GamMarineCenterPopupInqireService gamMarineCenterPopupInqireService;
	
    
    /**
     * 마린센터  정보현황알림 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gamer/htld/gamHtldPopupInqire"; 
     * @throws Exception the exception  
     */
	@RequestMapping(value="/oper/center/gamMarineCenterPopupInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		GamAssetPopupInqireVO gamAssetPopupInqireVO = new GamAssetPopupInqireVO();
		gamAssetPopupInqireVO.setsDeptcd(user.getOrgnztId());
		
		GamAssetPopupInqireVO resultMap = gamMarineCenterPopupInqireService.selectMarineCenterPopupInqire(gamAssetPopupInqireVO);
    	
		model.addAttribute("prmisnYnCnt", resultMap.getPrmisnYnCnt());
		model.addAttribute("nticPdCnt", resultMap.getNticPdCnt());
		model.addAttribute("rcivSeCnt", resultMap.getRcivSeCnt());
		model.addAttribute("nhtIsueCnt", resultMap.getNhtIsueCnt());
		
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/oper/center/GamMarineCenterPopupInqire"; 
    }
	
}
