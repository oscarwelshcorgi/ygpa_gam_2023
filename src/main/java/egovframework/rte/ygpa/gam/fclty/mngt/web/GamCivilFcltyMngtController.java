/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.mngt.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.fclty.mngt.service.GamCivilFcltyManageVO;
import egovframework.rte.ygpa.gam.fclty.mngt.service.GamCivilFcltyMngtService;

/**
 * 
 * @author kok
 * @since 2014. 2. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 3.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamCivilFcltyMngtController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	@Resource(name = "civilFcltyMngtService")
	protected GamCivilFcltyMngtService civilFcltyMngtService;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	/**
     * 화면호출
     * @param windowId
     * @param model
     * @return String
     * @throws Exception
     */
	@RequestMapping(value="/fclty/mngt/gamCivilFcltyMngt.do")
    String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/fclty/mngt/GamCivilFcltyMngt";
    }
	
	
	/**
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/mngt/gamCivilFcltyMngtList.do")
	@ResponseBody Map<String, Object> selectCivilFcltyMngtList(@ModelAttribute("searchVO") ComDefaultVO searchVO)throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	// 내역 조회
    	/** EgovPropertyService */
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

		/** List Data */
		List civilFcltyMngtList = civilFcltyMngtService.selectCivilFcltyMngtList(searchVO);
        //int totCnt = menuManageService.selectMenuManageListTotCnt(searchVO);

        //paginationInfo.setTotalRecordCount(totCnt);
		
		map.put("resultCode", 0);			// return ok
//    	map.put("totalCount", totCnt);
    	map.put("resultList", civilFcltyMngtList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 시설관리등록
	 * @param civilFcltyManageVO
	 * @param bindingResult
	 * @param cmd
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value="/fclty/mngt/gamCivilFcltyInsert.do")
    @ResponseBody Map<String, Object> insertCivilFclty(@ModelAttribute("civilFcltyManageVO") GamCivilFcltyManageVO civilFcltyManageVO,BindingResult bindingResult, @RequestParam("cmd") String cmd)
            throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	String resultMsg    = "";
    	
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	if("insert".equals(cmd)) {
		    beanValidator.validate(civilFcltyManageVO, bindingResult);
		    if (bindingResult.hasErrors()){
		        map.put("resultCode", 1);
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}

		    //ComDefaultVO searchVO = new ComDefaultVO();
			//searchVO.setSearchKeyword(civilFcltyManageVO.getPrtFcltyNm());
		    

		   System.out.println("civilFcltyManageVO.getGisPrtFcltyCd()      :" + civilFcltyManageVO.getGisPrtFcltyCd()      );
		   //System.out.println("civilFcltyManageVO.getGisPrtFcltySeq()     :" + civilFcltyManageVO.getGisPrtFcltySeq()     );
		   System.out.println("civilFcltyManageVO.getPrtFcltyNm()         :" + civilFcltyManageVO.getPrtFcltyNm()         );
		   System.out.println("civilFcltyManageVO.getPrtFcltyStndrd()     :" + civilFcltyManageVO.getPrtFcltyStndrd()     );
		   System.out.println("civilFcltyManageVO.getPrtFcltyUnit()       :" + civilFcltyManageVO.getPrtFcltyUnit()       );
		   System.out.println("civilFcltyManageVO.getPrtFcltyInstlDt()    :" + civilFcltyManageVO.getPrtFcltyInstlDt()    );
		   System.out.println("civilFcltyManageVO.getPrtFcltyChangeDt()   :" + civilFcltyManageVO.getPrtFcltyChangeDt()   );
		   System.out.println("civilFcltyManageVO.getRegUsr()             :" + civilFcltyManageVO.getRegUsr()             );
		   System.out.println("civilFcltyManageVO.getRegistDt()           :" + civilFcltyManageVO.getRegistDt()           );
		   //System.out.println("civilFcltyManageVO.getUpdUsr()             :" + civilFcltyManageVO.getUpdUsr()             );
		   //System.out.println("civilFcltyManageVO.getUpdtDt()             :" + civilFcltyManageVO.getUpdtDt()             );
		   System.out.println("civilFcltyManageVO.getPrtFcltySe()         :" + civilFcltyManageVO.getPrtFcltySe()         );
		   System.out.println("civilFcltyManageVO.getPrtFcltyMngEntrpsCd():" + civilFcltyManageVO.getPrtFcltyMngEntrpsCd());
		   System.out.println("civilFcltyManageVO.getPrtFcltyGisCd()      :" + civilFcltyManageVO.getPrtFcltyGisCd()      );
		   System.out.println("civilFcltyManageVO.getGisAssetsPrtAtCode() :" + civilFcltyManageVO.getGisAssetsPrtAtCode() );
		   System.out.println("civilFcltyManageVO.getGisAssetsCd()        :" + civilFcltyManageVO.getGisAssetsCd()        );
		   System.out.println("civilFcltyManageVO.getGisAssetsSubCd()     :" + civilFcltyManageVO.getGisAssetsSubCd()     );
		    
		    
			civilFcltyMngtService.insertCivilFcltyManage(civilFcltyManageVO);

			map.put("resultCode", 0);			// return ok
    		resultMsg = egovMessageSource.getMessage("success.common.insert");
    	}

        map.put("resultMsg", resultMsg);
      	return map;
    }

}