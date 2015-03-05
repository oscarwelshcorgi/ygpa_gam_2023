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
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySpecMngVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 3. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 3.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamMachFcltySpecMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamMachFcltySpecMngService")
	private GamMachFcltySpecMngService gamMachFcltySpecMngService;

	@RequestMapping(value="/fclty/gamMachFcltySpecMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//List fcltsClCdList = gamMachFcltySpecMngService.selectFcltsClCdList();

		//model.addAttribute("fcltsClCdList", fcltsClCdList);
		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/fclty/GamMachFcltySpecMng";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecMngList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecMngList(GamMachFcltySpecMngVO searchVO) throws Exception {

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

		GamMachFcltySpecMngVO resultSum = gamMachFcltySpecMngService.selectMachFcltySpecMngListSum(searchVO);
		List resultList = gamMachFcltySpecMngService.selectMachFcltySpecMngList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", resultSum.getTotalCount());
		map.put("sumMfcAmt", resultSum.getSumMfcAmt());
		map.put("sumContrAmt", resultSum.getSumContrAmt());
		map.put("resultList", resultList);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecMngCvlEngStatusList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecMngCvlEngStatusList(GamMachFcltySpecMngVO searchVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamMachFcltySpecMngService.selectMachFcltySpecMngCvlEngStatusList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/fclty/gamInsertMachFcltySpecMng.do")
	@ResponseBody Map<String, Object> gamInsertMachFcltySpecMng(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamMachFcltySpecMngVO.setRegUsr((String)user.getId());
			gamMachFcltySpecMngService.insertMachFcltySpecMng(gamMachFcltySpecMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamUpdateMachFcltySpecMng.do")
	@ResponseBody Map<String, Object> gamUpdateMachFcltySpecMng(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamMachFcltySpecMngVO.setUpdUsr((String)user.getId());
			gamMachFcltySpecMngService.updateMachFcltySpecMng(gamMachFcltySpecMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamDeleteMachFcltySpecMng.do")
	@ResponseBody Map<String, Object> gamDeleteMachFcltySpecMng(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamMachFcltySpecMngService.deleteMachFcltySpecMng(gamMachFcltySpecMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectMachFcltySpecMngPk.do")
	@ResponseBody Map<String, Object> gamSelectMachFcltySpecMngPk(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamMachFcltySpecMngService.selectMachFcltySpecMngPk(gamMachFcltySpecMngVO);

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
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecMngMaxGisPrtFcltySeq.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecMngMaxGisPrtFcltySeq(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) throws Exception {

		String sMaxGisPrtFcltySeq;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sMaxGisPrtFcltySeq = gamMachFcltySpecMngService.selectMachFcltySpecMngMaxGisPrtFcltySeq(gamMachFcltySpecMngVO);

		map.put("resultCode", 0);
		map.put("sMaxGisPrtFcltySeq", sMaxGisPrtFcltySeq);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamExcelDownloadMachFcltySpecMng.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadMachFcltySpecMng(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamMachFcltySpecMngVO searchVO= new GamMachFcltySpecMngVO();
		searchVO = mapper.convertValue(excelParam, GamMachFcltySpecMngVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamMachFcltySpecMngService.selectMachFcltySpecMngList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecMngFcltsMngGroupNm.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecMngFcltsMngGroupNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sFcltsMngGroupNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sFcltsMngGroupNm = gamMachFcltySpecMngService.selectFcltsMngGroupNm(searchVO);

		map.put("resultCode", 0);
		map.put("sFcltsMngGroupNm", sFcltsMngGroupNm);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecMngEntrpsNm.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecMngEntrpsNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sEntrpsNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sEntrpsNm = gamMachFcltySpecMngService.selectEntrpsNm(searchVO);

		map.put("resultCode", 0);
		map.put("sEntrpsNm", sEntrpsNm);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecMngFcltsClCdNm.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecMngFcltsClCdNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sFcltsClCdNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sFcltsClCdNm = gamMachFcltySpecMngService.selectFcltsClCdNm(searchVO);

		map.put("resultCode", 0);
		map.put("sFcltsClCdNm", sFcltsClCdNm);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecMngGisPrtFcltyNm.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecMngGisPrtFcltyNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sGisPrtFcltyNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sGisPrtFcltyNm = gamMachFcltySpecMngService.selectGisPrtFcltyNm(searchVO);

		map.put("resultCode", 0);
		map.put("sGisPrtFcltyNm", sGisPrtFcltyNm);

		return map;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecMngAtchFileDirList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamMachFcltySpecMngService.selectMachFcltySpecMngAtchFileDirList(gamAtchFileDirMngVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectMachFcltySpecMngAtchFileDirPk.do")
	@ResponseBody Map<String, Object> gamSelectMachFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamMachFcltySpecMngService.selectMachFcltySpecMngAtchFileDirPk(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamInsertMachFcltySpecMngAtchFileDir.do")
	@ResponseBody Map<String, Object> gamInsertMachFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

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
			sNewNo = gamMachFcltySpecMngService.selectMachFcltySpecMngAtchFileDirNewNo(gamAtchFileDirMngVO);

			gamAtchFileDirMngVO.setDirNo(sNewNo);
			gamAtchFileDirMngVO.setRegUsr((String)user.getId());
			gamMachFcltySpecMngService.insertMachFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamUpdateMachFcltySpecMngAtchFileDir.do")
	@ResponseBody Map<String, Object> gamUpdateMachFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

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
			gamMachFcltySpecMngService.updateMachFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamDeleteMachFcltySpecMngAtchFileDir.do")
	@ResponseBody Map<String, Object> gamDeleteMachFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamMachFcltySpecMngService.deleteMachFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectMachFcltySpecMngAtchFileDirNewNo.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		String sNewNo;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sNewNo = gamMachFcltySpecMngService.selectMachFcltySpecMngAtchFileDirNewNo(gamAtchFileDirMngVO);

		map.put("resultCode", 0);
		map.put("sNewNo", sNewNo);

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectMachFcltySpecMngAtchFileDirLowerDataCnt.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamMachFcltySpecMngService.selectMachFcltySpecMngAtchFileDirLowerDataCnt(gamAtchFileDirMngVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecMngFcltsAtchFileList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {

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

		List resultList = gamMachFcltySpecMngService.selectMachFcltySpecMngFcltsAtchFileList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/fclty/gamInsertMachFcltySpecMngFcltsAtchFile.do")
	@ResponseBody Map<String, Object> gamInsertMachFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

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
			sNewNo = gamMachFcltySpecMngService.selectMachFcltySpecMngFcltsAtchFileNewNo(gamFcltsAtchFileMngVO);

			gamFcltsAtchFileMngVO.setAtchFileNo(sNewNo);
			gamFcltsAtchFileMngVO.setRegUsr((String)user.getId());
			gamMachFcltySpecMngService.insertMachFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);

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

	@RequestMapping(value="/fclty/gamUpdateMachFcltySpecMngFcltsAtchFile.do")
	@ResponseBody Map<String, Object> gamUpdateMachFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

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
			gamMachFcltySpecMngService.updateMachFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamDeleteMachFcltySpecMngFcltsAtchFile.do")
	@ResponseBody Map<String, Object> gamDeleteMachFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamMachFcltySpecMngService.deleteMachFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectMachFcltySpecMngFcltsAtchFilePk.do")
	@ResponseBody Map<String, Object> gamSelectMachFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamMachFcltySpecMngService.selectMachFcltySpecMngFcltsAtchFilePk(gamFcltsAtchFileMngVO);

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
	@RequestMapping(value="/fclty/gamSelectMachFcltySpecMngFcltsAtchFileNewNo.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectMachFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		String sNewNo;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sNewNo = gamMachFcltySpecMngService.selectMachFcltySpecMngFcltsAtchFileNewNo(gamFcltsAtchFileMngVO);

		map.put("resultCode", 0);
		map.put("sNewNo", sNewNo);

		return map;

	}

}
