package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMtRentFeeSttusInqireVO;

/**
 * @Class Name : GamHtldMtRentFeeSttusInqireDao.java
 * @Description : 배후단지임대월별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamHtldMtRentFeeSttusInqireDao")
public class GamHtldMtRentFeeSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 배후단지임대월별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectHtldMtRentFeeSttusInqireList(GamHtldMtRentFeeSttusInqireVO searchVO) throws Exception {
        return list("gamHtldMtRentFeeSttusInqireDao.selectHtldMtRentFeeSttusInqireList_D", searchVO);
    }

    /**
	 * 항만시설월별사용료현황 목록 자료수 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int - 자료수
	 * @exception Exception
	 */
    int selectPrtFcltyMtRentFeeSttusInqireListTotCnt(GamHtldMtRentFeeSttusInqireVO searchVO) throws Exception{
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldMtRentFeeSttusInqireDao.selectHtldMtRentFeeSttusInqireListTotCnt_S", searchVO);    
    }

    /**
	 * 항만시설월별사용료현황 자료수, 합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return resultVO - 합계정보가 담긴 VO
	 * @exception Exception
	 */
    GamHtldMtRentFeeSttusInqireVO selectPrtFcltyMtRentFeeSttusInqireSum(GamHtldMtRentFeeSttusInqireVO searchVO) throws Exception {
    	return (GamHtldMtRentFeeSttusInqireVO) selectByPk("gamHtldMtRentFeeSttusInqireDao.selectHtldMtRentFeeSttusInqireSum_S", searchVO);
    }

}
