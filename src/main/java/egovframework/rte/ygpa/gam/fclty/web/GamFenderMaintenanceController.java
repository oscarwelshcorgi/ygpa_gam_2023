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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceService;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderMaintenanceVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderMngGroupVO;

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
public class GamFenderMaintenanceController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamFenderMaintenanceService")
	private GamFenderMaintenanceService gamFenderMaintenanceService;

	@RequestMapping(value="/fclty/gamFenderMaintenance.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//List fcltsClCdList = gamCvlEngFcltySpecMngService.selectFcltsClCdList();

		//model.addAttribute("fcltsClCdList", fcltsClCdList);
		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/fclty/GamFenderMaintenance";

	}


	/**
	 * 방충재 그룹 시설 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamFenderMaintenanceList.do")
	public @ResponseBody Map gamFenderMaintenanceList(GamFenderMaintenanceVO searchVO)throws Exception {

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
		List gamFenderMngGroupList = gamFenderMaintenanceService.selectFenderMaintenanceList(searchVO);

        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", gamFenderMngGroupList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	/**
	 * 방충재 시설 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */	
	@RequestMapping(value="/fclty/gamFenderMaintenanceDetailList.do")
	@ResponseBody Map<String, Object> gamFenderMaintenanceDetailList(GamFenderMaintenanceVO searchVO) throws Exception {

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
		List FenderMaintenanceList = gamFenderMaintenanceService.selectFenderMaintenanceDetailList(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", FenderMaintenanceList);

    	return map;

	}
	
	
	@RequestMapping(value="/fclty/gamInsertFenderMaintenance.do")
	@ResponseBody Map<String, Object> gamInsertFenderMaintenance(@RequestParam Map inputVO, Model model) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Map insertFenderMaintenance = new HashMap();
    	List<HashMap<String,String>> insertObjList=null;
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		
//		beanValidator.validate(inputVO, bindingResult);
		
//		if (bindingResult.hasErrors()){
/*			model.addAttribute("inputVO", inputVO);
			return "/ygpa/gam/fclty/GamFenderMaintenance";
*/
//			Object[] error = bindingResult.getAllErrors().get(0).getArguments();
//			String msg = error[0].toString()+"가 부정확합니다.";
			
//    		map.put("resultCode", 1);
//   		map.put("resultMsg", msg);
//    		return map;
//		}
		
		insertFenderMaintenance = mapper.readValue((String)inputVO.get("detailForm"),
    		    new TypeReference<HashMap<String,String>>(){});
    	
		insertObjList = mapper.readValue((String)inputVO.get("insertMntnObjList"),
				new TypeReference<List<HashMap<String,String>>>(){});
    	
		try {
			gamFenderMaintenanceService.gamInsertFenderMaintenance (insertFenderMaintenance, insertObjList);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}
		return map;
	}
	
	@RequestMapping(value="/fclty/gamUpdateFenderMaintenance.do")
	@ResponseBody Map<String, Object> gamUpdateFenderMaintenance(@RequestParam Map inputVO) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Map insertFenderMaintenance = new HashMap();
    	List<HashMap<String,String>> insertObjList=null;
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		
		insertFenderMaintenance = mapper.readValue((String)inputVO.get("detailForm"),
    		    new TypeReference<HashMap<String,String>>(){});
    	
		insertObjList = mapper.readValue((String)inputVO.get("insertMntnObjList"),
				new TypeReference<List<HashMap<String,String>>>(){});
    	
		try {
			gamFenderMaintenanceService.gamUpdateFenderMaintenance (insertFenderMaintenance, insertObjList);
			
			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}
		return map;
	}
	
	

	@RequestMapping(value="/fclty/gamDeleteFenderMaintenance.do")
	@ResponseBody Map<String, Object> gamDeleteFenderMaintenance(GamFenderMaintenanceVO deleteVO) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		
		try {
			gamFenderMaintenanceService.gamDeleteFenderMaintenance (deleteVO);
			
			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}
		return map;
	}
	
	
	/**
	 * 방충재 유지보수 출력
	 * @param searchOpt
	 * @return String
	 * @throws Exception
	 */	

    @RequestMapping(value="/fclty/gamFenderMaintenancePrint.do")
    public String gamFenderMaintenancePrint(GamFenderMaintenanceVO printVo, ModelMap model) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "ygpa/gam/code/GamAssetCodeListPrint";
    	}
    	
    	
    	List fenderMaintenanceDetailList = gamFenderMaintenanceService.selectFenderMaintenanceDetailPrint(printVo);

//    	fenderMng = mapper.convertValue(searchOpt, GamFenderMngGroupVO.class);
//    	searchVO.setsFcltsMngGroupNo(fenderMng.getFcltsMngGroupNo());

//		List FenderMaintenanceList = gamFenderMaintenanceService.selectFenderMaintenanceList(searchVO);

    	model.addAttribute("resultCode", 0);	// return ok
    	model.addAttribute("printVo", printVo);
    	model.addAttribute("fenderMaintenanceDetailList", fenderMaintenanceDetailList);

    	return "ygpa/gam/fclty/GamFenderMaintenancePrint";
    }

}

