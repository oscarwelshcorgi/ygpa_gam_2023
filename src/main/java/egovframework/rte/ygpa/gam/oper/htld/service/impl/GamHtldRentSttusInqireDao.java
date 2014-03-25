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

}