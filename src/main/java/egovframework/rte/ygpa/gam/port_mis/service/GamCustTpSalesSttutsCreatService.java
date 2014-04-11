package egovframework.rte.ygpa.gam.port_mis.service;

import java.util.List;

/**
 * @Class Name : GamCustTpSalesSttutsCreatService.java
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
public interface GamCustTpSalesSttutsCreatService {
	
	/**
	 * 매출액 통계 생성
	 * @param createVO - 통계 생성 정보가 담긴 VO
	 * @return 매출액 통계 생성 항목
	 * @exception Exception
	 */
	GamCustTpSalesSttutsCreatVO createCustTpSalesSttuts(GamCustTpSalesSttutsCreatVO createVO) throws Exception;

    /**
	 * PORT-MIS 매출액 통계 목록 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return PORT-MIS 매출액 통계 목록
	 * @exception
	 */
    List selectPortMisCostvalStatsList(GamCustTpSalesSttutsCreatVO searchVO) throws Exception;

    /**
	 * PORT-MIS 매출액 통계 목록 레코드수 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return PORT-MIS 매출액 통계 목록 레코드 수
	 * @exception
	 */
	public int selectPortMisCostvalStatsListTotCnt(GamCustTpSalesSttutsCreatVO searchVO) throws Exception;

    /**
	 * 업체별 매출액 통계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체별 매출액 통계 목록
	 * @exception
	 */
    List selectCustAgentSalesSttutsList(GamCustTpSalesSttutsCreatVO searchVO) throws Exception;
    
    /**
	 * 업체별 매출액 통계 레코드 수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체별 매출액 통계 레코드 수
	 * @exception
	 */
    int selectCustAgentSalesSttutsListTotCnt(GamCustTpSalesSttutsCreatVO searchVO) throws Exception;

    /**
	 * 선사별 매출액 통계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 선사별 매출액 통계 목록
	 * @exception
	 */
    List selectCustTpSalesSttutsList(GamCustTpSalesSttutsCreatVO searchVO) throws Exception;
    
    /**
	 * 선사별 매출액 통계 레코드 수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 선사별 매출액 통계 레코드 수
	 * @exception
	 */
    int selectCustTpSalesSttutsListTotCnt(GamCustTpSalesSttutsCreatVO searchVO) throws Exception;
}
