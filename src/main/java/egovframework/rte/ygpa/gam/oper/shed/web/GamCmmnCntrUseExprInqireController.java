package egovframework.rte.ygpa.gam.oper.shed.web;

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
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrUseExprInqireService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrUseExprInqireVO;

/**
 * @Class Name : GamCmmnCntrUseExprInqireController.java
 * @Description : 공컨장치장임대만기도래자료조회
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamCmmnCntrUseExprInqireController {
	
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
    
    @Resource(name = "gamCmmnCntrUseExprInqireService")
    private GamCmmnCntrUseExprInqireService gamCmmnCntrUseExprInqireService;
	
    
    /**
     * 공컨장치장임대관리 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/shed/GamCmmnCntrUseExprInqire"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/oper/shed/gamCmmnCntrUseExprInqire.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		//공시지가정보
		GamCmmnCntrUseExprInqireVO gvo = new GamCmmnCntrUseExprInqireVO();
		List olnlpList = gamCmmnCntrUseExprInqireService.selectOlnlpInfo();
		
		model.addAttribute("olnlpList", olnlpList);
		model.addAttribute("loginOrgnztId", loginVO.getOrgnztId());
		model.addAttribute("loginUserId", loginVO.getId());
		model.addAttribute("grUsagePdFromStr", EgovDateUtil.formatDate(EgovDateUtil.getToday(), "-"));
		model.addAttribute("grUsagePdToStr",   EgovDateUtil.formatDate(EgovDateUtil.addYearMonthDay(EgovDateUtil.getToday(),0,1,0), "-"));  
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/oper/shed/GamCmmnCntrUseExprInqire"; 
    }
	
	/**
     * 공컨장치장임대목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/shed/gamSelectCmmnCntrUseExprInqireList.do", method=RequestMethod.POST)
	@ResponseBody Map selectCmmnCntrUseExprInqireList(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {
		
		int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	//searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	//searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		//공컨장치장임대목록
    	totalCnt = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireListTotCnt(searchVO);
    	List assetRentList = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireList(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	//총면적, 총사용료
    	GamCmmnCntrUseExprInqireVO resultSum = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireSum(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	map.put("sumGrAr", resultSum.getSumGrAr());
    	map.put("sumGrFee", resultSum.getSumGrFee());
    	
    	return map;
    }
	
	/**
     * 공컨장치장임대상세리스트를 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/shed/gamSelectCmmnCntrUseExprInqireDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectCmmnCntrUseExprInqireDetailList(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();
    	
    	//searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	//searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 공컨장치장임대상세리스트 및 총건수
		totalCnt = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireDetailListTotCnt(searchVO);
		List resultList = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireDetailList(searchVO);
		
		paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	/**
     * 공컨장치장임대,상세,첨부파일을 저장한다.
     * @param dataList
     * @return map
     * @throws Exception
     */
	@RequestMapping(value="/oper/shed/gamSaveCmmnCntrUseExprInqire.do")
	@ResponseBody Map<String, Object> saveCmmnCntrUseExprInqire(@RequestParam Map<String, Object> dataList) throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		GamCmmnCntrUseExprInqireDetailVO saveDetailVO = new GamCmmnCntrUseExprInqireDetailVO(); 
		
		Map<String,Object> map = new HashMap<String,Object>();
		ObjectMapper mapper = new ObjectMapper();

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
    		
    		
    		//공컨장치장임대저장
    		GamCmmnCntrUseExprInqireVO saveVO= new GamCmmnCntrUseExprInqireVO();
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
    			GamCmmnCntrUseExprInqireVO keyVO = new GamCmmnCntrUseExprInqireVO();
    			keyVO = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireMaxKey(saveVO);
    			
    			saveVO.setMngYear(keyVO.getMngYear());    
    			saveVO.setMngNo(keyVO.getMngNo());    
    			saveVO.setMngCnt(keyVO.getMngCnt()); 
    			saveVO.setReqstSeCd("1");  //신청구분코드(1:최초, 2:연장, 3:변경, 4:취소)
    			saveVO.setRegUsr(loginVO.getId()); 
    			
    			gamCmmnCntrUseExprInqireService.insertCmmnCntrUseExprInqireFirst(saveVO);
    			
    			//임대상세저장을 위한 키
    			saveDetailVO.setDetailPrtAtCode(form.get("prtAtCode"));
        		saveDetailVO.setDetailMngYear(keyVO.getMngYear());    
        		saveDetailVO.setDetailMngNo(keyVO.getMngNo());      
        		saveDetailVO.setDetailMngCnt(keyVO.getMngCnt());     
    		} else {
    			//saveVO.setReqstSeCd("3"); //신청구분코드(1:최초, 2:연장, 3:변경, 4:취소)
    	    	
    	        gamCmmnCntrUseExprInqireService.updateCmmnCntrUseExprInqire(saveVO);
    			
    			//임대상세저장을 위한 키
    			saveDetailVO.setDetailPrtAtCode(form.get("prtAtCode"));
        		saveDetailVO.setDetailMngYear(form.get("mngYear"));    
        		saveDetailVO.setDetailMngNo(form.get("mngNo"));      
        		saveDetailVO.setDetailMngCnt(form.get("mngCnt"));     
    		}
    		
    		//공컨장치장임대상세저장
    		for( int i = 0 ; i < insertList.size() ; i++ ) {
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ insertList.get(i) String => " + insertList.get(i));
    			
    			Map resultMap = insertList.get(i);
    			
    			GamCmmnCntrUseExprInqireDetailVO insertDetailVO = new GamCmmnCntrUseExprInqireDetailVO();
    			
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
    			gamCmmnCntrUseExprInqireService.insertCmmnCntrUseExprInqireDetail(insertDetailVO);
    		}
    		
    		for( int i = 0 ; i < updateList.size() ; i++ ) {
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ updateList.get(i) String => " + updateList.get(i));
    			
    			Map resultMap = updateList.get(i);
    			
    			GamCmmnCntrUseExprInqireDetailVO updateDetailVO = new GamCmmnCntrUseExprInqireDetailVO();
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
    			
    			gamCmmnCntrUseExprInqireService.updateCmmnCntrUseExprInqireDetail(updateDetailVO);
    		}
    		
    		for( int i = 0 ; i < deleteList.size() ; i++ ) {
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ deleteList.get(i) String => " + deleteList.get(i));
    			
    			Map resultMap = deleteList.get(i);
    			
    			GamCmmnCntrUseExprInqireDetailVO deleteDetailVO = new GamCmmnCntrUseExprInqireDetailVO();
    			deleteDetailVO.setAssetsUsageSeq(resultMap.get("assetsUsageSeq").toString());
    			deleteDetailVO.setPrtAtCode(resultMap.get("prtAtCode").toString());
    			deleteDetailVO.setMngYear(resultMap.get("mngYear").toString());    
    			deleteDetailVO.setMngNo(resultMap.get("mngNo").toString());      
    			deleteDetailVO.setMngCnt(resultMap.get("mngCnt").toString());
    			
    			gamCmmnCntrUseExprInqireService.deleteCmmnCntrUseExprInqireDetail2(deleteDetailVO);
    		}
    		
    		//파일저장
    		for( int i = 0 ; i < insertFileList.size() ; i++ ) {
    			Map resultMap = insertFileList.get(i);
    			
    			GamCmmnCntrUseExprInqireVO insertFileVO = new GamCmmnCntrUseExprInqireVO();
    			
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
    			
    			gamCmmnCntrUseExprInqireService.insertCmmnCntrUseExprInqireFile(insertFileVO);
    		}
    		
    		for( int i = 0 ; i < updateFileList.size() ; i++ ) {
    			Map resultMap = updateFileList.get(i);
    			
    			GamCmmnCntrUseExprInqireVO updateFileVO = new GamCmmnCntrUseExprInqireVO();
    			
    			updateFileVO.setPhotoSeq(resultMap.get("photoSeq").toString());
    			updateFileVO.setPrtAtCode(resultMap.get("prtAtCode").toString());
    			updateFileVO.setMngYear(resultMap.get("mngYear").toString());    
    			updateFileVO.setMngNo(resultMap.get("mngNo").toString());      
    			updateFileVO.setMngCnt(resultMap.get("mngCnt").toString());
    			
    			updateFileVO.setPhotoSj(resultMap.get("photoSj").toString());
    			updateFileVO.setShotDt(resultMap.get("shotDt").toString());
    			updateFileVO.setPhotoDesc(resultMap.get("photoDesc").toString());
    			
    			System.out.println("############################################### updateFileVO => " + updateFileVO);
    			
    			gamCmmnCntrUseExprInqireService.updateCmmnCntrUseExprInqireFile(updateFileVO);
    		}
    		
    		for( int i = 0 ; i < deleteFileList.size() ; i++ ) {
    			Map resultMap = deleteFileList.get(i);
    			
    			GamCmmnCntrUseExprInqireVO deleteFileVO = new GamCmmnCntrUseExprInqireVO();
    			
    			deleteFileVO.setPhotoSeq(resultMap.get("photoSeq").toString());
    			deleteFileVO.setPrtAtCode(resultMap.get("prtAtCode").toString());
    			deleteFileVO.setMngYear(resultMap.get("mngYear").toString());    
    			deleteFileVO.setMngNo(resultMap.get("mngNo").toString());      
    			deleteFileVO.setMngCnt(resultMap.get("mngCnt").toString());
    			
    			System.out.println("############################################### deleteFileVO => " + deleteFileVO);
    			
    			gamCmmnCntrUseExprInqireService.deleteCmmnCntrUseExprInqirePhotoSingle(deleteFileVO);
    		}
    		
    		//총사용료, 총면적, 총사용기간 조회
    		GamCmmnCntrUseExprInqireVO paramVO = new GamCmmnCntrUseExprInqireVO(); 
    		paramVO.setPrtAtCode(saveDetailVO.getDetailPrtAtCode());
    		paramVO.setMngYear(saveDetailVO.getDetailMngYear());
    		paramVO.setMngNo(saveDetailVO.getDetailMngNo());
    		paramVO.setMngCnt(saveDetailVO.getDetailMngCnt());
    		
    		GamCmmnCntrUseExprInqireVO updRentVO = new GamCmmnCntrUseExprInqireVO();
    		updRentVO = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireCurrRenewInfo(paramVO);
    		
    		if( updRentVO != null ) {
    			updRentVO.setPrtAtCode(paramVO.getPrtAtCode());
    			updRentVO.setMngYear(paramVO.getMngYear());
    			updRentVO.setMngNo(paramVO.getMngNo());
    			updRentVO.setMaxMngCnt(paramVO.getMngCnt());
    			
    			//총사용료, 총면적, 총사용기간 업데이트
    			gamCmmnCntrUseExprInqireService.updateCmmnCntrUseExprInqireRenewInfo(updRentVO);
    			
    			//부두코드 가져오기
    			GamCmmnCntrUseExprInqireVO quaycdVO = new GamCmmnCntrUseExprInqireVO();
    			quaycdVO = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireDetailQuaycd(updRentVO);
    			
    			//부두코드 업데이트
    			if( quaycdVO == null || quaycdVO.getQuayCd() == null || "".equals(quaycdVO.getQuayCd()) ) {
    				quaycdVO = new GamCmmnCntrUseExprInqireVO();
    				quaycdVO.setPrtAtCode(paramVO.getPrtAtCode());
    				quaycdVO.setMngYear(paramVO.getMngYear());
    				quaycdVO.setMngNo(paramVO.getMngNo());
    				quaycdVO.setMaxMngCnt(paramVO.getMngCnt());
    			}
    			
    			gamCmmnCntrUseExprInqireService.updateCmmnCntrUseExprInqireQuaycd(quaycdVO);
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
     * 공컨장치장임대 최초신청을 등록한다.
     * @param String
     * @param gamCmmnCntrUseExprInqireVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamInsertCmmnCntrUseExprInqireFirst.do") 
    public @ResponseBody Map insertCmmnCntrUseExprInqireFirst(
    	   @RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamCmmnCntrUseExprInqireVO") GamCmmnCntrUseExprInqireVO gamCmmnCntrUseExprInqireVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
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
	        beanValidator.validate(gamCmmnCntrUseExprInqireVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamCmmnCntrUseExprInqireVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamCmmnCntrUseExprInqireService.insertCmmnCntrUseExprInqireFirst(gamCmmnCntrUseExprInqireVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	if("insert".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamCmmnCntrUseExprInqireVO.setReqstSeCd("1");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamCmmnCntrUseExprInqireVO.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	gamCmmnCntrUseExprInqireVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	//gamCmmnCntrUseExprInqireVO.setDeptcd("A001");   //부서코드 (세션?) 
	    	
	        gamCmmnCntrUseExprInqireService.insertCmmnCntrUseExprInqireFirst(gamCmmnCntrUseExprInqireVO);
	    	
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
     * 공컨장치장임대 연장신청을 등록한다.
     * @param gamCmmnCntrUseExprInqireVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamInsertCmmnCntrUseExprInqireRenew.do") 
    public @ResponseBody Map insertCmmnCntrUseExprInqireRenew(
    	   @ModelAttribute("gamCmmnCntrUseExprInqireVO") GamCmmnCntrUseExprInqireVO gamCmmnCntrUseExprInqireVO,
   	       BindingResult bindingResult)
           throws Exception {
    	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	GamCmmnCntrUseExprInqireVO resultVO = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireMaxNo(gamCmmnCntrUseExprInqireVO);
    	
    	if( gamCmmnCntrUseExprInqireVO.getMngCnt().equals(resultVO.getMaxMngCnt()) ) {
    		//키 같고 max관리번호가 같으면 연장신청 등록
        	
    		gamCmmnCntrUseExprInqireService.insertCmmnCntrUseExprInqireRenew(gamCmmnCntrUseExprInqireVO);
    		
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
     * 공컨장치장임대 정보를 수정한다.
     * @param String
     * @param gamCmmnCntrUseExprInqireVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamUpdateCmmnCntrUseExprInqire.do") 
    public @ResponseBody Map updateCmmnCntrUseExprInqireFirst(
    	   @RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamCmmnCntrUseExprInqireVO") GamCmmnCntrUseExprInqireVO gamCmmnCntrUseExprInqireVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	if("modify".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamCmmnCntrUseExprInqireVO.setReqstSeCd("3");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamCmmnCntrUseExprInqireVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	//gamCmmnCntrUseExprInqireVO.setDeptcd("A001");   //부서코드 (세션?) 
	    	
	        gamCmmnCntrUseExprInqireService.updateCmmnCntrUseExprInqire(gamCmmnCntrUseExprInqireVO);
	    	
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
     * 공컨장치장임대 정보를 삭제한다.
     * @param String
     * @param gamCmmnCntrUseExprInqireVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamDeleteCmmnCntrUseExprInqire.do") 
    public @ResponseBody Map deleteCmmnCntrUseExprInqire(
    	   //@RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamCmmnCntrUseExprInqireDetailVO") GamCmmnCntrUseExprInqireVO gamCmmnCntrUseExprInqireVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg  = "";
        String deleteFlag = "";
        int resultCode = 1;
        
        int resultLevReqestCnt = -1;
        
        if( EgovStringUtil.isEmpty(gamCmmnCntrUseExprInqireVO.getPrmisnYn()) || gamCmmnCntrUseExprInqireVO.getPrmisnYn().equals("N") ) { //허가여부가 'N'이면 삭제가능
        	deleteFlag = "Y";
        } else {
        	/*
        	resultLevReqestCnt = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireLevReqestCnt(gamCmmnCntrUseExprInqireVO); //징수의뢰 정보 카운트
        	
        	if( gamCmmnCntrUseExprInqireVO.getPrmisnYn().equals("Y") && resultLevReqestCnt == 0 ) { //허가여부가 Y이고 징수의뢰테이블에 정보가 없으면 삭제가능
            	deleteFlag = "Y";
            }
            */
        	deleteFlag = "N";
        }
    	
    	if("Y".equals(deleteFlag)) {
	        gamCmmnCntrUseExprInqireService.deleteCmmnCntrUseExprInqire(gamCmmnCntrUseExprInqireVO);
	    	
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
     * 공컨장치장임대 상세를 등록한다.
     * @param String
     * @param gamCmmnCntrUseExprInqireDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamInsertCmmnCntrUseExprInqireDetail.do") 
    public @ResponseBody Map insertCmmnCntrUseExprInqireDetail(
    	   @RequestParam("detailCmd") String detailCmd, 
    	   @ModelAttribute("gamCmmnCntrUseExprInqireDetailVO") GamCmmnCntrUseExprInqireDetailVO gamCmmnCntrUseExprInqireDetailVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
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
	        beanValidator.validate(gamCmmnCntrUseExprInqireVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamCmmnCntrUseExprInqireVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamCmmnCntrUseExprInqireService.insertCmmnCntrUseExprInqireFirst(gamCmmnCntrUseExprInqireVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
        GamCmmnCntrUseExprInqireVO gamCmmnCntrUseExprInqireVO = new GamCmmnCntrUseExprInqireVO();
        gamCmmnCntrUseExprInqireVO.setPrtAtCode(gamCmmnCntrUseExprInqireDetailVO.getDetailPrtAtCode());
        gamCmmnCntrUseExprInqireVO.setMngYear(gamCmmnCntrUseExprInqireDetailVO.getDetailMngYear());
        gamCmmnCntrUseExprInqireVO.setMngNo(gamCmmnCntrUseExprInqireDetailVO.getDetailMngNo());
        gamCmmnCntrUseExprInqireVO.setMngCnt(gamCmmnCntrUseExprInqireDetailVO.getDetailMngCnt());
        
        //임대정보 조회후 승낙여부 체크
        GamCmmnCntrUseExprInqireVO rentPrmisnInfo = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqirePrmisnInfo(gamCmmnCntrUseExprInqireVO);
        
        
        
        log.debug("######################################## rentPrmisnInfo.getPrmisnYn() => " + rentPrmisnInfo.getPrmisnYn());
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 등록가능
        	if("insert".equals(detailCmd)) {
    	    	//확인후 변경혀라~~
    	    	gamCmmnCntrUseExprInqireDetailVO.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
    	    	gamCmmnCntrUseExprInqireDetailVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
    	    	
    	        gamCmmnCntrUseExprInqireService.insertCmmnCntrUseExprInqireDetail(gamCmmnCntrUseExprInqireDetailVO);
    	    	
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
     * 공컨장치장임대 상세를 수정한다.
     * @param String
     * @param gamCmmnCntrUseExprInqireDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamUpdateCmmnCntrUseExprInqireDetail.do") 
    public @ResponseBody Map updateCmmnCntrUseExprInqireDetail(
    	   @RequestParam("detailCmd") String detailCmd, 
    	   @ModelAttribute("gamCmmnCntrUseExprInqireDetailVO") GamCmmnCntrUseExprInqireDetailVO gamCmmnCntrUseExprInqireDetailVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
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
    	log.debug("######################################## gamCmmnCntrUseExprInqireVO.getDetailPrtAtCode() => " + gamCmmnCntrUseExprInqireDetailVO.getDetailPrtAtCode());
    	
    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamCmmnCntrUseExprInqireVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamCmmnCntrUseExprInqireVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamCmmnCntrUseExprInqireService.insertCmmnCntrUseExprInqireFirst(gamCmmnCntrUseExprInqireVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	GamCmmnCntrUseExprInqireVO gamCmmnCntrUseExprInqireVO = new GamCmmnCntrUseExprInqireVO();
        gamCmmnCntrUseExprInqireVO.setPrtAtCode(gamCmmnCntrUseExprInqireDetailVO.getDetailPrtAtCode());
        gamCmmnCntrUseExprInqireVO.setMngYear(gamCmmnCntrUseExprInqireDetailVO.getDetailMngYear());
        gamCmmnCntrUseExprInqireVO.setMngNo(gamCmmnCntrUseExprInqireDetailVO.getDetailMngNo());
        gamCmmnCntrUseExprInqireVO.setMngCnt(gamCmmnCntrUseExprInqireDetailVO.getDetailMngCnt());
        
        //임대정보 조회후 승낙여부 체크
        GamCmmnCntrUseExprInqireVO rentPrmisnInfo = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqirePrmisnInfo(gamCmmnCntrUseExprInqireVO);
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 수정가능
	    	if("modify".equals(detailCmd)) {
		    	gamCmmnCntrUseExprInqireDetailVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
		    	
		        gamCmmnCntrUseExprInqireService.updateCmmnCntrUseExprInqireDetail(gamCmmnCntrUseExprInqireDetailVO);
		    	
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
     * 공컨장치장임대 상세를 삭제한다.
     * @param gamCmmnCntrUseExprInqireDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamDeleteCmmnCntrUseExprInqireDetail.do") 
    public @ResponseBody Map deleteCmmnCntrUseExprInqireDetail(
    	   @ModelAttribute("gamCmmnCntrUseExprInqireDetailVO") GamCmmnCntrUseExprInqireDetailVO gamCmmnCntrUseExprInqireDetailVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
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
	        beanValidator.validate(gamCmmnCntrUseExprInqireVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamCmmnCntrUseExprInqireVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamCmmnCntrUseExprInqireService.insertCmmnCntrUseExprInqireFirst(gamCmmnCntrUseExprInqireVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	gamCmmnCntrUseExprInqireService.deleteCmmnCntrUseExprInqireDetail2(gamCmmnCntrUseExprInqireDetailVO);
    	
        resultCode = 0; // return ok
		resultMsg  = egovMessageSource.getMessage("success.common.delete");
		
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 승낙 팝업화면을 로딩한다. 
     *
     * @param gamCmmnCntrUseExprInqireLevReqestVO
     * @param model the model
     * @return "/ygpa/gam/oper/shed/GamPopupCmmnCntrUseExprInqirePrmisn"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/oper/shed/popup/showCmmnCntrUseExprInqirePrmisn.do") 
    String showEntrpsInfo(GamCmmnCntrUseExprInqireLevReqestVO gamCmmnCntrUseExprInqireLevReqestVO, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM024"); //요금종류
		List chrgeKndCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		model.addAttribute("gamCmmnCntrUseExprInqireInfo", gamCmmnCntrUseExprInqireLevReqestVO);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);

    	return "/ygpa/gam/oper/shed/GamPopupCmmnCntrUseExprInqirePrmisn";
    }
    
    /**
     * 공컨장치장임대 승낙(허가)을 한다.
     * @param gamCmmnCntrUseExprInqireVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamInsertCmmnCntrUseExprInqirePrmisn.do") 
    public @ResponseBody Map insertCmmnCntrUseExprInqireLevReqest(
    	   @ModelAttribute("gamCmmnCntrUseExprInqireVO") GamCmmnCntrUseExprInqireVO gamCmmnCntrUseExprInqireVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
        
        //승낙할 임대정보조회
        GamCmmnCntrUseExprInqireVO rentPrmisnInfo = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqirePrmisnInfo(gamCmmnCntrUseExprInqireVO);
        
        //징수의뢰 테이블에 갯수 카운트 조회
        int levReqestCnt = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireLevReqestCnt(gamCmmnCntrUseExprInqireVO);
        
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
        
        GamCmmnCntrUseExprInqireLevReqestVO levReqestInfo = new GamCmmnCntrUseExprInqireLevReqestVO();
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
		levReqestInfo.setChrgeKnd( gamCmmnCntrUseExprInqireVO.getChrgeKnd() );
		levReqestInfo.setVatYn( gamCmmnCntrUseExprInqireVO.getVatYn() );
		levReqestInfo.setPayMth( rentPrmisnInfo.getPayMth() );
		
        levReqestInfo.setPrmisnYn("Y"); //허가여부
        levReqestInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
        levReqestInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
        
        //임대정보의 허가여부를 Y로 업데이트 및 징수의뢰 insert
        gamCmmnCntrUseExprInqireService.updateCmmnCntrUseExprInqirePrmisn(levReqestInfo);
        
        resultCode = 0; 
		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.exec"); //승낙이 정상적으로 처리되었습니다.
        
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 공컨장치장임대 승낙취소(허가취소)를 한다.
     * @param gamCmmnCntrUseExprInqireVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamUpdateCmmnCntrUseExprInqirePrmisnCancel.do") 
    public @ResponseBody Map updateCmmnCntrUseExprInqirePrmisnCancel(
     	   @ModelAttribute("gamCmmnCntrUseExprInqireVO") GamCmmnCntrUseExprInqireVO gamCmmnCntrUseExprInqireVO, 
     	   BindingResult bindingResult)
            throws Exception {
 	
     	Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
         
         //승낙할 임대정보조회
         GamCmmnCntrUseExprInqireVO rentPrmisnInfo = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqirePrmisnInfo(gamCmmnCntrUseExprInqireVO);
         
         //징수의뢰 테이블에 갯수 카운트 조회
         int levReqestCnt = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireLevReqestCnt(gamCmmnCntrUseExprInqireVO);
         
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
         
         GamCmmnCntrUseExprInqireLevReqestVO levReqestInfo = new GamCmmnCntrUseExprInqireLevReqestVO();
         levReqestInfo.setPrtAtCode( rentPrmisnInfo.getPrtAtCode() );
         levReqestInfo.setMngYear( rentPrmisnInfo.getMngYear() );
         levReqestInfo.setMngNo( rentPrmisnInfo.getMngNo() );
         levReqestInfo.setMngCnt( rentPrmisnInfo.getMngCnt() );
 		
         levReqestInfo.setPrmisnYn("N"); //허가여부
         levReqestInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
         levReqestInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
         
         //임대정보의 허가여부를 N으로 업데이트
         gamCmmnCntrUseExprInqireService.updateCmmnCntrUseExprInqirePrmisnCancel(levReqestInfo);
         
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
    @RequestMapping(value="/oper/shed/gamSelectCmmnCntrUseExprInqireFileList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectCmmnCntrUseExprInqireFileList(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	//searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	//searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		//공컨장치장임대목록
    	totalCnt = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireFileListTotCnt(searchVO);
    	List assetFileList = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireFileList(searchVO);
    	
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
     * @param gamCmmnCntrUseExprInqireVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/shed/gamUpdateCmmnCntrUseExprInqireComment.do") 
    public @ResponseBody Map updateCmmnCntrUseExprInqireComment(
    	   @ModelAttribute("gamCmmnCntrUseExprInqireVO") GamCmmnCntrUseExprInqireVO gamCmmnCntrUseExprInqireVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg  = "";
        String updateFlag = "";
        int resultCode = 1;
        
        /*
        int resultLevReqestCnt = -1;
        
        if( EgovStringUtil.isEmpty(gamCmmnCntrUseExprInqireVO.getPrmisnYn()) || gamCmmnCntrUseExprInqireVO.getPrmisnYn().equals("N") ) { //허가여부가 'N'이면 삭제가능
        	deleteFlag = "Y";
        } else {
        	resultLevReqestCnt = gamCmmnCntrUseExprInqireService.selectCmmnCntrUseExprInqireLevReqestCnt(gamCmmnCntrUseExprInqireVO); //징수의뢰 정보 카운트
        	
        	if( gamCmmnCntrUseExprInqireVO.getPrmisnYn().equals("Y") && resultLevReqestCnt == 0 ) { //허가여부가 Y이고 징수의뢰테이블에 정보가 없으면 삭제가능
            	deleteFlag = "Y";
            }
        }
    	*/
        if( gamCmmnCntrUseExprInqireVO.getMngYear() == null || "".equals(gamCmmnCntrUseExprInqireVO.getMngYear()) ) {
        	updateFlag = "N";
        } else {
        	updateFlag = "Y";
        }
        
    	if("Y".equals(updateFlag)) {
	        gamCmmnCntrUseExprInqireService.updateCmmnCntrUseExprInqireComment(gamCmmnCntrUseExprInqireVO);
	    	
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
