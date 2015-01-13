package egovframework.rte.ygpa.gam.oper.htld.service;

import java.util.List;

/**
 * @Class Name : GamHtldUseExprInqireService.java
 * @Description : 배후단지임대만기도래자료조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamHtldUseExprInqireService {

	/**
	 * 배후단지임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    List selectHtldUseExprInqireList(GamHtldUseExprInqireVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    GamHtldUseExprInqireVO selectHtldUseExprInqireSum(GamHtldUseExprInqireVO searchVO) throws Exception;

    /**
	 * 배후단지임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대 목록 총 갯수
	 * @exception
	 */
    int selectHtldUseExprInqireListTotCnt(GamHtldUseExprInqireVO searchVO) throws Exception;

	/**
	 * 배후단지임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectHtldUseExprInqireDetailList(GamHtldUseExprInqireVO vo) throws Exception;

    /**
	 * 배후단지임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldUseExprInqireDetailListTotCnt(GamHtldUseExprInqireVO vo) throws Exception;

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
    int selectHtldUseExprInqireLevReqestCnt(GamHtldUseExprInqireVO vo) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectHtldUseExprInqireFileList(GamHtldUseExprInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldUseExprInqireFileListTotCnt(GamHtldUseExprInqireVO searchVO) throws Exception;

}
