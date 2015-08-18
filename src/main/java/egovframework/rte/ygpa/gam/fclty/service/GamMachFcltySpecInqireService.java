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
 * @since 2015. 3. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 10.		LFIT		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamMachFcltySpecInqireService {

	/**
	 * @param searchVO
	 * @return
	 */
	List selectMachFcltySpecInqireList(GamMachFcltySpecInqireVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	GamMachFcltySpecInqireVO selectMachFcltySpecInqireListSum(GamMachFcltySpecInqireVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectMachFcltySpecInqireCvlEngStatusList(GamMachFcltySpecInqireVO searchVO) throws Exception;

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	List gamSelectMachFcltySpecInqireAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectMachFcltySpecInqireFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) throws Exception;

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	EgovMap selectMachFcltySpecInqireAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) throws Exception;

	/**
	 * @param gamFcltsAtchFileMngVO
	 * @return
	 */
	EgovMap selectMachFcltySpecInqireFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	String selectFcltsMngGroupNm(Map searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectMachFcltySpecInqireMntnRprDtlsList(GamMntnRprDtlsVO searchVO) throws Exception;

	
	
}
