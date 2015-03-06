/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.fclty.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 14.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 14.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamNticRequestMngtService {

	/**
	 * 고지의뢰건을 포트미스에 고지 한다.
	 * @param vo (nticCnt:고지횟수, prtAtCode, mngYear:관리년도, mngNo:관리 번호, mngCnt:관리 횟수, chrgeKnd: 요금종류)
	 * @throws Exception
	 */
	public void sendNticRequest(Map<String, Object> vo) throws Exception;

	/**
	 * 고지된 자료를 고지 취소 한다.
	 * @param vo (nticCnt:고지횟수, prtAtCode, mngYear:관리년도, mngNo:관리 번호, mngCnt:관리 횟수, chrgeKnd: 요금종류)
	 * @throws Exception
	 */
	public void cancelNticRequest(Map<String, Object> vo) throws Exception;

	/**
	 * 고지의뢰건을 포트미스에 연체 고지 한다.
	 * @param vo (nticCnt:고지횟수, prtAtCode, mngYear:관리년도, mngNo:관리 번호, mngCnt:관리 횟수, chrgeKnd: 요금종류)
	 * @throws Exception
	 */
	public void sendUnpaidRequest(Map<String, Object> vo) throws Exception;


	/**
	 * 여러건의 고지의뢰 건을 연체 고지 한다.
	 * @param list
	 * @throws Exception
	 */
	public void sendMultiUnpaidRequest(List list) throws Exception;

	/**
	 * 연체고지건을 포트미스에 고지 취소 한다.
	 * @param vo (nticCnt:고지횟수, prtAtCode, mngYear:관리년도, mngNo:관리 번호, mngCnt:관리 횟수, chrgeKnd: 요금종류)
	 * @throws Exception
	 */
	public void cancelUnpaidRequest(Map<String, Object> vo) throws Exception;

	/**
	 * 연체고지 한건을 고지 취소 한다.
	 * @param vo (nticCnt:고지횟수, prtAtCode, mngYear:관리년도, mngNo:관리 번호, mngCnt:관리 횟수, chrgeKnd: 요금종류, dlySerNo: 연체 횟수)
	 * @throws Exception
	 */
	public void cancelUnpaidRequestPk(Map<String, Object> vo) throws Exception;
/**
	 * 고지서 출력 여부를 업데이트 한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updateNticPrintState(Map<String, Object> vo) throws Exception;

	/**
	 * 연체료 분리된 고지서 출력 여부를 업데이트 한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updateNticPrintState2(Map<String, Object> vo) throws Exception;

	public int updateRentFeePaySttusRefresh() throws Exception;
}
