/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 17.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamHtldRentNticSchedulingDao")
public class GamHtldRentNticSchedulingDao extends YGPAAbstractDAO {
	/**
	 * REV_COLL_F 의 납부상태를 HTLD_NTIC_DTLS_F에 업데이트
	 */
	public void updateHtldRentNticIssueStatusRefreshRevcoll() throws Exception {
		update("gamHtldRentNticSchedulingDao.updateHtldRentNticIssueStatusRefreshRevcoll_D", null); 
	}

	/**
	 * UNPAID_F 의 납부상태를 HTLD_NTIC_DTLS_F에 업데이트
	 */
	public void updateHtldRentNticIssueStatusRefreshUnpaid() throws Exception {
		update("gamHtldRentNticSchedulingDao.updateHtldRentNticIssueStatusRefreshUnpaid_D", null); 
	}
	
}
