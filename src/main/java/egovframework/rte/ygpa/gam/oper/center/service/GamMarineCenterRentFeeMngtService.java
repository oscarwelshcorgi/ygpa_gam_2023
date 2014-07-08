package egovframework.rte.ygpa.gam.oper.center.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtVO;

/**
 * @Class Name : GamMarineCenterRentFeeMngtService.java
 * @Description : 마린센터임대료관리 Business class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamMarineCenterRentFeeMngtService {

	/**
	 * 마린센터임대료관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectMarineCenterRentFeeList(GamMarineCenterRentFeeMngtVO searchVO) throws Exception;

    /**
	 * 마린센터임대료관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectMarineCenterRentFeeListTotCnt(GamMarineCenterRentFeeMngtVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamMarineCenterRentFeeMngtVO selectMarineCenterRentFeeSum(GamMarineCenterRentFeeMngtVO searchVO) throws Exception;

    /**
	 * 마린센터임대료관리정보를 수정한다.
	 * @param vo GamMarineCenterRentFeeMngtVO
	 * @exception Exception
	 */
	void updateMarineCenterRentFee(GamMarineCenterRentFeeMngtVO vo) throws Exception;

	/**
	 * 마린센터임대료관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대료관리정보
	 * @exception Exception
	 */
    GamMarineCenterRentFeeMngtVO selectMarineCenterRentFeeInfo(GamMarineCenterRentFeeMngtVO searchVO) throws Exception;

    /**
   	 * 세입징수 등록건수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectAnlrveLevCnt(GamMarineCenterRentFeeMngtVO searchVO) throws Exception;

    /**
   	 * 세입징수를 등록한다.
   	 * @param vo GamMarineCenterRentFeeMngtVO
   	 * @exception Exception
   	 */
   	void insertAnlrveLev(GamMarineCenterRentFeeMngtVO vo) throws Exception;

   	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamMarineCenterRentFeeMngtVO
	 * @exception Exception
	 */
	void deleteMarineCenterRentFee(GamMarineCenterRentFeeMngtVO vo) throws Exception;

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamMarineCenterRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAssetRentLevReqest(GamMarineCenterRentFeeMngtVO vo) throws Exception;

	List selectNpticPrintInfo(Map searchVO) throws Exception;

	List selectTaxNtcPrintInfo(Map searchVO) throws Exception;

	void updateAssetRentFeeMngtListDetail(GamMarineCenterRentFeeMngtVO vo) throws Exception;

	List selectAssetRentFeeDetailList(GamMarineCenterRentFeeMngtVO searchVO);

	Map selectAssetRentFeeDetailMstPk(GamMarineCenterRentFeeMngtVO searchVO);

	Map selectAssetRentFeeDetailSumPk(GamMarineCenterRentFeeMngtVO searchVO);

	 /**
     * 관리비를 조회 한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectAssetRentMngFeeData(GamMarineCenterRentFeeMngtVO searchVO) throws Exception;

    /**
     * 관리비 등록을 위한 정보를 조회한다,.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectAssetRentMngFeeInfo(GamMarineCenterRentFeeMngtVO searchVO) throws Exception;

    /**
     * 기 입력 된 관리비를 조회 한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    Map selectAssetRentMngFeeVal(GamMarineCenterRentFeeMngtVO searchVO) throws Exception;

    /**
     * 관리비를 입력한다.
     * @param vo
     * @throws Exception
     */
    void updateAssetRentMngFee(GamMarineCenterRentFeeMngtVO vo) throws Exception;
}
