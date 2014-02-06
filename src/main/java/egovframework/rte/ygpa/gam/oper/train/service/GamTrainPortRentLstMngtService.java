package egovframework.rte.ygpa.gam.oper.train.service;

import java.util.List;

import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentLstMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentLstMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentLstMngtVO;

/**
 * @Class Name : GamTrainPortRentLstMngtService.java
 * @Description : 철송장임대목록관리 (철송장/철송장/철송장임대목록관리)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamTrainPortRentLstMngtService {
	
	/**
	 * 철송장 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectTrainPortRentLstMngtList(GamTrainPortRentLstMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamTrainPortRentLstMngtVO selectTrainPortRentLstMngtSum(GamTrainPortRentLstMngtVO searchVO) throws Exception;
    
    /**
	 * 철송장 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectTrainPortRentLstMngtListTotCnt(GamTrainPortRentLstMngtVO searchVO) throws Exception;
	
	
    /**
	 * 철송장 최초 신청을 등록한다.
	 * @param vo GamTrainPortRentLstMngtVO
	 * @exception Exception
	 */
	void insertTrainPortRentLstMngtFirst(GamTrainPortRentLstMngtVO vo) throws Exception;
	
	/**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamTrainPortRentLstMngtVO selectTrainPortRentLstMngtMaxNo(GamTrainPortRentLstMngtVO searchVO) throws Exception;
    
    /**
	 * 철송장 연장 신청을 등록한다.
	 * @param vo GamTrainPortRentLstMngtVO
	 * @exception Exception
	 */
	void insertTrainPortRentLstMngtRenew(GamTrainPortRentLstMngtVO vo) throws Exception;
	
	/**
	 * 철송장 정보를 수정한다.
	 * @param vo GamTrainPortRentLstMngtVO
	 * @exception Exception
	 */
	void updateTrainPortRentLstMngt(GamTrainPortRentLstMngtVO vo) throws Exception;
	
	/**
	 * 철송장 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortRentLstMngtDetailList(GamTrainPortRentLstMngtVO vo) throws Exception;

    /**
	 * 철송장 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortRentLstMngtDetailListTotCnt(GamTrainPortRentLstMngtVO vo) throws Exception;
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortRentLstMngtLevReqestCnt(GamTrainPortRentLstMngtVO vo) throws Exception;
    
    /**
	 * 철송장 정보를 삭제한다.
	 * @param vo GamTrainPortRentLstMngtDetailVO
	 * @exception Exception
	 */
	void deleteTrainPortRentLstMngt(GamTrainPortRentLstMngtVO vo) throws Exception;
	
	/**
	 * 철송장 상세정보를 삭제한다.
	 * @param vo GamTrainPortRentLstMngtDetailVO
	 * @exception Exception
	 */
	void deleteTrainPortRentLstMngtDetail(GamTrainPortRentLstMngtVO vo) throws Exception;
	
	/**
	 * 철송장 상세를 등록한다.
	 * @param vo GamTrainPortRentLstMngtDetailVO
	 * @exception Exception
	 */
	void insertTrainPortRentLstMngtDetail(GamTrainPortRentLstMngtDetailVO vo) throws Exception;
	
	/**
	 * 철송장 상세를 수정한다.
	 * @param vo GamTrainPortRentLstMngtDetailVO
	 * @exception Exception
	 */
	void updateTrainPortRentLstMngtDetail(GamTrainPortRentLstMngtDetailVO vo) throws Exception;
	
	/**
	 * 철송장 상세를 삭제한다.
	 * @param vo GamTrainPortRentLstMngtDetailVO
	 * @exception Exception
	 */
	void deleteTrainPortRentLstMngtDetail2(GamTrainPortRentLstMngtDetailVO vo) throws Exception;
	
	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대정보
	 * @exception Exception
	 */
	GamTrainPortRentLstMngtVO selectTrainPortRentLstMngtPrmisnInfo(GamTrainPortRentLstMngtVO searchVO) throws Exception;

    /**
	 * 철송장 허가여부를 수정 및 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyRentLevReqestVO
	 * @exception Exception
	 */
	void updateTrainPortRentLstMngtPrmisn(GamTrainPortRentLstMngtLevReqestVO vo) throws Exception;
	
	/**
	 * 철송장 허가여부를 취소한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	void updateTrainPortRentLstMngtPrmisnCancel(GamTrainPortRentLstMngtLevReqestVO vo) throws Exception;
}
