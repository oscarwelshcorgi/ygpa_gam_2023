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
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamElectyFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamElectyFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMntnRprDtlsVO;

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
public class GamElectyFcltySpecMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamElectyFcltySpecMngService")
	private GamElectyFcltySpecMngService gamElectyFcltySpecMngService;

	@RequestMapping(value="/fclty/gamElectyFcltySpecMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//List fcltsClCdList = gamElectyFcltySpecMngService.selectFcltsClCdList();

		//model.addAttribute("fcltsClCdList", fcltsClCdList);
		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/fclty/GamElectyFcltySpecMng";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectElectyFcltySpecMngList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectElectyFcltySpecMngList(GamElectyFcltySpecMngVO searchVO) throws Exception {

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

		GamElectyFcltySpecMngVO resultSum = gamElectyFcltySpecMngService.selectElectyFcltySpecMngListSum(searchVO);
		List resultList = gamElectyFcltySpecMngService.selectElectyFcltySpecMngList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", resultSum.getTotalCount());
		map.put("sumQy", resultSum.getSumQy());
		map.put("sumOilQty", resultSum.getSumOilQty());
		map.put("sumFuelConsum", resultSum.getSumFuelConsum());
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/fclty/gamInsertElectyFcltySpecMng.do")
	@ResponseBody Map<String, Object> gamInsertElectyFcltySpecMng(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElectyFcltySpecMngVO.setRegUsr((String)user.getId());
			gamElectyFcltySpecMngService.insertElectyFcltySpecMng(gamElectyFcltySpecMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch(IOException e) {
			
		}catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamUpdateElectyFcltySpecMng.do")
	@ResponseBody Map<String, Object> gamUpdateElectyFcltySpecMng(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElectyFcltySpecMngVO.setUpdUsr((String)user.getId());
			gamElectyFcltySpecMngService.updateElectyFcltySpecMng(gamElectyFcltySpecMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch(IOException e) {
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamDeleteElectyFcltySpecMng.do")
	@ResponseBody Map<String, Object> gamDeleteElectyFcltySpecMng(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElectyFcltySpecMngService.deleteElectyFcltySpecMng(gamElectyFcltySpecMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch(IOException e) {
			
		}catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectElectyFcltySpecMngPk.do")
	@ResponseBody Map<String, Object> gamSelectElectyFcltySpecMngPk(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamElectyFcltySpecMngService.selectElectyFcltySpecMngPk(gamElectyFcltySpecMngVO);

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectElectyFcltySpecMngMaxGisPrtFcltySeq.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectElectyFcltySpecMngMaxGisPrtFcltySeq(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) throws Exception {

		String sMaxGisPrtFcltySeq;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sMaxGisPrtFcltySeq = gamElectyFcltySpecMngService.selectElectyFcltySpecMngMaxGisPrtFcltySeq(gamElectyFcltySpecMngVO);

		map.put("resultCode", 0);
		map.put("sMaxGisPrtFcltySeq", sMaxGisPrtFcltySeq);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamExcelDownloadElectyFcltySpecMng.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadElectyFcltySpecMng(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamElectyFcltySpecMngVO searchVO= new GamElectyFcltySpecMngVO();
		searchVO = mapper.convertValue(excelParam, GamElectyFcltySpecMngVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamElectyFcltySpecMngService.selectElectyFcltySpecMngList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectElectyFcltySpecMngFcltsMngGroupNm.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectElectyFcltySpecMngFcltsMngGroupNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sFcltsMngGroupNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sFcltsMngGroupNm = gamElectyFcltySpecMngService.selectFcltsMngGroupNm(searchVO);

		map.put("resultCode", 0);
		map.put("sFcltsMngGroupNm", sFcltsMngGroupNm);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectElectyFcltySpecMngEntrpsNm.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectElectyFcltySpecMngEntrpsNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sEntrpsNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sEntrpsNm = gamElectyFcltySpecMngService.selectEntrpsNm(searchVO);

		map.put("resultCode", 0);
		map.put("sEntrpsNm", sEntrpsNm);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectElectyFcltySpecMngFcltsClCdNm.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectElectyFcltySpecMngFcltsClCdNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sFcltsClCdNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sFcltsClCdNm = gamElectyFcltySpecMngService.selectFcltsClCdNm(searchVO);

		map.put("resultCode", 0);
		map.put("sFcltsClCdNm", sFcltsClCdNm);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectElectyFcltySpecMngGisPrtFcltyNm.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectElectyFcltySpecMngGisPrtFcltyNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sGisPrtFcltyNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sGisPrtFcltyNm = gamElectyFcltySpecMngService.selectGisPrtFcltyNm(searchVO);

		map.put("resultCode", 0);
		map.put("sGisPrtFcltyNm", sGisPrtFcltyNm);

		return map;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectElectyFcltySpecMngAtchFileDirList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectElectyFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamElectyFcltySpecMngService.selectElectyFcltySpecMngAtchFileDirList(gamAtchFileDirMngVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectElectyFcltySpecMngAtchFileDirPk.do")
	@ResponseBody Map<String, Object> gamSelectElectyFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamElectyFcltySpecMngService.selectElectyFcltySpecMngAtchFileDirPk(gamAtchFileDirMngVO);

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

	@RequestMapping(value="/fclty/gamInsertElectyFcltySpecMngAtchFileDir.do")
	@ResponseBody Map<String, Object> gamInsertElectyFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

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
			sNewNo = gamElectyFcltySpecMngService.selectElectyFcltySpecMngAtchFileDirNewNo(gamAtchFileDirMngVO);

			gamAtchFileDirMngVO.setDirNo(sNewNo);
			gamAtchFileDirMngVO.setRegUsr((String)user.getId());
			gamElectyFcltySpecMngService.insertElectyFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch(IOException e) {
			
		}catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamUpdateElectyFcltySpecMngAtchFileDir.do")
	@ResponseBody Map<String, Object> gamUpdateElectyFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

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
			gamElectyFcltySpecMngService.updateElectyFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch(IOException e) {
			
		}catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamDeleteElectyFcltySpecMngAtchFileDir.do")
	@ResponseBody Map<String, Object> gamDeleteElectyFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElectyFcltySpecMngService.deleteElectyFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch(IOException e) {
			
		}catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectElectyFcltySpecMngAtchFileDirNewNo.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectElectyFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		String sNewNo;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sNewNo = gamElectyFcltySpecMngService.selectElectyFcltySpecMngAtchFileDirNewNo(gamAtchFileDirMngVO);

		map.put("resultCode", 0);
		map.put("sNewNo", sNewNo);

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectElectyFcltySpecMngAtchFileDirLowerDataCnt.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectElectyFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamElectyFcltySpecMngService.selectElectyFcltySpecMngAtchFileDirLowerDataCnt(gamAtchFileDirMngVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectElectyFcltySpecMngFcltsAtchFileList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectElectyFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {

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

		List resultList = gamElectyFcltySpecMngService.selectElectyFcltySpecMngFcltsAtchFileList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/fclty/gamInsertElectyFcltySpecMngFcltsAtchFile.do")
	@ResponseBody Map<String, Object> gamInsertElectyFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

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
			sNewNo = gamElectyFcltySpecMngService.selectElectyFcltySpecMngFcltsAtchFileNewNo(gamFcltsAtchFileMngVO);

			gamFcltsAtchFileMngVO.setAtchFileNo(sNewNo);
			gamFcltsAtchFileMngVO.setRegUsr((String)user.getId());
			gamElectyFcltySpecMngService.insertElectyFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("atchFileNo", sNewNo);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch(IOException e) {
			
		}catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamUpdateElectyFcltySpecMngFcltsAtchFile.do")
	@ResponseBody Map<String, Object> gamUpdateElectyFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

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
			gamElectyFcltySpecMngService.updateElectyFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch(IOException e) {
			
		}catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamDeleteElectyFcltySpecMngFcltsAtchFile.do")
	@ResponseBody Map<String, Object> gamDeleteElectyFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElectyFcltySpecMngService.deleteElectyFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch(IOException e) {
			
		}catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectElectyFcltySpecMngFcltsAtchFilePk.do")
	@ResponseBody Map<String, Object> gamSelectElectyFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamElectyFcltySpecMngService.selectElectyFcltySpecMngFcltsAtchFilePk(gamFcltsAtchFileMngVO);

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectElectyFcltySpecMngFcltsAtchFileNewNo.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectElectyFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {

		String sNewNo;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sNewNo = gamElectyFcltySpecMngService.selectElectyFcltySpecMngFcltsAtchFileNewNo(gamFcltsAtchFileMngVO);

		map.put("resultCode", 0);
		map.put("sNewNo", sNewNo);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectElectyFcltySpecMngMntnRprDtlsList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectElectyFcltySpecMngMntnRprDtlsList(GamMntnRprDtlsVO searchVO) throws Exception {

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

		List resultList = gamElectyFcltySpecMngService.selectElectyFcltySpecMngMntnRprDtlsList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	/**
	 *     수정일			   수정자				수정내용
	 * ------------		--------	---------------------------
	 * 2018.06.07.		김재철		트리뷰 이동
	 *
	 * @param gamAtchFileDirMngVO
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamUpdateElectyFcltySpecMngAtchFileDirChage.do")
	@ResponseBody Map gamUpdateElectyFcltySpecMngAtchFileDirChage(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		if(!"E".equals(gamAtchFileDirMngVO.getDirFcltsJobSe())){
			map.put("resultCode", 1);
			map.put("resultMsg","다른 시설담당자가 생성한 디렉토리입니다. (이동 불가능)");
			return map;
		}

		gamElectyFcltySpecMngService.updateElectyFcltySpecMngAtchFileDirChage(gamAtchFileDirMngVO);

		map.put("resultCode", 0);

		return map;

	}

}
