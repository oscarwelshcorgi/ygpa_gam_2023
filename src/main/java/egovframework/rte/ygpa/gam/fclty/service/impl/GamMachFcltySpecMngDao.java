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
import egovframework.rte.ygpa.gam.fclty.service.GamMachFcltySpecMngVO;

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

@Repository("gamMachFcltySpecMngDao")
public class GamMachFcltySpecMngDao extends YGPAAbstractDAO {

	/**
	 * @name selectMachFcltySpecMngList
	 * @param searchVO
	 * @return List
	 */
	public List selectMachFcltySpecMngList(GamMachFcltySpecMngVO searchVO) {
		return list("gamMachFcltySpecMngDao.selectMachFcltySpecMngList_D", searchVO);
	}

	/**
	 * @name insertMachFcltySpecMng
	 * @param gamMachFcltySpecMngVO
	 * @return void
	 */
	public void insertMachFcltySpecMng(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) {
		insert("gamMachFcltySpecMngDao.insertMachFcltySpecMng_S", gamMachFcltySpecMngVO);
	}

	/**
	 * @name updateMachFcltySpecMng
	 * @param gamMachFcltySpecMngVO
	 * @return void
	 */
	public void updateMachFcltySpecMng(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) {
		update("gamMachFcltySpecMngDao.updateMachFcltySpecMng_S", gamMachFcltySpecMngVO);
	}

	/**
	 * @name deleteMachFcltySpecMng
	 * @param gamMachFcltySpecMngVO
	 * @return void
	 */
	public void deleteMachFcltySpecMng(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) {
		delete("gamMachFcltySpecMngDao.deleteMachFcltySpecMng_S", gamMachFcltySpecMngVO);
	}

	/**
	 * @name selectMachFcltySpecMngPk
	 * @param gamMachFcltySpecMngVO
	 * @return EgovMap
	 */
	public EgovMap selectMachFcltySpecMngPk(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) {
		return (EgovMap)selectByPk("gamMachFcltySpecMngDao.selectMachFcltySpecMngPk_S", gamMachFcltySpecMngVO);
	}

	/**
	 * @name selectMachFcltySpecMngListSum
	 * @param searchVO
	 * @return GamMachFcltySpecMngVO
	 */
	public GamMachFcltySpecMngVO selectMachFcltySpecMngListSum(GamMachFcltySpecMngVO searchVO) {
		return (GamMachFcltySpecMngVO)selectByPk("gamMachFcltySpecMngDao.selectMachFcltySpecMngListSum_S", searchVO);
	}


	/**
	 * @name insertMachFcltySpecMngGisPrtFcltyCd
	 * @param gamMachFcltySpecMngVO
	 * @return void
	 */
	public void insertMachFcltySpecMngGisPrtFcltyCd(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) {
		insert("gamMachFcltySpecMngDao.insertMachFcltySpecMngGisPrtFcltyCd_S", gamMachFcltySpecMngVO);
	}

	/**
	 * @name updateMachFcltySpecMngGisPrtFcltyCd
	 * @param gamMachFcltySpecMngVO
	 * @return void
	 */
	public void updateMachFcltySpecMngGisPrtFcltyCd(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) {
		update("gamMachFcltySpecMngDao.updateMachFcltySpecMngGisPrtFcltyCd_S", gamMachFcltySpecMngVO);
	}

	/**
	 * @name deleteMachFcltySpecMngGisPrtFcltyCd
	 * @param gamMachFcltySpecMngVO
	 * @return void
	 */
	public void deleteMachFcltySpecMngGisPrtFcltyCd(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) {
		delete("gamMachFcltySpecMngDao.deleteMachFcltySpecMngGisPrtFcltyCd_S", gamMachFcltySpecMngVO);
	}

	/**
	 * @name selectMachFcltySpecMngMaxGisPrtFcltySeq
	 * @param gamMachFcltySpecMngVO
	 * @return String
	 */
	public String selectMachFcltySpecMngMaxGisPrtFcltySeq(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) {
		return (String)selectByPk("gamMachFcltySpecMngDao.selectMachFcltySpecMngMaxGisPrtFcltySeq_S", gamMachFcltySpecMngVO);
	}


	/**
	 * @name selectFcltsMngGroupNm
	 * @param searchVO
	 * @return String
	 */
	public String selectFcltsMngGroupNm(Map searchVO) {
		return (String)selectByPk("gamMachFcltySpecMngDao.selectFcltsMngGroupNm_S", searchVO);
	}

	/**
	 * @name selectEntrpsNm
	 * @param searchVO
	 * @return String
	 */
	public String selectEntrpsNm(Map searchVO) {
		return (String)selectByPk("gamMachFcltySpecMngDao.selectEntrpsNm_S", searchVO);
	}

	/**
	 * @name selectFcltsClCdNm
	 * @param searchVO
	 * @return String
	 */
	public String selectFcltsClCdNm(Map searchVO) {
		return (String)selectByPk("gamMachFcltySpecMngDao.selectFcltsClCdNm_S", searchVO);
	}

	/**
	 * @name selectFcltsClCdList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltsClCdList() {
		return list("gamMachFcltySpecMngDao.selectFcltsClCdList_D", null);
	}

	/**
	 * @name selectGisPrtFcltyNm
	 * @param searchVO
	 * @return String
	 */
	public String selectGisPrtFcltyNm(Map searchVO) {
		return (String)selectByPk("gamMachFcltySpecMngDao.selectGisPrtFcltyNm_S", searchVO);
	}


	/**
	 * @name selectMachFcltySpecMngAtchFileDirList
	 * @param gamAtchFileDirMngVO
	 * @return List
	 */
	public List selectMachFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return list("gamMachFcltySpecMngDao.selectMachFcltySpecMngAtchFileDirList_D", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectMachFcltySpecMngAtchFileDirPk
	 * @param gamAtchFileDirMngVO
	 * @return EgovMap
	 */
	public EgovMap selectMachFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (EgovMap)selectByPk("gamMachFcltySpecMngDao.selectMachFcltySpecMngAtchFileDirPk_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name insertMachFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void insertMachFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		insert("gamMachFcltySpecMngDao.insertMachFcltySpecMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateMachFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateMachFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamMachFcltySpecMngDao.updateMachFcltySpecMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateMachFcltySpecMngInsertAtchFileDirLeafYn
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateMachFcltySpecMngInsertAtchFileDirLeafYn(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamMachFcltySpecMngDao.updateMachFcltySpecMngInsertAtchFileDirLeafYn_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateMachFcltySpecMngDeleteAtchFileDirLeafYn
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateMachFcltySpecMngDeleteAtchFileDirLeafYn(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamMachFcltySpecMngDao.updateMachFcltySpecMngDeleteAtchFileDirLeafYn_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name deleteMachFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void deleteMachFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		delete("gamMachFcltySpecMngDao.deleteMachFcltySpecMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name deleteMachFcltySpecMngAtchFileDirLowerData
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public void deleteMachFcltySpecMngAtchFileDirLowerData(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		delete("gamMachFcltySpecMngDao.deleteMachFcltySpecMngAtchFileDirLowerData_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectMachFcltySpecMngAtchFileDirNewNo
	 * @param gamAtchFileDirMngVO
	 * @return String
	 */
	public String selectMachFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (String)selectByPk("gamMachFcltySpecMngDao.selectMachFcltySpecMngAtchFileDirNewNo_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectMachFcltySpecMngAtchFileDirLowerDataCnt
	 * @param gamAtchFileDirMngVO
	 * @return List
	 */
	public List selectMachFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return list("gamMachFcltySpecMngDao.selectMachFcltySpecMngAtchFileDirLowerDataCnt_S", gamAtchFileDirMngVO);
	}


	/**
	 * @name selectMachFcltySpecMngFcltsAtchFileList
	 * @param searchVO
	 * @return List
	 */
	public List selectMachFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) {
		return list("gamMachFcltySpecMngDao.selectMachFcltySpecMngFcltsAtchFileList_D", searchVO);
	}

	/**
	 * @name insertMachFcltySpecMngFcltsAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 */
	public void insertMachFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		insert("gamMachFcltySpecMngDao.insertMachFcltySpecMngFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name updateMachFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void updateMachFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		update("gamMachFcltySpecMngDao.updateMachFcltySpecMngFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name deleteMachFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void deleteMachFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		delete("gamMachFcltySpecMngDao.deleteMachFcltySpecMngFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name deleteMachFcltySpecMngAllFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void deleteMachFcltySpecMngAllFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		delete("gamMachFcltySpecMngDao.deleteMachFcltySpecMngAllFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name selectMachFcltySpecMngFcltsAtchFilePk
	 * @param gamFcltsAtchFileMngVO
	 * @return EgovMap
	 */
	public EgovMap selectMachFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (EgovMap)selectByPk("gamMachFcltySpecMngDao.selectMachFcltySpecMngFcltsAtchFilePk_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name selectMachFcltySpecMngFcltsAtchFileNewNo
	 * @param gamFcltsAtchFileMngVO
	 * @return String
	 */
	public String selectMachFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (String)selectByPk("gamMachFcltySpecMngDao.selectMachFcltySpecMngFcltsAtchFileNewNo_S", gamFcltsAtchFileMngVO);
	}

}
