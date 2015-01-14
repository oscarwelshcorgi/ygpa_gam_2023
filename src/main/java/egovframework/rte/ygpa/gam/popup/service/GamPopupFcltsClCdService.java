/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service;

import java.util.List;

/**
 *
 * @author 김종민
 * @since 2014. 11. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 5.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupFcltsClCdService {

	/**
	 * 시설물 분류 목록을 조회한다.
	 * @name selectQcItemCdList
	 * @param searchVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectFcltsClCdList(GamPopupFcltsClCdVO searchVO) throws Exception;

	/**
	 * 시설물 분류 목록 총 갯수를 조회한다.
	 * @name selectFcltsClCdListTotCnt
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return totalCount
	 * @exception
	 */
	int selectFcltsClCdListTotCnt(GamPopupFcltsClCdVO searchVO) throws Exception;

}
