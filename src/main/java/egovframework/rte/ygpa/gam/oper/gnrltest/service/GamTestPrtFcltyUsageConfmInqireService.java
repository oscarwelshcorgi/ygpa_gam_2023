/**
 *
 */
package egovframework.rte.ygpa.gam.oper.gnrltest.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author Administrator
 * @since 2016. 6. 28.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 28.		Administrator		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamTestPrtFcltyUsageConfmInqireService {

	/**
	 * 항만시설사용현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyUsageConfmInqireList(GamTestPrtFcltyUsageConfmInqireVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	int selectPrtFcltyUsageConfmInqireListTotCnt(
			GamTestPrtFcltyUsageConfmInqireVO searchVO);

	/**
	 * @param searchVO
	 * @return
	 */
	EgovMap selectPrtFcltyUsageConfmInqireSum(GamTestPrtFcltyUsageConfmInqireVO searchVO);

}
