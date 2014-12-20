/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamFcltsMngGroupMngService;
import egovframework.rte.ygpa.gam.code.service.GamFcltsMngGroupMngVo;

/**
 *
 * @author 김종민
 * @since 2014. 12. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 10.	김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltsMngGroupMngService")
public class GamFcltsMngGroupMngServiceImpl extends AbstractServiceImpl implements GamFcltsMngGroupMngService {

	@Resource(name="gamFcltsMngGroupMngDao")
	private GamFcltsMngGroupMngDao gamFcltsMngGroupMngDao;

	@Override
	public List selectFcltsMngGroupMngList(GamFcltsMngGroupMngVo searchVO) throws Exception {
		return gamFcltsMngGroupMngDao.selectFcltsMngGroupMngList(searchVO);
	}

	@Override
	public GamFcltsMngGroupMngVo selectFcltsMngGroupMngListTotCnt(GamFcltsMngGroupMngVo searchVO) throws Exception {
		return gamFcltsMngGroupMngDao.selectFcltsMngGroupMngListTotCnt(searchVO);
	}

	@Override
	public EgovMap selectFcltsMngGroupMngPk(GamFcltsMngGroupMngVo searchVO) throws Exception {
		return gamFcltsMngGroupMngDao.selectFcltsMngGroupMngPk(searchVO);
	}

	@Override
	public void insertFcltsMngGroupMng(GamFcltsMngGroupMngVo gamFcltsMngGroupMngVo) throws Exception {
		gamFcltsMngGroupMngDao.insertFcltsMngGroupMng(gamFcltsMngGroupMngVo);
	}

	@Override
	public void updateFcltsMngGroupMng(GamFcltsMngGroupMngVo gamFcltsMngGroupMngVo) throws Exception {
		gamFcltsMngGroupMngDao.updateFcltsMngGroupMng(gamFcltsMngGroupMngVo);
	}

	@Override
	public void deleteFcltsMngGroupMng(GamFcltsMngGroupMngVo gamFcltsMngGroupMngVo) throws Exception {
		gamFcltsMngGroupMngDao.deleteFcltsMngGroupMng(gamFcltsMngGroupMngVo);
	}

}
