package egovframework.rte.ygpa.gam.domain.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.gam.domain.code.service.GisAssetsCdVO;
import egovframework.rte.ygpa.gam.domain.code.service.GisAssetsCdDefaultVO;

/**
 * @Class Name : GisAssetsCdFDAO.java
 * @Description : GisAssetsCdF DAO Class
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-01-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("gisAssetsCdFDAO")
public class GisAssetsCdDAO extends EgovAbstractDAO {

	/**
	 * GIS_ASSETS_CD_F을 등록한다.
	 * @param vo - 등록할 정보가 담긴 GisAssetsCdFVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertGisAssetsCdF(GisAssetsCdVO vo) throws Exception {
        return (String)insert("gisAssetsCdFDAO.insertGisAssetsCdF_S", vo);
    }

    /**
	 * GIS_ASSETS_CD_F을 수정한다.
	 * @param vo - 수정할 정보가 담긴 GisAssetsCdFVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateGisAssetsCdF(GisAssetsCdVO vo) throws Exception {
        update("gisAssetsCdFDAO.updateGisAssetsCdF_S", vo);
    }

    /**
	 * GIS_ASSETS_CD_F을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 GisAssetsCdFVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteGisAssetsCdF(GisAssetsCdVO vo) throws Exception {
        delete("gisAssetsCdFDAO.deleteGisAssetsCdF_S", vo);
    }

    /**
	 * GIS_ASSETS_CD_F을 조회한다.
	 * @param vo - 조회할 정보가 담긴 GisAssetsCdFVO
	 * @return 조회한 GIS_ASSETS_CD_F
	 * @exception Exception
	 */
    public GisAssetsCdVO selectGisAssetsCdF(GisAssetsCdVO vo) throws Exception {
        return (GisAssetsCdVO) selectByPk("gisAssetsCdFDAO.selectGisAssetsCdF_S", vo);
    }

    /**
	 * GIS_ASSETS_CD_F 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return GIS_ASSETS_CD_F 목록
	 * @exception Exception
	 */
    public List selectGisAssetsCdFList(GisAssetsCdDefaultVO searchVO) throws Exception {
        return list("gisAssetsCdFDAO.selectGisAssetsCdFList_D", searchVO);
    }

    /**
	 * GIS_ASSETS_CD_F 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return GIS_ASSETS_CD_F 총 갯수
	 * @exception
	 */
    public int selectGisAssetsCdFListTotCnt(GisAssetsCdDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gisAssetsCdFDAO.selectGisAssetsCdFListTotCnt_S", searchVO);
    }

}
