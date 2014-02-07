package egovframework.rte.ygpa.gam.oper.shed.service;

import java.util.List;

import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrShedRentFeeMngtVO;

/**
 * @Class Name : GamCmmnCntrShedRentFeeMngtService.java
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
public interface GamCmmnCntrShedRentFeeMngtService {
	
	/**
	 * 공컨장치장임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectCmmnCntrShedRentFeeMngtList(GamCmmnCntrShedRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 공컨장치장임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectCmmnCntrShedRentFeeMngtListTotCnt(GamCmmnCntrShedRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamCmmnCntrShedRentFeeMngtVO selectCmmnCntrShedRentFeeMngtSum(GamCmmnCntrShedRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 공컨장치장임대료고지관리정보를 수정한다.
	 * @param vo GamCmmnCntrShedRentFeeMngtVO
	 * @exception Exception
	 */
	void updateCmmnCntrShedRentFeeMngt(GamCmmnCntrShedRentFeeMngtVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대료고지관리정보
	 * @exception Exception
	 */
    GamCmmnCntrShedRentFeeMngtVO selectCmmnCntrShedRentFeeMngtInfo(GamCmmnCntrShedRentFeeMngtVO searchVO) throws Exception;   
    
    /**
   	 * 세입징수 등록건수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectAnlrveLevCnt(GamCmmnCntrShedRentFeeMngtVO searchVO) throws Exception;
       
    /**
   	 * 세입징수를 등록한다.
   	 * @param vo GamCmmnCntrShedRentFeeMngtVO
   	 * @exception Exception
   	 */
   	void insertAnlrveLev(GamCmmnCntrShedRentFeeMngtVO vo) throws Exception;
   	
   	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamCmmnCntrShedRentFeeMngtVO
	 * @exception Exception
	 */
	void deleteCmmnCntrShedRentFeeMngt(GamCmmnCntrShedRentFeeMngtVO vo) throws Exception;
    
}
