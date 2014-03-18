package egovframework.rte.ygpa.eap.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : EapGwCallInterfaceService.java
 * @Description : EapGwCallInterface Business class
 * @Modification Information
 *
 * @author 장은성
 * @since 2014-03-17
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface EapGwCallInterfaceService {

	/**
	 * ERP_CMMN_CD_CL을 등록한다.
	 * @param vo - 등록할 정보가 담긴 EapGwCallInterfaceVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertEapGwCallInterface(Map<String, Object> vo) throws Exception;

    /**
	 * ERP_CMMN_CD_CL을 수정한다.
	 * @param vo - 수정할 정보가 담긴 EapGwCallInterfaceVO
	 * @return void형
	 * @exception Exception
	 */
    void updateEapGwCallInterface(Map<String, Object> vo) throws Exception;

    /**
	 * ERP_CMMN_CD_CL을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 EapGwCallInterfaceVO
	 * @return void형
	 * @exception Exception
	 */
    void deleteEapGwCallInterface(Map<String, Object> vo) throws Exception;

    /**
	 * ERP_CMMN_CD_CL을 조회한다.
	 * @param vo - 조회할 정보가 담긴 EapGwCallInterfaceVO
	 * @return 조회한 ERP_CMMN_CD_CL
	 * @exception Exception
	 */
    EgovMap selectEapGwCallInterface(Map<String, Object> vo) throws Exception;

    /**
	 * ERP_CMMN_CD_CL 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_CMMN_CD_CL 목록
	 * @exception Exception
	 */
    List selectEapGwCallInterfaceList(Map<String, Object> searchVO) throws Exception;

    /**
	 * ERP_CMMN_CD_CL 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_CMMN_CD_CL 총 갯수
	 * @exception
	 */
    int selectEapGwCallInterfaceListTotCnt(Map<String, Object> searchVO);

}
