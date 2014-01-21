package egovframework.rte.ygpa.gam.oper.gnrl.service;

import java.util.List;

/**
 * @Class Name : GamPrtOperRentMngtService.java
 * @Description : 항만시설사용목록관리 
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamPrtOperRentMngtService {
	
	/**
	 * 항만시설 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectPrtOperRentMngtList(GamPrtOperRentMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamPrtOperRentMngtVO selectPrtOperRentMngtSum(GamPrtOperRentMngtVO searchVO) throws Exception;
    
    /**
	 * 항만시설 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectPrtOperRentMngtListTotCnt(GamPrtOperRentMngtVO searchVO) throws Exception;
	
	
    /**
	 * 항만시설 최초 신청을 등록한다.
	 * @param vo GamPrtOperRentMngtVO
	 * @exception Exception
	 */
	void insertPrtOperRentMngtFirst(GamPrtOperRentMngtVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamPrtOperRentMngtVO selectPrtOperRentMngtMaxNo(GamPrtOperRentMngtVO searchVO) throws Exception;
    
    /**
	 * 항만시설 연장 신청을 등록한다.
	 * @param vo GamPrtOperRentMngtVO
	 * @exception Exception
	 */
	void insertPrtOperRentMngtRenew(GamPrtOperRentMngtVO vo) throws Exception;
	
	/**
	 * 항만시설 정보를 수정한다.
	 * @param vo GamPrtOperRentMngtVO
	 * @exception Exception
	 */
	void updatePrtOperRentMngt(GamPrtOperRentMngtVO vo) throws Exception;
	
	/**
	 * 항만시설 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtOperRentMngtDetailList(GamPrtOperRentMngtVO vo) throws Exception;

    /**
	 * 항만시설 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtOperRentMngtDetailListTotCnt(GamPrtOperRentMngtVO vo) throws Exception;
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtOperRentMngtLevReqestCnt(GamPrtOperRentMngtVO vo) throws Exception;
    
    /**
	 * 항만시설 정보를 삭제한다.
	 * @param vo GamPrtOperRentMngtDetailVO
	 * @exception Exception
	 */
	void deletePrtOperRentMngt(GamPrtOperRentMngtVO vo) throws Exception;
	
	/**
	 * 항만시설 상세정보를 삭제한다.
	 * @param vo GamPrtOperRentMngtDetailVO
	 * @exception Exception
	 */
	void deletePrtOperRentMngtDetail(GamPrtOperRentMngtVO vo) throws Exception;
	
	/**
	 * 항만시설 상세를 등록한다.
	 * @param vo GamPrtOperRentMngtDetailVO
	 * @exception Exception
	 */
	void insertPrtOperRentMngtDetail(GamPrtOperRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 항만시설 상세를 수정한다.
	 * @param vo GamPrtOperRentMngtDetailVO
	 * @exception Exception
	 */
	void updatePrtOperRentMngtDetail(GamPrtOperRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 항만시설 상세를 삭제한다.
	 * @param vo GamPrtOperRentMngtDetailVO
	 * @exception Exception
	 */
	void deletePrtOperRentMngtDetail2(GamPrtOperRentMngtDetailVO vo) throws Exception;
	
}
