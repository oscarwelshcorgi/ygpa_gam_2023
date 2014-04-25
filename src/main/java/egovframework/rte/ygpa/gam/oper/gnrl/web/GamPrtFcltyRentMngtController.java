package egovframework.rte.ygpa.gam.oper.gnrl.web;

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
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtVO;

import egovframework.rte.ygpa.gam.asset.service.GamAssetDisUseMngtVO;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamAssetsUsePermMngtService;

/**
 * @Class Name : GamPrtFcltyRentMngtController.java
 * @Description : 항만시설사용목록관리 (항만시설/일반부두/항만시설사용목록관리)
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
public class GamPrtFcltyRentMngtController {

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

    @Resource(name = "gamPrtFcltyRentMngtService")
    private GamPrtFcltyRentMngtService gamPrtFcltyRentMngtService;

    @Resource(name = "gamAssetsUsePermMngtService")
    private GamAssetsUsePermMngtService gamAssetsUsePermMngtService;

    /**
     * 항만시설사용관리 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/gnrl/GamPrtFcltyRentMngt"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/gnrl/gamPrtFcltyRentMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//공시지가정보
		//GamPrtFcltyRentMngtVO gvo = new GamPrtFcltyRentMngtVO();
		List olnlpList = gamPrtFcltyRentMngtService.selectOlnlpInfo();

		//코픽스 이자율
		List cofixList = gamPrtFcltyRentMngtService.selectCofixInfo();

		//현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져와서 해당하는 코픽스 이자율 가져오기.
		GamPrtFcltyRentMngtVO cofixVO = new GamPrtFcltyRentMngtVO();
		GamPrtFcltyRentMngtVO cofixResultVO = new GamPrtFcltyRentMngtVO();

		cofixVO.setcYear(EgovDateUtil.getToday().substring(0,6));
		cofixVO = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtBeforeQuarterInfo(cofixVO);

		if( cofixVO != null ) {
			cofixResultVO = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtCofixInfo(cofixVO);

			if( cofixResultVO == null ) {
				cofixResultVO = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtCofixInfoMax(cofixVO);
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

    	return "/ygpa/gam/oper/gnrl/GamPrtFcltyRentMngt";
    }

	/**
     * 항만시설사용목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrl/gamSelectPrtFcltyRentMngtList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectPrtFcltyRentMngtList(GamPrtFcltyRentMngtVO searchVO) throws Exception {

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

		//항만시설사용목록
    	totalCnt = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtListTotCnt(searchVO);
    	List assetRentList = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	//총면적, 총사용료
    	GamPrtFcltyRentMngtVO resultSum = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtSum(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	map.put("sumGrAr", resultSum.getSumGrAr());
    	map.put("sumGrFee", resultSum.getSumGrFee());

    	return map;
    }

	/**
     * 항만시설사용상세리스트를 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrl/gamSelectPrtFcltyRentMngtDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectPrtFcltyRentMngtDetailList(GamPrtFcltyRentMngtVO searchVO) throws Exception {

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

		// 항만시설사용상세리스트 및 총건수
		totalCnt = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtDetailListTotCnt(searchVO);
		List resultList = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtDetailList(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	/**
     * 항만시설,상세,첨부파일을 저장한다.
     * @param String
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
	@RequestMapping(value="/oper/gnrl/gamSavePrtFcltyRentMngt.do")
	@ResponseBody Map<String, Object> savePrtFcltyRentMngt(@RequestParam Map<String, Object> dataList) throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		GamPrtFcltyRentMngtDetailVO saveDetailVO = new GamPrtFcltyRentMngtDetailVO();

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

    		/*
    		log.debug("###################################################### insertList : "+insertList);
    		log.debug("###################################################### updateList : "+updateList);
    		log.debug("###################################################### deleteList : "+deleteList);
    		log.debug("###################################################### form : "+form);
    		log.debug("###################################################### cmd : "+form.get("cmd"));

    		log.debug("###################################################### insertList.size() => "+insertList.size());
    		log.debug("###################################################### updateList.size() => "+updateList.size());
    		log.debug("###################################################### deleteList.size() => "+deleteList.size());
			*/

    		//항만시설사용저장
    		GamPrtFcltyRentMngtVO saveVO= new GamPrtFcltyRentMngtVO();
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

    		log.debug("########### form.get(payMth) => "+form.get("payMth"));
    		log.debug("########### saveVO.setPayMth(.get(payMth) => "+saveVO.getPayMth());

    		//if( form.get("cmd") != null && "insert".equals(form.get("cmd")) ) {
    		if( form.get("mngYear") == null || "".equals(form.get("mngYear")) ) {
    			GamPrtFcltyRentMngtVO keyVO = new GamPrtFcltyRentMngtVO();
    			keyVO = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtMaxKey(saveVO);

    			saveVO.setMngYear(keyVO.getMngYear());
    			saveVO.setMngNo(keyVO.getMngNo());
    			saveVO.setMngCnt(keyVO.getMngCnt());
    			saveVO.setReqstSeCd("1");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
    			saveVO.setRegUsr(loginVO.getId());

    			gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(saveVO);

    			//항만시설사용 상세저장을 위한 키
    			saveDetailVO.setDetailPrtAtCode(form.get("prtAtCode"));
        		saveDetailVO.setDetailMngYear(keyVO.getMngYear());
        		saveDetailVO.setDetailMngNo(keyVO.getMngNo());
        		saveDetailVO.setDetailMngCnt(keyVO.getMngCnt());
    		} else {
    			//saveVO.setReqstSeCd("3");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?

    	        gamPrtFcltyRentMngtService.updatePrtFcltyRentMngt(saveVO);

    			//항만시설사용 상세저장을 위한 키
    			saveDetailVO.setDetailPrtAtCode(form.get("prtAtCode"));
        		saveDetailVO.setDetailMngYear(form.get("mngYear"));
        		saveDetailVO.setDetailMngNo(form.get("mngNo"));
        		saveDetailVO.setDetailMngCnt(form.get("mngCnt"));
    		}

    		//항만시설사용 상세저장
    		for( int i = 0 ; i < insertList.size() ; i++ ) {
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ insertList.get(i) String => " + insertList.get(i));

    			Map resultMap = insertList.get(i);

    			GamPrtFcltyRentMngtDetailVO insertDetailVO = new GamPrtFcltyRentMngtDetailVO();

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
    			gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtDetail(insertDetailVO);
    		}

    		for( int i = 0 ; i < updateList.size() ; i++ ) {
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ updateList.get(i) String => " + updateList.get(i));

    			Map resultMap = updateList.get(i);

    			GamPrtFcltyRentMngtDetailVO updateDetailVO = new GamPrtFcltyRentMngtDetailVO();
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

    			gamPrtFcltyRentMngtService.updatePrtFcltyRentMngtDetail(updateDetailVO);
    		}

    		for( int i = 0 ; i < deleteList.size() ; i++ ) {
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ deleteList.get(i) String => " + deleteList.get(i));

    			Map resultMap = deleteList.get(i);

    			GamPrtFcltyRentMngtDetailVO deleteDetailVO = new GamPrtFcltyRentMngtDetailVO();
    			deleteDetailVO.setAssetsUsageSeq(resultMap.get("assetsUsageSeq").toString());
    			deleteDetailVO.setPrtAtCode(resultMap.get("prtAtCode").toString());
    			deleteDetailVO.setMngYear(resultMap.get("mngYear").toString());
    			deleteDetailVO.setMngNo(resultMap.get("mngNo").toString());
    			deleteDetailVO.setMngCnt(resultMap.get("mngCnt").toString());

    			gamPrtFcltyRentMngtService.deletePrtFcltyRentMngtDetail2(deleteDetailVO);
    		}

    		//파일저장
    		for( int i = 0 ; i < insertFileList.size() ; i++ ) {
    			Map resultMap = insertFileList.get(i);

    			GamPrtFcltyRentMngtVO insertFileVO = new GamPrtFcltyRentMngtVO();

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

    			gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtFile(insertFileVO);
    		}

    		for( int i = 0 ; i < updateFileList.size() ; i++ ) {
    			Map resultMap = updateFileList.get(i);

    			GamPrtFcltyRentMngtVO updateFileVO = new GamPrtFcltyRentMngtVO();

    			updateFileVO.setPhotoSeq(resultMap.get("photoSeq").toString());
    			updateFileVO.setPrtAtCode(resultMap.get("prtAtCode").toString());
    			updateFileVO.setMngYear(resultMap.get("mngYear").toString());
    			updateFileVO.setMngNo(resultMap.get("mngNo").toString());
    			updateFileVO.setMngCnt(resultMap.get("mngCnt").toString());

    			updateFileVO.setPhotoSj(resultMap.get("photoSj").toString());
    			updateFileVO.setShotDt(resultMap.get("shotDt").toString());
    			updateFileVO.setPhotoDesc(resultMap.get("photoDesc").toString());

    			System.out.println("############################################### updateFileVO => " + updateFileVO);

    			gamPrtFcltyRentMngtService.updatePrtFcltyRentMngtFile(updateFileVO);
    		}

    		for( int i = 0 ; i < deleteFileList.size() ; i++ ) {
    			Map resultMap = deleteFileList.get(i);

    			GamPrtFcltyRentMngtVO deleteFileVO = new GamPrtFcltyRentMngtVO();

    			deleteFileVO.setPhotoSeq(resultMap.get("photoSeq").toString());
    			deleteFileVO.setPrtAtCode(resultMap.get("prtAtCode").toString());
    			deleteFileVO.setMngYear(resultMap.get("mngYear").toString());
    			deleteFileVO.setMngNo(resultMap.get("mngNo").toString());
    			deleteFileVO.setMngCnt(resultMap.get("mngCnt").toString());

    			System.out.println("############################################### deleteFileVO => " + deleteFileVO);

    			gamPrtFcltyRentMngtService.deletePrtFcltyRentMngtPhotoSingle(deleteFileVO);
    		}

    		//총사용료, 총면적, 총사용기간 조회
    		GamPrtFcltyRentMngtVO paramVO = new GamPrtFcltyRentMngtVO();
    		paramVO.setPrtAtCode(saveDetailVO.getDetailPrtAtCode());
    		paramVO.setMngYear(saveDetailVO.getDetailMngYear());
    		paramVO.setMngNo(saveDetailVO.getDetailMngNo());
    		paramVO.setMngCnt(saveDetailVO.getDetailMngCnt());

    		GamPrtFcltyRentMngtVO updRentVO = new GamPrtFcltyRentMngtVO();
    		updRentVO = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtCurrRenewInfo(paramVO);

    		if( updRentVO != null ) {
    			updRentVO.setPrtAtCode(paramVO.getPrtAtCode());
    			updRentVO.setMngYear(paramVO.getMngYear());
    			updRentVO.setMngNo(paramVO.getMngNo());
    			updRentVO.setMaxMngCnt(paramVO.getMngCnt());

    			//총사용료, 총면적, 총사용기간 업데이트
    			gamPrtFcltyRentMngtService.updatePrtFcltyRentMngtRenewInfo(updRentVO);

    			//부두코드 가져오기
    			GamPrtFcltyRentMngtVO quaycdVO = new GamPrtFcltyRentMngtVO();
    			quaycdVO = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtDetailQuaycd(updRentVO);

    			//부두코드 업데이트
    			if( quaycdVO == null || quaycdVO.getQuayCd() == null || "".equals(quaycdVO.getQuayCd()) ) {
    				quaycdVO = new GamPrtFcltyRentMngtVO();
    				quaycdVO.setPrtAtCode(paramVO.getPrtAtCode());
    				quaycdVO.setMngYear(paramVO.getMngYear());
    				quaycdVO.setMngNo(paramVO.getMngNo());
    				quaycdVO.setMaxMngCnt(paramVO.getMngCnt());
    			}

    			gamPrtFcltyRentMngtService.updatePrtFcltyRentMngtQuaycd(quaycdVO);
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
     * 항만시설사용,상세를 저장한다.
     * @param String
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamInsertPrtFcltyRentMngtFirst.do")
    public @ResponseBody Map insertPrtFcltyRentMngtFirst(
    	   @RequestParam("cmd") String cmd,
    	   @ModelAttribute("gamPrtFcltyRentMngtVO") GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO,
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
	        beanValidator.validate(gamPrtFcltyRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamPrtFcltyRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(gamPrtFcltyRentMngtVO);

			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */

    	if("insert".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamPrtFcltyRentMngtVO.setReqstSeCd("1");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamPrtFcltyRentMngtVO.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	gamPrtFcltyRentMngtVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	//gamPrtFcltyRentMngtVO.setDeptcd("A001");   //부서코드 (세션?)

	        gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(gamPrtFcltyRentMngtVO);

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
     * 항만시설사용 연장신청을 등록한다.
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamInsertPrtFcltyRentMngtRenew.do")
    public @ResponseBody Map insertPrtFcltyRentMngtRenew(
    	   @ModelAttribute("gamPrtFcltyRentMngtVO") GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO,
   	       BindingResult bindingResult)
           throws Exception {

    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;

        try {
	    	GamPrtFcltyRentMngtVO resultVO = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtMaxNo(gamPrtFcltyRentMngtVO);

	    	if( gamPrtFcltyRentMngtVO.getMngCnt().equals(resultVO.getMaxMngCnt()) ) {
	    		//키 같고 max관리번호가 같으면 연장신청 등록

	    		gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtRenew(gamPrtFcltyRentMngtVO);

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
     * 항만시설사용 정보를 수정한다.
     * @param String
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamUpdatePrtFcltyRentMngt.do")
    public @ResponseBody Map updatePrtFcltyRentMngtFirst(
    	   @RequestParam("cmd") String cmd,
    	   @ModelAttribute("gamPrtFcltyRentMngtVO") GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO,
    	   BindingResult bindingResult)
           throws Exception {

    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;

    	if("modify".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamPrtFcltyRentMngtVO.setReqstSeCd("3");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamPrtFcltyRentMngtVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)
	    	//gamPrtFcltyRentMngtVO.setDeptcd("A001");   //부서코드 (세션?)

	        gamPrtFcltyRentMngtService.updatePrtFcltyRentMngt(gamPrtFcltyRentMngtVO);

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
     * 항만시설사용 정보를 삭제한다.
     * @param String
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamDeletePrtFcltyRentMngt.do")
    public @ResponseBody Map deletePrtFcltyRentMngt(
    	   //@RequestParam("cmd") String cmd,
    	   @ModelAttribute("gamPrtFcltyRentMngtDetailVO") GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO,
    	   BindingResult bindingResult)
           throws Exception {

    	Map map = new HashMap();
        String resultMsg  = "";
        String deleteFlag = "";
        int resultCode = 1;

        int resultLevReqestCnt = -1;

        try {
	        if( EgovStringUtil.isEmpty(gamPrtFcltyRentMngtVO.getPrmisnYn()) || gamPrtFcltyRentMngtVO.getPrmisnYn().equals("N") ) { //허가여부가 'N'이면 삭제가능
	        	deleteFlag = "Y";
	        } else {
	        	/*
	        	resultLevReqestCnt = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtLevReqestCnt(gamPrtFcltyRentMngtVO); //징수의뢰 정보 카운트

	        	if( gamPrtFcltyRentMngtVO.getPrmisnYn().equals("Y") && resultLevReqestCnt == 0 ) { //허가여부가 Y이고 징수의뢰테이블에 정보가 없으면 삭제가능
	            	deleteFlag = "Y";
	            }
	            */
	        	deleteFlag = "N";
	        }

	    	if("Y".equals(deleteFlag)) {
		        gamPrtFcltyRentMngtService.deletePrtFcltyRentMngt(gamPrtFcltyRentMngtVO);

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
     * 항만시설사용 상세를 등록한다.
     * @param String
     * @param gamPrtFcltyRentMngtDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamInsertPrtFcltyRentMngtDetail.do")
    public @ResponseBody Map insertPrtFcltyRentMngtDetail(
    	   @RequestParam("detailCmd") String detailCmd,
    	   @ModelAttribute("gamPrtFcltyRentMngtDetailVO") GamPrtFcltyRentMngtDetailVO gamPrtFcltyRentMngtDetailVO,
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
	        beanValidator.validate(gamPrtFcltyRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamPrtFcltyRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(gamPrtFcltyRentMngtVO);

			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */

        GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO = new GamPrtFcltyRentMngtVO();
        gamPrtFcltyRentMngtVO.setPrtAtCode(gamPrtFcltyRentMngtDetailVO.getDetailPrtAtCode());
        gamPrtFcltyRentMngtVO.setMngYear(gamPrtFcltyRentMngtDetailVO.getDetailMngYear());
        gamPrtFcltyRentMngtVO.setMngNo(gamPrtFcltyRentMngtDetailVO.getDetailMngNo());
        gamPrtFcltyRentMngtVO.setMngCnt(gamPrtFcltyRentMngtDetailVO.getDetailMngCnt());

        //임대정보 조회후 승낙여부 체크
        GamPrtFcltyRentMngtVO rentPrmisnInfo = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtPrmisnInfo(gamPrtFcltyRentMngtVO);



        log.debug("######################################## rentPrmisnInfo.getPrmisnYn() => " + rentPrmisnInfo.getPrmisnYn());

        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 등록가능
        	if("insert".equals(detailCmd)) {
    	    	//확인후 변경혀라~~
    	    	gamPrtFcltyRentMngtDetailVO.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
    	    	gamPrtFcltyRentMngtDetailVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)

    	        gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtDetail(gamPrtFcltyRentMngtDetailVO);

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
     * 항만시설사용 상세를 수정한다.
     * @param String
     * @param gamPrtFcltyRentMngtDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamUpdatePrtFcltyRentMngtDetail.do")
    public @ResponseBody Map updatePrtFcltyRentMngtDetail(
    	   @RequestParam("detailCmd") String detailCmd,
    	   @ModelAttribute("gamPrtFcltyRentMngtDetailVO") GamPrtFcltyRentMngtDetailVO gamPrtFcltyRentMngtDetailVO,
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
    	log.debug("######################################## gamPrtFcltyRentMngtVO.getDetailPrtAtCode() => " + gamPrtFcltyRentMngtDetailVO.getDetailPrtAtCode());

    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamPrtFcltyRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamPrtFcltyRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(gamPrtFcltyRentMngtVO);

			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */

    	GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO = new GamPrtFcltyRentMngtVO();
        gamPrtFcltyRentMngtVO.setPrtAtCode(gamPrtFcltyRentMngtDetailVO.getDetailPrtAtCode());
        gamPrtFcltyRentMngtVO.setMngYear(gamPrtFcltyRentMngtDetailVO.getDetailMngYear());
        gamPrtFcltyRentMngtVO.setMngNo(gamPrtFcltyRentMngtDetailVO.getDetailMngNo());
        gamPrtFcltyRentMngtVO.setMngCnt(gamPrtFcltyRentMngtDetailVO.getDetailMngCnt());

        //임대정보 조회후 승낙여부 체크
        GamPrtFcltyRentMngtVO rentPrmisnInfo = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtPrmisnInfo(gamPrtFcltyRentMngtVO);

        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 수정가능
	    	if("modify".equals(detailCmd)) {
		    	gamPrtFcltyRentMngtDetailVO.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)

		        gamPrtFcltyRentMngtService.updatePrtFcltyRentMngtDetail(gamPrtFcltyRentMngtDetailVO);

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
     * 항만시설사용 상세를 삭제한다.
     * @param gamPrtFcltyRentMngtDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamDeletePrtFcltyRentMngtDetail.do")
    public @ResponseBody Map deletePrtFcltyRentMngtDetail(
    	   @ModelAttribute("gamPrtFcltyRentMngtDetailVO") GamPrtFcltyRentMngtDetailVO gamPrtFcltyRentMngtDetailVO,
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
	        beanValidator.validate(gamPrtFcltyRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamPrtFcltyRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(gamPrtFcltyRentMngtVO);

			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */

    	gamPrtFcltyRentMngtService.deletePrtFcltyRentMngtDetail2(gamPrtFcltyRentMngtDetailVO);

        resultCode = 0; // return ok
		resultMsg  = egovMessageSource.getMessage("success.common.delete");

    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

		return map;
    }

    /**
     * 승낙 팝업화면을 로딩한다.
     *
     * @param gamPrtFcltyRentMngtLevReqestVO
     * @param model the model
     * @return "/ygpa/gam/oper/gnrl/GamPopupPrtFcltyRentMngtPrmisn"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/gnrl/popup/showPrtFcltyRentMngtPrmisn.do")
    String showEntrpsInfo(GamPrtFcltyRentMngtLevReqestVO gamPrtFcltyRentMngtLevReqestVO, ModelMap model) throws Exception {

		ComDefaultCodeVO codeVo = new ComDefaultCodeVO();

		codeVo.setCodeId("GAM024"); //요금종류
		List chrgeKndCdList = cmmUseService.selectCmmCodeDetail(codeVo);

		model.addAttribute("gamPrtFcltyRentMngtInfo", gamPrtFcltyRentMngtLevReqestVO);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);

    	return "/ygpa/gam/oper/gnrl/GamPopupPrtFcltyRentMngtPrmisn";
    }

    /**
     * 항만시설사용 승낙(허가)을 한다.
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamInsertPrtFcltyRentMngtPrmisn.do")
    public @ResponseBody Map insertPrtFcltyRentMngtLevReqest(
    	   @ModelAttribute("gamPrtFcltyRentMngtVO") GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO,
    	   BindingResult bindingResult)
           throws Exception {

    	Map map = new HashMap();
        String resultMsg = "";
        int resultCode = 1;


        //승낙할 임대정보조회
        GamPrtFcltyRentMngtVO rentPrmisnInfo = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtPrmisnInfo(gamPrtFcltyRentMngtVO);

        //징수의뢰 테이블에 갯수 카운트 조회
        int levReqestCnt = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtLevReqestCnt(gamPrtFcltyRentMngtVO);

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

        GamPrtFcltyRentMngtLevReqestVO levReqestInfo = new GamPrtFcltyRentMngtLevReqestVO();
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
		levReqestInfo.setChrgeKnd( gamPrtFcltyRentMngtVO.getChrgeKnd() );
		levReqestInfo.setVatYn( gamPrtFcltyRentMngtVO.getVatYn() );
		levReqestInfo.setPayMth( rentPrmisnInfo.getPayMth() );

        levReqestInfo.setPrmisnYn("Y"); //허가여부
        levReqestInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
        levReqestInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)

        //임대정보의 허가여부를 Y로 업데이트 및 징수의뢰 insert
        gamPrtFcltyRentMngtService.updatePrtFcltyRentMngtPrmisn(levReqestInfo);

        resultCode = 0;
		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.exec"); //승낙이 정상적으로 처리되었습니다.

    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

		return map;
    }

    /**
     * 항만시설사용 승낙취소(허가취소)를 한다.
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    /*
    @RequestMapping(value="/oper/gnrl/gamUpdatePrtFcltyRentMngtPrmisnCancel.do")
    public @ResponseBody Map updatePrtFcltyRentMngtPrmisnCancel(
     	   @ModelAttribute("gamPrtFcltyRentMngtVO") GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO,
     	   BindingResult bindingResult)
            throws Exception {

     	 Map map = new HashMap();
         String resultMsg = "";
         int resultCode = 1;

         //승낙할 임대정보조회
         GamPrtFcltyRentMngtVO rentPrmisnInfo = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtPrmisnInfo(gamPrtFcltyRentMngtVO);

         //징수의뢰 테이블에 갯수 카운트 조회
         int levReqestCnt = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtLevReqestCnt(gamPrtFcltyRentMngtVO);

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

         GamPrtFcltyRentMngtLevReqestVO levReqestInfo = new GamPrtFcltyRentMngtLevReqestVO();
         levReqestInfo.setPrtAtCode( rentPrmisnInfo.getPrtAtCode() );
         levReqestInfo.setMngYear( rentPrmisnInfo.getMngYear() );
         levReqestInfo.setMngNo( rentPrmisnInfo.getMngNo() );
         levReqestInfo.setMngCnt( rentPrmisnInfo.getMngCnt() );

         levReqestInfo.setPrmisnYn("N"); //허가여부
         levReqestInfo.setRegUsr("admin1"); //등록자 (세션 로그인 아이디)
         levReqestInfo.setUpdUsr("admin1"); //등록자 (세션 로그인 아이디)

         //임대정보의 허가여부를 N으로 업데이트
         gamPrtFcltyRentMngtService.updatePrtFcltyRentMngtPrmisnCancel(levReqestInfo);

         resultCode = 0;
 		 resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.execCancel"); //승낙이 정상적으로 취소되었습니다.

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }
	*/

    /**
     * 항만시설사용 승낙을 한다.
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamUpdatePrtFcltyRentMngtPrmisn.do")
    public @ResponseBody Map updatePrtFcltyRentMngtPrmisn(
     	   @ModelAttribute("gamPrtFcltyRentMngtVO") GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO,
     	   BindingResult bindingResult)
            throws Exception {

     	 Map map = new HashMap();
     	 Map paramMap = new HashMap();
         String resultMsg = "";
         int resultCode = 1;

         LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

         System.out.println("##################################### 승낙시작!!");
         System.out.println("##################################### getPrtAtCode => " +  gamPrtFcltyRentMngtVO.getPrtAtCode());
         System.out.println("##################################### getMngYear => " +  gamPrtFcltyRentMngtVO.getMngYear());
         System.out.println("##################################### getMngNo => " +  gamPrtFcltyRentMngtVO.getMngNo());
         System.out.println("##################################### getMngCnt => " +  gamPrtFcltyRentMngtVO.getMngCnt());
         System.out.println("##################################### getChrgeKnd => " +  gamPrtFcltyRentMngtVO.getChrgeKnd());

         //prtAtCode:항코드, mngYear:관리번호, mngNo:관리 순번, mngCnt:관리 횟수, chrgeKnd: 요금종류
         paramMap.put("prtAtCode", gamPrtFcltyRentMngtVO.getPrtAtCode());
         paramMap.put("mngYear", gamPrtFcltyRentMngtVO.getMngYear());
         paramMap.put("mngNo", gamPrtFcltyRentMngtVO.getMngNo());
         paramMap.put("mngCnt", gamPrtFcltyRentMngtVO.getMngCnt());
         paramMap.put("regUsr", loginVO.getId());
         paramMap.put("chrgeKnd", gamPrtFcltyRentMngtVO.getChrgeKnd());

         System.out.println("##################################### paramMap => " + paramMap);

         //승낙 서비스 클래스 호출
         //gamAssetsUsePermMngtService.confirmAssetsRentUsePerm(paramMap); //승낙

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
     * 항만시설사용 승낙취소(허가취소)를 한다.
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamUpdatePrtFcltyRentMngtPrmisnCancel.do")
    public @ResponseBody Map updatePrtFcltyRentMngtPrmisnCancel(
     	   @ModelAttribute("gamPrtFcltyRentMngtVO") GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO,
     	   BindingResult bindingResult)
            throws Exception {

     	 Map map = new HashMap();
     	 Map paramMap = new HashMap();
         String resultMsg = "";
         int resultCode = 1;

         LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

         System.out.println("##################################### 승낙취소시작!!");
         System.out.println("##################################### getPrtAtCode => " +  gamPrtFcltyRentMngtVO.getPrtAtCode());
         System.out.println("##################################### getMngYear => " +  gamPrtFcltyRentMngtVO.getMngYear());
         System.out.println("##################################### getMngNo => " +  gamPrtFcltyRentMngtVO.getMngNo());
         System.out.println("##################################### getMngCnt => " +  gamPrtFcltyRentMngtVO.getMngCnt());

         //prtAtCode:항코드, mngYear:관리번호, mngNo:관리 순번, mngCnt:관리 횟수, chrgeKnd: 요금종류
         paramMap.put("prtAtCode", gamPrtFcltyRentMngtVO.getPrtAtCode());
         paramMap.put("mngYear", gamPrtFcltyRentMngtVO.getMngYear());
         paramMap.put("mngNo", gamPrtFcltyRentMngtVO.getMngNo());
         paramMap.put("mngCnt", gamPrtFcltyRentMngtVO.getMngCnt());
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
    @RequestMapping(value="/oper/gnrl/gamSelectPrtFcltyRentMngtFileList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectPrtFcltyRentMngtFileList(GamPrtFcltyRentMngtVO searchVO) throws Exception {

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

		//파일목록
    	totalCnt = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtFileListTotCnt(searchVO);
    	List assetFileList = gamPrtFcltyRentMngtService.selectPrtFcltyRentMngtFileList(searchVO);

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
     * @param gamPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrl/gamUpdatePrtFcltyRentMngtComment.do")
    public @ResponseBody Map updatePrtFcltyRentMngtComment(
    	   @ModelAttribute("gamPrtFcltyRentMngtVO") GamPrtFcltyRentMngtVO gamPrtFcltyRentMngtVO,
    	   BindingResult bindingResult)
           throws Exception {

    	Map map = new HashMap();
        String resultMsg  = "";
        String updateFlag = "";
        int resultCode = 1;

        if( gamPrtFcltyRentMngtVO.getMngYear() == null || "".equals(gamPrtFcltyRentMngtVO.getMngYear()) ) {
        	updateFlag = "N";
        } else {
        	updateFlag = "Y";
        }

    	if("Y".equals(updateFlag)) {
	        gamPrtFcltyRentMngtService.updatePrtFcltyRentMngtComment(gamPrtFcltyRentMngtVO);

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

	/**
     * 과태료 처분 팝업화면을 로딩한다.
     *
     * @param gamAssetDisUseMngtVO
     * @param model the model
     * @return "/ygpa/gam/asset/GamPopupAssetRentPrmisn"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/gnrl/popupPenaltyFee.do")
    String showAssetDisUse(Map mngItem, ModelMap model) throws Exception {

		model.addAttribute("mngItem", mngItem);

    	return "/ygpa/gam/oper/gnrl/GamPopupPrtFcltyPenaltyFee";
   }

}