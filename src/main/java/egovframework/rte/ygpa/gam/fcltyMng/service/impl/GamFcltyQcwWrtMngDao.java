/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcwWrtMngVO;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairMngVO;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamQcMngAtchFileMngVO;

/**
 *
 * @author 김종민
 * @since 2014. 11. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 24.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamFcltyQcwWrtMngDao")
public class GamFcltyQcwWrtMngDao extends YGPAAbstractDAO {
	/**
	 * 점검관리내역 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectQcMngDtlsList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectQcMngDtlsList_D", searchVO);
	}

	/**
	 * 점검관리내역 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectQcMngDtlsListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyQcwWrtMngDao.selectQcMngDtlsListTotCnt_S", searchVO);
	}

	/**
	 * 점검관리내역 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */
	public EgovMap selectQcMngDtlsDetail(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamFcltyQcwWrtMngDao.selectQcMngDtlsDetail_S", searchVO);
	}

	/**
	 * 점검관리순번 가져오기
	 * @param vo
	 * @return String
	 * @throws Exception
	 */
	public String selectMaxQcMngSeq(Map<?, ?> vo) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("gamFcltyQcwWrtMngDao.selectMaxQcMngSeq_S", vo);
	}

	/**
	 * 점검관리내역 데이터 삽입
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void insertQcMngDtls(Map<?, ?> vo) throws Exception {
		insert("gamFcltyQcwWrtMngDao.insertQcMngDtls_S", vo);
	}

	/**
	 * 점검관리내역 데이터 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void updateQcMngDtls(Map<?, ?> vo) throws Exception {
		update("gamFcltyQcwWrtMngDao.updateQcMngDtls_S", vo);
	}

	/**
	 * 점검관리내역 데이터 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void deleteQcMngDtls(Map<?, ?> vo) throws Exception {
		delete("gamFcltyQcwWrtMngDao.deleteQcMngDtls_S", vo);
	}

	/**
	 * 점검관리첨부파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectQcMngAtchPictureFileList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectQcMngAtchPictureFileList_D", searchVO);
	}

	/**
	 * 점검관리첨부파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectQcMngAtchFileList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectQcMngAtchFileList_D", searchVO);
	}

	/**
	 * 점검관리첨부파일 데이터 삽입
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public void insertQcMngAtchFile(Map<?, ?> vo) throws Exception {
		insert("gamFcltyQcwWrtMngDao.insertQcMngAtchFile_S", vo);
	}

	/**
	 * 점검관리첨부파일 목록 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void deleteQcMngAtchFileList(Map<?, ?> vo) throws Exception {
		delete("gamFcltyQcwWrtMngDao.deleteQcMngAtchFileList_S", vo);
	}


	/**
	 * 점검관리대상시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectQcMngObjFcltsList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectQcMngObjFcltsList_D", searchVO);
	}

	/**
	 * 점검관리대상시설물 삽입
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public void insertQcMngObjFclts(Map<?, ?> vo) throws Exception {
		insert("gamFcltyQcwWrtMngDao.insertQcMngObjFclts_S", vo);
	}

	/**
	 * 점검관리대상시설물 목록 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void deleteQcMngObjFcltsList(Map<?, ?> vo) throws Exception {
		delete("gamFcltyQcwWrtMngDao.deleteQcMngObjFcltsList_S", vo);
	}


	/**
	 * 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectQcMngResultItemList_D", searchVO);
	}

	/**
	 * 점검관리결과항목 목록 갯수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectQcMngResultItemListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyQcwWrtMngDao.selectQcMngResultItemListTotCnt_S", searchVO);
	}

	/**
	 * 기계 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectMechQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectMechQcMngResultItemList_D", searchVO);
	}

	/**
	 * 점검관리결과항목 삽입
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public void insertQcMngResultItem(Map<?, ?> vo) throws Exception {
		insert("gamFcltyQcwWrtMngDao.insertQcMngResultItem_S", vo);
	}

	/**
	 * 점검관리결과항목 목록 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void deleteQcMngResultItemList(Map<?, ?> vo) throws Exception {
		delete("gamFcltyQcwWrtMngDao.deleteQcMngResultItemList_S", vo);
	}


	/**
	 * 관리그룹 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */
	public EgovMap selectFcltsMngGroupInfo(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamFcltyQcwWrtMngDao.selectFcltsMngGroupInfo_S", searchVO);
	}



	/**
	 * @name selectFcltyQcwWrtMngQcMngAtchFileList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyQcwWrtMngQcMngAtchFileList(GamQcMngAtchFileMngVO searchVO) {
		return list("gamFcltyQcwWrtMngDao.selectFcltyQcwWrtMngQcMngAtchFileList_D", searchVO);
	}

	/**
	 * @name insertFcltyQcwWrtMngQcMngAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 */
	public void insertFcltyQcwWrtMngQcMngAtchFile(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) {
		insert("gamFcltyQcwWrtMngDao.insertFcltyQcwWrtMngQcMngAtchFile_S", gamQcMngAtchFileMngVO);
	}

	/**
	 * @name updateFcltyQcwWrtMngQcMngAtchFile
	 * @param gamQcMngAtchFileMngVO
	 * @return void
	 */
	public void updateFcltyQcwWrtMngQcMngAtchFile(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) {
		update("gamFcltyQcwWrtMngDao.updateFcltyQcwWrtMngQcMngAtchFile_S", gamQcMngAtchFileMngVO);
	}

	/**
	 * @name deleteFcltyQcwWrtMngQcMngAtchFile
	 * @param gamQcMngAtchFileMngVO
	 * @return void
	 */
	public void deleteFcltyQcwWrtMngQcMngAtchFile(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) {
		delete("gamFcltyQcwWrtMngDao.deleteFcltyQcwWrtMngQcMngAtchFile_S", gamQcMngAtchFileMngVO);
	}

	/**
	 * @name deleteFcltyQcwWrtMngQcMngAtchFileMulti
	 * @param deleteVO
	 * @return void
	 */
	public void deleteFcltyQcwWrtMngQcMngAtchFileMulti(Map<?, ?> deleteVO) {
		delete("gamFcltyQcwWrtMngDao.deleteFcltyQcwWrtMngQcMngAtchFileMulti_S", deleteVO);
	}

	/**
	 * @name deleteFcltyQcwWrtMngAllQcMngAtchFile
	 * @param gamQcMngAtchFileMngVO
	 * @return void
	 */
	public void deleteFcltyQcwWrtMngAllQcMngAtchFile(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) {
		delete("gamFcltyQcwWrtMngDao.deleteFcltyQcwWrtMngAllQcMngAtchFile_S", gamQcMngAtchFileMngVO);
	}

	/**
	 * @name selectFcltyQcwWrtMngQcMngAtchFilePk
	 * @param gamQcMngAtchFileMngVO
	 * @return EgovMap
	 */
	public EgovMap selectFcltyQcwWrtMngQcMngAtchFilePk(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) {
		return (EgovMap)selectByPk("gamFcltyQcwWrtMngDao.selectFcltyQcwWrtMngQcMngAtchFilePk_S", gamQcMngAtchFileMngVO);
	}

	/**
	 * @name selectFcltyQcwWrtMngQcMngAtchFileNewSeq
	 * @param gamQcMngAtchFileMngVO
	 * @return String
	 */
	public String selectFcltyQcwWrtMngQcMngAtchFileNewSeq(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) {
		return (String)selectByPk("gamFcltyQcwWrtMngDao.selectFcltyQcwWrtMngQcMngAtchFileNewSeq_S", gamQcMngAtchFileMngVO);
	}
	
	/**
	 * 점검자의 정보를 검색한다.
	 * */
	public EgovMap selectChargerInfo(Map<String, String> charger) throws Exception{
		return (EgovMap) selectByPk("gamFcltyQcwWrtMngDao.selectCharger_S", charger);
	}

}
