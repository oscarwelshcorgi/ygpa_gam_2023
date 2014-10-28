/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.web;

import java.util.ArrayList;
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
import org.springmodules.validation.commons.DefaultBeanValidator;


import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyUsageSttusMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyUsageSttusMngVo;
import egovframework.rte.ygpa.gam.soc.service.GamSocAgentService;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseVO;



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



    @RequestMapping(value="/mngFee/gamElctyUsageSttusMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

    	///login정보
    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    	int year = Integer.parseInt(EgovDateUtil.getToday().substring(0,4));
		List yearList = new ArrayList();
		Map yearMap = null;

		for( int i = year ; i >= year-10 ; i-- ) {
			yearMap = new HashMap();
			yearMap.put("code", i);
			yearMap.put("codeNm", i+"년");

			yearList.add(yearMap);
		}

		List monList = new ArrayList();
		Map monMap;
		for(int i=1; i < 13; i++){
			monMap = new HashMap();
			monMap.put("code", i);
			monMap.put("codeNm", i+"월");
			monList.add(monMap);
		}

		model.addAttribute("monList", monList);
		model.addAttribute("yearsList", yearList);
		model.addAttribute("thisyear", year);
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/mngFee/GamElctyUsageSttusMng";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/mngFee/gamSelectElctyUsageSttusMng.do" , method=RequestMethod.POST)
    @ResponseBody Map gamSelectElctyUsageSttusMngList(GamElctyUsageSttusMngVo searchVO) throws Exception {

    	int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
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


    @RequestMapping(value="/mngFee/gamInsertElctyUsageSttusMng.do")
	@ResponseBody Map<String, Object> insertElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo)	throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	/*
		CmmnDetailCode vo = gamCarMngService.selectCmmnDetailCodeDetail(cmmnDetailCode);

		if(vo != null){
			map.put("resultCode", 1);
			map.put("resultMsg", "이미 등록된 차량 번호입니다.");
            return map;
    	}
		*/
		try {
			gamElctyUsageSttusMngVo.setRegUsr((String)user.getId());
			gamElctyUsageSttusMngService.insertElctyUsageSttusMng(gamElctyUsageSttusMngVo);

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

    @RequestMapping(value="/mngFee/gamDeleteElctyUsageSttusMng.do")
	@ResponseBody Map<String, Object> deleteElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo)	throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	/*
		CmmnDetailCode vo = gamCarMngService.selectCmmnDetailCodeDetail(cmmnDetailCode);

		if(vo != null){
			map.put("resultCode", 1);
			map.put("resultMsg", "이미 등록된 차량 번호입니다.");
            return map;
    	}
		*/
		try {
			gamElctyUsageSttusMngVo.setRegUsr((String)user.getId());
			gamElctyUsageSttusMngService.deleteElctyUsageSttusMng(gamElctyUsageSttusMngVo);

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

}
