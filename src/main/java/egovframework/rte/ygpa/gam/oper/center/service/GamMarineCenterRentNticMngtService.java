package egovframework.rte.ygpa.gam.oper.center.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : GamMarineCenterRentNticMngtService.java
 * @Description : 마린센터임대료납부관리 Business class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-02-05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamMarineCenterRentNticMngtService {
	
    /**
	 * 자산임대료납부관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterRentFeePayDtlsList(GamMarineCenterRentNticMngtVO searchVO) throws Exception;

    /**
	 * 자산임대료납부관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectMarineCenterRentFeePayDtlsListTotCnt(GamMarineCenterRentNticMngtVO searchVO) throws Exception;

	/**
	 * 자산임대 상세 마스터 내역을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectMarineCenterRentFeePayDtlsMngtDetailMstPk(GamMarineCenterRentNticMngtVO searchVO) throws Exception;


    /**
     * 납부관리 상세 내역을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    List selectMarineCenterRentFeePayDtlsMngtDetailList(GamMarineCenterRentNticMngtVO searchVO) throws Exception;

	/**
	 * 자산임대 상세 내역을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectMarineCenterRentFeePayDtlsMngtDetailSumPk(GamMarineCenterRentNticMngtVO searchVO) throws Exception;

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료납부관리목록
	 * @exception Exception
	 */
    GamMarineCenterRentNticMngtVO selectMarineCenterRentFeePayDtlsSum(GamMarineCenterRentNticMngtVO searchVO) throws Exception;


    /**
	 * 고지금액합계, 수납금액합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamMarineCenterRentNticMngtVO selectMarineCenterRentFeePayDtlsMngtSum(GamMarineCenterRentNticMngtVO searchVO) throws Exception;

    /**
     * 납부현황을 업데이트 한다.
     * @throws Exception
     */
    int updateMarineCenterRentFeePayDtlsMngtList() throws Exception;

	/**
	 * 연체 세입 목록을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<?> selectNticArrrgList(GamMarineCenterRentArrrgMngtVO searchVO) throws Exception;

	/**
	 * 연체세입건을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	Map selectNticArrrgDetail(GamMarineCenterRentNticMngtVO searchVO) throws Exception;

	/**
	 * 연체세입 건수 조회
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	int selectNticArrrgListTotCnt(GamMarineCenterRentArrrgMngtVO searchVO) throws Exception;

	/**
	 * 연체 세입을 등록한다.
	 * @param mergeMap
	 * @return
	 * @throws Exception
	 */
	List mergeNticArrrgListMngt(Map mergeMap) throws Exception;

}
