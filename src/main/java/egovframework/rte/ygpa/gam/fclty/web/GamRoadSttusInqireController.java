/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeDefaultVO;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadMngGroupVO;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadSttusInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamRoadSttusInqireVO;

/**
*
* @author LFIT
* @since 2019. 6. 19.
* @version 1.0
* @see
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일 		 수정자		 수정내용
*  -------		--------	---------------------------
*  2019.6.19.		LFIT		최초 생성
*
* </pre>
*/

@Controller
public class GamRoadSttusInqireController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamRoadSttusInqireService")
	private GamRoadSttusInqireService gamRoadSttusInqireService;

	@RequestMapping(value="/fclty/gamRoadSttusInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//List fcltsClCdList = gamCvlEngFcltySpecMngService.selectFcltsClCdList();

		//model.addAttribute("fcltsClCdList", fcltsClCdList);
		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/fclty/GamRoadSttusInqire";

	}


	/**
	 * 방충재 그룹 시설 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamRoadMngGroupList.do")
	public @ResponseBody Map gamRoadMngGroupList(GamRoadMngGroupVO searchVO)throws Exception {

		Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
		List gamRoadMngGroupList = gamRoadSttusInqireService.selectRoadMngGroupList(searchVO);

        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", gamRoadMngGroupList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	/**
	 * 방충재 시설 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */	
	@RequestMapping(value="/fclty/gamRoadSttusInqireList.do")
	@ResponseBody Map<String, Object> gamRoadSttusInqireList(GamRoadSttusInqireVO searchVO) throws Exception {

		Map map = new HashMap();

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회

		/** List Data */
		List roadSttusInqireList = gamRoadSttusInqireService.selectRoadSttusInqireList(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", roadSttusInqireList);

    	return map;

	}

	/**
	 * 방충재 시설 출력
	 * @param searchOpt
	 * @return String
	 * @throws Exception
	 */	

    @RequestMapping(value="/fclty/roadSttusInqirePrint.do")
    public String gamRoadSttusInqirePrint(@RequestParam Map<String, Object> searchOpt, ModelMap model) throws Exception {
    	Map map = new HashMap();

    	GamRoadMngGroupVO roadMng;
    	GamRoadSttusInqireVO searchVO = new GamRoadSttusInqireVO();
		ObjectMapper mapper = new ObjectMapper();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "ygpa/gam/code/GamAssetCodeListPrint";
    	}

    	roadMng = mapper.convertValue(searchOpt, GamRoadMngGroupVO.class);
    	searchVO.setsFcltsMngGroupNo(roadMng.getFcltsMngGroupNo());

		List roadSttusInqireList = gamRoadSttusInqireService.selectRoadSttusInqireList(searchVO);

    	model.addAttribute("resultCode", 0);	// return ok
    	model.addAttribute("roadMng", roadMng);
    	model.addAttribute("roadMngSttusInqireList", roadSttusInqireList);

    	return "ygpa/gam/fclty/GamRoadSttusInqirePrint";
    }

}

