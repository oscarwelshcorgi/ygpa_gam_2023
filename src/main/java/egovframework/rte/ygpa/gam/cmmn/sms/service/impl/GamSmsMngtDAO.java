package egovframework.rte.ygpa.gam.cmmn.sms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamEntrpsChargerFVO;

@Repository("gamSmsMngtDAO")
public class GamSmsMngtDAO extends YGPAAbstractDAO {

	public String sendSmsMessage(Map<String, Object> vo) {
        return (String)insert("gamSmsMngtDAO.sendSmsMessage_S", vo);
	}

	public String sendSmsMessageServer(Map<String, Object> vo) {
        return (String)insert("gamSmsMngtDAO.sendSmsMessageServer_S", vo);
	}

	public EgovMap selectChargerInfo(Map<String,Object> vo) throws Exception{
		return (EgovMap) selectByPk("gamSmsMngtDAO.selectChargerInfo_S", vo);
	}

	public EgovMap selectEmployInfo(String emplyrId) throws Exception{
		return (EgovMap) selectByPk("gamSmsMngtDAO.selectEmployInfo_S", emplyrId);
	}

}
