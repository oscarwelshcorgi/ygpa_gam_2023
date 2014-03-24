package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentSttusInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentSttusInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentSttusInqireVO;

/**
 * @Class Name : GamMarineCenterRentSttusInqireDao.java
 * @Description : 마린센터임대현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamMarineCenterRentSttusInqireDao")
public class GamMarineCenterRentSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 마린센터임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentSttusInqireList(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
        return list("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireList_D", searchVO);
    }

    /**
	 * 마린센터임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentSttusInqireListTotCnt(GamMarineCenterRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireSum(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
		return (GamMarineCenterRentSttusInqireVO) selectByPk("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireSum_S", searchVO);
	}
    
	/**
	 * 마린센터임대 최초 신청을 등록한다.
	 * @param vo GamMarineCenterRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentSttusInqireFirst(GamMarineCenterRentSttusInqireVO vo){
		insert("gamMarineCenterRentSttusInqireDao.insertMarineCenterRentSttusInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamMarineCenterRentSttusInqireVO
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireMaxNo(GamMarineCenterRentSttusInqireVO vo) {
        return (GamMarineCenterRentSttusInqireVO)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireMaxNo_S", vo);
    }
    
    /**
	 * 마린센터임대 연장 신청을 등록한다.
	 * @param vo GamMarineCenterRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentSttusInqireRenew(GamMarineCenterRentSttusInqireVO vo){
		insert("gamMarineCenterRentSttusInqireDao.insertMarineCenterRentSttusInqireRenew_S", vo);
	}
	
	/**
	 * 마린센터임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectMarineCenterRentSttusInqireMaxMngCnt(GamMarineCenterRentSttusInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 마린센터임대 연장 신청시 마린센터임대 상세를 복사하여 등록한다.
	 * @param vo GamMarineCenterRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentSttusInqireDetailRenew(GamMarineCenterRentSttusInqireDetailVO vo){
		insert("gamMarineCenterRentSttusInqireDao.insertMarineCenterRentSttusInqireDetailRenew_S", vo);
	}
	
	/**
	 * 마린센터임대 정보를 수정한다.
	 * @param vo GamMarineCenterRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentSttusInqire(GamMarineCenterRentSttusInqireVO vo){
		update("gamMarineCenterRentSttusInqireDao.updateMarineCenterRentSttusInqire_S", vo);
	}
	
	/**
	 * 마린센터임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentSttusInqireDetailList(GamMarineCenterRentSttusInqireVO vo) throws Exception {
        return list("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireDetailList_D", vo);
    }

    /**
	 * 마린센터임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentSttusInqireDetailListTotCnt(GamMarineCenterRentSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentSttusInqireLevReqestCnt(GamMarineCenterRentSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 마린센터임대 사진정보를 삭제한다.
	 * @param vo GamMarineCenterRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRentSttusInqirePhoto(GamMarineCenterRentSttusInqireVO vo){
		delete("gamMarineCenterRentSttusInqireDao.deleteMarineCenterRentSttusInqirePhoto_S", vo);
	}
    
	/**
	 * 마린센터임대 정보를 삭제한다.
	 * @param vo GamMarineCenterRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRentSttusInqire(GamMarineCenterRentSttusInqireVO vo){
		delete("gamMarineCenterRentSttusInqireDao.deleteMarineCenterRentSttusInqire_S", vo);
	}
    
	/**
	 * 마린센터임대 상세정보를 삭제한다.
	 * @param vo GamMarineCenterRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRentSttusInqireDetail(GamMarineCenterRentSttusInqireVO vo){
		delete("gamMarineCenterRentSttusInqireDao.deleteMarineCenterRentSttusInqireDetail_S", vo);
	}
	
	/**
	 * 마린센터임대 상세를 등록한다.
	 * @param vo GamMarineCenterRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentSttusInqireDetail(GamMarineCenterRentSttusInqireDetailVO vo){
		insert("gamMarineCenterRentSttusInqireDao.insertMarineCenterRentSttusInqireDetail_S", vo);
	}
	
	/**
	 * 마린센터임대 상세를 수정한다.
	 * @param vo GamMarineCenterRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentSttusInqireDetail(GamMarineCenterRentSttusInqireDetailVO vo){
		update("gamMarineCenterRentSttusInqireDao.updateMarineCenterRentSttusInqireDetail_S", vo);
	}
	
	/**
	 * 마린센터임대 상세를 삭제한다.
	 * @param vo GamMarineCenterRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRentSttusInqireDetail2(GamMarineCenterRentSttusInqireDetailVO vo){
		delete("gamMarineCenterRentSttusInqireDao.deleteMarineCenterRentSttusInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 마린센터임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대정보
	 * @exception Exception
	 */
	public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqirePrmisnInfo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
		return (GamMarineCenterRentSttusInqireVO) selectByPk("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 마린센터임대 허가여부를 수정한다.
	 * @param vo GamMarineCenterRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentSttusInqirePrmisn(GamMarineCenterRentSttusInqireLevReqestVO vo){
		update("gamMarineCenterRentSttusInqireDao.updateMarineCenterRentSttusInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamMarineCenterRentSttusInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentSttusInqireLevReqest(GamMarineCenterRentSttusInqireLevReqestVO vo){
		insert("gamMarineCenterRentSttusInqireDao.insertMarineCenterRentSttusInqireLevReqest_S", vo);
	}
	
	/**
	 * 마린센터임대 허가여부를 취소한다.
	 * @param vo GamMarineCenterRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentSttusInqirePrmisnCancel(GamMarineCenterRentSttusInqireLevReqestVO vo){
		update("gamMarineCenterRentSttusInqireDao.updateMarineCenterRentSttusInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamMarineCenterRentSttusInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentSttusInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 마린센터임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대상세 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentSttusInqireDetailInfo(GamMarineCenterRentSttusInqireVO vo) throws Exception {
        return list("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamMarineCenterRentSttusInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentSttusInqireFileList(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
        return list("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentSttusInqireFileListTotCnt(GamMarineCenterRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamMarineCenterRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentSttusInqireFile(GamMarineCenterRentSttusInqireVO vo){
		insert("gamMarineCenterRentSttusInqireDao.insertMarineCenterRentSttusInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamMarineCenterRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentSttusInqireFile(GamMarineCenterRentSttusInqireVO vo){
		insert("gamMarineCenterRentSttusInqireDao.updateMarineCenterRentSttusInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamMarineCenterRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRentSttusInqirePhotoSingle(GamMarineCenterRentSttusInqireVO vo){
		delete("gamMarineCenterRentSttusInqireDao.deleteMarineCenterRentSttusInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireMaxKey(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
		return (GamMarineCenterRentSttusInqireVO) selectByPk("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamMarineCenterRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentSttusInqireComment(GamMarineCenterRentSttusInqireVO vo){
		update("gamMarineCenterRentSttusInqireDao.updateMarineCenterRentSttusInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireRenewInfo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
		return (GamMarineCenterRentSttusInqireVO) selectByPk("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamMarineCenterRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentSttusInqireRenewInfo(GamMarineCenterRentSttusInqireVO vo){
		update("gamMarineCenterRentSttusInqireDao.updateMarineCenterRentSttusInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireCurrRenewInfo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
		return (GamMarineCenterRentSttusInqireVO) selectByPk("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireDetailQuaycd(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
		return (GamMarineCenterRentSttusInqireVO) selectByPk("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamMarineCenterRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentSttusInqireQuaycd(GamMarineCenterRentSttusInqireVO vo){
		update("gamMarineCenterRentSttusInqireDao.updateMarineCenterRentSttusInqireQuaycd_S", vo);
	}
	
	 /**
	 * 코픽스 이자율 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectCofixInfo() throws Exception {
        return list("gamMarineCenterRentSttusInqireDao.selectCofixInfo_S", null);
    }
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireBeforeQuarterInfo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
		return (GamMarineCenterRentSttusInqireVO) selectByPk("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireBeforeQuarterInfo_S", searchVO);
	}
	
	/**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentSttusInqireVO selectMarineCenterRentSttusInqireCofixInfo(GamMarineCenterRentSttusInqireVO searchVO) throws Exception {
		return (GamMarineCenterRentSttusInqireVO) selectByPk("gamMarineCenterRentSttusInqireDao.selectMarineCenterRentSttusInqireCofixInfo_S", searchVO);
	}
}
