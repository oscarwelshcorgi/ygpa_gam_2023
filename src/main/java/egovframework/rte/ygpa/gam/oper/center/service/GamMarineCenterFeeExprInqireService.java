package egovframework.rte.ygpa.gam.oper.center.service;

import java.util.List;

/**
 * @Class Name : GamMarineCenterFeeExprInqireService.java
 * @Description : 마린센터임대고지도래자료조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamMarineCenterFeeExprInqireService {
	
	/**
	 * 마린센터임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    List selectMarineCenterFeeExprInqireList(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireSum(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;
    
    /**
	 * 마린센터임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대 목록 총 갯수
	 * @exception
	 */
    int selectMarineCenterFeeExprInqireListTotCnt(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;
	
	
    /**
	 * 마린센터임대 최초 신청을 등록한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	void insertMarineCenterFeeExprInqireFirst(GamMarineCenterFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireMaxNo(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;
    
    /**
	 * 마린센터임대 연장 신청을 등록한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	void insertMarineCenterFeeExprInqireRenew(GamMarineCenterFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 마린센터임대정보를 수정한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	void updateMarineCenterFeeExprInqire(GamMarineCenterFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 마린센터임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterFeeExprInqireDetailList(GamMarineCenterFeeExprInqireVO vo) throws Exception;

    /**
	 * 마린센터임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectMarineCenterFeeExprInqireDetailListTotCnt(GamMarineCenterFeeExprInqireVO vo) throws Exception;
	
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
    int selectMarineCenterFeeExprInqireLevReqestCnt(GamMarineCenterFeeExprInqireVO vo) throws Exception;
    
    /**
	 * 마린센터임대 정보를 삭제한다.
	 * @param vo GamMarineCenterFeeExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteMarineCenterFeeExprInqire(GamMarineCenterFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 마린센터임대 상세정보를 삭제한다.
	 * @param vo GamMarineCenterFeeExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteMarineCenterFeeExprInqireDetail(GamMarineCenterFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 마린센터임대 상세를 등록한다.
	 * @param vo GamMarineCenterFeeExprInqireDetailVO
	 * @exception Exception
	 */
	void insertMarineCenterFeeExprInqireDetail(GamMarineCenterFeeExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 마린센터임대 상세를 수정한다.
	 * @param vo GamMarineCenterFeeExprInqireDetailVO
	 * @exception Exception
	 */
	void updateMarineCenterFeeExprInqireDetail(GamMarineCenterFeeExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 마린센터임대 상세를 삭제한다.
	 * @param vo GamMarineCenterFeeExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteMarineCenterFeeExprInqireDetail2(GamMarineCenterFeeExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 마린센터임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대정보
	 * @exception Exception
	 */
    GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqirePrmisnInfo(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;

    /**
	 * 마린센터임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamMarineCenterFeeExprInqireLevReqestVO
	 * @exception Exception
	 */
	void updateMarineCenterFeeExprInqirePrmisn(GamMarineCenterFeeExprInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 마린센터임대 허가여부를 취소한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	void updateMarineCenterFeeExprInqirePrmisnCancel(GamMarineCenterFeeExprInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterFeeExprInqireFileList(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectMarineCenterFeeExprInqireFileListTotCnt(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	void insertMarineCenterFeeExprInqireFile(GamMarineCenterFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	void updateMarineCenterFeeExprInqireFile(GamMarineCenterFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	void deleteMarineCenterFeeExprInqirePhotoSingle(GamMarineCenterFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireMaxKey(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamMarineCenterFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterFeeExprInqireComment(GamMarineCenterFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireRenewInfo(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamMarineCenterFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterFeeExprInqireRenewInfo(GamMarineCenterFeeExprInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireCurrRenewInfo(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 마린센터임대목록
   	 * @exception Exception
   	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireDetailQuaycd(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamMarineCenterFeeExprInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updateMarineCenterFeeExprInqireQuaycd(GamMarineCenterFeeExprInqireVO vo) throws Exception;
    
}
