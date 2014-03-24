package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentDetailVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentLevReqestVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentMngtVO;

/**
 * @Class Name : GamMarineCenterRentMngtDao.java
 * @Description : 마린센터임대목록관리 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamMarineCenterRentMngtDao")
public class GamMarineCenterRentMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 마린센터임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentList(GamMarineCenterRentMngtVO searchVO) throws Exception {
        return list("gamMarineCenterRentMngtDao.selectMarineCenterRentList_D", searchVO);
    }

    /**
	 * 마린센터임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentListTotCnt(GamMarineCenterRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentMngtDao.selectMarineCenterRentListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentMngtVO selectMarineCenterRentSum(GamMarineCenterRentMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentMngtVO) selectByPk("gamMarineCenterRentMngtDao.selectMarineCenterRentSum_S", searchVO);
	}
    
	/**
	 * 마린센터임대 최초 신청을 등록한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentFirst(GamMarineCenterRentMngtVO vo){
		insert("gamMarineCenterRentMngtDao.insertMarineCenterRentFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamMarineCenterRentVO
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public GamMarineCenterRentMngtVO selectMarineCenterRentMaxNo(GamMarineCenterRentMngtVO vo) {
        return (GamMarineCenterRentMngtVO)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentMngtDao.selectMarineCenterRentMaxNo_S", vo);
    }
    
    /**
	 * 마린센터임대 연장 신청을 등록한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentRenew(GamMarineCenterRentMngtVO vo){
		insert("gamMarineCenterRentMngtDao.insertMarineCenterRentRenew_S", vo);
	}
	
	/**
	 * 마린센터임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectMarineCenterRentMaxMngCnt(GamMarineCenterRentMngtVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentMngtDao.selectMarineCenterRentMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 마린센터임대 연장 신청시 마린센터임대 상세를 복사하여 등록한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentDetailRenew(GamMarineCenterRentDetailVO vo){
		insert("gamMarineCenterRentMngtDao.insertMarineCenterRentDetailRenew_S", vo);
	}
	
	/**
	 * 마린센터임대 정보를 수정한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void updateMarineCenterRent(GamMarineCenterRentMngtVO vo){
		update("gamMarineCenterRentMngtDao.updateMarineCenterRent_S", vo);
	}
	
	/**
	 * 마린센터임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentDetailList(GamMarineCenterRentMngtVO vo) throws Exception {
        return list("gamMarineCenterRentMngtDao.selectMarineCenterRentDetailList_D", vo);
    }

    /**
	 * 마린센터임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentDetailListTotCnt(GamMarineCenterRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentMngtDao.selectMarineCenterRentDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentLevReqestCnt(GamMarineCenterRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentMngtDao.selectMarineCenterRentLevReqestCnt_S", vo);
    }
    
    /**
	 * 마린센터임대 사진정보를 삭제한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRentPhoto(GamMarineCenterRentMngtVO vo){
		delete("gamMarineCenterRentMngtDao.deleteMarineCenterRentPhoto_S", vo);
	}
    
	/**
	 * 마린센터임대 정보를 삭제한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRent(GamMarineCenterRentMngtVO vo){
		delete("gamMarineCenterRentMngtDao.deleteMarineCenterRent_S", vo);
	}
    
	/**
	 * 마린센터임대 상세정보를 삭제한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRentDetail(GamMarineCenterRentMngtVO vo){
		delete("gamMarineCenterRentMngtDao.deleteMarineCenterRentDetail_S", vo);
	}
	
	/**
	 * 마린센터임대 상세를 등록한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentDetail(GamMarineCenterRentDetailVO vo){
		insert("gamMarineCenterRentMngtDao.insertMarineCenterRentDetail_S", vo);
	}
	
	/**
	 * 마린센터임대 상세를 수정한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentDetail(GamMarineCenterRentDetailVO vo){
		update("gamMarineCenterRentMngtDao.updateMarineCenterRentDetail_S", vo);
	}
	
	/**
	 * 마린센터임대 상세를 삭제한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRentDetail2(GamMarineCenterRentDetailVO vo){
		delete("gamMarineCenterRentMngtDao.deleteMarineCenterRentDetail2_S", vo);
	}

	/**
	 * 승낙할 마린센터임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대정보
	 * @exception Exception
	 */
	public GamMarineCenterRentMngtVO selectMarineCenterRentPrmisnInfo(GamMarineCenterRentMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentMngtVO) selectByPk("gamMarineCenterRentMngtDao.selectMarineCenterRentPrmisnInfo_S", searchVO);
	}
	
	/**
	 * 마린센터임대 허가여부를 수정한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentPrmisn(GamMarineCenterRentLevReqestVO vo){
		update("gamMarineCenterRentMngtDao.updateMarineCenterRentPrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamMarineCenterRentLevReqestVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentLevReqest(GamMarineCenterRentLevReqestVO vo){
		insert("gamMarineCenterRentMngtDao.insertMarineCenterRentLevReqest_S", vo);
	}
	
	/**
	 * 마린센터임대 허가여부를 취소한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentPrmisnCancel(GamMarineCenterRentLevReqestVO vo){
		update("gamMarineCenterRentMngtDao.updateMarineCenterRentPrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamMarineCenterRentLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentMngtDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 마린센터임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대상세 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentDetailInfo(GamMarineCenterRentMngtVO vo) throws Exception {
        return list("gamMarineCenterRentMngtDao.selectMarineCenterRentDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamMarineCenterRentMngtDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentFileList(GamMarineCenterRentMngtVO searchVO) throws Exception {
        return list("gamMarineCenterRentMngtDao.selectMarineCenterRentFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentFileListTotCnt(GamMarineCenterRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentMngtDao.selectMarineCenterRentFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentFile(GamMarineCenterRentMngtVO vo){
		insert("gamMarineCenterRentMngtDao.insertMarineCenterRentFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentFile(GamMarineCenterRentMngtVO vo){
		insert("gamMarineCenterRentMngtDao.updateMarineCenterRentFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRentPhotoSingle(GamMarineCenterRentMngtVO vo){
		delete("gamMarineCenterRentMngtDao.deleteMarineCenterRentPhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentMngtVO selectMarineCenterRentMaxKey(GamMarineCenterRentMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentMngtVO) selectByPk("gamMarineCenterRentMngtDao.selectMarineCenterRentMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentComment(GamMarineCenterRentMngtVO vo){
		update("gamMarineCenterRentMngtDao.updateMarineCenterRentComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentMngtVO selectMarineCenterRentRenewInfo(GamMarineCenterRentMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentMngtVO) selectByPk("gamMarineCenterRentMngtDao.selectMarineCenterRentRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentRenewInfo(GamMarineCenterRentMngtVO vo){
		update("gamMarineCenterRentMngtDao.updateMarineCenterRentRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentMngtVO selectMarineCenterRentCurrRenewInfo(GamMarineCenterRentMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentMngtVO) selectByPk("gamMarineCenterRentMngtDao.selectMarineCenterRentCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentMngtVO selectMarineCenterRentDetailQuaycd(GamMarineCenterRentMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentMngtVO) selectByPk("gamMarineCenterRentMngtDao.selectMarineCenterRentDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentQuaycd(GamMarineCenterRentMngtVO vo){
		update("gamMarineCenterRentMngtDao.updateMarineCenterRentQuaycd_S", vo);
	}
	
	 /**
	 * 코픽스 이자율 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectCofixInfo() throws Exception {
        return list("gamMarineCenterRentMngtDao.selectCofixInfo_S", null);
    }
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentMngtVO selectMarineCenterRentBeforeQuarterInfo(GamMarineCenterRentMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentMngtVO) selectByPk("gamMarineCenterRentMngtDao.selectMarineCenterRentBeforeQuarterInfo_S", searchVO);
	}
	
	/**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentMngtVO selectMarineCenterRentCofixInfo(GamMarineCenterRentMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentMngtVO) selectByPk("gamMarineCenterRentMngtDao.selectMarineCenterRentCofixInfo_S", searchVO);
	}
}
