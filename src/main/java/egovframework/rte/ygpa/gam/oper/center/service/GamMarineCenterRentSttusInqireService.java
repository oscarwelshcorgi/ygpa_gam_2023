package egovframework.rte.ygpa.gam.oper.center.service;

import java.util.List;

/**
 * @Class Name : GamMarineCenterRentSttusInqireService.java
 * @Description : 마린센터임대현황조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamMarineCenterRentSttusInqireService {

	/**
	 * 마린센터임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    List selectMarineCenterRentSttusInqireList(GamMarineCenterRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireSum(GamMarineCenterRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 마린센터임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대 목록 총 갯수
	 * @exception
	 */
    int selectMarineCenterRentSttusInqireListTotCnt(GamMarineCenterRentSttusInqireVO searchVO) throws Exception;

	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireMaxNo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception;

	/**
	 * 마린센터임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterRentSttusInqireDetailList(GamMarineCenterRentSttusInqireVO vo) throws Exception;

    /**
	 * 마린센터임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectMarineCenterRentSttusInqireDetailListTotCnt(GamMarineCenterRentSttusInqireVO vo) throws Exception;

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
    int selectMarineCenterRentSttusInqireLevReqestCnt(GamMarineCenterRentSttusInqireVO vo) throws Exception;

	/**
	 * 승낙할 마린센터임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대정보
	 * @exception Exception
	 */
    GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqirePrmisnInfo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterRentSttusInqireFileList(GamMarineCenterRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectMarineCenterRentSttusInqireFileListTotCnt(GamMarineCenterRentSttusInqireVO searchVO) throws Exception;

	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireMaxKey(GamMarineCenterRentSttusInqireVO searchVO) throws Exception;

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireRenewInfo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception;

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireCurrRenewInfo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception;

    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 마린센터임대목록
   	 * @exception Exception
   	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireDetailQuaycd(GamMarineCenterRentSttusInqireVO searchVO) throws Exception;

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
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireBeforeQuarterInfo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireCofixInfo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception;

}
