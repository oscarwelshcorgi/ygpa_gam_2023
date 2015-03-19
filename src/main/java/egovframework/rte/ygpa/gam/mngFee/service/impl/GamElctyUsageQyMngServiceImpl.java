/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyUsageQyMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyUsageQyMngVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 7.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamElctyUsageQyMngService")
public class GamElctyUsageQyMngServiceImpl extends AbstractServiceImpl implements GamElctyUsageQyMngService {

	@Resource(name="gamElctyUsageQyMngDao")
	private GamElctyUsageQyMngDao gamElctyUsageQyMngDao;

	@Override
	public List selectElctyUsageQyMngList(GamElctyUsageQyMngVO searchVO) throws Exception {
		return gamElctyUsageQyMngDao.selectElctyUsageQyMngList(searchVO);
	}

	@Override
	public void insertElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {
		gamElctyUsageQyMngDao.insertElctyUsageQyMng(gamElctyUsageQyMngVO);
	}

	@Override
	public void updateElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {
		gamElctyUsageQyMngDao.updateElctyUsageQyMng(gamElctyUsageQyMngVO);
	}

	@Override
	public void deleteElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {
		gamElctyUsageQyMngDao.deleteElctyUsageQyMng(gamElctyUsageQyMngVO);
	}

	@Override
	public EgovMap selectElctyUsageQyMngPk(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {
		return gamElctyUsageQyMngDao.selectElctyUsageQyMngPk(gamElctyUsageQyMngVO);
	}

	@Override
	public GamElctyUsageQyMngVO selectElctyUsageQyMngListSum(GamElctyUsageQyMngVO searchVO) throws Exception {
		return gamElctyUsageQyMngDao.selectElctyUsageQyMngListSum(searchVO);
	}

	@Override
	public List selectElctyUsageQyMngChartList(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {
		return gamElctyUsageQyMngDao.selectElctyUsageQyMngChartList(gamElctyUsageQyMngVO);
	}

	@Override
	public List selectElctyUsageQyMngMonthChartList(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {
		return gamElctyUsageQyMngDao.selectElctyUsageQyMngMonthChartList(gamElctyUsageQyMngVO);
	}

	@Override
	public List selectElctyUsageQyMngYearCntList(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {
		return gamElctyUsageQyMngDao.selectElctyUsageQyMngYearCntList(gamElctyUsageQyMngVO);
	}

	@Override
	public void copyElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception {
		gamElctyUsageQyMngDao.copyElctyUsageQyMng(gamElctyUsageQyMngVO);
	}

	@Override
	public List selectMngFeeFcltyCdList() throws Exception {
		return gamElctyUsageQyMngDao.selectMngFeeFcltyCdList();
	}

}
