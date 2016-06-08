/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentMngDefaultVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticDefaultVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticInfoVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentRntfeeVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 3.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamHtldRentNticIssueDao")
public class GamHtldRentNticIssueDao  extends YGPAAbstractDAO {
	/**
	 * 고지 정보 조회
	 * @param GamHtldRentMngDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectNticIssueMaster(GamHtldRentMngDefaultVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamHtldRentNticIssueDao.selectNticIssueMaster_S", searchVO);
	}

	/**
	 * 고지 상세 목록 조회
	 * @param GamHtldRentMngDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectNticIssueDetailList(GamHtldRentMngDefaultVO searchVO) throws Exception {
		return (List<?>) list("gamHtldRentNticIssueDao.selectNticIssueDetailList_D", searchVO);
	}
	

	/**
	 * 고지 요약 정보 Pk를 생성하거나 조회
	 * @param GamHtldRentNticInfoVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldNticSummPk(GamHtldRentNticInfoVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamHtldRentNticIssueDao.selectHtldNticSummPk_S", searchVO);
	}

	/**
	 * 고지 요약 정보 삽입
	 * @param GamHtldRentNticInfoVO
	 * @return 
	 * @exception Exception
	 */
	public void insertHtldNticSumm(GamHtldRentNticInfoVO searchVO) throws Exception {
		insert("gamHtldRentNticIssueDao.insertHtldNticSumm_S", searchVO);
	}

	/**
	 * 고지 요약 정보 수정
	 * @param GamHtldRentNticInfoVO
	 * @return  
	 * @exception Exception
	 */
	public void updateHtldNticSumm(GamHtldRentNticInfoVO searchVO) throws Exception {
		update("gamHtldRentNticIssueDao.updateHtldNticSumm_S", searchVO);
	}

	/**
	 * 고지 상세 순번 생성
	 * @param GamHtldRentNticDefaultVO
	 * @return String 
	 * @exception Exception
	 */
	public String selectHtldNticDtlsNextNticSeq(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return (String) selectByPk("gamHtldRentNticIssueDao.selectHtldNticDtlsNextNticSeq_S", searchVO);
	}
	
	/**
	 * LEV_REQEST_F의 NTIC_CNT 생성
	 * @param GamHtldRentNticInfoVO
	 * @return String 
	 * @exception Exception
	 */
	public String selectLevReqestNextNticCnt(GamHtldRentNticInfoVO searchVO) throws Exception {
		return (String) selectByPk("gamHtldRentNticIssueDao.selectLevReqestNextNticCnt_S", searchVO);
	}

	/**
	 * REV_COLL_F의 NTIC_NO 생성
	 * @param GamHtldRentNticInfoVO
	 * @return String 
	 * @exception Exception
	 */
	public String selectRevCollNextNticNo(GamHtldRentNticInfoVO searchVO) throws Exception {
		return (String) selectByPk("gamHtldRentNticIssueDao.selectRevCollNextNticNo_S", searchVO);
	}

	/**
	 * 고지 상세 정보 삽입
	 * @param GamHtldRentNticInfoVO
	 * @return 
	 * @exception Exception
	 */
	public void insertHtldNticDtls(GamHtldRentNticInfoVO searchVO) throws Exception {
		insert("gamHtldRentNticIssueDao.insertHtldNticDtls_S", searchVO);
	}

	/**
	 * 임대료 상세 부분 중 고지상세와 연결되는 부분 수정
	 * @param GamHtldRentRntfeeVO
	 * @return 
	 * @exception Exception
	 */
	public void updateHtldRntfee(GamHtldRentRntfeeVO searchVO) throws Exception {
		update("gamHtldRentNticIssueDao..updateHtldRntfee_S", searchVO);
	}

	/**
	 * LEV_REQEST_F 에 고지자료 등록
	 * @param GamHtldRentNticInfoVO
	 * @return 
	 * @exception Exception
	 */
	public void insertLevReqest(GamHtldRentNticInfoVO searchVO) throws Exception {
		insert("gamHtldRentNticIssueDao.insertLevReqest_S", searchVO);
	}
	
	/**
	 * REV_COLL_F 에 고지자료 등록
	 * @param GamHtldRentNticInfoVO
	 * @return 
	 * @exception Exception
	 */
	public void insertRevColl(GamHtldRentNticInfoVO searchVO) throws Exception {
		insert("gamHtldRentNticIssueDao.insertRevColl_S", searchVO);
	}
	
}
