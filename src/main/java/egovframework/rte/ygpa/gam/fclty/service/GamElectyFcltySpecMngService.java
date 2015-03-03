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

public interface GamElectyFcltySpecMngService {

	/**
	 * @name selectElectyFcltySpecMngList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectElectyFcltySpecMngList(GamElectyFcltySpecMngVO searchVO) throws Exception;

	/**
	 * @name insertElectyFcltySpecMng
	 * @param gamElectyFcltySpecMngVO
	 * @return void
	 * @throws Exception
	 */
	void insertElectyFcltySpecMng(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) throws Exception;

	/**
	 * @name updateElectyFcltySpecMng
	 * @param gamElectyFcltySpecMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateElectyFcltySpecMng(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) throws Exception;

	/**
	 * @name deleteElectyFcltySpecMng
	 * @param gamElectyFcltySpecMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteElectyFcltySpecMng(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) throws Exception;

	/**
	 * @name selectElectyFcltySpecMngPk
	 * @param gamElectyFcltySpecMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectElectyFcltySpecMngPk(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) throws Exception;

	/**
	 * @name selectElectyFcltySpecMngListSum
	 * @param searchVO
	 * @return GamElectyFcltySpecMngVO
	 * @throws Exception
	 */
	GamElectyFcltySpecMngVO selectElectyFcltySpecMngListSum(GamElectyFcltySpecMngVO searchVO) throws Exception;

	/**
	 * @name selectElectyFcltySpecMngMaxGisPrtFcltySeq
	 * @param gamElectyFcltySpecMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectElectyFcltySpecMngMaxGisPrtFcltySeq(GamElectyFcltySpecMngVO gamElectyFcltySpecMngVO) throws Exception;


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
	 * @name selectElectyFcltySpecMngAtchFileDirList
	 * @param gamAtchFileDirMngVO
	 * @return List
	 * @throws Exception
	 */
	List selectElectyFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name selectElectyFcltySpecMngAtchFileDirPk
	 * @param gamAtchFileDirMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectElectyFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name insertElectyFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @throws Exception
	 */
	void insertElectyFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name updateElectyFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @throws Exception
	 */
	void updateElectyFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name deleteElectyFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @throws Exception
	 */
	void deleteElectyFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name selectElectyFcltySpecMngAtchFileDirNewNo
	 * @param gamAtchFileDirMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectElectyFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name selectElectyFcltySpecMngAtchFileDirLowerDataCnt
	 * @param gamAtchFileDirMngVO
	 * @return List
	 * @throws Exception
	 */
	List selectElectyFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;


	/**
	 * @name selectElectyFcltySpecMngFcltsAtchFileList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectElectyFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception;

	/**
	 * @name insertElectyFcltySpecMngFcltsAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 * @throws Exception
	 */
	void insertElectyFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name updateElectyFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateElectyFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name deleteElectyFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteElectyFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name selectElectyFcltySpecMngFcltsAtchFilePk
	 * @param gamFcltsAtchFileMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectElectyFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name selectElectyFcltySpecMngFcltsAtchFileNewNo
	 * @param gamFcltsAtchFileMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectElectyFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

}
