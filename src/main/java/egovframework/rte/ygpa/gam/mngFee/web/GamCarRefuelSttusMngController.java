/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.web;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.mngFee.service.GamCarRefuelSttusMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamCarRefuelSttusMngVo;


/**
 *
 * @author Lee
 * @since 2014. 10. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 22.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamCarRefuelSttusMngController {

	protected Log log = LogFactory.getLog(this.getClass());

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "gamCarRefuelSttusMngService")
	private GamCarRefuelSttusMngService gamCarRefuelSttusMngService;

	@Resource(name = "excelCarRefuelSttusService")
	private EgovExcelService excelCarRefuelSttusService;

	@RequestMapping(value="/mngFee/gamCarRefuelSttusMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		int year = Integer.parseInt(EgovDateUtil.getToday().substring(0,4));
		List yearList = new ArrayList();
		Map yearMap = null;

		for ( int i = year ; i >= year-10 ; i-- ) {
			yearMap = new HashMap();
			yearMap.put("code", i);
			yearMap.put("codeNm", i+"년");
			yearList.add(yearMap);
		}

		model.addAttribute("yearsList", yearList);
		model.addAttribute("thisyear", year);
		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/mngFee/GamCarRefuelSttusMng";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectCarRefuelSttusMng.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectCarRefuelSttusMng(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) throws Exception {

		int totalCnt;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(gamCarRefuelSttusMngVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(gamCarRefuelSttusMngVo.getPageUnit());
		paginationInfo.setPageSize(gamCarRefuelSttusMngVo.getPageSize());

		gamCarRefuelSttusMngVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		gamCarRefuelSttusMngVo.setLastIndex(paginationInfo.getLastRecordIndex());
		gamCarRefuelSttusMngVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		totalCnt = gamCarRefuelSttusMngService.selectCarRefuelSttusMngListTotCnt(gamCarRefuelSttusMngVo);
		List resultList = gamCarRefuelSttusMngService.selectCarRefuelSttusMngList(gamCarRefuelSttusMngVo);

		map.put("resultCode", 0);
		map.put("totalCount", totalCnt);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectCarRefuelSttusMngPk.do")
	@ResponseBody Map<String, Object> gamSelectCarRefuelSttusMngPk(GamCarRefuelSttusMngVo searchVO)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamCarRefuelSttusMngService.selectCarRefuelSttusMngPk(searchVO);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (Exception e) {
			// TODO: handle exception

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamExcelDownloadCarRefuelSttusMng.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadCarRefuelSttusMng(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamCarRefuelSttusMngVo searchVO= new GamCarRefuelSttusMngVo();
		searchVO = mapper.convertValue(excelParam, GamCarRefuelSttusMngVo.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamCarRefuelSttusMngService.selectCarRefuelSttusMngList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamInsertCarRefuelSttusMng.do" , method=RequestMethod.POST)
	@ResponseBody Map gamInsertCarRefuelSttusMng(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) throws Exception {

		Map map = new HashMap();

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamCarRefuelSttusMngVo.setRegUsr((String)user.getId());
			gamCarRefuelSttusMngVo.setUpdUsr((String)user.getId());
			gamCarRefuelSttusMngService.insertCarRefuelSttusMngList(gamCarRefuelSttusMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			// TODO: handle exception

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamUpdateCarRefuelSttusMng.do")
	@ResponseBody Map<String, Object> gamUpdateCarRefuelSttusMng(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamCarRefuelSttusMngVo.setUpdUsr((String)user.getId());
			gamCarRefuelSttusMngService.updateCarRefuelSttusMngList(gamCarRefuelSttusMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			// TODO: handle exception

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamDeleteCarRefuelSttusMng.do")
	@ResponseBody Map<String, Object> gamDeleteCarRefuelSttusMng(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamCarRefuelSttusMngVo.setUpdUsr((String)user.getId());
			gamCarRefuelSttusMngService.deleteCarRefuelSttusMngList(gamCarRefuelSttusMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			// TODO: handle exception

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value = "/mngFee/gamExcelUploadCarRefuelSttusMng.do")
	@ResponseBody Map<String, Object> gamExcelUploadCarRefuelSttusMng(final HttpServletRequest request) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		InputStream fis = null;
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			file = entry.getValue();
			if (!"".equals(file.getOriginalFilename())) {
				if (file.getOriginalFilename().endsWith(".xls") ||
					file.getOriginalFilename().endsWith(".xlsx") ||
					file.getOriginalFilename().endsWith(".XLS") ||
					file.getOriginalFilename().endsWith(".XLSX")) {
					try {
						fis = file.getInputStream();
						excelCarRefuelSttusService.uploadExcel("gamCarRefuelSttusMngDao.insertCarRefuelSttusF_S", fis, 1, (long)5000);
					} catch(Exception e) {
						throw e;
					} finally {
						if (fis != null) {
							fis.close();
						}
					}
				} else {
					map.put("resultCode", 3);
					map.put("resultMsg", "xls, xlsx 파일 타입만 등록이 가능합니다.");
					return map;
				}
			}
		}

		map.put("resultCode", 0);
		map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		return map;

	}

}
