/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFcltsAtchFileViewService;

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

@Service("gamPopupFcltsAtchFileViewService")
public class GamPopupFcltsAtchFileViewServiceImpl extends AbstractServiceImpl implements GamPopupFcltsAtchFileViewService {

	@Resource(name="gamPopupFcltsAtchFileViewDao")
	private GamPopupFcltsAtchFileViewDao gamPopupFcltsAtchFileViewDao;

	/**
	 * @name selectFcltsAtchFileViewPk
	 * @param gamFcltsAtchFileMngVO
	 * @return EgovMap
	 * @exception Exception
	 */
	public EgovMap selectFcltsAtchFileViewPk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamPopupFcltsAtchFileViewDao.selectFcltsAtchFileViewPk(gamFcltsAtchFileMngVO);
	}

	/**
	 * @name updateFcltsAtchFileView
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 * @exception Exception
	 */
	public void updateFcltsAtchFileView(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamPopupFcltsAtchFileViewDao.updateFcltsAtchFileView(gamFcltsAtchFileMngVO);
	}

}
