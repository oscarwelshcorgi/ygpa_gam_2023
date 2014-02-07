package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtVO;

/**
 * @Class Name : GamPrtFcltyRentFeePaySttusMngtDao.java
 * @Description : 항만시설납부현황관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPrtFcltyRentFeePaySttusMngtDao")
public class GamPrtFcltyRentFeePaySttusMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 항만시설납부현황관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentFeePaySttusMngtList(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtList_D", searchVO);
    }

    /**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentFeePaySttusMngtListTotCnt(GamPrtFcltyRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설납부현황관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyRentFeePaySttusMngtVO selectPrtFcltyRentFeePaySttusMngtSum(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentFeePaySttusMngtVO) selectByPk("gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtSum_S", searchVO);
	}
	
}
