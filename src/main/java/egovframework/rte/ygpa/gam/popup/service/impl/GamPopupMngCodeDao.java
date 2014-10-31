/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupMngCodeVO;

/**
 *
 * @author Lee
 * @since 2014. 10. 30.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 30.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamPopupMngCodeDao")
public class GamPopupMngCodeDao extends YGPAAbstractDAO {
	/**
	 * @param searchVO
	 * @return
	 */
	public List selectMngCodeList(GamPopupMngCodeVO searchVO) {
        return list("PopupMngCodeDao.selectMngCodeList_S", searchVO);

	}

	/**
	 * @param searchVO
	 * @return
	 */
	public int selectMngCodeListTotCnt(GamPopupMngCodeVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("PopupMngCodeDao.selectMngCodeListTotCnt_S", searchVO);
	}

}
