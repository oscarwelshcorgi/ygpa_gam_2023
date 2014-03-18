/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


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

    /**
     * 결재 처리 모듈 호출
     * @param searchOpt
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cmmn/fclty/openApprovalRequest.do")
    String showAssetsCd(Map<String, Object> searchOpt, ModelMap model) throws Exception {
    	model.addAttribute("searchOpt", searchOpt);
    	// 결재 중으로 결재 상태를 변경하고 전자결재를 위한 자산 임대 데이터를 출력한다.
    	return "/ygpa/gam/cmmn/fclty/GamOpenApprovalRequest";
    }

}
