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
import egovframework.rte.ygpa.gam.fclty.service.GamElectyFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamElectyFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 3. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 3.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamElectyFcltySpecMngService")
public class GamElectyFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamElectyFcltySpecMngService {

	@Resource(name="gamElectyFcltySpecMngDao")
	private GamElectyFcltySpecMngDao gamElectyFcltySpecMngDao;

	@Override
	public List selectElectyFcltySpecMngList(GamElectyFcltySpecMngVO searchVO) throws Exception {
		return gamElectyFcltySpecMngDao.selectElectyFcltySpecMngList(searchVO);
	}

	@Override
	public void insertElectyFcltySpecMng(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) throws Exception {
		gamElectyFcltySpecMngDao.insertElectyFcltySpecMngGisPrtFcltyCd(gamElectyFcltySpecMngVO);
		gamElectyFcltySpecMngDao.insertElectyFcltySpecMng(gamElectyFcltySpecMngVO);
	}

	@Override
	public void updateElectyFcltySpecMng(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) throws Exception {
		String strFcltySpecExistYn = (String)gamElectyFcltySpecMngVO.getFcltySpecExistYn();
		gamElectyFcltySpecMngDao.updateElectyFcltySpecMngGisPrtFcltyCd(gamElectyFcltySpecMngVO);
		if ("Y".equals(strFcltySpecExistYn)) {
			gamElectyFcltySpecMngDao.updateElectyFcltySpecMng(gamElectyFcltySpecMngVO);
		} else {
			gamElectyFcltySpecMngDao.insertElectyFcltySpecMng(gamElectyFcltySpecMngVO);
		}
	}

	@Override
	public void deleteElectyFcltySpecMng(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) throws Exception {
		gamElectyFcltySpecMngDao.deleteElectyFcltySpecMng(gamElectyFcltySpecMngVO);
		gamElectyFcltySpecMngDao.deleteElectyFcltySpecMngGisPrtFcltyCd(gamElectyFcltySpecMngVO);
	}

	@Override
	public EgovMap selectElectyFcltySpecMngPk(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) throws Exception {
		return gamElectyFcltySpecMngDao.selectElectyFcltySpecMngPk(gamElectyFcltySpecMngVO);
	}

	@Override
	public GamElectyFcltySpecMngVO selectElectyFcltySpecMngListSum(GamElectyFcltySpecMngVO searchVO) throws Exception {
		return gamElectyFcltySpecMngDao.selectElectyFcltySpecMngListSum(searchVO);
	}

	@Override
	public String selectElectyFcltySpecMngMaxGisPrtFcltySeq(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) throws Exception {
		return gamElectyFcltySpecMngDao.selectElectyFcltySpecMngMaxGisPrtFcltySeq(gamElectyFcltySpecMngVO);
	}


	@Override
	public String selectFcltsMngGroupNm(Map searchVO) throws Exception {
		return gamElectyFcltySpecMngDao.selectFcltsMngGroupNm(searchVO);
	}

	@Override
	public String selectEntrpsNm(Map searchVO) throws Exception {
		return gamElectyFcltySpecMngDao.selectEntrpsNm(searchVO);
	}

	@Override
	public String selectFcltsClCdNm(Map searchVO) throws Exception {
		return gamElectyFcltySpecMngDao.selectFcltsClCdNm(searchVO);
	}

	@Override
	public List selectFcltsClCdList() throws Exception {
		return gamElectyFcltySpecMngDao.selectFcltsClCdList();
	}

	@Override
	public String selectGisPrtFcltyNm(Map searchVO) throws Exception {
		return gamElectyFcltySpecMngDao.selectGisPrtFcltyNm(searchVO);
	}


	@Override
	public List selectElectyFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamElectyFcltySpecMngDao.selectElectyFcltySpecMngAtchFileDirList(gamAtchFileDirMngVO);
	}

	@Override
	public EgovMap selectElectyFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamElectyFcltySpecMngDao.selectElectyFcltySpecMngAtchFileDirPk(gamAtchFileDirMngVO);
	}

	@Override
	public void insertElectyFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		String strDepthSort = (String)gamAtchFileDirMngVO.getDepthSort();
		gamElectyFcltySpecMngDao.insertElectyFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);
		if (!"1".equals(strDepthSort)) {
			gamElectyFcltySpecMngDao.updateElectyFcltySpecMngInsertAtchFileDirLeafYn(gamAtchFileDirMngVO);
		}
	}

	@Override
	public void updateElectyFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		gamElectyFcltySpecMngDao.updateElectyFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);
	}

	@Override
	public void deleteElectyFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		String strDepthSort = (String)gamAtchFileDirMngVO.getDepthSort();
		gamElectyFcltySpecMngDao.deleteElectyFcltySpecMngAtchFileDirLowerData(gamAtchFileDirMngVO);
		gamElectyFcltySpecMngDao.deleteElectyFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);
		if (!"1".equals(strDepthSort)) {
			gamElectyFcltySpecMngDao.updateElectyFcltySpecMngDeleteAtchFileDirLeafYn(gamAtchFileDirMngVO);
		}
		GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO = new GamFcltsAtchFileMngVO();
		gamFcltsAtchFileMngVO.setAtchFileDirNo(gamAtchFileDirMngVO.getDirNo());
		gamElectyFcltySpecMngDao.deleteElectyFcltySpecMngAllFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectElectyFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamElectyFcltySpecMngDao.selectElectyFcltySpecMngAtchFileDirNewNo(gamAtchFileDirMngVO);
	}

	@Override
	public List selectElectyFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamElectyFcltySpecMngDao.selectElectyFcltySpecMngAtchFileDirLowerDataCnt(gamAtchFileDirMngVO);
	}


	@Override
	public List selectElectyFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {
		return gamElectyFcltySpecMngDao.selectElectyFcltySpecMngFcltsAtchFileList(searchVO);
	}

	@Override
	public void insertElectyFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamElectyFcltySpecMngDao.insertElectyFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public void updateElectyFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamElectyFcltySpecMngDao.updateElectyFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public void deleteElectyFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamElectyFcltySpecMngDao.deleteElectyFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public EgovMap selectElectyFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamElectyFcltySpecMngDao.selectElectyFcltySpecMngFcltsAtchFilePk(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectElectyFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamElectyFcltySpecMngDao.selectElectyFcltySpecMngFcltsAtchFileNewNo(gamFcltsAtchFileMngVO);
	}

}
