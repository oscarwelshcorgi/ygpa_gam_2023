/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.soc.service.GamSocApplyVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 7.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamSocApplyDao")
public class GamSocApplyDAO extends YGPAAbstractDAO {
	/**
	 * 면제요청내역관리 데이터를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 면제요청내역관리 VO
	 * @exception
	 */
	GamSocApplyVO selectSocApplyDetailInquire(GamSocApplyVO searchVO) {
		return (GamSocApplyVO) selectByPk("gamSocApplyDAO.selectSocApplyDetail_D", searchVO);
	}
	
	/**
	 * 면제요청내역관리 데이터의 총 개수를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	int selectSocApplyDetailTotCnt(GamSocApplyVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamSocApplyDAO.selectSocApplyDetailTotCnt_S", searchVO);
	}
	
	/**
	 * 면제요청내역 리스트를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 면제요청내역 리스트
	 * @exception
	 */
	List selectSocApplyList(GamSocApplyVO searchVO) {
		return list("gamSocApplyDAO.selectSocApplyList_S", searchVO);
	}
	
	/**
	 * 면제요청내역 리스트의 총 개수를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	int selectSocApplyListTotCnt(GamSocApplyVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamSocApplyDao.selectSocApplyListTotCnt_S", searchVO);
	}
	
	/**
	 * 면제요청내역관리 데이터를 삽입한다. 
	 * @param insertVO - 삽입할 정보가 담긴 VO
	 * @return 
	 * @exception
	 */
	void insertSocApplyDetail(GamSocApplyVO insertVO) {
		insert("gamSocApplyDao.insertSocApplyDetail", insertVO);
	}
	
	/**
	 * 면제요청내역관리 데이터를 수정한다. 
	 * @param updateVO - 수정할 정보가 담긴 VO
	 * @return 
	 * @exception
	 */	
	void updateSocApplyDetail(GamSocApplyVO updateVO) {
		update("gamSocApplyDao.updateSocApplyDetail", updateVO);
	}
	
	/**
	 * 면제요청내역관리 데이터를 삭제한다. 
	 * @param deleteVO - 삭제할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	void deleteSocApplyDetail(GamSocApplyVO deleteVO) {
		delete("gamSocApplyDao.deleteSocApplyDetail", deleteVO);
	}
}
