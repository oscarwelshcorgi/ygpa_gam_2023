/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageHistInqireVO;

/**
 * 
 * @author LFIT
 * @since 2014. 12. 11.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 11.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamFcltyUsageHistInqireDao")
public class GamFcltyUsageHistInqireDao extends YGPAAbstractDAO{

	/**
	 * @param searchVO
	 * @return
	 */
	public List<ComDefaultVO> selectFcltyUsageHistInqireList(GamFcltyUsageHistInqireVO searchVO) {
		return list("gamFcltyUsageHistInqireDao.selectFcltyUsageHistInqireList", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public GamFcltyUsageHistInqireVO selectFcltyUsageHistInqireListTotCnt(GamFcltyUsageHistInqireVO searchVO) {
		return (GamFcltyUsageHistInqireVO)selectByPk("gamFcltyUsageHistInqireDao.selectFcltyUsageHistInqireListTotCnt", searchVO);
	}

	
}
