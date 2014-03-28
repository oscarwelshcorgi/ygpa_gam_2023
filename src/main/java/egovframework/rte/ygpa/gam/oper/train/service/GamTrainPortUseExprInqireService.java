package egovframework.rte.ygpa.gam.oper.train.service;

import java.util.List;

/**
 * @Class Name : GamTrainPortUseExprInqireService.java
 * @Description : 철송장임대만기도래자료조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamTrainPortUseExprInqireService {
	
	/**
	 * 철송장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    List selectTrainPortUseExprInqireList(GamTrainPortUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    GamTrainPortUseExprInqireVO selectTrainPortUseExprInqireSum(GamTrainPortUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 철송장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대 목록 총 갯수
	 * @exception
	 */
    int selectTrainPortUseExprInqireListTotCnt(GamTrainPortUseExprInqireVO searchVO) throws Exception;
	
	
    /**
	 * 철송장임대 최초 신청을 등록한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	void insertTrainPortUseExprInqireFirst(GamTrainPortUseExprInqireVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    GamTrainPortUseExprInqireVO selectTrainPortUseExprInqireMaxNo(GamTrainPortUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 철송장임대 연장 신청을 등록한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	void insertTrainPortUseExprInqireRenew(GamTrainPortUseExprInqireVO vo) throws Exception;
	
	/**
	 * 철송장임대정보를 수정한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	void updateTrainPortUseExprInqire(GamTrainPortUseExprInqireVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortUseExprInqireDetailList(GamTrainPortUseExprInqireVO vo) throws Exception;

    /**
	 * 철송장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortUseExprInqireDetailListTotCnt(GamTrainPortUseExprInqireVO vo) throws Exception;
	
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
    int selectTrainPortUseExprInqireLevReqestCnt(GamTrainPortUseExprInqireVO vo) throws Exception;
    
    /**
	 * 철송장임대 정보를 삭제한다.
	 * @param vo GamTrainPortUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteTrainPortUseExprInqire(GamTrainPortUseExprInqireVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세정보를 삭제한다.
	 * @param vo GamTrainPortUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteTrainPortUseExprInqireDetail(GamTrainPortUseExprInqireVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세를 등록한다.
	 * @param vo GamTrainPortUseExprInqireDetailVO
	 * @exception Exception
	 */
	void insertTrainPortUseExprInqireDetail(GamTrainPortUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세를 수정한다.
	 * @param vo GamTrainPortUseExprInqireDetailVO
	 * @exception Exception
	 */
	void updateTrainPortUseExprInqireDetail(GamTrainPortUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세를 삭제한다.
	 * @param vo GamTrainPortUseExprInqireDetailVO
	 * @exception Exception
	 */
	void deleteTrainPortUseExprInqireDetail2(GamTrainPortUseExprInqireDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 철송장임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대정보
	 * @exception Exception
	 */
    GamTrainPortUseExprInqireVO selectTrainPortUseExprInqirePrmisnInfo(GamTrainPortUseExprInqireVO searchVO) throws Exception;

    /**
	 * 철송장임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamTrainPortUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	void updateTrainPortUseExprInqirePrmisn(GamTrainPortUseExprInqireLevReqestVO vo) throws Exception;
	
	
	/**
	 * 철송장임대 허가여부를 취소한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	void updateTrainPortUseExprInqirePrmisnCancel(GamTrainPortUseExprInqireLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortUseExprInqireFileList(GamTrainPortUseExprInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortUseExprInqireFileListTotCnt(GamTrainPortUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	void insertTrainPortUseExprInqireFile(GamTrainPortUseExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	void updateTrainPortUseExprInqireFile(GamTrainPortUseExprInqireVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	void deleteTrainPortUseExprInqirePhotoSingle(GamTrainPortUseExprInqireVO vo) throws Exception;
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    public GamTrainPortUseExprInqireVO selectTrainPortUseExprInqireMaxKey(GamTrainPortUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamTrainPortUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortUseExprInqireComment(GamTrainPortUseExprInqireVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    public GamTrainPortUseExprInqireVO selectTrainPortUseExprInqireRenewInfo(GamTrainPortUseExprInqireVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamTrainPortUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortUseExprInqireRenewInfo(GamTrainPortUseExprInqireVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    public GamTrainPortUseExprInqireVO selectTrainPortUseExprInqireCurrRenewInfo(GamTrainPortUseExprInqireVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 철송장임대목록
   	 * @exception Exception
   	 */
    public GamTrainPortUseExprInqireVO selectTrainPortUseExprInqireDetailQuaycd(GamTrainPortUseExprInqireVO searchVO) throws Exception;
       
       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamTrainPortUseExprInqireDetailVO
   	 * @exception Exception
   	 */
   	public void updateTrainPortUseExprInqireQuaycd(GamTrainPortUseExprInqireVO vo) throws Exception;
    
}
