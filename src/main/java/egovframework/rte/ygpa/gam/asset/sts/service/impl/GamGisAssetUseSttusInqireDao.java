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
@Repository("gamGisAssetUseSttusInqireDao")
public class GamGisAssetUseSttusInqireDao extends YGPAAbstractDAO {

    /**
	 * GIS자산통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectGisAssetUseSttusList(Map searchVO) throws Exception {
		return list("gamGisAssetUseSttusInqireDao.selectGisAssetUseSttusList_D", searchVO);
	}

    /**
     * GIS자산통계 목록 갯수를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectGisAssetUseSttusListTotCnt(Map searchVO) throws Exception {
		return (Integer)getSqlMapClient().queryForObject("gamGisAssetUseSttusInqireDao.selectGisAssetUseSttusListTotCnt_S", searchVO);
	}

    /**
     * GIS자산통계 목록 합계를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectGisAssetUseSttusListTotSum(Map searchVO) throws Exception {
		return (Map) selectByPk("gamGisAssetUseSttusInqireDao.selectGisAssetUseSttusListTotSum_S", searchVO);
	}

    /**
     * 코드에 대한 GIS자산통계 정보를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    public Map selectAssetUseSttusInfoByCode(Map searchVO) throws Exception {
		return (Map) selectByPk("gamGisAssetUseSttusInqireDao.selectAssetUseSttusInfoByCode_S", searchVO);
    }

    /**
     * 코드에 대한 GIS자산통계 분포 목록을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List selectAssetUseSttusListByCode(Map searchVO) throws Exception {
		return list("gamGisAssetUseSttusInqireDao.selectAssetUseSttusListByCode_D", searchVO);
    }

}
