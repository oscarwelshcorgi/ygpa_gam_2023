/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamCvlEngFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;

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

@Repository("gamCvlEngFcltySpecMngDao")
public class GamCvlEngFcltySpecMngDao extends YGPAAbstractDAO {

	/**
	 * @name selectCvlEngFcltySpecMngList
	 * @param searchVO
	 * @return List
	 */
	public List selectCvlEngFcltySpecMngList(GamCvlEngFcltySpecMngVO searchVO) {
		return list("gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngList_D", searchVO);
	}

	/**
	 * @name insertCvlEngFcltySpecMng
	 * @param gamCvlEngFcltySpecMngVO
	 * @return void
	 */
	public void insertCvlEngFcltySpecMng(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) {
		insert("gamCvlEngFcltySpecMngDao.insertCvlEngFcltySpecMng_S", gamCvlEngFcltySpecMngVO);
	}

	/**
	 * @name updateCvlEngFcltySpecMng
	 * @param gamCvlEngFcltySpecMngVO
	 * @return void
	 */
	public void updateCvlEngFcltySpecMng(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) {
		update("gamCvlEngFcltySpecMngDao.updateCvlEngFcltySpecMng_S", gamCvlEngFcltySpecMngVO);
	}

	/**
	 * @name deleteCvlEngFcltySpecMng
	 * @param gamCvlEngFcltySpecMngVO
	 * @return void
	 */
	public void deleteCvlEngFcltySpecMng(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) {
		delete("gamCvlEngFcltySpecMngDao.deleteCvlEngFcltySpecMng_S", gamCvlEngFcltySpecMngVO);
	}

	/**
	 * @name selectCvlEngFcltySpecMngPk
	 * @param gamCvlEngFcltySpecMngVO
	 * @return EgovMap
	 */
	public EgovMap selectCvlEngFcltySpecMngPk(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) {
		return (EgovMap)selectByPk("gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngPk_S", gamCvlEngFcltySpecMngVO);
	}

	/**
	 * @name selectCvlEngFcltySpecMngListSum
	 * @param searchVO
	 * @return GamCvlEngFcltySpecMngVO
	 */
	public GamCvlEngFcltySpecMngVO selectCvlEngFcltySpecMngListSum(GamCvlEngFcltySpecMngVO searchVO) {
		return (GamCvlEngFcltySpecMngVO)selectByPk("gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngListSum_S", searchVO);
	}


	/**
	 * @name insertCvlEngFcltySpecMngGisPrtFcltyCd
	 * @param gamCvlEngFcltySpecMngVO
	 * @return void
	 */
	public void insertCvlEngFcltySpecMngGisPrtFcltyCd(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) {
		insert("gamCvlEngFcltySpecMngDao.insertCvlEngFcltySpecMngGisPrtFcltyCd_S", gamCvlEngFcltySpecMngVO);
	}

	/**
	 * @name updateCvlEngFcltySpecMngGisPrtFcltyCd
	 * @param gamCvlEngFcltySpecMngVO
	 * @return void
	 */
	public void updateCvlEngFcltySpecMngGisPrtFcltyCd(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) {
		update("gamCvlEngFcltySpecMngDao.updateCvlEngFcltySpecMngGisPrtFcltyCd_S", gamCvlEngFcltySpecMngVO);
	}

	/**
	 * @name deleteCvlEngFcltySpecMngGisPrtFcltyCd
	 * @param gamCvlEngFcltySpecMngVO
	 * @return void
	 */
	public void deleteCvlEngFcltySpecMngGisPrtFcltyCd(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) {
		delete("gamCvlEngFcltySpecMngDao.deleteCvlEngFcltySpecMngGisPrtFcltyCd_S", gamCvlEngFcltySpecMngVO);
	}

	/**
	 * @name selectCvlEngFcltySpecMngMaxGisPrtFcltySeq
	 * @param gamCvlEngFcltySpecMngVO
	 * @return String
	 */
	public String selectCvlEngFcltySpecMngMaxGisPrtFcltySeq(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) {
		return (String)selectByPk("gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngMaxGisPrtFcltySeq_S", gamCvlEngFcltySpecMngVO);
	}


	/**
	 * @name selectFcltsMngGroupNm
	 * @param searchVO
	 * @return String
	 */
	public String selectFcltsMngGroupNm(Map searchVO) {
		return (String)selectByPk("gamCvlEngFcltySpecMngDao.selectFcltsMngGroupNm_S", searchVO);
	}

	/**
	 * @name selectEntrpsNm
	 * @param searchVO
	 * @return String
	 */
	public String selectEntrpsNm(Map searchVO) {
		return (String)selectByPk("gamCvlEngFcltySpecMngDao.selectEntrpsNm_S", searchVO);
	}

	/**
	 * @name selectFcltsClCdNm
	 * @param searchVO
	 * @return String
	 */
	public String selectFcltsClCdNm(Map searchVO) {
		return (String)selectByPk("gamCvlEngFcltySpecMngDao.selectFcltsClCdNm_S", searchVO);
	}

	/**
	 * @name selectFcltsClCdList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltsClCdList() {
		return list("gamCvlEngFcltySpecMngDao.selectFcltsClCdList_D", null);
	}


	/**
	 * @name selectCvlEngFcltySpecMngAtchFileDirList
	 * @param gamAtchFileDirMngVO
	 * @return List
	 */
	public List selectCvlEngFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return list("gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngAtchFileDirList_D", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectCvlEngFcltySpecMngAtchFileDirPk
	 * @param gamAtchFileDirMngVO
	 * @return EgovMap
	 */
	public EgovMap selectCvlEngFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (EgovMap)selectByPk("gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngAtchFileDirPk_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name insertCvlEngFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void insertCvlEngFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		insert("gamCvlEngFcltySpecMngDao.insertCvlEngFcltySpecMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateCvlEngFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateCvlEngFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamCvlEngFcltySpecMngDao.updateCvlEngFcltySpecMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateCvlEngFcltySpecMngInsertAtchFileDirLeafYn
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateCvlEngFcltySpecMngInsertAtchFileDirLeafYn(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamCvlEngFcltySpecMngDao.updateCvlEngFcltySpecMngInsertAtchFileDirLeafYn_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateCvlEngFcltySpecMngDeleteAtchFileDirLeafYn
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateCvlEngFcltySpecMngDeleteAtchFileDirLeafYn(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamCvlEngFcltySpecMngDao.updateCvlEngFcltySpecMngDeleteAtchFileDirLeafYn_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name deleteCvlEngFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void deleteCvlEngFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		delete("gamCvlEngFcltySpecMngDao.deleteCvlEngFcltySpecMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name deleteCvlEngFcltySpecMngAtchFileDirLowerData
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public void deleteCvlEngFcltySpecMngAtchFileDirLowerData(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		delete("gamCvlEngFcltySpecMngDao.deleteCvlEngFcltySpecMngAtchFileDirLowerData_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectCvlEngFcltySpecMngAtchFileDirNewNo
	 * @param gamAtchFileDirMngVO
	 * @return String
	 */
	public String selectCvlEngFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (String)selectByPk("gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngAtchFileDirNewNo_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectCvlEngFcltySpecMngAtchFileDirLowerDataCnt
	 * @param gamAtchFileDirMngVO
	 * @return List
	 */
	public List selectCvlEngFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return list("gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngAtchFileDirLowerDataCnt_S", gamAtchFileDirMngVO);
	}


	/**
	 * @name selectCvlEngFcltySpecMngFcltsAtchFileList
	 * @param searchVO
	 * @return List
	 */
	public List selectCvlEngFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) {
		return list("gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngFcltsAtchFileList_D", searchVO);
	}

	/**
	 * @name insertCvlEngFcltySpecMngFcltsAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 */
	public void insertCvlEngFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		insert("gamCvlEngFcltySpecMngDao.insertCvlEngFcltySpecMngFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name updateCvlEngFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void updateCvlEngFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		update("gamCvlEngFcltySpecMngDao.updateCvlEngFcltySpecMngFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name deleteCvlEngFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void deleteCvlEngFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		delete("gamCvlEngFcltySpecMngDao.deleteCvlEngFcltySpecMngFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name deleteCvlEngFcltySpecMngAllFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void deleteCvlEngFcltySpecMngAllFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		delete("gamCvlEngFcltySpecMngDao.deleteCvlEngFcltySpecMngAllFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name deleteCvlEngFcltySpecMngFcltsAtchFileMulti
	 * @param deleteVO
	 * @return void
	 */
	public void deleteCvlEngFcltySpecMngFcltsAtchFileMulti(Map<?, ?> deleteVO) {
		delete("gamCvlEngFcltySpecMngDao.deleteCvlEngFcltySpecMngFcltsAtchFileMulti_S", deleteVO);
	}

	/**
	 * @name selectCvlEngFcltySpecMngFcltsAtchFilePk
	 * @param gamFcltsAtchFileMngVO
	 * @return EgovMap
	 */
	public EgovMap selectCvlEngFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (EgovMap)selectByPk("gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngFcltsAtchFilePk_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name selectCvlEngFcltySpecMngFcltsAtchFileNewNo
	 * @param gamFcltsAtchFileMngVO
	 * @return String
	 */
	public String selectCvlEngFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (String)selectByPk("gamCvlEngFcltySpecMngDao.selectCvlEngFcltySpecMngFcltsAtchFileNewNo_S", gamFcltsAtchFileMngVO);
	}

}
