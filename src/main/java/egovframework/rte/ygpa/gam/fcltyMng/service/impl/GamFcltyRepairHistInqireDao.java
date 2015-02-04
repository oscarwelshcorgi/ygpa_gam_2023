/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairHistInqireVO;

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

@Repository("gamFcltyRepairHistInqireDao")
public class GamFcltyRepairHistInqireDao extends YGPAAbstractDAO {
	
	
	/**
	 * 하자보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFcltyRepairHistInqireList(GamFcltyRepairHistInqireVO vo) throws Exception {
		return list("gamFcltyRepairHistInqireDao.selectFcltyRepairHistInqireList_D", vo);
	}
	
	
	/**
	 * 하자보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public GamFcltyRepairHistInqireVO selectFcltyRepairHistInqireListTotCnt(GamFcltyRepairHistInqireVO vo) throws Exception {
		return (GamFcltyRepairHistInqireVO) selectByPk("gamFcltyRepairHistInqireDao.selectFcltyRepairHistInqireListTotCnt_S", vo);
	}
	
	
	
	/**
	 * 하자보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	public EgovMap selectFcltyRepairHistInqireDetail(GamFcltyRepairHistInqireVO vo) throws Exception{
		return (EgovMap) selectByPk("gamFcltyRepairHistInqireDao.selectFcltyRepairHistInqireDetail_S", vo);
	}
	
	

}
