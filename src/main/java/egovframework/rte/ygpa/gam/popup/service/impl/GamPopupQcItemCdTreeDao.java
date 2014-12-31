/**
 * 
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupQcItemCdTreeVO;

/**
 * 
 * @author 김종민
 * @since 2014. 12. 31.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 31.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamPopupQcItemCdTreeDao")
public class GamPopupQcItemCdTreeDao extends YGPAAbstractDAO {
	/**
	 * @param searchVO
	 * @return
	 */
	public List<?> selectQcItemCdTreeList(GamPopupQcItemCdTreeVO searchVO) {
		return list("PopupQcItemCdTreeDao.selectQcItemCdTreeList_D", searchVO);
	}
}
