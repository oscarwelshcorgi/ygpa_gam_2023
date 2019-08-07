/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeDefaultVO;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupRoadInfoMngtVO;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 12.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamRoadInfoMngtDao")
public class GamRoadInfoMngtDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List<?> selectRoadInfoList(GamPopupRoadInfoMngtVO searchVO) {
		// TODO Auto-generated method stub
		return list("gamBupJungDongCodeMngtDao.selectRoadInfoList", searchVO);
	}

}
