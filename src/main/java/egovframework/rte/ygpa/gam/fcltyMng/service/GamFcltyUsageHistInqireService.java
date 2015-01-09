/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import java.util.List;

/**
 * 
 * @author LFIT
 * @since 2014. 12. 11.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 11.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltyUsageHistInqireService {

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFcltyUsageHistInqireList(GamFcltyUsageHistInqireVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFcltyUsageHistInqireDetail(GamFcltyUsageHistInqireVO searchVO) throws Exception;
	
	/**
	 * @param searchVO
	 * @return
	 */
	GamFcltyUsageHistInqireVO selectFcltyUsageHistInqireListTotCnt(GamFcltyUsageHistInqireVO searchVO) throws Exception;

}
