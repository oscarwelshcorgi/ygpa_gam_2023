/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.soc.service.GamSocStatsVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 17.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamSocStatsDao")
public class GamSocStatsDAO extends YGPAAbstractDAO {

	/**
	 * 업체별 투자비보전 집계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체별 투자비보전 집계 목록
	 * @exception Exception
	 */
	public List selectSocEntprStatsList(GamSocStatsVO searchVO) {
		return list("gamSocStatsDao.selectSocEntprStatsList_S", searchVO);
	}
	
	/**
	 * 업체별 투자비보전 집계 목록 자료수와 상계금액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체별 투자비보전 집계 목록 자료수와 상계금액
	 * @exception Exception
	 */
	public GamSocStatsVO selectSocEntprStatsListTotSum(GamSocStatsVO searchVO) {
		return (GamSocStatsVO) selectByPk("gamSocStatsDao.selectSocEntprStatsListTotSum_S", searchVO);
	}
	

	/**
	 * 월별 투자비보전 집계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 월별 투자비보전 집계 목록
	 * @exception Exception
	 */
	public List selectSocMonthStatsList(GamSocStatsVO searchVO) {
		return list("gamSocStatsDao.selectSocMonthStatsList_S", searchVO);
	}
	
	/**
	 * 월별 투자비보전 집계 목록 자료수와 상계금액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 월별 투자비보전 집계 목록 자료수와 상계금액
	 * @exception Exception
	 */
	public GamSocStatsVO selectSocMonthStatsListTotSum(GamSocStatsVO searchVO) {
		return (GamSocStatsVO) selectByPk("gamSocStatsDao.selectSocMonthStatsListTotSum_S", searchVO);
	}

	/**
	 * 업체기준 월별 투자비보전 집계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체기준 월별 투자비보전 집계 목록
	 * @exception Exception
	 */
	public List selectSocEntprBasisMonthStatsList(GamSocStatsVO searchVO) {
		return list("gamSocStatsDao.selectSocEntprBasisMonthStatsList_S", searchVO);
	}
	
	/**
	 * 업체기준 월별 투자비보전 집계 목록 자료수와 상계금액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체기준 월별 투자비보전 집계 목록 자료수와 상계금액
	 * @exception Exception
	 */
	public GamSocStatsVO selectSocEntprBasisMonthStatsListTotSum(GamSocStatsVO searchVO) {
		return (GamSocStatsVO) selectByPk("gamSocStatsDao.selectSocEntprBasisMonthStatsListTotSum_S", searchVO);
	}
	
	/**
	 * 월기준 업체별 투자비보전 집계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체별 투자비보전 집계 목록
	 * @exception Exception
	 */
	public List selectSocMonthBasisEntprStatsList(GamSocStatsVO searchVO) {
		return list("gamSocStatsDao.selectSocMonthBasisEntprStatsList_S", searchVO);
	}
	
	/**
	 * 월기준 업체별 투자비보전 집계 목록 자료수와 상계금액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체별 투자비보전 집계 목록 자료수와 상계금액
	 * @exception Exception
	 */
	public GamSocStatsVO selectSocMonthBasisEntprStatsListTotSum(GamSocStatsVO searchVO) {
		return (GamSocStatsVO) selectByPk("gamSocStatsDao.selectSocMonthBasisEntprStatsListTotSum_S", searchVO);
	}
}
