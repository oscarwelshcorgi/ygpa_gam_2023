package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentSttusInqireVO;

/**
 * @Class Name : GamHtldRentSttusInqireDao.java
 * @Description : 배후단지임대현황조회 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamHtldRentSttusInqireDao")
public class GamHtldRentSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 배후단지임대현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대현황 목록
	 * @exception Exception
	 */
    public List selectHtldRentSttusInqireList(GamHtldRentSttusInqireVO searchVO) throws Exception {
        return list("gamHtldRentSttusInqireDao.selectHtldRentSttusInqireList_D", searchVO);
    }

    /**
	 * 배후단지임대현황 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대현황 목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentSttusInqireListTotCnt(GamHtldRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentSttusInqireDao.selectHtldRentSttusInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대현황 목록
	 * @exception Exception
	 */
	public GamHtldRentSttusInqireVO selectHtldRentSttusInqireSum(GamHtldRentSttusInqireVO searchVO) throws Exception {
		return (GamHtldRentSttusInqireVO) selectByPk("gamHtldRentSttusInqireDao.selectHtldRentSttusInqireSum_S", searchVO);
	}
	
	/**
	 * 배후단지임대현황 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대현황 목록
	 * @exception Exception
	 */
    public List selectHtldRentSttusInqireDetailList(GamHtldRentSttusInqireVO vo) throws Exception {
        return list("gamHtldRentSttusInqireDao.selectHtldRentSttusInqireDetailList_D", vo);
    }

    /**
	 * 배후단지임대현황 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대현황 목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentSttusInqireDetailListTotCnt(GamHtldRentSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentSttusInqireDao.selectHtldRentSttusInqireDetailListTotCnt_S", vo);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldRentSttusInqireFileList(GamHtldRentSttusInqireVO searchVO) throws Exception {
        return list("gamHtldRentSttusInqireDao.selectHtldRentSttusInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentSttusInqireFileListTotCnt(GamHtldRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentSttusInqireDao.selectHtldRentSttusInqireFileListTotCnt_S", searchVO);
    }
    
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamHtldRentSttusInqireDao.selectOlnlpInfo_S", null);
    }
    
    
    /**
	 * 코픽스 이자율 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectCofixInfo() throws Exception {
        return list("gamHtldRentSttusInqireDao.selectCofixInfo_S", null);
    }
    
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamHtldRentSttusInqireVO selectHtldRentSttusInqireBeforeQuarterInfo(GamHtldRentSttusInqireVO searchVO) throws Exception {
		return (GamHtldRentSttusInqireVO) selectByPk("gamHtldRentSttusInqireDao.selectHtldRentSttusInqireBeforeQuarterInfo_S", searchVO);
	}
	
	/**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamHtldRentSttusInqireVO selectHtldRentSttusInqireCofixInfo(GamHtldRentSttusInqireVO searchVO) throws Exception {
		return (GamHtldRentSttusInqireVO) selectByPk("gamHtldRentSttusInqireDao.selectHtldRentSttusInqireCofixInfo_S", searchVO);
	}

}