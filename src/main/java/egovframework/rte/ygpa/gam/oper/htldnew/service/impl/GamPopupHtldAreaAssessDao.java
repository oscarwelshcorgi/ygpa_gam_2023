/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldAreaAssessVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 30.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 30.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamPopupHtldAreaAssessDao")
public class GamPopupHtldAreaAssessDao extends YGPAAbstractDAO {
	/**
	 * 지적평가 조회
	 * @param GamPopupHtldAreaAssessVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldAreaAssessDetail(GamPopupHtldAreaAssessVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamPopupHtldAreaAssessDao.selectAreaAssessDetail_S", searchVO);
	}

	/**
	 * 임대료 순번 생성
	 * @param searchVO
	 * @return 임대료 순번
	 * @throws Exception
	 */
	public String selectNextRntfeeSeq(GamPopupHtldAreaAssessVO searchVO) throws Exception {
		return (String) getSqlMapClientTemplate().queryForObject("gamPopupHtldAreaAssessDao.selectNextRntfeeSeq_S", searchVO);
	}
	
	/**
	 * 지적평가 등록
	 * @param GamPopupHtldAreaAssessVO
	 * @return 
	 * @exception Exception
	 */
	public void insertAreaAssess(GamPopupHtldAreaAssessVO vo) throws Exception {
		insert("gamPopupHtldAreaAssessDao.insertAreaAssess_S", vo);
	}
	
	/**
	 * 지적평가 수정
	 * @param GamPopupHtldAreaAssessVO
	 * @return 
	 * @exception Exception
	 */
	public void updateAreaAssess(GamPopupHtldAreaAssessVO vo) throws Exception {
		insert("gamPopupHtldAreaAssessDao.updateAreaAssess_S", vo);
	}
	
	/**
	 * 지적평가 삭제
	 * @param GamPopupHtldAreaAssessVO
	 * @return 
	 * @exception Exception
	 */
	public void deleteAreaAssess(GamPopupHtldAreaAssessVO vo) throws Exception {
		insert("gamPopupHtldAreaAssessDao.deleteAreaAssess_S", vo);
	}
}
