package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupEntrpsInfoVO;

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
@Repository("gamPopupEntrpsInfoDao")
public class GamPopupEntrpsInfoDao extends YGPAAbstractDAO {
	
	/**
	 * 업체정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체정보 목록
	 * @exception Exception
	 */
    public List selectEntrpsInfoList(GamPopupEntrpsInfoVO searchVO) throws Exception {
        return list("gamPopupEntrpsInfoDao.selectEntrpsInfoList_S", searchVO);
    }

    /**
	 * 업체정보 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 업체정보 목록 총 갯수
	 * @exception
	 */
    public int selectEntrpsInfoListTotCnt(GamPopupEntrpsInfoVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPopupEntrpsInfoDao.selectEntrpsInfoListTotCnt_S", searchVO);
    }
    
}
