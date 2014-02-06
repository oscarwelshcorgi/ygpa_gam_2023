package egovframework.rte.ygpa.gam.oper.htld.service;

import java.util.List;

import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO;

/**
 * @Class Name : GamHtldRentMngtService.java
 * @Description : 배후단지임대목록관리 (배후단지/배후단지/배후단지임대목록관리)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamHtldRentMngtService {
	
	/**
	 * 배후단지 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectHtldRentMngtList(GamHtldRentMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamHtldRentMngtVO selectHtldRentMngtSum(GamHtldRentMngtVO searchVO) throws Exception;
    
    /**
	 * 배후단지 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectHtldRentMngtListTotCnt(GamHtldRentMngtVO searchVO) throws Exception;
	
	
    /**
	 * 배후단지 최초 신청을 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	void insertHtldRentMngtFirst(GamHtldRentMngtVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamHtldRentMngtVO selectHtldRentMngtMaxNo(GamHtldRentMngtVO searchVO) throws Exception;
    
    /**
	 * 배후단지 연장 신청을 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	void insertHtldRentMngtRenew(GamHtldRentMngtVO vo) throws Exception;
	
	/**
	 * 배후단지 정보를 수정한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	void updateHtldRentMngt(GamHtldRentMngtVO vo) throws Exception;
	
	/**
	 * 배후단지 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectHtldRentMngtDetailList(GamHtldRentMngtVO vo) throws Exception;

    /**
	 * 배후단지 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldRentMngtDetailListTotCnt(GamHtldRentMngtVO vo) throws Exception;
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldRentMngtLevReqestCnt(GamHtldRentMngtVO vo) throws Exception;
    
    /**
	 * 배후단지 정보를 삭제한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteHtldRentMngt(GamHtldRentMngtVO vo) throws Exception;
	
	/**
	 * 배후단지 상세정보를 삭제한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteHtldRentMngtDetail(GamHtldRentMngtVO vo) throws Exception;
	
	/**
	 * 배후단지 상세를 등록한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	void insertHtldRentMngtDetail(GamHtldRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 배후단지 상세를 수정한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	void updateHtldRentMngtDetail(GamHtldRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 배후단지 상세를 삭제한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteHtldRentMngtDetail2(GamHtldRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대정보
	 * @exception Exception
	 */
	GamHtldRentMngtVO selectHtldRentMngtPrmisnInfo(GamHtldRentMngtVO searchVO) throws Exception;

    /**
	 * 배후단지 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyRentLevReqestVO
	 * @exception Exception
	 */
	void updateHtldRentMngtPrmisn(GamHtldRentMngtLevReqestVO vo) throws Exception;
	
	/**
	 * 배후단지 허가여부를 취소한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	void updateHtldRentMngtPrmisnCancel(GamHtldRentMngtLevReqestVO vo) throws Exception;
}
