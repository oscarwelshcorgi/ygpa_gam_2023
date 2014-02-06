package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentSttusInqireVO;

/**
 * @Class Name : GamTrainPortRentSttusInqireDao.java
 * @Description : 철송장임대현황조회 (철송장/철송장/철송장임대현황조회)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamTrainPortRentSttusInqireDao")
public class GamTrainPortRentSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 철송장사용현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장사용현황 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentSttusInqireList(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
        return list("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireList_D", searchVO);
    }

    /**
	 * 철송장사용현황 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장사용현황 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentSttusInqireListTotCnt(GamTrainPortRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장사용현황 목록
	 * @exception Exception
	 */
	public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireSum(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
		return (GamTrainPortRentSttusInqireVO) selectByPk("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireSum_S", searchVO);
	}
	
	/**
	 * 철송장사용현황 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장사용현황 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentSttusInqireDetailList(GamTrainPortRentSttusInqireVO vo) throws Exception {
        return list("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireDetailList_D", vo);
    }

    /**
	 * 철송장사용현황 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장사용현황 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentSttusInqireDetailListTotCnt(GamTrainPortRentSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireDetailListTotCnt_S", vo);
    }

}
