/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 
 * @author LFIT
 * @since 2015. 3. 9.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 9.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamInfoCommFcltySpecInqireService {

	/**
	 * @param searchVO
	 * @return
	 */
	List selectInfoCommFcltySpecInqireList(GamInfoCommFcltySpecInqireVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	GamInfoCommFcltySpecInqireVO selectInfoCommFcltySpecInqireListSum(GamInfoCommFcltySpecInqireVO searchVO) throws Exception;

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	List selectInfoCommFcltySpecInqireAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectInfoCommFcltySpecInqireFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception;

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	EgovMap selectInfoCommFcltySpecInqireAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @param gamFcltsAtchFileMngVO
	 * @return
	 */
	EgovMap selectInfoCommFcltySpecInqireFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	String selectFcltsMngGroupNm(Map searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectInfoCommFcltySpecInqireMntnRprDtlsList(GamMntnRprDtlsVO searchVO) throws Exception;

}
