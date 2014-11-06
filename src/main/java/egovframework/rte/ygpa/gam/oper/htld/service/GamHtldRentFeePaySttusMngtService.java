package egovframework.rte.ygpa.gam.oper.htld.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;


/**
 * @Class Name : GamHtldRentFeePaySttusMngtService.java
 * @Description : 배후단지납부현황관리 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamHtldRentFeePaySttusMngtService {

	/**
	 * 배후단지임대납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectHtldRentFeePaySttusMngtList(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception;

    /**
	 * 배후단지임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldRentFeePaySttusMngtListTotCnt(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception;

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대납부현황관리목록
	 * @exception Exception
	 */
    GamHtldRentFeePaySttusMngtVO selectHtldRentFeePaySttusSum(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception;




    /**
	 * 고지금액합계, 수납금액합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamHtldRentFeePaySttusMngtVO selectHtldRentFeePaySttusMngtSum(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception;


    /**
	 * 자산임대 상세 마스터 내역을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectHtldRentFeePaySttusMngtDetailMstPk(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception;


    /**
     * 납부관리 상세 내역을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    List selectHtldRentFeePaySttusMngtDetailList(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception;

	/**
	 * 자산임대 상세 내역을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectHtldRentFeePaySttusMngtDetailSumPk(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception;

	/**
	 * 연체 세입 목록을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<?> selectNticArrrgList(GamHtldRentArrrgMngtVO searchVO) throws Exception;

	/**
	 * 연체세입건을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	Map selectNticArrrgDetail(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception;

	/**
	 * 연체세입 건수 조회
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	int selectNticArrrgListTotCnt(GamHtldRentArrrgMngtVO searchVO) throws Exception;

	/**
	 * 연체 세입을 등록한다.
	 * @param mergeMap
	 * @return
	 * @throws Exception
	 */
	List mergeNticArrrgListMngt(Map mergeMap) throws Exception;




	/** change**
     * 납부관리 연체 내역을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    List selectHtldRentFeePaySttusMngtDlyList(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception;


    /** change**
	 * 항만시설 연체 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldRentFeePaySttusMngtDlyListTotCnt(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception;


	/** change**
	 * 연체 내역을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectHtldRentFeePaySttusMngtDlyListSum(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectPrtFcltyRentFeePaySttusMngtDlyInfo(
			GamHtldRentFeePaySttusMngtVO searchVO) throws Exception;

}
