package egovframework.rte.ygpa.gam.cmmn.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.sec.ram.service.AuthorManage;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class GamAuthorMngController {

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "egovAuthorManageService")
    private EgovAuthorManageService egovAuthorManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Autowired
	private DefaultBeanValidator beanValidator;
	
    
    final static String basePath = "/ygpa/gam/cmmn"; 
    
	@RequestMapping(value="/cmmn/gamAuthorMng.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	
    	return basePath+"/GamAuthorMng";
    }
	
	
	/**
	 * 권한 목록을 조회한다
	 * @param authorManageVO AuthorManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/cmmn/gamAuthorList.do")
    @ResponseBody Map<String, Object> selectAuthorList(@ModelAttribute("authorManageVO") AuthorManageVO authorManageVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		
    	/** EgovPropertyService */
    	authorManageVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	authorManageVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(authorManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(authorManageVO.getPageUnit());
		paginationInfo.setPageSize(authorManageVO.getPageSize());
		
		authorManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		authorManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		authorManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		/** List Data */
        List<AuthorManageVO> AuthorList = egovAuthorManageService.selectAuthorList(authorManageVO);
        int totCnt = egovAuthorManageService.selectAuthorListTotCnt(authorManageVO);
		
        map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", AuthorList);
    	map.put("searchOption", authorManageVO);

    	return map;
    }
	
	
	/**
	 * 권한 세부정보를 등록한다. 
	 * @param authorManage
	 * @param bindingResult
	 * @param status
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping(value="/cmmn/gamAuthorInsert.do")
    @ResponseBody Map<String, Object> insertAuthor(@ModelAttribute("authorManage") AuthorManage authorManage,BindingResult bindingResult,SessionStatus status) throws Exception {
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	beanValidator.validate(authorManage, bindingResult); //validation 수행
    	
		if (bindingResult.hasErrors()) { 
	        map.put("resultCode", 1);
			map.put("resultMsg", "입력 값에 오류가 있습니다.");
			map.put("resultObject", bindingResult.getAllErrors());
			
		} else {
	    	egovAuthorManageService.insertAuthor(authorManage);
	        status.setComplete();

	        map.put("resultCode", 0);	// return ok
	    	map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		}
		return map;
    }
    
    
    /**
     * 권한 세부정보를 수정한다.   
     * @param authorManage
     * @param bindingResult
     * @param status
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/cmmn/gamAuthorUpdate.do")
    @ResponseBody Map<String, Object> updateAuthor(@ModelAttribute("authorManage") AuthorManage authorManage,BindingResult bindingResult,SessionStatus status) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	beanValidator.validate(authorManage, bindingResult); //validation 수행
    	
    	if (bindingResult.hasErrors()) { 
	        map.put("resultCode", 1);
			map.put("resultMsg", "입력 값에 오류가 있습니다.");
			map.put("resultObject", bindingResult.getAllErrors());
			
		} else {
	    	egovAuthorManageService.updateAuthor(authorManage);
	        status.setComplete();
	        map.put("resultCode", 0);	// return ok
	    	map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		}
    	return map;
    }
    
    
    /**
	 * 권한 세부정보를 삭제한다.
	 * @param authorManage AuthorManage
	 * @return String
	 * @exception Exception
	 */  
    @RequestMapping(value="/cmmn/gamAuthorDelete.do")
    @ResponseBody Map<String, Object> deleteAuthor(@ModelAttribute("authorManage") AuthorManage authorManage,SessionStatus status) throws Exception {
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	egovAuthorManageService.deleteAuthor(authorManage);
    	status.setComplete();
    	map.put("resultCode", 0);	// return ok
    	map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
        
    	return map;
    }   
}