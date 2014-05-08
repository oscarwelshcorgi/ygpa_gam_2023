package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetSttusInqireVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetTypeValueStsVO;

/**
 * @Class Name : GamAssetSttusInqireDao.java
 * @Description : 자산정보현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetSttusInqireDao")
public class GamAssetSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 자산정보현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectAssetSttusInqireList(GamAssetSttusInqireVO searchVO) throws Exception {
        return list("gamAssetSttusInqireDao.selectAssetSttusInqireList_D", searchVO);
    }

    /**
	 * 자산정보현황 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록 총 갯수
	 * @exception
	 */
    public int selectAssetSttusInqireListTotCnt(GamAssetSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetSttusInqireDao.selectAssetSttusInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 금액합계를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return GamAssetSttusInqireVO
	 * @exception Exception
	 */
    public GamAssetSttusInqireVO selectAssetSttusInqireSum(GamAssetSttusInqireVO searchVO) throws Exception {
        return (GamAssetSttusInqireVO) selectByPk("gamAssetSttusInqireDao.selectAssetSttusInqireListSum_S", searchVO);
    }
}
