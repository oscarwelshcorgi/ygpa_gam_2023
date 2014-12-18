/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service;

import java.util.List;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 16.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 16.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamSocFrghtProcessSetoffLgerService {
	
	/**
	 * 투자비보전(화물)상계처리대장 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전(화물)상계처리대장 목록
	 * @exception Exception
	 */
	List<?> selectSocFrghtProcessSetoffLgerList(GamSocFrghtProcessSetoffLgerVO searchVO)  throws Exception;

	
	
	/**
	 * 투자비보전(화물)상계처리대장 총갯수 및 금액합계를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투투자비보전(화물)상계처리대장 상세내역 총갯수 및 금액합계
	 * @exception
	 */
	GamSocFrghtProcessSetoffLgerVO selectSocFrghtProcessSetoffLgerListSum(GamSocFrghtProcessSetoffLgerVO searchVO)  throws Exception;
	
	


}
