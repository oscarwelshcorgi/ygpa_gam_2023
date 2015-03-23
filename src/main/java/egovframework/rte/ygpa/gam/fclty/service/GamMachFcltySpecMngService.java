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

public interface GamMachFcltySpecMngService {

	/**
	 * @name selectMachFcltySpecMngList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectMachFcltySpecMngList(GamMachFcltySpecMngVO searchVO) throws Exception;

	/**
	 * @name selectMachFcltySpecMngCvlEngStatusList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectMachFcltySpecMngCvlEngStatusList(GamMachFcltySpecMngVO searchVO) throws Exception;

	/**
	 * @name insertMachFcltySpecMng
	 * @param gamMachFcltySpecMngVO
	 * @return void
	 * @throws Exception
	 */
	void insertMachFcltySpecMng(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) throws Exception;

	/**
	 * @name updateMachFcltySpecMng
	 * @param gamMachFcltySpecMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateMachFcltySpecMng(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) throws Exception;

	/**
	 * @name deleteMachFcltySpecMng
	 * @param gamMachFcltySpecMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteMachFcltySpecMng(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) throws Exception;

	/**
	 * @name selectMachFcltySpecMngPk
	 * @param gamMachFcltySpecMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectMachFcltySpecMngPk(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) throws Exception;

	/**
	 * @name selectMachFcltySpecMngListSum
	 * @param searchVO
	 * @return GamMachFcltySpecMngVO
	 * @throws Exception
	 */
	GamMachFcltySpecMngVO selectMachFcltySpecMngListSum(GamMachFcltySpecMngVO searchVO) throws Exception;

	/**
	 * @name selectMachFcltySpecMngMaxGisPrtFcltySeq
	 * @param gamMachFcltySpecMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectMachFcltySpecMngMaxGisPrtFcltySeq(GamMachFcltySpecMngVO gamMachFcltySpecMngVO) throws Exception;


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
	 * @name selectMachFcltySpecMngAtchFileDirList
	 * @param gamAtchFileDirMngVO
	 * @return List
	 * @throws Exception
	 */
	List selectMachFcltySpecMngAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name selectMachFcltySpecMngAtchFileDirPk
	 * @param gamAtchFileDirMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectMachFcltySpecMngAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name insertMachFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @throws Exception
	 */
	void insertMachFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name updateMachFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @throws Exception
	 */
	void updateMachFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name deleteMachFcltySpecMngAtchFileDir
	 * @param gamAtchFileDirMngVO
	 * @throws Exception
	 */
	void deleteMachFcltySpecMngAtchFileDir(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name selectMachFcltySpecMngAtchFileDirNewNo
	 * @param gamAtchFileDirMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectMachFcltySpecMngAtchFileDirNewNo(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @name selectMachFcltySpecMngAtchFileDirLowerDataCnt
	 * @param gamAtchFileDirMngVO
	 * @return List
	 * @throws Exception
	 */
	List selectMachFcltySpecMngAtchFileDirLowerDataCnt(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;


	/**
	 * @name selectMachFcltySpecMngFcltsAtchFileList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectMachFcltySpecMngFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception;

	/**
	 * @name insertMachFcltySpecMngFcltsAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 * @throws Exception
	 */
	void insertMachFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name updateMachFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateMachFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name deleteMachFcltySpecMngFcltsAtchFile
	 * @param gamFcltsAtchFileMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteMachFcltySpecMngFcltsAtchFile(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name selectMachFcltySpecMngFcltsAtchFilePk
	 * @param gamFcltsAtchFileMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectMachFcltySpecMngFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @name selectMachFcltySpecMngFcltsAtchFileNewNo
	 * @param gamFcltsAtchFileMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectMachFcltySpecMngFcltsAtchFileNewNo(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;


	/**
	 * @name selectMachFcltySpecMngMachFcltySttusList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectMachFcltySpecMngMachFcltySttusList(GamMachFcltySttusMngVO searchVO) throws Exception;

	/**
	 * @name insertMachFcltySpecMngMachFcltySttus
	 * @param gamMachFcltySttusMngVO
	 * @return void
	 * @throws Exception
	 */
	void insertMachFcltySpecMngMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception;

	/**
	 * @name updateMachFcltySpecMngMachFcltySttus
	 * @param gamMachFcltySttusMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateMachFcltySpecMngMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception;

	/**
	 * @name deleteMachFcltySpecMngMachFcltySttus
	 * @param gamMachFcltySttusMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteMachFcltySpecMngMachFcltySttus(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception;

	/**
	 * @name selectMachFcltySpecMngMachFcltySttusPk
	 * @param gamMachFcltySttusMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectMachFcltySpecMngMachFcltySttusPk(GamMachFcltySttusMngVO gamMachFcltySttusMngVO) throws Exception;

}
