/**
 * 
 */
package egovframework.rte.ygpa.gam.cmmn.itgrn.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.cmmn.itgrn.service.GamNticPayListVO;

/**
 * 
 * @author kok
 * @since 2014. 2. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 7.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamNticPayListDao")
public class GamNticPayListDao extends YGPAAbstractDAO{
	
	
	/**
	 * 납부현황목록조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	
	/**
	 * 세입목록조회
	 */
	public List selectNticPayList(GamNticPayListVO vo) throws Exception{
		return list("gamNticPayListDao.selectNticPayList", vo);
	}
	
	
	/**
	 * 세입목록조회 총 수
	 */
    public int selectNticPayListTotCnt(GamNticPayListVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamNticPayListDao.selectNticPayListTotCnt", vo);
    }
    
    
    /**
	 * 자료수와 고지금액합계,수납금액(수납구분이 2나 3)합계
	 */
    public GamNticPayListVO selectNticPayListSum(GamNticPayListVO vo) throws Exception {
    	return (GamNticPayListVO) selectByPk("gamNticPayListDao.selectNticPayListSum_S", vo);
    }
    
    
    /**
	 * 연체세입목록조회
	 */
	public List selectDelayNticPayList(GamNticPayListVO vo) throws Exception{
		return list("gamNticPayListDao.selectDelayNticPayList", vo);
	}
	
	
	/**
	 * 연체세입목록조회 총 수
	 */
    public int selectDelayNticPayListTotCnt(GamNticPayListVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamNticPayListDao.selectDelayNticPayListTotCnt", vo);
    }
    
    
    /**
	 * 자료수와 미납고지금액합계
	 */
    public GamNticPayListVO selectDelayNticPayListSum(GamNticPayListVO vo) throws Exception {
    	return (GamNticPayListVO) selectByPk("gamNticPayListDao.selectDelayNticPayListSum_S", vo);
    }

}