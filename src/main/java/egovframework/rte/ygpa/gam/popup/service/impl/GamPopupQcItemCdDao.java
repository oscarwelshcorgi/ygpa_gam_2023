/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupQcItemCdVo;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 23.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 23.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamPopupQcItemCdDao")
public class GamPopupQcItemCdDao extends YGPAAbstractDAO {

	/**
	 * @name selectQcItemCdList
	 * @param searchVO
	 * @return List
	 */
	public List selectQcItemCdList(GamPopupQcItemCdVo searchVO) {
		return list("PopupQcItemCdDao.selectQcItemCdList_D", searchVO);
	}

	/**
	 * @name selectQcItemCdListTotCnt
	 * @param searchVO
	 * @return totalCount
	 */
	public int selectQcItemCdListTotCnt(GamPopupQcItemCdVo searchVO) {
		return (Integer)selectByPk("PopupQcItemCdDao.selectQcItemCdListTotCnt_S", searchVO);
	}

}
