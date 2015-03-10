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
 * @since 2015. 3. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 5.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamArchFcltySpecInqireService {

	/**
	 * @param searchVO
	 * @return
	 */
	List selectArchFcltySpecInqireList(GamArchFcltySpecInqireVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	GamArchFcltySpecInqireVO selectArchFcltySpecInqireListSum(GamArchFcltySpecInqireVO searchVO) throws Exception;

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	List selectArchFcltySpecInqireAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;
	
	/**
	 * @param searchVO
	 * @return
	 */
	List selectArchFcltySpecInqireFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception;

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	EgovMap selectArchFcltySpecInqireAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @param gamFcltsAtchFileMngVO
	 * @return
	 */
	EgovMap selectArchFcltySpecInqireFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	String selectFcltsMngGroupNm(Map searchVO);
	
}
