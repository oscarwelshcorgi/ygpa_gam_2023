/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.code.service.GamOlnlpFVO;
import egovframework.rte.ygpa.gam.code.service.GisAssetsCodeVO;
/**
 * 공시지가 조회 DAO
 * @author EUNSUNGJ
 * @since 2014. 5. 1.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 5. 1.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamOlnlpInqireDao")
public class GamOlnlpInqireDao extends YGPAAbstractDAO{

	/**
	 * 공시지가 등록현황 목록 조회
	 * @param vo GisAssetsCodeVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List selectOlnlpInsertList(GisAssetsCodeVO vo) throws Exception{
		return list("gamOlnlpInqireDao.selectOlnlpInsertList", vo);
	}

	/**
	 * 공시지가 등록현황 목록 총 수
	 * @param GisAssetsCodeVO vo
	 * @return int
	 * @exception Exception
	 */
	public int selectOlnlpInsertListTotCnt(GisAssetsCodeVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamOlnlpInqireDao.selectOlnlpInsertListTotCnt", vo);
	}

	/**
	 * 공시지가 관리 목록 조회
	 * @param vo GamOlnlpFVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List selectOlnlpInqireList(GamOlnlpFVO vo) throws Exception{
		return list("gamOlnlpInqireDao.selectOlnlpInqireList", vo);
	}

	/**
	 * 공시지가 관리 목록 총 수
	 * @param GamOlnlpFVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectOlnlpInqireListTotCnt(GamOlnlpFVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamOlnlpInqireDao.selectOlnlpInqireListTotCnt", vo);
    }

}