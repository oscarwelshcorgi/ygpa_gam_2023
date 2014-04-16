/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.sms.service;

import java.util.List;

/**
 * @Class Name : GamSmsMngtService.java
 * @Description : SMS 관리 목록 Service
 * @Modification Information
 *
 * @author 김종민
 * @since 2014-04-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

public interface GamSmsMngtService {
	
	/**
	 * SMS 관리 목록 서비스
	 * @param searchVO - SMS 관리 목록 조회 정보 VO
	 * @return SMS 관리 목록
	 * @exception Exception
	 */
	public List selectSmsMngtList(GamSmsMngtVO searchVO) throws Exception;

	/**
	 * SMS 관리 목록 수 서비스
	 * @param searchVO - SMS 관리 목록 조회 정보 VO
	 * @return 매출액 통계 생성 항목
	 * @exception Exception
	 */
	public int selectSmsMngtListTotCnt(GamSmsMngtVO searchVO) throws Exception;

	/**
	 * SMS 재전송 요청 서비스
	 * @param createVO - 재전송 데이터 생성 정보가 담긴 VO
	 * @return 
	 * @exception Exception
	 */
	public String smsRetransmit(GamSmsMngtVO createVO) throws Exception;
}
