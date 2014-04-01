package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO;

/**
 * @Class Name : GamHtldRentMngtDao.java
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
@Repository("gamHtldRentMngtDao")
public class GamHtldRentMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 배후단지임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldRentMngtList(GamHtldRentMngtVO searchVO) throws Exception {
        return list("gamHtldRentMngtDao.selectHtldRentMngtList_D", searchVO);
    }

    /**
	 * 배후단지임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentMngtListTotCnt(GamHtldRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectHtldRentMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldRentMngtVO selectHtldRentMngtSum(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtSum_S", searchVO);
	}
    
	/**
	 * 배후단지임대 최초 신청을 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void insertHtldRentMngtFirst(GamHtldRentMngtVO vo){
		insert("gamHtldRentMngtDao.insertHtldRentMngtFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamHtldRentMngtVO
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public GamHtldRentMngtVO selectHtldRentMngtMaxNo(GamHtldRentMngtVO vo) {
        return (GamHtldRentMngtVO)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectHtldRentMngtMaxNo_S", vo);
    }
    
    /**
	 * 배후단지임대 연장 신청을 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void insertHtldRentMngtRenew(GamHtldRentMngtVO vo){
		insert("gamHtldRentMngtDao.insertHtldRentMngtRenew_S", vo);
	}
	
	/**
	 * 배후단지임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectHtldRentMngtMaxMngCnt(GamHtldRentMngtVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectHtldRentMngtMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 배후단지임대 연장 신청시 배후단지임대 상세를 복사하여 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void insertHtldRentMngtDetailRenew(GamHtldRentMngtDetailVO vo){
		insert("gamHtldRentMngtDao.insertHtldRentMngtDetailRenew_S", vo);
	}
	
	/**
	 * 배후단지임대 정보를 수정한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentMngt(GamHtldRentMngtVO vo){
		update("gamHtldRentMngtDao.updateHtldRentMngt_S", vo);
	}
	
	/**
	 * 배후단지임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldRentMngtDetailList(GamHtldRentMngtVO vo) throws Exception {
        return list("gamHtldRentMngtDao.selectHtldRentMngtDetailList_D", vo);
    }

    /**
	 * 배후단지임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentMngtDetailListTotCnt(GamHtldRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectHtldRentMngtDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentMngtLevReqestCnt(GamHtldRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectHtldRentMngtLevReqestCnt_S", vo);
    }
    
    /**
	 * 배후단지임대 사진정보를 삭제한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void deleteHtldRentMngtPhoto(GamHtldRentMngtVO vo){
		delete("gamHtldRentMngtDao.deleteHtldRentMngtPhoto_S", vo);
	}
    
	/**
	 * 배후단지임대 정보를 삭제한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void deleteHtldRentMngt(GamHtldRentMngtVO vo){
		delete("gamHtldRentMngtDao.deleteHtldRentMngt_S", vo);
	}
    
	/**
	 * 배후단지임대 상세정보를 삭제한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void deleteHtldRentMngtDetail(GamHtldRentMngtVO vo){
		delete("gamHtldRentMngtDao.deleteHtldRentMngtDetail_S", vo);
	}
	
	/**
	 * 배후단지임대 상세를 등록한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertHtldRentMngtDetail(GamHtldRentMngtDetailVO vo){
		insert("gamHtldRentMngtDao.insertHtldRentMngtDetail_S", vo);
	}
	
	/**
	 * 배후단지임대 상세를 수정한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtDetail(GamHtldRentMngtDetailVO vo){
		update("gamHtldRentMngtDao.updateHtldRentMngtDetail_S", vo);
	}
	
	/**
	 * 배후단지임대 상세를 삭제한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteHtldRentMngtDetail2(GamHtldRentMngtDetailVO vo){
		delete("gamHtldRentMngtDao.deleteHtldRentMngtDetail2_S", vo);
	}

	/**
	 * 승낙할 배후단지임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대정보
	 * @exception Exception
	 */
	public GamHtldRentMngtVO selectHtldRentMngtPrmisnInfo(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtPrmisnInfo_S", searchVO);
	}
	
	/**
	 * 배후단지임대 허가여부를 수정한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtPrmisn(GamHtldRentMngtLevReqestVO vo){
		update("gamHtldRentMngtDao.updateHtldRentMngtPrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamHtldRentMngtLevReqestVO
	 * @exception Exception
	 */
	public void insertHtldRentMngtLevReqest(GamHtldRentMngtLevReqestVO vo){
		insert("gamHtldRentMngtDao.insertHtldRentMngtLevReqest_S", vo);
	}
	
	/**
	 * 배후단지임대 허가여부를 취소한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtPrmisnCancel(GamHtldRentMngtLevReqestVO vo){
		update("gamHtldRentMngtDao.updateHtldRentMngtPrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamHtldRentMngtLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 배후단지임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대상세 목록
	 * @exception Exception
	 */
    public List selectHtldRentMngtDetailInfo(GamHtldRentMngtVO vo) throws Exception {
        return list("gamHtldRentMngtDao.selectHtldRentMngtDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamHtldRentMngtDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldRentMngtFileList(GamHtldRentMngtVO searchVO) throws Exception {
        return list("gamHtldRentMngtDao.selectHtldRentMngtFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentMngtFileListTotCnt(GamHtldRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectHtldRentMngtFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void insertHtldRentMngtFile(GamHtldRentMngtVO vo){
		insert("gamHtldRentMngtDao.insertHtldRentMngtFile_S", vo);
	}
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtFile(GamHtldRentMngtVO vo){
		insert("gamHtldRentMngtDao.updateHtldRentMngtFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void deleteHtldRentMngtPhotoSingle(GamHtldRentMngtVO vo){
		delete("gamHtldRentMngtDao.deleteHtldRentMngtPhotoSingle_S", vo);
	}
	
	/**
	 * 배후단지임대 신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldRentMngtVO selectHtldRentMngtMaxKey(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtComment(GamHtldRentMngtVO vo){
		update("gamHtldRentMngtDao.updateHtldRentMngtComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대 목록
	 * @exception Exception
	 */
	public GamHtldRentMngtVO selectHtldRentMngtRenewInfo(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtRenewInfo(GamHtldRentMngtVO vo){
		update("gamHtldRentMngtDao.updateHtldRentMngtRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대 목록
	 * @exception Exception
	 */
	public GamHtldRentMngtVO selectHtldRentMngtCurrRenewInfo(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 배후단지임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldRentMngtVO selectHtldRentMngtDetailQuaycd(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 배후단지임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtQuaycd(GamHtldRentMngtVO vo){
		update("gamHtldRentMngtDao.updateHtldRentMngtQuaycd_S", vo);
	}
	
	/**
	 * 코픽스 이자율 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectCofixInfo() throws Exception {
        return list("gamHtldRentMngtDao.selectCofixInfo_S", null);
    }
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldRentMngtVO selectHtldRentMngtBeforeQuarterInfo(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtBeforeQuarterInfo_S", searchVO);
	}
	
	/**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldRentMngtVO selectHtldRentMngtCofixInfo(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtCofixInfo_S", searchVO);
	}
	
	/**
	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldRentMngtVO selectHtldRentMngtCofixInfoMax(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtCofixInfoMax_S", searchVO);
	}
}