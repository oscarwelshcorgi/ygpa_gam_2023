/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
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

@Repository("gamPopupQcMngAtchFileViewDao")
public class GamPopupQcMngAtchFileViewDao extends YGPAAbstractDAO {

	/**
	 * @name selectQcMngAtchFileViewPk
	 * @param gamQcMngAtchFileMngVO
	 * @return EgovMap 수정
	 */
	public EgovMap selectQcMngAtchFileViewPk(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) {
		return (EgovMap)selectByPk("PopupQcMngAtchFileViewDao.selectQcMngAtchFileViewPk_S", gamQcMngAtchFileMngVO);
	}

	/**
	 * @name updateQcMngAtchFileView
	 * @param gamQcMngAtchFileMngVO
	 * @return void
	 */
	public void updateQcMngAtchFileView(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) {
		update("PopupQcMngAtchFileViewDao.updateQcMngAtchFileView_S", gamQcMngAtchFileMngVO);
	}

}
