package egovframework.rte.ygpa.gam.oper.gnrl.service;

import java.util.List;

import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtLevReqestVO;

/**
 * @Class Name : GamPrtFcltyRentMngtService.java
 * @Description : 항만시설사용목록관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamPrtFcltyRentMngtService {

	/**
	 * 항만시설사용 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    List selectPrtFcltyRentMngtList(GamPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtSum(GamPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 항만시설사용 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록 총 갯수
	 * @exception
	 */
    int selectPrtFcltyRentMngtListTotCnt(GamPrtFcltyRentMngtVO searchVO) throws Exception;


    /**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void insertPrtFcltyRentMngtFirst(GamPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtMaxNo(GamPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void insertPrtFcltyRentMngtRenew(GamPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 항만시설사용정보를 수정한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void updatePrtFcltyRentMngt(GamPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 항만시설사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyRentMngtDetailList(GamPrtFcltyRentMngtVO vo) throws Exception;

    /**
	 * 항만시설사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyRentMngtDetailListTotCnt(GamPrtFcltyRentMngtVO vo) throws Exception;

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
    int selectPrtFcltyRentMngtLevReqestCnt(GamPrtFcltyRentMngtVO vo) throws Exception;

    /**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	void deletePrtFcltyRentMngt(GamPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	void deletePrtFcltyRentMngtDetail(GamPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	void insertPrtFcltyRentMngtDetail(GamPrtFcltyRentMngtDetailVO vo) throws Exception;

	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	void updatePrtFcltyRentMngtDetail(GamPrtFcltyRentMngtDetailVO vo) throws Exception;

	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	void deletePrtFcltyRentMngtDetail2(GamPrtFcltyRentMngtDetailVO vo) throws Exception;

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
    GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtPrmisnInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception;

	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void updatePrtFcltyRentMngtPrmisnCancel(GamPrtFcltyRentMngtLevReqestVO vo) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyRentMngtFileList(GamPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyRentMngtFileListTotCnt(GamPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 파일을 등록한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void insertPrtFcltyRentMngtFile(GamPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 파일을 수정한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void updatePrtFcltyRentMngtFile(GamPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 파일을 삭제한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void deletePrtFcltyRentMngtPhotoSingle(GamPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 항만시설사용 신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return  항만시설사용 목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtMaxKey(GamPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtComment(GamPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtRenewInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtRenewInfo(GamPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtCurrRenewInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
   	 * 신청저장시 항만시설사용 상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 항만시설사용 목록
   	 * @exception Exception
   	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtDetailQuaycd(GamPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
   	 * 신청저장시 항만시설사용 테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamPrtFcltyRentMngtDetailVO
   	 * @exception Exception
   	 */
   	public void updatePrtFcltyRentMngtQuaycd(GamPrtFcltyRentMngtVO vo) throws Exception;

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
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtBeforeQuarterInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtCofixInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
   	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 항만시설사용목록
   	 * @exception Exception
   	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtCofixInfoMax(GamPrtFcltyRentMngtVO searchVO) throws Exception;

    public List selectChargeKndList(GamPrtFcltyRentMngtVO searchVO) throws Exception;

}
