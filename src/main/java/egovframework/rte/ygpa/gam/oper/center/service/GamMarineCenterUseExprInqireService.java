package egovframework.rte.ygpa.gam.oper.center.service;

import java.util.List;

/**
 * @Class Name : GamMarineCenterUseExprInqireService.java
 * @Description : 마린센터임대만기도래자료조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamMarineCenterUseExprInqireService {

	/**
	 * 마린센터임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    List selectMarineCenterUseExprInqireList(GamMarineCenterUseExprInqireVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireSum(GamMarineCenterUseExprInqireVO searchVO) throws Exception;

    /**
	 * 마린센터임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대 목록 총 갯수
	 * @exception
	 */
    int selectMarineCenterUseExprInqireListTotCnt(GamMarineCenterUseExprInqireVO searchVO) throws Exception;


	/**
	 * 마린센터임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterUseExprInqireDetailList(GamMarineCenterUseExprInqireVO vo) throws Exception;

    /**
	 * 마린센터임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectMarineCenterUseExprInqireDetailListTotCnt(GamMarineCenterUseExprInqireVO vo) throws Exception;

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
    int selectMarineCenterUseExprInqireLevReqestCnt(GamMarineCenterUseExprInqireVO vo) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterUseExprInqireFileList(GamMarineCenterUseExprInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectMarineCenterUseExprInqireFileListTotCnt(GamMarineCenterUseExprInqireVO searchVO) throws Exception;
}
