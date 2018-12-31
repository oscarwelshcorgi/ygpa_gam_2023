/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMntnRprDtlsVO;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyMaintMngVO;

/**
 *
 * @author HNJ
 * @since 2014. 11. 25.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 25.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamFcltyMaintMngDao")
public class GamFcltyMaintMngDao extends YGPAAbstractDAO {


	/**
	 * 유지보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFcltyMaintMngList(GamFcltyMaintMngVO vo) throws Exception {
		return list("gamFcltyMaintMngDao.selectFcltyMaintMngList_D", vo);
	}


	/**
	 * 유지보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public GamFcltyMaintMngVO selectFcltyMaintMngListTotCnt(GamFcltyMaintMngVO vo) throws Exception {
		return (GamFcltyMaintMngVO) selectByPk("gamFcltyMaintMngDao.selectFcltyMaintMngListTotCnt_S", vo);
	}


	/**
	 * 유지보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	public EgovMap selectFcltyMaintMngDetail(GamFcltyMaintMngVO vo) throws Exception{
		return (EgovMap) selectByPk("gamFcltyMaintMngDao.selectFcltyMaintMngDetail_S", vo);
	}


	/**
	 * 유지보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectMntnRprObjFcltsFList(GamFcltyMaintMngVO vo) throws Exception {
		return list("gamFcltyMaintMngDao.selectMntnRprObjFcltsFList_D", vo);
	}


	/**
	 * 유지보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFcltyMaintFileList(GamFcltyMaintMngVO vo) throws Exception {
		return list("gamFcltyMaintMngDao.selectFcltyMaintFileList_D", vo);
	}


	/**
	 * 유지보수 순번
	 * @param map
	 * @return String
	 * @throws Exception
	 */
	public int selectNextMntnRprSeq(Map<?,?> vo) throws Exception{
		return (Integer) getSqlMapClient().queryForObject("gamFcltyMaintMngDao.selectNextMntnRprSeq_S", vo);
	}


	/**
	 * 유지보수내역 입력
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public void insertFcltyMaintMng(Map<?,?> vo) throws Exception{
		insert("gamFcltyMaintMngDao.insertFcltyMaintMng", vo);
	}


	/**
	 * 유지보수내역 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public void updateFcltyMaintMng(Map<?,?> vo) throws Exception{
		update("gamFcltyMaintMngDao.updateFcltyMaintMng", vo);
	}

	/**
	 * 유지보수내역 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public void deleteFcltyMaintMng(Map<?,?> vo) throws Exception{
		delete("gamFcltyMaintMngDao.deleteFcltyMaintMng", vo);
	}


	/**
	 * 유지보수내역 하위 대상시설물 입력
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public void insertMntnRprObjFcltsF(Map<?,?> vo) throws Exception{
		insert("gamFcltyMaintMngDao.insertMntnRprObjFcltsF", vo);
	}


	/**
	 * 유지보수내역 하위 대상시설물 전체 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public void deleteMntnRprObjFcltsF(Map<?,?> vo) throws Exception{
		delete("gamFcltyMaintMngDao.deleteMntnRprObjFcltsF", vo);
	}


	/**
	 * 유지보수내역 하위 첨부파일 입력
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public void insertFcltyMaintFile(Map<?,?> vo) throws Exception{
		insert("gamFcltyMaintMngDao.insertFcltyMaintFile", vo);
	}


	/**
	 * 유지보수내역 하위 첨부파일 전체 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public void deleteFcltyMaintFile(Map<?,?> vo) throws Exception{
		delete("gamFcltyMaintMngDao.deleteFcltyMaintFile", vo);
	}





	/**
	 * @name selectMaintMngAtchFileDirList
	 * @param gamAtchFileDirMngVO
	 * @return List
	 */
	public List selectMaintMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		return list("gamFcltyMaintMngDao.selectMaintMngAtchFileDirList_D", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectMaintMngAtchFileDirPk
	 * @param gamAtchFileDirMngVO
	 * @return EgovMap
	 */
	public EgovMap selectMaintMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (EgovMap)selectByPk("gamFcltyMaintMngDao.selectMaintMngAtchFileDirPk_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name insertMaintMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void insertMaintMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		insert("gamFcltyMaintMngDao.insertMaintMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateMaintMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateMaintMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamFcltyMaintMngDao.updateMaintMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateMaintMngInsertAtchFileDirLeafYn
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateMaintMngInsertAtchFileDirLeafYn(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamFcltyMaintMngDao.updateMaintMngInsertAtchFileDirLeafYn_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name updateMaintMngDeleteAtchFileDirLeafYn
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void updateMaintMngDeleteAtchFileDirLeafYn(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		update("gamFcltyMaintMngDao.updateMaintMngDeleteAtchFileDirLeafYn_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name deleteMaintMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @return void
	 */
	public void deleteMaintMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		delete("gamFcltyMaintMngDao.deleteMaintMngAtchFileDir_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name deleteMaintMngAtchFileDirLowerData
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public void deleteMaintMngAtchFileDirLowerData(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception {
		delete("gamFcltyMaintMngDao.deleteMaintMngAtchFileDirLowerData_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectMaintMngAtchFileDirNewNo
	 * @param gamAtchFileDirMngVO
	 * @return String
	 */
	public String selectMaintMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (String)selectByPk("gamFcltyMaintMngDao.selectMaintMngAtchFileDirNewNo_S", gamAtchFileDirMngVO);
	}

	/**
	 * @name selectMaintMngAtchFileDirLowerDataCnt
	 * @param gamAtchFileDirMngVO
	 * @return List
	 */
	public List selectMaintMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return list("gamFcltyMaintMngDao.selectMaintMngAtchFileDirLowerDataCnt_S", gamAtchFileDirMngVO);
	}


	/**
	 * @name selectMaintMngFcltsAtchFileList
	 * @param searchVO
	 * @return List
	 */
	public List selectMaintMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) {
		return list("gamFcltyMaintMngDao.selectMaintMngFcltsAtchFileList_D", searchVO);
	}

	/**
	 * @name insertMaintMngFcltsAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 */
	public void insertMaintMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		insert("gamFcltyMaintMngDao.insertMaintMngFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name updateMaintMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void updateMaintMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		update("gamFcltyMaintMngDao.updateMaintMngFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name deleteMaintMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void deleteMaintMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		delete("gamFcltyMaintMngDao.deleteMaintMngFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name deleteMaintMngAllFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 */
	public void deleteMaintMngAllFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		delete("gamFcltyMaintMngDao.deleteMaintMngAllFcltsAtchFile_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name deleteMaintMngFcltsAtchFileMulti
	 * @param deleteVO
	 * @return void
	 */
	public void deleteMaintMngFcltsAtchFileMulti(Map<?, ?> deleteVO) {
		delete("gamFcltyMaintMngDao.deleteMaintMngFcltsAtchFileMulti_S", deleteVO);
	}

	/**
	 * @name selectMaintMngFcltsAtchFilePk
	 * @param gamFcltsAtchFileMngVO
	 * @return EgovMap
	 */
	public EgovMap selectMaintMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (EgovMap)selectByPk("gamFcltyMaintMngDao.selectMaintMngFcltsAtchFilePk_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @name selectMaintMngFcltsAtchFileNewNo
	 * @param gamFcltsAtchFileMngVO
	 * @return String
	 */
	public String selectMaintMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (String)selectByPk("gamFcltyMaintMngDao.selectMaintMngFcltsAtchFileNewNo_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectMaintMngMntnRprDtlsList(GamMntnRprDtlsVO searchVO) {
		return list("gamFcltyMaintMngDao.selectMaintMngMntnRprDtlsList_D", searchVO);
	}


	/**
	 * @param searchVO
	 * @return
	 */
	public List<?> selectFcltyMaintMngListPrint(GamFcltyMaintMngVO searchVO) {
		// TODO Auto-generated method stub
		return list("gamFcltyMaintMngDao.selectFcltyMaintMngListPrint_D", searchVO);
	}



}
