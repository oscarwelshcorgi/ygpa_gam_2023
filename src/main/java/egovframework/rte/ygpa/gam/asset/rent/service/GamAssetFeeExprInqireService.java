package egovframework.rte.ygpa.gam.asset.rent.service;

import java.util.List;

/**
 * @Class Name : GamAssetFeeExprInqireService.java
 * @Description : 자산임대고지도래자료조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetFeeExprInqireService {
	
	/**
	 * 자산임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectAssetFeeExprInqireList(GamAssetFeeExprInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetFeeExprInqireVO selectAssetFeeExprInqireSum(GamAssetFeeExprInqireVO searchVO) throws Exception;
    
    /**
	 * 자산임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectAssetFeeExprInqireListTotCnt(GamAssetFeeExprInqireVO searchVO) throws Exception;
	
	
    /**
	 * 자산임대 최초 신청을 등록한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	void insertAssetFeeExprInqireFirst(GamAssetFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetFeeExprInqireVO selectAssetFeeExprInqireMaxNo(GamAssetFeeExprInqireVO searchVO) throws Exception;
    
    /**
	 * 자산임대 연장 신청을 등록한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	void insertAssetFeeExprInqireRenew(GamAssetFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 자산임대정보를 수정한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	void updateAssetFeeExprInqire(GamAssetFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 자산임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetFeeExprInqireDetailList(GamAssetFeeExprInqireVO vo) throws Exception;

    /**
	 * 자산임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetFeeExprInqireDetailListTotCnt(GamAssetFeeExprInqireVO vo) throws Exception;
	
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
    int selectAssetFeeExprInqireLevReqestCnt(GamAssetFeeExprInqireVO vo) throws Exception;
    
    /**
	 * 자산임대 정보를 삭제한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteAssetFeeExprInqire(GamAssetFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 자산임대 상세정보를 삭제한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteAssetFeeExprInqireDetail(GamAssetFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 자산임대 상세를 등록한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	void insertAssetFeeExprInqireDetail(GamAssetFeeExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 자산임대 상세를 수정한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	void updateAssetFeeExprInqireDetail(GamAssetFeeExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 자산임대 상세를 삭제한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteAssetFeeExprInqireDetail2(GamAssetFeeExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대정보
	 * @exception Exception
	 */
    GamAssetFeeExprInqireVO selectAssetFeeExprInqirePrmisnInfo(GamAssetFeeExprInqireVO searchVO) throws Exception;

    /**
	 * 자산임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamAssetFeeExprInqireLevReqestVO
	 * @exception Exception
	 */
	void updateAssetFeeExprInqirePrmisn(GamAssetFeeExprInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 자산임대 허가여부를 취소한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	void updateAssetFeeExprInqirePrmisnCancel(GamAssetFeeExprInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetFeeExprInqireFileList(GamAssetFeeExprInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetFeeExprInqireFileListTotCnt(GamAssetFeeExprInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	void insertAssetFeeExprInqireFile(GamAssetFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	void updateAssetFeeExprInqireFile(GamAssetFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	void deleteAssetFeeExprInqirePhotoSingle(GamAssetFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetFeeExprInqireVO selectAssetFeeExprInqireMaxKey(GamAssetFeeExprInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqireComment(GamAssetFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetFeeExprInqireVO selectAssetFeeExprInqireRenewInfo(GamAssetFeeExprInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqireRenewInfo(GamAssetFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetFeeExprInqireVO selectAssetFeeExprInqireCurrRenewInfo(GamAssetFeeExprInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 자산임대목록
   	 * @exception Exception
   	 */
    public GamAssetFeeExprInqireVO selectAssetFeeExprInqireDetailQuaycd(GamAssetFeeExprInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamAssetFeeExprInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updateAssetFeeExprInqireQuaycd(GamAssetFeeExprInqireVO vo) throws Exception;
    
}
