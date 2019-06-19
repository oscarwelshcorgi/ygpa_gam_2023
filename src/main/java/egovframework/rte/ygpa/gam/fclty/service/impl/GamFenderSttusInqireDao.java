/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderMngGroupVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFenderSttusInqireVO;

/**
 *
 * @author LFIT
 * @since 2015. 3. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 9.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamFenderSttusInqireDao")
public class GamFenderSttusInqireDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectFenderMngGroupList(GamFenderMngGroupVO searchVO) {
		// TODO Auto-generated method stub
		return list("gamFenderSttusInqireDao.selectFenderMngGroupList", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectFenderSttusInqireList(GamFenderSttusInqireVO searchVO) {
		// TODO Auto-generated method stub
		return list("gamFenderSttusInqireDao.selectFenderSttusInqireList", searchVO);
	}


}
