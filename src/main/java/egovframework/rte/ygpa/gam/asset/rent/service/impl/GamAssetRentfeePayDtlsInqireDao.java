package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentfeePayDtlsInqireVO;

/**
 * @Class Name : GamAssetRentfeePayDtlsInqireDao.java
 * @Description : 사용료납부내역조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetRentfeePayDtlsInqireDao")
public class GamAssetRentfeePayDtlsInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 사용료납부내역 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectAssetRentfeePayDtlsInqireList(GamAssetRentfeePayDtlsInqireVO searchVO) throws Exception {
        return list("gamAssetRentfeePayDtlsInqireDao.selectAssetRentfeePayDtlsInqireList_D", searchVO);
    }
    
    /**
	 * 사용료납부내역 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentfeePayDtlsInqireListTotCnt(GamAssetRentfeePayDtlsInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentfeePayDtlsInqireDao.selectAssetRentfeePayDtlsInqireListTotCnt_S", searchVO);
    }
    
}
