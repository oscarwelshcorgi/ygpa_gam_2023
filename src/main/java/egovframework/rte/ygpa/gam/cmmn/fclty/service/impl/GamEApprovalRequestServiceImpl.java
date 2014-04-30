package egovframework.rte.ygpa.gam.cmmn.fclty.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.impl.GamAssetRentMngtDao;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamEApprovalRequestService;

@Service("gamEApprovalRequestService")
public class GamEApprovalRequestServiceImpl extends AbstractServiceImpl
		implements GamEApprovalRequestService {

    @Resource(name="gamEApprovalRequestDAO")
    private GamEApprovalRequestDAO gamEApprovalRequestDAO;

	@Override
	public String sendEApprovalAssetUsePermRequest(Map<String, Object> vo)
			throws Exception {
		String tNo=gamEApprovalRequestDAO.selectEApprovalTno();
		vo.put("tNo", tNo);
		gamEApprovalRequestDAO.sendEApprovalAssetUsePermRequest(vo);	// 결재 정보를 입력 한다.
		vo.put("sanctnSttus", "2");
		gamEApprovalRequestDAO.updateAssetRentSanctn(vo);	// 결재 요청 중 코드 삽입
		return tNo;
	}

}
