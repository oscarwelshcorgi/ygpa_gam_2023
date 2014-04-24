package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamRecvTpRecvStsInqireVO;

/**
 * @Class Name : GamRecvTpRecvStsInqireDao.java
 * @Description : 수입종류별수입현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamRecvTpRecvStsInqireDao")
public class GamRecvTpRecvStsInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 수입종류별수입현황조회 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectRecvTpRecvStsInqireList(GamRecvTpRecvStsInqireVO searchVO) throws Exception {
        return list("gamRecvTpRecvStsInqireDao.selectRecvTpRecvStsInqireList_D", searchVO);
    }

    /**
	 * 수입종류별수입현황조회 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록 총 갯수
	 * @exception
	 */
    public int selectRecvTpRecvStsInqireListTotCnt(GamRecvTpRecvStsInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamRecvTpRecvStsInqireDao.selectRecvTpRecvStsInqireListTotCnt_S", searchVO);
    }

    /**
	 * 수입종류별수입현황조회 자료수, 총금액, 총할인금액
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 통계할 정보가 담긴 VO
	 * @exception
	 */
	public GamRecvTpRecvStsInqireVO selectCmpyRecvStsInqireSum(GamRecvTpRecvStsInqireVO searchVO) throws Exception {
		return (GamRecvTpRecvStsInqireVO) selectByPk("gamRecvTpRecvStsInqireDao.selectRecvTpRecvStsInqireSum_S", searchVO);
	}
}
