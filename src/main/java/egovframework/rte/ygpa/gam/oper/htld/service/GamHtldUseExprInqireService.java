package egovframework.rte.ygpa.gam.oper.htld.service;

import java.util.List;

/**
 * @Class Name : GamHtldUseExprInqireService.java
 * @Description : 배후단지임대만기도래자료조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamHtldUseExprInqireService {
	
	/**
	 * 배후단지임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    List selectHtldUseExprInqireList(GamHtldUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    GamHtldUseExprInqireVO selectHtldUseExprInqireSum(GamHtldUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 배후단지임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대 목록 총 갯수
	 * @exception
	 */
    int selectHtldUseExprInqireListTotCnt(GamHtldUseExprInqireVO searchVO) throws Exception;
	
	
    /**
	 * 배후단지임대 최초 신청을 등록한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	void insertHtldUseExprInqireFirst(GamHtldUseExprInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    GamHtldUseExprInqireVO selectHtldUseExprInqireMaxNo(GamHtldUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 배후단지임대 연장 신청을 등록한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	void insertHtldUseExprInqireRenew(GamHtldUseExprInqireVO vo) throws Exception;
	
	/**
	 * 배후단지임대정보를 수정한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	void updateHtldUseExprInqire(GamHtldUseExprInqireVO vo) throws Exception;
	
	/**
	 * 배후단지임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectHtldUseExprInqireDetailList(GamHtldUseExprInqireVO vo) throws Exception;

    /**
	 * 배후단지임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldUseExprInqireDetailListTotCnt(GamHtldUseExprInqireVO vo) throws Exception;
	
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
    int selectHtldUseExprInqireLevReqestCnt(GamHtldUseExprInqireVO vo) throws Exception;
    
    /**
	 * 배후단지임대 정보를 삭제한다.
	 * @param vo GamHtldUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteHtldUseExprInqire(GamHtldUseExprInqireVO vo) throws Exception;
	
	/**
	 * 배후단지임대 상세정보를 삭제한다.
	 * @param vo GamHtldUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteHtldUseExprInqireDetail(GamHtldUseExprInqireVO vo) throws Exception;
	
	/**
	 * 배후단지임대 상세를 등록한다.
	 * @param vo GamHtldUseExprInqireDetailVO
	 * @exception Exception
	 */
	void insertHtldUseExprInqireDetail(GamHtldUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 배후단지임대 상세를 수정한다.
	 * @param vo GamHtldUseExprInqireDetailVO
	 * @exception Exception
	 */
	void updateHtldUseExprInqireDetail(GamHtldUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 배후단지임대 상세를 삭제한다.
	 * @param vo GamHtldUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteHtldUseExprInqireDetail2(GamHtldUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 배후단지임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대정보
	 * @exception Exception
	 */
    GamHtldUseExprInqireVO selectHtldUseExprInqirePrmisnInfo(GamHtldUseExprInqireVO searchVO) throws Exception;

    /**
	 * 배후단지임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamHtldUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	void updateHtldUseExprInqirePrmisn(GamHtldUseExprInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 배후단지임대 허가여부를 취소한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	void updateHtldUseExprInqirePrmisnCancel(GamHtldUseExprInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectHtldUseExprInqireFileList(GamHtldUseExprInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldUseExprInqireFileListTotCnt(GamHtldUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	void insertHtldUseExprInqireFile(GamHtldUseExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	void updateHtldUseExprInqireFile(GamHtldUseExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	void deleteHtldUseExprInqirePhotoSingle(GamHtldUseExprInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public GamHtldUseExprInqireVO selectHtldUseExprInqireMaxKey(GamHtldUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamHtldUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateHtldUseExprInqireComment(GamHtldUseExprInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public GamHtldUseExprInqireVO selectHtldUseExprInqireRenewInfo(GamHtldUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamHtldUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateHtldUseExprInqireRenewInfo(GamHtldUseExprInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public GamHtldUseExprInqireVO selectHtldUseExprInqireCurrRenewInfo(GamHtldUseExprInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 배후단지임대목록
   	 * @exception Exception
   	 */
    public GamHtldUseExprInqireVO selectHtldUseExprInqireDetailQuaycd(GamHtldUseExprInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamHtldUseExprInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updateHtldUseExprInqireQuaycd(GamHtldUseExprInqireVO vo) throws Exception;
    
}
