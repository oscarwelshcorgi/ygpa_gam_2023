package egovframework.rte.ygpa.gam.oper.train.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortEntrpsRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortEntrpsRentFeeSttusInqireVO;

/**
 * @Class Name : GamTrainPortEntrpsRentFeeSttusInqireController.java
 * @Description : 철송장임대업체별사용료현황조회 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamTrainPortEntrpsRentFeeSttusInqireController {
	
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
    
    @Resource(name = "gamTrainPortEntrpsRentFeeSttusInqireService")
    private GamTrainPortEntrpsRentFeeSttusInqireService gamTrainPortEntrpsRentFeeSttusInqireService;
	
    
    /**
     * 철송장임대업체별사용료현황 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/train/GamTrainPortEntrpsRentFeeSttusInqire"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/oper/train/gamTrainPortEntrpsRentFeeSttusInqire.do") 
	String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM019"); //항코드 
		List prtAtCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/oper/train/GamTrainPortEntrpsRentFeeSttusInqire"; 
    }
	
	/**
     * 철송장임대업체별사용료현황 목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/train/gamSelectTrainPortEntrpsRentFeeSttusInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map selectTrainPortEntrpsRentFeeSttusInqireList(GamTrainPortEntrpsRentFeeSttusInqireVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();
		
    	List assetRentList = gamTrainPortEntrpsRentFeeSttusInqireService.selectTrainPortEntrpsRentFeeSttusInqireList(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	
    	return map;
    }
	
}
