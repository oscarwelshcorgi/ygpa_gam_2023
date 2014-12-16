/**
 * 
 */
package egovframework.rte.ygpa.gam.soc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.soc.service.GamSocShipProcessRealloadVO;

/**
 * 
 * @author HNJ
 * @since 2014. 10. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 15.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamSocShipProcessRealloadDao")
public class GamSocShipProcessRealloadDao extends YGPAAbstractDAO {
	
	
	/**
	 * 투자비보전 처리실적 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return Map
	 * @exception Exception
	 */
	public List selectSocShipProcessRealloadList(GamSocShipProcessRealloadVO searchVO) throws Exception {
		return list("gamSocShipProcessRealloadDao.selectSocShipProcessRealloadList_D", searchVO);
	}
	
	
	/**
	 * 투자비보전 처리실적 총갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전 처리실적 총갯수
	 * @exception Exception
	 */
	public int selectSocShipProcessRealloadListTotCnt(GamSocShipProcessRealloadVO searchVO)  throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("gamSocShipProcessRealloadDao.selectSocShipProcessRealloadListTotCnt_S", searchVO);
	}
	
	
	/**
	 * 투자비보전 처리실적 상세내역 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return Map
	 * @exception Exception
	 */
	public List selectSocShipProcessRealloadDetail(GamSocShipProcessRealloadVO searchVO) throws Exception {
		return list("gamSocShipProcessRealloadDao.selectSocShipProcessRealloadDetail_D", searchVO);
	}
	

	/**
	 * 투자비보전 처리실적 상세내역 총갯수 및 금액합계를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
	public GamSocShipProcessRealloadVO selectSocShipProcessRealloadDetailSum(GamSocShipProcessRealloadVO searchVO) throws Exception {
		return (GamSocShipProcessRealloadVO) selectByPk("gamSocShipProcessRealloadDao.selectSocShipProcessRealloadDetailSum_S", searchVO);
	}
	
	
	
	/**
	 * 투자비보전 처리실적 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return Map
	 * @exception Exception
	 */
	public List selectSocShipProcessRealloadListPrint(GamSocShipProcessRealloadVO searchVO) throws Exception {
		return list("gamSocShipProcessRealloadDao.selectSocShipProcessRealloadListPrint_D", searchVO);
	}
	
	
	/**
	 * 투자비보전 처리실적 총갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 투자비보전 처리실적 총갯수
	 * @exception Exception
	 */
	public GamSocShipProcessRealloadVO selectSocShipProcessRealloadListPrintTotCnt(GamSocShipProcessRealloadVO searchVO)  throws Exception{
		return (GamSocShipProcessRealloadVO) selectByPk("gamSocShipProcessRealloadDao.selectSocShipProcessRealloadListPrintTotCnt_S", searchVO);
	}

}
