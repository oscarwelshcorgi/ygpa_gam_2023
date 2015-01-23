/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
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
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.cmmn.service.GamFileServiceVo;
import egovframework.rte.ygpa.gam.cmmn.service.GamFileUploadUtil;
import egovframework.rte.ygpa.gam.mngFee.service.GamGasUsageSttusMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamGasUsageSttusMngVo;


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
public class GamGasUsageSttusMngController {

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

	@Resource(name = "gamGasUsageSttusMngService")
	private GamGasUsageSttusMngService gamGasUsageSttusMngService;

	@Resource(name = "excelGasUsageSttusService")
	private EgovExcelService excelGasUsageSttusService;

    /**
     * 임시파일 아이디를 생성한다.
     */
    @Resource(name="gamTempFileIdGnrService")
    EgovTableIdGnrService gamTempFileIdGnrService;

	@RequestMapping(value="/mngFee/gamGasUsageSttusMng.do")
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

		return "/ygpa/gam/mngFee/GamGasUsageSttusMng";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectGasUsageSttusMng.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectGasUsageSttusMng(GamGasUsageSttusMngVo searchVO) throws Exception {

		int totalCnt;
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

		totalCnt = gamGasUsageSttusMngService.selectGasUsageSttusMngListTotCnt(searchVO);
		List resultList = gamGasUsageSttusMngService.selectGasUsageSttusMngList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", totalCnt);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectGasUsageSttusMngPk.do")
	@ResponseBody Map<String, Object> gamSelectGasUsageSttusMngPk(GamGasUsageSttusMngVo searchVO)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamGasUsageSttusMngService.selectGasUsageSttusMngPk(searchVO);

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

	@RequestMapping(value="/mngFee/gamSelectGasUsageSttusMngChart.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectGasUsageSttusMngChart(GamGasUsageSttusMngVo gamGasUsageSttusMngVo) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamGasUsageSttusMngService.selectGasUsageSttusMngChartList(gamGasUsageSttusMngVo);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectGasUsageSttusMngPrevMtUsageQy.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectGasUsageSttusMngPrevMtUsageQy(GamGasUsageSttusMngVo gamGasUsageSttusMngVo) throws Exception {

		String sPrevMtUsageQy;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sPrevMtUsageQy = gamGasUsageSttusMngService.selectGasUsageSttusMngPrevMtUsageQy(gamGasUsageSttusMngVo);

		map.put("resultCode", 0);
		map.put("sPrevMtUsageQy", sPrevMtUsageQy);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamExcelDownloadGasUsageSttusMng.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadGasUsageSttusMng(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamGasUsageSttusMngVo searchVO= new GamGasUsageSttusMngVo();
		searchVO = mapper.convertValue(excelParam, GamGasUsageSttusMngVo.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamGasUsageSttusMngService.selectGasUsageSttusMngList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@RequestMapping(value="/mngFee/gamInsertGasUsageSttusMng.do")
	@ResponseBody Map<String, Object> gamInsertGasUsageSttusMng(GamGasUsageSttusMngVo gamGasUsageSttusMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamGasUsageSttusMngVo.setRegUsr((String)user.getId());
			gamGasUsageSttusMngService.insertGasUsageSttusMng(gamGasUsageSttusMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			// TODO: handle exception

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamUpdateGasUsageSttusMng.do")
	@ResponseBody Map<String, Object> gamUpdateGasUsageSttusMng(GamGasUsageSttusMngVo gamGasUsageSttusMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamGasUsageSttusMngVo.setUpdUsr((String)user.getId());
			gamGasUsageSttusMngService.updateGasUsageSttusMng(gamGasUsageSttusMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			// TODO: handle exception

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamDeleteGasUsageSttusMng.do")
	@ResponseBody Map<String, Object> gamDeleteGasUsageSttusMng(GamGasUsageSttusMngVo gamGasUsageSttusMngVo)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamGasUsageSttusMngService.deleteGasUsageSttusMng(gamGasUsageSttusMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			// TODO: handle exception

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/mngFee/gamExcelUploadGasUsageSttusMng.do", method=RequestMethod.POST)
    public @ResponseBody Map gamExcelUploadGasUsageSttusMng(HttpServletRequest request) throws Exception {
    	File file = null;
		InputStream fis = null; // 2011.11.1 보안점검 후속조치

		Map map = new HashMap();
		String uploadPath = EgovProperties.getProperty("Globals.fileStorePath");
		List<GamFileServiceVo> list = GamFileUploadUtil.uploadFiles(request, uploadPath, gamTempFileIdGnrService);

		if(list.size()==0) {
			map.put("resultCode", "-1");
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.upload"));
			return map;
		}
		else if(list.size()!=1) {
			map.put("resultCode", "-2");
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.not_one_upload"));
			return map;
		}

		GamFileServiceVo filevo = list.get(0);

		if (!"".equals(filevo.getPhyscalFileNm())) {
			if (filevo.getPhyscalFileNm().endsWith(".xls")
					|| filevo.getPhyscalFileNm().endsWith(".xlsx")
					|| filevo.getPhyscalFileNm().endsWith(".XLS")
					|| filevo.getPhyscalFileNm().endsWith(".XLSX")) {

				try {
					file = new File(uploadPath+filevo.getPhyscalFileNm());
					fis = new FileInputStream(file);
					excelGasUsageSttusService.uploadExcel("gamGasUsageSttusMngDao.insertGasUsageSttusF_S", fis, 1, (long)5000);
				}
				catch(FileNotFoundException e) {
					map.put("resultCode", "-1");
					map.put("resultMsg", egovMessageSource.getMessage("fail.common.upload"));
					return map;
				}
				catch(OfficeXmlFileException e) {
					map.put("resultCode", "-5");
					map.put("resultMsg", e.getMessage());
					return map;
				}
				catch(NumberFormatException e) {
					map.put("resultCode", "-4");
					map.put("resultMsg", egovMessageSource.getMessage("fail.common.invalid_number_format")+e.getMessage());
					return map;
				}
				catch(IllegalArgumentException e) {
					map.put("resultCode", "-3");
					map.put("resultMsg", egovMessageSource.getMessage("fail.common.invalid_xls_format"));
					return map;
				}
				catch(Exception e) {
					map.put("resultCode", "-3");
					map.put("resultMsg", egovMessageSource.getMessage("fail.common.invalid_xls_format")+" : "+e.getMessage());
					return map;
				} finally {
					if (fis != null)	// 2011.11.1 보안점검 후속조치
						fis.close();
				}

			}else{
				log.info("xls, xlsx 파일 타입만 등록이 가능합니다.");
				map.put("resultCode", "-3");
				map.put("resultMsg", "xls, xlsx 파일 타입만 등록이 가능합니다.");

				return map;
			}
		}
		map.put("resultCode", "0");
		map.put("resultMst", "전송이 완료 되었습니다.");

		return map;
	}

}
