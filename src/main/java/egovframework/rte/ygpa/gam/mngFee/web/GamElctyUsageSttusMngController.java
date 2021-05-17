/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.apache.log4j.Logger;
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
import com.sun.corba.se.impl.protocol.giopmsgheaders.IORAddressingInfo;

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
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyUsageSttusMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyUsageSttusMngVo;


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
public class GamElctyUsageSttusMngController {

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

	@Resource(name="gamElctyUsageSttusMngService")
	GamElctyUsageSttusMngService gamElctyUsageSttusMngService;

	@Resource(name = "excelElctyUsageSttusService")
	private EgovExcelService excelElctyUsageSttusService;

    /**
     * 임시파일 아이디를 생성한다.
     */
    @Resource(name="gamTempFileIdGnrService")
    EgovTableIdGnrService gamTempFileIdGnrService;

	@RequestMapping(value="/mngFee/gamElctyUsageSttusMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

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

		return "/ygpa/gam/mngFee/GamElctyUsageSttusMng";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectElctyUsageSttusMng.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectElctyUsageSttusMng(GamElctyUsageSttusMngVo searchVO) throws Exception {

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

		totalCnt = gamElctyUsageSttusMngService.selectElctyUsageSttusMngListTotCnt(searchVO);
		List resultList = gamElctyUsageSttusMngService.selectElctyUsageSttusMngList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", totalCnt);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectElctyUsageSttusMngPk.do")
	@ResponseBody Map<String, Object> gamSelectElctyUsageSttusMngPk(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamElctyUsageSttusMngService.selectElctyUsageSttusMngPk(gamElctyUsageSttusMngVo);

			map.put("resultCode", 0);
			map.put("result", result);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (IOException i) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;
	}

	@RequestMapping(value="/mngFee/gamSelectElctyUsageSttusMngChart.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectElctyUsageSttusMngChart(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamElctyUsageSttusMngService.selectElctyUsageSttusMngChartList(gamElctyUsageSttusMngVo);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectElctyUsageSttusMngPrevMtUsageQy.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectElctyUsageSttusMngPrevMtUsageQy(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception {

		String sPrevMtUsageQy;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sPrevMtUsageQy = gamElctyUsageSttusMngService.selectElctyUsageSttusMngPrevMtUsageQy(gamElctyUsageSttusMngVo);

		map.put("resultCode", 0);
		map.put("sPrevMtUsageQy", sPrevMtUsageQy);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectElctyUsageSttusMngMonthCnt.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectElctyUsageSttusMngMonthCnt(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception {

		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List resultList = gamElctyUsageSttusMngService.selectElctyUsageSttusMngMonthCntList(gamElctyUsageSttusMngVo);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamExcelDownloadElctyUsageSttusMng.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadElctyUsageSttusMng(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamElctyUsageSttusMngVo searchVO= new GamElctyUsageSttusMngVo();
		searchVO = mapper.convertValue(excelParam, GamElctyUsageSttusMngVo.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamElctyUsageSttusMngService.selectElctyUsageSttusMngList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@RequestMapping(value="/mngFee/gamInsertElctyUsageSttusMng.do")
	@ResponseBody Map<String, Object> gamInsertElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElctyUsageSttusMngVo.setRegUsr((String)user.getId());
			gamElctyUsageSttusMngService.insertElctyUsageSttusMng(gamElctyUsageSttusMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (IOException i) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamUpdateElctyUsageSttusMng.do")
	@ResponseBody Map<String, Object> gamUpdateElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElctyUsageSttusMngVo.setUpdUsr((String)user.getId());
			gamElctyUsageSttusMngService.updateElctyUsageSttusMng(gamElctyUsageSttusMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (IOException i) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamDeleteElctyUsageSttusMng.do")
	@ResponseBody Map<String, Object> gamDeleteElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElctyUsageSttusMngService.deleteElctyUsageSttusMng(gamElctyUsageSttusMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (IOException i) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamCopyElctyUsageSttusMng.do")
	@ResponseBody Map<String, Object> gamCopyElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamElctyUsageSttusMngVo.setRegUsr((String)user.getId());
			gamElctyUsageSttusMngService.copyElctyUsageSttusMng(gamElctyUsageSttusMngVo);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (IOException i) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
		} catch (Exception e) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/mngFee/gamExcelUploadElctyUsageSttusMng.do", method=RequestMethod.POST)
    public @ResponseBody Map gamExcelUploadElctyUsageSttusMng(HttpServletRequest request) throws Exception {
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
					excelElctyUsageSttusService.uploadExcel("gamElctyUsageSttusMngDao.insertElctyUsageSttusF_S", fis, 1, (long)5000);
				}
				catch(FileNotFoundException e) {
					map.put("resultCode", "-1");
					map.put("resultMsg", egovMessageSource.getMessage("fail.common.upload"));
					return map;
				}
				catch(IOException e) {
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
