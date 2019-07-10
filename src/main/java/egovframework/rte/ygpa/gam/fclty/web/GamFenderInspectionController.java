/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.ccm.ccc.service.CmmnClCode;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.cmmn.service.GamFileServiceVo;
import egovframework.rte.ygpa.gam.cmmn.service.GamFileUploadUtil;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionPrintVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionService;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderInspectionVO;

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
public class GamFenderInspectionController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamFenderInspectionService")
	private GamFenderInspectionService gamFenderInspectionService;
	
    @Resource(name="gamFenderFileIdGnrService")
    EgovTableIdGnrService gamFenderFileIdGnrService;

	@RequestMapping(value="/fclty/gamFenderInspection.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//List fcltsClCdList = gamCvlEngFcltySpecMngService.selectFcltsClCdList();

		//model.addAttribute("fcltsClCdList", fcltsClCdList);
		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/fclty/GamFenderInspection";

	}


	@RequestMapping(value="/fclty/gamFenderInspectionList.do")
	@ResponseBody Map<String, Object> gamFenderInspectionList(GamFenderInspectionVO searchVO) throws Exception {

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
		List mntnRprObjFcltsFList = gamFenderInspectionService.selectFenderInspectionList(searchVO);

		map.put("resultCode", 0);			// return ok
    	map.put("resultList", mntnRprObjFcltsFList);

    	return map;

	}

	
	@RequestMapping(value="/fclty/gamInsertFenderInspection.do")
	@ResponseBody Map<String, Object> gamInsertFenderInspection(GamFenderInspectionVO inputVO, BindingResult bindingResult, Model model) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		
		beanValidator.validate(inputVO, bindingResult);
		
		if (bindingResult.hasErrors()){
/*			model.addAttribute("inputVO", inputVO);
			return "/ygpa/gam/fclty/GamFenderInspection";
*/
			Object[] error = bindingResult.getAllErrors().get(0).getArguments();
			String msg = error[0].toString()+"가 부정확합니다.";
			
			
    		map.put("resultCode", 1);
    		map.put("resultMsg", msg);
    		return map;
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		try {
			inputVO.setRegister(user.getId());
			gamFenderInspectionService.gamInsertFenderInspection (inputVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}
		return map;
	}
	
	@RequestMapping(value="/fclty/gamUpdateFenderInspection.do")
	@ResponseBody Map<String, Object> gamUpdateFenderInspection(GamFenderInspectionVO inputVO, BindingResult bindingResult) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
/*		
		beanValidator.validate(inputVO, bindingResult);
*/		
		try {
			inputVO.setRegister(user.getId());
			gamFenderInspectionService.gamUpdateFenderInspection (inputVO);
			
			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}
		return map;
	}
	
	

	@RequestMapping(value="/fclty/gamDeleteFenderInspection.do")
	@ResponseBody Map<String, Object> gamDeleteCvlEngFcltySpecMng(GamFenderInspectionVO deleteVO) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		
		try {
			gamFenderInspectionService.deleteFenderInspection (deleteVO);
			
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
	 * 방충재 정기점검 출력
	 * @param searchOpt
	 * @return String
	 * @throws Exception
	 */	

    @RequestMapping(value="/fclty/fenderInspectionPrint.do")
    public String gamFenderInspectionPrint(GamFenderInspectionPrintVO printVo, ModelMap model) throws Exception {
    	Map map = new HashMap();

		ObjectMapper mapper = new ObjectMapper();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "ygpa/gam/code/GamAssetCodeListPrint";
    	}
//    	fenderMng = mapper.convertValue(searchOpt, GamFenderMngGroupVO.class);
//    	searchVO.setsFcltsMngGroupNo(fenderMng.getFcltsMngGroupNo());
    	
    	String fcltsGbn = printVo.getFcltsGbn();
    	
    	if(fcltsGbn=="1") {
    		printVo.setFcltsGbnNm("1종");
    	}else if(fcltsGbn=="2") {
    		printVo.setFcltsGbnNm("2종");
    	}else if(fcltsGbn=="3") {
    		printVo.setFcltsGbnNm("1종/2종");
    	}else if(fcltsGbn=="9") {
    		printVo.setFcltsGbnNm("기타");
    	}else {
    		printVo.setFcltsGbnNm("기타");
    	}
    	
    	model.addAttribute("resultCode", 0);	// return ok
    	model.addAttribute("printVo", printVo);

    	return "ygpa/gam/fclty/GamFenderInspectionPrint";
    }
    
    
	/**
	 * 관리그룹 팝업
	 * @param searchOpt
	 * @return String
	 * @throws Exception
	 */	
	@RequestMapping(value="/popup/showFenderMngGroup.do")
    String showFenderMngGroupList(GamFenderInspectionVO searchOpt, ModelMap model) throws Exception {
		model.addAttribute("searchOpt", searchOpt);
    	return "/ygpa/gam/popup/GamPopupFenderMngGroup";
    }

	/**
	 * 관리그룹 팝업 조회
	 * @param searchOpt
	 * @return Map
	 * @throws Exception
	 */	
    @RequestMapping(value="/popup/selectFenderMngGroupList.do", method=RequestMethod.POST)
	@ResponseBody Map selectFenderMngGroupList(GamFenderInspectionVO searchVO) throws Exception {
		int totalCnt;
    	Map map = new HashMap();

		List resultList = gamFenderInspectionService.selectFenderMngGroupList(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
    // 파일 처리 (자산 임대 공통 - 리퀘스트 패스만 변경 하여 사용)
    @RequestMapping(value="/fclty/uploadFenderAttachFile.do", method=RequestMethod.POST)
    public @ResponseBody Map uploadFile(HttpServletRequest request) throws Exception {
		Map map = new HashMap();
		String uploadPath = EgovProperties.getProperty("assetsRent.fileStorePath");
		try {
			List<GamFileServiceVo> list = GamFileUploadUtil.uploadFiles(request, uploadPath, gamFenderFileIdGnrService);

			map.put("resultCode", "0");
			map.put("result", list);
		}
		catch(Exception e) {
			map.put("resultCode", "-1");
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.upload"));
		}
		return map;
	}

	
	
}

