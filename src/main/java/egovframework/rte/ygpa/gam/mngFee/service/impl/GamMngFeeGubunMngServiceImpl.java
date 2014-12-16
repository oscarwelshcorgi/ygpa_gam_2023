/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeGubunMngService;
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
@Service("gamMngFeeGubunMngService")
public class GamMngFeeGubunMngServiceImpl extends AbstractServiceImpl implements GamMngFeeGubunMngService{

	@Resource(name="gamMngFeeGubunMngDao")
	private GamMngFeeGubunMngDao gamMngFeeGubunMngDao;

	@Override
	public int selectMngFeeGubunMngListTotCnt(GamMngFeeGubunMngVo searchVO) throws Exception {
		return gamMngFeeGubunMngDao.selectMngFeeGubunMngListTotCnt(searchVO);
	}

	@Override
	public List selectMngFeeGubunMngList(GamMngFeeGubunMngVo searchVO) throws Exception {
		return gamMngFeeGubunMngDao.selectMngFeeGubunMngList(searchVO);
	}

	@Override
	public EgovMap selectMngFeeGubunMngPk(GamMngFeeGubunMngVo searchVO) throws Exception {
		return gamMngFeeGubunMngDao.selectMngFeeGubunMngPk(searchVO);
	}

	@Override
	public void insertMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo) throws Exception {
		gamMngFeeGubunMngDao.insertMngFeeGubunMng(gamMngFeeGubunMngVo);
	}

	@Override
	public void updateMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo) throws Exception {
		gamMngFeeGubunMngDao.updateMngFeeGubunMng(gamMngFeeGubunMngVo);
	}

	@Override
	public void deleteMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo) throws Exception  {
		gamMngFeeGubunMngDao.deleteMngFeeGubunMng(gamMngFeeGubunMngVo);
	}

	@Override
	public int checkSeFeeGubunMng(String checkSe) throws Exception {
		return gamMngFeeGubunMngDao.checkSeFeeGubunMng(checkSe);
	}

}
