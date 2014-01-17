package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupGisAssetsCdDefaultVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupGisAssetsCdVO;

/**
 * @Class Name : GisAssetsCdDAO.java
 * @Description : GisAssetsCd DAO Class
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-01-15
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Repository("gamPopupGisAssetsCdDAO")
public class GamPopupGisAssetsCdDAO extends EgovAbstractDAO {

    /**
	 * GIS_ASSETS_CD_F을 조회한다.
	 * @param vo - 조회할 정보가 담긴 GisAssetsCdVO
	 * @return 조회한 GIS_ASSETS_CD_F
	 * @exception Exception
	 */
    public GamPopupGisAssetsCdVO selectGisAssetsCd(GamPopupGisAssetsCdVO vo) throws Exception {
        return (GamPopupGisAssetsCdVO) selectByPk("popupGisAssetsCdDAO.selectGisAssetsCd_S", vo);
    }

    /**
	 * GIS_ASSETS_CD_F 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return GIS_ASSETS_CD_F 목록
	 * @exception Exception
	 */
    public List selectGisAssetsCdList(GamPopupGisAssetsCdDefaultVO searchVO) throws Exception {
        return list("popupGisAssetsCdDAO.selectGisAssetsCdList_D", searchVO);
    }

    /**
	 * GIS_ASSETS_CD_F 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return GIS_ASSETS_CD_F 총 갯수
	 * @exception
	 */
    public int selectGisAssetsCdListTotCnt(GamPopupGisAssetsCdDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("popupGisAssetsCdDAO.selectGisAssetsCdListTotCnt_S", searchVO);
    }

}
