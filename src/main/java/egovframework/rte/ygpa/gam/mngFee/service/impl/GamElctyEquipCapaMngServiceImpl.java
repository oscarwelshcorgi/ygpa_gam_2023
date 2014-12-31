/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyEquipCapaMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyEquipCapaMngVo;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 31.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 31.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamElctyEquipCapaMngService")
public class GamElctyEquipCapaMngServiceImpl extends AbstractServiceImpl implements GamElctyEquipCapaMngService {

	@Resource(name="gamElctyEquipCapaMngDao")
	private GamElctyEquipCapaMngDao gamElctyEquipCapaMngDao;

	@Override
	public GamElctyEquipCapaMngVo selectElctyEquipCapaMngListTotCnt(GamElctyEquipCapaMngVo searchVO) throws Exception {
		return gamElctyEquipCapaMngDao.selectElctyEquipCapaMngListTotCnt(searchVO);
	}

	@Override
	public List selectElctyEquipCapaMngList(GamElctyEquipCapaMngVo searchVO) throws Exception {
		return gamElctyEquipCapaMngDao.selectElctyEquipCapaMngList(searchVO);
	}

	@Override
	public EgovMap selectElctyEquipCapaMngPk(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception {
		return gamElctyEquipCapaMngDao.selectElctyEquipCapaMngPk(gamElctyEquipCapaMngVo);
	}

	@Override
	public EgovMap selectElctyEquipCapaMngPrevYearCapa(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception {
		return gamElctyEquipCapaMngDao.selectElctyEquipCapaMngPrevYearCapa(gamElctyEquipCapaMngVo);
	}

	@Override
	public List selectElctyEquipCapaMngYearCntList(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception {
		return gamElctyEquipCapaMngDao.selectElctyEquipCapaMngYearCntList(gamElctyEquipCapaMngVo);
	}

	@Override
	public List selectElctyEquipCapaMngChartList(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception {
		return gamElctyEquipCapaMngDao.selectElctyEquipCapaMngChartList(gamElctyEquipCapaMngVo);
	}

	@Override
	public String selectElctyEquipCapaMngNewMngSeq(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception {
		return gamElctyEquipCapaMngDao.selectElctyEquipCapaMngNewMngSeq(gamElctyEquipCapaMngVo);
	}

	@Override
	public void insertElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception {
		gamElctyEquipCapaMngDao.insertElctyEquipCapaMng(gamElctyEquipCapaMngVo);
	}

	@Override
	public void updateElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception {
		gamElctyEquipCapaMngDao.updateElctyEquipCapaMng(gamElctyEquipCapaMngVo);
	}

	@Override
	public void deleteElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception {
		gamElctyEquipCapaMngDao.deleteElctyEquipCapaMng(gamElctyEquipCapaMngVo);
	}

	@Override
	public void copyElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) throws Exception {
		gamElctyEquipCapaMngDao.copyElctyEquipCapaMng(gamElctyEquipCapaMngVo);
	}

}
