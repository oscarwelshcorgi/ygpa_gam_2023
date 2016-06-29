/**
 *
 */
package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUsageConfmInqireService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUsageConfmInqireVO;

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

@Service("gamPrtFcltyUsageConfmInqireService")
public class GamPrtFcltyUsageConfmInqireServiceImpl extends AbstractServiceImpl implements GamPrtFcltyUsageConfmInqireService {

	@Resource(name="gamPrtFcltyUsageConfmInqireDao")
    private GamPrtFcltyUsageConfmInqireDao gamPrtFcltyUsageConfmInqireDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUsageConfmInqireService#selectPrtFcltyUsageConfmInqireList(egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUsageConfmInqireVO)
	 */
	@Override
	public List selectPrtFcltyUsageConfmInqireList(
			GamPrtFcltyUsageConfmInqireVO searchVO) throws Exception {
		return gamPrtFcltyUsageConfmInqireDao.selectPrtFcltyUsageConfmInqireList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUsageConfmInqireService#selectPrtFcltyUsageConfmInqireListTotCnt(egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUsageConfmInqireVO)
	 */
	@Override
	public int selectPrtFcltyUsageConfmInqireListTotCnt(
			GamPrtFcltyUsageConfmInqireVO searchVO) {
		return gamPrtFcltyUsageConfmInqireDao.selectPrtFcltyUsageConfmInqireListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUsageConfmInqireService#selectPrtFcltyUsageConfmInqireSum(egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUsageConfmInqireVO)
	 */
	@Override
	public EgovMap selectPrtFcltyUsageConfmInqireSum(
			GamPrtFcltyUsageConfmInqireVO searchVO) {
		return gamPrtFcltyUsageConfmInqireDao.selectPrtFcltyUsageConfmInqireSum(searchVO);

	}


}
