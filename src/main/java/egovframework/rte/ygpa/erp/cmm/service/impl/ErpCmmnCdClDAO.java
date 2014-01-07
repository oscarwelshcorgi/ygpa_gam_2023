package egovframework.rte.ygpa.erp.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAErpAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdClVO;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdClDefaultVO;

/**
 * @Class Name : ErpCmmnCdClDAO.java
 * @Description : ErpCmmnCdCl DAO Class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("erpCmmnCdClDAO")
public class ErpCmmnCdClDAO extends YGPAErpAbstractDAO {

	/**
	 * ERP_CMMN_CD_CL을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpCmmnCdClVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertErpCmmnCdCl(ErpCmmnCdClVO vo) throws Exception {
        return (String)insert("erpCmmnCdClDAO.insertErpCmmnCdCl_S", vo);
    }

    /**
	 * ERP_CMMN_CD_CL을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpCmmnCdClVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateErpCmmnCdCl(ErpCmmnCdClVO vo) throws Exception {
        update("erpCmmnCdClDAO.updateErpCmmnCdCl_S", vo);
    }

    /**
	 * ERP_CMMN_CD_CL을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpCmmnCdClVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteErpCmmnCdCl(ErpCmmnCdClVO vo) throws Exception {
        delete("erpCmmnCdClDAO.deleteErpCmmnCdCl_S", vo);
    }

    /**
	 * ERP_CMMN_CD_CL을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpCmmnCdClVO
	 * @return 조회한 ERP_CMMN_CD_CL
	 * @exception Exception
	 */
    public ErpCmmnCdClVO selectErpCmmnCdCl(ErpCmmnCdClVO vo) throws Exception {
        return (ErpCmmnCdClVO) selectByPk("erpCmmnCdClDAO.selectErpCmmnCdCl_S", vo);
    }

    /**
	 * ERP_CMMN_CD_CL 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_CMMN_CD_CL 목록
	 * @exception Exception
	 */
    public List selectErpCmmnCdClList(ErpCmmnCdClDefaultVO searchVO) throws Exception {
        return list("erpCmmnCdClDAO.selectErpCmmnCdClList_D", searchVO);
    }

    /**
	 * ERP_CMMN_CD_CL 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_CMMN_CD_CL 총 갯수
	 * @exception
	 */
    public int selectErpCmmnCdClListTotCnt(ErpCmmnCdClDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("erpCmmnCdClDAO.selectErpCmmnCdClListTotCnt_S", searchVO);
    }

}
