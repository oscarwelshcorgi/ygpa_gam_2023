package egovframework.rte.ygpa.gam.cmmn.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.sec.gmt.service.EgovGroupManageService;
import egovframework.com.sec.gmt.service.GroupManageVO;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.com.sec.rgm.service.AuthorGroup;
import egovframework.com.sec.rgm.service.AuthorGroupVO;
import egovframework.com.sec.rgm.service.EgovAuthorGroupService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class GamAuthorGrpMngController {

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
	@Resource(name = "egovAuthorGroupService")
    private EgovAuthorGroupService egovAuthorGroupService;
	
	@Resource(name = "egovAuthorManageService")
    private EgovAuthorManageService egovAuthorManageService;
	
	@Resource(name = "egovGroupManageService")
    private EgovGroupManageService egovGroupManageService;
	
	/**
	 * 화면호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/cmmn/gamAuthorGrpMng.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	
    	List<AuthorManageVO> list = egovAuthorManageService.selectAuthorAllList(new AuthorManageVO());
        model.addAttribute("authorManageList", list);

    	return "/ygpa/gam/cmmn/GamAuthorGrpMng";
    }
	
	
	/**
	 * 그룹별 할당된 권한 목록 조회
	 * @param authorGroupVO AuthorGroupVO
	 * @param authorManageVO AuthorManageVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/cmmn/gamAuthorGroupList.do")
    @ResponseBody Map<String, Object> selectAuthorGroupList(@ModelAttribute("authorGroupVO") AuthorGroupVO authorGroupVO,
			                            @ModelAttribute("authorManageVO") AuthorManageVO authorManageVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
    	
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(authorGroupVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(authorGroupVO.getPageUnit());
		paginationInfo.setPageSize(authorGroupVO.getPageSize());
		
		authorGroupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		authorGroupVO.setLastIndex(paginationInfo.getLastRecordIndex());
		authorGroupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
		authorGroupVO.setAuthorGroupList(egovAuthorGroupService.selectAuthorGroupList(authorGroupVO));
		int totCnt = egovAuthorGroupService.selectAuthorGroupListTotCnt(authorGroupVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		authorGroupVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", authorGroupVO.getAuthorGroupList());
    	map.put("searchOption", authorManageVO);

    	return map;
	}
	
	
	/**
	 * 그룹에 권한정보를 할당하여 데이터베이스에 등록
	 * @param userIds
	 * @param authorCodes
	 * @param regYns
	 * @param mberTyCodes
	 * @param authorGroup
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/cmmn/gamAuthorGroupInsert.do")
	@ResponseBody Map<String, Object> insertAuthorGroup(@RequestParam Map<String, Object> dataList) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		ObjectMapper mapper = new ObjectMapper();

		List<AuthorGroup> updateList = mapper.readValue((String)dataList.get("authorList"),
    		    new TypeReference<List<AuthorGroup>>(){});

    	for(int i=0; i<updateList.size();i++) {
    		AuthorGroup group=updateList.get(i);
    		if("Y".equals(group.getRegYn()))
    		    egovAuthorGroupService.updateAuthorGroup(group);
    		else 
    		    egovAuthorGroupService.insertAuthorGroup(group);
    	}

    	map.put("resultCode", 0);		
        map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		return map;
	}

	
	/**
	 * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
	 * @param userIds
	 * @param authorGroup
	 * @param status
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/cmmn/gamAuthorGroupDelete.do")
	@ResponseBody Map<String, Object> deleteAuthorGroup(@RequestParam("esntlIds") String esntlIds, @ModelAttribute("authorGroup") AuthorGroup authorGroup) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
    	String [] strEsntlIds = esntlIds.split(";");
    	for(int i=0; i<strEsntlIds.length;i++) {
    		authorGroup.setUniqId(strEsntlIds[i]);
    		egovAuthorGroupService.deleteAuthorGroup(authorGroup);
    	}
    	
    	map.put("resultCode", 0);		
        map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		return map;
	}
	
	
	/**
	 * 그룹검색 리스트 팝업 호출
	 * @param searchVO
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/cmmn/popup/gamPopupGroupView.do", method=RequestMethod.POST)
	public String popupGrouphView(ModelMap model, @RequestParam("searchKeyword") String searchKeyword) throws Exception {
		
		model.addAttribute("searchKeyword", searchKeyword);
		return "/ygpa/gam/cmmn/popup/GamPopupGroupList";
	}
	

	/**
     * 그룹검색 리스트 팝업
     * @param searchVO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/cmmn/popup/gamPopupGroupList.do", method=RequestMethod.POST)
    @ResponseBody Map<String, Object> selectGroupSearchList(@ModelAttribute("groupManageVO") GroupManageVO groupManageVO) throws Exception {
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(groupManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(groupManageVO.getPageUnit());
		paginationInfo.setPageSize(groupManageVO.getPageSize());
		
		groupManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		groupManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		groupManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		if(!"".equals(groupManageVO.getSearchKeyword()) && groupManageVO.getSearchKeyword() != null){
			groupManageVO.setSearchCondition("1");			
		}

		groupManageVO.setGroupManageList(egovGroupManageService.selectGroupList(groupManageVO));
        int totCnt = egovGroupManageService.selectGroupListTotCnt(groupManageVO);
		paginationInfo.setTotalRecordCount(totCnt);

    	map.put("totalCount", totCnt);
    	map.put("searchOption", groupManageVO);
        map.put("resultList", groupManageVO.getGroupManageList());
        return map;
	}
}