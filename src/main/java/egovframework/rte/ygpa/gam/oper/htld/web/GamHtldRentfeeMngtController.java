package egovframework.rte.ygpa.gam.oper.htld.web;

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
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdService;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentfeeMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentfeeMngtVO;

/**
 * @Class Name : GamHtldRentfeeMngtController.java
 * @Description : 배후단지임대료관리 (항만시설/배후단지/배후단지임대료관리)
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
public class GamHtldRentfeeMngtController {
	
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

    @Resource(name = "erpCmmnCdService")
    private ErpCmmnCdService erpCmmnCdService;

    /*   
    @Resource(name = "erpCmmnCdClService")
    private ErpCmmnCdClService erpCmmnCdClService;
    
    @Resource(name = "erpAssetCdService")
    private ErpAssetCdService erpAssetCdService;
	*/
	
    @Resource(name = "gamHtldRentfeeMngtService")
    private GamHtldRentfeeMngtService gamHtldRentfeeMngtService;
    
    /**
	 * 배후단지임대료관리 화면으로 이동한다.
	 * @param windowId
	 * @return 배후단지임대료관리 화면
	 * @exception Exception
	 */
    @RequestMapping(value="/oper/htld/gamHtldRentfeeMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
    	model.addAttribute("windowId", windowId);

    	ErpCmmnCdVO searchVO = new ErpCmmnCdVO();

    	searchVO.setBigClsCd("0401");	// 제코드구분(자산)
    	searchVO.setFirstIndex(0);
    	searchVO.setLastIndex(99);
    	List erpAssetClsList = erpCmmnCdService.selectErpCmmnCdList(searchVO);
    	model.addAttribute("erpAssetClsList", erpAssetClsList);
    	
    	return "/ygpa/gam/oper/htld/GamHtldRentfeeMngt";
    }

    /**
	 * 배후단지임대료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대료관리 목록
	 * @exception Exception
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htld/selectGamHtldRentfeeMngt.do", method=RequestMethod.POST)
    @ResponseBody Map selectGamHtldRentfeeMngtList(GamHtldRentfeeMngtVO searchVO) throws Exception {
    	
    	int totalCnt, page, firstIndex;
    	Map map = new HashMap();
    	
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	totalCnt = gamHtldRentfeeMngtService.selectGamHtldRentfeeMngtListTotCnt(searchVO);
    	List resultList = gamHtldRentfeeMngtService.selectGamHtldRentfeeMngtList(searchVO);
    	
    	// 배후단지임대료관리 정보
    	GamHtldRentfeeMngtVO resultInfo = gamHtldRentfeeMngtService.selectGamHtldRentfeeMngtInfo(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);
    	
    	map.put("resultInfo", resultInfo);

    	return map;
    }

}
