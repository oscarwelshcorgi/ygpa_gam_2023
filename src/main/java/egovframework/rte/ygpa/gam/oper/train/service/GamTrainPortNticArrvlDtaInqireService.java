package egovframework.rte.ygpa.gam.oper.train.service;

import java.util.List;

/**
 * @Class Name : GamTrainPortNticArrvlDtaInqireService.java
 * @Description : 철송장임대고지도래현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamTrainPortNticArrvlDtaInqireService {
	
	/**
	 * 철송장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    List selectTrainPortNticArrvlDtaInqireList(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqireSum(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 철송장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대 목록 총 갯수
	 * @exception
	 */
    int selectTrainPortNticArrvlDtaInqireListTotCnt(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;
	
	
    /**
	 * 철송장임대 최초 신청을 등록한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void insertTrainPortNticArrvlDtaInqireFirst(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqireMaxNo(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 철송장임대 연장 신청을 등록한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void insertTrainPortNticArrvlDtaInqireRenew(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 철송장임대정보를 수정한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void updateTrainPortNticArrvlDtaInqire(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortNticArrvlDtaInqireDetailList(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;

    /**
	 * 철송장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortNticArrvlDtaInqireDetailListTotCnt(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;
	
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
    int selectTrainPortNticArrvlDtaInqireLevReqestCnt(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;
    
    /**
	 * 철송장임대 정보를 삭제한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void deleteTrainPortNticArrvlDtaInqire(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세정보를 삭제한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void deleteTrainPortNticArrvlDtaInqireDetail(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세를 등록한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void insertTrainPortNticArrvlDtaInqireDetail(GamTrainPortNticArrvlDtaInqireDetailVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세를 수정한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void updateTrainPortNticArrvlDtaInqireDetail(GamTrainPortNticArrvlDtaInqireDetailVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세를 삭제한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void deleteTrainPortNticArrvlDtaInqireDetail2(GamTrainPortNticArrvlDtaInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 철송장임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대정보
	 * @exception Exception
	 */
    GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqirePrmisnInfo(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 철송장임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireLevReqestVO
	 * @exception Exception
	 */
	void updateTrainPortNticArrvlDtaInqirePrmisn(GamTrainPortNticArrvlDtaInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 철송장임대 허가여부를 취소한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void updateTrainPortNticArrvlDtaInqirePrmisnCancel(GamTrainPortNticArrvlDtaInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortNticArrvlDtaInqireFileList(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortNticArrvlDtaInqireFileListTotCnt(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void insertTrainPortNticArrvlDtaInqireFile(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void updateTrainPortNticArrvlDtaInqireFile(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void deleteTrainPortNticArrvlDtaInqirePhotoSingle(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    public GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqireMaxKey(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortNticArrvlDtaInqireComment(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    public GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqireRenewInfo(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortNticArrvlDtaInqireRenewInfo(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    public GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqireCurrRenewInfo(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 철송장임대목록
   	 * @exception Exception
   	 */
    public GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqireDetailQuaycd(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamTrainPortNticArrvlDtaInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updateTrainPortNticArrvlDtaInqireQuaycd(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;
    
}
