/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

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

public interface GamCvlEngFcltySpecMngService {

	/**
	 * @name selectCvlEngFcltySpecMngList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectCvlEngFcltySpecMngList(GamCvlEngFcltySpecMngVO searchVO) throws Exception;

	/**
	 * @name insertCvlEngFcltySpecMng
	 * @param gamCvlEngFcltySpecMngVO
	 * @return void
	 * @throws Exception
	 */
	void insertCvlEngFcltySpecMng(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) throws Exception;

	/**
	 * @name updateCvlEngFcltySpecMng
	 * @param gamCvlEngFcltySpecMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateCvlEngFcltySpecMng(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) throws Exception;

	/**
	 * @name deleteCvlEngFcltySpecMng
	 * @param gamCvlEngFcltySpecMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteCvlEngFcltySpecMng(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) throws Exception;

	/**
	 * @name selectCvlEngFcltySpecMngPk
	 * @param gamCvlEngFcltySpecMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectCvlEngFcltySpecMngPk(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) throws Exception;

	/**
	 * @name selectCvlEngFcltySpecMngListSum
	 * @param searchVO
	 * @return GamCvlEngFcltySpecMngVO
	 * @throws Exception
	 */
	GamCvlEngFcltySpecMngVO selectCvlEngFcltySpecMngListSum(GamCvlEngFcltySpecMngVO searchVO) throws Exception;

	/**
	 * @name selectCvlEngFcltySpecMngMaxGisPrtFcltySeq
	 * @param gamCvlEngFcltySpecMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectCvlEngFcltySpecMngMaxGisPrtFcltySeq(GamCvlEngFcltySpecMngVO gamCvlEngFcltySpecMngVO) throws Exception;


	/**
	 * @name selectFcltsMngGroupNm
	 * @param searchVO
	 * @return String
	 * @throws Exception
	 */
	String selectFcltsMngGroupNm(Map searchVO) throws Exception;

	/**
	 * @name selectEntrpsNm
	 * @param searchVO
	 * @return String
	 * @throws Exception
	 */
	String selectEntrpsNm(Map searchVO) throws Exception;

	/**
	 * @name selectFcltsClCdNm
	 * @param searchVO
	 * @return String
	 * @throws Exception
	 */
	String selectFcltsClCdNm(Map searchVO) throws Exception;

	/**
	 * @name selectFcltsClCdList
	 * @param
	 * @return List
	 * @throws Exception
	 */
	public List selectFcltsClCdList() throws Exception;


	/**
	 * @name selectCvlEngFcltySpecMngAtchFileDirList
	 * @param gamAtchFileDirMngVO
	 * @return List
	 * @throws Exception
	 */
	List selectCvlEngFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name selectCvlEngFcltySpecMngAtchFileDirPk
	 * @param gamAtchFileDirMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectCvlEngFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name insertCvlEngFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @throws Exception
	 */
	void insertCvlEngFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name updateCvlEngFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @throws Exception
	 */
	void updateCvlEngFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name deleteCvlEngFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @throws Exception
	 */
	void deleteCvlEngFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name selectCvlEngFcltySpecMngAtchFileDirNewNo
	 * @param gamAtchFileDirMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectCvlEngFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name selectCvlEngFcltySpecMngAtchFileDirLowerDataCnt
	 * @param gamAtchFileDirMngVO
	 * @return List
	 * @throws Exception
	 */
	List selectCvlEngFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;


	/**
	 * @name selectCvlEngFcltySpecMngFcltsAtchFileList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectCvlEngFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception;

	/**
	 * @name insertCvlEngFcltySpecMngFcltsAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 * @throws Exception
	 */
	void insertCvlEngFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name updateCvlEngFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateCvlEngFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name deleteCvlEngFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteCvlEngFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name deleteCvlEngFcltySpecMngFcltsAtchFileMulti
	 * @param deleteVO
	 * @return void
	 * @throws Exception
	 */
	void deleteCvlEngFcltySpecMngFcltsAtchFileMulti(Map<?, ?> deleteVO) throws Exception;

	/**
	 * @name selectCvlEngFcltySpecMngFcltsAtchFilePk
	 * @param gamFcltsAtchFileMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectCvlEngFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name selectCvlEngFcltySpecMngFcltsAtchFileNewNo
	 * @param gamFcltsAtchFileMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectCvlEngFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;


	/**
	 * @name selectCvlEngFcltySpecMngMntnRprDtlsList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectCvlEngFcltySpecMngMntnRprDtlsList(GamMntnRprDtlsVO searchVO) throws Exception;

}
