/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamArchFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltySpecAtchFileVO;

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

@Repository("gamArchFcltySpecMngDao")
public class GamArchFcltySpecMngDao extends YGPAAbstractDAO {

	/**
	 * @name selectArchFcltySpecMngList
	 * @param searchVO
	 * @return List
	 */
	public List selectArchFcltySpecMngList(GamArchFcltySpecMngVO searchVO) {
		return list("gamArchFcltySpecMngDao.selectArchFcltySpecMngList_D", searchVO);
	}

	/**
	 * @name insertArchFcltySpecMng
	 * @param gamArchFcltySpecMngVO
	 * @return void
	 */
	public void insertArchFcltySpecMng(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) {
		insert("gamArchFcltySpecMngDao.insertArchFcltySpecMng_S", gamArchFcltySpecMngVO);
	}

	/**
	 * @name updateArchFcltySpecMng
	 * @param gamArchFcltySpecMngVO
	 * @return void
	 */
	public void updateArchFcltySpecMng(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) {
		update("gamArchFcltySpecMngDao.updateArchFcltySpecMng_S", gamArchFcltySpecMngVO);
	}

	/**
	 * @name deleteArchFcltySpecMng
	 * @param gamArchFcltySpecMngVO
	 * @return void
	 */
	public void deleteArchFcltySpecMng(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) {
		delete("gamArchFcltySpecMngDao.deleteArchFcltySpecMng_S", gamArchFcltySpecMngVO);
	}

	/**
	 * @name selectArchFcltySpecMngPk
	 * @param gamArchFcltySpecMngVO
	 * @return EgovMap
	 */
	public EgovMap selectArchFcltySpecMngPk(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) {
		return (EgovMap)selectByPk("gamArchFcltySpecMngDao.selectArchFcltySpecMngPk_S", gamArchFcltySpecMngVO);
	}

	/**
	 * @name selectArchFcltySpecMngListSum
	 * @param searchVO
	 * @return GamArchFcltySpecMngVO
	 */
	public GamArchFcltySpecMngVO selectArchFcltySpecMngListSum(GamArchFcltySpecMngVO searchVO) {
		return (GamArchFcltySpecMngVO)selectByPk("gamArchFcltySpecMngDao.selectArchFcltySpecMngListSum_S", searchVO);
	}


	/**
	 * @name insertArchFcltySpecMngGisPrtFcltyCd
	 * @param gamArchFcltySpecMngVO
	 * @return void
	 */
	public void insertArchFcltySpecMngGisPrtFcltyCd(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) {
		insert("gamArchFcltySpecMngDao.insertArchFcltySpecMngGisPrtFcltyCd_S", gamArchFcltySpecMngVO);
	}

	/**
	 * @name updateArchFcltySpecMngGisPrtFcltyCd
	 * @param gamArchFcltySpecMngVO
	 * @return void
	 */
	public void updateArchFcltySpecMngGisPrtFcltyCd(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) {
		update("gamArchFcltySpecMngDao.updateArchFcltySpecMngGisPrtFcltyCd_S", gamArchFcltySpecMngVO);
	}

	/**
	 * @name deleteArchFcltySpecMngGisPrtFcltyCd
	 * @param gamArchFcltySpecMngVO
	 * @return void
	 */
	public void deleteArchFcltySpecMngGisPrtFcltyCd(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) {
		delete("gamArchFcltySpecMngDao.deleteArchFcltySpecMngGisPrtFcltyCd_S", gamArchFcltySpecMngVO);
	}

	/**
	 * @name selectArchFcltySpecMngMaxGisPrtFcltySeq
	 * @param gamArchFcltySpecMngVO
	 * @return String
	 */
	public String selectArchFcltySpecMngMaxGisPrtFcltySeq(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) {
		return (String)selectByPk("gamArchFcltySpecMngDao.selectArchFcltySpecMngMaxGisPrtFcltySeq_S", gamArchFcltySpecMngVO);
	}


	/**
	 * @name selectArchFcltySpecMngAtchFileList
	 * @param searchVO
	 * @return List
	 */
	public List selectArchFcltySpecMngAtchFileList(GamFcltySpecAtchFileVO searchVO) {
		return list("gamArchFcltySpecMngDao.selectArchFcltySpecMngAtchFileList_D", searchVO);
	}

	/**
	 * @name insertArchFcltySpecMngAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 */
	public void insertArchFcltySpecMngAtchFile(GamFcltySpecAtchFileVO gamFcltySpecAtchFileVO) {
		insert("gamArchFcltySpecMngDao.insertArchFcltySpecMngAtchFile_S", gamFcltySpecAtchFileVO);
	}

	/**
	 * @name uploadArchFcltySpecMngAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 */
	public void uploadArchFcltySpecMngAtchFile(GamFcltySpecAtchFileVO gamFcltySpecAtchFileVO) {
		insert("gamArchFcltySpecMngDao.uploadArchFcltySpecMngAtchFile_S", gamFcltySpecAtchFileVO);
	}

	/**
	 * @name updateArchFcltySpecMngAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 */
	public void updateArchFcltySpecMngAtchFile(GamFcltySpecAtchFileVO gamFcltySpecAtchFileVO) {
		update("gamArchFcltySpecMngDao.updateArchFcltySpecMngAtchFile_S", gamFcltySpecAtchFileVO);
	}

	/**
	 * @name deleteArchFcltySpecMngAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 */
	public void deleteArchFcltySpecMngAtchFile(GamFcltySpecAtchFileVO gamFcltySpecAtchFileVO) {
		delete("gamArchFcltySpecMngDao.deleteArchFcltySpecMngAtchFile_S", gamFcltySpecAtchFileVO);
	}

	/**
	 * @name deleteArchFcltySpecMngAllAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 */
	public void deleteArchFcltySpecMngAllAtchFile(GamFcltySpecAtchFileVO gamFcltySpecAtchFileVO) {
		delete("gamArchFcltySpecMngDao.deleteArchFcltySpecMngAllAtchFile_S", gamFcltySpecAtchFileVO);
	}

	/**
	 * @name selectArchFcltySpecMngAtchFilePk
	 * @param gamFcltySpecAtchFileVO
	 * @return EgovMap
	 */
	public EgovMap selectArchFcltySpecMngAtchFilePk(GamFcltySpecAtchFileVO gamFcltySpecAtchFileVO) {
		return (EgovMap)selectByPk("gamArchFcltySpecMngDao.selectArchFcltySpecMngAtchFilePk_S", gamFcltySpecAtchFileVO);
	}

	/**
	 * @name selectArchFcltySpecMngAtchFileMaxSeq
	 * @param gamFcltySpecAtchFileVO
	 * @return String
	 */
	public String selectArchFcltySpecMngAtchFileMaxSeq(GamFcltySpecAtchFileVO gamFcltySpecAtchFileVO) {
		return (String)selectByPk("gamArchFcltySpecMngDao.selectArchFcltySpecMngAtchFileMaxSeq_S", gamFcltySpecAtchFileVO);
	}


	/**
	 * @name selectFcltsMngGroupNm
	 * @param searchVO
	 * @return String
	 */
	public String selectFcltsMngGroupNm(Map searchVO) {
		return (String)selectByPk("gamArchFcltySpecMngDao.selectFcltsMngGroupNm_S", searchVO);
	}

	/**
	 * @name selectEntrpsNm
	 * @param searchVO
	 * @return String
	 */
	public String selectEntrpsNm(Map searchVO) {
		return (String)selectByPk("gamArchFcltySpecMngDao.selectEntrpsNm_S", searchVO);
	}

	/**
	 * @name selectFcltsClCdNm
	 * @param searchVO
	 * @return String
	 */
	public String selectFcltsClCdNm(Map searchVO) {
		return (String)selectByPk("gamArchFcltySpecMngDao.selectFcltsClCdNm_S", searchVO);
	}

	/**
	 * @name selectFcltsClCdList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltsClCdList() {
		return list("gamArchFcltySpecMngDao.selectFcltsClCdList_D", null);
	}


	/**
	 * @name selectFcltsClCdMngTreeList
	 * @param gamAtchFileDirMngVO
	 * @return List
	 */
	public List selectArchFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return list("gamArchFcltySpecMngDao.selectArchFcltySpecMngAtchFileDirList_D", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectArchFcltySpecMngAtchFileDirPk
	 * @param gamAtchFileDirMngVO
	 * @return EgovMap
	 */
	public EgovMap selectArchFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (EgovMap)selectByPk("gamArchFcltySpecMngDao.selectArchFcltySpecMngAtchFileDirPk_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name insertArchFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void insertArchFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		insert("gamArchFcltySpecMngDao.insertArchFcltySpecMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateArchFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateArchFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamArchFcltySpecMngDao.updateArchFcltySpecMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateArchFcltySpecMngAtchFileDirLeafYn
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateArchFcltySpecMngAtchFileDirLeafYn(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamArchFcltySpecMngDao.updateArchFcltySpecMngAtchFileDirLeafYn_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name deleteArchFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void deleteArchFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		delete("gamArchFcltySpecMngDao.deleteArchFcltySpecMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name deleteArchFcltySpecMngAtchFileDirLowerData
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public void deleteArchFcltySpecMngAtchFileDirLowerData(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		delete("gamArchFcltySpecMngDao.deleteArchFcltySpecMngAtchFileDirLowerData_S", gamAtchFileDirMngVO);
	}


	/**
	 * @name selectArchFcltySpecMngAtchFileDirNewNo
	 * @param gamAtchFileDirMngVO
	 * @return String
	 */
	public String selectArchFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (String)selectByPk("gamArchFcltySpecMngDao.selectArchFcltySpecMngAtchFileDirNewNo_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectArchFcltySpecMngAtchFileDirLowerDataCnt
	 * @param gamAtchFileDirMngVO
	 * @return List
	 */
	public List selectArchFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return list("gamArchFcltySpecMngDao.selectArchFcltySpecMngAtchFileDirLowerDataCnt_S", gamAtchFileDirMngVO);
	}


}
