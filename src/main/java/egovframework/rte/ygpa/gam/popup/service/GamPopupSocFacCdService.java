/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service;

import java.util.List;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 25.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupSocFacCdService {
	/**
	 * 시설 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
	public List selectSocFacCdList(GamPopupSocFacCdVO searchVO) throws Exception;
	
	
	/**
	 * 시설 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	public int selectSocFacCdTotCnt(GamPopupSocFacCdVO searchVO) throws Exception;
}
