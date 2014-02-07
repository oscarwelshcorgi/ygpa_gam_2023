package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentFeePaySttusMngtVO;

/**
 * @Class Name : GamCntnrRentFeePaySttusMngtDao.java
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
@Repository("gamCntnrRentFeePaySttusMngtDao")
public class GamCntnrRentFeePaySttusMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 컨테이너부두납부현황관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두납부현황관리 목록
	 * @exception Exception
	 */
    public List selectCntnrRentFeePaySttusMngtList(GamCntnrRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamCntnrRentFeePaySttusMngtDao.selectCntnrRentFeePaySttusMngtList_D", searchVO);
    }

    /**
	 * 컨테이너부두납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrRentFeePaySttusMngtListTotCnt(GamCntnrRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrRentFeePaySttusMngtDao.selectCntnrRentFeePaySttusMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두납부현황관리 목록
	 * @exception Exception
	 */
	public GamCntnrRentFeePaySttusMngtVO selectCntnrRentFeePaySttusMngtSum(GamCntnrRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamCntnrRentFeePaySttusMngtVO) selectByPk("gamCntnrRentFeePaySttusMngtDao.selectCntnrRentFeePaySttusMngtSum_S", searchVO);
	}
	
}
