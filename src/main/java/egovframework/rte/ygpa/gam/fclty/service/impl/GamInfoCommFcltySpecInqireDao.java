/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamInfoCommFcltySpecInqireVO;
import egovframework.rte.ygpa.gam.fclty.service.GamMntnRprDtlsVO;

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

@Repository("gamInfoCommFcltySpecInqireDao")
public class GamInfoCommFcltySpecInqireDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectInfoCommFcltySpecInqireList(GamInfoCommFcltySpecInqireVO searchVO) {
		return list("gamInfoCommFcltySpecInqireDao.selectInfoCommFcltySpecInqireList_D", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public GamInfoCommFcltySpecInqireVO selectInfoCommFcltySpecInqireListSum(GamInfoCommFcltySpecInqireVO searchVO) {
		return (GamInfoCommFcltySpecInqireVO) selectByPk("gamInfoCommFcltySpecInqireDao.selectInfoCommFcltySpecInqireListSum_S", searchVO);
	}

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public List selectInfoCommFcltySpecInqireAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return list("gamInfoCommFcltySpecInqireDao.selectInfoCommFcltySpecInqireAtchFileDirList_D", gamAtchFileDirMngVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectInfoCommFcltySpecInqireFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) {
		return list("gamInfoCommFcltySpecInqireDao.selectInfoCommFcltySpecInqireFcltsAtchFileList_D", searchVO);
	}

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public EgovMap selectInfoCommFcltySpecInqireAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (EgovMap) selectByPk("gamInfoCommFcltySpecInqireDao.selectInfoCommFcltySpecInqireAtchFileDirPk_S", gamAtchFileDirMngVO);
	}

	/**
	 * @param gamFcltsAtchFileMngVO
	 * @return
	 */
	public EgovMap selectInfoCommFcltySpecInqireFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (EgovMap) selectByPk("gamInfoCommFcltySpecInqireDao.selectInfoCommFcltySpecInqireFcltsAtchFilePk_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public String selectFcltsMngGroupNm(Map searchVO) {
		return (String) selectByPk("gamInfoCommFcltySpecInqireDao.selectFcltsMngGroupNm_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectInfoCommFcltySpecInqireMntnRprDtlsList(GamMntnRprDtlsVO searchVO) {
		return list("gamInfoCommFcltySpecInqireDao.selectInfoCommFcltySpecInqireMntnRprDtlsList_D", searchVO);
	}

}
