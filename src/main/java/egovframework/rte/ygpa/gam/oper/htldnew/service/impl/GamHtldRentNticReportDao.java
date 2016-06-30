/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticDefaultVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticInfoVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 19.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 19.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamHtldRentNticReportDao")
public class GamHtldRentNticReportDao extends YGPAAbstractDAO {
	/**
	 * 고지서 출력 마스터 정보 조회
	 * @param GamHtldRentMngDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectNticPrintMaster(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamHtldRentNticReportDao.selectNticPrintMaster_S", searchVO);
	}

	/**
	 * 연체 고지서 출력 마스터 정보 조회
	 * @param GamHtldRentMngDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectArrrgNticPrintMaster(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamHtldRentNticReportDao.selectArrrgNticPrintMaster_S", searchVO);
	}
	
	/**
	 * 출력용 고지 상세 리스트 조회
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<?> selectNticIssueList(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return list("gamHtldRentNticReportDao.selectNticIssueList_D", searchVO);
	}

	/**
	 * 고지일자와 시스템 일자와 일수 차이
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public int selectNticDaysDiff(GamHtldRentNticInfoVO nticInfoVO) throws Exception {
		return (Integer)selectByPk("gamHtldRentNticReportDao.selectNticDaysDiff_S", nticInfoVO);
	}

	/**
	 * 예약 출력 상태 변경
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public void updateReservePrintState(GamHtldRentNticInfoVO nticInfoVO) throws Exception {
		update("gamHtldRentNticReportDao.updateReservePrintState_S", nticInfoVO);
	}

	/**
	 * 즉시 출력 상태 변경 (HTLD_NTIC_DTLS_F)
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public void updateImmediatelyPrintStateNticDtls(GamHtldRentNticInfoVO nticInfoVO) throws Exception {
		update("gamHtldRentNticReportDao.updateImmediatelyPrintStateNticDtls_S", nticInfoVO);
	}
	
	/**
	 * 즉시 출력 상태 변경 (REV_COLL_F)
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public void updateImmediatelyPrintStateRevColl(GamHtldRentNticInfoVO nticInfoVO) throws Exception {
		update("gamHtldRentNticReportDao.updateImmediatelyPrintStateRevColl_S", nticInfoVO);
	}

	/**
	 * 연체고지 출력 상태 변경
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public void updateArrrgPrintState(GamHtldRentNticInfoVO nticInfoVO) throws Exception {
		update("gamHtldRentNticReportDao.updateArrrgPrintState_S", nticInfoVO);
	}

	/**
	 * 원고지 마스터 (산출내역서 엑셀용)
	 * @param GamHtldRentMngDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectNticXlsMaster(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamHtldRentNticReportDao.selectNticXlsMaster_S", searchVO);
	}

	/**
	 * 연체고지 마스터 (산출내역서 엑셀용)
	 * @param GamHtldRentMngDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectArrrgNticXlsMaster(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamHtldRentNticReportDao.selectArrrgNticXlsMaster_S", searchVO);
	}
	
	/**
	 * 고지 상세 리스트 조회 (산출내역서 엑셀용)
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<?> selectNticIssueXlsList(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return list("gamHtldRentNticReportDao.selectNticIssueXlsList_D", searchVO);
	}
}
