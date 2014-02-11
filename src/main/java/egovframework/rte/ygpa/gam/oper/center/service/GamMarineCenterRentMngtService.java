package egovframework.rte.ygpa.gam.oper.center.service;

import java.util.List;

/**
 * @Class Name : GamMarineCenterRentMngtService.java
 * @Description : 마린센터임대목록관리 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-02-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamMarineCenterRentMngtService {
	
	/**
	 * 마린센터임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    List selectMarineCenterRentList(GamMarineCenterRentMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자료수, 총면적, 총사용료
	 * @exception Exception
	 */
    GamMarineCenterRentMngtVO selectMarineCenterRentSum(GamMarineCenterRentMngtVO searchVO) throws Exception;
    
    /**
	 * 마린센터임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대 목록 총 갯수
	 * @exception
	 */
    int selectMarineCenterRentListTotCnt(GamMarineCenterRentMngtVO searchVO) throws Exception;
	
	
    /**
	 * 마린센터임대 최초 신청을 등록한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	void insertMarineCenterRentFirst(GamMarineCenterRentMngtVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    GamMarineCenterRentMngtVO selectMarineCenterRentMaxNo(GamMarineCenterRentMngtVO searchVO) throws Exception;
    
    /**
	 * 마린센터임대 연장 신청을 등록한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	void insertMarineCenterRentRenew(GamMarineCenterRentMngtVO vo) throws Exception;
	
	/**
	 * 마린센터임대정보를 수정한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	void updateMarineCenterRent(GamMarineCenterRentMngtVO vo) throws Exception;
	
	/**
	 * 마린센터임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterRentDetailList(GamMarineCenterRentMngtVO vo) throws Exception;

    /**
	 * 마린센터임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectMarineCenterRentDetailListTotCnt(GamMarineCenterRentMngtVO vo) throws Exception;
	
    /**
	 * 마린센터임대 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectMarineCenterRentLevReqestCnt(GamMarineCenterRentMngtVO vo) throws Exception;
    
    /**
	 * 마린센터임대 정보를 삭제한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	void deleteMarineCenterRent(GamMarineCenterRentMngtVO vo) throws Exception;
	
	/**
	 * 마린센터임대 상세정보를 삭제한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	void deleteMarineCenterRentDetail(GamMarineCenterRentMngtVO vo) throws Exception;
	
	/**
	 * 마린센터임대 상세를 등록한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	void insertMarineCenterRentDetail(GamMarineCenterRentDetailVO vo) throws Exception;
	
	/**
	 * 마린센터임대 상세를 수정한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	void updateMarineCenterRentDetail(GamMarineCenterRentDetailVO vo) throws Exception;
	
	/**
	 * 마린센터임대 상세를 삭제한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	void deleteMarineCenterRentDetail2(GamMarineCenterRentDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 마린센터임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대정보
	 * @exception Exception
	 */
    GamMarineCenterRentMngtVO selectMarineCenterRentPrmisnInfo(GamMarineCenterRentMngtVO searchVO) throws Exception;

    /**
	 * 마린센터임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamMarineCenterRentLevReqestVO
	 * @exception Exception
	 */
	void updateMarineCenterRentPrmisn(GamMarineCenterRentLevReqestVO vo) throws Exception;
	
	
	/**
	 * 마린센터임대 허가여부를 취소한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	void updateMarineCenterRentPrmisnCancel(GamMarineCenterRentLevReqestVO vo) throws Exception;
    
}
