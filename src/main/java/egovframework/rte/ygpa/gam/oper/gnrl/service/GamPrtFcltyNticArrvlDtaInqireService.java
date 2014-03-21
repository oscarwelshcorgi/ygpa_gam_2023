package egovframework.rte.ygpa.gam.oper.gnrl.service;

import java.util.List;

/**
 * @Class Name : GamPrtFcltyNticArrvlDtaInqireService.java
 * @Description : 항만시설고지도래현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamPrtFcltyNticArrvlDtaInqireService {
	
	/**
	 * 항만시설사용 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    List selectPrtFcltyNticArrvlDtaInqireList(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireSum(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 항만시설사용 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록 총 갯수
	 * @exception
	 */
    int selectPrtFcltyNticArrvlDtaInqireListTotCnt(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;
	
	
    /**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void insertPrtFcltyNticArrvlDtaInqireFirst(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireMaxNo(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void insertPrtFcltyNticArrvlDtaInqireRenew(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 항만시설사용정보를 수정한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void updatePrtFcltyNticArrvlDtaInqire(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 항만시설사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyNticArrvlDtaInqireDetailList(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;

    /**
	 * 항만시설사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyNticArrvlDtaInqireDetailListTotCnt(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;
	
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
    int selectPrtFcltyNticArrvlDtaInqireLevReqestCnt(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;
    
    /**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void deletePrtFcltyNticArrvlDtaInqire(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void deletePrtFcltyNticArrvlDtaInqireDetail(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void insertPrtFcltyNticArrvlDtaInqireDetail(GamPrtFcltyNticArrvlDtaInqireDetailVO vo) throws Exception;
	
	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void updatePrtFcltyNticArrvlDtaInqireDetail(GamPrtFcltyNticArrvlDtaInqireDetailVO vo) throws Exception;
	
	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void deletePrtFcltyNticArrvlDtaInqireDetail2(GamPrtFcltyNticArrvlDtaInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
    GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqirePrmisnInfo(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 항만시설사용 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireLevReqestVO
	 * @exception Exception
	 */
	void updatePrtFcltyNticArrvlDtaInqirePrmisn(GamPrtFcltyNticArrvlDtaInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void updatePrtFcltyNticArrvlDtaInqirePrmisnCancel(GamPrtFcltyNticArrvlDtaInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyNticArrvlDtaInqireFileList(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyNticArrvlDtaInqireFileListTotCnt(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void insertPrtFcltyNticArrvlDtaInqireFile(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void updatePrtFcltyNticArrvlDtaInqireFile(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void deletePrtFcltyNticArrvlDtaInqirePhotoSingle(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireMaxKey(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqireComment(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireRenewInfo(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqireRenewInfo(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireCurrRenewInfo(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 항만시설사용목록
   	 * @exception Exception
   	 */
    public GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireDetailQuaycd(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamPrtFcltyNticArrvlDtaInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updatePrtFcltyNticArrvlDtaInqireQuaycd(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;
    
}
