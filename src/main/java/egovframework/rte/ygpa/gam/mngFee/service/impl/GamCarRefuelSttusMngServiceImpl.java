/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamCarRefuelSttusExcelVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamCarRefuelSttusMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamCarRefuelSttusMngVo;

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
@Service("gamCarRefuelSttusMngService")
public class GamCarRefuelSttusMngServiceImpl extends AbstractServiceImpl implements GamCarRefuelSttusMngService{

	@Resource(name="gamCarRefuelSttusMngDao")
	private GamCarRefuelSttusMngDao gamCarRefuelSttusMngDao;

	@Override
	public int selectCarRefuelSttusMngListTotCnt(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) throws Exception {
		return gamCarRefuelSttusMngDao.selectCarRefuelSttusMngListTotCnt(gamCarRefuelSttusMngVo);
	}

	@Override
	public List selectCarRefuelSttusMngList(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) throws Exception {
		return gamCarRefuelSttusMngDao.selectCarRefuelSttusMngList(gamCarRefuelSttusMngVo);
	}

	@Override
	public EgovMap selectCarRefuelSttusMngPk(GamCarRefuelSttusMngVo searchVO) throws Exception {
		return gamCarRefuelSttusMngDao.selectCarRefuelSttusMngPk(searchVO);
	}

	@Override
	public void insertCarRefuelSttusMngList(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) throws Exception {
		gamCarRefuelSttusMngDao.deleteCarRefuelSttusMngList(gamCarRefuelSttusMngVo);
		gamCarRefuelSttusMngDao.insertCarRefuelSttusMngList(gamCarRefuelSttusMngVo);
	}

	@Override
	public void updateCarRefuelSttusMngList(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) throws Exception {
		gamCarRefuelSttusMngDao.updateCarRefuelSttusMngList(gamCarRefuelSttusMngVo);
	}

	@Override
	public void deleteCarRefuelSttusMngList(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) throws Exception {
		gamCarRefuelSttusMngDao.deleteCarRefuelSttusMngList(gamCarRefuelSttusMngVo);
	}

}
