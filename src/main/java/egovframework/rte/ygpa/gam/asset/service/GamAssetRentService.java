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
 * @author 정윤후
 * @since 2014-01-10
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
	 * 자산임대정보를 수정한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	void updateAssetRent(GamAssetRentVO vo) throws Exception;
	
	/**
	 * 자산임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetRentDetailList(GamAssetRentVO vo) throws Exception;

    /**
	 * 자산임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetRentDetailListTotCnt(GamAssetRentVO vo) throws Exception;
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetRentLevReqestCnt(GamAssetRentVO vo) throws Exception;
    
    /**
	 * 자산임대 정보를 삭제한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	void deleteAssetRent(GamAssetRentVO vo) throws Exception;
	
	/**
	 * 자산임대 상세정보를 삭제한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	void deleteAssetRentDetail(GamAssetRentVO vo) throws Exception;
	
	/**
	 * 자산임대 상세를 등록한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	void insertAssetRentDetail(GamAssetRentDetailVO vo) throws Exception;
	
	/**
	 * 자산임대 상세를 수정한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	void updateAssetRentDetail(GamAssetRentDetailVO vo) throws Exception;
	
	/**
	 * 자산임대 상세를 삭제한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	void deleteAssetRentDetail2(GamAssetRentDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대정보
	 * @exception Exception
	 */
    GamAssetRentVO selectAssetRentPrmisnInfo(GamAssetRentVO searchVO) throws Exception;

    /**
	 * 자산임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamAssetRentLevReqestVO
	 * @exception Exception
	 */
	void updateAssetRentPrmisn(GamAssetRentLevReqestVO vo) throws Exception;
    
}
