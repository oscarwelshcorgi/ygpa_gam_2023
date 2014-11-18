/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.mngFee.service.GamCarMngService;
import egovframework.rte.ygpa.gam.mngFee.service.GamCarMngVo;

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
@Service("gamCarMngService")
public class GamCarMngServiceImpl extends AbstractServiceImpl implements GamCarMngService{

	@Resource(name="gamCarMngDao")
	private GamCarMngDao gamCarMngDao;

	public List selectCarMngList(GamCarMngVo searchVO) throws Exception {
		return gamCarMngDao.selectCarMngList(searchVO);
	}

	public int selectCarMngListTotCnt(GamCarMngVo searchVO) throws Exception {
        return gamCarMngDao.selectCarMngListTotCnt(searchVO);
    }

	@Override
	public void insertCarMng(GamCarMngVo gamCarMngVo) throws Exception {
		 gamCarMngDao.insertCarMng(gamCarMngVo);
	}

	@Override
	public void updateCarMng(GamCarMngVo gamCarMngVo) throws Exception {
		gamCarMngDao.updateCarMng(gamCarMngVo);
	}

	@Override
	public void deleteCarMng(GamCarMngVo gamCarMngVo) throws Exception {
		gamCarMngDao.deleteCarMng(gamCarMngVo);
	}
}
