package egovframework.rte.ygpa.gam.oper.gnrl.service;

import java.util.List;

/**
 * @Class Name : GamPrtFcltyNticArrvlDtaInqireService.java
 * @Description : 항만시설고지도래현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamPrtFcltyNticArrvlDtaInqireService {

	/**
	 * 항만시설사용 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    List selectPrtFcltyNticArrvlDtaInqireList(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireSum(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 항만시설사용 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록 총 갯수
	 * @exception
	 */
    int selectPrtFcltyNticArrvlDtaInqireListTotCnt(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;

	/**
	 * 항만시설사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyNticArrvlDtaInqireDetailList(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;

    /**
	 * 항만시설사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyNticArrvlDtaInqireDetailListTotCnt(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;

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
    int selectPrtFcltyNticArrvlDtaInqireLevReqestCnt(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception;

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
    GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqirePrmisnInfo(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyNticArrvlDtaInqireFileList(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectPrtFcltyNticArrvlDtaInqireFileListTotCnt(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception;

}
