/**
 * 
 */
package egovframework.rte.ygpa.gam.code.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.code.service.GamCmpyInfoMngtService;
import egovframework.rte.ygpa.gam.code.service.GamEntrpsChargerFVO;
import egovframework.rte.ygpa.gam.code.service.GamEntrpsInfoFVO;
import egovframework.rte.ygpa.gam.sample.web.GamAssetMngSampleController;
/**
 * 
 * @author kok
 * @since 2014. 3. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 5.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamCmpyInfoMngtController {

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	@Resource(name = "gamCmpyInfoMngtService")
    private GamCmpyInfoMngtService gamCmpyInfoMngtService;
	
	@Autowired
	private DefaultBeanValidator beanValidator;
	
    /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    
    protected static final Log LOG = LogFactory.getLog(GamAssetMngSampleController.class);
    
    /**
     * 화면 호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/code/gamCmpyInfoMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);

    	// 관리부서
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
    	vo.setTableNm("COMTNORGNZTINFO");
        List<?> groupId_result = cmmUseService.selectOgrnztIdDetail(vo);
        model.addAttribute("ogrnztId_result",         groupId_result);

        return "/ygpa/gam/code/GamCmpyInfoMngt";
    }
    
    
    /**
	 * 업체정보 목록을 조회한다.
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/code/gamCmpyInfoMngtList.do")
	@ResponseBody Map<String, Object> selectCmpyInfoMngtList(GamEntrpsInfoFVO searchVO,
			@RequestParam("searchEntrpsCd") String searchEntrpsCd, @RequestParam("searchEntrpsTy") String searchEntrpsTy,
			@RequestParam("searchBizrno") String searchBizrno, @RequestParam("searchBsnmSe") String searchBsnmSe) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		searchVO.setEntrpscd(searchEntrpsCd);
		searchVO.setEntrpsTy(searchEntrpsTy);
		searchVO.setBizrno(searchBizrno);
		searchVO.setBsnmSe(searchBsnmSe);
        List cmpyInfoMngtList = gamCmpyInfoMngtService.selectCmpyInfoMngtList(searchVO);
        int totCnt = gamCmpyInfoMngtService.selectCmpyInfoMngtListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", cmpyInfoMngtList);
    	map.put("searchOption", searchVO);

    	return map;
	}

    
    /**
     * 업체정보관리 상세화면
     * @param entrpscd
     * @return map
     * @throws Exception
     */
	@RequestMapping(value="/code/cmpyInfoMngtDetail.do")
 	@ResponseBody Map<String, Object> selectCmpyInfoMngtDetail (@RequestParam("entrpscd") String entrpscd) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		GamEntrpsInfoFVO vo = new GamEntrpsInfoFVO();
		vo.setEntrpscd(entrpscd);
		
		GamEntrpsInfoFVO detail = gamCmpyInfoMngtService.selectCmpyInfoMngtDetail(vo);

		map.put("detail", detail);
		return map;
	}


	/**
	 * 업체 목록을 조회한다.
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/code/gamCmpyMngtList.do")
	@ResponseBody Map<String, Object> selectCmpyMngtList(GamEntrpsChargerFVO searchVO) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List cmpyMngtList = gamCmpyInfoMngtService.selectCmpyMngtList(searchVO);
        int totCnt = gamCmpyInfoMngtService.selectCmpyMngtListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);

		map.put("resultCode", 0);			// return ok
    	map.put("totalCount", totCnt);
    	map.put("resultList", cmpyMngtList);
    	map.put("searchOption", searchVO);

    	return map;
	}
    
    
    /**
     * 업체담당자정보 상세화면
     * @param entrpscd
     * @return map
     * @throws Exception
     */
	@RequestMapping(value="/code/cmpyMngtDetail.do")
 	@ResponseBody Map<String, Object> selectCmpyMngtDetail (@RequestParam("entrpscd") String entrpscd, @RequestParam("chargerNo") Integer chargerNo) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		GamEntrpsChargerFVO vo = new GamEntrpsChargerFVO();
		vo.setChargerNo(chargerNo);
		vo.setEntrpscd(entrpscd);
		
		GamEntrpsChargerFVO detail = gamCmpyInfoMngtService.selectCmpyMngtDetail(vo);

		map.put("detail", detail);
		return map;
	}

	
	/**
	 * 업체정보 관리 저장
	 * @param chargerVo
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/code/gamCmpyInfoMngtRegist.do")
	@ResponseBody Map<String, Object> insertCmpyInfoMngt(@RequestParam Map<String, Object> cmpyMngtList) throws Exception {    
    	
    	Map<String, Object> map = new HashMap<String, Object>();

    	cmpyMngtList.put("USERID", user.getId());
    	gamCmpyInfoMngtService.insertCmpyInfoMngt(cmpyMngtList);
    	
    	map.put("resultCode", 0);			// return ok
		map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));

        return map;
    }
    
    
    /*
	 * 공통코드관리를 등록한다.
	 * @param loginVO
	 * @param cmmnCode
	 * @param bindingResult
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping(value="/code/gamCheckEntrpscd.do")
	@ResponseBody Map<String, Object> checkEntrpscd (@RequestParam("entrpscd") String entrpscd) throws Exception {    
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	int codeCount = gamCmpyInfoMngtService.checkEntrpscd(entrpscd);
    	
    	map.put("codeCount", codeCount);			// return ok
    	map.put("resultCode", 0);			// return ok

        return map;
    }
    
/*
    /**
     * 공통코드관리를 수정한다.
     * @param loginVO
     * @param cmmnCode
     * @param bindingResult
     * @param commandMap
     * @return map
     * @throws Exception
     
    @RequestMapping(value="/code/gamCcmCmmnCodeModify.do")
	@ResponseBody Map<String, Object> updateCmmnCode (@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("cmmnCode") CmmnCode cmmnCode
			, BindingResult bindingResult, @ModelAttribute("cmd") String cmd) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
    	
		if (cmd.equals("")) {
    		CmmnCode vo = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCode);
    		map.put("cmmnCode", vo);

    		map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
    		return map;
    	} else if (cmd.equals("modify")) {
            beanValidator.validate(cmmnCode, bindingResult);
    		if (bindingResult.hasErrors()){
        		CmmnCode vo = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCode);
        		map.put("cmmnCode", vo);

        		map.put("resultCode", 1);
        		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
        		return map;
    		}
    		
    		if("".equals(loginVO.getUniqId()) || loginVO.getUniqId() == null){
    			cmmnCode.setLastUpdusrId("TESTUPDATEID");
    		}

	    	cmmnCodeManageService.updateCmmnCode(cmmnCode);
	    	
	    	map.put("resultCode", 0);
	    	map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
	        return map;
    	} else {
    		return map;
    	}
    }
    
    
    /**
     * 공통코드관리를 삭제한다.
     * @param cmmnCode
     * @return map
     * @throws Exception
     
    @RequestMapping(value="/code/gamCcmCmmnCodeRemove.do")
	@ResponseBody Map<String, Object> deleteCmmnCode (@ModelAttribute("clCode") CmmnCode cmmnCode) throws Exception {
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	cmmnCodeManageService.deleteCmmnCode(cmmnCode);
        
    	map.put("resultCode", 0);
      	map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
    	return map;
	}*/
}