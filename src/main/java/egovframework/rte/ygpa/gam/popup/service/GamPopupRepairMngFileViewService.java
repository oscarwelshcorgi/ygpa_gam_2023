/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairMngVO;


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

public interface GamPopupRepairMngFileViewService {

	/**
	 * @name selectRepairMngFileViewPk
	 * @param gamRepairMngFileMngVO
	 * @return EgovMap
	 * @exception Exception
	 */
	EgovMap selectRepairMngFileViewPk(GamFcltyRepairMngVO gamFcltyRepairMngVO) throws Exception;

	/**
	 * @name updateRepairMngFileView
	 * @param gamRepairMngFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateRepairMngFileView(GamFcltyRepairMngVO gamFcltyRepairMngVO) throws Exception;

	/**
	 * @name updateFcltyRepairMngContentsRm
	 * @param gamRepairMngFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateFcltyRepairMngContentsRm(GamFcltyRepairMngVO gamFcltyRepairMngVO) throws Exception;

}
