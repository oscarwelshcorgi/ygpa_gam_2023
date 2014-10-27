/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.mngFee.service.GamCarMngVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamGasUsageSttusMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamGrHseEmitQyMngService;
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

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeGubunMngService#selectCarMngListTotCnt(egovframework.rte.ygpa.gam.mngFee.service.GamCarMngVo)
	 */

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeGubunMngService#selectMngFeeGubunMngListTotCnt(egovframework.rte.ygpa.gam.mngFee.service.GamCarMngVo)
	 */
	@Override
	public int selectMngFeeGubunMngListTotCnt(GamMngFeeGubunMngVo searchVO) throws Exception {
		return gamMngFeeGubunMngDao.selectMngFeeGubunMngListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeGubunMngService#selectMngFeeGubunMngList(egovframework.rte.ygpa.gam.mngFee.service.GamCarMngVo)
	 */
	@Override
	public List selectMngFeeGubunMngList(GamMngFeeGubunMngVo searchVO) throws Exception {
		return gamMngFeeGubunMngDao.selectMngFeeGubunMngList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeGubunMngService#InsertMngFeeGubunMng(egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeGubunMngVo)
	 */
	@Override
	public void InsertMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo) throws Exception {
		// TODO Auto-generated method stub
		gamMngFeeGubunMngDao.InsertMngFeeGubunMng(gamMngFeeGubunMngVo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeGubunMngService#DeleteMngFeeGubunMng(egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeGubunMngVo)
	 */
	@Override
	public void DeleteMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo) {
		// TODO Auto-generated method stub
		gamMngFeeGubunMngDao.DeleteMngFeeGubunMng(gamMngFeeGubunMngVo);
	}



}
