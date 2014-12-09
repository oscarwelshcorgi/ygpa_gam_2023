/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintHistInqireVO;

/**
 * 
 * @author HNJ
 * @since 2014. 12. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 9.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamFcltyMaintHistInqireDao")
public class GamFcltyMaintHistInqireDao extends YGPAAbstractDAO {
	
	
	/**
	 * 유지보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List selectFcltyMaintHistInqireList(GamFcltyMaintHistInqireVO vo) throws Exception {
		return list("gamFcltyMaintHistInqireDao.selectFcltyMaintHistInqireList_D", vo);
	}
	
	
	/**
	 * 유지보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyMaintHistInqireListTotCnt(GamFcltyMaintHistInqireVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyMaintHistInqireDao.selectFcltyMaintHistInqireListTotCnt_S", vo);
	}
	

}
