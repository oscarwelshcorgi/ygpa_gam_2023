package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentSttusInqireVO;


/**
 * @Class Name : GamCntnrQuayRentSttusInqireDao.java
 * @Description : 컨테이너부두임대현황조회 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCntnrQuayRentSttusInqireDao")
public class GamCntnrQuayRentSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 컨테이너부두임대현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대현황 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayRentSttusInqireList(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception {
        return list("gamCntnrQuayRentSttusInqireDao.selectCntnrQuayRentSttusInqireList_D", searchVO);
    }

    /**
	 * 컨테이너부두임대현황 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대현황 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayRentSttusInqireListTotCnt(GamCntnrQuayRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentSttusInqireDao.selectCntnrQuayRentSttusInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대현황 목록
	 * @exception Exception
	 */
	public GamCntnrQuayRentSttusInqireVO selectCntnrQuayRentSttusInqireSum(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception {
		return (GamCntnrQuayRentSttusInqireVO) selectByPk("gamCntnrQuayRentSttusInqireDao.selectCntnrQuayRentSttusInqireSum_S", searchVO);
	}
	
	/**
	 * 컨테이너부두임대현황 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대현황 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayRentSttusInqireDetailList(GamCntnrQuayRentSttusInqireVO vo) throws Exception {
        return list("gamCntnrQuayRentSttusInqireDao.selectCntnrQuayRentSttusInqireDetailList_D", vo);
    }

    /**
	 * 컨테이너부두임대현황 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대현황 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayRentSttusInqireDetailListTotCnt(GamCntnrQuayRentSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentSttusInqireDao.selectCntnrQuayRentSttusInqireDetailListTotCnt_S", vo);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayRentSttusInqireFileList(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception {
        return list("gamCntnrQuayRentSttusInqireDao.selectCntnrQuayRentSttusInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayRentSttusInqireFileListTotCnt(GamCntnrQuayRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentSttusInqireDao.selectCntnrQuayRentSttusInqireFileListTotCnt_S", searchVO);
    }
    
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamCntnrQuayRentSttusInqireDao.selectOlnlpInfo_S", null);
    }
    
    
    /**
	 * 코픽스 이자율 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectCofixInfo() throws Exception {
        return list("gamCntnrQuayRentSttusInqireDao.selectCofixInfo_S", null);
    }
    
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayRentSttusInqireVO selectCntnrQuayRentSttusInqireBeforeQuarterInfo(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception {
		return (GamCntnrQuayRentSttusInqireVO) selectByPk("gamCntnrQuayRentSttusInqireDao.selectCntnrQuayRentSttusInqireBeforeQuarterInfo_S", searchVO);
	}
	
	/**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayRentSttusInqireVO selectCntnrQuayRentSttusInqireCofixInfo(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception {
		return (GamCntnrQuayRentSttusInqireVO) selectByPk("gamCntnrQuayRentSttusInqireDao.selectCntnrQuayRentSttusInqireCofixInfo_S", searchVO);
	}
    
    

}