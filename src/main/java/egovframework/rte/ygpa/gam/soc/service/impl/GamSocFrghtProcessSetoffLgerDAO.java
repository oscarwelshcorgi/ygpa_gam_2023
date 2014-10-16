/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.soc.service.GamSocFrghtProcessSetoffLgerVO;

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
@Repository("gamSocFrghtProcessSetoffLgerDao")
public class GamSocFrghtProcessSetoffLgerDAO extends YGPAAbstractDAO {
	
	/**
	 * 투자비보전(화물)상계처리대장 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return Map
	 * @exception Exception
	 */
	public List selectSocFrghtProcessSetoffLgerList(GamSocFrghtProcessSetoffLgerVO searchVO) throws Exception {
		return list("gamSocFrghtProcessSetoffLgerDao.selectSocFrghtProcessSetoffLgerList", searchVO);
	}
	
	
	/**
	 * 투자비보전(화물)상계처리대장 총갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전(화물)상계처리대장 총갯수
	 * @exception Exception
	 */
	public int selectSocFrghtProcessSetoffLgerListTotCnt(GamSocFrghtProcessSetoffLgerVO searchVO)  throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("gamSocFrghtProcessSetoffLgerDao.selectSocFrghtProcessSetoffLgerListTotCnt", searchVO);
	}
	
	
	/**
	 * 투자비보전(화물)상계처리대장 상세내역 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return Map
	 * @exception Exception
	 */
	public List selectSocFrghtProcessSetoffLgerDetail(GamSocFrghtProcessSetoffLgerVO searchVO) throws Exception {
		return list("gamSocFrghtProcessSetoffLgerDao.selectSocFrghtProcessSetoffLgerDetail", searchVO);
	}
	

	/**
	 * 투자비보전(화물)상계처리대장 상세내역 총갯수 및 금액합계를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	public GamSocFrghtProcessSetoffLgerVO selectSocFrghtProcessSetoffLgerDetailSum(GamSocFrghtProcessSetoffLgerVO searchVO) throws Exception {
		return (GamSocFrghtProcessSetoffLgerVO) selectByPk("gamSocFrghtProcessSetoffLgerDao.selectSocFrghtProcessSetoffLgerDetailSum", searchVO);
	}

}
