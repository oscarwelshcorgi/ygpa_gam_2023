package egovframework.rte.ygpa.gam.cmmn.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.cmmn.sms.service.GamSmsMngtVO;


@Repository("gamSmsMngtDAO")
public class GamSmsMngtDAO extends YGPAAbstractDAO {
	public List selectSmsMngtList(GamSmsMngtVO vo) {
		return list("gamSmsMngtDAO.selectSmsMngtList_S", vo);
	}
	
	public int selectSmsMngtListTotCnt(GamSmsMngtVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamSmsMngtDAO.selectSmsMngtListTotCnt_S", vo);
	}
	
	public String smsRetransmit(GamSmsMngtVO vo) {
		return (String)insert("gamSmsMngtDAO.smsRetransmit_S", vo);
	}
}
