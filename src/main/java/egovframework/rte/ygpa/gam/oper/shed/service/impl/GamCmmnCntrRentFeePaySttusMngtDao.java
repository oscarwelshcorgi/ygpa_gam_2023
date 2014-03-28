package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentFeePaySttusMngtVO;

/**
 * @Class Name : GamCmmnCntrRentFeePaySttusMngtDao.java
 * @Description : 공컨장치장납부현황관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCmmnCntrRentFeePaySttusMngtDao")
public class GamCmmnCntrRentFeePaySttusMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 공컨장치장임대납부현황관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대납부현황관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrRentFeePaySttusMngtList(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return list("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtList_D", searchVO);
    }

    /**
	 * 공컨장치장임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대납부현황관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrRentFeePaySttusMngtListTotCnt(GamCmmnCntrRentFeePaySttusMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대납부현황관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrRentFeePaySttusMngtVO selectCmmnCntrRentFeePaySttusMngtSum(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return (GamCmmnCntrRentFeePaySttusMngtVO) selectByPk("gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtSum_S", searchVO);
	}
	
}
