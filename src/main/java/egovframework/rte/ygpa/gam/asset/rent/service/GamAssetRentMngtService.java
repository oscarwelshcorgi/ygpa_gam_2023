package egovframework.rte.ygpa.gam.asset.rent.service;

import java.util.List;

import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeVO;

/**
 * @Class Name : GamAssetRentMngtService.java
 * @Description : 자산임대관리 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetRentMngtService {

	/**
	 * 자산임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectAssetRentList(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetRentMngtVO selectAssetRentSum(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 자산임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectAssetRentListTotCnt(GamAssetRentMngtVO searchVO) throws Exception;


    /**
	 * 자산임대 최초 신청을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	void insertAssetRentFirst(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetRentMngtVO selectAssetRentMaxNo(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 자산임대 연장 신청을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	void insertAssetRentRenew(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 자산임대정보를 수정한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	void updateAssetRent(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 자산임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetRentDetailList(GamAssetRentMngtVO vo) throws Exception;

    /**
	 * 자산임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetRentDetailListTotCnt(GamAssetRentMngtVO vo) throws Exception;

    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectOlnlpInfo(GamGisAssetCodeVO searchVO) throws Exception;

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetRentLevReqestCnt(GamAssetRentMngtVO vo) throws Exception;

    /**
	 * 자산임대 정보를 삭제한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	void deleteAssetRent(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 자산임대 상세정보를 삭제한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	void deleteAssetRentDetail(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 자산임대 상세를 등록한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	void insertAssetRentDetail(GamAssetRentDetailVO vo) throws Exception;

	/**
	 * 자산임대 상세를 수정한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	void updateAssetRentDetail(GamAssetRentDetailVO vo) throws Exception;

	/**
	 * 자산임대 상세를 삭제한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	void deleteAssetRentDetail2(GamAssetRentDetailVO vo) throws Exception;

	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대정보
	 * @exception Exception
	 */
    GamAssetRentMngtVO selectAssetRentPrmisnInfo(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 자산임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamAssetRentLevReqestVO
	 * @exception Exception
	 */
	void updateAssetRentPrmisn(GamAssetRentLevReqestVO vo) throws Exception;


	/**
	 * 자산임대 허가여부를 취소한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	void updateAssetRentPrmisnCancel(GamAssetRentLevReqestVO vo) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetRentFileList(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetRentFileListTotCnt(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 파일을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	void insertAssetRentFile(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 파일을 수정한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	void updateAssetRentFile(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 파일을 삭제한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	void deleteAssetRentPhotoSingle(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMaxKey(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentComment(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentRenewInfo(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentRenewInfo(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentCurrRenewInfo(GamAssetRentMngtVO searchVO) throws Exception;

    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 자산임대목록
   	 * @exception Exception
   	 */
    public GamAssetRentMngtVO selectAssetRentDetailQuaycd(GamAssetRentMngtVO searchVO) throws Exception;

       /**
   	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamAssetRentDetailVO
   	 * @exception Exception
   	 */
   	public void updateAssetRentQuaycd(GamAssetRentMngtVO vo) throws Exception;

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
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentBeforeQuarterInfo(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentCofixInfo(GamAssetRentMngtVO searchVO) throws Exception;

    /**
   	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 자산임대목록
   	 * @exception Exception
   	 */
    public GamAssetRentMngtVO selectAssetRentCofixInfoMax(GamAssetRentMngtVO searchVO) throws Exception;
}
