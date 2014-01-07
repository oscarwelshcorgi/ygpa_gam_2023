package egovframework.rte.ygpa.erp.code.service;

import java.util.List;
import egovframework.rte.ygpa.erp.code.service.ErpAssetDeprctnDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetDeprctnVO;

/**
 * @Class Name : ErpAssetDeprctnService.java
 * @Description : ErpAssetDeprctn Business class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface ErpAssetDeprctnService {
	
	/**
	 * ERP_ASSET_DEPRCTN을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpAssetDeprctnVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertErpAssetDeprctn(ErpAssetDeprctnVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_DEPRCTN을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpAssetDeprctnVO
	 * @return void형
	 * @exception Exception
	 */
    void updateErpAssetDeprctn(ErpAssetDeprctnVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_DEPRCTN을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpAssetDeprctnVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteErpAssetDeprctn(ErpAssetDeprctnVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_DEPRCTN을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpAssetDeprctnVO
	 * @return 조회한 ERP_ASSET_DEPRCTN
	 * @exception Exception
	 */
    ErpAssetDeprctnVO selectErpAssetDeprctn(ErpAssetDeprctnVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_DEPRCTN 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_DEPRCTN 목록
	 * @exception Exception
	 */
    List selectErpAssetDeprctnList(ErpAssetDeprctnDefaultVO searchVO) throws Exception;
    
    /**
	 * ERP_ASSET_DEPRCTN 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_DEPRCTN 총 갯수
	 * @exception
	 */
    int selectErpAssetDeprctnListTotCnt(ErpAssetDeprctnDefaultVO searchVO);
    
}
