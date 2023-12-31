/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageSttusInqireService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageSttusInqireVO;

/**
 *
 * @author jckim
 * @since 2014. 12. 8.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 8.		jckim		최초 생성
 *  
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltyUsageSttusInqireController {

	protected Log log = LogFactory.getLog(this.getClass());

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	// 수정 부분
	@Resource(name = "gamFcltyUsageSttusInqireService")
	protected GamFcltyUsageSttusInqireService gamFcltyUsageSttusInqireService;

	@RequestMapping(value = "/fcltyMng/gamFcltyUsageSttusInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/fcltyMng/GamFcltyUsageSttusInqire";

	}

	/**
	 * GIS 항만 시설
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/fcltyMng/gamFcltyGisPrtFcltyCdList.do", method = RequestMethod.POST)
	@ResponseBody
	Map selectFcltyGisPrtFcltyCdList(GamFcltyUsageSttusInqireVO searchVO) throws Exception {
		int totalCnt, page, firstIndex;
		Map map = new HashMap();

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		// 내역 조회
		/** pageing */
		// 수정 안해도됨!
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/** List Data */
		// 데이터 쿼리 데이터

		totalCnt = gamFcltyUsageSttusInqireService.selectFcltyGisPrtFcltyCdListTotCnt(searchVO);
		List resultList = gamFcltyUsageSttusInqireService.selectFcltyGisPrtFcltyCdList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", totalCnt);
		map.put("resultList", resultList);

		return map;
	}

	/**
	 * 시설물 임대현황 목록
	 * @param searchVO
	 * @param sPrtAtCode
	 * @param sUsagePdFrom
	 * @param sUsagePdTo
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/fcltyMng/selectLoadAssetRentData.do", method = RequestMethod.POST)
	@ResponseBody
	Map selectLoadQcMngData(GamFcltyUsageSttusInqireVO searchVO) throws Exception {
		
		Map map = new HashMap();

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		/** List Data */
		// 데이터 쿼리 데이터

		int totalCnt = gamFcltyUsageSttusInqireService.selectFcltyAssetsRentListTotCnt(searchVO);
		List resultList = gamFcltyUsageSttusInqireService.selectFcltyAssetsRentList(searchVO);
		map.put("resultCode", 0);
		map.put("totalCount", totalCnt);
		map.put("resultList", resultList);

		return map;
	}

	
	/**
	 * 점검 관리 데이터
	 * @param searchVO
	 * @param sUsagePdFrom
	 * @param sUsagePdTo
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/fcltyMng/selectLoadQcwWrtMngData.do", method = RequestMethod.POST)
	@ResponseBody Map selectLoadQcWrtMngDataList(GamFcltyUsageSttusInqireVO searchVO) throws Exception {
		
		Map map = new HashMap();

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		/** List Data */
		// 데이터 쿼리 데이터
		List resultList = gamFcltyUsageSttusInqireService.selectQcMngList(searchVO);
		
		map.put("resultCode", 0);
		map.put("resultList", resultList);
		return map;
	}
	
	/**
	 * 유지보수 데이터
	 * @param searchVO
	 * @param sUsagePdFrom
	 * @param sUsagePdTo
	 * @return map
	 * @throws Exception
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/fcltyMng/selectLoadMaintMngData.do", method = RequestMethod.POST)
	@ResponseBody Map selectLoadMaintMngDataList(GamFcltyUsageSttusInqireVO searchVO) throws Exception {
		
		Map map = new HashMap();

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		/** List Data */
		// 데이터 쿼리 데이터
		List resultList = gamFcltyUsageSttusInqireService.selectMntnRprDtlsList(searchVO);
		
		map.put("resultCode", 0);
		map.put("resultList", resultList);
		return map;
	}
	/**
	 * 하자 보수 데이터
	 * @param searchVO
	 * @param sUsagePdFrom
	 * @param sUsagePdTo
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/fcltyMng/selectLoadRepairMngData.do", method = RequestMethod.POST)
	@ResponseBody Map selectLoadFlawMngDataList(GamFcltyUsageSttusInqireVO searchVO) throws Exception {
		
		Map map = new HashMap();

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		/** List Data */
		// 데이터 쿼리 데이터
		List resultList = gamFcltyUsageSttusInqireService.selectFlawList(searchVO);
		
		map.put("resultCode", 0);
		map.put("resultList", resultList);
		return map;
	}
		

		



	/**
	 *  시설물사용현황 엑셀다운로드 
	 * @param searchVO
	 * @param sPrtAtCode
	 * @param sUsagePdFrom
	 * @param sUsagePdTo
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/gamExcelFcltyGisPrtFcltyCdList.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelFcltyGisPrtFcltyCdList(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltyUsageSttusInqireVO searchVO= new GamFcltyUsageSttusInqireVO();
		searchVO = mapper.convertValue(excelParam, GamFcltyUsageSttusInqireVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltyUsageSttusInqireService.selectFcltyGisPrtFcltyCdList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);
	}
	
	/**
	 *  시설물임대현황 엑셀다운로드 
	 * @param searchVO
	 * @param sPrtAtCode
	 * @param sUsagePdFrom
	 * @param sUsagePdTo
	 * @return map
	 * @throws Exception
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/fcltyMng/gamExcelFcltyAssetsRentList.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelFcltyAssetsRentList(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamFcltyUsageSttusInqireVO searchVO= new GamFcltyUsageSttusInqireVO();
		searchVO = mapper.convertValue(excelParam, GamFcltyUsageSttusInqireVO.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamFcltyUsageSttusInqireService.selectFcltyAssetsRentList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);
	}
	
	
	/**
	 *  시설물임대현황 엑셀다운로드 
	 * @param searchVO
	 * @param sPrtAtCode
	 * @param sUsagePdFrom
	 * @param sUsagePdTo
	 * @return map
	 * @throws Exception
	 */
	
	@RequestMapping(value="/fcltyMng/selectGetSearchFcltsMngGroupNm.do")
	@ResponseBody Map<String, Object> selectGetSearchFcltsMngGroupNm(@RequestParam Map<String, Object> searchVO)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamFcltyUsageSttusInqireService.selectFcltsMngGroupNm(searchVO);

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
	
	}
