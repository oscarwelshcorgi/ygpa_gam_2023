package egovframework.rte.ygpa.erp.cmm.service;

import java.util.List;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdClDefaultVO;
import egovframework.rte.ygpa.erp.cmm.service.ErpCmmnCdClVO;

/**
 * @Class Name : ErpCmmnCdClService.java
 * @Description : ErpCmmnCdCl Business class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface ErpCmmnCdClService {
	
	/**
	 * ERP_CMMN_CD_CL을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpCmmnCdClVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertErpCmmnCdCl(ErpCmmnCdClVO vo) throws Exception;
    
    /**
	 * ERP_CMMN_CD_CL을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpCmmnCdClVO
	 * @return void형
	 * @exception Exception
	 */
    void updateErpCmmnCdCl(ErpCmmnCdClVO vo) throws Exception;
    
    /**
	 * ERP_CMMN_CD_CL을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpCmmnCdClVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteErpCmmnCdCl(ErpCmmnCdClVO vo) throws Exception;
    
    /**
	 * ERP_CMMN_CD_CL을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpCmmnCdClVO
	 * @return 조회한 ERP_CMMN_CD_CL
	 * @exception Exception
	 */
    ErpCmmnCdClVO selectErpCmmnCdCl(ErpCmmnCdClVO vo) throws Exception;
    
    /**
	 * ERP_CMMN_CD_CL 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_CMMN_CD_CL 목록
	 * @exception Exception
	 */
    List selectErpCmmnCdClList(ErpCmmnCdClDefaultVO searchVO) throws Exception;
    
    /**
	 * ERP_CMMN_CD_CL 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_CMMN_CD_CL 총 갯수
	 * @exception
	 */
    int selectErpCmmnCdClListTotCnt(ErpCmmnCdClDefaultVO searchVO);
    
}
