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
import egovframework.rte.ygpa.gam.fclty.service.GamCvlEngFcltySpecInqireVO;
import egovframework.rte.ygpa.gam.fclty.service.GamCvlEngFcltySpecMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;

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

@Repository("gamCvlEngFcltySpecInqireDao")
public class GamCvlEngFcltySpecInqireDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectCvlEngFcltySpecInqireList(GamCvlEngFcltySpecInqireVO searchVO) {
		return list("gamCvlEngFcltySpecInqireDao.selectCvlEngFcltySpecInqireList_D", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public GamCvlEngFcltySpecInqireVO selectCvlEngFcltySpecInqireListSum(GamCvlEngFcltySpecInqireVO searchVO) {
		return (GamCvlEngFcltySpecInqireVO)selectByPk("gamCvlEngFcltySpecInqireDao.selectCvlEngFcltySpecInqireListSum_S", searchVO);
	}

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public List selectCvlEngFcltySpecInqireAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return list("gamCvlEngFcltySpecInqireDao.selectCvlEngFcltySpecInqireAtchFileDirList_D", gamAtchFileDirMngVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectCvlEngFcltySpecInqireFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) {
		return list("gamCvlEngFcltySpecInqireDao.selectCvlEngFcltySpecInqireFcltsAtchFileList_D", searchVO);
	}

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public EgovMap selectCvlEngFcltySpecInqireAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (EgovMap)selectByPk("gamCvlEngFcltySpecInqireDao.selectCvlEngFcltySpecInqireAtchFileDirPk_S", gamAtchFileDirMngVO);
	}

	/**
	 * @param gamFcltsAtchFileMngVO
	 * @return
	 */
	public EgovMap selectCvlEngFcltySpecInqireFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (EgovMap)selectByPk("gamCvlEngFcltySpecInqireDao.selectCvlEngFcltySpecInqireFcltsAtchFilePk_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public String selectFcltsMngGroupNm(Map searchVO) {
		return (String)selectByPk("gamCvlEngFcltySpecInqireDao.selectFcltsMngGroupNm_S", searchVO);
	}

	
	
}
