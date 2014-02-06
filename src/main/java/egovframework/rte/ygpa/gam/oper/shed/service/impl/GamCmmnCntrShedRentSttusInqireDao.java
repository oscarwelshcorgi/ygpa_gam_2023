package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrShedRentSttusInqireVO;

/**
 * @Class Name : GamCmmnCntrShedRentSttusInqireDao.java
 * @Description : 공컨장치장임대현황조회 (공컨장치장/공컨장치장/공컨장치장임대현황조회)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCmmnCntrShedRentSttusInqireDao")
public class GamCmmnCntrShedRentSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 공컨장치장사용현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장사용현황 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrShedRentSttusInqireList(GamCmmnCntrShedRentSttusInqireVO searchVO) throws Exception {
        return list("gamCmmnCntrShedRentSttusInqireDao.selectCmmnCntrShedRentSttusInqireList_D", searchVO);
    }

    /**
	 * 공컨장치장사용현황 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장사용현황 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrShedRentSttusInqireListTotCnt(GamCmmnCntrShedRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrShedRentSttusInqireDao.selectCmmnCntrShedRentSttusInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장사용현황 목록
	 * @exception Exception
	 */
	public GamCmmnCntrShedRentSttusInqireVO selectCmmnCntrShedRentSttusInqireSum(GamCmmnCntrShedRentSttusInqireVO searchVO) throws Exception {
		return (GamCmmnCntrShedRentSttusInqireVO) selectByPk("gamCmmnCntrShedRentSttusInqireDao.selectCmmnCntrShedRentSttusInqireSum_S", searchVO);
	}
	
	/**
	 * 공컨장치장사용현황 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장사용현황 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrShedRentSttusInqireDetailList(GamCmmnCntrShedRentSttusInqireVO vo) throws Exception {
        return list("gamCmmnCntrShedRentSttusInqireDao.selectCmmnCntrShedRentSttusInqireDetailList_D", vo);
    }

    /**
	 * 공컨장치장사용현황 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장사용현황 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrShedRentSttusInqireDetailListTotCnt(GamCmmnCntrShedRentSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrShedRentSttusInqireDao.selectCmmnCntrShedRentSttusInqireDetailListTotCnt_S", vo);
    }

}
