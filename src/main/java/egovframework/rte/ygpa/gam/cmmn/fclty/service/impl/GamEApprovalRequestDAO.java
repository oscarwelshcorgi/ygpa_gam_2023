package egovframework.rte.ygpa.gam.cmmn.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;

@Repository("gamEApprovalRequestDAO")
public class GamEApprovalRequestDAO extends YGPAAbstractDAO {

	public String sendEApprovalAssetUsePermRequest(Map<String, Object> vo) {
        return (String)insert("gamEApprovalRequestDAO.sendEApprovalAssetUsePermRequest_S", vo);
	}

    public void updateAssetRentSanctn(Map vo) throws Exception {
        update("gamEApprovalRequestDAO.updateAssetRentSanctn_S", vo);
    }

    public void updatePrtFcltyUseSanctn(Map vo) throws Exception {
        update("gamEApprovalRequestDAO.updatePrtFcltyUseSanctn_S", vo);
    }

    public List selectAssetRentList(Map vo) throws Exception {
        return list("gamEApprovalRequestDao.selectAssetRent_S", vo);
    }
}
