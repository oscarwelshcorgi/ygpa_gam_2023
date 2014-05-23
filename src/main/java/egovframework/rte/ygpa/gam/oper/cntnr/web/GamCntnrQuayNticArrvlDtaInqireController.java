package egovframework.rte.ygpa.gam.oper.cntnr.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayNticArrvlDtaInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayNticArrvlDtaInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayNticArrvlDtaInqireService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayNticArrvlDtaInqireVO;

/**
 * @Class Name : GamCntnrQuayNticArrvlDtaInqireController.java
 * @Description : 컨테이너부두임대고지도래현황조회
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
public class GamCntnrQuayNticArrvlDtaInqireController {
	
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
    
    @Resource(name = "gamCntnrQuayNticArrvlDtaInqireService")
    private GamCntnrQuayNticArrvlDtaInqireService gamCntnrQuayNticArrvlDtaInqireService;
	
    
    /**
     * 컨테이너부두임대관리 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/cntnr/GamCntnrQuayNticArrvlDtaInqire"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/oper/cntnr/gamCntnrQuayNticArrvlDtaInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		//공시지가정보
		GamCntnrQuayNticArrvlDtaInqireVO gvo = new GamCntnrQuayNticArrvlDtaInqireVO();
		List olnlpList = gamCntnrQuayNticArrvlDtaInqireService.selectOlnlpInfo();
		
		model.addAttribute("olnlpList", olnlpList);
		model.addAttribute("loginOrgnztId", loginVO.getOrgnztId());
		model.addAttribute("loginUserId", loginVO.getId());
		model.addAttribute("grUsagePdFromStr", EgovDateUtil.formatDate(EgovDateUtil.getToday(), "-"));
		model.addAttribute("grUsagePdToStr",   EgovDateUtil.formatDate(EgovDateUtil.addYearMonthDay(EgovDateUtil.getToday(),0,1,0), "-"));  
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/oper/cntnr/GamCntnrQuayNticArrvlDtaInqire"; 
    }
	
	/**
     * 컨테이너부두임대목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/cntnr/gamSelectCntnrQuayNticArrvlDtaInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map selectCntnrQuayNticArrvlDtaInqireList(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
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
		
		//컨테이너부두임대목록
    	totalCnt = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqireListTotCnt(searchVO);
    	List assetRentList = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqireList(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	//총면적, 총사용료
    	GamCntnrQuayNticArrvlDtaInqireVO resultSum = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqireSum(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	//map.put("sumGrAr", resultSum.getSumGrAr());
    	//map.put("sumGrFee", resultSum.getSumGrFee());
    	map.put("totalNticAmt", resultSum.getSumNticAmt());
    	
    	return map;
    }
	
	/**
     * 컨테이너부두임대상세리스트를 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/cntnr/gamSelectCntnrQuayNticArrvlDtaInqireDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectCntnrQuayNticArrvlDtaInqireDetailList(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
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

		// 컨테이너부두임대상세리스트 및 총건수
		totalCnt = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqireDetailListTotCnt(searchVO);
		List resultList = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqireDetailList(searchVO);
		
		paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	/**
     * 컨테이너부두임대,상세,첨부파일을 저장한다.
     * @param dataList
     * @return map
     * @throws Exception
     */
	@RequestMapping(value="/oper/cntnr/gamSaveCntnrQuayNticArrvlDtaInqire.do")
	@ResponseBody Map<String, Object> saveCntnrQuayNticArrvlDtaInqire(@RequestParam Map<String, Object> dataList) throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		GamCntnrQuayNticArrvlDtaInqireDetailVO saveDetailVO = new GamCntnrQuayNticArrvlDtaInqireDetailVO(); 
		
		Map<String,Object> map = new HashMap<String,Object>();
		ObjectMapper mapper = new ObjectMapper();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	List<HashMap<String,String>> insertList=null;
    	List<HashMap<String,String>> updateList=null;
    	List<HashMap<String,String>> deleteList=null;
    	List<HashMap<String,String>> insertFileList=null;
    	List<HashMap<String,String>> updateFileList=null;
    	List<HashMap<String,String>> deleteFileList=null;
    	HashMap<String,String> form=null;
    	
    	try {
    		insertList = mapper.readValue((String)dataList.get("insertList"),
    		    new TypeReference<List<HashMap<String,String>>>(){});

    		updateList = mapper.readValue((String)dataList.get("updateList"),
        		    new TypeReference<List<HashMap<String,String>>>(){});

    		deleteList = mapper.readValue((String)dataList.get("deleteList"),
        		    new TypeReference<List<HashMap<String,String>>>(){});

    		form = mapper.readValue((String)dataList.get("form"),
        		    new TypeReference<HashMap<String,String>>(){});
    		
    		
    		insertFileList = mapper.readValue((String)dataList.get("insertFileList"),
        		    new TypeReference<List<HashMap<String,String>>>(){});
    		
    		updateFileList = mapper.readValue((String)dataList.get("updateFileList"),
        		    new TypeReference<List<HashMap<String,String>>>(){});
    		
    		deleteFileList = mapper.readValue((String)dataList.get("deleteFileList"),
        		    new TypeReference<List<HashMap<String,String>>>(){});
    		
    		log.debug("##############################################################################################");
    		log.debug("###################################################### dataList : "+dataList);
    		log.debug("###################################################### form : "+form);
    		log.debug("###################################################### cmd : "+form.get("cmd"));
    		log.debug("----------------------------------------------------------------------------------------------");
    		log.debug("###################################################### insertList : "+insertList);
    		log.debug("###################################################### updateList : "+updateList);
    		log.debug("###################################################### deleteList : "+deleteList);
    		log.debug("###################################################### insertList.size() => "+insertList.size());
    		log.debug("###################################################### updateList.size() => "+updateList.size());
    		log.debug("###################################################### deleteList.size() => "+deleteList.size());
    		log.debug("----------------------------------------------------------------------------------------------");
    		log.debug("###################################################### insertFileList : "+insertFileList);
    		log.debug("###################################################### updateFileList : "+updateFileList);
    		log.debug("###################################################### deleteFileList : "+deleteFileList);
    		log.debug("###################################################### insertFileList.size() => "+insertFileList.size());
    		log.debug("###################################################### updateFileList.size() => "+updateFileList.size());
    		log.debug("###################################################### deleteFileList.size() => "+deleteFileList.size());
    		log.debug("##############################################################################################");
    		
    		
    		//컨테이너부두임대저장
    		GamCntnrQuayNticArrvlDtaInqireVO saveVO= new GamCntnrQuayNticArrvlDtaInqireVO();
			saveVO.setPrtAtCode(form.get("prtAtCode"));
			saveVO.setDeptcd(loginVO.getOrgnztId());     
			saveVO.setMngYear(form.get("mngYear"));    
			saveVO.setMngNo(form.get("mngNo"));      
			saveVO.setMngCnt(form.get("mngCnt"));     
			saveVO.setEntrpscd(form.get("entrpscd"));   
			saveVO.setFrstReqstDt(form.get("frstReqstDt"));
			saveVO.setReqstDt(form.get("reqstDt"));
			saveVO.setPayMth(form.get("payMth"));     
			saveVO.setNticMth(form.get("nticMth"));    
			saveVO.setRm(form.get("rm"));         
			saveVO.setCmt(form.get("cmt")); 
    		saveVO.setUpdUsr(loginVO.getId());
    		
    		//if( form.get("cmd") != null && "insert".equals(form.get("cmd")) ) {
    		if( form.get("mngYear") == null || "".equals(form.get("mngYear")) ) {
    			GamCntnrQuayNticArrvlDtaInqireVO keyVO = new GamCntnrQuayNticArrvlDtaInqireVO();
    			keyVO = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqireMaxKey(saveVO);
    			
    			saveVO.setMngYear(keyVO.getMngYear());    
    			saveVO.setMngNo(keyVO.getMngNo());    
    			saveVO.setMngCnt(keyVO.getMngCnt()); 
    			saveVO.setReqstSeCd("1");  //신청구분코드(1:최초, 2:연장, 3:변경, 4:취소)
    			saveVO.setRegUsr(loginVO.getId()); 
    			
    			gamCntnrQuayNticArrvlDtaInqireService.insertCntnrQuayNticArrvlDtaInqireFirst(saveVO);
    			
    			//임대상세저장을 위한 키
    			saveDetailVO.setDetailPrtAtCode(form.get("prtAtCode"));
        		saveDetailVO.setDetailMngYear(keyVO.getMngYear());    
        		saveDetailVO.setDetailMngNo(keyVO.getMngNo());      
        		saveDetailVO.setDetailMngCnt(keyVO.getMngCnt());     
    		} else {
    			//saveVO.setReqstSeCd("3"); //신청구분코드(1:최초, 2:연장, 3:변경, 4:취소)
    	    	
    	        gamCntnrQuayNticArrvlDtaInqireService.updateCntnrQuayNticArrvlDtaInqire(saveVO);
    			
    			//임대상세저장을 위한 키
    			saveDetailVO.setDetailPrtAtCode(form.get("prtAtCode"));
        		saveDetailVO.setDetailMngYear(form.get("mngYear"));    
        		saveDetailVO.setDetailMngNo(form.get("mngNo"));      
        		saveDetailVO.setDetailMngCnt(form.get("mngCnt"));     
    		}
    		
    		//컨테이너부두임대상세저장
    		for( int i = 0 ; i < insertList.size() ; i++ ) {
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ insertList.get(i) String => " + insertList.get(i));
    			
    			Map resultMap = insertList.get(i);
    			
    			GamCntnrQuayNticArrvlDtaInqireDetailVO insertDetailVO = new GamCntnrQuayNticArrvlDtaInqireDetailVO();
    			
    			insertDetailVO.setDetailPrtAtCode(saveDetailVO.getDetailPrtAtCode());
    			insertDetailVO.setDetailMngYear(saveDetailVO.getDetailMngYear());    
    			insertDetailVO.setDetailMngNo(saveDetailVO.getDetailMngNo());      
    			insertDetailVO.setDetailMngCnt(saveDetailVO.getDetailMngCnt());
    			
    			insertDetailVO.setGisAssetsCd(resultMap.get("gisAssetsCd").toString());
    			insertDetailVO.setGisAssetsSubCd(resultMap.get("gisAssetsSubCd").toString());
    			insertDetailVO.setGisAssetsPrtAtCode(resultMap.get("gisAssetsPrtAtCode").toString());
    			insertDetailVO.setUsageAr(resultMap.get("usageAr").toString());
    			insertDetailVO.setExemptPdFrom(resultMap.get("exemptPdFrom").toString());
    			insertDetailVO.setExemptPdTo(resultMap.get("exemptPdTo").toString());
    			insertDetailVO.setUsagePdFrom(resultMap.get("usagePdFrom").toString());
    			insertDetailVO.setUsagePdTo(resultMap.get("usagePdTo").toString());
    			insertDetailVO.setOlnlp(resultMap.get("olnlp").toString());
    			insertDetailVO.setApplcTariff(resultMap.get("applcTariff").toString());
    			insertDetailVO.setApplcMth(resultMap.get("applcMth").toString());
    			insertDetailVO.setExemptSe(resultMap.get("exemptSe").toString());
    			insertDetailVO.setExemptRsnCd(resultMap.get("exemptRsnCd").toString());
    			insertDetailVO.setExemptRsn(resultMap.get("exemptRsn").toString());
    			insertDetailVO.setRdcxptFee(resultMap.get("rdcxptFee").toString());
    			insertDetailVO.setFee(resultMap.get("fee").toString());
    			insertDetailVO.setComputDtls(resultMap.get("computDtls").toString());
    			insertDetailVO.setUsagePurps(resultMap.get("usagePurps").toString());
    			insertDetailVO.setUsageDtls(resultMap.get("usageDtls").toString());
    			insertDetailVO.setQuayCd(resultMap.get("quayCd").toString());
    			
    			insertDetailVO.setRegUsr(loginVO.getId());
    			insertDetailVO.setUpdUsr(loginVO.getId());
    			
    			/*saveDetailVO.setDetailPrtAtCode(form.get("prtAtCode"));
        		saveDetailVO.setDetailMngYear(keyVO.getMngYear());    
        		saveDetailVO.setDetailMngNo(keyVO.getMngNo());      
        		saveDetailVO.setDetailMngCnt(keyVO.getMngCnt()); */
    			
    			//resultMap.get("gisAssetsPrtAtCode")
    			gamCntnrQuayNticArrvlDtaInqireService.insertCntnrQuayNticArrvlDtaInqireDetail(insertDetailVO);
    		}
    		
    		for( int i = 0 ; i < updateList.size() ; i++ ) {
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ updateList.get(i) String => " + updateList.get(i));
    			
    			Map resultMap = updateList.get(i);
    			
    			GamCntnrQuayNticArrvlDtaInqireDetailVO updateDetailVO = new GamCntnrQuayNticArrvlDtaInqireDetailVO();
    			updateDetailVO.setAssetsUsageSeq(resultMap.get("assetsUsageSeq").toString());
    			updateDetailVO.setDetailPrtAtCode(resultMap.get("prtAtCode").toString());
    			updateDetailVO.setDetailMngYear(resultMap.get("mngYear").toString());    
    			updateDetailVO.setDetailMngNo(resultMap.get("mngNo").toString());      
    			updateDetailVO.setDetailMngCnt(resultMap.get("mngCnt").toString());
    			updateDetailVO.setGisAssetsCd(resultMap.get("gisAssetsCd").toString());
    			updateDetailVO.setGisAssetsSubCd(resultMap.get("gisAssetsSubCd").toString());
    			updateDetailVO.setGisAssetsPrtAtCode(resultMap.get("gisAssetsPrtAtCode").toString());
    			updateDetailVO.setUsageAr(resultMap.get("usageAr").toString());
    			updateDetailVO.setExemptPdFrom(resultMap.get("exemptPdFrom").toString());
    			updateDetailVO.setExemptPdTo(resultMap.get("exemptPdTo").toString());
    			updateDetailVO.setUsagePdFrom(resultMap.get("usagePdFrom").toString());
    			updateDetailVO.setUsagePdTo(resultMap.get("usagePdTo").toString());
    			updateDetailVO.setOlnlp(resultMap.get("olnlp").toString());
    			updateDetailVO.setApplcTariff(resultMap.get("applcTariff").toString());
    			updateDetailVO.setApplcMth(resultMap.get("applcMth").toString());
    			updateDetailVO.setExemptSe(resultMap.get("exemptSe").toString());
    			updateDetailVO.setExemptRsnCd(resultMap.get("exemptRsnCd").toString());
    			updateDetailVO.setExemptRsn(resultMap.get("exemptRsn").toString());
    			updateDetailVO.setRdcxptFee(resultMap.get("rdcxptFee").toString());
    			updateDetailVO.setFee(resultMap.get("fee").toString());
    			updateDetailVO.setComputDtls(resultMap.get("computDtls").toString());
    			updateDetailVO.setUsagePurps(resultMap.get("usagePurps").toString());
    			updateDetailVO.setUsageDtls(resultMap.get("usageDtls").toString());
    			updateDetailVO.setQuayCd(resultMap.get("quayCd").toString());
    			
    			updateDetailVO.setRegUsr(loginVO.getId());
    			updateDetailVO.setUpdUsr(loginVO.getId());
    			
    			gamCntnrQuayNticArrvlDtaInqireService.updateCntnrQuayNticArrvlDtaInqireDetail(updateDetailVO);
    		}
    		
    		for( int i = 0 ; i < deleteList.size() ; i++ ) {
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ deleteList.get(i) String => " + deleteList.get(i));
    			
    			Map resultMap = deleteList.get(i);
    			
    			GamCntnrQuayNticArrvlDtaInqireDetailVO deleteDetailVO = new GamCntnrQuayNticArrvlDtaInqireDetailVO();
    			deleteDetailVO.setAssetsUsageSeq(resultMap.get("assetsUsageSeq").toString());
    			deleteDetailVO.setPrtAtCode(resultMap.get("prtAtCode").toString());
    			deleteDetailVO.setMngYear(resultMap.get("mngYear").toString());    
    			deleteDetailVO.setMngNo(resultMap.get("mngNo").toString());      
    			deleteDetailVO.setMngCnt(resultMap.get("mngCnt").toString());
    			
    			gamCntnrQuayNticArrvlDtaInqireService.deleteCntnrQuayNticArrvlDtaInqireDetail2(deleteDetailVO);
    		}
    		
    		//파일저장
    		for( int i = 0 ; i < insertFileList.size() ; i++ ) {
    			Map resultMap = insertFileList.get(i);
    			
    			GamCntnrQuayNticArrvlDtaInqireVO insertFileVO = new GamCntnrQuayNticArrvlDtaInqireVO();
    			
    			insertFileVO.setPrtAtCode(saveDetailVO.getDetailPrtAtCode());
    			insertFileVO.setMngYear(saveDetailVO.getDetailMngYear());    
    			insertFileVO.setMngNo(saveDetailVO.getDetailMngNo());      
    			insertFileVO.setMngCnt(saveDetailVO.getDetailMngCnt());
    			
    			insertFileVO.setPhotoSj(resultMap.get("photoSj").toString());
    			insertFileVO.setFilenmLogic(resultMap.get("filenmLogic").toString());
    			insertFileVO.setFilenmPhysicl(resultMap.get("filenmPhysicl").toString());
    			insertFileVO.setShotDt(resultMap.get("shotDt").toString());
    			insertFileVO.setPhotoDesc(resultMap.get("photoDesc").toString());
    			insertFileVO.setRegUsr(loginVO.getId());
    			
    			System.out.println("############################################### insertFileVO => " + insertFileVO);
    			
    			gamCntnrQuayNticArrvlDtaInqireService.insertCntnrQuayNticArrvlDtaInqireFile(insertFileVO);
    		}
    		
    		for( int i = 0 ; i < updateFileList.size() ; i++ ) {
    			Map resultMap = updateFileList.get(i);
    			
    			GamCntnrQuayNticArrvlDtaInqireVO updateFileVO = new GamCntnrQuayNticArrvlDtaInqireVO();
    			
    			updateFileVO.setPhotoSeq(resultMap.get("photoSeq").toString());
    			updateFileVO.setPrtAtCode(resultMap.get("prtAtCode").toString());
    			updateFileVO.setMngYear(resultMap.get("mngYear").toString());    
    			updateFileVO.setMngNo(resultMap.get("mngNo").toString());      
    			updateFileVO.setMngCnt(resultMap.get("mngCnt").toString());
    			
    			updateFileVO.setPhotoSj(resultMap.get("photoSj").toString());
    			updateFileVO.setShotDt(resultMap.get("shotDt").toString());
    			updateFileVO.setPhotoDesc(resultMap.get("photoDesc").toString());
    			
    			System.out.println("############################################### updateFileVO => " + updateFileVO);
    			
    			gamCntnrQuayNticArrvlDtaInqireService.updateCntnrQuayNticArrvlDtaInqireFile(updateFileVO);
    		}
    		
    		for( int i = 0 ; i < deleteFileList.size() ; i++ ) {
    			Map resultMap = deleteFileList.get(i);
    			
    			GamCntnrQuayNticArrvlDtaInqireVO deleteFileVO = new GamCntnrQuayNticArrvlDtaInqireVO();
    			
    			deleteFileVO.setPhotoSeq(resultMap.get("photoSeq").toString());
    			deleteFileVO.setPrtAtCode(resultMap.get("prtAtCode").toString());
    			deleteFileVO.setMngYear(resultMap.get("mngYear").toString());    
    			deleteFileVO.setMngNo(resultMap.get("mngNo").toString());      
    			deleteFileVO.setMngCnt(resultMap.get("mngCnt").toString());
    			
    			System.out.println("############################################### deleteFileVO => " + deleteFileVO);
    			
    			gamCntnrQuayNticArrvlDtaInqireService.deleteCntnrQuayNticArrvlDtaInqirePhotoSingle(deleteFileVO);
    		}
    		
    		//총사용료, 총면적, 총사용기간 조회
    		GamCntnrQuayNticArrvlDtaInqireVO paramVO = new GamCntnrQuayNticArrvlDtaInqireVO(); 
    		paramVO.setPrtAtCode(saveDetailVO.getDetailPrtAtCode());
    		paramVO.setMngYear(saveDetailVO.getDetailMngYear());
    		paramVO.setMngNo(saveDetailVO.getDetailMngNo());
    		paramVO.setMngCnt(saveDetailVO.getDetailMngCnt());
    		
    		GamCntnrQuayNticArrvlDtaInqireVO updRentVO = new GamCntnrQuayNticArrvlDtaInqireVO();
    		updRentVO = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqireCurrRenewInfo(paramVO);
    		
    		if( updRentVO != null ) {
    			updRentVO.setPrtAtCode(paramVO.getPrtAtCode());
    			updRentVO.setMngYear(paramVO.getMngYear());
    			updRentVO.setMngNo(paramVO.getMngNo());
    			updRentVO.setMaxMngCnt(paramVO.getMngCnt());
    			
    			//총사용료, 총면적, 총사용기간 업데이트
    			gamCntnrQuayNticArrvlDtaInqireService.updateCntnrQuayNticArrvlDtaInqireRenewInfo(updRentVO);
    			
    			//부두코드 가져오기
    			GamCntnrQuayNticArrvlDtaInqireVO quaycdVO = new GamCntnrQuayNticArrvlDtaInqireVO();
    			quaycdVO = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqireDetailQuaycd(updRentVO);
    			
    			//부두코드 업데이트
    			if( quaycdVO == null || quaycdVO.getQuayCd() == null || "".equals(quaycdVO.getQuayCd()) ) {
    				quaycdVO = new GamCntnrQuayNticArrvlDtaInqireVO();
    				quaycdVO.setPrtAtCode(paramVO.getPrtAtCode());
    				quaycdVO.setMngYear(paramVO.getMngYear());
    				quaycdVO.setMngNo(paramVO.getMngNo());
    				quaycdVO.setMaxMngCnt(paramVO.getMngCnt());
    			}
    			
    			gamCntnrQuayNticArrvlDtaInqireService.updateCntnrQuayNticArrvlDtaInqireQuaycd(quaycdVO);
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	log.debug("insert list : "+insertList.size());
    	log.debug("updateList list : "+updateList.size());
    	log.debug("deleteList list : "+deleteList.size());

		map.put("resultCode", 0);			// return ok
		map.put("resultMsg", egovMessageSource.getMessage("success.common.merge"));
		return map;
    }
	
	
	/**
     * 컨테이너부두임대 최초신청을 등록한다.
     * @param String
     * @param gamCntnrQuayNticArrvlDtaInqireVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/cntnr/gamInsertCntnrQuayNticArrvlDtaInqireFirst.do") 
    public @ResponseBody Map insertCntnrQuayNticArrvlDtaInqireFirst(
    	   @RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamCntnrQuayNticArrvlDtaInqireVO") GamCntnrQuayNticArrvlDtaInqireVO gamCntnrQuayNticArrvlDtaInqireVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO loginVo = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();        
        /*
        String sLocationUrl = null;
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	*/

    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamCntnrQuayNticArrvlDtaInqireVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamCntnrQuayNticArrvlDtaInqireVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamCntnrQuayNticArrvlDtaInqireService.insertCntnrQuayNticArrvlDtaInqireFirst(gamCntnrQuayNticArrvlDtaInqireVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	if("insert".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamCntnrQuayNticArrvlDtaInqireVO.setReqstSeCd("1");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamCntnrQuayNticArrvlDtaInqireVO.setRegUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)
	    	gamCntnrQuayNticArrvlDtaInqireVO.setUpdUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)
	    	//gamCntnrQuayNticArrvlDtaInqireVO.setDeptcd("A001");   //부서코드 (세션?) 
	    	
	        gamCntnrQuayNticArrvlDtaInqireService.insertCntnrQuayNticArrvlDtaInqireFirst(gamCntnrQuayNticArrvlDtaInqireVO);
	    	
	        resultCode = 0; // return ok
			resultMsg  = egovMessageSource.getMessage("success.common.insert");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
    	}
		
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    
    /**
     * 컨테이너부두임대 연장신청을 등록한다.
     * @param gamCntnrQuayNticArrvlDtaInqireVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/cntnr/gamInsertCntnrQuayNticArrvlDtaInqireRenew.do") 
    public @ResponseBody Map insertCntnrQuayNticArrvlDtaInqireRenew(
    	   @ModelAttribute("gamCntnrQuayNticArrvlDtaInqireVO") GamCntnrQuayNticArrvlDtaInqireVO gamCntnrQuayNticArrvlDtaInqireVO,
   	       BindingResult bindingResult)
           throws Exception {
    	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO loginVo = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();        
    	
    	GamCntnrQuayNticArrvlDtaInqireVO resultVO = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqireMaxNo(gamCntnrQuayNticArrvlDtaInqireVO);
    	
    	if( gamCntnrQuayNticArrvlDtaInqireVO.getMngCnt().equals(resultVO.getMaxMngCnt()) ) {
    		//키 같고 max관리번호가 같으면 연장신청 등록
    		gamCntnrQuayNticArrvlDtaInqireVO.setRegUsr(loginVo.getId());
    		gamCntnrQuayNticArrvlDtaInqireService.insertCntnrQuayNticArrvlDtaInqireRenew(gamCntnrQuayNticArrvlDtaInqireVO);
    		
    		resultCode = 0; // return ok
    		resultMsg  = egovMessageSource.getMessage("success.common.insert");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.reject");
    	}
    	
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 컨테이너부두임대 정보를 수정한다.
     * @param String
     * @param gamCntnrQuayNticArrvlDtaInqireVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/cntnr/gamUpdateCntnrQuayNticArrvlDtaInqire.do") 
    public @ResponseBody Map updateCntnrQuayNticArrvlDtaInqireFirst(
    	   @RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamCntnrQuayNticArrvlDtaInqireVO") GamCntnrQuayNticArrvlDtaInqireVO gamCntnrQuayNticArrvlDtaInqireVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO loginVo = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	
    	if("modify".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamCntnrQuayNticArrvlDtaInqireVO.setReqstSeCd("3");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamCntnrQuayNticArrvlDtaInqireVO.setUpdUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)
	    	//gamCntnrQuayNticArrvlDtaInqireVO.setDeptcd("A001");   //부서코드 (세션?) 
	    	
	        gamCntnrQuayNticArrvlDtaInqireService.updateCntnrQuayNticArrvlDtaInqire(gamCntnrQuayNticArrvlDtaInqireVO);
	    	
	        resultCode = 0; // return ok
	        resultMsg  = egovMessageSource.getMessage("success.common.update");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
    	}
    	
    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);
        
		return map;
    }
	
    /**
     * 컨테이너부두임대 정보를 삭제한다.
     * @param String
     * @param gamCntnrQuayNticArrvlDtaInqireVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/cntnr/gamDeleteCntnrQuayNticArrvlDtaInqire.do") 
    public @ResponseBody Map deleteCntnrQuayNticArrvlDtaInqire(
    	   //@RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamCntnrQuayNticArrvlDtaInqireDetailVO") GamCntnrQuayNticArrvlDtaInqireVO gamCntnrQuayNticArrvlDtaInqireVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg  = "";
        String deleteFlag = "";
        int resultCode = 1;
        
        int resultLevReqestCnt = -1;
        
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	if( EgovStringUtil.isEmpty(gamCntnrQuayNticArrvlDtaInqireVO.getPrmisnYn()) || gamCntnrQuayNticArrvlDtaInqireVO.getPrmisnYn().equals("N") ) { //허가여부가 'N'이면 삭제가능
        	deleteFlag = "Y";
        } else {
        	/*
        	resultLevReqestCnt = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqireLevReqestCnt(gamCntnrQuayNticArrvlDtaInqireVO); //징수의뢰 정보 카운트
        	
        	if( gamCntnrQuayNticArrvlDtaInqireVO.getPrmisnYn().equals("Y") && resultLevReqestCnt == 0 ) { //허가여부가 Y이고 징수의뢰테이블에 정보가 없으면 삭제가능
            	deleteFlag = "Y";
            }
            */
        	deleteFlag = "N";
        }
    	
    	if("Y".equals(deleteFlag)) {
	        gamCntnrQuayNticArrvlDtaInqireService.deleteCntnrQuayNticArrvlDtaInqire(gamCntnrQuayNticArrvlDtaInqireVO);
	    	
	        resultCode = 0; // return ok
	        resultMsg  = egovMessageSource.getMessage("success.common.delete");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.err.delete");
    	}
		
    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 컨테이너부두임대 상세를 등록한다.
     * @param String
     * @param gamCntnrQuayNticArrvlDtaInqireDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/cntnr/gamInsertCntnrQuayNticArrvlDtaInqireDetail.do") 
    public @ResponseBody Map insertCntnrQuayNticArrvlDtaInqireDetail(
    	   @RequestParam("detailCmd") String detailCmd, 
    	   @ModelAttribute("gamCntnrQuayNticArrvlDtaInqireDetailVO") GamCntnrQuayNticArrvlDtaInqireDetailVO gamCntnrQuayNticArrvlDtaInqireDetailVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO loginVo = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();        
    	/*
        String sLocationUrl = null;
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	*/

    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamCntnrQuayNticArrvlDtaInqireVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamCntnrQuayNticArrvlDtaInqireVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamCntnrQuayNticArrvlDtaInqireService.insertCntnrQuayNticArrvlDtaInqireFirst(gamCntnrQuayNticArrvlDtaInqireVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
        GamCntnrQuayNticArrvlDtaInqireVO gamCntnrQuayNticArrvlDtaInqireVO = new GamCntnrQuayNticArrvlDtaInqireVO();
        gamCntnrQuayNticArrvlDtaInqireVO.setPrtAtCode(gamCntnrQuayNticArrvlDtaInqireDetailVO.getDetailPrtAtCode());
        gamCntnrQuayNticArrvlDtaInqireVO.setMngYear(gamCntnrQuayNticArrvlDtaInqireDetailVO.getDetailMngYear());
        gamCntnrQuayNticArrvlDtaInqireVO.setMngNo(gamCntnrQuayNticArrvlDtaInqireDetailVO.getDetailMngNo());
        gamCntnrQuayNticArrvlDtaInqireVO.setMngCnt(gamCntnrQuayNticArrvlDtaInqireDetailVO.getDetailMngCnt());
        
        //임대정보 조회후 승낙여부 체크
        GamCntnrQuayNticArrvlDtaInqireVO rentPrmisnInfo = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqirePrmisnInfo(gamCntnrQuayNticArrvlDtaInqireVO);
        
        
        
        log.debug("######################################## rentPrmisnInfo.getPrmisnYn() => " + rentPrmisnInfo.getPrmisnYn());
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 등록가능
        	if("insert".equals(detailCmd)) {
    	    	//확인후 변경혀라~~
    	    	gamCntnrQuayNticArrvlDtaInqireDetailVO.setRegUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)
    	    	gamCntnrQuayNticArrvlDtaInqireDetailVO.setUpdUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)
    	    	
    	        gamCntnrQuayNticArrvlDtaInqireService.insertCntnrQuayNticArrvlDtaInqireDetail(gamCntnrQuayNticArrvlDtaInqireDetailVO);
    	    	
    	        resultCode = 0; // return ok
    			resultMsg  = egovMessageSource.getMessage("success.common.insert");
        	} else {
        		resultCode = 1; // return fail
        		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
        	}
        } else {
        	resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.detailModify.reject");
        }
    	
		
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 컨테이너부두임대 상세를 수정한다.
     * @param String
     * @param gamCntnrQuayNticArrvlDtaInqireDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/cntnr/gamUpdateCntnrQuayNticArrvlDtaInqireDetail.do") 
    public @ResponseBody Map updateCntnrQuayNticArrvlDtaInqireDetail(
    	   @RequestParam("detailCmd") String detailCmd, 
    	   @ModelAttribute("gamCntnrQuayNticArrvlDtaInqireDetailVO") GamCntnrQuayNticArrvlDtaInqireDetailVO gamCntnrQuayNticArrvlDtaInqireDetailVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO loginVo = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();        
        /*
        String sLocationUrl = null;
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	*/

    	log.debug("######################################## detailCmd => " + detailCmd);
    	log.debug("######################################## gamCntnrQuayNticArrvlDtaInqireVO.getDetailPrtAtCode() => " + gamCntnrQuayNticArrvlDtaInqireDetailVO.getDetailPrtAtCode());
    	
    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamCntnrQuayNticArrvlDtaInqireVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamCntnrQuayNticArrvlDtaInqireVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamCntnrQuayNticArrvlDtaInqireService.insertCntnrQuayNticArrvlDtaInqireFirst(gamCntnrQuayNticArrvlDtaInqireVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	GamCntnrQuayNticArrvlDtaInqireVO gamCntnrQuayNticArrvlDtaInqireVO = new GamCntnrQuayNticArrvlDtaInqireVO();
        gamCntnrQuayNticArrvlDtaInqireVO.setPrtAtCode(gamCntnrQuayNticArrvlDtaInqireDetailVO.getDetailPrtAtCode());
        gamCntnrQuayNticArrvlDtaInqireVO.setMngYear(gamCntnrQuayNticArrvlDtaInqireDetailVO.getDetailMngYear());
        gamCntnrQuayNticArrvlDtaInqireVO.setMngNo(gamCntnrQuayNticArrvlDtaInqireDetailVO.getDetailMngNo());
        gamCntnrQuayNticArrvlDtaInqireVO.setMngCnt(gamCntnrQuayNticArrvlDtaInqireDetailVO.getDetailMngCnt());
        
        //임대정보 조회후 승낙여부 체크
        GamCntnrQuayNticArrvlDtaInqireVO rentPrmisnInfo = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqirePrmisnInfo(gamCntnrQuayNticArrvlDtaInqireVO);
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 수정가능
	    	if("modify".equals(detailCmd)) {
		    	gamCntnrQuayNticArrvlDtaInqireDetailVO.setUpdUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)
		    	
		        gamCntnrQuayNticArrvlDtaInqireService.updateCntnrQuayNticArrvlDtaInqireDetail(gamCntnrQuayNticArrvlDtaInqireDetailVO);
		    	
		        resultCode = 0; // return ok
				resultMsg  = egovMessageSource.getMessage("success.common.update");
	    	} else {
	    		resultCode = 1; // return fail
	    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
	    	}
        } else {
        	resultCode = 1; // return fail
    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.detailModify.reject");
        }
		
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 컨테이너부두임대 상세를 삭제한다.
     * @param gamCntnrQuayNticArrvlDtaInqireDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/cntnr/gamDeleteCntnrQuayNticArrvlDtaInqireDetail.do") 
    public @ResponseBody Map deleteCntnrQuayNticArrvlDtaInqireDetail(
    	   @ModelAttribute("gamCntnrQuayNticArrvlDtaInqireDetailVO") GamCntnrQuayNticArrvlDtaInqireDetailVO gamCntnrQuayNticArrvlDtaInqireDetailVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	/*
        String sLocationUrl = null;
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	*/

    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamCntnrQuayNticArrvlDtaInqireVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamCntnrQuayNticArrvlDtaInqireVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamCntnrQuayNticArrvlDtaInqireService.insertCntnrQuayNticArrvlDtaInqireFirst(gamCntnrQuayNticArrvlDtaInqireVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	gamCntnrQuayNticArrvlDtaInqireService.deleteCntnrQuayNticArrvlDtaInqireDetail2(gamCntnrQuayNticArrvlDtaInqireDetailVO);
    	
        resultCode = 0; // return ok
		resultMsg  = egovMessageSource.getMessage("success.common.delete");
		
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 승낙 팝업화면을 로딩한다. 
     *
     * @param gamCntnrQuayNticArrvlDtaInqireLevReqestVO
     * @param model the model
     * @return "/ygpa/gam/oper/cntnr/GamPopupCntnrQuayNticArrvlDtaInqirePrmisn"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/oper/cntnr/popup/showCntnrQuayNticArrvlDtaInqirePrmisn.do") 
    String showEntrpsInfo(GamCntnrQuayNticArrvlDtaInqireLevReqestVO gamCntnrQuayNticArrvlDtaInqireLevReqestVO, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM024"); //요금종류
		List chrgeKndCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		model.addAttribute("gamCntnrQuayNticArrvlDtaInqireInfo", gamCntnrQuayNticArrvlDtaInqireLevReqestVO);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);

    	return "/ygpa/gam/oper/cntnr/GamPopupCntnrQuayNticArrvlDtaInqirePrmisn";
    }
    
    /**
     * 컨테이너부두임대 승낙(허가)을 한다.
     * @param gamCntnrQuayNticArrvlDtaInqireVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/cntnr/gamInsertCntnrQuayNticArrvlDtaInqirePrmisn.do") 
    public @ResponseBody Map insertCntnrQuayNticArrvlDtaInqireLevReqest(
    	   @ModelAttribute("gamCntnrQuayNticArrvlDtaInqireVO") GamCntnrQuayNticArrvlDtaInqireVO gamCntnrQuayNticArrvlDtaInqireVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO loginVo = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();        
        
    	//승낙할 임대정보조회
        GamCntnrQuayNticArrvlDtaInqireVO rentPrmisnInfo = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqirePrmisnInfo(gamCntnrQuayNticArrvlDtaInqireVO);
        
        //징수의뢰 테이블에 갯수 카운트 조회
        int levReqestCnt = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqireLevReqestCnt(gamCntnrQuayNticArrvlDtaInqireVO);
        
        if( "Y".equals(rentPrmisnInfo.getPrmisnYn()) ) { 
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject2")); //이미 승낙된 상태입니다.
            
    		return map;
        }
        
        if( levReqestCnt > 0 ) { 
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject3")); //징수의뢰에 정보가 존재하여 승낙을 진행할 수 없습니다.
            
    		return map;
        }
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getNticMth()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject1")); //고지방법코드가 없습니다.
            
    		return map;
        }
        
        if( !"1".equals(rentPrmisnInfo.getNticMth()) && !"2".equals(rentPrmisnInfo.getNticMth()) && !"3".equals(rentPrmisnInfo.getNticMth()) && !"4".equals(rentPrmisnInfo.getNticMth()) && !"5".equals(rentPrmisnInfo.getNticMth())) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject5")); // 고지방법코드가 올바르지 않습니다. ('1':일괄, '2':반기납, '3':3분납, '4':분기납, '5':월납)
            
    		return map;
        }
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getGrUsagePdFrom()) || EgovStringUtil.isEmpty(rentPrmisnInfo.getGrUsagePdTo()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject4")); //총사용기간 일자가 없습니다.
            
    		return map;
        }
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getGrFee()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject6")); //총사용료가 없습니다.
            
    		return map;
        }
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPayMth()) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject10")); //납부방법 코드가 없습니다.
            
    		return map;
        }
        
        if( !"Pre".equals( rentPrmisnInfo.getPayMth() ) && !"Aft".equals( rentPrmisnInfo.getPayMth() ) ) {
        	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject9")); //납부방법 코드가 올바르지 않습니다.
            
    		return map;
        }
        
        GamCntnrQuayNticArrvlDtaInqireLevReqestVO levReqestInfo = new GamCntnrQuayNticArrvlDtaInqireLevReqestVO();
        levReqestInfo.setPrtAtCode( rentPrmisnInfo.getPrtAtCode() );
        levReqestInfo.setMngYear( rentPrmisnInfo.getMngYear() );
        levReqestInfo.setMngNo( rentPrmisnInfo.getMngNo() );
        levReqestInfo.setMngCnt( rentPrmisnInfo.getMngCnt() );
        levReqestInfo.setEntrpscd( rentPrmisnInfo.getEntrpscd() );
        levReqestInfo.setEntrpsNm( rentPrmisnInfo.getEntrpsNm() );
        levReqestInfo.setRm( rentPrmisnInfo.getRm() );
        levReqestInfo.setNticMth( rentPrmisnInfo.getNticMth() );
        levReqestInfo.setGrFee( rentPrmisnInfo.getGrFee() );
        levReqestInfo.setGrUsagePdFrom( rentPrmisnInfo.getGrUsagePdFrom() ); //총사용기간 FROM
        levReqestInfo.setGrUsagePdTo( rentPrmisnInfo.getGrUsagePdTo() ); //총사용기간 TO
        levReqestInfo.setReqstSeCd( rentPrmisnInfo.getReqstSeCd() );
		levReqestInfo.setChrgeKnd( gamCntnrQuayNticArrvlDtaInqireVO.getChrgeKnd() );
		levReqestInfo.setVatYn( gamCntnrQuayNticArrvlDtaInqireVO.getVatYn() );
		levReqestInfo.setPayMth( rentPrmisnInfo.getPayMth() );
		
        levReqestInfo.setPrmisnYn("Y"); //허가여부
        levReqestInfo.setRegUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)
        levReqestInfo.setUpdUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)
        
        //임대정보의 허가여부를 Y로 업데이트 및 징수의뢰 insert
        gamCntnrQuayNticArrvlDtaInqireService.updateCntnrQuayNticArrvlDtaInqirePrmisn(levReqestInfo);
        
        resultCode = 0; 
		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.exec"); //승낙이 정상적으로 처리되었습니다.
        
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 컨테이너부두임대 승낙취소(허가취소)를 한다.
     * @param gamCntnrQuayNticArrvlDtaInqireVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/cntnr/gamUpdateCntnrQuayNticArrvlDtaInqirePrmisnCancel.do") 
    public @ResponseBody Map updateCntnrQuayNticArrvlDtaInqirePrmisnCancel(
     	   @ModelAttribute("gamCntnrQuayNticArrvlDtaInqireVO") GamCntnrQuayNticArrvlDtaInqireVO gamCntnrQuayNticArrvlDtaInqireVO, 
     	   BindingResult bindingResult)
            throws Exception {
 	
     	Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
         
     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
 	        map.put("resultCode", 1);
     		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
         	return map;
     	}

     	LoginVO loginVo = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();         
        
     	//승낙할 임대정보조회
         GamCntnrQuayNticArrvlDtaInqireVO rentPrmisnInfo = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqirePrmisnInfo(gamCntnrQuayNticArrvlDtaInqireVO);
         
         //징수의뢰 테이블에 갯수 카운트 조회
         int levReqestCnt = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqireLevReqestCnt(gamCntnrQuayNticArrvlDtaInqireVO);
         
         if( !"Y".equals(rentPrmisnInfo.getPrmisnYn()) ) { 
         	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject7")); //승낙된 상태가 아닙니다.
             
     		return map;
         }
         
         if( levReqestCnt > 0 ) { 
         	map.put("resultCode", 1);
            map.put("resultMsg", egovMessageSource.getMessage("gam.asset.rent.prmisn.reject8")); //징수의뢰에 정보가 존재하여 승낙을 취소 할 수 없습니다.
             
     		return map;
         }
         
         GamCntnrQuayNticArrvlDtaInqireLevReqestVO levReqestInfo = new GamCntnrQuayNticArrvlDtaInqireLevReqestVO();
         levReqestInfo.setPrtAtCode( rentPrmisnInfo.getPrtAtCode() );
         levReqestInfo.setMngYear( rentPrmisnInfo.getMngYear() );
         levReqestInfo.setMngNo( rentPrmisnInfo.getMngNo() );
         levReqestInfo.setMngCnt( rentPrmisnInfo.getMngCnt() );
 		
         levReqestInfo.setPrmisnYn("N"); //허가여부
         levReqestInfo.setRegUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)
         levReqestInfo.setUpdUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)
         
         //임대정보의 허가여부를 N으로 업데이트
         gamCntnrQuayNticArrvlDtaInqireService.updateCntnrQuayNticArrvlDtaInqirePrmisnCancel(levReqestInfo);
         
         resultCode = 0; 
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.execCancel"); //승낙이 정상적으로 취소되었습니다.
         
     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);
         
 		return map;
     }
    
    /**
     * 파일목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/cntnr/gamSelectCntnrQuayNticArrvlDtaInqireFileList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectCntnrQuayNticArrvlDtaInqireFileList(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
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
		
		//컨테이너부두임대목록
    	totalCnt = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqireFileListTotCnt(searchVO);
    	List assetFileList = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqireFileList(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("assetFileList", assetFileList);
    	map.put("searchOption", searchVO);
    	
    	return map;
    }
	
	/**
     * 코멘트를 저장한다.
     * @param String
     * @param gamCntnrQuayNticArrvlDtaInqireVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/cntnr/gamUpdateCntnrQuayNticArrvlDtaInqireComment.do") 
    public @ResponseBody Map updateCntnrQuayNticArrvlDtaInqireComment(
    	   @ModelAttribute("gamCntnrQuayNticArrvlDtaInqireVO") GamCntnrQuayNticArrvlDtaInqireVO gamCntnrQuayNticArrvlDtaInqireVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg  = "";
        String updateFlag = "";
        int resultCode = 1;
        
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

    	LoginVO loginVo = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();        
    	/*
        int resultLevReqestCnt = -1;
        
        if( EgovStringUtil.isEmpty(gamCntnrQuayNticArrvlDtaInqireVO.getPrmisnYn()) || gamCntnrQuayNticArrvlDtaInqireVO.getPrmisnYn().equals("N") ) { //허가여부가 'N'이면 삭제가능
        	deleteFlag = "Y";
        } else {
        	resultLevReqestCnt = gamCntnrQuayNticArrvlDtaInqireService.selectCntnrQuayNticArrvlDtaInqireLevReqestCnt(gamCntnrQuayNticArrvlDtaInqireVO); //징수의뢰 정보 카운트
        	
        	if( gamCntnrQuayNticArrvlDtaInqireVO.getPrmisnYn().equals("Y") && resultLevReqestCnt == 0 ) { //허가여부가 Y이고 징수의뢰테이블에 정보가 없으면 삭제가능
            	deleteFlag = "Y";
            }
        }
    	*/
        if( gamCntnrQuayNticArrvlDtaInqireVO.getMngYear() == null || "".equals(gamCntnrQuayNticArrvlDtaInqireVO.getMngYear()) ) {
        	updateFlag = "N";
        } else {
        	updateFlag = "Y";
        }
        
    	if("Y".equals(updateFlag)) {
    		gamCntnrQuayNticArrvlDtaInqireVO.setUpdUsr(loginVo.getId());
	        gamCntnrQuayNticArrvlDtaInqireService.updateCntnrQuayNticArrvlDtaInqireComment(gamCntnrQuayNticArrvlDtaInqireVO);
	    	
	        resultCode = 0; // return ok
	        resultMsg  = egovMessageSource.getMessage("success.common.insert");
    	} else {
    		resultCode = 1; // return fail
    		resultMsg  = "신청 저장후 코멘트 저장이 가능합니다.";
    	}
		
    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);
        
		return map;
    }
    
}
