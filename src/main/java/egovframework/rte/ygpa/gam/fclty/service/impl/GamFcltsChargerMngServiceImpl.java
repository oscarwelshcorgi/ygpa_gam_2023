/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsChargerMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsChargerMngVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 3. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 5.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltsChargerMngService")
public class GamFcltsChargerMngServiceImpl extends AbstractServiceImpl implements GamFcltsChargerMngService {

	@Resource(name="gamFcltsChargerMngDao")
	private GamFcltsChargerMngDao gamFcltsChargerMngDao;

	@Override
	public List selectFcltsChargerMngList(GamFcltsChargerMngVO searchVO) throws Exception {
		return gamFcltsChargerMngDao.selectFcltsChargerMngList(searchVO);
	}

	@Override
	public void insertFcltsChargerMng(GamFcltsChargerMngVO gamFcltsChargerMngVO) throws Exception {
		gamFcltsChargerMngDao.insertFcltsChargerMng(gamFcltsChargerMngVO);
	}

	@Override
	public void updateFcltsChargerMng(GamFcltsChargerMngVO gamFcltsChargerMngVO) throws Exception {
		gamFcltsChargerMngDao.updateFcltsChargerMng(gamFcltsChargerMngVO);
	}

	@Override
	public void deleteFcltsChargerMng(GamFcltsChargerMngVO gamFcltsChargerMngVO) throws Exception {
		gamFcltsChargerMngDao.deleteFcltsChargerMng(gamFcltsChargerMngVO);
	}

	@Override
	public GamFcltsChargerMngVO selectFcltsChargerMngListSum(GamFcltsChargerMngVO searchVO) throws Exception {
		return gamFcltsChargerMngDao.selectFcltsChargerMngListSum(searchVO);
	}

	@Override
	public EgovMap selectFcltsChargerMngPk(GamFcltsChargerMngVO gamFcltsChargerMngVO) throws Exception {
		return gamFcltsChargerMngDao.selectFcltsChargerMngPk(gamFcltsChargerMngVO);
	}

}
