package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeePaySttusMngtVO;

/**
 * @Class Name : GamCntnrQuayRentFeePaySttusMngtDao.java
 * @Description : 컨테이너부두납부현황관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCntnrQuayRentFeePaySttusMngtDao")
public class GamCntnrQuayRentFeePaySttusMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 컨테이너부두임대납부현황관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대납부현황관리 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayRentFeePaySttusMngtList(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtList_D", searchVO);
    }

    /**
	 * 컨테이너부두임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayRentFeePaySttusMngtListTotCnt(GamCntnrQuayRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대납부현황관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayRentFeePaySttusMngtVO selectCntnrQuayRentFeePaySttusMngtSum(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamCntnrQuayRentFeePaySttusMngtVO) selectByPk("gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtSum_S", searchVO);
	}
	
}
