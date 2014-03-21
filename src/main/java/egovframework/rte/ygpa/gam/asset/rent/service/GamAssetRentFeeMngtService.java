package egovframework.rte.ygpa.gam.asset.rent.service;

import java.util.List;
import java.util.Map;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;

/**
 * @Class Name : GamAssetRentFeeMngtService.java
 * @Description : 자산임대료고지관리 Business class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetRentFeeMngtService {
	
	/**
	 * 자산임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectAssetRentFeeList(GamAssetRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 자산임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectAssetRentFeeListTotCnt(GamAssetRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetRentFeeMngtVO selectAssetRentFeeSum(GamAssetRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 자산임대료고지관리정보를 수정한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	void updateAssetRentFee(GamAssetRentFeeMngtVO vo) throws Exception;
	
	/**
	 * 자산임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료고지관리정보
	 * @exception Exception
	 */
    GamAssetRentFeeMngtVO selectAssetRentFeeInfo(GamAssetRentFeeMngtVO searchVO) throws Exception;   
    
    /**
   	 * 세입징수 등록건수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectAnlrveLevCnt(GamAssetRentFeeMngtVO searchVO) throws Exception;
       
    /**
   	 * 세입징수를 등록한다.
   	 * @param vo GamAssetRentFeeMngtVO
   	 * @exception Exception
   	 */
   	void insertAnlrveLev(GamAssetRentFeeMngtVO vo) throws Exception;
   	
   	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	void deleteAssetRentFee(GamAssetRentFeeMngtVO vo) throws Exception;
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAssetRentLevReqest(GamAssetRentFeeMngtVO vo) throws Exception;
    
}
