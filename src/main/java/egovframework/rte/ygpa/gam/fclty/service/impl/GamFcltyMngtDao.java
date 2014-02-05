/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltyManageVO;

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
@Repository("gamFcltyMngtDao")
public class GamFcltyMngtDao extends YGPAAbstractDAO{

	/**
	 * 시설관리 저장
	 * @param vo GamFcltyManageVO 
	 * @exception Exception
	 */
	public void insertFcltyManage(GamFcltyManageVO vo){

		insert("gamFcltyMngtDao.insertFcltyManage", vo);
	}
	
	
	/**
	 * 시설관리 목록 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDefaultVO> selectFcltyMngtList(ComDefaultVO vo) throws Exception{
		return list("gamFcltyMngtDao.selectFcltyMngtList", vo);
	}
	
	
	/**
	 * 시설관리 총 갯수를 조회한다.
	 * @param ComDefaultVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectFcltyMngtListTotCnt(ComDefaultVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyMngtDao.selectFcltyMngtListTotCnt", vo);
    }
    
    
    /**
	 * 자산관리 목록 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDefaultVO> selectSearchGisCdPopupList(ComDefaultVO vo) throws Exception{
		return list("gamFcltyMngtDao.selectSearchGisCdPopupList", vo);
	}
	
	
	/**
	 * 자산관리 팝업목록 총 갯수를 조회한다.
	 * @param ComDefaultVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectSearchGisCdPopupListTotCnt(ComDefaultVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyMngtDao.selectSearchGisCdPopupListTotCnt", vo);
    }
    
    
    /**
     * 시설관리 상세화면
     * @param vo
     * @return GamFcltyManageVO
     */
    public GamFcltyManageVO fcltyMngSelectView(GamFcltyManageVO vo){
        return (GamFcltyManageVO) selectByPk("gamFcltyMngtDao.fcltyMngSelectView", vo);
    }
    
    
    /**
     * 시설관리 수정화면
     * @param vo
     */
    public void updateFclty(GamFcltyManageVO vo){
        update("gamFcltyMngtDao.updateFclty",vo);
    }

    
    /**
     * 시설관리 삭제화면
     * @param vo
     */
    public void deleteFclty(GamFcltyManageVO vo){
    	delete("gamFcltyMngtDao.deleteFclty", vo);
    }
}