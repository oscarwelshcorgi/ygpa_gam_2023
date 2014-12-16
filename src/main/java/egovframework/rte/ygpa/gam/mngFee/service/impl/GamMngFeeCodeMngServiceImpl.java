/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeCodeMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeCodeMngVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeGubunMngVo;

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
@Service("gamMngFeeCodeMngService")
public class GamMngFeeCodeMngServiceImpl extends AbstractServiceImpl implements GamMngFeeCodeMngService{

	@Resource(name="gamMngFeeCodeMngDao")
	private GamMngFeeCodeMngDao gamMngFeeCodeMngDao;

	@Override
	public int selectMngFeeCodeMngListTotCnt(GamMngFeeCodeMngVo searchVO) throws Exception {
		return gamMngFeeCodeMngDao.selectMngFeeCodeMngListTotCnt(searchVO);
	}

	@Override
	public List selectMngFeeCodeMngList(GamMngFeeCodeMngVo searchVO) throws Exception {
		return gamMngFeeCodeMngDao.selectMngFeeCodeMngList(searchVO);
	}

	@Override
	public EgovMap selectMngFeeCodeMngPk(GamMngFeeCodeMngVo searchVO) throws Exception {
		return gamMngFeeCodeMngDao.selectMngFeeCodeMngPk(searchVO);
	}

	@Override
	public List selectMngFeeFcltySeMngList(Map searchVO) throws Exception {
		return gamMngFeeCodeMngDao.selectMngFeeFcltySeMngList(searchVO);
	}

	@Override
	public String selectMngFeeCodeMngMaxFcltyCd(GamMngFeeCodeMngVo gamMngFeeCodeMngVo) throws Exception {
		return gamMngFeeCodeMngDao.selectMngFeeCodeMngMaxFcltyCd(gamMngFeeCodeMngVo);
	}

	@Override
	public void insertMngFeeCodeMng(GamMngFeeCodeMngVo gamMngFeeCodeMngVo) throws Exception {
		gamMngFeeCodeMngDao.insertMngFeeCodeMng(gamMngFeeCodeMngVo);
	}

	@Override
	public void updateMngFeeCodeMng(GamMngFeeCodeMngVo gamMngFeeCodeMngVo) throws Exception {
		gamMngFeeCodeMngDao.updateMngFeeCodeMng(gamMngFeeCodeMngVo);
	}

	@Override
	public void deleteMngFeeCodeMng(GamMngFeeCodeMngVo gamMngFeeCodeMngVo) throws Exception {
		gamMngFeeCodeMngDao.deleteMngFeeCodeMng(gamMngFeeCodeMngVo);
	}

}
