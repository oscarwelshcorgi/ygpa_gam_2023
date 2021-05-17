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
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsMngRegistMngMntnRprDtlsVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsMngRegistMngQcMngDtlsVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsMngRegistMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsMngRegistMngVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 10.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltsMngRegistMngController {
	/** Logger */
	static final Logger logger = Logger.getLogger(GamFcltsMngRegistMngController.class);
	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamFcltsMngRegistMngService")
	private GamFcltsMngRegistMngService gamFcltsMngRegistMngService;

	@RequestMapping(value="/fclty/gamFcltsMngRegistMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/fclty/GamFcltsMngRegistMng";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectFcltsMngRegistMngList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsMngRegistMngList(GamFcltsMngRegistMngVO searchVO) throws Exception {

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

		GamFcltsMngRegistMngVO resultSum = gamFcltsMngRegistMngService.selectFcltsMngRegistMngListSum(searchVO);
		List resultList = gamFcltsMngRegistMngService.selectFcltsMngRegistMngList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", resultSum.getTotalCount());
		map.put("sumCnstrctAmt", resultSum.getSumCnstrctAmt());
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/fclty/gamInsertFcltsMngRegistMng.do")
	@ResponseBody Map<String, Object> gamInsertFcltsMngRegistMng(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsMngRegistMngVO.setRegUsr((String)user.getId());
			gamFcltsMngRegistMngService.insertFcltsMngRegistMng(gamFcltsMngRegistMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			//e.printStackTrace();
			logger.info(e.getMessage());
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamUpdateFcltsMngRegistMng.do")
	@ResponseBody Map<String, Object> gamUpdateFcltsMngRegistMng(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsMngRegistMngVO.setUpdUsr((String)user.getId());
			gamFcltsMngRegistMngService.updateFcltsMngRegistMng(gamFcltsMngRegistMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			//e.printStackTrace();
			logger.info(e.getMessage());
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamDeleteFcltsMngRegistMng.do")
	@ResponseBody Map<String, Object> gamDeleteFcltsMngRegistMng(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamFcltsMngRegistMngService.deleteFcltsMngRegistMng(gamFcltsMngRegistMngVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			//e.printStackTrace();
			logger.info(e.getMessage());
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/fclty/gamSelectFcltsMngRegistMngPk.do")
	@ResponseBody Map<String, Object> gamSelectFcltsMngRegistMngPk(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltsMngRegistMngService.selectFcltsMngRegistMngPk(gamFcltsMngRegistMngVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			//e.printStackTrace();
			logger.info(e.getMessage());
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamExcelDownloadFcltsMngRegistMng.do", method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadFcltsMngRegistMng(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltsMngRegistMngVO searchVO= new GamFcltsMngRegistMngVO();
		searchVO = mapper.convertValue(excelParam, GamFcltsMngRegistMngVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltsMngRegistMngService.selectFcltsMngRegistMngList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	/**
	 * 시설물관리대장 한글문서 다운로드 - 김종민 추가 작업 2016.03.31
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/gamHwpDownloadFcltsMngRegistMng.do")
	public String gamHwpDownloadFcltsMngRegistMng(@RequestParam Map<String, Object> printOpt, ModelMap model) throws Exception {
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if(!isAuthenticated) {
			model.addAttribute("resultCode", 1);
			model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return "/ygpa/gam/fcltyMng/GamFcltyQcMngResultListReportHwp";
		}
		
		String fcltsNo = (String) printOpt.get("fcltsNo");
		String fcltsMngGroupNo = (String) printOpt.get("fcltsMngGroupNo"); 
		String fcltsJobSe = (String) printOpt.get("fcltsJobSe");
		
		String hwpML = gamFcltsMngRegistMngService.downloadHwpFcltsMngRegistMng(fcltsNo, fcltsMngGroupNo, fcltsJobSe);
		
		model.addAttribute("resultCode", 0);
		model.addAttribute("resultMsg", "");
		model.addAttribute("hwpML", hwpML);

		//hwp선택시 파일명
		if(printOpt.get("filename") != null){
			model.addAttribute("isHwp", true);
			model.addAttribute("filename", printOpt.get("filename"));
		}

		return "/ygpa/gam/fcltyMng/GamFcltyQcMngResultListReportHwp";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectFcltsMngRegistMngFcltsMngGroupNm.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsMngRegistMngFcltsMngGroupNm(@RequestParam Map<String, Object> searchVO) throws Exception {

		String sFcltsMngGroupNm;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sFcltsMngGroupNm = gamFcltsMngRegistMngService.selectFcltsMngGroupNm(searchVO);

		map.put("resultCode", 0);
		map.put("sFcltsMngGroupNm", sFcltsMngGroupNm);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectFcltsMngRegistMngQcMngDtlsPlanList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsMngRegistMngQcMngDtlsPlanList(GamFcltsMngRegistMngQcMngDtlsVO searchVO) throws Exception {

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

		List resultList = gamFcltsMngRegistMngService.selectFcltsMngRegistMngQcMngDtlsPlanList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectFcltsMngRegistMngQcMngDtlsHistList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsMngRegistMngQcMngDtlsHistList(GamFcltsMngRegistMngQcMngDtlsVO searchVO) throws Exception {

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

		List resultList = gamFcltsMngRegistMngService.selectFcltsMngRegistMngQcMngDtlsHistList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectFcltsMngRegistMngMntnRprDtlsPlanList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsMngRegistMngMntnRprDtlsPlanList(GamFcltsMngRegistMngMntnRprDtlsVO searchVO) throws Exception {

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

		List resultList = gamFcltsMngRegistMngService.selectFcltsMngRegistMngMntnRprDtlsPlanList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fclty/gamSelectFcltsMngRegistMngMntnRprDtlsHistList.do", method=RequestMethod.POST)
	@ResponseBody Map gamSelectFcltsMngRegistMngMntnRprDtlsHistList(GamFcltsMngRegistMngMntnRprDtlsVO searchVO) throws Exception {

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

		List resultList = gamFcltsMngRegistMngService.selectFcltsMngRegistMngMntnRprDtlsHistList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

}
