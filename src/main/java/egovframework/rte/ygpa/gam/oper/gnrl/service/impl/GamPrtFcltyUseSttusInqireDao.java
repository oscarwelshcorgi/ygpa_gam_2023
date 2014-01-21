package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseSttusInqireVO;

/**
 * @Class Name : GamPrtFcltyUseSttusInqireDao.java
 * @Description : 항만시설사용현황조회 DAO Class
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPrtFcltyUseSttusInqireDao")
public class GamPrtFcltyUseSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 항만시설사용현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용현황 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyUseSttusInqireList(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
        return list("gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireList_D", searchVO);
    }

    /**
	 * 항만시설사용현황 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용현황 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyUseSttusInqireListTotCnt(GamPrtFcltyUseSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용현황 목록
	 * @exception Exception
	 */
	public GamPrtFcltyUseSttusInqireVO selectPrtFcltyUseSttusInqireSum(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
		return (GamPrtFcltyUseSttusInqireVO) selectByPk("gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireSum_S", searchVO);
	}
	
	/**
	 * 항만시설사용현황 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용현황 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyUseSttusInqireDetailList(GamPrtFcltyUseSttusInqireVO vo) throws Exception {
        return list("gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireDetailList_D", vo);
    }

    /**
	 * 항만시설사용현황 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용현황 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyUseSttusInqireDetailListTotCnt(GamPrtFcltyUseSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireDetailListTotCnt_S", vo);
    }

}
