package egovframework.rte.ygpa.gam.oper.shed.service;

import java.util.List;

/**
 * @Class Name : GamCmmnCntrRentSttusInqireService.java
 * @Description : 공컨장치장임대현황조회 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCmmnCntrRentSttusInqireService {
	
	/**
	 * 공컨장치장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    List selectCmmnCntrRentSttusInqireList(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireSum(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 공컨장치장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대 목록 총 갯수
	 * @exception
	 */
    int selectCmmnCntrRentSttusInqireListTotCnt(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;
	
	
    /**
	 * 공컨장치장임대 최초 신청을 등록한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	void insertCmmnCntrRentSttusInqireFirst(GamCmmnCntrRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireMaxNo(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 공컨장치장임대 연장 신청을 등록한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	void insertCmmnCntrRentSttusInqireRenew(GamCmmnCntrRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대정보를 수정한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	void updateCmmnCntrRentSttusInqire(GamCmmnCntrRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrRentSttusInqireDetailList(GamCmmnCntrRentSttusInqireVO vo) throws Exception;

    /**
	 * 공컨장치장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrRentSttusInqireDetailListTotCnt(GamCmmnCntrRentSttusInqireVO vo) throws Exception;
	
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
    int selectCmmnCntrRentSttusInqireLevReqestCnt(GamCmmnCntrRentSttusInqireVO vo) throws Exception;
    
    /**
	 * 공컨장치장임대 정보를 삭제한다.
	 * @param vo GamCmmnCntrRentSttusInqireDetailVO
	 * @exception Exception
	 */
	void deleteCmmnCntrRentSttusInqire(GamCmmnCntrRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대 상세정보를 삭제한다.
	 * @param vo GamCmmnCntrRentSttusInqireDetailVO
	 * @exception Exception
	 */
	void deleteCmmnCntrRentSttusInqireDetail(GamCmmnCntrRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대 상세를 등록한다.
	 * @param vo GamCmmnCntrRentSttusInqireDetailVO
	 * @exception Exception
	 */
	void insertCmmnCntrRentSttusInqireDetail(GamCmmnCntrRentSttusInqireDetailVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대 상세를 수정한다.
	 * @param vo GamCmmnCntrRentSttusInqireDetailVO
	 * @exception Exception
	 */
	void updateCmmnCntrRentSttusInqireDetail(GamCmmnCntrRentSttusInqireDetailVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대 상세를 삭제한다.
	 * @param vo GamCmmnCntrRentSttusInqireDetailVO
	 * @exception Exception
	 */
	void deleteCmmnCntrRentSttusInqireDetail2(GamCmmnCntrRentSttusInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 공컨장치장임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대정보
	 * @exception Exception
	 */
    GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqirePrmisnInfo(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 공컨장치장임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamCmmnCntrRentSttusInqireLevReqestVO
	 * @exception Exception
	 */
	void updateCmmnCntrRentSttusInqirePrmisn(GamCmmnCntrRentSttusInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 공컨장치장임대 허가여부를 취소한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	void updateCmmnCntrRentSttusInqirePrmisnCancel(GamCmmnCntrRentSttusInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrRentSttusInqireFileList(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrRentSttusInqireFileListTotCnt(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	void insertCmmnCntrRentSttusInqireFile(GamCmmnCntrRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	void updateCmmnCntrRentSttusInqireFile(GamCmmnCntrRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	void deleteCmmnCntrRentSttusInqirePhotoSingle(GamCmmnCntrRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireMaxKey(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamCmmnCntrRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentSttusInqireComment(GamCmmnCntrRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireRenewInfo(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamCmmnCntrRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentSttusInqireRenewInfo(GamCmmnCntrRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireCurrRenewInfo(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 공컨장치장임대목록
   	 * @exception Exception
   	 */
    public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireDetailQuaycd(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamCmmnCntrRentSttusInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updateCmmnCntrRentSttusInqireQuaycd(GamCmmnCntrRentSttusInqireVO vo) throws Exception;
   	
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
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireBeforeQuarterInfo(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireCofixInfo(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;
    
}

