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
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamInfoCommFcltySpecInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamInfoCommFcltySpecInqireVO;

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

@Service("gamInfoCommFcltySpecInqireService")
public class GamInfoCommFcltySpecInqireServiceImpl extends AbstractServiceImpl implements GamInfoCommFcltySpecInqireService {

	@Resource(name="gamInfoCommFcltySpecInqireDao")
	private GamInfoCommFcltySpecInqireDao gamInfoCommFcltySpecInqireDao;
	
	@Override
	public List selectInfoCommFcltySpecInqireList(GamInfoCommFcltySpecInqireVO searchVO) throws Exception {
		return gamInfoCommFcltySpecInqireDao.selectInfoCommFcltySpecInqireList(searchVO);
	}

	@Override
	public GamInfoCommFcltySpecInqireVO selectInfoCommFcltySpecInqireListSum(GamInfoCommFcltySpecInqireVO searchVO) throws Exception {
		return gamInfoCommFcltySpecInqireDao.selectInfoCommFcltySpecInqireListSum(searchVO);
	}

	@Override
	public List selectInfoCommFcltySpecInqireAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamInfoCommFcltySpecInqireDao.selectInfoCommFcltySpecInqireAtchFileDirList(gamAtchFileDirMngVO);
	}

	@Override
	public List selectInfoCommFcltySpecInqireFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {
		return gamInfoCommFcltySpecInqireDao.selectInfoCommFcltySpecInqireFcltsAtchFileList(searchVO);
	}

	@Override
	public EgovMap selectInfoCommFcltySpecInqireAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamInfoCommFcltySpecInqireDao.selectInfoCommFcltySpecInqireAtchFileDirPk(gamAtchFileDirMngVO);
	}

	@Override
	public EgovMap selectInfoCommFcltySpecInqireFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamInfoCommFcltySpecInqireDao.selectInfoCommFcltySpecInqireFcltsAtchFilePk(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectFcltsMngGroupNm(Map searchVO) throws Exception {
		return gamInfoCommFcltySpecInqireDao.selectFcltsMngGroupNm(searchVO);
	}

}
