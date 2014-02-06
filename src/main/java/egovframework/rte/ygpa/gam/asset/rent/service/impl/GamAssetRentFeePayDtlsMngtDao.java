package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO;

/**
 * @Class Name : GamAssetRentFeePayDtlsMngtDao.java
 * @Description : 자산임대료납부관리 DAO Class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-02-05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetRentFeePayDtlsMngtDao")
public class GamAssetRentFeePayDtlsMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 자산임대료납부관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료납부관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentFeePayDtlsList(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
        return list("gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsList_D", searchVO);
    }

    /**
	 * 자산임대료납부관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료납부관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentFeePayDtlsListTotCnt(GamAssetRentFeePayDtlsMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료납부관리 목록
	 * @exception Exception
	 */
	public GamAssetRentFeePayDtlsMngtVO selectAssetRentFeePayDtlsSum(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
		return (GamAssetRentFeePayDtlsMngtVO) selectByPk("gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsSum_S", searchVO);
	}
	
}
