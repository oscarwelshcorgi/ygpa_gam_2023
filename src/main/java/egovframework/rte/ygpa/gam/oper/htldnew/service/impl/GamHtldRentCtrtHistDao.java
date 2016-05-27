/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngDefaultVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 27.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 27.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamHtldRentCtrtHistDao")
public class GamHtldRentCtrtHistDao extends YGPAAbstractDAO {
	/**
	 * 배후단지 임대계약 최신이력번호 조회
	 * @param GamHtldRentCtrtVO
	 * @return String 이력 번호
	 * @exception Exception
	 */
	public String selectHtldRentCtrtMaxHistSeq(GamHtldRentMngDefaultVO searchVO) throws Exception {
		return (String) selectByPk("gamHtldRentCtrtHistDao.selectHtldRentCtrtMaxHistSeq_S", searchVO);
	}
	
	/**
	 * 배후단지 임대계약 이력번호 생성
	 * @param GamHtldRentCtrtVO
	 * @return String 새로운 이력변호
	 * @exception Exception
	 */
	public String selectNextHistSeq(GamHtldRentMngDefaultVO searchVO) throws Exception {
		return (String) getSqlMapClientTemplate().queryForObject("gamHtldRentCtrtHistDao.selectNextHistSeq_S", searchVO);
	}
	
	/**
	 * 배후단지 임대계약 변경시 이력등록
	 * @param GamHtldRentCtrtVO
	 * @return 
	 * @exception Exception
	 */
	public void insertHtldRentCtrtHist(GamHtldRentMngDefaultVO vo) throws Exception {
		insert("gamHtldRentCtrtHistDao.insertHtldRentCtrtHist_S", vo);
	}

	/**
	 * 배후단지 임대계약 상세목록 변경시 이력등록
	 * @param GamHtldRentCtrtVO
	 * @return 
	 * @exception Exception
	 */
	public void insertHtldRentCtrtDetailHist(GamHtldRentMngDefaultVO vo) throws Exception {
		insert("gamHtldRentCtrtHistDao.insertHtldRentCtrtDetailHist_D", vo);
	}
}
