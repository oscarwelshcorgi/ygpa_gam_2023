package egovframework.rte.ygpa.gam.cmmn.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.umt.service.EgovUserManageService;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.com.uss.umt.service.UserManageUpdateVO;
import egovframework.com.uss.umt.service.UserManageVO;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class GamUserInfoMngController {

	@Autowired
	private DefaultBeanValidator beanValidator;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** userManageService */
    @Resource(name = "userManageService")
    private EgovUserManageService userManageService;

    /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

	@RequestMapping(value="/cmmn/gamUserInfoMng.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();

        //패스워드힌트목록을 코드정보로부터 조회
        vo.setCodeId("COM022");
        List passwordHint_result = cmmUseService.selectCmmCodeDetail(vo);
        //성별구분코드를 코드정보로부터 조회
        vo.setCodeId("COM014");
        List sexdstnCode_result = cmmUseService.selectCmmCodeDetail(vo);
        //사용자상태코드를 코드정보로부터 조회
        vo.setCodeId("COM013");
        List emplyrSttusCode_result = cmmUseService.selectCmmCodeDetail(vo);
        //소속기관코드를 코드정보로부터 조회 - COM025
        vo.setCodeId("COM025");
        List insttCode_result = cmmUseService.selectCmmCodeDetail(vo);
        //조직정보를 조회 - ORGNZT_ID정보
        vo.setTableNm("COMTNORGNZTINFO");
        List orgnztId_result = cmmUseService.selectOgrnztIdDetail(vo);
        //그룹정보를 조회 - GROUP_ID정보
        vo.setTableNm("COMTNORGNZTINFO");
        List groupId_result = cmmUseService.selectGroupIdDetail(vo);

        model.addAttribute("passwordHint_result", passwordHint_result);			// 패스워트힌트목록
        model.addAttribute("sexdstnCode_result", sexdstnCode_result);			// 성별구분코드목록
        model.addAttribute("emplyrSttusCode_result", emplyrSttusCode_result);	// 사용자상태코드목록
        model.addAttribute("insttCode_result", insttCode_result);				// 소속기관코드목록
        model.addAttribute("orgnztId_result", orgnztId_result);					// 조직정보 목록
        model.addAttribute("groupId_result", groupId_result);					// 그룹정보 목록

		model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/cmmn/GamUserInfoMng";
    }

    @RequestMapping("/cmmn/selectGamUserInfo.do")
    @ResponseBody Map<String, Object> selectGamUserInfo() throws Exception {
		Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        UserManageVO userManageVO = new UserManageVO();
        userManageVO = userManageService.selectUser(loginVO.getUniqId());

        map.put("resultCode", 0);
        map.put("userManageVO", userManageVO);

	        return map;
    }

    @RequestMapping(value="/cmmn/popup/gamPopupChgUserPw.do", method=RequestMethod.POST)
    String gamPopupChgUserPw(ModelMap model) throws Exception {
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    		return "ygpa/gam/main/loginError";
    	}

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    	model.addAttribute("emplyrId", loginVO.getId());

    	return "/ygpa/gam/cmmn/popup/GamPopupChgUserPw";
    }

    @RequestMapping(value="/cmmn/popup/gamPopupChgUserPwUpdt.do")
    @ResponseBody Map<String, Object> updatePassword(@RequestParam UserManageVO userManageVO)throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	String resultCode = "";

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		map.put("resultCode", 1);
    		map.put("message", egovMessageSource.getMessage("fail.common.login"));
    		return map;
    	}

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    	String oldPassword = userManageVO.getOldPassword();
        String newPassword = userManageVO.getNewPassword();
        String newPassword2 = userManageVO.getNewPassword2();
        String uniqId = loginVO.getUniqId();

        boolean isCorrectPassword=false;
        UserManageVO resultVO = new UserManageVO();
        userManageVO.setPassword(newPassword);
        userManageVO.setOldPassword(oldPassword);
        userManageVO.setUniqId(uniqId);

    	String resultMsg = "";
    	resultVO = userManageService.selectPassword(userManageVO);
    	//패스워드 암호화
		String encryptPass = EgovFileScrty.encryptPassword(oldPassword);
    	if (encryptPass.equals(resultVO.getPassword())){
    		if (newPassword.equals(newPassword2)){
        		isCorrectPassword = true;
        	}else{
        		isCorrectPassword = false;
        		resultMsg=egovMessageSource.getMessage("fail.user.passwordUpdate2");
        		resultCode = "1";
        	}
    	}else{
    		isCorrectPassword = false;
    		resultMsg=egovMessageSource.getMessage("fail.user.passwordUpdate1");
    		resultCode = "1";
    	}

    	if (isCorrectPassword){
//    		userManageVO.setPassword(EgovFileScrty.encryptPassword(newPassword));
    		userManageService.updatePassword(userManageVO);
            map.put("userManageVO", userManageVO);
            resultMsg = "success.common.update";
            resultCode = "0";
        }else{
        	map.put("userManageVO", userManageVO);
        }

    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);

        return map;
    }

    /**
     * 사용자정보를 수정한다.
     * @param userManageVO
     * @param bindingResult
     * @return String
     * @throws Exception
     */
    @RequestMapping("/cmmn/gamUserInfoUpdt.do")
    @ResponseBody Map<String, Object> updateUser(@ModelAttribute("userManageUpdateVO") UserManageUpdateVO userManageUpdateVO,BindingResult bindingResult)throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	String resultCode = "";

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		map.put("resultCode", 1);
    		map.put("message", egovMessageSource.getMessage("fail.common.login"));
    		return map;
    	}

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		userManageUpdateVO.setUniqId(loginVO.getUniqId());

        beanValidator.validate(userManageUpdateVO, bindingResult);
		if (bindingResult.hasErrors()){
	        map.put("resultCode", 2);
			map.put("resultMsg", "입력 값에 오류가 있습니다.");
			map.put("resultObject", bindingResult.getAllErrors());

		}else{

			//업무사용자 수정시 히스토리 정보를 등록한다.
//			userManageService.insertUserHistory(userManageUpdateVO);
//			if(userManageUpdateVO.getOrgnztId().equals("")){
//				userManageUpdateVO.setOrgnztId(null);
//			}
//			if(userManageUpdateVO.getGroupId().equals("")){
//				userManageUpdateVO.setGroupId(null);
//			}
			userManageService.updateUser(userManageUpdateVO);
			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		}

        return map;
    }

}
