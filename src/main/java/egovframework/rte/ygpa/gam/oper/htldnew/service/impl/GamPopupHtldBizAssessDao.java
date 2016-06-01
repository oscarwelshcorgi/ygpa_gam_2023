/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldBizAssessVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldRntfeeBizAssessVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 15.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamPopupHtldBizAssessDao")
public class GamPopupHtldBizAssessDao extends YGPAAbstractDAO {
	
	/**
	 * 배후단지 임대계약 조회
	 * @param GamPopupHtldBizAssessVO
	 * @return Map 임대계약
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldRentBizAssessDetail(GamPopupHtldBizAssessVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamPopupHtldBizAssessDao.selectHtldRentBizAssessDetail_S", searchVO);
	}

	/**
	 * 실적평가 등록(상세)
	 * @param GamPopupHtldBizAssessVO
	 * @return 
	 * @exception Exception
	 */
	public void updateBizAssess(GamPopupHtldBizAssessVO vo) throws Exception {
		update("gamPopupHtldBizAssessDao.updateBizAssess_S", vo);
	}
	
	/**
	 * 임대료 순번 생성
	 * @param searchVO
	 * @return 임대료 순번
	 * @throws Exception
	 */
	public String selectNextRntfeeSeq(GamPopupHtldBizAssessVO searchVO) throws Exception {
		return (String) getSqlMapClientTemplate().queryForObject("gamPopupHtldBizAssessDao.selectNextRntfeeSeq_S", searchVO);
	}
	
	/**
	 * 실적평가 등록(상세)
	 * @param GamPopupHtldBizAssessVO
	 * @return 
	 * @exception Exception
	 */
	public void insertRntfeeBizAssess(GamPopupHtldRntfeeBizAssessVO vo) throws Exception {
		insert("gamPopupHtldBizAssessDao.insertRntfeeBizAssess_S", vo);
	}

	/**
	 * 실적평가 삭제(상세)
	 * @param GamPopupHtldBizAssessVO
	 * @return 
	 * @exception Exception
	 */
	public void deleteRntfeeBizAssess(GamPopupHtldRntfeeBizAssessVO vo) throws Exception {
		delete("gamPopupHtldBizAssessDao.deleteRntfeeBizAssess_S", vo);
	}
}
