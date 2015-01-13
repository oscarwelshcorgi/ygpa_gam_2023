package egovframework.rte.ygpa.gam.oper.train.service;

import java.util.List;

/**
 * @Class Name : GamTrainPortNticArrvlDtaInqireService.java
 * @Description : 철송장임대고지도래현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamTrainPortNticArrvlDtaInqireService {

	/**
	 * 철송장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    List selectTrainPortNticArrvlDtaInqireList(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqireSum(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 철송장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대 목록 총 갯수
	 * @exception
	 */
    int selectTrainPortNticArrvlDtaInqireListTotCnt(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;

	/**
	 * 철송장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortNticArrvlDtaInqireDetailList(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;

    /**
	 * 철송장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortNticArrvlDtaInqireDetailListTotCnt(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;

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
    int selectTrainPortNticArrvlDtaInqireLevReqestCnt(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortNticArrvlDtaInqireFileList(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortNticArrvlDtaInqireFileListTotCnt(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception;

}
