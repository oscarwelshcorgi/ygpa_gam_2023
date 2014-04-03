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

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.prm.service.EgovProgrmManageService;
import egovframework.com.sym.prm.service.ProgrmManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class GamProgListMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovProgrmManageService */
	@Resource(name = "progrmManageService")
    private EgovProgrmManageService progrmManageService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@RequestMapping(value="/cmmn/gamProgListMng.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/cmmn/GamProgListMng";
    }


	/**
     * 프로그램목록 리스트조회한다.
     * @param searchVO ComDefaultVO
     * @return 출력페이지정보 "sym/prm/EgovProgramListManage"
     * @exception Exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/cmmn/gamProgramListManageSelect.do")
    @ResponseBody Map selectProgrmList(@ModelAttribute("searchVO") ComDefaultVO searchVO) throws Exception {

    	Map map = new HashMap();

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
		List list_progrmmanage = progrmManageService.selectProgrmList(searchVO);
        int totCnt = progrmManageService.selectProgrmListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
        map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", list_progrmmanage);
    	map.put("searchOption", searchVO);

    	return map;
    }

    /**
     * 프로그램 목록을 추가한다.
     * @param commandMap
     * @param progrmManageVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cmmn/gamInsertProgramListRegist.do")
    public @ResponseBody Map<String, Object> insertProgrmList(
    		@RequestParam("cmd") String cmd,
    		@ModelAttribute("progrmManageVO") ProgrmManageVO progrmManageVO,
			BindingResult bindingResult)
            throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
        String resultMsg = "";

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

        if("insert".equals(cmd)) {
	        beanValidator.validate(progrmManageVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			if(progrmManageVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	progrmManageService.insertProgrm(progrmManageVO);
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        map.put("resultCode", 0);			// return ok
        map.put("resultMsg", resultMsg);
		return map;
    }


	/**
	 * 프로그램목록을 수정 한다.
	 * @param commandMap
	 * @param progrmManageVO
	 * @param bindingResult
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping(value="/cmmn/gamProgramListDetailSelectUpdt.do")
    public @ResponseBody Map<String, Object> updateProgrmList(Map<String, Object> commandMap,@ModelAttribute("progrmManageVO") ProgrmManageVO progrmManageVO,BindingResult bindingResult)throws Exception {
    	
    	Map<String, Object> map = new HashMap<String, Object>();
		
    	// 0. Spring Security 사용자권한 처리
   	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

        beanValidator.validate(progrmManageVO, bindingResult);
		if (bindingResult.hasErrors()){
	        map.put("resultCode", 1);
			map.put("resultMsg", "입력 값에 오류가 있습니다.");
			map.put("resultObject", bindingResult.getAllErrors());
			return map;
		}
		if(progrmManageVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
		progrmManageService.updateProgrm(progrmManageVO);

        map.put("resultCode", 0);			// return ok
        map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		return map;
    }

    
    /**
     * 프로그램목록을 삭제 한다.
     * @param progrmManageVO
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/cmmn/gamProgramListManageDelete.do", method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> deleteProgrmList(ProgrmManageVO progrmManageVO)throws Exception {
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
        
    	// DATA 삭제
    	progrmManageService.deleteProgrm(progrmManageVO);
        
    	map.put("resultCode", 0);			// return ok
        map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));

        return map;
    }
}