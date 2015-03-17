package egovframework.rte.ygpa.gam.oper.train.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamFcltyRentArrrgMngtVO;

import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentFeePaySttusMngtVO;

/**
 * @Class Name : GamTrainPortRentFeePaySttusMngtService.java
 * @Description : 철송장납부현황관리 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamTrainPortRentFeePaySttusMngtService {

    /**
	 * 철송장임대납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortRentFeePaySttusMngtList(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception;

    /**
	 * 철송장임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortRentFeePaySttusMngtListTotCnt(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception;

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대납부현황관리목록
	 * @exception Exception
	 */
    GamTrainPortRentFeePaySttusMngtVO selectTrainPortRentFeePaySttusSum(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception;





    /**
	 * 고지금액합계, 수납금액합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamTrainPortRentFeePaySttusMngtVO selectTrainPortRentFeePaySttusMngtSum(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception;


    /**
	 * 자산임대 상세 마스터 내역을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectTrainPortRentFeePaySttusMngtDetailMstPk(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception;


    /**
     * 납부관리 상세 내역을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    List selectTrainPortRentFeePaySttusMngtDetailList(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception;

	/**
	 * 자산임대 상세 내역을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectTrainPortRentFeePaySttusMngtDetailSumPk(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception;

	/**
	 * 연체 세입 목록을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<?> selectNticArrrgList(GamFcltyRentArrrgMngtVO searchVO) throws Exception;

	/**
	 * 연체세입건을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	Map selectNticArrrgDetail(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception;

	/**
	 * 연체세입 건수 조회
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	int selectNticArrrgListTotCnt(GamFcltyRentArrrgMngtVO searchVO) throws Exception;

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
    List selectTrainPortRentFeePaySttusMngtDlyList(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception;


    /** change**
	 * 철송장임대 연체 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortRentFeePaySttusMngtDlyListTotCnt(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception;

	/**
	 * 연체 내역을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectTrainPortRentFeePaySttusMngtDlyInfo(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception;

	/** change**
	 * 연체 내역을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectTrainPortRentFeePaySttusMngtDlyListSum(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception;

	/**
	 * 연체료 고지서를 출력한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectArrrgNpticPrintInfo(Map searchVO) throws Exception;

	/**
	 * 연체금만 있는 고지서를 출력한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List selectArrrgNpticPrintInfo2(Map searchVO) throws Exception;

}
