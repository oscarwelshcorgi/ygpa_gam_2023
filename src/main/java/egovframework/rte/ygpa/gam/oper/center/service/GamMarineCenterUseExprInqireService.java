package egovframework.rte.ygpa.gam.oper.center.service;

import java.util.List;

/**
 * @Class Name : GamMarineCenterUseExprInqireService.java
 * @Description : 마린센터임대만기도래자료조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamMarineCenterUseExprInqireService {
	
	/**
	 * 마린센터임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    List selectMarineCenterUseExprInqireList(GamMarineCenterUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireSum(GamMarineCenterUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 마린센터임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대 목록 총 갯수
	 * @exception
	 */
    int selectMarineCenterUseExprInqireListTotCnt(GamMarineCenterUseExprInqireVO searchVO) throws Exception;
	
	
    /**
	 * 마린센터임대 최초 신청을 등록한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	void insertMarineCenterUseExprInqireFirst(GamMarineCenterUseExprInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireMaxNo(GamMarineCenterUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 마린센터임대 연장 신청을 등록한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	void insertMarineCenterUseExprInqireRenew(GamMarineCenterUseExprInqireVO vo) throws Exception;
	
	/**
	 * 마린센터임대정보를 수정한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	void updateMarineCenterUseExprInqire(GamMarineCenterUseExprInqireVO vo) throws Exception;
	
	/**
	 * 마린센터임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterUseExprInqireDetailList(GamMarineCenterUseExprInqireVO vo) throws Exception;

    /**
	 * 마린센터임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectMarineCenterUseExprInqireDetailListTotCnt(GamMarineCenterUseExprInqireVO vo) throws Exception;
	
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
    int selectMarineCenterUseExprInqireLevReqestCnt(GamMarineCenterUseExprInqireVO vo) throws Exception;
    
    /**
	 * 마린센터임대 정보를 삭제한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteMarineCenterUseExprInqire(GamMarineCenterUseExprInqireVO vo) throws Exception;
	
	/**
	 * 마린센터임대 상세정보를 삭제한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteMarineCenterUseExprInqireDetail(GamMarineCenterUseExprInqireVO vo) throws Exception;
	
	/**
	 * 마린센터임대 상세를 등록한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	void insertMarineCenterUseExprInqireDetail(GamMarineCenterUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 마린센터임대 상세를 수정한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	void updateMarineCenterUseExprInqireDetail(GamMarineCenterUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 마린센터임대 상세를 삭제한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteMarineCenterUseExprInqireDetail2(GamMarineCenterUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 마린센터임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대정보
	 * @exception Exception
	 */
    GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqirePrmisnInfo(GamMarineCenterUseExprInqireVO searchVO) throws Exception;

    /**
	 * 마린센터임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamMarineCenterUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	void updateMarineCenterUseExprInqirePrmisn(GamMarineCenterUseExprInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 마린센터임대 허가여부를 취소한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	void updateMarineCenterUseExprInqirePrmisnCancel(GamMarineCenterUseExprInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterUseExprInqireFileList(GamMarineCenterUseExprInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectMarineCenterUseExprInqireFileListTotCnt(GamMarineCenterUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	void insertMarineCenterUseExprInqireFile(GamMarineCenterUseExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	void updateMarineCenterUseExprInqireFile(GamMarineCenterUseExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	void deleteMarineCenterUseExprInqirePhotoSingle(GamMarineCenterUseExprInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireMaxKey(GamMarineCenterUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqireComment(GamMarineCenterUseExprInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireRenewInfo(GamMarineCenterUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqireRenewInfo(GamMarineCenterUseExprInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireCurrRenewInfo(GamMarineCenterUseExprInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 마린센터임대목록
   	 * @exception Exception
   	 */
    public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireDetailQuaycd(GamMarineCenterUseExprInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamMarineCenterUseExprInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updateMarineCenterUseExprInqireQuaycd(GamMarineCenterUseExprInqireVO vo) throws Exception;
    
}
