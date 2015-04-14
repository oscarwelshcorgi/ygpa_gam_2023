/**
 *
 */
package egovframework.rte.ygpa.gam.code.service;

import java.util.List;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 12.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamBjdOlnlpMngtService {
	/**
	 * 법정동코드 목록 조회
	 * @param vo
	 * @return lis t
	 * @throws Exception
	 */
	List selectBjdOlnlpList(GamBupjungdongOlnlpVO vo) throws Exception;

}
