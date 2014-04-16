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



@Controller
public class GamSmsMngtController {

	 /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    @Resource(name="gamSmsMngtService")
    private GamSmsMngtService gamSmsMngtService;
    
    @Resource(name="gamSmsIdGnrService")
    private EgovIdGnrService gamSmsIdGnrService;
    
	@RequestMapping(value="/cmmn/sms/GamSendMesgListMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/cmmn/sms/GamSendMesgListMngt";
    }
	
	@RequestMapping(value="/cmmn/sms/gamSelectSendMesgList.do", method=RequestMethod.POST)
    public @ResponseBody Map selectGamSendMesgList(GamSmsMngtVO searchVO) throws Exception {
		int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	String sttus = searchVO.getTransmisSttus();
    	String[] array = sttus.split(",");
    	String[] sttusArray = null;
    	if(array.length - 1 > 0) {
    		sttusArray = new String[array.length - 1];
    		System.arraycopy(array, 0, sttusArray, 0, array.length - 1);
    	}
    	searchVO.setTransmisSttusArray(sttusArray);
    	
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
    	System.out.print("test ****************************************** : " + resultList);
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
 
    	return map;
    }
	
	@RequestMapping(value="/cmmn/sms/smsRetransmit.do", method=RequestMethod.POST)
    public @ResponseBody Map smsRetransmit(GamSmsMngtVO searchVO) throws Exception {
    	Map map = new HashMap();

    	searchVO.setNewSmsSeq(String.valueOf(gamSmsIdGnrService.getNextLongId()));
    	
    	gamSmsMngtService.smsRetransmit(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("searchOption", searchVO); 
    	return map;
    }
	
}
