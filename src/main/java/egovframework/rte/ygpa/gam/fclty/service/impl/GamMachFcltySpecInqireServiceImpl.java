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
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySpecInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySpecInqireVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySpecMngVO;

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

@Service("gamMachFcltySpecInqireService")
public class GamMachFcltySpecInqireServiceImpl extends AbstractServiceImpl implements GamMachFcltySpecInqireService {

	@Resource(name="gamMachFcltySpecInqireDao")
	private GamMachFcltySpecInqireDao gamMachFcltySpecInqireDao;

	@Override
	public List selectMachFcltySpecInqireList(GamMachFcltySpecInqireVO searchVO) throws Exception {
		return gamMachFcltySpecInqireDao.selectMachFcltySpecInqireList(searchVO);
	}

	@Override
	public GamMachFcltySpecInqireVO selectMachFcltySpecInqireListSum(GamMachFcltySpecInqireVO searchVO) throws Exception {
		return gamMachFcltySpecInqireDao.selectMachFcltySpecInqireListSum(searchVO);
	}

	@Override
	public List selectMachFcltySpecInqireCvlEngStatusList(GamMachFcltySpecInqireVO searchVO) throws Exception {
		return gamMachFcltySpecInqireDao.selectMachFcltySpecInqireCvlEngStatusList(searchVO);
	}

	@Override
	public List gamSelectMachFcltySpecInqireAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamMachFcltySpecInqireDao.gamSelectMachFcltySpecInqireAtchFileDirList(gamAtchFileDirMngVO);
	}

	@Override
	public List selectMachFcltySpecInqireFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {
		return gamMachFcltySpecInqireDao.selectMachFcltySpecInqireFcltsAtchFileList(searchVO);
	}

	@Override
	public EgovMap selectMachFcltySpecInqireAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamMachFcltySpecInqireDao.selectMachFcltySpecInqireAtchFileDirPk(gamAtchFileDirMngVO);
	}

	@Override
	public EgovMap selectMachFcltySpecInqireFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamMachFcltySpecInqireDao.selectMachFcltySpecInqireFcltsAtchFilePk(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectFcltsMngGroupNm(Map searchVO) throws Exception {
		return gamMachFcltySpecInqireDao.selectFcltsMngGroupNm(searchVO);
	}
	
}
