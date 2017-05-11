package egovframework.rte.ygpa.gam.oper.gnrltest.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtLevReqestVO;

/**
 * @Class Name : GamTestPrtFcltyRentMngtService.java
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
public interface GamTestPrtFcltyRentMngtService {

	/**
	 * 항만시설사용 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    List selectPrtFcltyRentMngtList(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtSum(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 항만시설사용 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록 총 갯수
	 * @exception
	 */
    int selectPrtFcltyRentMngtListTotCnt(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;


    /**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void insertPrtFcltyRentMngtFirst(GamTestPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtMaxNo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void insertPrtFcltyRentMngtRenew(GamTestPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 항만시설사용정보를 수정한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void updatePrtFcltyRentMngt(GamTestPrtFcltyRentMngtVO vo) throws Exception;

    /**
     * 임대 정보 내역을 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
    Map selectPrtFcltyRentMngtMasterInfo(GamTestPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 항만시설사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyRentMngtDetailList(GamTestPrtFcltyRentMngtVO vo) throws Exception;

    /**
	 * 항만시설사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyRentMngtDetailListTotCnt(GamTestPrtFcltyRentMngtVO vo) throws Exception;

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyRentMngtLevReqestCnt(GamTestPrtFcltyRentMngtVO vo) throws Exception;

    /**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	void deletePrtFcltyRentMngt(GamTestPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	void deletePrtFcltyRentMngtDetail(GamTestPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	void insertPrtFcltyRentMngtDetail(GamTestPrtFcltyRentMngtDetailVO vo) throws Exception;

	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	void updatePrtFcltyRentMngtDetail(GamTestPrtFcltyRentMngtDetailVO vo) throws Exception;

	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	void deletePrtFcltyRentMngtDetail2(GamTestPrtFcltyRentMngtDetailVO vo) throws Exception;

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
    GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtPrmisnInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;

	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void updatePrtFcltyRentMngtPrmisnCancel(GamTestPrtFcltyRentMngtLevReqestVO vo) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyRentMngtFileList(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyRentMngtFileListTotCnt(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 파일을 등록한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void insertPrtFcltyRentMngtFile(GamTestPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 파일을 수정한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void updatePrtFcltyRentMngtFile(GamTestPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 파일을 삭제한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	void deletePrtFcltyRentMngtPhotoSingle(GamTestPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 항만시설사용 신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return  항만시설사용 목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtMaxKey(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtComment(GamTestPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtRenewInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtRenewInfo(GamTestPrtFcltyRentMngtVO vo) throws Exception;

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtCurrRenewInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
   	 * 신청저장시 항만시설사용 상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 항만시설사용 목록
   	 * @exception Exception
   	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtDetailQuaycd(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
   	 * 신청저장시 항만시설사용 테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamTestPrtFcltyRentMngtDetailVO
   	 * @exception Exception
   	 */
   	public void updatePrtFcltyRentMngtQuaycd(GamTestPrtFcltyRentMngtVO vo) throws Exception;

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
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtBeforeQuarterInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtCofixInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
   	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 항만시설사용목록
   	 * @exception Exception
   	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtCofixInfoMax(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;

    public List selectChargeKndList(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;

    /**
     * 고지된 자료가 있는지 조회 한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectRentFeeNoticeListCount(GamTestPrtFcltyRentMngtVO searchVO) throws Exception;
}
