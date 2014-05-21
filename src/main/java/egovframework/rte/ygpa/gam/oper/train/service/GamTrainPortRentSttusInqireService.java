package egovframework.rte.ygpa.gam.oper.train.service;

import java.util.List;

/**
 * @Class Name : GamTrainPortRentSttusInqireService.java
 * @Description : 철송장임대현황조회 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamTrainPortRentSttusInqireService {
	
	/**
	 * 철송장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    List selectTrainPortRentSttusInqireList(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireSum(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 철송장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대 목록 총 갯수
	 * @exception
	 */
    int selectTrainPortRentSttusInqireListTotCnt(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
	
	
    /**
	 * 철송장임대 최초 신청을 등록한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	void insertTrainPortRentSttusInqireFirst(GamTrainPortRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireMaxNo(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 철송장임대 연장 신청을 등록한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	void insertTrainPortRentSttusInqireRenew(GamTrainPortRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 철송장임대정보를 수정한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	void updateTrainPortRentSttusInqire(GamTrainPortRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortRentSttusInqireDetailList(GamTrainPortRentSttusInqireVO vo) throws Exception;

    /**
	 * 철송장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortRentSttusInqireDetailListTotCnt(GamTrainPortRentSttusInqireVO vo) throws Exception;
	
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
    int selectTrainPortRentSttusInqireLevReqestCnt(GamTrainPortRentSttusInqireVO vo) throws Exception;
    
    /**
	 * 철송장임대 정보를 삭제한다.
	 * @param vo GamTrainPortRentSttusInqireDetailVO
	 * @exception Exception
	 */
	void deleteTrainPortRentSttusInqire(GamTrainPortRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세정보를 삭제한다.
	 * @param vo GamTrainPortRentSttusInqireDetailVO
	 * @exception Exception
	 */
	void deleteTrainPortRentSttusInqireDetail(GamTrainPortRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세를 등록한다.
	 * @param vo GamTrainPortRentSttusInqireDetailVO
	 * @exception Exception
	 */
	void insertTrainPortRentSttusInqireDetail(GamTrainPortRentSttusInqireDetailVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세를 수정한다.
	 * @param vo GamTrainPortRentSttusInqireDetailVO
	 * @exception Exception
	 */
	void updateTrainPortRentSttusInqireDetail(GamTrainPortRentSttusInqireDetailVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세를 삭제한다.
	 * @param vo GamTrainPortRentSttusInqireDetailVO
	 * @exception Exception
	 */
	void deleteTrainPortRentSttusInqireDetail2(GamTrainPortRentSttusInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 철송장임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대정보
	 * @exception Exception
	 */
    GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqirePrmisnInfo(GamTrainPortRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 철송장임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamTrainPortRentSttusInqireLevReqestVO
	 * @exception Exception
	 */
	void updateTrainPortRentSttusInqirePrmisn(GamTrainPortRentSttusInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 철송장임대 허가여부를 취소한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	void updateTrainPortRentSttusInqirePrmisnCancel(GamTrainPortRentSttusInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortRentSttusInqireFileList(GamTrainPortRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortRentSttusInqireFileListTotCnt(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	void insertTrainPortRentSttusInqireFile(GamTrainPortRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	void updateTrainPortRentSttusInqireFile(GamTrainPortRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	void deleteTrainPortRentSttusInqirePhotoSingle(GamTrainPortRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireMaxKey(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamTrainPortRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortRentSttusInqireComment(GamTrainPortRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireRenewInfo(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamTrainPortRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortRentSttusInqireRenewInfo(GamTrainPortRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireCurrRenewInfo(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 철송장임대목록
   	 * @exception Exception
   	 */
    public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireDetailQuaycd(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamTrainPortRentSttusInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updateTrainPortRentSttusInqireQuaycd(GamTrainPortRentSttusInqireVO vo) throws Exception;
   	
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
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireBeforeQuarterInfo(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireCofixInfo(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
    
}

