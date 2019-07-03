/**
 * 
 */
package egovframework.rte.ygpa.gam.asset.web;

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

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ygpa.gam.code.service.GamBuldMktcStdAmManageService;
import egovframework.rte.ygpa.gam.code.service.GamBuldMktcStdAmVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 4. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 4. 25.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamPopupBuldMktcStdAmController {
	protected Log log = LogFactory.getLog(this.getClass());
	
	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name="gamBuldMktcStdAmManageService")
    GamBuldMktcStdAmManageService gamBuldMktcStdAmManageService;

    /**
     * 건축물 시가표준액 팝업화면을 로딩한다.
     * @param vo
     * @param model
     * @return /ygpa/gam/asset/GamPopupBuldMktcStdAm
     */
    @RequestMapping(value="/asset/showPopupBuldMktcStdAm.do")
    public String showPopupBuldMktcStdAm(GamBuldMktcStdAmVO searchOpt, ModelMap model) {
    	model.addAttribute("searchOpt", searchOpt);
    	return "/ygpa/gam/asset/GamPopupBuldMktcStdAm";
    }
    
    @RequestMapping(value="/asset/selectPopupBuldMktcStdAm.do")
	@ResponseBody Map<String, Object> selectPopupBuldMktcStdAm (GamBuldMktcStdAmVO searchOpt) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	List<?> buldMktcStdAmList = gamBuldMktcStdAmManageService.selectBuldMktcStdAmList(searchOpt);

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", buldMktcStdAmList.size());
    	map.put("resultList", buldMktcStdAmList);

    	return map;
	}
}
