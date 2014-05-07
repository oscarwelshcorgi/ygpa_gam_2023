package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupFacilInfoVO;

/**
 * @Class Name : GamPopupFacilInfoDao.java
 * @Description : 선석정보 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPopupFacilInfoDao")
public class GamPopupFacilInfoDao extends YGPAAbstractDAO {
	
	/**
	 * 선석정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 선석정보 목록
	 * @exception Exception
	 */
    public List selectFacilInfoList(GamPopupFacilInfoVO searchVO) throws Exception {
        return list("gamPopupFacilInfoDAO.selectFacilInfoList_S", searchVO);
    }

    /**
	 * 선석정보 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 선석정보 목록 총 갯수
	 * @exception
	 */
    public int selectFacilInfoListTotCnt(GamPopupFacilInfoVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPopupFacilInfoDAO.selectFacilInfoListTotCnt_S", searchVO);
    }
    
}
