/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentArrrgNticInfoVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticDefaultVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 10.
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
@Repository("gamHtldRentArrrgNticIssueDao")
public class GamHtldRentArrrgNticIssueDao  extends YGPAAbstractDAO {
	/**
	 * 고지 정보 조회
	 * @param GamHtldRentNticDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectNticIssueMaster(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamHtldRentArrrgNticIssueDao.selectNticIssueMaster_S", searchVO);
	}

	/**
	 * 고지 상세 목록 조회
	 * @param GamHtldRentNticDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectNticIssueDetailList(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return (List<?>) list("gamHtldRentArrrgNticIssueDao.selectNticIssueDetailList_D", searchVO);
	}
	
	/**
	 * 연체고지 정보 조회
	 * @param GamHtldRentNticDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectNticArrrgDetail(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamHtldRentArrrgNticIssueDao.selectNticArrrgDetail_S", searchVO);
	}

	/**
	 * 연체고지 등록 전 고지정보 확인 및 연체고지정보 조회
	 * @param GamHtldRentArrrgNticInfoVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectArrrgInfo(GamHtldRentArrrgNticInfoVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamHtldRentArrrgNticIssueDao.selectArrrgInfo_S", searchVO);
	}

	/**
	 * UNPAID_F에 연체정보 등록
	 * @param Map
	 * @return  
	 * @exception Exception
	 */
	public void insertArrrgInfo(Map<?, ?> vo) throws Exception {
		insert("gamHtldRentArrrgNticIssueDao.insertArrrgInfo_S", vo);
	}

	/**
	 * LEV_REQEST_F 에 연체정보 수정
	 * @param Map
	 * @return  
	 * @exception Exception
	 */
	public void updateLevReqestNticInfo(Map<?, ?> vo) throws Exception {
		update("gamHtldRentArrrgNticIssueDao.updateLevReqestNticInfo_S", vo);
	}

	/**
	 * HTLD_NTIC_DTLS_F 에 납부기한 수정
	 * @param Map
	 * @return  
	 * @exception Exception
	 */
	public void updateNticDtlsInfo(Map<?, ?> vo) throws Exception {
		update("gamHtldRentArrrgNticIssueDao.updateNticDtlsInfo_S", vo);
	}
}
