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
import egovframework.rte.ygpa.gam.mngFee.service.GamCarMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamCarMngVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamCarRefuelSttusMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamCarRefuelSttusMngVo;
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

    @Resource(name = "gamCarMngService")
    private GamCarMngService gamCarMngService;

    @Resource(name = "gamCarRefuelSttusMngService")
    private GamCarRefuelSttusMngService gamCarRefuelSttusMngService;


    @RequestMapping(value="/mngFee/gamCarRefuelSttusMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

    	//login정보
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

		model.addAttribute("yearsList", yearList);
		model.addAttribute("thisyear", year);
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/mngFee/CarRefuelSttusMng";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/mngFee/gamSelectCarRefuelSttusMng.do" , method=RequestMethod.POST)
    @ResponseBody Map gamSelectCarMngList(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) throws Exception {

    	int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
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

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/mngFee/gamInsertCarRefuelSttusMng.do" , method=RequestMethod.POST)
    @ResponseBody Map gamInsertCarRefuelSttusMngList(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) throws Exception {

    	Map map = new HashMap();

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		gamCarRefuelSttusMngVo.setRegUsr((String)user.getId());
    		gamCarRefuelSttusMngService.InsertCarMngList(gamCarRefuelSttusMngVo);

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
