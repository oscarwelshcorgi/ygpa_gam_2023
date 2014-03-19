package egovframework.rte.ygpa.gam.asset.rent.service;

import java.util.List;

/**
 * @Class Name : GamAssetUseExprInqireService.java
 * @Description : 자산임대만기도래자료조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetUseExprInqireService {
	
	/**
	 * 자산임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectAssetUseExprInqireList(GamAssetUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetUseExprInqireVO selectAssetUseExprInqireSum(GamAssetUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 자산임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectAssetUseExprInqireListTotCnt(GamAssetUseExprInqireVO searchVO) throws Exception;
	
	
    /**
	 * 자산임대 최초 신청을 등록한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	void insertAssetUseExprInqireFirst(GamAssetUseExprInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetUseExprInqireVO selectAssetUseExprInqireMaxNo(GamAssetUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 자산임대 연장 신청을 등록한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	void insertAssetUseExprInqireRenew(GamAssetUseExprInqireVO vo) throws Exception;
	
	/**
	 * 자산임대정보를 수정한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	void updateAssetUseExprInqire(GamAssetUseExprInqireVO vo) throws Exception;
	
	/**
	 * 자산임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetUseExprInqireDetailList(GamAssetUseExprInqireVO vo) throws Exception;

    /**
	 * 자산임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetUseExprInqireDetailListTotCnt(GamAssetUseExprInqireVO vo) throws Exception;
	
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
    int selectAssetUseExprInqireLevReqestCnt(GamAssetUseExprInqireVO vo) throws Exception;
    
    /**
	 * 자산임대 정보를 삭제한다.
	 * @param vo GamAssetUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteAssetUseExprInqire(GamAssetUseExprInqireVO vo) throws Exception;
	
	/**
	 * 자산임대 상세정보를 삭제한다.
	 * @param vo GamAssetUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteAssetUseExprInqireDetail(GamAssetUseExprInqireVO vo) throws Exception;
	
	/**
	 * 자산임대 상세를 등록한다.
	 * @param vo GamAssetUseExprInqireDetailVO
	 * @exception Exception
	 */
	void insertAssetUseExprInqireDetail(GamAssetUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 자산임대 상세를 수정한다.
	 * @param vo GamAssetUseExprInqireDetailVO
	 * @exception Exception
	 */
	void updateAssetUseExprInqireDetail(GamAssetUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 자산임대 상세를 삭제한다.
	 * @param vo GamAssetUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteAssetUseExprInqireDetail2(GamAssetUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대정보
	 * @exception Exception
	 */
    GamAssetUseExprInqireVO selectAssetUseExprInqirePrmisnInfo(GamAssetUseExprInqireVO searchVO) throws Exception;

    /**
	 * 자산임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamAssetUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	void updateAssetUseExprInqirePrmisn(GamAssetUseExprInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 자산임대 허가여부를 취소한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	void updateAssetUseExprInqirePrmisnCancel(GamAssetUseExprInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetUseExprInqireFileList(GamAssetUseExprInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetUseExprInqireFileListTotCnt(GamAssetUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	void insertAssetUseExprInqireFile(GamAssetUseExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	void updateAssetUseExprInqireFile(GamAssetUseExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	void deleteAssetUseExprInqirePhotoSingle(GamAssetUseExprInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetUseExprInqireVO selectAssetUseExprInqireMaxKey(GamAssetUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamAssetUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetUseExprInqireComment(GamAssetUseExprInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetUseExprInqireVO selectAssetUseExprInqireRenewInfo(GamAssetUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetUseExprInqireRenewInfo(GamAssetUseExprInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetUseExprInqireVO selectAssetUseExprInqireCurrRenewInfo(GamAssetUseExprInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 자산임대목록
   	 * @exception Exception
   	 */
    public GamAssetUseExprInqireVO selectAssetUseExprInqireDetailQuaycd(GamAssetUseExprInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamAssetUseExprInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updateAssetUseExprInqireQuaycd(GamAssetUseExprInqireVO vo) throws Exception;
    
}
