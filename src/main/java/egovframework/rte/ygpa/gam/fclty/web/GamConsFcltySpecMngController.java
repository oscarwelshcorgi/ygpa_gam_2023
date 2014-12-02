/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.web;

import java.util.ArrayList;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamGisPrtFcltyCdMngtService;
import egovframework.rte.ygpa.gam.fclty.service.GamConsFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamConsFcltySpecMngService;

/**
 *
 * @author HNJ
 * @since 2014. 11. 6.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 6.	HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamConsFcltySpecMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	@Resource(name = "gamConsFcltySpecMngService")
	protected GamConsFcltySpecMngService gamConsFcltySpecMngService;
	
	@Resource(name = "gamGisPrtFcltyCdMngtService")
	protected GamGisPrtFcltyCdMngtService gamGisPrtFcltyCdMngtService;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    private final static String prtFcltySe = "A";

	/**
     * 건축시설 관리화면호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/fclty/gamConstFcltySpecMng.do")
    String indexConstFcltySpecMng(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fclty/GamConsFcltySpecMng";
    }


	
	/**
	 * 건축시설목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamConstFcltySpecMngList.do")
	@ResponseBody Map<String, Object> selectFcltySpecMngList(GamConsFcltySpecMngVO searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

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
		searchVO.setPrtFcltySe(prtFcltySe);
		List fcltyMngtList = gamConsFcltySpecMngService.selectFcltySpecMngList(searchVO);

        int totCnt = gamConsFcltySpecMngService.selectFcltySpecMngListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", fcltyMngtList);
    	map.put("searchOption", searchVO);

    	return map;
    }


	/**
	 * 건축시설 파일 목록
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamConstFcltySpecFileList.do")
	@ResponseBody Map<String, Object> selectFcltySpecMngFileList(GamConsFcltySpecMngVO searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

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
		searchVO.setPrtFcltySe(prtFcltySe);

		List<?> fcltyMngtFileList = gamConsFcltySpecMngService.selectFcltySpecMngFileList(searchVO);
		int totCnt = gamConsFcltySpecMngService.selectFcltySpecMngFileListTotCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
		map.put("totalCount", totCnt);
		map.put("resultList", fcltyMngtFileList);
		map.put("searchOption", searchVO);

		return map;
	}


	/**
	 * 건축 시설관리 등록
	 * @param fcltyManageVO
	 * @param bindingResult
	 * @param cmd
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamConstFcltySpecInsert.do")
    @ResponseBody Map<String, Object> insertFclty(@RequestParam Map<String, Object> fcltyItem) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	String fcltsMngNo, gisAssetsPrtAtCode, gisAssetsCd, gisAssetsSubCd, gisPrtFcltyCd, gisPrtFcltySeq;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	fcltyItem.put("regUsr",user.getId());
    	fcltyItem.put("prtFcltySe",prtFcltySe);
    	
    	gisPrtFcltySeq = gamGisPrtFcltyCdMngtService.selectNextFcltySeq(fcltyItem);
		
		gisAssetsPrtAtCode = (String) fcltyItem.get("gisAssetsPrtAtCode");
		gisAssetsCd = (String) fcltyItem.get("gisAssetsCd");
		gisAssetsSubCd = (String) fcltyItem.get("gisAssetsSubCd");
		gisPrtFcltyCd = (String) fcltyItem.get("gisPrtFcltyCd");
		
		fcltsMngNo = gisAssetsPrtAtCode + gisAssetsCd + gisAssetsSubCd + gisPrtFcltyCd + gisPrtFcltySeq + prtFcltySe;
		fcltyItem.put("gisPrtFcltySeq",gisPrtFcltySeq);

    	try {

    		// GIS 항만시설코드 입력
    		gamGisPrtFcltyCdMngtService.insertGisPrtFclty(fcltyItem);
    		
    		// 건축시설 제원 입력
    		gamConsFcltySpecMngService.insertFcltySpec(fcltyItem);

    		map.put("resultCode", 0);			// return ok
    		map.put("fcltsMngNo", fcltsMngNo);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

      	return map;
    }


	/**
	 * 건축 시설관리 상세
	 * @param fcltyManageVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamConstFcltySpecDetail.do")
    @ResponseBody Map<String, Object> fcltyMngSelectView(@RequestParam Map fcltyManageVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	String fcltsMngNo;
    	EgovMap result=null;
    	EgovMap specResult=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
        	result = gamConsFcltySpecMngService.fcltyMngSelectView(fcltyManageVO);
        	
        	System.out.print("test : " + result);
        	fcltsMngNo = (String) result.get("fcltsMngNo");
        	fcltyManageVO.put("fcltsMngNo", fcltsMngNo);
        	
        	specResult = gamConsFcltySpecMngService.fcltySpecMngSelectView(fcltyManageVO);
    	}
    	catch(Exception e) {
            map.put("resultCode", 2);
            map.put("resultMsg", e.getMessage());
            return map;
    	}

        map.put("resultCode", 0);
        map.put("result", result);
        map.put("specResult", specResult);

        return map;
    }


	/**
	 * 건축 시설관리 수정
	 * @param fcltyManageVO
	 * @param bindingResult
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping("/fclty/gamConstFcltySpecUpdate.do")
    @ResponseBody Map<String, Object> updateFclty(@RequestParam Map fcltyMngtList)throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	String fcltsMngNo;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	fcltyMngtList.put("updUsr", user.getId());
    	fcltyMngtList.put("prtFcltySe",prtFcltySe);

    	try {
    		
    		// GIS 항만시설코드 수정
    		gamGisPrtFcltyCdMngtService.updateGisPrtFclty(fcltyMngtList);
    		
    		// 건축시설 제원 수정
    		gamConsFcltySpecMngService.updateFcltySpec(fcltyMngtList);
    		
    		fcltsMngNo = (String) fcltyMngtList.get("fcltsMngNo");
    		
    		map.put("resultCode", 0);			// return ok
    		map.put("fcltsMngNo", fcltsMngNo);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

    	return map;
    }


    /**
     * 건축 시설관리 삭제
     * @param fcltyManageVO
     * @return map
     * @throws Exception
     */
    @RequestMapping("/fclty/gamConstFcltySpecDelete.do")
    @ResponseBody Map<String, Object> deleteFclty(@RequestParam Map fcltyManageVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	fcltyManageVO.put("prtFcltySe",prtFcltySe);

    	try {
    		
    		// GIS 항만시설코드 삭제
    		gamGisPrtFcltyCdMngtService.deleteGisPrtFclty(fcltyManageVO);
    		
    		// 건축시설 제원 삭제
    		gamConsFcltySpecMngService.deleteFcltySpec(fcltyManageVO);
    		
    		// 건축시설 층별제원 삭제
    		gamConsFcltySpecMngService.deleteFcltyFloorSpecData(fcltyManageVO);
    		
    		// 건축시설 첨부파일 삭제
    		gamConsFcltySpecMngService.deleteFcltyTotalFile(fcltyManageVO);

            map.put("resultCode", 0);
            map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

    	return map;
    }

	
	
	/**
	 * 건축시설 파일 목록
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamFcltyinfo9.do")
	@ResponseBody Map<String, Object> selectFcltyinfo9List(GamConsFcltySpecMngVO searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

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
		searchVO.setPrtFcltySe(prtFcltySe);

		List<ComDefaultVO> fcltyinfo9List = gamConsFcltySpecMngService.selectFcltyinfo9List(searchVO);
		int totCnt = gamConsFcltySpecMngService.selectFcltyinfo9ListTotCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
		map.put("totalCount", totCnt);
		map.put("resultList", fcltyinfo9List);
		map.put("searchOption", searchVO);

		return map;
	}
	

	/**
     * /건축물현황/층수 추가편집 팝업
     *
     * 건축물 층수 팝업
     */
	@RequestMapping(value="/popup/fcltySpecinfo9ListPopup.do")
    String showFloorSpecInfo(@RequestParam Map fcltyinfo9List, ModelMap model) throws Exception {

		model.addAttribute("fcltyinfo9List", fcltyinfo9List);
    	return "/ygpa/gam/fclty/GamPopupFcltySpecPopup";
    }
	
	
	/**
	 * 건축시설층별제원 입력
	 * @param fcltyFloorSpecList
	 * @return map
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
	@RequestMapping("/fclty/gamFcltyFloorSpecSave.do")
    @ResponseBody Map<String, Object> insertFcltyFloorSpecList(@RequestParam Map fcltyFloorSpecList)throws Exception {

    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String, String> userMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		
		int resultCode;
		String resultMsg;

    	List<HashMap<String,String>> insertList=null;
    	List<HashMap<String,String>> updateList=null;
    	List<HashMap<String,String>> deleteList=null;
    	List<Map<String,String>> userList=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	
    	insertList = mapper.readValue((String)fcltyFloorSpecList.get("insertList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		updateList = mapper.readValue((String)fcltyFloorSpecList.get("updateList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		deleteList = mapper.readValue((String)fcltyFloorSpecList.get("deleteList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

		userList = new ArrayList();
		userMap.put("id",  loginVO.getId());
		userList.add(userMap);

		Map<String,Object> mergeMap = new HashMap<String,Object>();

		insertList.addAll(updateList);

		mergeMap.put("CU", insertList);
		mergeMap.put("D", deleteList);
		mergeMap.put("USER", userList);

    	try {
    		
    		gamConsFcltySpecMngService.mergeFcltyFloorMngt(mergeMap);
    		
    		map.put("resultCode", 0);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

    	return map;
    }
    
    @RequestMapping(value="/fclty/mergeGamConstFcltySpecFileMngt.do")
	@ResponseBody Map<String, Object> mergeGamGisAssetFileMngt(@RequestParam Map<String, Object> dataList) throws Exception {

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

		gamConsFcltySpecMngService.mergeFcltyFileMngt(mergeMap);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));

		return map;
	}

}