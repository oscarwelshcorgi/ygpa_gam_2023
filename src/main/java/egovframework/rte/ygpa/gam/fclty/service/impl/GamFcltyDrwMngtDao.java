/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyDrwDtaFVO;

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
	 * 시설관리 저장
	 * @param vo GamFcltyDrwDtaFVO 
	 * @exception Exception
	 */
	public void insertFcltyDrwListMng(GamFcltyDrwDtaFVO vo){

		insert("gamFcltyDrwMngtDao.insertFcltyDrwListMng", vo);
	}
	
	
	/**
	 * 시설관리 목록 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDefaultVO> selectFcltyDrwMngtList(ComDefaultVO vo) throws Exception{
		return list("gamFcltyDrwMngtDao.selectFcltyMngtList", vo);
	}
	
	
	/**
	 * 시설관리 총 갯수를 조회한다.
	 * @param ComDefaultVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectFcltyDrwMngtListTotCnt(ComDefaultVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyDrwMngtDao.selectFcltyDrwMngtListTotCnt", vo);
    }
    
    
    /**
     * 시설관리 시퀀스
     * @return int
     * @exception Exception
     */
    public String insertFcltyGetSeq() throws Exception {
    	return (String)getSqlMapClientTemplate().queryForObject("gamFcltyDrwMngtDao.insertFcltyGetSeq");
    }
    
    
    /**
     * 시설관리 상세화면
     * @param vo
     * @return GamFcltyDrwDtaFVO
     */
    public GamFcltyDrwDtaFVO fcltyDrwListMngSelectView(GamFcltyDrwDtaFVO vo){
        return (GamFcltyDrwDtaFVO) selectByPk("gamFcltyDrwMngtDao.fcltyDrwListMngSelectView", vo);
    }
    
    
    /**
     * 시설관리 수정화면
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