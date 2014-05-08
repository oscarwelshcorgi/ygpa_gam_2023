/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.web;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamEApprovalRequestService;


/**
 * 결재 요청 모듈
 * @author EUNSUNGJ
 * @since 2014. 3. 14.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 14.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Controller
public class GamEAprovalRequestController {

	@Resource(name="gamEApprovalRequestService")
	private GamEApprovalRequestService gamEApprovalRequestService;


	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;

	/**
     * 결재 처리 모듈 호출
     * @param searchOpt
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cmmn/fclty/openApprovalRequest.do")
    String showAssetsCd(@RequestParam Map<String, Object> approvalOpt, ModelMap model) throws Exception {
    	String tNo;
    	model.addAttribute("searchOpt", approvalOpt);

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("resultCode", 1);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("fail.common.login"));
    	}
    	else {
    		LoginVO loginVo = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    		String eaCode = (String)approvalOpt.get("type");
    		String acCode =  eaCode.substring(2,3);
    		eaCode = eaCode.substring(0, 2);
    		approvalOpt.put("empCd", loginVo.getEmplNo());
			approvalOpt.put("regIp", loginVo.getIp());
	    	if("AR".equals(eaCode)) {
	    		// 자산 임대 관리 결재 데이터를 준비 한다.
    			if("U".equals(acCode)) {	// 사용승낙
    	    		approvalOpt.put("docId", "GAM00001");
    	    		approvalOpt.put("misKeyValue", "GAMARCNF");
    	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
    	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.usecnfirm.docName"));
        			tNo=gamEApprovalRequestService.sendEApprovalAssetUsePermRequest(approvalOpt);	// 결재 데이터를 전송한다
    			}
    			else {
    	    		approvalOpt.put("docId", "GAM00002");
    	    		approvalOpt.put("misKeyValue", "GAMARNTC");
    	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
    	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.nticissueAr.docName"));
        			tNo=gamEApprovalRequestService.sendEApprovalNticIssueRequest(approvalOpt);	// 결재 데이터를 전송한다
    			}
	    	}
	    	else if("PF".equals(eaCode)) {
	    		// 항만시설 임대관리 사용 승낙
	    		// 자산 임대 관리 결재 데이터를 준비 한다.
    			if("U".equals(acCode)) {	// 사용승낙
    	    		approvalOpt.put("docId", "GAM00001");
    	    		approvalOpt.put("misKeyValue", "GAMPFCNF");
    	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
    	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.usecnfirm.docName"));
        			tNo=gamEApprovalRequestService.sendEApprovalAssetUsePermRequest(approvalOpt);	// 결재 데이터를 전송한다
    			}
    			else {
    	    		approvalOpt.put("docId", "GAM00002");
    	    		approvalOpt.put("misKeyValue", "GAMPFNTC");
    	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
    	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.nticissueAr.docName"));
        			tNo=gamEApprovalRequestService.sendEApprovalNticIssueRequest(approvalOpt);	// 결재 데이터를 전송한다
    			}
			}
	    	else if("HT".equals(eaCode)) {
	    		// 항만시설 임대관리 사용 승낙
	    		// 자산 임대 관리 결재 데이터를 준비 한다.
    			if("U".equals(acCode)) {	// 사용승낙
    	    		approvalOpt.put("docId", "GAM00001");
    	    		approvalOpt.put("misKeyValue", "GAMHTCNF");
    	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
    	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.usecnfirm.docName"));
        			tNo=gamEApprovalRequestService.sendEApprovalAssetUsePermRequest(approvalOpt);	// 결재 데이터를 전송한다
    			}
    			else {
    	    		approvalOpt.put("docId", "GAM00002");
    	    		approvalOpt.put("misKeyValue", "GAMHTNTC");
    	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
    	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.nticissueAr.docName"));
        			tNo=gamEApprovalRequestService.sendEApprovalNticIssueRequest(approvalOpt);	// 결재 데이터를 전송한다
    			}
	    	}
	    	else if("CN".equals(eaCode)) {
	    		// 항만시설 임대관리 사용 승낙
	    		// 자산 임대 관리 결재 데이터를 준비 한다.
    			if("U".equals(acCode)) {	// 사용승낙
    	    		approvalOpt.put("docId", "GAM00001");
    	    		approvalOpt.put("misKeyValue", "GAMCNCNF");
    	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
    	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.usecnfirm.docName"));
        			tNo=gamEApprovalRequestService.sendEApprovalAssetUsePermRequest(approvalOpt);	// 결재 데이터를 전송한다
    			}
    			else {
    	    		approvalOpt.put("docId", "GAM00002");
    	    		approvalOpt.put("misKeyValue", "GAMCNNTC");
    	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
    	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.nticissueAr.docName"));
        			tNo=gamEApprovalRequestService.sendEApprovalNticIssueRequest(approvalOpt);	// 결재 데이터를 전송한다
    			}
	    	}
	    	else if("TP".equals(eaCode)) {
	    		// 항만시설 임대관리 사용 승낙
	    		// 자산 임대 관리 결재 데이터를 준비 한다.
    			if("U".equals(acCode)) {	// 사용승낙
    	    		approvalOpt.put("docId", "GAM00001");
    	    		approvalOpt.put("misKeyValue", "GAMTPCNF");
    	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
    	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.usecnfirm.docName"));
        			tNo=gamEApprovalRequestService.sendEApprovalAssetUsePermRequest(approvalOpt);	// 결재 데이터를 전송한다
    			}
    			else {
    	    		approvalOpt.put("docId", "GAM00002");
    	    		approvalOpt.put("misKeyValue", "GAMTPNTC");
    	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
    	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.nticissueAr.docName"));
        			tNo=gamEApprovalRequestService.sendEApprovalNticIssueRequest(approvalOpt);	// 결재 데이터를 전송한다
    			}
	    	}
	    	else if("CC".equals(eaCode)) {
	    		// 공컨 임대관리 사용 승낙
	    		// 자산 임대 관리 결재 데이터를 준비 한다.
    			if("U".equals(acCode)) {	// 사용승낙
    	    		approvalOpt.put("docId", "GAM00001");
    	    		approvalOpt.put("misKeyValue", "GAMCCCNF");
    	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
    	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.usecnfirm.docName"));
    				tNo=gamEApprovalRequestService.sendEApprovalAssetUsePermRequest(approvalOpt);	// 결재 데이터를 전송한다
    			}
    			else {
    	    		approvalOpt.put("docId", "GAM00002");
    	    		approvalOpt.put("misKeyValue", "GAMCCNTC");
    	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
    	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.nticissueAr.docName"));
        			tNo=gamEApprovalRequestService.sendEApprovalNticIssueRequest(approvalOpt);	// 결재 데이터를 전송한다
    			}
	    	}
	    	else if("MC".equals(eaCode)) {
	    		// 마린센터 임대관리 사용 승낙
	    		// 자산 임대 관리 결재 데이터를 준비 한다.
    			if("U".equals(acCode)) {	// 사용승낙
    	    		approvalOpt.put("docId", "GAM00001");
    	    		approvalOpt.put("misKeyValue", "GAMMCCNF");
    	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
    	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.usecnfirm.docName"));
        			tNo=gamEApprovalRequestService.sendEApprovalAssetUsePermRequest(approvalOpt);	// 결재 데이터를 전송한다
    			}
    			else {
    	    		approvalOpt.put("docId", "GAM00002");
    	    		approvalOpt.put("misKeyValue", "GAMMCNTC");
    	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
    	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.nticissueAr.docName"));
        			tNo=gamEApprovalRequestService.sendEApprovalNticIssueRequest(approvalOpt);	// 결재 데이터를 전송한다
    			}
	    	}
	    	else {
	    		model.addAttribute("resultCode", 2);
	    		model.addAttribute("resultMsg", egovMessageSource.getMessage("gam.ea.usecnfirm.errortype"));

	    		return "ygpa/gam/cmmn/fclty/GamOpenApprovalRequest";
	    	}
    		model.addAttribute("tNo", tNo);
    		model.addAttribute("emplyrNo", loginVo.getEmplNo());
    		model.addAttribute("resultCode", 0);
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("gam.ea.usecnfirm.sending"));
    	}

    	return "ygpa/gam/cmmn/fclty/GamOpenApprovalRequest";
    	}

}
