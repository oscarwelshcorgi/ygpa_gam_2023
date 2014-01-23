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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.uss.umt.service.EgovUserManageService;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.com.uss.umt.service.UserManageVO;
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

    	/** EgovPropertyService */
        userSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        userSearchVO.setPageSize(propertiesService.getInt("pageSize"));

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

    	if("insert".equals(cmd)) {

	    	beanValidator.validate(userManageVO, bindingResult);
			if (bindingResult.hasErrors()){
		        map.put("resultCode", 1);
				map.put("resultMsg", bindingResult.getAllErrors());
//				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}

			if(userManageVO.getOrgnztId().equals("")){
				userManageVO.setOrgnztId(null);
			}
			if(userManageVO.getGroupId().equals("")){
				userManageVO.setGroupId(null);
			}

			userManageService.insertUser(userManageVO);
			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
    	}
    	return map;
    }


    /**
     * 사용자정보를 수정한다.
     * @param userManageVO 사용자수정정보
     * @param bindingResult 입력값검증용 bindingResult
     * @param model 화면모델
     * @return forward:/uss/umt/EgovUserManage.do
     * @throws Exception
     */
    @RequestMapping("/cmmn/gamUserSelectUpdt.do")
    @ResponseBody Map<String, Object> updateUser(@ModelAttribute("userManageVO") UserManageVO userManageVO,BindingResult bindingResult)throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

        beanValidator.validate(userManageVO, bindingResult);
		if (bindingResult.hasErrors()){
	        map.put("resultCode", 1);
			map.put("resultMsg", "입력 값에 오류가 있습니다.");
			map.put("resultObject", bindingResult.getAllErrors());

		}else{

			//업무사용자 수정시 히스토리 정보를 등록한다.
			userManageService.insertUserHistory(userManageVO);
			if(userManageVO.getOrgnztId().equals("")){
				userManageVO.setOrgnztId(null);
			}
			if(userManageVO.getGroupId().equals("")){
				userManageVO.setGroupId(null);
			}
			userManageService.updateUser(userManageVO);
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

        //userManageVO.
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
    @ResponseBody Map<String, Object> deleteUser(@RequestParam("uniqId") String uniqId)
            throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	// 삭제타입 설정필요[USR03]
        userManageService.deleteUser("USR03:"+uniqId);

        map.put("resultCode", 0);
        map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
        return map;
    }
}