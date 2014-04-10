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
 * @author lsl
 * @since 2014-04-9
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCustTpSalesSttutsCreatDao")
public class GamCustTpSalesSttutsCreatDao extends YGPAAbstractDAO {
	
	/**
	 * 항만시설납부현황관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록
	 * @exception Exception
	 */
    public List selectFcltyUseSttusInqireList(GamCustTpSalesSttutsCreatVO searchVO) throws Exception {
        return list("gamCustTpSalesSttutsCreatDao.selectFcltyUseSttusInqireList_D", searchVO);
    }

    /**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectFcltyUseSttusInqireListTotCnt(GamCustTpSalesSttutsCreatVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCustTpSalesSttutsCreatDao.selectFcltyUseSttusInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록
	 * @exception Exception
	 */
	public GamCustTpSalesSttutsCreatVO selectFcltyUseSttusInqireSum(GamCustTpSalesSttutsCreatVO searchVO) throws Exception {
		return (GamCustTpSalesSttutsCreatVO) selectByPk("gamCustTpSalesSttutsCreatDao.selectFcltyUseSttusInqireSum_S", searchVO);
	}

}
