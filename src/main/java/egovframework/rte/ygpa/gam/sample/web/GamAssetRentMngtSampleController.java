package egovframework.rte.ygpa.gam.sample.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.log.clg.service.EgovLoginLogService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentLevReqestVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService;
import egovframework.rte.ygpa.gam.cmmn.sms.service.GamSmsMngtService;

@Controller
public class GamAssetRentMngtSampleController {

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name="gamAssetsUsePermMngtService")
    private GamAssetsUsePermMngtService gamAssetsUsePermMngtService;

	/** SMS 아이디 생성 서비스 */
	@Resource(name="gamSmsIdGnrService")
	private EgovIdGnrService gamSmsIdGnrService;

	/**
	 * SMS 전송 서비스
	 */
	@Resource(name="gamSmsMngtService")
	private GamSmsMngtService gamSmsMngtService;

	@RequestMapping(value="/sample/gamAssetRentMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/sample/GamAssetRentMngt";
    }

    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/sample/asset/gamAssetRentUsePerm.do")
    public @ResponseBody Map gamAssetRentUsePerm(
     	   @RequestParam Map<String, Object> vo)
            throws Exception {

     	Map<String, Object> map = new HashMap<String, Object>();
         String resultMsg = "";
         int resultCode = 1;

     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
 	        map.put("resultCode", 1);
     		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
         	return map;
     	}

     	LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

     	if(loginVo==null) {
 	        map.put("resultCode", -1);
     		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
         	return map;
     	}

         if(!vo.containsKey("prtAtCode") || !vo.containsKey("mngYear") || !vo.containsKey("mngNo") || !vo.containsKey("mngCnt")) {
             resultCode = 2;
        	 resultMsg = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
         }
         else {
        	 vo.put("regUsr", loginVo.getId());

	         gamAssetsUsePermMngtService.confirmAssetsRentUsePerm(vo);

	         // 메시지를 전송 합니다.

	         Map<String, Object> sms = new HashMap<String, Object>();
	         sms.put("smsSeq", gamSmsIdGnrService.getNextLongId());	// 아이디를 가져온다.
	         sms.put("recptnNo", "01047630145");		// 테스트
	         sms.put("replyNo", "01047630145");		// 테스트
	         sms.put("prtAtCode", vo.get("prtAtCode"));
	         sms.put("mngYear", vo.get("mngYear"));
	         sms.put("mngNo", vo.get("mngNo"));
	         sms.put("mngCnt", vo.get("mngCnt"));
	         sms.put("cn", egovMessageSource.getMessage("gam.asset.rent.sms.confirm"));	// 메시지 내용

	         gamSmsMngtService.sendSmsMessage(sms);

	         resultCode = 0;
	 		 resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.exec");
         }

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }

    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/sample/asset/gamAssetRentCancelUsePerm.do")
    public @ResponseBody Map gamAssetRentCancelUsePerm(
     	   @RequestParam Map<String, Object> vo)
            throws Exception {

     	Map<String, Object> map = new HashMap<String, Object>();
         String resultMsg = "";
         int resultCode = 1;

     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
 	        map.put("resultCode", 1);
     		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
         	return map;
     	}

     	LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

     	if(loginVo==null) {
 	        map.put("resultCode", -1);
     		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
         	return map;
     	}

         if(!vo.containsKey("prtAtCode") || !vo.containsKey("mngYear") || !vo.containsKey("mngNo") || !vo.containsKey("mngCnt")) {
             resultCode = 2;
        	 resultMsg = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
         }
         else {
        	 vo.put("regUsr", loginVo.getId());

	         gamAssetsUsePermMngtService.cancelAssetsRentUsePerm(vo);

	         resultCode = 0;
	 		 resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.exec");
         }

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }

}
