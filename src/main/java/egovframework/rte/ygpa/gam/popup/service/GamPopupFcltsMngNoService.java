/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service;

import java.util.List;

/**
 *
 * @author 김종민
 * @since 2014. 12. 11.
 * @version 1.0
 * @see 시설물 관리번호 조회 서비스
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 11.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupFcltsMngNoService {

	/**
	 * @name selectFcltsMngNoList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List<?> selectFcltsMngNoList(GamPopupFcltsMngNoVO searchVO) throws Exception;

	/**
	 * @name selectFcltsMngNoListTotCnt
	 * @param searchVO
	 * @return Integer
	 * @throws Exception
	 */
	int selectFcltsMngNoListTotCnt(GamPopupFcltsMngNoVO searchVO) throws Exception;

}
