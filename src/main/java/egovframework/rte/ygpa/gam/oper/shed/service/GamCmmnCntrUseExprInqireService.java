package egovframework.rte.ygpa.gam.oper.shed.service;

import java.util.List;

/**
 * @Class Name : GamCmmnCntrUseExprInqireService.java
 * @Description : 공컨장치장임대만기도래자료조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCmmnCntrUseExprInqireService {
	
	/**
	 * 공컨장치장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    List selectCmmnCntrUseExprInqireList(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqireSum(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 공컨장치장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대 목록 총 갯수
	 * @exception
	 */
    int selectCmmnCntrUseExprInqireListTotCnt(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;
	
	
    /**
	 * 공컨장치장임대 최초 신청을 등록한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	void insertCmmnCntrUseExprInqireFirst(GamCmmnCntrUseExprInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqireMaxNo(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 공컨장치장임대 연장 신청을 등록한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	void insertCmmnCntrUseExprInqireRenew(GamCmmnCntrUseExprInqireVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대정보를 수정한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	void updateCmmnCntrUseExprInqire(GamCmmnCntrUseExprInqireVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrUseExprInqireDetailList(GamCmmnCntrUseExprInqireVO vo) throws Exception;

    /**
	 * 공컨장치장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrUseExprInqireDetailListTotCnt(GamCmmnCntrUseExprInqireVO vo) throws Exception;
	
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
    int selectCmmnCntrUseExprInqireLevReqestCnt(GamCmmnCntrUseExprInqireVO vo) throws Exception;
    
    /**
	 * 공컨장치장임대 정보를 삭제한다.
	 * @param vo GamCmmnCntrUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteCmmnCntrUseExprInqire(GamCmmnCntrUseExprInqireVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대 상세정보를 삭제한다.
	 * @param vo GamCmmnCntrUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteCmmnCntrUseExprInqireDetail(GamCmmnCntrUseExprInqireVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대 상세를 등록한다.
	 * @param vo GamCmmnCntrUseExprInqireDetailVO
	 * @exception Exception
	 */
	void insertCmmnCntrUseExprInqireDetail(GamCmmnCntrUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대 상세를 수정한다.
	 * @param vo GamCmmnCntrUseExprInqireDetailVO
	 * @exception Exception
	 */
	void updateCmmnCntrUseExprInqireDetail(GamCmmnCntrUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 공컨장치장임대 상세를 삭제한다.
	 * @param vo GamCmmnCntrUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteCmmnCntrUseExprInqireDetail2(GamCmmnCntrUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 공컨장치장임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대정보
	 * @exception Exception
	 */
    GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqirePrmisnInfo(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;

    /**
	 * 공컨장치장임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamCmmnCntrUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	void updateCmmnCntrUseExprInqirePrmisn(GamCmmnCntrUseExprInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 공컨장치장임대 허가여부를 취소한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	void updateCmmnCntrUseExprInqirePrmisnCancel(GamCmmnCntrUseExprInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrUseExprInqireFileList(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrUseExprInqireFileListTotCnt(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	void insertCmmnCntrUseExprInqireFile(GamCmmnCntrUseExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	void updateCmmnCntrUseExprInqireFile(GamCmmnCntrUseExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	void deleteCmmnCntrUseExprInqirePhotoSingle(GamCmmnCntrUseExprInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqireMaxKey(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamCmmnCntrUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrUseExprInqireComment(GamCmmnCntrUseExprInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqireRenewInfo(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamCmmnCntrUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrUseExprInqireRenewInfo(GamCmmnCntrUseExprInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqireCurrRenewInfo(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 공컨장치장임대목록
   	 * @exception Exception
   	 */
    public GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqireDetailQuaycd(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamCmmnCntrUseExprInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updateCmmnCntrUseExprInqireQuaycd(GamCmmnCntrUseExprInqireVO vo) throws Exception;
    
}
