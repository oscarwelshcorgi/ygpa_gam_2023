package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentNticMngtVO;

/**
 * @Class Name : GamMarineCenterRentNticMngtDao.java
 * @Description : 마린센터임대료납부관리 DAO Class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-02-05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamMarineCenterRentNticMngtDao")
public class GamMarineCenterRentNticMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 마린센터임대료납부관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대료납부관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentNticList(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
        return list("gamMarineCenterRentNticMngtDao.selectMarineCenterRentNticList_D", searchVO);
    }

    /**
	 * 마린센터임대료납부관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대료납부관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentNticListTotCnt(GamMarineCenterRentNticMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentNticMngtDao.selectMarineCenterRentNticListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대료납부관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentNticMngtVO selectMarineCenterRentNticSum(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentNticMngtVO) selectByPk("gamMarineCenterRentNticMngtDao.selectMarineCenterRentNticSum_S", searchVO);
	}
	
}
