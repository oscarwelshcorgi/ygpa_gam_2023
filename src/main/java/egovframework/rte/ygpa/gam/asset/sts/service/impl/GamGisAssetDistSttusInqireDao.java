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
@Repository("gamGisAssetDistSttusInqireDao")
public class GamGisAssetDistSttusInqireDao extends YGPAAbstractDAO {

    /**
	 * GIS자산통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectGisAssetDistSttusList(Map searchVO) throws Exception {
		return list("gamGisAssetDistSttusInqireDao.selectGisAssetDistSttusList_D", searchVO);
	}

    /**
     * GIS자산통계 목록 갯수를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectGisAssetDistSttusListTotCnt(Map searchVO) throws Exception {
		return (Integer)getSqlMapClient().queryForObject("gamGisAssetDistSttusInqireDao.selectGisAssetDistSttusListTotCnt_S", searchVO);
	}

    /**
     * GIS자산통계 목록 합계를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectGisAssetDistSttusListTotSum(Map searchVO) throws Exception {
		return (Map) selectByPk("gamGisAssetDistSttusInqireDao.selectGisAssetDistSttusListTotSum_S", searchVO);
	}

    /**
     * 코드에 대한 GIS자산통계 정보를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    public Map selectAssetDistSttusInfoByCode(Map searchVO) throws Exception {
		return (Map) selectByPk("gamGisAssetDistSttusInqireDao.selectAssetDistSttusInfoByCode_S", searchVO);
    }

    /**
     * 코드에 대한 GIS자산통계 분포 목록을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List selectAssetDistSttusListByCode(Map searchVO) throws Exception {
		return list("gamGisAssetDistSttusInqireDao.selectAssetDistSttusListByCode_D", searchVO);
    }

}
