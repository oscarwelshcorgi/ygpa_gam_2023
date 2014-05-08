package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetTypeValueStsVO;

/**
 * @Class Name : GamAssetTypeValueStsDao.java
 * @Description : 자산종류별자산가치통계조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetTypeValueStsDao")
public class GamAssetTypeValueStsDao extends YGPAAbstractDAO {
	
	/**
	 * 자산종류별자산가치통계조회 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectAssetTypeValueStsList(GamAssetTypeValueStsVO searchVO) throws Exception {
        return list("gamAssetTypeValueStsDao.selectAssetTypeValueStsList_D", searchVO);
    }
    
    /**
	 * 자산종류별자산가치통계조회 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetTypeValueStsListTotCnt(GamAssetTypeValueStsVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetTypeValueStsDao.selectAssetTypeValueStsListTotCnt_S", searchVO);
    }
    
    
    /**
	 * 금액합계를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return GamAssetTypeValueStsVO
	 * @exception Exception
	 */
    public GamAssetTypeValueStsVO selectAssetTypeValueStsSum(GamAssetTypeValueStsVO searchVO) throws Exception {
        return (GamAssetTypeValueStsVO) selectByPk("gamAssetTypeValueStsDao.selectAssetTypeValueStsSum_S", searchVO);
    }
    
}
