/**
 * 
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.code.service.GamOlnlpFVO;
import egovframework.rte.ygpa.gam.code.service.GisAssetsCodeVO;
/**
 * 
 * @author kok
 * @since 2014. 3. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 7.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamOlnlpMngtDao")
public class GamOlnlpMngtDao extends YGPAAbstractDAO{

	/**
	 * 공시지가 등록현황 목록 조회
	 * @param vo GisAssetsCodeVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<GisAssetsCodeVO> selectOlnlpInsertList(GisAssetsCodeVO vo) throws Exception{
		return list("gamOlnlpMngtDao.selectOlnlpInsertList", vo);
	}
	
	
	/**
	 * 공시지가 등록현황 목록 총 수
	 * @param GisAssetsCodeVO vo
	 * @return int
	 * @exception Exception
	 */
	public int selectOlnlpInsertListTotCnt(GisAssetsCodeVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamOlnlpMngtDao.selectOlnlpInsertListTotCnt", vo);
	}
	
	
	/**
	 * 공시지가 관리 목록 조회
	 * @param vo GamOlnlpFVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<GamOlnlpFVO> selectOlnlpMngtList(GamOlnlpFVO vo) throws Exception{
		return list("gamOlnlpMngtDao.selectOlnlpMngtList", vo);
	}
	
	
	/**
	 * 공시지가 관리 목록 총 수
	 * @param GamOlnlpFVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectOlnlpMngtListTotCnt(GamOlnlpFVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamOlnlpMngtDao.selectOlnlpMngtListTotCnt", vo);
    }
    
    
    /**
     * 공시지가 관리 등록화면
     * @param vo
     */
    public void insertOlnlpMngt(GamOlnlpFVO vo){
    	insert("gamOlnlpMngtDao.insertOlnlpMngt",vo);
    }

    
    /**
     * 공시지가 관리 수정화면
     * @param vo
     */
    public void updateOlnlpMngt(GamOlnlpFVO vo){
        update("gamOlnlpMngtDao.updateOlnlpMngt",vo);
    }

    
    /**
     * 공시지가 관리 삭제
     * @param vo
     */
    public void deleteOlnlpMngt(GamOlnlpFVO vo){
    	delete("gamOlnlpMngtDao.deleteOlnlpMngt", vo);
    }
}