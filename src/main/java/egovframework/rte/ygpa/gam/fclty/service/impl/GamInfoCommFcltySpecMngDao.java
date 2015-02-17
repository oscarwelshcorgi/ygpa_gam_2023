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
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;
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

@Repository("gamInfoCommFcltySpecMngDao")
public class GamInfoCommFcltySpecMngDao extends YGPAAbstractDAO {

	/**
	 * @name selectInfoCommFcltySpecMngList
	 * @param searchVO
	 * @return List
	 */
	public List selectInfoCommFcltySpecMngList(GamInfoCommFcltySpecMngVO searchVO) {
		return list("gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngList_D", searchVO);
	}

	/**
	 * @name insertInfoCommFcltySpecMng
	 * @param gamInfoCommFcltySpecMngVO
	 * @return void
	 */
	public void insertInfoCommFcltySpecMng(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) {
		insert("gamInfoCommFcltySpecMngDao.insertInfoCommFcltySpecMng_S", gamInfoCommFcltySpecMngVO);
	}

	/**
	 * @name updateInfoCommFcltySpecMng
	 * @param gamInfoCommFcltySpecMngVO
	 * @return void
	 */
	public void updateInfoCommFcltySpecMng(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) {
		update("gamInfoCommFcltySpecMngDao.updateInfoCommFcltySpecMng_S", gamInfoCommFcltySpecMngVO);
	}

	/**
	 * @name deleteInfoCommFcltySpecMng
	 * @param gamInfoCommFcltySpecMngVO
	 * @return void
	 */
	public void deleteInfoCommFcltySpecMng(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) {
		delete("gamInfoCommFcltySpecMngDao.deleteInfoCommFcltySpecMng_S", gamInfoCommFcltySpecMngVO);
	}

	/**
	 * @name selectInfoCommFcltySpecMngPk
	 * @param gamInfoCommFcltySpecMngVO
	 * @return EgovMap
	 */
	public EgovMap selectInfoCommFcltySpecMngPk(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) {
		return (EgovMap)selectByPk("gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngPk_S", gamInfoCommFcltySpecMngVO);
	}

	/**
	 * @name selectInfoCommFcltySpecMngListSum
	 * @param searchVO
	 * @return GamInfoCommFcltySpecMngVO
	 */
	public GamInfoCommFcltySpecMngVO selectInfoCommFcltySpecMngListSum(GamInfoCommFcltySpecMngVO searchVO) {
		return (GamInfoCommFcltySpecMngVO)selectByPk("gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngListSum_S", searchVO);
	}


	/**
	 * @name insertInfoCommFcltySpecMngGisPrtFcltyCd
	 * @param gamInfoCommFcltySpecMngVO
	 * @return void
	 */
	public void insertInfoCommFcltySpecMngGisPrtFcltyCd(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) {
		insert("gamInfoCommFcltySpecMngDao.insertInfoCommFcltySpecMngGisPrtFcltyCd_S", gamInfoCommFcltySpecMngVO);
	}

	/**
	 * @name updateInfoCommFcltySpecMngGisPrtFcltyCd
	 * @param gamInfoCommFcltySpecMngVO
	 * @return void
	 */
	public void updateInfoCommFcltySpecMngGisPrtFcltyCd(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) {
		update("gamInfoCommFcltySpecMngDao.updateInfoCommFcltySpecMngGisPrtFcltyCd_S", gamInfoCommFcltySpecMngVO);
	}

	/**
	 * @name deleteInfoCommFcltySpecMngGisPrtFcltyCd
	 * @param gamInfoCommFcltySpecMngVO
	 * @return void
	 */
	public void deleteInfoCommFcltySpecMngGisPrtFcltyCd(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) {
		delete("gamInfoCommFcltySpecMngDao.deleteInfoCommFcltySpecMngGisPrtFcltyCd_S", gamInfoCommFcltySpecMngVO);
	}

	/**
	 * @name selectInfoCommFcltySpecMngMaxGisPrtFcltySeq
	 * @param gamInfoCommFcltySpecMngVO
	 * @return String
	 */
	public String selectInfoCommFcltySpecMngMaxGisPrtFcltySeq(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) {
		return (String)selectByPk("gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngMaxGisPrtFcltySeq_S", gamInfoCommFcltySpecMngVO);
	}


	/**
	 * @name selectFcltsMngGroupNm
	 * @param searchVO
	 * @return String
	 */
	public String selectFcltsMngGroupNm(Map searchVO) {
		return (String)selectByPk("gamInfoCommFcltySpecMngDao.selectFcltsMngGroupNm_S", searchVO);
	}

	/**
	 * @name selectEntrpsNm
	 * @param searchVO
	 * @return String
	 */
	public String selectEntrpsNm(Map searchVO) {
		return (String)selectByPk("gamInfoCommFcltySpecMngDao.selectEntrpsNm_S", searchVO);
	}

	/**
	 * @name selectFcltsClCdNm
	 * @param searchVO
	 * @return String
	 */
	public String selectFcltsClCdNm(Map searchVO) {
		return (String)selectByPk("gamInfoCommFcltySpecMngDao.selectFcltsClCdNm_S", searchVO);
	}

	/**
	 * @name selectFcltsClCdList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltsClCdList() {
		return list("gamInfoCommFcltySpecMngDao.selectFcltsClCdList_D", null);
	}

	/**
	 * @name selectGisPrtFcltyNm
	 * @param searchVO
	 * @return String
	 */
	public String selectGisPrtFcltyNm(Map searchVO) {
		return (String)selectByPk("gamInfoCommFcltySpecMngDao.selectGisPrtFcltyNm_S", searchVO);
	}


	/**
	 * @name selectInfoCommFcltySpecMngAtchFileDirList
	 * @param gamAtchFileDirMngVO
	 * @return List
	 */
	public List selectInfoCommFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return list("gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngAtchFileDirList_D", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectInfoCommFcltySpecMngAtchFileDirPk
	 * @param gamAtchFileDirMngVO
	 * @return EgovMap
	 */
	public EgovMap selectInfoCommFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (EgovMap)selectByPk("gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngAtchFileDirPk_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name insertInfoCommFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void insertInfoCommFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		insert("gamInfoCommFcltySpecMngDao.insertInfoCommFcltySpecMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateInfoCommFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateInfoCommFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamInfoCommFcltySpecMngDao.updateInfoCommFcltySpecMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateInfoCommFcltySpecMngInsertAtchFileDirLeafYn
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateInfoCommFcltySpecMngInsertAtchFileDirLeafYn(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamInfoCommFcltySpecMngDao.updateInfoCommFcltySpecMngInsertAtchFileDirLeafYn_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateInfoCommFcltySpecMngDeleteAtchFileDirLeafYn
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateInfoCommFcltySpecMngDeleteAtchFileDirLeafYn(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamInfoCommFcltySpecMngDao.updateInfoCommFcltySpecMngDeleteAtchFileDirLeafYn_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name deleteInfoCommFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void deleteInfoCommFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		delete("gamInfoCommFcltySpecMngDao.deleteInfoCommFcltySpecMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name deleteInfoCommFcltySpecMngAtchFileDirLowerData
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public void deleteInfoCommFcltySpecMngAtchFileDirLowerData(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		delete("gamInfoCommFcltySpecMngDao.deleteInfoCommFcltySpecMngAtchFileDirLowerData_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectInfoCommFcltySpecMngAtchFileDirNewNo
	 * @param gamAtchFileDirMngVO
	 * @return String
	 */
	public String selectInfoCommFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (String)selectByPk("gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngAtchFileDirNewNo_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectInfoCommFcltySpecMngAtchFileDirLowerDataCnt
	 * @param gamAtchFileDirMngVO
	 * @return List
	 */
	public List selectInfoCommFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return list("gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngAtchFileDirLowerDataCnt_S", gamAtchFileDirMngVO);
	}


	/**
	 * @name selectInfoCommFcltySpecMngFcltsAtchFileList
	 * @param searchVO
	 * @return List
	 */
	public List selectInfoCommFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) {
		return list("gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngFcltsAtchFileList_D", searchVO);
	}

	/**
	 * @name insertInfoCommFcltySpecMngFcltsAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 */
	public void insertInfoCommFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		insert("gamInfoCommFcltySpecMngDao.insertInfoCommFcltySpecMngFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name updateInfoCommFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void updateInfoCommFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		update("gamInfoCommFcltySpecMngDao.updateInfoCommFcltySpecMngFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name deleteInfoCommFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void deleteInfoCommFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		delete("gamInfoCommFcltySpecMngDao.deleteInfoCommFcltySpecMngFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name deleteInfoCommFcltySpecMngAllFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void deleteInfoCommFcltySpecMngAllFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		delete("gamInfoCommFcltySpecMngDao.deleteInfoCommFcltySpecMngAllFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name selectInfoCommFcltySpecMngFcltsAtchFilePk
	 * @param gamFcltsAtchFileMngVO
	 * @return EgovMap
	 */
	public EgovMap selectInfoCommFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (EgovMap)selectByPk("gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngFcltsAtchFilePk_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name selectInfoCommFcltySpecMngFcltsAtchFileNewNo
	 * @param gamFcltsAtchFileMngVO
	 * @return String
	 */
	public String selectInfoCommFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (String)selectByPk("gamInfoCommFcltySpecMngDao.selectInfoCommFcltySpecMngFcltsAtchFileNewNo_S", gamFcltsAtchFileMngVO);
	}

}
