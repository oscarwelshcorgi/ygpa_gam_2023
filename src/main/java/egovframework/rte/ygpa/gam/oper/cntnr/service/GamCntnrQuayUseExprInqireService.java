package egovframework.rte.ygpa.gam.oper.cntnr.service;

import java.util.List;

/**
 * @Class Name : GamCntnrQuayUseExprInqireService.java
 * @Description : 컨테이너부두임대만기도래자료조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCntnrQuayUseExprInqireService {
	
	/**
	 * 컨테이너부두임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    List selectCntnrQuayUseExprInqireList(GamCntnrQuayUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireSum(GamCntnrQuayUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대 목록 총 갯수
	 * @exception
	 */
    int selectCntnrQuayUseExprInqireListTotCnt(GamCntnrQuayUseExprInqireVO searchVO) throws Exception;
	
	
    /**
	 * 컨테이너부두임대 최초 신청을 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	void insertCntnrQuayUseExprInqireFirst(GamCntnrQuayUseExprInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireMaxNo(GamCntnrQuayUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두임대 연장 신청을 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	void insertCntnrQuayUseExprInqireRenew(GamCntnrQuayUseExprInqireVO vo) throws Exception;
	
	/**
	 * 컨테이너부두임대정보를 수정한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	void updateCntnrQuayUseExprInqire(GamCntnrQuayUseExprInqireVO vo) throws Exception;
	
	/**
	 * 컨테이너부두임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCntnrQuayUseExprInqireDetailList(GamCntnrQuayUseExprInqireVO vo) throws Exception;

    /**
	 * 컨테이너부두임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCntnrQuayUseExprInqireDetailListTotCnt(GamCntnrQuayUseExprInqireVO vo) throws Exception;
	
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
    int selectCntnrQuayUseExprInqireLevReqestCnt(GamCntnrQuayUseExprInqireVO vo) throws Exception;
    
    /**
	 * 컨테이너부두임대 정보를 삭제한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteCntnrQuayUseExprInqire(GamCntnrQuayUseExprInqireVO vo) throws Exception;
	
	/**
	 * 컨테이너부두임대 상세정보를 삭제한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteCntnrQuayUseExprInqireDetail(GamCntnrQuayUseExprInqireVO vo) throws Exception;
	
	/**
	 * 컨테이너부두임대 상세를 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	void insertCntnrQuayUseExprInqireDetail(GamCntnrQuayUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 컨테이너부두임대 상세를 수정한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	void updateCntnrQuayUseExprInqireDetail(GamCntnrQuayUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 컨테이너부두임대 상세를 삭제한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteCntnrQuayUseExprInqireDetail2(GamCntnrQuayUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 컨테이너부두임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대정보
	 * @exception Exception
	 */
    GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqirePrmisnInfo(GamCntnrQuayUseExprInqireVO searchVO) throws Exception;

    /**
	 * 컨테이너부두임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	void updateCntnrQuayUseExprInqirePrmisn(GamCntnrQuayUseExprInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 컨테이너부두임대 허가여부를 취소한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	void updateCntnrQuayUseExprInqirePrmisnCancel(GamCntnrQuayUseExprInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCntnrQuayUseExprInqireFileList(GamCntnrQuayUseExprInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCntnrQuayUseExprInqireFileListTotCnt(GamCntnrQuayUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	void insertCntnrQuayUseExprInqireFile(GamCntnrQuayUseExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	void updateCntnrQuayUseExprInqireFile(GamCntnrQuayUseExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	void deleteCntnrQuayUseExprInqirePhotoSingle(GamCntnrQuayUseExprInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireMaxKey(GamCntnrQuayUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqireComment(GamCntnrQuayUseExprInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireRenewInfo(GamCntnrQuayUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqireRenewInfo(GamCntnrQuayUseExprInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireCurrRenewInfo(GamCntnrQuayUseExprInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 컨테이너부두임대목록
   	 * @exception Exception
   	 */
    public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireDetailQuaycd(GamCntnrQuayUseExprInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamCntnrQuayUseExprInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updateCntnrQuayUseExprInqireQuaycd(GamCntnrQuayUseExprInqireVO vo) throws Exception;
    
}
