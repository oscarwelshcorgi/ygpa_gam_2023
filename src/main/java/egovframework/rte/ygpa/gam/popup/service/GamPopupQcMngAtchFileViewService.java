/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamQcMngAtchFileMngVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 5. 8.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 5. 8.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupQcMngAtchFileViewService {

	/**
	 * @name selectQcMngAtchFileViewPk
	 * @param gamQcMngAtchFileMngVO
	 * @return EgovMap
	 * @exception Exception
	 */
	EgovMap selectQcMngAtchFileViewPk(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception;

	/**
	 * @name updateQcMngAtchFileView
	 * @param gamQcMngAtchFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateQcMngAtchFileView(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception;

}
