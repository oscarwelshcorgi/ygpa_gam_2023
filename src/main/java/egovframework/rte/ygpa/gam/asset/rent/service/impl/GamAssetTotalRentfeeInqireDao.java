package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetTotalRentfeeInqireVO;

/**
 * @Class Name : GamAssetTotalRentfeeInqireDao.java
 * @Description : 자산별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetTotalRentfeeInqireDao")
public class GamAssetTotalRentfeeInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 자산별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectAssetTotalRentfeeInqireList(GamAssetTotalRentfeeInqireVO searchVO) throws Exception {
        return list("gamAssetTotalRentfeeInqireDao.selectAssetTotalRentfeeInqireList_D", searchVO);
    }
    
    /**
	 * 자산별사용료현황 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetTotalRentfeeInqireListTotCnt(GamAssetTotalRentfeeInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetTotalRentfeeInqireDao.selectAssetTotalRentfeeInqireListTotCnt_S", searchVO);
    }
    
    
    
    /**
	 * 사용료합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
    public GamAssetTotalRentfeeInqireVO selectAssetTotalRentfeeInqireSum(GamAssetTotalRentfeeInqireVO searchVO) throws Exception {
    	return (GamAssetTotalRentfeeInqireVO) selectByPk("gamAssetTotalRentfeeInqireDao.selectAssetTotalRentfeeInqireSum_S", searchVO);
    }
    
}
