package egovframework.rte.ygpa.erp.code.service;

import java.util.List;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;

/**
 * @Class Name : ErpAssetCdService.java
 * @Description : ErpAssetCd Business class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface ErpAssetCdService {
	
	/**
	 * ERP_ASSET_CD을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpAssetCdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertErpAssetCd(ErpAssetCdVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_CD을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpAssetCdVO
	 * @return void형
	 * @exception Exception
	 */
    void updateErpAssetCd(ErpAssetCdVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_CD을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpAssetCdVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteErpAssetCd(ErpAssetCdVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_CD을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpAssetCdVO
	 * @return 조회한 ERP_ASSET_CD
	 * @exception Exception
	 */
    ErpAssetCdVO selectErpAssetCd(ErpAssetCdVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_CD 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_CD 목록
	 * @exception Exception
	 */
    List selectErpAssetCdList(ErpAssetCdDefaultVO searchVO) throws Exception;
    
    /**
	 * ERP_ASSET_CD 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_CD 총 갯수
	 * @exception
	 */
    int selectErpAssetCdListTotCnt(ErpAssetCdDefaultVO searchVO);
    
}
