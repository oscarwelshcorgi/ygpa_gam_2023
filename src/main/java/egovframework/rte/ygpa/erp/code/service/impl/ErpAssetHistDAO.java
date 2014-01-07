package egovframework.rte.ygpa.erp.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAErpAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetHistVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetHistDefaultVO;

/**
 * @Class Name : ErpAssetHistDAO.java
 * @Description : ErpAssetHist DAO Class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("erpAssetHistDAO")
public class ErpAssetHistDAO extends YGPAErpAbstractDAO {

	/**
	 * ERP_ASSET_HIST을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpAssetHistVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertErpAssetHist(ErpAssetHistVO vo) throws Exception {
        return (String)insert("erpAssetHistDAO.insertErpAssetHist_S", vo);
    }

    /**
	 * ERP_ASSET_HIST을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpAssetHistVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateErpAssetHist(ErpAssetHistVO vo) throws Exception {
        update("erpAssetHistDAO.updateErpAssetHist_S", vo);
    }

    /**
	 * ERP_ASSET_HIST을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpAssetHistVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteErpAssetHist(ErpAssetHistVO vo) throws Exception {
        delete("erpAssetHistDAO.deleteErpAssetHist_S", vo);
    }

    /**
	 * ERP_ASSET_HIST을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpAssetHistVO
	 * @return 조회한 ERP_ASSET_HIST
	 * @exception Exception
	 */
    public ErpAssetHistVO selectErpAssetHist(ErpAssetHistVO vo) throws Exception {
        return (ErpAssetHistVO) selectByPk("erpAssetHistDAO.selectErpAssetHist_S", vo);
    }

    /**
	 * ERP_ASSET_HIST 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_ASSET_HIST 목록
	 * @exception Exception
	 */
    public List selectErpAssetHistList(ErpAssetHistDefaultVO searchVO) throws Exception {
        return list("erpAssetHistDAO.selectErpAssetHistList_D", searchVO);
    }

    /**
	 * ERP_ASSET_HIST 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_ASSET_HIST 총 갯수
	 * @exception
	 */
    public int selectErpAssetHistListTotCnt(ErpAssetHistDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("erpAssetHistDAO.selectErpAssetHistListTotCnt_S", searchVO);
    }

}
