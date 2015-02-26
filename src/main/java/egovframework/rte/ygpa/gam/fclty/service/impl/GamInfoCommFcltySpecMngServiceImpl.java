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
import egovframework.rte.ygpa.gam.fclty.service.GamInfoCommFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamInfoCommFcltySpecMngVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 17.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 17.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamInfoCommFcltySpecMngService")
public class GamInfoCommFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamInfoCommFcltySpecMngService {

	@Resource(name="gamInfoCommFcltySpecMngDao")
	private GamInfoCommFcltySpecMngDao gamInfoCommFcltySpecMngDao;

	@Override
	public List selectInfoCommFcltySpecMngList(GamInfoCommFcltySpecMngVO searchVO) throws Exception {
		return gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngList(searchVO);
	}

	@Override
	public void insertInfoCommFcltySpecMng(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) throws Exception {
		gamInfoCommFcltySpecMngDao.insertInfoCommFcltySpecMngGisPrtFcltyCd(gamInfoCommFcltySpecMngVO);
		gamInfoCommFcltySpecMngDao.insertInfoCommFcltySpecMng(gamInfoCommFcltySpecMngVO);
	}

	@Override
	public void updateInfoCommFcltySpecMng(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) throws Exception {
		String strFcltySpecExistYn = (String)gamInfoCommFcltySpecMngVO.getFcltySpecExistYn();
		gamInfoCommFcltySpecMngDao.updateInfoCommFcltySpecMngGisPrtFcltyCd(gamInfoCommFcltySpecMngVO);
		if ("Y".equals(strFcltySpecExistYn)) {
			gamInfoCommFcltySpecMngDao.updateInfoCommFcltySpecMng(gamInfoCommFcltySpecMngVO);
		} else {
			gamInfoCommFcltySpecMngDao.insertInfoCommFcltySpecMng(gamInfoCommFcltySpecMngVO);
		}
	}

	@Override
	public void deleteInfoCommFcltySpecMng(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) throws Exception {
		gamInfoCommFcltySpecMngDao.deleteInfoCommFcltySpecMng(gamInfoCommFcltySpecMngVO);
		gamInfoCommFcltySpecMngDao.deleteInfoCommFcltySpecMngGisPrtFcltyCd(gamInfoCommFcltySpecMngVO);
	}

	@Override
	public EgovMap selectInfoCommFcltySpecMngPk(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) throws Exception {
		return gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngPk(gamInfoCommFcltySpecMngVO);
	}

	@Override
	public GamInfoCommFcltySpecMngVO selectInfoCommFcltySpecMngListSum(GamInfoCommFcltySpecMngVO searchVO) throws Exception {
		return gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngListSum(searchVO);
	}

	@Override
	public String selectInfoCommFcltySpecMngMaxGisPrtFcltySeq(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) throws Exception {
		return gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngMaxGisPrtFcltySeq(gamInfoCommFcltySpecMngVO);
	}


	@Override
	public String selectFcltsMngGroupNm(Map searchVO) throws Exception {
		return gamInfoCommFcltySpecMngDao.selectFcltsMngGroupNm(searchVO);
	}

	@Override
	public String selectEntrpsNm(Map searchVO) throws Exception {
		return gamInfoCommFcltySpecMngDao.selectEntrpsNm(searchVO);
	}

	@Override
	public String selectFcltsClCdNm(Map searchVO) throws Exception {
		return gamInfoCommFcltySpecMngDao.selectFcltsClCdNm(searchVO);
	}

	@Override
	public List selectFcltsClCdList() throws Exception {
		return gamInfoCommFcltySpecMngDao.selectFcltsClCdList();
	}

	@Override
	public String selectGisPrtFcltyNm(Map searchVO) throws Exception {
		return gamInfoCommFcltySpecMngDao.selectGisPrtFcltyNm(searchVO);
	}


	@Override
	public List selectInfoCommFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngAtchFileDirList(gamAtchFileDirMngVO);
	}

	@Override
	public EgovMap selectInfoCommFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngAtchFileDirPk(gamAtchFileDirMngVO);
	}

	@Override
	public void insertInfoCommFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		String strDepthSort = (String)gamAtchFileDirMngVO.getDepthSort();
		gamInfoCommFcltySpecMngDao.insertInfoCommFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);
		if (!"1".equals(strDepthSort)) {
			gamInfoCommFcltySpecMngDao.updateInfoCommFcltySpecMngInsertAtchFileDirLeafYn(gamAtchFileDirMngVO);
		}
	}

	@Override
	public void updateInfoCommFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		gamInfoCommFcltySpecMngDao.updateInfoCommFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);
	}

	@Override
	public void deleteInfoCommFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		String strDepthSort = (String)gamAtchFileDirMngVO.getDepthSort();
		gamInfoCommFcltySpecMngDao.deleteInfoCommFcltySpecMngAtchFileDirLowerData(gamAtchFileDirMngVO);
		gamInfoCommFcltySpecMngDao.deleteInfoCommFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);
		if (!"1".equals(strDepthSort)) {
			gamInfoCommFcltySpecMngDao.updateInfoCommFcltySpecMngDeleteAtchFileDirLeafYn(gamAtchFileDirMngVO);
		}
		GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO = new GamFcltsAtchFileMngVO();
		gamFcltsAtchFileMngVO.setAtchFileDirNo(gamAtchFileDirMngVO.getDirNo());
		gamInfoCommFcltySpecMngDao.deleteInfoCommFcltySpecMngAllFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectInfoCommFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngAtchFileDirNewNo(gamAtchFileDirMngVO);
	}

	@Override
	public List selectInfoCommFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngAtchFileDirLowerDataCnt(gamAtchFileDirMngVO);
	}


	@Override
	public List selectInfoCommFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {
		return gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngFcltsAtchFileList(searchVO);
	}

	@Override
	public void insertInfoCommFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamInfoCommFcltySpecMngDao.insertInfoCommFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public void updateInfoCommFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamInfoCommFcltySpecMngDao.updateInfoCommFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public void deleteInfoCommFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamInfoCommFcltySpecMngDao.deleteInfoCommFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public void deleteInfoCommFcltySpecMngFcltsAtchFileMulti(Map<?, ?> deleteVO) throws Exception {
		gamInfoCommFcltySpecMngDao.deleteInfoCommFcltySpecMngFcltsAtchFileMulti(deleteVO);
	}

	@Override
	public EgovMap selectInfoCommFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngFcltsAtchFilePk(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectInfoCommFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngFcltsAtchFileNewNo(gamFcltsAtchFileMngVO);
	}

}
