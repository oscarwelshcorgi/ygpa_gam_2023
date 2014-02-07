package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.service.GamCmpyRecvStsInqireVO;

/**
 * @Class Name : GamCmpyRecvStsInqireDao.java
 * @Description : 업체별수입현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCmpyRecvStsInqireDao")
public class GamCmpyRecvStsInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 업체별수입현황조회 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectCmpyRecvStsInqireList(GamCmpyRecvStsInqireVO searchVO) throws Exception {
        return list("gamCmpyRecvStsInqireDao.selectCmpyRecvStsInqireList_D", searchVO);
    }

    /**
	 * 업체별수입현황조회 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록 총 갯수
	 * @exception
	 */
    public int selectCmpyRecvStsInqireListTotCnt(GamCmpyRecvStsInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmpyRecvStsInqireDao.selectCmpyRecvStsInqireListTotCnt_S", searchVO);
    }
    
}
