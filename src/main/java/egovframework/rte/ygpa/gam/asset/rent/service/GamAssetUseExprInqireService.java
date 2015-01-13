package egovframework.rte.ygpa.gam.asset.rent.service;

import java.util.List;

/**
 * @Class Name : GamAssetUseExprInqireService.java
 * @Description : 자산임대만기도래자료조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetUseExprInqireService {

	/**
	 * 자산임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectAssetUseExprInqireList(GamAssetUseExprInqireVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetUseExprInqireVO selectAssetUseExprInqireSum(GamAssetUseExprInqireVO searchVO) throws Exception;

    /**
	 * 자산임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectAssetUseExprInqireListTotCnt(GamAssetUseExprInqireVO searchVO) throws Exception;


	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetUseExprInqireVO selectAssetUseExprInqireMaxNo(GamAssetUseExprInqireVO searchVO) throws Exception;

	/**
	 * 자산임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetUseExprInqireDetailList(GamAssetUseExprInqireVO vo) throws Exception;

    /**
	 * 자산임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetUseExprInqireDetailListTotCnt(GamAssetUseExprInqireVO vo) throws Exception;

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
    int selectAssetUseExprInqireLevReqestCnt(GamAssetUseExprInqireVO vo) throws Exception;

	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대정보
	 * @exception Exception
	 */
    GamAssetUseExprInqireVO selectAssetUseExprInqirePrmisnInfo(GamAssetUseExprInqireVO searchVO) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetUseExprInqireFileList(GamAssetUseExprInqireVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetUseExprInqireFileListTotCnt(GamAssetUseExprInqireVO searchVO) throws Exception;

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamAssetUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetUseExprInqireComment(GamAssetUseExprInqireVO vo) throws Exception;

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetUseExprInqireVO selectAssetUseExprInqireCurrRenewInfo(GamAssetUseExprInqireVO searchVO) throws Exception;

    /**
   	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 자산임대목록
   	 * @exception Exception
   	 */
    public GamAssetUseExprInqireVO selectAssetUseExprInqireDetailQuaycd(GamAssetUseExprInqireVO searchVO) throws Exception;

}
