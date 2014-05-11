package egovframework.rte.ygpa.gam.oper.train.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : GamTrainPortRentFeeMngtService.java
 * @Description : 철송장임대료관리 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamTrainPortRentFeeMngtService {
	
	/**
	 * 철송장임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectTrainPortRentFeeMngtList(GamTrainPortRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 철송장임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectTrainPortRentFeeMngtListTotCnt(GamTrainPortRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamTrainPortRentFeeMngtVO selectTrainPortRentFeeMngtSum(GamTrainPortRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 철송장임대료고지관리정보를 수정한다.
	 * @param vo GamTrainPortRentFeeMngtVO
	 * @exception Exception
	 */
	void updateTrainPortRentFeeMngt(GamTrainPortRentFeeMngtVO vo) throws Exception;
	
	/**
	 * 철송장임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대료고지관리정보
	 * @exception Exception
	 */
    GamTrainPortRentFeeMngtVO selectTrainPortRentFeeMngtInfo(GamTrainPortRentFeeMngtVO searchVO) throws Exception;   
    
    /**
   	 * 세입징수 등록건수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectAnlrveLevCnt(GamTrainPortRentFeeMngtVO searchVO) throws Exception;
       
    /**
   	 * 세입징수를 등록한다.
   	 * @param vo GamTrainPortRentFeeMngtVO
   	 * @exception Exception
   	 */
   	void insertAnlrveLev(GamTrainPortRentFeeMngtVO vo) throws Exception;
   	
   	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamTrainPortRentFeeMngtVO
	 * @exception Exception
	 */
	void deleteTrainPortRentFeeMngt(GamTrainPortRentFeeMngtVO vo) throws Exception;
    
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamTrainPortRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertTrainPortRentFeeMngtLevReqest(GamTrainPortRentFeeMngtVO vo) throws Exception;
	
	List selectNpticPrintInfo(Map searchVO) throws Exception;
	
	List selectTaxNtcPrintInfo(Map searchVO) throws Exception;
	
	void updateAssetRentFeeMngtListDetail(GamTrainPortRentFeeMngtVO vo) throws Exception;
	
	List selectAssetRentFeeDetailList(GamTrainPortRentFeeMngtVO searchVO);
	
	Map selectAssetRentFeeDetailMstPk(GamTrainPortRentFeeMngtVO searchVO);
	
	Map selectAssetRentFeeDetailSumPk(GamTrainPortRentFeeMngtVO searchVO);
}