package egovframework.rte.ygpa.gam.asset.service;

import java.util.List;
import java.util.Map;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;

/**
 * @Class Name : GamAssetRentService.java
 * @Description : 자산임대관리 Business class
 * @Modification Information
 *
 * @author Dev
 * @since 2013-12-20
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetRentService {
	
	/**
	 * 자산임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectAssetRentList(GamAssetRentVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetRentVO selectAssetRentSum(GamAssetRentVO searchVO) throws Exception;
    
    /**
	 * 자산임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectAssetRentListTotCnt(GamAssetRentVO searchVO) throws Exception;
	
	
    /**
	 * 자산임대 최초 신청을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	void insertAssetRentFirst(GamAssetRentVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetRentVO selectAssetRentMaxNo(GamAssetRentVO searchVO) throws Exception;
    
    /**
	 * 자산임대 연장 신청을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	void insertAssetRentRenew(GamAssetRentVO vo) throws Exception;
	
	/**
	 * 자산임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetRentDetailList(GamAssetRentDetailVO vo) throws Exception;

    /**
	 * 자산임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetRentDetailListTotCnt(GamAssetRentDetailVO vo) throws Exception;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ERP_ASSET_CD을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpAssetCdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    //String insertErpAssetCd(ErpAssetCdVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_CD을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpAssetCdVO
	 * @return void형
	 * @exception Exception
	 */
    //void updateErpAssetCd(ErpAssetCdVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_CD을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpAssetCdVO
	 * @return void형 
	 * @exception Exception
	 */
    //void deleteErpAssetCd(ErpAssetCdVO vo) throws Exception;
    
    /**
	 * ERP_ASSET_CD을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpAssetCdVO
	 * @return 조회한 ERP_ASSET_CD
	 * @exception Exception
	 */
    //ErpAssetCdVO selectErpAssetCd(ErpAssetCdVO vo) throws Exception;
    
}
