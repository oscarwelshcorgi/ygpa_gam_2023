package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentMngtVO;

/**
 * @Class Name : GamTestPrtFcltyRentMngtDao.java
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
@Repository("gamTestPrtFcltyRentMngtDao")
public class GamTestPrtFcltyRentMngtDao extends YGPAAbstractDAO {

	/**
	 * 항만시설사용관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtList(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
        return list("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtList_D", searchVO);
    }

    /**
	 * 항만시설사용관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentMngtListTotCnt(GamTestPrtFcltyRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtSum(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamTestPrtFcltyRentMngtVO) selectByPk("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtSum_S", searchVO);
	}

	/**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtFirst(GamTestPrtFcltyRentMngtVO vo){
		insert("gamTestPrtFcltyRentMngtDao.insertPrtFcltyRentMngtFirst_S", vo);
	}


	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamTestPrtFcltyRentMngtVO
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtMaxNo(GamTestPrtFcltyRentMngtVO vo) {
        return (GamTestPrtFcltyRentMngtVO)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMaxNo_S", vo);
    }

    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtRenew(GamTestPrtFcltyRentMngtVO vo){
		insert("gamTestPrtFcltyRentMngtDao.insertPrtFcltyRentMngtRenew_S", vo);
	}

	/**
	 * 항만시설사용 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public String selectPrtFcltyRentMngtMaxMngCnt(GamTestPrtFcltyRentMngtVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMaxMngCnt_S", searchVO);
    }

	/**
	 * 항만시설사용 연장 신청시 항만시설사용 상세를 복사하여 등록한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtDetailRenew(GamTestPrtFcltyRentMngtDetailVO vo){
		insert("gamTestPrtFcltyRentMngtDao.insertPrtFcltyRentMngtDetailRenew_S", vo);
	}

	/**
	 * 항만시설사용 정보를 수정한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngt(GamTestPrtFcltyRentMngtVO vo){
		update("gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngt_S", vo);
	}

	/**
	 * 항만시설사용관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtDetailList(GamTestPrtFcltyRentMngtVO vo) throws Exception {
        return list("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailList_D", vo);
    }

    /**
	 * 항만시설사용관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentMngtDetailListTotCnt(GamTestPrtFcltyRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailListTotCnt_S", vo);
    }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentMngtLevReqestCnt(GamTestPrtFcltyRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtLevReqestCnt_S", vo);
    }

    /**
	 * 항만시설사용 사진정보를 삭제한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtPhoto(GamTestPrtFcltyRentMngtVO vo){
		delete("gamTestPrtFcltyRentMngtDao.deletePrtFcltyRentMngtPhoto_S", vo);
	}

	/**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngt(GamTestPrtFcltyRentMngtVO vo){
		delete("gamTestPrtFcltyRentMngtDao.deletePrtFcltyRentMngt_S", vo);
	}

	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtDetail(GamTestPrtFcltyRentMngtVO vo){
		delete("gamTestPrtFcltyRentMngtDao.deletePrtFcltyRentMngtDetail_S", vo);
	}

	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtDetail(GamTestPrtFcltyRentMngtDetailVO vo){
		insert("gamTestPrtFcltyRentMngtDao.insertPrtFcltyRentMngtDetail_S", vo);
	}

	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtDetail(GamTestPrtFcltyRentMngtDetailVO vo){
		update("gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngtDetail_S", vo);
	}

	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtDetail2(GamTestPrtFcltyRentMngtDetailVO vo){
		delete("gamTestPrtFcltyRentMngtDao.deletePrtFcltyRentMngtDetail2_S", vo);
	}

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
	public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtPrmisnInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamTestPrtFcltyRentMngtVO) selectByPk("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtPrmisnInfo_S", searchVO);
	}

	/**
	 * 항만시설사용 허가여부를 수정한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtPrmisn(GamTestPrtFcltyRentMngtLevReqestVO vo){
		update("gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngtPrmisn_S", vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamTestPrtFcltyRentMngtLevReqestVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtLevReqest(GamTestPrtFcltyRentMngtLevReqestVO vo){
		insert("gamTestPrtFcltyRentMngtDao.insertPrtFcltyRentMngtLevReqest_S", vo);
	}

	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtPrmisnCancel(GamTestPrtFcltyRentMngtLevReqestVO vo){
		update("gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngtPrmisnCancel_S", vo);
	}

	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamTestPrtFcltyRentMngtLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyRentMngtDao.selectUsagePdMonthCnt_S", vo);
    }

	/**
	 * 항만시설사용상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용상세 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtDetailInfo(GamTestPrtFcltyRentMngtVO vo) throws Exception {
        return list("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailInfo_S", vo);
    }

    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamTestPrtFcltyRentMngtDao.selectOlnlpInfo_S", null);
    }

    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtFileList(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
        return list("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentMngtFileListTotCnt(GamTestPrtFcltyRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtFileListTotCnt_S", searchVO);
    }

    /**
	 * 파일을 등록한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtFile(GamTestPrtFcltyRentMngtVO vo){
		insert("gamTestPrtFcltyRentMngtDao.insertPrtFcltyRentMngtFile_S", vo);
	}

	/**
	 * 파일을 수정한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtFile(GamTestPrtFcltyRentMngtVO vo){
		insert("gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngtFile_S", vo);
	}

	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtPhotoSingle(GamTestPrtFcltyRentMngtVO vo){
		delete("gamTestPrtFcltyRentMngtDao.deletePrtFcltyRentMngtPhotoSingle_S", vo);
	}

	/**
	 * 항만시설사용 신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtMaxKey(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamTestPrtFcltyRentMngtVO) selectByPk("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMaxKey_S", searchVO);
	}

	/**
	 * 코멘트를 수정한다.
	 * @param vo GamTestPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtComment(GamTestPrtFcltyRentMngtVO vo){
		update("gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngtComment_S", vo);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtRenewInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamTestPrtFcltyRentMngtVO) selectByPk("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtRenewInfo_S", searchVO);
	}

	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtRenewInfo(GamTestPrtFcltyRentMngtVO vo){
		update("gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngtRenewInfo_S", vo);
	}

	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtCurrRenewInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamTestPrtFcltyRentMngtVO) selectByPk("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtCurrRenewInfo_S", searchVO);
	}

	/**
	 * 신청저장시 항만시설사용상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtDetailQuaycd(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamTestPrtFcltyRentMngtVO) selectByPk("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailQuaycd_S", searchVO);
	}

	/**
	 * 신청저장시 항만시설사용테이블의 부두코드를 업데이트 한다.
	 * @param vo GamTestPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtQuaycd(GamTestPrtFcltyRentMngtVO vo){
		update("gamTestPrtFcltyRentMngtDao.updatePrtFcltyRentMngtQuaycd_S", vo);
	}

	/**
	 * 코픽스 이자율 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectCofixInfo() throws Exception {
        return list("gamTestPrtFcltyRentMngtDao.selectCofixInfo_S", null);
    }

    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtBeforeQuarterInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamTestPrtFcltyRentMngtVO) selectByPk("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtBeforeQuarterInfo_S", searchVO);
	}

	/**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtCofixInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamTestPrtFcltyRentMngtVO) selectByPk("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtCofixInfo_S", searchVO);
	}

	/**
	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyRentMngtVO selectPrtFcltyRentMngtCofixInfoMax(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamTestPrtFcltyRentMngtVO) selectByPk("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtCofixInfoMax_S", searchVO);
	}

	public List selectChargeKndList(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
		return list("gamTestPrtFcltyRentMngtDao.selectChargeKndList_D", searchVO);
	}


	public Map selectPrtFcltyRentMngtMasterInfo(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
		return (Map) selectByPk("gamTestPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMasterInfo_S", searchVO);
	}

	/**
	 * 고지된 자료 갯수를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public int selectRentFeeNoticeListCount(GamTestPrtFcltyRentMngtVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyRentMngtDao.selectRentFeeNoticeListCount_S", searchVO);
	}
}