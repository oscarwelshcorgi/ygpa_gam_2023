/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.sms.service;

import java.util.List;

/**
 *
 * @author 김종민
 * @since 2014. 4. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 4. 15.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamSmsMngtService {
	public List selectSmsMngtList(GamSmsMngtVO searchVO) throws Exception;
	public int selectSmsMngtListTotCnt(GamSmsMngtVO searchVO) throws Exception;
	public String smsRetransmit(GamSmsMngtVO searchVO) throws Exception;
}
