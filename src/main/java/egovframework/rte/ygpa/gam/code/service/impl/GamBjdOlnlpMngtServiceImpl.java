/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.code.service.GamBjdOlnlpMngtService;
import egovframework.rte.ygpa.gam.code.service.GamBjdOlnlpMngtVO;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeDefaultVO;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeMngtService;
import egovframework.rte.ygpa.gam.code.service.GamBupJungDongCodeVO;
import egovframework.rte.ygpa.gam.code.service.GamBupjungdongOlnlpVO;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 3. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 3. 12.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamBjdOlnlpMngtService")
public class GamBjdOlnlpMngtServiceImpl extends AbstractServiceImpl implements GamBjdOlnlpMngtService{

	@Resource(name="gamBjdOlnlpMngtDao")
	  private GamBjdOlnlpMngtDao gamBjdOlnlpMngtDao;

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.code.service.GamBjdOlnlpMngtService#selectBjdOlnlpList(egovframework.rte.ygpa.gam.code.service.GamBupjungdongOlnlpVO)
	 */
	@Override
	public List selectBjdOlnlpList(GamBjdOlnlpMngtVO vo) throws Exception {
		return gamBjdOlnlpMngtDao.selectBjdOlnlpList(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.code.service.GamBjdOlnlpMngtService#selectBjdOlnlpListTotCnt(egovframework.rte.ygpa.gam.code.service.GamBjdOlnlpMngtVO)
	 */
	@Override
	public int selectBjdOlnlpListTotCnt(GamBjdOlnlpMngtVO vo) throws Exception {
		return gamBjdOlnlpMngtDao.selectBjdOlnlpListToTCnt(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.code.service.GamBjdOlnlpMngtService#mergeOlnlpMngt(java.util.Map)
	 */
	@Override
	public List mergeOlnlpMngt(Map mergeMap) throws Exception {
		return gamBjdOlnlpMngtDao.mergeOlnlpMngt(mergeMap);

	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.code.service.GamBjdOlnlpMngtService#applyOlnlpData()
	 */
	@Override
	public void applyOlnlpData() throws Exception {
		gamBjdOlnlpMngtDao.deleteOlnlpExists();
		gamBjdOlnlpMngtDao.addOlnlpAll();
	}



}