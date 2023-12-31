/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamGasUsageSttusMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamGasUsageSttusMngVo;

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
@Service("gamGasUsageSttusMngService")
public class GamGasUsageSttusMngServiceImpl extends AbstractServiceImpl implements GamGasUsageSttusMngService{

	@Resource(name="gamGasUsageSttusMngDao")
	private GamGasUsageSttusMngDao gamGasUsageSttusMngDao;

	@Override
	public int selectGasUsageSttusMngListTotCnt(GamGasUsageSttusMngVo searchVO) throws Exception {
		return gamGasUsageSttusMngDao.selectGasUsageSttusMngListTotCnt(searchVO);
	}

	@Override
	public List selectGasUsageSttusMngList(GamGasUsageSttusMngVo searchVO) throws Exception {
		return gamGasUsageSttusMngDao.selectGasUsageSttusMngList(searchVO);
	}

	@Override
	public EgovMap selectGasUsageSttusMngPk(GamGasUsageSttusMngVo searchVO) throws Exception {
		return gamGasUsageSttusMngDao.selectGasUsageSttusMngPk(searchVO);
	}

	@Override
	public List selectGasUsageSttusMngChartList(GamGasUsageSttusMngVo searchVO) throws Exception {
		return gamGasUsageSttusMngDao.selectGasUsageSttusMngChartList(searchVO);
	}

	@Override
	public String selectGasUsageSttusMngPrevMtUsageQy(GamGasUsageSttusMngVo gamGasUsageSttusMngVo) throws Exception {
		return gamGasUsageSttusMngDao.selectGasUsageSttusMngPrevMtUsageQy(gamGasUsageSttusMngVo);
	}

	@Override
	public void insertGasUsageSttusMng(GamGasUsageSttusMngVo gamGasUsageSttusMngVo) throws Exception {
		gamGasUsageSttusMngDao.insertGasUsageSttusMng(gamGasUsageSttusMngVo);
	}

	@Override
	public void updateGasUsageSttusMng(GamGasUsageSttusMngVo gamGasUsageSttusMngVo) throws Exception {
		gamGasUsageSttusMngDao.updateGasUsageSttusMng(gamGasUsageSttusMngVo);
	}

	@Override
	public void deleteGasUsageSttusMng(GamGasUsageSttusMngVo gamGasUsageSttusMngVo) throws Exception {
		gamGasUsageSttusMngDao.deleteGasUsageSttusMng(gamGasUsageSttusMngVo);
	}

}
