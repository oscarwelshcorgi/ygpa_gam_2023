package egovframework.rte.ygpa.gam.oper.gnrl.service;

import java.util.List;

import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtLevReqestVO;

/**
 * @Class Name : GamPrtFcltyRentMngtService.java
 * @Description : 항만시설사용목록관리 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamPrtFcltyRentMngtService {
	
	/**
	 * 항만시설사용 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    List selectPrtFcltyRentMngtList(GamPrtFcltyRentMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtSum(GamPrtFcltyRentMngtVO searchVO) throws Exception;
    
    /**
	 * 항만시설사용 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록 총 갯수
	 * @exception
	 */
    int selectPrtFcltyRentMngtListTotCnt(GamPrtFcltyRentMngtVO searchVO) throws Exception;
	
	
    /**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void insertPrtFcltyRentMngtFirst(GamPrtFcltyRentMngtVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtMaxNo(GamPrtFcltyRentMngtVO searchVO) throws Exception;
    
    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void insertPrtFcltyRentMngtRenew(GamPrtFcltyRentMngtVO vo) throws Exception;
	
	/**
	 * 항만시설사용정보를 수정한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void updatePrtFcltyRentMngt(GamPrtFcltyRentMngtVO vo) throws Exception;
	
	/**
	 * 항만시설사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyRentMngtDetailList(GamPrtFcltyRentMngtVO vo) throws Exception;

    /**
	 * 항만시설사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyRentMngtDetailListTotCnt(GamPrtFcltyRentMngtVO vo) throws Exception;
	
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception;
    
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyRentMngtLevReqestCnt(GamPrtFcltyRentMngtVO vo) throws Exception;
    
    /**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	void deletePrtFcltyRentMngt(GamPrtFcltyRentMngtVO vo) throws Exception;
	
	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	void deletePrtFcltyRentMngtDetail(GamPrtFcltyRentMngtVO vo) throws Exception;
	
	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	void insertPrtFcltyRentMngtDetail(GamPrtFcltyRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	void updatePrtFcltyRentMngtDetail(GamPrtFcltyRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	void deletePrtFcltyRentMngtDetail2(GamPrtFcltyRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
    GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtPrmisnInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 항만시설사용 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyRentMngtLevReqestVO
	 * @exception Exception
	 */
	void updatePrtFcltyRentMngtPrmisn(GamPrtFcltyRentMngtLevReqestVO vo) throws Exception;
	
	
	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void updatePrtFcltyRentMngtPrmisnCancel(GamPrtFcltyRentMngtLevReqestVO vo) throws Exception;
    
}
