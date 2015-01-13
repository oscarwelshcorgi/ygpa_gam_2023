package egovframework.rte.ygpa.gam.oper.shed.service;

import java.util.List;

/**
 * @Class Name : GamCmmnCntrUseExprInqireService.java
 * @Description : 공컨장치장임대만기도래자료조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamCmmnCntrUseExprInqireService {

	/**
	 * 공컨장치장임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    List selectCmmnCntrUseExprInqireList(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대목록
	 * @exception Exception
	 */
    GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqireSum(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;

    /**
	 * 공컨장치장임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대 목록 총 갯수
	 * @exception
	 */
    int selectCmmnCntrUseExprInqireListTotCnt(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;

	/**
	 * 공컨장치장임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrUseExprInqireDetailList(GamCmmnCntrUseExprInqireVO vo) throws Exception;

    /**
	 * 공컨장치장임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrUseExprInqireDetailListTotCnt(GamCmmnCntrUseExprInqireVO vo) throws Exception;

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
    int selectCmmnCntrUseExprInqireLevReqestCnt(GamCmmnCntrUseExprInqireVO vo) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrUseExprInqireFileList(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrUseExprInqireFileListTotCnt(GamCmmnCntrUseExprInqireVO searchVO) throws Exception;

}
