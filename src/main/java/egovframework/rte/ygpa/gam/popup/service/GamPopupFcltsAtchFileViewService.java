/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;

/**
 *
 * @author Administrator
 * @since 2015. 2. 26.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 26.		Administrator		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupFcltsAtchFileViewService {

	/**
	 * @name selectFcltsAtchFileViewPk
	 * @param gamFcltsAtchFileMngVO
	 * @return EgovMap
	 * @exception Exception
	 */
	EgovMap selectFcltsAtchFileViewPk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name updateFcltsAtchFileView
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateFcltsAtchFileView(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

}
