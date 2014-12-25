/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 25.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupFcltsFeeMngArrrgNticService {

	/**
	 * @param vo
	 * @return
	 */
	EgovMap calcDlyBillAmnt(Map<String, Object> vo) throws Exception;

}
