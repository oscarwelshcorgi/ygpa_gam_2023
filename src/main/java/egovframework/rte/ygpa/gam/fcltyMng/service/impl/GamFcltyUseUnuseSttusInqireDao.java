/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUseUnuseSttusInqireVO;

/**
 *
 * @author 정성현
 * @since 2014. 12. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------			-------- ---------------------------
 *  2014. 12. 12.	정성현		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamFcltyUseUnuseSttusInqireDao")
public class GamFcltyUseUnuseSttusInqireDao extends YGPAAbstractDAO {


	/**
	 * 사용/미사용시설 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyUseUnuseSttusInqireList(GamFcltyUseUnuseSttusInqireVO searchVO) throws Exception {
		return list("gamFcltyUseUnuseSttusInqireDao.selectFcltyUseUnuseSttusInqireList_D", searchVO);
	}


	/**
	 * 사용/미사용시설 목록 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyUseUnuseSttusInqireListTotCnt(GamFcltyUseUnuseSttusInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyUseUnuseSttusInqireDao.selectFcltyUseUnuseSttusInqireListTotCnt_S", searchVO);
	}


	/**
	 * @param searchVO
	 * @return
	 */
	public EgovMap selectFcltyUseUnuseSttusInqireDetail(GamFcltyUseUnuseSttusInqireVO searchVO) {
		// TODO Auto-generated method stub
		return null;
	}

}
	

	
