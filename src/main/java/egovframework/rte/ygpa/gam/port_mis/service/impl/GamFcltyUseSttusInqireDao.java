package egovframework.rte.ygpa.gam.port_mis.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.port_mis.service.GamFcltyUseSttusInqireVO;

/**
 * @Class Name : GamFcltyUseSttusInqireDao.java
 * @Description : 항만시설사용현황조회(포트미스정보) DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamFcltyUseSttusInqireDao")
public class GamFcltyUseSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 항만시설납부현황관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록
	 * @exception Exception
	 */
    public List selectFcltyUseSttusInqireList(GamFcltyUseSttusInqireVO searchVO) throws Exception {
        return list("gamFcltyUseSttusInqireDao.selectFcltyUseSttusInqireList_D", searchVO);
    }

    /**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectFcltyUseSttusInqireListTotCnt(GamFcltyUseSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyUseSttusInqireDao.selectFcltyUseSttusInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록
	 * @exception Exception
	 */
	public GamFcltyUseSttusInqireVO selectFcltyUseSttusInqireSum(GamFcltyUseSttusInqireVO searchVO) throws Exception {
		return (GamFcltyUseSttusInqireVO) selectByPk("gamFcltyUseSttusInqireDao.selectFcltyUseSttusInqireSum_S", searchVO);
	}

}
