/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.web;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngDetailVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngVo;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtVO;
import egovframework.rte.ygpa.gam.soc.service.GamSocAgentService;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseService;
import egovframework.rte.ygpa.gam.soc.service.GamSocCmmUseVO;



/**
 *
 * @author Lee
 * @since 2014. 10. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 22.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamFcltsMngFeeMngController {

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

    @Resource(name = "gamFcltsMngFeeMngService")
    private GamFcltsMngFeeMngService gamFcltsMngFeeMngService;


    @RequestMapping(value="/mngFee/gamFcltsMngFeeMng.do")
	public String indexMain(@RequestParam("window_id") String windowId, ModelMap model) throws Exception {

    	//login정보
    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    	int year = Integer.parseInt(EgovDateUtil.getToday().substring(0,4));
		List yearList = new ArrayList();
		Map yearMap = null;
    	for( int i = year ; i >= year-10 ; i-- ) {
			yearMap = new HashMap();
			yearMap.put("code", i);
			yearMap.put("codeNm", i+"년");

			yearList.add(yearMap);
		}

		List monList = new ArrayList();
		Map monMap;
		for(int i=1; i < 13; i++){
			NumberFormat month = new DecimalFormat("00");
			String moni = month.format(i);
			monMap = new HashMap();
			monMap.put("code", moni);
			monMap.put("codeNm", moni+"월");
			monList.add(monMap);
		}

		model.addAttribute("monList", monList);
		model.addAttribute("yearsList", yearList);
		model.addAttribute("thisyear", year);
		model.addAttribute("windowId", windowId);

    	return "/ygpa/gam/mngFee/GamFcltsMngFeeMng";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/mngFee/gamSelectFcltsMngFeeMng.do" , method=RequestMethod.POST)
    @ResponseBody Map gamSelectFcltsMngFeeMngList(GamFcltsMngFeeMngVo searchVO) throws Exception {
    	//@ModelAttribute("gamFcltsMngFeeMngForm")
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

		totalCnt = gamFcltsMngFeeMngService.selectFcltsMngFeeMngListTotCnt(searchVO);
    	List resultList = gamFcltsMngFeeMngService.selectFcltsMngFeeMngList(searchVO);



    	map.put("resultCode", 0);
    	map.put("totalCount", totalCnt);
    	map.put("resultList", resultList);

    	return map;
    }

    @RequestMapping(value="/mngFee/gamSaveFcltsMngFeeMng.do")
	@ResponseBody Map<String, Object> gamSaveFcltsMngFeeMng(@RequestParam Map<String, Object> dataList) throws Exception {

    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    	GamFcltsMngFeeMngDetailVo saveDetailVO = new GamFcltsMngFeeMngDetailVo();

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


    		//항만시설사용저장
    		GamFcltsMngFeeMngVo saveVO= new GamFcltsMngFeeMngVo();
			saveVO.setMngMt(form.get("mngMt"));
			saveVO.setMngFeeJobSe(form.get("mngFeeJobSe"));
			saveVO.setMngFeeSj(form.get("mngFeeSj"));
			saveVO.setFcltyMngFee(form.get("fcltyMngFee"));
			saveVO.setElctyFee(form.get("elctyFee"));
			saveVO.setWaterFee(form.get("waterFee"));
			saveVO.setGasFee(form.get("gasFee"));
			saveVO.setEnvFee(form.get("envFee"));
			saveVO.setMngTotalFee(form.get("mngTotalFee"));
    		saveVO.setRegUsr((loginVO.getId()));
/*
    		log.debug("########### form.get(payMth) => "+form.get("payMth"));
    		log.debug("########### saveVO.setPayMth(.get(payMth) => "+saveVO.getPayMth());
*/
    		if( form.get("cmd") != null && "insert".equals(form.get("cmd")) ) {
    		/*
    		if( form.get("mngYear") == null || "".equals(form.get("mngYear")) ) {
    			GamPrtFcltyRentMngtVO keyVO = new GamPrtFcltyRentMngtVO();
    			keyVO = gamFcltsMngFeeMngService.selectPrtFcltyRentMngtMaxKey(saveVO);

    			saveVO.setMngYear(keyVO.getMngYear());
    			saveVO.setMngNo(keyVO.getMngNo());
    			saveVO.setMngCnt(keyVO.getMngCnt());
    			saveVO.setReqstSeCd("1");   //신청구분코드   (1:최초, 2:연장, 3	:변경, 4	:취소) 이게 맞나?
    			saveVO.setRegUsr(loginVO.getId());
*/
    			gamFcltsMngFeeMngService.insertFcltsMngFeeMng(saveVO);
/*
    			//항만시설사용 상세저장을 위한 키
    			saveDetailVO.setDetailPrtAtCode(form.get("prtAtCode"));
        		saveDetailVO.setDetailMngYear(keyVO.getMngYear());
        		saveDetailVO.setDetailMngNo(keyVO.getMngNo());
        		saveDetailVO.setDetailMngCnt(keyVO.getMngCnt());
*/
    		} else {
/*
    	        gamFcltsMngFeeMngService.updatePrtFcltyRentMngt(saveVO);

    			//항만시설사용 상세저장을 위한 키
    			saveDetailVO.setDetailPrtAtCode(form.get("prtAtCode"));
        		saveDetailVO.setDetailMngYear(form.get("mngYear"));
        		saveDetailVO.setDetailMngNo(form.get("mngNo"));
        		saveDetailVO.setDetailMngCnt(form.get("mngCnt"));
*/
    		}

    		//항만시설사용 상세저장
    		for( int i = 0 ; i < insertList.size() ; i++ ) {
    			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ insertList.get(i) String => " + insertList.get(i));

    			Map resultMap = insertList.get(i);

    			GamFcltsMngFeeMngDetailVo insertDetailVO = new GamFcltsMngFeeMngDetailVo();


    			insertDetailVO.setMngMt(resultMap.get("mngMt").toString());
    			insertDetailVO.setMngFeeJobSe(resultMap.get("mngFeeJobSe").toString());
    			insertDetailVO.setEntrpscd(resultMap.get("entrpscd").toString());
    			insertDetailVO.setMngFee(resultMap.get("mngFee").toString());
    			insertDetailVO.setElctyFee(resultMap.get("elctyFee").toString());
    			insertDetailVO.setWaterFee(resultMap.get("waterFee").toString());
    			insertDetailVO.setGasFee(resultMap.get("gasFee").toString());
    			insertDetailVO.setMngTotalFee(resultMap.get("mngTotalFee").toString());
    			insertDetailVO.setUsageAr(resultMap.get("usageAr").toString());
    			insertDetailVO.setRegUsr(loginVO.getId());
    			insertDetailVO.setUpdUsr(loginVO.getId());

    			gamFcltsMngFeeMngService.insertFcltsMngFeeMngDetail(insertDetailVO);
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

//    			gamFcltsMngFeeMngService.updatePrtFcltyRentMngtDetail(updateDetailVO);
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

//    			gamFcltsMngFeeMngService.deletePrtFcltyRentMngtDetail2(deleteDetailVO);
    		}
/*
    		//파일저장
    		for( int i = 0 ; i < insertFileList.size() ; i++ ) {
    			Map resultMap = insertFileList.get(i);

    			GamPrtFcltyRentMngtVO insertFileVO = new GamPrtFcltyRentMngtVO();

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

    			gamFcltsMngFeeMngService.insertPrtFcltyRentMngtFile(insertFileVO);
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

    			gamFcltsMngFeeMngService.updatePrtFcltyRentMngtFile(updateFileVO);
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

    			gamFcltsMngFeeMngService.deletePrtFcltyRentMngtPhotoSingle(deleteFileVO);
    		}

    		//총사용료, 총면적, 총사용기간 조회
    		GamPrtFcltyRentMngtVO paramVO = new GamPrtFcltyRentMngtVO();
    		paramVO.setPrtAtCode(saveDetailVO.getDetailPrtAtCode());
    		paramVO.setMngYear(saveDetailVO.getDetailMngYear());
    		paramVO.setMngNo(saveDetailVO.getDetailMngNo());
    		paramVO.setMngCnt(saveDetailVO.getDetailMngCnt());

    		GamPrtFcltyRentMngtVO updRentVO = new GamPrtFcltyRentMngtVO();
    		updRentVO = gamFcltsMngFeeMngService.selectPrtFcltyRentMngtCurrRenewInfo(paramVO);

    		if( updRentVO != null ) {
    			updRentVO.setPrtAtCode(paramVO.getPrtAtCode());
    			updRentVO.setMngYear(paramVO.getMngYear());
    			updRentVO.setMngNo(paramVO.getMngNo());
    			updRentVO.setMaxMngCnt(paramVO.getMngCnt());

    			//총사용료, 총면적, 총사용기간 업데이트
    			gamFcltsMngFeeMngService.updatePrtFcltyRentMngtRenewInfo(updRentVO);

    			//부두코드 가져오기
    			GamPrtFcltyRentMngtVO quaycdVO = new GamPrtFcltyRentMngtVO();
    			quaycdVO = gamFcltsMngFeeMngService.selectPrtFcltyRentMngtDetailQuaycd(updRentVO);

    		}
*/
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


    @RequestMapping(value="/mngFee/gamInsertFcltsMngFeeMng.do")
	@ResponseBody Map<String, Object> insertFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo)	throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
		try {
			gamFcltsMngFeeMngVo.setRegUsr((String)user.getId());
			gamFcltsMngFeeMngService.insertFcltsMngFeeMng(gamFcltsMngFeeMngVo);

	    	map.put("resultCode", 0);			// return ok
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

    	return map;
    }

    @RequestMapping(value="/mngFee/gamDeleteFcltsMngFeeMng.do")
	@ResponseBody Map<String, Object> deleteFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo)	throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	Map<String, Object> map = new HashMap<String, Object>();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
	        map.put("resultCode", 1);
    		map.put("resultMsg", egovMessageSource.getMessage("fail.common.login"));
        	return map;
    	}
    	/*
		CmmnDetailCode vo = gamFcltsMngFeeMngService.selectCmmnDetailCodeDetail(cmmnDetailCode);

		if(vo != null){
			map.put("resultCode", 1);
			map.put("resultMsg", "이미 등록된 차량 번호입니다.");
            return map;
    	}
		*/
		try {
			gamFcltsMngFeeMngVo.setRegUsr((String)user.getId());
			gamFcltsMngFeeMngService.deleteFcltsMngFeeMng(gamFcltsMngFeeMngVo);

	    	map.put("resultCode", 0);			// return ok
			map.put("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resultCode", 1);
			map.put("resultMsg", egovMessageSource.getMessage("fail.common.insert"));
		}

    	return map;
    }

}
