package egovframework.rte.ygpa.erp.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAErpAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdVO;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;

/**
 * @Class Name : ErpCmmnCdDAO.java
 * @Description : ErpCmmnCd DAO Class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("erpCmmnCdDAO")
public class ErpCmmnCdDAO extends YGPAErpAbstractDAO {

	/**
	 * ERP_CMMN_CD을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpCmmnCdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertErpCmmnCd(ErpCmmnCdVO vo) throws Exception {
        return (String)insert("erpCmmnCdDAO.insertErpCmmnCd_S", vo);
    }

    /**
	 * ERP_CMMN_CD을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpCmmnCdVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateErpCmmnCd(ErpCmmnCdVO vo) throws Exception {
        update("erpCmmnCdDAO.updateErpCmmnCd_S", vo);
    }

    /**
	 * ERP_CMMN_CD을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpCmmnCdVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteErpCmmnCd(ErpCmmnCdVO vo) throws Exception {
        delete("erpCmmnCdDAO.deleteErpCmmnCd_S", vo);
    }

    /**
	 * ERP_CMMN_CD을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpCmmnCdVO
	 * @return 조회한 ERP_CMMN_CD
	 * @exception Exception
	 */
    public ErpCmmnCdVO selectErpCmmnCd(ErpCmmnCdVO vo) throws Exception {
        return (ErpCmmnCdVO) selectByPk("erpCmmnCdDAO.selectErpCmmnCd_S", vo);
    }

    /**
	 * ERP_CMMN_CD 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_CMMN_CD 목록
	 * @exception Exception
	 */
    public List selectErpCmmnCdList(ErpCmmnCdDefaultVO searchVO) throws Exception {
        return list("erpCmmnCdDAO.selectErpCmmnCdList_D", searchVO);
    }

    /**
	 * ERP_CMMN_CD 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_CMMN_CD 총 갯수
	 * @exception
	 */
    public int selectErpCmmnCdListTotCnt(ErpCmmnCdDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("erpCmmnCdDAO.selectErpCmmnCdListTotCnt_S", searchVO);
    }

}
