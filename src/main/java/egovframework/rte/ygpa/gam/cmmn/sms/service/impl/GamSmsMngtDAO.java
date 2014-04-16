package egovframework.rte.ygpa.gam.cmmn.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.cmmn.sms.service.GamSmsMngtVO;

/**
 * @Class Name : GamSmsMngtDao.java
 * @Description : 고객군들통계(포트미스정보) DAO Class
 * @Modification Information
 *
 * @author 김종민
 * @since 2014-04-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("gamSmsMngtDAO")
public class GamSmsMngtDAO extends YGPAAbstractDAO {

	/**
	 * SMS 관리 목록 조회
	 * @param  searchVO - SMS 관리 목록 조회 정보가 담긴 VO
	 * @return SMS 관리 목록
	 * @exception Exception
	 */
	public List selectSmsMngtList(GamSmsMngtVO searchVO) {
		return list("gamSmsMngtDAO.selectSmsMngtList_S", searchVO);
	}
	
	/**
	 * SMS 관리 목록 자료 수
	 * @param  searchVO - SMS 관리 목록 조회 정보가 담긴 VO
	 * @return SMS 관리 목록 자료 수
	 * @exception Exception
	 */
	public int selectSmsMngtListTotCnt(GamSmsMngtVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamSmsMngtDAO.selectSmsMngtListTotCnt_S", searchVO);
	}
	
	/**
	 * SMS 재전송 데이터 생성
	 * @param  createVO - SMS 재전송 데이터 생성 정보가 담긴 VO
	 * @return error message
	 * @exception Exception
	 */
	public String smsRetransmit(GamSmsMngtVO createVO) {
		return (String)insert("gamSmsMngtDAO.smsRetransmit_S", createVO);
	}
}
