package egovframework.rte.ygpa.gam.oper.shed.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtVO;




/**
 * @Class Name : GamCmmnCntrRentMngtService.java
 * @Description : 공컨장치장임대목록관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamCmmnCntrRentMngtService {

	/**
	 * 항만부지임대사용 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만부지임대사용목록
	 * @exception Exception
	 */
    List selectCmmnCntrRentMngtList(GamCmmnCntrRentMngtVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만부지임대사용목록
	 * @exception Exception
	 */
    GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtSum(GamCmmnCntrRentMngtVO searchVO) throws Exception;
   
    /**
     * 임대 정보 내역을 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
    Map selectCmmnCntrRentMngtMasterInfo(GamCmmnCntrRentMngtVO vo) throws Exception;

    /**
     * 고지된 자료가 있는지 조회 한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectRentFeeNoticeListCount(GamCmmnCntrRentMngtVO searchVO) throws Exception;

    /**
	 * 항만부지임대사용 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만부지임대사용 목록 총 갯수
	 * @exception
	 */
    int selectCmmnCntrRentMngtListTotCnt(GamCmmnCntrRentMngtVO searchVO) throws Exception;


    /**
	 * 항만부지임대사용 최초 신청을 등록한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	void insertCmmnCntrRentMngtFirst(GamCmmnCntrRentMngtVO vo) throws Exception;

	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만부지임대사용목록
	 * @exception Exception
	 */
    GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtMaxNo(GamCmmnCntrRentMngtVO searchVO) throws Exception;

    /**
	 * 항만부지임대사용 연장 신청을 등록한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	void insertCmmnCntrRentMngtRenew(GamCmmnCntrRentMngtVO vo) throws Exception;

	/**
	 * 항만부지임대사용정보를 수정한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	void updateCmmnCntrRentMngt(GamCmmnCntrRentMngtVO vo) throws Exception;

	/**
	 * 항만부지임대사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrRentMngtDetailList(GamCmmnCntrRentMngtVO vo) throws Exception;

    /**
	 * 항만부지임대사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrRentMngtDetailListTotCnt(GamCmmnCntrRentMngtVO vo) throws Exception;

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
    int selectCmmnCntrRentMngtLevReqestCnt(GamCmmnCntrRentMngtVO vo) throws Exception;

    /**
	 * 항만부지임대사용 정보를 삭제한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteCmmnCntrRentMngt(GamCmmnCntrRentMngtVO vo) throws Exception;

	/**
	 * 항만부지임대사용 상세정보를 삭제한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteCmmnCntrRentMngtDetail(GamCmmnCntrRentMngtVO vo) throws Exception;

	/**
	 * 항만부지임대사용 상세를 등록한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	void insertCmmnCntrRentMngtDetail(GamCmmnCntrRentMngtDetailVO vo) throws Exception;

	/**
	 * 항만부지임대사용 상세를 수정한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	void updateCmmnCntrRentMngtDetail(GamCmmnCntrRentMngtDetailVO vo) throws Exception;

	/**
	 * 항만부지임대사용 상세를 삭제한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteCmmnCntrRentMngtDetail2(GamCmmnCntrRentMngtDetailVO vo) throws Exception;

	/**
	 * 승낙할 항만부지임대사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만부지임대사용정보
	 * @exception Exception
	 */
    GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtPrmisnInfo(GamCmmnCntrRentMngtVO searchVO) throws Exception;

	/**
	 * 항만부지임대사용 허가여부를 취소한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	void updateCmmnCntrRentMngtPrmisnCancel(GamCmmnCntrRentMngtLevReqestVO vo) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrRentMngtFileList(GamCmmnCntrRentMngtVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmmnCntrRentMngtFileListTotCnt(GamCmmnCntrRentMngtVO searchVO) throws Exception;

    /**
	 * 파일을 등록한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	void insertCmmnCntrRentMngtFile(GamCmmnCntrRentMngtVO vo) throws Exception;

	/**
	 * 파일을 수정한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	void updateCmmnCntrRentMngtFile(GamCmmnCntrRentMngtVO vo) throws Exception;

	/**
	 * 파일을 삭제한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	void deleteCmmnCntrRentMngtPhotoSingle(GamCmmnCntrRentMngtVO vo) throws Exception;

	/**
	 * 항만부지임대사용 신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return  항만부지임대사용 목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtMaxKey(GamCmmnCntrRentMngtVO searchVO) throws Exception;

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentMngtComment(GamCmmnCntrRentMngtVO vo) throws Exception;

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만부지임대사용 목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtRenewInfo(GamCmmnCntrRentMngtVO searchVO) throws Exception;

    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentMngtRenewInfo(GamCmmnCntrRentMngtVO vo) throws Exception;

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만부지임대사용 목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtCurrRenewInfo(GamCmmnCntrRentMngtVO searchVO) throws Exception;

    /**
   	 * 신청저장시 항만부지임대사용 상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 항만부지임대사용 목록
   	 * @exception Exception
   	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtDetailQuaycd(GamCmmnCntrRentMngtVO searchVO) throws Exception;

    /**
   	 * 신청저장시 항만부지임대사용 테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamCmmnCntrRentMngtDetailVO
   	 * @exception Exception
   	 */
   	public void updateCmmnCntrRentMngtQuaycd(GamCmmnCntrRentMngtVO vo) throws Exception;

   	/**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception;

    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만부지임대사용목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtBeforeQuarterInfo(GamCmmnCntrRentMngtVO searchVO) throws Exception;

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만부지임대사용목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtCofixInfo(GamCmmnCntrRentMngtVO searchVO) throws Exception;

    /**
   	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 항만부지임대사용목록
   	 * @exception Exception
   	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtCofixInfoMax(GamCmmnCntrRentMngtVO searchVO) throws Exception;

    public List selectChargeKndList(GamCmmnCntrRentMngtVO searchVO) throws Exception;

}
