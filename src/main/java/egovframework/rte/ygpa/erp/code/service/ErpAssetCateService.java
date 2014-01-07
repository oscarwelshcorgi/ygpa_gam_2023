package egovframework.rte.ygpa.erp.code.service;

import java.util.List;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCateDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCateVO;

/**
 * @Class Name : ErpAssetCateService.java
 * @Description : ErpAssetCate Business class
 * @Modification Information
 *
 * @author 장은성
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface ErpAssetCateService {
	
	/**
	 * ERP_ASSET_CATE을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpAssetCateVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertErpAssetCate(ErpAssetCateVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_CATE을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpAssetCateVO
	 * @return void형
	 * @exception Exception
	 */
    void updateErpAssetCate(ErpAssetCateVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_CATE을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpAssetCateVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteErpAssetCate(ErpAssetCateVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_CATE을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpAssetCateVO
	 * @return 조회한 ERP_ASSET_CATE
	 * @exception Exception
	 */
    ErpAssetCateVO selectErpAssetCate(ErpAssetCateVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_CATE 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_CATE 목록
	 * @exception Exception
	 */
    List selectErpAssetCateList(ErpAssetCateDefaultVO searchVO) throws Exception;
    
    /**
	 * ERP_ASSET_CATE 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return ERP_ASSET_CATE 총 갯수
	 * @exception
	 */
    int selectErpAssetCateListTotCnt(ErpAssetCateDefaultVO searchVO);
    
}
