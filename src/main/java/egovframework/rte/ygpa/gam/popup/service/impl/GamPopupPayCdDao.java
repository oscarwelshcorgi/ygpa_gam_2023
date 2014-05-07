package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupPayCdVO;

/**
 * @Class Name : GamPopupEntrpsInfoDao.java
 * @Description : 업체정보 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPopupPayCdDao")
public class GamPopupPayCdDao extends YGPAAbstractDAO {
	
	/**
	 * 업체정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체정보 목록
	 * @exception Exception
	 */
    public List selectPayCdList(GamPopupPayCdVO searchVO) throws Exception {
        return list("gamPopupPayCdDao.selectPayCdList_S", searchVO);
    }

    /**
	 * 업체정보 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체정보 목록 총 갯수
	 * @exception
	 */
    public int selectPayCdTotCnt(GamPopupPayCdVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPopupPayCdDao.selectPayCdTotCnt_S", searchVO);
    }
    
}
