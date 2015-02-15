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
import egovframework.rte.ygpa.gam.fclty.service.GamArchFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamArchFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 1. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 1. 15.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamArchFcltySpecMngService")
public class GamArchFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamArchFcltySpecMngService {

	@Resource(name="gamArchFcltySpecMngDao")
	private GamArchFcltySpecMngDao gamArchFcltySpecMngDao;

	@Override
	public List selectArchFcltySpecMngList(GamArchFcltySpecMngVO searchVO) throws Exception {
		return gamArchFcltySpecMngDao.selectArchFcltySpecMngList(searchVO);
	}

	@Override
	public void insertArchFcltySpecMng(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) throws Exception {
		gamArchFcltySpecMngDao.insertArchFcltySpecMngGisPrtFcltyCd(gamArchFcltySpecMngVO);
		gamArchFcltySpecMngDao.insertArchFcltySpecMng(gamArchFcltySpecMngVO);
	}

	@Override
	public void updateArchFcltySpecMng(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) throws Exception {
		String strFcltySpecExistYn = (String)gamArchFcltySpecMngVO.getFcltySpecExistYn();
		gamArchFcltySpecMngDao.updateArchFcltySpecMngGisPrtFcltyCd(gamArchFcltySpecMngVO);
		if ("Y".equals(strFcltySpecExistYn)) {
			gamArchFcltySpecMngDao.updateArchFcltySpecMng(gamArchFcltySpecMngVO);
		} else {
			gamArchFcltySpecMngDao.insertArchFcltySpecMng(gamArchFcltySpecMngVO);
		}
	}

	@Override
	public void deleteArchFcltySpecMng(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) throws Exception {
		gamArchFcltySpecMngDao.deleteArchFcltySpecMng(gamArchFcltySpecMngVO);
		gamArchFcltySpecMngDao.deleteArchFcltySpecMngGisPrtFcltyCd(gamArchFcltySpecMngVO);
	}

	@Override
	public EgovMap selectArchFcltySpecMngPk(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) throws Exception {
		return gamArchFcltySpecMngDao.selectArchFcltySpecMngPk(gamArchFcltySpecMngVO);
	}

	@Override
	public GamArchFcltySpecMngVO selectArchFcltySpecMngListSum(GamArchFcltySpecMngVO searchVO) throws Exception {
		return gamArchFcltySpecMngDao.selectArchFcltySpecMngListSum(searchVO);
	}

	@Override
	public String selectArchFcltySpecMngMaxGisPrtFcltySeq(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) throws Exception {
		return gamArchFcltySpecMngDao.selectArchFcltySpecMngMaxGisPrtFcltySeq(gamArchFcltySpecMngVO);
	}


	@Override
	public String selectFcltsMngGroupNm(Map searchVO) throws Exception {
		return gamArchFcltySpecMngDao.selectFcltsMngGroupNm(searchVO);
	}

	@Override
	public String selectEntrpsNm(Map searchVO) throws Exception {
		return gamArchFcltySpecMngDao.selectEntrpsNm(searchVO);
	}

	@Override
	public String selectFcltsClCdNm(Map searchVO) throws Exception {
		return gamArchFcltySpecMngDao.selectFcltsClCdNm(searchVO);
	}

	@Override
	public List selectFcltsClCdList() throws Exception {
		return gamArchFcltySpecMngDao.selectFcltsClCdList();
	}


	@Override
	public List selectArchFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamArchFcltySpecMngDao.selectArchFcltySpecMngAtchFileDirList(gamAtchFileDirMngVO);
	}

	@Override
	public EgovMap selectArchFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamArchFcltySpecMngDao.selectArchFcltySpecMngAtchFileDirPk(gamAtchFileDirMngVO);
	}

	@Override
	public void insertArchFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		String strDepthSort = (String)gamAtchFileDirMngVO.getDepthSort();
		gamArchFcltySpecMngDao.insertArchFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);
		if (!"1".equals(strDepthSort)) {
			gamArchFcltySpecMngDao.updateArchFcltySpecMngInsertAtchFileDirLeafYn(gamAtchFileDirMngVO);
		}
	}

	@Override
	public void updateArchFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		gamArchFcltySpecMngDao.updateArchFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);
	}

	@Override
	public void deleteArchFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		String strDepthSort = (String)gamAtchFileDirMngVO.getDepthSort();
		gamArchFcltySpecMngDao.deleteArchFcltySpecMngAtchFileDirLowerData(gamAtchFileDirMngVO);
		gamArchFcltySpecMngDao.deleteArchFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);
		if (!"1".equals(strDepthSort)) {
			gamArchFcltySpecMngDao.updateArchFcltySpecMngDeleteAtchFileDirLeafYn(gamAtchFileDirMngVO);
		}
		GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO = new GamFcltsAtchFileMngVO();
		gamFcltsAtchFileMngVO.setAtchFileDirNo(gamAtchFileDirMngVO.getDirNo());
		gamArchFcltySpecMngDao.deleteArchFcltySpecMngAllFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectArchFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamArchFcltySpecMngDao.selectArchFcltySpecMngAtchFileDirNewNo(gamAtchFileDirMngVO);
	}

	@Override
	public List selectArchFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamArchFcltySpecMngDao.selectArchFcltySpecMngAtchFileDirLowerDataCnt(gamAtchFileDirMngVO);
	}


	@Override
	public List selectArchFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {
		return gamArchFcltySpecMngDao.selectArchFcltySpecMngFcltsAtchFileList(searchVO);
	}

	@Override
	public void insertArchFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamArchFcltySpecMngDao.insertArchFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public void updateArchFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamArchFcltySpecMngDao.updateArchFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public void deleteArchFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamArchFcltySpecMngDao.deleteArchFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public EgovMap selectArchFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamArchFcltySpecMngDao.selectArchFcltySpecMngFcltsAtchFilePk(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectArchFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamArchFcltySpecMngDao.selectArchFcltySpecMngFcltsAtchFileNewNo(gamFcltsAtchFileMngVO);
	}

}
