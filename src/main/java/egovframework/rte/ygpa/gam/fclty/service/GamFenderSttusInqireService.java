/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;

/**
 *
 * @author 源�?ъ쿋
 * @since 2019. 6. 19.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2019. 6. 19.		源�?ъ쿋		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFenderSttusInqireService {

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFenderMngGroupList(GamFenderMngGroupVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFenderSttusInqireList(GamFenderSttusInqireVO searchVO) throws Exception;

}
