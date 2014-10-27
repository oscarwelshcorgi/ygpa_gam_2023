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
	 * @param gamFcltsFeeMngInqireVo
	 * @return
	 */
	public int selectFcltsFeeMngInqireListTotCnt(GamFcltsFeeMngInqireVo gamFcltsFeeMngInqireVo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltsFeeMngInqireDao.selectFcltsFeeMngInqireListTotCnt_S", gamFcltsFeeMngInqireVo);
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
	 */
	public void insertFcltsFeeMngInqire(GamFcltsFeeMngInqireVo gamFcltsFeeMngInqireVo) {
		insert("gamFcltsFeeMngInqireDao.insertFcltsFeeMngInqire_D", gamFcltsFeeMngInqireVo);
	}

	/**
	 * @param gamFcltsFeeMngInqireVo
	 */
	public void deleteFcltsFeeMngInqire(GamFcltsFeeMngInqireVo gamFcltsFeeMngInqireVo) {
		delete("gamFcltsFeeMngInqireDao.deleteFcltsFeeMngInqire_D", gamFcltsFeeMngInqireVo);
	}


}
