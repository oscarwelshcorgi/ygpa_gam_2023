/**
 *
 */
package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUsageConfmInqireVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseExprInqireVO;

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

@Repository("gamPrtFcltyUsageConfmInqireDao")
public class GamPrtFcltyUsageConfmInqireDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectPrtFcltyUsageConfmInqireList(
			GamPrtFcltyUsageConfmInqireVO searchVO) {
        return list("gamPrtFcltyUsageConfmInqireDao.selectPrtFcltyUsageConfmInqireList_D", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public int selectPrtFcltyUsageConfmInqireListTotCnt(
			GamPrtFcltyUsageConfmInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyUsageConfmInqireDao.selectPrtFcltyUsageConfmInqireListTotCnt_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public EgovMap selectPrtFcltyUsageConfmInqireSum(
			GamPrtFcltyUsageConfmInqireVO searchVO) {
		return (EgovMap) selectByPk("gamPrtFcltyUsageConfmInqireDao.selectPrtFcltyUsageConfmInqireSum_S", searchVO);

	}

}
