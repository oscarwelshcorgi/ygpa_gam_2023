/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamArchFcltySpecInqireVO;
import egovframework.rte.ygpa.gam.fclty.service.GamAtchFileDirMngVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsAtchFileMngVO;

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

@Repository("gamArchFcltySpecInqireDao")
public class GamArchFcltySpecInqireDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectArchFcltySpecInqireList(GamArchFcltySpecInqireVO searchVO) {
		return list("gamArchFcltySpecInqireDao.selectArchFcltySpecInqireList_D", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public GamArchFcltySpecInqireVO selectArchFcltySpecInqireListSum(GamArchFcltySpecInqireVO searchVO) {
		return (GamArchFcltySpecInqireVO)selectByPk("gamArchFcltySpecInqireDao.selectArchFcltySpecInqireListSum_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectArchFcltySpecInqireFcltsAtchFileList(GamFcltsAtchFileMngVO searchVO) {
		return list("gamArchFcltySpecInqireDao.selectArchFcltySpecInqireFcltsAtchFileList_D", searchVO);
	}

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public List selectArchFcltySpecInqireAtchFileDirList(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return list("gamArchFcltySpecInqireDao.selectArchFcltySpecInqireAtchFileDirList_D", gamAtchFileDirMngVO);
	}

	/**
	 * @param gamAtchFileDirMngVO
	 * @return
	 */
	public EgovMap selectArchFcltySpecInqireAtchFileDirPk(GamAtchFileDirMngVO gamAtchFileDirMngVO) {
		return (EgovMap)selectByPk("gamArchFcltySpecInqireDao.selectArchFcltySpecInqireAtchFileDirPk_S", gamAtchFileDirMngVO);
	}

	/**
	 * @param gamFcltsAtchFileMngVO
	 * @return
	 */
	public EgovMap selectArchFcltySpecInqireFcltsAtchFilePk(GamFcltsAtchFileMngVO gamFcltsAtchFileMngVO) {
		return (EgovMap)selectByPk("gamArchFcltySpecInqireDao.selectArchFcltySpecInqireFcltsAtchFilePk_S", gamFcltsAtchFileMngVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public String selectFcltsMngGroupNm(Map<String, Object> searchVO) {
		return (String)selectByPk("gamArchFcltySpecInqireDao.selectFcltsMngGroupNm_S", searchVO);
	}

}
