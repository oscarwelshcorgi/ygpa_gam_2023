package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentSttusInqireVO;

/**
 * @Class Name : GamAssetRentSttusInqireDao.java
 * @Description : 자산임대현황조회 DAO Class
 * @Modification Information
 *
 * @author 정윤후
 * @since 2014-01-22
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetRentSttusInqireDao")
public class GamAssetRentSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 자산임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentList(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return list("gamAssetRentSttusInqireDao.selectAssetRentList_D", searchVO);
    }

    /**
	 * 자산임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentListTotCnt(GamAssetRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentSttusInqireDao.selectAssetRentListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetRentSttusInqireVO selectAssetRentSum(GamAssetRentSttusInqireVO searchVO) throws Exception {
		return (GamAssetRentSttusInqireVO) selectByPk("gamAssetRentSttusInqireDao.selectAssetRentSum_S", searchVO);
	}
    
	/**
	 * 자산임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentDetailList(GamAssetRentSttusInqireVO vo) throws Exception {
        return list("gamAssetRentSttusInqireDao.selectAssetRentDetailList_D", vo);
    }

    /**
	 * 자산임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentDetailListTotCnt(GamAssetRentSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentSttusInqireDao.selectAssetRentDetailListTotCnt_S", vo);
    }
	
}
