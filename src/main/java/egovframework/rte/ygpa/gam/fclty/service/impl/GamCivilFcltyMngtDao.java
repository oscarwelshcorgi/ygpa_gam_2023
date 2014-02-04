/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.fclty.service.GamCivilFcltyManageVO;

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

//YGPAAbstractDAO
//EgovComAbstractDAO
//EgovAbstractDAO
@Repository("gamCivilFcltyMngtDao")
public class GamCivilFcltyMngtDao extends YGPAAbstractDAO{

	/**
	 * 시설관리 저장
	 * @param vo GamCivilFcltyManageVO 
	 * @exception Exception
	 */
	public void insertCivilFcltyManage(GamCivilFcltyManageVO vo){

		insert("gamCivilFcltyMngtDao.insertCivilFcltyManage", vo);
	}
	
	
	/**
	 * 시설관리 목록 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComDefaultVO> selectCivilFcltyMngtList(ComDefaultVO vo) throws Exception{
		return list("gamCivilFcltyMngtDao.selectCivilFcltyMngtList", vo);
	}
	
	
	/**
	 * 시설관리 총 갯수를 조회한다.
	 * @param ComDefaultVO vo
	 * @return int
	 * @exception Exception
	 */
    public int selectCivilFcltyMngtListTotCnt(ComDefaultVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCivilFcltyMngtDao.selectCivilFcltyMngtListTotCnt", vo);
    }
    
    
    /**
     * 시설관리 상세화면
     * @param vo
     * @return GamCivilFcltyManageVO
     */
    public GamCivilFcltyManageVO civilFcltyMngSelectView(GamCivilFcltyManageVO vo){
        return (GamCivilFcltyManageVO) selectByPk("gamCivilFcltyMngtDao.civilFcltyMngSelectView", vo);
    }
    
    
    /**
     * 시설관리 수정화면
     * @param vo
     */
    public void updateCivilFclty(GamCivilFcltyManageVO vo){
        update("gamCivilFcltyMngtDao.updateCivilFclty",vo);
    }

    
    /**
     * 시설관리 삭제화면
     * @param vo
     */
    public void deleteCivilFclty(GamCivilFcltyManageVO vo){
    	delete("gamCivilFcltyMngtDao.deleteCivilFclty", vo);
    }
}