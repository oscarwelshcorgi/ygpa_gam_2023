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
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.uss.umt.service.EgovUserManageService;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.com.uss.umt.service.UserManageUpdateVO;
import egovframework.com.uss.umt.service.UserManageVO;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class GamUserMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

    /** userManageService */
    @Resource(name = "userManageService")
    private EgovUserManageService userManageService;

    /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /**
     * 화면 초기 로드
     * @param windowId
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/cmmn/gamUserMng.do")
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

    	return "/ygpa/gam/cmmn/GamUserMng";
    }


	/**
	 * 사용자목록을 조회한다. (pageing)
	 * @param userSearchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked" })
    @RequestMapping(value="/cmmn/gamUserManage.do")
    @ResponseBody Map<String, Object> selectUserMngList(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit());
        paginationInfo.setPageSize(userSearchVO.getPageSize());

        userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        userSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        /** List Data */
        List<UserDefaultVO> userList = userManageService.selectUserList(userSearchVO);
        int totCnt = userManageService.selectUserListTotCnt(userSearchVO);
        
        paginationInfo.setTotalRecordCount(totCnt);
        userSearchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

        map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", userList);
    	map.put("searchOption", userSearchVO);

    	return map;
    }


	/**
     * 사용자관리 등록화면 로드
     * @param windowId
     * @param model
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/cmmn/gamUserInsertView.do")
    String gamUserInsert(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/cmmn/GamUserInsert";
    }


	/**
	 * 사용자 등록을 처리한다
	 * @param userManageVO
	 * @param bindingResult
	 * @param cmd
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping("/cmmn/gamUserInsert.do")
    @ResponseBody Map<String, Object> insertUser(@ModelAttribute("userManageVO") UserManageVO userManageVO,BindingResult bindingResult, @RequestParam("cmd") String cmd)throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

		try {
			userManageService.insertUser(userManageVO);
			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
			
		} catch (Exception e) {
			// TODO: handle exception
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}
    	return map;
    }


    /**
     * 사용자정보를 수정한다.
     * @param userManageVO
     * @param bindingResult
     * @return String
     * @throws Exception
     */
    @RequestMapping("/cmmn/gamUserSelectUpdt.do")
    @ResponseBody Map<String, Object> updateUser(@ModelAttribute("userManageUpdateVO") UserManageUpdateVO userManageUpdateVO,BindingResult bindingResult)throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

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


    /**
     * 사용자정보 수정을 위해 사용자정보를 상세조회한다.
     * @param uniqId
     * @param userSearchVO
     * @return map
     * @throws Exception
     */
    @RequestMapping("/cmmn/gamUserSelectUpdtView.do")
    @ResponseBody Map<String, Object> updateUserView(@RequestParam("uniqId") String uniqId, @ModelAttribute("searchVO") UserDefaultVO userSearchVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

        UserManageVO userManageVO = new UserManageVO();
        userManageVO = userManageService.selectUser(uniqId);

        map.put("userSearchVO", userSearchVO);
        map.put("userManageVO", userManageVO);

        return map;
    }


    /**
     * 사용자정보삭제후 목록조회 화면으로 이동한다.
     * @param checkedId
     * @param userSearchVO
     * @return map
     * @throws Exception
     */
    @RequestMapping("/cmmn/gamUserDelete.do")
    @ResponseBody Map<String, Object> deleteUser(@RequestParam("uniqId") String uniqId) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	// 삭제타입 설정필요[USR03]
        userManageService.deleteUser("USR03:"+uniqId);

        map.put("resultCode", 0);
        map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
        return map;
    }
    
    
    /**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     * @param commandMap
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/cmmn/gamIdDplctCnfirm.do")
    @ResponseBody Map<String, Object> checkIdDplct(@RequestParam("checkId") String checkId)throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	checkId =  new String(checkId.getBytes("ISO-8859-1"), "UTF-8");

    	if (checkId==null || checkId.equals("")){
    		map.put("resultCode", 1);
			map.put("resultMsg", "입력한 아이디가 존재하지 않습니다.");
    	}

        int usedCnt = userManageService.checkIdDplct(checkId);
        
        map.put("resultCode", 0);
        map.put("usedCnt", usedCnt);
        map.put("checkId", checkId);

        return map;
    }
    
    
    /**
     * 업무사용자 암호 수정  화면 이동
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/cmmn/popup/gamPopupChgPwView.do", method=RequestMethod.POST)
    String updatePasswordView(@RequestParam("emplyrId") String emplyrId, @RequestParam("uniqId") String uniqId, ModelMap model) throws Exception {
    	
    	/*String userTyForPassword = (String)commandMap.get("userTyForPassword");
    	userManageVO.setUserTy(userTyForPassword);
    	*/

    	model.addAttribute("emplyrId", emplyrId);
    	model.addAttribute("uniqId", uniqId);
    	return "/ygpa/gam/cmmn/popup/GamPopupChgPwView";
    }
    
    
    /**
     * 업무사용자 암호 수정처리 후 화면 이동
     * @param model 화면모델
     * @param commandMap 파라메터전달용 commandMap
     * @param userSearchVO 검색조 건
     * @param userManageVO 사용자수정정보(비밀번호)
     * @return uss/umt/EgovUserPasswordUpdt
     * @throws Exception
     */
    @RequestMapping(value="/cmmn/popup/gamUserPasswordUpdt.do")
    @ResponseBody Map<String, Object> updatePassword(Map<String, Object> commandMap,@ModelAttribute("searchVO") UserDefaultVO userSearchVO,
    		  					 @ModelAttribute("userManageVO") UserManageVO userManageVO)throws Exception {
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	String resultCode = "";
    	
//    	String oldPassword = (String)commandMap.get("oldPassword");
//        String newPassword = (String)commandMap.get("newPassword");
//        String newPassword2 = (String)commandMap.get("newPassword2");
//        String uniqId = (String)commandMap.get("uniqId");
    	
    	String oldPassword = userManageVO.getOldPassword();
        String newPassword = userManageVO.getNewPassword();
        String newPassword2 = userManageVO.getNewPassword2();
        String uniqId = userManageVO.getUniqId();

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
    	map.put("userSearchVO", userSearchVO);
    	map.put("resultMsg", resultMsg);
    	map.put("resultCode", resultCode);

        return map;
    }
}