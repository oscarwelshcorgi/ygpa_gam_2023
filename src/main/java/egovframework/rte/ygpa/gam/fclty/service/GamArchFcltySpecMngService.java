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
	 * @name selectArchFcltySpecMngAtchFileList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectArchFcltySpecMngAtchFileList(GamFcltySpecAtchFileVO searchVO) throws Exception;

	/**
	 * @name insertArchFcltySpecMngAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 * @throws Exception
	 */
	void insertArchFcltySpecMngAtchFile(GamFcltySpecAtchFileVO gamFcltySpecAtchFileVO) throws Exception;

	/**
	 * @name uploadArchFcltySpecMngAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 * @throws Exception
	 */
	void uploadArchFcltySpecMngAtchFile(GamFcltySpecAtchFileVO gamFcltySpecAtchFileVO) throws Exception;

	/**
	 * @name updateArchFcltySpecMngAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 * @throws Exception
	 */
	void updateArchFcltySpecMngAtchFile(GamFcltySpecAtchFileVO gamFcltySpecAtchFileVO) throws Exception;

	/**
	 * @name deleteArchFcltySpecMngAtchFile
	 * @param gamFcltySpecAtchFileVO
	 * @return void
	 * @throws Exception
	 */
	void deleteArchFcltySpecMngAtchFile(GamFcltySpecAtchFileVO gamFcltySpecAtchFileVO) throws Exception;

	/**
	 * @name selectArchFcltySpecMngAtchFilePk
	 * @param gamFcltySpecAtchFileVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectArchFcltySpecMngAtchFilePk(GamFcltySpecAtchFileVO gamFcltySpecAtchFileVO) throws Exception;

	/**
	 * @name selectArchFcltySpecMngAtchFileMaxSeq
	 * @param gamFcltySpecAtchFileVO
	 * @return String
	 * @throws Exception
	 */
	String selectArchFcltySpecMngAtchFileMaxSeq(GamFcltySpecAtchFileVO gamFcltySpecAtchFileVO) throws Exception;


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

}
