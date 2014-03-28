package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentSttusInqireVO;

/**
 * @Class Name : GamCmmnCntrRentSttusInqireDao.java
 * @Description : 공컨장치장임대현황조회 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCmmnCntrRentSttusInqireDao")
public class GamCmmnCntrRentSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 공컨장치장임대현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대현황 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrRentSttusInqireList(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
        return list("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireList_D", searchVO);
    }

    /**
	 * 공컨장치장임대현황 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대현황 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrRentSttusInqireListTotCnt(GamCmmnCntrRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대현황 목록
	 * @exception Exception
	 */
	public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireSum(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
		return (GamCmmnCntrRentSttusInqireVO) selectByPk("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireSum_S", searchVO);
	}
	
	/**
	 * 공컨장치장임대현황 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대현황 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrRentSttusInqireDetailList(GamCmmnCntrRentSttusInqireVO vo) throws Exception {
        return list("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireDetailList_D", vo);
    }

    /**
	 * 공컨장치장임대현황 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대현황 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrRentSttusInqireDetailListTotCnt(GamCmmnCntrRentSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireDetailListTotCnt_S", vo);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrRentSttusInqireFileList(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
        return list("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrRentSttusInqireFileListTotCnt(GamCmmnCntrRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireFileListTotCnt_S", searchVO);
    }

}