package egovframework.rte.ygpa.gam.oper.train.service;

import java.util.List;

/**
 * @Class Name : GamTrainPortUseExprInqireService.java
 * @Description : 철송장임대만기도래자료조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamTrainPortUseExprInqireService {

	/**
	 * 철송장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    List selectTrainPortUseExprInqireList(GamTrainPortUseExprInqireVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대목록
	 * @exception Exception
	 */
    GamTrainPortUseExprInqireVO selectTrainPortUseExprInqireSum(GamTrainPortUseExprInqireVO searchVO) throws Exception;

    /**
	 * 철송장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대 목록 총 갯수
	 * @exception
	 */
    int selectTrainPortUseExprInqireListTotCnt(GamTrainPortUseExprInqireVO searchVO) throws Exception;

    /**
	 * 철송장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortUseExprInqireDetailListTotCnt(GamTrainPortUseExprInqireVO vo) throws Exception;

    /**
	 * 철송장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortUseExprInqireDetailList(GamTrainPortUseExprInqireVO vo) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortUseExprInqireFileList(GamTrainPortUseExprInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortUseExprInqireFileListTotCnt(GamTrainPortUseExprInqireVO searchVO) throws Exception;

}
