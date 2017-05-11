package egovframework.rte.ygpa.gam.oper.gnrltest.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : GamTestPrtFcltyRentFeeMngtService.java
 * @Description : 항만시설사용료관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamTestPrtFcltyRentFeeMngtService {

	/**
	 * 자산임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectPrtFcltyRentFeeMngtList(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception;

    /**
	 * 자산임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectPrtFcltyRentFeeMngtListTotCnt(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamTestPrtFcltyRentFeeMngtVO selectPrtFcltyRentFeeMngtSum(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception;

    /**
	 * 자산임대료고지관리정보를 수정한다.
	 * @param vo GamTestPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	void updatePrtFcltyRentFeeMngt(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception;

	/**
	 * 임대고지 사용료를 변경한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updatePrtFcltyRentFee(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception;

	/**
	 * 자산임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료고지관리정보
	 * @exception Exception
	 */
    GamTestPrtFcltyRentFeeMngtVO selectPrtFcltyRentFeeMngtInfo(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception;

    /**
   	 * 세입징수 등록건수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectAnlrveLevCnt(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception;

    /**
   	 * 세입징수를 등록한다.
   	 * @param vo GamTestPrtFcltyRentFeeMngtVO
   	 * @exception Exception
   	 */
   	void insertAnlrveLev(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception;

   	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamTestPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	void deletePrtFcltyRentFeeMngt(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception;

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamTestPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentFeeMngtLevReqest(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception;

	List selectNpticPrintInfo(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception;

	/**
	 * 연체금만 있는 고지서를 출력한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List selectNpticPrintInfo2(Map searchVO) throws Exception;

	List selectTaxNtcPrintInfo(Map searchVO) throws Exception;

	void updateAssetRentFeeMngtListDetail(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception;

	List selectAssetRentFeeDetailList(GamTestPrtFcltyRentFeeMngtVO searchVO);

	Map selectAssetRentFeeDetailMstPk(GamTestPrtFcltyRentFeeMngtVO searchVO);

	Map selectAssetRentFeeDetailSumPk(GamTestPrtFcltyRentFeeMngtVO searchVO);

	/**
	 * 투자비보전 상계처리한다. 2017.12.17 김종민
	 * @param vo
	 * @throws Exception
	 */
	void updatePrtFcltyRentFeeSaveSingle(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception;

	/**
	 * 투자비보전 상계취소한다. 2017.12.17 김종민
	 * @param vo
	 * @throws Exception
	 */
	void cancelPrtFcltyRentFeeSaveSingle(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception;	
}

