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

}