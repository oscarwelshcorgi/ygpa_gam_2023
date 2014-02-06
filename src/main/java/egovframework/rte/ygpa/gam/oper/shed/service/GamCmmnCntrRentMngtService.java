package egovframework.rte.ygpa.gam.oper.shed.service;

import java.util.List;

import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtVO;

/**
 * @Class Name : GamCmmnCntrRentMngtService.java
 * @Description : 공컨장치장임대목록관리 (공컨장치장/공컨장치장/공컨장치장임대목록관리)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCmmnCntrRentMngtService {
	
	/**
	 * 공컨장치장 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectCmmnCntrRentMngtList(GamCmmnCntrRentMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtSum(GamCmmnCntrRentMngtVO searchVO) throws Exception;
    
    /**
	 * 공컨장치장 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectCmmnCntrRentMngtListTotCnt(GamCmmnCntrRentMngtVO searchVO) throws Exception;
	
	
    /**
	 * 공컨장치장 최초 신청을 등록한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	void insertCmmnCntrRentMngtFirst(GamCmmnCntrRentMngtVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtMaxNo(GamCmmnCntrRentMngtVO searchVO) throws Exception;
    
    /**
	 * 공컨장치장 연장 신청을 등록한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	void insertCmmnCntrRentMngtRenew(GamCmmnCntrRentMngtVO vo) throws Exception;
	
	/**
	 * 공컨장치장 정보를 수정한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	void updateCmmnCntrRentMngt(GamCmmnCntrRentMngtVO vo) throws Exception;
	
	/**
	 * 공컨장치장 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrRentMngtDetailList(GamCmmnCntrRentMngtVO vo) throws Exception;

    /**
	 * 공컨장치장 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrRentMngtDetailListTotCnt(GamCmmnCntrRentMngtVO vo) throws Exception;
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrRentMngtLevReqestCnt(GamCmmnCntrRentMngtVO vo) throws Exception;
    
    /**
	 * 공컨장치장 정보를 삭제한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteCmmnCntrRentMngt(GamCmmnCntrRentMngtVO vo) throws Exception;
	
	/**
	 * 공컨장치장 상세정보를 삭제한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteCmmnCntrRentMngtDetail(GamCmmnCntrRentMngtVO vo) throws Exception;
	
	/**
	 * 공컨장치장 상세를 등록한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	void insertCmmnCntrRentMngtDetail(GamCmmnCntrRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 공컨장치장 상세를 수정한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	void updateCmmnCntrRentMngtDetail(GamCmmnCntrRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 공컨장치장 상세를 삭제한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteCmmnCntrRentMngtDetail2(GamCmmnCntrRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대정보
	 * @exception Exception
	 */
	GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtPrmisnInfo(GamCmmnCntrRentMngtVO searchVO) throws Exception;

    /**
	 * 공컨장치장 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyRentLevReqestVO
	 * @exception Exception
	 */
	void updateCmmnCntrRentMngtPrmisn(GamCmmnCntrRentMngtLevReqestVO vo) throws Exception;
	
	/**
	 * 공컨장치장 허가여부를 취소한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	void updateCmmnCntrRentMngtPrmisnCancel(GamCmmnCntrRentMngtLevReqestVO vo) throws Exception;
}
