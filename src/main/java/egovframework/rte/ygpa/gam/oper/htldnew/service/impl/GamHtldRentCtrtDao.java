/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentCtrtDetailVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentCtrtVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 2.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 2.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamHtldRentCtrtDao")
public class GamHtldRentCtrtDao extends YGPAAbstractDAO {
		
	/**
	 * 배후단지 임대계약 조회
	 * @param GamRentCtrtVO
	 * @return Map 임대계약
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldRentCtrt(GamHtldRentCtrtVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamHtldRentCtrtDao.selectHtldRentCtrt_S", searchVO);
	}
	
	/**
	 * 배후단지 임대계약 상세목록 조회
	 * @param GamRentCtrtVO
	 * @return List 임대계약상세목록
	 * @exception Exception
	 */
	public List<?> selectHtldRentCtrtDetailList(GamHtldRentCtrtVO searchVO) throws Exception {
		return (List<?>) list("gamHtldRentCtrtDao.selectHtldRentCtrtDetailList_D", searchVO);
	}

	/**
	 * 자산관리코드(관리년도, 관리번호, 관리순번) 생성
	 * @return GamHtldRentCtrtVO 자산관리코드
	 * @exception Exception
	 */
	public GamHtldRentCtrtVO selectMngKeyValues() throws Exception {
		return (GamHtldRentCtrtVO) selectByPk("gamHtldRentCtrtDao.selectMngKeyValues_S", null);
	}
	
	/**
	 * 배후단지 임대계약 등록
	 * @param Map
	 * @return 
	 * @exception Exception
	 */
	public void insertHtldRentCtrt(GamHtldRentCtrtVO vo) throws Exception {
		insert("gamHtldRentCtrtDao.insertHtldRentCtrt_S", vo);
	}
	
	/**
	 * 배후단지 임대계약 수정
	 * @param Map
	 * @return 
	 * @exception Exception
	 */
	public void updateHtldRentCtrt(GamHtldRentCtrtVO vo) throws Exception {
		update("gamHtldRentCtrtDao.updateHtldRentCtrt_S", vo);
	}
	
	/**
	 * 배후단지 임대계약 해지
	 * @param Map
	 * @return 
	 * @exception Exception
	 */
	public void terminateHtldRentCtrt(GamHtldRentCtrtVO vo) throws Exception {
		update("gamHtldRentCtrtDao.terminateHtldRentCtrt_S", vo);
	}
	
	/**
	 * 배후단지 임대계약 상세번호 생성
	 * @param GamHtldRentCtrtVO
	 * @return String 새로운 상세 번호
	 * @exception Exception
	 */
	public String selectNextRegistSeq(GamHtldRentCtrtVO searchVO) throws Exception {
		return (String) getSqlMapClientTemplate().queryForObject("gamHtldRentCtrtDao.selectNextRegistSeq_S", searchVO);
	}

	/**
	 * 배후단지 임대계약 상세목록 등록
	 * @param Map
	 * @return 
	 * @exception Exception
	 */
	public void insertHtldRentCtrtDetail(GamHtldRentCtrtDetailVO vo) throws Exception {
		insert("gamHtldRentCtrtDao.insertHtldRentCtrtDetail_S", vo);
	}

	/**
	 * 배후단지 임대계약 상세목록 수정
	 * @param Map
	 * @return 
	 * @exception Exception
	 */
	public void updateHtldRentCtrtDetail(GamHtldRentCtrtDetailVO vo) throws Exception {
		update("gamHtldRentCtrtDao.updateHtldRentCtrtDetail_S", vo);
	}

	/**
	 * 배후단지 임대계약 상세목록 삭제
	 * @param Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteHtldRentCtrtDetail(GamHtldRentCtrtDetailVO vo) throws Exception {
		delete("gamHtldRentCtrtDao.deleteHtldRentCtrtDetail_S", vo);
	}

}