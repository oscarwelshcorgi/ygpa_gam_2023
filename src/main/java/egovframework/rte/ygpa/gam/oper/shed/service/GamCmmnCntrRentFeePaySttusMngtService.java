package egovframework.rte.ygpa.gam.oper.shed.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;



/**
 * @Class Name : GamCmmnCntrRentFeePaySttusMngtService.java
 * @Description : 공컨장치장납부현황관리 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamCmmnCntrRentFeePaySttusMngtService {


    /**
	 * 공컨장치장임대납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrRentFeePaySttusMngtList(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception;

    /**
	 * 공컨장치장임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrRentFeePaySttusMngtListTotCnt(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception;

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대납부현황관리목록
	 * @exception Exception
	 */
    GamCmmnCntrRentFeePaySttusMngtVO selectCmmnCntrRentFeePaySttusSum(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception;





    /**
	 * 고지금액합계, 수납금액합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamCmmnCntrRentFeePaySttusMngtVO selectCmmnCntrRentFeePaySttusMngtSum(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception;


    /**
	 * 자산임대 상세 마스터 내역을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectCmmnCntrRentFeePaySttusMngtDetailMstPk(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception;


    /**
     * 납부관리 상세 내역을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    List selectCmmnCntrRentFeePaySttusMngtDetailList(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception;

	/**
	 * 자산임대 상세 내역을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectCmmnCntrRentFeePaySttusMngtDetailSumPk(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception;

	/**
	 * 연체 세입 목록을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<?> selectNticArrrgList(GamCmmnCntrRentArrrgMngtVO searchVO) throws Exception;

	/**
	 * 연체세입건을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	Map selectNticArrrgDetail(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception;

	/**
	 * 연체세입 건수 조회
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	int selectNticArrrgListTotCnt(GamCmmnCntrRentArrrgMngtVO searchVO) throws Exception;

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
    List selectCmmnCntrRentFeePaySttusMngtDlyList(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception;


    /** change**
	 * 공컨장치장임대 연체 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrRentFeePaySttusMngtDlyListTotCnt(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception;

	/**
	 * 연체 내역을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectCmmnCntrRentFeePaySttusMngtDlyInfo(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception;

	/** change**
	 * 연체 내역을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectCmmnCntrRentFeePaySttusMngtDlyListSum(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception;

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
	

	/**
	 * 수납처리 팝업 항목을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectFeePayPopup(GamCmmnCntrRentFeePaySttusMngtVO vo) throws Exception;


	/**
	 * 수납 확인 처리를 한다.
	 * @param vo
	 * @throws Exception
	 */
	void updateRevCollRcvdTp(GamCmmnCntrRentFeePaySttusMngtVO vo) throws Exception;

	/**
	 * 지로로 수납된 자료인지 확인한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	EgovMap selectCheckOcrResult(GamCmmnCntrRentFeePaySttusMngtVO vo) throws Exception;


}
