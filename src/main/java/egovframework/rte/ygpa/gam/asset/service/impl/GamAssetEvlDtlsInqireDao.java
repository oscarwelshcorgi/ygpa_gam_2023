package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetEvlDtlsInqireVO;

/**
 * @Class Name : GamAssetEvlDtlsInqireDao.java
 * @Description : 자산가치평가내역조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetEvlDtlsInqireDao")
public class GamAssetEvlDtlsInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 자산가치평가내역 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectAssetEvlDtlsInqireList(GamAssetEvlDtlsInqireVO searchVO) throws Exception {
        return list("gamAssetEvlDtlsInqireDao.selectAssetEvlDtlsInqireList_D", searchVO);
    }

    /**
	 * 자산가치평가내역 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록 총 갯수
	 * @exception
	 */
    public int selectAssetEvlDtlsInqireListTotCnt(GamAssetEvlDtlsInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetEvlDtlsInqireDao.selectAssetEvlDtlsInqireListTotCnt_S", searchVO);
    }
    
}
