/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;

/**
 *
 * @author Administrator
 * @since 2015. 2. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 25.		Administrator		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamPopupFcltsAtchFileViewDao")
public class GamPopupFcltsAtchFileViewDao extends YGPAAbstractDAO {

	/**
	 * @name selectFcltsAtchFileViewPk
	 * @param gamFcltsAtchFileMngVO
	 * @return EgovMap
	 */
	public EgovMap selectFcltsAtchFileViewPk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (EgovMap)selectByPk("PopupFcltsAtchFileViewDao.selectFcltsAtchFileViewPk_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name updateFcltsAtchFileView
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void updateFcltsAtchFileView(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		update("PopupFcltsAtchFileViewDao.updateFcltsAtchFileView_S", gamFcltsAtchFileMngVO);
	}

}
