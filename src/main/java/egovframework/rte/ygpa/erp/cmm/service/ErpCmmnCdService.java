package egovframework.rte.ygpa.erp.cmm.service;

import java.util.List;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdDefaultVO;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdVO;

/**
 * @Class Name : ErpCmmnCdService.java
 * @Description : ErpCmmnCd Business class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface ErpCmmnCdService {
	
	/**
	 * ERP_CMMN_CD을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpCmmnCdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertErpCmmnCd(ErpCmmnCdVO vo) throws Exception;
    
    /**
	 * ERP_CMMN_CD을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpCmmnCdVO
	 * @return void형
	 * @exception Exception
	 */
    void updateErpCmmnCd(ErpCmmnCdVO vo) throws Exception;
    
    /**
	 * ERP_CMMN_CD을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpCmmnCdVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteErpCmmnCd(ErpCmmnCdVO vo) throws Exception;
    
    /**
	 * ERP_CMMN_CD을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpCmmnCdVO
	 * @return 조회한 ERP_CMMN_CD
	 * @exception Exception
	 */
    ErpCmmnCdVO selectErpCmmnCd(ErpCmmnCdVO vo) throws Exception;
    
    /**
	 * ERP_CMMN_CD 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_CMMN_CD 목록
	 * @exception Exception
	 */
    List selectErpCmmnCdList(ErpCmmnCdDefaultVO searchVO) throws Exception;
    
    /**
	 * ERP_CMMN_CD 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_CMMN_CD 총 갯수
	 * @exception
	 */
    int selectErpCmmnCdListTotCnt(ErpCmmnCdDefaultVO searchVO);
    
}
