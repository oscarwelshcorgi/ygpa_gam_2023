/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngNticVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngDetailVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeCodeMngVo;

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
@Service("gamFcltsMngFeeMngService")
public class GamFcltsMngFeeMngServiceImpl extends AbstractServiceImpl implements GamFcltsMngFeeMngService{

	@Resource(name="gamFcltsMngFeeMngDao")
	private GamFcltsMngFeeMngDao gamFcltsMngFeeMngDao;

	@Override
	public GamFcltsMngFeeMngVo selectFcltsMngFeeMngListTotCnt(GamFcltsMngFeeMngVo searchVO) throws Exception {
		return gamFcltsMngFeeMngDao.selectFcltsMngFeeMngListTotCnt(searchVO);
	}

	@Override
	public List selectFcltsMngFeeMngList(GamFcltsMngFeeMngVo searchVO) throws Exception {
		return gamFcltsMngFeeMngDao.selectFcltsMngFeeMngList(searchVO);
	}

	@Override
	public void insertFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception {
		gamFcltsMngFeeMngDao.insertFcltsMngFeeMng(gamFcltsMngFeeMngVo);
	}

	@Override
	public void updateFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception {
		gamFcltsMngFeeMngDao.updateFcltsMngFeeMng(gamFcltsMngFeeMngVo);
	}

	@Override
	public void deleteFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception {
		gamFcltsMngFeeMngDao.deleteAllFcltsMngFeeMngDetail(gamFcltsMngFeeMngVo);
		gamFcltsMngFeeMngDao.deleteFcltsMngFeeMng(gamFcltsMngFeeMngVo);
	}

	@Override
	public List selectFcltsMngFeeMngDetailList(GamFcltsMngFeeMngDetailVo searchVO) throws Exception {
		return gamFcltsMngFeeMngDao.selectFcltsMngFeeMngDetailList(searchVO);
	}

	@Override
	public String selectFcltsMngFeeMngDetailMaxMngSeq(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception {
		return gamFcltsMngFeeMngDao.selectFcltsMngFeeMngDetailMaxMngSeq(gamFcltsMngFeeMngDetailVo);
	}

	@Override
	public void insertFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception {
		gamFcltsMngFeeMngDao.insertFcltsMngFeeMngDetail(gamFcltsMngFeeMngDetailVo);
	}

	@Override
	public void updateFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception {
		gamFcltsMngFeeMngDao.updateFcltsMngFeeMngDetail(gamFcltsMngFeeMngDetailVo);
	}

	@Override
	public void deleteFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception {
		gamFcltsMngFeeMngDao.deleteFcltsMngFeeMngDetail(gamFcltsMngFeeMngDetailVo);
	}

}