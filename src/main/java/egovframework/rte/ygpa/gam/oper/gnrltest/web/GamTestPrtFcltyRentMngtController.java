package egovframework.rte.ygpa.gam.oper.gnrltest.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentFeePaySttusMngtVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetDisUseMngtVO;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamTestAssetsUsePermMngtService;
import egovframework.rte.ygpa.gam.cmmn.service.GamFileServiceVo;
import egovframework.rte.ygpa.gam.cmmn.service.GamFileUploadUtil;

/**
 * @Class Name : GamTestPrtFcltyRentMngtController.java
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
public class GamTestPrtFcltyRentMngtController {

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

    @Resource(name = "gamTestPrtFcltyRentMngtService")
    private GamTestPrtFcltyRentMngtService gamTestPrtFcltyRentMngtService;

    @Resource(name = "gamTestAssetsUsePermMngtService")
    private GamTestAssetsUsePermMngtService gamTestAssetsUsePermMngtService;

    @Resource(name="gamRentFileIdGnrService")
    EgovTableIdGnrService gamRentFileIdGnrService;

    /**
     * 항만시설사용관리 화면을 로딩한다.
     *
     * @param windowId
     * @param model the model
     * @return "/ygpa/gam/oper/gnrltest/GamTestPrtFcltyRentMngt"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/gnrltest/gamTestPrtFcltyRentMngt.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

		//login정보
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		//공시지가정보
		//GamTestPrtFcltyRentMngtVO gvo = new GamTestPrtFcltyRentMngtVO();
//		List olnlpList = gamTestPrtFcltyRentMngtService.selectOlnlpInfo();

		//코픽스 이자율
		List cofixList = gamTestPrtFcltyRentMngtService.selectCofixInfo();

		//현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져와서 해당하는 코픽스 이자율 가져오기.
		GamTestPrtFcltyRentMngtVO cofixVO = new GamTestPrtFcltyRentMngtVO();
		GamTestPrtFcltyRentMngtVO cofixResultVO = new GamTestPrtFcltyRentMngtVO();

		cofixVO.setcYear(EgovDateUtil.getToday().substring(0,6));
		cofixVO = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtBeforeQuarterInfo(cofixVO);

		if( cofixVO != null ) {
			cofixResultVO = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtCofixInfo(cofixVO);

			if( cofixResultVO == null ) {
				cofixResultVO = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtCofixInfoMax(cofixVO);
			}

			if( cofixResultVO != null && cofixResultVO.getBlceStdrIntrrate() != null ) {
				model.addAttribute("blceStdrIntrrate", cofixResultVO.getBlceStdrIntrrate());
			}

			if( cofixResultVO != null && cofixResultVO.getBlceStdrIntrrateShow() != null ) {
				model.addAttribute("blceStdrIntrrateShow", cofixResultVO.getBlceStdrIntrrateShow());
			}
		}

//		model.addAttribute("olnlpList", olnlpList);
		model.addAttribute("cofixList", cofixList);
		model.addAttribute("loginOrgnztId", loginVO.getOrgnztId());
		model.addAttribute("loginUserId", loginVO.getId());
		model.addAttribute("currentDateStr", EgovDateUtil.formatDate(EgovDateUtil.getToday(), "-"));
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/oper/gnrltest/GamTestPrtFcltyRentMngt";
    }

	/**
     * 항만시설사용목록을 조회한다.
     *
     * @param searchVO
     * @return map
     * @throws Exception the exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrltest/gamSelectPrtFcltyRentMngtList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectPrtFcltyRentMngtList(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {

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

		//항만시설사용목록
    	totalCnt = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtListTotCnt(searchVO);
    	List assetRentList = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtList(searchVO);

    	paginationInfo.setTotalRecordCount(totalCnt);
        searchVO.setPageSize(paginationInfo.getLastPageNoOnPageList());

    	//총면적, 총사용료
    	GamTestPrtFcltyRentMngtVO resultSum = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtSum(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", assetRentList);
    	map.put("searchOption", searchVO);
    	map.put("sumGrAr", resultSum.getSumGrAr());
    	map.put("sumGrFee", resultSum.getSumGrFee());
    	map.put("sumGrRdcxptFee", resultSum.getSumGrRdcxptFee());

    	return map;
    }

		@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrltest/selectRentMasterInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map selectRentDetailInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {

		int totalCnt, page, firstIndex;
    	Map map = new HashMap();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}

		// 항만시설사용상세리스트 및 총건수
		Map result = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtMasterInfo(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("result", result);
    	map.put("searchOption", searchVO);

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
    @RequestMapping(value="/oper/gnrltest/gamSelectPrtFcltyRentMngtDetailList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectPrtFcltyRentMngtDetailList(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {

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

		// 항만시설사용상세리스트 및 총건수
		totalCnt = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtDetailListTotCnt(searchVO);
		List resultList = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtDetailList(searchVO);

    	map.put("resultCode", 0);	// return ok
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);
    	map.put("searchOption", searchVO);

    	return map;
    }

	/**
     * 항만시설,상세,첨부파일을 저장한다.
     * @param String
     * @param gamTestPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
	@RequestMapping(value="/oper/gnrltest/gamSavePrtFcltyRentMngt.do")
	@ResponseBody Map<String, Object> savePrtFcltyRentMngt(@RequestParam Map<String, Object> dataList) throws Exception {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		GamTestPrtFcltyRentMngtDetailVO saveDetailVO = new GamTestPrtFcltyRentMngtDetailVO();

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
    		GamTestPrtFcltyRentMngtVO saveVO= new GamTestPrtFcltyRentMngtVO();
			saveVO.setPrtAtCode(form.get("prtAtCode"));
			saveVO.setDeptcd(loginVO.getOrgnztId());
			saveVO.setMngYear(form.get("mngYear"));
			saveVO.setMngNo(form.get("mngNo"));
			saveVO.setMngCnt(form.get("mngCnt"));
			saveVO.setEntrpscd(form.get("entrpscd"));
			saveVO.setFrstReqstDt(form.get("frstReqstDt"));
			saveVO.setReqstDt(form.get("reqstDt"));
			saveVO.setPayMth(form.get("payMth"));
			saveVO.setTaxtSe(form.get("taxtSe"));
			saveVO.setNticMth(form.get("nticMth"));
			saveVO.setRm(form.get("rm"));
			saveVO.setCmt(form.get("cmt"));
			saveVO.setChargerNo(form.get("chargerNo"));	// 담당자 번호
			saveVO.setPayinstIntrrate(form.get("payinstIntrrate"));
    		saveVO.setUpdUsr(loginVO.getId());

    		log.debug("########### form.get(payMth) => "+form.get("payMth"));
    		log.debug("########### saveVO.setPayMth(.get(payMth) => "+saveVO.getPayMth());

    		//if( form.get("cmd") != null && "insert".equals(form.get("cmd")) ) {
    		if( form.get("mngYear") == null || "".equals(form.get("mngYear")) ) {
    			GamTestPrtFcltyRentMngtVO keyVO = new GamTestPrtFcltyRentMngtVO();
    			keyVO = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtMaxKey(saveVO);

    			saveVO.setMngYear(keyVO.getMngYear());
    			saveVO.setMngNo(keyVO.getMngNo());
    			saveVO.setMngCnt(keyVO.getMngCnt());
    			saveVO.setReqstSeCd("1");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
    			saveVO.setRegUsr(loginVO.getId());

    			gamTestPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(saveVO);

    			//항만시설사용 상세저장을 위한 키
    			saveDetailVO.setDetailPrtAtCode(form.get("prtAtCode"));
        		saveDetailVO.setDetailMngYear(keyVO.getMngYear());
        		saveDetailVO.setDetailMngNo(keyVO.getMngNo());
        		saveDetailVO.setDetailMngCnt(keyVO.getMngCnt());
    		} else {
    			//saveVO.setReqstSeCd("3");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?

    	        gamTestPrtFcltyRentMngtService.updatePrtFcltyRentMngt(saveVO);

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

    			GamTestPrtFcltyRentMngtDetailVO insertDetailVO = new GamTestPrtFcltyRentMngtDetailVO();

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
    			insertDetailVO.setApplcPrice(resultMap.get("applcPrice").toString());
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
    			insertDetailVO.setUsagePdChk(resultMap.get("usagePdChk").toString());
    			//insertDetailVO.setQuayCd(resultMap.get("quayCd").toString());

    			insertDetailVO.setRegUsr(loginVO.getId());
    			insertDetailVO.setUpdUsr(loginVO.getId());

    			/*saveDetailVO.setDetailPrtAtCode(form.get("prtAtCode"));
        		saveDetailVO.setDetailMngYear(keyVO.getMngYear());
        		saveDetailVO.setDetailMngNo(keyVO.getMngNo());
        		saveDetailVO.setDetailMngCnt(keyVO.getMngCnt()); */

    			//resultMap.get("gisAssetsPrtAtCode")
    			gamTestPrtFcltyRentMngtService.insertPrtFcltyRentMngtDetail(insertDetailVO);
    		}

    		for( int i = 0 ; i < updateList.size() ; i++ ) {
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ updateList.get(i) String => " + updateList.get(i));

    			Map resultMap = updateList.get(i);

    			GamTestPrtFcltyRentMngtDetailVO updateDetailVO = new GamTestPrtFcltyRentMngtDetailVO();
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
    			updateDetailVO.setApplcPrice(resultMap.get("applcPrice").toString());
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
    			updateDetailVO.setUsagePdChk(resultMap.get("usagePdChk").toString());
//    			updateDetailVO.setQuayCd(resultMap.get("quayCd").toString());

    			updateDetailVO.setRegUsr(loginVO.getId());
    			updateDetailVO.setUpdUsr(loginVO.getId());

    			gamTestPrtFcltyRentMngtService.updatePrtFcltyRentMngtDetail(updateDetailVO);
    		}

    		for( int i = 0 ; i < deleteList.size() ; i++ ) {
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ deleteList.get(i) String => " + deleteList.get(i));

    			Map resultMap = deleteList.get(i);

    			GamTestPrtFcltyRentMngtDetailVO deleteDetailVO = new GamTestPrtFcltyRentMngtDetailVO();
    			deleteDetailVO.setAssetsUsageSeq(resultMap.get("assetsUsageSeq").toString());
    			deleteDetailVO.setPrtAtCode(resultMap.get("prtAtCode").toString());
    			deleteDetailVO.setMngYear(resultMap.get("mngYear").toString());
    			deleteDetailVO.setMngNo(resultMap.get("mngNo").toString());
    			deleteDetailVO.setMngCnt(resultMap.get("mngCnt").toString());

    			gamTestPrtFcltyRentMngtService.deletePrtFcltyRentMngtDetail2(deleteDetailVO);
    		}

    		//파일저장
    		for( int i = 0 ; i < insertFileList.size() ; i++ ) {
    			Map resultMap = insertFileList.get(i);

    			GamTestPrtFcltyRentMngtVO insertFileVO = new GamTestPrtFcltyRentMngtVO();

    			insertFileVO.setPrtAtCode(saveDetailVO.getDetailPrtAtCode());
    			insertFileVO.setMngYear(saveDetailVO.getDetailMngYear());
    			insertFileVO.setMngNo(saveDetailVO.getDetailMngNo());
    			insertFileVO.setMngCnt(saveDetailVO.getDetailMngCnt());

    			insertFileVO.setPhotoSj((String)resultMap.get("photoSj"));
    			insertFileVO.setFilenmLogic((String)resultMap.get("filenmLogic"));
    			insertFileVO.setFilenmPhysicl((String)resultMap.get("filenmPhysicl"));
    			insertFileVO.setShotDt((String)resultMap.get("shotDt"));
    			insertFileVO.setPhotoDesc((String)resultMap.get("photoDesc"));
    			insertFileVO.setRegUsr(loginVO.getId());

    			System.out.println("############################################### insertFileVO => " + insertFileVO);

    			gamTestPrtFcltyRentMngtService.insertPrtFcltyRentMngtFile(insertFileVO);
    		}

    		for( int i = 0 ; i < updateFileList.size() ; i++ ) {
    			Map resultMap = updateFileList.get(i);

    			GamTestPrtFcltyRentMngtVO updateFileVO = new GamTestPrtFcltyRentMngtVO();

    			updateFileVO.setPhotoSeq(resultMap.get("photoSeq").toString());
    			updateFileVO.setPrtAtCode(resultMap.get("prtAtCode").toString());
    			updateFileVO.setMngYear(resultMap.get("mngYear").toString());
    			updateFileVO.setMngNo(resultMap.get("mngNo").toString());
    			updateFileVO.setMngCnt(resultMap.get("mngCnt").toString());

    			updateFileVO.setPhotoSj(resultMap.get("photoSj").toString());
    			updateFileVO.setShotDt(resultMap.get("shotDt").toString());
    			updateFileVO.setPhotoDesc(resultMap.get("photoDesc").toString());

    			System.out.println("############################################### updateFileVO => " + updateFileVO);

    			gamTestPrtFcltyRentMngtService.updatePrtFcltyRentMngtFile(updateFileVO);
    		}

    		for( int i = 0 ; i < deleteFileList.size() ; i++ ) {
    			Map resultMap = deleteFileList.get(i);

    			GamTestPrtFcltyRentMngtVO deleteFileVO = new GamTestPrtFcltyRentMngtVO();

    			deleteFileVO.setPhotoSeq(resultMap.get("photoSeq").toString());
    			deleteFileVO.setPrtAtCode(resultMap.get("prtAtCode").toString());
    			deleteFileVO.setMngYear(resultMap.get("mngYear").toString());
    			deleteFileVO.setMngNo(resultMap.get("mngNo").toString());
    			deleteFileVO.setMngCnt(resultMap.get("mngCnt").toString());

    			System.out.println("############################################### deleteFileVO => " + deleteFileVO);

    			gamTestPrtFcltyRentMngtService.deletePrtFcltyRentMngtPhotoSingle(deleteFileVO);
    		}

    		//총사용료, 총면적, 총사용기간 조회
    		GamTestPrtFcltyRentMngtVO paramVO = new GamTestPrtFcltyRentMngtVO();
    		paramVO.setPrtAtCode(saveDetailVO.getDetailPrtAtCode());
    		paramVO.setMngYear(saveDetailVO.getDetailMngYear());
    		paramVO.setMngNo(saveDetailVO.getDetailMngNo());
    		paramVO.setMngCnt(saveDetailVO.getDetailMngCnt());

    		GamTestPrtFcltyRentMngtVO updRentVO = new GamTestPrtFcltyRentMngtVO();
    		updRentVO = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtCurrRenewInfo(paramVO);

    		if( updRentVO != null ) {
    			updRentVO.setPrtAtCode(paramVO.getPrtAtCode());
    			updRentVO.setMngYear(paramVO.getMngYear());
    			updRentVO.setMngNo(paramVO.getMngNo());
    			updRentVO.setMaxMngCnt(paramVO.getMngCnt());

    			//총사용료, 총면적, 총사용기간 업데이트
    			gamTestPrtFcltyRentMngtService.updatePrtFcltyRentMngtRenewInfo(updRentVO);

    			//부두코드 가져오기
    			GamTestPrtFcltyRentMngtVO quaycdVO = new GamTestPrtFcltyRentMngtVO();
    			quaycdVO = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtDetailQuaycd(updRentVO);

    			//부두코드 업데이트
//    			if( quaycdVO == null || quaycdVO.getQuayCd() == null || "".equals(quaycdVO.getQuayCd()) ) {
//    				quaycdVO = new GamTestPrtFcltyRentMngtVO();
//    				quaycdVO.setPrtAtCode(paramVO.getPrtAtCode());
//    				quaycdVO.setMngYear(paramVO.getMngYear());
//    				quaycdVO.setMngNo(paramVO.getMngNo());
//    				quaycdVO.setMaxMngCnt(paramVO.getMngCnt());
//    			}
//
//    			gamTestPrtFcltyRentMngtService.updatePrtFcltyRentMngtQuaycd(quaycdVO);
    		}

    		resultCode = 0;
        	resultMsg  = egovMessageSource.getMessage("success.common.merge");

    	} catch (IOException i) {
    		
    	} catch (Exception e) {


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
     * @param gamTestPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamInsertPrtFcltyRentMngtFirst.do")
    public @ResponseBody Map insertPrtFcltyRentMngtFirst(
    	   @RequestParam("cmd") String cmd,
    	   @ModelAttribute("gamTestPrtFcltyRentMngtVO") GamTestPrtFcltyRentMngtVO gamTestPrtFcltyRentMngtVO,
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
	        beanValidator.validate(gamTestPrtFcltyRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamTestPrtFcltyRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamTestPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(gamTestPrtFcltyRentMngtVO);

			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */

    	if("insert".equals(cmd)) {
	    	//확인후 변경혀라~~
	    	gamTestPrtFcltyRentMngtVO.setReqstSeCd("1");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamTestPrtFcltyRentMngtVO.setRegUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)
	    	gamTestPrtFcltyRentMngtVO.setUpdUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)
	    	//gamTestPrtFcltyRentMngtVO.setDeptcd("A001");   //부서코드 (세션?)

	        gamTestPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(gamTestPrtFcltyRentMngtVO);

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
     * @param gamTestPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamInsertPrtFcltyRentMngtRenew.do")
    public @ResponseBody Map insertPrtFcltyRentMngtRenew(
    	   @ModelAttribute("gamTestPrtFcltyRentMngtVO") GamTestPrtFcltyRentMngtVO gamTestPrtFcltyRentMngtVO,
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
        try {
	    	GamTestPrtFcltyRentMngtVO resultVO = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtMaxNo(gamTestPrtFcltyRentMngtVO);

	    	if( gamTestPrtFcltyRentMngtVO.getMngCnt().equals(resultVO.getMaxMngCnt()) ) {
	    		//키 같고 max관리번호가 같으면 연장신청 등록
	    		gamTestPrtFcltyRentMngtVO.setRegUsr(loginVo.getId());
	    		gamTestPrtFcltyRentMngtService.insertPrtFcltyRentMngtRenew(gamTestPrtFcltyRentMngtVO);

	    		resultCode = 0; // return ok
	    		resultMsg  = egovMessageSource.getMessage("success.common.insert");
	    	} else {
	    		resultCode = 1; // return fail
	    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.reject");
	    	}
        } catch (IOException i) {
        	
        } catch(Exception e) {


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
     * @param gamTestPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamUpdatePrtFcltyRentMngt.do")
    public @ResponseBody Map updatePrtFcltyRentMngtFirst(
    	   @RequestParam("cmd") String cmd,
    	   @ModelAttribute("gamTestPrtFcltyRentMngtVO") GamTestPrtFcltyRentMngtVO gamTestPrtFcltyRentMngtVO,
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
	    	gamTestPrtFcltyRentMngtVO.setReqstSeCd("3");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
	    	gamTestPrtFcltyRentMngtVO.setUpdUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)
	    	//gamTestPrtFcltyRentMngtVO.setDeptcd("A001");   //부서코드 (세션?)

	        gamTestPrtFcltyRentMngtService.updatePrtFcltyRentMngt(gamTestPrtFcltyRentMngtVO);

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
     * @param gamTestPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamDeletePrtFcltyRentMngt.do")
    public @ResponseBody Map deletePrtFcltyRentMngt(
    	   //@RequestParam("cmd") String cmd,
    	   @ModelAttribute("gamTestPrtFcltyRentMngtDetailVO") GamTestPrtFcltyRentMngtVO gamTestPrtFcltyRentMngtVO,
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

        try {
	        if( EgovStringUtil.isEmpty(gamTestPrtFcltyRentMngtVO.getPrmisnYn()) || gamTestPrtFcltyRentMngtVO.getPrmisnYn().equals("N") ) { //허가여부가 'N'이면 삭제가능
	        	deleteFlag = "Y";
	        } else {
	        	/*
	        	resultLevReqestCnt = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtLevReqestCnt(gamTestPrtFcltyRentMngtVO); //징수의뢰 정보 카운트

	        	if( gamTestPrtFcltyRentMngtVO.getPrmisnYn().equals("Y") && resultLevReqestCnt == 0 ) { //허가여부가 Y이고 징수의뢰테이블에 정보가 없으면 삭제가능
	            	deleteFlag = "Y";
	            }
	            */
	        	deleteFlag = "N";
	        }

	    	if("Y".equals(deleteFlag)) {
		        gamTestPrtFcltyRentMngtService.deletePrtFcltyRentMngt(gamTestPrtFcltyRentMngtVO);

		        resultCode = 0; // return ok
		        resultMsg  = egovMessageSource.getMessage("success.common.delete");
	    	} else {
	    		resultCode = 1; // return fail
	    		resultMsg  = egovMessageSource.getMessage("gam.asset.rent.err.delete");
	    	}
        } catch (IOException i) {
        	
        }
        catch(Exception e) {


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
     * @param gamTestPrtFcltyRentMngtDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamInsertPrtFcltyRentMngtDetail.do")
    public @ResponseBody Map insertPrtFcltyRentMngtDetail(
    	   @RequestParam("detailCmd") String detailCmd,
    	   @ModelAttribute("gamTestPrtFcltyRentMngtDetailVO") GamTestPrtFcltyRentMngtDetailVO gamTestPrtFcltyRentMngtDetailVO,
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
	        beanValidator.validate(gamTestPrtFcltyRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamTestPrtFcltyRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamTestPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(gamTestPrtFcltyRentMngtVO);

			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */

        GamTestPrtFcltyRentMngtVO gamTestPrtFcltyRentMngtVO = new GamTestPrtFcltyRentMngtVO();
        gamTestPrtFcltyRentMngtVO.setPrtAtCode(gamTestPrtFcltyRentMngtDetailVO.getDetailPrtAtCode());
        gamTestPrtFcltyRentMngtVO.setMngYear(gamTestPrtFcltyRentMngtDetailVO.getDetailMngYear());
        gamTestPrtFcltyRentMngtVO.setMngNo(gamTestPrtFcltyRentMngtDetailVO.getDetailMngNo());
        gamTestPrtFcltyRentMngtVO.setMngCnt(gamTestPrtFcltyRentMngtDetailVO.getDetailMngCnt());

        //임대정보 조회후 승낙여부 체크
        GamTestPrtFcltyRentMngtVO rentPrmisnInfo = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtPrmisnInfo(gamTestPrtFcltyRentMngtVO);



        log.debug("######################################## rentPrmisnInfo.getPrmisnYn() => " + rentPrmisnInfo.getPrmisnYn());

        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 등록가능
        	if("insert".equals(detailCmd)) {
    	    	//확인후 변경혀라~~
    	    	gamTestPrtFcltyRentMngtDetailVO.setRegUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)
    	    	gamTestPrtFcltyRentMngtDetailVO.setUpdUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)

    	        gamTestPrtFcltyRentMngtService.insertPrtFcltyRentMngtDetail(gamTestPrtFcltyRentMngtDetailVO);

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
     * @param gamTestPrtFcltyRentMngtDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamUpdatePrtFcltyRentMngtDetail.do")
    public @ResponseBody Map updatePrtFcltyRentMngtDetail(
    	   @RequestParam("detailCmd") String detailCmd,
    	   @ModelAttribute("gamTestPrtFcltyRentMngtDetailVO") GamTestPrtFcltyRentMngtDetailVO gamTestPrtFcltyRentMngtDetailVO,
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
    	log.debug("######################################## gamTestPrtFcltyRentMngtVO.getDetailPrtAtCode() => " + gamTestPrtFcltyRentMngtDetailVO.getDetailPrtAtCode());

    	/*
        if("insert".equals(cmd)) {
	        beanValidator.validate(gamTestPrtFcltyRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamTestPrtFcltyRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamTestPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(gamTestPrtFcltyRentMngtVO);

			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */

    	GamTestPrtFcltyRentMngtVO gamTestPrtFcltyRentMngtVO = new GamTestPrtFcltyRentMngtVO();
        gamTestPrtFcltyRentMngtVO.setPrtAtCode(gamTestPrtFcltyRentMngtDetailVO.getDetailPrtAtCode());
        gamTestPrtFcltyRentMngtVO.setMngYear(gamTestPrtFcltyRentMngtDetailVO.getDetailMngYear());
        gamTestPrtFcltyRentMngtVO.setMngNo(gamTestPrtFcltyRentMngtDetailVO.getDetailMngNo());
        gamTestPrtFcltyRentMngtVO.setMngCnt(gamTestPrtFcltyRentMngtDetailVO.getDetailMngCnt());

        //임대정보 조회후 승낙여부 체크
        GamTestPrtFcltyRentMngtVO rentPrmisnInfo = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtPrmisnInfo(gamTestPrtFcltyRentMngtVO);

        if(rentPrmisnInfo!=null) {	// 취약점 점검 수정 KJC 2015-04-20
	        if( EgovStringUtil.isEmpty(rentPrmisnInfo.getPrmisnYn()) || !rentPrmisnInfo.getPrmisnYn().equals("Y") ) { //임대정보가 승낙이 되지 않았을 경우에만 수정가능
		    	if("modify".equals(detailCmd)) {
			    	gamTestPrtFcltyRentMngtDetailVO.setUpdUsr(loginVo.getId()); //등록자 (세션 로그인 아이디)

			        gamTestPrtFcltyRentMngtService.updatePrtFcltyRentMngtDetail(gamTestPrtFcltyRentMngtDetailVO);

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
        }
        else {
        	resultCode = -1; // return fail
    		resultMsg  = egovMessageSource.getMessage("fail.common.msg");
        }

    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

		return map;
    }

    /**
     * 항만시설사용 상세를 삭제한다.
     * @param gamTestPrtFcltyRentMngtDetailVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamDeletePrtFcltyRentMngtDetail.do")
    public @ResponseBody Map deletePrtFcltyRentMngtDetail(
    	   @ModelAttribute("gamTestPrtFcltyRentMngtDetailVO") GamTestPrtFcltyRentMngtDetailVO gamTestPrtFcltyRentMngtDetailVO,
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
	        beanValidator.validate(gamTestPrtFcltyRentMngtVO, bindingResult);
			if (bindingResult.hasErrors()){
				map.put("resultCode", 1);			// return error
				map.put("resultMsg", "입력 값에 오류가 있습니다.");
				map.put("resultObject", bindingResult.getAllErrors());
				return map;
			}
			//if(gamTestPrtFcltyRentMngtVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
	    	gamTestPrtFcltyRentMngtService.insertPrtFcltyRentMngtFirst(gamTestPrtFcltyRentMngtVO);

			resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        */

    	gamTestPrtFcltyRentMngtService.deletePrtFcltyRentMngtDetail2(gamTestPrtFcltyRentMngtDetailVO);

        resultCode = 0; // return ok
		resultMsg  = egovMessageSource.getMessage("success.common.delete");

    	map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);

		return map;
    }

    /**
     * 승낙 팝업화면을 로딩한다.
     *
     * @param gamTestPrtFcltyRentMngtLevReqestVO
     * @param model the model
     * @return "/ygpa/gam/oper/gnrltest/GamPopupPrtFcltyRentMngtPrmisn"
     * @throws Exception the exception
     */
	@RequestMapping(value="/oper/gnrltest/popup/showPrtFcltyRentMngtPrmisn.do")
    String showEntrpsInfo(GamTestPrtFcltyRentMngtVO gamTestPrtFcltyRentMngtLevReqestVO, ModelMap model) throws Exception {

		List chrgeKndCdList = gamTestPrtFcltyRentMngtService.selectChargeKndList(gamTestPrtFcltyRentMngtLevReqestVO);

		model.addAttribute("gamTestPrtFcltyRentMngtInfo", gamTestPrtFcltyRentMngtLevReqestVO);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);

    	return "/ygpa/gam/oper/gnrltest/GamPopupPrtFcltyRentMngtPrmisn";
    }

    /**
     * 항만시설사용 승낙을 한다.
     * @param gamTestPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamUpdatePrtFcltyRentMngtPrmisn.do")
    public @ResponseBody Map updatePrtFcltyRentMngtPrmisn(
     	   @ModelAttribute("gamTestPrtFcltyRentMngtVO") GamTestPrtFcltyRentMngtVO gamTestPrtFcltyRentMngtVO,
     	   BindingResult bindingResult)
            throws Exception {

     	 Map map = new HashMap();
     	 Map paramMap = new HashMap();
         String resultMsg = "";
         int resultCode = 1;

     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
 	        map.put("resultCode", 1);
     		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
         	return map;
     	}

         LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

         //prtAtCode:항코드, mngYear:관리번호, mngNo:관리 순번, mngCnt:관리 횟수, chrgeKnd: 요금종류
         paramMap.put("prtAtCode", gamTestPrtFcltyRentMngtVO.getPrtAtCode());
         paramMap.put("mngYear", gamTestPrtFcltyRentMngtVO.getMngYear());
         paramMap.put("mngNo", gamTestPrtFcltyRentMngtVO.getMngNo());
         paramMap.put("mngCnt", gamTestPrtFcltyRentMngtVO.getMngCnt());
         paramMap.put("regUsr", loginVO.getId());
         paramMap.put("deptcd", loginVO.getOrgnztId());
         paramMap.put("chrgeKnd", gamTestPrtFcltyRentMngtVO.getChrgeKnd());
         paramMap.put("taxtSe", gamTestPrtFcltyRentMngtVO.getTaxtSe());

         //승낙 서비스 클래스 호출
         //gamTestAssetsUsePermMngtService.confirmAssetsRentUsePerm(paramMap); //승낙

         if(!paramMap.containsKey("prtAtCode") || !paramMap.containsKey("mngYear") || !paramMap.containsKey("mngNo") || !paramMap.containsKey("mngCnt")) {
             resultCode = 2;
        	 resultMsg = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
         }
         else {
        	 gamTestAssetsUsePermMngtService.confirmAssetsRentUsePerm(paramMap);

	         resultCode = 0;
	 		 resultMsg  = egovMessageSource.getMessage("gam.asset.rent.prmisn.exec");
         }

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }

	/**
     * 항만시설사용 승낙취소(허가취소) 가 가능 한지 체크한다.
     * @param gamTestPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/checkPrtFcltyRentMngtPrmisnCancel.do")
    public @ResponseBody Map checkPrtFcltyRentMngtPrmisnCancel(
     	   @ModelAttribute("gamTestPrtFcltyRentMngtVO") GamTestPrtFcltyRentMngtVO gamTestPrtFcltyRentMngtVO)
            throws Exception {

     	 Map map = new HashMap();
     	 Map paramMap = new HashMap();
         String resultMsg = "";
         int resultCode = 1;

     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
 	        map.put("resultCode", 1);
     		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
         	return map;
     	}

     	int noticeNo=gamTestPrtFcltyRentMngtService.selectRentFeeNoticeListCount(gamTestPrtFcltyRentMngtVO);

         if(noticeNo!=0) {
             resultCode = noticeNo;
        	 resultMsg = egovMessageSource.getMessage("gam.asset.rent.prmisnCalcel.notice");
         }
         else {
             resultCode = 0;
             resultMsg = "";
         }

     	 map.put("resultCode", resultCode);
         map.put("resultMsg", resultMsg);

 		return map;
     }

    /**
     * 항만시설사용 승낙취소(허가취소)를 한다.
     * @param gamTestPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamUpdatePrtFcltyRentMngtPrmisnCancel.do")
    public @ResponseBody Map updatePrtFcltyRentMngtPrmisnCancel(
     	   @ModelAttribute("gamTestPrtFcltyRentMngtVO") GamTestPrtFcltyRentMngtVO gamTestPrtFcltyRentMngtVO,
     	   BindingResult bindingResult)
            throws Exception {

     	 Map map = new HashMap();
     	 Map paramMap = new HashMap();
         String resultMsg = "";
         int resultCode = 1;

     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
 	        map.put("resultCode", 1);
     		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
         	return map;
     	}

         LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

         System.out.println("##################################### 승낙취소시작!!");
         System.out.println("##################################### getPrtAtCode => " +  gamTestPrtFcltyRentMngtVO.getPrtAtCode());
         System.out.println("##################################### getMngYear => " +  gamTestPrtFcltyRentMngtVO.getMngYear());
         System.out.println("##################################### getMngNo => " +  gamTestPrtFcltyRentMngtVO.getMngNo());
         System.out.println("##################################### getMngCnt => " +  gamTestPrtFcltyRentMngtVO.getMngCnt());

         //prtAtCode:항코드, mngYear:관리번호, mngNo:관리 순번, mngCnt:관리 횟수, chrgeKnd: 요금종류
         paramMap.put("prtAtCode", gamTestPrtFcltyRentMngtVO.getPrtAtCode());
         paramMap.put("mngYear", gamTestPrtFcltyRentMngtVO.getMngYear());
         paramMap.put("mngNo", gamTestPrtFcltyRentMngtVO.getMngNo());
         paramMap.put("mngCnt", gamTestPrtFcltyRentMngtVO.getMngCnt());
         paramMap.put("regUsr", loginVO.getId());

         System.out.println("##################################### paramMap => " + paramMap);

         if(!paramMap.containsKey("prtAtCode") || !paramMap.containsKey("mngYear") || !paramMap.containsKey("mngNo") || !paramMap.containsKey("mngCnt")) {
             resultCode = 2;
        	 resultMsg = egovMessageSource.getMessage("gam.asset.rent.err.exceptional");
         }
         else {
        	 gamTestAssetsUsePermMngtService.cancelAssetsRentUsePerm(paramMap);

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
    @RequestMapping(value="/oper/gnrltest/gamSelectPrtFcltyRentMngtFileList.do", method=RequestMethod.POST)
	public @ResponseBody Map selectPrtFcltyRentMngtFileList(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {

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

		//파일목록
    	totalCnt = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtFileListTotCnt(searchVO);
    	List assetFileList = gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtFileList(searchVO);

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
     * @param gamTestPrtFcltyRentMngtVO
     * @param bindingResult
     * @return map
     * @throws Exception
     */
    @RequestMapping(value="/oper/gnrltest/gamUpdatePrtFcltyRentMngtComment.do")
    public @ResponseBody Map updatePrtFcltyRentMngtComment(
    	   @ModelAttribute("gamTestPrtFcltyRentMngtVO") GamTestPrtFcltyRentMngtVO gamTestPrtFcltyRentMngtVO,
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

    	if( gamTestPrtFcltyRentMngtVO.getMngYear() == null || "".equals(gamTestPrtFcltyRentMngtVO.getMngYear()) ) {
        	updateFlag = "N";
        } else {
        	updateFlag = "Y";
        }

    	if("Y".equals(updateFlag)) {
    		gamTestPrtFcltyRentMngtVO.setUpdUsr(loginVo.getId());
	        gamTestPrtFcltyRentMngtService.updatePrtFcltyRentMngtComment(gamTestPrtFcltyRentMngtVO);

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
	@RequestMapping(value="/oper/gnrltest/popupPenaltyFee.do")
    String showAssetDisUse(Map mngItem, ModelMap model) throws Exception {

		model.addAttribute("mngItem", mngItem);

    	return "/ygpa/gam/oper/gnrltest/GamPopupPrtFcltyPenaltyFee";
   }

	/**
	 * 추가고지 팝업화면을 로딩한다. (요금종류 코드를 fee_tp_f 를 사용 하도록 수정 한다.)
	 * @param gamTestPrtFcltyRentMngtLevReqestVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/oper/gnrltest/popupLevReqestAdit.do")
  String popupLevReqestAdit(GamTestPrtFcltyRentMngtVO gamTestPrtFcltyRentMngtVO, ModelMap model) throws Exception {

		List chrgeKndCdList = gamTestPrtFcltyRentMngtService.selectChargeKndList(gamTestPrtFcltyRentMngtVO);

		model.addAttribute("gamTestPrtFcltyRentMngtInfo", gamTestPrtFcltyRentMngtVO);
		model.addAttribute("chrgeKndCdList", chrgeKndCdList);

  	return "ygpa/gam/oper/gnrltest/GamPopupPrtFcltyRentMngtLevReqestAdit";
  }


	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/oper/gnrltest/selectPrtFcltyRentMngtListExcel.do", method=RequestMethod.POST)
    @ResponseBody ModelAndView selectErpAssetCodeListExcel(@RequestParam Map<String, Object> excelParam) throws Exception {
		Map map = new HashMap();
		List header;
		ObjectMapper mapper = new ObjectMapper();

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    		return new ModelAndView("gridExcelView", "gridResultMap", map);
    	}


    	// 환경설정
    	/** EgovPropertyService */
    	GamTestPrtFcltyRentMngtVO searchVO= new GamTestPrtFcltyRentMngtVO();

        header = mapper.readValue((String)excelParam.get("header"),
			    new TypeReference<List<HashMap<String,String>>>(){});

        excelParam.remove("header");	// 파라미터에서 헤더를 삭제 한다.

		// 조회 조건
		searchVO = mapper.convertValue(excelParam, GamTestPrtFcltyRentMngtVO.class);

		searchVO.setFirstIndex(0);
		searchVO.setLastIndex(9999);
		searchVO.setRecordCountPerPage(9999);

		/** List Data */
//    	int totCnt = erpAssetCdService.selectErpAssetCdListTotCnt(searchVO);

    	List gamAssetList =  gamTestPrtFcltyRentMngtService.selectPrtFcltyRentMngtList(searchVO);

    	map.put("resultList", gamAssetList);
    	map.put("header", header);

    	return new ModelAndView("gridExcelView", "gridResultMap", map);
    }

	// 파일 처리 (자산 임대 공통 - 리퀘스트 패스만 변경 하여 사용)
    @RequestMapping(value="/oper/gnrltest/uploadRentAttachFile.do", method=RequestMethod.POST)
    public @ResponseBody Map uploadFile(HttpServletRequest request) throws Exception {
		Map map = new HashMap();
		String uploadPath = EgovProperties.getProperty("assetsRent.fileStorePath");
		try {
			List<GamFileServiceVo> list = GamFileUploadUtil.uploadFiles(request, uploadPath, gamRentFileIdGnrService);

			map.put("resultCode", "0");
			map.put("result", list);
		}
		catch(IOException i) {
			map.put("resultCode", "-1");
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.upload"));
		}
		catch(Exception e) {
			map.put("resultCode", "-1");
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.upload"));
		}
		return map;
	}

    @RequestMapping("/oper/gnrltest/getRentAttachFile.do")
    public void getImage(final HttpServletRequest request, HttpServletResponse response) throws Exception {
		GamFileServiceVo gamFileServiceVo = new GamFileServiceVo();
		String uploadPath = EgovProperties.getProperty("assetsRent.fileStorePath");

		gamFileServiceVo.setPhyscalFileNm((String)request.getParameter("physicalFileNm"));

		GamFileUploadUtil.downloadImage(request, response, uploadPath, gamFileServiceVo);
    }

    @RequestMapping("/oper/gnrltest/downloadRentAttachFile.do")
    public void getDownload(final HttpServletRequest request, HttpServletResponse response) throws Exception {
		GamFileServiceVo gamFileServiceVo = new GamFileServiceVo();
		String uploadPath = EgovProperties.getProperty("assetsRent.fileStorePath");

		gamFileServiceVo.setPhyscalFileNm((String)request.getParameter("physicalFileNm"));
		gamFileServiceVo.setLogicalFileNm((String)request.getParameter("logicalFileNm"));
		GamFileUploadUtil.downloadFile(request, response, uploadPath, gamFileServiceVo);
    }
}