package egovframework.rte.ygpa.eap.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.cmmn.dataaccess.YGPAErpAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : GwCallInterfaceDAO.java
 * @Description : GwCallInterface DAO Class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Repository("eapGwCallInterfaceDAO")
public class EapGwCallInterfaceDAO extends YGPAAbstractDAO {

	/**
	 * ERP_ASSET_CATE을 등록한다.
	 * @param vo - 등록할 정보가 담긴 GwCallInterfaceVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertGwCallInterface(Map<String, Object> vo) throws Exception {
        return (String)insert("eapGwCallInterfceDAO.insertGwCallInterface_S", vo);
    }

    /**
	 * ERP_ASSET_CATE을 수정한다.
	 * @param vo - 수정할 정보가 담긴 GwCallInterfaceVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateGwCallInterface(Map<String, Object> vo) throws Exception {
        update("eapGwCallInterfceDAO.updateGwCallInterface_S", vo);
    }

    /**
	 * ERP_ASSET_CATE을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 GwCallInterfaceVO
	 * @return void형
	 * @exception Exception
	 */
    public void deleteGwCallInterface(Map<String, Object> vo) throws Exception {
        delete("eapGwCallInterfceDAO.deleteGwCallInterface_S", vo);
    }

    /**
	 * ERP_ASSET_CATE을 조회한다.
	 * @param vo - 조회할 정보가 담긴 GwCallInterfaceVO
	 * @return 조회한 ERP_ASSET_CATE
	 * @exception Exception
	 */
    public EgovMap selectGwCallInterface(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("eapGwCallInterfceDAO.selectGwCallInterface_S", vo);
    }

    /**
	 * ERP_ASSET_CATE 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_ASSET_CATE 목록
	 * @exception Exception
	 */
    public List selectGwCallInterfaceList(Map<String, Object> searchVO) throws Exception {
        return list("eapGwCallInterfceDAO.selectGwCallInterfaceList_D", searchVO);
    }

    /**
	 * ERP_ASSET_CATE 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return ERP_ASSET_CATE 총 갯수
	 * @exception
	 */
    public int selectGwCallInterfaceListTotCnt(Map<String, Object> searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("eapGwCallInterfceDAO.selectGwCallInterfaceListTotCnt_S", searchVO);
    }

}
