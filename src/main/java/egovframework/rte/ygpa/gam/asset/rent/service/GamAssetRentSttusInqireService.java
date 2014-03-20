package egovframework.rte.ygpa.gam.asset.rent.service;

import java.util.List;

/**
 * @Class Name : GamAssetRentSttusInqireService.java
 * @Description : 자산임대현황조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetRentSttusInqireService {
	
	/**
	 * 자산임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectAssetRentSttusInqireList(GamAssetRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetRentSttusInqireVO selectAssetRentSttusInqireSum(GamAssetRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 자산임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectAssetRentSttusInqireListTotCnt(GamAssetRentSttusInqireVO searchVO) throws Exception;
	
	
    /**
	 * 자산임대 최초 신청을 등록한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	void insertAssetRentSttusInqireFirst(GamAssetRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetRentSttusInqireVO selectAssetRentSttusInqireMaxNo(GamAssetRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 자산임대 연장 신청을 등록한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	void insertAssetRentSttusInqireRenew(GamAssetRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 자산임대정보를 수정한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	void updateAssetRentSttusInqire(GamAssetRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 자산임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetRentSttusInqireDetailList(GamAssetRentSttusInqireVO vo) throws Exception;

    /**
	 * 자산임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetRentSttusInqireDetailListTotCnt(GamAssetRentSttusInqireVO vo) throws Exception;
	
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
    int selectAssetRentSttusInqireLevReqestCnt(GamAssetRentSttusInqireVO vo) throws Exception;
    
    /**
	 * 자산임대 정보를 삭제한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	void deleteAssetRentSttusInqire(GamAssetRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 자산임대 상세정보를 삭제한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	void deleteAssetRentSttusInqireDetail(GamAssetRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 자산임대 상세를 등록한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	void insertAssetRentSttusInqireDetail(GamAssetRentSttusInqireDetailVO vo) throws Exception;
	
	/**
	 * 자산임대 상세를 수정한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	void updateAssetRentSttusInqireDetail(GamAssetRentSttusInqireDetailVO vo) throws Exception;
	
	/**
	 * 자산임대 상세를 삭제한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	void deleteAssetRentSttusInqireDetail2(GamAssetRentSttusInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대정보
	 * @exception Exception
	 */
    GamAssetRentSttusInqireVO selectAssetRentSttusInqirePrmisnInfo(GamAssetRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 자산임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamAssetRentSttusInqireLevReqestVO
	 * @exception Exception
	 */
	void updateAssetRentSttusInqirePrmisn(GamAssetRentSttusInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 자산임대 허가여부를 취소한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	void updateAssetRentSttusInqirePrmisnCancel(GamAssetRentSttusInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetRentSttusInqireFileList(GamAssetRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetRentSttusInqireFileListTotCnt(GamAssetRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	void insertAssetRentSttusInqireFile(GamAssetRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	void updateAssetRentSttusInqireFile(GamAssetRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	void deleteAssetRentSttusInqirePhotoSingle(GamAssetRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqireMaxKey(GamAssetRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentSttusInqireComment(GamAssetRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqireRenewInfo(GamAssetRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentSttusInqireRenewInfo(GamAssetRentSttusInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqireCurrRenewInfo(GamAssetRentSttusInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 자산임대목록
   	 * @exception Exception
   	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqireDetailQuaycd(GamAssetRentSttusInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamAssetRentSttusInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updateAssetRentSttusInqireQuaycd(GamAssetRentSttusInqireVO vo) throws Exception;
   	
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
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqireBeforeQuarterInfo(GamAssetRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqireCofixInfo(GamAssetRentSttusInqireVO searchVO) throws Exception;
    
}
