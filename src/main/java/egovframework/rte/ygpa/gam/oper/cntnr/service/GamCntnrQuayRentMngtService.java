package egovframework.rte.ygpa.gam.oper.cntnr.service;

import java.util.List;

/**
 * @Class Name : GamCntnrQuayRentMngtService.java
 * @Description : 컨테이너부두임대목록관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamCntnrQuayRentMngtService {

	/**
	 * 컨테이너부두임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    List selectCntnrQuayRentMngtList(GamCntnrQuayRentMngtVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtSum(GamCntnrQuayRentMngtVO searchVO) throws Exception;

    /**
	 * 컨테이너부두임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대 목록 총 갯수
	 * @exception
	 */
    int selectCntnrQuayRentMngtListTotCnt(GamCntnrQuayRentMngtVO searchVO) throws Exception;


    /**
	 * 컨테이너부두임대 최초 신청을 등록한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	void insertCntnrQuayRentMngtFirst(GamCntnrQuayRentMngtVO vo) throws Exception;

	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtMaxNo(GamCntnrQuayRentMngtVO searchVO) throws Exception;

    /**
	 * 컨테이너부두임대 연장 신청을 등록한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	void insertCntnrQuayRentMngtRenew(GamCntnrQuayRentMngtVO vo) throws Exception;

	/**
	 * 컨테이너부두임대정보를 수정한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	void updateCntnrQuayRentMngt(GamCntnrQuayRentMngtVO vo) throws Exception;

	/**
	 * 컨테이너부두임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCntnrQuayRentMngtDetailList(GamCntnrQuayRentMngtVO vo) throws Exception;

    /**
	 * 컨테이너부두임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCntnrQuayRentMngtDetailListTotCnt(GamCntnrQuayRentMngtVO vo) throws Exception;

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
    int selectCntnrQuayRentMngtLevReqestCnt(GamCntnrQuayRentMngtVO vo) throws Exception;

    /**
	 * 컨테이너부두임대 정보를 삭제한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteCntnrQuayRentMngt(GamCntnrQuayRentMngtVO vo) throws Exception;

	/**
	 * 컨테이너부두임대 상세정보를 삭제한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteCntnrQuayRentMngtDetail(GamCntnrQuayRentMngtVO vo) throws Exception;

	/**
	 * 컨테이너부두임대 상세를 등록한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	void insertCntnrQuayRentMngtDetail(GamCntnrQuayRentMngtDetailVO vo) throws Exception;

	/**
	 * 컨테이너부두임대 상세를 수정한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	void updateCntnrQuayRentMngtDetail(GamCntnrQuayRentMngtDetailVO vo) throws Exception;

	/**
	 * 컨테이너부두임대 상세를 삭제한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteCntnrQuayRentMngtDetail2(GamCntnrQuayRentMngtDetailVO vo) throws Exception;

	/**
	 * 승낙할 컨테이너부두임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대정보
	 * @exception Exception
	 */
    GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtPrmisnInfo(GamCntnrQuayRentMngtVO searchVO) throws Exception;

    /**
	 * 컨테이너부두임대 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamCntnrQuayRentMngtLevReqestVO
	 * @exception Exception
	 */
	void updateCntnrQuayRentMngtPrmisn(GamCntnrQuayRentMngtLevReqestVO vo) throws Exception;


	/**
	 * 컨테이너부두임대 허가여부를 취소한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	void updateCntnrQuayRentMngtPrmisnCancel(GamCntnrQuayRentMngtLevReqestVO vo) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCntnrQuayRentMngtFileList(GamCntnrQuayRentMngtVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCntnrQuayRentMngtFileListTotCnt(GamCntnrQuayRentMngtVO searchVO) throws Exception;

    /**
	 * 파일을 등록한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	void insertCntnrQuayRentMngtFile(GamCntnrQuayRentMngtVO vo) throws Exception;

	/**
	 * 파일을 수정한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	void updateCntnrQuayRentMngtFile(GamCntnrQuayRentMngtVO vo) throws Exception;

	/**
	 * 파일을 삭제한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	void deleteCntnrQuayRentMngtPhotoSingle(GamCntnrQuayRentMngtVO vo) throws Exception;

	/**
	 * 컨테이너부두임대 신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return  컨테이너부두임대 목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtMaxKey(GamCntnrQuayRentMngtVO searchVO) throws Exception;

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngtComment(GamCntnrQuayRentMngtVO vo) throws Exception;

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대 목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtRenewInfo(GamCntnrQuayRentMngtVO searchVO) throws Exception;

    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngtRenewInfo(GamCntnrQuayRentMngtVO vo) throws Exception;

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대 목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtCurrRenewInfo(GamCntnrQuayRentMngtVO searchVO) throws Exception;

    /**
   	 * 신청저장시 컨테이너부두임대 상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 컨테이너부두임대 목록
   	 * @exception Exception
   	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtDetailQuaycd(GamCntnrQuayRentMngtVO searchVO) throws Exception;

    /**
   	 * 신청저장시 컨테이너부두임대 테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamCntnrQuayRentMngtDetailVO
   	 * @exception Exception
   	 */
   	public void updateCntnrQuayRentMngtQuaycd(GamCntnrQuayRentMngtVO vo) throws Exception;

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
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtBeforeQuarterInfo(GamCntnrQuayRentMngtVO searchVO) throws Exception;

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtCofixInfo(GamCntnrQuayRentMngtVO searchVO) throws Exception;

    /**
   	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 컨테이너부두임대목록
   	 * @exception Exception
   	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtCofixInfoMax(GamCntnrQuayRentMngtVO searchVO) throws Exception;

    public List selectChargeKndList() throws Exception;

}