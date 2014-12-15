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
	 * 시설물번호 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List<?> selectFcltsMngNoList(GamPopupFcltsMngNoVO searchVO) throws Exception;

    /**
	 * 시설물번호 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectFcltsMngNoListTotCnt(GamPopupFcltsMngNoVO searchVO) throws Exception;
}
