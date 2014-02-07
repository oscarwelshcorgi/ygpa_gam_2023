package egovframework.rte.ygpa.gam.oper.cntnr.service;

import java.util.List;

import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentFeeMngtVO;

/**
 * @Class Name : GamCntnrRentFeeMngtService.java
 * @Description : 컨테이너부두임대료관리 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCntnrRentFeeMngtService {
	
	/**
	 * 컨테이너부두임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectCntnrRentFeeMngtList(GamCntnrRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectCntnrRentFeeMngtListTotCnt(GamCntnrRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamCntnrRentFeeMngtVO selectCntnrRentFeeMngtSum(GamCntnrRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두임대료고지관리정보를 수정한다.
	 * @param vo GamCntnrRentFeeMngtVO
	 * @exception Exception
	 */
	void updateCntnrRentFeeMngt(GamCntnrRentFeeMngtVO vo) throws Exception;
	
	/**
	 * 컨테이너부두임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료고지관리정보
	 * @exception Exception
	 */
    GamCntnrRentFeeMngtVO selectCntnrRentFeeMngtInfo(GamCntnrRentFeeMngtVO searchVO) throws Exception;   
    
    /**
   	 * 세입징수 등록건수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectAnlrveLevCnt(GamCntnrRentFeeMngtVO searchVO) throws Exception;
       
    /**
   	 * 세입징수를 등록한다.
   	 * @param vo GamCntnrRentFeeMngtVO
   	 * @exception Exception
   	 */
   	void insertAnlrveLev(GamCntnrRentFeeMngtVO vo) throws Exception;
   	
   	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamCntnrRentFeeMngtVO
	 * @exception Exception
	 */
	void deleteCntnrRentFeeMngt(GamCntnrRentFeeMngtVO vo) throws Exception;
    
}
