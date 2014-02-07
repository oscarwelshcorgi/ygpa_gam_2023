package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeePaySttusMngtVO;

/**
 * @Class Name : GamHtldRentFeePaySttusMngtDao.java
 * @Description : 배후단지납부현황관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamHtldRentFeePaySttusMngtDao")
public class GamHtldRentFeePaySttusMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 배후단지납부현황관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지납부현황관리 목록
	 * @exception Exception
	 */
    public List selectHtldRentFeePaySttusMngtList(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtList_D", searchVO);
    }

    /**
	 * 배후단지납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentFeePaySttusMngtListTotCnt(GamHtldRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지납부현황관리 목록
	 * @exception Exception
	 */
	public GamHtldRentFeePaySttusMngtVO selectHtldRentFeePaySttusMngtSum(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamHtldRentFeePaySttusMngtVO) selectByPk("gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtSum_S", searchVO);
	}
	
}
