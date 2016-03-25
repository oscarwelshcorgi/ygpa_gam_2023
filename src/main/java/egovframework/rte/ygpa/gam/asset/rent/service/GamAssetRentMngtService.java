package egovframework.rte.ygpa.gam.asset.rent.service;



import java.util.List;
import java.util.Map;

import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeVO;



/**
 * @Class Name : GamAssetRentMngtService.java
 * @Description : 항만시설사용목록관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetRentMngtService {

	/**
	 * 항만시설사용 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    List selectAssetRentMngtList(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    GamAssetRentMngtVO selectAssetRentMngtSum(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 항만시설사용 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록 총 갯수
	 * @exception
	 */
    int selectAssetRentMngtListTotCnt(GamAssetRentMngtVO searchVO) throws Exception;


    /**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	void insertAssetRentMngtFirst(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    GamAssetRentMngtVO selectAssetRentMngtMaxNo(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	void insertAssetRentMngtRenew(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 항만시설사용정보를 수정한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	void updateAssetRentMngt(GamAssetRentMngtVO vo) throws Exception;

    /**
     * 임대 정보 내역을 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
    Map selectAssetRentMngtMasterInfo(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 항만시설사용 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetRentMngtDetailList(GamAssetRentMngtVO vo) throws Exception;

    /**
	 * 항만시설사용 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetRentMngtDetailListTotCnt(GamAssetRentMngtVO vo) throws Exception;

    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectOlnlpInfo(GamGisAssetCodeVO searchVO) throws Exception;
    
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetRentMngtLevReqestCnt(GamAssetRentMngtVO vo) throws Exception;

    /**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamAssetRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteAssetRentMngt(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamAssetRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteAssetRentMngtDetail(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamAssetRentMngtDetailVO
	 * @exception Exception
	 */
	void insertAssetRentMngtDetail(GamAssetRentDetailVO vo) throws Exception;
            
	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamAssetRentMngtDetailVO
	 * @exception Exception
	 */
	void updateAssetRentMngtDetail(GamAssetRentDetailVO vo) throws Exception;

	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamAssetRentMngtDetailVO
	 * @exception Exception
	 */
	void deleteAssetRentMngtDetail2(GamAssetRentDetailVO vo) throws Exception;

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
    GamAssetRentMngtVO selectAssetRentMngtPrmisnInfo(GamAssetRentMngtVO searchVO) throws Exception;

	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	void updateAssetRentMngtPrmisnCancel(GamAssetRentLevReqestVO vo) throws Exception;

	/**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetRentMngtFileList(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetRentMngtFileListTotCnt(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 파일을 등록한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	void insertAssetRentMngtFile(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 파일을 수정한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	void updateAssetRentMngtFile(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 파일을 삭제한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	void deleteAssetRentMngtPhotoSingle(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 항만시설사용 신규저장시 키값 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return  항만시설사용 목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtMaxKey(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamAssetRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentMngtComment(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtRenewInfo(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentMngtRenewInfo(GamAssetRentMngtVO vo) throws Exception;

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtCurrRenewInfo(GamAssetRentMngtVO searchVO) throws Exception;

    /**
   	 * 신청저장시 항만시설사용 상세테이블의 (MIN)순번의 부두코드 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 항만시설사용 목록
   	 * @exception Exception
   	 */
    public GamAssetRentMngtVO selectAssetRentMngtDetailQuaycd(GamAssetRentMngtVO searchVO) throws Exception;

    /**
   	 * 신청저장시 항만시설사용 테이블의 부두코드를 업데이트 한다.
   	 * @param vo GamAssetRentMngtDetailVO
   	 * @exception Exception
   	 */
   	public void updateAssetRentMngtQuaycd(GamAssetRentMngtVO vo) throws Exception;

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
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtBeforeQuarterInfo(GamAssetRentMngtVO searchVO) throws Exception;

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용목록
	 * @exception Exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtCofixInfo(GamAssetRentMngtVO searchVO) throws Exception;

    /**
   	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 항만시설사용목록
   	 * @exception Exception
   	 */
    public GamAssetRentMngtVO selectAssetRentMngtCofixInfoMax(GamAssetRentMngtVO searchVO) throws Exception;

    public List selectChargeKndList(GamAssetRentMngtVO searchVO) throws Exception;

    /**
     * 고지된 자료가 있는지 조회 한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectRentFeeNoticeListCount(GamAssetRentMngtVO searchVO) throws Exception;
}
