package egovframework.rte.ygpa.erp.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAErpAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;

/**
 * @Class Name : ErpAssetCdDAO.java
 * @Description : ErpAssetCd DAO Class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Repository("erpAssetCdDAO")
public class ErpAssetCdDAO extends YGPAErpAbstractDAO {

	/**
	 * ERP_ASSET_CD을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpAssetCdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertErpAssetCd(ErpAssetCdVO vo) throws Exception {
        return (String)insert("erpAssetCdDAO.insertErpAssetCd_S", vo);
    }

    /**
	 * ERP_ASSET_CD을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpAssetCdVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateErpAssetCd(ErpAssetCdVO vo) throws Exception {
        update("erpAssetCdDAO.updateErpAssetCd_S", vo);
    }

    /**
	 * ERP_ASSET_CD을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpAssetCdVO
	 * @return void형
	 * @exception Exception
	 */
    public void deleteErpAssetCd(ErpAssetCdVO vo) throws Exception {
        delete("erpAssetCdDAO.deleteErpAssetCd_S", vo);
    }

    /**
	 * ERP_ASSET_CD을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpAssetCdVO
	 * @return 조회한 ERP_ASSET_CD
	 * @exception Exception
	 */
    public ErpAssetCdVO selectErpAssetCd(ErpAssetCdVO vo) throws Exception {
        return (ErpAssetCdVO) selectByPk("erpAssetCdDAO.selectErpAssetCd_S", vo);
    }

    /**
	 * ERP_ASSET_CD 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_ASSET_CD 목록
	 * @exception Exception
	 */
    public List selectErpAssetCdList(ErpAssetCdDefaultVO searchVO) throws Exception {
        return list("erpAssetCdDAO.selectErpAssetCdList_D", searchVO);
    }

    /**
	 * ERP_ASSET_CD 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_ASSET_CD 총 갯수
	 * @exception
	 */
    public int selectErpAssetCdListTotCnt(ErpAssetCdDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("erpAssetCdDAO.selectErpAssetCdListTotCnt_S", searchVO);
    }

    /**
	 * ERP_ASSET_CD 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_ASSET_CD 목록
	 * @exception Exception
	 */
    public List selectErpAssetCdPopupList(ErpAssetCdDefaultVO searchVO) throws Exception {
        return list("erpAssetCdDAO.selectErpAssetCdPopupList_D", searchVO);
    }

    /**
	 * ERP_ASSET_CD 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_ASSET_CD 총 갯수
	 * @exception
	 */
    public int selectErpAssetCdPopupListTotCnt(ErpAssetCdDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("erpAssetCdDAO.selectErpAssetCdPopupListTotCnt_S", searchVO);
    }

}
