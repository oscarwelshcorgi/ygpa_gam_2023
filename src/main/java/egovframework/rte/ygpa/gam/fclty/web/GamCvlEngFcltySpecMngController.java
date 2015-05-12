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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamCvlEngFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamCvlEngFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMntnRprDtlsVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 17.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamCvlEngFcltySpecMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamCvlEngFcltySpecMngService")
	private GamCvlEngFcltySpecMngService gamCvlEngFcltySpecMngService;

	@RequestMapping(value="/fclty/gamCvlEngFcltySpecMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//List fcltsClCdList = gamCvlEngFcltySpecMngService.selectFcltsClCdList();

		//model.addAttribute("fcltsClCdList", fcltsClCdList);
		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/fclty/GamCvlEngFcltySpecMng";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectCvlEngFcltySpecMngList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectCvlEngFcltySpecMngList(GamCvlEngFcltySpecMngVO searchVO) throws Exception {

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

		GamCvlEngFcltySpecMngVO resultSum = gamCvlEngFcltySpecMngService.selectCvlEngFcltySpecMngListSum(searchVO);
		List resultList = gamCvlEngFcltySpecMngService.selectCvlEngFcltySpecMngList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", resultSum.getTotalCount());
		map.put("sumYardAr", resultSum.getSumYardAr());
		map.put("sumBerth", resultSum.getSumBerth());
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/fclty/gamInsertCvlEngFcltySpecMng.do")
	@ResponseBody Map<String, Object> gamInsertCvlEngFcltySpecMng(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamCvlEngFcltySpecMngVO.setRegUsr((String)user.getId());
			gamCvlEngFcltySpecMngService.insertCvlEngFcltySpecMng(gamCvlEngFcltySpecMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamUpdateCvlEngFcltySpecMng.do")
	@ResponseBody Map<String, Object> gamUpdateCvlEngFcltySpecMng(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamCvlEngFcltySpecMngVO.setUpdUsr((String)user.getId());
			gamCvlEngFcltySpecMngService.updateCvlEngFcltySpecMng(gamCvlEngFcltySpecMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamDeleteCvlEngFcltySpecMng.do")
	@ResponseBody Map<String, Object> gamDeleteCvlEngFcltySpecMng(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamCvlEngFcltySpecMngService.deleteCvlEngFcltySpecMng(gamCvlEngFcltySpecMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectCvlEngFcltySpecMngPk.do")
	@ResponseBody Map<String, Object> gamSelectCvlEngFcltySpecMngPk(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamCvlEngFcltySpecMngService.selectCvlEngFcltySpecMngPk(gamCvlEngFcltySpecMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectCvlEngFcltySpecMngMaxGisPrtFcltySeq.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectCvlEngFcltySpecMngMaxGisPrtFcltySeq(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) throws Exception {

		String sMaxGisPrtFcltySeq;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sMaxGisPrtFcltySeq = gamCvlEngFcltySpecMngService.selectCvlEngFcltySpecMngMaxGisPrtFcltySeq(gamCvlEngFcltySpecMngVO);

		map.put("resultCode", 0);
		map.put("sMaxGisPrtFcltySeq", sMaxGisPrtFcltySeq);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamExcelDownloadCvlEngFcltySpecMng.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadCvlEngFcltySpecMng(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamCvlEngFcltySpecMngVO searchVO= new GamCvlEngFcltySpecMngVO();
		searchVO = mapper.convertValue(excelParam, GamCvlEngFcltySpecMngVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamCvlEngFcltySpecMngService.selectCvlEngFcltySpecMngList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectCvlEngFcltySpecMngFcltsMngGroupNm.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectCvlEngFcltySpecMngFcltsMngGroupNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sFcltsMngGroupNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sFcltsMngGroupNm = gamCvlEngFcltySpecMngService.selectFcltsMngGroupNm(searchVO);

		map.put("resultCode", 0);
		map.put("sFcltsMngGroupNm", sFcltsMngGroupNm);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectCvlEngFcltySpecMngEntrpsNm.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectCvlEngFcltySpecMngEntrpsNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sEntrpsNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sEntrpsNm = gamCvlEngFcltySpecMngService.selectEntrpsNm(searchVO);

		map.put("resultCode", 0);
		map.put("sEntrpsNm", sEntrpsNm);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectCvlEngFcltySpecMngFcltsClCdNm.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectCvlEngFcltySpecMngFcltsClCdNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sFcltsClCdNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sFcltsClCdNm = gamCvlEngFcltySpecMngService.selectFcltsClCdNm(searchVO);

		map.put("resultCode", 0);
		map.put("sFcltsClCdNm", sFcltsClCdNm);

		return map;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectCvlEngFcltySpecMngAtchFileDirList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectCvlEngFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamCvlEngFcltySpecMngService.selectCvlEngFcltySpecMngAtchFileDirList(gamAtchFileDirMngVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectCvlEngFcltySpecMngAtchFileDirPk.do")
	@ResponseBody Map<String, Object> gamSelectCvlEngFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamCvlEngFcltySpecMngService.selectCvlEngFcltySpecMngAtchFileDirPk(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamInsertCvlEngFcltySpecMngAtchFileDir.do")
	@ResponseBody Map<String, Object> gamInsertCvlEngFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();
		String sNewNo;

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			sNewNo = gamCvlEngFcltySpecMngService.selectCvlEngFcltySpecMngAtchFileDirNewNo(gamAtchFileDirMngVO);

			gamAtchFileDirMngVO.setDirNo(sNewNo);
			gamAtchFileDirMngVO.setRegUsr((String)user.getId());
			gamCvlEngFcltySpecMngService.insertCvlEngFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamUpdateCvlEngFcltySpecMngAtchFileDir.do")
	@ResponseBody Map<String, Object> gamUpdateCvlEngFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamAtchFileDirMngVO.setUpdUsr((String)user.getId());
			gamCvlEngFcltySpecMngService.updateCvlEngFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamDeleteCvlEngFcltySpecMngAtchFileDir.do")
	@ResponseBody Map<String, Object> gamDeleteCvlEngFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamCvlEngFcltySpecMngService.deleteCvlEngFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectCvlEngFcltySpecMngAtchFileDirNewNo.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectCvlEngFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		String sNewNo;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sNewNo = gamCvlEngFcltySpecMngService.selectCvlEngFcltySpecMngAtchFileDirNewNo(gamAtchFileDirMngVO);

		map.put("resultCode", 0);
		map.put("sNewNo", sNewNo);

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectCvlEngFcltySpecMngAtchFileDirLowerDataCnt.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectCvlEngFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamCvlEngFcltySpecMngService.selectCvlEngFcltySpecMngAtchFileDirLowerDataCnt(gamAtchFileDirMngVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectCvlEngFcltySpecMngFcltsAtchFileList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectCvlEngFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {

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

		List resultList = gamCvlEngFcltySpecMngService.selectCvlEngFcltySpecMngFcltsAtchFileList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/fclty/gamInsertCvlEngFcltySpecMngFcltsAtchFile.do")
	@ResponseBody Map<String, Object> gamInsertCvlEngFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();
		String sNewNo;

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			sNewNo = gamCvlEngFcltySpecMngService.selectCvlEngFcltySpecMngFcltsAtchFileNewNo(gamFcltsAtchFileMngVO);

			gamFcltsAtchFileMngVO.setAtchFileNo(sNewNo);
			gamFcltsAtchFileMngVO.setRegUsr((String)user.getId());
			gamCvlEngFcltySpecMngService.insertCvlEngFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("atchFileNo", sNewNo);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamUpdateCvlEngFcltySpecMngFcltsAtchFile.do")
	@ResponseBody Map<String, Object> gamUpdateCvlEngFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsAtchFileMngVO.setUpdUsr((String)user.getId());
			gamCvlEngFcltySpecMngService.updateCvlEngFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamDeleteCvlEngFcltySpecMngFcltsAtchFile.do")
	@ResponseBody Map<String, Object> gamDeleteCvlEngFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamCvlEngFcltySpecMngService.deleteCvlEngFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamDeleteCvlEngFcltySpecMngFcltsAtchFileMulti.do")
	@ResponseBody Map<String, Object> gamDeleteCvlEngFcltySpecMngFcltsAtchFileMulti(@RequestParam Map deleteVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamCvlEngFcltySpecMngService.deleteCvlEngFcltySpecMngFcltsAtchFileMulti(deleteVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectCvlEngFcltySpecMngFcltsAtchFilePk.do")
	@ResponseBody Map<String, Object> gamSelectCvlEngFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamCvlEngFcltySpecMngService.selectCvlEngFcltySpecMngFcltsAtchFilePk(gamFcltsAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectCvlEngFcltySpecMngFcltsAtchFileNewNo.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectCvlEngFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		String sNewNo;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sNewNo = gamCvlEngFcltySpecMngService.selectCvlEngFcltySpecMngFcltsAtchFileNewNo(gamFcltsAtchFileMngVO);

		map.put("resultCode", 0);
		map.put("sNewNo", sNewNo);

		return map;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectCvlEngFcltySpecMngMntnRprDtlsList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectCvlEngFcltySpecMngMntnRprDtlsList(GamMntnRprDtlsVO searchVO) throws Exception {

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

		List resultList = gamCvlEngFcltySpecMngService.selectCvlEngFcltySpecMngMntnRprDtlsList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

}
