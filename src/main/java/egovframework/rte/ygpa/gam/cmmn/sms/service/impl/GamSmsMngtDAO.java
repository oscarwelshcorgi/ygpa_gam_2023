package egovframework.rte.ygpa.gam.cmmn.sms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;

@Repository("gamSmsMngtDAO")
public class GamSmsMngtDAO extends YGPAAbstractDAO {

	public String sendSmsMessage(Map<String, Object> vo) {
        return (String)insert("gamSmsMngtDAO.sendSmsMessage_S", vo);
	}
}
