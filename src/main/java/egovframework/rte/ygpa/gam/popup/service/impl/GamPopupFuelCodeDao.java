/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFuelCodeVO;

/**
 *
 * @author ACEWOLF
 * @since 2014. 11. 28.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 28.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamPopupFuelCodeDao")
public class GamPopupFuelCodeDao extends YGPAAbstractDAO {
	/**
	 * @param searchVO
	 * @return
	 */
	public List selectFuelCodeList(GamPopupFuelCodeVO searchVO) {
		return list("gamPopupFuelCodeDao.selectFuelCodeList_D", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public int selectFuelCodeListTotCnt(GamPopupFuelCodeVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamPopupFuelCodeDao.selectFuelCodeListTotCnt_S", searchVO);
	}

}
