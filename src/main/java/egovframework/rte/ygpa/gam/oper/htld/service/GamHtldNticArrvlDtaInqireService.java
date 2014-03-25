package egovframework.rte.ygpa.gam.oper.htld.service;

import java.util.List;

/**
 * @Class Name : GamHtldNticArrvlDtaInqireService.java
 * @Description : 배후단지임대고지도래현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamHtldNticArrvlDtaInqireService {
	
	/**
	 * 배후단지임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    List selectHtldNticArrvlDtaInqireList(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqireSum(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 배후단지임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대 목록 총 갯수
	 * @exception
	 */
    int selectHtldNticArrvlDtaInqireListTotCnt(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;
	
	
    /**
	 * 배후단지임대 최초 신청을 등록한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void insertHtldNticArrvlDtaInqireFirst(GamHtldNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqireMaxNo(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 배후단지임대 연장 신청을 등록한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void insertHtldNticArrvlDtaInqireRenew(GamHtldNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 배후단지임대정보를 수정한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void updateHtldNticArrvlDtaInqire(GamHtldNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 배후단지임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectHtldNticArrvlDtaInqireDetailList(GamHtldNticArrvlDtaInqireVO vo) throws Exception;

    /**
	 * 배후단지임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldNticArrvlDtaInqireDetailListTotCnt(GamHtldNticArrvlDtaInqireVO vo) throws Exception;
	
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
    int selectHtldNticArrvlDtaInqireLevReqestCnt(GamHtldNticArrvlDtaInqireVO vo) throws Exception;
    
    /**
	 * 배후단지임대 정보를 삭제한다.
	 * @param vo GamHtldNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void deleteHtldNticArrvlDtaInqire(GamHtldNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 배후단지임대 상세정보를 삭제한다.
	 * @param vo GamHtldNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void deleteHtldNticArrvlDtaInqireDetail(GamHtldNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 배후단지임대 상세를 등록한다.
	 * @param vo GamHtldNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void insertHtldNticArrvlDtaInqireDetail(GamHtldNticArrvlDtaInqireDetailVO vo) throws Exception;
	
	/**
	 * 배후단지임대 상세를 수정한다.
	 * @param vo GamHtldNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void updateHtldNticArrvlDtaInqireDetail(GamHtldNticArrvlDtaInqireDetailVO vo) throws Exception;
	
	/**
	 * 배후단지임대 상세를 삭제한다.
	 * @param vo GamHtldNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void deleteHtldNticArrvlDtaInqireDetail2(GamHtldNticArrvlDtaInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 배후단지임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대정보
	 * @exception Exception
	 */
    GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqirePrmisnInfo(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 배후단지임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamHtldNticArrvlDtaInqireLevReqestVO
	 * @exception Exception
	 */
	void updateHtldNticArrvlDtaInqirePrmisn(GamHtldNticArrvlDtaInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 배후단지임대 허가여부를 취소한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void updateHtldNticArrvlDtaInqirePrmisnCancel(GamHtldNticArrvlDtaInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectHtldNticArrvlDtaInqireFileList(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldNticArrvlDtaInqireFileListTotCnt(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void insertHtldNticArrvlDtaInqireFile(GamHtldNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void updateHtldNticArrvlDtaInqireFile(GamHtldNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void deleteHtldNticArrvlDtaInqirePhotoSingle(GamHtldNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqireMaxKey(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamHtldNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateHtldNticArrvlDtaInqireComment(GamHtldNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqireRenewInfo(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamHtldNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateHtldNticArrvlDtaInqireRenewInfo(GamHtldNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqireCurrRenewInfo(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 배후단지임대목록
   	 * @exception Exception
   	 */
    public GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqireDetailQuaycd(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamHtldNticArrvlDtaInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updateHtldNticArrvlDtaInqireQuaycd(GamHtldNticArrvlDtaInqireVO vo) throws Exception;
    
}
