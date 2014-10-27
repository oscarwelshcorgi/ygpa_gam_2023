/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.mngFee.service.GamGasUsageSttusMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamGrHseEmitQyMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeCodeMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeCodeMngVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeGubunMngService;

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
	public void InsertMngFeeCodeMng(GamMngFeeCodeMngVo gamMngFeeCodeMngVo) throws Exception {
		gamMngFeeCodeMngDao.InsertMngFeeCodeMng(gamMngFeeCodeMngVo);
	}

	@Override
	public void DeleteMngFeeCodeMng(GamMngFeeCodeMngVo gamMngFeeCodeMngVo) throws Exception {
		gamMngFeeCodeMngDao.DeleteMngFeeCodeMng(gamMngFeeCodeMngVo);
	}


}
