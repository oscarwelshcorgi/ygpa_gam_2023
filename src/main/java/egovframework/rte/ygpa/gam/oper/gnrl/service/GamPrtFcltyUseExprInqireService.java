package egovframework.rte.ygpa.gam.oper.gnrl.service;

import java.util.List;

/**
 * @Class Name : GamPrtFcltyUseExprInqireService.java
 * @Description : 항만시설사용만기도래자료조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamPrtFcltyUseExprInqireService {
	
	/**
	 * 항만시설사용 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    List selectPrtFcltyUseExprInqireList(GamPrtFcltyUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireSum(GamPrtFcltyUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 항만시설사용 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록 총 갯수
	 * @exception
	 */
    int selectPrtFcltyUseExprInqireListTotCnt(GamPrtFcltyUseExprInqireVO searchVO) throws Exception;
	
	
    /**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	void insertPrtFcltyUseExprInqireFirst(GamPrtFcltyUseExprInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireMaxNo(GamPrtFcltyUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	void insertPrtFcltyUseExprInqireRenew(GamPrtFcltyUseExprInqireVO vo) throws Exception;
	
	/**
	 * 항만시설사용정보를 수정한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	void updatePrtFcltyUseExprInqire(GamPrtFcltyUseExprInqireVO vo) throws Exception;
	
	/**
	 * 항만시설사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyUseExprInqireDetailList(GamPrtFcltyUseExprInqireVO vo) throws Exception;

    /**
	 * 항만시설사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyUseExprInqireDetailListTotCnt(GamPrtFcltyUseExprInqireVO vo) throws Exception;
	
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
    int selectPrtFcltyUseExprInqireLevReqestCnt(GamPrtFcltyUseExprInqireVO vo) throws Exception;
    
    /**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deletePrtFcltyUseExprInqire(GamPrtFcltyUseExprInqireVO vo) throws Exception;
	
	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deletePrtFcltyUseExprInqireDetail(GamPrtFcltyUseExprInqireVO vo) throws Exception;
	
	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	void insertPrtFcltyUseExprInqireDetail(GamPrtFcltyUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	void updatePrtFcltyUseExprInqireDetail(GamPrtFcltyUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deletePrtFcltyUseExprInqireDetail2(GamPrtFcltyUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
    GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqirePrmisnInfo(GamPrtFcltyUseExprInqireVO searchVO) throws Exception;

    /**
	 * 항만시설사용 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	void updatePrtFcltyUseExprInqirePrmisn(GamPrtFcltyUseExprInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	void updatePrtFcltyUseExprInqirePrmisnCancel(GamPrtFcltyUseExprInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyUseExprInqireFileList(GamPrtFcltyUseExprInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyUseExprInqireFileListTotCnt(GamPrtFcltyUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	void insertPrtFcltyUseExprInqireFile(GamPrtFcltyUseExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	void updatePrtFcltyUseExprInqireFile(GamPrtFcltyUseExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	void deletePrtFcltyUseExprInqirePhotoSingle(GamPrtFcltyUseExprInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireMaxKey(GamPrtFcltyUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireComment(GamPrtFcltyUseExprInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireRenewInfo(GamPrtFcltyUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireRenewInfo(GamPrtFcltyUseExprInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireCurrRenewInfo(GamPrtFcltyUseExprInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 항만시설사용목록
   	 * @exception Exception
   	 */
    public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireDetailQuaycd(GamPrtFcltyUseExprInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamPrtFcltyUseExprInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updatePrtFcltyUseExprInqireQuaycd(GamPrtFcltyUseExprInqireVO vo) throws Exception;
    
}
