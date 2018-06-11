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
import egovframework.rte.ygpa.gam.fclty.service.GamCvlEngFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamCvlEngFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMntnRprDtlsVO;

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

@Service("gamCvlEngFcltySpecMngService")
public class GamCvlEngFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamCvlEngFcltySpecMngService {

	@Resource(name="gamCvlEngFcltySpecMngDao")
	private GamCvlEngFcltySpecMngDao gamCvlEngFcltySpecMngDao;

	@Override
	public List selectCvlEngFcltySpecMngList(GamCvlEngFcltySpecMngVO searchVO) throws Exception {
		return gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngList(searchVO);
	}

	@Override
	public void insertCvlEngFcltySpecMng(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) throws Exception {
		gamCvlEngFcltySpecMngDao.insertCvlEngFcltySpecMngGisPrtFcltyCd(gamCvlEngFcltySpecMngVO);
		gamCvlEngFcltySpecMngDao.insertCvlEngFcltySpecMng(gamCvlEngFcltySpecMngVO);
	}

	@Override
	public void updateCvlEngFcltySpecMng(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) throws Exception {
		String strFcltySpecExistYn = (String)gamCvlEngFcltySpecMngVO.getFcltySpecExistYn();
		gamCvlEngFcltySpecMngDao.updateCvlEngFcltySpecMngGisPrtFcltyCd(gamCvlEngFcltySpecMngVO);
		if ("Y".equals(strFcltySpecExistYn)) {
			gamCvlEngFcltySpecMngDao.updateCvlEngFcltySpecMng(gamCvlEngFcltySpecMngVO);
		} else {
			gamCvlEngFcltySpecMngDao.insertCvlEngFcltySpecMng(gamCvlEngFcltySpecMngVO);
		}
	}

	@Override
	public void deleteCvlEngFcltySpecMng(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) throws Exception {
		gamCvlEngFcltySpecMngDao.deleteCvlEngFcltySpecMng(gamCvlEngFcltySpecMngVO);
		gamCvlEngFcltySpecMngDao.deleteCvlEngFcltySpecMngGisPrtFcltyCd(gamCvlEngFcltySpecMngVO);
	}

	@Override
	public EgovMap selectCvlEngFcltySpecMngPk(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) throws Exception {
		return gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngPk(gamCvlEngFcltySpecMngVO);
	}

	@Override
	public GamCvlEngFcltySpecMngVO selectCvlEngFcltySpecMngListSum(GamCvlEngFcltySpecMngVO searchVO) throws Exception {
		return gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngListSum(searchVO);
	}

	@Override
	public String selectCvlEngFcltySpecMngMaxGisPrtFcltySeq(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) throws Exception {
		return gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngMaxGisPrtFcltySeq(gamCvlEngFcltySpecMngVO);
	}


	@Override
	public String selectFcltsMngGroupNm(Map searchVO) throws Exception {
		return gamCvlEngFcltySpecMngDao.selectFcltsMngGroupNm(searchVO);
	}

	@Override
	public String selectEntrpsNm(Map searchVO) throws Exception {
		return gamCvlEngFcltySpecMngDao.selectEntrpsNm(searchVO);
	}

	@Override
	public String selectFcltsClCdNm(Map searchVO) throws Exception {
		return gamCvlEngFcltySpecMngDao.selectFcltsClCdNm(searchVO);
	}

	@Override
	public List selectFcltsClCdList() throws Exception {
		return gamCvlEngFcltySpecMngDao.selectFcltsClCdList();
	}


	@Override
	public List selectCvlEngFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngAtchFileDirList(gamAtchFileDirMngVO);
	}

	@Override
	public EgovMap selectCvlEngFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngAtchFileDirPk(gamAtchFileDirMngVO);
	}

	@Override
	public void insertCvlEngFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		String strDepthSort = (String)gamAtchFileDirMngVO.getDepthSort();
		gamCvlEngFcltySpecMngDao.insertCvlEngFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);
		if (!"1".equals(strDepthSort)) {
			gamCvlEngFcltySpecMngDao.updateCvlEngFcltySpecMngInsertAtchFileDirLeafYn(gamAtchFileDirMngVO);
		}
	}

	@Override
	public void updateCvlEngFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		gamCvlEngFcltySpecMngDao.updateCvlEngFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);
	}

	@Override
	public void deleteCvlEngFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		String strDepthSort = (String)gamAtchFileDirMngVO.getDepthSort();
		gamCvlEngFcltySpecMngDao.deleteCvlEngFcltySpecMngAtchFileDirLowerData(gamAtchFileDirMngVO);
		gamCvlEngFcltySpecMngDao.deleteCvlEngFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);
		if (!"1".equals(strDepthSort)) {
			gamCvlEngFcltySpecMngDao.updateCvlEngFcltySpecMngDeleteAtchFileDirLeafYn(gamAtchFileDirMngVO);
		}
		GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO = new GamFcltsAtchFileMngVO();
		gamFcltsAtchFileMngVO.setAtchFileDirNo(gamAtchFileDirMngVO.getDirNo());
		gamCvlEngFcltySpecMngDao.deleteCvlEngFcltySpecMngAllFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectCvlEngFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngAtchFileDirNewNo(gamAtchFileDirMngVO);
	}

	@Override
	public List selectCvlEngFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngAtchFileDirLowerDataCnt(gamAtchFileDirMngVO);
	}


	@Override
	public List selectCvlEngFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {
		return gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngFcltsAtchFileList(searchVO);
	}

	@Override
	public void insertCvlEngFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamCvlEngFcltySpecMngDao.insertCvlEngFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public void updateCvlEngFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamCvlEngFcltySpecMngDao.updateCvlEngFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public void deleteCvlEngFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamCvlEngFcltySpecMngDao.deleteCvlEngFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public void deleteCvlEngFcltySpecMngFcltsAtchFileMulti(Map<?, ?> deleteVO) throws Exception {
		gamCvlEngFcltySpecMngDao.deleteCvlEngFcltySpecMngFcltsAtchFileMulti(deleteVO);
	}

	@Override
	public EgovMap selectCvlEngFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngFcltsAtchFilePk(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectCvlEngFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngFcltsAtchFileNewNo(gamFcltsAtchFileMngVO);
	}


	@Override
	public List selectCvlEngFcltySpecMngMntnRprDtlsList(GamMntnRprDtlsVO searchVO) throws Exception {
		return gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngMntnRprDtlsList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamCvlEngFcltySpecMngService#updateCvlEngFcltySpecMngAtchFileDirChage(egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO)
	 */
	@Override
	public void updateCvlEngFcltySpecMngAtchFileDirChage(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		// TODO Auto-generated method stub
		gamCvlEngFcltySpecMngDao.updateCvlEngFcltySpecMngAtchFileDirChage(gamAtchFileDirMngVO);
	}

}
