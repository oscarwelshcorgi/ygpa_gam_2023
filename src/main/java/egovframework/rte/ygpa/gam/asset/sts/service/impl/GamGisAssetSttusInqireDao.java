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
    List selectGisAssetSttusList(Map searchVO) throws Exception {
		return list("gamGisAssetSttusInqireDao.selectGisAssetSttusList_D", searchVO);
	}

    /**
     * GIS자산통계 목록 갯수를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectGisAssetSttusListTotCnt(Map searchVO) throws Exception {
		return (Integer)getSqlMapClient().queryForObject("gamGisAssetSttusInqireDao.selectGisAssetSttusListTotCnt_S", searchVO);
	}

    /**
     * GIS자산통계 목록 합계를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectGisAssetSttusListTotSum(Map searchVO) throws Exception {
		return (Map) selectByPk("gamGisAssetSttusInqireDao.selectGisAssetSttusListTotSum_S", searchVO);
	}

    /**
     * 시설 임대 월별 이력을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    List selectGisAssetSttusByFcltyList(Map searchVO) throws Exception {
		return list("gamGisAssetSttusInqireDao.selectGisAssetSttusByFcltyList_D", searchVO);
	}

    /**
	 * GIS자산통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectGisAssetRentSttusList(Map searchVO) throws Exception {
		return list("gamGisAssetSttusInqireDao.selectGisAssetRentSttusList_D", searchVO);
	}

    /**
     * GIS자산통계 목록 갯수를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectGisAssetRentSttusListTotCnt(Map searchVO) throws Exception {
		return (Integer)getSqlMapClient().queryForObject("gamGisAssetSttusInqireDao.selectGisAssetRentSttusListTotCnt_S", searchVO);
	}

    /**
     * GIS자산통계 목록 합계를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectGisAssetRentSttusListTotSum(Map searchVO) throws Exception {
		return (Map) selectByPk("gamGisAssetSttusInqireDao.selectGisAssetRentSttusListTotSum_S", searchVO);
	}

    /**
	 * GIS자산통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectGisAssetRentFeeSttusList(Map searchVO) throws Exception {
		return list("gamGisAssetSttusInqireDao.selectGisAssetRentFeeSttusList_D", searchVO);
	}

    /**
     * GIS자산통계 목록 갯수를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectGisAssetRentFeeSttusListTotCnt(Map searchVO) throws Exception {
		return (Integer)getSqlMapClient().queryForObject("gamGisAssetSttusInqireDao.selectGisAssetRentFeeSttusListTotCnt_S", searchVO);
	}

    /**
     * GIS자산통계 목록 합계를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectGisAssetRentFeeSttusListTotSum(Map searchVO) throws Exception {
		return (Map) selectByPk("gamGisAssetSttusInqireDao.selectGisAssetRentFeeSttusListTotSum_S", searchVO);
	}

    /**
	 * GIS자산통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectGisAssetMntnRprSttusList(Map searchVO) throws Exception {
		return list("gamGisAssetSttusInqireDao.selectGisAssetMntnRprSttusList_D", searchVO);
	}

    /**
     * GIS자산통계 목록 갯수를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectGisAssetMntnRprSttusListTotCnt(Map searchVO) throws Exception {
		return (Integer)getSqlMapClient().queryForObject("gamGisAssetSttusInqireDao.selectGisAssetMntnRprSttusListTotCnt_S", searchVO);
	}

    /**
     * GIS자산통계 목록 합계를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectGisAssetMntnRprSttusListTotSum(Map searchVO) throws Exception {
		return (Map) selectByPk("gamGisAssetSttusInqireDao.selectGisAssetMntnRprSttusListTotSum_S", searchVO);
	}

    /**
	 * GIS자산통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectGisAssetFlawRprSttusList(Map searchVO) throws Exception {
		return list("gamGisAssetSttusInqireDao.selectGisAssetFlawRprSttusList_D", searchVO);
	}

    /**
     * GIS자산통계 목록 갯수를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectGisAssetFlawRprSttusListTotCnt(Map searchVO) throws Exception {
		return (Integer)getSqlMapClient().queryForObject("gamGisAssetSttusInqireDao.selectGisAssetFlawRprSttusListTotCnt_S", searchVO);
	}

    /**
     * GIS자산통계 목록 합계를 리턴한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectGisAssetFlawRprSttusListTotSum(Map searchVO) throws Exception {
		return (Map) selectByPk("gamGisAssetSttusInqireDao.selectGisAssetFlawRprSttusListTotSum_S", searchVO);
	}
}
