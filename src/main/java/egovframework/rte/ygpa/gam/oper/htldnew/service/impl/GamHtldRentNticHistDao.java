/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticHistVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 12.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamHtldRentNticHistDao")
public class GamHtldRentNticHistDao  extends YGPAAbstractDAO {

	/**
	 * 업체정보 조회
	 * @param GamHtldRentNticHistVO
	 * @return List
	 * @exception Exception
	 */
	public Map<?, ?> selectEntrpsInfo(GamHtldRentNticHistVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamHtldRentNticHistDao.selectEntrpsInfo_S", searchVO);
	}
	
	/**
	 * 업체별 고지 이력 목록 조회
	 * @param GamHtldRentNticHistVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectHtldRentNticHistList(GamHtldRentNticHistVO searchVO) throws Exception {
		return (List<?>) list("gamHtldRentNticHistDao.selectHtldRentNticHistList_D", searchVO);
	}

	/**
	 * 고지 상세 조회
	 * @param GamHtldRentNticHistVO
	 * @return List
	 * @exception Exception 
	 */
	public List<?> selectHistNticIssueList(GamHtldRentNticHistVO searchVO) throws Exception {
		return (List<?>) list("gamHtldRentNticHistDao.selectHistNticIssueList_D", searchVO);
	}

	/**
	 * 연체고지 상세 조회
	 * @param GamHtldRentNticHistVO
	 * @return List
	 * @exception Exception 
	 */
	public List<?> selectHistArrrgNticIssueList(GamHtldRentNticHistVO searchVO) throws Exception {
		return (List<?>) list("gamHtldRentNticHistDao.selectHistArrrgNticIssueList_D", searchVO);
	}

}
