/**
 *
 */
package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUsageConfmInqireVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUseExprInqireVO;

/**
 *
 * @author Administrator
 * @since 2016. 6. 28.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 28.		Administrator		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamTestPrtFcltyUsageConfmInqireDao")
public class GamTestPrtFcltyUsageConfmInqireDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectPrtFcltyUsageConfmInqireList(
			GamTestPrtFcltyUsageConfmInqireVO searchVO) {
        return list("gamTestPrtFcltyUsageConfmInqireDao.selectPrtFcltyUsageConfmInqireList_D", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public int selectPrtFcltyUsageConfmInqireListTotCnt(
			GamTestPrtFcltyUsageConfmInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyUsageConfmInqireDao.selectPrtFcltyUsageConfmInqireListTotCnt_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public EgovMap selectPrtFcltyUsageConfmInqireSum(
			GamTestPrtFcltyUsageConfmInqireVO searchVO) {
		return (EgovMap) selectByPk("gamTestPrtFcltyUsageConfmInqireDao.selectPrtFcltyUsageConfmInqireSum_S", searchVO);

	}

}
