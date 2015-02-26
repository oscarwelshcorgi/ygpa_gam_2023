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

public interface GamArchFcltySpecMngService {

	/**
	 * @name selectArchFcltySpecMngList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectArchFcltySpecMngList(GamArchFcltySpecMngVO searchVO) throws Exception;

	/**
	 * @name insertArchFcltySpecMng
	 * @param gamArchFcltySpecMngVO
	 * @return void
	 * @throws Exception
	 */
	void insertArchFcltySpecMng(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) throws Exception;

	/**
	 * @name updateArchFcltySpecMng
	 * @param gamArchFcltySpecMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateArchFcltySpecMng(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) throws Exception;

	/**
	 * @name deleteArchFcltySpecMng
	 * @param gamArchFcltySpecMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteArchFcltySpecMng(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) throws Exception;

	/**
	 * @name selectArchFcltySpecMngPk
	 * @param gamArchFcltySpecMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectArchFcltySpecMngPk(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) throws Exception;

	/**
	 * @name selectArchFcltySpecMngListSum
	 * @param searchVO
	 * @return GamArchFcltySpecMngVO
	 * @throws Exception
	 */
	GamArchFcltySpecMngVO selectArchFcltySpecMngListSum(GamArchFcltySpecMngVO searchVO) throws Exception;

	/**
	 * @name selectArchFcltySpecMngMaxGisPrtFcltySeq
	 * @param gamArchFcltySpecMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectArchFcltySpecMngMaxGisPrtFcltySeq(GamArchFcltySpecMngVO gamArchFcltySpecMngVO) throws Exception;


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
	 * @name selectArchFcltySpecMngAtchFileDirList
	 * @param gamAtchFileDirMngVO
	 * @return List
	 * @throws Exception
	 */
	List selectArchFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name selectArchFcltySpecMngAtchFileDirPk
	 * @param gamAtchFileDirMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectArchFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name insertArchFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @throws Exception
	 */
	void insertArchFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name updateArchFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @throws Exception
	 */
	void updateArchFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name deleteArchFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @throws Exception
	 */
	void deleteArchFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name selectArchFcltySpecMngAtchFileDirNewNo
	 * @param gamAtchFileDirMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectArchFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name selectArchFcltySpecMngAtchFileDirLowerDataCnt
	 * @param gamAtchFileDirMngVO
	 * @return List
	 * @throws Exception
	 */
	List selectArchFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;


	/**
	 * @name selectArchFcltySpecMngFcltsAtchFileList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectArchFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception;

	/**
	 * @name insertArchFcltySpecMngFcltsAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 * @throws Exception
	 */
	void insertArchFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name updateArchFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateArchFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name deleteArchFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteArchFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name deleteArchFcltySpecMngFcltsAtchFileMulti
	 * @param deleteVO
	 * @return void
	 * @throws Exception
	 */
	void deleteArchFcltySpecMngFcltsAtchFileMulti(Map<?, ?> deleteVO) throws Exception;

	/**
	 * @name selectArchFcltySpecMngFcltsAtchFilePk
	 * @param gamFcltsAtchFileMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectArchFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name selectArchFcltySpecMngFcltsAtchFileNewNo
	 * @param gamFcltsAtchFileMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectArchFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

}
