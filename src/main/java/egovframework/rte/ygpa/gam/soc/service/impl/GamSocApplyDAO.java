/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
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
	EgovMap selectSocApplyDetailInquire(GamSocApplyVO searchVO) {
		return (EgovMap) selectByPk("gamSocApplyDAO.selectSocApplyDetail_S", searchVO);
	}
	

	/**
	 * 면제요청내역 리스트를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 면제요청내역 리스트
	 * @exception
	 */
	List<?> selectSocApplyList(GamSocApplyVO searchVO) {
		return list("gamSocApplyDAO.selectSocApplyList_D", searchVO);
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
	void insertSocApplyDetail(Map<?, ?> insertMap) {
		insert("gamSocApplyDao.insertSocApplyDetail_S", insertMap);
	}
	
	/**
	 * 면제요청내역관리 데이터를 수정한다. 
	 * @param updateVO - 수정할 정보가 담긴 VO
	 * @return 
	 * @exception
	 */	
	void updateSocApplyDetail(Map<?, ?> updateMap) {
		update("gamSocApplyDao.updateSocApplyDetail_S", updateMap);
	}
	
	/**
	 * 면제요청내역관리 데이터를 삭제한다. 
	 * @param deleteVO - 삭제할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	void deleteSocApplyDetail(Map<?, ?> deleteMap) {
		delete("gamSocApplyDao.deleteSocApplyDetail_S", deleteMap);
	}
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 시섧물리스트를  조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	List<?> selectSocApplyFacilList(GamSocApplyVO searchVO) {
		return list("gamSocApplyDao.selectSocApplyFacilList_D", searchVO);
	}

	/**
	 * 면제요청내역관리 데이터에 해당하는 시섧물리스트 총갯슈를  조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	int selectSocApplyFacilListTotCnt(GamSocApplyVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamSocApplyDao.selectSocApplyFacilListTotCnt_S", searchVO);
	}
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 시섧물데이터 유무를  조회한다. 
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return
	 * @exception
	 */	
	int selectSocApplyFacilInfoCnt(Map<?, ?> searchMap) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamSocApplyDao.selectSocApplyFacilInfoCnt_S", searchMap);
	}
	
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 시섧물데이터를 삽입한다. 
	 * @param insertMap - 삽입할 정보가 담긴 Map
	 * @return
	 * @exception
	 */	
	void insertSocApplyFacilInfo(Map<?, ?> insertMap) {
		insert("gamSocApplyDao.insertSocApplyFacilInfo_S", insertMap);
	}
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 시섧물데이터전체를 삭제한다. 
	 * @param deleteVO - 삭제할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	void deleteSocApplyFacilList(Map<?, ?> deleteMap) {
		delete("gamSocApplyDao.deleteSocApplyFacilList_S", deleteMap);
	}

	/**
	 * 면제요청내역관리 데이터에 해당하는 요금종류리스트를  조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	List<?> selectSocApplyFeeList(GamSocApplyVO searchVO) {
		return list("gamSocApplyDao.selectSocApplyFeeList_D", searchVO);
	}

	/**
	 * 면제요청내역관리 데이터에 해당하는 요금종류리스트 총갯슈를  조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	int selectSocApplyFeeListTotCnt(GamSocApplyVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamSocApplyDao.selectSocApplyFeeListTotCnt_S", searchVO);
	}
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 요금종류데이터 유무를  조회한다. 
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return
	 * @exception
	 */	
	int selectSocApplyFeeInfoCnt(Map<?, ?> searchMap) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamSocApplyDao.selectSocApplyFeeInfoCnt", searchMap);
	}
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 요금종류데이터를 삽입한다. 
	 * @param insertMap - 삽입할 정보가 담긴 Map
	 * @return
	 * @exception
	 */	
	void insertSocApplyFeeInfo(Map<?, ?> insertMap) {
		insert("gamSocApplyDao.insertSocApplyFeeInfo_S", insertMap);
	}
	
	/**
	 * 면제요청내역관리 데이터에 해당하는 요금종류데이터전체를 삭제한다. 
	 * @param deleteVO - 삭제할 정보가 담긴 VO
	 * @return
	 * @exception
	 */	
	void deleteSocApplyFeeList(Map<?, ?> deleteMap) {
		delete("gamSocApplyDao.deleteSocApplyFeeList_S", deleteMap);
	}
}
