/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticDefaultVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldRcivProcVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 15.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamPopupHtldRcivProcDao")
public class GamPopupHtldRcivProcDao extends YGPAAbstractDAO {
	/**
	 * 지로 수납된 자료인지 조회
	 * @param GamHtldRentNticDefaultVO
	 * @return String 'Y, N'
	 * @exception Exception
	 */
	public String selectCheckOcrResult(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return (String) selectByPk("gamPopupHtldRcivProcDao.selectCheckOcrResult_S", searchVO);
	}
	
	/**
	 * 수납처리정보 조회
	 * @param GamHtldRentNticDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldNticDtlsRcivInfo(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamPopupHtldRcivProcDao.selectHtldNticDtlsRcivInfo_S", searchVO);
	}
	
	/**
	 * HTLD_NTIC_DTLS_F 의 수납정보 수정
	 * @param GamPopupHtldRcivProcVO
	 * @return 
	 * @exception Exception
	 */
	public void updateNticDtlsRcivInfo(GamPopupHtldRcivProcVO vo) throws Exception {
		update("gamPopupHtldRcivProcDao.updateNticDtlsRcivInfo_S", vo);
	}

	/**
	 * REV_COLL_F 의 수납정보 수정
	 * @param GamPopupHtldRcivProcVO
	 * @return 
	 * @exception Exception
	 */
	public void updateRevCollRcivInfo(GamPopupHtldRcivProcVO vo) throws Exception {
		update("gamPopupHtldRcivProcDao.updateRevCollRcivInfo_S", vo);
	}
}
