/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldRntfeeBizAssessVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 26.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 26.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamPopupHtldRntfeeBizAssessDao")
public class GamPopupHtldRntfeeBizAssessDao extends YGPAAbstractDAO {

	/**
	 * 실적평가정산 조회
	 * @param GamPopupHtldBizAssessVO
	 * @return Map 실적평가정산
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldRntfeeBizAssessDetail(GamPopupHtldRntfeeBizAssessVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamPopupHtldRntfeeBizAssessDao.selectHtldRntfeeBizAssessDetail_S", searchVO);
	}

	/**
	 * 실적평가정산 수정
	 * @param GamPopupHtldBizAssessVO
	 * @return 
	 * @exception Exception
	 */
	public void updateRntfeeBizAssess(GamPopupHtldRntfeeBizAssessVO vo) throws Exception {
		update("gamPopupHtldRntfeeBizAssessDao.updateRntfeeBizAssess_S", vo);
	}

	/**
	 * 실적평가정산 삭제
	 * @param GamPopupHtldBizAssessVO
	 * @return 
	 * @exception Exception
	 */
	public void deleteRntfeeBizAssess(GamPopupHtldRntfeeBizAssessVO vo) throws Exception {
		delete("gamPopupHtldRntfeeBizAssessDao.deleteRntfeeBizAssess_S", vo);
	}
}
