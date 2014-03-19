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

    String insertEapGwCallInterface(Map<String, Object> vo) throws Exception;

    void updateEapGwCallInterface(Map<String, Object> vo) throws Exception;

    void deleteEapGwCallInterface(Map<String, Object> vo) throws Exception;

    EgovMap selectEapGwCallInterface(Map<String, Object> vo) throws Exception;

    void eApprovalTest(Map<String, Object> vo) throws Exception;

    List selectEapGwCallInterfaceList(Map<String, Object> searchVO) throws Exception;

    int selectEapGwCallInterfaceListTotCnt(Map<String, Object> searchVO);

}
