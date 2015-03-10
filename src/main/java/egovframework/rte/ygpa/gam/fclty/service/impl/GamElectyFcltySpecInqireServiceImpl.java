/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamElectyFcltySpecInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamElectyFcltySpecInqireVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;

/**
 * 
 * @author LFIT
 * @since 2015. 3. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 10.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamElectyFcltySpecInqireService")
public class GamElectyFcltySpecInqireServiceImpl extends AbstractServiceImpl implements GamElectyFcltySpecInqireService {

	@Resource(name="gamElectyFcltySpecInqireDao")
	private GamElectyFcltySpecInqireDao gamElectyFcltySpecInqireDao;

	@Override
	public List selectElectyFcltySpecInqireList(GamElectyFcltySpecInqireVO searchVO) throws Exception {
		return gamElectyFcltySpecInqireDao.selectElectyFcltySpecInqireList(searchVO);
	}

	@Override
	public GamElectyFcltySpecInqireVO selectElectyFcltySpecInqireListSum(GamElectyFcltySpecInqireVO searchVO) throws Exception {
		return gamElectyFcltySpecInqireDao.selectElectyFcltySpecInqireListSum(searchVO);
	}

	@Override
	public List selectElectyFcltySpecInqireAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamElectyFcltySpecInqireDao.selectElectyFcltySpecInqireAtchFileDirList(gamAtchFileDirMngVO);
	}

	@Override
	public List selectElectyFcltySpecInqireFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {
		return gamElectyFcltySpecInqireDao.selectElectyFcltySpecInqireFcltsAtchFileList(searchVO);
	}

	@Override
	public EgovMap selectElectyFcltySpecInqireAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamElectyFcltySpecInqireDao.selectElectyFcltySpecInqireAtchFileDirPk(gamAtchFileDirMngVO);
	}

	@Override
	public EgovMap selectElectyFcltySpecInqireFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamElectyFcltySpecInqireDao.selectElectyFcltySpecInqireFcltsAtchFilePk(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectFcltsMngGroupNm(Map searchVO) throws Exception {
		return gamElectyFcltySpecInqireDao.selectFcltsMngGroupNm(searchVO);
	}
	
}
