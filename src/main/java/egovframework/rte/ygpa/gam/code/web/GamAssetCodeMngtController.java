/**
 *
 */
package egovframework.rte.ygpa.gam.code.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.util.fileupload.multi.service.FileInfoVO;
import egovframework.rte.ygpa.gam.cmmn.service.GamFileServiceVo;
import egovframework.rte.ygpa.gam.cmmn.service.GamFileUploadUtil;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeDefaultVO;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeMngtService;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeVO;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetPhotoMngtService;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetPhotoVO;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 4. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 4. 3.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamAssetCodeMngtController {

    @Resource(name = "gamGisAssetCodeMngtService")
    private GamGisAssetCodeMngtService gamGisAssetCodeMngtService;

    @Resource(name = "gamGisAssetPhotoMngtService")
    private GamGisAssetPhotoMngtService gamGisAssetPhotoMngtService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="gamGisAssetFileIdGnrService")
    EgovTableIdGnrService gamGisAssetFileIdGnrService;

	/**
	 * 화면 호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/code/assets/gamAssetCodeMngt.do")
    String gamAssetCodeMngt(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		Boolean isAdmin=false;
		model.addAttribute("windowId", windowId);
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(isAuthenticated) {
    		List<String> auth = EgovUserDetailsHelper.getAuthorities();
    		for(int i=0; i<auth.size(); i++) {
    			if("role_admin".equalsIgnoreCase((String)auth.get(i))) {
    				isAdmin=true;
    				break;
    			}
    		}
    	}
    	model.addAttribute("isAdmin", isAdmin);

    	return "ygpa/gam/code/GamAssetCodeMngt";
    }

    /**
     * 자산 코드 조회
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/code/assets/selectGisAssetCodeList.do")
    @ResponseBody Map selectAssetList(GamGisAssetCodeDefaultVO searchVO) throws Exception {
    	int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	totalCnt = gamGisAssetCodeMngtService.selectAssetCodeListTotCnt(searchVO);

    	List gamAssetList = gamGisAssetCodeMngtService.selectAssetCodeList(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", gamAssetList);
    	map.put("searchOption", searchVO);

    	return map;
    }

    @RequestMapping(value="/code/assets/selectGisAssetCodeListPrint.do")
    public String selectAssetListPrint(@RequestParam Map<String, Object> searchOpt, ModelMap model) throws Exception {
    	int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	GamGisAssetCodeDefaultVO searchVO;
		ObjectMapper mapper = new ObjectMapper();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return "ygpa/gam/code/GamAssetCodeListPrint";
    	}

    	searchVO = mapper.convertValue(searchOpt, GamGisAssetCodeDefaultVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

    	List gamAssetList = gamGisAssetCodeMngtService.selectAssetCodeList(searchVO);

    	model.addAttribute("resultList", gamAssetList);

    	model.addAttribute("resultCode", 0);	// return ok
    	model.addAttribute("resultList", gamAssetList);

    	return "ygpa/gam/code/GamAssetCodeListPrint";
    }

    /**
     * 자산 코드 조회
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/code/assets/selectGisAssetCodeByPk.do")
    @ResponseBody Map selectAssetCodeByPk(@RequestParam Map assetCode) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}


    	EgovMap gamAssetCode = gamGisAssetCodeMngtService.selectAssetCode(assetCode);

    	map.put("resultCode", 0);	// return ok
    	map.put("result", gamAssetCode);

    	return map;
    }

	@RequestMapping(value="/code/assets/insertGamGisAssetCode.do")
	@ResponseBody Map<String, Object> insertGamGisAssetCode(@RequestParam Map<String, Object> insertVO) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}


    	gamGisAssetCodeMngtService.insertAssetCode(insertVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));

    	return map;
	}

	@RequestMapping(value="/code/assets/updateGamGisAssetCode.do")
	@ResponseBody Map<String, Object> updateGamGisAssetCode(@RequestParam Map<String, Object> insertVO) throws Exception {
    	Map map = new HashMap();
    	Map result;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    		insertVO.put("updUsr", loginVo.getId());
        	result=gamGisAssetCodeMngtService.updateAssetCode(insertVO);
    	} 
    	catch(NullPointerException e) {
        	map.put("resultCode", -1);	// return ok
        	map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));

        	return map;
    	}

    	map.put("resultCode", 0);	// return ok
    	map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
    	map.put("resultVo", result);

    	return map;
	}

	@RequestMapping(value="/code/assets/deleteGamGisAssetCode.do")
	@ResponseBody Map<String, Object> deleteGamGisAssetCode(@RequestParam Map<String, Object> insertVO) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
        	gamGisAssetCodeMngtService.deleteAssetCode(insertVO);
    	} catch(ParseException e){
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
    	}
    	catch(Exception e) {
        	map.put("resultCode", -1);	// return ok
        	map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));

        	return map;
    	}

    	map.put("resultCode", 0);	// return ok
    	map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));

    	return map;
	}

	@RequestMapping(value="/code/assets/deleteGamGisAssetCodes.do")
	@ResponseBody Map<String, Object> deleteGamGisAssetCodes(@RequestParam Map deleteItems) throws Exception {
    	Map map = new HashMap();
    	List<HashMap<String,String>> deleteList=null;
		ObjectMapper mapper = new ObjectMapper();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		deleteList = mapper.readValue((String)deleteItems.get("deleteList"),
        		    new TypeReference<List<HashMap<String,String>>>(){});


        	gamGisAssetCodeMngtService.deleteAssetCodes(deleteList);
    	} catch(ParseException e){
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
    	}
    	catch(Exception e) {
        	map.put("resultCode", -1);	// return ok
        	map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));

        	return map;
    	}

    	map.put("resultCode", 0);	// return ok
    	map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));

    	return map;
	}

    @RequestMapping(value="/code/assets/selectGisAssetPhotoList.do")
    @ResponseBody Map selectGisAssetPhotoList(GamGisAssetPhotoVO searchVO) throws Exception {
    	int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	totalCnt = gamGisAssetPhotoMngtService.selectAssetPhotoListTotCnt(searchVO);

    	List gamAssetList = gamGisAssetPhotoMngtService.selectAssetPhotoList(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", gamAssetList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	@RequestMapping(value="/code/assets/mergeGamGisAssetPhotoMngt.do")
	@ResponseBody Map<String, Object> mergeGamGisAssetPhotoMngt(@RequestParam Map<String, Object> dataList) throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		Map<String,Object> map = new HashMap<String,Object>();
		Map<String, String> userMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

    	List<HashMap<String,String>> insertList=null;
    	List<HashMap<String,String>> updateList=null;
    	List<HashMap<String,String>> deleteList=null;
    	List<Map<String,String>> userList=null;

    	int resultCode = -1;
    	String resultMsg = "";

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		insertList = mapper.readValue((String)dataList.get("insertList"),
		    new TypeReference<List<HashMap<String,String>>>(){});

		updateList = mapper.readValue((String)dataList.get("updateList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		deleteList = mapper.readValue((String)dataList.get("deleteList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		userList = new ArrayList();
		userMap.put("id",  loginVO.getId());
		userList.add(userMap);

		Map<String,Object> mergeMap = new HashMap<String,Object>();

		insertList.addAll(updateList);
		mergeMap.put("CU", insertList);
		mergeMap.put("D", deleteList);
		mergeMap.put("USER", userList);

		gamGisAssetPhotoMngtService.mergeAssetPhotoMngt(mergeMap);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));

		return map;
	}

    @RequestMapping(value="/code/assets/uploadAssetFile.do", method=RequestMethod.POST)
    public @ResponseBody Map uploadFile(HttpServletRequest request, Model model) throws Exception {
		Map map = new HashMap();
		String uploadPath = EgovProperties.getProperty("gisAssetsCd.fileStorePath");
		try {
			List<GamFileServiceVo> list = GamFileUploadUtil.uploadFiles(request, uploadPath, gamGisAssetFileIdGnrService);

			map.put("resultCode", "0");
			map.put("result", list);
		} catch(ParseException e){
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}
		catch(Exception e) {
			map.put("resultCode", "-1");
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.upload"));
		}

		return map;
	}

    @RequestMapping("/code/assets/getAssetImage.do")
    public void getImage(final HttpServletRequest request, HttpServletResponse response) throws Exception {
		GamFileServiceVo gamFileServiceVo = new GamFileServiceVo();
		String uploadPath = EgovProperties.getProperty("gisAssetsCd.fileStorePath");

		gamFileServiceVo.setPhyscalFileNm((String)request.getParameter("physicalFileNm"));

		GamFileUploadUtil.downloadImage(request, response, uploadPath, gamFileServiceVo);
    }

    @RequestMapping("/code/assets/getAssetDown.do")
    public void getDownload(final HttpServletRequest request, HttpServletResponse response) throws Exception {
		GamFileServiceVo gamFileServiceVo = new GamFileServiceVo();
		String uploadPath = EgovProperties.getProperty("gisAssetsCd.fileStorePath");

		gamFileServiceVo.setPhyscalFileNm((String)request.getParameter("physicalFileNm"));

		GamFileUploadUtil.downloadFile(request, response, uploadPath, gamFileServiceVo);
    }
}
