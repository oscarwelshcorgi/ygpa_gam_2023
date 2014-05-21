package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseSttusInqireVO;

/**
 * @Class Name : GamPrtFcltyUseSttusInqireDao.java
 * @Description : 항만시설사용현황조회 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPrtFcltyUseSttusInqireDao")
public class GamPrtFcltyUseSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 항만시설사용현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용현황 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyUseSttusInqireList(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
        return list("gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireList_D", searchVO);
    }

    /**
	 * 항만시설사용현황 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용현황 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyUseSttusInqireListTotCnt(GamPrtFcltyUseSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용현황 목록
	 * @exception Exception
	 */
	public GamPrtFcltyUseSttusInqireVO selectPrtFcltyUseSttusInqireSum(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
		return (GamPrtFcltyUseSttusInqireVO) selectByPk("gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireSum_S", searchVO);
	}
	
	/**
	 * 항만시설사용현황 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용현황 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyUseSttusInqireDetailList(GamPrtFcltyUseSttusInqireVO vo) throws Exception {
        return list("gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireDetailList_D", vo);
    }

    /**
	 * 항만시설사용현황 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용현황 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyUseSttusInqireDetailListTotCnt(GamPrtFcltyUseSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireDetailListTotCnt_S", vo);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyUseSttusInqireFileList(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
        return list("gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyUseSttusInqireFileListTotCnt(GamPrtFcltyUseSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireFileListTotCnt_S", searchVO);
    }
    
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamPrtFcltyUseSttusInqireDao.selectOlnlpInfo_S", null);
    }
    
    
    /**
	 * 코픽스 이자율 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectCofixInfo() throws Exception {
        return list("gamPrtFcltyUseSttusInqireDao.selectCofixInfo_S", null);
    }
    
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyUseSttusInqireVO selectPrtFcltyUseSttusInqireBeforeQuarterInfo(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
		return (GamPrtFcltyUseSttusInqireVO) selectByPk("gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireBeforeQuarterInfo_S", searchVO);
	}
	
	/**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyUseSttusInqireVO selectPrtFcltyUseSttusInqireCofixInfo(GamPrtFcltyUseSttusInqireVO searchVO) throws Exception {
		return (GamPrtFcltyUseSttusInqireVO) selectByPk("gamPrtFcltyUseSttusInqireDao.selectPrtFcltyUseSttusInqireCofixInfo_S", searchVO);
	}

}
