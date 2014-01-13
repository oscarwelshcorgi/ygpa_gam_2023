package egovframework.rte.ygpa.gam.cmmn.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.uss.umt.service.EgovUserManageService;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.com.uss.umt.service.UserManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class GamUserMngController {

    /** userManageService */
    @Resource(name = "userManageService")
    private EgovUserManageService userManageService;
    
    /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;
    
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    
    /**
     * 화면 초기 로드
     * @param windowId
     * @param model
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/cmmn/gamUserMng.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/cmmn/GamUserMng";
    }
	
	
	/**
     * 사용자목록을 조회한다. (pageing)
     * @param userSearchVO 검색조건정보
     * @param map 화면모델
     * @return /cmmn/selectUserMngList
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/cmmn/gamUserManage.do")
    @ResponseBody Map selectUserMngList(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO) throws Exception {
        
    	Map map = new HashMap();
    	
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
        List userList = userManageService.selectUserList(userSearchVO);
        int totCnt = userManageService.selectUserListTotCnt(userSearchVO);
        paginationInfo.setTotalRecordCount(totCnt);

        //사용자상태코드를 코드정보로부터 조회
        /*
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM013");
        
        // 사용자상태코드목록
        List emplyrSttusCode_result = cmmUseService.selectCmmCodeDetail(vo);
        */
        
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
     * 사용자등록처리후 목록화면으로 이동한다.
     * @param userManageVO 사용자등록정보
     * @param bindingResult 입력값검증용 bindingResult
     * @param model 화면모델
     * @throws Exception
     */
    @RequestMapping("/cmmn/gamUserInsert.do")
    @ResponseBody Map insertUser(@ModelAttribute("userManageVO") UserManageVO userManageVO,BindingResult bindingResult)throws Exception {

    	Map map = new HashMap();
    			
        //beanValidator.validate(userManageVO, bindingResult);
    	if (bindingResult.hasErrors()){
    		return map;
		}else{
			if(userManageVO.getOrgnztId().equals("")){
				userManageVO.setOrgnztId(null);
			}
			if(userManageVO.getGroupId().equals("")){
				userManageVO.setGroupId(null);
			}
			System.out.println("userManageVO : "+userManageVO.toString());
			userManageService.insertUser(userManageVO);
	        //Exception 없이 진행시 등록성공메시지
	        //model.addAttribute("resultMsg", "success.common.insert");
		}
    	return map;
    }
}