/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngInqireService;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngInqireVo;

/**
 *
 * @author Lee
 * @since 2014. 10. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 22.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamFcltsFeeMngInqireService")
public class GamFcltsFeeMngInqireServiceImpl extends AbstractServiceImpl implements GamFcltsFeeMngInqireService{

	@Resource(name="gamFcltsFeeMngInqireDao")
	private GamFcltsFeeMngInqireDao gamFcltsFeeMngInqireDao;

	@Override
	public GamFcltsFeeMngInqireVo selectFcltsFeeMngInqireListTotCnt(GamFcltsFeeMngInqireVo searchVO) throws Exception {
		return gamFcltsFeeMngInqireDao.selectFcltsFeeMngInqireListTotCnt(searchVO);
	}

	@Override
	public List selectFcltsFeeMngInqireList(GamFcltsFeeMngInqireVo searchVO) throws Exception {
		return gamFcltsFeeMngInqireDao.selectFcltsFeeMngInqireList(searchVO);
	}

	@Override
	public List selectFcltsFeeMngInqireChartList(GamFcltsFeeMngInqireVo gamFcltsFeeMngInqireVo) throws Exception {
		return gamFcltsFeeMngInqireDao.selectFcltsFeeMngInqireChartList(gamFcltsFeeMngInqireVo);
	}

	@Override
	public void updateFcltsFeeMngInqire(Map gamFcltsFeeMngInqireMap) throws Exception {
		gamFcltsFeeMngInqireDao.updateFcltsFeeMngInqire(gamFcltsFeeMngInqireMap);
	}
}
