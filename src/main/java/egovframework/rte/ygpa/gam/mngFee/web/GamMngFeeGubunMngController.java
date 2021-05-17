/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.web;

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
import com.sun.corba.se.impl.protocol.giopmsgheaders.IORAddressingInfo;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeGubunMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeGubunMngVo;

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
public class GamMngFeeGubunMngController {

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

	@Resource(name = "gamMngFeeGubunMngService")
	private GamMngFeeGubunMngService gamMngFeeGubunMngService;

	@RequestMapping(value="/mngFee/gamMngFeeGubunMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		model.addAttribute("windowId", windowId);

		return "/ygpa/gam/mngFee/GamMngFeeGubunMng";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectMngFeeGubunMng.do" , method=RequestMethod.POST)
	@ResponseBody Map gamSelectMngFeeGubunMng(GamMngFeeGubunMngVo searchVO) throws Exception {

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

		totalCnt = gamMngFeeGubunMngService.selectMngFeeGubunMngListTotCnt(searchVO);
		List resultList = gamMngFeeGubunMngService.selectMngFeeGubunMngList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", totalCnt);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamSelectMngFeeGubunMngPk.do")
	@ResponseBody Map<String, Object> gamSelectMngFeeGubunMngPk(GamMngFeeGubunMngVo searchVO)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			Map result = gamMngFeeGubunMngService.selectMngFeeGubunMngPk(searchVO);

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamExcelDownloadMngFeeGubunMng.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView gamExcelDownloadMngFeeGubunMng(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamMngFeeGubunMngVo searchVO= new GamMngFeeGubunMngVo();
		searchVO = mapper.convertValue(excelParam, GamMngFeeGubunMngVo.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamMngFeeGubunMngService.selectMngFeeGubunMngList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@RequestMapping(value="/mngFee/gamInsertMngFeeGubunMng.do")
	@ResponseBody Map<String, Object> gamInsertMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamMngFeeGubunMngVo.setRegUsr((String)user.getId());
			gamMngFeeGubunMngService.insertMngFeeGubunMng(gamMngFeeGubunMngVo);

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

	@RequestMapping(value="/mngFee/gamUpdateMngFeeGubunMng.do")
	@ResponseBody Map<String, Object> gamUpdateMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamMngFeeGubunMngVo.setUpdUsr((String)user.getId());
			gamMngFeeGubunMngService.updateMngFeeGubunMng(gamMngFeeGubunMngVo);

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

	@RequestMapping(value="/mngFee/gamDeleteMngFeeGubunMng.do")
	@ResponseBody Map<String, Object> gamDeleteMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamMngFeeGubunMngService.deleteMngFeeGubunMng(gamMngFeeGubunMngVo);

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

	@RequestMapping(value="/mngFee/gamCheckSeFeeGubunMng.do")
	@ResponseBody Map<String, Object> gamCheckSeFeeGubunMng(@RequestParam("checkSe") String checkSe)	throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			int checkSeCnt = gamMngFeeGubunMngService.checkSeFeeGubunMng(checkSe);

			map.put("resultCode", 0);
			map.put("checkSeCnt", checkSeCnt);
			map.put("checkSe", checkSe);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.select"));
		} catch (IOException i) {
			
		} catch (Exception e) {
			// TODO: handle exception

			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.select"));
		}

		return map;
	}

}
