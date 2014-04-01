package egovframework.rte.ygpa.gam.oper.htld.service;

import java.util.List;

/**
 * @Class Name : GamHtldRentMngtService.java
 * @Description : 배후단지임대목록관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamHtldRentMngtService {
	
	/**
	 * 배후단지임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    List selectHtldRentMngtList(GamHtldRentMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    GamHtldRentMngtVO selectHtldRentMngtSum(GamHtldRentMngtVO searchVO) throws Exception;
    
    /**
	 * 배후단지임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대 목록 총 갯수
	 * @exception
	 */
    int selectHtldRentMngtListTotCnt(GamHtldRentMngtVO searchVO) throws Exception;
	
	
    /**
	 * 배후단지임대 최초 신청을 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	void insertHtldRentMngtFirst(GamHtldRentMngtVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    GamHtldRentMngtVO selectHtldRentMngtMaxNo(GamHtldRentMngtVO searchVO) throws Exception;
    
    /**
	 * 배후단지임대 연장 신청을 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	void insertHtldRentMngtRenew(GamHtldRentMngtVO vo) throws Exception;
	
	/**
	 * 배후단지임대정보를 수정한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	void updateHtldRentMngt(GamHtldRentMngtVO vo) throws Exception;
	
	/**
	 * 배후단지임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectHtldRentMngtDetailList(GamHtldRentMngtVO vo) throws Exception;

    /**
	 * 배후단지임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldRentMngtDetailListTotCnt(GamHtldRentMngtVO vo) throws Exception;
	
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
    int selectHtldRentMngtLevReqestCnt(GamHtldRentMngtVO vo) throws Exception;
    
    /**
	 * 배후단지임대 정보를 삭제한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteHtldRentMngt(GamHtldRentMngtVO vo) throws Exception;
	
	/**
	 * 배후단지임대 상세정보를 삭제한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteHtldRentMngtDetail(GamHtldRentMngtVO vo) throws Exception;
	
	/**
	 * 배후단지임대 상세를 등록한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	void insertHtldRentMngtDetail(GamHtldRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 배후단지임대 상세를 수정한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	void updateHtldRentMngtDetail(GamHtldRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 배후단지임대 상세를 삭제한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteHtldRentMngtDetail2(GamHtldRentMngtDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 배후단지임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대정보
	 * @exception Exception
	 */
    GamHtldRentMngtVO selectHtldRentMngtPrmisnInfo(GamHtldRentMngtVO searchVO) throws Exception;

    /**
	 * 배후단지임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamHtldRentMngtLevReqestVO
	 * @exception Exception
	 */
	void updateHtldRentMngtPrmisn(GamHtldRentMngtLevReqestVO vo) throws Exception;
	
	
	/**
	 * 배후단지임대 허가여부를 취소한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	void updateHtldRentMngtPrmisnCancel(GamHtldRentMngtLevReqestVO vo) throws Exception;
	
	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectHtldRentMngtFileList(GamHtldRentMngtVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldRentMngtFileListTotCnt(GamHtldRentMngtVO searchVO) throws Exception;
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	void insertHtldRentMngtFile(GamHtldRentMngtVO vo) throws Exception;
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	void updateHtldRentMngtFile(GamHtldRentMngtVO vo) throws Exception;
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	void deleteHtldRentMngtPhotoSingle(GamHtldRentMngtVO vo) throws Exception;
    
	/**
	 * 배후단지임대 신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return  배후단지임대 목록
	 * @exception Exception
	 */
    public GamHtldRentMngtVO selectHtldRentMngtMaxKey(GamHtldRentMngtVO searchVO) throws Exception;
    
    /**
	 * 코멘트를 수정한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtComment(GamHtldRentMngtVO vo) throws Exception;
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대 목록
	 * @exception Exception
	 */
    public GamHtldRentMngtVO selectHtldRentMngtRenewInfo(GamHtldRentMngtVO searchVO) throws Exception;
    
    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtRenewInfo(GamHtldRentMngtVO vo) throws Exception;
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대 목록
	 * @exception Exception
	 */
    public GamHtldRentMngtVO selectHtldRentMngtCurrRenewInfo(GamHtldRentMngtVO searchVO) throws Exception;
    
    /**
   	 * 신청저장시 배후단지임대 상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 배후단지임대 목록
   	 * @exception Exception
   	 */
    public GamHtldRentMngtVO selectHtldRentMngtDetailQuaycd(GamHtldRentMngtVO searchVO) throws Exception;
       
    /**
   	 * 신청저장시 배후단지임대 테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamHtldRentMngtDetailVO
   	 * @exception Exception
   	 */
   	public void updateHtldRentMngtQuaycd(GamHtldRentMngtVO vo) throws Exception;
   	
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
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public GamHtldRentMngtVO selectHtldRentMngtBeforeQuarterInfo(GamHtldRentMngtVO searchVO) throws Exception;
    
    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public GamHtldRentMngtVO selectHtldRentMngtCofixInfo(GamHtldRentMngtVO searchVO) throws Exception;
    
    /**
   	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 배후단지임대목록
   	 * @exception Exception
   	 */
    public GamHtldRentMngtVO selectHtldRentMngtCofixInfoMax(GamHtldRentMngtVO searchVO) throws Exception;
    
}