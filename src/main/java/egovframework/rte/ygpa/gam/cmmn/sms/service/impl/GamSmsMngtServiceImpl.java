package egovframework.rte.ygpa.gam.cmmn.sms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.impl.GamAssetRentMngtDao;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamEApprovalRequestService;
import egovframework.rte.ygpa.gam.cmmn.sms.service.GamSmsMngtService;

@Service("gamSmsMngtService")
public class GamSmsMngtServiceImpl extends AbstractServiceImpl
		implements GamSmsMngtService {

    @Resource(name="gamSmsMngtDAO")
    private GamSmsMngtDAO gamSmsMngtDAO;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.sms.service.GamSmsMngtService#sendSmsMessage(java.util.Map)
	 */
	@Override
	public void sendSmsMessage(Map<String, Object> vo) throws Exception {
		gamSmsMngtDAO.sendSmsMessage(vo);
	}

}
