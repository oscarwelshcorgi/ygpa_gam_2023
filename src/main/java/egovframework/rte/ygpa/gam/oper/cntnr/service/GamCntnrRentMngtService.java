package egovframework.rte.ygpa.gam.oper.cntnr.service;

import java.util.List;

import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentMngtVO;

/**
 * @Class Name : GamCntnrRentMngtService.java
 * @Description : 컨테이너부두임대목록관리 (컨테이너부두/컨테이너부두/컨테이너부두임대목록관리)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCntnrRentMngtService {
	
	/**
	 * 컨테이너부두 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectCntnrRentMngtList(GamCntnrRentMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamCntnrRentMngtVO selectCntnrRentMngtSum(GamCntnrRentMngtVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectCntnrRentMngtListTotCnt(GamCntnrRentMngtVO searchVO) throws Exception;
	
	
    /**
	 * 컨테이너부두 최초 신청을 등록한다.
	 * @param vo GamCntnrRentMngtVO
	 * @exception Exception
	 */
	void insertCntnrRentMngtFirst(GamCntnrRentMngtVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamCntnrRentMngtVO selectCntnrRentMngtMaxNo(GamCntnrRentMngtVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두 연장 신청을 등록한다.
	 * @param vo GamCntnrRentMngtVO
	 * @exception Exception
	 */
	void insertCntnrRentMngtRenew(GamCntnrRentMngtVO vo) throws Exception;
	
	/**
	 * 컨테이너부두 정보를 수정한다.
	 * @param vo GamCntnrRentMngtVO
	 * @exception Exception
	 */
	void updateCntnrRentMngt(GamCntnrRentMngtVO vo) throws Exception;
	
	/**
	 * 컨테이너부두 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCntnrRentMngtDetailList(GamCntnrRentMngtVO vo) throws Exception;

    /**
	 * 컨테이너부두 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCntnrRentMngtDetailListTotCnt(GamCntnrRentMngtVO vo) throws Exception;
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCntnrRentMngtLevReqestCnt(GamCntnrRentMngtVO vo) throws Exception;
    
    /**
	 * 컨테이너부두 정보를 삭제한다.
	 * @param vo GamCntnrRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteCntnrRentMngt(GamCntnrRentMngtVO vo) throws Exception;
	
	/**
	 * 컨테이너부두 상세정보를 삭제한다.
	 * @param vo GamCntnrRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteCntnrRentMngtDetail(GamCntnrRentMngtVO vo) throws Exception;
	
	/**
	 * 컨테이너부두 상세를 등록한다.
	 * @param vo GamCntnrRentMngtDetailVO
	 * @exception Exception
	 */
	void insertCntnrRentMngtDetail(GamCntnrRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 컨테이너부두 상세를 수정한다.
	 * @param vo GamCntnrRentMngtDetailVO
	 * @exception Exception
	 */
	void updateCntnrRentMngtDetail(GamCntnrRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 컨테이너부두 상세를 삭제한다.
	 * @param vo GamCntnrRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteCntnrRentMngtDetail2(GamCntnrRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대정보
	 * @exception Exception
	 */
	GamCntnrRentMngtVO selectCntnrRentMngtPrmisnInfo(GamCntnrRentMngtVO searchVO) throws Exception;

    /**
	 * 컨테이너부두 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyRentLevReqestVO
	 * @exception Exception
	 */
	void updateCntnrRentMngtPrmisn(GamCntnrRentMngtLevReqestVO vo) throws Exception;
	
	/**
	 * 컨테이너부두 허가여부를 취소한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	void updateCntnrRentMngtPrmisnCancel(GamCntnrRentMngtLevReqestVO vo) throws Exception;
}
