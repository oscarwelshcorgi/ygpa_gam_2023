package egovframework.rte.ygpa.gam.asset.sts.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetEvlDtlsInqireVO;

/**
 * @Class Name : GamAssetDisUseMngtDao.java
 * @Description : GIS자산통계 DAO
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-11-24
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Repository("gamGisAssetSttusInqireDao")
public class GamGisAssetSttusInqireDao extends YGPAAbstractDAO {

    /**
	 * GIS자산통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectGisAssetSttusAssetList(Map searchVO) throws Exception {
		return list("gamGisAssetSttusInqireDao.selectGisAssetSttusAssetList_D", searchVO);
	}

    /**
     * GIS자산통계 목록 갯수를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectGisAssetSttusAssetListTotCnt(Map searchVO) throws Exception {
		return (Integer)getSqlMapClient().queryForObject("gamGisAssetSttusInqireDao.selectGisAssetSttusAssetListTotCnt_S", searchVO);
	}

    /**
     * GIS자산통계 목록 합계를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectGisAssetSttusAssetListTotSum(Map searchVO) throws Exception {
		return (Map) selectByPk("gamGisAssetSttusInqireDao.selectGisAssetSttusAssetListTotSum_S", searchVO);
	}
}
