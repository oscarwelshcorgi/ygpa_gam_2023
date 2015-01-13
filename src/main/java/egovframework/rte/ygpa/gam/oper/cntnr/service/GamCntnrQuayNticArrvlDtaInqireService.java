package egovframework.rte.ygpa.gam.oper.cntnr.service;

import java.util.List;

/**
 * @Class Name : GamCntnrQuayNticArrvlDtaInqireService.java
 * @Description : 컨테이너부두임대고지도래현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamCntnrQuayNticArrvlDtaInqireService {

	/**
	 * 컨테이너부두임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    List selectCntnrQuayNticArrvlDtaInqireList(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록
	 * @exception Exception
	 */
    GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqireSum(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 컨테이너부두임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대 목록 총 갯수
	 * @exception
	 */
    int selectCntnrQuayNticArrvlDtaInqireListTotCnt(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;

	/**
	 * 컨테이너부두임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCntnrQuayNticArrvlDtaInqireDetailList(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;

    /**
	 * 컨테이너부두임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCntnrQuayNticArrvlDtaInqireDetailListTotCnt(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;

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
    int selectCntnrQuayNticArrvlDtaInqireLevReqestCnt(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCntnrQuayNticArrvlDtaInqireFileList(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCntnrQuayNticArrvlDtaInqireFileListTotCnt(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception;

}
