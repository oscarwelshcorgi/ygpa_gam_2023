/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtLgerHistService;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtLgerHistVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngChangeVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngFulfillCaryFwdVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngJoinContrVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngMoneyPymntVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngScsbidInfoVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngSubctrtVO;

/**
 *
 * @author HNJ
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltyCtrtLgerHistService")
public class GamFcltyCtrtLgerHistServiceImpl  extends AbstractServiceImpl implements GamFcltyCtrtLgerHistService {

	@Resource(name="gamFcltyCtrtLgerHistDao")
    private GamFcltyCtrtLgerHistDao gamFcltyCtrtLgerHistDao;

	@Override
	public List selectFcltyCtrtLgerHistList(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		return gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistList(searchVO);
	}

	@Override
	public GamFcltyCtrtLgerHistVO selectFcltyCtrtLgerHistListSum(GamFcltyCtrtLgerHistVO searchVO) throws Exception {
		return gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistListSum(searchVO);
	}


	@Override
	public List selectFcltyCtrtLgerHistJoinContrList(GamFcltyCtrtMngJoinContrVO searchVO) throws Exception {
		return gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistJoinContrList(searchVO);
	}


	@Override
	public List selectFcltyCtrtLgerHistSubctrtList(GamFcltyCtrtMngSubctrtVO searchVO) throws Exception {
		return gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistSubctrtList(searchVO);
	}


	@Override
	public List selectFcltyCtrtLgerHistChangeList(GamFcltyCtrtMngChangeVO searchVO) throws Exception {
		return gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistChangeList(searchVO);
	}


	@Override
	public List selectFcltyCtrtLgerHistMoneyPymntList(GamFcltyCtrtMngMoneyPymntVO searchVO) throws Exception {
		return gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistMoneyPymntList(searchVO);
	}


	@Override
	public List selectFcltyCtrtLgerHistFulfillCaryFwdList(GamFcltyCtrtMngFulfillCaryFwdVO searchVO) throws Exception {
		return gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistFulfillCaryFwdList(searchVO);
	}


	@Override
	public List selectFcltyCtrtLgerHistScsbidInfoList(GamFcltyCtrtMngScsbidInfoVO searchVO) throws Exception {
		return gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistScsbidInfoList(searchVO);
	}

	@Override
	public EgovMap selectEntrpsInfo(Map searchVO) throws Exception {
		return gamFcltyCtrtLgerHistDao.selectEntrpsInfo(searchVO);
	}

}
