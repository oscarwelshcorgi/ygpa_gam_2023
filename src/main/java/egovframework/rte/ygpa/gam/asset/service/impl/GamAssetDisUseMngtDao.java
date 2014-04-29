package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetDisUseMngtVO;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeVO;

/**
 * @Class Name : GamAssetDisUseMngtDao.java
 * @Description : 자산폐기등록 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetDisUseMngtDao")
public class GamAssetDisUseMngtDao extends YGPAAbstractDAO {

	/**
	 * GIS자산코드 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectAssetDisUseList(GamGisAssetCodeVO searchVO) throws Exception {
        return list("gamAssetDisUseMngtDao.selectAssetDisUseList_D", searchVO);
    }

    /**
	 * GIS자산코드 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록 총 갯수
	 * @exception
	 */
    public int selectAssetDisUseListTotCnt(GamGisAssetCodeVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetDisUseMngtDao.selectAssetDisUseListTotCnt_S", searchVO);
    }

	/**
	 * ERP 자산 폐기 정보를 수정한다.
	 * @param vo GamAssetDisUseMngtVO
	 * @exception Exception
	 */
	public void updateAssetDisUse(GamGisAssetCodeVO vo){
		update("gamAssetDisUseMngtDao.updateAssetDisUse_S", vo);
	}

}
