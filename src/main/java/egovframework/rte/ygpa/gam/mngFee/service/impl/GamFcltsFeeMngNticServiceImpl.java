/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngNticService;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngNticVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamGasUsageSttusMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamGrHseEmitQyMngService;

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
@Service("gamFcltsFeeMngNticService")
public class GamFcltsFeeMngNticServiceImpl extends AbstractServiceImpl implements GamFcltsFeeMngNticService{

	@Resource(name="gamFcltsFeeMngNticDao")
	private GamFcltsFeeMngNticDao gamFcltsFeeMngNticDao;

	@Override
	public int selectFcltsFeeMngNticListTotCnt(GamFcltsFeeMngNticVo searchVO) throws Exception {
		return gamFcltsFeeMngNticDao.selectFcltsFeeMngNticListTotCnt(searchVO);
	}

	@Override
	public List selectFcltsFeeMngNticList(GamFcltsFeeMngNticVo searchVO) throws Exception {
		return gamFcltsFeeMngNticDao.selectFcltsFeeMngNticList(searchVO);
	}

	@Override
	public void InsertFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) throws Exception {
		gamFcltsFeeMngNticDao.InsertFcltsFeeMngNtic(gamFcltsFeeMngNticVo);
	}

	@Override
	public void DeleteFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) throws Exception {
		gamFcltsFeeMngNticDao.DeleteFcltsFeeMngNtic(gamFcltsFeeMngNticVo);
	}



}
