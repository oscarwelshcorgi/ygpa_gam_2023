/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service;

import java.util.List;

/**
 *
 * @author Lee
 * @since 2014. 10. 30.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 30.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupMngCodeService {

	/**
	 * @param searchVO
	 * @return
	 */
	List selectMngCodeList(GamPopupMngCodeVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	int selectMngCodeListTotCnt(GamPopupMngCodeVO searchVO) throws Exception;

}
