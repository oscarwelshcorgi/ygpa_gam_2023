/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.web;


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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupRoadMngGroupService;

/**
 * 
 * @author HNJ
 * @since 2014. 5. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 5. 7.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamPopupRoadMngGroupController {
	
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
    
    /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;
    
    @Resource(name = "gamPopupRoadMngGroupService")
    private GamPopupRoadMngGroupService gamPopupRoadMngGroupService;
	
	
	/**
	 * 관리그룹 팝업
	 * @param searchOpt
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/popup/showRoadMngGroup.do")
    String showRoadMngGroupList(ModelMap model) throws Exception {
    	return "/ygpa/gam/popup/GamPopupRoadMngGroup";
    }

	/**
	 * 관리그룹 팝업 조회
	 * @param searchOpt
	 * @return Map
	 * @throws Exception
	 */
    @RequestMapping(value="/popup/selectRoadMngGroupList.do")
	@ResponseBody Map selectRoadMngGroupList() throws Exception {
		int totalCnt;
    	Map map = new HashMap();

		List resultList = gamPopupRoadMngGroupService.selectRoadMngGroupList();

    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", resultList);

    	return map;
    }
    
}
