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
    
    /**
     * 화면 호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
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
		
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(authorManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(authorManageVO.getPageUnit());
		paginationInfo.setPageSize(authorManageVO.getPageSize());
		
		authorManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		authorManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		authorManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		// 조회값이 있을 경우
		if(!"".equals(authorManageVO.getSearchKeyword()) && authorManageVO.getSearchKeyword() != null){
			authorManageVO.setSearchCondition("1");
		}
		
		/** List Data */
        List<AuthorManageVO> AuthorList = egovAuthorManageService.selectAuthorList(authorManageVO);
        int totCnt = egovAuthorManageService.selectAuthorListTotCnt(authorManageVO);
        
        paginationInfo.setTotalRecordCount(totCnt);
        authorManageVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
		
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
    @ResponseBody Map<String, Object> insertAuthor(@ModelAttribute("authorManage") AuthorManage authorManage) throws Exception {
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	try {
    		egovAuthorManageService.insertAuthor(authorManage);

            map.put("resultCode", 0);	// return ok
        	map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));	
		} catch (Exception e) {
			// TODO: handle exception
			map.put("resultCode", 1);
        	map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
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
    @ResponseBody Map<String, Object> updateAuthor(@ModelAttribute("authorManage") AuthorManage authorManage) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
			
		try {
			egovAuthorManageService.updateAuthor(authorManage);
	        map.put("resultCode", 0);	// return ok
	    	map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));	
		} catch (Exception e) {
			// TODO: handle exception
			map.put("resultCode", 1);
	    	map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
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
    @ResponseBody Map<String, Object> deleteAuthor(@ModelAttribute("authorManage") AuthorManage authorManage) throws Exception {
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	try {
        	egovAuthorManageService.deleteAuthor(authorManage);
        	map.put("resultCode", 0);	// return ok
        	map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
			
		} catch (Exception e) {
			// TODO: handle exception
        	map.put("resultCode", 1);
        	map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));

		}
        
    	return map;
    }   
}