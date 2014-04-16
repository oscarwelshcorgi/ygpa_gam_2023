package egovframework.rte.ygpa.gam.cmmn.sms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.cmmn.sms.service.GamSmsMngtService;
import egovframework.rte.ygpa.gam.cmmn.sms.service.GamSmsMngtVO;

@Service("gamSmsMngtService")
public class GamSmsMngtServiceImpl extends AbstractServiceImpl
		implements GamSmsMngtService {

    @Resource(name="gamSmsMngtDAO")
    private GamSmsMngtDAO gamSmsMngtDAO;

    protected Log log = LogFactory.getLog(this.getClass());

    public List selectSmsMngtList(GamSmsMngtVO searchVO) throws Exception {
		return gamSmsMngtDAO.selectSmsMngtList(searchVO);
	}

	public int selectSmsMngtListTotCnt(GamSmsMngtVO searchVO) throws Exception {
		return gamSmsMngtDAO.selectSmsMngtListTotCnt(searchVO);
	}
	
    public String smsRetransmit(GamSmsMngtVO searchVO) throws Exception {
		return gamSmsMngtDAO.smsRetransmit(searchVO);
	}
}
