/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltyMngtService;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyManageVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyMngtService;

/**
 *
 * @author kok
 * @since 2014. 2. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 3.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamCivilFcltyMngtController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	@Resource(name = "gamCivilFcltyMngtService")
	protected GamCivilFcltyMngtService gamCivilFcltyMngtService;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    private final static String prtFcltySe = "C";

	/**
     * 토목시설 관리화면호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/fclty/gamCivilFcltyMngt.do")
    String indexCivilFcltyMngt(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fclty/GamCivilFcltyMngt";
    }


	/**
	 * 토목시설 조회화면호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamCivilFcltyInqire.do")
	String indexCivilFcltyInqire(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
		return "/ygpa/gam/fclty/GamCivilFcltyInqire";
	}



	/**
	 * 토목시설목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamCivilFcltyMngtList.do")
	@ResponseBody Map<String, Object> selectFcltyMngtList(GamFcltyManageVO searchVO)throws Exception {

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
		List<ComDefaultVO> fcltyMngtList = gamCivilFcltyMngtService.selectCivilFcltyMngtList(searchVO);
        int totCnt = gamCivilFcltyMngtService.selectCivilFcltyMngtListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", fcltyMngtList);
    	map.put("searchOption", searchVO);

    	return map;
    }


	/**
	 * 토목시설 파일 목록
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamCivilFcltyPhotoList.do")
	@ResponseBody Map<String, Object> selectFcltyMngtPhotoList(GamFcltyManageVO searchVO)throws Exception {

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

		List<ComDefaultVO> fcltyMngtPhotoList = gamCivilFcltyMngtService.selectCivilFcltyMngtPhotoList(searchVO);
		int totCnt = gamCivilFcltyMngtService.selectCivilFcltyMngtPhotoListTotCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
		map.put("totalCount", totCnt);
		map.put("resultList", fcltyMngtPhotoList);
		map.put("searchOption", searchVO);

		return map;
	}


	/**
	 * 토목 시설관리 등록
	 * @param fcltyManageVO
	 * @param bindingResult
	 * @param cmd
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamCivilFcltyInsert.do")
    @ResponseBody Map<String, Object> insertFclty(@RequestParam Map<String, Object> fcltyItem) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	fcltyItem.put("USERID",user.getId());
    	fcltyItem.put("prtFcltySe",prtFcltySe);

    	try {
    		gamCivilFcltyMngtService.insertCivilFclty(fcltyItem);

    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

      	return map;
    }


	/**
	 * 토목 시설관리 상세
	 * @param fcltyManageVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamCivilFcltyDetail.do")
    @ResponseBody Map<String, Object> fcltyMngSelectView(@RequestParam Map fcltyManageVO) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	EgovMap result=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
        	result = gamCivilFcltyMngtService.CivilfcltyMngSelectView(fcltyManageVO);
    	} catch(IOException e) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
    	}
    	catch(Exception e) {
            map.put("resultCode", 2);
            map.put("resultMsg", e.getMessage());
            return map;
    	}

        map.put("resultCode", 0);
        map.put("result", result);

        return map;
    }

	/**
	 * 토목 시설관리 수정
	 * @param fcltyManageVO
	 * @param bindingResult
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping("/fclty/gamCivilFcltyUpdate.do")
    @ResponseBody Map<String, Object> updateFclty(@RequestParam Map fcltyMngtList)throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	fcltyMngtList.put("USERID", user.getId());
    	fcltyMngtList.put("prtFcltySe",prtFcltySe);

    	try {
    		gamCivilFcltyMngtService.updateCivilFclty(fcltyMngtList);
    		map.put("resultCode", 0);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

    	return map;
    }


    /**
     * 토목 시설관리 삭제
     * @param fcltyManageVO
     * @return map
     * @throws Exception
     */
    @RequestMapping("/fclty/gamCivilFcltyDelete.do")
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
    		gamCivilFcltyMngtService.deleteCivilFclty(fcltyManageVO);

            map.put("resultCode", 0);
            map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));

		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

    	return map;
    }

	@RequestMapping(value="/fclty/mergeGamCivilFcltyPhotoMngt.do")
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

		gamCivilFcltyMngtService.mergeCivilFcltyPhotoMngt(mergeMap, this.prtFcltySe);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));

		return map;
	}

}