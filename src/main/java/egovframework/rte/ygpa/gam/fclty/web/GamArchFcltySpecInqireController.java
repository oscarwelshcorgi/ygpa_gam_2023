/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.web;

import java.io.IOException;
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
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fclty.service.GamArchFcltySpecInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamArchFcltySpecInqireVO;
import egovframework.rte.ygpa.gam.fclty.service.GamArchFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMntnRprDtlsVO;

/**
 * 
 * @author LFIT
 * @since 2015. 3. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 5.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamArchFcltySpecInqireController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamArchFcltySpecInqireService")
	private GamArchFcltySpecInqireService gamArchFcltySpecInqireService;
	
	/**
	 *	건축시설 조회화면
	 */
	@RequestMapping(value="/fclty/gamArchFcltySpecInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
		model.addAttribute("windowId", windowId);
		return "/ygpa/gam/fclty/GamArchFcltySpecInqire";
	}

	/**
	 *	건축시설 목록조회
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectArchFcltySpecInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectArchFcltySpecInqireList(GamArchFcltySpecInqireVO searchVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
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

		List resultList = gamArchFcltySpecInqireService.selectArchFcltySpecInqireList(searchVO);
		GamArchFcltySpecInqireVO resultSum = gamArchFcltySpecInqireService.selectArchFcltySpecInqireListSum(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", resultSum.getTotalCount());
		map.put("sumAr", resultSum.getSumAr());
		map.put("sumArchAr", resultSum.getSumArchAr());
		map.put("sumPlotAr", resultSum.getSumPlotAr());
		map.put("resultList", resultList);

		return map;
	}
	
	/**
	 *	디렉토리 목록
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectArchFcltySpecInqireAtchFileDirList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectArchFcltySpecInqireAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamArchFcltySpecInqireService.selectArchFcltySpecInqireAtchFileDirList(gamAtchFileDirMngVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;
	}

	/**
	 *	첨부파일 목록
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectArchFcltySpecInqireFcltsAtchFileList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectArchFcltySpecInqireFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
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

		List resultList = gamArchFcltySpecInqireService.selectArchFcltySpecInqireFcltsAtchFileList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;
	}
	
	/**
	 *	refreshDirData
	 */
	@RequestMapping(value="/fclty/gamSelectArchFcltySpecInqireAtchFileDirPk.do")
	@ResponseBody Map<String, Object> gamSelectArchFcltySpecInqireAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamArchFcltySpecInqireService.selectArchFcltySpecInqireAtchFileDirPk(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;
	}
	
	/**
	 *	refreshFileData
	 */
	@RequestMapping(value="/fclty/gamSelectArchFcltySpecInqireFcltsAtchFilePk.do")
	@ResponseBody Map<String, Object> gamSelectArchFcltySpecInqireFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamArchFcltySpecInqireService.selectArchFcltySpecInqireFcltsAtchFilePk(gamFcltsAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}
	
	/**
	 *	시설물 관리 그룹 번호
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectArchFcltySpecInqireFcltsMngGroupNm.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectArchFcltySpecMngFcltsMngGroupNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sFcltsMngGroupNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sFcltsMngGroupNm = gamArchFcltySpecInqireService.selectFcltsMngGroupNm(searchVO);

		map.put("resultCode", 0);
		map.put("sFcltsMngGroupNm", sFcltsMngGroupNm);

		return map;

	}
	
	/**
	 *	엑셀다운로드
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamExcelDownloadArchFcltySpecInqire.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadArchFcltySpecInqire(@RequestParam Map<String, Object> excelParam) throws Exception {

		Map map = new HashMap();
		List header;
		ObjectMapper mapper = new ObjectMapper();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return new ModelAndView("gridExcelView", "gridResultMap", map);
		}

		header = mapper.readValue((String)excelParam.get("header"),
								  new TypeReference<List<HashMap<String,String>>>(){});
		excelParam.remove("header");

		GamArchFcltySpecInqireVO searchVO= new GamArchFcltySpecInqireVO();
		searchVO = mapper.convertValue(excelParam, GamArchFcltySpecInqireVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamArchFcltySpecInqireService.selectArchFcltySpecInqireList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectArchFcltySpecInqireMntnRprDtlsList.do", method=RequestMethod.POST)
	@ResponseBody Map selectArchFcltySpecInqireMntnRprDtlsList(GamMntnRprDtlsVO searchVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
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

		List resultList = gamArchFcltySpecInqireService.selectArchFcltySpecInqireMntnRprDtlsList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}
}
