/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySpecInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySpecInqireVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMntnRprDtlsVO;

/**
 * 
 * @author LFIT
 * @since 2015. 3. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 10.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamMachFcltySpecInqireController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamMachFcltySpecInqireService")
	private GamMachFcltySpecInqireService gamMachFcltySpecInqireService;

	@RequestMapping(value="/fclty/gamMachFcltySpecInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/fclty/GamMachFcltySpecInqire";

	}
	
	/**
	 *	목록조회
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecInqireList(GamMachFcltySpecInqireVO searchVO) throws Exception {

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

		GamMachFcltySpecInqireVO resultSum = gamMachFcltySpecInqireService.selectMachFcltySpecInqireListSum(searchVO);
		List resultList = gamMachFcltySpecInqireService.selectMachFcltySpecInqireList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", resultSum.getTotalCount());
		map.put("sumMfcAmt", resultSum.getSumMfcAmt());
		map.put("sumContrAmt", resultSum.getSumContrAmt());
		map.put("resultList", resultList);

		return map;

	}

	/**
	 *	??
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecInqireCvlEngStatusList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecInqireCvlEngStatusList(GamMachFcltySpecInqireVO searchVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamMachFcltySpecInqireService.selectMachFcltySpecInqireCvlEngStatusList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}
	
	/**
	 *	디렉토리 목록
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecInqireAtchFileDirList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecInqireAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamMachFcltySpecInqireService.gamSelectMachFcltySpecInqireAtchFileDirList(gamAtchFileDirMngVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}
	
	/**
	 *	첨부파일 목록
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecInqireFcltsAtchFileList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecInqireFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {

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

		List resultList = gamMachFcltySpecInqireService.selectMachFcltySpecInqireFcltsAtchFileList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}
	
	/**
	 *	refreshDirData
	 */
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecInqireAtchFileDirPk.do")
	@ResponseBody Map<String, Object> gamSelectMachFcltySpecInqireAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamMachFcltySpecInqireService.selectMachFcltySpecInqireAtchFileDirPk(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch(IOException e) {
			
		}catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}
	
	/**
	 *	refreshFileData
	 */
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecInqireFcltsAtchFilePk.do")
	@ResponseBody Map<String, Object> gamSelectMachFcltySpecInqireFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamMachFcltySpecInqireService.selectMachFcltySpecInqireFcltsAtchFilePk(gamFcltsAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch(IOException e) {
			
		}catch (Exception e) {
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
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecInqireFcltsMngGroupNm.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecMngFcltsMngGroupNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sFcltsMngGroupNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sFcltsMngGroupNm = gamMachFcltySpecInqireService.selectFcltsMngGroupNm(searchVO);

		map.put("resultCode", 0);
		map.put("sFcltsMngGroupNm", sFcltsMngGroupNm);

		return map;

	}
	
	/**
	 *	엑셀다운로드
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamExcelDownloadMachFcltySpecInqire.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadMachFcltySpecInqire(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamMachFcltySpecInqireVO searchVO= new GamMachFcltySpecInqireVO();
		searchVO = mapper.convertValue(excelParam, GamMachFcltySpecInqireVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamMachFcltySpecInqireService.selectMachFcltySpecInqireList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecInqireMntnRprDtlsList.do", method=RequestMethod.POST)
	@ResponseBody Map selectMachFcltySpecInqireMntnRprDtlsList(GamMntnRprDtlsVO searchVO) throws Exception {

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

		List resultList = gamMachFcltySpecInqireService.selectMachFcltySpecInqireMntnRprDtlsList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}
}
