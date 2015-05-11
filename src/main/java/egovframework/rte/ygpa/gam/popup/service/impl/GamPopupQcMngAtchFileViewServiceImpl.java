/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamQcMngAtchFileMngVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupQcMngAtchFileViewService;

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

@Service("gamPopupQcMngAtchFileViewService")
public class GamPopupQcMngAtchFileViewServiceImpl extends AbstractServiceImpl implements GamPopupQcMngAtchFileViewService {

	@Resource(name="gamPopupQcMngAtchFileViewDao")
	private GamPopupQcMngAtchFileViewDao gamPopupQcMngAtchFileViewDao;

	/**
	 * @name selectQcMngAtchFileViewPk
	 * @param gamQcMngAtchFileMngVO
	 * @return EgovMap
	 * @exception Exception
	 */
	public EgovMap selectQcMngAtchFileViewPk(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception {
		return gamPopupQcMngAtchFileViewDao.selectQcMngAtchFileViewPk(gamQcMngAtchFileMngVO);
	}

	/**
	 * @name updateQcMngAtchFileView
	 * @param gamQcMngAtchFileMngVO
	 * @return void
	 * @exception Exception
	 */
	public void updateQcMngAtchFileView(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception {
		gamPopupQcMngAtchFileViewDao.updateQcMngAtchFileView(gamQcMngAtchFileMngVO);
	}

}
