/**
 *
 */
package egovframework.com.ygpa.prtfcl.service;

import java.util.Map;

/**
 * 자산정보에 대해 사용승낙을 처리하고 요금 정보를 생성한다.
 * @author EUNSUNGJ
 * @since 2014. 2. 26.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 26.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface PFPrtFcltyUsePermMngtService {
	/**
	 * 선택한 자료에 대해 사용승낙/승낙취소를 처리하고 요금정보를 생성 한다. 취소하면 요금 정보를 삭제 한다.
	 * @param map - 자료입력 mngYear, mngNo, mngCnt, fillTp, prmisnYn
	 * @throws Exception
	 */
	public void updatePrtFcltyUsePerm(Map<String, Object>map) throws Exception;
	
}
