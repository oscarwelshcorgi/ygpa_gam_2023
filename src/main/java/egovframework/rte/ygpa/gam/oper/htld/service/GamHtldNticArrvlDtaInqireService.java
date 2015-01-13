package egovframework.rte.ygpa.gam.oper.htld.service;

import java.util.List;

/**
 * @Class Name : GamHtldNticArrvlDtaInqireService.java
 * @Description : 배후단지임대고지도래현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamHtldNticArrvlDtaInqireService {

	/**
	 * 배후단지임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    List selectHtldNticArrvlDtaInqireList(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqireSum(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 배후단지임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대 목록 총 갯수
	 * @exception
	 */
    int selectHtldNticArrvlDtaInqireListTotCnt(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;

	/**
	 * 배후단지임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectHtldNticArrvlDtaInqireDetailList(GamHtldNticArrvlDtaInqireVO vo) throws Exception;

    /**
	 * 배후단지임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldNticArrvlDtaInqireDetailListTotCnt(GamHtldNticArrvlDtaInqireVO vo) throws Exception;

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
    int selectHtldNticArrvlDtaInqireLevReqestCnt(GamHtldNticArrvlDtaInqireVO vo) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectHtldNticArrvlDtaInqireFileList(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldNticArrvlDtaInqireFileListTotCnt(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception;

}
