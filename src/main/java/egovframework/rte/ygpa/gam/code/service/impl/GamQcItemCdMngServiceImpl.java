/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamQcItemCdMngService;
import egovframework.rte.ygpa.gam.code.service.GamQcItemCdMngVo;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 22.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamQcItemCdMngService")
public class GamQcItemCdMngServiceImpl extends AbstractServiceImpl implements GamQcItemCdMngService {

	@Resource(name="gamQcItemCdMngDao")
	private GamQcItemCdMngDao gamQcItemCdMngDao;

	@Override
	public List selectQcItemCdMngList(GamQcItemCdMngVo searchVO) throws Exception {
		return gamQcItemCdMngDao.selectQcItemCdMngList(searchVO);
	}

	@Override
	public GamQcItemCdMngVo selectQcItemCdMngListTotCnt(GamQcItemCdMngVo searchVO) throws Exception {
		return gamQcItemCdMngDao.selectQcItemCdMngListTotCnt(searchVO);
	}

	@Override
	public EgovMap selectQcItemCdMngPk(GamQcItemCdMngVo searchVO) throws Exception {
		return gamQcItemCdMngDao.selectQcItemCdMngPk(searchVO);
	}

	@Override
	public String selectQcItemCdMngNewQcItemCd(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception {
		return gamQcItemCdMngDao.selectQcItemCdMngNewQcItemCd(gamQcItemCdMngVo);
	}

	@Override
	public List selectQcItemCdMngTreeList(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception {
		return gamQcItemCdMngDao.selectQcItemCdMngTreeList(gamQcItemCdMngVo);
	}

	@Override
	public List selectQcItemCdMngLowerDataCnt(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception {
		return gamQcItemCdMngDao.selectQcItemCdMngLowerDataCnt(gamQcItemCdMngVo);
	}

	@Override
	public void insertQcItemCdMng(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception {
		gamQcItemCdMngDao.insertQcItemCdMng(gamQcItemCdMngVo);
		gamQcItemCdMngDao.updateQcItemCdMngLeafYn(gamQcItemCdMngVo);
	}

	@Override
	public void updateQcItemCdMng(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception {
		gamQcItemCdMngDao.updateQcItemCdMng(gamQcItemCdMngVo);
	}

	@Override
	public void deleteQcItemCdMng(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception {
		gamQcItemCdMngDao.deleteQcItemCdMngLowerData(gamQcItemCdMngVo);
		gamQcItemCdMngDao.deleteQcItemCdMng(gamQcItemCdMngVo);
		gamQcItemCdMngDao.updateQcItemCdMngLeafYn(gamQcItemCdMngVo);
	}

	@Override
	public void deleteQcItemCdMngLowerData(GamQcItemCdMngVo gamQcItemCdMngVo) throws Exception {
		gamQcItemCdMngDao.deleteQcItemCdMngLowerData(gamQcItemCdMngVo);
	}

}
