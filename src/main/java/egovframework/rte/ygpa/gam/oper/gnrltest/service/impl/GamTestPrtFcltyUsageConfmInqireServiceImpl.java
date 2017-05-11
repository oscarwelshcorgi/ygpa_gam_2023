/**
 *
 */
package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUsageConfmInqireService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUsageConfmInqireVO;

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

@Service("gamTestPrtFcltyUsageConfmInqireService")
public class GamTestPrtFcltyUsageConfmInqireServiceImpl extends AbstractServiceImpl implements GamTestPrtFcltyUsageConfmInqireService {

	@Resource(name="gamTestPrtFcltyUsageConfmInqireDao")
    private GamTestPrtFcltyUsageConfmInqireDao gamTestPrtFcltyUsageConfmInqireDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyUsageConfmInqireService#selectPrtFcltyUsageConfmInqireList(egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyUsageConfmInqireVO)
	 */
	@Override
	public List selectPrtFcltyUsageConfmInqireList(
			GamTestPrtFcltyUsageConfmInqireVO searchVO) throws Exception {
		return gamTestPrtFcltyUsageConfmInqireDao.selectPrtFcltyUsageConfmInqireList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyUsageConfmInqireService#selectPrtFcltyUsageConfmInqireListTotCnt(egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyUsageConfmInqireVO)
	 */
	@Override
	public int selectPrtFcltyUsageConfmInqireListTotCnt(
			GamTestPrtFcltyUsageConfmInqireVO searchVO) {
		return gamTestPrtFcltyUsageConfmInqireDao.selectPrtFcltyUsageConfmInqireListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyUsageConfmInqireService#selectPrtFcltyUsageConfmInqireSum(egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyUsageConfmInqireVO)
	 */
	@Override
	public EgovMap selectPrtFcltyUsageConfmInqireSum(
			GamTestPrtFcltyUsageConfmInqireVO searchVO) {
		return gamTestPrtFcltyUsageConfmInqireDao.selectPrtFcltyUsageConfmInqireSum(searchVO);

	}


}
