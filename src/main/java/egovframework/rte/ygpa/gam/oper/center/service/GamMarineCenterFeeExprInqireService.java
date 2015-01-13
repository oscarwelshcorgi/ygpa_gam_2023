package egovframework.rte.ygpa.gam.oper.center.service;

import java.util.List;

/**
 * @Class Name : GamMarineCenterFeeExprInqireService.java
 * @Description : 마린센터임대고지도래자료조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamMarineCenterFeeExprInqireService {

	/**
	 * 마린센터임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    List selectMarineCenterFeeExprInqireList(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireSum(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;

    /**
	 * 마린센터임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대 목록 총 갯수
	 * @exception
	 */
    int selectMarineCenterFeeExprInqireListTotCnt(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;


	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireMaxNo(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;

	/**
	 * 마린센터임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterFeeExprInqireDetailList(GamMarineCenterFeeExprInqireVO vo) throws Exception;

    /**
	 * 마린센터임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectMarineCenterFeeExprInqireDetailListTotCnt(GamMarineCenterFeeExprInqireVO vo) throws Exception;

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
    int selectMarineCenterFeeExprInqireLevReqestCnt(GamMarineCenterFeeExprInqireVO vo) throws Exception;

	/**
	 * 승낙할 마린센터임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대정보
	 * @exception Exception
	 */
    GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqirePrmisnInfo(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterFeeExprInqireFileList(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectMarineCenterFeeExprInqireFileListTotCnt(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;

	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireMaxKey(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamMarineCenterFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterFeeExprInqireComment(GamMarineCenterFeeExprInqireVO vo) throws Exception;

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireRenewInfo(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대목록
	 * @exception Exception
	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireCurrRenewInfo(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;

    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 마린센터임대목록
   	 * @exception Exception
   	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireDetailQuaycd(GamMarineCenterFeeExprInqireVO searchVO) throws Exception;

}
