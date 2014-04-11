package egovframework.rte.ygpa.gam.port_mis.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.port_mis.service.GamCustTpSalesSttutsCreatVO;

/**
 * @Class Name : GamCustTpSalesSttutsCreatDao.java
 * @Description : 고객군들통계(포트미스정보) DAO Class
 * @Modification Information
 *
 * @author 김종민
 * @since 2014-04-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCustTpSalesSttutsCreatDao")
public class GamCustTpSalesSttutsCreatDao extends YGPAAbstractDAO {
	/**
	 * 매출액 통계 생성
	 * @param createVO - 조회할 정보가 담긴 VO
	 * @return 매출액 통계 생성 항목
	 * @exception Exception
	 */
	GamCustTpSalesSttutsCreatVO createCustTpSalesSttuts(GamCustTpSalesSttutsCreatVO createVO) {
		return null;
	}

    /**
	 * PORT-MIS 매출액 통계 목록 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return PORT-MIS 매출액 통계 목록
	 * @exception
	 */
    List selectPortMisCostvalStatsList(GamCustTpSalesSttutsCreatVO searchVO) {
    	return list("gamCustTpSalesSttutsCreatDao.selectPortMisCostvalStatsList_D", searchVO);
    }

    /**
	 * PORT-MIS 매출액 통계 목록 레코드 수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return PORT-MIS 매출액 통계 목록 레코드 수
	 * @exception
	 */
    int selectPortMisCostvalStatsListTotCnt(GamCustTpSalesSttutsCreatVO searchVO) {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamCustTpSalesSttutsCreatDao.selectPortMisCostvalStatsListTotCnt_S", searchVO);
    }

    /**
	 * 업체별 매출액 통계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체별 매출액 통계 목록
	 * @exception
	 */
    List selectCustAgentSalesSttutsList(GamCustTpSalesSttutsCreatVO searchVO) {
    	return list("gamCustTpSalesSttutsCreatDao.selectCustAgentSalesSttutsList_D", searchVO);
    }
    
    /**
	 * 업체별 매출액 통계 레코드 수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체별 매출액 통계 레코드 수
	 * @exception
	 */
    int selectCustAgentSalesSttutsListTotCnt(GamCustTpSalesSttutsCreatVO searchVO) {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamCustTpSalesSttutsCreatDao.selectCustAgentSalesSttutsListTotCnt_S", searchVO);
    }

    /**
	 * 선사별 매출액 통계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 선사별 매출액 통계 목록
	 * @exception
	 */
    List selectCustTpSalesSttutsList(GamCustTpSalesSttutsCreatVO searchVO) {
    	return list("gamCustTpSalesSttutsCreatDao.selectCustTpSalesSttutsList_D", searchVO);
    }
    
    /**
	 * 선사별 매출액 통계 레코드 수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 선사별 매출액 통계 레코드 수
	 * @exception
	 */
    int selectCustTpSalesSttutsListTotCnt(GamCustTpSalesSttutsCreatVO searchVO) {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamCustTpSalesSttutsCreatDao.selectCustTpSalesSttutsListTotCnt_S", searchVO);
    }
}
