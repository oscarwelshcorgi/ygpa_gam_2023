package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtVO;

/**
 * @Class Name : GamPrtFcltyRentMngtDao.java
 * @Description : 항만시설사용목록관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPrtFcltyRentMngtDao")
public class GamPrtFcltyRentMngtDao extends YGPAAbstractDAO {

	/**
	 * 항만시설사용관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtList(GamPrtFcltyRentMngtVO searchVO) throws Exception {
        return list("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtList_D", searchVO);
    }

    /**
	 * 항만시설사용관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentMngtListTotCnt(GamPrtFcltyRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtSum(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentMngtVO) selectByPk("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtSum_S", searchVO);
	}

	/**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtFirst(GamPrtFcltyRentMngtVO vo){
		insert("gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtFirst_S", vo);
	}


	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamPrtFcltyRentMngtVO
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtMaxNo(GamPrtFcltyRentMngtVO vo) {
        return (GamPrtFcltyRentMngtVO)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMaxNo_S", vo);
    }

    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtRenew(GamPrtFcltyRentMngtVO vo){
		insert("gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtRenew_S", vo);
	}

	/**
	 * 항만시설사용 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public String selectPrtFcltyRentMngtMaxMngCnt(GamPrtFcltyRentMngtVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMaxMngCnt_S", searchVO);
    }

	/**
	 * 항만시설사용 연장 신청시 항만시설사용 상세를 복사하여 등록한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtDetailRenew(GamPrtFcltyRentMngtDetailVO vo){
		insert("gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtDetailRenew_S", vo);
	}

	/**
	 * 항만시설사용 정보를 수정한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngt(GamPrtFcltyRentMngtVO vo){
		update("gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngt_S", vo);
	}

	/**
	 * 항만시설사용관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtDetailList(GamPrtFcltyRentMngtVO vo) throws Exception {
        return list("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailList_D", vo);
    }

    /**
	 * 항만시설사용관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentMngtDetailListTotCnt(GamPrtFcltyRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailListTotCnt_S", vo);
    }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentMngtLevReqestCnt(GamPrtFcltyRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtLevReqestCnt_S", vo);
    }

    /**
	 * 항만시설사용 사진정보를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtPhoto(GamPrtFcltyRentMngtVO vo){
		delete("gamPrtFcltyRentMngtDao.deletePrtFcltyRentMngtPhoto_S", vo);
	}

	/**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngt(GamPrtFcltyRentMngtVO vo){
		delete("gamPrtFcltyRentMngtDao.deletePrtFcltyRentMngt_S", vo);
	}

	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtDetail(GamPrtFcltyRentMngtVO vo){
		delete("gamPrtFcltyRentMngtDao.deletePrtFcltyRentMngtDetail_S", vo);
	}

	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtDetail(GamPrtFcltyRentMngtDetailVO vo){
		insert("gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtDetail_S", vo);
	}

	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtDetail(GamPrtFcltyRentMngtDetailVO vo){
		update("gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtDetail_S", vo);
	}

	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtDetail2(GamPrtFcltyRentMngtDetailVO vo){
		delete("gamPrtFcltyRentMngtDao.deletePrtFcltyRentMngtDetail2_S", vo);
	}

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
	public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtPrmisnInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentMngtVO) selectByPk("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtPrmisnInfo_S", searchVO);
	}

	/**
	 * 항만시설사용 허가여부를 수정한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtPrmisn(GamPrtFcltyRentMngtLevReqestVO vo){
		update("gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtPrmisn_S", vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyRentMngtLevReqestVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtLevReqest(GamPrtFcltyRentMngtLevReqestVO vo){
		insert("gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtLevReqest_S", vo);
	}

	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtPrmisnCancel(GamPrtFcltyRentMngtLevReqestVO vo){
		update("gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtPrmisnCancel_S", vo);
	}

	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamPrtFcltyRentMngtLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentMngtDao.selectUsagePdMonthCnt_S", vo);
    }

	/**
	 * 항만시설사용상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용상세 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtDetailInfo(GamPrtFcltyRentMngtVO vo) throws Exception {
        return list("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailInfo_S", vo);
    }

    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamPrtFcltyRentMngtDao.selectOlnlpInfo_S", null);
    }

    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtFileList(GamPrtFcltyRentMngtVO searchVO) throws Exception {
        return list("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentMngtFileListTotCnt(GamPrtFcltyRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtFileListTotCnt_S", searchVO);
    }

    /**
	 * 파일을 등록한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtFile(GamPrtFcltyRentMngtVO vo){
		insert("gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtFile_S", vo);
	}

	/**
	 * 파일을 수정한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtFile(GamPrtFcltyRentMngtVO vo){
		insert("gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtFile_S", vo);
	}

	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtPhotoSingle(GamPrtFcltyRentMngtVO vo){
		delete("gamPrtFcltyRentMngtDao.deletePrtFcltyRentMngtPhotoSingle_S", vo);
	}

	/**
	 * 항만시설사용 신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtMaxKey(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentMngtVO) selectByPk("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMaxKey_S", searchVO);
	}

	/**
	 * 코멘트를 수정한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtComment(GamPrtFcltyRentMngtVO vo){
		update("gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtComment_S", vo);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
	public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtRenewInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentMngtVO) selectByPk("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtRenewInfo_S", searchVO);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtRenewInfo(GamPrtFcltyRentMngtVO vo){
		update("gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtRenewInfo_S", vo);
	}

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
	public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtCurrRenewInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentMngtVO) selectByPk("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtCurrRenewInfo_S", searchVO);
	}

	/**
	 * 신청저장시 항만시설사용상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtDetailQuaycd(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentMngtVO) selectByPk("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailQuaycd_S", searchVO);
	}

	/**
	 * 신청저장시 항만시설사용테이블의 부두코드를 업데이트 한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtQuaycd(GamPrtFcltyRentMngtVO vo){
		update("gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtQuaycd_S", vo);
	}

	/**
	 * 코픽스 이자율 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectCofixInfo() throws Exception {
        return list("gamPrtFcltyRentMngtDao.selectCofixInfo_S", null);
    }

    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtBeforeQuarterInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentMngtVO) selectByPk("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtBeforeQuarterInfo_S", searchVO);
	}

	/**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtCofixInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentMngtVO) selectByPk("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtCofixInfo_S", searchVO);
	}

	/**
	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtCofixInfoMax(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentMngtVO) selectByPk("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtCofixInfoMax_S", searchVO);
	}

	public List selectChargeKndList(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return list("gamPrtFcltyRentMngtDao.selectChargeKndList_D", searchVO);
	}


	public Map selectPrtFcltyRentMngtMasterInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return (Map) selectByPk("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMasterInfo_S", searchVO);
	}

	/**
	 * 고지된 자료 갯수를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public int selectRentFeeNoticeListCount(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentMngtDao.selectRentFeeNoticeListCount_S", searchVO);
	}
}