package egovframework.rte.ygpa.gam.oper.htld.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : GamHtldRentMngtService.java
 * @Description : 배후단지임대목록관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamHtldRentMngtService {

	/**
	 * 배후단지임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    List selectHtldRentMngtList(GamHtldRentDefaultVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    EgovMap selectHtldRentMngtSum(GamHtldRentDefaultVO searchVO) throws Exception;

    /**
	 * 배후단지임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대 목록 총 갯수
	 * @exception
	 */
    int selectHtldRentMngtListTotCnt(GamHtldRentDefaultVO searchVO) throws Exception;

    /**
     * 배후단지임대 상세 항목을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    GamHtldRentMngtVO selectHtldRentMngtDetailPk(GamHtldRentMngtVO searchVO) throws Exception;

	/**
	 * 배후단지임대정보를 등록 한다.
	 * @param rentVo
	 * @param createList
	 * @return GamHtldRentMngtVO 추가한 임대 정보
	 * @throws Exception
	 */
    GamHtldRentMngtVO insertHtldRentMngt(GamHtldRentMngtVO rentVo, List<GamHtldRentMngtDetailVO> createList) throws Exception;

	/**
	 * 배후단지임대정보를 변경 한다.
	 * @param rentVo
	 * @param createList
	 * @return GamHtldRentMngtVO 추가한 임대 정보
	 * @throws Exception
	 */
    GamHtldRentMngtVO changeHtldRentMngt(GamHtldRentMngtVO rentVo, List<GamHtldRentMngtDetailVO> createList) throws Exception;
    
	/**
	 * 배후단지임대정보를 수정 한다.
	 * @param dataList GamHtldRentMngtVO
	 * @exception Exception
	 */
	void updateHtldRentMngt(GamHtldRentMngtVO rentVo, List<GamHtldRentMngtDetailVO> createList,  List<GamHtldRentMngtDetailVO> updateList,  List<GamHtldRentMngtDetailVO> deleteList) throws Exception;

    /**
	 * 배후단지임대 연장 신청을 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @return	 연장 신청 된 데이터를 리턴한다.
	 * @exception Exception
	 */
	GamHtldRentMngtVO insertHtldRentMngtExtend(GamHtldRentMngtVO vo) throws Exception;

	/**
	 * 배후단지임대 상세 목록을 조회한다.
	 * @param vo - 조회할 조건이 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectHtldRentMngtDetailList(GamHtldRentMngtVO vo) throws Exception;

    /**
     * 배후단지임대 상세 파일목록을 조회한다.
	 * @param vo - 조회할 조건이 담긴 VO
     * @return
     * @throws Exception
     */
    List selectHtldRentMngtFileList(GamHtldRentMngtVO vo) throws Exception;

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectHtldRentMngtLevReqestCnt(GamHtldRentMngtVO vo) throws Exception;

    /**
	 * 배후단지임대 정보를 삭제한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteHtldRentMngt(GamHtldRentMngtVO vo) throws Exception;

    /**
	 * 배후단지임대계약을 해지한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	void terminateHtldRentMngt(GamHtldRentMngtVO vo) throws Exception;

	/**
     * 배후단지 요금 종류 코드를 가져온다.
     * @return	배후단지 요금종류 코드 목록
     * @throws Exception
     */
    public List selectChargeKndList(GamHtldRentMngtVO vo) throws Exception;

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtComment(GamHtldRentMngtVO vo) throws Exception;

   	/**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception;

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public EgovMap selectHtldRentMngtCofixInfo(Map searchVO) throws Exception;

    /**
   	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 배후단지임대목록
   	 * @exception Exception
   	 */
    public EgovMap selectHtldRentMngtCofixInfoMax(Map searchVO) throws Exception;

    /**
     * 평가 목록 조회
     * @param searchVO
     * @return
     * @throws Exception
     */
    List selectHtldAssessList(GamHtldAssessVO searchVO) throws Exception;

	
    /**
     * @param searchVO
     * @return
     * @throws Exception
     */
    EgovMap selectHtldAssessSum(GamHtldAssessVO searchVO) throws Exception;

    /**
     * @param searchVO
     * @return
     * @throws Exception
     */
    List selectHtldNticRcivList(GamHtldRentDefaultVO searchVO) throws Exception;

    /**
     * @param searchVO
     * @return
     * @throws Exception
     */
    EgovMap selectHtldNticRcivSum(GamHtldRentDefaultVO searchVO) throws Exception;

    /**
     * 기간 내 요금을 산정한다.
     * @param fromDate
     * @param toDate
     * @param monthFee
     * @return
     */
    public BigDecimal getTotalFee(LocalDate fromDate, LocalDate toDate, BigDecimal monthFee);

    /**
     * 현재 등록 되어있는 사용 자료에 대한 고지 목록을 생성한다.
     * @throws Exception
     */
    public void createHtldRentMngtFirst() throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectHtldAssessSeCodeList(GamHtldRentDefaultVO searchVO) throws Exception;

	/**
	 * @param modifyVo
	 */
	void applyHtldAssessList(GamHtldAssessVO modifyVo) throws Exception;

	/**
	 * 실적평가 합계를 가져온다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectHtldBizAssessSum(GamHtldAssessVO searchVO) throws Exception;

	/**
	 * 실적평가 목록을 가져온다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List selectHtldBizAssessList(GamHtldAssessVO searchVO) throws Exception;

	/**
	 * 실적평가 고지대상기간 가져오기
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	EgovMap selectBizAssessNticPd(GamHtldAssessVO searchVO) throws Exception;
	
	/**
	 * 싪적평가 항목 및 고지내역 저장
	 * @param createList
	 * @param updateList
	 * @param deleteList
	 * @param rentData
	 * @param nticData
	 * @param loginVo
	 * @throws Exception
	 */
	void updateBizHtldAssessList(List<GamHtldAssessVO> createList, List<GamHtldAssessVO> updateList, List<GamHtldAssessVO> deleteList, Map<String, String> rentData, Map<String, String> nticData, LoginVO loginVo) throws Exception;

	/**
	 * 면적평가 등록
	 * @param areaAssessData
	 */
	void insertAreaHtldAssess(Map<String, String> areaAssessData) throws Exception;

	/**
	 * 면적평가 수정
	 * @param areaAssessData
	 */
	void updateAreaHtldAssess(Map<String, String> areaAssessData) throws Exception;
}