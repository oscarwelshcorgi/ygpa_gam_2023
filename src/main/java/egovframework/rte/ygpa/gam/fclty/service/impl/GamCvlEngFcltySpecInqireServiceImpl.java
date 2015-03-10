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
import egovframework.rte.ygpa.gam.fclty.service.GamCvlEngFcltySpecInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamCvlEngFcltySpecInqireVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;

/**
 * 
 * @author LFIT
 * @since 2015. 3. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 9.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamCvlEngFcltySpecInqireService")
public class GamCvlEngFcltySpecInqireServiceImpl extends AbstractServiceImpl implements GamCvlEngFcltySpecInqireService {

	@Resource(name="gamCvlEngFcltySpecInqireDao")
	private GamCvlEngFcltySpecInqireDao gamCvlEngFcltySpecInqireDao;

	@Override
	public List selectCvlEngFcltySpecInqireList(GamCvlEngFcltySpecInqireVO searchVO) throws Exception {
		return gamCvlEngFcltySpecInqireDao.selectCvlEngFcltySpecInqireList(searchVO);
	}

	@Override
	public GamCvlEngFcltySpecInqireVO selectCvlEngFcltySpecInqireListSum(GamCvlEngFcltySpecInqireVO searchVO) throws Exception {
		return gamCvlEngFcltySpecInqireDao.selectCvlEngFcltySpecInqireListSum(searchVO);
	}

	@Override
	public List selectCvlEngFcltySpecInqireAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamCvlEngFcltySpecInqireDao.selectCvlEngFcltySpecInqireAtchFileDirList(gamAtchFileDirMngVO);
	}

	public List selectCvlEngFcltySpecInqireFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {
		return gamCvlEngFcltySpecInqireDao.selectCvlEngFcltySpecInqireFcltsAtchFileList(searchVO);
	}

	@Override
	public EgovMap selectCvlEngFcltySpecInqireAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamCvlEngFcltySpecInqireDao.selectCvlEngFcltySpecInqireAtchFileDirPk(gamAtchFileDirMngVO);
	}

	@Override
	public EgovMap selectCvlEngFcltySpecInqireFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamCvlEngFcltySpecInqireDao.selectCvlEngFcltySpecInqireFcltsAtchFilePk(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectFcltsMngGroupNm(Map searchVO) throws Exception {
		return gamCvlEngFcltySpecInqireDao.selectFcltsMngGroupNm(searchVO);
	}
	
}
