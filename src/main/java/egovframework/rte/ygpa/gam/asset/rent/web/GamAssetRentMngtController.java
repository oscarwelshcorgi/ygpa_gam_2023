package egovframework.rte.ygpa.gam.asset.rent.web;

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
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentDetailVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentLevReqestVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService;

/**
 * @Class Name : GamAssetRentMngtController.java
 * @Description : 자산임대관리
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Controller
public class GamAssetRentMngtController {
	
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
    
    @Resource(name = "gamAssetRentMngtService")
    private GamAssetRentMngtService gamAssetRentMngtService;
    
    @Resource(name = "gamAssetsUsePermMngtService")
    private GamAssetsUsePermMngtService gamAssetsUsePermMngtService;
	
    
    /**
     * 자산임대관리 화면을 로딩한다. 
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/asset/rent/GamAssetRentMngt"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/asset/rent/gamAssetRentMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {
    	
		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		//공시지가정보
		List olnlpList = gamAssetRentMngtService.selectOlnlpInfo();
		
		//코픽스 이자율
		List cofixList = gamAssetRentMngtService.selectCofixInfo();
		
		//현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져와서 해당하는 코픽스 이자율 가져오기.
		GamAssetRentMngtVO cofixVO = new GamAssetRentMngtVO();
		GamAssetRentMngtVO cofixResultVO = new GamAssetRentMngtVO();
		
		cofixVO.setcYear(EgovDateUtil.getToday().substring(0,6)); 
		cofixVO = gamAssetRentMngtService.selectAssetRentBeforeQuarterInfo(cofixVO);
		
		if( cofixVO != null ) {
			cofixResultVO = gamAssetRentMngtService.selectAssetRentCofixInfo(cofixVO);
			
			if( cofixResultVO == null ) {
				cofixResultVO = gamAssetRentMngtService.selectAssetRentCofixInfoMax(cofixVO);
			}
			
			if( cofixResultVO != null && cofixResultVO.getBlceStdrIntrrate() != null ) {
				model.addAttribute("blceStdrIntrrate", cofixResultVO.getBlceStdrIntrrate());
			}
			
			if( cofixResultVO != null && cofixResultVO.getBlceStdrIntrrateShow() != null ) {
				model.addAttribute("blceStdrIntrrateShow", cofixResultVO.getBlceStdrIntrrateShow());
			}
		}
		
		model.addAttribute("olnlpList", olnlpList);
		model.addAttribute("cofixList", cofixList);
		model.addAttribute("loginOrgnztId", loginVO.getOrgnztId());
		model.addAttribute("loginUserId", loginVO.getId());
		model.addAttribute("currentDateStr", EgovDateUtil.formatDate(EgovDateUtil.getToday(), "-"));
		model.addAttribute("windowId", windowId);
    	
    	return "/ygpa/gam/asset/rent/GamAssetRentMngt";
    }
	
	/**
     * 자산임대목록을 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/rent/gamSelectAssetRentList.do", method=RequestMethod.POST)
	@ResponseBody Map selectAssetRentList(GamAssetRentMngtVO searchVO) throws Exception {
		
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
		
		//자산임대목록
    	totalCnt = gamAssetRentMngtService.selectAssetRentListTotCnt(searchVO);
    	List assetRentList = gamAssetRentMngtService.selectAssetRentList(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	//총면적, 총사용료
    	GamAssetRentMngtVO resultSum = gamAssetRentMngtService.selectAssetRentSum(searchVO);
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	map.put("sumGrAr", resultSum.getSumGrAr());
    	map.put("sumGrFee", resultSum.getSumGrFee());
    	
    	return map;
    }
	
	/**
     * 자산임대상세리스트를 조회한다. 
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception  
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/asset/rent/gamSelectAssetRentDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetRentDetailList(GamAssetRentMngtVO searchVO) throws Exception {

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

		// 자산임대상세리스트 및 총건수
		totalCnt = gamAssetRentMngtService.selectAssetRentDetailListTotCnt(searchVO);
		List resultList = gamAssetRentMngtService.selectAssetRentDetailList(searchVO);
    	
		paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
		
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }
	
	/**
     * 자산임대,상세,첨부파일을 저장한다.
     * @param dataList
     * @return map
     * @throws Exception
     */
	@RequestMapping(value="/asset/rent/gamSaveAssetRent.do")
	@ResponseBody Map<String, Object> saveAssetRent(@RequestParam Map<String, Object> dataList) throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		GamAssetRentDetailVO saveDetailVO = new GamAssetRentDetailVO(); 
		
		Map<String,Object> map = new HashMap<String,Object>();
		ObjectMapper mapper = new ObjectMapper();

    	List<HashMap<String,String>> insertList=null;
    	List<HashMap<String,String>> updateList=null;
    	List<HashMap<String,String>> deleteList=null;
    	List<HashMap<String,String>> insertFileList=null;
    	List<HashMap<String,String>> updateFileList=null;
    	List<HashMap<String,String>> deleteFileList=null;
    	HashMap<String,String> form=null;
    	
    	int resultCode = -1;
    	String resultMsg = "";
    	
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
    		
    		
    		//자산임대저장
    		GamAssetRentMngtVO saveVO= new GamAssetRentMngtVO();
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
			saveVO.setPayinstIntrrate(form.get("payinstIntrrate"));
    		saveVO.setUpdUsr(loginVO.getId());
    		
    		//if( form.get("cmd") != null && "insert".equals(form.get("cmd")) ) {
    		if( form.get("mngYear") == null || "".equals(form.get("mngYear")) ) {
    			GamAssetRentMngtVO keyVO = new GamAssetRentMngtVO();
    			keyVO = gamAssetRentMngtService.selectAssetRentMaxKey(saveVO);
    			
    			saveVO.setMngYear(keyVO.getMngYear());    
    			saveVO.setMngNo(keyVO.getMngNo());    
    			saveVO.setMngCnt(keyVO.getMngCnt()); 
    			saveVO.setReqstSeCd("1");  //신청구분코드(1:최초, 2:연장, 3:변경, 4:취소)
    			saveVO.setRegUsr(loginVO.getId()); 
    			
    			gamAssetRentMngtService.insertAssetRentFirst(saveVO);
    			
    			//임대상세저장을 위한 키
    			saveDetailVO.setDetailPrtAtCode(form.get("prtAtCode"));
        		saveDetailVO.setDetailMngYear(keyVO.getMngYear());    
        		saveDetailVO.setDetailMngNo(keyVO.getMngNo());      
        		saveDetailVO.setDetailMngCnt(keyVO.getMngCnt());     
    		} else {
    			//saveVO.setReqstSeCd("3"); //신청구분코드(1:최초, 2:연장, 3:변경, 4:취소)
    	    	
    	        gamAssetRentMngtService.updateAssetRent(saveVO);
    			
    			//임대상세저장을 위한 키
    			saveDetailVO.setDetailPrtAtCode(form.get("prtAtCode"));
        		saveDetailVO.setDetailMngYear(form.get("mngYear"));    
        		saveDetailVO.setDetailMngNo(form.get("mngNo"));      
        		saveDetailVO.setDetailMngCnt(form.get("mngCnt"));     
    		}
    		
    		//자산임대상세저장
    		for( int i = 0 ; i < insertList.size() ; i++ ) {
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ insertList.get(i) String => " + insertList.get(i));
    			
    			Map resultMap = insertList.get(i);
    			
    			GamAssetRentDetailVO insertDetailVO = new GamAssetRentDetailVO();
    			
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
    			gamAssetRentMngtService.insertAssetRentDetail(insertDetailVO);
    		}
    		
    		for( int i = 0 ; i < updateList.size() ; i++ ) {
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ updateList.get(i) String => " + updateList.get(i));
    			
    			Map resultMap = updateList.get(i);
    			
    			GamAssetRentDetailVO updateDetailVO = new GamAssetRentDetailVO();
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
    			
    			gamAssetRentMngtService.updateAssetRentDetail(updateDetailVO);
    		}
    		
    		for( int i = 0 ; i < deleteList.size() ; i++ ) {
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ deleteList.get(i) String => " + deleteList.get(i));
    			
    			Map resultMap = deleteList.get(i);
    			
    			GamAssetRentDetailVO deleteDetailVO = new GamAssetRentDetailVO();
    			deleteDetailVO.setAssetsUsageSeq(resultMap.get("assetsUsageSeq").toString());
    			deleteDetailVO.setPrtAtCode(resultMap.get("prtAtCode").toString());
    			deleteDetailVO.setMngYear(resultMap.get("mngYear").toString());    
    			deleteDetailVO.setMngNo(resultMap.get("mngNo").toString());      
    			deleteDetailVO.setMngCnt(resultMap.get("mngCnt").toString());
    			
    			gamAssetRentMngtService.deleteAssetRentDetail2(deleteDetailVO);
    		}
    		
    		//파일저장
    		for( int i = 0 ; i < insertFileList.size() ; i++ ) {
    			Map resultMap = insertFileList.get(i);
    			
    			GamAssetRentMngtVO insertFileVO = new GamAssetRentMngtVO();
    			
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
    			
    			gamAssetRentMngtService.insertAssetRentFile(insertFileVO);
    		}
    		
    		for( int i = 0 ; i < updateFileList.size() ; i++ ) {
    			Map resultMap = updateFileList.get(i);
    			
    			GamAssetRentMngtVO updateFileVO = new GamAssetRentMngtVO();
    			
    			updateFileVO.setPhotoSeq(resultMap.get("photoSeq").toString());
    			updateFileVO.setPrtAtCode(resultMap.get("prtAtCode").toString());
    			updateFileVO.setMngYear(resultMap.get("mngYear").toString());    
    			updateFileVO.setMngNo(resultMap.get("mngNo").toString());      
    			updateFileVO.setMngCnt(resultMap.get("mngCnt").toString());
    			
    			updateFileVO.setPhotoSj(resultMap.get("photoSj").toString());
    			updateFileVO.setShotDt(resultMap.get("shotDt").toString());
    			updateFileVO.setPhotoDesc(resultMap.get("photoDesc").toString());
    			
    			System.out.println("############################################### updateFileVO => " + updateFileVO);
    			
    			gamAssetRentMngtService.updateAssetRentFile(updateFileVO);
    		}
    		
    		for( int i = 0 ; i < deleteFileList.size() ; i++ ) {
    			Map resultMap = deleteFileList.get(i);
    			
    			GamAssetRentMngtVO deleteFileVO = new GamAssetRentMngtVO();
    			
    			deleteFileVO.setPhotoSeq(resultMap.get("photoSeq").toString());
    			deleteFileVO.setPrtAtCode(resultMap.get("prtAtCode").toString());
    			deleteFileVO.setMngYear(resultMap.get("mngYear").toString());    
    			deleteFileVO.setMngNo(resultMap.get("mngNo").toString());      
    			deleteFileVO.setMngCnt(resultMap.get("mngCnt").toString());
    			
    			System.out.println("############################################### deleteFileVO => " + deleteFileVO);
    			
    			gamAssetRentMngtService.deleteAssetRentPhotoSingle(deleteFileVO);
    		}
    		
    		//총사용료, 총면적, 총사용기간 조회
    		GamAssetRentMngtVO paramVO = new GamAssetRentMngtVO(); 
    		paramVO.setPrtAtCode(saveDetailVO.getDetailPrtAtCode());
    		paramVO.setMngYear(saveDetailVO.getDetailMngYear());
    		paramVO.setMngNo(saveDetailVO.getDetailMngNo());
    		paramVO.setMngCnt(saveDetailVO.getDetailMngCnt());
    		
    		GamAssetRentMngtVO updRentVO = new GamAssetRentMngtVO();
    		updRentVO = gamAssetRentMngtService.selectAssetRentCurrRenewInfo(paramVO);
    		
    		if( updRentVO != null ) {
    			updRentVO.setPrtAtCode(paramVO.getPrtAtCode());
    			updRentVO.setMngYear(paramVO.getMngYear());
    			updRentVO.setMngNo(paramVO.getMngNo());
    			updRentVO.setMaxMngCnt(paramVO.getMngCnt());
    			
    			//총사용료, 총면적, 총사용기간 업데이트
    			gamAssetRentMngtService.updateAssetRentRenewInfo(updRentVO);
    			
    			//부두코드 가져오기
    			GamAssetRentMngtVO quaycdVO = new GamAssetRentMngtVO();
    			quaycdVO = gamAssetRentMngtService.selectAssetRentDetailQuaycd(updRentVO);
    			
    			//부두코드 업데이트
    			if( quaycdVO == null || quaycdVO.getQuayCd() == null || "".equals(quaycdVO.getQuayCd()) ) {
    				quaycdVO = new GamAssetRentMngtVO();
    				quaycdVO.setPrtAtCode(paramVO.getPrtAtCode());
    				quaycdVO.setMngYear(paramVO.getMngYear());
    				quaycdVO.setMngNo(paramVO.getMngNo());
    				quaycdVO.setMaxMngCnt(paramVO.getMngCnt());
    			}
    			
    			gamAssetRentMngtService.updateAssetRentQuaycd(quaycdVO);
    		}
    		
    		resultCode = 0;
        	resultMsg  = egovMessageSource.getMessage("success.common.merge");
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		
    		resultCode = 1;
    		resultMsg  = egovMessageSource.getMessage("fail.common.msg");
    	}
    	log.debug("insert list : "+insertList.size());
    	log.debug("updateList list : "+updateList.size());
    	log.debug("deleteList list : "+deleteList.size());

		map.put("resultCode", resultCode);			
		map.put("resultMsg", resultMsg);
		return map;
    }
	
	
	/**
     * 자산임대 최초신청을 등록한다.
     * @param String
     * @param gamAssetRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamInsertAssetRentFirst.do") 
    public @ResponseBody Map insertAssetRentFirst(
    	   @RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamAssetRentMngtVO") GamAssetRentMngtVO gamAssetRentMngtVO, 
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
	        beanValidator.validate(gamAssetRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamAssetRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamAssetRentMngtService.insertAssetRentFirst(gamAssetRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	if("insert".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamAssetRentMngtVO.setReqstSeCd("1");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamAssetRentMngtVO.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	gamAssetRentMngtVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	//gamAssetRentMngtVO.setDeptcd("A001");   //부서코드 (세션?) 
	    	
	        gamAssetRentMngtService.insertAssetRentFirst(gamAssetRentMngtVO);
	    	
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
     * 자산임대 연장신청을 등록한다.
     * @param gamAssetRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamInsertAssetRentRenew.do") 
    public @ResponseBody Map insertAssetRentRenew(
    	   @ModelAttribute("gamAssetRentMngtVO") GamAssetRentMngtVO gamAssetRentMngtVO,
   	       BindingResult bindingResult)
           throws Exception {
    	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
        try {
	    	GamAssetRentMngtVO resultVO = gamAssetRentMngtService.selectAssetRentMaxNo(gamAssetRentMngtVO);
	    	
	    	if( gamAssetRentMngtVO.getMngCnt().equals(resultVO.getMaxMngCnt()) ) {
	    		//키 같고 max관리번호가 같으면 연장신청 등록
	        	
	    		gamAssetRentMngtService.insertAssetRentRenew(gamAssetRentMngtVO);
	    		
	    		resultCode = 0; // return ok
	    		resultMsg  = egovMessageSource.getMessage("success.common.insert");
	    	} else {
	    		resultCode = 1; // return fail
	    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.reject");
	    	}
        } catch(Exception e) {
        	e.printStackTrace();
    		
    		resultCode = 1;
    		resultMsg  = egovMessageSource.getMessage("fail.common.msg");
        }
        
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 자산임대 정보를 수정한다.
     * @param String
     * @param gamAssetRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamUpdateAssetRent.do") 
    public @ResponseBody Map updateAssetRentFirst(
    	   @RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamAssetRentMngtVO") GamAssetRentMngtVO gamAssetRentMngtVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
    	if("modify".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamAssetRentMngtVO.setReqstSeCd("3");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamAssetRentMngtVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	//gamAssetRentMngtVO.setDeptcd("A001");   //부서코드 (세션?) 
	    	
	        gamAssetRentMngtService.updateAssetRent(gamAssetRentMngtVO);
	    	
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
     * 자산임대 정보를 삭제한다.
     * @param String
     * @param gamAssetRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamDeleteAssetRent.do") 
    public @ResponseBody Map deleteAssetRent(
    	   //@RequestParam("cmd") String cmd, 
    	   @ModelAttribute("gamAssetRentDetailVO") GamAssetRentMngtVO gamAssetRentMngtVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg  = "";
        String deleteFlag = "";
        int resultCode = 1;
        
        int resultLevReqestCnt = -1;
        
        try {
	        if( EgovStringUtil.isEmpty(gamAssetRentMngtVO.getPrmisnYn()) || gamAssetRentMngtVO.getPrmisnYn().equals("N") ) { //허가여부가 'N'이면 삭제가능
	        	deleteFlag = "Y";
	        } else {
	        	/*
	        	resultLevReqestCnt = gamAssetRentMngtService.selectAssetRentLevReqestCnt(gamAssetRentMngtVO); //징수의뢰 정보 카운트
	        	
	        	if( gamAssetRentMngtVO.getPrmisnYn().equals("Y") && resultLevReqestCnt == 0 ) { //허가여부가 Y이고 징수의뢰테이블에 정보가 없으면 삭제가능
	            	deleteFlag = "Y";
	            }
	            */
	        	deleteFlag = "N";
	        }
	    	
	    	if("Y".equals(deleteFlag)) {
		        gamAssetRentMngtService.deleteAssetRent(gamAssetRentMngtVO);
		    	
		        resultCode = 0; // return ok
		        resultMsg  = egovMessageSource.getMessage("success.common.delete");
	    	} else {
	    		resultCode = 1; // return fail
	    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.err.delete");
	    	}
        } catch(Exception e) {
        	e.printStackTrace();
    		
    		resultCode = 1;
    		resultMsg  = egovMessageSource.getMessage("fail.common.msg");
        }
        
    	map.put("resultCode", resultCode);
    	map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 자산임대 상세를 등록한다.
     * @param String
     * @param gamAssetRentDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamInsertAssetRentDetail.do") 
    public @ResponseBody Map insertAssetRentDetail(
    	   @RequestParam("detailCmd") String detailCmd, 
    	   @ModelAttribute("gamAssetRentDetailVO") GamAssetRentDetailVO gamAssetRentDetailVO, 
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
	        beanValidator.validate(gamAssetRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamAssetRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamAssetRentMngtService.insertAssetRentFirst(gamAssetRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
        GamAssetRentMngtVO gamAssetRentMngtVO = new GamAssetRentMngtVO();
        gamAssetRentMngtVO.setPrtAtCode(gamAssetRentDetailVO.getDetailPrtAtCode());
        gamAssetRentMngtVO.setMngYear(gamAssetRentDetailVO.getDetailMngYear());
        gamAssetRentMngtVO.setMngNo(gamAssetRentDetailVO.getDetailMngNo());
        gamAssetRentMngtVO.setMngCnt(gamAssetRentDetailVO.getDetailMngCnt());
        
        //임대정보 조회후 승낙여부 체크
        GamAssetRentMngtVO rentPrmisnInfo = gamAssetRentMngtService.selectAssetRentPrmisnInfo(gamAssetRentMngtVO);
        
        
        
        log.debug("######################################## rentPrmisnInfo.getPrmisnYn() => " + rentPrmisnInfo.getPrmisnYn());
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 등록가능
        	if("insert".equals(detailCmd)) {
    	    	//확인후 변경혀라~~
    	    	gamAssetRentDetailVO.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
    	    	gamAssetRentDetailVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
    	    	
    	        gamAssetRentMngtService.insertAssetRentDetail(gamAssetRentDetailVO);
    	    	
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
     * 자산임대 상세를 수정한다.
     * @param String
     * @param gamAssetRentDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamUpdateAssetRentDetail.do") 
    public @ResponseBody Map updateAssetRentDetail(
    	   @RequestParam("detailCmd") String detailCmd, 
    	   @ModelAttribute("gamAssetRentDetailVO") GamAssetRentDetailVO gamAssetRentDetailVO, 
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
    	log.debug("######################################## gamAssetRentMngtVO.getDetailPrtAtCode() => " + gamAssetRentDetailVO.getDetailPrtAtCode());
    	
    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamAssetRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamAssetRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamAssetRentMngtService.insertAssetRentFirst(gamAssetRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	GamAssetRentMngtVO gamAssetRentMngtVO = new GamAssetRentMngtVO();
        gamAssetRentMngtVO.setPrtAtCode(gamAssetRentDetailVO.getDetailPrtAtCode());
        gamAssetRentMngtVO.setMngYear(gamAssetRentDetailVO.getDetailMngYear());
        gamAssetRentMngtVO.setMngNo(gamAssetRentDetailVO.getDetailMngNo());
        gamAssetRentMngtVO.setMngCnt(gamAssetRentDetailVO.getDetailMngCnt());
        
        //임대정보 조회후 승낙여부 체크
        GamAssetRentMngtVO rentPrmisnInfo = gamAssetRentMngtService.selectAssetRentPrmisnInfo(gamAssetRentMngtVO);
        
        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 수정가능
	    	if("modify".equals(detailCmd)) {
		    	gamAssetRentDetailVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
		    	
		        gamAssetRentMngtService.updateAssetRentDetail(gamAssetRentDetailVO);
		    	
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
     * 자산임대 상세를 삭제한다.
     * @param gamAssetRentDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamDeleteAssetRentDetail.do") 
    public @ResponseBody Map deleteAssetRentDetail(
    	   @ModelAttribute("gamAssetRentDetailVO") GamAssetRentDetailVO gamAssetRentDetailVO, 
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
	        beanValidator.validate(gamAssetRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamAssetRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamAssetRentMngtService.insertAssetRentFirst(gamAssetRentMngtVO);
	    	
			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */
    	
    	gamAssetRentMngtService.deleteAssetRentDetail2(gamAssetRentDetailVO);
    	
        resultCode = 0; // return ok
		resultMsg  = egovMessageSource.getMessage("success.common.delete");
		
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 승낙 팝업화면을 로딩한다. 
     *
     * @param gamAssetRentLevReqestVO
     * @param model the model
     * @return "/ygpa/gam/asset/rent/GamPopupAssetRentPrmisn"
     * @throws Exception the exception  
     */
	@RequestMapping(value="/asset/rent/popup/showAssetRentPrmisn.do") 
    String showEntrpsInfo(GamAssetRentLevReqestVO gamAssetRentLevReqestVO, ModelMap model) throws Exception {
    	
		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();
		
		codeVo.setCodeId("GAM024"); //요금종류
		List chrgeKndCdList = cmmUseService.selectCmmCodeDetail(codeVo);
		
		model.addAttribute("gamAssetRentInfo", gamAssetRentLevReqestVO);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);

    	return "/ygpa/gam/asset/rent/GamPopupAssetRentPrmisn";
    }
    
    /**
     * 자산임대 승낙(허가)을 한다.
     * @param gamAssetRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamInsertAssetRentPrmisn.do") 
    public @ResponseBody Map insertAssetRentLevReqest(
    	   @ModelAttribute("gamAssetRentMngtVO") GamAssetRentMngtVO gamAssetRentMngtVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;
        
        
        //승낙할 임대정보조회
        GamAssetRentMngtVO rentPrmisnInfo = gamAssetRentMngtService.selectAssetRentPrmisnInfo(gamAssetRentMngtVO);
        
        //징수의뢰 테이블에 갯수 카운트 조회
        int levReqestCnt = gamAssetRentMngtService.selectAssetRentLevReqestCnt(gamAssetRentMngtVO);
        
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
        
        GamAssetRentLevReqestVO levReqestInfo = new GamAssetRentLevReqestVO();
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
		levReqestInfo.setChrgeKnd( gamAssetRentMngtVO.getChrgeKnd() );
		levReqestInfo.setVatYn( gamAssetRentMngtVO.getVatYn() );
		levReqestInfo.setPayMth( rentPrmisnInfo.getPayMth() );
		
        levReqestInfo.setPrmisnYn("Y"); //허가여부
        levReqestInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
        levReqestInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
        
        //임대정보의 허가여부를 Y로 업데이트 및 징수의뢰 insert
        gamAssetRentMngtService.updateAssetRentPrmisn(levReqestInfo);
        
        resultCode = 0; 
		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.exec"); //승낙이 정상적으로 처리되었습니다.
        
    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        
		return map;
    }
    
    /**
     * 자산임대 승낙취소(허가취소)를 한다.
     * @param gamAssetRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    /*
    @RequestMapping(value="/asset/rent/gamUpdateAssetRentPrmisnCancel.do") 
    public @ResponseBody Map updateAssetRentPrmisnCancel(
     	   @ModelAttribute("gamAssetRentMngtVO") GamAssetRentMngtVO gamAssetRentMngtVO, 
     	   BindingResult bindingResult)
            throws Exception {
 	
     	Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
         
         //승낙할 임대정보조회
         GamAssetRentMngtVO rentPrmisnInfo = gamAssetRentMngtService.selectAssetRentPrmisnInfo(gamAssetRentMngtVO);
         
         //징수의뢰 테이블에 갯수 카운트 조회
         int levReqestCnt = gamAssetRentMngtService.selectAssetRentLevReqestCnt(gamAssetRentMngtVO);
         
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
         
         GamAssetRentLevReqestVO levReqestInfo = new GamAssetRentLevReqestVO();
         levReqestInfo.setPrtAtCode( rentPrmisnInfo.getPrtAtCode() );
         levReqestInfo.setMngYear( rentPrmisnInfo.getMngYear() );
         levReqestInfo.setMngNo( rentPrmisnInfo.getMngNo() );
         levReqestInfo.setMngCnt( rentPrmisnInfo.getMngCnt() );
 		
         levReqestInfo.setPrmisnYn("N"); //허가여부
         levReqestInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
         levReqestInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
         
         //임대정보의 허가여부를 N으로 업데이트
         gamAssetRentMngtService.updateAssetRentPrmisnCancel(levReqestInfo);
         
         resultCode = 0; 
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.execCancel"); //승낙이 정상적으로 취소되었습니다.
         
     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);
         
 		return map;
     }
     */
    
    /**
     * 자산임대 승낙을 한다.
     * @param gamAssetRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamUpdateAssetRentPrmisn.do") 
    public @ResponseBody Map updateAssetRentPrmisn(
     	   @ModelAttribute("gamAssetRentMngtVO") GamAssetRentMngtVO gamAssetRentMngtVO, 
     	   BindingResult bindingResult)
            throws Exception {
 	
     	 Map map = new HashMap();
     	 Map paramMap = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
         
         LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
         
         System.out.println("##################################### 승낙시작!!");
         System.out.println("##################################### getPrtAtCode => " +  gamAssetRentMngtVO.getPrtAtCode());
         System.out.println("##################################### getMngYear => " +  gamAssetRentMngtVO.getMngYear());
         System.out.println("##################################### getMngNo => " +  gamAssetRentMngtVO.getMngNo());
         System.out.println("##################################### getMngCnt => " +  gamAssetRentMngtVO.getMngCnt());
         System.out.println("##################################### getChrgeKnd => " +  gamAssetRentMngtVO.getChrgeKnd());
         
         //prtAtCode:항코드, mngYear:관리번호, mngNo:관리 순번, mngCnt:관리 횟수, chrgeKnd: 요금종류
         paramMap.put("prtAtCode", gamAssetRentMngtVO.getPrtAtCode());
         paramMap.put("mngYear", gamAssetRentMngtVO.getMngYear());
         paramMap.put("mngNo", gamAssetRentMngtVO.getMngNo());
         paramMap.put("mngCnt", gamAssetRentMngtVO.getMngCnt());
         paramMap.put("regUsr", loginVO.getId());
         paramMap.put("chrgeKnd", gamAssetRentMngtVO.getChrgeKnd());
         
         System.out.println("##################################### paramMap => " + paramMap);
         
         if(!paramMap.containsKey("prtAtCode") || !paramMap.containsKey("mngYear") || !paramMap.containsKey("mngNo") || !paramMap.containsKey("mngCnt")) {
             resultCode = 2;
        	 resultMsg = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
         }
         else {
        	 gamAssetsUsePermMngtService.confirmAssetsRentUsePerm(paramMap);

	         resultCode = 0;
	 		 resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.exec");
         }
         
     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);
         
 		return map;
     }
    
    /**
     * 자산임대 승낙취소(허가취소)를 한다.
     * @param gamAssetRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamUpdateAssetRentPrmisnCancel.do") 
    public @ResponseBody Map updateAssetRentPrmisnCancel(
     	   @ModelAttribute("gamAssetRentMngtVO") GamAssetRentMngtVO gamAssetRentMngtVO, 
     	   BindingResult bindingResult)
            throws Exception {
 	
     	 Map map = new HashMap();
     	 Map paramMap = new HashMap();
         String resultMsg = "";
         int resultCode = 1;
         
         LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
         
         System.out.println("##################################### 승낙취소시작!!");
         System.out.println("##################################### getPrtAtCode => " +  gamAssetRentMngtVO.getPrtAtCode());
         System.out.println("##################################### getMngYear => " +  gamAssetRentMngtVO.getMngYear());
         System.out.println("##################################### getMngNo => " +  gamAssetRentMngtVO.getMngNo());
         System.out.println("##################################### getMngCnt => " +  gamAssetRentMngtVO.getMngCnt());
         
         //prtAtCode:항코드, mngYear:관리번호, mngNo:관리 순번, mngCnt:관리 횟수, chrgeKnd: 요금종류
         paramMap.put("prtAtCode", gamAssetRentMngtVO.getPrtAtCode());
         paramMap.put("mngYear", gamAssetRentMngtVO.getMngYear());
         paramMap.put("mngNo", gamAssetRentMngtVO.getMngNo());
         paramMap.put("mngCnt", gamAssetRentMngtVO.getMngCnt());
         paramMap.put("regUsr", loginVO.getId());
         
         System.out.println("##################################### paramMap => " + paramMap);
         
         if(!paramMap.containsKey("prtAtCode") || !paramMap.containsKey("mngYear") || !paramMap.containsKey("mngNo") || !paramMap.containsKey("mngCnt")) {
             resultCode = 2;
        	 resultMsg = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
         }
         else {
        	 gamAssetsUsePermMngtService.cancelAssetsRentUsePerm(paramMap);

	         resultCode = 0;
	 		 resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.exec");
         }
         
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
    @RequestMapping(value="/asset/rent/gamSelectAssetRentFileList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectAssetRentFileList(GamAssetRentMngtVO searchVO) throws Exception {

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
		
		//자산임대목록
    	totalCnt = gamAssetRentMngtService.selectAssetRentFileListTotCnt(searchVO);
    	List assetFileList = gamAssetRentMngtService.selectAssetRentFileList(searchVO);
    	
    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());
    	
    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetFileList);
    	map.put("searchOption", searchVO);
    	
    	return map;
    }
	
	/**
     * 코멘트를 저장한다.
     * @param String
     * @param gamAssetRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/asset/rent/gamUpdateAssetRentComment.do") 
    public @ResponseBody Map updateAssetRentComment(
    	   @ModelAttribute("gamAssetRentMngtVO") GamAssetRentMngtVO gamAssetRentMngtVO, 
    	   BindingResult bindingResult)
           throws Exception {
	
    	Map map = new HashMap();
        String resultMsg  = "";
        String updateFlag = "";
        int resultCode = 1;
        
        /*
        int resultLevReqestCnt = -1;
        
        if( EgovStringUtil.isEmpty(gamAssetRentMngtVO.getPrmisnYn()) || gamAssetRentMngtVO.getPrmisnYn().equals("N") ) { //허가여부가 'N'이면 삭제가능
        	deleteFlag = "Y";
        } else {
        	resultLevReqestCnt = gamAssetRentMngtService.selectAssetRentLevReqestCnt(gamAssetRentMngtVO); //징수의뢰 정보 카운트
        	
        	if( gamAssetRentMngtVO.getPrmisnYn().equals("Y") && resultLevReqestCnt == 0 ) { //허가여부가 Y이고 징수의뢰테이블에 정보가 없으면 삭제가능
            	deleteFlag = "Y";
            }
        }
    	*/
        if( gamAssetRentMngtVO.getMngYear() == null || "".equals(gamAssetRentMngtVO.getMngYear()) ) {
        	updateFlag = "N";
        } else {
        	updateFlag = "Y";
        }
        
    	if("Y".equals(updateFlag)) {
	        gamAssetRentMngtService.updateAssetRentComment(gamAssetRentMngtVO);
	    	
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
