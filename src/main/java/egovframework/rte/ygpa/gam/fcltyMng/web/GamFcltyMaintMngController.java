/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.web;

//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
//import egovframework.com.cmm.LoginVO;
//import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
//import egovframework.rte.psl.dataaccess.util.EgovMap;
//import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
//import egovframework.rte.ygpa.gam.fclty.service.GamFcltyMaintMngService;

/**
 *
 * @author HNJ
 * @since 2014. 11. 20.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 20.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamFcltyMaintMngController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

//	@Resource(name = "gamFcltyMaintMngService")
//	protected GamFcltyMaintMngService gamFcltyMaintMngService;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;


	/**
     * 유지보수내역 관리화면호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/fcltyMng/gamFcltyMaintMng.do")
    String indexFcltyMaintMng(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fcltyMng/GamFcltyMaintMng";
    }


	

}