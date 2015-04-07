/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairMngVO;


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

@Repository("gamPopupRepairMngFileViewDao")
public class GamPopupRepairMngFileViewDao extends YGPAAbstractDAO {

	/**
	 * @name selectRepairMngFileViewPk
	 * @param gamRepairMngFileMngVO
	 * @return EgovMap 수정
	 */
	public EgovMap selectRepairMngFileViewPk(GamFcltyRepairMngVO gamFcltyRepairMngVO) {
		return (EgovMap)selectByPk("PopupRepairMngFileViewDao.selectRepairMngFileViewPk_S", gamFcltyRepairMngVO);
	}

	/**
	 * @name updateRepairMngFileView
	 * @param gamRepairMngFileMngVO
	 * @return void
	 */
	public void updateRepairMngFileView(GamFcltyRepairMngVO gamFcltyRepairMngVO) {
		update("PopupRepairMngFileViewDao.updateRepairMngFileView_S", gamFcltyRepairMngVO);
	}

	/**
	 * @name updateFcltyRepairMngContentsRm
	 * @param gamRepairMngFileMngVO
	 * @return void
	 */
	public void updateFcltyRepairMngContentsRm(GamFcltyRepairMngVO gamFcltyRepairMngVO) {
		update("PopupRepairMngFileViewDao.updateFcltyRepairMngContentsRm_S", gamFcltyRepairMngVO);
	}

}
