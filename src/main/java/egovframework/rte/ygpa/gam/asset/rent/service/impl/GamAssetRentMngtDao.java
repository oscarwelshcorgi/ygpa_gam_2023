package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentDetailVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentLevReqestVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeVO;

/**
 * @Class Name : GamAssetRentMngtDao.java
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
@Repository("gamAssetRentMngtDao")
public class GamAssetRentMngtDao extends YGPAAbstractDAO {

	/**
	 * 항만시설사용관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentMngtList(GamAssetRentMngtVO searchVO) throws Exception {
        return list("gamAssetRentMngtDao.selectAssetRentMngtList_D", searchVO);
    }

    /**
	 * 항만시설사용관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentMngtListTotCnt(GamAssetRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentMngtDao.selectAssetRentMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamAssetRentMngtVO selectAssetRentMngtSum(GamAssetRentMngtVO searchVO) throws Exception {
		return (GamAssetRentMngtVO) selectByPk("gamAssetRentMngtDao.selectAssetRentMngtSum_S", searchVO);
	}

	/**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void insertAssetRentMngtFirst(GamAssetRentMngtVO vo){
		insert("gamAssetRentMngtDao.insertAssetRentMngtFirst_S", vo);
	}


	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamAssetRentMngtVO
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public GamAssetRentMngtVO selectAssetRentMngtMaxNo(GamAssetRentMngtVO vo) {
        return (GamAssetRentMngtVO)getSqlMapClientTemplate().queryForObject("gamAssetRentMngtDao.selectAssetRentMngtMaxNo_S", vo);
    }

    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void insertAssetRentMngtRenew(GamAssetRentMngtVO vo){
		insert("gamAssetRentMngtDao.insertAssetRentMngtRenew_S", vo);
	}

	/**
	 * 항만시설사용 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public String selectAssetRentMngtMaxMngCnt(GamAssetRentMngtVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamAssetRentMngtDao.selectAssetRentMngtMaxMngCnt_S", searchVO);
    }

	/**
	 * 항만시설사용 연장 신청시 항만시설사용 상세를 복사하여 등록한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void insertAssetRentMngtDetailRenew(GamAssetRentDetailVO vo){
		insert("gamAssetRentMngtDao.insertAssetRentMngtDetailRenew_S", vo);
	}

	/**
	 * 항만시설사용 정보를 수정한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void updateAssetRentMngt(GamAssetRentMngtVO vo){
		update("gamAssetRentMngtDao.updateAssetRentMngt_S", vo);
	}

	/**
	 * 항만시설사용관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentMngtDetailList(GamAssetRentMngtVO vo) throws Exception {
        return list("gamAssetRentMngtDao.selectAssetRentMngtDetailList_D", vo);
    }

    /**
	 * 항만시설사용관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentMngtDetailListTotCnt(GamAssetRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentMngtDao.selectAssetRentMngtDetailListTotCnt_S", vo);
    }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentMngtLevReqestCnt(GamAssetRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentMngtDao.selectAssetRentMngtLevReqestCnt_S", vo);
    }

    /**
	 * 항만시설사용 사진정보를 삭제한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void deleteAssetRentMngtPhoto(GamAssetRentMngtVO vo){
		delete("gamAssetRentMngtDao.deleteAssetRentMngtPhoto_S", vo);
	}

	/**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void deleteAssetRentMngt(GamAssetRentMngtVO vo){
		delete("gamAssetRentMngtDao.deleteAssetRentMngt_S", vo);
	}

	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void deleteAssetRentMngtDetail(GamAssetRentMngtVO vo){
		delete("gamAssetRentMngtDao.deleteAssetRentMngtDetail_S", vo);
	}

	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void insertAssetRentMngtDetail(GamAssetRentDetailVO vo){
		insert("gamAssetRentMngtDao.insertAssetRentMngtDetail_S", vo);
	}

	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentMngtDetail(GamAssetRentDetailVO vo){
		update("gamAssetRentMngtDao.updateAssetRentMngtDetail_S", vo);
	}

	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void deleteAssetRentMngtDetail2(GamAssetRentDetailVO vo){
		delete("gamAssetRentMngtDao.deleteAssetRentMngtDetail2_S", vo);
	}

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
	public GamAssetRentMngtVO selectAssetRentMngtPrmisnInfo(GamAssetRentMngtVO searchVO) throws Exception {
		return (GamAssetRentMngtVO) selectByPk("gamAssetRentMngtDao.selectAssetRentMngtPrmisnInfo_S", searchVO);
	}

	/**
	 * 항만시설사용 허가여부를 수정한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void updateAssetRentMngtPrmisn(GamAssetRentLevReqestVO vo){
		update("gamAssetRentMngtDao.updateAssetRentMngtPrmisn_S", vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamAssetRentLevReqestVO
	 * @exception Exception
	 */
	public void insertAssetRentMngtLevReqest(GamAssetRentLevReqestVO vo){
		insert("gamAssetRentMngtDao.insertAssetRentMngtLevReqest_S", vo);
	}

	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void updateAssetRentMngtPrmisnCancel(GamAssetRentLevReqestVO vo){
		update("gamAssetRentMngtDao.updateAssetRentMngtPrmisnCancel_S", vo);
	}

	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamAssetRentLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentMngtDao.selectUsagePdMonthCnt_S", vo);
    }

	/**
	 * 항만시설사용상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용상세 목록
	 * @exception Exception
	 */
    public List selectAssetRentMngtDetailInfo(GamAssetRentMngtVO vo) throws Exception {
        return list("gamAssetRentMngtDao.selectAssetRentMngtDetailInfo_S", vo);
    }

    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo(GamGisAssetCodeVO searchVO) throws Exception {
        return list("gamAssetRentMngtDao.selectOlnlpInfo_S", searchVO);
    }

    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentMngtFileList(GamAssetRentMngtVO searchVO) throws Exception {
        return list("gamAssetRentMngtDao.selectAssetRentMngtFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentMngtFileListTotCnt(GamAssetRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentMngtDao.selectAssetRentMngtFileListTotCnt_S", searchVO);
    }

    /**
	 * 파일을 등록한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void insertAssetRentMngtFile(GamAssetRentMngtVO vo){
		insert("gamAssetRentMngtDao.insertAssetRentMngtFile_S", vo);
	}

	/**
	 * 파일을 수정한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void updateAssetRentMngtFile(GamAssetRentMngtVO vo){
		insert("gamAssetRentMngtDao.updateAssetRentMngtFile_S", vo);
	}

	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void deleteAssetRentMngtPhotoSingle(GamAssetRentMngtVO vo){
		delete("gamAssetRentMngtDao.deleteAssetRentMngtPhotoSingle_S", vo);
	}

	/**
	 * 항만시설사용 신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamAssetRentMngtVO selectAssetRentMngtMaxKey(GamAssetRentMngtVO searchVO) throws Exception {
		return (GamAssetRentMngtVO) selectByPk("gamAssetRentMngtDao.selectAssetRentMngtMaxKey_S", searchVO);
	}

	/**
	 * 코멘트를 수정한다.
	 * @param vo GamAssetRentMngtVO
	 * @exception Exception
	 */
	public void updateAssetRentMngtComment(GamAssetRentMngtVO vo){
		update("gamAssetRentMngtDao.updateAssetRentMngtComment_S", vo);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
	public GamAssetRentMngtVO selectAssetRentMngtRenewInfo(GamAssetRentMngtVO searchVO) throws Exception {
		return (GamAssetRentMngtVO) selectByPk("gamAssetRentMngtDao.selectAssetRentMngtRenewInfo_S", searchVO);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentMngtRenewInfo(GamAssetRentMngtVO vo){
		update("gamAssetRentMngtDao.updateAssetRentMngtRenewInfo_S", vo);
	}

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
	public GamAssetRentMngtVO selectAssetRentMngtCurrRenewInfo(GamAssetRentMngtVO searchVO) throws Exception {
		return (GamAssetRentMngtVO) selectByPk("gamAssetRentMngtDao.selectAssetRentMngtCurrRenewInfo_S", searchVO);
	}

	/**
	 * 신청저장시 항만시설사용상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamAssetRentMngtVO selectAssetRentMngtDetailQuaycd(GamAssetRentMngtVO searchVO) throws Exception {
		return (GamAssetRentMngtVO) selectByPk("gamAssetRentMngtDao.selectAssetRentMngtDetailQuaycd_S", searchVO);
	}

	/**
	 * 신청저장시 항만시설사용테이블의 부두코드를 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentMngtQuaycd(GamAssetRentMngtVO vo){
		update("gamAssetRentMngtDao.updateAssetRentMngtQuaycd_S", vo);
	}

	/**
	 * 코픽스 이자율 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectCofixInfo() throws Exception {
        return list("gamAssetRentMngtDao.selectCofixInfo_S", null);
    }

    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamAssetRentMngtVO selectAssetRentMngtBeforeQuarterInfo(GamAssetRentMngtVO searchVO) throws Exception {
		return (GamAssetRentMngtVO) selectByPk("gamAssetRentMngtDao.selectAssetRentMngtBeforeQuarterInfo_S", searchVO);
	}

	/**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamAssetRentMngtVO selectAssetRentMngtCofixInfo(GamAssetRentMngtVO searchVO) throws Exception {
		return (GamAssetRentMngtVO) selectByPk("gamAssetRentMngtDao.selectAssetRentMngtCofixInfo_S", searchVO);
	}

	/**
	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamAssetRentMngtVO selectAssetRentMngtCofixInfoMax(GamAssetRentMngtVO searchVO) throws Exception {
		return (GamAssetRentMngtVO) selectByPk("gamAssetRentMngtDao.selectAssetRentMngtCofixInfoMax_S", searchVO);
	}

	public List selectChargeKndList(GamAssetRentMngtVO searchVO) throws Exception {
		return list("gamAssetRentMngtDao.selectChargeKndList_D", searchVO);
	}


	public Map selectAssetRentMngtMasterInfo(GamAssetRentMngtVO searchVO) throws Exception {
		return (Map) selectByPk("gamAssetRentMngtDao.selectAssetRentMngtMasterInfo_S", searchVO);
	}

	/**
	 * 고지된 자료 갯수를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public int selectRentFeeNoticeListCount(GamAssetRentMngtVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentMngtDao.selectRentFeeNoticeListCount_S", searchVO);
	}
}