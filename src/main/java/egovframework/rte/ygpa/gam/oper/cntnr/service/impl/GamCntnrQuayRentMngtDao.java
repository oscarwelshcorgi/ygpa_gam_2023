package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentMngtVO;

/**
 * @Class Name : GamCntnrQuayRentMngtDao.java
 * @Description : 컨테이너부두임대목록관리 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCntnrQuayRentMngtDao")
public class GamCntnrQuayRentMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 컨테이너부두임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayRentMngtList(GamCntnrQuayRentMngtVO searchVO) throws Exception {
        return list("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtList_D", searchVO);
    }

    /**
	 * 컨테이너부두임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayRentMngtListTotCnt(GamCntnrQuayRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtSum(GamCntnrQuayRentMngtVO searchVO) throws Exception {
		return (GamCntnrQuayRentMngtVO) selectByPk("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtSum_S", searchVO);
	}
    
	/**
	 * 컨테이너부두임대 최초 신청을 등록한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void insertCntnrQuayRentMngtFirst(GamCntnrQuayRentMngtVO vo){
		insert("gamCntnrQuayRentMngtDao.insertCntnrQuayRentMngtFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamCntnrQuayRentMngtVO
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtMaxNo(GamCntnrQuayRentMngtVO vo) {
        return (GamCntnrQuayRentMngtVO)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtMaxNo_S", vo);
    }
    
    /**
	 * 컨테이너부두임대 연장 신청을 등록한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void insertCntnrQuayRentMngtRenew(GamCntnrQuayRentMngtVO vo){
		insert("gamCntnrQuayRentMngtDao.insertCntnrQuayRentMngtRenew_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectCntnrQuayRentMngtMaxMngCnt(GamCntnrQuayRentMngtVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 컨테이너부두임대 연장 신청시 컨테이너부두임대 상세를 복사하여 등록한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void insertCntnrQuayRentMngtDetailRenew(GamCntnrQuayRentMngtDetailVO vo){
		insert("gamCntnrQuayRentMngtDao.insertCntnrQuayRentMngtDetailRenew_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 정보를 수정한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngt(GamCntnrQuayRentMngtVO vo){
		update("gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngt_S", vo);
	}
	
	/**
	 * 컨테이너부두임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayRentMngtDetailList(GamCntnrQuayRentMngtVO vo) throws Exception {
        return list("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtDetailList_D", vo);
    }

    /**
	 * 컨테이너부두임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayRentMngtDetailListTotCnt(GamCntnrQuayRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayRentMngtLevReqestCnt(GamCntnrQuayRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtLevReqestCnt_S", vo);
    }
    
    /**
	 * 컨테이너부두임대 사진정보를 삭제한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayRentMngtPhoto(GamCntnrQuayRentMngtVO vo){
		delete("gamCntnrQuayRentMngtDao.deleteCntnrQuayRentMngtPhoto_S", vo);
	}
    
	/**
	 * 컨테이너부두임대 정보를 삭제한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayRentMngt(GamCntnrQuayRentMngtVO vo){
		delete("gamCntnrQuayRentMngtDao.deleteCntnrQuayRentMngt_S", vo);
	}
    
	/**
	 * 컨테이너부두임대 상세정보를 삭제한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayRentMngtDetail(GamCntnrQuayRentMngtVO vo){
		delete("gamCntnrQuayRentMngtDao.deleteCntnrQuayRentMngtDetail_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 상세를 등록한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertCntnrQuayRentMngtDetail(GamCntnrQuayRentMngtDetailVO vo){
		insert("gamCntnrQuayRentMngtDao.insertCntnrQuayRentMngtDetail_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 상세를 수정한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngtDetail(GamCntnrQuayRentMngtDetailVO vo){
		update("gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngtDetail_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 상세를 삭제한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayRentMngtDetail2(GamCntnrQuayRentMngtDetailVO vo){
		delete("gamCntnrQuayRentMngtDao.deleteCntnrQuayRentMngtDetail2_S", vo);
	}

	/**
	 * 승낙할 컨테이너부두임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대정보
	 * @exception Exception
	 */
	public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtPrmisnInfo(GamCntnrQuayRentMngtVO searchVO) throws Exception {
		return (GamCntnrQuayRentMngtVO) selectByPk("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtPrmisnInfo_S", searchVO);
	}
	
	/**
	 * 컨테이너부두임대 허가여부를 수정한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngtPrmisn(GamCntnrQuayRentMngtLevReqestVO vo){
		update("gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngtPrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamCntnrQuayRentMngtLevReqestVO
	 * @exception Exception
	 */
	public void insertCntnrQuayRentMngtLevReqest(GamCntnrQuayRentMngtLevReqestVO vo){
		insert("gamCntnrQuayRentMngtDao.insertCntnrQuayRentMngtLevReqest_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 허가여부를 취소한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngtPrmisnCancel(GamCntnrQuayRentMngtLevReqestVO vo){
		update("gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngtPrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamCntnrQuayRentMngtLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentMngtDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 컨테이너부두임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대상세 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayRentMngtDetailInfo(GamCntnrQuayRentMngtVO vo) throws Exception {
        return list("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamCntnrQuayRentMngtDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayRentMngtFileList(GamCntnrQuayRentMngtVO searchVO) throws Exception {
        return list("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayRentMngtFileListTotCnt(GamCntnrQuayRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void insertCntnrQuayRentMngtFile(GamCntnrQuayRentMngtVO vo){
		insert("gamCntnrQuayRentMngtDao.insertCntnrQuayRentMngtFile_S", vo);
	}
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngtFile(GamCntnrQuayRentMngtVO vo){
		insert("gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngtFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayRentMngtPhotoSingle(GamCntnrQuayRentMngtVO vo){
		delete("gamCntnrQuayRentMngtDao.deleteCntnrQuayRentMngtPhotoSingle_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtMaxKey(GamCntnrQuayRentMngtVO searchVO) throws Exception {
		return (GamCntnrQuayRentMngtVO) selectByPk("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamCntnrQuayRentMngtVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngtComment(GamCntnrQuayRentMngtVO vo){
		update("gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngtComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대 목록
	 * @exception Exception
	 */
	public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtRenewInfo(GamCntnrQuayRentMngtVO searchVO) throws Exception {
		return (GamCntnrQuayRentMngtVO) selectByPk("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngtRenewInfo(GamCntnrQuayRentMngtVO vo){
		update("gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngtRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대 목록
	 * @exception Exception
	 */
	public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtCurrRenewInfo(GamCntnrQuayRentMngtVO searchVO) throws Exception {
		return (GamCntnrQuayRentMngtVO) selectByPk("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 컨테이너부두임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtDetailQuaycd(GamCntnrQuayRentMngtVO searchVO) throws Exception {
		return (GamCntnrQuayRentMngtVO) selectByPk("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 컨테이너부두임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamCntnrQuayRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentMngtQuaycd(GamCntnrQuayRentMngtVO vo){
		update("gamCntnrQuayRentMngtDao.updateCntnrQuayRentMngtQuaycd_S", vo);
	}
	
	/**
	 * 코픽스 이자율 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectCofixInfo() throws Exception {
        return list("gamCntnrQuayRentMngtDao.selectCofixInfo_S", null);
    }
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtBeforeQuarterInfo(GamCntnrQuayRentMngtVO searchVO) throws Exception {
		return (GamCntnrQuayRentMngtVO) selectByPk("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtBeforeQuarterInfo_S", searchVO);
	}
	
	/**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayRentMngtVO selectCntnrQuayRentMngtCofixInfo(GamCntnrQuayRentMngtVO searchVO) throws Exception {
		return (GamCntnrQuayRentMngtVO) selectByPk("gamCntnrQuayRentMngtDao.selectCntnrQuayRentMngtCofixInfo_S", searchVO);
	}
}