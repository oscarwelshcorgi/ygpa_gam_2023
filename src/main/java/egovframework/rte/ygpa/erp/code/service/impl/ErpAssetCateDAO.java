package egovframework.rte.ygpa.erp.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAErpAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCateVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCateDefaultVO;

/**
 * @Class Name : ErpAssetCateDAO.java
 * @Description : ErpAssetCate DAO Class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("erpAssetCateDAO")
public class ErpAssetCateDAO extends YGPAErpAbstractDAO {

	/**
	 * ERP_ASSET_CATE을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpAssetCateVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertErpAssetCate(ErpAssetCateVO vo) throws Exception {
        return (String)insert("erpAssetCateDAO.insertErpAssetCate_S", vo);
    }

    /**
	 * ERP_ASSET_CATE을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpAssetCateVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateErpAssetCate(ErpAssetCateVO vo) throws Exception {
        update("erpAssetCateDAO.updateErpAssetCate_S", vo);
    }

    /**
	 * ERP_ASSET_CATE을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpAssetCateVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteErpAssetCate(ErpAssetCateVO vo) throws Exception {
        delete("erpAssetCateDAO.deleteErpAssetCate_S", vo);
    }

    /**
	 * ERP_ASSET_CATE을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpAssetCateVO
	 * @return 조회한 ERP_ASSET_CATE
	 * @exception Exception
	 */
    public ErpAssetCateVO selectErpAssetCate(ErpAssetCateVO vo) throws Exception {
        return (ErpAssetCateVO) selectByPk("erpAssetCateDAO.selectErpAssetCate_S", vo);
    }

    /**
	 * ERP_ASSET_CATE 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_ASSET_CATE 목록
	 * @exception Exception
	 */
    public List selectErpAssetCateList(ErpAssetCateDefaultVO searchVO) throws Exception {
        return list("erpAssetCateDAO.selectErpAssetCateList_D", searchVO);
    }

    /**
	 * ERP_ASSET_CATE 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_ASSET_CATE 총 갯수
	 * @exception
	 */
    public int selectErpAssetCateListTotCnt(ErpAssetCateDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("erpAssetCateDAO.selectErpAssetCateListTotCnt_S", searchVO);
    }

}
