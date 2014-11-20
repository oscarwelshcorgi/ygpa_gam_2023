/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.mngFee.service.GamEnergyUsageMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamEnergyUsageMngVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamGasUsageSttusMngService;

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
@Service("gamEnergyUsageMngService")
public class GamEnergyUsageMngServiceImpl extends AbstractServiceImpl implements GamEnergyUsageMngService{

	@Resource(name="gamEnergyUsageMngDao")
	private GamEnergyUsageMngDao gamEnergyUsageMngDao;

	@Override
	public int selectEnergyUsageMngListTotCnt(GamEnergyUsageMngVo searchVO) throws Exception {
		return gamEnergyUsageMngDao.selectEnergyUsageMngListTotCnt(searchVO);
	}

	@Override
	public int selectEnergyUsageMngListYearCnt(GamEnergyUsageMngVo searchVO) throws Exception {
		return gamEnergyUsageMngDao.selectEnergyUsageMngListYearCnt(searchVO);
	}

	@Override
	public List selectEnergyUsageMngList(GamEnergyUsageMngVo searchVO) throws Exception {
		return gamEnergyUsageMngDao.selectEnergyUsageMngList(searchVO);
	}

	@Override
	public void insertEnergyUsageMng(GamEnergyUsageMngVo gamEnergyUsageMngVo) throws Exception {
		gamEnergyUsageMngDao.insertEnergyUsageMng(gamEnergyUsageMngVo);
	}

	@Override
	public void updateEnergyUsageMng(GamEnergyUsageMngVo gamEnergyUsageMngVo) throws Exception {
		gamEnergyUsageMngDao.updateEnergyUsageMng(gamEnergyUsageMngVo);
	}

	@Override
	public void deleteEnergyUsageMng(GamEnergyUsageMngVo gamEnergyUsageMngVo) throws Exception {
		gamEnergyUsageMngDao.deleteEnergyUsageMng(gamEnergyUsageMngVo);
	}

	@Override
	public void copyEnergyUsageMng(GamEnergyUsageMngVo gamEnergyUsageMngVo) throws Exception {
		gamEnergyUsageMngDao.copyEnergyUsageMng(gamEnergyUsageMngVo);
	}

}
