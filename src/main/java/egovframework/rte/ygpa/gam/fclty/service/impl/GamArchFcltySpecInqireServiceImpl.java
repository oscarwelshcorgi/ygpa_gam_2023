/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamArchFcltySpecInqireService;
import egovframework.rte.ygpa.gam.fclty.service.GamArchFcltySpecInqireVO;
import egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;

/**
 * 
 * @author LFIT
 * @since 2015. 3. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 5.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamArchFcltySpecInqireService")
public class GamArchFcltySpecInqireServiceImpl implements
		GamArchFcltySpecInqireService {

	@Resource(name="gamArchFcltySpecInqireDao")
	private GamArchFcltySpecInqireDao gamArchFcltySpecInqireDao;
	
	@Override
	public List selectArchFcltySpecInqireList(GamArchFcltySpecInqireVO searchVO) throws Exception {
		return gamArchFcltySpecInqireDao.selectArchFcltySpecInqireList(searchVO);
	}

	@Override
	public GamArchFcltySpecInqireVO selectArchFcltySpecInqireListSum(GamArchFcltySpecInqireVO searchVO) throws Exception {
		return gamArchFcltySpecInqireDao.selectArchFcltySpecInqireListSum(searchVO);
	}

	@Override
	public List selectArchFcltySpecInqireAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamArchFcltySpecInqireDao.selectArchFcltySpecInqireAtchFileDirList(gamAtchFileDirMngVO);
	}
	
	@Override
	public List selectArchFcltySpecInqireFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {
		return gamArchFcltySpecInqireDao.selectArchFcltySpecInqireFcltsAtchFileList(searchVO);
	}

	@Override
	public EgovMap selectArchFcltySpecInqireAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamArchFcltySpecInqireDao.selectArchFcltySpecInqireAtchFileDirPk(gamAtchFileDirMngVO);
	}

	@Override
	public EgovMap selectArchFcltySpecInqireFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamArchFcltySpecInqireDao.selectArchFcltySpecInqireFcltsAtchFilePk(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectFcltsMngGroupNm(Map searchVO) {
		return gamArchFcltySpecInqireDao.selectFcltsMngGroupNm(searchVO);
	}

}
