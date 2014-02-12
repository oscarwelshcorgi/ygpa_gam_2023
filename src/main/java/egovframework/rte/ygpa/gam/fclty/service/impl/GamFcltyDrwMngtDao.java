/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwDtaFVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwInfoFVO;

/**
 * 
 * @author kok
 * @since 2014. 2. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 2. 3.		kok		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamFcltyDrwMngtDao")
public class GamFcltyDrwMngtDao extends YGPAAbstractDAO{

	/**
	 * 도면 정보 목록
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<GamFcltyDrwInfoFVO> selectFcltyDrwMngtInfoList(GamFcltyDrwInfoFVO vo) throws Exception{
		return list("gamFcltyDrwMngtDao.selectFcltyMngtInfoList", vo);
	}
	
	/**
	 * 도면 정보 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyDrwMngtInfoListTotCnt(GamFcltyDrwInfoFVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyDrwMngtDao.selectFcltyDrwMngtInfoListTotCnt", vo);
	}
	
	/**
	 * 도면 자료 목록
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<GamFcltyDrwDtaFVO> selectFcltyDrwMngtList(GamFcltyDrwDtaFVO vo) throws Exception{
		return list("gamFcltyDrwMngtDao.selectFcltyDrwMngtList", vo);
	}
	
	/**
	 * 도면 자료 총 수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
    public int selectFcltyDrwMngtListTotCnt(GamFcltyDrwDtaFVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyDrwMngtDao.selectFcltyDrwMngtListTotCnt", vo);
    }
    
	/**
	 * 도면 정보 저장
	 * @param vo
	 */
	public void insertFcltyDrwInfoListMng(GamFcltyDrwInfoFVO vo){
		insert("gamFcltyDrwMngtDao.insertFcltyDrwInfoListMng", vo);
	}

	/**
	 * 도면 정보 수정
	 * @param vo
	 */
	public void updateFcltyDrwInfoListMng(GamFcltyDrwInfoFVO vo){
		insert("gamFcltyDrwMngtDao.updateFcltyDrwInfoListMng", vo);
	}
	
	/**
	 * 도면 자료 저장
	 * @param vo
	 */
	public void insertFcltyDrwListMng(GamFcltyDrwDtaFVO vo){
		insert("gamFcltyDrwMngtDao.insertFcltyDrwListMng", vo);
	}
    
    /**
     * 도면 정보 시퀀스
     * @return String
     * @throws Exception
     */
    public String insertFcltyInfoGetSeq() throws Exception {
    	return (String)getSqlMapClientTemplate().queryForObject("gamFcltyDrwMngtDao.insertFcltyInfoGetSeq");
    }
    
    /**
     * 도면 정보 상세화면
     * @param vo
     * @return
     */
    public GamFcltyDrwInfoFVO fcltyDrwInfoListMngSelectView(GamFcltyDrwInfoFVO vo){
    	return (GamFcltyDrwInfoFVO) selectByPk("gamFcltyDrwMngtDao.fcltyDrwInfoListMngSelectView", vo);
    }
    
    /**
     * 도면 자료 상세화면
     * @param vo
     * @return
     */
    public GamFcltyDrwDtaFVO fcltyDrwListMngSelectView(GamFcltyDrwDtaFVO vo){
        return (GamFcltyDrwDtaFVO) selectByPk("gamFcltyDrwMngtDao.fcltyDrwListMngSelectView", vo);
    }
    
    /**
     * 도면 자료 수정
     * @param vo
     */
    public void updateFcltyDrwListMng(GamFcltyDrwDtaFVO vo){
        update("gamFcltyDrwMngtDao.updateFcltyDrwListMng",vo);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    
    /**
     * 시설관리 삭제화면
     * @param vo
     */
    public void deleteFcltyDrwListMng(GamFcltyDrwDtaFVO vo){
    	delete("gamFcltyDrwMngtDao.deleteFcltyDrwListMng", vo);
    }
}