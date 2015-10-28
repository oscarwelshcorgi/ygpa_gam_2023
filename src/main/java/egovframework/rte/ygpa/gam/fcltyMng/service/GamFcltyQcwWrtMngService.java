/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

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

public interface GamFcltyQcwWrtMngService {
	/**
	 * 점검관리내역 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectQcMngDtlsList(GamFcltyQcwWrtMngVO searchVO) throws Exception;

	/**
	 * 점검관리내역 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectQcMngDtlsListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception;

	/**
	 * 점검관리내역 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectQcMngDtlsDetail(GamFcltyQcwWrtMngVO searchVO) throws Exception;

	/**
	 * 점검관리내역 데이터 삽입
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	void insertQcMngDtls(Map<String, String> detailForm, List<HashMap<String, String>> qcObjList,  List<HashMap<String, String>> qcResultList) throws Exception;

	/**
	 * 점검관리내역 데이터 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	void updateQcMngDtls(Map<String, String> detailForm, List<HashMap<String, String>> qcObjList,  List<HashMap<String, String>> qcResultList) throws Exception;

	/**
	 * 점검관리내역 데이터 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	void deleteQcMngDtls(Map<?, ?> vo) throws Exception;

	/**
	 * 점검관리대상시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectQcMngObjFcltsList(GamFcltyQcwWrtMngVO searchVO) throws Exception;

	/**
	 * 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception;

	/**
	 * 점검관리결과항목 목록 개수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	int selectQcMngResultItemListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception;

	/**
	 * 기계 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	List<?> selectMechQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception;


	/**
	 * 관리그룹 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectFcltsMngGroupInfo(GamFcltyQcwWrtMngVO searchVO) throws Exception;



	/**
	 * @name selectFcltyQcwWrtMngQcMngAtchFileList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyQcwWrtMngQcMngAtchFileList(GamQcMngAtchFileMngVO searchVO) throws Exception;

	/**
	 * @name insertFcltyQcwWrtMngQcMngAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 * @throws Exception
	 */
	void insertFcltyQcwWrtMngQcMngAtchFile(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception;

	/**
	 * @name updateFcltyQcwWrtMngQcMngAtchFile
	 * @param gamQcMngAtchFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateFcltyQcwWrtMngQcMngAtchFile(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception;

	/**
	 * @name deleteFcltyQcwWrtMngQcMngAtchFile
	 * @param gamQcMngAtchFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteFcltyQcwWrtMngQcMngAtchFile(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception;

	/**
	 * @name deleteFcltyQcwWrtMngQcMngAtchFileMulti
	 * @param deleteVO
	 * @return void
	 * @throws Exception
	 */
	void deleteFcltyQcwWrtMngQcMngAtchFileMulti(Map<?, ?> deleteVO) throws Exception;

	/**
	 * @name deleteFcltyQcwWrtMngAllQcMngAtchFile
	 * @param gamQcMngAtchFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteFcltyQcwWrtMngAllQcMngAtchFile(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception;

	/**
	 * @name selectFcltyQcwWrtMngQcMngAtchFilePk
	 * @param gamQcMngAtchFileMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectFcltyQcwWrtMngQcMngAtchFilePk(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception;

	/**
	 * @name selectFcltyQcwWrtMngQcMngAtchFileNewSeq
	 * @param gamQcMngAtchFileMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectFcltyQcwWrtMngQcMngAtchFileNewSeq(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception;

	
	String selectQcMngResultListReportHWPML(GamFcltyQcwWrtMngVO searchVO) throws Exception;	
}
