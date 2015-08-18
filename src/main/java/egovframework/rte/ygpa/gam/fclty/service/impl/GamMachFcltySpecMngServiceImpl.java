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
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySpecMngService;
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySttusMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMntnRprDtlsVO;

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

@Service("gamMachFcltySpecMngService")
public class GamMachFcltySpecMngServiceImpl extends AbstractServiceImpl implements GamMachFcltySpecMngService {

	@Resource(name="gamMachFcltySpecMngDao")
	private GamMachFcltySpecMngDao gamMachFcltySpecMngDao;

	@Override
	public List selectMachFcltySpecMngList(GamMachFcltySpecMngVO searchVO) throws Exception {
		return gamMachFcltySpecMngDao.selectMachFcltySpecMngList(searchVO);
	}

	@Override
	public List selectMachFcltySpecMngCvlEngStatusList(GamMachFcltySpecMngVO searchVO) throws Exception {
		return gamMachFcltySpecMngDao.selectMachFcltySpecMngCvlEngStatusList(searchVO);
	}

	@Override
	public void insertMachFcltySpecMng(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) throws Exception {
		gamMachFcltySpecMngDao.insertMachFcltySpecMngGisPrtFcltyCd(gamMachFcltySpecMngVO);
		gamMachFcltySpecMngDao.insertMachFcltySpecMng(gamMachFcltySpecMngVO);
	}

	@Override
	public void updateMachFcltySpecMng(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) throws Exception {
		String strFcltySpecExistYn = (String)gamMachFcltySpecMngVO.getFcltySpecExistYn();
		gamMachFcltySpecMngDao.updateMachFcltySpecMngGisPrtFcltyCd(gamMachFcltySpecMngVO);
		if ("Y".equals(strFcltySpecExistYn)) {
			gamMachFcltySpecMngDao.updateMachFcltySpecMng(gamMachFcltySpecMngVO);
		} else {
			gamMachFcltySpecMngDao.insertMachFcltySpecMng(gamMachFcltySpecMngVO);
		}
	}

	@Override
	public void deleteMachFcltySpecMng(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) throws Exception {
		gamMachFcltySpecMngDao.deleteMachFcltySpecMng(gamMachFcltySpecMngVO);
		gamMachFcltySpecMngDao.deleteMachFcltySpecMngGisPrtFcltyCd(gamMachFcltySpecMngVO);
	}

	@Override
	public EgovMap selectMachFcltySpecMngPk(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) throws Exception {
		return gamMachFcltySpecMngDao.selectMachFcltySpecMngPk(gamMachFcltySpecMngVO);
	}

	@Override
	public GamMachFcltySpecMngVO selectMachFcltySpecMngListSum(GamMachFcltySpecMngVO searchVO) throws Exception {
		return gamMachFcltySpecMngDao.selectMachFcltySpecMngListSum(searchVO);
	}

	@Override
	public String selectMachFcltySpecMngMaxGisPrtFcltySeq(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) throws Exception {
		return gamMachFcltySpecMngDao.selectMachFcltySpecMngMaxGisPrtFcltySeq(gamMachFcltySpecMngVO);
	}


	@Override
	public String selectFcltsMngGroupNm(Map searchVO) throws Exception {
		return gamMachFcltySpecMngDao.selectFcltsMngGroupNm(searchVO);
	}

	@Override
	public String selectEntrpsNm(Map searchVO) throws Exception {
		return gamMachFcltySpecMngDao.selectEntrpsNm(searchVO);
	}

	@Override
	public String selectFcltsClCdNm(Map searchVO) throws Exception {
		return gamMachFcltySpecMngDao.selectFcltsClCdNm(searchVO);
	}

	@Override
	public List selectFcltsClCdList() throws Exception {
		return gamMachFcltySpecMngDao.selectFcltsClCdList();
	}

	@Override
	public String selectGisPrtFcltyNm(Map searchVO) throws Exception {
		return gamMachFcltySpecMngDao.selectGisPrtFcltyNm(searchVO);
	}


	@Override
	public List selectMachFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamMachFcltySpecMngDao.selectMachFcltySpecMngAtchFileDirList(gamAtchFileDirMngVO);
	}

	@Override
	public EgovMap selectMachFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamMachFcltySpecMngDao.selectMachFcltySpecMngAtchFileDirPk(gamAtchFileDirMngVO);
	}

	@Override
	public void insertMachFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		String strDepthSort = (String)gamAtchFileDirMngVO.getDepthSort();
		gamMachFcltySpecMngDao.insertMachFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);
		if (!"1".equals(strDepthSort)) {
			gamMachFcltySpecMngDao.updateMachFcltySpecMngInsertAtchFileDirLeafYn(gamAtchFileDirMngVO);
		}
	}

	@Override
	public void updateMachFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		gamMachFcltySpecMngDao.updateMachFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);
	}

	@Override
	public void deleteMachFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		String strDepthSort = (String)gamAtchFileDirMngVO.getDepthSort();
		gamMachFcltySpecMngDao.deleteMachFcltySpecMngAtchFileDirLowerData(gamAtchFileDirMngVO);
		gamMachFcltySpecMngDao.deleteMachFcltySpecMngAtchFileDir(gamAtchFileDirMngVO);
		if (!"1".equals(strDepthSort)) {
			gamMachFcltySpecMngDao.updateMachFcltySpecMngDeleteAtchFileDirLeafYn(gamAtchFileDirMngVO);
		}
		GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO = new GamFcltsAtchFileMngVO();
		gamFcltsAtchFileMngVO.setAtchFileDirNo(gamAtchFileDirMngVO.getDirNo());
		gamMachFcltySpecMngDao.deleteMachFcltySpecMngAllFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectMachFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamMachFcltySpecMngDao.selectMachFcltySpecMngAtchFileDirNewNo(gamAtchFileDirMngVO);
	}

	@Override
	public List selectMachFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return gamMachFcltySpecMngDao.selectMachFcltySpecMngAtchFileDirLowerDataCnt(gamAtchFileDirMngVO);
	}


	@Override
	public List selectMachFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception {
		return gamMachFcltySpecMngDao.selectMachFcltySpecMngFcltsAtchFileList(searchVO);
	}

	@Override
	public void insertMachFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamMachFcltySpecMngDao.insertMachFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public void updateMachFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamMachFcltySpecMngDao.updateMachFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public void deleteMachFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		gamMachFcltySpecMngDao.deleteMachFcltySpecMngFcltsAtchFile(gamFcltsAtchFileMngVO);
	}

	@Override
	public EgovMap selectMachFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamMachFcltySpecMngDao.selectMachFcltySpecMngFcltsAtchFilePk(gamFcltsAtchFileMngVO);
	}

	@Override
	public String selectMachFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception {
		return gamMachFcltySpecMngDao.selectMachFcltySpecMngFcltsAtchFileNewNo(gamFcltsAtchFileMngVO);
	}


	@Override
	public List selectMachFcltySpecMngMachFcltySttusList(GamMachFcltySttusMngVO searchVO) throws Exception {
		return gamMachFcltySpecMngDao.selectMachFcltySpecMngMachFcltySttusList(searchVO);
	}

	@Override
	public void insertMachFcltySpecMngMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception {
		gamMachFcltySpecMngDao.insertMachFcltySpecMngMachFcltySttus(gamMachFcltySttusMngVO);
	}

	@Override
	public void updateMachFcltySpecMngMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception {
		gamMachFcltySpecMngDao.updateMachFcltySpecMngMachFcltySttus(gamMachFcltySttusMngVO);
	}

	@Override
	public void deleteMachFcltySpecMngMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception {
		gamMachFcltySpecMngDao.deleteMachFcltySpecMngMachFcltySttus(gamMachFcltySttusMngVO);
	}

	@Override
	public EgovMap selectMachFcltySpecMngMachFcltySttusPk(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception {
		return gamMachFcltySpecMngDao.selectMachFcltySpecMngMachFcltySttusPk(gamMachFcltySttusMngVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySpecMngService#selectMachFcltySpecMngMntnRprDtlsList(egovframework.rte.ygpa.gam.fclty.service.GamMntnRprDtlsVO)
	 */
	@Override
	public List selectMachFcltySpecMngMntnRprDtlsList(GamMntnRprDtlsVO searchVO) throws Exception {
		return gamMachFcltySpecMngDao.selectMachFcltySpecMngMntnRprDtlsList(searchVO);
	}

}
