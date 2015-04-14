package egovframework.rte.ygpa.gam.asset.rent.service;

import java.util.List;
import java.util.Map;




/**
 * @Class Name : GamAssetRentFeeMngtService.java
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
public interface GamAssetRentFeeMngtService {

	/**
	 * 자산임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectAssetRentFeeMngtList(GamAssetRentFeeMngtVO searchVO) throws Exception;

    /**
	 * 자산임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectAssetRentFeeMngtListTotCnt(GamAssetRentFeeMngtVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetRentFeeMngtVO selectAssetRentFeeMngtSum(GamAssetRentFeeMngtVO searchVO) throws Exception;

    /**
	 * 자산임대료고지관리정보를 수정한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	void updateAssetRentFeeMngt(GamAssetRentFeeMngtVO vo) throws Exception;

	/**
	 * 임대고지 사용료를 변경한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updateAssetRentFee(GamAssetRentFeeMngtVO vo) throws Exception;

	/**
	 * 자산임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료고지관리정보
	 * @exception Exception
	 */
    GamAssetRentFeeMngtVO selectAssetRentFeeMngtInfo(GamAssetRentFeeMngtVO searchVO) throws Exception;

    /**
   	 * 세입징수 등록건수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectAnlrveLevCnt(GamAssetRentFeeMngtVO searchVO) throws Exception;

    /**
   	 * 세입징수를 등록한다.
   	 * @param vo GamAssetRentFeeMngtVO
   	 * @exception Exception
   	 */
   	void insertAnlrveLev(GamAssetRentFeeMngtVO vo) throws Exception;

   	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	void deleteAssetRentFeeMngt(GamAssetRentFeeMngtVO vo) throws Exception;

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAssetRentFeeMngtLevReqest(GamAssetRentFeeMngtVO vo) throws Exception;

	Map selectNpticPrintInfo(GamAssetRentFeeMngtVO searchVO) throws Exception;

	/**
	 * 연체금만 있는 고지서를 출력한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List selectNpticPrintInfo2(Map searchVO) throws Exception;

	List selectTaxNtcPrintInfo(Map searchVO) throws Exception;

	void updateAssetRentFeeMngtListDetail(GamAssetRentFeeMngtVO vo) throws Exception;

	List selectAssetRentFeeDetailList(GamAssetRentFeeMngtVO searchVO);

	Map selectAssetRentFeeDetailMstPk(GamAssetRentFeeMngtVO searchVO);

	Map selectAssetRentFeeDetailSumPk(GamAssetRentFeeMngtVO searchVO);

	}

