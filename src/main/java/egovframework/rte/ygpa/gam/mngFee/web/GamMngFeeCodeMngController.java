/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeCodeMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeCodeMngVo;


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
public class GamMngFeeCodeMngController {

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

	@Resource(name = "gamMngFeeCodeMngService")
	private GamMngFeeCodeMngService gamMngFeeCodeMngService;

	@RequestMapping(value="/mngFee/gamMngFeeCodeMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Map searchVO = new HashMap();
		List<?> mngFeeFcltySeList = gamMngFeeCodeMngService.selectMngFeeFcltySeMngList(searchVO);

		model.addAttribute("windowId", windowId);
		model.addAttribute("mngFeeFcltySeList", mngFeeFcltySeList);

		return "/ygpa/gam/mngFee/GamMngFeeCodeMng";

    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamSelectMngFeeCodeMng.do" , method=RequestMethod.POST)
	@ResponseBody Map selectMngFeeCodeMngList(GamMngFeeCodeMngVo searchVO) throws Exception {

		int totalCnt, page, firstIndex;
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

		totalCnt = gamMngFeeCodeMngService.selectMngFeeCodeMngListTotCnt(searchVO);
		List resultList = gamMngFeeCodeMngService.selectMngFeeCodeMngList(searchVO);

		map.put("resultCode", 0);
		map.put("totalCount", totalCnt);
		map.put("resultList", resultList);

		return map;

	}

	@RequestMapping(value="/mngFee/gamCodeMngFeeFcltySeMng.do" , method=RequestMethod.POST)
	@ResponseBody Map<String, Object> codeMngFeeFcltySeMngList(@RequestParam Map<String, Object> searchVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		List<?> resultList = gamMngFeeCodeMngService.selectMngFeeFcltySeMngList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);

		return map;
	}

	@RequestMapping(value="/mngFee/gamMngFeeCodeMngMaxFcltyCd.do" , method=RequestMethod.POST)
	@ResponseBody Map selectMngFeeCodeMngMaxFcltyCd(GamMngFeeCodeMngVo gamMngFeeCodeMngVo) throws Exception {

		String sMaxFcltyCd;
		Map map = new HashMap();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		sMaxFcltyCd = gamMngFeeCodeMngService.selectMngFeeCodeMngMaxFcltyCd(gamMngFeeCodeMngVo);

		map.put("resultCode", 0);
		map.put("sMaxFcltyCd", sMaxFcltyCd);

		return map;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/mngFee/gamExcelMngFeeCodeMng.do" , method=RequestMethod.POST)
	@ResponseBody ModelAndView excelMngFeeCodeMngList(@RequestParam Map<String, Object> excelParam) throws Exception {

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

		GamMngFeeCodeMngVo searchVO= new GamMngFeeCodeMngVo();
		searchVO = mapper.convertValue(excelParam, GamMngFeeCodeMngVo.class);
		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		List resultList = gamMngFeeCodeMngService.selectMngFeeCodeMngList(searchVO);

		map.put("resultCode", 0);
		map.put("resultList", resultList);
		map.put("header", header);

		return new ModelAndView("gridExcelView", "gridResultMap", map);

	}

	@RequestMapping(value="/mngFee/gamInsertMngFeeCodeMng.do")
	@ResponseBody Map<String, Object> insertMngFeeCodeMng(GamMngFeeCodeMngVo gamMngFeeCodeMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamMngFeeCodeMngVo.setRegUsr((String)user.getId());
			gamMngFeeCodeMngService.insertMngFeeCodeMng(gamMngFeeCodeMngVo);

			map.put("resultCode", 0);			// return ok
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamUpdateMngFeeCodeMng.do")
	@ResponseBody Map<String, Object> updateMngFeeCodeMng(GamMngFeeCodeMngVo gamMngFeeCodeMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamMngFeeCodeMngVo.setUpdUsr((String)user.getId());
			gamMngFeeCodeMngService.updateMngFeeCodeMng(gamMngFeeCodeMngVo);

			map.put("resultCode", 0);			// return ok
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

		return map;

	}

	@RequestMapping(value="/mngFee/gamDeleteMngFeeCodeMng.do")
	@ResponseBody Map<String, Object> deleteMngFeeCodeMng(GamMngFeeCodeMngVo gamMngFeeCodeMngVo)	throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> map = new HashMap<String, Object>();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}

		try {
			gamMngFeeCodeMngService.deleteMngFeeCodeMng(gamMngFeeCodeMngVo);

			map.put("resultCode", 0);			// return ok
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}

		return map;

	}

}
