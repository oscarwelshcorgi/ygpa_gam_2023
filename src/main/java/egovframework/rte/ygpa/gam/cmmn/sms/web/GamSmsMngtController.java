package egovframework.rte.ygpa.gam.cmmn.sms.web;

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

import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.cmmn.sms.service.GamSmsMngtService;
import egovframework.rte.ygpa.gam.cmmn.sms.service.GamSmsMngtVO;

/**
 * @Class Name : GamSmsMngtController.java
 * @Description : SMS목록 관리 컨트롤러
 * @Modification Information
 *
 * @author 김종민
 * @since 2014-04-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
public class GamSmsMngtController {

	 /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    @Resource(name="gamSmsMngtService")
    private GamSmsMngtService gamSmsMngtService;
    
    @Resource(name="gamSmsIdGnrService")
    private EgovIdGnrService gamSmsIdGnrService;
    
    /**
     * SMS목록관리 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/cmmn/sms/GamSendMesgListMngt"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/cmmn/sms/GamSendMesgListMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/cmmn/sms/GamSendMesgListMngt";
    }
	
	/**
     * SMS 관리 목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@RequestMapping(value="/cmmn/sms/gamSelectSendMesgList.do", method=RequestMethod.POST)
    public @ResponseBody Map selectGamSendMesgList(GamSmsMngtVO searchVO) throws Exception {
		int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	String sttus = searchVO.getTransmisSttus();
    	String[] array = sttus.split(",");

    	String temp = null;
    	if(array.length - 1 > 0) {
    		temp = "( ";
    		for(int i=0; i<array.length-1; i++) {
    			if(i != array.length - 2)
    				temp = temp + array[i] + ", ";
    			else 
    				temp = temp + array[i] + " )";
    		}
    	}
    	searchVO.setTransmisSttus(temp);
    	
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		//목록
    	totalCnt = gamSmsMngtService.selectSmsMngtListTotCnt(searchVO);
		List resultList = gamSmsMngtService.selectSmsMngtList(searchVO);

		map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
 
    	return map;
    }
	
	/**
     * 재전송 데이터를 처리한다. 
     *
     * @param createVO
     * @return map
     * @throws Exception the exception  
     */
	@RequestMapping(value="/cmmn/sms/smsRetransmit.do", method=RequestMethod.POST)
    public @ResponseBody Map smsRetransmit(GamSmsMngtVO createVO) throws Exception {
    	Map map = new HashMap();

    	createVO.setNewSmsSeq(String.valueOf(gamSmsIdGnrService.getNextLongId()));
    	
    	gamSmsMngtService.smsRetransmit(createVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("searchOption", createVO); 
    	return map;
    }
	
}
