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

public interface GamInfoCommFcltySpecMngService {

	/**
	 * @name selectInfoCommFcltySpecMngList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectInfoCommFcltySpecMngList(GamInfoCommFcltySpecMngVO searchVO) throws Exception;

	/**
	 * @name insertInfoCommFcltySpecMng
	 * @param gamInfoCommFcltySpecMngVO
	 * @return void
	 * @throws Exception
	 */
	void insertInfoCommFcltySpecMng(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) throws Exception;

	/**
	 * @name updateInfoCommFcltySpecMng
	 * @param gamInfoCommFcltySpecMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateInfoCommFcltySpecMng(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) throws Exception;

	/**
	 * @name deleteInfoCommFcltySpecMng
	 * @param gamInfoCommFcltySpecMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteInfoCommFcltySpecMng(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) throws Exception;

	/**
	 * @name selectInfoCommFcltySpecMngPk
	 * @param gamInfoCommFcltySpecMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectInfoCommFcltySpecMngPk(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) throws Exception;

	/**
	 * @name selectInfoCommFcltySpecMngListSum
	 * @param searchVO
	 * @return GamInfoCommFcltySpecMngVO
	 * @throws Exception
	 */
	GamInfoCommFcltySpecMngVO selectInfoCommFcltySpecMngListSum(GamInfoCommFcltySpecMngVO searchVO) throws Exception;

	/**
	 * @name selectInfoCommFcltySpecMngMaxGisPrtFcltySeq
	 * @param gamInfoCommFcltySpecMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectInfoCommFcltySpecMngMaxGisPrtFcltySeq(GamInfoCommFcltySpecMngVO gamInfoCommFcltySpecMngVO) throws Exception;


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
	 * @name selectGisPrtFcltyNm
	 * @param searchVO
	 * @return String
	 * @throws Exception
	 */
	String selectGisPrtFcltyNm(Map searchVO) throws Exception;


	/**
	 * @name selectInfoCommFcltySpecMngAtchFileDirList
	 * @param gamAtchFileDirMngVO
	 * @return List
	 * @throws Exception
	 */
	List selectInfoCommFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name selectInfoCommFcltySpecMngAtchFileDirPk
	 * @param gamAtchFileDirMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectInfoCommFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name insertInfoCommFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @throws Exception
	 */
	void insertInfoCommFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name updateInfoCommFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @throws Exception
	 */
	void updateInfoCommFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name deleteInfoCommFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @throws Exception
	 */
	void deleteInfoCommFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name selectInfoCommFcltySpecMngAtchFileDirNewNo
	 * @param gamAtchFileDirMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectInfoCommFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name selectInfoCommFcltySpecMngAtchFileDirLowerDataCnt
	 * @param gamAtchFileDirMngVO
	 * @return List
	 * @throws Exception
	 */
	List selectInfoCommFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;


	/**
	 * @name selectInfoCommFcltySpecMngFcltsAtchFileList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectInfoCommFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception;

	/**
	 * @name insertInfoCommFcltySpecMngFcltsAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 * @throws Exception
	 */
	void insertInfoCommFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name updateInfoCommFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateInfoCommFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name deleteInfoCommFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteInfoCommFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name selectInfoCommFcltySpecMngFcltsAtchFilePk
	 * @param gamFcltsAtchFileMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectInfoCommFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name selectInfoCommFcltySpecMngFcltsAtchFileNewNo
	 * @param gamFcltsAtchFileMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectInfoCommFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

}
