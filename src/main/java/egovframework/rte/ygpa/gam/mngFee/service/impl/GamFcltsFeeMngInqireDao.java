/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngInqireVo;

/**
 *
 * @author Lee
 * @since 2014. 10. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 24.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamFcltsFeeMngInqireDao")
public class GamFcltsFeeMngInqireDao extends YGPAAbstractDAO{

	/**
	 * @param searchVO
	 * @return
	 */
	public GamFcltsFeeMngInqireVo selectFcltsFeeMngInqireListTotCnt(GamFcltsFeeMngInqireVo searchVO) {
		return (GamFcltsFeeMngInqireVo)selectByPk("gamFcltsFeeMngInqireDao.selectFcltsFeeMngInqireListTotCnt_S", searchVO);
	}

	/**
	 * @param gamFcltsFeeMngInqireVo
	 * @return
	 */
	public List selectFcltsFeeMngInqireList(GamFcltsFeeMngInqireVo gamFcltsFeeMngInqireVo) {
		return list("gamFcltsFeeMngInqireDao.selectFcltsFeeMngInqireList_D", gamFcltsFeeMngInqireVo);
	}

	/**
	 * @param gamFcltsFeeMngInqireVo
	 * @return
	 */
	public List selectFcltsFeeMngInqireChartList(GamFcltsFeeMngInqireVo gamFcltsFeeMngInqireVo) {
		return list("gamFcltsFeeMngInqireDao.selectFcltsFeeMngInqireChartList_D", gamFcltsFeeMngInqireVo);
	}

	/**
	 * @param gamFcltsFeeMngInqireVo
	 */
	public void updateFcltsFeeMngInqire(GamFcltsFeeMngInqireVo gamFcltsFeeMngInqireVo) {
		update("gamFcltsFeeMngInqireDao.updateFcltsFeeMngInqire_S",gamFcltsFeeMngInqireVo);
	}

}
