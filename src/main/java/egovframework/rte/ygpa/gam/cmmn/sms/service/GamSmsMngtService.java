/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.sms.service;

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

public interface GamSmsMngtService {

	/**
	 * SMS 서비스를 이용하여 문자메시지를 전송한다.
	 * @param vo (prtAtCode:항코드, mngYear:관리번호, mngNo:관리 순번, mngCnt:관리 횟수, entrpsCd: 업체코드(필수), chargerNo:담당자 번호(필수), recptnNo: 수신번호, replyNo: 회신번호, cn: 메시지 내용, regUsr: 등록자)
	 * @throws Exception
	 */
	public void sendSmsMessage(Map<String, Object> vo) throws Exception;

}
