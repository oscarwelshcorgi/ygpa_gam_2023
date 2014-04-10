package egovframework.rte.ygpa.gam.port_mis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.port_mis.service.GamCustTpSalesSttutsCreatService;
import egovframework.rte.ygpa.gam.port_mis.service.GamCustTpSalesSttutsCreatVO;

/**
 * @Class Name : GamCustTpSalesSttutsCreatServiceImpl.java
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
@Service("gamCustTpSalesSttutsCreatService")
public class GamCustTpSalesSttutsCreatServiceImpl extends AbstractServiceImpl implements GamCustTpSalesSttutsCreatService {

	@Resource(name="gamCustTpSalesSttutsCreatDao")
    private GamCustTpSalesSttutsCreatDao gamCustTpSalesSttutsCreatDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 매출액 통계 생성
	 * @param createVO - 조회할 정보가 담긴 VO
	 * @return 매출액 통계 생성 항목
	 * @exception Exception
	 */
	public GamCustTpSalesSttutsCreatVO createCustTpSalesSttuts(GamCustTpSalesSttutsCreatVO createVO) throws Exception {
		return gamCustTpSalesSttutsCreatDao.createCustTpSalesSttuts(createVO);
	}

    /**
	 * 업체별 매출액 통계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체별 매출액 통계 목록
	 * @exception
	 */
	public List selectCustAgentSalesSttutsList(GamCustTpSalesSttutsCreatVO searchVO) throws Exception {
		return gamCustTpSalesSttutsCreatDao.selectCustAgentSalesSttutsList(searchVO);
	}

    /**
	 * 업체별 매출액 통계 레코드 수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체별 매출액 통계 레코드 수
	 * @exception
	 */
	public int selectCustAgentSalesSttutsListTotCnt(GamCustTpSalesSttutsCreatVO searchVO) throws Exception {
		return gamCustTpSalesSttutsCreatDao.selectCustAgentSalesSttutsListTotCnt(searchVO);
	}

	/**
	 * 선사별 매출액 통계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 선사별 매출액 통계 목록
	 * @exception
	 */
	public List selectCustTpSalesSttutsList(GamCustTpSalesSttutsCreatVO searchVO)
			throws Exception {
		return gamCustTpSalesSttutsCreatDao.selectCustTpSalesSttutsList(searchVO);
	}

    /**
	 * 선사별 매출액 통계 레코드 수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 선사별 매출액 통계 레코드 수
	 * @exception
	 */
	public int selectCustTpSalesSttutsListTotCnt(GamCustTpSalesSttutsCreatVO searchVO) throws Exception {
		return gamCustTpSalesSttutsCreatDao.selectCustTpSalesSttutsListTotCnt(searchVO);
	}
}
