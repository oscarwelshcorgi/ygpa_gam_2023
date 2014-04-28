package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortPdRentFeeSttusInqireVO;

/**
 * @Class Name : GamTrainPortPdRentFeeSttusInqireDao.java
 * @Description : 철송장임대기간별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamTrainPortPdRentFeeSttusInqireDao")
public class GamTrainPortPdRentFeeSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 철송장임대기간별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectTrainPortPdRentFeeSttusInqireList(GamTrainPortPdRentFeeSttusInqireVO searchVO) throws Exception {
        return list("gamTrainPortPdRentFeeSttusInqireDao.selectTrainPortPdRentFeeSttusInqireList_D", searchVO);
    }
    
    
    /**
	 * 철송장임대기간별사용료현황 전체목록수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public int selectTrainPortPdRentFeeSttusInqireListTotCnt(GamTrainPortPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyPdRentFeeSttusInqireDao.selectTrainPortPdRentFeeSttusInqireListTotCnt_S", searchVO);
    }
    
    
    /**
	 * 사용료합계, 감면사용료 합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
    public GamTrainPortPdRentFeeSttusInqireVO selectTrainPortPdRentFeeSttusInqireSum(GamTrainPortPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return (GamTrainPortPdRentFeeSttusInqireVO) selectByPk("gamPrtFcltyPdRentFeeSttusInqireDao.selectTrainPortPdRentFeeSttusInqireSum_S", searchVO);
    }
    
}
