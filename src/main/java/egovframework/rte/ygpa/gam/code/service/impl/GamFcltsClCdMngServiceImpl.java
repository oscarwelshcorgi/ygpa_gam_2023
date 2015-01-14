/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamFcltsClCdMngService;
import egovframework.rte.ygpa.gam.code.service.GamFcltsClCdMngVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 1. 14.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 1. 14.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltsClCdMngService")
public class GamFcltsClCdMngServiceImpl extends AbstractServiceImpl implements GamFcltsClCdMngService {

	@Resource(name="gamFcltsClCdMngDao")
	private GamFcltsClCdMngDao gamFcltsClCdMngDao;

	@Override
	public List selectFcltsClCdMngList(GamFcltsClCdMngVO searchVO) throws Exception {
		return gamFcltsClCdMngDao.selectFcltsClCdMngList(searchVO);
	}

	@Override
	public void insertFcltsClCdMng(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception {
		gamFcltsClCdMngDao.insertFcltsClCdMng(gamFcltsClCdMngVO);
		gamFcltsClCdMngDao.updateFcltsClCdMngLeafYn(gamFcltsClCdMngVO);
	}

	@Override
	public void updateFcltsClCdMng(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception {
		gamFcltsClCdMngDao.updateFcltsClCdMng(gamFcltsClCdMngVO);
	}

	@Override
	public void deleteFcltsClCdMng(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception {
		gamFcltsClCdMngDao.deleteFcltsClCdMngLowerData(gamFcltsClCdMngVO);
		gamFcltsClCdMngDao.deleteFcltsClCdMng(gamFcltsClCdMngVO);
		gamFcltsClCdMngDao.updateFcltsClCdMngLeafYn(gamFcltsClCdMngVO);
	}

	@Override
	public GamFcltsClCdMngVO selectFcltsClCdMngListSum(GamFcltsClCdMngVO searchVO) throws Exception {
		return gamFcltsClCdMngDao.selectFcltsClCdMngListSum(searchVO);
	}

	@Override
	public EgovMap selectFcltsClCdMngPk(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception {
		return gamFcltsClCdMngDao.selectFcltsClCdMngPk(gamFcltsClCdMngVO);
	}

	@Override
	public String selectFcltsClCdMngNewCd(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception {
		return gamFcltsClCdMngDao.selectFcltsClCdMngNewCd(gamFcltsClCdMngVO);
	}

	@Override
	public List selectFcltsClCdMngTreeList(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception {
		return gamFcltsClCdMngDao.selectFcltsClCdMngTreeList(gamFcltsClCdMngVO);
	}

	@Override
	public List selectFcltsClCdMngLowerDataCnt(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception {
		return gamFcltsClCdMngDao.selectFcltsClCdMngLowerDataCnt(gamFcltsClCdMngVO);
	}

	@Override
	public void updateFcltsClCdMngLeafYn(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception {
		gamFcltsClCdMngDao.updateFcltsClCdMngLeafYn(gamFcltsClCdMngVO);
	}

	@Override
	public void deleteFcltsClCdMngLowerData(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception {
		gamFcltsClCdMngDao.deleteFcltsClCdMngLowerData(gamFcltsClCdMngVO);
	}

}
