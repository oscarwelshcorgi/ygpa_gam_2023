package egovframework.rte.ygpa.gam.oper.shed.service;

import java.util.List;

/**
 * @Class Name : GamCmmnCntrNticArrvlDtaInqireService.java
 * @Description : 공컨장치장임대고지도래현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamCmmnCntrNticArrvlDtaInqireService {

	/**
	 * 공컨장치장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    List selectCmmnCntrNticArrvlDtaInqireList(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqireSum(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 공컨장치장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대 목록 총 갯수
	 * @exception
	 */
    int selectCmmnCntrNticArrvlDtaInqireListTotCnt(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;

	/**
	 * 공컨장치장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrNticArrvlDtaInqireDetailList(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;

    /**
	 * 공컨장치장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrNticArrvlDtaInqireDetailListTotCnt(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;

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
    int selectCmmnCntrNticArrvlDtaInqireLevReqestCnt(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrNticArrvlDtaInqireFileList(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrNticArrvlDtaInqireFileListTotCnt(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception;

}
