package egovframework.rte.ygpa.gam.code.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.ccm.cca.service.CmmnCode;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.code.service.GamBuldMktcStdAmManageService;
import egovframework.rte.ygpa.gam.code.service.GamBuldMktcStdAmVO;
/**
 *
 * @author Administrator
 * @since 2014. 1. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 1. 22.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamBuldMktcStdAmController{
	
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	@Resource(name = "gamBuldMktcStdAmManageService")
    private GamBuldMktcStdAmManageService gamBuldMktcStdAmManageService;
	
	/**
	 * 화면 호출
	 * @param windowId
	 * @param model
	 * @return String
	 * @throws Exception
	 */

   @RequestMapping(value="/code/gamBuldMktcStdAm.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/code/GamBuldMktcStdAm";
   }
    
    /**
	 * 건물시가표준액 목록을 조회한다.
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
   @RequestMapping(value="/code/gamBuldMktcStdAmList.do")
   @ResponseBody Map<String, Object> selectBuldMktcStdAmList(GamBuldMktcStdAmVO searchVO)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();

       	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
       	if(!isAuthenticated) {
   	        map.put("resultCode", 1);
       		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
           	return map;
   }
  
/*       	// 내역 조회
       	*//** pageing *//*
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());       	
       	  	*/
       	
       	List<?> BuldMktcStdAmList = gamBuldMktcStdAmManageService.selectBuldMktcStdAmList(searchVO);
		int totCnt = gamBuldMktcStdAmManageService.selectBuldMktcStdAmListTotCnt(searchVO);

           /*paginationInfo.setTotalRecordCount(totCnt);
           searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());*/
				        
   		map.put("resultCode", 0);			// return ok
   		map.put("totalCount", totCnt);
       	map.put("resultList", BuldMktcStdAmList);
   		map.put("searchOption", searchVO);

   		return map;
   	}
   
  
   /**
    * 건축물시가표준액상세항목을 등록한다.
    * @param inputVO
    * @throws Exception
    */
   @RequestMapping(value="/code/gamBuldMktcStdAmInsertList.do")
   @ResponseBody Map<String, Object> insertBuldMktcStdAm( GamBuldMktcStdAmVO inputVO, Model model) throws Exception {
   
	   Map<String, Object> map = new HashMap<String, Object>();
   	
	   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	  
	   if(!isAuthenticated) {
        map.put("resultCode", 1);
		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	return map;
	   }

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		inputVO.setRegister(user.getId());
		
		try {
			//inputVO.setAdres(user.getId());
			gamBuldMktcStdAmManageService.insertBuldMktcStdAmList(inputVO);

			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch(IOException e){
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}
		return map;
	}
   
   /**
    * 건축물시가표준액상세항목을 수정한다.
    * @param inputVO
    * @throws Exception
    */
   @RequestMapping(value="/code/gamBuldMktcStdAmUpdateList.do")
   @ResponseBody Map<String, Object> updateBuldMktcStdAmList(GamBuldMktcStdAmVO inputVO) throws Exception {
	   
	   Map<String, Object> map = new HashMap<String, Object>();
	   
	   	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
	        map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
	    	return map;
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		inputVO.setUpdusr(user.getId());
		
		try {
			//inputVO.setRegister(user.getId());
			gamBuldMktcStdAmManageService.updateBuldMktcStdAmList(inputVO);
			
			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}
		return map;
	}
   
   /**
    * 건축물시가표준액상세항목을 삭제한다.
    * @param deleteVO
    * @throws Exception
    */
   @RequestMapping(value="/code/gamBuldMktcStdAmDeleteList.do")
	@ResponseBody Map<String, Object> deleteBuldMktcStdAm(GamBuldMktcStdAmVO deleteVO) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
			return map;
		}
		
		try {
			gamBuldMktcStdAmManageService.deleteBuldMktcStdAm (deleteVO);
			
			map.put("resultCode", 0);
			map.put("resultMsg", egovMessageSource.getMessage("success.common.delete"));
		} catch(IOException e){
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.delete"));
		}
		return map;
	}
}
