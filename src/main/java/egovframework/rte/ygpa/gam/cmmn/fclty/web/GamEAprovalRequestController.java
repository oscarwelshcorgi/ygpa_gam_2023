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
	    	if("ARUC".equals(approvalOpt.get("type"))) {
	    		// 자산 임대 관리 결재 데이터를 준비 한다.
	    		approvalOpt.put("empCd", loginVo.getEmplNo());
	    		approvalOpt.put("docId", "GAMAR01");
	    		approvalOpt.put("misKeyValue", "GAMARCNF");
	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.usecnfirm.docName"));
    			approvalOpt.put("regIp", loginVo.getIp());

	    		tNo=gamEApprovalRequestService.sendEApprovalAssetUsePermRequest(approvalOpt);	// 결재 데이터를 전송한다
	    		model.addAttribute("tNo", tNo);
	    		model.addAttribute("emplyrNo", loginVo.getEmplNo());
	    		model.addAttribute("resultCode", 0);
	    		model.addAttribute("resultMsg", egovMessageSource.getMessage("gam.ea.usecnfirm.sending"));
	    	}
	    	else if("PFUC".equals(approvalOpt.get("type"))) {
	    		// 항만시설 임대관리 사용 승낙
	    		// 자산 임대 관리 결재 데이터를 준비 한다.
	    		approvalOpt.put("empCd", loginVo.getEmplNo());
	    		approvalOpt.put("docId", "GAMPF01");
	    		approvalOpt.put("misKeyValue", "GAMPFCNF");
	    		approvalOpt.put("mCnt", 1);	// 문서 갯수
	    		approvalOpt.put("docNm", egovMessageSource.getMessage("gam.ea.usecnfirm.docName"));
	    		approvalOpt.put("regIp", loginVo.getIp());

	    		tNo=gamEApprovalRequestService.sendEApprovalAssetUsePermRequest(approvalOpt);	// 결재 데이터를 전송한다
	    		model.addAttribute("tNo", tNo);
	    		model.addAttribute("emplyrNo", loginVo.getEmplNo());
	    		model.addAttribute("resultCode", 0);
	    		model.addAttribute("resultMsg", egovMessageSource.getMessage("gam.ea.usecnfirm.sending"));
	    	}
	    	else {
	    		model.addAttribute("resultCode", 2);
	    		model.addAttribute("resultMsg", egovMessageSource.getMessage("gam.ea.usecnfirm.errortype"));
	    	}
    	}

    	return "ygpa/gam/cmmn/fclty/GamOpenApprovalRequest";
    	}

}
