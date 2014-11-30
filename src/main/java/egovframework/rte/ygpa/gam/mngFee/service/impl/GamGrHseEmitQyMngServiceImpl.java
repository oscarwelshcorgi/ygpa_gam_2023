/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.mngFee.service.GamGrHseEmitQyMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamGrHseEmitQyMngVo;

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
@Service("gamGrHseEmitQyMngService")
public class GamGrHseEmitQyMngServiceImpl extends AbstractServiceImpl implements GamGrHseEmitQyMngService{

	@Resource(name="gamGrHseEmitQyMngDao")
	private GamGrHseEmitQyMngDao gamGrHseEmitQyMngDao;

	@Override
	public int selectGrHseEmitQyMngListTotCnt(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception {
		return gamGrHseEmitQyMngDao.selectGrHseEmitQyMngListTotCnt(gamGrHseEmitQyMngVo);
	}

	@Override
	public List selectGrHseEmitQyMngList(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception {
		return gamGrHseEmitQyMngDao.selectGrHseEmitQyMngList(gamGrHseEmitQyMngVo);
	}

	@Override
	public List selectGrHseEmitQyMngChartList(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception {
		return gamGrHseEmitQyMngDao.selectGrHseEmitQyMngChartList(gamGrHseEmitQyMngVo);
	}

	@Override
	public List selectGrHseEmitQyMngMonthCntList(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception {
		return gamGrHseEmitQyMngDao.selectGrHseEmitQyMngMonthCntList(gamGrHseEmitQyMngVo);
	}

	@Override
	public void insertGrHseEmitQyMng(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception {
		gamGrHseEmitQyMngDao.insertGrHseEmitQyMng(gamGrHseEmitQyMngVo);
	}

	@Override
	public void updateGrHseEmitQyMng(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception {
		gamGrHseEmitQyMngDao.updateGrHseEmitQyMng(gamGrHseEmitQyMngVo);
	}

	@Override
	public void deleteGrHseEmitQyMng(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception {
		gamGrHseEmitQyMngDao.deleteGrHseEmitQyMng(gamGrHseEmitQyMngVo);
	}

	@Override
	public void copyGrHseEmitQyMng(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception {
		gamGrHseEmitQyMngDao.copyGrHseEmitQyMng(gamGrHseEmitQyMngVo);
	}

}
