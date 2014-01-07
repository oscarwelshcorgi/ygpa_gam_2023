package egovframework.rte.ygpa.erp.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAErpAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetDeprctnVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetDeprctnDefaultVO;

/**
 * @Class Name : ErpAssetDeprctnDAO.java
 * @Description : ErpAssetDeprctn DAO Class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("erpAssetDeprctnDAO")
public class ErpAssetDeprctnDAO extends YGPAErpAbstractDAO {

	/**
	 * ERP_ASSET_DEPRCTN을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpAssetDeprctnVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertErpAssetDeprctn(ErpAssetDeprctnVO vo) throws Exception {
        return (String)insert("erpAssetDeprctnDAO.insertErpAssetDeprctn_S", vo);
    }

    /**
	 * ERP_ASSET_DEPRCTN을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpAssetDeprctnVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateErpAssetDeprctn(ErpAssetDeprctnVO vo) throws Exception {
        update("erpAssetDeprctnDAO.updateErpAssetDeprctn_S", vo);
    }

    /**
	 * ERP_ASSET_DEPRCTN을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpAssetDeprctnVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteErpAssetDeprctn(ErpAssetDeprctnVO vo) throws Exception {
        delete("erpAssetDeprctnDAO.deleteErpAssetDeprctn_S", vo);
    }

    /**
	 * ERP_ASSET_DEPRCTN을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpAssetDeprctnVO
	 * @return 조회한 ERP_ASSET_DEPRCTN
	 * @exception Exception
	 */
    public ErpAssetDeprctnVO selectErpAssetDeprctn(ErpAssetDeprctnVO vo) throws Exception {
        return (ErpAssetDeprctnVO) selectByPk("erpAssetDeprctnDAO.selectErpAssetDeprctn_S", vo);
    }

    /**
	 * ERP_ASSET_DEPRCTN 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_ASSET_DEPRCTN 목록
	 * @exception Exception
	 */
    public List selectErpAssetDeprctnList(ErpAssetDeprctnDefaultVO searchVO) throws Exception {
        return list("erpAssetDeprctnDAO.selectErpAssetDeprctnList_D", searchVO);
    }

    /**
	 * ERP_ASSET_DEPRCTN 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_ASSET_DEPRCTN 총 갯수
	 * @exception
	 */
    public int selectErpAssetDeprctnListTotCnt(ErpAssetDeprctnDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("erpAssetDeprctnDAO.selectErpAssetDeprctnListTotCnt_S", searchVO);
    }

}
