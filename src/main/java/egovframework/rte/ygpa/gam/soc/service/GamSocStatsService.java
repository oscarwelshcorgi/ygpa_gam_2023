/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import java.util.List;

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

public interface GamSocStatsService {
	/**
	 * 업체별 투자비보전 집계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체별 투자비보전 집계 목록
	 * @exception Exception
	 */
	List selectSocEntprStatsList(GamSocStatsVO searchVO);
	
	/**
	 * 업체별 투자비보전 집계 목록 자료수와 상계금액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체별 투자비보전 집계 목록 자료수와 상계금액
	 * @exception Exception
	 */
	GamSocStatsVO selectSocEntprStatsListTotSum(GamSocStatsVO searchVO);
	

	/**
	 * 월별 투자비보전 집계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 월별 투자비보전 집계 목록
	 * @exception Exception
	 */
	List selectSocMonthStatsList(GamSocStatsVO searchVO);
	
	/**
	 * 월별 투자비보전 집계 목록 자료수와 상계금액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 월별 투자비보전 집계 목록 자료수와 상계금액
	 * @exception Exception
	 */
	GamSocStatsVO selectSocMonthStatsListTotSum(GamSocStatsVO searchVO);
	
	/**
	 * 업체기준 월별 투자비보전 집계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체기준 월별 투자비보전 집계 목록
	 * @exception Exception
	 */
	List selectSocEntprBasisMonthStatsList(GamSocStatsVO searchVO);
	
	/**
	 * 업체기준 월별 투자비보전 집계 목록 자료수와 상계금액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체기준 월별 투자비보전 집계 목록 자료수와 상계금액
	 * @exception Exception
	 */
	GamSocStatsVO selectSocEntprBasisMonthStatsListTotSum(GamSocStatsVO searchVO);
	
	/**
	 * 월기준 업체별 투자비보전 집계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체별 투자비보전 집계 목록
	 * @exception Exception
	 */
	List selectSocMonthBasisEntprStatsList(GamSocStatsVO searchVO);
	
	/**
	 * 월기준 업체별 투자비보전 집계 목록 자료수와 상계금액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체별 투자비보전 집계 목록 자료수와 상계금액
	 * @exception Exception
	 */
	GamSocStatsVO selectSocMonthBasisEntprStatsListTotSum(GamSocStatsVO searchVO);
}
