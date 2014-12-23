/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service;

import java.util.List;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 23.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 23.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupQcItemCdService {

	/**
	 * @param searchVO
	 * @return
	 */
	List selectQcItemCdList(GamPopupQcItemCdVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	int selectQcItemCdListTotCnt(GamPopupQcItemCdVo searchVO) throws Exception;

}
