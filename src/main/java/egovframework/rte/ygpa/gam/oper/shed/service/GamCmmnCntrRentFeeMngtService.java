package egovframework.rte.ygpa.gam.oper.shed.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : GamCmmnCntrRentFeeMngtService.java
 * @Description : 공컨장치장임대료관리 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCmmnCntrRentFeeMngtService {
	
	/**
	 * 공컨장치장임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectCmmnCntrRentFeeMngtList(GamCmmnCntrRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 공컨장치장임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectCmmnCntrRentFeeMngtListTotCnt(GamCmmnCntrRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamCmmnCntrRentFeeMngtVO selectCmmnCntrRentFeeMngtSum(GamCmmnCntrRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 공컨장치장임대료고지관리정보를 수정한다.
	 * @param vo GamCmmnCntrRentFeeMngtVO
	 * @exception Exception
	 */
	void updateCmmnCntrRentFeeMngt(GamCmmnCntrRentFeeMngtVO vo) throws Exception;

	/**
	 * 임대고지 사용료를 변경한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updateCmmnCntrRentFee(GamCmmnCntrRentFeeMngtVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대료고지관리정보
	 * @exception Exception
	 */
    GamCmmnCntrRentFeeMngtVO selectCmmnCntrRentFeeMngtInfo(GamCmmnCntrRentFeeMngtVO searchVO) throws Exception;   
    
    /**
   	 * 세입징수 등록건수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectAnlrveLevCnt(GamCmmnCntrRentFeeMngtVO searchVO) throws Exception;
       
    /**
   	 * 세입징수를 등록한다.
   	 * @param vo GamCmmnCntrRentFeeMngtVO
   	 * @exception Exception
   	 */
   	void insertAnlrveLev(GamCmmnCntrRentFeeMngtVO vo) throws Exception;
   	
   	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamCmmnCntrRentFeeMngtVO
	 * @exception Exception
	 */
	void deleteCmmnCntrRentFeeMngt(GamCmmnCntrRentFeeMngtVO vo) throws Exception;
    
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamCmmnCntrRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentFeeMngtLevReqest(GamCmmnCntrRentFeeMngtVO vo) throws Exception;

	Map selectNpticPrintInfo(GamCmmnCntrRentFeeMngtVO searchVO) throws Exception;

	/**
	 * 연체금만 있는 고지서를 출력한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List selectNpticPrintInfo2(Map searchVO) throws Exception;

	List selectTaxNtcPrintInfo(Map searchVO) throws Exception;
	
	void updateAssetRentFeeMngtListDetail(GamCmmnCntrRentFeeMngtVO vo) throws Exception;
	
	List selectAssetRentFeeDetailList(GamCmmnCntrRentFeeMngtVO searchVO);
	
	Map selectAssetRentFeeDetailMstPk(GamCmmnCntrRentFeeMngtVO searchVO);
	
	Map selectAssetRentFeeDetailSumPk(GamCmmnCntrRentFeeMngtVO searchVO);
}