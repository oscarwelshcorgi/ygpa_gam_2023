/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.soc.service.GamSocRentProcessSetoffLgerVO;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 16.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 16.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamSocRentProcessSetoffLgerDao")
public class GamSocRentProcessSetoffLgerDAO extends YGPAAbstractDAO {
	
	/**
	 * 투자비보전(임대)상계처리대장 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return Map
	 * @exception Exception
	 */
	public List<?> selectSocRentProcessSetoffLgerList(GamSocRentProcessSetoffLgerVO searchVO) throws Exception {
		return list("gamSocRentProcessSetoffLgerDao.selectSocRentProcessSetoffLgerList_D", searchVO);
	}
	

	/**
	 * 투자비보전(임대)상계처리대장 총갯수 및 금액합계를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	public GamSocRentProcessSetoffLgerVO selectSocRentProcessSetoffLgerListSum(GamSocRentProcessSetoffLgerVO searchVO) throws Exception {
		return (GamSocRentProcessSetoffLgerVO) selectByPk("gamSocRentProcessSetoffLgerDao.selectSocRentProcessSetoffLgerListSum_S", searchVO);
	}
	


}
