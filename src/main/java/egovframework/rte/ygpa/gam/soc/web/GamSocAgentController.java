/**
 *
 */
package egovframework.rte.ygpa.gam.soc.web;

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
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;












import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.soc.service.GamSocAgentService;
import egovframework.rte.ygpa.gam.soc.service.GamSocAgentVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseVO;



/**
 *
 * @author Lee
 * @since 2014. 9. 19.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 19.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamSocAgentController {

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

    @Resource(name = "gamSocCmmUseService")
    private GamSocCmmUseService gamSocCmmUseService;
    
    @Resource(name = "gamSocAgentService")
    private GamSocAgentService gamSocAgentService;


    @RequestMapping(value="/soc/gamSocAgent.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
    	//login정보 
    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    	GamSocCmmUseVO codeVo = new GamSocCmmUseVO();
		
		codeVo.setCodeId("GAM019"); //항코드 
		
		List prtAtCdList = gamSocCmmUseService.selectSocPrtAtCodeDetail();
		List yearsList = this.getYears(); // 조회연도
		
		model.addAttribute("prtAtCdList", prtAtCdList);
		model.addAttribute("windowId", windowId);
		model.addAttribute("yearsList", yearsList);

    	return "/ygpa/gam/soc/GamSocAgent";
    }
    
    
    /**
     * 조회기간 연도를 가져온다
     *
     */
	public List getYears(){

		java.util.Calendar cal = java.util.Calendar.getInstance();
		int currentYear = cal.get(cal.YEAR);
		List result = new ArrayList();
   		
   		for (int i = 2000; i <= currentYear; i++) {
   			
   			result.add(String.valueOf(i));
   		}

   		return result;
   	}
	
	
	/**
     * 항만공사허가원부목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/soc/gamSelectSocAgentList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectSocAgentList(GamSocAgentVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
		long sumTotalAmnt, sumAccFee;
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
		

		//항만공사허가원부 검색업체 정보
		GamSocAgentVO socAgentInfo = gamSocAgentService.selectSocAgentInfo(searchVO);
		
		if(socAgentInfo != null){
			searchVO.setPrtAtCode(socAgentInfo.getPrtAtCode());
			searchVO.setAgentCode(socAgentInfo.getAgentCode());
			searchVO.setCmplYr(socAgentInfo.getCmplYr());
		}

		//항만공사허가원부목록
    	List socAgentList = gamSocAgentService.selectSocAgentList(searchVO);
    	
    	//항만공사허가원부목록 총갯수 및 금액합계
		GamSocAgentVO socAgentInfoSum = gamSocAgentService.selectSocAgentInfoSum(searchVO);
    	
		totalCnt = socAgentInfoSum.getTotalCnt();
    	sumTotalAmnt = socAgentInfoSum.getSumTotalAmnt();
    	sumAccFee = socAgentInfoSum.getSumAccFee();
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
        
 
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("sumTotalAmnt", sumTotalAmnt);
    	map.put("sumAccFee", sumAccFee);
    	map.put("resultList", socAgentList);
    	map.put("socAgentInfo", socAgentInfo);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	
	/**
	 * 항만공사시행허가원부 입력
	 * @param socAgentList
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping("/soc/gamInsertSocAgentList.do")
    @ResponseBody Map<String, Object> insertSocAgentList(@RequestParam Map socAgentList)throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	Map<String, Object> updateTotal = new HashMap<String, Object>();
    	ObjectMapper mapper = new ObjectMapper();
    	
    	List<HashMap<String,String>> updateList=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

//    	socAgentList.put("USERID", user.getId());
//    	socAgentList.put("prtFcltySe",prtFcltySe);
    	
    	updateList = mapper.readValue((String)socAgentList.get("updateList"),new TypeReference<List<HashMap<String,String>>>(){});
    	
    	Map searchData = mapper.readValue((String)socAgentList.get("searchData"),new TypeReference<HashMap<String,String>>(){});
    	Map updateData = mapper.readValue((String)socAgentList.get("updateData"),new TypeReference<HashMap<String,String>>(){});
    	Map updateData1 = mapper.readValue((String)socAgentList.get("updateData1"),new TypeReference<HashMap<String,String>>(){});

    	updateTotal.putAll(updateData);
    	updateTotal.putAll(updateData1);
    	updateTotal.putAll(searchData);

    	try {
    		
    		gamSocAgentService.insertSocAgentData(updateTotal);
    		
    		for(int i=0;i<updateList.size();i++){
    			HashMap updateSubData = (HashMap)updateList.get(i);
    			
    			updateSubData.put("prtAtCode", updateTotal.get("sPrtAtCode"));
    			updateSubData.put("cmplYr", updateTotal.get("sCmplYr"));
    			updateSubData.put("constNo", updateTotal.get("sConstNo"));
    			
    			gamSocAgentService.insertSocAgentList(updateSubData);
    		}
    		map.put("resultCode", 0);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

    	return map;
    }
	
	
	/**
	 * 항만공사시행허가원부 수정
	 * @param socAgentList
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping("/soc/gamUpdateSocAgentList.do")
    @ResponseBody Map<String, Object> updateSocAgentList(@RequestParam Map socAgentList)throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	Map<String, Object> updateTotal = new HashMap<String, Object>();
    	ObjectMapper mapper = new ObjectMapper();
    	
    	List<HashMap<String,String>> updateList=null;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

//    	socAgentList.put("USERID", user.getId());
//    	socAgentList.put("prtFcltySe",prtFcltySe);
    	
    	updateList = mapper.readValue((String)socAgentList.get("updateList"),new TypeReference<List<HashMap<String,String>>>(){});
    	
    	Map searchData = mapper.readValue((String)socAgentList.get("searchData"),new TypeReference<HashMap<String,String>>(){});
    	Map updateData = mapper.readValue((String)socAgentList.get("updateData"),new TypeReference<HashMap<String,String>>(){});
    	Map updateData1 = mapper.readValue((String)socAgentList.get("updateData1"),new TypeReference<HashMap<String,String>>(){});

    	updateTotal.putAll(updateData);
    	updateTotal.putAll(updateData1);
    	updateTotal.putAll(searchData);

    	try {
    		
    		gamSocAgentService.updateSocAgentData(updateTotal);
    		
    		gamSocAgentService.deleteSocAgentList(searchData);
    		
    		for(int i=0;i<updateList.size();i++){
    			HashMap updateSubData = (HashMap)updateList.get(i);
    			
    			updateSubData.put("prtAtCode", updateTotal.get("sPrtAtCode"));
    			updateSubData.put("cmplYr", updateTotal.get("sCmplYr"));
    			updateSubData.put("constNo", updateTotal.get("sConstNo"));
    			
    			gamSocAgentService.insertSocAgentList(updateSubData);
    		}
    		map.put("resultCode", 0);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

    	return map;
    }
    
    
    /**
	 * 항만공사시행허가원부 삭제
	 * @param socAgentList
	 * @return map
	 * @throws Exception
	 */
    @RequestMapping("/soc/gamDeleteSocAgentList.do")
    @ResponseBody Map<String, Object> deleteSocAgentList(@RequestParam Map socAgentList)throws Exception {

    	Map<String, Object> map = new HashMap<String, Object>();
    	ObjectMapper mapper = new ObjectMapper();
    	

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

//    	socAgentList.put("USERID", user.getId());
//    	socAgentList.put("prtFcltySe",prtFcltySe);
    	
    	Map searchData = mapper.readValue((String)socAgentList.get("searchData"),new TypeReference<HashMap<String,String>>(){});

    	try {
    		
    		gamSocAgentService.deleteSocAgentData(searchData);
    		gamSocAgentService.deleteSocAgentList(searchData);
    		
    		map.put("resultCode", 0);			// return ok
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
		}

    	return map;
    }
}
