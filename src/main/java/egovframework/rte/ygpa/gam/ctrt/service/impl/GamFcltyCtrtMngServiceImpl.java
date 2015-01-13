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
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngChangeVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngFulfillCaryFwdVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngJoinContrVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngMoneyPymntVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngScsbidInfoVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngService;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngSubctrtVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngVO;

/**
 *
 * @author 김종민
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltyCtrtMngService")
public class GamFcltyCtrtMngServiceImpl extends AbstractServiceImpl implements GamFcltyCtrtMngService {

	@Resource(name="gamFcltyCtrtMngDao")
	private GamFcltyCtrtMngDao gamFcltyCtrtMngDao;

	@Override
	public List selectFcltyCtrtMngList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngList(searchVO);
	}

	@Override
	public void insertFcltyCtrtMng(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) throws Exception {
		gamFcltyCtrtMngDao.insertFcltyCtrtMng(gamFcltyCtrtMngVO);
	}

	@Override
	public void updateFcltyCtrtMng(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) throws Exception {
		gamFcltyCtrtMngDao.updateFcltyCtrtMng(gamFcltyCtrtMngVO);
	}

	@Override
	public void deleteFcltyCtrtMng(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) throws Exception {
		GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO = new GamFcltyCtrtMngJoinContrVO();
		gamFcltyCtrtMngJoinContrVO.setJoinCtrtNo(gamFcltyCtrtMngVO.getCtrtNo());
		gamFcltyCtrtMngDao.deleteFcltyCtrtMngJoinContr(gamFcltyCtrtMngJoinContrVO);

		GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO = new GamFcltyCtrtMngSubctrtVO();
		gamFcltyCtrtMngSubctrtVO.setSubCtrtNo(gamFcltyCtrtMngVO.getCtrtNo());
		gamFcltyCtrtMngDao.deleteFcltyCtrtMngSubctrt(gamFcltyCtrtMngSubctrtVO);

		GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO = new GamFcltyCtrtMngChangeVO();
		gamFcltyCtrtMngChangeVO.setChangeInfoCtrtNo(gamFcltyCtrtMngVO.getCtrtNo());
		gamFcltyCtrtMngDao.deleteFcltyCtrtMngChange(gamFcltyCtrtMngChangeVO);

		GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO = new GamFcltyCtrtMngMoneyPymntVO();
		gamFcltyCtrtMngMoneyPymntVO.setPymntCtrtNo(gamFcltyCtrtMngVO.getCtrtNo());
		gamFcltyCtrtMngDao.deleteFcltyCtrtMngMoneyPymnt(gamFcltyCtrtMngMoneyPymntVO);

		GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO = new GamFcltyCtrtMngFulfillCaryFwdVO();
		gamFcltyCtrtMngFulfillCaryFwdVO.setCaryFwdCtrtNo(gamFcltyCtrtMngVO.getCtrtNo());
		gamFcltyCtrtMngDao.deleteFcltyCtrtMngFulfillCaryFwd(gamFcltyCtrtMngFulfillCaryFwdVO);

		GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO = new GamFcltyCtrtMngScsbidInfoVO();
		gamFcltyCtrtMngScsbidInfoVO.setScsbidCtrtNo(gamFcltyCtrtMngVO.getCtrtNo());
		gamFcltyCtrtMngDao.deleteFcltyCtrtMngScsbidInfo(gamFcltyCtrtMngScsbidInfoVO);

		gamFcltyCtrtMngDao.deleteFcltyCtrtMng(gamFcltyCtrtMngVO);
	}

	@Override
	public EgovMap selectFcltyCtrtMngPk(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngPk(gamFcltyCtrtMngVO);
	}

	@Override
	public GamFcltyCtrtMngVO selectFcltyCtrtMngListSum(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngListSum(searchVO);
	}

	@Override
	public String selectFcltyCtrtMngNewCtrtNo(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngNewCtrtNo(gamFcltyCtrtMngVO);
	}


	@Override
	public List selectFcltyCtrtMngJoinContrList(GamFcltyCtrtMngJoinContrVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngJoinContrList(searchVO);
	}

	@Override
	public void insertFcltyCtrtMngJoinContr(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) throws Exception {
		gamFcltyCtrtMngDao.insertFcltyCtrtMngJoinContr(gamFcltyCtrtMngJoinContrVO);
	}

	@Override
	public void updateFcltyCtrtMngJoinContr(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) throws Exception {
		gamFcltyCtrtMngDao.updateFcltyCtrtMngJoinContr(gamFcltyCtrtMngJoinContrVO);
	}

	@Override
	public void deleteFcltyCtrtMngJoinContr(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) throws Exception {
		gamFcltyCtrtMngDao.deleteFcltyCtrtMngJoinContr(gamFcltyCtrtMngJoinContrVO);
	}

	@Override
	public EgovMap selectFcltyCtrtMngJoinContrPk(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngJoinContrPk(gamFcltyCtrtMngJoinContrVO);
	}

	@Override
	public String selectFcltyCtrtMngJoinContrMaxSeq(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngJoinContrMaxSeq(gamFcltyCtrtMngJoinContrVO);
	}


	@Override
	public List selectFcltyCtrtMngSubctrtList(GamFcltyCtrtMngSubctrtVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngSubctrtList(searchVO);
	}

	@Override
	public void insertFcltyCtrtMngSubctrt(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) throws Exception {
		gamFcltyCtrtMngDao.insertFcltyCtrtMngSubctrt(gamFcltyCtrtMngSubctrtVO);
	}

	@Override
	public void updateFcltyCtrtMngSubctrt(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) throws Exception {
		gamFcltyCtrtMngDao.updateFcltyCtrtMngSubctrt(gamFcltyCtrtMngSubctrtVO);
	}

	@Override
	public void deleteFcltyCtrtMngSubctrt(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) throws Exception {
		gamFcltyCtrtMngDao.deleteFcltyCtrtMngSubctrt(gamFcltyCtrtMngSubctrtVO);
	}

	@Override
	public EgovMap selectFcltyCtrtMngSubctrtPk(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngSubctrtPk(gamFcltyCtrtMngSubctrtVO);
	}

	@Override
	public String selectFcltyCtrtMngSubctrtMaxSeq(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngSubctrtMaxSeq(gamFcltyCtrtMngSubctrtVO);
	}


	@Override
	public List selectFcltyCtrtMngChangeList(GamFcltyCtrtMngChangeVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngChangeList(searchVO);
	}

	@Override
	public void insertFcltyCtrtMngChange(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) throws Exception {
		gamFcltyCtrtMngDao.insertFcltyCtrtMngChange(gamFcltyCtrtMngChangeVO);
	}

	@Override
	public void updateFcltyCtrtMngChange(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) throws Exception {
		gamFcltyCtrtMngDao.updateFcltyCtrtMngChange(gamFcltyCtrtMngChangeVO);
	}

	@Override
	public void deleteFcltyCtrtMngChange(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) throws Exception {
		gamFcltyCtrtMngDao.deleteFcltyCtrtMngChange(gamFcltyCtrtMngChangeVO);
	}

	@Override
	public EgovMap selectFcltyCtrtMngChangePk(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngChangePk(gamFcltyCtrtMngChangeVO);
	}

	@Override
	public String selectFcltyCtrtMngChangeMaxSeq(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngChangeMaxSeq(gamFcltyCtrtMngChangeVO);
	}


	@Override
	public List selectFcltyCtrtMngMoneyPymntList(GamFcltyCtrtMngMoneyPymntVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngMoneyPymntList(searchVO);
	}

	@Override
	public void insertFcltyCtrtMngMoneyPymnt(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) throws Exception {
		gamFcltyCtrtMngDao.insertFcltyCtrtMngMoneyPymnt(gamFcltyCtrtMngMoneyPymntVO);
	}

	@Override
	public void updateFcltyCtrtMngMoneyPymnt(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) throws Exception {
		gamFcltyCtrtMngDao.updateFcltyCtrtMngMoneyPymnt(gamFcltyCtrtMngMoneyPymntVO);
	}

	@Override
	public void deleteFcltyCtrtMngMoneyPymnt(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) throws Exception {
		gamFcltyCtrtMngDao.deleteFcltyCtrtMngMoneyPymnt(gamFcltyCtrtMngMoneyPymntVO);
	}

	@Override
	public EgovMap selectFcltyCtrtMngMoneyPymntPk(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngMoneyPymntPk(gamFcltyCtrtMngMoneyPymntVO);
	}

	@Override
	public String selectFcltyCtrtMngMoneyPymntMaxSeq(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngMoneyPymntMaxSeq(gamFcltyCtrtMngMoneyPymntVO);
	}


	@Override
	public List selectFcltyCtrtMngFulfillCaryFwdList(GamFcltyCtrtMngFulfillCaryFwdVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngFulfillCaryFwdList(searchVO);
	}

	@Override
	public void insertFcltyCtrtMngFulfillCaryFwd(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) throws Exception {
		gamFcltyCtrtMngDao.insertFcltyCtrtMngFulfillCaryFwd(gamFcltyCtrtMngFulfillCaryFwdVO);
	}

	@Override
	public void updateFcltyCtrtMngFulfillCaryFwd(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) throws Exception {
		gamFcltyCtrtMngDao.updateFcltyCtrtMngFulfillCaryFwd(gamFcltyCtrtMngFulfillCaryFwdVO);
	}

	@Override
	public void deleteFcltyCtrtMngFulfillCaryFwd(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) throws Exception {
		gamFcltyCtrtMngDao.deleteFcltyCtrtMngFulfillCaryFwd(gamFcltyCtrtMngFulfillCaryFwdVO);
	}

	@Override
	public EgovMap selectFcltyCtrtMngFulfillCaryFwdPk(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngFulfillCaryFwdPk(gamFcltyCtrtMngFulfillCaryFwdVO);
	}

	@Override
	public String selectFcltyCtrtMngFulfillCaryFwdMaxSeq(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngFulfillCaryFwdMaxSeq(gamFcltyCtrtMngFulfillCaryFwdVO);
	}


	@Override
	public List selectFcltyCtrtMngScsbidInfoList(GamFcltyCtrtMngScsbidInfoVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngScsbidInfoList(searchVO);
	}

	@Override
	public void insertFcltyCtrtMngScsbidInfo(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) throws Exception {
		gamFcltyCtrtMngDao.insertFcltyCtrtMngScsbidInfo(gamFcltyCtrtMngScsbidInfoVO);
	}

	@Override
	public void updateFcltyCtrtMngScsbidInfo(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) throws Exception {
		gamFcltyCtrtMngDao.updateFcltyCtrtMngScsbidInfo(gamFcltyCtrtMngScsbidInfoVO);
	}

	@Override
	public void deleteFcltyCtrtMngScsbidInfo(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) throws Exception {
		gamFcltyCtrtMngDao.deleteFcltyCtrtMngScsbidInfo(gamFcltyCtrtMngScsbidInfoVO);
	}

	@Override
	public EgovMap selectFcltyCtrtMngScsbidInfoPk(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngScsbidInfoPk(gamFcltyCtrtMngScsbidInfoVO);
	}

	@Override
	public String selectFcltyCtrtMngScsbidInfoMaxSeq(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngScsbidInfoMaxSeq(gamFcltyCtrtMngScsbidInfoVO);
	}

	@Override
	public EgovMap selectEntrpsInfo(Map searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectEntrpsInfo(searchVO);
	}

}
