/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsMngRegistMngMntnRprDtlsVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsMngRegistMngQcMngDtlsVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsMngRegistMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsMngRegistMngVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 10.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltsMngRegistMngService")
public class GamFcltsMngRegistMngServiceImpl extends AbstractServiceImpl implements GamFcltsMngRegistMngService {

	@Resource(name="gamFcltsMngRegistMngDao")
	private GamFcltsMngRegistMngDao gamFcltsMngRegistMngDao;

	@Override
	public List selectFcltsMngRegistMngList(GamFcltsMngRegistMngVO searchVO) throws Exception {
		return gamFcltsMngRegistMngDao.selectFcltsMngRegistMngList(searchVO);
	}

	@Override
	public void insertFcltsMngRegistMng(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) throws Exception {
		gamFcltsMngRegistMngDao.insertFcltsMngRegistMng(gamFcltsMngRegistMngVO);
	}

	@Override
	public void updateFcltsMngRegistMng(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) throws Exception {
		gamFcltsMngRegistMngDao.updateFcltsMngRegistMng(gamFcltsMngRegistMngVO);
	}

	@Override
	public void deleteFcltsMngRegistMng(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) throws Exception {
		gamFcltsMngRegistMngDao.deleteFcltsMngRegistMng(gamFcltsMngRegistMngVO);
	}

	@Override
	public GamFcltsMngRegistMngVO selectFcltsMngRegistMngListSum(GamFcltsMngRegistMngVO searchVO) throws Exception {
		return gamFcltsMngRegistMngDao.selectFcltsMngRegistMngListSum(searchVO);
	}

	@Override
	public EgovMap selectFcltsMngRegistMngPk(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) throws Exception {
		return gamFcltsMngRegistMngDao.selectFcltsMngRegistMngPk(gamFcltsMngRegistMngVO);
	}

	@Override
	public String selectFcltsMngGroupNm(Map searchVO) throws Exception {
		return gamFcltsMngRegistMngDao.selectFcltsMngGroupNm(searchVO);
	}

	@Override
	public List selectFcltsMngRegistMngQcMngDtlsPlanList(GamFcltsMngRegistMngQcMngDtlsVO searchVO) throws Exception {
		return gamFcltsMngRegistMngDao.selectFcltsMngRegistMngQcMngDtlsPlanList(searchVO);
	}

	@Override
	public List selectFcltsMngRegistMngQcMngDtlsHistList(GamFcltsMngRegistMngQcMngDtlsVO searchVO) throws Exception {
		return gamFcltsMngRegistMngDao.selectFcltsMngRegistMngQcMngDtlsHistList(searchVO);
	}

	@Override
	public List selectFcltsMngRegistMngMntnRprDtlsPlanList(GamFcltsMngRegistMngMntnRprDtlsVO searchVO) throws Exception {
		return gamFcltsMngRegistMngDao.selectFcltsMngRegistMngMntnRprDtlsPlanList(searchVO);
	}

	@Override
	public List selectFcltsMngRegistMngMntnRprDtlsHistList(GamFcltsMngRegistMngMntnRprDtlsVO searchVO) throws Exception {
		return gamFcltsMngRegistMngDao.selectFcltsMngRegistMngMntnRprDtlsHistList(searchVO);
	}

	/**
	 * 시설물관리대장 한글 문서 다운로드 - 김종민 추가 작업 2016.03.31
	 */
	@SuppressWarnings("unchecked")
	public String downloadHwpFcltsMngRegistMng(String fcltsNo, String fcltsMngGroupNo, String fcltsJobSe) throws Exception {
		String result = null;
		GamFcltsMngRegistMngVO mngRegistVO = new GamFcltsMngRegistMngVO();
		mngRegistVO.setFcltsNo(fcltsNo);
		EgovMap fcltsData = gamFcltsMngRegistMngDao.selectFcltsMngRegistMngPkHwp(mngRegistVO);
		
		GamFcltsMngRegistMngQcMngDtlsVO qcVO = new GamFcltsMngRegistMngQcMngDtlsVO();
		qcVO.setFcltsMngGroupNo(fcltsMngGroupNo);
		qcVO.setFcltsJobSe(fcltsJobSe);
		List<EgovMap> qcHistList = gamFcltsMngRegistMngDao.selectFcltsMngRegistMngQcMngDtlsHistListHwp(qcVO);
		
		GamFcltsMngRegistMngMntnRprDtlsVO mntnVO = new GamFcltsMngRegistMngMntnRprDtlsVO();
		mntnVO.setFcltsMngGroupNo(fcltsMngGroupNo);
		mntnVO.setFcltsJobSe(fcltsJobSe);
		List<EgovMap> mntnHistList = gamFcltsMngRegistMngDao.selectFcltsMngRegistMngMntnRprDtlsHistListHwp(mntnVO);
		
		GamFcltsMngRegistMngHwpReport report = new GamFcltsMngRegistMngHwpReport(fcltsData, qcHistList, mntnHistList);
		result = report.getHwpReport();
		return result;
	}
}
