/**
 *
 */
package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFcltsMngNoVO;

/**
 *
 * @author 김종민
 * @since 2014. 12. 11.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 11.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamPopupFcltsMngNoDao")
public class GamPopupFcltsMngNoDao extends YGPAAbstractDAO {

	/**
	 * @name selectFcltsMngNoList
	 * @param searchVO
	 * @return List
	 */
	public List<?> selectFcltsMngNoList(GamPopupFcltsMngNoVO searchVO) throws Exception {
		return list("gamPopupFcltsMngNoDao.selectFcltsMngNoList_D", searchVO);
	}

	/**
	 * @name selectFcltsMngNoListTotCnt
	 * @param searchVO
	 * @return Integer
	 */
	public int selectFcltsMngNoListTotCnt(GamPopupFcltsMngNoVO searchVO) throws Exception {
		return (Integer)selectByPk("gamPopupFcltsMngNoDao.selectFcltsMngNoListTotCnt_S", searchVO);
	}

}

