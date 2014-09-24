/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 24.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamSocExmpMngtService {
	/**
	 * 투자비보전내역관리 데이터를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전내역 데이터 VO
	 * @exception
	 */
	GamSocExmpMngtVO selectSocExmpMngtDetailInquire(GamSocExmpMngtVO searchVO);
}
