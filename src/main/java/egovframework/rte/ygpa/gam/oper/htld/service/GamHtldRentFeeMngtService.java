package egovframework.rte.ygpa.gam.oper.htld.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : GamHtldRentFeeMngtService.java
 * @Description : 배후단지임대료관리 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamHtldRentFeeMngtService {
	
	/**
	 * 배후단지임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectHtldRentFeeMngtList(GamHtldRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 배후단지임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectHtldRentFeeMngtListTotCnt(GamHtldRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamHtldRentFeeMngtVO selectHtldRentFeeMngtSum(GamHtldRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 배후단지임대료고지관리정보를 수정한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	void updateHtldRentFeeMngt(GamHtldRentFeeMngtVO vo) throws Exception;
	
	/**
	 * 배후단지임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대료고지관리정보
	 * @exception Exception
	 */
    GamHtldRentFeeMngtVO selectHtldRentFeeMngtInfo(GamHtldRentFeeMngtVO searchVO) throws Exception;   
    
    /**
   	 * 세입징수 등록건수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectAnlrveLevCnt(GamHtldRentFeeMngtVO searchVO) throws Exception;
       
    /**
   	 * 세입징수를 등록한다.
   	 * @param vo GamHtldRentFeeMngtVO
   	 * @exception Exception
   	 */
   	void insertAnlrveLev(GamHtldRentFeeMngtVO vo) throws Exception;
   	
   	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	void deleteHtldRentFeeMngt(GamHtldRentFeeMngtVO vo) throws Exception;
    
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertHtldRentFeeMngtLevReqest(GamHtldRentFeeMngtVO vo) throws Exception;
	
	List selectNpticPrintInfo(Map searchVO) throws Exception;
	
	List selectTaxNtcPrintInfo(Map searchVO) throws Exception;
	
	void updateAssetRentFeeMngtListDetail(GamHtldRentFeeMngtVO vo) throws Exception;
	
	List selectAssetRentFeeDetailList(GamHtldRentFeeMngtVO searchVO);
	
	Map selectAssetRentFeeDetailMstPk(GamHtldRentFeeMngtVO searchVO);
	
	Map selectAssetRentFeeDetailSumPk(GamHtldRentFeeMngtVO searchVO);
}