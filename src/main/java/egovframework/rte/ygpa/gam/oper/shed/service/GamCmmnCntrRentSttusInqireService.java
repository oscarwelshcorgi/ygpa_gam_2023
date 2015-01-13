package egovframework.rte.ygpa.gam.oper.shed.service;

import java.util.List;

/**
 * @Class Name : GamCmmnCntrRentSttusInqireService.java
 * @Description : 공컨장치장임대현황조회
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamCmmnCntrRentSttusInqireService {

	/**
	 * 공컨장치장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    List selectCmmnCntrRentSttusInqireList(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireSum(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 공컨장치장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대 목록 총 갯수
	 * @exception
	 */
    int selectCmmnCntrRentSttusInqireListTotCnt(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;

	/**
	 * 공컨장치장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrRentSttusInqireDetailList(GamCmmnCntrRentSttusInqireVO vo) throws Exception;

    /**
	 * 공컨장치장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrRentSttusInqireDetailListTotCnt(GamCmmnCntrRentSttusInqireVO vo) throws Exception;

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
    int selectCmmnCntrRentSttusInqireLevReqestCnt(GamCmmnCntrRentSttusInqireVO vo) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrRentSttusInqireFileList(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrRentSttusInqireFileListTotCnt(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;

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
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireBeforeQuarterInfo(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireCofixInfo(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception;

}

