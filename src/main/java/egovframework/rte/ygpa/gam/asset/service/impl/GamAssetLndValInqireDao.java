package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeVO;

/**
 * @Class Name : GamAssetLndValInqireDao.java
 * @Description : 자산부지공시지가조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetLndValInqireDao")
public class GamAssetLndValInqireDao extends YGPAAbstractDAO {

	/**
	 * 자산부지공시지가조회 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectAssetLndValInqireList(GamGisAssetCodeVO searchVO) throws Exception {
        return list("gamAssetLndValInqireDao.selectAssetLndValInqireList_D", searchVO);
    }

    /**
	 * 자산부지공시지가조회 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록 총 갯수
	 * @exception
	 */
    public int selectAssetLndValInqireListTotCnt(GamGisAssetCodeVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetLndValInqireDao.selectAssetLndValInqireListTotCnt_S", searchVO);
    }

}
