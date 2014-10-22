/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import java.util.List;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 14.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 14.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamSocApplyDtlsService {
	/**
	 * 투자비보전신청내역 리스트를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 면제요청내역 리스트
	 * @exception
	 */
	List selectSocApplyDtlsList(GamSocApplyDtlsVO searchVO);
	
	/**
	 * 투자비보전신청내역 리스트의 총계 자료를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 총계자료 VO
	 * @exception
	 */
	GamSocApplyDtlsVO selectSocApplyDtlsListTotSum(GamSocApplyDtlsVO searchVO);
}
