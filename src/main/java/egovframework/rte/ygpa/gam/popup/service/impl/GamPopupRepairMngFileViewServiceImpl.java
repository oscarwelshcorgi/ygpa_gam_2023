/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairMngVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupRepairMngFileViewService;

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

@Service("gamPopupRepairMngFileViewService")
public class GamPopupRepairMngFileViewServiceImpl extends AbstractServiceImpl implements GamPopupRepairMngFileViewService {

	@Resource(name="gamPopupRepairMngFileViewDao")
	private GamPopupRepairMngFileViewDao gamPopupRepairMngFileViewDao;

	/**
	 * @name selectRepairMngFileViewPk
	 * @param gamFcltyRepairMngVO
	 * @return EgovMap
	 * @exception Exception
	 */
	public EgovMap selectRepairMngFileViewPk(GamFcltyRepairMngVO gamFcltyRepairMngVO) throws Exception {
		return gamPopupRepairMngFileViewDao.selectRepairMngFileViewPk(gamFcltyRepairMngVO);
	}

	/**
	 * @name updateRepairMngFileView
	 * @param gamFcltyRepairMngVO
	 * @return void
	 * @exception Exception
	 */
	public void updateRepairMngFileView(GamFcltyRepairMngVO gamFcltyRepairMngVO) throws Exception {
		gamPopupRepairMngFileViewDao.updateRepairMngFileView(gamFcltyRepairMngVO);
	}

}
