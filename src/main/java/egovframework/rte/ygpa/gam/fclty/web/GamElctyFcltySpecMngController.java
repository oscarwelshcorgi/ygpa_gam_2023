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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamGisPrtFcltyCdMngtService;
import egovframework.rte.ygpa.gam.fclty.service.GamElctyFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamElctyFcltySpecMngVO;

/**
 * 
 * @author HNJ
 * @since 2014. 11. 19.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 19.		HNJ		최초 생성 
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamElctyFcltySpecMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="gamElctyFcltySpecMngService")
    GamElctyFcltySpecMngService gamElctyFcltySpecMngService;
    
    @Resource(name="gamGisPrtFcltyCdMngtService")
    GamGisPrtFcltyCdMngtService gamGisPrtFcltyCdMngtService;
    
    private final static String prtFcltySe = "E";    
	/**
     * 전기시설 관리화면호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/fclty/gamElctyFcltySpecMng.do")
    public String indexElctyFcltySpecMng(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fclty/GamElctyFcltySpecMng";
    }


	/**
	 * 전기시설관리목록 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/selectElctyFcltySpecMngList.do")
	public @ResponseBody Map selectElctyFcltySpecMngList(GamElctyFcltySpecMngVO searchVO) throws Exception {

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
		
		searchVO.setPrtFcltySe(prtFcltySe);
		List resultList = gamElctyFcltySpecMngService.selectElctyFcltySpecMngList(searchVO);
		int totCnt = gamElctyFcltySpecMngService.selectElctyFcltySpecMngListTotCnt(searchVO);
		
        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }


	/**
	 * 전기 시설관리 상세
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/selectElctyFcltySpecMngDetail.do")
    public @ResponseBody Map selectElctyFcltySpecMngDetail(@RequestParam Map searchVO) throws Exception {
    	Map map = new HashMap();
    	EgovMap result=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
        	result = gamElctyFcltySpecMngService.selectElctyFcltySpecMngDetail(searchVO);
    	} catch(IOException e){
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
	 * 전기 시설관리 등록
	 * @param fcltyItem
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/insertElctyFcltySpecMngDetail.do")
    public @ResponseBody Map insertElctyFcltySpecMngDetail(@RequestParam Map fcltyItem) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map map = new HashMap();
    	ObjectMapper mapper = new ObjectMapper();
    	Map fcltyManageVO = new HashMap();
    	List<HashMap<String,String>> insertFileList=null;
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	fcltyManageVO = mapper.readValue((String)fcltyItem.get("fcltyManageVO"),
    		    new TypeReference<HashMap<String,String>>(){});
    	
    	insertFileList = mapper.readValue((String)fcltyItem.get("insertFileList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

    	fcltyManageVO.put("prtFcltySe",prtFcltySe);
    	fcltyManageVO.put("regUsr",user.getId());
    	
    	try {
    		gamElctyFcltySpecMngService.insertElctyFcltySpecMngDetail(fcltyManageVO, insertFileList);
    		
    		map.put("resultCode", 0);			// return ok
    		map.put("fcltsMngNo", fcltyManageVO.get("fcltsMngNo"));
    		map.put("gisPrtFcltySeq", fcltyManageVO.get("gisPrtFcltySeq"));
            map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

      	return map;		
	}
	
	
	/**
	 * 전기 시설관리 수정
	 * @param fcltyMngtList
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/updateElctyFcltySpecMngDetail.do")
	public @ResponseBody Map updateElctyFcltySpecMngDetail(@RequestParam Map fcltyMngtList) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map map = new HashMap();
    	ObjectMapper mapper = new ObjectMapper();
    	
    	Map fcltyManageVO = new HashMap();
    	List<HashMap<String,String>> insertFileList=null;
    	List<HashMap<String,String>> updateFileList=null;
    	List<HashMap<String,String>> deleteFileList=null;
    	List<Map<String,String>> userList=null;
    	
    	Map<String, String> userMap = new HashMap<String, String>();
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	fcltyManageVO = mapper.readValue((String)fcltyMngtList.get("fcltyManageVO"),
    		    new TypeReference<HashMap<String,String>>(){});
    	
    	insertFileList = mapper.readValue((String)fcltyMngtList.get("insertFileList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});
    	updateFileList = mapper.readValue((String)fcltyMngtList.get("updateFileList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});
    	deleteFileList = mapper.readValue((String)fcltyMngtList.get("deleteFileList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

    	userList = new ArrayList();
		userMap.put("id",  user.getId());
		userList.add(userMap);

		Map<String,Object> mergeMap = new HashMap<String,Object>();
		
		insertFileList.addAll(updateFileList);

		mergeMap.put("CU", insertFileList);
		mergeMap.put("D", deleteFileList);
		mergeMap.put("USER", userList);
		
		fcltyManageVO.put("prtFcltySe",prtFcltySe);
		fcltyManageVO.put("updUsr",user.getId());
    	
    	try {
    		gamElctyFcltySpecMngService.updateElctyFcltySpecMngDetail(fcltyManageVO, mergeMap);
    		
    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch(IOException e) {
			
		}catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

      	return map;		
	}
	
	
	/**
     * 전기 시설관리 삭제
     * @param deleteMap
     * @return map
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/deleteElctyFcltySpecMngDetail.do")
    public @ResponseBody Map deleteElctyFcltySpecMngDetail(@RequestParam Map deleteMap) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		gamElctyFcltySpecMngService.deleteElctyFcltySpecMngDetail(deleteMap);
    		
    		map.put("resultCode", 0);			// return ok
            map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch(IOException e) {
			
		}catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

      	return map;		
	}
	
	
	/**
	 * 전기시설 파일 목록
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/selectElctyFcltySpecFileList.do")
    public @ResponseBody Map selectElctyFcltySpecFileList(GamElctyFcltySpecMngVO searchVO) throws Exception {
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

		List resultList = gamElctyFcltySpecMngService.selectElctyFcltySpecFileList(searchVO);
		int totCnt = gamElctyFcltySpecMngService.selectElctyFcltySpecFileListTotCnt(searchVO);
		
        paginationInfo.setTotalRecordCount(totCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
	}

	
	/**
	 * 전기시설제원관리 리스트를 엑셀로 다운로드한다.
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/fclty/selectElctyFcltySpecMngListExcel.do", method=RequestMethod.POST)
    public @ResponseBody ModelAndView selectElctyFcltySpecMngListExcel(@RequestParam Map<String, Object> excelParam) throws Exception {
		Map map = new HashMap();
		List header;
		ObjectMapper mapper = new ObjectMapper();

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    		return new ModelAndView("gridExcelView", "gridResultMap", map);
    	}

    	// 환경설정
    	/** EgovPropertyService */
    	GamElctyFcltySpecMngVO searchVO= new GamElctyFcltySpecMngVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.
		// 조회 조건
		searchVO = mapper.convertValue(excelParam, GamElctyFcltySpecMngVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		
		searchVO.setPrtFcltySe(prtFcltySe);
		List resultList = gamElctyFcltySpecMngService.selectElctyFcltySpecMngList(searchVO);
		

    	map.put("resultList", resultList);
    	map.put("header", header);

    	return new ModelAndView("gridExcelView", "gridResultMap", map);
    }
	
}
