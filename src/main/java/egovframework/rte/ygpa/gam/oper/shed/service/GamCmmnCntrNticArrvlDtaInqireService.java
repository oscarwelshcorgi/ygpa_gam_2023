package egovframework.rte.ygpa.gam.oper.shed.service;

import java.util.List;

/**
 * @Class Name : GamCmmnCntrNticArrvlDtaInqireService.java
 * @Description : 공컨장치장임대고지도래현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCmmnCntrNticArrvlDtaInqireService {
	
	/**
	 * 공컨장치장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    List selectCmmnCntrNticArrvlDtaInqireList(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqireSum(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 공컨장치장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대 목록 총 갯수
	 * @exception
	 */
    int selectCmmnCntrNticArrvlDtaInqireListTotCnt(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;
	
	
    /**
	 * 공컨장치장임대 최초 신청을 등록한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void insertCmmnCntrNticArrvlDtaInqireFirst(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqireMaxNo(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 공컨장치장임대 연장 신청을 등록한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void insertCmmnCntrNticArrvlDtaInqireRenew(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대정보를 수정한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void updateCmmnCntrNticArrvlDtaInqire(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrNticArrvlDtaInqireDetailList(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;

    /**
	 * 공컨장치장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrNticArrvlDtaInqireDetailListTotCnt(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;
	
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
    int selectCmmnCntrNticArrvlDtaInqireLevReqestCnt(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;
    
    /**
	 * 공컨장치장임대 정보를 삭제한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void deleteCmmnCntrNticArrvlDtaInqire(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대 상세정보를 삭제한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void deleteCmmnCntrNticArrvlDtaInqireDetail(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대 상세를 등록한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void insertCmmnCntrNticArrvlDtaInqireDetail(GamCmmnCntrNticArrvlDtaInqireDetailVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대 상세를 수정한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void updateCmmnCntrNticArrvlDtaInqireDetail(GamCmmnCntrNticArrvlDtaInqireDetailVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대 상세를 삭제한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void deleteCmmnCntrNticArrvlDtaInqireDetail2(GamCmmnCntrNticArrvlDtaInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 공컨장치장임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대정보
	 * @exception Exception
	 */
    GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqirePrmisnInfo(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 공컨장치장임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireLevReqestVO
	 * @exception Exception
	 */
	void updateCmmnCntrNticArrvlDtaInqirePrmisn(GamCmmnCntrNticArrvlDtaInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 공컨장치장임대 허가여부를 취소한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void updateCmmnCntrNticArrvlDtaInqirePrmisnCancel(GamCmmnCntrNticArrvlDtaInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrNticArrvlDtaInqireFileList(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrNticArrvlDtaInqireFileListTotCnt(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void insertCmmnCntrNticArrvlDtaInqireFile(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void updateCmmnCntrNticArrvlDtaInqireFile(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void deleteCmmnCntrNticArrvlDtaInqirePhotoSingle(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqireMaxKey(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrNticArrvlDtaInqireComment(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqireRenewInfo(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrNticArrvlDtaInqireRenewInfo(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqireCurrRenewInfo(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 공컨장치장임대목록
   	 * @exception Exception
   	 */
    public GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqireDetailQuaycd(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamCmmnCntrNticArrvlDtaInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updateCmmnCntrNticArrvlDtaInqireQuaycd(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;
    
}
