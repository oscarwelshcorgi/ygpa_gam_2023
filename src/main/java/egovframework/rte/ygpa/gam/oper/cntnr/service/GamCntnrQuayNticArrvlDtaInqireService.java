package egovframework.rte.ygpa.gam.oper.cntnr.service;

import java.util.List;

/**
 * @Class Name : GamCntnrQuayNticArrvlDtaInqireService.java
 * @Description : 컨테이너부두임대고지도래현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCntnrQuayNticArrvlDtaInqireService {
	
	/**
	 * 컨테이너부두임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    List selectCntnrQuayNticArrvlDtaInqireList(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqireSum(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대 목록 총 갯수
	 * @exception
	 */
    int selectCntnrQuayNticArrvlDtaInqireListTotCnt(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;
	
	
    /**
	 * 컨테이너부두임대 최초 신청을 등록한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void insertCntnrQuayNticArrvlDtaInqireFirst(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqireMaxNo(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두임대 연장 신청을 등록한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void insertCntnrQuayNticArrvlDtaInqireRenew(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 컨테이너부두임대정보를 수정한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void updateCntnrQuayNticArrvlDtaInqire(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 컨테이너부두임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCntnrQuayNticArrvlDtaInqireDetailList(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;

    /**
	 * 컨테이너부두임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCntnrQuayNticArrvlDtaInqireDetailListTotCnt(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;
	
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
    int selectCntnrQuayNticArrvlDtaInqireLevReqestCnt(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;
    
    /**
	 * 컨테이너부두임대 정보를 삭제한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void deleteCntnrQuayNticArrvlDtaInqire(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 컨테이너부두임대 상세정보를 삭제한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void deleteCntnrQuayNticArrvlDtaInqireDetail(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 컨테이너부두임대 상세를 등록한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void insertCntnrQuayNticArrvlDtaInqireDetail(GamCntnrQuayNticArrvlDtaInqireDetailVO vo) throws Exception;
	
	/**
	 * 컨테이너부두임대 상세를 수정한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void updateCntnrQuayNticArrvlDtaInqireDetail(GamCntnrQuayNticArrvlDtaInqireDetailVO vo) throws Exception;
	
	/**
	 * 컨테이너부두임대 상세를 삭제한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	void deleteCntnrQuayNticArrvlDtaInqireDetail2(GamCntnrQuayNticArrvlDtaInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 컨테이너부두임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대정보
	 * @exception Exception
	 */
    GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqirePrmisnInfo(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 컨테이너부두임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireLevReqestVO
	 * @exception Exception
	 */
	void updateCntnrQuayNticArrvlDtaInqirePrmisn(GamCntnrQuayNticArrvlDtaInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 컨테이너부두임대 허가여부를 취소한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void updateCntnrQuayNticArrvlDtaInqirePrmisnCancel(GamCntnrQuayNticArrvlDtaInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCntnrQuayNticArrvlDtaInqireFileList(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCntnrQuayNticArrvlDtaInqireFileListTotCnt(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void insertCntnrQuayNticArrvlDtaInqireFile(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void updateCntnrQuayNticArrvlDtaInqireFile(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	void deleteCntnrQuayNticArrvlDtaInqirePhotoSingle(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqireMaxKey(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayNticArrvlDtaInqireComment(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqireRenewInfo(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayNticArrvlDtaInqireRenewInfo(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqireCurrRenewInfo(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 컨테이너부두임대목록
   	 * @exception Exception
   	 */
    public GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqireDetailQuaycd(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamCntnrQuayNticArrvlDtaInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updateCntnrQuayNticArrvlDtaInqireQuaycd(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;
    
}
