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
import egovframework.rte.ygpa.gam.fclty.service.GamElectyFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;
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

@Repository("gamElectyFcltySpecMngDao")
public class GamElectyFcltySpecMngDao extends YGPAAbstractDAO {

	/**
	 * @name selectElectyFcltySpecMngList
	 * @param searchVO
	 * @return List
	 */
	public List selectElectyFcltySpecMngList(GamElectyFcltySpecMngVO searchVO) {
		return list("gamElectyFcltySpecMngDao.selectElectyFcltySpecMngList_D", searchVO);
	}

	/**
	 * @name insertElectyFcltySpecMng
	 * @param gamElectyFcltySpecMngVO
	 * @return void
	 */
	public void insertElectyFcltySpecMng(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) {
		insert("gamElectyFcltySpecMngDao.insertElectyFcltySpecMng_S", gamElectyFcltySpecMngVO);
	}

	/**
	 * @name updateElectyFcltySpecMng
	 * @param gamElectyFcltySpecMngVO
	 * @return void
	 */
	public void updateElectyFcltySpecMng(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) {
		update("gamElectyFcltySpecMngDao.updateElectyFcltySpecMng_S", gamElectyFcltySpecMngVO);
	}

	/**
	 * @name deleteElectyFcltySpecMng
	 * @param gamElectyFcltySpecMngVO
	 * @return void
	 */
	public void deleteElectyFcltySpecMng(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) {
		delete("gamElectyFcltySpecMngDao.deleteElectyFcltySpecMng_S", gamElectyFcltySpecMngVO);
	}

	/**
	 * @name selectElectyFcltySpecMngPk
	 * @param gamElectyFcltySpecMngVO
	 * @return EgovMap
	 */
	public EgovMap selectElectyFcltySpecMngPk(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) {
		return (EgovMap)selectByPk("gamElectyFcltySpecMngDao.selectElectyFcltySpecMngPk_S", gamElectyFcltySpecMngVO);
	}

	/**
	 * @name selectElectyFcltySpecMngListSum
	 * @param searchVO
	 * @return GamElectyFcltySpecMngVO
	 */
	public GamElectyFcltySpecMngVO selectElectyFcltySpecMngListSum(GamElectyFcltySpecMngVO searchVO) {
		return (GamElectyFcltySpecMngVO)selectByPk("gamElectyFcltySpecMngDao.selectElectyFcltySpecMngListSum_S", searchVO);
	}


	/**
	 * @name insertElectyFcltySpecMngGisPrtFcltyCd
	 * @param gamElectyFcltySpecMngVO
	 * @return void
	 */
	public void insertElectyFcltySpecMngGisPrtFcltyCd(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) {
		insert("gamElectyFcltySpecMngDao.insertElectyFcltySpecMngGisPrtFcltyCd_S", gamElectyFcltySpecMngVO);
	}

	/**
	 * @name updateElectyFcltySpecMngGisPrtFcltyCd
	 * @param gamElectyFcltySpecMngVO
	 * @return void
	 */
	public void updateElectyFcltySpecMngGisPrtFcltyCd(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) {
		update("gamElectyFcltySpecMngDao.updateElectyFcltySpecMngGisPrtFcltyCd_S", gamElectyFcltySpecMngVO);
	}

	/**
	 * @name deleteElectyFcltySpecMngGisPrtFcltyCd
	 * @param gamElectyFcltySpecMngVO
	 * @return void
	 */
	public void deleteElectyFcltySpecMngGisPrtFcltyCd(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) {
		delete("gamElectyFcltySpecMngDao.deleteElectyFcltySpecMngGisPrtFcltyCd_S", gamElectyFcltySpecMngVO);
	}

	/**
	 * @name selectElectyFcltySpecMngMaxGisPrtFcltySeq
	 * @param gamElectyFcltySpecMngVO
	 * @return String
	 */
	public String selectElectyFcltySpecMngMaxGisPrtFcltySeq(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) {
		return (String)selectByPk("gamElectyFcltySpecMngDao.selectElectyFcltySpecMngMaxGisPrtFcltySeq_S", gamElectyFcltySpecMngVO);
	}


	/**
	 * @name selectFcltsMngGroupNm
	 * @param searchVO
	 * @return String
	 */
	public String selectFcltsMngGroupNm(Map searchVO) {
		return (String)selectByPk("gamElectyFcltySpecMngDao.selectFcltsMngGroupNm_S", searchVO);
	}

	/**
	 * @name selectEntrpsNm
	 * @param searchVO
	 * @return String
	 */
	public String selectEntrpsNm(Map searchVO) {
		return (String)selectByPk("gamElectyFcltySpecMngDao.selectEntrpsNm_S", searchVO);
	}

	/**
	 * @name selectFcltsClCdNm
	 * @param searchVO
	 * @return String
	 */
	public String selectFcltsClCdNm(Map searchVO) {
		return (String)selectByPk("gamElectyFcltySpecMngDao.selectFcltsClCdNm_S", searchVO);
	}

	/**
	 * @name selectFcltsClCdList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltsClCdList() {
		return list("gamElectyFcltySpecMngDao.selectFcltsClCdList_D", null);
	}

	/**
	 * @name selectGisPrtFcltyNm
	 * @param searchVO
	 * @return String
	 */
	public String selectGisPrtFcltyNm(Map searchVO) {
		return (String)selectByPk("gamElectyFcltySpecMngDao.selectGisPrtFcltyNm_S", searchVO);
	}


	/**
	 * @name selectElectyFcltySpecMngAtchFileDirList
	 * @param gamAtchFileDirMngVO
	 * @return List
	 */
	public List selectElectyFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return list("gamElectyFcltySpecMngDao.selectElectyFcltySpecMngAtchFileDirList_D", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectElectyFcltySpecMngAtchFileDirPk
	 * @param gamAtchFileDirMngVO
	 * @return EgovMap
	 */
	public EgovMap selectElectyFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (EgovMap)selectByPk("gamElectyFcltySpecMngDao.selectElectyFcltySpecMngAtchFileDirPk_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name insertElectyFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void insertElectyFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		insert("gamElectyFcltySpecMngDao.insertElectyFcltySpecMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateElectyFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateElectyFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamElectyFcltySpecMngDao.updateElectyFcltySpecMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateElectyFcltySpecMngInsertAtchFileDirLeafYn
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateElectyFcltySpecMngInsertAtchFileDirLeafYn(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamElectyFcltySpecMngDao.updateElectyFcltySpecMngInsertAtchFileDirLeafYn_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateElectyFcltySpecMngDeleteAtchFileDirLeafYn
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateElectyFcltySpecMngDeleteAtchFileDirLeafYn(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamElectyFcltySpecMngDao.updateElectyFcltySpecMngDeleteAtchFileDirLeafYn_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name deleteElectyFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void deleteElectyFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		delete("gamElectyFcltySpecMngDao.deleteElectyFcltySpecMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name deleteElectyFcltySpecMngAtchFileDirLowerData
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public void deleteElectyFcltySpecMngAtchFileDirLowerData(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		delete("gamElectyFcltySpecMngDao.deleteElectyFcltySpecMngAtchFileDirLowerData_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectElectyFcltySpecMngAtchFileDirNewNo
	 * @param gamAtchFileDirMngVO
	 * @return String
	 */
	public String selectElectyFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (String)selectByPk("gamElectyFcltySpecMngDao.selectElectyFcltySpecMngAtchFileDirNewNo_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectElectyFcltySpecMngAtchFileDirLowerDataCnt
	 * @param gamAtchFileDirMngVO
	 * @return List
	 */
	public List selectElectyFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return list("gamElectyFcltySpecMngDao.selectElectyFcltySpecMngAtchFileDirLowerDataCnt_S", gamAtchFileDirMngVO);
	}


	/**
	 * @name selectElectyFcltySpecMngFcltsAtchFileList
	 * @param searchVO
	 * @return List
	 */
	public List selectElectyFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) {
		return list("gamElectyFcltySpecMngDao.selectElectyFcltySpecMngFcltsAtchFileList_D", searchVO);
	}

	/**
	 * @name insertElectyFcltySpecMngFcltsAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 */
	public void insertElectyFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		insert("gamElectyFcltySpecMngDao.insertElectyFcltySpecMngFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name updateElectyFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void updateElectyFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		update("gamElectyFcltySpecMngDao.updateElectyFcltySpecMngFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name deleteElectyFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void deleteElectyFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		delete("gamElectyFcltySpecMngDao.deleteElectyFcltySpecMngFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name deleteElectyFcltySpecMngAllFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void deleteElectyFcltySpecMngAllFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		delete("gamElectyFcltySpecMngDao.deleteElectyFcltySpecMngAllFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name selectElectyFcltySpecMngFcltsAtchFilePk
	 * @param gamFcltsAtchFileMngVO
	 * @return EgovMap
	 */
	public EgovMap selectElectyFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (EgovMap)selectByPk("gamElectyFcltySpecMngDao.selectElectyFcltySpecMngFcltsAtchFilePk_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name selectElectyFcltySpecMngFcltsAtchFileNewNo
	 * @param gamFcltsAtchFileMngVO
	 * @return String
	 */
	public String selectElectyFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (String)selectByPk("gamElectyFcltySpecMngDao.selectElectyFcltySpecMngFcltsAtchFileNewNo_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectElectyFcltySpecMngMntnRprDtlsList(GamMntnRprDtlsVO searchVO) {
		return list("gamElectyFcltySpecMngDao.selectElectyFcltySpecMngMntnRprDtlsList_D", searchVO);
	}

	/**
	 * @param gamAtchFileDirMngVO
	 */
	public void updateElectyFcltySpecMngAtchFileDirChage(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		// TODO Auto-generated method stub
		update("gamElectyFcltySpecMngDao.updateElectyFcltySpecMngAtchFileDirChage_S", gamAtchFileDirMngVO);
	}

}
