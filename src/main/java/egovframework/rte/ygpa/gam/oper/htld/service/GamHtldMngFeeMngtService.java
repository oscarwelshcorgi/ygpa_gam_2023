package egovframework.rte.ygpa.gam.oper.htld.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : GamHtldMngFeeMngtService.java
 * @Description : 배후단지관리비관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamHtldMngFeeMngtService {

	/**
	 * 배후단지관리비고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectHtldMngFeeMngtList(GamHtldMngFeeDefaultVO searchVO) throws Exception;

    /**
	 * 배후단지관리비고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectHtldMngFeeMngtListTotCnt(GamHtldMngFeeDefaultVO searchVO) throws Exception;

    /**
     * Cofix 이자율을 조회 한다.
     * @return
     * @throws Exception
     */
    Map selectHtldCofixPk(GamHtldMngFeeDefaultVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    EgovMap selectHtldMngFeeMngtSum(GamHtldMngFeeDefaultVO searchVO) throws Exception;

    /**
	 * 배후단지관리비고지관리정보를 수정한다.
	 * @param vo GamHtldMngFeeMngtVO
	 * @exception Exception
	 */
	void updateHtldMngFeeMngt(GamHtldMngFeeMngtVO vo) throws Exception;

	/**
	 * 배후단지관리비고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지관리비고지관리정보
	 * @exception Exception
	 */
    GamHtldMngFeeMngtVO selectHtldMngFeeMngtInfo(GamHtldMngFeeMngtVO searchVO) throws Exception;

    /**
   	 * 세입징수 등록건수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectAnlrveLevCnt(GamHtldMngFeeMngtVO searchVO) throws Exception;

    /**
   	 * 세입징수를 등록한다.
   	 * @param vo GamHtldMngFeeMngtVO
   	 * @exception Exception
   	 */
   	void insertAnlrveLev(GamHtldMngFeeMngtVO vo) throws Exception;

   	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamHtldMngFeeMngtVO
	 * @exception Exception
	 */
	void deleteHtldMngFeeMngt(GamHtldMngFeeMngtVO vo) throws Exception;

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamHtldMngFeeMngtVO
	 * @exception Exception
	 */
	public void insertHtldMngFeeMngtLevReqest(GamHtldMngFeeMngtVO vo) throws Exception;

	List selectNpticPrintInfo(Map searchVO) throws Exception;

	List selectTaxNtcPrintInfo(Map searchVO) throws Exception;

	void updateAssetMngFeeMngtListDetail(GamHtldMngFeeMngtVO vo) throws Exception;

	List selectAssetMngFeeDetailList(GamHtldMngFeeMngtVO searchVO);

	Map selectAssetMngFeeDetailMstPk(GamHtldMngFeeMngtVO searchVO);

	Map selectAssetMngFeeDetailSumPk(GamHtldMngFeeMngtVO searchVO);

	/**
	 * 고지 정보를 불러온다.
	 * @param searchVO
	 * @return
	 */
	Map selectNoticeRequest(GamHtldMngFeeMngtVO searchVO);


	void updateHtldMngFee(List<GamHtldMngFeeMngtVO> createList,  List<GamHtldMngFeeMngtVO> updateList) throws Exception;

	Map selectAssetLevReqestNticPk(GamHtldMngFeeMngtVO searchVO);

	/**
	 * 고지처리
	 * @param vo
	 * @throws Exception
	 */
	public void sendLevReqestRevCollF(Map<String, Object> vo) throws Exception;

	public List selectNticPrintMaster(Map searchVO) throws Exception;

	public List selectNticPrintDetail(Map searchVO) throws Exception;

	public void cancelNticRequest(Map<String, Object> vo) throws Exception;

	public void updateNticPrintState(Map<String, Object> vo) throws Exception;

	public void clearHtldMngFeeList(GamHtldMngFeeDefaultVO vo) throws Exception;

	/**
	 * 징수의뢰 정보 목록을 삭제 한다.
	 * @param vo
	 * @throws Exception
	 */
	void deleteHtldMngFeeMngtList(GamHtldMngFeeDefaultVO vo) throws Exception;


}