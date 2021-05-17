/**
 *
 */
package egovframework.rte.ygpa.gam.oper.htldnew.web;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ygpa.gam.code.service.GamCofixIntrrateVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldBizAssessVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldQuGtqyVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngtMainService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngtMainVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentRntfeeVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldBizAssessService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldBizAssessVO;

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
public class GamHtldRentMngtMainController {

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

    /** gamHtldRentMngtMainService */
    @Resource(name="gamHtldRentMngtMainService")
    private GamHtldRentMngtMainService gamHtldRentMngtMainService;

    @Resource(name="gamPopupHtldBizAssessService")
    private GamPopupHtldBizAssessService gamPopupHtldBizAssessService;

    /**
     * 배후단지 임대관리메인화면을 로딩한다.
     * @param windowId
     * @param model
     * @return /ygpa/gam/oper/htldnew/GamHtldRentMngtMain
     */
    @RequestMapping(value="/oper/htldnew/gamHtldRentMngtMain.do")
    public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) {
    	model.addAttribute("windowId", windowId);
    	return "/ygpa/gam/oper/htldnew/GamHtldRentMngtMain";
    }

    /**
     * 배후단지임대상세목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htldnew/selectHtldRentDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectHtldRentDetailList(GamHtldRentMngtMainVO searchVO) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	List resultList = gamHtldRentMngtMainService.selectHtldRentDetailList(searchVO);
    	String cofixIntrrate = gamHtldRentMngtMainService.selectCofixIntrrate(searchVO);

    	map.put("resultCode", 0);
    	map.put("resultList", resultList);
    	map.put("cofixIntrrate", cofixIntrrate);

    	return map;
	}

	/**
     * 배후단지 임대료를 저장한다.
     * @param Map
     * @return Map
     * @throws Exception
     */
	@RequestMapping(value="/oper/htldnew/updateHtldRntfee.do")
	public @ResponseBody Map<String, Object> updateHtldRntfee(@RequestParam Map<String, Object> rentFeeData) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		ObjectMapper mapper = new ObjectMapper();

		//사용자 인증 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    	List<GamHtldRentRntfeeVO> feeInsertList = null;
    	List<GamHtldRentRntfeeVO> feeUpdateList = null;

    	if(rentFeeData.containsKey("feeInsertList")) {
    		feeInsertList = mapper.readValue((String)rentFeeData.get("feeInsertList"), TypeFactory.defaultInstance().constructCollectionType(List.class, GamHtldRentRntfeeVO.class));
    	}

    	if(rentFeeData.containsKey("feeUpdateList")) {
    		feeUpdateList = mapper.readValue((String)rentFeeData.get("feeUpdateList"), TypeFactory.defaultInstance().constructCollectionType(List.class, GamHtldRentRntfeeVO.class));
    	}

    	try {
    		gamHtldRentMngtMainService.updateHtldRntfee(feeInsertList, feeUpdateList, loginVO.getId());
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
    	} catch (IOException i) {
    		map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
    	} catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
    	}

    	return map;
	}

    /**
     * 전년도 배후단지임대계약 복사.
     *
     * @param
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htldnew/gamCopyRentContract.do", method=RequestMethod.POST)
	public @ResponseBody Map gamCopyAllRentContract(GamHtldRentMngtMainVO searchVO) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
    		gamHtldRentMngtMainService.insertCopyAllRentContract(searchVO);

    		map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.request.msg"));
    	} catch (IOException i) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
    	}
    	catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.request.msg"));
    	}

    	return map;
	}


    /**
     * 배후단지 물동량 조회.
     *
     * @param
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/htldnew/gamHtldQuGtqyList.do", method=RequestMethod.POST)
	public @ResponseBody Map gamHtldQuGtqyList(GamHtldQuGtqyVO searchVO) throws Exception {
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	List resultList = gamHtldRentMngtMainService.selectHtldQuGtqyList(searchVO);

    	map.put("resultCode", 0);
    	map.put("resultList", resultList);

    	return map;
	}

    /**
     * 배후단지 물동량 등록/수정/삭제.
     *
     * @param
     * @return map
     * @throws Exception the exception
     */
    @RequestMapping(value="/oper/htldnew/updateHtldQuGtqyList.do" , method=RequestMethod.POST)
    public @ResponseBody Map updateHtldQuGtqyList(@RequestParam Map<String, Object> intrrateList) throws Exception {

     	 Map map = new HashMap();
         ObjectMapper mapper = new ObjectMapper();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	try {
        	List<GamHtldQuGtqyVO> createList=null, updateList=null, deleteList=null;
        	if(intrrateList.containsKey("_cList")) {
    	    	createList = mapper.readValue((String)intrrateList.get("_cList"), TypeFactory.defaultInstance().constructCollectionType(List.class, GamHtldQuGtqyVO.class));
        	}
        	if(intrrateList.containsKey("_uList")) {
    	    	updateList = mapper.readValue((String)intrrateList.get("_uList"), TypeFactory.defaultInstance().constructCollectionType(List.class,GamHtldQuGtqyVO.class));
        	}
        	if(intrrateList.containsKey("_dList")) {
        		deleteList = mapper.readValue((String)intrrateList.get("_dList"), TypeFactory.defaultInstance().constructCollectionType(List.class,GamHtldQuGtqyVO.class));
        	}

        	gamHtldRentMngtMainService.updateHtldQuGtqyList(createList, updateList, deleteList);

	     	 map.put("resultCode", 0);
	         map.put("resultMsg", egovMessageSource.getMessage("gam.asset.proc"));
    	}
    	catch(IOException i) {
    		map.put("resultCode", 1);
	        map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
    	}
    	catch(Exception e) {
	     	 map.put("resultCode", 1);
	         map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
    	}

 		return map;
     }


	/**
     * 실적평가 등록
     * @param Map
     * @return Map
     * @throws Exception
     */
	@RequestMapping(value="/oper/htldnew/updateBizAssessList.do")
	public @ResponseBody Map<String, Object> updateBizAssessList(@RequestParam Map<String, Object> assessList) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();

		//사용자 인증 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    	try {
    		List<GamHtldBizAssessVO> updateList;
            ObjectMapper mapper = new ObjectMapper();
	    	updateList = mapper.readValue((String)assessList.get("gridData"), TypeFactory.defaultInstance().constructCollectionType(List.class,GamHtldBizAssessVO.class));

	    	for(int i=0; i<updateList.size(); i++) {
	    		GamHtldBizAssessVO updateVo = updateList.get(i);
	    		gamHtldRentMngtMainService.updateRntfeeBizAssess(updateVo, loginVO.getId());
	    	}
	        map.put("resultCode", 0);
    		map.put("resultMsg", egovMessageSource.getMessage("success.common.update"));
    	} catch (IOException i) {
    		Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
    	} catch(Exception e) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.update"));
    	}

    	return map;
	}
}
