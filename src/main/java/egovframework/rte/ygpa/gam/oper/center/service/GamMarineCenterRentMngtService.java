package egovframework.rte.ygpa.gam.oper.center.service;

import java.util.List;

/**
 * @Class Name : GamMarineCenterRentMngtService.java
 * @Description : 마린센터임대관리 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
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
	 * @return 마린센터임대목록
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
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterRentFileList(GamMarineCenterRentMngtVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectMarineCenterRentFileListTotCnt(GamMarineCenterRentMngtVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	void insertMarineCenterRentFile(GamMarineCenterRentMngtVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	void updateMarineCenterRentFile(GamMarineCenterRentMngtVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	void deleteMarineCenterRentPhotoSingle(GamMarineCenterRentMngtVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentMngtVO selectMarineCenterRentMaxKey(GamMarineCenterRentMngtVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentComment(GamMarineCenterRentMngtVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentMngtVO selectMarineCenterRentRenewInfo(GamMarineCenterRentMngtVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentRenewInfo(GamMarineCenterRentMngtVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentMngtVO selectMarineCenterRentCurrRenewInfo(GamMarineCenterRentMngtVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 마린센터임대목록
   	 * @exception Exception
   	 */
    public GamMarineCenterRentMngtVO selectMarineCenterRentDetailQuaycd(GamMarineCenterRentMngtVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamMarineCenterRentDetailVO
   	 * @exception Exception
   	 */
   	public void updateMarineCenterRentQuaycd(GamMarineCenterRentMngtVO vo) throws Exception;
   	
   	/**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception;
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentMngtVO selectMarineCenterRentBeforeQuarterInfo(GamMarineCenterRentMngtVO searchVO) throws Exception;
    
    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentMngtVO selectMarineCenterRentCofixInfo(GamMarineCenterRentMngtVO searchVO) throws Exception;
    
}
