/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.soc.service.GamSocExmpMngtVO;

/**
 * 
 * @author 김종민
 * @since 2014. 9. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 24.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamSocExmpMngtDao")
public class GamSocExmpMngtDAO extends YGPAAbstractDAO {
	
	/**
	 * 투자비보전내역관리 데이터를 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전내역 데이터 VO
	 * @exception
	 */
	GamSocExmpMngtVO selectSocExmpMngtDetail(GamSocExmpMngtVO searchVO) {
		return (GamSocExmpMngtVO) selectByPk("gamSocExmpMngtDAO.selectSocExmpMngtDetail_D", searchVO);
	}
}
