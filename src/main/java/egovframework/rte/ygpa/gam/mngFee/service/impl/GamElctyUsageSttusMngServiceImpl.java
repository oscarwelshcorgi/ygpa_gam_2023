/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyUsageSttusMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyUsageSttusMngVo;

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
@Service("gamElctyUsageSttusMngService")
public class GamElctyUsageSttusMngServiceImpl extends AbstractServiceImpl implements GamElctyUsageSttusMngService{

	@Resource(name="gamElctyUsageSttusMngDao")
	private GamElctyUsageSttusMngDao gamElctyUsageSttusMngDao;

	@Override
	public List selectElctyUsageSttusMngMonthCntList(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception {
		return gamElctyUsageSttusMngDao.selectElctyUsageSttusMngMonthCntList(gamElctyUsageSttusMngVo);
	}

	@Override
	public List selectElctyUsageSttusMngList(GamElctyUsageSttusMngVo searchVO) throws Exception {
		return gamElctyUsageSttusMngDao.selectElctyUsageSttusMngList(searchVO);
	}

	@Override
	public EgovMap selectElctyUsageSttusMngPk(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception {
		return gamElctyUsageSttusMngDao.selectElctyUsageSttusMngPk(gamElctyUsageSttusMngVo);
	}

	@Override
	public int selectElctyUsageSttusMngListTotCnt(GamElctyUsageSttusMngVo searchVO) throws Exception {
		return gamElctyUsageSttusMngDao.selectElctyUsageSttusMngListTotCnt(searchVO);
	}

	@Override
	public String selectElctyUsageSttusMngPrevMtUsageQy(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception {
		return gamElctyUsageSttusMngDao.selectElctyUsageSttusMngPrevMtUsageQy(gamElctyUsageSttusMngVo);
	}

	@Override
	public List selectElctyUsageSttusMngChartList(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception {
		return gamElctyUsageSttusMngDao.selectElctyUsageSttusMngChartList(gamElctyUsageSttusMngVo);
	}

	@Override
	public void insertElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception {
		gamElctyUsageSttusMngDao.insertElctyUsageSttusMng(gamElctyUsageSttusMngVo);
	}

	@Override
	public void updateElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception {
		gamElctyUsageSttusMngDao.updateElctyUsageSttusMng(gamElctyUsageSttusMngVo);
	}

	@Override
	public void deleteElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception {
		gamElctyUsageSttusMngDao.deleteElctyUsageSttusMng(gamElctyUsageSttusMngVo);
	}

	@Override
	public void copyElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception {
		gamElctyUsageSttusMngDao.copyElctyUsageSttusMng(gamElctyUsageSttusMngVo);
	}

}
