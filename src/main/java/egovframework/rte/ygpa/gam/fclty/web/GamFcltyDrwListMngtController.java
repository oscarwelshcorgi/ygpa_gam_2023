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
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwDtaFVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwInfoFVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwMngtService;

/**
 *
 * @author kok
 * @since 2014. 2. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 10.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltyDrwListMngtController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	@Resource(name = "gamFcltyDrwMngtService")
	protected GamFcltyDrwMngtService gamFcltyDrwMngtService;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;




	/**
	 * 도면시설 관리화면호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamDrwListMngt.do")
	String indexDrwListMngt(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
		return "/ygpa/gam/fclty/GamDrwListMngt";
	}


	/**
	 * 도면 정보 목록
	 * @param searchVO
	 * @param drwLstRegistYear
	 * @param drwLstSeq
	 * @param drwLstNm
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamDrwListMngtList.do")
	@ResponseBody Map<String, Object> selectDrwListMngtList(GamFcltyDrwInfoFVO searchVO,
			@RequestParam("searchDrwLstRegistYear") String drwLstRegistYear, @RequestParam("searchDrwLstSeq") String drwLstSeq, @RequestParam("searchDrwLstNm") String drwLstNm,
			@RequestParam("searchAuthnm") String authnm, @RequestParam("searchDeptCd") String deptCd)throws Exception {

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

		searchVO.setDrwLstRegistYear(drwLstRegistYear);
		searchVO.setDrwLstSeq(drwLstSeq);
		searchVO.setDrwLstNm(drwLstNm);
		searchVO.setAuthnm(authnm);
		searchVO.setDrwLstMngDeptCd(deptCd);

		/** List Data */
		List<GamFcltyDrwInfoFVO> drwMngtList = gamFcltyDrwMngtService.selectDrwListMngtList(searchVO);
		int totCnt = gamFcltyDrwMngtService.selectDrwListMngtListTotCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
		map.put("totalCount", totCnt);
		map.put("resultList", drwMngtList);
		map.put("searchOption", searchVO);

		return map;
	}


	/**
	 * 도면 파일 목록
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamDrwListPhotoList.do")
	@ResponseBody Map<String, Object> selectDrwListPhotoList(GamFcltyDrwDtaFVO searchVO)throws Exception {

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

		List<ComDefaultVO> DrwListPhotoList = gamFcltyDrwMngtService.selectDrwListPhotoList(searchVO);
		int totCnt = gamFcltyDrwMngtService.selectDrwListPhotoListTotCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);
		searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
		map.put("totalCount", totCnt);
		map.put("resultList", DrwListPhotoList);
		map.put("searchOption", searchVO);

		return map;
	}


	/**
	 * 도면 정보 저장
	 * @param drwListManageVO
	 * @param bindingResult
	 * @param cmd
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamDrwInfoListMngInsert.do")
	@ResponseBody Map<String, Object> insertDrwInfoListMng(@RequestParam Map<String, Object> drwListMngtList) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		drwListMngtList.put("USERID",user.getId());

    		gamFcltyDrwMngtService.insertDrwListMng(drwListMngtList);
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
	 * 도면 자료 삭제
	 * @param drwListManageVO
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping("/fclty/deleteDrwInfoMngt.do")
    @ResponseBody Map<String, Object> deleteDrwListMng(@RequestParam Map vo) throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {

    		gamFcltyDrwMngtService.deleteDrwListMng(vo);

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

    /**
	 * 도면 정보 merge
	 * @param drwListManageVO
	 * @return map
	 * @throws Exception
	 * @@@@@@@@@@@@@@@
	 */

    @RequestMapping(value="/fclty/mergeDrwInfoMngt.do")
	@ResponseBody Map<String, Object> mergeDrwInfoMngt(@RequestParam Map<String, Object> dataList) throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		Map<String,Object> map = new HashMap<String,Object>();
		Map<String, String> userMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

    	List<HashMap<String,String>> insertList=null;
    	List<HashMap<String,String>> updateList=null;
    	List<HashMap<String,String>> deleteList=null;
    	List<Map<String,String>> userList=null;
    	Map<String,String> detailMaster=null;

    	int resultCode = -1;
    	String resultMsg = "";

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	detailMaster = mapper.readValue((String)dataList.get("drwListMaster"),
    		    new TypeReference<HashMap<String,String>>(){});

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
		mergeMap.put("MST", detailMaster);
//
		gamFcltyDrwMngtService.mergeDrwInfoMngt(mergeMap);

        map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));

		return map;
	}

    /**
	 * 도면 목록관리 상세
	 * @param fcltyManageVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/selectDrwListDetail.do")
    @ResponseBody Map<String, Object> selectDrwListDetail(@RequestParam Map fcltyManageVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
    	Map detailMaster=null;
    	List detailFileList=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		detailMaster = gamFcltyDrwMngtService.selectDrwListDetailMaster(fcltyManageVO);
    		detailFileList = gamFcltyDrwMngtService.selectDrwListDetailFileList(fcltyManageVO);
    	} catch(IOException e) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
    	} 
    	catch(Exception e) {
            map.put("resultCode", 2);
            map.put("resultMsg", e.getMessage());
            return map;
    	}

        map.put("resultCode", 0);
        map.put("resultMaster", detailMaster);
        map.put("resultDetail", detailFileList);

        return map;

    }

}