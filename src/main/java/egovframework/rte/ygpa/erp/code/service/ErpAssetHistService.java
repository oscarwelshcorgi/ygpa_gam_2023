package egovframework.rte.ygpa.erp.code.service;

import java.util.List;
import egovframework.rte.ygpa.erp.code.service.ErpAssetHistDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetHistVO;

/**
 * @Class Name : ErpAssetHistService.java
 * @Description : ErpAssetHist Business class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface ErpAssetHistService {
	
	/**
	 * ERP_ASSET_HIST을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpAssetHistVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertErpAssetHist(ErpAssetHistVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_HIST을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpAssetHistVO
	 * @return void형
	 * @exception Exception
	 */
    void updateErpAssetHist(ErpAssetHistVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_HIST을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpAssetHistVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteErpAssetHist(ErpAssetHistVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_HIST을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpAssetHistVO
	 * @return 조회한 ERP_ASSET_HIST
	 * @exception Exception
	 */
    ErpAssetHistVO selectErpAssetHist(ErpAssetHistVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_HIST 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_HIST 목록
	 * @exception Exception
	 */
    List selectErpAssetHistList(ErpAssetHistDefaultVO searchVO) throws Exception;
    
    /**
	 * ERP_ASSET_HIST 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_HIST 총 갯수
	 * @exception
	 */
    int selectErpAssetHistListTotCnt(ErpAssetHistDefaultVO searchVO);
    
}
