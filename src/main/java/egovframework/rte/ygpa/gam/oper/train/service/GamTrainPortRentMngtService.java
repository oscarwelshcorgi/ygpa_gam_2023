package egovframework.rte.ygpa.gam.oper.train.service;

import java.util.List;

/**
 * @Class Name : GamTrainPortRentMngtService.java
 * @Description : 철송장임대목록관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamTrainPortRentMngtService {
	
	/**
	 * 철송장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    List selectTrainPortRentMngtList(GamTrainPortRentMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    GamTrainPortRentMngtVO selectTrainPortRentMngtSum(GamTrainPortRentMngtVO searchVO) throws Exception;
    
    /**
	 * 철송장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대 목록 총 갯수
	 * @exception
	 */
    int selectTrainPortRentMngtListTotCnt(GamTrainPortRentMngtVO searchVO) throws Exception;
	
	
    /**
	 * 철송장임대 최초 신청을 등록한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	void insertTrainPortRentMngtFirst(GamTrainPortRentMngtVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    GamTrainPortRentMngtVO selectTrainPortRentMngtMaxNo(GamTrainPortRentMngtVO searchVO) throws Exception;
    
    /**
	 * 철송장임대 연장 신청을 등록한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	void insertTrainPortRentMngtRenew(GamTrainPortRentMngtVO vo) throws Exception;
	
	/**
	 * 철송장임대정보를 수정한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	void updateTrainPortRentMngt(GamTrainPortRentMngtVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortRentMngtDetailList(GamTrainPortRentMngtVO vo) throws Exception;

    /**
	 * 철송장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortRentMngtDetailListTotCnt(GamTrainPortRentMngtVO vo) throws Exception;
	
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
    int selectTrainPortRentMngtLevReqestCnt(GamTrainPortRentMngtVO vo) throws Exception;
    
    /**
	 * 철송장임대 정보를 삭제한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteTrainPortRentMngt(GamTrainPortRentMngtVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세정보를 삭제한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteTrainPortRentMngtDetail(GamTrainPortRentMngtVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세를 등록한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	void insertTrainPortRentMngtDetail(GamTrainPortRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세를 수정한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	void updateTrainPortRentMngtDetail(GamTrainPortRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 철송장임대 상세를 삭제한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteTrainPortRentMngtDetail2(GamTrainPortRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 철송장임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대정보
	 * @exception Exception
	 */
    GamTrainPortRentMngtVO selectTrainPortRentMngtPrmisnInfo(GamTrainPortRentMngtVO searchVO) throws Exception;

    /**
	 * 철송장임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamTrainPortRentMngtLevReqestVO
	 * @exception Exception
	 */
	void updateTrainPortRentMngtPrmisn(GamTrainPortRentMngtLevReqestVO vo) throws Exception;
	
	
	/**
	 * 철송장임대 허가여부를 취소한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	void updateTrainPortRentMngtPrmisnCancel(GamTrainPortRentMngtLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortRentMngtFileList(GamTrainPortRentMngtVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortRentMngtFileListTotCnt(GamTrainPortRentMngtVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	void insertTrainPortRentMngtFile(GamTrainPortRentMngtVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	void updateTrainPortRentMngtFile(GamTrainPortRentMngtVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	void deleteTrainPortRentMngtPhotoSingle(GamTrainPortRentMngtVO vo) throws Exception;
    
	/**
	 * 철송장임대 신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return  철송장임대 목록
	 * @exception Exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtMaxKey(GamTrainPortRentMngtVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngtComment(GamTrainPortRentMngtVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대 목록
	 * @exception Exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtRenewInfo(GamTrainPortRentMngtVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngtRenewInfo(GamTrainPortRentMngtVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대 목록
	 * @exception Exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtCurrRenewInfo(GamTrainPortRentMngtVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 철송장임대 상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 철송장임대 목록
   	 * @exception Exception
   	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtDetailQuaycd(GamTrainPortRentMngtVO searchVO) throws Exception;
       
    /**
   	 * 신청저장시 철송장임대 테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamTrainPortRentMngtDetailVO
   	 * @exception Exception
   	 */
   	public void updateTrainPortRentMngtQuaycd(GamTrainPortRentMngtVO vo) throws Exception;
   	
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
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtBeforeQuarterInfo(GamTrainPortRentMngtVO searchVO) throws Exception;
    
    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtCofixInfo(GamTrainPortRentMngtVO searchVO) throws Exception;
    
    /**
   	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 철송장임대목록
   	 * @exception Exception
   	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtCofixInfoMax(GamTrainPortRentMngtVO searchVO) throws Exception;
    
}