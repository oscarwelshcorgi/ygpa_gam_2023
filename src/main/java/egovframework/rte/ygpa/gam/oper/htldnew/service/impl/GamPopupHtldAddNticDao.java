/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldAddNticVO;

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
@Repository("gamPopupHtldAddNticDao")
public class GamPopupHtldAddNticDao extends YGPAAbstractDAO {
	/**
	 * 추가정산 계약정보 조회
	 * @param GamPopupHtldAddNticVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldAddNticCtrtDetail(GamPopupHtldAddNticVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamPopupHtldAddNticDao.selectAddNticCtrtDetail_S", searchVO);
	}

	/**
	 * 추가정산 조회
	 * @param GamPopupHtldAddNticVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldAddNticDetail(GamPopupHtldAddNticVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamPopupHtldAddNticDao.selectAddNticDetail_S", searchVO);
	}

	/**
	 * 임대료 순번 생성
	 * @param searchVO
	 * @return 임대료 순번
	 * @throws Exception
	 */
	public String selectNextRntfeeSeq(GamPopupHtldAddNticVO searchVO) throws Exception {
		return (String) getSqlMapClientTemplate().queryForObject("gamPopupHtldAddNticDao.selectNextRntfeeSeq_S", searchVO);
	}
	
	/**
	 * 추가정산 등록
	 * @param GamPopupHtldAddNticVO
	 * @return 
	 * @exception Exception
	 */
	public void insertAddNtic(GamPopupHtldAddNticVO vo) throws Exception {
		insert("gamPopupHtldAddNticDao.insertAddNtic_S", vo);
	}
	
	/**
	 * 추가정산 수정
	 * @param GamPopupHtldAddNticVO
	 * @return 
	 * @exception Exception
	 */
	public void updateAddNtic(GamPopupHtldAddNticVO vo) throws Exception {
		insert("gamPopupHtldAddNticDao.updateAddNtic_S", vo);
	}
	
	/**
	 * 추가정산 삭제
	 * @param GamPopupHtldAddNticVO
	 * @return 
	 * @exception Exception
	 */
	public void deleteAddNtic(GamPopupHtldAddNticVO vo) throws Exception {
		insert("gamPopupHtldAddNticDao.deleteAddNtic_S", vo);
	}
}
